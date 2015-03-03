package santemetre.device.bracelet;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.imageio.ImageIO;

import org.cybergarage.util.*;
public class BraceletPane extends JPanel {
	private final static String BRACELET_OFF_OFF_IMAGE = "images/off-off.png";
	private final static String BRACELET_ON_OFF_IMAGE = "images/on-off.png";
	private final static String BRACELET_ON_ON_IMAGE = "images/on-on.png";
	private BufferedImage panelmage;
	private BraceletDevice braceletDev = null;
	public BraceletPane()
	{
		loadImage(BRACELET_OFF_OFF_IMAGE);
		initPanel();
	}
	private void loadImage(String finename)
	{
		File f = new File(finename);
		try {
			panelmage = ImageIO.read(f);
		}
		catch (Exception e) {
			Debug.warning(e);
		}
	}
	private void setAlarmJPanelText(String time){
		JLabel label=new JLabel(time);
	    this.add(label); // Add the label to the panel
	}
	private BufferedImage getPaneImage()
	{
		return panelmage;
	}
	private void initPanel()
	{
		BufferedImage panelmage = getPaneImage();
		setPreferredSize(new Dimension(panelmage.getWidth(), panelmage.getHeight()));
	}
	public void setDevice(BraceletDevice dev)
	{
		braceletDev = dev;
	}
	public BraceletDevice getDevice()
	{
		return braceletDev;
	}
	////////////////////////////////////////////////
	//	paint
	////////////////////////////////////////////////
	private void clear(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.clearRect(0, 0, getWidth(), getHeight());
	}
	private void drawPanelImage(Graphics g)
	{
		g.drawImage(getPaneImage(), 0, 0, null);
	}
	public void paint(Graphics g)
	{
		BraceletDevice dev = getDevice();
		if (dev.isPowerOn()&&dev.isAlarmPowerOn())
			loadImage(BRACELET_ON_ON_IMAGE);
		else if (dev.isPowerOn()&&!dev.isAlarmPowerOn())
				loadImage(BRACELET_ON_OFF_IMAGE);
		else if (!dev.isPowerOn()&&!dev.isAlarmPowerOn())
			loadImage(BRACELET_OFF_OFF_IMAGE);
		clear(g);
		drawPanelImage(g);
		//draw alarm time
		int fontSize = 18;
	    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
	    g.setColor(Color.WHITE);
		g.drawString(dev.getAlarmTime(), 280 , 80);
		String text= dev.getResultInfo();
		int y=135;
		for (String line : text.split("\r")){
		g.drawString(line, 270, y += g.getFontMetrics().getHeight());}
	}
}