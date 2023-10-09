import static org.junit.Assert.assertEquals;
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


public class EmailManagerTest {
    private EmailManager emailManager;

    @Before
    public void setUp() {
        emailManager = new EmailManager();
    }

    @Test
    public void testCreateContact() {
        // Test creating a contact
        Contact contact = emailManager.createContact("John Doe", "johndoe@example.com");
        assertNotNull(contact);
        assertEquals("John Doe", contact.getName());
        assertEquals("johndoe@example.com", contact.getEmail());
    }

    @Test
    public void testCreateContactWithInvalidEmail() {
        // Test creating a contact with an invalid email
        try {
            emailManager.createContact("Invalid Contact", "invalid_email");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid email format", e.getMessage());
        }
    }

    @Test
    public void testCreateContactWithNullEmail() {
        // Test creating a contact with a null email
        try {
            emailManager.createContact("Null Email Contact", null);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid email format", e.getMessage());
        }
    }
}

    




 

























    

    

    








