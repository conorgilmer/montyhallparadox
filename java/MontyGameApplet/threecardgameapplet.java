/* 
   Three card game applet

 * The Three Card Game Paradox.  
 * the player has three cards to chose from, with one having a prize
 * after the player choses a card, if one card which is inccorect is removed
 * and you are given the option to stay with your card or select the other 
 * remaining card it is better to change you have more chances of winning 
 * yet your gut thinks its a 1 in 2 chances of winning 
 * when changing is really 2 out of 3 chances of winning and 
 * staying is 1 out of 3 chances of winning
  
*/

//applet imports
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

//simulation imports
import java.util.Random;

public class threecardgameapplet extends Applet implements ActionListener
{
	// Gui widgets
	TextField noofrunsText;

	Button cardA;
	Button cardB;
	Button cardC;
	Button Stick;
	Button Twist;
	Button goButton;
	Button infoButton;
	TextArea outText;
	Checkbox toScreen;
	boolean echoruns = true;
	
	// program variables
	float noofruns;
        String result = "Who Knows";
	String verdict ="Will see";
        int stickwins = 0; //counters of wins and losses
        int sticklost = 0;
        int percent = 0;
	int runNo = 0;
	int choice = 0;
	int prize = 0;
	int othercard = 0;
 
	public static char[] cards = new char[]{'a','b','c'}; // array of 3 characters (cards)


	
	public void init() {
		makeGui();
	} // init
	


	public void writeInfo (String info) {
		outText.appendText(info + "\n");
	}

	public void log (String info) {
		if (echoruns) {
		outText.appendText(info + "\n");
		}
	}
	
	private float readValue(TextField t) {
		String s;
		Float f;
		
		// get string from text field
		s = t.getText();
		
		// convert it to Float object
		f = new Float(s);
		
		// convert it to float value
		return f.floatValue();
		
	} // readValue
	
