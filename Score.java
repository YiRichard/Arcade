import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Score extends JPanel implements ActionListener {
    private final static int BOARDWIDTH = 560;
    private final static int BOARDHEIGHT = 720;
    int score = 0;
    private boolean inGame = true;
    private Snake snake = new Snake();
    private Food food = new Food();
    
    
    public Score() {
        setBackground(Color. LIGHT_GRAY);
        setFocusable(true);
        setBounds(720, 0, 560, 720);
        setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));
    }
    private boolean proximity(int a, int b, int closeness) {
        return Math.abs((long) a - b) <= closeness;
    }
    void checkScore() {
        if((proximity(snake.getSnakeX(0), food.getFoodX(), 20)) && (proximity(snake.getSnakeY(0), food.getFoodY(), 20))) {
            score = score + 100;
            System.out.println("Score: " + score);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    void scoreScreen (Graphics g) {
        String message = "Score: " + score;
        Font font = new Font("Comic Sans Ms" , Font.BOLD, 50);
        FontMetrics metrics = getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(message, (560 - metrics.stringWidth(message)) / 2, 340 / 2);
        System.out.println("Score: " + score);
    }
    void endScore (Graphics g) {
        String message = "End Score: " + score;
        Font font = new Font("Comic Sans Ms" , Font.BOLD, 50);
        FontMetrics metrics = getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(message, (560 - metrics.stringWidth(message)) / 2, 720 / 2);
        System.out.println("Score: " + score);
    }
    void draw(Graphics g) {
        if(inGame == true) {
            scoreScreen(g);
        } else {
            endScore(g);
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(inGame == true) {
            checkScore();
        }
        repaint(0, 0, 560, 720);
    }
}