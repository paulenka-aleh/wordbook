package paulenka.aleh.wordbook.dao.table;

public final class WordTable {

    private WordTable() {
    }

    public final static String TABLE = "word";

    public final static String ID = TABLE + "." + "id";
    public final static String WORD = TABLE + "." + "word";
    public final static String EXPLANATION = TABLE + "." + "explanation";
}