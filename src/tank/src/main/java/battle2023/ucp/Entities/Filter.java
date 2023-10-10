package battle2023.ucp.Entities;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import battle2023.ucp.interfaces.MailFilter;

public class Filter {
    private String name;
    private List<Email> foundEmails = new ArrayList<>();

    public Filter(String name) {
        this.name = name;
    }

    public void filter(List<Email> mails, MailFilter filtroCorreo) {
        foundEmails = mails.stream()
                .filter(email -> filtroCorreo.cumpleFiltro(email))
                .collect(Collectors.toList());
    }

    public List<Email> getFoundEmails() {
        return foundEmails;
    }

    
}




























/*public class FilterContent implements MailFilter {
    private String content;

    public FilterContent(String content) {
        this.content = content;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getContent().contains(content);
    }
}
}
*/

/*public class FilterSenderEmail implements MailFilter {
    private String senderEmail;

    public FilterSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getSender().getEmail().equalsIgnoreCase(senderEmail);
    }
}*/

/*public class FilterSenderName implements MailFilter {
    private String senderName;

    public FilterSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getSender().getName().equalsIgnoreCase(senderName);
    }
} */

/*import battle2023.ucp.interfaces.MailFilter;
import java.util.List;

public class FilterRecipients implements MailFilter {
    private List<String> recipientNames;

    public FilterRecipients(List<String> recipientNames) {
        this.recipientNames = recipientNames;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        // Check if any of the recipient names in the email match the provided recipient names.
        return email.getRecipients().stream()
                .anyMatch(recipient -> recipientNames.contains(recipient.getName()));
    }
} */