import javax.swing.*;
import java.awt.*;

public class QuizApplication extends JFrame {
    String[] questions = {
        "Which keyword creates a class in Java?",
        "Which method starts a Java program?",
        "Which collection stores a dynamic list?"
    };
    String[][] options = {
        {"class", "new", "void", "object"},
        {"start()", "main()", "run()", "begin()"},
        {"ArrayList", "String", "Scanner", "JFrame"}
    };
    int[] answers = {0, 1, 0};
    int current = 0, score = 0;

    JLabel question = new JLabel();
    JRadioButton[] buttons = new JRadioButton[4];
    ButtonGroup group = new ButtonGroup();

    QuizApplication() {
        setTitle("Java Quiz Application");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1));
        question.setFont(new Font("Arial", Font.BOLD, 17));
        panel.add(question);

        for (int i = 0; i < 4; i++) {
            buttons[i] = new JRadioButton();
            group.add(buttons[i]);
            panel.add(buttons[i]);
        }

        JButton next = new JButton("Next");
        next.addActionListener(e -> nextQuestion());
        panel.add(next);

        add(panel);
        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        question.setText((current + 1) + ". " + questions[current]);
        for (int i = 0; i < 4; i++) buttons[i].setText(options[current][i]);
        group.clearSelection();
    }

    void nextQuestion() {
        for (int i = 0; i < 4; i++)
            if (buttons[i].isSelected() && i == answers[current]) score++;

        current++;
        if (current < questions.length) {
            loadQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "Score: " + score + "/" + questions.length);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new QuizApplication();
    }
}
