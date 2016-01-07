package dut.pinpin;

public class Main {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				MainWindow window = new MainWindow();
				window.creatingAndSettingFrame();
				window.settingUpButton();
				window.showFrame();
				
			}
		});
		
	}

}
