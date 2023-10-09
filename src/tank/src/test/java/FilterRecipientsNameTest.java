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



public class FilterRecipientsNameTest {
    @Test
    public void testFilterRecipientsName() { //Verifica que el mail identifique que un mail cumple el criterio
        Contact recipient1 = new Contact("Recipient1", "recipient1@example.com");
        Contact recipient2 = new Contact("Recipient2", "recipient2@example.com");
        Contact recipient3 = new Contact("Recipient3", "recipient3@example.com");

        Email email1 = new Email("Subject1", "Content", null, Arrays.asList(recipient1, recipient2));
        Email email2 = new Email("Subject2", "Content", null, Arrays.asList(recipient2, recipient3));
        Email email3 = new Email("Subject3", "Content", null, Arrays.asList(recipient1, recipient3));

        List<Email> emails = Arrays.asList(email1, email2, email3);

        Filter filter = new Filter("RecipientFilter");
        FilterRecipientsName filterRecipientsName = new FilterRecipientsName("Recipient1");
        
        filter.filter(emails, filterRecipientsName);

        List<Email> filteredEmails = filter.getFoundEmails();

        assertEquals(2, filteredEmails.size());
        assertTrue(filteredEmails.contains(email1));
        assertTrue(filteredEmails.contains(email3));
        assertFalse(filteredEmails.contains(email2));
    }

    @Test
    public void testFilterRecipientsNameNoMatch() { //Verifica que el mail identifique que un mail no cumple el criterio
        Contact recipient1 = new Contact("Recipient1", "recipient1@example.com");
        Contact recipient2 = new Contact("Recipient2", "recipient2@example.com");

        Email email1 = new Email("Subject1", "Content", null, Arrays.asList(recipient1));
        Email email2 = new Email("Subject2", "Content", null, Arrays.asList(recipient2));

        List<Email> emails = Arrays.asList(email1, email2);

        Filter filter = new Filter("RecipientFilter");
        FilterRecipientsName filterRecipientsName = new FilterRecipientsName("Recipient3");
        
        filter.filter(emails, filterRecipientsName);

        List<Email> filteredEmails = filter.getFoundEmails();

        assertTrue(filteredEmails.isEmpty());
    }

}
