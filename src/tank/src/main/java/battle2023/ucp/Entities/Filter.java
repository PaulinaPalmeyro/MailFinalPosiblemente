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
