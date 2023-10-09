import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Before;
import org.junit.Test;


import battle2023.ucp.Entities.Contact;
import battle2023.ucp.Entities.EmailManager;


public class EmailManagerTest {
    private EmailManager emailManager;

    @Before
    public void setUp() {
        emailManager = new EmailManager();
    }

    @Test
    public void testCreateContact() { //se puede crear un contacto
        Contact contact = emailManager.createContact("John Doe", "johndoe@example.com");
        assertNotNull(contact);
        assertEquals("John Doe", contact.getName());
        assertEquals("johndoe@example.com", contact.getEmail());
    }

    @Test
    public void testCreateContactWithInvalidEmail() { //no se puede crear un contacto sin @ en email manager
        try {
            emailManager.createContact("Invalid Contact", "invalid_email");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid email format", e.getMessage());
        }
    }

    @Test
    public void testCreateContactWithNullEmail() { //contacto con mail nulo
        try {
            emailManager.createContact("Null Email Contact", null);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid email format", e.getMessage());
        }
    }
}

    




 

























    

    

    








