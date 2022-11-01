package fact.it.game_service.model;

public class GameDTO {
    private int gameId;
    private String question;
    private int level;
    private double x;
    private double y;
    private String correctanswer;
    private String       answertwo;
    private String       answerthree;
    private int scoreOffensive;
    private int        scoreDefensive;
    private String objectName;

    public int getGameId() {
        return gameId;
    }



    public String getQuestion() {
        return question;
    }



    public int getLevel() {
        return level;
    }



    public double getX() {
        return x;
    }



    public double getY() {
        return y;
    }



    public String getCorrectanswer() {
        return correctanswer;
    }



    public String getAnswertwo() {
        return answertwo;
    }



    public String getAnswerthree() {
        return answerthree;
    }



    public int getScoreOffensive() {
        return scoreOffensive;
    }



    public int getScoreDefensive() {
        return scoreDefensive;
    }



    public String getObjectName() {
        return objectName;
    }


}
