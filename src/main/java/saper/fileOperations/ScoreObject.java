package saper.fileOperations;

import org.joda.time.DateTime;
import java.io.Serializable;

public class ScoreObject implements Serializable {

    private String name;
    private DateTime dateTime;
    private int score;
    private String gameTime;

    public int getScore() {
        return score;
    }

    public ScoreObject(String name, DateTime dateTime, int score) {
        this.name = name;
        this.dateTime = dateTime;
        this.score = score;
    }

    @Override
    public String toString() {
        return name+" >>> scored: "+score+" >>> "+dateTime;
    }
}
