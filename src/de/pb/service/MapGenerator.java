package de.pb.service;

import java.awt.*;

public class MapGenerator {
    public int map[][];
    public int bWidth;
    public int bHeight;

    public MapGenerator(int row,int col){
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j]=3;
            }
        }

        bWidth=540/col;
        bHeight=175/row;
    }
    public void draw(Graphics2D g){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
               if(map[i][j]==3){
                   g.setColor(Color.ORANGE);
                   g.fillRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight);


                   g.setStroke(new BasicStroke(3));
                   g.setColor(Color.BLACK);
                   g.drawRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight);
               }else  if(map[i][j]==2){
                   g.setColor(Color.CYAN);
                   g.fillRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight);


                   g.setStroke(new BasicStroke(3));
                   g.setColor(Color.BLACK);
                   g.drawRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight);
            }else  if(map[i][j]==1){
                   g.setColor(Color.white);
                   g.fillRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight);


                   g.setStroke(new BasicStroke(3));
                   g.setColor(Color.BLACK);
                   g.drawRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight);
        }
    }}}

    public void setBrickValue(int value,int row,int col){
        map[row][col]=value;
    }

}
