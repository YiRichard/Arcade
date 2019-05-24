import java.awt.EventQueue;
import javax.swing.JFrame;
public class Game extends JFrame {
    Game()  {
        add(new Score());
        add(new Board ());
        
        setResizable(true);
        setSize(1280, 720);
        //pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void gay() {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Game();
                frame.setVisible(true);
            }
        });
    }
}