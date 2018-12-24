package cy.its.service.util;

public class StringUtils {
	
	public static  boolean  isEmpty(String  value){
		if(value == null || "".equals(value)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean  isNotEmpty(String  value){
		if(value != null && !"".equals(value)){
			return true;
		}else{
			return false;
		}
	}
}
