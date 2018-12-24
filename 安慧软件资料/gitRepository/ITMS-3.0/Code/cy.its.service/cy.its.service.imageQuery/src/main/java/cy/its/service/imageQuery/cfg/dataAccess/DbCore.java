package cy.its.service.imageQuery.cfg.dataAccess;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

class DbCore {

	public static void executeReader(Connection conn, Consumer<ResultSet> consumer, String sql, Object... parameters)
			throws Exception {
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			ps = conn.prepareStatement(sql);
			setParameters(ps, parameters);
			result = ps.executeQuery();
			if (consumer != null) {
				while (result.next()) {
					consumer.accept(result);
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close(result);
			close(ps);
		}
	}

	public static <T> List<T> executeDataset(Connection conn, Class<T> cls, String sql, Object... parameters)
			throws Exception {
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			ps = conn.prepareStatement(sql);
			setParameters(ps, parameters);
			result = ps.executeQuery();
			List<Field> fs = getMapFields(cls);
			List<T> lst = new ArrayList<T>();
			while (result.next()) {
				lst.add(getValue(cls, result, fs));
			}
			return lst;

		} catch (Exception e) {
			throw e;
		} finally {
			close(result);
			close(ps);
		}
	}

	public static Object executeScalar(Connection conn, String sql, Object... parameters) throws Exception {
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			ps = conn.prepareStatement(sql);
			setParameters(ps, parameters);
			result = ps.executeQuery();
			while (result.next()) {
				return result.getObject(1);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close(result);
			close(ps);
		}

		return null;
	}

	public static int executeNonQuery(Connection conn, String sql, Object... parameters)
			throws Exception, SQLException {
		PreparedStatement ps = null;
		int result;
		try {
			ps = conn.prepareStatement(sql);
			setParameters(ps, parameters);
			result = ps.executeUpdate(sql);
			ps.close();
		} catch (Exception e) {
			throw e;
		} finally {
			close(ps);
		}

		return result;
	}

	public static void batchNonQuery(Connection conn, String sql, List<List<Object>> paramsList) throws Exception {
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			if (paramsList != null && paramsList.size() > 0) {
				for (List<Object> params : paramsList) {
					setParameters(ps, params);
					ps.addBatch();
				}
			}

			ps.executeBatch();
			conn.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			close(ps);
		}
	}

	public static void singleNonQuery(Connection conn, String sql, List<Object> params) throws Exception {
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			if (params != null) {
				setParameters(ps, params);
				ps.addBatch();
			}

			ps.executeBatch();
			conn.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			close(ps);
		}
	}

	private static void close(PreparedStatement ps) throws SQLException {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e1) {
				throw e1;
			}
		}
	}

	private static void close(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void setParameters(PreparedStatement ps, Object... parameters) throws SQLException {
		if (parameters != null && parameters.length > 0) {
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] instanceof Date) {
					ps.setObject(i + 1, new java.sql.Timestamp(((Date) parameters[i]).getTime()));
				} else {
					ps.setObject(i + 1, parameters[i]);
				}
			}
		}
	}
	
	private static void setParameters(PreparedStatement ps, List<Object> params) throws SQLException {
		if (params != null && params.size() > 0) {
			Object obj;
			for (int i = 0; i < params.size(); i++) {
				obj = params.get(i);
				if (obj instanceof Date) {
					ps.setObject(i + 1, new java.sql.Timestamp(((Date) obj).getTime()));
				} else {
					ps.setObject(i + 1, obj);
				}
			}
		}
	}

	private static <T> T getValue(Class<T> cls, ResultSet result, List<Field> fs)
			throws InstantiationException, IllegalAccessException {
		T t = cls.newInstance();
		for (Field field : fs) {
			try {
				if (field.getType() == Date.class) {
					Date d = result.getTimestamp(field.getName());
					field.set(t, d);
				} else {
					field.set(t, result.getObject(field.getName()));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	@SuppressWarnings("rawtypes")
	static HashMap<Class, List<Field>> classFields = new HashMap<Class, List<Field>>();

	private static <T> List<Field> getMapFields(Class<T> clz) {
		if (classFields.containsKey(clz)) {
			return classFields.get(clz);
		}

		Field[] fs = clz.getDeclaredFields();
		List<Field> m = new ArrayList<Field>(fs.length);

		for (Field field : clz.getDeclaredFields()) {
			if (field.isAnnotationPresent(MapColumn.class)) {
				field.setAccessible(true);
				m.add(field);
			}
		}

		classFields.put(clz, m);

		return m;
	}
}
