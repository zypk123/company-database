package cy.its.service.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
* @Title: RedisConfig.java 
* @Package cy.its.service.surveillance.util 
* @Description: redis缓存服务连接池
* @author lil@cychina.cn
* @date 2015年10月20日 下午6:34:19 
* @version V1.0   
 */
public class RedisPoolUtil {
	
	   private static  Logger log=Logger.getLogger(RedisPoolUtil.class);
	   //属性值
	   private  static   String  propertity="";
	   //配置文件路径
	   private  static   String  resource="";
	   
	   //过期时间
	   private static int timeOut = 10000;
	   //IP 地址
	   private  static   String  ip;
	   
	   //服务端口号
	   private  static   int    port;
	   //密码
	   private  static   String  pwd;
	   
	   //如果赋值为-1，则表示不限制；如果pool已经分配了MaxTotal个jedis实例，则此时pool的状态为exhausted(耗尽)。  
	   private  static   int  maxTotal;
	   //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
	   private  static   int  maxIdle;
	   //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException； 
	   private  static   int  maxWaitMillis;
	   //当前库实例下标 
	   public  static   int  index;
	   
	   private static JedisPool  pool = null;  
	   
	   private RedisPoolUtil(){}
	   
	   private RedisPoolUtil(String  propertity1,String  resource1){
		   propertity=propertity1;
		   resource=resource1;
		   //初始化，参数信息
		   try {
			  initAllProperties();
			} catch (IOException e) {
				log.error("redis连接池初始化异常，请检查配置文件！");
				e.printStackTrace();
			}
	   }
	   
	   
	 /**
	 * @throws IOException 
	 * @throws FileNotFoundException  
	* @Title: initAllProperties 
	* @Description: 初始化属性信息
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private static  void initAllProperties() throws IOException {
		OrderedProperties prop = new OrderedProperties();//属性集合对象 
		InputStream fis =  new FileInputStream(resource);
		prop.load(fis);// 将属性文件流装载到Properties对象中 
		fis.close();// 关闭流 
		ip = prop.getProperty("redis.ip");
		port = Integer.valueOf(prop.getProperty("redis.port"));
		maxTotal =  Integer.valueOf(prop.getProperty("redis.maxTotal"));
		pwd = prop.getProperty("redis.pwd");
		maxIdle =  Integer.valueOf(prop.getProperty("redis.maxIdle"));
		maxWaitMillis =  Integer.valueOf(prop.getProperty("redis.maxWaitMillis"));
	}
	/** 
	     * 构建redis连接池 
	     *  
	     * @param ip 
	     * @param port 
	     * @return JedisPool 
	     */  
	    public static JedisPool getPool() {  
	        if (pool == null) {  
	            JedisPoolConfig config = new JedisPoolConfig();
	            config.setMaxTotal(10);  
	            config.setMaxIdle(maxIdle);  
	            config.setMaxWaitMillis(maxWaitMillis*100);  
	            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
	            config.setTestOnBorrow(true);  
	            pool = new JedisPool(config, ip, port,timeOut,pwd);  
	        }  
	        return pool;  
	    }  
	    /** 
	     * 返还到连接池 
	     * @param pool  
	     * @param redis 
	     */  
	    public static void returnResource(JedisPool pool, Jedis redis) {  
	        if (redis != null) {  
	            pool.returnResource(redis);  
	        }  
	    } 
	    /** 
	     * 获取数据 
	     * @param key 
	     * @return 
	     */  
	    public static String get(String key){  
	        String value = null;  
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();
	            jedis.flushAll();
	            jedis.select(index);
	            value = jedis.get(key);  
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	        return value;  
	    }  
	    
	    /** 
	     * 获取数据 
	     * @param key 
	     * @return 
	     */  
	    public static long pttl(String key){  
	    	long value = -1;  
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            value= jedis.pttl(key);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	        return value;  
	    }  
	    
	    /** 
	     * 获取数据 
	     * @param key 
	     * @return 
	     */  
	    public static String hget(String key,String filed){  
	        String value = "";  
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            value= jedis.hget(key,filed); 
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	        return value;  
	    }  
	    
	    /** 
	     * @param key 
	     * @return 
	     */  
	    public static void hdel(String key,String filed){  
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	           jedis.hdel(key,filed); 
	        } catch (Exception e) {  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	    }  
	    
	    
	    /** 
	     * 获取数据 
	     * @param key 
	     * @return 
	     */  
	    public static boolean hexists(String key,String filed){  
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            return jedis.hexists(key,filed);  
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	        return false;  
	    }  
	    