	private void processInput() { // reads float values from text fields
//		runNo = (int)readValue(noofrunsText); //cast floats to integer number for runs
		//echoruns = toScreen.getState();
	        stickwins = 0; //counters of wins and losses
                sticklost = 0;
                percent = 0;
	} // processInput
	
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == goButton) {
			processInput();
			singleRun(choice);
			repaint();
		}

		else if (e.getSource() == cardA) {
			choice = 0;
			log("Card A Chosen\n");
			singleRun(choice);
		}
		else if (e.getSource() == cardB) {
			choice = 1;
			log("Card B Chosen\n");
			singleRun(choice);
		}
		else if (e.getSource() == cardC) {
			choice = 2;
			log("Card C Chosen\n");
			singleRun(choice);
		}
		else if (e.getSource() == Twist) {
			getResult("t");
		}
		else if (e.getSource() == Stick) {
			getResult("s");
		}
		else if (e.getSource() == infoButton) {
			writeInfo("The Three Card Game Paradox.\nthe player has three cards to chose from, with one having a prize\n after the player choses a card, if one card which is inccorect is removed\n and you are given the option to stay with your card or select the other \n remaining card it is better to change you have more chances of winning \n yet your gut thinks its a 1 in 2 chances of winning \n when changing is really 2 out of 3 chances of winning and \n staying is 1 out of 3 chances of winning\n\n");
			choice = 7;
			prize  = 7;
			cardA.setEnabled(true);
			cardB.setEnabled(true);
			cardC.setEnabled(true);
			Stick.setEnabled(false);
			Twist.setEnabled(false);
		}
	} // actionPerformed
	
	private void makeGui() {
		Panel allPanel;
		
		// applet layout
		setLayout(new BorderLayout());
			
		// panel to hold all input elements
		allPanel = new Panel();
		allPanel.setLayout(new GridLayout(7,1));
		
		// noofruns
//		noofrunsText = new TextField(10);
//		makeWidget("Run # times", noofrunsText, allPanel);
	
	        // display to the output the iterations	
//		toScreen = new Checkbox("Display Runs", null, true);
//		Panel cB = new Panel();
//		cB.add(toScreen);
//		allPanel.add(cB);
//
		Label Intro = new Label("Pick a Card");       // create a Label
		Panel introPanel = new Panel();
		introPanel.add(Intro);
		allPanel.add(introPanel);

		// cards 
		cardA = new Button("A");
		cardB = new Button("B");
		cardC = new Button("C");
		Panel cardP = new Panel();

		cardP.add(cardA);
		cardP.add(cardB);
		cardP.add(cardC);
		allPanel.add(cardP);
		cardA.addActionListener(this);
		cardB.addActionListener(this);
		cardC.addActionListener(this);
		
		//Stick or Twist Panel
		Panel choicePanel = new Panel();
		Stick = new Button("Stick");
		Twist = new Button("Twist");
		choicePanel.add(Stick);
		choicePanel.add(Twist);
		allPanel.add(choicePanel);

		Stick.addActionListener(this);
		Twist.addActionListener(this);
		Stick.setEnabled(false);
		Twist.setEnabled(false);
		

		
		// go button
		goButton = new Button("Run");
		Panel bP = new Panel();
//		bP.add(goButton);
//		allPanel.add(bP);
		goButton.addActionListener(this);

		// info button
		infoButton = new Button("Reset");
		Panel iP = new Panel();
		bP.add(infoButton);
		allPanel.add(bP);
		infoButton.addActionListener(this);
		
		// add all panel to applet
		
		add("West", allPanel);
		
		// Output area with some verbiage about it
		outText = new TextArea("The Three Card Game Paradox.\nthe player has three cards to chose from, with one having a prize\n after the player choses a card, if one card which is inccorect is removed\n and you are given the option to stay with your card or select the other \n remaining card it is better to change you have more chances of winning \n yet your gut thinks its a 1 in 2 chances of winning \n when changing is really 2 out of 3 chances of winning and \n staying is 1 out of 3 chances of winning\n\n", 20, 40, TextArea.SCROLLBARS_BOTH);
		outText.setBackground(new Color(155, 150, 0));
		add("Center", outText);
		
	} // makeGui
	
	private void makeWidget(String lstring, TextField text, Panel all) {
		Label l;
		Panel p = new Panel();
		
		l = new Label(lstring);
		//text = new TextField(10);
		p.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p.add(l);
		p.add(text);
		all.add(p);
	
	} // makeWigdet



//single run
  public void singleRun(int choice)
    {
    String result = "Who Knows";
    int stickwins = 0;
    int sticklost = 0;
    int switchwins = 0;
    int switchlost = 0;
    int percent = 0;

    log("Play Game:");
    //note a single Random object is reused here
    Random randomGenerator = new Random();
      prize = randomGenerator.nextInt(3);
      int onewrong = 0;
      while (onewrong == choice || onewrong == prize) { 
      onewrong = randomGenerator.nextInt(3);}
      switch (onewrong) {
	case 0:
	  cardA.setEnabled(false);
	  break;
	case 1:
	  cardB.setEnabled(false);
	  break;
	case 2:
	  cardC.setEnabled(false);
	  break;
	 
      }
      log("Removing One incorrect card ");
      log("Removed " + cards[onewrong] );
//      int othercard = 0;
      for (int count = 0; count <= 2; count++) {
        if (count != choice && count != onewrong)
	othercard = count;
      }
      
      log("Cards left [" + cards[choice] + "] and " + cards[othercard]);
      log("Do you want to stick(s) or twist(t)");

		Stick.setEnabled(true);
		Twist.setEnabled(true);

	}

public void getResult(String twist){
      //Scanner SorT = new Scanner(System.in);
      //change choice if twist or the other card are typed in else stick with original choice
      if ((twist.equalsIgnoreCase("t")) ||
	 (twist.equalsIgnoreCase(Character.toString(cards[othercard])))) {
         choice = othercard;
	 twist = "twisted choosing "; } 
      else
         twist = "stuck with ";
      if (prize == choice) {
         result = "win";
	}
      else{
	result = "lose";}

      log("Game: Winning Card \"" + cards[prize] + "\" Player " + twist + "["+ cards[choice] + "] - Result " +result + "\n\n\n");
  } // end of single run function


} // threecardapplet

