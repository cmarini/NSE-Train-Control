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
import global.*;
import trackmodel.*;
import java.util.ArrayList;

/**
 * 
 * @author AM
 */
public class MapPanel extends JPanel
{
    private boolean debugMode;
    private CTCModel model;
    private ID trackSection; 
    private int greenCoordinates [][][] = {
                                            {{20, 20, 100, 100}, 
                                            {105, 105, 185, 185}, 
                                            {190, 190, 270, 270}, 
                                            {275, 275, 375, 315}, 
                                            {380, 317, 480, 357}, 
                                            {485, 360, 600, 360}},
                                            
                                            {{400, 400, 500, 375}, 
                                            {500, 370, 500, 270}, 
                                            {500, 265, 400, 200}, 
                                            {398, 198, 280, 168}, 
                                            {275, 165, 160, 165}},
                                            
                                            {{650, 10, 600, 10}, 
                                            {645, 10, 595, 10}, 
                                            {590, 10, 540, 10}, 
                                            {535, 10, 485, 10}, 
                                            {480, 10, 430, 10},
                                            {425, 10, 375, 10},
                                            {370, 10, 325, 20},
                                            {320, 22, 280, 40},
                                            {277, 43, 240, 75},
                                            {240, 78, 240, 125},
                                            {240, 128, 240, 164},
                                            {240, 167, 240, 203},
                                            {240, 206, 240, 242},
                                            {240, 245, 240, 281},
                                            {240, 284, 240, 320},
                                            {240, 323, 240, 359},
                                            {240, 362, 240, 401},
                                            {240, 404, 240, 440}},
                                            
                                            {{10, 200, 10, 230}, 
                                            {10, 233, 10, 263}, 
                                            {10, 266, 10, 293}, 
                                            {10, 296, 15, 321}, 
                                            {17, 323, 33, 340},
                                            {35, 342, 65, 350},
                                            {67, 350, 94, 350},
                                            {97, 350, 124, 350},
                                            {127, 350, 154, 350},
                                            {157, 350, 184, 350},
                                            {187, 350, 214, 350},
                                            {217, 350, 244, 350},
                                            {247, 350, 274, 350},
                                            {277, 350, 304, 350},
                                            {307, 350, 334, 350},
                                            {337, 350, 364, 350},
                                            {367, 350, 394, 350},
                                            {397, 350, 424, 350},
                                            {427, 350, 454, 350},
                                            {457, 350, 484, 350},
                                            {487, 350, 514, 350},
                                            {517, 350, 544, 350},
                                            {547, 350, 574, 350},
                                            {577, 350, 604, 350},
                                            {607, 350, 634, 350},
                                            {637, 350, 664, 350},
                                            {667, 350, 694, 350},
                                            {697, 350, 724, 350},
                                            {727, 350, 754, 350}},
                                            
                                            {{50, 50, 250, 125},
                                            {260, 135, 410, 285},
                                            {420, 295, 495, 445}},
                                            
                                            {{650, 10, 650, 55}, 
                                            {650, 60, 650, 105}, 
                                            {650, 110, 650, 155}, 
                                            {650, 160, 650, 205}, 
                                            {650, 210, 650, 255},
                                            {650, 260, 650, 305},
                                            {650, 310, 640, 353},
                                            {638, 355, 628, 396},
                                            {627, 398, 610, 432},
                                            {608, 434, 590, 460},
                                            {588, 462, 560, 475},
                                            {558, 475, 515, 475},
                                            {510, 475, 460, 475},
                                            {455, 475, 405, 475}},
                                            
                                            {{650, 250, 605, 250}, 
                                            {600, 250, 555, 250}, 
                                            {550, 250, 505, 250}, 
                                            {500, 250, 455, 250}, 
                                            {450, 250, 405, 250},
                                            {400, 250, 355, 250},
                                            {350, 250, 305, 250},
                                            {300, 250, 255, 250},
                                            {250, 250, 205, 250},
                                            {200, 250, 155, 250},
                                            {150, 250, 105, 250}},
                                            
                                            {{550, 400, 450, 400},
                                                {445, 400, 345, 400}, 
                                            {340, 400, 260, 380}, 
                                            {255, 375, 200, 340}, 
                                            {200, 335, 200, 235},
                                            {205, 230, 265, 195},
                                            {270, 192, 360, 180}},
                                            
                                            {{20, 20, 120, 20}, 
                                            {125, 20, 195, 90}, 
                                            {200, 95, 250, 175}, 
                                            {255, 180, 255, 280}, 
                                            {260, 285, 310, 345},
                                            {315, 350, 385, 400},
                                            {390, 405, 475, 445}},
                                            
                                            {{530, 465, 542, 455}, 
                                            {544, 455, 562, 455}, 
                                            {564, 455, 582, 455}, 
                                            {584, 455, 602, 455}, 
                                            {604, 455, 622, 455},
                                            {624, 455, 638, 450},
                                            {640, 449, 650, 439},
                                            {652, 437, 657, 423},
                                            {657, 423, 657, 407},
                                            {657, 405, 657, 389},
                                            
                                            {657, 387, 657, 371},
                                            {657, 369, 657, 353},
                                            {657, 351, 657, 335},
                                            {657, 333, 657, 317},
                                            {657, 315, 657, 299},
                                            {657, 297, 657, 281},
                                            {657, 279, 657, 263},
                                            {657, 261, 657, 245},
                                            
                                            {657, 244, 652, 230},
                                            {650, 228, 640, 218},
                                            {638, 216, 624, 210},
                                            {622, 208, 604, 205},
                                            
                                            {602, 205, 584, 205},
                                            {582, 205, 564, 205},
                                            {562, 205, 544, 205},
                                            {542, 205, 524, 205},
                                            {522, 205, 504, 205},
                                            {502, 205, 484, 205},
                                            {482, 205, 464, 205},
                                            {462, 205, 444, 205},
                                            {442, 205, 424, 205},
                                            {422, 205, 404, 205},
                                            {402, 205, 384, 205},
                                            {382, 205, 364, 205}, 
                                            {362, 205, 344, 205}, 
                                            {342, 205, 324, 205}, 
                                            {322, 205, 304, 205},
                                            {302, 205, 284, 205},
                                            {282, 205, 264, 205},
                                            {262, 205, 244, 205},
                                            {242, 205, 224, 205},
                                            {222, 205, 204, 205},
                                            {202, 205, 184, 205},
                                            
                                            {182, 205, 164, 200},
                                            {162, 198, 140, 188},
                                            {138, 186, 131, 174}, 
                                            {130, 173, 130, 155}, 
                                            {130, 153, 130, 135},
                                            {130, 133, 130, 115},
                                            {130, 113, 145, 103}},
                                            
                                            {{20, 20, 120, 20}, 
                                            {125, 20, 195, 90}, 
                                            {200, 95, 250, 175}, 
                                            {255, 180, 255, 280}, 
                                            {260, 285, 310, 345},
                                            {315, 350, 385, 400},
                                            {390, 405, 475, 445}},
                                            
                                            {{10, 200, 10, 230}, 
                                            {10, 233, 10, 263}, 
                                            {10, 266, 10, 293}, 
                                            {10, 296, 15, 321}, 
                                            {17, 323, 33, 340},
                                            {35, 342, 65, 350},
                                            {67, 350, 94, 350},
                                            {97, 350, 124, 350},
                                            {127, 350, 154, 350},
                                            {157, 350, 184, 350},
                                            {187, 350, 214, 350},
                                            {217, 350, 244, 350},
                                            {247, 350, 274, 350},
                                            {277, 350, 304, 350},
                                            {307, 350, 334, 350},
                                            {337, 350, 364, 350},
                                            {367, 350, 394, 350},
                                            {397, 350, 424, 350},
                                            {427, 350, 454, 350},
                                            {457, 350, 484, 350},
                                            {487, 350, 514, 350},
                                            {517, 350, 544, 350},
                                            {547, 350, 574, 350},
                                            {577, 350, 604, 350},
                                            {607, 350, 634, 350},
                                            {637, 350, 664, 350},
                                            {667, 350, 694, 350},
                                            {697, 350, 724, 350},
                                            {727, 350, 754, 350},
                                            {757, 350, 784, 350}}
                                          };
    
