package de.pb.ui;

import de.pb.service.MapGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score =0;
    private int totalBricks=21;

    private Timer timer;
    private int delay =9;

    private int playerX=310;

    private int ballX=180;
    private int ballY=350;
    private int ballDirX=-1;
    private int ballDirY=-2;

    private int row=4;
    private int col=7;

    private MapGenerator map;
    public GamePanel(){
        map = new MapGenerator(row,col);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();


    }
    public void paint (Graphics g){
        //Background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);

        //borders
        g.setColor(Color.GRAY);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(692,0,3,592);

        //peddal
        g.setColor(Color.red);
        g.fillRect(playerX,550,100,8);

        //ball
        g.setColor(Color.yellow);
        g.fillOval(ballX,ballY,20,20);

        //drawing map
        map.draw((Graphics2D) g);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,590,30);
        if(totalBricks<=0){
            play=false;
            ballDirX=0;
            ballDirY=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You WON! \n Score: "+score,190,350);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart",230,400);
        }

        if(ballY>570){
            play=false;
            ballDirX=0;
            ballDirY=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over \n Score: "+score,190,350);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart",230,400);
        }

        g.dispose();

    }



    @Override
    public void actionPerformed(ActionEvent e) {
    timer.start();
    if(play){
        if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(playerX,550,100,8))){
            ballDirY= -ballDirY;
        }

      A:  for (int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[0].length; j++) {
                if(map.map[i][j]>0){
                    int brickX = j* map.bWidth+80;
                    int brickY=i* map.bHeight+50;
                    int bWidth=map.bWidth;
                    int bHeight=map.bHeight;

                    Rectangle rect = new Rectangle(brickX,brickY,bWidth,bHeight);
                    Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);
                    Rectangle brickRect=rect;

                    if(ballRect.intersects(brickRect)){
                        map.setBrickValue(map.map[i][j]-1,i,j);
                        if(map.map[i][j]-1<=0) {
                            totalBricks--;
                            score += 10;
                        }else{
                            score +=5;
                        }


                        if(ballX+19<=brickRect.x||ballX+1>=brickRect.x+brickRect.width){
                            ballDirX= -ballDirX;
                        }else{
                            ballDirY= -ballDirY;
                        }
                        break A;
                    }
                }
            }
        }

        ballX+=ballDirX;
        ballY+=ballDirY;
        if(ballX<0){
            ballDirX= -ballDirX;
        }
        if(ballY<0){
            ballDirY= -ballDirY;
        }
        if(ballX>670){
            ballDirX= -ballDirX;
        }
    }
    repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if (playerX>=600){
                playerX=600;
            }else{
                moveRight();
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if (playerX<=10){
                playerX=10;
            }else{
                moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
        if(!play){
            play=true;
            ballX=180;
            ballY=350;
            ballDirX= -1;
            ballDirY=-2;
            playerX=310;
            score=0;
            totalBricks=21;
            map = new MapGenerator(row,col);
            repaint();

        }
        }
    }

    private void moveLeft() {
        play=true;
        playerX-=20;
    }

    private void moveRight() {
        play=true;
        playerX+=20;
    }






    @Override
    public void keyReleased(KeyEvent e) {}
}
