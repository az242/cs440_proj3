	
public class FilterAlgorithm {
	public static Map[] filter(TruthData td, Map map){
		Map[] mapSteps = new Map[td.getMoveData().length];
		//don't know if this will work
		for(int x=0;x<td.getMoveData().length;x++){
			mapSteps[x]= new Map(map);
		}
		for(int x=1;x<mapSteps.length;x++){
			double total=0;
			for(int y=0;y<map.getCellMap().length;y++){
				for(int z=0;z<map.getCellMap().length;z++){
					switch(td.getMoveData()[x]){
					case 'U':
						//up
						if(y==0 || map.getCellMap()[y-1][z].getType()=='B'){
							//stay in same place because of block or ceiling
							if(y==map.getCellMap().length-1){
								//nothing to add from prev cell because we are at boarder && 100% move
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//1
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
								total = total + prob*mapSteps[x-1].getCellMap()[y][z].getProbability();
							}else{
								//can't move up and not at the bottom
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//1 + p(move)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y+1][z].getProbability());
								total = total + prob*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y+1][z].getProbability();
							}
						}else{
							//add probs from prev cell and this cell
							if(y==map.getCellMap().length-1){
								//nothing to add from prev cell because we are at boarder && fail to move
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//p(Fmove)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
								total = total + prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability();
							}else{
								//add prev cell prob, 
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//p(Fmove) + p(move)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y+1][z].getProbability());
								total = total + prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y+1][z].getProbability();
							}
						}
						break;
					case 'D':
						if(y==map.getCellMap().length-1 || map.getCellMap()[y+1][z].getType()=='B'){
							if(y==0){
								//nothing to add from prev cell because we are at boarder && 100% move
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//1
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
								total = total + prob*mapSteps[x-1].getCellMap()[y][z].getProbability();
							}else{
								//can't move up and not at the bottom
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//1 + p(move)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y-1][z].getProbability());
								total = total + prob*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y-1][z].getProbability();
							}
						}else{
							//add probs from prev cell and this cell
							if(y==0){
								//nothing to add from prev cell because we are at boarder && fail to move
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//p(Fmove)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
								total = total + prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability();
							}else{
								//add prev cell prob, 
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//p(Fmove) + p(move)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y-1][z].getProbability());
								total = total + prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y-1][z].getProbability();
							}
						}
						//down
						break;
					case 'L':
						if(z==0 || map.getCellMap()[y][z-1].getType()=='B'){
							if(z==map.getCellMap().length-1){
								//nothing to add from prev cell because we are at boarder && 100% move
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//1
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
								total = total + prob*mapSteps[x-1].getCellMap()[y][z].getProbability();
							}else{
								//can't move left and not at the right
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//1 + p(move)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y][z+1].getProbability());
								total = total + prob*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y][z+1].getProbability();
							}
						}else{
							if(z==map.getCellMap().length-1){
								//nothing to add from prev cell because we are at boarder && fail to move
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//p(Fmove)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
								total = total + prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability();
							}else{
								//add prev cell prob, 
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//p(Fmove) + p(move)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y][z+1].getProbability());
								total = total + prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y][z+1].getProbability();
							}
						}
						//left
						break;
					case 'R':
						if(z==map.getCellMap().length-1 || map.getCellMap()[y][z+1].getType()=='B'){
							if(z==0){
								//nothing to add from prev cell because we are at boarder && 100% move
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//1
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability());
								total = total + prob*mapSteps[x-1].getCellMap()[y][z].getProbability();
							}else{
								//can't move up and not at the bottom
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//1 + p(move)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y][z-1].getProbability());
								total = total + prob*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y][z-1].getProbability();
							}
						}else{
							if(z==0){
								//nothing to add from prev cell because we are at boarder && fail to move
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//p(Fmove)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability());
								total = total + prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability();
							}else{
								//add prev cell prob, 
								double prob = .05;
								if(map.getCell(y, z).getType()==td.getSensorReading()[x-1]){
									prob=.9;
								}
								//p(Fmove) + p(move)
								mapSteps[x].getCellMap()[y][z].setProbability(prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y][z-1].getProbability());
								total = total + prob*.1*mapSteps[x-1].getCellMap()[y][z].getProbability()
										+ prob*.9*mapSteps[x-1].getCellMap()[y][z-1].getProbability();
							}
						}
						//right
						break;
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