    MapPanel(CTCModel m)
    {
        model = m;
        setBackground(Color.WHITE);
        repaint();
    }
    
    MapPanel(CTCModel m, String tc)
    {
        model = m;
        trackSection = setSection(tc);
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
        trackSection = setSection(tc);
        if(debugMode)
        {
            System.out.println("Map Panel: section selected: " + trackSection);
        }
    }
    
    /**
     * 
     * @param tc
     */
    public void setTrackSection(ID tc)
    {
        trackSection = tc;
        if(debugMode)
        {
            System.out.println("Map Panel: section selected: " + trackSection);
        }
    }
    
    /**
     * 
     * @param s
     */
    private ID setSection(String s)
    {
        if(s.length() > 0)
        {
            if(s.contains("Green"))
            {
                switch(s.charAt(s.length()-1))
                {
                    case ' ':
                        return (new ID(Line.GREEN, ' ', -1));
                    case 'A':
                        return (new ID(Line.GREEN, 'A', -1));
                    case 'B':
                        return (new ID(Line.GREEN, 'B', -1));
                    case 'C':
                        return (new ID(Line.GREEN, 'C', -1));
                    case 'D':
                        return (new ID(Line.GREEN, 'D', -1));
                    case 'E':
                        return (new ID(Line.GREEN, 'E', -1));
                    case 'F':
                        return (new ID(Line.GREEN, 'F', -1));
                    case 'G':
                        return (new ID(Line.GREEN, 'G', -1));
                    case 'H':
                        return (new ID(Line.GREEN, 'H', -1));
                    case 'I':
                        return (new ID(Line.GREEN, 'I', -1));
                    case 'J':
                        return (new ID(Line.GREEN, 'J', -1));
                }
            }
            else
            {
                switch(s.charAt(s.length()-1))
                {
                    case ' ':
                        return (new ID(Line.RED, ' ', -1));
                }
            }
        }
        
        return null;
    }
    
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        ArrayList <Track> t;
        
