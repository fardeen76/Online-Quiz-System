import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    private User user;
    private Scanner scanner;
    
    public Quiz(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
        QuestionBank questionBank = new QuestionBank();
        this.questions = questionBank.getQuestions();
    }
    
    public void startQuiz() {
        user.resetStats();
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("🎯 WELCOME TO THE ONLINE QUIZ SYSTEM 🎯");
        System.out.println("=".repeat(70));
        System.out.println("Hello, " + user.getUsername() + "!");
        System.out.println("You have " + questions.size() + " multiple-choice questions to answer.");
        System.out.println("Each question has 4 options (A, B, C, D).");
        System.out.println("Good luck!");
        
        System.out.print("\nPress Enter to start the quiz...");
        scanner.nextLine();
        
        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            askQuestion(currentQuestion, i + 1);
        }
        
        displayResults();
    }
    
    private void askQuestion(Question question, int questionNumber) {
        question.displayQuestion(questionNumber);
        
        char userAnswer = getUserAnswer();
        user.incrementTotalQuestions();
        
        if (question.isCorrect(userAnswer)) {
            user.incrementScore();
            System.out.println("✅ Correct! Well done.");
        } else {
            user.incrementIncorrectAnswers();
            System.out.println("❌ Incorrect. The correct answer was: " + question.getCorrectAnswer());
        }
        
        if (questionNumber < questions.size()) {
            System.out.print("\nPress Enter to continue to the next question...");
            scanner.nextLine();
        }
    }
    
    private char getUserAnswer() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (input.length() == 1 && (input.charAt(0) >= 'A' && input.charAt(0) <= 'D')) {
                return input.charAt(0);
            } else {
                System.out.print("Invalid input! Please enter A, B, C, or D: ");
            }
        }
    }
    
    private void displayResults() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("🏆 QUIZ COMPLETED! 🏆");
        System.out.println("=".repeat(70));
        
        System.out.println("📊 FINAL RESULTS FOR: " + user.getUsername());
        System.out.println("-".repeat(40));
        System.out.println("Total Questions: " + user.getTotalQuestions());
        System.out.println("Correct Answers: " + user.getCorrectAnswers());
        System.out.println("Incorrect Answers: " + user.getIncorrectAnswers());
        System.out.println("Final Score: " + user.getScore() + "/" + user.getTotalQuestions());
        System.out.printf("Percentage: %.2f%%\n", user.getPercentage());
        
        // Performance feedback
        double percentage = user.getPercentage();
        System.out.println("\n🎭 PERFORMANCE EVALUATION:");
        if (percentage >= 90) {
            System.out.println("🌟 Outstanding! You're a quiz master!");
        } else if (percentage >= 80) {
            System.out.println("🎉 Excellent work! Great job!");
        } else if (percentage >= 70) {
            System.out.println("👍 Good performance! Keep it up!");
        } else if (percentage >= 60) {
            System.out.println("📚 Not bad! Room for improvement.");
        } else {
            System.out.println("📖 Keep studying! You'll do better next time.");
        }
        
        System.out.println("\n" + "=".repeat(70));
    }
}