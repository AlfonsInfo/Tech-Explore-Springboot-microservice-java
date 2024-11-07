package spring.template.mediasocial.constant;
// enum for post draft, published, deleted
public enum PostEnum {
    DRAFT("draft"),
    PUBLISHED("published"),
    DELETED("archived");

    private final String value;

    PostEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
