package battle2023.ucp.Entities;

import battle2023.ucp.interfaces.MailFilter;

public class FilterRecipientsName implements MailFilter {
    private String recipientName;

    public FilterRecipientsName(String recipientName) {
        this.recipientName = recipientName;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getRecipients().stream().anyMatch(recipient -> recipient.getName().equalsIgnoreCase(recipientName));
    }
}