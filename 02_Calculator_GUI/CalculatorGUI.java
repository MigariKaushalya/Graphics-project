import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends JFrame implements ActionListener {
    JTextField display = new JTextField();
    double first = 0;
    String operator = "";

    CalculatorGUI() {
        setTitle("Java Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display.setFont(new Font("Arial", Font.BOLD, 25));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] keys = {"7","8","9","/","4","5","6","*","1","2","3","-","0","C","=","+"};

        for (String key : keys) {
            JButton button = new JButton(key);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String key = e.getActionCommand();

        if (key.matches("\\d")) {
            display.setText(display.getText() + key);
        } else if (key.equals("C")) {
            display.setText("");
            first = 0;
            operator = "";
        } else if ("+-*/".contains(key)) {
            first = Double.parseDouble(display.getText());
            operator = key;
            display.setText("");
        } else if (key.equals("=")) {
            double second = Double.parseDouble(display.getText());
            double result = 0;
            if (operator.equals("+")) result = first + second;
            if (operator.equals("-")) result = first - second;
            if (operator.equals("*")) result = first * second;
            if (operator.equals("/")) result = second == 0 ? Double.NaN : first / second;
            display.setText(String.valueOf(result));
            operator = "";
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
