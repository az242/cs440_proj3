import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String[] args){
		String currentDir = new File("").getAbsolutePath();
		String[] test = currentDir.split("\\\\");
		currentDir = currentDir + "\\" + "name"+ "\\" + "textFile.txt";
		System.out.println(currentDir);
		List<String> linesToWrite = new ArrayList<String>();
		linesToWrite.add("test");
		Path file = Paths.get(currentDir);
		for(int x=0;x<test.length;x++){
			System.out.println(test[x]);
		}
		try {
			Files.write(file, linesToWrite, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
