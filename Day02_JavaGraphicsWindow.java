/*
 DAY 02 - CREATE A GRAPHICS WINDOW
 Learn: JFrame, JPanel, paintComponent(), Graphics.
*/
import javax.swing.*;
import java.awt.*;

public class Day02_JavaGraphicsWindow extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("My First Computer Graphics Program", 40, 50);
        g.drawRect(50, 80, 200, 120);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Day 02");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(new Day02_JavaGraphicsWindow());
        frame.setVisible(true);
    }
}
