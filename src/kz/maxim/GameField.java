package kz.maxim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private int dots;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private Timer timer;
    private Image apple, dot;
    private boolean left = false, right = true, up = false, down = false;
    private boolean inGame = true;

    public GameField(){
        setBackground(Color.BLACK);
        initStart();
    }

    public void initStart(){
        loadImage();

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

    public void move(){
        for(int i = dots; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if(right){
            x[0] += DOT_SIZE;
        }
    }

    public void paint(Graphics g){
        super.paint(g);

        if(inGame) {

            for(int i = dots; i > 0; i--){
                g.drawImage(dot,x[i],y[i],this);
            }

            for(int i = 0; i <= SIZE; i += DOT_SIZE) {
                g.drawLine(i, 0, i, SIZE);
                g.drawLine(0, i, SIZE, i);
            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(inGame){
            move();
        }
        repaint();
    }
}
