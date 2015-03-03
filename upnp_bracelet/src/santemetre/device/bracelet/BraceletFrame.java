package santemetre.device.bracelet;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import org.cybergarage.upnp.device.InvalidDescriptionException;
import org.cybergarage.util.Debug;



public class BraceletFrame extends JFrame implements WindowListener{
	private final static String TITLE = "SanteMetre Bracelet";
	private BraceletDevice braceletDev;
	private BraceletPane braceletPane;
	
	public BraceletFrame()
	{
		super(TITLE);
		try {
			braceletDev = new BraceletDevice();
		}
		catch (InvalidDescriptionException e) {
			Debug.warning(e);
		}
		getContentPane().setLayout(new BorderLayout());
		braceletPane = new BraceletPane();
		braceletPane.setDevice(braceletDev);
		braceletDev.setComponent(braceletPane);
		getContentPane().add(braceletPane, BorderLayout.CENTER);
		addWindowListener(this);
		setResizable(false);
		pack();
		setVisible(true);
		braceletDev.start();
		braceletDev.getComponent().repaint();
	}

	public BraceletPane getClockPanel()
	{
		return braceletPane;
	}

	public BraceletDevice getClockDevice()
	{
		return braceletDev;
	}
	public static void main(String args[]) 
	{
		//Debug.on();
		BraceletFrame sampBracelet = new BraceletFrame();
		System.out.println("bracelet on ");
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


}

