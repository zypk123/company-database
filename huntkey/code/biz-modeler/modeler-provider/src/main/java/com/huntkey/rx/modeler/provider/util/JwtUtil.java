package com.huntkey.rx.modeler.provider.util;

import com.alibaba.fastjson.JSONObject;


import com.huntkey.rx.modeler.common.model.UserInfo;

import com.huntkey.rx.modeler.provider.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtUtil {
	/**
	 * 由字符串生成加密key
	 * @return
	 */
	public SecretKey generalKey(){
		String stringKey = Constant.JWT_SECRET;
		byte[] encodedKey = Base64.decodeBase64(stringKey);
	    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	    return key;
	}

	/**
	 * 创建jwt
	 * @param id
	 * @param subject
	 * @param ttlMillis
	 * @return
	 * @throws Exception
	 */
	public String createJWT(String id, JSONObject subject, long ttlMillis) throws Exception {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("alg", "HS256")
				.setIssuedAt(now)
			.claim("sub",subject)
		    .signWith(signatureAlgorithm, key);
		//添加Token过期时间
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	/**
	 * 解密jwt
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public Claims parseJWT(String jwt){
		try{
			SecretKey key = generalKey();
			Claims claims = Jwts.parser()
					.setSigningKey(key)
					.parseClaimsJws(jwt).getBody();
			return claims;
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 生成subject信息
	 * @param userInfo
	 * @return
	 */
	public static JSONObject generalSubject(UserInfo userInfo){
		JSONObject jo = new JSONObject();
		jo.put("epeoCode",userInfo.getEpeoCode());
		jo.put("epeoPassword",userInfo.getEpeoPassword());
		jo.put("status",userInfo.getStatus());
		return jo;
	}
}
