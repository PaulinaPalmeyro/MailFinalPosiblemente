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


public class ContactTest {
    private EmailManager emailManager;

    @Before
    public void setUp() {
        emailManager = new EmailManager();
        
    }


    @Test
    public void testValidEmail() {
        // Valid email address
        String validEmail = "test@example.com";
        Contact contact = new Contact("John Doe", validEmail);
        assertEquals(validEmail, contact.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmail() {
        // Invalid email address
        String invalidEmail = "invalid_email";
        new Contact("Jane Smith", invalidEmail);
    }

    @Test
    public void testGetName() {
        String name = "Alice Johnson";
        String email = "alice@example.com";
        Contact contact = new Contact(name, email);
        assertEquals(name, contact.getName());
    }

    @Test
    public void testGetEmail() {
        String name = "Bob Brown";
        String email = "bob@example.com";
        Contact contact = new Contact(name, email);
        assertEquals(email, contact.getEmail());
    }

    @Test
    public void testValidEmailConArroba() {
        // Valid email
        Contact contact = new Contact("John Doe", "john@example.com");
        assertEquals("john@example.com", contact.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmailSinArroba() {
        // Invalid email (missing "@" symbol)
        new Contact("Jane Smith", "janeexample.com");
    }

    @Test
    public void testCreateContact() {
        // Create multiple contacts
        Contact contact1 = emailManager.createContact("John Doe", "john.doe@example.com");
        Contact contact2 = emailManager.createContact("Alice Smith", "alice.smith@example.com");
        Contact contact3 = emailManager.createContact("Bob Johnson", "bob.johnson@example.com");

        // Verify that the contacts were created and added to the EmailManager's contacts list
        assertTrue(emailManager.getContacts().contains(contact1));
        assertTrue(emailManager.getContacts().contains(contact2));
        assertTrue(emailManager.getContacts().contains(contact3));
    }
}
