import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;

import java.util.List;


import org.junit.Before;
import org.junit.Test;

import battle2023.ucp.Entities.Mailbox;
import battle2023.ucp.Entities.Contact;
import battle2023.ucp.Entities.Email;
import battle2023.ucp.Entities.EmailManager;
import battle2023.ucp.Entities.Filter;
import battle2023.ucp.Entities.FilterKeyword;
import battle2023.ucp.Entities.FilterSubject;
import battle2023.ucp.Entities.FilterRecipientsName;
import battle2023.ucp.Entities.FilterSenderNameAndSubject;
import battle2023.ucp.Entities.FilterRecipientNameAndContentKeyword;

public class MailboxTest {
    private List<Mailbox> mailboxes;
    private Contact sender;
    private Contact recipient;
    private EmailManager emailManager;
    private Contact contact1;
    private Contact contact2;
    private Email email;

    @Before
    public void setUp() {
        mailboxes = new ArrayList<>();
        sender = new Contact("John Doe", "john.doe@example.com");
        recipient = new Contact("Jane Smith", "jane.smith@example.com");
        emailManager = new EmailManager();
        contact1 = emailManager.createContact("John Doe", "john@example.com");
        contact2 = emailManager.createContact("Jane Smith", "jane@example.com");
        email = Email.createEmail("Test Subject", "Test Content", sender, new ArrayList<>());
    }

    @Test
    public void testAddSentEmail() {
        Mailbox mailbox = Mailbox.senderMailbox(sender, mailboxes);
        Email email = Email.createEmail("Test Subject", "Test Content", sender, new ArrayList<>());

        mailbox.addSentEmail(email);

        assertTrue(mailbox.getSentEmails().contains(email));
    }

    @Test
    public void testAddReceivedEmail() {
        Mailbox mailbox = Mailbox.recipientMailbox(recipient, mailboxes);
        
        // Create an empty list of recipients
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient); // Add the recipient to the list
    
        Email email = Email.createEmail("Test Subject", "Test Content", sender, recipients);
    
        mailbox.addReceivedEmail(email);
    
        assertTrue(mailbox.getReceivedEmails().contains(email));
    }

    @Test
    public void testMailboxCreationForSender() {
        Contact sender1 = emailManager.createContact("Sender1", "sender1@example.com");
        Contact sender2 = emailManager.createContact("Sender2", "sender2@example.com");

        List<Mailbox> mailboxes = new ArrayList<>();
        Mailbox mailbox1 = Mailbox.senderMailbox(sender1, mailboxes);
        Mailbox mailbox2 = Mailbox.senderMailbox(sender2, mailboxes);

        assertEquals(2, mailboxes.size());
        assertTrue(mailboxes.contains(mailbox1));
        assertTrue(mailboxes.contains(mailbox2));
    }

    @Test
    public void testMailboxCreationForRecipient() {
        Contact recipient1 = emailManager.createContact("Recipient1", "recipient1@example.com");
        Contact recipient2 = emailManager.createContact("Recipient2", "recipient2@example.com");

        List<Mailbox> mailboxes = new ArrayList<>();
        Mailbox mailbox1 = Mailbox.recipientMailbox(recipient1, mailboxes);
        Mailbox mailbox2 = Mailbox.recipientMailbox(recipient2, mailboxes);

        assertEquals(2, mailboxes.size());
        assertTrue(mailboxes.contains(mailbox1));
        assertTrue(mailboxes.contains(mailbox2));
    }

    @Test
    public void testMailboxExistence() {
        Contact sender = emailManager.createContact("John Doe", "john@example.com");
        Contact recipient = emailManager.createContact("Alice Smith", "alice@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient);

        Email email = Email.createEmail("Test Subject", "Test Content", sender, recipients);

        Mailbox senderMailbox = Mailbox.senderMailbox(sender, emailManager.getMailboxes());
        assertNotNull(senderMailbox);

        Mailbox recipientMailbox = Mailbox.recipientMailbox(recipient, emailManager.getMailboxes());
        assertNotNull(recipientMailbox);
     

    }

    @Test
    public void testSendEmailToExistingRecipient() {
        // Create an email
        Email email = Email.createEmail("Test Subject", "Test Content", sender, new ArrayList<>());
        email.getRecipients().add(recipient);

        // Get the recipient's mailbox
        Mailbox recipientMailbox = Mailbox.recipientMailbox(recipient, mailboxes);

        // Send the email
        recipientMailbox.addReceivedEmail(email);

        // Check if the email is in the recipient's mailbox
        List<Email> receivedEmails = recipientMailbox.getReceivedEmails();
        assertTrue(receivedEmails.contains(email));
    }

    @Test
    public void testSendEmailToNonExistentMailbox() {
        // Create an email with a recipient that doesn't exist in any mailbox
        List<Mailbox> mailboxes = new ArrayList<>();
        Mailbox mailbox = Mailbox.senderMailbox(sender, mailboxes);
        emailManager.getContacts().remove(recipient); // Simulate that recipient doesn't exist in contacts

        // Send the email
        mailbox.addSentEmail(email);

        // Check if a new mailbox is created
        assertNotNull(mailbox);
        assertEquals(1, mailboxes.size()); // A new mailbox should be created

        // Check if the email is added to the sender's mailbox
        assertEquals(1, mailbox.getSentEmails().size());
        assertEquals(email, mailbox.getSentEmails().get(0));
    }

    @Test
    public void testSenderMailboxAndRecipientMailboxAreNotSame() {
        // Create a sender and a recipient
        Contact sender = emailManager.createContact("Sender", "sender@example.com");
        Contact recipient = emailManager.createContact("Recipient", "recipient@example.com");

        // Get the sender and recipient mailboxes
        Mailbox senderMailbox = Mailbox.senderMailbox(sender, mailboxes);
        Mailbox recipientMailbox = Mailbox.recipientMailbox(recipient, mailboxes);

        // Assert that senderMailbox and recipientMailbox are not the same
        assertNotEquals(senderMailbox, recipientMailbox);
    }

    @Test
    public void testSubjectExistsInAll100Mailboxes() {
        // Creamos un tema al que vamos a buscar
        String subjectToSearch = "Important Subject";

        // Creamos 100 mailbox
        List<Mailbox> mailboxes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mailboxes.add(new Mailbox());
        }

        // Creamos los mails con el mismo tema 
        for (Mailbox mailbox : mailboxes) {
            Contact sender = emailManager.createContact("Sender", "sender@example.com");
            List<Contact> recipients = new ArrayList<>();
            recipients.add(emailManager.createContact("Recipient", "recipient@example.com"));
            Email email = Email.createEmail(subjectToSearch, "Email content", sender, recipients);
            mailbox.addReceivedEmail(email);
        }

        // Creamos un filter para el tema
        FilterSubject filterSubject = new FilterSubject(subjectToSearch);

        // Checkeamos si el tema existe en todos los mailboxes
        for (Mailbox mailbox : mailboxes) {
            List<Email> receivedEmails = mailbox.getReceivedEmails();
            Filter filter = new Filter("Subject Filter");
            filter.filter(receivedEmails, filterSubject);
            List<Email> foundEmails = filter.getFoundEmails();
            assertEquals(1, foundEmails.size());
        }
    }

    

   

    


    
    }






























    /*@Test
public void testContentExistsInAll100Mailboxes() {
    // Create content to search for
    String contentToSearch = "Email content";

    // Create 100 mailboxes
    List<Mailbox> mailboxes = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
        mailboxes.add(new Mailbox());
    }

    // Create emails with the same content and add them to the mailboxes
    for (Mailbox mailbox : mailboxes) {
        Contact sender = emailManager.createContact("Sender", "sender@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(emailManager.createContact("Recipient", "recipient@example.com"));
        Email email = Email.createEmail("Subject", contentToSearch, sender, recipients);
        mailbox.addReceivedEmail(email);
    }

    // Create a filter for the content
    FilterContent filterContent = new FilterContent(contentToSearch);

    // Check if the content exists in all mailboxes
    for (Mailbox mailbox : mailboxes) {
        List<Email> receivedEmails = mailbox.getReceivedEmails();
        Filter filter = new Filter("Content Filter");
        filter.filter(receivedEmails, filterContent);
        List<Email> foundEmails = filter.getFoundEmails();
        assertEquals(1, foundEmails.size());
    }
}*/

