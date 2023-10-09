package battle2023.ucp.Entities;
import java.util.List;
import java.util.ArrayList;


public class Contact {
    private String name;
    private String email;

    public Contact(String name, String email) {
        this.name = name;
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}



