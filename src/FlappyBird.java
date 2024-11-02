import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidht = 360;
    int boardHeight = 640;

    //IMAGES
    Image backGroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //Bird
    int birdX = boardWidht / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;


    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    //Pipes
    int pipeX = boardWidht;
    int pipeY = 0;//start in the top from the top of the screen on the right side
    int pipeWidth = 64;// scaled by /16
    int pipeHeight = 512;

    class Pipe{
        int x = pipeX;
        int y = pipeY;;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false; //check if the bird passed the pipe

        Pipe(Image img){
            this.img = img;
        }
    }
    ArrayList<Pipe> pipes;

    // Game Logic
    Bird bird;
    int velocityX = -4; //move pipes to eh left speed( simulates bird moving right)
    int velocityY = 0; //bird goes up/down speed
    int gravity = 1;   //every frames bird goes down 1 px

    Timer gameLoop;
    Timer placePipesTimer;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidht, boardHeight));
        //setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);

        //load images
        backGroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        //Bird
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        //place pipes timer
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        //game timer(draws the frame for our game)
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void placePipes(){
        Pipe topPipe = new Pipe(topPipeImg);
        pipes.add(topPipe);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void move() {
        //bir move
        velocityY += gravity;//gravity setting to the bird velocity
        bird.y += velocityY;//bird moves upwards
        bird.y = Math.max(bird.y, 0);// very top of the screen

        //pipes
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }

    }

    public void draw(Graphics g) {
        //Background
        g.drawImage(backGroundImg, 0, 0, boardWidht, boardHeight, null);

        //Bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        //pipes
        for(int i = 0; i <pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


}

