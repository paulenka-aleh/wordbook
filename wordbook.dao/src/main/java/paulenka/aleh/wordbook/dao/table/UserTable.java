package paulenka.aleh.wordbook.dao.table;

public final class UserTable {

    private UserTable() {
    }

    public final static String TABLE = "user";

    public final static String ID = TABLE + "." + "id";
    public final static String USERNAME = TABLE + "." + "username";
    public final static String PASSWORD = TABLE + "." + "password";
}