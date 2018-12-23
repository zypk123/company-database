package com.zhang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhang.entity.Customer;
import com.zhang.entity.CustomerFw;
import com.zhang.entity.CustomerGc;
import com.zhang.entity.CustomerGx;
import com.zhang.entity.PageBean;
import com.zhang.service.CustomerService;
import com.zhang.util.DateUtil;
import com.zhang.util.ResponseUtil;
import com.zhang.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 客户Controller层
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * 分页，条件查询客户列表
	 * @param page easyui分页所需的参数(当前页)
	 * @param rows easyui分页所需的参数(列，参数)
	 * @param s_customer
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, Customer s_customer, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("khno", StringUtil.formatLike(s_customer.getKhno())); //为了mybatis的模糊查询的字段前面加上%...%
		map.put("name", StringUtil.formatLike(s_customer.getName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Customer> customerList = customerService.find(map);
		Long total = customerService.getTotal(map);
		JSONObject result = new JSONObject(); //创建json对象
		JSONArray jsonArray = JSONArray.fromObject(customerList);//将list转换json数组
		result.put("rows", jsonArray);//参数 ，传到easyui的前台，用row接收。
		result.put("total", total);//总记录数
		ResponseUtil.write(response, result);//写到前台数据
		return null;
	}
	
	/**
	 * 添加或修改客户时的保存
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Customer customer,HttpServletResponse response) throws Exception{
		int resultTotal=0; // 操作的记录条数
		if(customer.getId()==null){ //如果获取不到id，则说明是添加
			customer.setKhno("KH"+DateUtil.getCurrentDateStr()); // 动态生成客户编号
			resultTotal = customerService.add(customer); //增加
		} else{
			resultTotal = customerService.update(customer); //修改
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
	 * 删除客户
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String[] idsStr=ids.split(","); //可能是一次删除多条数据，获得id数组
		for(int i=0;i<idsStr.length;i++){
			customerService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 通过ID查找实体
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Customer customer=customerService.findById(Integer.parseInt(id));
		JSONObject jsonObject=JSONObject.fromObject(customer);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	
	/**
	 * 分页，条件查询客户列表
	 * @param page easyui分页所需的参数(当前页)
	 * @param rows easyui分页所需的参数(列，参数)
	 * @param s_customer
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findCustomerGx")
	public String findCustomerGx(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, CustomerGx s_customerGx, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", StringUtil.formatLike(s_customerGx.getName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<CustomerGx> customerGxList = customerService.findCustomerGx(map);
		Long total = customerService.getTotalCustomerGx(map);
		JSONObject result = new JSONObject(); //创建json对象
		JSONArray jsonArray = JSONArray.fromObject(customerGxList);//将list转换json数组
		result.put("rows", jsonArray);//参数 ，传到easyui的前台，用row接收。
		result.put("total", total);//总记录数
		ResponseUtil.write(response, result);//写到前台数据
		return null;
	}
	
	/**
	 * 查询客户构成列表
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findCustomerGc")
	public String findCustomerGc(HttpServletResponse response)throws Exception {
		List<CustomerGc> customerGcList = customerService.findCustomerGc();
		JSONArray jsonArray = JSONArray.fromObject(customerGcList);//将list转换json数组
		ResponseUtil.write(response, jsonArray);//写到前台数据
		return null;
	}
	
	/**
	 * 查询客户服务列表
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findCustomerFw")
	public String findCustomerFw(HttpServletResponse response)throws Exception {
		List<CustomerFw> customerFwList = customerService.findCustomerFw();
		JSONArray jsonArray = JSONArray.fromObject(customerFwList);//将list转换json数组
		ResponseUtil.write(response, jsonArray);//写到前台数据
		return null;
	}
}
