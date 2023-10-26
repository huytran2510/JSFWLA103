package fa.training.entities.enums;

public enum ERole {
    USER("USER"), ADMIN("ADMIN");
    private final String text;

    private ERole(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
