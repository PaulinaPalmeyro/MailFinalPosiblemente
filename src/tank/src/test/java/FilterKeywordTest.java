
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;




import org.junit.Before;
import org.junit.Test;


import battle2023.ucp.Entities.Email;
import battle2023.ucp.Entities.FilterKeyword;


public class FilterKeywordTest {
    
    private FilterKeyword filter;

    @Before
    public void setUp() {
        filter = new FilterKeyword("important");
    }

    @Test
    public void testCumpleFiltroWithMatchingKeyword() { //Verifica que el mail identifique que un mail cumple el criterio
        // Create an email with content containing the keyword
        Email email = new Email("Subject", "This is an important email.", null, null);

        // Check if the filter correctly identifies the email as matching
        assertTrue(filter.cumpleFiltro(email));
    }

    @Test
    public void testCumpleFiltroWithNonMatchingKeyword() { //Verifica que el mail identifique que ningun mail cumple el criterio
        // Create an email with content that doesn't contain the keyword
        Email email = new Email("Subject", "This is a regular email.", null, null);

        // Check if the filter correctly identifies the email as not matching
        assertFalse(filter.cumpleFiltro(email));
    }

}
