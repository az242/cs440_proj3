

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
		
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
				this.cellArr[j][i] = new Cell(j, i, rows[i].charAt(j));
			}
		}

		/*
		 * for (int i = 0; i < 160; i++) { String row = sc.next(); for (int j =
		 * 0; j < 120; j++) { this.mapArr[i][j] = row.charAt(j);
		 * this.cellArr[i][j] = new Cell(i, j, row.charAt(j)); } }
		 */
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
}
