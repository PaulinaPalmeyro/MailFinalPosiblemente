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
    
    @Test
    public void testCumpleFiltroWithMatchingCriteria() {
        // Create a test email
        Contact recipient = new Contact("John Doe", "john@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient);
        Email email = new Email("Test Subject", "This is a test email.", null, recipients);

        // Create the filter with criteria
        String recipientName = "John Doe";
        String keyword = "test";

        FilterRecipientNameAndContentKeyword filter = new FilterRecipientNameAndContentKeyword(recipientName, keyword);

        // Check if the email passes the filter
        assertTrue(filter.cumpleFiltro(email));
    }

    @Test
    public void testCumpleFiltroWithNonMatchingRecipientName() {
        // Create a test email
        Contact recipient = new Contact("Jane Smith", "jane@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient);
        Email email = new Email("Test Subject", "This is a test email.", null, recipients);

        // Create the filter with criteria
        String recipientName = "John Doe";
        String keyword = "test";

        FilterRecipientNameAndContentKeyword filter = new FilterRecipientNameAndContentKeyword(recipientName, keyword);

        // Check if the email does not pass the filter
        assertFalse(filter.cumpleFiltro(email));
    }


    @Test
    public void testCumpleFiltroWithNonMatchingContentKeyword() {
        // Create a test email
        Contact recipient = new Contact("John Doe", "john@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(recipient);
        Email email = new Email("Test Subject", "This is a test email.", null, recipients);

        // Create the filter with criteria
        String recipientName = "John Doe";
        String keyword = "invalid";

        FilterRecipientNameAndContentKeyword filter = new FilterRecipientNameAndContentKeyword(recipientName, keyword);

        // Check if the email does not pass the filter
        assertFalse(filter.cumpleFiltro(email));
    }
}
