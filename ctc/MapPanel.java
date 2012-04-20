/*
*	Program Name:	MapPanel.java
*	Lead Programmer:	Zachary Sweigart
*	Description:	
*	Date Modified:	4/19/12
*/

package ctc;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 
 * @author AM
 */
public class MapPanel extends JPanel
{
    private boolean debugMode;
    private CTCModel model;
    private String trackSection;
    
    MapPanel(CTCModel m)
    {
        model = m;
        setBackground(Color.WHITE);
        repaint();
    }
    
    MapPanel(CTCModel m, String tc)
    {
        model = m;
        trackSection = tc;
        setBackground(Color.WHITE);
        repaint();
    }
    
    /**
     * 
     * @param d
     */
    public void setDebugMode(boolean d)
    {
        debugMode = d;
    }
    
    /**
     * 
     * @param tc
     */
    public void setTrackSection(String tc)
    {
        trackSection = tc;
    }
    
    /**
     * 
     * @param s
     */
    public void setSection(String s)
    {
        trackSection = s;
        if(debugMode)
        {
            System.out.println("Map Panel: section selected: " + trackSection);
        }
    }
    
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if(!trackSection.isEmpty())
        {
            if(!trackSection.equals(""));
            {
                if(trackSection.equals("Green Line"))
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, WIDTH, HEIGHT);
                    
                    g.setColor(Color.GREEN);
                    
                    g.drawLine(400, 20, 450, 80);
                    g.drawLine(450, 80, 475, 100);
                    g.drawLine(475, 100, 525, 100);
                    g.drawLine(525, 100, 550, 80);
                    g.drawLine(550, 80, 500, 40);
                    g.drawLine(500, 40, 450, 20);
                    g.drawLine(450, 20, 400, 20);
                    
                    g.drawLine(450, 20, 100, 20);
                    g.drawLine(100, 20, 50, 50);
                    g.drawLine(50, 50, 25, 75);
                    g.drawLine(25, 75, 25, 150);
                    
                    g.drawLine(25, 150, 50, 190);
                    g.drawLine(50, 190, 75, 210);
                    g.drawLine(75, 210, 550, 210);
                    g.drawLine(550, 210, 650, 180);
                    
                    g.drawLine(550, 210, 600, 240);
                    g.drawLine(600, 240, 620, 270);
                    g.drawLine(620, 270, 620, 425);
                    g.drawLine(620, 270, 670, 200);
                    
                    g.drawLine(620, 425, 600, 450);
                    g.drawLine(600, 450, 540, 465);
                    g.drawLine(540, 465, 100, 465);
                    
                    g.drawLine(100, 465, 80, 450);
                    g.drawLine(80, 450, 60, 425);
                    g.drawLine(60, 425, 60, 400);
                    g.drawLine(60, 400, 70, 370);
                    g.drawLine(70, 370, 90, 360);
                    g.drawLine(90, 360, 110, 360);
                    g.drawLine(110, 360, 140, 400);
                    g.drawLine(140, 400, 160, 450);
                    g.drawLine(160, 450, 175, 465);
                    
                    g.drawLine(450, 465, 465, 450);
                    g.drawLine(465, 450, 525, 450);
                    
                    g.drawLine(525, 450, 585, 435);
                    g.drawLine(585, 435, 605, 405);
                    g.drawLine(605, 405, 605, 275);
                    
                    g.drawLine(605, 275, 605, 275);
                    g.drawLine(605, 275, 585, 245);
                    g.drawLine(585, 245, 550, 225);
                    g.drawLine(550, 225, 70, 225);
                    
                    g.drawLine(70, 225, 35, 195);
                    g.drawLine(35, 195, 10, 150);
                    g.drawLine(10, 150, 10, 135);
                    g.drawLine(10, 135, 25, 125);
                }
                else
                {
                    if(trackSection.equals("Red Line"))
                    {
                        g.setColor(Color.WHITE);
                        g.fillRect(0, 0, WIDTH, HEIGHT);
                    
                        g.setColor(Color.RED);
                        
                        g.drawLine(475, 100, 525, 75);
                        g.drawLine(525, 75, 575, 40);
                        g.drawLine(575, 40, 610, 20);
                        g.drawLine(610, 20, 620, 20);
                        g.drawLine(620, 20, 650, 40);
                        g.drawLine(650, 40, 670, 70);
                        g.drawLine(670, 70, 670, 80);
                        g.drawLine(670, 80, 660, 150);
                        g.drawLine(670, 80, 620, 90);
                        g.drawLine(620, 90, 500, 100);
                        g.drawLine(500, 100, 375, 100);
                        
                        g.drawLine(375, 100, 350, 120);
                        g.drawLine(350, 120, 325, 170);
                        g.drawLine(325, 170, 325, 400);
                        g.drawLine(325, 400, 310, 440);
                        g.drawLine(310, 440, 275, 465);
                        
                        g.drawLine(275, 465, 100, 465);
                        g.drawLine(100, 465, 50, 440);
                        g.drawLine(50, 440, 40, 400);
                        g.drawLine(40, 400, 40, 380);
                        g.drawLine(40, 380, 60, 350);
                        g.drawLine(60, 350, 90, 350);
                        g.drawLine(90, 350, 100, 375);
                        g.drawLine(100, 375, 120, 445);
                        g.drawLine(120, 445, 145, 465);
                        
                        g.drawLine(325, 375, 300, 365);
                        g.drawLine(300, 365, 300, 310);
                        g.drawLine(300, 310, 325, 300);
                        
                        g.drawLine(325, 265, 300, 255);
                        g.drawLine(300, 255, 300, 200);
                        g.drawLine(300, 200, 325, 190);
                    }
                    else
                    {
                        if(trackSection.equals("Green A"));
                        {
                            
                        }
                    }
                }
            }
        }
        else
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }
    }
}
