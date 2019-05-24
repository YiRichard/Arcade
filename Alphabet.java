import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Alphabet extends JPanel
{
    //letter shown in the array
    private final ArrayList<LetterTile> bank;
    
    //word that be guessed
    private final String word;
    
    //how big the rack can get
    private final int capacity;
    
    //how many coloumns
    private final int rackCols;
    
    //how many rows
    private final int rackRows;
    
    //makes the layout for the rack
    private final GridLayout alphabetLayout;
    
    // directory for the image
    private final String imageDirectory;
    
    //what kind of image
    private final String imageType;
    
    public Alphabet()
    {
        this("word", "images/", ".png");
    }
    
    //based on the word creates the rack
    public Alphabet(String inword, String Directory,String Type)
    {
        rackCols = 13;
        rackRows = 2;
        alphabetLayout = new GridLayout(rackRows, rackCols);
        alphabetLayout.setVgap(5);
        alphabetLayout.setHgap(10);
        capacity = rackRows * rackCols;
        
        imageDirectory = Directory;
        imageType = Type;
        
        bank = new ArrayList<>();
        word = inword;
        
        // add a little padding to make sure the letter rack is centered
        setBorder(BorderFactory.createEmptyBorder(10, 17, 10, 10));
        setLayout(alphabetLayout);
        loadRack();
    }
    
    //build and load letter rack
    private void loadRack()
    {
        buildRack();
        for (LetterTile tile : bank)
            add(tile);
    }
    
    //blends the words from the win condition and alphabet
    private void buildRack()
    {
        StringBuilder wordBuilder = 
        new StringBuilder(word.toLowerCase());
        ArrayList<Character> tiles = new ArrayList<>(); 
        Random rand = new Random();
        int i = 0, j = 0;
        
        // add the password letters to the rack
        while (wordBuilder.length() > 0)
        {
            // make sure the same letters are not put in twice
            if (!tiles.contains(wordBuilder.charAt(0)))
            {
                tiles.add(wordBuilder.charAt(0));
                i++;
            }
            wordBuilder.deleteCharAt(0);
        }
        
        // add random values to fill the remainder of the rack
        for (; i < capacity; i++)
        {
            Character c = 'a'; // 'a' is just a default value
            do
            {
                c = (char) (rand.nextInt(26) + 'a');
            } while (tiles.contains(c));
            tiles.add(c);
        }
        
        // random tiles are grabbed
        for (i = 0; i < capacity; i++)
        {
            j = rand.nextInt(tiles.size());
            bank.add(new LetterTile(tiles.get(j), 
                    imageDirectory, 
                    imageType));
            tiles.remove(j);
        }
    }
    
    //allows each tile to be clicked on
    public void attachListeners(MouseListener l)
    {
        for (LetterTile tile : bank)
            tile.addTileListener(l);
    }
    
    //make the tile unclickable
    public void removeListeners()
    {
        for (LetterTile tile : bank)
            tile.removeTileListener();
    }
}