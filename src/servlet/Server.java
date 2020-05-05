package servlet;

import entity.Emoji;
import entity.Word;
import service.EmojiService;
import service.UserService;
import service.WordService;
import service.serviceImpl.EmojiServiceImpl;
import service.serviceImpl.UserServiceImpl;
import service.serviceImpl.WordServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/message/server/{username}/{room}")
public class Server {

    /**
     * @ServerEndpoin 注解声明该类为 WebSocket 的服务端端点
     * value 值为监听客户端访问的 URL
     */

        //这里只是简单测试用，真正的场景请考虑线程安全的容器或其它并发解决方案
        private static List<Session> sessions = new ArrayList<>();

        /**
         * @param session  与客户端的会话对象【可选】
         * @param username 路径参数值 【可选】
         * @throws IOException
         * @OnOpen 标注此方法在 ws 连接建立时调用，可用来处理一些准备性工作 可选参数
         * EndpointConfig（端点配置信息对象） Session 连接会话对象
         */
        @OnOpen
        public void OnOpen(Session session, @PathParam("username") String username, @PathParam("room") String room) throws IOException {
            sessions.add(session);
            sendTextMsg("好友 [" + username + "] 加入群聊￥"+room);
        }

        /**
         * @param msg      接受客户端消息
         * @param username RESTful 路径方式获取用户名
         * @throws IOException
         * @OnMessage 在收到客户端消息调用 消息形式不限于文本消息，还可以是二进制消息(byte[]/ByteBuffer等)，ping/pong 消息
         */
        @OnMessage
        public void OnMsg(String msg, @PathParam("username") String username, @PathParam("room") String room) throws IOException {

            System.out.println("执行到server，room = " + room);
            WordService wordService = new WordServiceImpl();
            UserService userService = new UserServiceImpl();
            List<Word> wordList = wordService.findWords();
            boolean flag = true;
            //如果是图片的话，从msg里判断不出是不是图片
            if (msg.contains(".jpg") || msg.contains(".png")){
                //是表情包，不能使用该判断条件，因为传过来的是id
                int index = msg.lastIndexOf(".");
                String Id = msg.substring(0,index);
                EmojiService emojiService = new EmojiServiceImpl();
                int id = Integer.parseInt(Id);

                Emoji emoji = emojiService.getEmoji(id);
                sendTextMsg(emoji.getEmojiname() + ":" + username + "￥" + room/* userService.getUser(username).getUserid()*/);

            }else {
                //如果是文字消息
                for (Word word : wordList) {
                    if (msg.contains(word.getWordContent())) {
                        //包含了屏蔽字，无法发送该信息
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    sendTextMsg(/*username + "：" + */msg + ":" + username + "￥" + room);
                } else {
                    //包含了违禁词
                    sendTextMsg("对不起" + username + ",您的发言包含了违禁词！！！,如果超过十次，您会被系统强制禁言，请注意");
                }
            }
        }


        /**
         * @OnClose 连接关闭调用
         */
        @OnClose
        public void OnClose(Session session, @PathParam("username") String username, @PathParam("room") String room) throws IOException {
            sessions.remove(session);
            sendTextMsg("好友 ["+username + "] 退出聊天室￥" + room);
        }

        /**
         * @param e 异常参数
         * @OnError 连接出现错误调用
         */
        @OnError
        public void OnError(Throwable e) {
            e.printStackTrace();
        }

        //这里的session指的是所有与服务器建立连接的用户对话
        private void sendTextMsg(String msg) {
            for (Session session : sessions) {

                //再向各个用户发送的时候判断是不是一个房间的
                session.getAsyncRemote().sendText(msg);
            }
        }

}
