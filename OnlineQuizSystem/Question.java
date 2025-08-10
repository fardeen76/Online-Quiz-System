public class Question {
    private String questionText;
    private String[] options;
    private char correctAnswer;
    
    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options.clone();
        this.correctAnswer = correctAnswer;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    
    public String[] getOptions() {
        return options.clone();
    }
    
    public char getCorrectAnswer() {
        return correctAnswer;
    }
    
    public boolean isCorrect(char userAnswer) {
        return Character.toLowerCase(userAnswer) == Character.toLowerCase(correctAnswer);
    }
    
    public void displayQuestion(int questionNumber) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Question " + questionNumber + ": " + questionText);
        System.out.println("=".repeat(60));
        
        char optionLetter = 'A';
        for (String option : options) {
            System.out.println(optionLetter + ". " + option);
            optionLetter++;
        }
        
        System.out.print("\nYour answer (A/B/C/D): ");
    }
}