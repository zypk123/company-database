package cy.its.device.rest.dto;

import cy.its.device.rest.dto.Results;

/**
 * 页面查询返回值对象(可以提取到common中，除了过车等大数据量的查询不适合外，其他所有查询都采用这种返回值)
 * 
 * @author chenzhiying 2015.09.21
 *
 * @param <T>各个页面查询结果DTO对象
 */
public class DataGridResult<T> {

	private String erro;

	/**
	 * get查询结果错误信息
	 * 
	 * @return
	 */
	public String getErro() {
		return erro;
	}

	/**
	 * set查询结果错误信息
	 * 
	 * @param erro
	 */
	public void setErro(String erro) {
		this.erro = erro;
	}

	private Results<T> result;

	/**
	 * get查询结果列表
	 * 
	 * @return
	 */
	public Results<T> getResult() {
		return result;
	}

	/**
	 * set查询结果
	 * 
	 * @param result
	 */
	public void setResult(Results<T> result) {
		this.result = result;
	}

}
