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
}