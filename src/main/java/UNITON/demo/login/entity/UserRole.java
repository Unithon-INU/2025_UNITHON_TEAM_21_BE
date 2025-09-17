package UNITON.demo.login.entity;

public enum UserRole {
    USER(0), ADMIN(1),ORG_ADMIN(1);

    private final int code;

    UserRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
