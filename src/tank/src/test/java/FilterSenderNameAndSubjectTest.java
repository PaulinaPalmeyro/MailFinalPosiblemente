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
    @Test
    public void testCumpleFiltro_MatchingCriteria() {
        // Create test data
        Contact sender = new Contact("John Doe", "john@example.com");
        Email email = new Email("Important Subject", "Content", sender, new ArrayList<>());
        FilterSenderNameAndSubject filter = new FilterSenderNameAndSubject("John Doe", "Important Subject");

        // Test the filter
        assertTrue(filter.cumpleFiltro(email));
    }

    @Test
    public void testCumpleFiltro_DifferentSender() {
        // Create test data
        Contact sender = new Contact("Jane Smith", "jane@example.com");
        Email email = new Email("Important Subject", "Content", sender, new ArrayList<>());
        FilterSenderNameAndSubject filter = new FilterSenderNameAndSubject("John Doe", "Important Subject");

        // Test the filter
        assertFalse(filter.cumpleFiltro(email));
    }

    @Test
    public void testCumpleFiltro_DifferentSubject() {
        // Create test data
        Contact sender = new Contact("John Doe", "john@example.com");
        Email email = new Email("Different Subject", "Content", sender, new ArrayList<>());
        FilterSenderNameAndSubject filter = new FilterSenderNameAndSubject("John Doe", "Important Subject");

        // Test the filter
        assertFalse(filter.cumpleFiltro(email));
    }
}
