package paulenka.aleh.wordbook.constant;

public enum AuthorizationRole {
    ADMINISTRATOR(1), MODERATOR(2), USER(3);

    private AuthorizationRole(int roleId) {
        this.roleId = roleId;
    }

    private final int roleId;

    public int getRoleId() {
        return roleId;
    }

    public static AuthorizationRole getRoleById(int roleId) {
        for (AuthorizationRole role : AuthorizationRole.values()) {
            if (role.getRoleId() == roleId) {
                return role;
            }
        }
        return null;
    }
}