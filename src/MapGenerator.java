

import java.io.File;
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
		String currentDir = new File("").getAbsolutePath();
		new File(currentDir+"\\"+name.substring(0,name.length()-4)).mkdir();
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
	public static TruthData createMoves(String name,int moveSize,Map map) throws IOException {
		name = map.getName().substring(0,map.getName().length()-4)+"\\"+"total,"+moveSize+","+name;
		Path file = Paths.get(name);
		char[] moveData = new char[moveSize];
		char[] sensorReading = new char[moveSize];
		Coord[] coordData = new Coord[moveSize];
		/*
		Coord init;
		Coord[] TrueCordData;
		char[] MoveData;
		char[] sensorReading;
		*/
		
		List<String> linesToWrite = new ArrayList<String>();
		//init coord
		Coord current = new Coord((int)(Math.random()*map.getCellMap().length),(int)(Math.random()*map.getCellMap().length) );
		linesToWrite.add(current.getX()+" "+current.getY());
		System.out.println(current.toString());
		//generate moves
		for(int x=0;x<moveSize;x++){
			double temp = Math.random();
			if(temp<.25){
				moveData[x] = 'U';
			}else if(temp>=.25 && temp<.5){
				moveData[x] = 'L';
			}else if(temp>=.5 && temp<.75){
				moveData[x] = 'D';
			}else{
				moveData[x] = 'R';
			}
		}
		
		//coord data
		
		for(int x=0;x<moveSize;x++){
			int xunit = 0;
			int yunit = 0;
			switch(moveData[x]){
			case 'U':
				if(current.getY()-1>=0 && map.getCell(current.getX(), current.getY()-1).getType()!='B' && Math.random()<.9)
					yunit=-1;
				break;
			case 'L':
				if(current.getX()-1>=0 && map.getCell(current.getX()-1, current.getY()).getType()!='B' && Math.random()<.9)
					xunit=-1;
				break;
			case 'D':
				if(current.getY()+1<map.getCellMap().length && map.getCell(current.getX(), current.getY()+1).getType()!='B' &&  Math.random()<.9)
					yunit=1;
				break;
			case 'R':
				if(current.getX()+1<map.getCellMap().length && map.getCell(current.getX()+1, current.getY()).getType()!='B' && Math.random()<.9)
					xunit=1;
				break;
			}
			coordData[x] = new Coord(current.getX()+xunit,current.getY()+yunit);
			double what = Math.random();
			if(what<=.9){
				sensorReading[x] = map.getCell(coordData[x]).getType();
			}else if(what>.9 && what<=.95){
				switch(map.getCell(coordData[x]).getType()){
				case 'N':
					sensorReading[x] = 'H';
					break;
				case 'H':
					sensorReading[x] = 'T';
					break;
				case 'T':
					sensorReading[x] = 'N';
					break;
				}
			}else{
				switch(map.getCell(coordData[x]).getType()){
				case 'N':
					sensorReading[x] = 'T';
					break;
				case 'H':
					sensorReading[x] = 'N';
					break;
				case 'T':
					sensorReading[x] = 'H';
					break;
				}
			}
			current = coordData[x];
		}
		for(int x=0;x<moveSize;x++){
			linesToWrite.add(coordData[x].toString());
		}
		for(int x=0;x<moveSize;x++){
			linesToWrite.add(moveData[x]+"");
		}
		for(int x=0;x<moveSize;x++){
			linesToWrite.add(sensorReading[x]+"");
		}
		
		Files.write(file, linesToWrite, Charset.forName("UTF-8"));

		return new TruthData(name);
	}

}
