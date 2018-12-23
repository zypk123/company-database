package com.zhang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhang.entity.PageBean;
import com.zhang.entity.User;
import com.zhang.service.UserService;
import com.zhang.util.ResponseUtil;
import com.zhang.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户Controller层
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户登录
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request) throws Exception {
		User resultUser = userService.login(user);
		if (resultUser == null) {
			request.setAttribute("user", user); // 用来页面展示输入的登录信息，回显信息
			request.setAttribute("errorMsg", "用户名或密码错误");// 提示错误信息
			return "login";// 登录失败，返回登录页面
		} else {
			// 登录成功保存登录信息到session里面
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", resultUser);
			return "redirect:/main.jsp";// 重定向到首页
		}
	}

	/**
	 * 分页，条件查询用户列表
	 * @param page easyui分页所需的参数(当前页)
	 * @param rows easyui分页所需的参数(列，参数)
	 * @param s_user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, User s_user, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", StringUtil.formatLike(s_user.getUserName())); //为了mybatis的模糊查询的字段前面加上%...%
//		map.put("userName", s_user.getUserName());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<User> userList = userService.find(map);
		Long total = userService.getTotal(map);
		JSONObject result = new JSONObject(); //创建json对象
		JSONArray jsonArray = JSONArray.fromObject(userList);//将list转换json数组
		result.put("rows", jsonArray);//参数 ，传到easyui的前台，用row接收。
		result.put("total", total);//总记录数
		ResponseUtil.write(response, result);//写到前台数据
		return "page/userManage";
	}
	
	/**
	 * 添加或修改用户时的保存
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(User user,HttpServletResponse response) throws Exception{
		int resultTotal=0; // 操作的记录条数
		if(user.getId()==null){ //如果获取不到id，则说明是添加
			resultTotal = userService.add(user); //增加
		} else{
			resultTotal = userService.update(user);//更新
		}
		JSONObject result = new JSONObject(); //创建json对象
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 删除用户
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String[] idsStr=ids.split(","); //可能是一次删除多条数据，获得id数组
		for(int i=0;i<idsStr.length;i++){
			userService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 获取客户经理信息 下拉框数据用到
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/customerManagerComboList")
	public String customerManagerComboList(HttpServletResponse response)throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("roleName", "客户经理");
		List<User> userList=userService.find(map);
		JSONArray row=JSONArray.fromObject(userList);
		ResponseUtil.write(response, row);
		return null;
	}
	
	/**
	 * 修改密码
	 * @param id
	 * @param newPassword
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(Integer id,String newPassword,HttpServletResponse response)throws Exception{
		User user = new User();
		user.setId(id);
		user.setPassword(newPassword);
		int resultTotal=userService.update(user);
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate(); //清除session中数据
		return "redirect:/login.jsp";//重定向到登录页面
	}
}
