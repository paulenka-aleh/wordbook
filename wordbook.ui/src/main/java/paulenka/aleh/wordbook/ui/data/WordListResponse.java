package paulenka.aleh.wordbook.ui.data;

import java.util.List;

import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.data.util.Entity;

public class WordListResponse extends Entity {

    private static final long serialVersionUID = 1L;

    private List<Word> words;
    private int total;

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}