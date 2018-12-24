package cy.its.service.common;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

	public static String serialize(Object object) {
		return JSON.toJSONString(object);
	}

	public static <T> T parseObject(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}

	public static <T> List<T> parseList(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}

	public static void main(String[] args) {
		List<String> lst = new ArrayList<String>();
		lst.add("sssssssss");

		// lst.add(new uuuuuu("ssssÄãºÃ", 15));
		String jsString = serialize(lst);
		System.out.println(jsString);

		List<String> u = parseList(jsString, String.class);

		System.out.println(u.size());
	}
}

class uuuuuu {
	public uuuuuu() {
	}

	/**
	 * @param nameString
	 * @param ageInteger
	 */
	public uuuuuu(String nameString, Integer ageInteger) {
		super();
		this.nameString = nameString;
		this.ageInteger = ageInteger;
	}

	public String nameString;
	public Integer ageInteger;
}
