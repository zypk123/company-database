package com.huntkey.rx.sceo.serviceCenter.common.model;

import java.util.Collection;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.common.emun.OperatorType;

/**
 * 查询对象 Created by chenxiaojun on 2017/8/10.
 */
public class SearchParam extends JSONObject implements Param {
	private static final long serialVersionUID = 5893811486061638516L;

	public SearchParam(String edmName) {
		this.put(NodeConstant.EDMNAME, edmName);
		this.put(NodeConstant.SEARCH, new JSONObject());
	}

	/**
	 * 添加 null 条件
	 *
	 * @param key
	 * @return
	 */
	public SearchParam addCond_isNull(String key) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.IsNull, "null"));

		return this;
	}

	/**
	 * 添加 非 null 条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addCond_notNull(String key) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.NotNull, "null"));

		return this;
	}

	/**
	 * 添加 大于 条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addCond_greater(String key, String value) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		if (StringUtil.isNullOrEmpty(value)) {
			throw new IllegalArgumentException("condition's value is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.Greater, value));

		return this;
	}

	/**
	 * 添加 小于 条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addCond_less(String key, String value) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		if (StringUtil.isNullOrEmpty(value)) {
			throw new IllegalArgumentException("condition's value is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.Less, value));

		return this;
	}

	/**
	 * 添加 等于 条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addCond_equals(String key, String value) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.Equals, value));

		return this;
	}

	/**
	 * 添加 大于等于 条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addCond_greaterOrEquals(String key, String value) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		if (StringUtil.isNullOrEmpty(value)) {
			throw new IllegalArgumentException("condition's value is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.GreaterEquals, value));

		return this;
	}

	/**
	 * 添加 小于等于 条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addCond_lessOrEquals(String key, String value) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		if (StringUtil.isNullOrEmpty(value)) {
			throw new IllegalArgumentException("condition's value is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.LessEquals, value));

		return this;
	}

	/**
	 * 添加 like 条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addCond_like(String key, String value) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		if (StringUtil.isNullOrEmpty(value)) {
			throw new IllegalArgumentException("condition's value is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.Like, value));

		return this;
	}

	/**
	 * 添加 in 条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addCond_in(String key, String value) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		if (StringUtil.isNullOrEmpty(value)) {
			throw new IllegalArgumentException("condition's value is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.In, value));

		return this;
	}

	/**
	 * 添加 not in 条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addCond_notIn(String key, String value) {
		if (StringUtil.isNullOrEmpty(key)) {
			throw new IllegalArgumentException("condition's key is not allowed as empty!");
		}

		if (StringUtil.isNullOrEmpty(value)) {
			throw new IllegalArgumentException("condition's value is not allowed as empty!");
		}

		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(new ConditionNode(key, OperatorType.NotIn, value));

		return this;
	}

	/**
	 * 添加排序条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addConditions(Collection<ConditionNode> conditions) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).addAll(conditions);

		return this;
	}

	/**
	 *
	 * @param condition
	 * @return
	 */
	public SearchParam addCondition(ConditionNode condition) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.CONDITIONS)) {
			search.put(NodeConstant.CONDITIONS, new JSONArray());
		}
		search.getJSONArray(NodeConstant.CONDITIONS).add(condition);

		return this;
	}

	/**
	 * 清除所有查询条件
	 *
	 * @return
	 */
	public SearchParam clearConditions() {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (search.containsKey(NodeConstant.CONDITIONS)) {
			search.remove(NodeConstant.CONDITIONS);
		}

		return this;
	}


	/**
	 * 添加查询单个列属性
	 *
	 * @param column 添加单个属性字段
	 * @return
	 */
	public SearchParam  addColumn(String column) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		String columns = search.getString(NodeConstant.COLUMNS);
		if (columns == null || columns.isEmpty()){
			columns = column;
		}else{
			columns = columns  + "," + column;
		}
		search.put(NodeConstant.COLUMNS, columns);
		return this;
	}
	/**
	 * 添加查询多个列属性,重复操作会替换掉原有的
	 *
	 * @param columns 多个字段
	 * @return
	 */
	public SearchParam addColumns(String columns) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.COLUMNS, columns.split(","));
		return this;
	}

	/**
	 * 添加查询列属性
	 *
	 * @param columns
	 * @return
	 */
	public SearchParam addColumns(Collection<String> columns) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.COLUMNS, columns);

		return this;
	}

	/**
	 * 添加查询列属性（多个）
	 *
	 * @param columns
	 * @return
	 */
	public SearchParam addColumns(String[] columns) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.COLUMNS, columns);

		return this;
	}



	/**
	 * 添加排序条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addSortParam(Collection<SortNode> sortParams) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.ORDERBY)) {
			search.put(NodeConstant.ORDERBY, new JSONArray());
		}
		search.getJSONArray(NodeConstant.ORDERBY).addAll(sortParams);

		return this;
	}

	/**
	 * 添加排序条件
	 *
	 * @param sortParam
	 * @return
	 */
	public SearchParam addSortParam(SortNode sortParam) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		if (!search.containsKey(NodeConstant.ORDERBY)) {
			search.put(NodeConstant.ORDERBY, new JSONArray());
		}
		search.getJSONArray(NodeConstant.ORDERBY).add(sortParam);

		return this;
	}

	/**
	 * 增加分页参数
	 *
	 * @param pagenation
	 * @return
	 */
	public SearchParam addPagenation(PagenationNode pagenation) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.PAGENATION, pagenation);

		return this;
	}

	/**
	 * 增加分页参数
	 *
	 * @param startPage:
	 *            起始页
	 * @param rows：每页返回的数据行数
	 * @return
	 */
	public SearchParam addPagenation(int startPage, int rows) {
		PagenationNode pagenation = new PagenationNode(startPage, rows);

		return addPagenation(pagenation);
	}

	@Override
	public String getEdmName() {
		return this.getString(NodeConstant.EDMNAME);
	}

	@Override
	public JSONObject getSearch() {
		return this.getJSONObject(NodeConstant.SEARCH);
	}

	@Override
	public InputArgument build() {
		return new FullInputArgument(this);
	}
}