        if(debugMode)
        {
            System.out.println("Map Panel: Track section: " + trackSection + " being painted");
        }
        
        if(trackSection != null)
        {
            switch(trackSection.getLine())
            {
                case GREEN:
                    g.setColor(Color.GREEN);
                    switch(trackSection.getSection())
                    {
                        case ' ':
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
                            break;
                        case 'A':
                            t = model.getWaysideTrack(Line.GREEN, 0);
                            for(int i = 0; i < greenCoordinates[0].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[0][i][0], greenCoordinates[0][i][1], greenCoordinates[0][i][2], greenCoordinates[0][i][3]);
                            }
                            break;
                        case 'B':
                            t = model.getWaysideTrack(Line.GREEN, 1);
                            for(int i = 0; i < greenCoordinates[1].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[1][i][0], greenCoordinates[1][i][1], greenCoordinates[1][i][2], greenCoordinates[1][i][3]);
                            }
                            break;
                        case 'C':
                            t = model.getWaysideTrack(Line.GREEN, 2);
                            for(int i = 0; i < greenCoordinates[2].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[2][i][0], greenCoordinates[2][i][1], greenCoordinates[2][i][2], greenCoordinates[2][i][3]);
                            }
                            break;
                        case 'D':
                            t = model.getWaysideTrack(Line.GREEN, 3);
                            for(int i = 0; i < greenCoordinates[3].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[3][i][0], greenCoordinates[3][i][1], greenCoordinates[3][i][2], greenCoordinates[3][i][3]);
                            }
                            break;
                        case 'E':
                            t = model.getWaysideTrack(Line.GREEN, 4);
                            for(int i = 0; i < greenCoordinates[4].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[4][i][0], greenCoordinates[4][i][1], greenCoordinates[4][i][2], greenCoordinates[4][i][3]);
                            }
                            break;
                        case 'F':
                            t = model.getWaysideTrack(Line.GREEN, 5);
                            for(int i = 0; i < greenCoordinates[5].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[5][i][0], greenCoordinates[5][i][1], greenCoordinates[5][i][2], greenCoordinates[5][i][3]);
                            }
                            break;
                        case 'G':
                            t = model.getWaysideTrack(Line.GREEN, 6);
                            for(int i = 0; i < greenCoordinates[6].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[6][i][0], greenCoordinates[6][i][1], greenCoordinates[6][i][2], greenCoordinates[6][i][3]);
                            }
                            break;
                        case 'H':
                            t = model.getWaysideTrack(Line.GREEN, 7);
                            for(int i = 0; i < greenCoordinates[7].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[7][i][0], greenCoordinates[7][i][1], greenCoordinates[7][i][2], greenCoordinates[7][i][3]);
                            }
                            break;
                        case 'I':
                            t = model.getWaysideTrack(Line.GREEN, 8);
                            for(int i = 0; i < greenCoordinates[8].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[8][i][0], greenCoordinates[8][i][1], greenCoordinates[8][i][2], greenCoordinates[8][i][3]);
                            }
                            break;
                        case 'J':
                            t = model.getWaysideTrack(Line.GREEN, 9);
                            for(int i = 0; i < greenCoordinates[9].length; i++)
                            {
                            if(t.get(i).isOccupied())
                            {
                                g.setColor(Color.BLACK);
                            }
                            else
                            {
                                g.setColor(Color.GREEN);
                            }
                                g.drawLine(greenCoordinates[9][i][0], greenCoordinates[9][i][1], greenCoordinates[9][i][2], greenCoordinates[9][i][3]);
                            }
                            break;
                    }
                    break;
                case RED:
                    g.setColor(Color.RED);
                    switch(trackSection.getSection())
                    {
                        case ' ':
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
                            break;
                    }
                    break;
                default:
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, WIDTH, HEIGHT);
            }
        }
    }
}
