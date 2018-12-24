package cy.its.service.imageQuery.cfg.dataAccess;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

class OraDiscardedDbError implements IDiscardedDbError {

	Map<Integer, String> Error = new HashMap<Integer, String>();

	OraDiscardedDbError() {
		// 遇到以下错误时数据将会被丢弃
		Error.put(1, "违反唯一约束条件");
		Error.put(67, "值对参数无效；至少必须为");
		Error.put(68, "值对参数无效，必须在和之间");
		Error.put(70, "命令无效");
		Error.put(79, "未找到变量");
		Error.put(93, "必须介于和之间");
		Error.put(94, "要求整数值");
		Error.put(96, "值对参数无效，它必须来自之间");
		Error.put(910, "指定的长度对于数据类型而言过长");
		Error.put(959, "表空间''不存在");
		Error.put(1282, "指定的日期范围无效");
		Error.put(1400, "无法将NULL插入()");
		Error.put(1401, "插入的值对于列过大");
		Error.put(1407, "无法更新()为NULL");
		Error.put(1412, "此数据类型不允许零长度");
		Error.put(1414, "尝试对数组赋值时的无效数组长度");
		Error.put(1428, "参数''超出范围");
		Error.put(1438, "值大于此列指定的允许精确度");
		Error.put(1449, "列包含NULL值；无法将其改变为NOTNULL");
		Error.put(1450, "超出最大的关键字长度()");
		Error.put(1451, "要修改为NULL的列无法修改为NULL");
		Error.put(1458, "内部变量字符串长度非");
		Error.put(1459, "变量字符串长度非法");
		Error.put(1461, "仅可以为插入LONG列的LONG值赋值");
		Error.put(1462, "不能插入超出4000个字符的文字型字符串");
		Error.put(1481, "无效的数字格式模型");
		Error.put(1482, "不受支持的字符集");
		Error.put(1488, "输入数据中的无效半字节或字节");
		Error.put(2017, "要求整数值");
		Error.put(24381, "数组 DML 出错");

	}

	@Override
	public Boolean isDiscardedDataError(Exception e) {
		if (e instanceof SQLException) {
			SQLException sqlException = SQLException.class.cast(e);
			if (sqlException != null) {
				int errorCode = sqlException.getErrorCode();
				return Error.containsKey(errorCode);
			}
		}

		return false;
	}
}