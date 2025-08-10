import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class QuizGUI extends JFrame {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private int totalQuestions;
    
    // GUI Components
    private JPanel mainPanel;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton submitButton;
    private JLabel scoreLabel;
    private JLabel progressLabel;
    
    public QuizGUI() {
        initializeQuestions();
        initializeGUI();
        loadQuestion();
    }
    
    private void initializeQuestions() {
        questions = new ArrayList<>();
        
        // Add sample questions
        questions.add(new Question(
            "Which of the following is NOT a primitive data type in Java?",
            new String[]{"int", "String", "boolean", "char"},
            1 // String is at index 1
        ));
        
        questions.add(new Question(
            "What is the correct way to create an object in Java?",
            new String[]{"MyClass obj = new MyClass();", "MyClass obj = MyClass();", "new MyClass() obj;", "MyClass = new obj();"},
            0 // First option is correct
        ));
        
        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"implements", "extends", "inherits", "super"},
            1 // extends is at index 1
        ));
        
        questions.add(new Question(
            "What does JVM stand for?",
            new String[]{"Java Virtual Machine", "Java Variable Method", "Java Verified Machine", "Java Visual Machine"},
            0 // Java Virtual Machine is at index 0
        ));
        
        questions.add(new Question(
            "What is the capital of France?",
            new String[]{"London", "Berlin", "Paris", "Madrid"},
            2 // Paris is at index 2
        ));
        
        currentQuestionIndex = 0;
        score = 0;
        totalQuestions = questions.size();
    }
    
    private void initializeGUI() {
        setTitle("Online Quiz System - MCQ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255)); // Light blue/gray background
        
        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 248, 255));
        
        JLabel titleLabel = new JLabel("Online Quiz System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102)); // Dark blue title
        
        progressLabel = new JLabel("", SwingConstants.CENTER);
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        progressLabel.setForeground(new Color(70, 70, 70));
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(progressLabel, BorderLayout.SOUTH);
        
        // Question Panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
            "Question",
            0, 0,
            new Font("Arial", Font.BOLD, 16),
            new Color(0, 51, 102)
        ));
        questionPanel.setBackground(Color.WHITE);
        
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 20, 15));
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 20));
        optionsPanel.setBackground(Color.WHITE);
        
        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            optionButtons[i].setBackground(Color.WHITE);
            optionButtons[i].setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            optionButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        
        questionPanel.add(questionLabel);
        questionPanel.add(optionsPanel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        submitButton = new JButton("Submit Answer");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(30, 144, 255)); // DodgerBlue
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(150, 35));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new SubmitButtonListener());
        
        // Add hover effects
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(new Color(65, 105, 225)); // darker blue
            }
            public void mouseExited(MouseEvent e) {
                submitButton.setBackground(new Color(30, 144, 255));
            }
        });
        
        buttonPanel.add(submitButton);
        
        // Score Panel (initially hidden)
        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(new Color(34, 139, 34));
        scoreLabel.setVisible(false);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(questionPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            
            // Update progress
            progressLabel.setText("Question " + (currentQuestionIndex + 1) + " of " + totalQuestions);
            
            // Set question text with HTML for better formatting
            questionLabel.setText("<html><div style='width: 500px;'>" + 
                                currentQuestion.getQuestionText() + "</div></html>");
            
            // Set options
            String[] options = currentQuestion.getOptions();
            char optionLetter = 'A';
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(optionLetter + ". " + options[i]);
                optionLetter++;
            }
            
            // Clear previous selection
            buttonGroup.clearSelection();
            
            // Update button text
            if (currentQuestionIndex == questions.size() - 1) {
                submitButton.setText("Finish Quiz");
            } else {
                submitButton.setText("Submit Answer");
            }
        }
    }
    
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if an option is selected
            int selectedOption = -1;
            for (int i = 0; i < 4; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedOption = i;
                    break;
                }
            }
            
            if (selectedOption == -1) {
                JOptionPane.showMessageDialog(QuizGUI.this, 
                    "Please select an answer before submitting!", 
                    "No Answer Selected", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Check if answer is correct
            Question currentQuestion = questions.get(currentQuestionIndex);
            boolean isCorrect = selectedOption == currentQuestion.getCorrectAnswerIndex();
            
            if (selectedOption == currentQuestion.getCorrectAnswerIndex()) {
                score++;
                JOptionPane.showMessageDialog(QuizGUI.this, 
                    "Correct!", 
                    "Result", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                String correctOption = String.valueOf((char)('A' + currentQuestion.getCorrectAnswerIndex()));
                JOptionPane.showMessageDialog(QuizGUI.this, 
                    "Wrong! Correct answer: " + correctOption, 
                    "Result", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
            currentQuestionIndex++;
            
            if (currentQuestionIndex < questions.size()) {
                loadQuestion();
            } else {
                showFinalScore();
            }
        }
    }
    
    private void showFinalScore() {
        // Hide question components
        mainPanel.getComponent(1).setVisible(false); // Question panel
        mainPanel.getComponent(2).setVisible(false); // Button panel
        
        // Create results panel
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(34, 139, 34), 3),
            "Quiz Results",
            0, 0,
            new Font("Arial", Font.BOLD, 18),
            new Color(34, 139, 34)
        ));
        resultsPanel.setBackground(Color.WHITE);
        resultsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Calculate percentage
        double percentage = (double) score / totalQuestions * 100;
        
        // Create result labels
        JLabel congratsLabel = new JLabel(" Quiz Completed! ", SwingConstants.CENTER);
        congratsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        congratsLabel.setForeground(new Color(34, 139, 34));
        congratsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel scoreResultLabel = new JLabel("Your Score: " + score + " out of " + totalQuestions, SwingConstants.CENTER);
        scoreResultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreResultLabel.setForeground(new Color(0, 51, 102));
        scoreResultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel percentageLabel = new JLabel(String.format("Percentage: %.1f%%", percentage), SwingConstants.CENTER);
        percentageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        percentageLabel.setForeground(new Color(70, 70, 70));
        percentageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Performance feedback
        String feedback;
        Color feedbackColor;
        if (percentage >= 90) {
            feedback = " Outstanding! You're a quiz master!";
            feedbackColor = new Color(34, 139, 34);
        } else if (percentage >= 80) {
            feedback = " Excellent work! Great job!";
            feedbackColor = new Color(34, 139, 34);
        } else if (percentage >= 70) {
            feedback = " Good performance! Keep it up!";
            feedbackColor = new Color(255, 140, 0);
        } else if (percentage >= 60) {
            feedback = " Not bad! Room for improvement.";
            feedbackColor = new Color(255, 140, 0);
        } else {
            feedback = " Keep studying! You'll do better next time.";
            feedbackColor = new Color(220, 20, 60);
        }
        
        JLabel feedbackLabel = new JLabel(feedback, SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        feedbackLabel.setForeground(feedbackColor);
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Restart button
        JButton restartButton = new JButton("Take Quiz Again");
        restartButton.setFont(new Font("Arial", Font.BOLD, 14));
        restartButton.setBackground(new Color(30, 144, 255));
        restartButton.setForeground(Color.WHITE);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setFocusPainted(false);
        restartButton.addActionListener(e -> restartQuiz());
        
        // Add hover effects to restart button
        restartButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                restartButton.setBackground(new Color(65, 105, 225));
            }
            public void mouseExited(MouseEvent e) {
                restartButton.setBackground(new Color(30, 144, 255));
            }
        });
        
        // Add components to results panel
        resultsPanel.add(Box.createVerticalStrut(20));
        resultsPanel.add(congratsLabel);
        resultsPanel.add(Box.createVerticalStrut(15));
        resultsPanel.add(scoreResultLabel);
        resultsPanel.add(Box.createVerticalStrut(10));
        resultsPanel.add(percentageLabel);
        resultsPanel.add(Box.createVerticalStrut(15));
        resultsPanel.add(feedbackLabel);
        resultsPanel.add(Box.createVerticalStrut(20));
        resultsPanel.add(restartButton);
        resultsPanel.add(Box.createVerticalStrut(20));
        
        mainPanel.add(resultsPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private void restartQuiz() {
        // Reset quiz state
        currentQuestionIndex = 0;
        score = 0;
        
        // Remove results panel and show question components
        mainPanel.remove(mainPanel.getComponentCount() - 1); // Remove results panel
        mainPanel.getComponent(1).setVisible(true); // Show question panel
        mainPanel.getComponent(2).setVisible(true); // Show button panel
        
        // Load first question
        loadQuestion();
        
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    // Inner class for Question
    private static class Question {
        private String questionText;
        private String[] options;
        private int correctAnswerIndex;
        
        public Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options.clone();
            this.correctAnswerIndex = correctAnswerIndex;
        }
        
        public String getQuestionText() {
            return questionText;
        }
        
        public String[] getOptions() {
            return options.clone();
        }
        
        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new QuizGUI().setVisible(true);
        });
    }

}
