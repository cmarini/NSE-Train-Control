/*
*	Program Name:	CTCView.java
*	Lead Programmer:	Zachary Sweigart
*	Description:	
*	Date Modified:	4/19/12
*/

package ctc;

import simulator.Simulator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

/**
 * 
 * @author AM
 */
public class CTCView extends JFrame
{
    private static boolean debugMode;
    private String dispatcherID = "";
    private int blockSelectedIndex = 0;
    private int trainSelectedIndex = 0;
    private int dispatcherSelectedIndex = 0;
    //private int dispatcherSelectedTrainIndex = 0;
    private static CTCModel model = new CTCModel();
    private static CTCControl control = new CTCControl(model);
    private static Simulator sim;
    private boolean isOpen = true;
    private int clockRate = 60;
    private boolean demoMode = false;
    private MainPanel mainPanel;
    private DispatcherPanel dispatcherPanel;
    private OperatorPanel operatorPanel = new OperatorPanel();
    private TrainPanel trainPanel;
    private TrackPanel trackPanel;
    private MetricsPanel metricsPanel;
    private SplashPanel splashPanel = new SplashPanel();
    
    /**
     * 
     */
    public CTCView()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.add(splashPanel);
        this.setSize(800,600);
        this.setVisible(true);
        intialize();
        debugMode = false;
        control.setDebugMode(debugMode);
        model.setDebugMode(debugMode);
    }
    
    /**
     * 
     * @param d
     * @param s
     */
    public CTCView(boolean d, Simulator s)
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.add(splashPanel);
        this.setSize(800,600);
        this.setVisible(true);
        sim = s;
        intialize();
        debugMode = d;
        control.setDebugMode(debugMode);
        model.setDebugMode(debugMode);
    }
    
    private void intialize()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem fileClose = new JMenuItem("Close");
        JMenu viewMenu = new JMenu("View");
        JMenuItem viewMain = new JMenuItem("Main");
        JMenuItem viewDispatcher = new JMenuItem("Dispatcher");
        JMenuItem viewOperator = new JMenuItem("Operator");
        JMenuItem viewTrain = new JMenuItem("Add/Modify Train");
        JMenuItem viewTrack = new JMenuItem("View/Modify Track");
        JMenuItem viewMetrics = new JMenuItem("Metrics");
        JMenu runMenu = new JMenu("Run");
        JMenuItem runDemo = new JMenuItem("Run Demo");
        JMenuItem runCancel = new JMenuItem("Cancel Demo");
        
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(runMenu);
        fileMenu.add(fileClose);
        viewMenu.add(viewMain);
        viewMenu.add(viewDispatcher);
        viewMenu.add(viewOperator);
        viewMenu.add(viewTrain);
        viewMenu.add(viewTrack);
        viewMenu.add(viewMetrics);
        runMenu.add(runDemo);
        runMenu.add(runCancel);
        setJMenuBar(menuBar);
        
        mainPanel = new MainPanel();
        dispatcherPanel = new DispatcherPanel();
        trainPanel= new TrainPanel();
        trackPanel = new TrackPanel();
        metricsPanel = new MetricsPanel();
        
        fileClose.addActionListener(new MenuActionClose());
        viewMain.addActionListener(new MenuOpenScreen(mainPanel));
        viewDispatcher.addActionListener(new MenuDialogLogin(this));
        viewOperator.addActionListener(new MenuOpenScreen(operatorPanel));
        viewTrain.addActionListener(new MenuOpenScreen(trainPanel));
        viewTrack.addActionListener(new MenuOpenScreen(trackPanel));
        viewMetrics.addActionListener(new MenuOpenScreen(metricsPanel));
        runDemo.addActionListener(new MenuActionRunDemo());
        runCancel.addActionListener(new MenuActionCancelDemo());
    }
    
    private class MenuActionRunDemo implements ActionListener
    {
        private MenuActionRunDemo() {}
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(debugMode)
            {
                System.out.println("CTC View: Run Demo menu item clicked");
            }
            demoMode = true;
        }
    }
    
    private class MenuActionCancelDemo implements ActionListener
    {
        private MenuActionCancelDemo() {}
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(debugMode)
            {
                System.out.println("CTC View: Cancel Demo menu item clicked");
            }
            demoMode = false;
        }
    }
    
    private class MenuActionClose implements ActionListener 
    {
        private MenuActionClose() {}
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(debugMode)
            {
                System.out.println("CTC View: Close menu item clicked");
            }
            isOpen = false;
            dispose();
        }

    }
    
    private class MenuDialogLogin implements ActionListener
    {
        private JFrame frame;
        
        private MenuDialogLogin(JFrame f) 
        {
            this.frame = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            LogInDialog login = new LogInDialog(frame, debugMode);
            login.setVisible(true);
            if(login.isSucceeded())
            {
                dispatcherID = login.getVerifiedUsername();
                dispatcherPanel.initialize();
                changePanel(dispatcherPanel);
            }
            else
            {
                changePanel(mainPanel);
            }
                        
        }
    }
    
    private class MenuOpenScreen implements ActionListener 
    {
        private JPanel panel;
        
        private MenuOpenScreen(JPanel pnl) 
        {
            this.panel = pnl;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            changePanel(panel);
        }

    }
    
    private void changePanel(JPanel panel) 
    {
        if(panel instanceof MainPanel)
        {
            if(debugMode)
            {
                System.out.println("CTC View: View Main menu item clicked");
            }
            ((MainPanel)panel).initialize();
        }
        else
        {
            if(panel instanceof DispatcherPanel)
            {
                if(debugMode)
                {
                    System.out.println("CTC View: View Dispatcher menu item clicked");
                }
                ((DispatcherPanel)panel).initialize();
            }
            else
            {
                if(panel instanceof OperatorPanel)
                {
                    if(debugMode)
                    {
                        System.out.println("CTC View: View Operator menu item clicked");
                    }
                    ((OperatorPanel)panel).initialize();
                }  
                else
                {
                    if(panel instanceof TrainPanel)
                    {
                        if(debugMode)
                        {
                            System.out.println("CTC View: View Add/Modify Train menu item clicked");
                        }
                        ((TrainPanel)panel).initialize();
                    }
                    else
                    {
                        if(panel instanceof TrackPanel)
                        {
                            if(debugMode)
                            {
                                System.out.println("CTC View: View View/Modify Track menu item clicked");
                            }
                            ((TrackPanel)panel).initialize();
                        } 
                        else
                        {
                            if(panel instanceof MetricsPanel)
                            {
                                if(debugMode)
                                {
                                    System.out.println("CTC View: View Metrics menu item clicked");
                                }
                                ((MetricsPanel)panel).initialize();
                            }   
                        }
                    }
                }
            }
        }
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().validate();
        update(getGraphics());
    }
    
    /**
     * 
     * @return
     */
    public static boolean getDebugMode()
    {
        return debugMode;
    }
    
    /**
     * 
     * @return
     */
    public static CTCModel getModel()
    {
        return model;
    }
    
    private class MainPanel extends JPanel
    {
        private MapPanel map = new MapPanel(model, "");
        private JLabel clockLabel = new JLabel("Clock Rate (1 hr = )");
        private final String clockRates [] = {"5 minutes", "6 minutes", "10 minutes", "15 minutes", "20 minutes", "30 minutes", "40 minutes", "45 minutes", "60 minutes", "90 minutes", "120 minutes"};
        private JComboBox clockCombo= new JComboBox(clockRates);        
        private JLabel trackSectionLabel = new JLabel("Display track secion: ");
        private final String trackSections [] = {"", "Green Line", "Green A", "Green B", "Green C", "Green D", "Red Line"};
        private JComboBox trackCombo = new JComboBox(trackSections);  
        private Insets insets = new Insets(0,10,0,0);
        private Insets insets2 = new Insets(0,0,0,0);

        MainPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {
            map.setDebugMode(debugMode);
            addComponent(this, map, 0, 0, 4, 1, 1.0, 1.0, insets2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            addComponent(this, clockLabel, 0, 1, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, clockCombo, 1, 1, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, trackSectionLabel, 2, 1, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trackCombo, 3, 1, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            clockCombo.addActionListener(clockComboListener);
            trackCombo.addActionListener(trackComboListener);
            clockCombo.setSelectedItem("60 minutes");
        }
        
        
        ActionListener clockComboListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                int index = (int)cb.getSelectedIndex();
                switch(index)
                {
                    case 0:
                        clockRate = 5;
                        break;
                    case 1:
                        clockRate = 6;
                        break;
                    case 2:
                        clockRate = 10;
                        break;
                    case 3:
                        clockRate = 15;
                        break;
                    case 4:
                        clockRate = 20;
                        break;
                    case 5:
                        clockRate = 30;
                        break;
                    case 6:
                        clockRate = 40;
                        break;
                    case 7:
                        clockRate = 45;  
                        break;
                    case 8:
                        clockRate = 60;  
                        break;
                    case 9:
                        clockRate = 90;  
                        break;
                    default:
                        clockRate = 120;
                }
                //sim.setClockRate(clockRate);
                //model.setClockRate(clockRate);
                if(CTCView.getDebugMode())
                {
                    System.out.println("CTC View: Clock rate set to : " + clockRate);
                }
            }
        };
        
        ActionListener trackComboListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(CTCView.getDebugMode())
                {
                    System.out.println("CTC View: track section selected: " + trackCombo.getSelectedItem().toString());
                }
                map.setSection(trackCombo.getSelectedItem().toString());
                map.repaint();
            }
            
        };
        
        public void paintComponent(Graphics g)
        {
            int width = getWidth();
            int height = getHeight();
            g.setColor(Color.WHITE);
            g.fillRect(0,0, width, height);
        }
    }
    
    private class DispatcherPanel extends JPanel
    {
        private MapPanel map = new MapPanel(model);
        private JLabel dispatcherIDLabel;
        //private JLabel trainID = new JLabel("Train ID");
        //private JComboBox trains = new JComboBox();
        private JLabel trackID = new JLabel("Track ID");
        private JComboBox track = new JComboBox();
        private JLabel setpointLabel = new JLabel("Setpoint");
        private JTextField setpoint = new JTextField();
        private JLabel authorityLabel = new JLabel("Authority");
        private JTextField authority = new JTextField();
        private JButton sendSetpoint;
        private JButton sendAuthority;
        private Insets insets = new Insets(0,0,0,0);
        private Insets insets2 = new Insets(0,0,0,10);
        private String [] trackIDs;
        private String [] trainIDs;

        DispatcherPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {           
            map.setDebugMode(debugMode);
            trackIDs = model.getTrackIDs();
            trainIDs = sim.getTrainIDs();
            
            track = new JComboBox();
            track.addItem("");
            for(int i = 0; i < trackIDs.length; i++)
            {
                track.addItem(trackIDs[i]);
            }
            track.setSelectedIndex(dispatcherSelectedIndex);
            
//            trains = new JComboBox();
//            trains.addItem("");
//            for(int i = 0; i < trainIDs.length; i++)
//            {
//            //System.out.println(trainIDs[i]);
//                trains.addItem(trainIDs[i]);
//            }
//            trains.setSelectedIndex(dispatcherSelectedTrainIndex);
            
            if(CTCView.getDebugMode())
            {
                System.out.println("CTC View: Dispatcher ID: " + dispatcherID);
            }
            if(dispatcherID.equals("sweigartz"))
            {
                map.setTrackSection("Green Line");
            }
            else
            {
                map.setTrackSection("Green Line");
            }
            sendSetpoint = new JButton("Send Setpoint"); 
            sendAuthority = new JButton("Send Authority"); 
            
            dispatcherIDLabel = new JLabel(dispatcherID);
            setpoint.setColumns(10);
            authority.setColumns(10);
            
            sendSetpoint.addActionListener(buttonClick);
            sendAuthority.addActionListener(buttonClick);
            track.addActionListener(trackComboListener);
            //trains.addActionListener(trainsComboListener);
            
            addComponent(this, map, 0, 0, 6, 1, 1.0, 1.0, insets, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            addComponent(this, dispatcherIDLabel, 0, 1, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            //addComponent(this, trainID, 0, 2, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            //addComponent(this, trains, 1, 2, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, setpointLabel, 0, 3, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, setpoint, 1, 3, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);      
            addComponent(this, sendSetpoint, 2, 3, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, trackID, 0, 2, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, track, 1, 2, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, authorityLabel, 3, 3, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, authority, 4, 3, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, sendAuthority, 5, 3, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);
        }

        ActionListener buttonClick = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource().toString().equals(sendSetpoint.toString()))
                {
                    try
                    {
                        int set = Integer.parseInt(setpoint.getText());
                        if(CTCView.getDebugMode() && dispatcherSelectedIndex > 0)
                        {
                            System.out.println("CTC View: Setpoint value: " + set + " sent to " + trackIDs[dispatcherSelectedIndex - 1]);
                        }
                        if(set < 0 || set > 70)
                        {
                            JOptionPane.showMessageDialog(DispatcherPanel.this, "Invalid Setpoint value, please enter a number between 0 and 70", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            if(dispatcherSelectedIndex == 0)
                            {
                               JOptionPane.showMessageDialog(DispatcherPanel.this, "Invalid train selection, please select the train to send the setpoint", "Input Error", JOptionPane.ERROR_MESSAGE); 
                            }
                            else
                            {
                            control.setDispatcherSpeed(set);
                            }
                        }
                    }
                    catch(NumberFormatException p)
                    {
                        JOptionPane.showMessageDialog(DispatcherPanel.this, "Invalid Setpoint value, please enter a number between 0 and 70", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if(e.getSource().toString().equals(sendAuthority.toString()))
                {
                    try
                    {
                        int auth = Integer.parseInt(authority.getText());
                        String trackID = track.getSelectedItem().toString();
                        if(CTCView.getDebugMode() && dispatcherSelectedIndex > 0)
                        {
                            System.out.println("CTC View: Authority value: " + auth + " sent to " + trackIDs[dispatcherSelectedIndex - 1]);
                        }
                        if(auth < 0 || auth > 70)
                        {
                            JOptionPane.showMessageDialog(DispatcherPanel.this, "Invalid Authority value, please enter a number between 0 and 70", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            if(dispatcherSelectedIndex == 0)
                            {
                               JOptionPane.showMessageDialog(DispatcherPanel.this, "Invalid track selection, please select the track to send the authority", "Input Error", JOptionPane.ERROR_MESSAGE); 
                            }
                            else
                            {
                                control.setDispatcherAuthority(auth);
                            }
                        }
                    }
                    catch(NumberFormatException p)
                    {
                        JOptionPane.showMessageDialog(DispatcherPanel.this, "Invalid Authority value, please enter a number between 0 and 70", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        
        ActionListener trackComboListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                dispatcherSelectedIndex = (int)cb.getSelectedIndex();
                String ID = (String)cb.getSelectedItem();
                //Track t = model.getTrack(ID);
                //get all track properties
                //initialize();
                if(CTCView.getDebugMode())
                {
                    System.out.println("Track " + ID + " selected");
                }
            }
        };
        
//        ActionListener trainsComboListener = new ActionListener()
//        {
//            public void actionPerformed(ActionEvent event)
//            {
//                JComboBox cb = (JComboBox)event.getSource();
//                dispatcherSelectedTrainIndex = (int)cb.getSelectedIndex();
//                String ID = (String)cb.getSelectedItem();
//                //Track t = model.getTrack(ID);
//                //get all track properties
//                //initialize();
//                if(CTCView.getDebugMode())
//                {
//                    System.out.println("Track " + ID + " selected");
//                }
//            }
//        };
        
        public void paintComponent(Graphics g)
        {
            int width = getWidth();
            int height = getHeight();
            g.setColor(Color.WHITE);
            g.fillRect(0,0, width, height);
        }
    }
    
    private class OperatorPanel extends JPanel
    {
        private MapPanel map = new MapPanel(model);
        private JLabel trainID = new JLabel("Train ID: ");
        private JComboBox trains = new JComboBox();
        private JLabel speedLabel = new JLabel("Speed: ");
        private JTextField operatorSpeed = new JTextField();
        private JLabel brakeLabel = new JLabel ("Emergency Brake: ");
        private JCheckBox brake = new JCheckBox("");
        private JButton send;
        private Insets insets = new Insets(0,0,0,0);
        private Insets insets2 = new Insets(0,0,0,10);
        private JLabel trackSectionLabel = new JLabel("Display track secion: ");
        private final String trackSections [] = {"", "Green Line", "Green A", "Green B", "Green C", "Green D"};
        private JComboBox trackCombo = new JComboBox(trackSections);  
        private MaskFormatter format;

        OperatorPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {
            map.setDebugMode(debugMode);
            map.setTrackSection("");
            send = new JButton("Send");
            operatorSpeed.setColumns(10);
            
            send.addActionListener(buttonClick);
            trackCombo.addActionListener(trackComboListener);
            
            addComponent(this, map, 0, 0, 6, 1, 1.0, 1.0, insets, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            addComponent(this, trainID, 0, 1, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trains, 1, 1, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, speedLabel, 0, 2, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, operatorSpeed, 1, 2, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, brakeLabel, 3, 2, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, brake, 4, 2, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, trackSectionLabel, 3, 1, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trackCombo, 4, 1, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, send, 5, 2, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);
        }

        ActionListener buttonClick = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource().toString().equals(send.toString()))
                {
                    try
                    {
                        int set = Integer.parseInt(operatorSpeed.getText());
                        if(CTCView.getDebugMode())
                        {   
                            System.out.println("CTC View: Speed Entered: " + set);
                        }
                        if(set < 0 || set > 100)
                        {
                            JOptionPane.showMessageDialog(OperatorPanel.this, "Invalid Speed value, please enter a number between 0 and 100", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            sendOperatorCommands();
                        }
                    }
                    catch(NumberFormatException p)
                    {
                        JOptionPane.showMessageDialog(OperatorPanel.this, "Invalid Speed value, please enter a number between 0 and 100", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        
        ActionListener trackComboListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(CTCView.getDebugMode())
                {
                    System.out.println("CTC View: track section selected: " + trackCombo.getSelectedItem().toString());
                }
                map.setSection(trackCombo.getSelectedItem().toString());
                map.repaint();
            }
            
        };

        public void sendOperatorCommands()
        {
            if(CTCView.getDebugMode())
            {
                System.out.println("CTC View: Commands sent, speed: " + operatorSpeed.getText() + " , brake: " + brake.isSelected());
            }
            control.setOperatorCommands(Integer.parseInt(operatorSpeed.getText()), brake.isSelected());
        }
        
        public void paintComponent(Graphics g)
        {
            int width = getWidth();
            int height = getHeight();
            g.setColor(Color.WHITE);
            g.fillRect(0,0, width, height);
        }
    }
    
    private class TrainPanel extends JPanel
    {
        private String [] lines = {"Green", "Red"};
        private String [] trainYards = {"", "Train Yard 1"};
        private JLabel currentTrainsLabel = new JLabel("Current Trains");
        private JLabel trainYardSelectLabel = new JLabel("Add new train from train yard: ");
        private JLabel lineLabel = new JLabel("Line");
        private JLabel heightLabel = new JLabel("Height");
        private JLabel widthLabel = new JLabel("Width");
        private JLabel numCarsLabel = new JLabel("Number of Cars");
        private JLabel lengthLabel = new JLabel("Length");
        private JLabel massLabel = new JLabel("Mass");
        private JLabel crewCountLabel = new JLabel("Crew Count");
        private JLabel passengerCountLabel = new JLabel("Passenger Count");
        private JLabel currentSpeedLabel = new JLabel("Current Speed");
        private JLabel currentAccelerationLabel = new JLabel("Current Acceleration");
        private JLabel messageLabel = new JLabel("Message Displayed");
        private JLabel headlightLabel = new JLabel("Headlights on");
        private JLabel cabinLightLabel = new JLabel("Cabin Lights on");
        private JLabel doorLabel = new JLabel("Doors Open");
        private JLabel brakeFailLabel = new JLabel("Brake Failure");
        private JLabel engineFailLabel = new JLabel("Engine Failure");
        private JLabel signalPickupFailLabel = new JLabel("Signal Pickup Failure");
        private JLabel trainIDLabel = new JLabel("Train ID");
        private JComboBox currentTrains;
        private JComboBox trainYard = new JComboBox(trainYards);
        private JComboBox line = new JComboBox(lines);
        private JTextField heightField = new JTextField();
        private JTextField widthField = new JTextField();
        private JTextField numCarsField = new JTextField();
        private JTextField lengthField = new JTextField();
        private JTextField massField = new JTextField();
        private JTextField crewCountField = new JTextField();
        private JTextField passengerCountField = new JTextField();
        private JTextField currentSpeedField = new JTextField();
        private JTextField currentAccelerationField = new JTextField();
        private JTextField messageField = new JTextField();
        private JFormattedTextField trainIDField;
        private JCheckBox headlightCheck = new JCheckBox();
        private JCheckBox cabinLightCheck = new JCheckBox();
        private JCheckBox doorCheck = new JCheckBox();
        private JCheckBox brakeFailCheck;
        private JCheckBox engineFailCheck;
        private JCheckBox signalPickupFailCheck;
        private JButton emergencyBrakeButton;
        private JButton addTrainButton;
        private JButton removeTrainButton;
        private Insets insets = new Insets(5,20,0,20);
        private int height;
        private int width;
        private int numCars;
        private int length;
        private int mass;
        private int crewCount;
        private int passCount;
        private int currentSpeed;
        private int currentAcceleration;
        private String message;
        private boolean cabinLights;
        private boolean headlights;
        private boolean doors;
        private boolean brakeFailure;
        private boolean engineFailure;
        private boolean signalFailure;
        private int lineIndex;
        private MaskFormatter format;
        private String trainIDs [];
        private String enteredTrainID;
        
        TrainPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {
            trainIDs = sim.getTrainIDs();
            currentTrains = new JComboBox();
            
            currentTrains.addItem("");
            for(int i = 0; i < trainIDs.length; i++)
            {
                currentTrains.addItem(trainIDs[i]);
            }
            currentTrains.setSelectedIndex(trainSelectedIndex);
            line.setSelectedIndex(lineIndex);
            
            try
            {
                format = new MaskFormatter("**********");
                trainIDField = new JFormattedTextField(format);
            }
            catch(ParseException e)
            {
                System.err.println("Unable to add format");
            }
                        
            emergencyBrakeButton = new JButton("Emergency Brake");
            addTrainButton = new JButton("Add Train");
            removeTrainButton = new JButton("Remove Train");
            
            brakeFailCheck = new JCheckBox();
            engineFailCheck = new JCheckBox();
            signalPickupFailCheck = new JCheckBox();
            
            emergencyBrakeButton.addActionListener(emergencyBrakeButtonListener);
            addTrainButton.addActionListener(addTrainButtonListener);
            removeTrainButton.addActionListener(removeTrainButtonListener);
            brakeFailCheck.addActionListener(brakeFailCheckListener);
            engineFailCheck.addActionListener(engineFailCheckListener);
            signalPickupFailCheck.addActionListener(signalPickupFailCheckListener);
            currentTrains.addActionListener(trainComboListener);
            trainIDField.addActionListener(trainIDFieldListener);
            trainIDField.addPropertyChangeListener(trainIDFieldPropertyListener);
            
            heightField.setColumns(10);
            widthField.setColumns(10);
            numCarsField.setColumns(10);
            lengthField.setColumns(10);
            massField.setColumns(10);
            crewCountField.setColumns(10);
            passengerCountField.setColumns(10);
            currentSpeedField.setColumns(10);
            currentAccelerationField.setColumns(10);
            messageField.setColumns(10);
            trainIDField.setColumns(10);
            
            heightField.setEditable(false);
            widthField.setEditable(false);
            numCarsField.setEditable(false);
            lengthField.setEditable(false);
            massField.setEditable(false);
            crewCountField.setEditable(true);
            passengerCountField.setEditable(false);
            currentSpeedField.setEditable(false);
            currentAccelerationField.setEditable(false);
            messageField.setEditable(false);
            trainIDField.setEditable(true);
            cabinLightCheck.setEnabled(false);
            headlightCheck.setEnabled(false);
            doorCheck.setEnabled(false);
            
            heightField.setText("" + height);
            widthField.setText("" + width);
            numCarsField.setText("" + numCars);
            lengthField.setText("" + length);
            massField.setText("" + mass);
            crewCountField.setText("" + crewCount);
            passengerCountField.setText("" + passCount);
            currentSpeedField.setText("" + currentSpeed);
            currentAccelerationField.setText("" + currentAcceleration);
            if(message == null)
            {
                messageField.setText("");
            }
            else
            {
                messageField.setText("" + message);
            }
            cabinLightCheck.setSelected(cabinLights);
            headlightCheck.setSelected(headlights);
            doorCheck.setSelected(doors);
            brakeFailCheck.setSelected(brakeFailure);
            engineFailCheck.setSelected(engineFailure);
            signalPickupFailCheck.setSelected(signalFailure);
                       
            addComponent(this, currentTrainsLabel, 0, 0, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, currentTrains, 1, 0, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, trainYardSelectLabel, 2, 0, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trainYard, 3, 0, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, trainIDLabel, 2, 1, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trainIDField, 3, 1, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, lineLabel, 0, 2, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, line, 1, 2, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, heightLabel, 0, 3, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, heightField, 1, 3, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, widthLabel, 0, 4, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, widthField, 1, 4, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, numCarsLabel, 0, 5, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, numCarsField, 1, 5, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, lengthLabel, 0, 6, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, lengthField, 1, 6, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, massLabel, 0, 7, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, massField, 1, 7, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, crewCountLabel, 0, 8, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, crewCountField, 1, 8, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, passengerCountLabel, 0, 9, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, passengerCountField, 1, 9, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, currentSpeedLabel, 0, 10, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, currentSpeedField, 1, 10, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, currentAccelerationLabel, 0, 11, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, currentAccelerationField, 1, 11, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, messageLabel, 0, 12, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, messageField, 1, 12, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, cabinLightLabel, 0, 13, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, cabinLightCheck, 1, 13, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, headlightLabel, 0, 14, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, headlightCheck, 1, 14, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, doorLabel, 0, 15, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, doorCheck, 1, 15, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, brakeFailLabel, 0, 16, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, brakeFailCheck, 1, 16, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, engineFailLabel, 0, 17, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, engineFailCheck, 1, 17, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, signalPickupFailLabel, 0, 18, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, signalPickupFailCheck, 1, 18, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, emergencyBrakeButton, 1, 19, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, addTrainButton, 2, 19, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, removeTrainButton, 3, 19, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            
        }
        
        ActionListener trainIDFieldListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JFormattedTextField jft = (JFormattedTextField)event.getSource();
                enteredTrainID = (String)jft.getValue();
                if(CTCView.getDebugMode())
                {
                    System.out.println("CTC View: Train ID entered " + enteredTrainID);
                }
            }
        };
        
        PropertyChangeListener trainIDFieldPropertyListener = new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent event)
            {
                JFormattedTextField jft = (JFormattedTextField)event.getSource();
                enteredTrainID = (String)jft.getValue();
                if(CTCView.getDebugMode())
                {
                    System.out.println("CTC View: Train ID entered " + enteredTrainID);
                }
            }
        };
        
        ActionListener trainComboListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                trainSelectedIndex = (int)cb.getSelectedIndex();
                if(CTCView.getDebugMode())
                {
                    System.out.println("CTC View: Train selected " + ID);
                }
                //TrainController t = sim.getTrainController(ID);
                //get all train properties
                //initialize();
            }
        };
        
        ActionListener emergencyBrakeButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String ID = trainIDs[trainSelectedIndex - 1];
                if(CTCView.getDebugMode())
                {
                    System.out.println("CTC View: Emergency Brake Signal sent to Train: " + ID);
                }
                //TrainController t = sim.getTrainController(ID);
                //t.emergencyBrake();
                //initialize();
            }
        };
                
        ActionListener addTrainButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(CTCView.getDebugMode())
                {
                    System.out.println("CTC View: Train created: " + enteredTrainID);
                }
                //sim.createTrain(line, crewCount, trainID);
                //t = sim.getTrain(trainID);
                //initialize();
            }
        };
                        
        ActionListener removeTrainButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String ID = trainIDs[trainSelectedIndex - 1];
                if(CTCView.getDebugMode())
                {
                    System.out.println("CTC View: Train: " + ID + " sent remove command");
                }
                //TrainController t = getTrainController(ID);
                //t.remove();
                //initialize();
            }
        };
        
        ActionListener brakeFailCheckListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JCheckBox cb = (JCheckBox)event.getSource();
                String ID = trainIDs[trainSelectedIndex - 1];
                //TrainController t = getTrainController(ID);
                if(cb.isSelected())
                {
                    //t.fail(0);
                    if(CTCView.getDebugMode())
                    {
                        System.out.println("CTC View: Train: " + ID + " sent brake failure command");
                    } 
                }
                else
                {
                    //t.fail(false);
                    if(CTCView.getDebugMode())
                    {
                        System.out.println("CTC View: Train: " + ID + " sent fix brake failure command");
                    } 
                }
                //initialize();
            }
        };
        
        ActionListener engineFailCheckListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JCheckBox cb = (JCheckBox)event.getSource();
                String ID = trainIDs[trainSelectedIndex - 1];
                //TrainController t = getTrainController(ID);
                if(cb.isSelected())
                {
                    //t.fail(1);
                    if(CTCView.getDebugMode())
                    {
                        System.out.println("CTC View: Train: " + ID + " sent engine failure command");
                    } 
                }
                else
                {
                    //t.fail(false);
                    if(CTCView.getDebugMode())
                    {
                        System.out.println("CTC View: Train: " + ID + " sent fix engine failure command");
                    } 
                }
                //initialize();
            }
        };
                
        ActionListener signalPickupFailCheckListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JCheckBox cb = (JCheckBox)event.getSource();
                String ID = trainIDs[trainSelectedIndex - 1];
                //TrainController t = getTrainController(ID);
                if(cb.isSelected())
                {
                    //t.fail(2);
                    if(CTCView.getDebugMode())
                    {
                        System.out.println("CTC View: Train: " + ID + " sent signal pickup failure command");
                    } 
                }
                else
                {
                    //t.fail(false);
                    if(CTCView.getDebugMode())
                    {
                        System.out.println("CTC View: Train: " + ID + " sent fix signal pickup failure command");
                    } 
                }
                //initialize();
            }
        };
        
        public void paintComponent(Graphics g)
        {
            int w = getWidth();
            int h = getHeight();
            g.setColor(Color.WHITE);
            g.fillRect(0,0, w, h);
        }
    }
    
    private class TrackPanel extends JPanel
    {
        private String [] failureTypes = {"Broken Rail", "Power Failure", "Circuit Failure"};
        private String [] lines = {"Green", "Red"};
        private JLabel lineLabel = new JLabel("Select Line");
        private JLabel controllerLabel = new JLabel("Select Wayside");
        private JLabel trackLabel = new JLabel("Select Track");
        private JLabel speedLimitLabel = new JLabel("Speed Limit");
        private JLabel elevationLabel = new JLabel("Elevation");
        private JLabel gradeLabel = new JLabel("Grade");
        private JLabel blockSizeLabel = new JLabel("Block Size");
        private JLabel trackTypeLabel = new JLabel("Track Type");
        private JLabel passengersBoardingLabel = new JLabel("Passengers Boarding");
        private JLabel passengersDisembarkingLabel = new JLabel("Passengers Disembarking");
        private JComboBox line;
        private JComboBox controller;
        private JComboBox track;
        private JComboBox failType = new JComboBox(failureTypes);
        private JTextField speedLimitField = new JTextField();
        private JTextField elevationField = new JTextField();
        private JTextField gradeField = new JTextField();
        private JTextField blockSizeField = new JTextField();
        private JTextField trackTypeField = new JTextField();
        private JTextField passengersBoardingField = new JTextField();
        private JTextField passengersDisembarkingField = new JTextField();
        private JButton closeButton = new JButton("Close");
        private JButton openButton = new JButton("Open");
        private JButton breakButton = new JButton("Break");
        private JButton fixButton = new JButton("Fix");
        private Insets insets = new Insets(5,20,0,20);
        private int speedLimit;
        private int elevation;
        private int grade;
        private int blockSize;
        private int trackType;
        private int passengersBoarding;
        private int passengersDisembarking;
        private String trackIDs[];
        private String controllerIDs [];
                
        TrackPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {
            trackIDs = model.getTrackIDs();
            
            line = new JComboBox();
            for(int i = 0; i < lines.length; i++)
            {
                line.addItem(lines[i]);
            }
            
            //controllerIDs = model.getControllerIDs(line.getSelectedItem());
            
            controller = new JComboBox();
            controller.addItem("");
            for(int i = 0; i < lines.length; i++)
            {
                //controller.addItem(controllerIDs[i]);
            }
                        
            track = new JComboBox();
            track.addItem("");
            for(int i = 0; i < trackIDs.length; i++)
            {
                track.addItem(trackIDs[i]);
            }
            track.setSelectedIndex(blockSelectedIndex);
            
            closeButton = new JButton("Close");
            openButton = new JButton("Open");
            breakButton = new JButton("Break");
            fixButton = new JButton("Fix");
            
            track.addActionListener(trackComboListener);
            closeButton.addActionListener(closeButtonListener);
            openButton.addActionListener(openButtonListener);
            breakButton.addActionListener(breakButtonListener);
            fixButton.addActionListener(fixButtonListener);
                        
            speedLimitField.setColumns(10);
            elevationField.setColumns(10);
            gradeField.setColumns(10);
            blockSizeField.setColumns(10);
            trackTypeField.setColumns(10);
            passengersBoardingField.setColumns(10);
            passengersDisembarkingField.setColumns(10);
            
            speedLimitField.setEditable(false);
            elevationField.setEditable(false);
            gradeField.setEditable(false);
            blockSizeField.setEditable(false);
            trackTypeField.setEditable(false);
            passengersBoardingField.setEditable(false);
            passengersDisembarkingField.setEditable(false);
            track.setEditable(false);
            
            speedLimitField.setText(""+speedLimit);
            elevationField.setText(""+elevation);
            gradeField.setText(""+grade);
            blockSizeField.setText(""+blockSize);
            trackTypeField.setText(""+trackType);
            passengersBoardingField.setText(""+passengersBoarding);
            passengersDisembarkingField.setText(""+passengersDisembarking);
            
            addComponent(this, lineLabel, 0, 0, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, line, 1, 0, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, controllerLabel, 0, 1, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, controller, 1, 1, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, trackLabel, 0, 2, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, track, 1, 2, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, speedLimitLabel, 0, 3, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, speedLimitField, 1, 3, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, elevationLabel, 0, 4, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, elevationField, 1, 4, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, gradeLabel, 0, 5, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, gradeField, 1, 5, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, blockSizeLabel, 0, 6, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, blockSizeField, 1, 6, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, trackTypeLabel, 0, 7, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trackTypeField, 1, 7, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, passengersBoardingLabel, 0, 8, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, passengersBoardingField, 1, 8, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, passengersDisembarkingLabel, 0, 9, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, passengersDisembarkingField, 1, 9, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, closeButton, 0, 10, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, openButton, 0, 11, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, breakButton, 0, 12, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, failType, 1, 12, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, fixButton, 0, 13, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
        }
        
        ActionListener trackComboListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                blockSelectedIndex = (int)cb.getSelectedIndex();
                String ID = (String)cb.getSelectedItem();
                //Track t = model.getTrack(ID);
                //get all track properties
                //initialize();
                if(CTCView.getDebugMode())
                {
                    System.out.println("Track " + ID + " selected");
                }
            }
        };
        
        ActionListener closeButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String ID = (String)trackIDs[blockSelectedIndex - 1];
                //Track t = model.getTrack();
                //track.close();
                //initialize();
                if(CTCView.getDebugMode())
                {
                    System.out.println("Track " + ID + " closed");
                }
            }
        };
        
        ActionListener openButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String ID = (String)trackIDs[blockSelectedIndex - 1];
                //Track t = model.getTrack();
                //t.open();
                //initialize();
                if(CTCView.getDebugMode())
                {
                    System.out.println("Track " + ID + " opened");
                }
            }
        };
        
        ActionListener breakButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String ID = (String)trackIDs[blockSelectedIndex - 1];
                String type = (String)failType.getSelectedItem();
                //Track t = model.getTrack();
                //t.fail(type);
                //initialize();
                if(CTCView.getDebugMode())
                {
                    System.out.println("Track " + ID + " broken: " + type);
                }
            }
        };
        
        ActionListener fixButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String ID = (String)trackIDs[blockSelectedIndex - 1];
                //Track t = model.getTrack();
                //t.fix();
                //initialize();
                if(CTCView.getDebugMode())
                {
                    System.out.println("Track " + ID + " fixed");
                }
            }
        };
        
        public void paintComponent(Graphics g)
        {
            int width = getWidth();
            int height = getHeight();
            g.setColor(Color.WHITE);
            g.fillRect(0,0, width, height);
        }
    }
    
    private class MetricsPanel extends JPanel
    {
        private JLabel throughputLabel = new JLabel("Throughput");
        private JLabel capacityLabel = new JLabel("Capacity");
        private JLabel occupancyLabel = new JLabel("Occupancy");
        private JTextField throughputField = new JTextField();
        private JTextField capacityField = new JTextField();
        private JTextField occupancyField = new JTextField();
        private Insets insets = new Insets(5,20,0,20);
        
        MetricsPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {
            throughputField.setColumns(10);
            capacityField.setColumns(10);
            occupancyField.setColumns(10);
            
            throughputField.setEditable(false);
            capacityField.setEditable(false);
            occupancyField.setEditable(false);
            
            throughputField.setText("" + model.getThroughput());
            capacityField.setText("" + model.getCapacity());
            occupancyField.setText("" + model.getOccupancy());
            
            addComponent(this, throughputLabel, 0, 0, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, throughputField, 1, 0, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, capacityLabel, 0, 1, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, capacityField, 1, 1, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, occupancyLabel, 0, 2, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, occupancyField, 1, 2, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
        }
        
        public void paintComponent(Graphics g)
        {
            int width = getWidth();
            int height = getHeight();
            g.setColor(Color.WHITE);
            g.fillRect(0,0, width, height);
        }
    }
    
    private class SplashPanel extends JPanel
    {
        
        public void paintComponent(Graphics g)
        {
            int width = getWidth();
            int height = getHeight();
            Font f = new Font(Font.MONOSPACED, Font.BOLD, 30);
            g.setColor(Color.WHITE);
            g.fillRect(0,0, width, height);
            g.setColor(Color.BLACK);
            g.setFont(f);
            g.drawString("Alpha Train Simulator", width/4, height/3);
        }
    }
    
    private void addComponent(Container c, Component comp, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, Insets i, int anchor, int fill)
    {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, i, 0, 0);
        c.add(comp, gbc);
    }
    
    /**
     * 
     * @return
     */
    public int getClockRate()
    {
        return clockRate;
    }
    
    /**
     * 
     * @return
     */
    public boolean getDemo()
    {
        return demoMode;
    }

    /**
     * 
     * @return
     */
    public boolean getIsOpen()
    {
        return isOpen;
    }
    
    /**
     * 
     * @param s
     */
    public void setSimulator(Simulator s)
    {
        sim = s;
    }
    
}
