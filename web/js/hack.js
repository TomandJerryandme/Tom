
function loadStyles() {
    var link = document.createElement("link");
    link.rel = "stylesheet";
    link.type = "text/css";
    link.href = "/css/hack.css";
    var head = document.getElementsByTagName("head")[0];
    head.appendChild(link);
}

function rand(min, max)
{
    return min + Math.round(Math.random() * (max - min));
}

function add()
{
    var x = rand(0, 1500);
    var html = '<div class="divText" style="left:' + x + 'px; bottom:500px;">';
    var color = [];
    for (var i=1; i<17; i++)
    {
        var f = i.toString(16);
        color.push('0' + f + '0');
    }
    var fontSize = rand(9, 24);
    for (var i=1; i<17; i++)
    {
        var c = rand(33, 127);
        var c = String.fromCharCode(c);
        html += '<span class="s' + i + '" style="color:#' + color[i-1] + '; font-size:' + fontSize + 'px; text-shadow:0px 0px 10px #' + color[i-1] + ';">' + c + '</span>';
    }
    html += '</div>';
    $('#divList').append(html);
}

function run()
{
    loadStyles();
    var x = rand(0, 100);
    if (x < 100)
    {
        add();
    }
    $('#spanCount').html($('.divText').size());
    $('.divText').each(function(){
        var y = $(this).css('bottom');
        y = parseInt(y);
        y -= $(this).find('span').eq(0).height();
        $(this).css('bottom', '' + y + 'px');
        if (y + $(this).height() <= 0)
        {
            $(this).remove();
            return;
        }
        $(this).find('span').each(function(){
            var c = rand(33, 127);
            var c = String.fromCharCode(c);
            $(this).html(c);
        });
    });
    window.setTimeout(run, 100);
}

run();