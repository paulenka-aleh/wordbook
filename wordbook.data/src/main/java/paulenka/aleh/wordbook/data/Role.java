package paulenka.aleh.wordbook.data;

public enum Role {
    ADMINISTRATOR(1), MODERATOR(2), USER(3);

    private Role(int roleId) {
        this.roleId = roleId;
    }

    private final int roleId;

    public int getRoleId() {
        return roleId;
    }

    public static Role getRoleById(int roleId) {
        for (Role role : Role.values()) {
            if (role.getRoleId() == roleId) {
                return role;
            }
        }
        return null;
    }
}