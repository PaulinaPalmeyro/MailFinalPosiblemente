package battle2023.ucp.Entities;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import battle2023.ucp.interfaces.MailFilter;

public class Filter {
    private String nombre;
    private List<Email> mailsEncontrados = new ArrayList<>();

    public Filter(String nombre) {
        this.nombre = nombre;
    }

    public void filter(List<Email> correos, MailFilter filtroCorreo) {
        mailsEncontrados = correos.stream()
                .filter(email -> filtroCorreo.cumpleFiltro(email))
                .collect(Collectors.toList());
    }

    public List<Email> getMailsEncontrados() {
        return mailsEncontrados;
    }

    
}
