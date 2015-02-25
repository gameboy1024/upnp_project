/******************************************************************
 *
 *	CyberUPnP for Java
 *
 *	Copyright (C) Satoshi Konno 2002
 *
 *	File : SampleClockPane.java
 *
 ******************************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChairPane extends JPanel {
	
	private ChairDevice chairDev;
	
	public ChairPane(ChairDevice chairDev){
		this.chairDev = chairDev;
		JButton button = new JButton();
		JTextArea text = new JTextArea();
		text.setText(chairDev.isOn() ? "on": "off");
		this.add(button);
		this.add(text);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chairDev.isOn()){
					chairDev.off();
					text.setText("off");
				}else{
					chairDev.on();
					text.setText("on");
				}
			}
		});
	}

	public ChairDevice getDevice() {
		return chairDev;
	}

	public void setDevice(ChairDevice chairDev) {
		this.chairDev = chairDev;
	}
}
