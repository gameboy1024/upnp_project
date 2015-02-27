/******************************************************************
 *
 *	CyberUPnP for Java
 *
 *	Copyright (C) Satoshi Konno 2002
 *
 *	File : SampleClockPane.java
 *
 ******************************************************************/


import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class BedPane extends JPanel {
	
	private BedDevice bedDev;
	private ImageIcon image=new ImageIcon("images/Bed.jpg");
	private ImageIcon image1=new ImageIcon("images/people_bed.jpg");
	public BedPane(final BedDevice bedDev){
		this.bedDev = bedDev;
		
		final JLabel label =new JLabel("", image,JLabel.CENTER);
		this.add(label);
		final JTextArea text = new JTextArea();
		text.setText(bedDev.isOn() ? "present": "not present");
		this.add(text);
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher(new KeyEventDispatcher() {
		    
			@Override
			public boolean dispatchKeyEvent(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				if(bedDev.isOn()){
					bedDev.off();
					label.setIcon(image);
					text.setText("get up");
				}else{
					bedDev.on();
					label.setIcon(image1);
					text.setText("sleep");
				}
				return false;
			}
		});
		
		
			
	}

	public BedDevice getDevice() {
		return bedDev;
	}

	public void setDevice(BedDevice bedDev) {
		this.bedDev = bedDev;
	}
}
