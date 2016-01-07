package dut.pinpin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow implements ActionListener {
	
	private static String TITLE = "Pinpin";
	private JFrame mainFrame;
	
	private JTextField tfAppName;
	private JTextField tfExec;
	private JTextField tfIcon;
	private TButton btnBrowseExec;
	private TButton btnBrowseIcon;
	private TButton btnPin;
	
	private final static String BROWSE_EXEC = "browse_exec";
	private final static String BROWSE_ICON = "browse_icon";
	private final static String PIN_TO_LAUNCHER = "pin_to_launcher";

	static String previousDir = null;
	
	private UbuntuApp ubuntuApp;
	
	public void creatingAndSettingFrame() {
		
		//creating and setting up a Frame
		mainFrame = new JFrame(TITLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		JFrame.setDefaultLookAndFeelDecorated(false);
		
		//set icon
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ubuntu_icon.png"));
		ImageIcon icon = new ImageIcon(image);
		mainFrame.setIconImage(icon.getImage());
		
		createFields();		
		
		//Setting the Window Size and Location
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);

	}
	
	public void settingUpButton() {
		btnBrowseExec.setActionCommand(BROWSE_EXEC);
		btnBrowseExec.addActionListener(this);
		btnBrowseIcon.setActionCommand(BROWSE_ICON);
		btnBrowseIcon.addActionListener(this);
		btnPin.setActionCommand(PIN_TO_LAUNCHER);
		btnPin.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		//Create file chooser apply for any file with size less than 2 MB
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Choose file");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new ChooseFileFilter("", "All file (size < 2 MB)", 2));
		
		//Handle the browse exec button.
		if (BROWSE_EXEC.equals(command)) {
			
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				tfExec.setText(fileChooser.getSelectedFile() + "");
				previousDir = fileChooser.getCurrentDirectory() + ""; //save current dir to easyer on next choose
			}
			
		} else if (BROWSE_ICON.equals(command)) {
			
			if (previousDir != null) {
				fileChooser.setCurrentDirectory(new java.io.File(previousDir)); //move to previous dir
			}
			
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				tfIcon.setText(fileChooser.getSelectedFile() + "");
			}
			
		} else if (PIN_TO_LAUNCHER.equals(command)) {

			if (tfExec.getText().isEmpty() || tfIcon.getText().isEmpty()) {
				new WarningDialog("Please chose Exec and Icon files", "Warning!").show();
			} else {
				ubuntuApp = new UbuntuApp(tfExec.getText(), tfIcon.getText());
				if (tfAppName != null && tfAppName.getText().trim().length() != 0) {
					ubuntuApp.setName(tfAppName.getText());
				}
				
				String fileName = Converter.toFileName(ubuntuApp.getName());
				
				DesktopFile.writeToFile(fileName, ubuntuApp.createContentDesktopFile());
				
				String shellCommand = "chmod +x " + DesktopFile.getFullPath(fileName);
				ExecuteShellComand.executeCommand(shellCommand);
				
				new InfoDialog("That application was pinned to launcher bar", "Finished").show();
			}
			
		}
		
	}

	public void showFrame() {
		mainFrame.setVisible(true);
	}
	
	public void createFields() {
		
		//for app name
		JLabel lbAppName = new JLabel("Application name");
		tfAppName = new JTextField(15);
		JPanel pnlAppName = new JPanel(new FlowLayout());
		pnlAppName.add(lbAppName);
		pnlAppName.add(tfAppName);
		
		//exec group component
		JLabel lbExec = new JLabel("Exec *");
		tfExec = new JTextField(20);
		btnBrowseExec = new TButton("Browse");
		JPanel pnlExec = new JPanel(new FlowLayout());
		pnlExec.add(lbExec);
		pnlExec.add(tfExec);
		pnlExec.add(btnBrowseExec);
		
		//icon group component
		JLabel lbIcon = new JLabel("Icon *");
		tfIcon = new JTextField(20);
		btnBrowseIcon = new TButton("Browse");
		JPanel pnlIcon = new JPanel(new FlowLayout());
		pnlIcon.add(lbIcon);
		pnlIcon.add(tfIcon);
		pnlIcon.add(btnBrowseIcon);
		
		//pin group component
		btnPin = new TButton("Pin");
		btnPin.setToolTipText("Pin to launcher");
		JPanel pnlPin = new JPanel(new FlowLayout());
		pnlPin.add(btnPin);

		//add to content panel
		JPanel inputPanel = new JPanel(new GridLayout(4, 1));
		inputPanel.add(pnlAppName);
		inputPanel.add(pnlExec);
		inputPanel.add(pnlIcon);
		inputPanel.add(pnlPin);
		mainFrame.getContentPane().add(inputPanel, BorderLayout.CENTER);
		
	}
	
}
