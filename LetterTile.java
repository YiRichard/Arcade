import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
public class LetterTile extends JLabel
{
    //width of tile
    private final int width;
    
    //height of tile
    private final int height;
    
    //letter displayed
    private final char letter;
    
    //has all the letter images
    private final String imageDirectory;
    
    //image type
    private final String imageType;
    
    //the path of the image
    private String path;
    
    //image displayed
    private BufferedImage image;
    
    //listener
    private MouseListener tileListener;

  
    public LetterTile() { this('a', "images/", ".png"); }
    
    //makes a letter tile
    public LetterTile(char imageLetter, String Directory, String Type)
    {
        letter = imageLetter;
        imageDirectory = Directory;
        imageType = Type;
        width = 40;
        height = 40;
        setPreferredSize(new Dimension(width, height));
        path = imageDirectory + imageLetter + imageType;
        image = loadImage(path);
    }
    
    //obtains the image from the folder
    private BufferedImage loadImage(String imagePath)
    {
        BufferedImage img = null;

        try 
        {
            img = ImageIO.read(new File(imagePath));
        } 

        catch (IOException ex) 
        {
            System.err.println("loadImage(): Error: Image at "+ imagePath + " could not be found");
            System.exit(1);
        }
        return img;
    }
    
    //removes the tile listener onced it has been guess to keep it from being chosen again
    public char guess() 
    { 
        loadNewImage("guessed");
        removeTileListener();
        return letter;
    }
    
    //hangman image loaded here
    private void loadNewImage(String suffix)
    {
        path = imageDirectory + letter + "_" + suffix + imageType;
        image = loadImage(path);
        repaint();  
    }
    
    //add tile listener
    public void addTileListener(MouseListener l) 
    { 
        tileListener = l;
        addMouseListener(tileListener);
    }

    //remove listener for the tile
    public void removeTileListener() { removeMouseListener(tileListener); }
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(image,0,0,width,height,null);
    }
}