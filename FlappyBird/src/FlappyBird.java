//we will be using jpanel inorder to draw our game
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;//inorder to store all the pipes in the game
import java.util.Random;//to place the pipes in different positions in the game
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    int borderwidth = 360;//height and width of the screen according to the background image
    int borderheight = 640;
    
    //window background
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //bird position
    int birdx=borderwidth/8;
    int birdy=borderheight/2;
    int birdwidth=34;
    int birdheight=24;

     class Bird {
        int x=birdx;
        int y=birdy;
        int width=birdwidth; 
        int height=birdheight;
        Image img;

        Bird(Image img){
            this.img=img;
        }
     }


    int pipex= borderwidth;
    int pipey= 0;
    int pipewidth = 64;  //scaled by 1/6;
    int pipeheight = 512;
 
    class Pipe{
        int x = pipex;
        int y = pipey;
        int width = pipewidth;
        int height = pipeheight;
        Image img;
        boolean passed =false ;
        Pipe(Image img){
            this.img=img;
        }
    }



     //game logic
     Bird bird;
     int velocityX= -4; //moves the pipe to the left sid and simulate the bird to move right side
     int velocityY=0;//to fly up
     int gravity=1;//to resist to down
 
    ArrayList <Pipe> pipes;
    Random random = new Random();

     Timer gameloop;// inorder to make the game infinate loop we will be using a timmer
     Timer placePipesTimmer;
     boolean gameOver = false ;
     double score = 0;
 


    FlappyBird(){//constructer
         setPreferredSize(new Dimension(borderwidth,borderheight));
         //setBackground(Color.green);
         setFocusable(true);
         addKeyListener(this);

         
         //load images
         backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
         birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
         topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
         bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
       
         //bird
         bird=new Bird(birdImg);
         pipes=new  ArrayList<Pipe>();



         //place pipe timer
         placePipesTimmer = new Timer(1500 , new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                placePipes();
            }
         });
         placePipesTimmer.start();


         //game timer
         gameloop = new Timer(1000/60 , this); //1000/60 ===> 60 times per second
         gameloop.start();
    }

    public void placePipes(){
        int randomPipeY=(int)(pipey - pipeheight/4 - Math.random()*(pipeheight/2));
        int openingSpace=borderheight/4;//the space between the two pipes
        Pipe topPipe= new Pipe(topPipeImg);
        topPipe.y= randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeheight + openingSpace ;
        pipes.add(bottomPipe);
    }


    //inorder to make the image to the background of the screen we are creating a function
    public void paintComponent(Graphics g){
        //this is function of jpanel
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //background
        g.drawImage(backgroundImg, 0, 0, borderwidth, borderheight , null);// here we are placing the image at top left corner of the screen


        //bird
        g.drawImage(bird.img , bird.x , bird.y , bird.width ,bird.height , null);


        for(int i=0 ; i<pipes.size() ; i++){
            Pipe pipe =pipes.get(i);
            g.drawImage(pipe.img , pipe.x , pipe.y , pipe.width ,pipe.height , null);
        }


        //score
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN , 32));
        if(gameOver){
            g.drawString("Game Over " +  String.valueOf((int)score),10,35);
        }
        else{
            g.drawString(String.valueOf((int)score),10,35);
        }
    }

    public void move(){
        //bird
        velocityY+=gravity;
        bird.y+=velocityY;
        bird.y=Math.max(bird.y,0);

        //pipes
        for(int i=0 ; i<pipes.size() ; i++){
            Pipe pipe =pipes.get(i);
            pipe.x+=velocityX;

            if(!pipe.passed && bird.x > pipe.x + pipe.width){
                pipe.passed = true ;
                score  += 0.5;
            }


            if(collision(bird,pipe)){
                gameOver =true;
            }
        }

        if(bird.y > borderheight){
            gameOver = true ;
        }
    }


    public boolean collision(Bird a,Pipe b){
        return a.x < b.x + b.width && 
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e){//this method is implemented for the loop ==> 60 times per second
        move(); 
        repaint();
        if(gameOver){
            placePipesTimmer.stop();
            gameloop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
         if(e.getKeyCode() == KeyEvent.VK_SPACE){
            velocityY=-9;
            if(gameOver){
                //restart the game resetting the conditions
                bird.y=birdy;
                velocityY = 0;
                pipes.clear();
                score=0;
                gameOver =false ;
                gameloop.start();
                placePipesTimmer.start();
            }
         }
    }

    @Override
    public void keyTyped(KeyEvent e){
        
    }

    @Override
    public void keyReleased(KeyEvent e){
        
    }
}