package de.pb.ui;

import javax.swing.*;

public class GUI {
    public GUI(){
        JFrame obj = new JFrame();
        GamePanel gamePanel = new GamePanel();
        obj.setBounds(10,10,700,600);
        obj.setTitle("BrickBreaker");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePanel);
    }
}
