import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String[] args) throws IOException{
		String output="";
		//generatemap
		//generatemoves
		//filterAlgo
		//printout data
		Map map = new Map("onehundred.txt");
		for(int x=1;x<100;x++){
			TruthData temp = MapGenerator.createMoves("as3"+x, x*10, map);
			Map[] steps = Algorithms.filter(temp, map);
			int hx=0,hy=0;
			for(int y=0;y<steps[0].getCellMap().length;y++){
				for(int z=0;z<steps[0].getCellMap().length;z++){
					if(steps[steps.length-1].getCell(y, z).getProbability() > steps[steps.length-1].getCell(hx,hy).getProbability()){
						hx=y;
						hy=z;
					}
				}
			}
			System.out.println("" + x + " "+ Math.sqrt(Math.pow(temp.getTrueCordData()[temp.getTrueCordData().length-1].getX() - hx, 2) + Math.pow(temp.getTrueCordData()[temp.getTrueCordData().length-1].getY() - hy, 2)));
		}
	}
}
