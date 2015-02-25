package f2b506.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerUI {
	private HashMap<String, Object> views;
	
	public HashMap<String, Object> getViews() {
		return views;
	}

	private JFrame frame;
	private JPanel statusPanel;
	private JLabel nameChair;
	private JLabel nameBed;
	private JLabel nameBand;
	private PresenceStatusLabel stateChair;
	private PresenceStatusLabel stateBed;
	private JLabel stateBand;
	private JTextArea debugInfo;
	
	public ServerUI() {
		views = new HashMap<String, Object>();
		nameChair = new JLabel("Chair");
		nameBed = new JLabel("Bed");
		nameBand = new JLabel("Band");
		stateChair = new PresenceStatusLabel("Disconnected");
		stateBed = new PresenceStatusLabel("Disconnected");
		stateBand = new JLabel("Disconnected");
		debugInfo = new JTextArea("Loading debugging info...");
		debugInfo.setSize(200, 500);
		debugInfo.setEditable(false);
		debugInfo.setLineWrap(true);
		debugInfo.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(debugInfo); 
		views.put("chair", stateChair);
		views.put("debug", debugInfo);
		
		
		frame = new JFrame("UPnP Server");
		statusPanel = new JPanel();
		
		
		
		frame.setSize(400, 600);
		frame.setLayout(new BorderLayout());
		frame.add(statusPanel, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		statusPanel.setSize(400, 100);
		statusPanel.setLayout(new GridLayout(3, 2));
		statusPanel.add(nameChair);
		statusPanel.add(stateChair);
		statusPanel.add(nameBed);
		statusPanel.add(stateBed);
		statusPanel.add(nameBand);
		statusPanel.add(stateBand);
		
		
		frame.setVisible(true);
	}
	

}
