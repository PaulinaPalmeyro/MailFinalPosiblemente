import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import java.util.List;


import org.junit.Before;
import org.junit.Test;


import battle2023.ucp.Entities.Contact;
import battle2023.ucp.Entities.Email;
import battle2023.ucp.Entities.EmailManager;
import battle2023.ucp.Entities.Mailbox;


public class EmailTest {
    
    private Contact sender;
    private List<Contact> recipients;
    private EmailManager emailManager;
    private Contact recipient;
    private Email email;

    @Before
    public void setUp() {
        sender = new Contact("Sender", "sender@example.com");
        recipients = new ArrayList<>();
        recipients.add(new Contact("Bruno", "brunito@example.com"));
        recipients.add(new Contact("Paulina", "pauli@example.com"));
        emailManager = new EmailManager();
        recipient = emailManager.createContact("Recipient", "recipient@example.com");
        email = Email.createEmail("Test Subject", "Test Content", sender, new ArrayList<>());
    }

    @Test
    public void testCreateValidEmail() { //verifica que se cree el mail con todos sus componentes
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertNotNull(email);
        assertEquals("Hello", email.getSubject());
        assertEquals("This is a test email", email.getContent());
        assertEquals(sender, email.getSender());
        assertEquals(recipients, email.getRecipients());
    }

    @Test(expected = IllegalArgumentException.class) //verifica que no se puede crear el mail con remitente nulo
    public void testCreateEmailWithNullSender() {
        Email.createEmail("Hello", "This is a test email", null, recipients);
    }

    @Test
    public void testGetSubject() { //verifica que el tema sea correcto
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertEquals("Hello", email.getSubject());
    }

    @Test
    public void testGetContent() { //verifica que el contenido sea correcto
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertEquals("This is a test email", email.getContent());
    }

    @Test
    public void testGetSender() { //verifica que el remitente sea correcto
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertEquals(sender, email.getSender());
    }

    @Test
    public void testGetRecipients() { //verifica que el para sea correcto
        Email email = Email.createEmail("Hello", "This is a test email", sender, recipients);
        assertEquals(recipients, email.getRecipients());
    }

    @Test
    public void testCreateEmailWithoutSender() { ////verifica que no se pueda crear el mail sin remitente
        Contact recipient = emailManager.createContact("Recipient Name", "recipient@example.com");

        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient);
        try {
            Email email = Email.createEmail("Subject", "Content", null, recipients);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Sender cannot be null", e.getMessage());
        }
    }

    @Test
    public void testSending100Emails() {
        // Creamos una lista de contactos
        List<Contact> contacts = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Contact contact = emailManager.createContact("Recipient " + i, "recipient" + i + "@example.com");
            contacts.add(contact);
        }

        // Creo el contacto sender
        Contact sender = emailManager.createContact("Sender", "sender@example.com");

        // Creo los mailboxes para ambos contactos
        Mailbox senderMailbox = Mailbox.senderMailbox(sender, emailManager.getMailboxes());
        List<Mailbox> recipientMailboxes = new ArrayList<>();
        for (Contact recipient : contacts) {
            recipientMailboxes.add(Mailbox.recipientMailbox(recipient, emailManager.getMailboxes()));
        }

        // Envio 100 mails
        for (Contact recipient : contacts) {
            List<Contact> recipientList = new ArrayList<>();
            recipientList.add(recipient);
            Email email = Email.createEmail("Test Subject", "Test Content", sender, recipientList);
            senderMailbox.addSentEmail(email);
            recipientMailboxes.get(contacts.indexOf(recipient)).addReceivedEmail(email);
        }

        // Comprobamos mailbox del destinatario
        for (Mailbox mailbox : recipientMailboxes) {
            assertEquals(1, mailbox.getReceivedEmails().size());
        }
    }




    
    @Test
    public void testSendingEmail() {
        

        // Creamos bandejas para el destinatario y remitente
        Mailbox senderMailbox = Mailbox.senderMailbox(sender, emailManager.getMailboxes());
        Mailbox recipientMailbox = Mailbox.recipientMailbox(recipient, emailManager.getMailboxes());

        // Mandamos un mail
        recipients.add(recipient);
        Email email = Email.createEmail("Test Subject", "Test Content", sender, recipients);
        senderMailbox.addSentEmail(email);
        recipientMailbox.addReceivedEmail(email);

        // Verificamos que el destinatario recibió el mail
        assertEquals(1, recipientMailbox.getReceivedEmails().size());
    }







    @Test
    public void testSending100EmailsInSendersMailbox() {
        // Create a list of contacts
        List<Contact> contacts = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Contact contact = emailManager.createContact("Recipient " + i, "recipient" + i + "@example.com");
            contacts.add(contact);
        }

        // Create a sender contact
        Contact sender = emailManager.createContact("Sender", "sender@example.com");

        // Create mailboxes for sender and recipients
        Mailbox senderMailbox = Mailbox.senderMailbox(sender, emailManager.getMailboxes());
        List<Mailbox> recipientMailboxes = new ArrayList<>();
        for (Contact recipient : contacts) {
            recipientMailboxes.add(Mailbox.recipientMailbox(recipient, emailManager.getMailboxes()));
        }

        // Send 100 emails from the sender to recipients
        for (Contact recipient : contacts) {
            List<Contact> recipientList = new ArrayList<>();
            recipientList.add(recipient);
            Email email = Email.createEmail("Test Subject", "Test Content", sender, recipientList);
            senderMailbox.addSentEmail(email);
            recipientMailboxes.get(contacts.indexOf(recipient)).addReceivedEmail(email);
        }

        // Verify that the sender's mailbox contains all 100 sent emails
        assertEquals(100, senderMailbox.getSentEmails().size());
    }

    
    
    

}
