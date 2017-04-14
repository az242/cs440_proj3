	
public class Algorithms {
	public static Map[] filter(TruthData td, Map map){
		Map[] mapSteps = new Map[td.getMoveData().length+1];
		//don't know if this will work
		for(int x=0;x<td.getMoveData().length+1;x++){
			mapSteps[x]= new Map(map);
		}
		for(int x=1;x<mapSteps.length;x++){
			double total=0;
			for(int y=0;y<map.getCellMap().length;y++){
				for(int z=0;z<map.getCellMap().length;z++){
					if(map.getCell(y, z).getType()!='B'){
						double prob=0;
						switch(td.getMoveData()[x-1]){
						case 'L':
							//up
							prob = .05;
							if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
								prob=.9;
							}
							if(y==0 || map.getCellMap()[y-1][z].getType()=='B'){
								//stay in same place because of block or ceiling
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}else{
								//fail to move 
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}
							if(y!=map.getCellMap().length-1 && map.getCellMap()[y+1][z].getType()!='B'){
								//if we arent at an edge and there isn't a block where we move from then add that
								mapSteps[x].getCellMap()[y][z].setProbability(mapSteps[x].getCellMap()[y][z].getProbability() + 
										prob*.9*mapSteps[x-1].getCellMap()[y+1][z].getProbability());
							}
							total = total + mapSteps[x].getCellMap()[y][z].getProbability();
							break;
						case 'R':
							prob = .05;
							if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
								prob=.9;
							}
							if(y==map.getCellMap().length-1 || map.getCellMap()[y+1][z].getType()=='B'){
								//stay in same place because of block or ceiling
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}else{
								//Nothing blocking the cell
								mapSteps[x]
										.getCellMap()[y][z]
												.setProbability(prob*.1*
														mapSteps[x-1]
														.getCellMap()[y][z]
																.getProbability());
							}
							if(y!=0 && map.getCellMap()[y-1][z].getType()!='B'){
								mapSteps[x].getCellMap()[y][z].setProbability(mapSteps[x].getCellMap()[y][z].getProbability() + 
										prob*.9*mapSteps[x-1].getCellMap()[y-1][z].getProbability());
							}
							total = total + mapSteps[x].getCellMap()[y][z].getProbability();
							//down
							break;
						case 'U':
							prob = .05;
							if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
								prob=.9;
								
							}
							if(z==0 || map.getCellMap()[y][z-1].getType()=='B'){
								//stay in same place because of block or edge
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}else{
								//add probs from prev cell and this cell
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}
							if(z!=map.getCellMap().length-1 && map.getCellMap()[y][z+1].getType()!='B'){
								mapSteps[x].getCellMap()[y][z].setProbability(mapSteps[x].getCellMap()[y][z].getProbability() + 
										prob*.9*mapSteps[x-1].getCellMap()[y][z+1].getProbability());
							}
							total = total + mapSteps[x].getCellMap()[y][z].getProbability();
							//left
							break;
						case 'D':
							prob = .05;
							if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
								prob=.9;
							}
							if(z==map.getCellMap().length-1 || map.getCellMap()[y][z+1].getType()=='B'){
								//stay in same place because of block or ceiling
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}else{
								//Nothing blocking the cell
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}
							if(z!=0 && map.getCellMap()[y][z-1].getType()!='B'){
								mapSteps[x].getCellMap()[y][z].setProbability(mapSteps[x].getCellMap()[y][z].getProbability() + 
										prob*.9*mapSteps[x-1].getCellMap()[y][z-1].getProbability());
							}
							total = total + mapSteps[x].getCellMap()[y][z].getProbability();
							//right
							break;
						}
					}
				}
			}
			
			//normalize probs
			for(int y=0;y<map.getCellMap().length;y++){
				for(int z=0;z<map.getCellMap().length;z++){
					mapSteps[x].getCellMap()[y][z].setProbability(mapSteps[x].getCellMap()[y][z].getProbability()/total);
				}
			}
		}
		return mapSteps;
	}
	public static Map[] verterbi(TruthData td, Map map){
		Map[] mapSteps = new Map[td.getMoveData().length+1];
		//don't know if this will work
		for(int x=0;x<td.getMoveData().length+1;x++){
			mapSteps[x]= new Map(map);
		}
		for(int x=1;x<mapSteps.length;x++){
			double total=0;
			for(int y=0;y<map.getCellMap().length;y++){
				for(int z=0;z<map.getCellMap().length;z++){
					if(map.getCell(y, z).getType()!='B'){
						double prob=0;
						switch(td.getMoveData()[x-1]){
						case 'L':
							//up
							prob = .05;
							if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
								prob=.9;
							}
							if(y==0 || map.getCellMap()[y-1][z].getType()=='B'){
								//stay in same place because of block or ceiling
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}else{
								//fail to move 
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}
							mapSteps[x].getCellMap()[y][z].setContributer(new Coord(y,z));
							if(y!=map.getCellMap().length-1 && map.getCellMap()[y+1][z].getType()!='B'){
								//if we arent at an edge and there isn't a block where we move from then add that
								if(prob*.9*mapSteps[x-1].getCellMap()[y+1][z].getProbability() > mapSteps[x].getCellMap()[y][z].getProbability()){
									mapSteps[x].getCellMap()[y][z].setProbability(prob*.9*mapSteps[x-1].getCellMap()[y+1][z].getProbability());
									mapSteps[x].getCellMap()[y][z].setContributer(new Coord(y+1,z));
								}
							}
							total = total + mapSteps[x].getCellMap()[y][z].getProbability();
							break;
						case 'R':
							prob = .05;
							if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
								prob=.9;
							}
							if(y==map.getCellMap().length-1 || map.getCellMap()[y+1][z].getType()=='B'){
								//stay in same place because of block or ceiling
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}else{
								//Nothing blocking the cell
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}
							mapSteps[x].getCellMap()[y][z].setContributer(new Coord(y,z));
							if(y!=0 && map.getCellMap()[y-1][z].getType()!='B'){
								if(prob*.9*mapSteps[x-1].getCellMap()[y-1][z].getProbability() > mapSteps[x].getCellMap()[y][z].getProbability()){
									mapSteps[x].getCellMap()[y][z].setProbability(prob*.9*mapSteps[x-1].getCellMap()[y-1][z].getProbability());
									mapSteps[x].getCellMap()[y][z].setContributer(new Coord(y-1,z));
								}
							}
							total = total + mapSteps[x].getCellMap()[y][z].getProbability();
							//down
							break;
						case 'U':
							prob = .05;
							if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
								prob=.9;
								
							}
							if(z==0 || map.getCellMap()[y][z-1].getType()=='B'){
								//stay in same place because of block or edge
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}else{
								//add probs from prev cell and this cell
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}
							mapSteps[x].getCellMap()[y][z].setContributer(new Coord(y,z));
							if(z!=map.getCellMap().length-1 && map.getCellMap()[y][z+1].getType()!='B'){
								if(prob*.9*mapSteps[x-1].getCellMap()[y][z+1].getProbability() > mapSteps[x].getCellMap()[y][z].getProbability()){
									mapSteps[x].getCellMap()[y][z].setProbability(prob*.9*mapSteps[x-1].getCellMap()[y][z+1].getProbability());
									mapSteps[x].getCellMap()[y][z].setContributer(new Coord(y,z+1));
								}
							}
							total = total + mapSteps[x].getCellMap()[y][z].getProbability();
							//left
							break;
						case 'D':
							prob = .05;
							if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
								prob=.9;
							}
							if(z==map.getCellMap().length-1 || map.getCellMap()[y][z+1].getType()=='B'){
								//stay in same place because of block or ceiling
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}else{
								//Nothing blocking the cell
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
							}
							mapSteps[x].getCellMap()[y][z].setContributer(new Coord(y,z));
							if(z!=0 && map.getCellMap()[y][z-1].getType()!='B'){
								if(prob*.9*mapSteps[x-1].getCellMap()[y][z-1].getProbability() > mapSteps[x].getCellMap()[y][z].getProbability()){
									mapSteps[x].getCellMap()[y][z].setProbability(prob*.9*mapSteps[x-1].getCellMap()[y][z-1].getProbability());
									mapSteps[x].getCellMap()[y][z].setContributer(new Coord(y,z-1));
								}
							}
							total = total + mapSteps[x].getCellMap()[y][z].getProbability();
							//right
							break;
						}
					}
				}
			}
			//normalize probs
			for(int y=0;y<map.getCellMap().length;y++){
				for(int z=0;z<map.getCellMap().length;z++){
					mapSteps[x].getCellMap()[y][z].setProbability(mapSteps[x].getCellMap()[y][z].getProbability()/total);
				}
			}
			
		}
		Coord max = new Coord(0,0);
		//normalize probs
		for(int y=0;y<map.getCellMap().length;y++){
			for(int z=0;z<map.getCellMap().length;z++){
				if(mapSteps[0].getCell(max).getProbability()<mapSteps[0].getCell(y,z).getProbability()){
					max = new Coord(y,z);
				}
			}
		}
		Coord[] path = getPath(mapSteps,max);
			for(int w=0;w<path.length;w++){
				//System.out.print(path[w].toString() + " -> ");
			}
		return mapSteps;
	}
	public static Coord[] getPath(Map[] steps, Coord max){
		Coord[] set = new Coord[steps.length];
		set[0] = max;
		for(int x=1;x<steps.length;x++){
			set[x] = steps[x].getCell(set[x-1]).getContributer();
		}
		return set;
	}
}
