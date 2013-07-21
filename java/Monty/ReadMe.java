import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ReadMe extends JDialog
{  
	
	public static String applicationVersion = "v1.0";
	public ReadMe(JFrame parent)
   {  super(parent, "Read me 3 Card Game", true);         

      Box b = Box.createVerticalBox();
      b.add(Box.createGlue());
      b.add(new JLabel("Three Card Game Show paradox  ."));
	  
	  b.add(new JLabel("Author: Conor Gilmer (Webwayz Ltd.)"));
	  b.add(new JLabel("Version Info: " + applicationVersion));

      b.add(Box.createGlue());
      getContentPane().add(b, "Center");
                
      JPanel p2 = new JPanel();
      JButton ok = new JButton("Ok");
      p2.add(ok);
      getContentPane().add(p2, "South");

      ok.addActionListener(new ActionListener() 
         {  public void actionPerformed(ActionEvent evt) 
            { setVisible(false); } 
         } );

	  
      setSize(300, 150);
	  
	  	// try to put dialog in nice position
		Point parentPos = parent.getLocation();
		Dimension parentSize = parent.getSize();
		setLocation(parentPos.x + ((parentSize.width - getSize().width)/2),
					parentPos.y + ((parentSize.height - getSize().height)/2));
	
   }
}

