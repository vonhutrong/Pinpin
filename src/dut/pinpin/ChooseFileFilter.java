package dut.pinpin;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ChooseFileFilter extends FileFilter{
	
	private String extension = "";
	private String description = "";
	private int maxSize = 0;
	
	public ChooseFileFilter(String extension) {
		this.extension = extension;
	}
	
	public ChooseFileFilter(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}
	
	public ChooseFileFilter(String extension, String description, int maxSize) {
		this.extension = extension;
		this.description = description;
		this.maxSize = maxSize;
	}

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		if (maxSize > 0) {
			return f.getName().toLowerCase().endsWith(extension) &&
					f.length() < (maxSize * 1024 * 1024);
		} else {
			return (f.getName().toLowerCase().endsWith(extension));
		}
	}

	@Override
	public String getDescription() {
		return description;
	}

}
