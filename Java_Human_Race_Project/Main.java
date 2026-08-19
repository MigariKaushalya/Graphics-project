import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main extends JPanel implements ActionListener, KeyListener {
    private final Timer timer = new Timer(16, this);
    private int playerX = 150, playerY = 360;
    private int velocityY = 0;
    private boolean jumping = false;
    private int obstacleX = 850;
    private int score = 0;
    private int speed = 7;
    private boolean gameOver = false;
    private final Random random = new Random();

    public Main() {
        setPreferredSize(new Dimension(900, 550));
        setFocusable(true);
        addKeyListener(this);
        timer.start();
    }

   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Sky
        g2.setColor(new Color(135, 206, 235));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Sun
        g2.setColor(new Color(255, 210, 60));
        g2.fillOval(720, 50, 80, 80);

        // Clouds
        drawCloud(g2, 100, 70);
        drawCloud(g2, 420, 100);

        // Ground
        g2.setColor(new Color(70, 180, 80));
        g2.fillRect(0, 410, getWidth(), 140);

        // Race track
        g2.setColor(new Color(65, 65, 70));
        g2.fillRect(0, 435, getWidth(), 100);

        // Track lines
        g2.setColor(Color.WHITE);
        for (int x = 0; x < getWidth(); x += 90) {
            g2.fillRect(x, 480, 50, 6);
        }

        // Finish flag
        g2.setColor(Color.BLACK);
        g2.fillRect(760, 190, 6, 220);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                g2.setColor((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                g2.fillRect(766 + col * 18, 190 + row * 18, 18, 18);
            }
        }

        // Runner
        drawRunner(g2, playerX, playerY);

        // Obstacle
        g2.setColor(new Color(220, 70, 55));
        g2.fillRoundRect(obstacleX, 370, 35, 40, 8, 8);
        g2.setColor(Color.WHITE);
        g2.drawString("X", obstacleX + 11, 397);

        // UI
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("Arial", Font.BOLD, 22));
        g2.drawString("🏃 Human Race", 25, 35);
        g2.drawString("Score: " + score, 25, 65);
        g2.setFont(new Font("Arial", Font.PLAIN, 15));
        g2.drawString("Press SPACE to jump over obstacles", 25, 90);

        if (gameOver) {
            g2.setColor(new Color(0, 0, 0, 170));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            g2.drawString("GAME OVER", 315, 245);
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            g2.drawString("Press R to restart", 345, 290);
        }
    }

    private void drawRunner(Graphics2D g, int x, int y) {
        // Head
        g.setColor(new Color(255, 205, 160));
        g.fillOval(x + 15, y - 55, 30, 30);

        // Hair
        g.setColor(new Color(55, 35, 25));
        g.fillOval(x + 15, y - 58, 30, 14);

        // Body / shirt
        g.setColor(new Color(35, 100, 230));
        g.fillRoundRect(x + 12, y - 25, 36, 55, 12, 12);

        // Arms
        g.setColor(new Color(255, 205, 160));
        g.setStroke(new BasicStroke(7));
        g.drawLine(x + 16, y - 12, x - 5, y + 12);
        g.drawLine(x + 44, y - 12, x + 65, y + 8);

        // Shorts
        g.setColor(new Color(35, 35, 110));
        g.fillRect(x + 15, y + 25, 32, 20);

        // Legs
        g.setColor(new Color(255, 205, 160));
        g.setStroke(new BasicStroke(8));
        g.drawLine(x + 22, y + 42, x + 5, y + 70);
        g.drawLine(x + 40, y + 42, x + 58, y + 68);

        // Shoes
        g.setColor(Color.WHITE);
        g.fillOval(x - 2, y + 65, 22, 10);
        g.fillOval(x + 53, y + 63, 22, 10);
    }

    private void drawCloud(Graphics2D g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y + 15, 60, 30);
        g.fillOval(x + 25, y, 55, 45);
        g.fillOval(x + 55, y + 15, 60, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        // Jump physics
        if (jumping) {
            playerY += velocityY;
            velocityY += 1;

            if (playerY >= 360) {
                playerY = 360;
                velocityY = 0;
                jumping = false;
            }
        }

        // Move obstacle
        obstacleX -= speed;

        if (obstacleX < -50) {
            obstacleX = getWidth() + random.nextInt(300);
            score++;
            if (score % 5 == 0) speed++;
        }

        // Collision detection
        Rectangle player = new Rectangle(playerX, playerY - 55, 65, 125);
        Rectangle obstacle = new Rectangle(obstacleX, 370, 35, 40);

        if (player.intersects(obstacle) && !jumping) {
            gameOver = true;
        }

        // Finish line
        if (score >= 20) {
            gameOver = true;
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !jumping && !gameOver) {
            jumping = true;
            velocityY = -17;
        }

        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restart();
        }
    }

    private void restart() {
        playerY = 360;
        obstacleX = 850;
        score = 0;
        speed = 7;
        jumping = false;
        gameOver = false;
        requestFocusInWindow();
        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java Human Race");
            Main game = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(game);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
            game.requestFocusInWindow();
        });
    }
}
