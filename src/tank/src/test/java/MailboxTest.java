import static org.junit.Assert.assertEquals;
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

public class MailboxTest {
    private List<Mailbox> mailboxes;
    private Contact sender;
    private Contact recipient;
    private EmailManager emailManager;
    private Contact contact1;
    private Contact contact2;

    @Before
    public void setUp() {
        mailboxes = new ArrayList<>();
        sender = new Contact("John Doe", "john.doe@example.com");
        recipient = new Contact("Jane Smith", "jane.smith@example.com");
        emailManager = new EmailManager();
        contact1 = emailManager.createContact("John Doe", "john@example.com");
        contact2 = emailManager.createContact("Jane Smith", "jane@example.com");
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
        Email email = Email.createEmail("Test Subject", "Test Content", sender, List.of(recipient));

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
    
    }

