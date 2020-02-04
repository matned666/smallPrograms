package saper.fileOperations;

import org.joda.time.DateTime;
import java.io.Serializable;

public class ScoreObject implements Serializable {

    private int idNumber;
    private String name;
    private DateTime dateTime;
    private int score;
    private String gameTime;

    public int getScore() {
        return score;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public ScoreObject(String name, DateTime dateTime, int score, String gameTime) {
        this.gameTime = gameTime;
        this.name = name;
        this.dateTime = dateTime;
        this.score = score;
    }

    @Override
    public String toString() {
        return idNumber+": "+ name+" >>> score:"+score+" >>> time:"+gameTime+" >>> date:"+dateTime;
    }
}
