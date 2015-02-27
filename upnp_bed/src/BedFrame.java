/******************************************************************
*
*	CyberUPnP for Java
*
*	Copyright (C) Satoshi Konno 2002-2003
*
*	File : SampleClock.java
*
******************************************************************/

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.cybergarage.util.*;

import org.cybergarage.upnp.device.*;

public class BedFrame extends JFrame implements WindowListener 
{
	private final static String TITLE = "CyberLink Sample Bed";
	private BedDevice bedDev;
	private BedPane bedPane;
	
	public BedFrame()
	{
		super(TITLE);

		try {
			bedDev = new BedDevice();
		}
		catch (InvalidDescriptionException e) {
			Debug.warning(e);
		}
				
		getContentPane().setLayout(new BorderLayout());

		bedPane = new BedPane(bedDev);
		bedDev.setComponent(bedPane);
		getContentPane().add(bedPane, BorderLayout.CENTER);

		addWindowListener(this);
		
		pack();
		setVisible(true);
		
		bedDev.start();
	}

	public BedPane getClockPanel()
	{
		return bedPane;
	}

	public BedDevice getClockDevice()
	{
		return bedDev;
	}
		
	////////////////////////////////////////////////
	//	main
	////////////////////////////////////////////////
	
	public void windowActivated(WindowEvent e) 
	{
	}
	
	public void windowClosed(WindowEvent e) 
	{
	}
	
	public void windowClosing(WindowEvent e) 
	{
		bedDev.stop();
		System.exit(0);
	}
	
	public void windowDeactivated(WindowEvent e) 
	{
	}
	
	public void windowDeiconified(WindowEvent e) 
	{
	}
	
	public void windowIconified(WindowEvent e) 
	{
	}
	
	public void windowOpened(WindowEvent e)
	{
	}
	

	////////////////////////////////////////////////
	//	main
	////////////////////////////////////////////////

	public static void main(String args[]) 
	{
		//Debug.on();
		BedFrame sampClock = new BedFrame();
	}

}

