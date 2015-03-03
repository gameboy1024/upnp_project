package f2b506.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import f2b506.control.BandSynchronisation;
import f2b506.control.ControlPoint;

public class ServerUI {
	private HashMap<String, Object> views;
	
	public HashMap<String, Object> getViews() {
		return views;
	}

	private ControlPoint controlPoint; 
	private JFrame frame;
	private JPanel statusPanel;
	private JLabel nameChair;
	private JLabel nameBed;
	private JLabel nameBand;
	private PresenceStatusLabel stateChair;
	private PresenceStatusLabel stateBed;
	private JButton syncBand;
	private JLabel walkTime;
	private JLabel walkStep;
	private JLabel runTime;
	private JLabel runStep;
	private TimeLabel walkTimeRes;
	private StepLabel walkStepRes;
	private TimeLabel runTimeRes;
	private StepLabel runStepRes;
	private JTextArea debugInfo;
	private JButton profile;
	
	public ServerUI() {
		views = new HashMap<String, Object>();
		nameChair = new JLabel("Chair");
		nameBed = new JLabel("Bed");
		nameBand = new JLabel("Band");
		stateChair = new PresenceStatusLabel("Disconnected");
		stateBed = new PresenceStatusLabel("Disconnected");
		syncBand = new JButton("Sync");
		syncBand.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				BandSynchronisation.start(controlPoint);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		walkTime = new JLabel("Walking Time");
		walkStep = new JLabel("Walking Steps");
		runTime = new JLabel("Running Time");
		runStep = new JLabel("Running Steps");
		walkTimeRes = new TimeLabel("");
		walkStepRes = new StepLabel("");
		runTimeRes = new TimeLabel("");
		runStepRes = new StepLabel("");
		debugInfo = new JTextArea("Loading debugging info...");
		debugInfo.setSize(200, 500);
		debugInfo.setEditable(false);
		debugInfo.setLineWrap(true);
		debugInfo.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(debugInfo); 
		views.put("chair", stateChair);
		views.put("bed", stateBed);
		views.put("band", syncBand);
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
		statusPanel.setLayout(new GridLayout(8, 2));
		statusPanel.add(nameChair);
		statusPanel.add(stateChair);
		statusPanel.add(nameBed);
		statusPanel.add(stateBed);
		statusPanel.add(nameBand);
		statusPanel.add(syncBand);
		
	
		statusPanel.add(walkTime);
		statusPanel.add(walkTimeRes);
		statusPanel.add(walkStep);
		statusPanel.add(walkStepRes);
		statusPanel.add(runTime);
		statusPanel.add(runTimeRes);
		statusPanel.add(runStep);
		statusPanel.add(runStepRes);
		
		profile = new JButton("My page SantéMètre");
		statusPanel.add(profile);
		profile.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.google.fr/"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		frame.setVisible(true);
	}

	public ControlPoint getControlPoint() {
		return controlPoint;
	}

	public void setControlPoint(ControlPoint controlPoint) {
		this.controlPoint = controlPoint;
	}
	

}
