package cy.its.violation.rest.action.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileToZip {
	   private  static String  fileseparator =  File.separator=="/"?"/":"\\";
	   private FileToZip(){}  
     
	    /** 
	     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下 
	     * @param sourceFilePath :待压缩的文件路径 
	     * @param zipFilePath :压缩后存放路径 
	     * @param fileName :压缩后文件的名称 
	     * @return 
	     */  
	    public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){  
	        boolean flag = false;  
	        File sourceFile = new File(sourceFilePath);  
	        FileInputStream fis = null;  
	        BufferedInputStream bis = null;  
	        FileOutputStream fos = null;  
	        ZipOutputStream zos = null;  
	          
	        if(sourceFile.exists() == false){  
	            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");  
	        }else{  
	            try {  
	                File zipFile = new File(zipFilePath + fileseparator + fileName +".zip");  
	                if(zipFile.exists()){  
	                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");  
	                }else{  
	                    File[] sourceFiles = sourceFile.listFiles();  
	                    if(null == sourceFiles || sourceFiles.length<1){  
	                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");  
	                    }else{  
	                        fos = new FileOutputStream(zipFile);  
	                        zos = new ZipOutputStream(new BufferedOutputStream(fos));  
	                        byte[] bufs = new byte[1024*10];  
	                        for(int i=0;i<sourceFiles.length;i++){  
	                            //创建ZIP实体，并添加进压缩包  
	                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
	                            zos.putNextEntry(zipEntry);  
	                            //读取待压缩的文件并写进压缩包里  
	                            fis = new FileInputStream(sourceFiles[i]);  
	                            bis = new BufferedInputStream(fis, 1024*10);  
	                            int read = 0;  
	                            while((read=bis.read(bufs, 0, 1024*10)) != -1){  
	                                zos.write(bufs,0,read);  
	                            }  
	                        }  
	                        flag = true;  
	                    }  
	                }  
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            } finally{  
	                //关闭流  
	                try {  
	                    if(null != bis) bis.close();  
	                    if(null != zos) zos.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                    throw new RuntimeException(e);  
	                }  
	            }  
	        }  
	        return flag;  
	    }  
	    
	    
	    public static byte[] readInputStream(InputStream inStream) throws Exception{  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        //创建一个Buffer字符串  
	        byte[] buffer = new byte[1024];  
	        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
	        int len = 0;  
	        //使用一个输入流从buffer里把数据读取出来  
	        while( (len=inStream.read(buffer)) != -1 ){  
	            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
	            outStream.write(buffer, 0, len);  
	        }  
	        //关闭输入流  
	        inStream.close();  
	        //把outStream里的数据写入内存  
	        return outStream.toByteArray();  
	    }  
}
