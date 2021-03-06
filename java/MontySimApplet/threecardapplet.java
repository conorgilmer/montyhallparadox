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

public class threecardapplet extends Applet implements ActionListener
{
	// Gui widgets
	TextField noofrunsText;
	Button goButton;
	Button infoButton;
	Button graphResultsButton;
	Button graphRandomButton;
	TextArea outText;
	Checkbox toScreen;
	boolean echoruns;
	
	// program variables
	float noofruns;
        String result = "Who Knows";
	String verdict ="Will see";
        int stickwins = 0; //counters of wins and losses
        int sticklost = 0;
        int percent = 0;
	int runNo = 0;
	int numA =0; /* counters to show how random is the rand function */
	int numB =0;
	int numC =0;
	int randCalled =0;
 
	public static char[] cards = new char[]{'a','b','c'}; // array of 3 characters (cards)


	
	public void init() {
		makeGui();
	} // init

/*
 * Generate a random number and increment counters 
 */
	public int genRandum(){
	    	Random randomGenerator = new Random();
		randCalled++;
      		int randum = randomGenerator.nextInt(3);
		if (randum == 1){
			numA++;
		} else if (randum == 2) {
			numB++;
		} else {
			numC++;
		}
		return randum;
	}	
	private void simulate() {
	
        log("Running " + runNo + " times:");
    //note a single Random object is reused here
    //it maybe more random to use a different randomiser for choice and winning card.
    Random randomGenerator = new Random();
    for (int idx = 1; idx <= runNo; ++idx){
      //int prize = randomGenerator.nextInt(3);
      int prize = genRandum();
      //int choice = randomGenerator.nextInt(3);
      int choice = genRandum();
      int onewrong = 0;
      while (onewrong == choice || onewrong == prize) { 
      //onewrong = randomGenerator.nextInt(3);}
      onewrong = genRandum();}
      if (prize == choice) {
         stickwins++;
         result = "win";}
      else{
        sticklost++;
	result = "lose";}

      log("Generated : Winning Card - " + cards[prize] + " Card Chosen - " + cards[choice] + " Removed Card - " + cards[onewrong] + " Result " +result);
    }
    //Results 
//    log("\nResults:\nSticking wins you " + stickwins + " times." );
  //  log("Changing wins you " + sticklost +" times." );
    if (stickwins == sticklost){
	// have to try 2 run to get this condition
        //log("Changing or Switching the same." );
        verdict = "Changing or Switching the same.";
	}
    else if (stickwins < sticklost){
	//percent = (stickwins * 100)/sticklost;
	percent = (sticklost * 100/runNo);
        //log("Changing wins more times." + percent + "%");
        verdict = "Changing wins more times. " + percent + "% of the time";
	}
    else {
	// have to try 1 run to get this condition
        //log("Sticking wins more times." );
        verdict = "Sticking wins more times.";

	}
		
	} // simulate Auto Run
	
	public void paint( Graphics g ) {
		outText.appendText("\nNo of Runs = " + runNo + "\n");
		
		// Output results
		outText.appendText("\nResults:\nSticking wins you " + stickwins + " times.\n");
		outText.appendText("Changing wins you " + sticklost + " times.\n");
		outText.appendText("Verdict: " + verdict + "\n\n\n ");
	} // paint


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
		runNo = (int)readValue(noofrunsText); //cast floats to integer number for runs
		echoruns = toScreen.getState();
	        stickwins = 0; //counters of wins and losses
                sticklost = 0;
                percent = 0;
		numA =0;
		numB =0;
		numC =0;
	} // processInput
	
	public void actionPerformed(ActionEvent e) {
		double[] resultsData =  new double[2];
		String[] resultsName = new String[2];
		Color[]  resultsColors = new Color[2];

	
		if (e.getSource() == goButton) {
			processInput();
			simulate();
			graphResultsButton.setEnabled(true);
			graphRandomButton.setEnabled(true);
			repaint();
		}
		else if (e.getSource() == infoButton) {
			writeInfo("The Three Card Game Paradox.\nthe player has three cards to chose from, with one having a prize\n after the player choses a card, if one card which is inccorect is removed\n and you are given the option to stay with your card or select the other \n remaining card it is better to change you have more chances of winning \n yet your gut thinks its a 1 in 2 chances of winning \n when changing is really 2 out of 3 chances of winning and \n staying is 1 out of 3 chances of winning\n\n");
		}
		else if (e.getSource() == graphResultsButton) {
		resultsData[0] = stickwins;
		resultsName[0] = "Stick Wins";
		resultsColors[0] = Color.green;

		resultsData[1] = sticklost;
		resultsName[1] = "Twisting Wins";
		resultsColors[1] = Color.red;

		SimpleBarChart.main(resultsData, resultsName, resultsColors,
		    "Results Analysis", 500, 400);
		}
		else if (e.getSource() == graphRandomButton) {
		resultsData = new double[]{ numA,numB,numC };
		resultsName = new String[]{"A","B","C"};
		resultsColors = new Color[]{Color.magenta, Color.blue, Color.yellow};
		SimpleBarChart.main(resultsData, resultsName, resultsColors,
			       "How Random", 500, 400);
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
		noofrunsText = new TextField(10);
		makeWidget("Run # times", noofrunsText, allPanel);
	
	        // display to the output the iterations	
		toScreen = new Checkbox("Display Runs", null, true);
		Panel cB = new Panel();
		cB.add(toScreen);
		allPanel.add(cB);
		
		// go button
		goButton = new Button("Run");
		Panel bP = new Panel();
		bP.add(goButton);
		allPanel.add(bP);
		goButton.addActionListener(this);

		// info button
		infoButton = new Button("Info");
		Panel iP = new Panel();
		iP.add(infoButton);
		allPanel.add(iP);
		infoButton.addActionListener(this);
		
		// graph results button
		graphResultsButton = new Button("Graph Results");
		graphResultsButton.setEnabled(false);
		Panel grP = new Panel();
		grP.add(graphResultsButton);
		allPanel.add(grP);
		graphResultsButton.addActionListener(this);
		
		// graph random button
		graphRandomButton = new Button("How Random");
		graphRandomButton.setEnabled(false);
		Panel grdP = new Panel();
		grdP.add(graphRandomButton);
		allPanel.add(grdP);
		graphRandomButton.addActionListener(this);

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
} // threecardapplet

