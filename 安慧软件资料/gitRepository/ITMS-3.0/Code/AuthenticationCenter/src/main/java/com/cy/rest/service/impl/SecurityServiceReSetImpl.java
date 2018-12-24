package com.cy.rest.service.impl;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cy.cache.domain.CacheDao;
import com.cy.cache.service.CacheServiceI;
import com.cy.rest.service.SecurityServiceReSetI;
import com.cy.security.domain.SecurityDao;
import com.cy.security.service.SecurityServiceI;
import com.cy.security.service.impl.SecurityServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cy.its.platform.common.utils.Log4jFactoryProxy;


/**
* @Title: SecurityServiceReSetImpl.java 
* @Package com.cy.rest.service.impl 
* @Description: 主要处理用户相关验证和统计
* @author lil@cychina.cn
* @date 2015年12月7日 下午3:46:37 
* @version V1.0   
 */
@RestController
@RequestMapping("/authentication")
public class SecurityServiceReSetImpl implements SecurityServiceReSetI {
	/*
	 * 日志代理
	 */
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(SecurityServiceImpl.class);
	
	/**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用securityMapper时，Spring就会自动注入securityMapper
     */
    @Autowired
    private SecurityServiceI securityServiceImpl;//注入dao
    
    @Autowired
    private CacheServiceI cacheServiceImpl;
    
    private DateFormat format= new SimpleDateFormat("yyyyMMddHHmmss");  
    
    /*
     * JSON工具包
     */
    private ObjectMapper  mapper  = new ObjectMapper();
    
    @RequestMapping("/getPwdByStaffCode") 
	public List<SecurityDao> getPwdByStaffCode(@RequestParam(value = "name") String name) {
		 return securityServiceImpl.getPwdByStaffCode(name.toUpperCase());
	}
    
