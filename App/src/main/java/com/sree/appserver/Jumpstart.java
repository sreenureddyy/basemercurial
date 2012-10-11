/**
 * 
 */
package com.sree.appserver;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sree.server.utils.OpenBrowser;
import com.sree.server.utils.PrintLogging;
import com.sree.server.utils.StringOperations;

/**
 * @author Sree
 * 
 */
@SuppressWarnings("serial")
public class Jumpstart extends JFrame implements ActionListener {

	JMenu objJMenuFile;
	JMenu objJMenuEdit;
	JMenu objJMenuLaunch;
	JMenu objJMenuHelp;

	JMenuItem objStartJMenuItem;
	JMenuItem objStopJMenuItem;
	JMenuItem objRestartJMenuItem;

	String applicationurl = "http://localhost:8080";

	public Jumpstart() {
		super();
		buildJMenuFile();
		buildJMenuEdit();
		buildJMenuLaunch();
		buildJMenuHelp();
		// buildJMenuAction();

		JMenuBar objJMenuBar = new JMenuBar();

		super.setJMenuBar(objJMenuBar);
		objJMenuBar.add(objJMenuFile);
		objJMenuBar.add(objJMenuEdit);
		objJMenuBar.add(objJMenuLaunch);
		objJMenuBar.add(objJMenuHelp);

		Dimension dim = getToolkit().getScreenSize();
		setLocation((dim.width - 442) / 2, (dim.height - 480) / 2);
		requestFocus();

		setIconImage(new ImageIcon("images/icon.png").getImage());

		setMinimumSize(new Dimension(450, 480));
		setMaximumSize(new Dimension(450, 480));
		setSize(450, 480);

		ImagePanel panel = new ImagePanel(
				new ImageIcon("images/jsksplash.png").getImage());
		getContentPane().add(panel);

		super.addWindowListener( // Register an anonymous class as a listener.
		new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
				UnloadWindow();
			}
		});

		setResizable(false);
		setVisible(true);
	}

	private void buildJMenuFile() {
		JMenuItem objJMenuItem;

		objJMenuFile = new JMenu("File");

		objJMenuItem = new JMenuItem("Exit"); // Quit
		objJMenuItem.addActionListener(this);
		objJMenuFile.add(objJMenuItem);
	}

	private void buildJMenuEdit() {
		objJMenuEdit = new JMenu("Server");

		objStartJMenuItem = new JMenuItem("Start");
		objStartJMenuItem.addActionListener(this);
		objJMenuEdit.add(objStartJMenuItem);

		objStopJMenuItem = new JMenuItem("Stop");
		objStopJMenuItem.addActionListener(this);
		objJMenuEdit.add(objStopJMenuItem);

		objRestartJMenuItem = new JMenuItem("Restart");
		objRestartJMenuItem.addActionListener(this);
		objJMenuEdit.add(objRestartJMenuItem);
	}

	public void buildJMenuLaunch() {
		JMenuItem objJMenuItem;
		objJMenuLaunch = new JMenu("Launch");
		objJMenuItem = new JMenuItem("Launch Browser");
		objJMenuItem.addActionListener(this);
		objJMenuLaunch.add(objJMenuItem);
	}

	public void buildJMenuHelp() {
		JMenuItem objJMenuItem;
		objJMenuHelp = new JMenu("Help");
		objJMenuItem = new JMenuItem("About");
		objJMenuItem.addActionListener(this);
		objJMenuHelp.add(objJMenuItem);
	}

	protected void UnloadWindow() {
		String[] arrayOfString = { "Quit", "Cancel" };
		int i = JOptionPane.showOptionDialog(null,
				"Are you sure you want to quit?", "", -1, 1, null,
				arrayOfString, arrayOfString[0]);
		if (i == 0) {
			PrintLogging
					.print(1,
							"\n-------------------------------\n Thanks for using Reddies \n");
			ShutDownTomcat command = new ShutDownTomcat();
			Thread thread = new Thread(command);
			thread.start();
			System.exit(0);
		} else {
			PrintLogging.print(1, "Cancelled Exit");
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String strMenuName = event.getActionCommand();
		if (StringOperations.goodString(strMenuName)) {
			if (strMenuName.equals("Exit")) {
				UnloadWindow();
			} else if (strMenuName.equals("Start")) {
				objStartJMenuItem.setEnabled(false);
				objStopJMenuItem.setEnabled(true);
				objRestartJMenuItem.setEnabled(true);

				StartTomcat command = new StartTomcat();
				Thread thread = new Thread(command);
				thread.start();

				thread = new Thread(new StartDbServer());
				thread.start();

			} else if (strMenuName.equals("Stop")) {
				objStartJMenuItem.setEnabled(true);
				objStopJMenuItem.setEnabled(false);
				objRestartJMenuItem.setEnabled(false);

				ShutDownTomcat command = new ShutDownTomcat();
				Thread thread = new Thread(command);
				thread.start();

				thread = new Thread(new StopDbServer());
				thread.start();

			} else if (strMenuName.equals("Restart")) {
				objStartJMenuItem.setEnabled(false);
				objStopJMenuItem.setEnabled(true);
				objRestartJMenuItem.setEnabled(true);

				ShutDownTomcat stopcommand = new ShutDownTomcat();
				new Thread(stopcommand).start();
				StartTomcat startcommand = new StartTomcat();
				new Thread(startcommand).start();
			} else if (strMenuName.equals("Launch Browser")) {
				OpenBrowser.launchBrowser();
			} else if (strMenuName.equals("About")) {
				new AboutCompany();
			}
		}
	}
}

@SuppressWarnings("serial")
class ImagePanel extends JPanel {

	private Image img;

	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
