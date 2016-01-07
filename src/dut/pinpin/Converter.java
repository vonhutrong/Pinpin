package dut.pinpin;

import java.util.List;

public class Converter {

	public static String listToString(@SuppressWarnings("rawtypes") List objects) {
		StringBuilder sb = new StringBuilder();
		String separator = ";";
		for (Object obj : objects) {
			sb.append(obj.toString()).append(separator);
		}
		return sb.toString();
	}
	
	public static String toFileName(String name) {
		return name.trim().replace(" ", "-").replace(".", "_");
	}

}