	/* (non-Javadoc)
	 * @see com.cy.reset.service.SecurityServiceReSetI#validatelogin(java.lang.String) 
	 * 验证用户登录，验证通过分配TOOKIN，验证失败重新登陆
	 */
	@RequestMapping("/validatelogin") 
	public Map validatelogin(@RequestParam(value = "paramInfo") String paramInfo) {
		Map  map  =  new HashMap();
		Map  paramMap=null;
		try {
			paramMap = mapper.readValue(paramInfo, Map.class);
			List<SecurityDao> listdao  = securityServiceImpl.getPwdByStaffCode(paramMap.get("username").toString().toUpperCase());
			if(listdao.size() > 0){
				String pwd = paramMap.get("password").toString();
				SecurityDao   dao  = listdao.get(0);
				if(dao.getQueryValue2().equals(pwd)){
					//写入缓存
					String  key= UUID.randomUUID().toString().replaceAll("-", "");//生成唯一码
					Map object   = new  HashMap();
					object.put("date",format.format(new Date()));//放入时间
					//objMap.put("role",paramMap.get("role")==null?"":paramMap.get("role").toString());
					object.put("loginName",dao.getQueryValue1());//用户登录信息
					object.put("userName",dao.getQueryValue5());//用户登录信息
					object.put("orgCode",dao.getQueryValue3());//用户机构
					object.put("orgPrivCode",dao.getQueryValue4());//用户机构
					object.put("orgId",dao.getQueryValue6());//用户机构
					object.put("userId",dao.getQueryValue7());//用户ID
					//存入缓存
					cacheServiceImpl.addMemeryCache("VU:"+key,JSONObject.toJSONString(object),10*60);//存入缓存
					//2成功标志
					map.put("status","2");
					map.put("info",key);
					map.put("data",object);
				}else{
					map.put("status","0");
					map.put("info","登陆密码错误！");
				}
			}else{
				map.put("status","0");
				map.put("info","输入用户不存在！");
			}
		} catch (JsonParseException e) {
			log.error(e);
		} catch (JsonMappingException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return map;
	}
	
	
	@RequestMapping("/getAllRoleByStaffCode") 
	public List<SecurityDao> getAllRoleByStaffCode(@RequestParam(value = "name") String name) {
		 return securityServiceImpl.getAllRoleByStaffCode(name.toUpperCase());
	}
	
	@Override
	@RequestMapping("/authorityService") 
	public Map authorityService(@RequestParam(value = "paramInfo") String paramInfo) {
		Map  map  =  new HashMap();
		Map  paramMap=null;
		try {
			paramMap = mapper.readValue(paramInfo,Map.class);
			String key = paramMap.get("sessionId")==null?"":paramMap.get("sessionId").toString();
			String role = paramMap.get("role")==null?"":paramMap.get("role").toString();
			String serviceName = paramMap.get("serviceName")==null?"":paramMap.get("serviceName").toString();
		    if(role != null){
				List<String> list2  = (List<String>) cacheServiceImpl.getMemeryCacheByKey(role);
				long count=list2.stream().filter(p->p.equals(serviceName)).count();//截取URL长度
				if(count >0){
					//可以访问
					map.put("status", "2");
					map.put("info", "通过验证！");
				}else{
					map.put("status","1");
					map.put("info", "该角色没有访问服务权限！");
				}
		    }else{
		    	 map.put("status","1");
		    	 map.put("info", "该用户目前没角色信息！");
		    }
		} catch (JsonParseException e) {
			log.error(e);
		} catch (JsonMappingException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return map;
	}


	/**
	 * @param string
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * 根据客户端传入的 sesssionId,判定当前状态是否有效
	 * @throws ParseException 
	 */
	private boolean validateStaffStatus(String sessionId) throws JsonParseException, JsonMappingException, IOException, ParseException {
		Object  obj  = cacheServiceImpl.getMemeryCacheByKey("VU:"+sessionId);
		if(obj != null ){
			return true;
		}else{
		    return  false;
		}
	}

	@Override
	@RequestMapping("/reFreshToken") 
	public Map reFreshToken(@RequestParam(value = "sessionId") String sessionId) {
	    Map map = new HashMap<>();
		if(sessionId == null){
			map.put("status", "0");
			map.put("info", "刷新失败！");
		}else{
			Map objMap;
			try {
				if(cacheServiceImpl.getMemeryCacheByKey("VU:"+sessionId) == null){//token被清除不能刷新
					map.put("status", "1");
					map.put("info", "登录已过期！");
				}else{
					cacheServiceImpl.freshExpoireKey("VU:"+sessionId,10*60);
					map.put("status", "2");
					map.put("info", "刷新成功！");
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
		return map;
	}
	
	/** 
	* @Title: loginOut 
	* @Description:用户登出服务
	* @param @param token
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	@Override
	@RequestMapping("/loginOut") 
	public Map loginOut(@RequestParam(value = "sessionId") String sessionId) {
		Map map = new HashMap<>();
		if(sessionId == null){
			map.put("status", "1");
			map.put("info", "已经注销，无需处理！");
		}else{
			cacheServiceImpl.deleteMemeryCache("VU:"+sessionId);
			map.put("status", "2");
			map.put("info", "注销成功！");
		}
		return map;
	}

	/** 
	* @Title: onlineUsers 
	* @Description: 统计在线用户数
	* @param @param paramInfo
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	@Override
	@RequestMapping("/onlineUsers") 
	public Map onlineUsers() {
		Map map = new HashMap<>();
		int count  = cacheServiceImpl.getAllMemeryCacheKeys("VU:");
		map.put("status", "2");
		map.put("usersCount",count);
		map.put("info", "sucess！");
		return map;
	}
	
	@Override
	@RequestMapping("/validateFrom3") 
	public Map validateFrom3(@RequestParam(value = "paramInfo") String paramInfo) {
		Map  map  =  new HashMap();
		Map  paramMap=null;
		try {
			paramMap = mapper.readValue(paramInfo, Map.class);
			String token = paramMap.get("token").toString();
			String loginName = paramMap.get("loginName").toString();
			SecurityDao usermap  = securityServiceImpl.queryAppUserById(paramMap.get("loginName").toString().toUpperCase());
			String str = (String) cacheServiceImpl.getMemeryCacheByKey("VU:"+token);
			JSONObject obj = JSONObject.parseObject(str);
			
			if(usermap!=null && obj!=null){
				String objName = obj.get("loginName").toString();
				//判断从redis取出的用户名和其他平台传过来的值是否一样
				if(objName.equalsIgnoreCase(loginName)){
					
					Map object   = new  HashMap();
					object.put("date",format.format(new Date()));//放入时间
					object.put("loginName",loginName);//用户登录名
					object.put("userName",usermap.getQueryValue4());//用户名
					object.put("groupId",usermap.getQueryValue2());//用户机构
					object.put("userId",usermap.getQueryValue1());//用户ID
					map.put("status","2");
					map.put("info",token);
					map.put("data",object);
				}
			}else{
				map.put("status","0");
				map.put("info","请重新登录");
			}
		} catch (JsonParseException e) {
			log.error(e);
		} catch (JsonMappingException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return map;
	}
}
