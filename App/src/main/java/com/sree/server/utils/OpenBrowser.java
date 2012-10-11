package com.sree.server.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenBrowser {

	public static void launchBrowser(){
		String fileToRead = SystemProperties.getCurrentPath()+File.separator+"conf"+File.separator+"conf.ini";
		JumpStartPropertyFile file = new JumpStartPropertyFile(fileToRead);
		String launchUrl = file.getProperty("launch.url").replace("<TOMCAT_PORT>", file.getProperty("TOMCAT_PORT")).replace("<Application_Context>", file.getProperty("Application.Context"));
		if (Desktop.isDesktopSupported()){
			try {
				Desktop.getDesktop().browse(new URI(launchUrl));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

}
