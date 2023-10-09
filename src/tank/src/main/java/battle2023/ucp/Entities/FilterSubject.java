package battle2023.ucp.Entities;

import battle2023.ucp.interfaces.MailFilter;

public class FilterSubject implements MailFilter{
    private String subject;

    public FilterSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getSubject().contains(subject);
    }
    
}
