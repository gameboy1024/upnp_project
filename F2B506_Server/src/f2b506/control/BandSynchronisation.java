package f2b506.control;

import java.util.HashMap;

import f2b506.ui.StepLabel;
import f2b506.ui.TimeLabel;

public class BandSynchronisation implements Runnable {
	
	private ControlPoint controlPoint;
	
	public BandSynchronisation(ControlPoint controlPoint){
		this.controlPoint = controlPoint;
	}

	@Override
	public void run() {
		if(controlPoint.isBandFound()) {
			HashMap<String, Object> views = controlPoint.getViews();
			try {
				String res = controlPoint.getBandInfo("StartSync");
				String[] resSplit = res.split(" ");
				((TimeLabel) views.get("walkTime"))
				.setTime(resSplit[1]);
				((StepLabel) views.get("walkStep"))
				.setStep(resSplit[0]);
				((TimeLabel) views.get("runTime"))
				.setTime(resSplit[3]);
				((StepLabel) views.get("runStep"))
				.setStep(resSplit[2]);
			} catch (DeviceNotAvailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void start(ControlPoint controlPoint){
		new Thread(new BandSynchronisation(controlPoint)).start();;
	}
}
