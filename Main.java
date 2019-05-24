import java.util.Scanner;
public class Main
{
    public static void main(String[] args){
        
        HomeScreen screen = new HomeScreen();
        screen.HomeScreen();
        
        //sleep(4000);
        //screen.close();
        
    }
    
    public static void sleep(int n){
        
        try{
            Thread.sleep(n);
        } catch (Exception except) {
            
        }
        
    }
}