import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class TicTacToe{
    
    //0: blank
    //1: o
    //2: x
    int[][] board;
    
    
    //board frame
    private JPanel grid;
    
    //frame with components of side panel
    private JPanel sidePanel;
    
    //tic tac toe at top of side panel
    public JLabel ticTacToeTextLabel;
    
    //who's turn it is, false = o's turn, true = x's turn
    boolean turn;
    
    //determines if the computer should play
    boolean computerPlayer;
    
    //button grid to reflect the board
    JButton[][] buttonBoard;
    
    //will store the pictures of x and o for buttons
    Icon x;
    Icon o;
    
    //white.png
    Icon white;
    
    //panel for exit screen
    JPanel end;
    
    //for toggling hotseat
    boolean ai;
    
    //for ending the game
    int result;
    
    //whos turn or who won
    JLabel statusLabel;
    
    //
    boolean gameOver;
    
    public TicTacToe(){
        
        gameOver = false;
        ai = true;
        board = new int[3][3];
        result = 0;
        
        
        grid = new JPanel();
        grid.setLayout(null);
        grid.setBounds(0, 0, 720, 720);
        
        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBounds(0, 720, 540, 720);
        
        grid.setBackground(Color.decode("#4286f4"));
        
        //computer arcade title
        ticTacToeTextLabel = new JLabel();
        ImageIcon ticTacToeText = new ImageIcon("TicTacToeText.png");
        ticTacToeTextLabel.setBounds(720, 10, 540, 60);
        ticTacToeTextLabel.setIcon(ticTacToeText);
        ticTacToeTextLabel.setVisible(true);
        sidePanel.add(ticTacToeTextLabel);
        
        //return to home screen button
        Button goBackButton = new Button("Go Back");
        goBackButton.setBackground(Color.decode("#4286f4"));
        goBackButton.setFont(new Font("Monospaced", Font.BOLD, 87));
        goBackButton.setBounds(730, 600, 523, 72);
        goBackButton.addActionListener(new goBack());
        sidePanel.add(goBackButton);
        
        
        statusLabel = new JLabel("O's Turn");
        //statusLabel.setFont(new Font("Courier", Font.PLAIN,12));
        statusLabel.setBounds(0, 0, 200, 200);
        sidePanel.add(statusLabel);
        
        
        HomeScreen.frame.add(grid);
        grid.setVisible(true);
        HomeScreen.frame.add(sidePanel);
        sidePanel.setVisible(true);
        
        white = new ImageIcon("white.png");
        buttonBoard = new JButton[3][3];
        for(int j = 0; j < buttonBoard.length; j++){
            for(int i = 0; i < buttonBoard[0].length; i++){
            
                buttonBoard[j][i] = new JButton(white);
                buttonBoard[j][i].setBounds( (215*i)+(10*(i + 1))+10, (215*j)+(10*(j + 1)), 215, 215);
                grid.add(buttonBoard[j][i]);
                
            }
        }
        
        x = new ImageIcon("tttx.png");
        o = new ImageIcon("ttto.png");
        
        //make listeners for buttons, add them manually
        class zz implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                play(0, 0);
                
            }
        }
        buttonBoard[0][0].addActionListener(new zz());
        
        class zo implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                play(0, 1);
                
            }
        }
        buttonBoard[0][1].addActionListener(new zo());
        
        class zt implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                play(0, 2);
                
            }
        }
        buttonBoard[0][2].addActionListener(new zt());
        
        class oz implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                play(1, 0);
                
            }
        }
        buttonBoard[1][0].addActionListener(new oz());
        
        class oo implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                play(1, 1);
                
            }
        }
        buttonBoard[1][1].addActionListener(new oo());
        
        class ot implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                play(1, 2);
                
            }
        }
        buttonBoard[1][2].addActionListener(new ot());
        
        class tz implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                play(2, 0);
                
            }
        }
        buttonBoard[2][0].addActionListener(new tz());
        
        class to implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                play(2, 1);
                
            }
        }
        buttonBoard[2][1].addActionListener(new to());
        
        class tt implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                
                play(2, 2);
                
            }
        }
        buttonBoard[2][2].addActionListener(new tt());
        
        end = new JPanel();
        end.setVisible(true);
        end.setBounds(100, 100, 800, 400);
        end.setBackground(Color.decode("#4286f4"));
        
        
    }
    
    public String getTurn(){
        if(turn){
            return "x";
        }                
        return "o";        
    }
    
    private void play(int i, int j){
        
        if(gameOver){
            System.out.println("The game's already over.");
            return;
        }
        
        if(board[i][j] == 0){
            if(turn){
                board[i][j] = 2;
                buttonBoard[i][j].setIcon(x);
                turn = !turn;
            } else {
                board[i][j] = 1;
                buttonBoard[i][j].setIcon(o);
                turn = !turn;
            }
        } else {
            System.out.println("This spot is already marked!");
        }
        

        if(gameResult() != 0){
            if(gameResult() == 1){
                System.out.println("o's won!");
            } else if(gameResult() == 2){
                System.out.println("x's won!");
            } else if(gameResult() == 3){
                System.out.println("game tied!");
            } else {
            
            }
        }
        
        if(gameResult() == 1 || gameResult() == 2){
            gameOver = true;
        }
        
        botPlay();
        
    }
    
    private void botPlay(){
        
        if(ai){
            if(turn){
                
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        if(board[i][j] == 0){
                        
                            play(i ,j);
                            return;
                            
                        }
                    }
                }
                
            }
        }        
    }
    
    private int gameResult(){
        
        // 0 -> not done
        // 1 -> o
        // 2 -> x
        // 3 -> tie
        
        
        //horizontal cases
        if(board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1){
           return 1; 
        }
        
        if(board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1){
           return 1; 
        }
        
        if(board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1){
           return 1; 
        }
        
        
        if(board[0][0] == 2 && board[0][1] == 2 && board[0][2] == 2){
           return 2; 
        }        
        
        if(board[1][0] == 2 && board[1][1] == 2 && board[1][2] == 2){
           return 2; 
        }        
        
        if(board[2][0] == 2 && board[2][1] == 2 && board[2][2] == 2){
           return 2; 
        }
        
        
        //vertical cases
        if(board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1){
           return 1; 
        }
        
        if(board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1){
           return 1; 
        }
        
        if(board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 1){
           return 1; 
        }
        
        
        if(board[0][0] == 2 && board[1][0] == 2 && board[2][0] == 2){
           return 2; 
        }        
        
        if(board[0][1] == 2 && board[1][1] == 2 && board[2][1] == 2){
           return 2; 
        }        
        
        if(board[0][2] == 2 && board[1][2] == 2 && board[2][2] == 2){
           return 2; 
        }
        
        
        //diagonal cases
        if(board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1){
            return 1;
        }
        
        if(board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1){
            return 1;
        }
        
        if(board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2){
            return 2;
        }
        
        if(board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2){
            return 2;
        }
        
        //*************************************************************
        
        boolean isTied = true;
        for(int j = 0; j < board.length; j++){
            for(int i = 0; i < board[0].length; i++){
                if(board[j][i] == 0){
                    isTied = false;
                }
            }
        }
        
        if(isTied){
            return 3;
        }
        
        //*************************************************************
        
        return 0;
        
    }
    
    class goBack implements ActionListener{
        public void actionPerformed(ActionEvent e) {
             
            end.setVisible(false);
            grid.setVisible(false);
            sidePanel.setVisible(false);
             
            System.out.println("Closing TicTacToe!");
            HomeScreen home = new HomeScreen();
            home.HomeScreen();
                
        }
    }
    
}
