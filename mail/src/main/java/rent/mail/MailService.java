package rent.mail;

public interface MailService {

    /**
     * Service that allows to send email.
     *
     * @param email - address that email should be send to
     * @param body  - body of the email
     */
    void sendEmail(String email, String body);

}
