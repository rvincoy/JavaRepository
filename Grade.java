public class Grade {
    private double Score;
    private double MaxScore;
    private double Weight;
    private String Type;

    public double getScore() {
        return Score;
    }
    public double getMaxScore() {
        return MaxScore;
    }
    public double getWeight() {
        return Weight;
    }
    public String getType() {
        return Type;
    }
    public void setScore(double score) {
        this.Score=score;
    }
    public void setMaxScore(double maxscore) {
        this.MaxScore=maxscore;
    }
    public void setWeight(double weight) {
        this.Weight=weight;
    }
    public void setType(String type) {
        this.Type=type;
    }

    public Grade() {
        this.Score=0;
        this.MaxScore=0;
        this.Weight=0;
        this.Type="";
    }
    public Grade(double score, double maxscore, double weight, String type) {
        this.Score=score;
        this.MaxScore=maxscore;
        this.Weight=weight;
        this.Type=type;
    }
}
