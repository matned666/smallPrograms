package saper.fileOperations;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTop100ListTest {

    private ScoreTop100List a;

    @Test
    void saveReadObjectsWorkCorrectly(){
        a = new ScoreTop100List();
        a.add(new ScoreObject("bobek", new DateTime(),2345));
        a.add(new ScoreObject("matned", new DateTime(),9999));
        a.add(new ScoreObject("dupa", new DateTime(),31));
        a.save();
        ScoreTop100List b = new ScoreTop100List();
        b.read();
        assertEquals( ((ScoreObject) a.get().get(0)).getScore() , 9999);
    }

    @Test
    void removeLastScoreWhenOver100(){
        ScoreTop100List b = new ScoreTop100List();
        for(int i = 0; i <= 120; i++){  //added 120 elements
            b.add(new ScoreObject("Object"+i,new DateTime(),i+100));
        }
        assertEquals(b.get().size(), 100);
    }

  @Test
    void isWellSorted(){
        ScoreTop100List b = new ScoreTop100List();
        for(int i = 0; i <= 120; i++){  //added 120 elements
            b.add(new ScoreObject("Object"+i,new DateTime(),i));
        }
      b.add(new ScoreObject("matned", new DateTime(),9999));

        assertEquals(((ScoreObject) b.get().get(0)).getScore(), 9999);
    }


}