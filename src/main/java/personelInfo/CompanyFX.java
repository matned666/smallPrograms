package personelInfo;

import personelInfo.mechanics.Company;

import java.util.LinkedList;
import java.util.List;

public class CompanyFX {

    public Company getCompany() {
        return company;
    }

    private List<PersonButtonFX> list;
    private Company company;



    public CompanyFX(Company company) {
        list = new LinkedList<>();
        this.company = company;
        createList();
    }

    public List<PersonButtonFX> getList() {
        return list;
    }

    void createList(){
        for(int i =0;i<company.getListOfWorkers().size();i++){
            list.add(new PersonButtonFX(company.getListOfWorkers().get(i)));
        }
    }


}
