import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import java.util.List;


import org.junit.Before;
import org.junit.Test;


import battle2023.ucp.Entities.Mailbox;
import battle2023.ucp.Entities.Contact;
import battle2023.ucp.Entities.Email;
import battle2023.ucp.Entities.EmailManager;


public class EmailTest {
    
    private Contact sender;
    private List<Contact> recipients;
    private EmailManager emailManager;
    private Contact recipient;
    private Email email;

    @Before
    public void setUp() {
        sender = new Contact("John Doe", "john.doe@example.com");
        recipients = new ArrayList<>();
        recipients.add(new Contact("Alice", "alice@example.com"));
        recipients.add(new Contact("Bob", "bob@example.com"));
        emailManager = new EmailManager();
        recipient = emailManager.createContact("Recipient", "recipient@example.com");
        email = Email.createEmail("Test Subject", "Test Content", sender, new ArrayList<>());
    }

    @Test
    public void testCreateValidEmail() {
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertNotNull(email);
        assertEquals("Hello", email.getSubject());
        assertEquals("This is a test email", email.getContent());
        assertEquals(sender, email.getSender());
        assertEquals(recipients, email.getRecipients());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailWithNullSender() {
        Email.createEmail("Hello", "This is a test email", null, recipients);
    }

    @Test
    public void testGetSubject() {
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertEquals("Hello", email.getSubject());
    }

    @Test
    public void testGetContent() {
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertEquals("This is a test email", email.getContent());
    }

    @Test
    public void testGetSender() {
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertEquals(sender, email.getSender());
    }

    @Test
    public void testGetRecipients() {
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertEquals(recipients, email.getRecipients());
    }

    @Test
    public void testCreateEmailWithoutSender() {
        // Create some contacts
        Contact recipient = emailManager.createContact("Recipient Name", "recipient@example.com");

        // Create an email without a sender
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient);
        try {
            Email email = Email.createEmail("Subject", "Content", null, recipients);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Sender cannot be null", e.getMessage());
        }
    }

    
    

}
