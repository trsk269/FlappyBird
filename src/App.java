import javax.swing.*;
public class App {
    public static void main(String[] args) throws Exception {
        int borderwidth = 360;//height and width of the screen according to the background image
        int borderheight = 640;

        JFrame frame = new JFrame("Flappy Bird");//create an instance of the jframe pass the window name as perameter
        frame.setSize(borderwidth,borderheight);//set the size of the window
        frame.setLocationRelativeTo(null);//to place the window center of the screen
        frame.setResizable(false);//we can't modify the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//by clicking the cancel we can terminate the program
         
        FlappyBird flappyBird=new FlappyBird();
        
        frame.add(flappyBird);
        frame.pack();//if we not mention this then the width and height will take the title bar into the account
        flappyBird.requestFocus();
        frame.setVisible(true);//make the screen visible
    }
}
