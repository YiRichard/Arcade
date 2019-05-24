import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class defaultGame{
    
    private JPanel panel;
    
    public defaultGame(){
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1280, 720);
        
        Button button = new Button("Go Back");
        button.setBackground(Color.decode("#4286f4"));
        button.setFont(new Font("Monospaced", Font.BOLD, 120));
        button.setBounds(250, 180, 800, 300);
        button.addActionListener(new goBack());
        panel.add(button);
        
        HomeScreen.frame.add(panel);
        panel.setVisible(true);
        System.out.println("There is no game here.");
        
    }
    
    class goBack implements ActionListener{
        public void actionPerformed(ActionEvent e){
            panel.setVisible(false);
            HomeScreen home = new HomeScreen();
            home.HomeScreen();
        }
    }
    
}
