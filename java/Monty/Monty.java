import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
//import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.text.*;
//import jahuwaldt.plot.EquiPlotWindow;

public class Monty extends JFrame 
   implements ActionListener, MenuListener
{
    static int V1str = 0;
    static int V2str = 0;    
    static int xSep = 0;        
    static int ySep = 0;    
    static int XSpace = xSep;
    static int YSpace = ySep;
    static int V1 = V1str;
    static int V2 = V2str;
    static int XSize = 22 + (XSpace % 2);
    static int XA = (int) ((XSize-XSpace)/2);
    static int XB = XA + XSpace;

    static final int ROWS = 99;
    static final int COLS = 99;
    static int changes = 0;
    static  String displayMsg = "Finite Difference Method";
    static double grid[][] = new double[ROWS][COLS];
    static double centergrid[][] = new double[31][31];

    static int no_lines = 0;

//Initialize the Array
static public void setGrid(int setVal, int rows, int cols) {
 for(int i = 0 ; i < rows; i++ )     {
	 for(int j = 0 ; j < cols; j++ ) 	    {
        	grid[i][j] = setVal;
	    }
    }
}

//Print the Grid
static public String printGrid(int ROWS, int COLS)
{
String output = "Screen Display.\n";   // Accumulate text here (should be StringBuilder).
//... Print array in rectangular form using nested for loops.
	int a = 0, b =0;
        for (int row = 35; row <= 65; row++) {
            for (int col = 35; col <= 65; col++) {
            output += " " + String.format("%02d", (int)Math.round(grid[row][col]));		
	    centergrid[a][b] = grid[row][col];
	    b++;
            }
	    b = 0;
	    a++;
            output += "\n"; // New Line
        }
        output += "\n End of Display";
	return output; 
}

   public Monty() 
   {
	   /** Basic window stuff: name, size	    * a	    */
	  //Util.debugOff();
	  setTitle("Three Card Game Paradox");
	  setSize(400, 500);
	  Window owner = getOwner();
	  Toolkit tk = Toolkit.getDefaultToolkit();
	  Dimension screenSize = tk.getScreenSize();
	  
      setLocation(((screenSize.width - getSize().width)/2),
				  ((screenSize.height - getSize().height)/2));
      /** Need this to close the window       */
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  System.exit(0);
         }
      } );
	  
	  /** Set up Menu 	   */
	  menuBar = new JMenuBar();
	  setJMenuBar(menuBar);

	  helpMenu = new JMenu("Help");
	  helpMenu.addMenuListener(this);
	  
	    
//	  graphGrid = new JMenuItem("Graph Grid");
//	  graphGrid.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
	  aboutItem = new JMenuItem("About");
	  aboutItem.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

	  readmeItem = new JMenuItem("Read Me");
	  readmeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
	  
	  resetItem = new JMenuItem("Reset Game");
	  resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));

	  simItem = new JMenuItem("Simulation");
	  simItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
	  menuBar.add(makeMenu(helpMenu, new Object[]{ aboutItem, readmeItem, resetItem, simItem }, this));

	  /** End menu set-up   */
	  JPanel cardPanel = new JPanel();
	  cardPanel.setLayout(new GridLayout(0, 3));
	  cardPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Pick a Card"));

	  cardA = new JButton("A");
	  cardA.setEnabled(true);
	  cardA.addActionListener(this);
	  cardPanel.add(cardA);
	  
	  cardB = new JButton("B");
	  cardB.setEnabled(true);
	  cardB.addActionListener(this);
	  cardPanel.add(cardB);
	  
	  cardC = new JButton("C");
	  cardC.setEnabled(true);
	  cardC.addActionListener(this);
	  cardPanel.add(cardC);
	 
	  JLabel cardSpace = new JLabel(" "); 
	  cardPanel.add(cardSpace);

	  JPanel choicePanel = new JPanel();
	  choicePanel.setLayout(new GridLayout(1, 2));
	  choicePanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Stick or Twist"));
	  Stick = new JButton("Stick");
	  Stick.setEnabled(true);
	  Stick.addActionListener(this);
	  choicePanel.add(Stick);
	  

	  Twist = new JButton("Twist");
	  Twist.setEnabled(true);
	  Twist.addActionListener(this);
	  choicePanel.add(Twist);
 

	  resultsTextArea = new JTextArea();
	  JScrollPane resultsPanel = new JScrollPane(resultsTextArea);
	  /* send req panel*/

