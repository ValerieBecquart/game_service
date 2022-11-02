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

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setCorrectanswer(String correctanswer) {
        this.correctanswer = correctanswer;
    }

    public void setAnswertwo(String answertwo) {
        this.answertwo = answertwo;
    }

    public void setAnswerthree(String answerthree) {
        this.answerthree = answerthree;
    }

    public void setScoreOffensive(int scoreOffensive) {
        this.scoreOffensive = scoreOffensive;
    }

    public void setScoreDefensive(int scoreDefensive) {
        this.scoreDefensive = scoreDefensive;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
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
