package f2b506.ui;

import javax.swing.JLabel;

public class PresenceStatusLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8239748898126378063L;

	public PresenceStatusLabel(String string) {
		super(string);
	}

	public void setStatus(boolean enabled) {
		if (enabled) {
			setText("Present");
		} else {
			setText("Absent");
		}
	}
}
