package kz.maxim;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;

    public GameField(){
        setBackground(Color.BLACK);
    }

    public void paint(Graphics g){
        super.paint(g);

        for(int i = 0; i <= SIZE; i+= DOT_SIZE){
            g.drawLine(i,0,i,SIZE);
            g.drawLine(0,i,SIZE,i);
        }

        g.dispose();
    }
}
