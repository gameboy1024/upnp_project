package f2b506.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;

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
	private PresenceStatusLabel stateBand;
	private JLabel walkTime;
	private JLabel walkStep;
	private JLabel runTime;
	private JLabel runStep;
	private TimeLabel walkTimeRes;
	private StepLabel walkStepRes;
	private TimeLabel runTimeRes;
	private StepLabel runStepRes;
	private JTextArea debugInfo;
	
	public ServerUI() {
		views = new HashMap<String, Object>();
		nameChair = new JLabel("Chair");
		nameBed = new JLabel("Bed");
		nameBand = new JLabel("Band");
		stateChair = new PresenceStatusLabel("Disconnected");
		stateBed = new PresenceStatusLabel("Disconnected");
		stateBand = new PresenceStatusLabel("Disconnected");
		walkTime = new JLabel("Walking Time");
		walkStep = new JLabel("Walking Steps");
		runTime = new JLabel("Running Time");
		runStep = new JLabel("Running Steps");
		walkTimeRes = new TimeLabel("Walking Time");
		walkStepRes = new StepLabel("Walking Steps");
		runTimeRes = new TimeLabel("Running Time");
		runStepRes = new StepLabel("Running Steps");
		debugInfo = new JTextArea("Loading debugging info...");
		debugInfo.setSize(200, 500);
		debugInfo.setEditable(false);
		debugInfo.setLineWrap(true);
		debugInfo.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(debugInfo); 
		views.put("chair", stateChair);
		views.put("bed", stateBed);
		views.put("band", stateBand);
		views.put("walkTime", walkTimeRes);
		views.put("walkStep", walkStepRes);
		views.put("runTime", runTimeRes);
		views.put("runStep", runStepRes);
		views.put("debug", debugInfo);
		
		
		frame = new JFrame("UPnP Server");
		statusPanel = new JPanel();
		
		
		
		frame.setSize(400, 600);
		frame.setLayout(new BorderLayout());
		frame.add(statusPanel, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		statusPanel.setSize(400, 100);
		statusPanel.setLayout(new GridLayout(7, 2));
		statusPanel.add(nameChair);
		statusPanel.add(stateChair);
		statusPanel.add(nameBed);
		statusPanel.add(stateBed);
		statusPanel.add(nameBand);
		statusPanel.add(stateBand);
		
	
		statusPanel.add(walkTime);
		statusPanel.add(walkTimeRes);
		statusPanel.add(walkStep);
		statusPanel.add(walkStepRes);
		statusPanel.add(runTime);
		statusPanel.add(runTimeRes);
		statusPanel.add(runStep);
		statusPanel.add(runStepRes);
		
		
		frame.setVisible(true);
	}
	

}
