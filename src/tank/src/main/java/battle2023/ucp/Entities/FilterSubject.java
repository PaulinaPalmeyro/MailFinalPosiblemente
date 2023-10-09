package battle2023.ucp.Entities;

import battle2023.ucp.interfaces.MailFilter;

public class FilterSubject implements MailFilter{
    private String asunto;

    public FilterSubject(String asunto) {
        this.asunto = asunto;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getSubject().contains(asunto);
    }
    
}
