package battle2023.ucp.Entities;
import java.util.List;
import java.util.ArrayList;

public class Mailbox {
    private List<Email> sentEmails;
    private List<Email> receivedEmails;

    public Mailbox() {
        sentEmails = new ArrayList<>();
        receivedEmails = new ArrayList<>();
    }

    public void addSentEmail(Email email) {
        sentEmails.add(email);
    }

    public void addReceivedEmail(Email email) {
        receivedEmails.add(email);
    }

    public List<Email> getSentEmails() {
        return sentEmails;
    }

    public List<Email> getReceivedEmails() {
        return receivedEmails;
    }

    public static Mailbox senderMailbox(Contact sender, List<Mailbox> mailboxes) {
        for (Mailbox mailbox : mailboxes) {
            if (mailbox.getSentEmails().isEmpty()) {
                continue;
            }
            
        }
        Mailbox mailbox = new Mailbox();
        mailboxes.add(mailbox);
        return mailbox;
    }

    public static Mailbox recipientMailbox(Contact recipient, List<Mailbox> mailboxes) {
        for (Mailbox mailbox : mailboxes) {
            if (mailbox.getReceivedEmails().isEmpty()) {
                continue;
            }
            
        }
        Mailbox mailbox = new Mailbox();
        mailboxes.add(mailbox);
        return mailbox;
    }
}





















