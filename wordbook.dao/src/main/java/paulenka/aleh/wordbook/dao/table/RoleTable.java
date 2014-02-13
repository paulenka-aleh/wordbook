package paulenka.aleh.wordbook.dao.table;

public final class RoleTable {

    private RoleTable() {
    }

    public final static String TABLE = "role";

    public final static String ID = TABLE + "." + "id";
    public final static String ROLE_ID = TABLE + "." + "role_id";
    public final static String USER_ID = TABLE + "." + "user_id";
}