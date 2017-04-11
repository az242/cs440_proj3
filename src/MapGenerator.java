

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.event.ListSelectionEvent;

public class MapGenerator {
	// '1' denotes open/unblocked
	// '0' denotes blocked
	// '2' denotes partially blocked
	// 'a', 'a' denotes vertical and horizontal highway
	// 'b', 'b' denotes vertical and horizontal highways in partially blocked
	public static Map createMap(String name,int dimension) throws IOException {
		Path file = Paths.get(name);

		// Create representation of the map, everything initialized to unblocked
		char[][] map = new char[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			Arrays.fill(map[i], 'N');
		}
		// Generate partially blocked cells
		map = generate(map,'H',0.2);

		// Generate highways/rivers
		map = generate(map,'T',0.2);

		// Generate blocked cells
		map = generate(map,'B',0.1);
		
		List<String> linesToWrite = new ArrayList<String>();
		linesToWrite.add(dimension+"");
		
		for (int i = 0; i < map[0].length; i++) {
			String tmp = "";
			for (int j = 0; j < map.length; j++) {
				tmp += map[j][i];
			}
			linesToWrite.add(tmp);
		}

		Files.write(file, linesToWrite, Charset.forName("UTF-8"));

		return new Map(name);
	}

	private static char[][] generate(char[][] map, char block, double percent) {
		// Generate coordinates to create partially blocked cells
		int numOfCells = (int) (map.length*map[0].length * percent);
		while(numOfCells>0){
			for(int x=0;x<map.length;x++){
				for(int y=0;y<map[0].length;y++){
					if(map[x][y]=='N' && Math.random()<percent){
						map[x][y] = block;
						numOfCells--;
					}
				}
			}
		}
		return map;
	}
	public static Map createMoves(String name,int moveSize) throws IOException {
		Path file = Paths.get(name);
		/*
		Coord init;
		Coord[] TrueCordData;
		char[] MoveData;
		char[] sensorReading;
		*/
		
		for(int x=0;x<moveSize;x++){
			
		}
		List<String> linesToWrite = new ArrayList<String>();
		
		for (int i = 0; i < map[0].length; i++) {
			String tmp = "";
			for (int j = 0; j < map.length; j++) {
				tmp += map[j][i];
			}
			linesToWrite.add(tmp);
		}

		Files.write(file, linesToWrite, Charset.forName("UTF-8"));

		return new Map(name);
	}
	
	public static SG[] generateSG(Coord start, Coord goal, Map map) {
		Random s = new Random(start.getX() + start.getY());
		Random g = new Random(goal.getX() + goal.getY());
		SG temp[] = new SG[9];
		for (int x = 0; x < 9; x++) {
			int startx = s.nextInt(160), starty = s.nextInt(120);
			while ((startx > 20 && startx < 140) || (starty > 20 && starty < 100)
					|| map.getCell(new Coord(startx, starty)).getType() == '0') {
				startx = s.nextInt(160);
				starty = s.nextInt(120);
			}
			int endx = s.nextInt(160), endy = s.nextInt(120);
			while ((endx > 20 && endx < 140) || (endy > 20 && endy < 100)
					|| (Math.sqrt(Math.pow(endx - startx, 2) + Math.pow(endy - starty, 2)) < 100)
					|| map.getCell(new Coord(endx, endy)).getType() == '0') {
				endx = s.nextInt(160);
				endy = s.nextInt(120);
			}
			temp[x] = new SG(new Coord(startx, starty), new Coord(endx, endy));
		}

		return temp;
	}

}
