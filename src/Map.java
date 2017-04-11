

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
	private Cell[][] cellArr;
	private String name;

	public Map(String path) throws FileNotFoundException {
		File mapfile = new File(path);
		String[] fileName = path.split("\\\\");
		name = fileName[fileName.length - 1];
		Scanner sc = new Scanner(mapfile);
		int dimensions = sc.nextInt();
		this.cellArr = new Cell[dimensions][dimensions];
		
		String[] rows = new String[dimensions];
		for (int i = 0; i < dimensions; i++) {
			rows[i] = sc.next();
		}
		int count = 0;
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
				this.cellArr[j][i] = new Cell(j, i, rows[i].charAt(j));
				if(this.cellArr[j][i].getType()=='B'){
					count++;
				}
			}
		}
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
				if(this.cellArr[j][i].getType()!='B'){
					this.cellArr[j][i].setProbability(1.0/( (dimensions*dimensions)-count) );
				}
			}
		}
	}
	public Map(Map map){
		this.cellArr = new Cell[map.getCellMap().length][map.getCellMap().length];
		for(int x=0;x<map.getCellMap().length;x++){
			for(int y=0;y<map.getCellMap().length;y++){
				this.cellArr[x][y] =  new Cell(x, y, map.getCellMap()[x][y].getType());
				this.cellArr[x][y].setProbability(map.getCellMap()[x][y].getProbability());
			}
		}
		name = map.getName();
	}

	public String getName() {
		return name;
	}

	public Cell[][] getCellMap() {
		return cellArr;
	}

	public Cell getCell(int x, int y) {
		if (x < 0 || x >= cellArr.length || y < 0 || y >= cellArr[0].length)
			return null;
		return cellArr[x][y];
	}

	public Cell getCell(Coord coord) {
		return cellArr[coord.getX()][coord.getY()];
	}

	public void setName(String name) {
		this.name = name;
	}
	public void copy(Map map){
		this.cellArr = new Cell[map.getCellMap().length][map.getCellMap().length];
		for(int x=0;x<map.getCellMap().length;x++){
			for(int y=0;y<map.getCellMap().length;y++){
				this.cellArr[x][y] =  new Cell(x, y, map.getCellMap()[x][y].getType());
				this.cellArr[x][y].setProbability(map.getCellMap()[x][y].getProbability());
			}
		}
	}
}
