package entity;

public class Word {
    private Integer id;
    private String wordContent;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordContent='" + wordContent + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWordContent() {
        return wordContent;
    }

    public void setWordContent(String wordContent) {
        this.wordContent = wordContent;
    }
}
