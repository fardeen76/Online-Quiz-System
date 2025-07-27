public class User {
    private String username;
    private int score;
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    
    public User(String username) {
        this.username = username;
        this.score = 0;
        this.totalQuestions = 0;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getScore() {
        return score;
    }
    
    public void incrementScore() {
        this.score++;
        this.correctAnswers++;
    }
    
    public void incrementTotalQuestions() {
        this.totalQuestions++;
    }
    
    public void incrementIncorrectAnswers() {
        this.incorrectAnswers++;
    }
    
    public int getTotalQuestions() {
        return totalQuestions;
    }
    
    public int getCorrectAnswers() {
        return correctAnswers;
    }
    
    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }
    
    public double getPercentage() {
        if (totalQuestions == 0) return 0.0;
        return (double) correctAnswers / totalQuestions * 100;
    }
    
    public void resetStats() {
        this.score = 0;
        this.totalQuestions = 0;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
    }
}
