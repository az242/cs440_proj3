	
public class FilterAlgorithm {
	public Map[] filter(TruthData td, Map map){
		Map[] mapSteps = new Map[td.getMoveData().length];
		for(int x=0;x<mapSteps.length;x++){
			Map temp = map;
			for(int y=0;y<map.getCellMap().length;y++){
				for(int z=0;z<map.getCellMap().length;z++){
					switch(td.getMoveData()[x]){
					case 'U':
						//up
						if(y==0 || temp.getCellMap()[y-1][z].getType()=='B'){
							
						}else{
							
						}
						break;
					case 'D':
						if(y==map.getCellMap().length-1 || temp.getCellMap()[y+1][z].getType()=='B'){
							
						}else{
							
						}
						//down
						break;
					case 'L':
						if(z==0 || temp.getCellMap()[y][z-1].getType()=='B'){
							
						}else{
							
						}
						//left
						break;
					case 'R':
						if(z==map.getCellMap().length-1 || temp.getCellMap()[y][z+1].getType()=='B'){
							
						}else{
							
						}
						//right
						break;
					}
				}
			}
		}
		return mapSteps;
	}
}
