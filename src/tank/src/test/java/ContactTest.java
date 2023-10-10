import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



import org.junit.Before;
import org.junit.Test;


import battle2023.ucp.Entities.Contact;
import battle2023.ucp.Entities.EmailManager;


public class ContactTest {
    private EmailManager emailManager;

    @Before
    public void setUp() {
        emailManager = new EmailManager();
        
    }


    @Test
    public void testValidEmail() { //Verificamos que sea correcto si es valido
        
        String validEmail = "test@example.com";
        Contact contact = new Contact("Bruno Pini", validEmail);
        assertEquals(validEmail, contact.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmail() { //verificamos que sea incorrecto si no es valido
        
        String invalidEmail = "invalid_email";
        new Contact("Oriana Farela", invalidEmail);
    }

    @Test
    public void testGetName() { //verifica quetenga nombre y sea correcto
        String name = "Valentina Salmon";
        String email = "valen@example.com";
        Contact contact = new Contact(name, email);
        assertEquals(name, contact.getName());
    }

    @Test
    public void testGetEmail() { //verifica que tenga emeil y sea valido
        String name = "Paulina Palmeyro";
        String email = "pauli@example.com";
        Contact contact = new Contact(name, email);
        assertEquals(email, contact.getEmail());
    }

    @Test
    public void testValidEmailConArroba() { //verifica que el mail tenga @ y si tiene esto sea correcto
        // Valid email
        Contact contact = new Contact(" Bruno Pini", "brunito@example.com");
        assertEquals("brunito@example.com", contact.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmailSinArroba() { //verifica que un mail sin arroba es imposible
        
        new Contact("Oriana Farela", "oriexample.com");
    }

    @Test
    public void testCreateContact() { //verifica que se cee un contacto y se agregue a EmailManager
        
        Contact contact1 = emailManager.createContact("Bruno Pini", "brunito@example.com");
        Contact contact2 = emailManager.createContact("Valentina Salmon", "valen@example.com");
        Contact contact3 = emailManager.createContact("Paulina Palmeyro", "pauli@example.com");

        assertTrue(emailManager.getContacts().contains(contact1));
        assertTrue(emailManager.getContacts().contains(contact2));
        assertTrue(emailManager.getContacts().contains(contact3));
    }
}
