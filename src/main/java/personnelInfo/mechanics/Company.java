package personnelInfo.mechanics;

import java.util.*;

public class Company {

    private String name;
    private int workersNumber;
    private ArrayList<Person> listOfWorkers;

    public Company(String name, int workersNumber) {
        this.name = name;
        this.workersNumber = workersNumber;
        listOfWorkers = new ArrayList<>();
        for (int i = 0; i < workersNumber; i++) {
            listOfWorkers.add(new Person.PersonBuilder("", "", 0)
                    .position("")
                    .ID(listOfWorkers.size() + 1)
                    .build());
        }
    }


    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Person> getListOfWorkers() {
        return listOfWorkers;
    }

    public void sort(SortPersonType sortPersonByType, int reversedSortSwitch_Minus1ToReverse_Plus1ToNormal) {
        sortInner(listOfWorkers, sortPersonByType, reversedSortSwitch_Minus1ToReverse_Plus1ToNormal);
    }

    private void sortInner(List<Person> list, SortPersonType sortPersonByType, int reversedSortSwitch_Minus1ToReverse_Plus1ToNormal) {
        list.sort(new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                return compareInner(o1, o2);
            }

            private int compareInner(Person o1, Person o2) {
                int temp;
                switch (sortPersonByType) {
                    case ID:
                        return compareNumbers(o1.getID(), o2.getID()) * reversedSortSwitch_Minus1ToReverse_Plus1ToNormal;
                    case AGE:
                        return compareNumbers(o1.getAGE(), o2.getAGE()) * reversedSortSwitch_Minus1ToReverse_Plus1ToNormal;
                    case NAME:
                        return compareWords(o1.getNAME(), o2.getNAME()) * reversedSortSwitch_Minus1ToReverse_Plus1ToNormal;
                    case SURNAME:
                        return compareWords(o1.getSURNAME(), o2.getSURNAME()) * reversedSortSwitch_Minus1ToReverse_Plus1ToNormal;
                    case POSITION:
                        return compareWords(o1.getPosition(), o2.getPosition()) * reversedSortSwitch_Minus1ToReverse_Plus1ToNormal;
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

    Person findWorkerByHisId(int ID) {
        for (Person element : listOfWorkers) {
            if (element.getID() == ID) return element;
        }
        return null;
    }


    void fillWorker(int i, String name, String surname, int age, String position) {
        listOfWorkers.set(i - 1, new Person.PersonBuilder(name, surname, age).position(position).ID(i).build());
    }

    public void addWorker() {
        listOfWorkers.add(new Person.PersonBuilder("", "", 0)
                .position("")
                .ID(listOfWorkers.size() + 1)
                .build());
    }

    public void removeWorker(int id) {
        listOfWorkers.remove(findWorkerByHisId(id));
    }

    public void clear() {
        listOfWorkers.clear();
        name = "";
        workersNumber = 0;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder(name + ";\n");
        temp.append(listOfWorkers.size()).append(";\n");
        for (Person el : listOfWorkers) {
            temp.append(el.toString());
        }
        return temp.toString();
    }



}

