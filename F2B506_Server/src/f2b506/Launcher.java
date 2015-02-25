package f2b506;

import f2b506.control.ControlPoint;
import f2b506.ui.ServerUI;

public class Launcher {

	public static void main(String[] args) {
		ServerUI serverUI = new ServerUI();
		ControlPoint controlPoint = new ControlPoint(serverUI.getViews());
		controlPoint.start();
		
	}

}
