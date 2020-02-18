package personelInfo.mechanics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    private Company company;

    @BeforeEach
    void setup() {
        company = new Company("Manufacture-MRN inc.", 10);
        company.fillWorker(1, "Roman", "Niedbal", 62, "prezes");
        company.fillWorker(4, "Marek", "Kicinski", 56, "spawacz");
        company.fillWorker(5, "Maria", "Kolacz", 37, "sekretarka");
        company.fillWorker(6, "Bartlomiej", "Mankowski", 30, "stolarz");
        company.fillWorker(7, "Wiktor", "Dudzik", 32, "");
        company.fillWorker(8, "Henryk", "Kowalski", 54, "");
        company.fillWorker(9, "Marta", "Sobina", 28, "malarz");
        company.fillWorker(10, "Mateusz", "Niedbal", 36, "helpdesk");
    }

    @Test
    void check_Company_CreatesProperNumberOfWorkers_WorkersHaveBeenMadeWithProperToString_isIDadded() {
        System.out.print(company.findWorkerByHisId(10));
        System.out.print(company.findWorkerByHisId(3));
        System.out.print(company.findWorkerByHisId(8));
        System.out.print(company.findWorkerByHisId(6));
        assertEquals(company.findWorkerByHisId(1).toString(), "1;Roman;Niedbal;62;prezes;\n");
        assertEquals(company.findWorkerByHisId(2).toString(), "2;;;0;;\n");
        assertEquals(company.findWorkerByHisId(3).toString(), "3;;;0;;\n");
        assertEquals(company.findWorkerByHisId(4).toString(), "4;Marek;Kicinski;56;spawacz;\n");
        assertEquals(company.findWorkerByHisId(5).toString(), "5;Maria;Kolacz;37;sekretarka;\n");
        assertEquals(company.findWorkerByHisId(6).toString(), "6;Bartlomiej;Mankowski;30;stolarz;\n");
        assertEquals(company.findWorkerByHisId(7).toString(), "7;Wiktor;Dudzik;32;;\n");
        assertEquals(company.findWorkerByHisId(8).toString(), "8;Henryk;Kowalski;54;;\n");
        assertEquals(company.findWorkerByHisId(9).toString(), "9;Marta;Sobina;28;malarz;\n");
        assertEquals(company.findWorkerByHisId(10).toString(), "10;Mateusz;Niedbal;36;helpdesk;\n");
    }

    @Test
    void check_Company_Adds_NewWorkerCorrectly() {
        company.addWorker();
        assertEquals(company.getListOfWorkers().get(10).toString(), "11;;;0;;\n");
    }

    @Test
    void check_Company_Removes_NewWorkerCorrectly() {
        company.removeWorker(6);
        assertNull(company.findWorkerByHisId(6));
    }

    @Test
    void check_sortingByName() {
        company.sort(SortPersonType.NAME, 1);
        assertEquals(company.getListOfWorkers().get(0).toString(), "2;;;0;;\n");
        assertEquals(company.getListOfWorkers().get(2).toString(), "6;Bartlomiej;Mankowski;30;stolarz;\n");
        assertEquals(company.getListOfWorkers().get(9).toString(), "7;Wiktor;Dudzik;32;;\n");
    }

    @Test
    void check_sortingSurname() {
        company.sort(SortPersonType.SURNAME, 1);
        assertEquals(company.getListOfWorkers().get(0).toString(), "2;;;0;;\n");
        assertEquals(company.getListOfWorkers().get(2).toString(), "7;Wiktor;Dudzik;32;;\n");
        assertEquals(company.getListOfWorkers().get(9).toString(), "9;Marta;Sobina;28;malarz;\n");
    }

    @Test
    void check_sortingByAge() {
        company.sort(SortPersonType.AGE, 1);
        assertEquals(company.getListOfWorkers().get(0).toString(), "2;;;0;;\n");
        assertEquals(company.getListOfWorkers().get(9).toString(), "1;Roman;Niedbal;62;prezes;\n");
        assertEquals(company.getListOfWorkers().get(2).toString(), "9;Marta;Sobina;28;malarz;\n");
    }

    @Test
    void check_sortingByPosition() {
        company.sort(SortPersonType.POSITION, 1);
        assertEquals(company.getListOfWorkers().get(0).toString(), "2;;;0;;\n");
        assertEquals(company.getListOfWorkers().get(4).toString(), "10;Mateusz;Niedbal;36;helpdesk;\n");
        assertEquals(company.getListOfWorkers().get(9).toString(), "6;Bartlomiej;Mankowski;30;stolarz;\n");
    }

    @Test
    void check_sortingByID() {
        company.sort(SortPersonType.ID, 1);
        assertEquals(company.getListOfWorkers().get(0).toString(), "1;Roman;Niedbal;62;prezes;\n");
        assertEquals(company.getListOfWorkers().get(4).toString(), "5;Maria;Kolacz;37;sekretarka;\n");
        assertEquals(company.getListOfWorkers().get(9).toString(), "10;Mateusz;Niedbal;36;helpdesk;\n");
    }


    @Test
    void check_REVsortingByName() {
        company.sort(SortPersonType.NAME, -1);
        assertEquals(company.getListOfWorkers().get(9).toString(), "3;;;0;;\n");
        assertEquals(company.getListOfWorkers().get(7).toString(), "6;Bartlomiej;Mankowski;30;stolarz;\n");
        assertEquals(company.getListOfWorkers().get(0).toString(), "7;Wiktor;Dudzik;32;;\n");
    }

    @Test
    void check_REVsortingSurname() {
        company.sort(SortPersonType.SURNAME, -1);
        assertEquals(company.getListOfWorkers().get(9).toString(), "3;;;0;;\n");
        assertEquals(company.getListOfWorkers().get(7).toString(), "7;Wiktor;Dudzik;32;;\n");
        assertEquals(company.getListOfWorkers().get(0).toString(), "9;Marta;Sobina;28;malarz;\n");
    }

    @Test
    void check_REVsortingByAge() {
        company.sort(SortPersonType.AGE, -1);
        assertEquals(company.getListOfWorkers().get(9).toString(), "3;;;0;;\n");
        assertEquals(company.getListOfWorkers().get(0).toString(), "1;Roman;Niedbal;62;prezes;\n");
        assertEquals(company.getListOfWorkers().get(7).toString(), "9;Marta;Sobina;28;malarz;\n");
    }

    @Test
    void check_REVsortingByPosition() {
        company.sort(SortPersonType.POSITION, -1);
        assertEquals(company.getListOfWorkers().get(9).toString(), "8;Henryk;Kowalski;54;;\n");
        assertEquals(company.getListOfWorkers().get(5).toString(), "10;Mateusz;Niedbal;36;helpdesk;\n");
        assertEquals(company.getListOfWorkers().get(0).toString(), "6;Bartlomiej;Mankowski;30;stolarz;\n");
    }

    @Test
    void check_REVsortingByID() {
        company.sort(SortPersonType.ID, -1);
        assertEquals(company.getListOfWorkers().get(9).toString(), "1;Roman;Niedbal;62;prezes;\n");
        assertEquals(company.getListOfWorkers().get(5).toString(), "5;Maria;Kolacz;37;sekretarka;\n");
        assertEquals(company.getListOfWorkers().get(0).toString(), "10;Mateusz;Niedbal;36;helpdesk;\n");
    }

    @Test
    void check_CompanyToString() {
        System.out.println(company.toString());
    }
}

