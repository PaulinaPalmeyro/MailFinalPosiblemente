import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.Arrays;
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

public class FilterSenderNameAndSubjectTest {
    private EmailManager emailManager;

    @Before
    public void setUp() {
        emailManager = new EmailManager();
    }

    @Test
    public void testCumpleFiltro_MatchingCriteria() { //Verifica que el mail identifique que un mail cumple ambos criterios
        // Create test data
        Contact sender = new Contact("Paulina", "pauli@example.com");
        Email email = new Email("Important Subject", "Content", sender, new ArrayList<>());
        FilterSenderNameAndSubject filter = new FilterSenderNameAndSubject("Paulina", "Important Subject");

        // Test the filter
        assertTrue(filter.cumpleFiltro(email));
    }

    @Test
    public void testCumpleFiltro_DifferentSender() { //Verifica que el mail identifique que un mail no cumple uno de los criterios
        // Create test data
        Contact sender = new Contact("Paulina", "pauli@example.com");
        Email email = new Email("Important Subject", "Content", sender, new ArrayList<>());
        FilterSenderNameAndSubject filter = new FilterSenderNameAndSubject("PedroJuan", "Important Subject");

        // Test the filter
        assertFalse(filter.cumpleFiltro(email));
    }

    @Test
    public void testCumpleFiltro_DifferentSubject() { //Verifica que el mail identifique que un mail no cumple uno de los criterios
        // Create test data
        Contact sender = new Contact("Paulina", "pauli@example.com");
        Email email = new Email("Different Subject", "Content", sender, new ArrayList<>());
        FilterSenderNameAndSubject filter = new FilterSenderNameAndSubject("Paulina", "Important Subject");

        // Test the filter
        assertFalse(filter.cumpleFiltro(email));
    }

    @Test
    public void testSenderNameAndSubjectExistInAll100Mailboxes() {
        // Create a sender and subject to search
        String senderNameToSearch = "Sender";
        String subjectToSearch = "Important Subject";

        // Create 100 mailboxes
        List<Mailbox> mailboxes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mailboxes.add(new Mailbox());
        }

        // Create emails with the same sender and subject
        for (Mailbox mailbox : mailboxes) {
            Contact sender = emailManager.createContact(senderNameToSearch, "sender@example.com");
            List<Contact> recipients = new ArrayList<>();
            recipients.add(emailManager.createContact("Recipient", "recipient@example.com"));
            Email email = Email.createEmail(subjectToSearch, "Email content", sender, recipients);
            mailbox.addReceivedEmail(email);
        }

        // Create a filter for sender name and subject
        FilterSenderNameAndSubject filterSenderNameAndSubject = new FilterSenderNameAndSubject(senderNameToSearch, subjectToSearch);

        // Check if the sender name and subject exist in all mailboxes
        for (Mailbox mailbox : mailboxes) {
            List<Email> receivedEmails = mailbox.getReceivedEmails();
            Filter filter = new Filter("SenderNameAndSubject Filter");
            filter.filter(receivedEmails, filterSenderNameAndSubject);
            List<Email> foundEmails = filter.getFoundEmails();
            assertEquals(1, foundEmails.size());
        }
    }

    @Test
    public void testSenderNameAndSubjectExistInRecipientMailbox() {
        // Create a sender and subject to search
        String senderNameToSearch = "Sender";
        String subjectToSearch = "Important Subject";

        // Create a mailbox for a specific recipient
        Mailbox recipientMailbox = new Mailbox();

        // Create an email with the same sender and subject
        Contact sender = emailManager.createContact(senderNameToSearch, "sender@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(emailManager.createContact("Recipient", "recipient@example.com"));
        Email email = Email.createEmail(subjectToSearch, "Email content", sender, recipients);
        recipientMailbox.addReceivedEmail(email);

        // Create a filter for sender name and subject
        FilterSenderNameAndSubject filterSenderNameAndSubject = new FilterSenderNameAndSubject(senderNameToSearch, subjectToSearch);

        // Check if the sender name and subject exist in the recipient's mailbox
        List<Email> receivedEmails = recipientMailbox.getReceivedEmails();
        Filter filter = new Filter("SenderNameAndSubject Filter");
        filter.filter(receivedEmails, filterSenderNameAndSubject);
        List<Email> foundEmails = filter.getFoundEmails();
        assertEquals(1, foundEmails.size());
    }


}
