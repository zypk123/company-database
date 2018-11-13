package com.huntkey.rx.sceo.formula.provider.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FreeMarker工具类
 *
 * @author zhangyu
 * @create 2017-07-26 10:12
 **/
public class FreeMarkerUtils {

    /**
     * 模版配置对象
     */
    private Configuration cfg = new Configuration();

    private String srcFilePath;

    public FreeMarkerUtils(String srcFilePath) {
        this.srcFilePath = srcFilePath;
    }

    public String getSrcFilePath() {
        return srcFilePath;
    }

    /**
     * 生成源文件
     *
     * @param classifyCode 分类编码
     * @param classifyDesc 分类描述
     * @param classifyName 分类名称
     */
    public File createSrcFile(String classifyCode, String classifyDesc, String classifyName) {

        File templateDir = null;
        FileWriter fw = null;

        try {

            templateDir = ResourceUtils.getFile("/freeMarkTemplate");

            // 设置模板文件路径
            cfg.setClassForTemplateLoading(this.getClass(), "/freeMarkTemplate");
            Map<String, String> map = new HashMap<String, String>();
            map.put("classifyCode", classifyCode);
            map.put("classifyDesc", classifyDesc);
            map.put("classifyName", classifyName);
            // 创建模版对象
            Template t = cfg.getTemplate("FreeMarkerTemplate.ftl");
            // 分类编号
            String code = map.get("classifyCode");
            String fileName = code.substring(code.lastIndexOf(".") + 1) + ".java";
            // 生成文件的路径
            File srcFileDir = ResourceUtils.getFile(srcFilePath);
            File file = new File(srcFileDir.getPath() + File.separator +
                    code.substring(0, code.lastIndexOf(".")).replace(".", "/"));
            if (!file.exists()) {
                file.mkdirs();
            }
            File sourceFile = new File(file + File.separator + fileName);
            fw = new FileWriter(sourceFile);
            // 文件生成
            t.process(map, fw);
            fw.flush();
            return sourceFile;
        } catch (Exception e) {
            throw new RuntimeException("create source file error.", e);
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
