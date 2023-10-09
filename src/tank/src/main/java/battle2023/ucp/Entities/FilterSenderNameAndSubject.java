package battle2023.ucp.Entities;


import battle2023.ucp.interfaces.MailFilter;

public class FilterSenderNameAndSubject implements MailFilter {
    private String senderName;
    private String subject;

    public FilterSenderNameAndSubject(String senderName, String subject) {
        this.senderName = senderName;
        this.subject = subject;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getSender().getName().equalsIgnoreCase(senderName) &&
               email.getSubject().contains(subject);
    }
}