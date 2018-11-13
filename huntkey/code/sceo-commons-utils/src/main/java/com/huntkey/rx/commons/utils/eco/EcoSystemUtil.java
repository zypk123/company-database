package com.huntkey.rx.commons.utils.eco;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangyu
 * @create 2017-10-14 15:00
 **/
public class EcoSystemUtil {

    private static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";

    /**
     * 判断jwt信息是否可用
     *
     * @return
     */
    public static Map<String, String> ifAuthUsable(String jwt) {
        Map<String, String> usermap = new LinkedHashMap<String, String>();
        boolean usableFlag = false;
        String epeoCode = "";//用户id
        //1.解析jwt字符串信息
        Map<String, Map<String, String>> claimmap = (Map) Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET))
                .parseClaimsJws(jwt).getBody();
        if (claimmap.containsKey("sub")) {
            usermap = claimmap.get("sub");
        }
        return usermap;
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        try {
            SecretKey key = generalKey();
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }
}
