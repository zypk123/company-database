package cy.its.sysCfg.rest.dto;
//创建一个结果集对象
public class DataGridResult<T> {
	
	public String error;
	private Results<T> result;
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	public Results<T> getResult() {
		return result;
	}
	public void setResult(Results<T> res) {
		this.result = res;
	}
	
	
}
