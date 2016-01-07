package dut.pinpin;

public class UbuntuApp {

	private String name;
	private String execPath;
	private String iconPath;
	private String comment;
	private boolean terminal;
	private String categories;
	
	public UbuntuApp(String execPath, String iconPath) {
		this.execPath = execPath.trim();
		this.iconPath = iconPath.trim();
		comment = "This desktop file was created by Pinpin app (develop by trongvn)";
		terminal = false;
		categories = "Utility;Application;";
	}
	
	public String createContentDesktopFile() {
		autoCreateName();	//auto create name if necessary
		
		String format = "[Desktop Entry]" + "\n" +
				"Version=1.0" + "\n" +
				"Name=%s" + "\n" +
				"Comment=%s" + "\n" +
				"Exec=%s" + "\n" +
				"Icon=%s" + "\n" +
				"Terminal=%s" + "\n" +
				"Type=Application" + "\n" +
				"Categories=%s";
		String content = String.format(format, name, comment, execPath, iconPath, Boolean.toString(terminal), categories);
		return content;
	}

	public String getName() {
		autoCreateName(); //auto create name if necessary
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private void autoCreateName() {
		if (name == null || name.trim().length() == 0) {
			name = execPath.substring(execPath.lastIndexOf('/') + 1);
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
		}
	}

}
