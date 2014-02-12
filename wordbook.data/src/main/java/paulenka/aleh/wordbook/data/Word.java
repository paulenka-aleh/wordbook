package paulenka.aleh.wordbook.data;

public class Word {

    private int id;
    private String word;
    private String explanation;

    public Word(String word, String explanation) {
        this.word = word;
        this.explanation = explanation;
    }

    public Word(int id, String word, String explanation) {
        this.id = id;
        this.word = word;
        this.explanation = explanation;
    }

    public Word(int id, String word) {
        this.id = id;
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}