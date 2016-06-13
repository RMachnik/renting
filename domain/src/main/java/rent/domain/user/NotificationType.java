package rent.domain.user;

public enum NotificationType {
    PAYMENT("Notification about payment day.", "Hi there, payment is coming."),
    NO_PAYMENT("Notification about missed payment.", "Hi, you've missed your payment."),
    INVOICE("Invoice notification.", "Hi, some invoice for you was settled.");

    private final String description;
    private final String defaultTemplate;

    NotificationType(String description, String defaultTemplate) {
        this.description = description;
        this.defaultTemplate = defaultTemplate;
    }

    public String getDefaultTemplate() {
        return defaultTemplate;
    }

    public String getDescription() {
        return description;
    }
}
