package personelInfo.mechanics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {

    private String name;
    private int workersNumber;
    private ArrayList<Person.PersonBuilder> listOfWorkers;

    public Company(String name, int workersNumber) {
        this.name = name;
        this.workersNumber = workersNumber;
        listOfWorkers = new ArrayList<>();
        for(int i = 0; i< workersNumber; i++){
            listOfWorkers.add(new Person.PersonBuilder("","",0)
                    .position("")
                    .ID(listOfWorkers.size()));
        }
    }

    public void sort(SortPersonType sortPersonByType){
        sortInner(listOfWorkers,sortPersonByType);
    }

    private void sortInner(List<Person.PersonBuilder> list, SortPersonType sortPersonByType){
        list.sort(new Comparator<Person.PersonBuilder>() {

            @Override
            public int compare(Person.PersonBuilder o1, Person.PersonBuilder o2) {
                return compareInner(o1,o2);
            }

            private int compareInner(Person.PersonBuilder o1, Person.PersonBuilder o2) {
                int temp;
                switch (sortPersonByType){
                    case ID:
                        return compareNumbers(o1.getID(),o2.getID());
                    case AGE:
                        return compareNumbers(o1.getAGE(),o2.getAGE());
                    case NAME:
                        return compareWords(o1.getNAME(),o2.getNAME());
                    case SURNAME:
                        return compareWords(o1.getSURNAME(),o2.getSURNAME());
                    case POSITION:
                        return compareWords(o1.getPosition(),o2.getPosition());
                    default:
                        return 0;
                }
            }

            private int compareNumbers(int n1, int n2) {
                return Integer.compare(n1, n2);
            }

            private int compareWords(String w1, String w2) {
                return w1.compareTo(w2);
            }
        });
    }

    public Person.PersonBuilder findWorkerByHisId(int ID){
        List<Person.PersonBuilder> tempList = listOfWorkers;
        sortInner(tempList,SortPersonType.ID);
        return tempList.get(ID);
    }



}
