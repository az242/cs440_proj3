	
public class FilterAlgorithm {
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
}
