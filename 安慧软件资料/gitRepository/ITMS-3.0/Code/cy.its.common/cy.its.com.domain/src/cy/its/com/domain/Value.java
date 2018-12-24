package cy.its.com.domain;

import cy.its.com.util.StringUtil;

public abstract class Value  {
	protected abstract boolean IsEmpty();
	
	public void NotNull(String valueName) throws Exception{
		if(IsEmpty()){
			throw new Exception(valueName + "²»¿ÉÎª¿Õ"); 
		}
	}
	
	protected boolean stringIsEmpty(String str) {
		return StringUtil.isNullOrEmpty(str);
	}
}
