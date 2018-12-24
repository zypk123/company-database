package cy.its.service.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DBUtil {
	
   private static  Logger log=Logger.getLogger(RedisPoolUtil.class);
   //属性值
   private  static   String  propertity="";	
   
   //配置文件路径
   private  static   String  resource="";
	//私有构造器
   private DBUtil(){
	   
   }
	//数据库连接池
	private static DataSource ds = null;  
    
    private  DBUtil(String propertity1,String  resource1){
    	propertity = propertity1;
    	resource = resource1;
        try{
            InputStream in =  new FileInputStream(resource);
            Properties props = new Properties();
            props.load(in);
            ds = new DataSource();
            ds.setPoolProperties(getNewProperties(props));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /** 
    * @Title: getNewProperties 
    * @Description:获取新的配置文件信息
    * @param @param props
    * @param @return    设定文件 
    * @return Properties    返回类型 
    * @throws 
    */
    private PoolProperties getNewProperties(Properties props) {
    	 PoolProperties p = new PoolProperties();
         p.setUrl(props.getProperty("jdbc_url"));
         p.setDriverClassName("oracle.jdbc.driver.OracleDriver");
         p.setUsername(props.getProperty("jdbc_user"));
         p.setPassword(props.getProperty("jdbc_pwd"));
         p.setJmxEnabled(true);
         p.setTestWhileIdle(false);
         p.setTestOnBorrow(true);
         p.setMaxIdle(2);
         p.setValidationQuery(" select 1 from dual ");
         p.setTestOnReturn(false);
         p.setValidationInterval(30000);
         p.setTimeBetweenEvictionRunsMillis(30000);
         p.setMaxActive(2);
         p.setInitialSize(1);
         p.setMaxWait(10000);
         p.setRemoveAbandonedTimeout(60);
         p.setMinEvictableIdleTimeMillis(30000);
         p.setMinIdle(1);
         p.setLogAbandoned(true);
         p.setRemoveAbandoned(true);
         p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                 + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		 return p;
	}

	public static Connection openConnection() throws SQLException{
        return ds.getConnection();
    }   
    
    /**
    * @Title: getDataSource 
    * @Description:获取数据库连接池
    * @param @return
    * @param @throws SQLException    设定文件 
    * @return DataSource    返回类型 
    * @throws
     */
    public static DataSource getDataSource() throws SQLException{
        return ds;
    }   
    
    /** 
    * @Title: insertData 
    * @Description: 插入数据
    * @param @param sql    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public static void updateData(String sql){
    	Connection conn = null; //表示数据库的连接对象  
	    Statement  stmt = null;   
		try {
			conn=openConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
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
    }
    /** 
    * @Title: insertData 
    * @Description: 插入数据
    * @param @param sql    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public static void insertData(String sql){
    	Connection conn = null; //表示数据库的连接对象  
	    Statement  stmt = null;   
		try {
			conn=openConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
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
    }
    
     /** 
    * @Title: executeBatchArryData 
    * @Description: 根据出入的数组信息批量插入数据
    * @param @param sql
    * @param @param params  [{Object,java.sql.Types.类型}]
    * @return void    返回类型 
    * @throws 
    */
    public static void executeBatchArryData(String sql,Object[]...params){
    	int  size  = params[0].length;
     	Connection conn = null; //表示数据库的连接对象  
     	PreparedStatement  stmt = null;   
 		try {
 			conn=openConnection();
 			stmt = conn.prepareStatement(sql);
 			//提交数据条数
 			for(int s =0;s<size;s++){
 		        //需要传参数??
 				for(int i=0;i<params.length;i++){
 					Object[]   temp = (Object[]) params[i][s];
 					stmt.setObject(i+1, temp[0],(int)temp[1]);
 				}
 				stmt.addBatch();
 			}
 			stmt.executeBatch();
 			stmt.close();
 			conn.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}finally{
 			try {
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
     }
    
    /** 
     * @Title: insertData 
     * @Description: 插入数据
     * @param @param sql    设定文件 
     * @return void    返回类型 
     * @throws 
     */
     public static void executeBatchData(List<String> sqls){
     	Connection conn = null; //表示数据库的连接对象  
 	    Statement  stmt = null;   
 		try {
 			conn=openConnection();
 			conn.setAutoCommit(false);
 			stmt = conn.createStatement();
 			for(String  sql:sqls){
 				stmt.addBatch(sql);
 			}
 			stmt.executeBatch();
 			conn.commit();
 			stmt.close();
 			conn.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}finally{
 			try {
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
     }
    /** 
    * @Title: queryData 
    * @Description:根据传入的SQL获取JSON数据 
    * @param @param sql
    * @param @return    设定文件 
    * @return JSONArray    返回类型 
    * @throws 
    */
    public static JSONArray queryJSONData(String  sql){
    	JSONArray  list =null;
		Connection conn = null; //表示数据库的连接对象  
	    Statement  stmt = null;   
	    ResultSet  rs = null; //表示接收数据库的查询结果
		try {
			conn=openConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			list = createJSONArray(rs);
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
    * @Title: createJSONArray 
    * @Description: 创建JSON集合 
    * @param @param rs
    * @param @return    设定文件 
    * @return JSONArray    返回类型 
    * @throws 
    */
    private static JSONArray createJSONArray(ResultSet rs) throws SQLException {
    	JSONArray list  = new JSONArray();
		while(rs.next()){
			JSONObject jsonObject  = new JSONObject();
	        try{
	            int  length = rs.getMetaData().getColumnCount();
	            for(int i=0;i<length;i++){
	            	String  key  = rs.getMetaData().getColumnName(i+1);
	            	String  classType = rs.getMetaData().getColumnClassName(i+1);
	            	jsonObject.put(key,Class.forName(classType).cast(rs.getObject(i+1)));
	            }
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        list.add(jsonObject);
		}
		return list;
	}

	/** 
	* @Title: queryData 
	* @Description: 根据传入的接口获取Object对象
	* @param @param sql
	* @param @param className
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws 
	*/
	public static List queryData(String sql,Class className) {
		List  list =null;
		Connection conn = null; //表示数据库的连接对象  
	    Statement  stmt = null;   
	    ResultSet  rs = null; //表示接收数据库的查询结果
		try {
			conn=openConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			list = createList(rs,className);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			log.info("执行SQL出现异常，SQL:"+sql);
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
	 * @throws ClassNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException  
	* @Title: createList 
	* @Description: 封装LIST OBJ
	* @param @param rs
	* @param @param string
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws 
	*/
	private static List createList(ResultSet rs, Class classType) throws SQLException{
		List list  = new ArrayList();
		Method[]  methods  = classType.getDeclaredMethods();
		while(rs.next()){
		    Object objInstance=null;
	        try{
	        	objInstance=classType.newInstance();
	        	for(Method method:methods){
	        		String methodName  = method.getName();
	        		if(methodName.startsWith("set")){
	        			//以set开头的方法都需要执行
	        			//要获取数据库的字段信息以删除SET字符，第一个字母小写
	        			String  fileld = methodName.replace("set", "");
	        			fileld =fileld.toUpperCase().substring(0, 1)+fileld.substring(1, fileld.length());
	        			try{
	        				method.invoke(objInstance,new Object[]{(method.getParameterTypes()[0]).cast(rs.getObject(fileld))});
	        			}catch(Exception e){
	        				log.info(methodName+"赋值 失败，程序不做处理！");
	        			}
	        		}
	        		
	        	}
	            list.add(objInstance);
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		return list;
	}

	/** 
	* @Title: execute 
	* @Description: 单条执行update,delete
	* @param @param delteSql    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void execute(String delteSql) {
		Connection conn = null; //表示数据库的连接对象  
 	    Statement  stmt = null;   
 		try {
 			conn=openConnection();
 			stmt = conn.createStatement();
 			stmt.execute(delteSql);
 			stmt.close();
 			conn.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}finally{
 			try {
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
	}
}