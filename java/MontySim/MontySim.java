import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
import java.net.UnknownHostException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.text.*;

public class MontySim extends JFrame 
   implements ActionListener, MenuListener
{
    static  String displayMsg = "Finite Difference Method";
    public int runsno =0;

   public MontySim() 
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
	  
	  aboutItem = new JMenuItem("About");
	  aboutItem.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

	  readmeItem = new JMenuItem("Read Me");
	  readmeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
	  
	  resetItem = new JMenuItem("Reset Game");
	  resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
          resetItem.setEnabled(false);

	  simItem = new JMenuItem("Simulation");
	  simItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
	  menuBar.add(makeMenu(helpMenu, new Object[]{ aboutItem, readmeItem, resetItem, simItem }, this));

	  /** End menu set-up   */
	  JPanel cardPanel = new JPanel();
	  cardPanel.setLayout(new GridLayout(1, 2));
	  cardPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Enter the Number of Times to Run"));

	  cardA = new JButton("A");
	  cardA.setEnabled(true);
	  cardA.addActionListener(this);
//	  cardPanel.add(cardA);
	  
	  cardB = new JButton("B");
	  cardB.setEnabled(true);
	  cardB.addActionListener(this);
//	  cardPanel.add(cardB);
	  
	  cardC = new JButton("C");
	  cardC.setEnabled(true);
	  cardC.addActionListener(this);
//	  cardPanel.add(cardC);
	 
	  JLabel cardSpace = new JLabel(" "); 
	  cardPanel.add(cardSpace);

	  JPanel choicePanel = new JPanel();
	  choicePanel.setLayout(new GridLayout(1, 2));
	  choicePanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Run or Reset"));
	  Run = new JButton("Run");
	  Run.setEnabled(true);
	  Run.addActionListener(this);
	  choicePanel.add(Run);
	  
	  Reset = new JButton("Reset");
	  Reset.setEnabled(false);
	  Reset.addActionListener(this);
	  choicePanel.add(Reset);
 
	  resultsTextArea = new JTextArea();
	  JScrollPane resultsPanel = new JScrollPane(resultsTextArea);
	  resultsPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Display Results"));
	  ButtonGroup directionGroup = new ButtonGroup();

	  runsLabel = new JLabel();
	  runsLabel.setText("Enter the Number of Runs:");
	  cardPanel.add(runsLabel);	  
	  
	  runsField = new JTextField();
	  runsField.setText( Integer.toString(runsno));
	  cardPanel.add(runsField);
	 
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
	  
  }
  else if (source == simItem)
	  {
           System.out.println("Run Sim...Starting.../n");
           runsno = Integer.parseInt(runsField.getText());	
           displayMessage("Run Simulation item");
           Reset.setEnabled(true);
           resetItem.setEnabled(true);
	   autoRun(runsno);
	  }
  else if (source == Run)
	  {
           System.out.println("Run Sim...Starting.../n");
           runsno = Integer.parseInt(runsField.getText());	
           displayMessage("Run Simulation ");
           Reset.setEnabled(true);
           resetItem.setEnabled(true);
	   autoRun(runsno);
	  }

	  else if(source == aboutItem)
	  {
		  new AboutMontySim(this).show();
	  }
	else if(source == readmeItem)
	  {

		  new ReadMe(this).show();
	  }
         else if (source == Reset)
	{	runsno=0;
	  runsField.setText( Integer.toString(runsno));
                Reset.setEnabled(false);
                resetItem.setEnabled(false);
		displayMessage("Reset Game");}
         else if (source == resetItem)
	{	runsno=0;
	  runsField.setText( Integer.toString(runsno));
                Reset.setEnabled(false);
                resetItem.setEnabled(false);
		displayMessage("Reset Game");}
      repaint();
   }

   public static void main(String[] args)
   {  Frame f = new MontySim();
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

   private JLabel runsLabel;
   private JTextField runsField;   
   
   private JButton initBtn;
  
   private JMenuBar menuBar;
   private JMenuItem aboutItem;
   private JMenuItem readmeItem;   
   private JMenuItem resetItem;   
   private JMenuItem simItem;   
   private JMenu helpMenu;
   
   private JTextArea resultsTextArea;


   private JButton cardA;
   private JButton cardB;
   private JButton cardC;
   private JButton Reset;
   private JButton Run;
   
   
   /** my fonts
    */
   Font boldFont = new Font("SansSerif", Font.BOLD + Font.ITALIC, 14);
   
   
   
}