/*@Test
public void testSenderExistsInAll100Mailboxes() {
    // Create a sender
    Contact senderToSearch = emailManager.createContact("Sender", "sender@example.com");

    // Create 100 mailboxes
    List<Mailbox> mailboxes = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
        mailboxes.add(new Mailbox());
    }

    // Create emails with the same sender and add them to the mailboxes
    for (Mailbox mailbox : mailboxes) {
        List<Contact> recipients = new ArrayList<>();
        recipients.add(emailManager.createContact("Recipient", "recipient@example.com"));
        Email email = Email.createEmail("Subject", "Email content", senderToSearch, recipients);
        mailbox.addReceivedEmail(email);
    }

    // Create a filter for the sender
    FilterSender filterSender = new FilterSender(senderToSearch);

    // Check if the sender exists in all mailboxes
    for (Mailbox mailbox : mailboxes) {
        List<Email> receivedEmails = mailbox.getReceivedEmails();
        Filter filter = new Filter("Sender Filter");
        filter.filter(receivedEmails, filterSender);
        List<Email> foundEmails = filter.getFoundEmails();
        assertEquals(1, foundEmails.size());
    }
} */

/*@Test
public void testRecipientExistsInAll100Mailboxes() {
    // Create a recipient
    Contact recipientToSearch = emailManager.createContact("Recipient", "recipient@example.com");

    // Create 100 mailboxes
    List<Mailbox> mailboxes = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
        mailboxes.add(new Mailbox());
    }

    // Create emails with the same recipient and add them to the mailboxes
    for (Mailbox mailbox : mailboxes) {
        Contact sender = emailManager.createContact("Sender", "sender@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipientToSearch);
        Email email = Email.createEmail("Subject", "Email content", sender, recipients);
        mailbox.addReceivedEmail(email);
    }

    // Create a filter for the recipient
    FilterRecipient filterRecipient = new FilterRecipient(recipientToSearch);

    // Check if the recipient exists in all mailboxes (similar to the sender test)
    for (Mailbox mailbox : mailboxes) {
        List<Email> receivedEmails = mailbox.getReceivedEmails();
        Filter filter = new Filter("Recipient Filter");
        filter.filter(receivedEmails, filterRecipient);
        List<Email> foundEmails = filter.getFoundEmails();
        assertEquals(1, foundEmails.size());
    }
} */