package battle2023.ucp.Entities;

import battle2023.ucp.interfaces.MailFilter;

public class FilterRecipientNameAndContentKeyword implements MailFilter {
    private String recipientName;
    private String keyword;

    public FilterRecipientNameAndContentKeyword(String recipientName, String keyword) {
        this.recipientName = recipientName;
        this.keyword = keyword;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getRecipients().stream().anyMatch(recipient -> recipient.getName().equalsIgnoreCase(recipientName)) &&
               email.getContent().contains(keyword);
    }
}