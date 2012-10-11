package com.sree.appserver;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sree.server.utils.PrintLogging;
import com.sree.server.utils.SystemType;

public class Execute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintLogging.setLogLevel("INFO", "UNSET");
		new SystemType();
		try {
			if (SystemType.isWINDOWS()) {
				PrintLogging.print(5, "We are on Windows");
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} else if (SystemType.isMACOSX()) {
				PrintLogging.print(5, "We are on MAC");
				UIManager
						.setLookAndFeel("com.sun.java.swing.mac.MacLookAndFeel");
			} else {
				PrintLogging.print(5, "We are on Unix");
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		new Jumpstart();
		
		//String fileToRead = SystemProperties.getCurrentPath()+File.separator+"conf"+File.separator+"conf.ini";
		//JumpStartPropertyFile file = new JumpStartPropertyFile(fileToRead);
		
	}

}
