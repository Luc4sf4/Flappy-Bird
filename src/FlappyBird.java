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


    // Game Logic
    Bird bird;
    int velocityY = 0;
    int gravity = 1;//every frames bird goes down 1 px

    Timer gameLoop;


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

        //game timer(draws the frame for our game)
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
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


    }

    public void draw(Graphics g) {
        System.out.println("Draw");

        //Background
        g.drawImage(backGroundImg, 0, 0, boardWidht, boardHeight, null);

        //Bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

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

