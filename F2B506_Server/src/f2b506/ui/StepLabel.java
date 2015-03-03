package f2b506.ui;

import javax.swing.JLabel;

public class StepLabel extends JLabel{
	
	private static final long serialVersionUID = -8239748498126378063L;

	public StepLabel(String string) {
		super(string);
	}

	public void setStep(String step) {
		setText(step+" steps");
	}

}
