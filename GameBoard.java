import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class GameBoard extends JFrame 
{
    //the word that must be guessed
    private String word;
    
    //used to hide the word
    private StringBuilder wordHidden;
    
    //gameboard width
    private final int width;
    
    //gameboard height
    private final int height;
    
    //incorrect amount that is allowed for each word
    private final int maxIncorrect;
    
    //how many characters per word
    private final int maxWordLength;
    
    //number of incorrect guesses
    private int numIncorrect;
    
      //makes a label of correct guesses
    private JLabel correct;
    
    //makes a label of incorect guesses
    private JLabel incorrect;
    //the letter rack contain all letter
    private Alphabet gameRack;
    
    // a mini placeholder for the image
    private Hangman gameHangman;
        //where to find the images 
    private final String hangmanImageDirectory;
    
    //the type of image needed
    private final String hangmanImageType;
    
    //the name of image
    private final String hangmanImageBaseName;
 
    public GameBoard()
    {
        width = 720;
        height = 720;
        maxWordLength = 100;
        maxIncorrect = 6;
        hangmanImageBaseName = "hangman";
        hangmanImageType = ".png";
        hangmanImageDirectory = "images/";
        setTitle("Hangman");
        setSize(width, height);
        setResizable(false);
        initialize();
    }
    boolean home = false;
    
    //this is used to construct the game
    private void initialize()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Word: ");
        incorrect = new JLabel("Incorrect: " + numIncorrect);
        word = new String();
        wordHidden = new StringBuilder();
        getPassword();
        if(home == true)
        {
            addTextPanel();
            addLetterRack();
            addHangman();
        }
        else{
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(dim.width / 2 - getSize().width / 2,180);
            setVisible(true);
            }
    }
    
    //gets the password
    private void getPassword()
    {
        String[] options = {"Let's Play", "Home"};
        JPanel wordPanel = new JPanel();
        JLabel wordLabel = new JLabel("Enter word to Be Guessed: ");
        JTextField wordText = new JTextField(maxWordLength);
        wordPanel.add(wordLabel);
        wordPanel.add(wordText);
        int confirm = -0;
        
        while (word.isEmpty())
        {
            confirm = JOptionPane.showOptionDialog(null,wordPanel,"Enter Word",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if (confirm == 0)
            {
                word = wordText.getText();
                
               
                if (!word.matches("[a-zA-Z]+") || word.length() > maxWordLength)
                {
                    JOptionPane.showMessageDialog(null,"Word must be less than 100 characters and "+"only contain letters A-Z.","Invalid word",JOptionPane.ERROR_MESSAGE);
                    word = ""; // empty word if error occurs
                }
            }
                    
            else if (confirm == 1)
                home = true;
                break;
        }
        
        
        //word is hidden behind ***
        wordHidden.append(word.replaceAll(".", "*"));
        correct.setText(correct.getText() + wordHidden.toString());
    }
    
    //moves the letter rack where it needs to go and creates it
    private void addLetterRack()
    {
        gameRack = new Alphabet(word,hangmanImageDirectory,hangmanImageType);
        gameRack.attachListeners(new TileListener());
        add(gameRack, BorderLayout.CENTER);
    }
    
       //correct and incorrect labels are made here
    private void addTextPanel()
    {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1,2));
        textPanel.add(correct);
        textPanel.add(incorrect);
        // layout so that everything is viewable
        add(textPanel, BorderLayout.NORTH);
    }
    
    //adds the images to the center of the screen
    private void addHangman()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman = new Hangman(hangmanImageBaseName,hangmanImageDirectory,hangmanImageType);
        hangmanPanel.add(gameHangman);
        add(hangmanPanel, BorderLayout.SOUTH);
    }
    
    //aske if you want to start a new game
    private void newGameDialog()
    {
        int dialogResult = JOptionPane.showConfirmDialog(null,"Word: " + word +"\nStart a New Game?","Play Again?",JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION)
            initialize(); // The board is initialized
        else
            System.exit(0);
    }
    
    //does an action
    private class TileListener implements MouseListener 
    {
        public void mouseClicked(MouseEvent e) {}  
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) 
        {
            Object source = e.getSource();
            if(source instanceof LetterTile)
            {
                char c = ' ';
                int index = 0;
                boolean updated = false;
                
                // cast the source
                LetterTile tilePressed = (LetterTile) source;
                c = tilePressed.guess();
                
                //word will be shown as the player guesses correctly
                while ((index = word.toLowerCase().indexOf(c, index)) != -1)
                {
                    wordHidden.setCharAt(index, word.charAt(index));
                    index++;
                    updated = true;
                }
                
                //everyguess will start a check on win condition
                if (updated)
                {
                    correct.setText("Word: " + wordHidden.toString());
                    
                    if (wordHidden.toString().equals(word))
                    {
                        gameRack.removeListeners();
                        gameHangman.winImage();
                        newGameDialog();
                    }
                }
                
                // however if guess is wrong checks the lose condition
                else
                {
                    incorrect.setText("Incorrect: " + ++numIncorrect);
                    
                    if (numIncorrect >= maxIncorrect)
                    {
                        gameHangman.loseImage();
                        gameRack.removeListeners();
                        newGameDialog();
                    }
                    
                    else
                        gameHangman.nextImage(numIncorrect);
                }
            }
        }
    }
}