	    /** 
	     * 写入数据
	     * @param key 
	     * @return 
	     */  
	    public static void put(String key,String jsonString){  
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            jedis.set(key, jsonString);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	    }
	    
	    /** 
	     * 写入数据
	     * @param key 
	     * @return 
	     */  
	    public static void lpush(String key,String jsonString){  
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            jedis.lpush(key, jsonString);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	    }
	    
	    
	    /** 
	     * 删除数据
	     * @param key 
	     * @return 
	     */  
	    public static void lrem(String key,int index,String value){  
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            jedis.lrem(key, index, value);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	    }
	    
	    /** 
	    * @Title: lranage 
	    * @Description:  取LIST数据
	    * @param @param key
	    * @param @param firstIndex
	    * @param @param lastIndex
	    * @param @return    设定文件 
	    * @return List<String>    返回类型 
	    * @throws 
	    */
	    public static List<String> lranage(String key,int firstIndex,int lastIndex){  
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        List<String>  str  = null;
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            str = jedis.lrange(key,firstIndex,lastIndex);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	        return str;
	    }
	    
	    
	    /** 
	    * @Title: expire 
	    * @Description: 给某一个KEY设置过期时间
	    * @param @param key
	    * @param @param seconds    设定文件 
	    * @return void    返回类型 
	    * @throws 
	    */
	    public static void expire(String key,int seconds){ 
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            jedis.expire(key,seconds);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	    }
	    
	    /** 
	    * @Title: keys 
	    * @Description: 模糊搜素
	    * @param @param pattern
	    * @param @return    设定文件 
	    * @return Set<String>    返回类型 
	    * @throws 
	    */
	    public static Set<String> keys(String pattern){ 
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        Set<String> sets =  null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            sets = jedis.keys(pattern);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        } 
	        return sets;
	    }
	    
	    
	    /** 
	    * @Title: putExpire 
	    * @Description: 写入带过期时间的数据 
	    * @param @param key
	    * @param @param jsonString
	    * @param @param seconds 时间单位秒 
	    * @return void    返回类型 
	    * @throws 
	    */
	    public static void putExpire(String key,String jsonString,int seconds){ 
	        JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            jedis.set(key, jsonString);
	            jedis.expire(key,seconds);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	    }

		/**
		 * @return  
		* @Title: exists 
		* @Description: 判定库中是否存在传入KEY 
		* @param @param string    设定文件 
		* @return void    返回类型 
		* @throws 
		*/
		public static Boolean exists(String key) {
			JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            return jedis.exists(key);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }  
	        return false;
		}

		/** 
		* @Title: hset 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param dataKey
		* @param @param filed    设定文件 
		* @return void    返回类型 
		* @throws 
		*/
		public static void hset(String key, String filed,String value) {
			 JedisPool pool = null;  
		        Jedis jedis = null;  
		        try {  
		            pool = getPool();  
		            jedis = pool.getResource();  
		            jedis.select(index);
		            jedis.hset(key,filed, value);
		        } catch (Exception e) {  
		            //释放redis对象  
		        	log.error("释放redis对象！");
		            pool.returnBrokenResource(jedis);  
		            e.printStackTrace();  
		        } finally {  
		            //返还到连接池  
		            returnResource(pool, jedis);  
		        }  
		}
		
		/** 
		* @Title: hkeys 
		* @Description: map操作
		* @param @param key    设定文件 
		* @return void    返回类型 
		* @throws 
		*/
		public static Set<String> hkeys(String key) {
			JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            return jedis.hkeys(key);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }
			return null;  
		}

		/** 
		* @Title: del 
		* @Description: 根据传入的key 删除数据信息
		* @param @param key    设定文件 
		* @return void    返回类型 
		* @throws 
		*/
		public static void del(String key) {
			JedisPool pool = null;  
	        Jedis jedis = null;  
	        try {  
	            pool = getPool();  
	            jedis = pool.getResource();  
	            jedis.select(index);
	            jedis.del(key);
	        } catch (Exception e) {  
	            //释放redis对象  
	        	log.error("释放redis对象！");
	            pool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
	        } finally {  
	            //返还到连接池  
	            returnResource(pool, jedis);  
	        }
		}  
}