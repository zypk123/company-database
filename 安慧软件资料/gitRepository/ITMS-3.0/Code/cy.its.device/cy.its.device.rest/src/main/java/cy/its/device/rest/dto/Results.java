package cy.its.device.rest.dto;

import java.util.List;

/**
 * 页面查询结果对象，包括总条数、具体结果列表(可以提取到common中，除了过车等大数据量的查询不适合外，其他所有查询都采用这种返回值)
 * 
 * @author chenzhiying 2015.09.21
 *
 * @param <T>各个页面查询结果DTO对象
 */
public class Results<T> {

	private long total;
	private List<T> rows;

	/**
	 * get查询总条数
	 * @return
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * set查询总条数
	 * @param total
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * get查询结果列表
	 * @return
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * set查询结果列表
	 * @param rows
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
