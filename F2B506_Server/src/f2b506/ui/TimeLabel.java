package f2b506.ui;

import javax.swing.JLabel;

public class TimeLabel extends JLabel{
	
	private static final long serialVersionUID = -8230748898126378063L;

	public TimeLabel(String string) {
		super(string);
	}

	public void setTime(String time) {
		setText(time+" minutes");
	}

}
