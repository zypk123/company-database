package com.zhang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhang.entity.PageBean;
import com.zhang.entity.Product;
import com.zhang.service.ProductService;
import com.zhang.util.ResponseUtil;
import com.zhang.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 产品Controller层
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 分页，条件查询产品列表
	 * @param page easyui分页所需的参数(当前页)
	 * @param rows easyui分页所需的参数(列，参数)
	 * @param s_product
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, Product s_product, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productName", StringUtil.formatLike(s_product.getProductName())); //为了mybatis的模糊查询的字段前面加上%...%
//		map.put("productName", s_product.getproductName());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Product> productList = productService.find(map);
		Long total = productService.getTotal(map);
		JSONObject result = new JSONObject(); //创建json对象
		JSONArray jsonArray = JSONArray.fromObject(productList);//将list转换json数组
		result.put("rows", jsonArray);//参数 ，传到easyui的前台，用row接收。
		result.put("total", total);//总记录数
		ResponseUtil.write(response, result);//写到前台数据
		return null;
	}
	
}
