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


public class FilterSubjectTest {
    private List<Email> emails;

    @Before
    public void setUp() {
        // Initialize the list of emails for testing
        emails = new ArrayList<>();
        Contact sender = new Contact("John Doe", "john@example.com");
        List<Contact> recipients = new ArrayList<>();
        recipients.add(new Contact("Alice", "alice@example.com"));
        recipients.add(new Contact("Bob", "bob@example.com"));

        // Create some sample emails
        emails.add(new Email("Important Subject", "Content", sender, recipients));
        emails.add(new Email("Another Subject", "Different Content", sender, recipients));
    }

    @Test
    public void testFilterSubject() {
        FilterSubject filter = new FilterSubject("Important");
        Filter filterManager = new Filter("FilterSubjectTest");
        filterManager.filter(emails, filter);
        List<Email> filteredEmails = filterManager.getFoundEmails();

        assertEquals(1, filteredEmails.size());
        assertEquals("Important Subject", filteredEmails.get(0).getSubject());
    }

    @Test
    public void testCumpleFiltro_WhenItsTrue() {
        
        String subject = "Important Subject";
        String keyword = "Important";
        Email email = new Email(subject, "Content", new Contact("Sender", "sender@example.com"), new ArrayList<>());
        FilterSubject filter = new FilterSubject(keyword);

        boolean result = filter.cumpleFiltro(email);

        assertTrue(result);
    }

    @Test
    public void testCumpleFiltro_WhenItsFalse() {
        // Arrange
        String subject = "Regular Subject";
        String keyword = "Important";
        Email email = new Email(subject, "Content", new Contact("Sender", "sender@example.com"), new ArrayList<>());
        FilterSubject filter = new FilterSubject(keyword);

        // Act
        boolean result = filter.cumpleFiltro(email);

        // Assert
        assertFalse(result);
    

    }
    

    }