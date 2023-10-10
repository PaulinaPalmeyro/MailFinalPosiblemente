package battle2023.ucp.Entities;
import java.util.List;
import java.util.ArrayList;

public class EmailManager {
    private List<Contact> contacts;
    private List<Mailbox> mailboxes;

    public EmailManager() {
        contacts = new ArrayList<>();
        mailboxes = new ArrayList<>();
    }

    public Contact createContact(String name, String email) {
        Contact contact = new Contact(name, email);
        contacts.add(contact);
        return contact;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Mailbox> getMailboxes() {
        return mailboxes;
    }
        


        


    
}











































































/*public class FilterContent implements MailFilter {
    private String content;

    public FilterContent(String content) {
        this.content = content;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getContent().contains(content);
    }
}
}
*/

/*public class FilterSenderEmail implements MailFilter {
    private String senderEmail;

    public FilterSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getSender().getEmail().equalsIgnoreCase(senderEmail);
    }
}*/

/*public class FilterSenderName implements MailFilter {
    private String senderName;

    public FilterSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getSender().getName().equalsIgnoreCase(senderName);
    }
} */

/*import battle2023.ucp.interfaces.MailFilter;
import java.util.List;

public class FilterRecipients implements MailFilter {
    private List<String> recipientNames;

    public FilterRecipients(List<String> recipientNames) {
        this.recipientNames = recipientNames;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        // Check if any of the recipient names in the email match the provided recipient names.
        return email.getRecipients().stream()
                .anyMatch(recipient -> recipientNames.contains(recipient.getName()));
    }
} */