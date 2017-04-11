


import java.io.File;
import java.util.ArrayList;

public class FileUtils {
	public static Map[] getMapList() {
		String currentDir = new File("").getAbsolutePath();
		File file = new File(currentDir);
		File[] files = file.listFiles();
		ArrayList<Map> test = new ArrayList<Map>();
		for (int z = 0; z < files.length; z++) {
			if (files[z].getName().contains(".txt")) {
				try {
					test.add(new Map(files[z].getAbsolutePath()));
				} catch (Exception e) {
					// e.printStackTrace();
					System.out.println(
							"error while grabbing map data. Incorrect Txt Format! Probably not a map? " + e.toString());

				}
			}
		}
		Map[] theMaps = new Map[test.size()];
		test.toArray(theMaps);
		return theMaps;
	}

}
