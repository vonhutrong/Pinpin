package dut.pinpin;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class InfoDialog {

	private String message;
	private String title;
	private JDialog dialog;
	
	public InfoDialog(String message, String title) {
		this.message = message;
		this.title = title;
		
		createDialog();
	}
	
	private void createDialog() {
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		dialog = optionPane.createDialog(title);
	}
	
	public void show() {
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
}
