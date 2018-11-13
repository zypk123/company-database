package com.huntkey.rx.modeler.provider.util;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
/**
 * Created by weijian on 2017/10/10.
 */
@Component
public class Md5Util {
    public static String getMd5(String inStr)
    {
        String outStr=null;
        if(inStr ==null){
            outStr = null;
        }else if("".equals(inStr)){
            outStr = "";
        }else{
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(inStr.getBytes());
                byte b[] = md.digest();
                StringBuffer buf = new StringBuffer();
                for(int i=1;i<b.length;i++)
                {
                    int c = b[i]>>>4 & 0xf;
                    buf.append(Integer.toHexString(c));
                    c = b[i]& 0xf;
                    buf.append(Integer.toHexString(c));
                }
                outStr = buf.toString();
            }catch (Exception e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return outStr;
    }
}
