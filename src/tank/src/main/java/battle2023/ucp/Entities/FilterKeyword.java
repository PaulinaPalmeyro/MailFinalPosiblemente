package battle2023.ucp.Entities;

import battle2023.ucp.interfaces.MailFilter;

public class FilterKeyword implements MailFilter {
    private String keyword;

    public FilterKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean cumpleFiltro(Email email) {
        return email.getContent().contains(keyword);
    }
}
