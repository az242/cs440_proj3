import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TruthData {
	Coord init;
	Coord[] TrueCordData;
	char[] MoveData;
	char[] sensorReading;
	public TruthData(Coord init, Coord[] coords, char[] moveChoices, char[] sensor){
		this.init=init;
		TrueCordData = coords;
		MoveData = moveChoices;
		sensorReading = sensor;
	}
	public TruthData(String filename) throws FileNotFoundException{
		File mapfile = new File(filename);
		String[] fileName = filename.split("\\\\");
		String size = fileName[fileName.length - 1];
		int moveSize=0;
		//find size of move list
		for(int x=0;x<size.length();x++){
			if(Character.isDigit(size.charAt(x))){
				int start=x;
				while(Character.isDigit(size.charAt(x))){
					x++;
				}
				moveSize = Integer.parseInt(size.substring(start,x));
			}
		}
		Scanner sc = new Scanner(mapfile);
		
		init = new Coord(sc.nextInt(),sc.nextInt());
		
		TrueCordData = new Coord[moveSize];
		for(int x=0;x<moveSize;x++){
			TrueCordData[x] = new Coord(sc.nextInt(),sc.nextInt());
		}
		
		MoveData = new char[moveSize];
		for(int x=0;x<moveSize;x++){
			MoveData[x] = sc.next().charAt(0);
		}
		
		sensorReading = new char[moveSize];
		for(int x=0;x<moveSize;x++){
			sensorReading[x] = sc.next().charAt(0);
		}
	}
	public Coord getInit() {
		return init;
	}
	public void setInit(Coord init) {
		this.init = init;
	}
	public Coord[] getTrueCordData() {
		return TrueCordData;
	}
	public void setTrueCordData(Coord[] trueCordData) {
		TrueCordData = trueCordData;
	}
	public char[] getMoveData() {
		return MoveData;
	}
	public void setMoveData(char[] moveData) {
		MoveData = moveData;
	}
	public char[] getSensorReading() {
		return sensorReading;
	}
	public void setSensorReading(char[] sensorReading) {
		this.sensorReading = sensorReading;
	}
	
}
