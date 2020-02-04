package saper.fileOperations;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;

public class ScoreTop100List implements Serializable {

    private LinkedList<ScoreObject> list;

    public ScoreTop100List() {
        list = new LinkedList<>();
    }

    public void add(ScoreObject obj){
        list.add(obj);
        list.sort(new ScoreObjComparator());
        if(list.size()>100) list.remove(list.size()-1);
    }

    public LinkedList get(){
        return list;
    }

    public void save(){
        FileOps saveIt = new FileOps();
        saveIt.writeToFile(list);
    }

    public void read(){
        FileOps readIt = new FileOps();
        list = readIt.readTop100ListFromFile();
    }

    static class ScoreObjComparator implements Comparator<ScoreObject>
    {
        @Override
        public int compare(ScoreObject o1, ScoreObject o2) {
            return Integer.compare(o2.getScore(), o1.getScore());
        }
    }

    @Override
    public String toString() {
        String temp = "";
        for(int i = 0; i < list.size(); i++){
            temp += list.get(i).toString()+"\n";
        }
        return temp;
    }
}
