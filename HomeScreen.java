import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class HomeScreen{
    
    //frame for whole program to manipulate
    public static JFrame frame;
    
    //panel for home screen
    private JPanel panel = new JPanel();
    
    //computer arcade at top
    public JLabel arcadeTitle = new JLabel();  
            
    final int height = 2;
    final int width  = 5;
    
    public void HomeScreen(){
        
        frame = new JFrame("Computer Arcade");
        frame.setResizable(false);
        frame.setLocation(160, 120);
        
        panel.setLayout(null);
        
        //making array of pictures to set as icons later; default is white
        Icon[][] icons = new Icon[height][width];
        for(int j = 0; j < icons.length; j++){
            for(int i = 0; i < icons[0].length; i++){
                icons[j][i] = new ImageIcon("white.png");
            }
        }
        
        //set custom icons for games
        icons[0][0] = new ImageIcon("TicTacToe.png");
        icons[0][1] = new ImageIcon("HangmanPicture.png");
        icons[0][2] = new ImageIcon("SnakePicture.png");
        
        //set up listeners for buttons
        class defaultListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                arcadeTitle.setVisible(false);
                panel.setVisible(false);
                defaultGame game = new defaultGame();
                
            }
        }
        
        class TicTacToeListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                arcadeTitle.setVisible(false);
                panel.setVisible(false);
                TicTacToe game = new TicTacToe();
                System.out.println("Starting TicTacToe!");
                
            }
        }
        
        class HangManListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                frame.setVisible(false);
                arcadeTitle.setVisible(false);
                panel.setVisible(false);
                GameBoard newPhantomHangman = new GameBoard();
                System.out.println("Starting Hangman!");
                
            }
        }
        
        class SnakeListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                arcadeTitle.setVisible(false);
                panel.setVisible(false);
                Game.gay();
                System.out.println("Starting Snake!");
                
            }
        }
        
        //icon for home screen jframe
        ImageIcon windowIcon = new ImageIcon("WindowIcon.png");
        frame.setIconImage(windowIcon.getImage());
        
        //computer arcade title
        ImageIcon computerArcade = new ImageIcon("ComputerArcadeTitle.png");
        arcadeTitle.setBounds(190, -310, 1280, 720);
        arcadeTitle.setIcon(computerArcade);
        
        
        //home screen array of buttons
        JButton[][] buttons = new JButton[height][width];
        
        for(int j = 0; j < buttons.length; j++){
            
            for(int i = 0; i < buttons[0].length; i++){
            
                buttons[j][i] = new JButton(icons[j][i]);
                buttons[j][i].setBounds( 10 + ((10 * i+1) + (240 * i)) , (95 + (252 * j)), 241, 241);
                panel.add(buttons[j][i]);
                
            }
            
        }
        
        
        //giving the buttons the ActionListeners they were individually made
        buttons[0][0].addActionListener(new TicTacToeListener());
        buttons[0][1].addActionListener(new HangManListener());
        buttons[0][2].addActionListener(new SnakeListener());
        buttons[0][3].addActionListener(new defaultListener());
        buttons[0][4].addActionListener(new defaultListener());
        
        buttons[1][0].addActionListener(new defaultListener());
        buttons[1][1].addActionListener(new defaultListener());
        buttons[1][2].addActionListener(new defaultListener());
        buttons[1][3].addActionListener(new defaultListener());
        buttons[1][4].addActionListener(new defaultListener());
        

        frame.add(arcadeTitle);
        frame.add(panel);
        
        
        
        //home screen jframe
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        arcadeTitle.setVisible(true);
        panel.setVisible(true);
        
    }
    
}