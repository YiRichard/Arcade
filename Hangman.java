import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

//hangman images
public class Hangman extends JLabel
{
    //height of images
    private final int height;
    
    //width of image
    private final int width;
    
    //base name of image
    private final String imageBaseName;
    
    //where to find image
    private final String imageDirectory;
    
    //type of image
    private final String imageType;
    
    //what folder
    private String path;
    
    //image
    private BufferedImage image;
    
   
    public Hangman()
    {
        this("hangman", "images/", ".png");
    }
    

    public Hangman(String imageBase, String Directory,String Type)
    {
        height = 255;
        width = 440;
        
        imageBaseName = imageBase;
        imageDirectory = Directory;
        imageType = Type;
        
        
        setPreferredSize(new Dimension(width,height));
        path = imageDirectory + imageBaseName + "_0" + imageType;
        image = loadImage(path);
    }
    
    //loads the image from the file chosen(images folder)
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
    
    //loads next death screen 
    public void nextImage(int imageNumber) 
    { 
        loadNewImage(String.valueOf(imageNumber));
    }
    
    //loads the monster image
    public void loseImage() { loadNewImage("lose"); }
    
    //loads the winner image
    public void winImage() { loadNewImage("win"); }
    
    //loads next hangman image
    private void loadNewImage(String suffix)
    {
        path = imageDirectory + imageBaseName + "_" + suffix + imageType;
        image = loadImage(path);
        repaint();  
    }
    
    
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(image,0,0,width,height,null);
    }
}