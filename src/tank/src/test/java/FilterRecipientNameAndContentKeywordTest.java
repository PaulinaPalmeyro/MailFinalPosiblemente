import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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


public class FilterRecipientNameAndContentKeywordTest {
    private EmailManager emailManager;

    @Before
    public void setUp() {
        emailManager = new EmailManager();
    }
    
    @Test
    public void testCumpleFiltroWithMatchingCriteria() { //Verifica que el mail identifique que un mail cumple el criterio
        // Create a test email
        Contact recipient = new Contact("Bruno", "brunito@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient);
        Email email = new Email("Test Subject", "This is a test email.", null, recipients);

        // Create the filter with criteria
        String recipientName = "Bruno";
        String keyword = "test";

        FilterRecipientNameAndContentKeyword filter = new FilterRecipientNameAndContentKeyword(recipientName, keyword);

        // Check if the email passes the filter
        assertTrue(filter.cumpleFiltro(email));
    }

    @Test
    public void testCumpleFiltroWithNonMatchingRecipientName() { //Verifica que el mail identifique el mail no cumple uno de los criterios
        // Create a test email
        Contact recipient = new Contact("Jane Smith", "pauli@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient);
        Email email = new Email("Test Subject", "This is a test email.", null, recipients);

        // Create the filter with criteria
        String recipientName = "Bruno";
        String keyword = "test";

        FilterRecipientNameAndContentKeyword filter = new FilterRecipientNameAndContentKeyword(recipientName, keyword);

        // Check if the email does not pass the filter
        assertFalse(filter.cumpleFiltro(email));
    }


    @Test
    public void testCumpleFiltroWithNonMatchingContentKeyword() { //Verifica que el mail identifique el mail no cumple uno de los criterios
        // Create a test email
        Contact recipient = new Contact("Brunito", "brunito@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient);
        Email email = new Email("Test Subject", "This is a test email.", null, recipients);

        // Create the filter with criteria
        String recipientName = "Brunito";
        String keyword = "invalid";

        FilterRecipientNameAndContentKeyword filter = new FilterRecipientNameAndContentKeyword(recipientName, keyword);

        // Check if the email does not pass the filter
        assertFalse(filter.cumpleFiltro(email));
    }

    @Test
    public void testContentAndRecipientNameExistsInAll100Mailboxes() {
        // Create content and recipient name to search for
        String contentToSearch = "Important Content";
        String recipientNameToSearch = "Recipient";

        // Create 100 mailboxes
        List<Mailbox> mailboxes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mailboxes.add(new Mailbox());
        }

        // Create emails with the same content and recipient name
        for (Mailbox mailbox : mailboxes) {
            Contact sender = emailManager.createContact("Sender", "sender@example.com");
            List<Contact> recipients = new ArrayList<>();
            recipients.add(emailManager.createContact(recipientNameToSearch, "recipient@example.com"));
            Email email = Email.createEmail("Subject", contentToSearch, sender, recipients);
            mailbox.addReceivedEmail(email);
        }
        

        // Create a filter for content and recipient name
        FilterRecipientNameAndContentKeyword filter = new FilterRecipientNameAndContentKeyword(recipientNameToSearch, contentToSearch);

        // Check if the content and recipient name exist in all mailboxes
        for (Mailbox mailbox : mailboxes) {
            List<Email> receivedEmails = mailbox.getReceivedEmails();
            Filter filterWrapper = new Filter("Recipient and Content Filter");
            filterWrapper.filter(receivedEmails, filter);
            List<Email> foundEmails = filterWrapper.getFoundEmails();
            assertEquals(1, foundEmails.size());
        }
    }



    /*TEST HECHO EN VIVO, SE MANDAN DOS MAILS UNO QUE CUMPLE CON AMBAS CONDICIONES Y UNO QUE NO CUMPLE CON UNA. VERIFICA QUE EXISTA SOLO UN MAIL QUE 
     * CUMPLE ESTO EN CADA
     */
    @Test
    public void testContentAndRecipientNameFilterWithAndWithout() {
        // Create creo un contenido y recipiente específico
        String contentToSearch = "Important Content";
        String invalidKeyword = "estoNoEs";
        String recipientNameToSearch = "Recipient";

        // Creo 100 mailboxes
        List<Mailbox> mailboxes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mailboxes.add(new Mailbox());
        }

        // Creo 20 mails que se mandan a 20 contactos y cumplen los criterios
        for (Mailbox mailbox : mailboxes) {
            Contact sender = emailManager.createContact("Sender", "sender@example.com");
            List<Contact> recipients = new ArrayList<>();
            recipients.add(emailManager.createContact(recipientNameToSearch, "recipient@example.com"));
            Email email = Email.createEmail("Subject", contentToSearch, sender, recipients);
            Email email2 = Email.createEmail("Subject", invalidKeyword, sender, recipients);
            mailbox.addReceivedEmail(email);
        }

        // Uso el filtro que toma como valido "contentToSearch" y "RecipientNameToSearch"
        FilterRecipientNameAndContentKeyword filter = new FilterRecipientNameAndContentKeyword(recipientNameToSearch, contentToSearch);

        // Checkeamos que el mail correcto exista solo una vez en cada mailbox
        for (Mailbox mailbox : mailboxes) {
            List<Email> receivedEmails = mailbox.getReceivedEmails();
            Filter filterWrapper = new Filter("Recipient and Content Filter");
            filterWrapper.filter(receivedEmails, filter);
            List<Email> foundEmails = filterWrapper.getFoundEmails();
            assertEquals(1, foundEmails.size());
        }
    }
}



