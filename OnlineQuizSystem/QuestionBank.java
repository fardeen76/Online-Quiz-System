import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private List<Question> questions;
    
    public QuestionBank() {
        questions = new ArrayList<>();
        loadQuestions();
    }
    
    private void loadQuestions() {
        // Programming Questions
        questions.add(new Question(
            "Which of the following is NOT a primitive data type in Java?",
            new String[]{"int", "String", "boolean", "char"},
            'B'
        ));
        
        questions.add(new Question(
            "What is the correct way to create an object in Java?",
            new String[]{"MyClass obj = new MyClass();", "MyClass obj = MyClass();", "new MyClass() obj;", "MyClass = new obj();"},
            'A'
        ));
        
        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"implements", "extends", "inherits", "super"},
            'B'
        ));
        
        questions.add(new Question(
            "What does JVM stand for?",
            new String[]{"Java Virtual Machine", "Java Variable Method", "Java Verified Machine", "Java Visual Machine"},
            'A'
        ));
        
        questions.add(new Question(
            "Which of these is used to handle exceptions in Java?",
            new String[]{"try-catch", "if-else", "switch-case", "for-loop"},
            'A'
        ));
        
        // General Knowledge Questions
        questions.add(new Question(
            "What is the capital of France?",
            new String[]{"London", "Berlin", "Paris", "Madrid"},
            'C'
        ));
        
        questions.add(new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"Venus", "Mars", "Jupiter", "Saturn"},
            'B'
        ));
        
        questions.add(new Question(
            "Who painted the Mona Lisa?",
            new String[]{"Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"},
            'C'
        ));
        
        questions.add(new Question(
            "What is the largest ocean on Earth?",
            new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"},
            'D'
        ));
        
        questions.add(new Question(
            "In which year did World War II end?",
            new String[]{"1944", "1945", "1946", "1947"},
            'B'
        ));
    }
    
    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }
    
    public int getTotalQuestions() {
        return questions.size();
    }
}
