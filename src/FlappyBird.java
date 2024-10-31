import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class FlappyBird extends JPanel implements  ActionListener {
    int boardWidht = 360;
    int boardHeight = 640;

    //IMAGES
    Image backGroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //Bird
    int birdX= boardWidht/8;
    int birdY= boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;




    class Bird{
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img){
            this.img = img;
        }
    }
    // Game Logic
    Bird bird;
    int velocityY = -6;

    Timer gameLoop;


    FlappyBird(){
        setPreferredSize(new Dimension(boardWidht,boardHeight));
        //setBackground(Color.blue);

        //load images
        backGroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        //Bird
        bird = new Bird(birdImg);

        //game timer
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void move(){
        //bir move
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);
    }

    public void draw(Graphics g){
        System.out.println("Draw");

        //Background
        g.drawImage(backGroundImg, 0,0, boardWidht, boardHeight, null);

        //Bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
}
