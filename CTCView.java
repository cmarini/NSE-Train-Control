/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CTC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.*;
import java.text.*;
import Simulator.Simulator;

public class CTCView extends JFrame
{
    private CTCModel model = new CTCModel();
    private CTCControl control = new CTCControl(model);
    private Simulator sim;
    private boolean isOpen = true;
    private int clockRate = 60;
    private boolean demoMode = false;
    private MainPanel mainPanel = new MainPanel();
    private DispatcherPanel dispatcherPanel = new DispatcherPanel();
    private OperatorPanel operatorPanel = new OperatorPanel();
    private TrainPanel trainPanel = new TrainPanel();
    private TrackPanel trackPanel = new TrackPanel();
    private MetricsPanel metricsPanel = new MetricsPanel();
    private SplashPanel splashPanel = new SplashPanel();
    private String dispatcherID;
    private int blockSelectedIndex;
    private int trainSelectedIndex;
    
    public CTCView()//SimulatorPrototype3 s)
    {
        //simulator = s;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        intialize();
        setLayout(new BorderLayout());
        this.add(splashPanel);
        this.setSize(800,600);
        this.setVisible(true);
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
        setJMenuBar(menuBar);
        
        fileClose.addActionListener(new MenuActionClose());
        viewMain.addActionListener(new MenuOpenScreen(mainPanel));
        viewDispatcher.addActionListener(new MenuDialogLogin(this));
        viewOperator.addActionListener(new MenuOpenScreen(operatorPanel));
        viewTrain.addActionListener(new MenuOpenScreen(trainPanel));
        viewTrack.addActionListener(new MenuOpenScreen(trackPanel));
        viewMetrics.addActionListener(new MenuOpenScreen(metricsPanel));
        runDemo.addActionListener(new MenuActionRunDemo());
    }
    
