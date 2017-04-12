import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TruthData {
	String name;
	int moves;
	Coord init;
	Coord[] TrueCordData;
	char[] MoveData;
	char[] sensorReading;
	public TruthData(String name,Coord init, Coord[] coords, char[] moveChoices, char[] sensor){
		this.init=init;
		this.name=name;
		TrueCordData = coords;
		MoveData = moveChoices;
		sensorReading = sensor;
	}
	public TruthData(String filename) throws FileNotFoundException{
		File mapfile = new File(filename);
		String[] fileName = filename.split("\\\\");
		String[] name = fileName[fileName.length - 1].split(",");
		this.name=name[name.length-1];
		int moveSize=0;
		//find size of move list
		for(int x=0;x<name.length;x++){
			if(name[x].equals("total")){
				moveSize = Integer.parseInt(name[x+1]);
			}
		}
		moves=moveSize;
		Scanner sc = new Scanner(mapfile);
		
		init = new Coord(sc.nextInt(),sc.nextInt());
		
		TrueCordData = new Coord[moveSize];
		for(int x=0;x<moveSize;x++){
			TrueCordData[x] = new Coord(sc.next());
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
