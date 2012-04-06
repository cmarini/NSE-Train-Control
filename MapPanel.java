/*
*	Program Name:	File.java
*	Lead Programmer:	First Last
*	Description:	This class/file/program will…
*	Date Modified:	1/20/12
*/

package CTC;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MapPanel extends JPanel
{
    CTCModel model;
    
    MapPanel(CTCModel m)
    {
        model = m;
        setBackground(Color.WHITE);
        repaint();
    }
    
    public void paintComponent(Graphics g) 
    {
        g.setColor(Color.BLACK);
        
        g.drawLine(0,0,0,800);
        g.drawLine(20,0,20,800);
        g.drawLine(40,0,40,800);
        g.drawLine(60,0,60,800);
        g.drawLine(80,0,80,800);
        g.drawLine(100,0,100,800);
        g.drawLine(120,0,120,800);
        g.drawLine(140,0,140,800);
        g.drawLine(160,0,160,800);
        g.drawLine(180,0,180,800);
        g.drawLine(200,0,200,800);
        g.drawLine(220,0,220,800);
        g.drawLine(240,0,240,800);
        g.drawLine(260,0,260,800);
        g.drawLine(280,0,280,800);
        g.drawLine(300,0,300,800);
        g.drawLine(320,0,320,800);
        g.drawLine(340,0,340,800);
        g.drawLine(360,0,360,800);
        g.drawLine(380,0,380,800);
        g.drawLine(400,0,400,800);
        g.drawLine(420,0,420,800);
        g.drawLine(440,0,440,800);
        g.drawLine(460,0,460,800);
        g.drawLine(480,0,480,800);
        g.drawLine(500,0,500,800);
        g.drawLine(520,0,520,800);
        g.drawLine(540,0,540,800);
        g.drawLine(560,0,560,800);
        g.drawLine(580,0,580,800);
        g.drawLine(600,0,600,800);
        g.drawLine(620,0,620,800);
        g.drawLine(640,0,640,800);
        g.drawLine(660,0,660,800);
        g.drawLine(680,0,680,800);        
        g.drawLine(700,0,700,800);
        g.drawLine(720,0,720,800);
        g.drawLine(740,0,740,800);
        g.drawLine(760,0,760,800);
        g.drawLine(780,0,780,800);
        
        
        g.drawLine(0,0,0,800);
        g.drawLine(0,20,800,20);
        g.drawLine(0,40,800,40);
        g.drawLine(0,60,800,60);
        g.drawLine(0,80,800,80);
        g.drawLine(0,100,800,100);
        g.drawLine(0,120,800,120);
        g.drawLine(0,140,800,140);
        g.drawLine(0,160,800,160);
        g.drawLine(0,180,800,180);
        g.drawLine(0,200,800,200);
        g.drawLine(0,220,800,220);
        g.drawLine(0,240,800,240);
        g.drawLine(0,260,800,260);
        g.drawLine(0,280,800,280);
        g.drawLine(0,300,800,300);
        g.drawLine(0,320,800,320);
        g.drawLine(0,340,800,340);
        g.drawLine(0,360,800,360);
        g.drawLine(0,380,800,380);
        g.drawLine(0,400,800,400);
        g.drawLine(0,420,800,420);
        g.drawLine(0,440,800,440);
        g.drawLine(0,460,800,460);
        g.drawLine(0,480,800,480);
        g.drawLine(0,500,800,500);
        g.drawLine(0,520,800,520);
        g.drawLine(0,540,800,540);
        g.drawLine(0,560,800,560);
        g.drawLine(0,580,800,580);
        g.drawLine(0,600,800,600);
        g.drawLine(0,620,800,620);
        g.drawLine(0,640,800,640);
        g.drawLine(0,660,800,660);
        g.drawLine(0,680,800,680);
        g.drawLine(0,700,800,700);
        g.drawLine(0,720,800,720);
        g.drawLine(0,740,800,740);
        g.drawLine(0,760,800,760);
        g.drawLine(0,780,800,780);
        g.drawLine(0,800,800,800);
        g.drawLine(0,820,800,820);
        
        
    }
}