    private class MenuActionRunDemo implements ActionListener
    {
        private MenuActionRunDemo() {}
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            demoMode = true;
        }
    }
    
    private class MenuActionClose implements ActionListener 
    {
        private MenuActionClose() {}
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
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
            LogInDialog login = new LogInDialog(frame);
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
            ((MainPanel)panel).initialize();
        }
        else
        {
            if(panel instanceof DispatcherPanel)
            {
                ((DispatcherPanel)panel).initialize();
            }
            else
            {
                if(panel instanceof OperatorPanel)
                {
                    ((OperatorPanel)panel).initialize();
                }  
                else
                {
                    if(panel instanceof TrainPanel)
                    {
                        ((TrainPanel)panel).initialize();
                    }
                    else
                    {
                        if(panel instanceof TrackPanel)
                        {
                            ((TrackPanel)panel).initialize();
                        } 
                        else
                        {
                            if(panel instanceof MetricsPanel)
                            {
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
    
    private class MainPanel extends JPanel
    {
        private MapPanel map = new MapPanel(model);
        private JLabel clockLabel = new JLabel("Clock Rate (1 hr = )");
        private final String clockRates [] = {"15 minutes", "20 minutes", "30 minutes", "40 minutes", "45 minutes", "60 minutes", "90 minutes", "120 minutes"};
        private JComboBox clockCombo= new JComboBox(clockRates);
        private Insets insets = new Insets(0,10,0,0);
        private Insets insets2 = new Insets(0,0,0,0);

        MainPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {
            addComponent(this, map, 0, 0, 2, 1, 1.0, 1.0, insets2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            addComponent(this, clockLabel, 0, 1, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, clockCombo, 1, 1, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            clockCombo.addActionListener(clockComboListener);
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
                        clockRate = 15;
                        break;
                    case 1:
                        clockRate = 20;
                        break;
                    case 2:
                        clockRate = 30;
                        break;
                    case 3:
                        clockRate = 40;
                        break;
                    case 4:
                        clockRate = 45;
                        break;
                    case 5:
                        clockRate = 60;
                        break;
                    case 6:
                        clockRate = 90;
                        break;
                    default:
                        clockRate = 120;       
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
    
    private class DispatcherPanel extends JPanel
    {
        private MapPanel map = new MapPanel(model);
        private JLabel dispatcherIDLabel;
        private JLabel trainID = new JLabel("Train ID");
        private JComboBox trains = new JComboBox();
        private JLabel trackID = new JLabel("Train ID");
        private JComboBox track = new JComboBox();
        private JLabel setpointLabel = new JLabel("Setpoint");
        private JTextField setpoint = new JTextField();
        private JLabel authorityLabel = new JLabel("Authority");
        private JTextField authority = new JTextField();
        private JButton sendSetpoint;
        private JButton sendAuthority;
        private Insets insets = new Insets(0,0,0,0);
        private Insets insets2 = new Insets(0,0,0,10);
        private MaskFormatter format;

        DispatcherPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {            
            sendSetpoint = new JButton("Send Setpoint"); 
            sendAuthority = new JButton("Send Authority"); 
            
            dispatcherIDLabel = new JLabel(dispatcherID);
            setpoint.setColumns(10);
            authority.setColumns(10);
            sendSetpoint.addActionListener(buttonClick);
            sendAuthority.addActionListener(buttonClick);
            addComponent(this, map, 0, 0, 6, 1, 1.0, 1.0, insets, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            addComponent(this, dispatcherIDLabel, 0, 1, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trainID, 0, 2, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trains, 1, 2, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, setpointLabel, 0, 3, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, setpoint, 1, 3, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);      
            addComponent(this, sendSetpoint, 2, 3, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, trackID, 3, 2, 1, 1, 0, 0, insets2, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, track, 4, 2, 1, 1, 0, 0, insets2, GridBagConstraints.WEST, GridBagConstraints.BOTH);
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
                        if(set < 0 || set > 70)
                        {
                            JOptionPane.showMessageDialog(DispatcherPanel.this, "Invalid Setpoint value, please enter a number between 0 and 70", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            control.setDispatcherSpeed(set);
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
                        if(auth < 0 || auth > 70)
                        {
                            JOptionPane.showMessageDialog(DispatcherPanel.this, "Invalid Authority value, please enter a number between 0 and 70", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            control.setDispatcherAuthority(auth);
                        }
                    }
                    catch(NumberFormatException p)
                    {
                        JOptionPane.showMessageDialog(DispatcherPanel.this, "Invalid Authority value, please enter a number between 0 and 70", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
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
    
    private class OperatorPanel extends JPanel
    {
        private MapPanel map = new MapPanel(model);
        private JLabel trainID = new JLabel("Train ID");
        private JComboBox trains = new JComboBox();
        private JLabel speedLabel = new JLabel("Speed");
        private JTextField operatorSpeed = new JTextField();
        private JLabel brakeLabel = new JLabel ("Brake");
        private JCheckBox brake = new JCheckBox("");
        private JButton send;
        private Insets insets = new Insets(0,0,0,0);
        private MaskFormatter format;

        OperatorPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {
            send = new JButton("Send");
            operatorSpeed.setColumns(10);
            send.addActionListener(buttonClick);
            
            addComponent(this, map, 0, 0, 3, 1, 1.0, 1.0, insets, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
            addComponent(this, trainID, 0, 1, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trains, 1, 1, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, speedLabel, 0, 2, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, operatorSpeed, 1, 2, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, brakeLabel, 0, 3, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, brake, 1, 3, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, send, 2, 3, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
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

        public void sendOperatorCommands()
        {
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
        private JCheckBox brakeFailCheck = new JCheckBox();
        private JCheckBox engineFailCheck = new JCheckBox();
        private JCheckBox signalPickupFailCheck = new JCheckBox();
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
        
        TrainPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {
            //String trainIDs [] = sim.getTrainIDs();
            String trainIDs [] = new String [0];
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
        
        ActionListener trainComboListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                trainSelectedIndex = (int)cb.getSelectedIndex();
                //TrainController t = sim.getTrainController(ID);
                //get all train properties
                //initialize();
            }
        };
        
        ActionListener emergencyBrakeButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                //TrainController t = sim.getTrainController(ID);
                //t.emergencyBrake();
                //initialize();
            }
        };
                
        ActionListener addTrainButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                //sim.createTrain(line, crewCount, trainID);
                //initialize();
            }
        };
                        
        ActionListener removeTrainButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                //TrainController t = getTrainController(ID);
                //t.remove();
                //initialize();
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
    
    private class TrackPanel extends JPanel
    {
        private String [] failureTypes = {"Broken Rail", "Power Failure", "Circuit Failure"};
        private JLabel trackLabel = new JLabel("Select Track");
        private JLabel speedLimitLabel = new JLabel("Speed Limit");
        private JLabel elevationLabel = new JLabel("Elevation");
        private JLabel gradeLabel = new JLabel("Grade");
        private JLabel blockSizeLabel = new JLabel("Block Size");
        private JLabel trackTypeLabel = new JLabel("Track Type");
        private JLabel passengersBoardingLabel = new JLabel("Passengers Boarding");
        private JLabel passengersDisembarkingLabel = new JLabel("Passengers Disembarking");
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
                
        TrackPanel()
        {
            this.setLayout(new GridBagLayout());
            initialize();
        }

        private void initialize()
        {
            String trackIDs [] = model.getTrackIDs();
            
            track = new JComboBox();
            for(int i = 0; i < trackIDs.length; i++)
            {
                track.addItem(trackIDs[i]);
            }
            track.setSelectedIndex(blockSelectedIndex);
            
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
            
            speedLimitField.setText(""+speedLimit);
            elevationField.setText(""+elevation);
            gradeField.setText(""+grade);
            blockSizeField.setText(""+blockSize);
            trackTypeField.setText(""+trackType);
            passengersBoardingField.setText(""+passengersBoarding);
            passengersDisembarkingField.setText(""+passengersDisembarking);
            
            
            addComponent(this, trackLabel, 0, 0, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, track, 1, 0, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, speedLimitLabel, 0, 1, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, speedLimitField, 1, 1, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, elevationLabel, 0, 2, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, elevationField, 1, 2, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, gradeLabel, 0, 3, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, gradeField, 1, 3, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, blockSizeLabel, 0, 4, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, blockSizeField, 1, 4, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, trackTypeLabel, 0, 5, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, trackTypeField, 1, 5, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, passengersBoardingLabel, 0, 6, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, passengersBoardingField, 1, 6, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, passengersDisembarkingLabel, 0, 7, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, passengersDisembarkingField, 1, 7, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.NONE);
            addComponent(this, closeButton, 0, 8, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, openButton, 0, 9, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, breakButton, 0, 10, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
            addComponent(this, failType, 1, 10, 1, 1, 0, 0, insets, GridBagConstraints.WEST, GridBagConstraints.BOTH);
            addComponent(this, fixButton, 0, 11, 1, 1, 0, 0, insets, GridBagConstraints.EAST, GridBagConstraints.NONE);
        }
        
        ActionListener trackComboListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                blockSelectedIndex = (int)cb.getSelectedIndex();
                //Track t = model.getTrack(ID);
                //get all track properties
                //initialize();
            }
        };
        
        ActionListener closeButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                //Track t = model.getTrack();
                //track.close();
                //initialize();
            }
        };
        
        ActionListener openButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                //Track t = model.getTrack();
                //t.open();
                //initialize();
            }
        };
        
        ActionListener breakButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                String type = (String)failType.getSelectedItem();
                //Track t = model.getTrack();
                //t.fail(type);
                //initialize();
            }
        };
        
        ActionListener fixButtonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JComboBox cb = (JComboBox)event.getSource();
                String ID = (String)cb.getSelectedItem();
                //Track t = model.getTrack();
                //t.fix();
                //initialize();
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
    
    public int getClockRate()
    {
        return clockRate;
    }
    
    public boolean getDemo()
    {
        return demoMode;
    }

    public boolean getIsOpen()
    {
        return isOpen;
    }
    
    public void setSimulator(Simulator s)
    {
        sim = s;
    }
    
}