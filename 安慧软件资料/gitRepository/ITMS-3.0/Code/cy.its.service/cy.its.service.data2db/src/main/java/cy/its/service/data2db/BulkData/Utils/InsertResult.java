package cy.its.service.data2db.BulkData.Utils;

import java.util.ArrayList;
import java.util.List;

public class InsertResult {
	public int succesCount = 0;
	public int discardCount = 0;
	public List<String> avaliableList;
	
	public InsertResult(int capcity){
		avaliableList = new ArrayList<String>(capcity);
	}
	
	public void increase(){
		succesCount += 1;
	}
	
	public void increaseDiscard(){
		discardCount += 1;
	}
	
	public void add(int count){
		succesCount += count;
	}
	
	public void reset(){
		succesCount = 0;
		avaliableList.clear();
	}
}

