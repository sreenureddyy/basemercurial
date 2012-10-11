/**
 * 
 */
package com.sree.appserver;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sree.server.utils.RegularExpression;

/**
 * @author Sree
 *
 */
@SuppressWarnings("serial")
public class AboutCompany extends JFrame{

	AboutCompany(){
		Dimension dim = getToolkit().getScreenSize();
	    setLocation((dim.width - 442) / 2, (dim.height - 480) / 2);
	    requestFocus();

	    Point Screen_location = getLocation();
	    int Pos_X = (int)Screen_location.getX();
	    int Pos_Y = (int)Screen_location.getY();
	    
		setLocation(Pos_X + 120, Pos_Y + 160);
	    setSize(195, 190);
	    requestFocus();
	    getContentPane().setLayout(new BoxLayout(getContentPane(), 1));
		setTitle("About Sree JSK");
	    setResizable(false);
	    String JSK_Version = "2.1";
	    String HTMText = "<html><center><b>Sree Server 1.2 JumpStart Kit</b><p></center><center>Copyright 2011<br>Sree Corporation. <br>All rights reserved.</center></html>";
	    HTMText = RegularExpression.RegEx("</html>", "<br><center><font size=-2>JSK Version: " + JSK_Version + "</font></center></html>", HTMText);
	    JPanel q = new JPanel(new GridLayout(0, 1));
	    JLabel j = new JLabel(HTMText, 0);
	    q.add(j);

	    JPanel p = new JPanel();
	    JButton buttonA = new JButton("Cancel");
	    p.add(buttonA);
	    buttonA.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        setVisible(false);
	        dispose();
	      }
	    });
	  
	    getContentPane().add(q);
	    getContentPane().add(p);
	    setVisible(true);
	}

}