//	 JPanel graphPanel = new JPanel();
//	  graphPanel.setBorder(BorderFactory.createTitledBorder(											BorderFactory.createEtchedBorder(),
//	  		"Graph Panel"));
//      graphPanel.setLayout(new GridLayout(2, 2));
	  ButtonGroup directionGroup = new ButtonGroup();


//	  linesLabel = new JLabel();
//	  linesLabel.setText("Lines to Plot (11):");
//	  graphPanel.add(linesLabel);	  
	  
//	  linesField = new JTextField();
//	  linesField.setText("11");
//	  graphPanel.add(linesField);
	 
	  
//	  graphBtn = new JButton("Plot Graph");
//	  graphBtn.addActionListener(this);
//	  graphBtn.setEnabled(false);
//	  graphPanel.add(graphBtn);

      
          Container contentPane = getContentPane();
	  contentPane.add(resultsPanel, "Center");
	  contentPane.add(cardPanel, "North");
	  contentPane.add(choicePanel, "South");

   }

   public JRadioButton addRadioButton(JPanel p, ButtonGroup g, String name, boolean selected)
   {  JRadioButton button          = new JRadioButton(name, selected);
      button.addActionListener(this);
      g.add(button);
      p.add(button);
      return button;
   }

   public JCheckBox addCheckBox(JPanel p, String name)
   {  JCheckBox checkBox = new JCheckBox(name);
      //checkBox.addActionListener(this);
      p.add(checkBox);
      return checkBox;
   }
   
   public void actionPerformed(ActionEvent evt)
   {  

  Object source = evt.getSource();
  if (source == initBtn)
  {  displayLine("Intializing..\n Set Grid\n");	   	   
	   displayLine("Intializing..\n Set Boundaries\n");	   	   
	   displayMessage("initialize ");
   	  runSimBtn.setEnabled(true);
	  
  }
  else if (source == simItem)
	  {

           System.out.println("Run Sim...Starting.../n");

           displayMessage("Run Simulation item");
	   autoRun(10);

	  }
  else if (source == runSimBtn)
	  {

           System.out.println("Run Sim...Starting.../n");

           displayMessage("Run Simulation ");

	   autoRun(10);

	  }

	  else if(source == aboutItem)
	  {
		  new AboutMonty(this).show();
	  }
	else if(source == readmeItem)
	  {

		  new ReadMe(this).show();
	  }
 	 else  if (source == graphBtn) 
	{
			 no_lines = Integer.parseInt(linesField.getText());	   

	 if (no_lines < 0 )
	   {
	   	   displayMessage("No of lines too low using 11.");
		   no_lines = 11;
	   }	   

//        jahuwaldt.plot.EquiPlotWindow.main(centergrid, no_lines);

	}
	
         else if (source == resetItem)
	{
		displayMessage("Reset Game");}
      repaint();
   }

   public static void main(String[] args)
   {  Frame f = new Monty();
      f.show();  
   }
   
   
   private void displayMessage(String message)
   {
	  
	  Time time = new Time(System.currentTimeMillis());
	  resultsTextArea.setFont(boldFont);
	  resultsTextArea.append(time.toLocaleString() + ":\r\n\t");
	  resultsTextArea.append(message + "\n");
   }

      private void displayLine(String message)
	   {
	  
	  resultsTextArea.setFont(boldFont);
	  resultsTextArea.append(message + "\n");
	   }


      private void log(String message)
	   {
	 displayLine(message); 
	   }


  public void autoRun(int runNo)
    {
 
  char[] cards = new char[]{'a','b','c'}; // array of 3 characters (cards)

    String result = "Who Knows";
    int stickwins = 0; //counters of wins and losses
    int sticklost = 0;
    int switchwins = 0;
    int switchlost = 0;
    int percent = 0;
 
    log("Running " + runNo + " times:");
    //note a single Random object is reused here
    //it maybe more random to use a different randomiser for choice and winning card.
    Random randomGenerator = new Random();
    for (int idx = 1; idx <= runNo; ++idx){
      int prize = randomGenerator.nextInt(3);
      int choice = randomGenerator.nextInt(3);
      int onewrong = 0;
      while (onewrong == choice || onewrong == prize) { 
      onewrong = randomGenerator.nextInt(3);}
      if (prize == choice) {
         stickwins++;
         result = "win";}
      else{
        sticklost++;
	result = "lose";}

      log("Generated : Winning Card - " + cards[prize] + " Card Chosen - " + cards[choice] + " Removed Card - " + cards[onewrong] + " Result " +result);
    }
    //Results 
    log("\nResults:\nSticking wins you " + stickwins + " times." );
    log("Changing wins you " + sticklost +" times." );
    if (stickwins == sticklost){
	// have to try 2 run to get this condition
        log("Changing or Switching the same." );
	}
    else if (stickwins < sticklost){
	//percent = (stickwins * 100)/sticklost;
	percent = (sticklost * 100/runNo);
        log("Changing wins more times." + percent + "%");
	}
    else {
	// have to try 1 run to get this condition
        log("Sticking wins more times." );
	}
  } // end of simulated multiple runs



   public void menuSelected(MenuEvent evt)
   {
	   
   }
   public void menuDeselected(MenuEvent evt)
   {
   }
   public void menuCanceled(MenuEvent evt)
   {
   }
   
   public static JMenu makeMenu(Object parent,
								Object[] items,
								Object target)
   {
	   JMenu m = null;
	   if (parent instanceof JMenu)
		   m = (JMenu)parent;
	   else if (parent instanceof String)
		   m = new JMenu((String) parent);
	   else
		   return null;
	   
	   for (int i= 0; i < items.length; i++)
	   {
		   if (items[i] == null)
			   m.addSeparator();
		   else
			   m.add(makeMenuItem(items[i], target));
	   }
	   
	   return m;
   }
   
   public static JMenuItem makeMenuItem(Object item,
										Object target)
   {
	   JMenuItem r = null;
	   if (item instanceof String)
		   r = new JMenuItem((String)item);
	   else if (item instanceof JMenuItem)
		   r = (JMenuItem)item;	   
	   else return null;
	   if (target instanceof ActionListener)
		   r.addActionListener((ActionListener) target);
	   return r;
	   
   }
   private JLabel portLabel;
   private JTextField portField;
   private JLabel leftLabel;
   private JTextField leftField;
   private JLabel rightLabel;
   private JTextField rightField;
   private JLabel YsepLabel;
   private JTextField YsepField;
   private JLabel XsepLabel;
   private JTextField XsepField;

   private JLabel linesLabel;
   private JTextField linesField;   
   
   private JLabel deltaLabel;
   private JTextField deltaField;   
   
   private JLabel addressLabel;
   private JTextField addressField;
   private JButton connectBtn;

   

   private JButton graphBtn;
   private JButton initBtn;
   private JButton runSimBtn;
  
   private JMenuBar menuBar;
   private JMenuItem aboutItem;
   private JMenuItem readmeItem;   
   private JMenuItem resetItem;   
   private JMenuItem simItem;   
//   private JMenuItem graphGrid;   
   private JMenu helpMenu;
   
   private JTextArea resultsTextArea;


   private JButton cardA;
   private JButton cardB;
   private JButton cardC;
   private JButton Twist;
   private JButton Stick;
   private JButton Reset;
   
   
   /** my fonts
    */
   Font boldFont = new Font("SansSerif", Font.BOLD + Font.ITALIC, 14);
   
   
   
}


