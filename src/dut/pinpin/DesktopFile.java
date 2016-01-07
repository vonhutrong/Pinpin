package dut.pinpin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class DesktopFile {
	
	public static String getFullPath(String fileName) {
		return System.getProperty("user.home") + "/.local/share/applications/" + fileName + ".desktop";
	}

	public static void writeToFile(String fileName, String content) {
		BufferedWriter writer = null;
		try {
			File file = new File(DesktopFile.getFullPath(fileName));
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
