import java.util.Random;

/** The Three Card Game Show Paradox.  */
public final class RandomInteger {
  
  public static final void main(String... aArgs){
    log("The Three Card Game Show Paradox.");
    String result = "Who Knows";
    int stickwins = 0;
    int sticklost = 0;
    int switchwins = 0;
    int switchlost = 0;


    //note a single Random object is reused here
    Random randomGenerator = new Random();
    for (int idx = 1; idx <= 300; ++idx){
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

      log("Generated : " + prize + " Choice " + choice + " Remove " + onewrong + " Result " +result);
    }
    
    log("Done with Sticking wins you " + stickwins + " times. and loses " + sticklost +" times." );
  }
  
  private static void log(String aMessage){
    System.out.println(aMessage);
  }
}

