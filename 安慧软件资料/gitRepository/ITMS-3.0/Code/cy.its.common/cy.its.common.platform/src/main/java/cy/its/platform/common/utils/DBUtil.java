package cy.its.platform.common.utils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
* @Title: DBUtil.java 
* @Package com.cy.utils 
* @Description: JDBC操作类 
* @author lil@cychina.cn
* @date 2015年8月19日 下午7:18:09 
* @version V1.0   
 */
public class DBUtil {

	/** 
	* @Title: queryData 
	* @Description: 根据传入的SQL获取数据 
	* @param @param sql
	* @param @param dataSource
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws 
	*/
	public static List queryData(String sql, DataSource dataSource) {
		List  list =null;
		Connection conn = null; //表示数据库的连接对象  
	    Statement  stmt = null;   
	    ResultSet  rs = null; //表示接收数据库的查询结果
		try {
			conn=openConnect(dataSource);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			list = createList(rs,"com.cy.cache.domain.CacheDao");
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close(); //出现异常关闭链接
				}
				if(stmt!=null){
					stmt.close();//出现异常关闭链接
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list;
	}


	/**
	 * @throws SQLException  
	* @Title: createList 
	* @Description: 封装LIST OBJ
	* @param @param rs
	* @param @param string
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws 
	*/
	private static List createList(ResultSet rs, String className) throws SQLException {
		List list  = new ArrayList();
		while(rs.next()){
		    Class<?> object=null;
		    Object objInstance=null;
	        try{
	        	object=Class.forName(className);
	        	objInstance=object.newInstance();
	        	Method setMethod =	 object.getDeclaredMethod("setProperty",new Class[]{String[].class});
	        	if(rs.getMetaData().getColumnCount()==3){
	        		String[]  args = new String[3];
	        		args[0] =rs.getString(1);
	        		args[1] =rs.getString(2);
	        		args[2] =rs.getString(3);
	        		setMethod.invoke(objInstance,new Object[]{args});
	        	}else {
	        		String[]  args = new String[2];
	        		args[0] =rs.getString(1);
	        		args[1] =rs.getString(2);
	        		setMethod.invoke(objInstance, new Object[]{args});
				}
	            list.add(objInstance);
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		return list;
	}


	/**
	 * @throws SQLException  
	* @Title: openConnect 
	* @Description: 打开数据库链接 
	* @param @param dataSource
	* @param @return    设定文件 
	* @return Connection    返回类型 
	* @throws 
	*/
	private static Connection openConnect(DataSource dataSource) throws SQLException {
		// TODO Auto-generated method stub
		return dataSource.getConnection();
	}

}
