package cy.its.service.common;

public class Container<T> {
	
	private T t;
	
	public static <T> Container<T> create(){
		return new  Container<T>();
	}
	
	private Container(){
		
	}
	
	public T get(){
		return t;
	}
	
	public void set(T t){
		this.t = t;
	}
}
