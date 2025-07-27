import java.util.Scanner;

public class QuizSystem {
    private Scanner scanner;
    private User currentUser;
    
    public QuizSystem() {
        scanner = new Scanner(System.in);
    }
    
    public void run() {
        displayWelcomeMessage();
        
        while (true) {
            if (currentUser == null) {
                login();
            }
            
            showMainMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    startQuiz();
                    break;
                case 2:
                    viewProfile();
                    break;
                case 3:
                    logout();
                    break;
                case 4:
                    exitSystem();
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
    
    private void displayWelcomeMessage() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("🎓 WELCOME TO THE ONLINE QUIZ SYSTEM 🎓");
        System.out.println("=".repeat(80));
        System.out.println("Test your knowledge with our multiple-choice questions!");
        System.out.println("Features: User login, interactive quiz, instant scoring");
        System.out.println("=".repeat(80));
    }
    
    private void login() {
        System.out.println("\n📝 USER LOGIN");
        System.out.println("-".repeat(20));
        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();
        
        if (username.isEmpty()) {
            System.out.println("❌ Username cannot be empty!");
            return;
        }
        
        currentUser = new User(username);
        System.out.println("✅ Welcome, " + username + "! You are now logged in.");
    }
    
    private void showMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📋 MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. 🎯 Start Quiz");
        System.out.println("2. 👤 View Profile");
        System.out.println("3. 🔄 Switch User");
        System.out.println("4. 🚪 Exit");
        System.out.println("=".repeat(50));
        System.out.print("Choose an option (1-4): ");
    }
    
    private int getUserChoice() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void startQuiz() {
        Quiz quiz = new Quiz(currentUser);
        quiz.startQuiz();
        
        System.out.print("\nPress Enter to return to main menu...");
        scanner.nextLine();
    }
    
    private void viewProfile() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("👤 USER PROFILE");
        System.out.println("=".repeat(40));
        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("Last Quiz Score: " + currentUser.getScore() + "/" + currentUser.getTotalQuestions());
        if (currentUser.getTotalQuestions() > 0) {
            System.out.printf("Last Quiz Percentage: %.2f%%\n", currentUser.getPercentage());
        } else {
            System.out.println("No quiz taken yet.");
        }
        System.out.println("=".repeat(40));
        
        System.out.print("\nPress Enter to return to main menu...");
        scanner.nextLine();
    }
    
    private void logout() {
        System.out.println("👋 Goodbye, " + currentUser.getUsername() + "!");
        currentUser = null;
    }
    
    private void exitSystem() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Thank you for using the Online Quiz System!");
        System.out.println("👋 Goodbye and happy learning!");
        System.out.println("=".repeat(50));
    }
    
    public static void main(String[] args) {
        QuizSystem system = new QuizSystem();
        system.run();
    }
}