package cy.its.service.imageQuery.cfg.dataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.function.Consumer;

public final class DbExecuter {

	/**
	 * 执行增删改操作
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static int executeNonQuery(String sql, Object... parameters) throws Exception {
		Connection conn = null;
		try {
			conn = ConnPool.getConnection();
			return DbCore.executeNonQuery(conn, sql, parameters);
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 列表查询
	 * 
	 * @param consumer
	 * @param sql
	 * @param parameters
	 * @throws Exception
	 */
	public static void executeReader(Consumer<ResultSet> consumer, String sql, Object... parameters) throws Exception {
		Connection conn = null;
		try {
			conn = ConnPool.getConnection();
			DbCore.executeReader(conn, consumer, sql, parameters);
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 列表查询
	 * 
	 * @param cls
	 * @param sql
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> executeDataset(Class<T> cls, String sql, Object... parameters) throws Exception {
		Connection conn = null;
		try {
			conn = ConnPool.getConnection();
			return DbCore.executeDataset(conn, cls, sql, parameters);
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 查询单值
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static Object executeScalar(String sql, Object... parameters) throws Exception {
		Connection conn = null;
		try {
			conn = ConnPool.getConnection();
			return DbCore.executeScalar(conn, sql, parameters);
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 批量处理
	 * 
	 * @param sql
	 * @param paramsList
	 * @throws Exception
	 */
	public static void batchInsert(String sql, List<List<Object>> paramsList) throws Exception {
		Connection conn = null;
		try {
			conn = ConnPool.getConnection();
			DbCore.batchNonQuery(conn, sql, paramsList);
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void singleInsert(String sql, List<Object> params) throws Exception {
		Connection conn = null;
		try {
			conn = ConnPool.getConnection();
			DbCore.singleNonQuery(conn, sql, params);
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 自定义处理:一次连接多次访问数据库
	 * 
	 * @param custom
	 * @throws Exception
	 */
	public static void customNonQuerys(Action<DbAccess> custom) throws Exception {
		DbAccess dbAccess = null;
		try {
			dbAccess = new DbAccess();
			custom.accept(dbAccess);
		} catch (Exception e) {
			throw e;
		} finally {
			if (dbAccess != null) {
				dbAccess.close();
			}
		}
	}

	/**
	 * 自定义处理:一次连接多次处理,且在同一事务里
	 * 
	 * @param sql
	 * @param paramsList
	 * @throws Exception
	 */
	public static void customTransNonQuerys(Action<DbAccess> custom) throws Exception {
		DbAccess dbAccess = null;
		try {
			dbAccess = new DbAccess();
			dbAccess.beginTransaction();
			custom.accept(dbAccess);
			dbAccess.commit();
		} catch (Exception e) {
			if (dbAccess != null) {
				dbAccess.rollBack();
			}
			throw e;
		} finally {
			if (dbAccess != null) {
				dbAccess.close();
			}
		}
	}
}
