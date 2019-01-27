package kz.maxim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private int dots;
    private int appleX, appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private Timer timer;
    private Image apple, dot;
    private boolean left = false, right = true, up = false, down = false;
    private boolean inGame = true;

    public GameField(){
        setBackground(Color.BLACK);
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
        initStart();
    }

    public void initStart(){
        loadImage();
        createApple();

        timer = new Timer(200,this);
        dots = 3;

        timer.start();
    }

    public void loadImage(){
        ImageIcon iia = new ImageIcon("resources/apple.jpg");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("resources/dot.jpg");
        dot = iid.getImage();
    }

    public void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }

    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            createApple();
        }
    }

    public void checkCollision(){
        if(x[0] > SIZE || x[0] < 0){
            inGame = false;
        }

        if(y[0] > SIZE || y[0] < 0){
            inGame = false;
        }
    }

    public void move(){
        for(int i = dots; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if(left){
            x[0] -= DOT_SIZE;
        }

        if(right){
            x[0] += DOT_SIZE;
        }

        if(up){
            y[0] -= DOT_SIZE;
        }

        if(down){
            y[0] += DOT_SIZE;
        }
    }

    public void paint(Graphics g){
        super.paint(g);

        if(inGame) {
            g.drawImage(apple,appleX,appleY,this);
            for(int i = dots; i > 0; i--){
                g.drawImage(dot,x[i],y[i],this);
            }

            for(int i = 0; i <= SIZE; i += DOT_SIZE) {
                g.drawLine(i, 0, i, SIZE);
                g.drawLine(0, i, SIZE, i);
            }
        }else{
            g.setColor(Color.WHITE);
            g.drawString("Game over!",135,135);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(inGame){
            checkApple();
            checkCollision();
            move();
        }else{
            timer.stop();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                up = true;
                left = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                down = true;
                left = false;
            }
        }
    }
}
