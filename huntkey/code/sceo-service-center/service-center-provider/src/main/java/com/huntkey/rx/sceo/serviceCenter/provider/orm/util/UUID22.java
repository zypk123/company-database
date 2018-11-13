package com.huntkey.rx.sceo.serviceCenter.provider.orm.util;

import java.util.UUID;

/**
 * Created by linziy on 2017/10/21.
 * uuid22 压缩规则:
 * uuid 32 利用64个可见字符编码进行压缩编码无损压缩成22位的uuid
 * uuid32 为16进制可见字符编码
 * uuid22 为64进制可见字符编码
 */
public class  UUID22{
    //16进制字符串
    private final static String HEXARRAY= "0123456789ABCDEF";

    //base 64 字符串(非标准)
    private final static String VARIANTBASE64 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+-";
    //标准的base64
//  private final static char[] BASE64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    public final static String createUUID22(){
        UUID uuid = UUID.randomUUID();
        String uuid32 = uuid.toString().replace("-","") ;
  //      uuid32 = "1f1a1bf21e7649c184e01d03b9e5f1ef";
        System.out.println("生成的UUID32:"+uuid32);
        byte[] bytes = uuid32ToByteArray(uuid32);//16个字节
        String uuid22 = "";
        for(int i = 0;i<5;i++){
            int pos = i*3;
            byte[] three = new byte[3];
            System.arraycopy(bytes,pos,three,0,3);
            uuid22 = uuid22 +  getStringByBytes3(three);
        }
        //处理第16 个字节
        uuid22 += getStringByLastByte(bytes[15]);
        System.out.println("压缩的UUID22:" + uuid22);
        return uuid22;
    }

    /**
     * 3 字节转4 字符表示
     * @param byteOfLengthThree
     * @return
     */
    private  final static String getStringByBytes3(byte[] byteOfLengthThree){
        if(byteOfLengthThree.length != 3){
            throw new IllegalArgumentException("数组长度不为3");
        }
        String rs = "";
        int pos = 0;
        pos = (byteOfLengthThree[0] >> 2) & 0x3F;//取前6位
        rs += VARIANTBASE64.charAt(pos);
        pos = ((byteOfLengthThree[0] << 4) & 0x30) | ((byteOfLengthThree[1] >> 4) & 0x0F);//取第一字节后2位,第二字节前4位
        rs += VARIANTBASE64.charAt(pos);
        pos = ((byteOfLengthThree[1] << 2) & 0x3C) | ((byteOfLengthThree[2] >>6) & 0x03); //第二字节的前4位,第3字节的前2位
        rs += VARIANTBASE64.charAt(pos);
        pos = byteOfLengthThree[2] & 0x3F;//第3字节的后6位
        rs += VARIANTBASE64.charAt(pos);
        return rs;
    }

    /**
     * 处理最后的一个字节
     * @return
     */
    private  final static String getStringByLastByte(byte last){
        String rs = "";
        int pos = (last >> 2) & 0x3F;//取前6位
        rs += VARIANTBASE64.charAt(pos);
        //取前2位 + 补 0 位置
        pos = ((last << 4) & 0x30);
        rs += VARIANTBASE64.charAt(pos);
        return rs;
    }

    private final static byte hexCharToByte(char c){
        return (byte)HEXARRAY.toString().indexOf(c);
    }

    /**
     * uuid32 转字节数组
     */
    private final static byte[] uuid32ToByteArray(String uuid32){
        uuid32 = uuid32.toUpperCase();
        int len = uuid32.length()/2 ;
        char[] hexChars = uuid32.toCharArray();
        byte[] bRs = new byte[len];
        for (int i= 0;i<len;++i){
            int pos = i*2;
            bRs[i] = (byte)( hexCharToByte(hexChars[pos]) << 4 | hexCharToByte(hexChars[pos + 1]) );
        }
        return bRs;
    }

    /**
     * uuid22 转字节数组
     * @param uuid22
     * @return
     */
    private final static byte[] uuid22To16ByteArray(String uuid22){
        byte[] bRs = new byte[16];

        int posStr = 0;

        for(int i = 0 ; i < 5 ; i++){
            posStr = i * 4;
            int posByte = i * 3;
            //4个字符 转3个字节
            String fourSigns = uuid22.substring(posStr, posStr + 4);
           // System.out.println(fourSigns);
            byte firstByte = (byte)VARIANTBASE64.indexOf(fourSigns.charAt(0));
            byte secondByte = (byte) VARIANTBASE64.indexOf(fourSigns.charAt(1));
            byte thirdByte = (byte) VARIANTBASE64.indexOf(fourSigns.charAt(2));
            byte fourthByte = (byte) VARIANTBASE64.indexOf(fourSigns.charAt(3));
            byte[] tmpRs = new byte[3];
            //firstByte 的1-6位+ secondByte前6.5位
            tmpRs[0] = (byte)((firstByte << 2) | ((secondByte >>4) & 0x03));
            //secondByte  的1-4位+ thirdByte 前3-6位
            tmpRs[1] = (byte)((secondByte << 4) | ((thirdByte >>2) & 0x0F));
            //thirdByte 的1-2 位 + fourthByte 1-6 位置
            tmpRs[2] = (byte)(((thirdByte << 6) & 0xC0) | (fourthByte & 0x3F));
           // System.arraycopy(bytes,pos,three,0,3);
            System.arraycopy(tmpRs,0,bRs,posByte,3);
        }
        //处理最后两个字符,最后的一个字节
        posStr += 4;
        byte lastButOne = (byte)VARIANTBASE64.indexOf(uuid22.charAt(posStr));
        posStr ++;
        byte lastOne = (byte)VARIANTBASE64.indexOf(uuid22.charAt(posStr));
        bRs[15] = (byte)(((lastButOne << 2) & 0xFC) | ((lastOne >> 4) & 0x03));
        return bRs;
    }



    public final static String Uuid22ToUuid32(String uuid22){
        byte[] byteArray16 = uuid22To16ByteArray(uuid22);
        //byte 数组转 32 进
        String  uuId32 = byteArrayToHexString(byteArray16);
        return uuId32;
    }

    private final static String byteArrayToHexString(byte[] bytes){
        String hexString = "";
        for(byte one : bytes){
            int halfleft = (one & 0xF0) >> 4;
            hexString += String.valueOf(HEXARRAY.charAt(halfleft));
            int halfright = (one & 0x0F) ;
            hexString += String.valueOf(HEXARRAY.charAt(halfright));
        }
        return hexString;
    }

//    //本类测试
//    public final static void main(String[] args){
//        String uuid22 = UUID22.createUUID22();
//        String uuid32 = Uuid22ToUuid32(uuid22);
//        System.out.println("恢复的UUID32:"+ uuid32.toLowerCase());
//    }
}
