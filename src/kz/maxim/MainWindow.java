package kz.maxim;

import javax.swing.*;

public class MainWindow {
    JFrame window = new JFrame();

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        mw.create();
    }

    public void create(){
        window.setTitle("SnakeGame");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(321,335);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.add(new GameField());
        window.setVisible(true);
    }
}
