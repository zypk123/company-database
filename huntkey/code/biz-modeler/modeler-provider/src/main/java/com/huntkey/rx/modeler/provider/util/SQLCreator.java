package com.huntkey.rx.modeler.provider.util;

import com.huntkey.rx.modeler.common.model.DBIndex;
import com.huntkey.rx.modeler.common.model.EdmTable;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoucj on 2017/11/3.
 */
public class SQLCreator {
    public static final void sqlCreate(Map<String, List<EdmTable>> map, List<DBIndex> indexList){
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");

        ve.init();

        Template t = ve.getTemplate("template/table.vm");
        VelocityContext ctx = new VelocityContext();

        ctx.put("createList", map.get("createList"));
        ctx.put("addList", map.get("addList"));
        ctx.put("updateList", map.get("updateList"));
        ctx.put("dropList", map.get("dropList"));
        ctx.put("indexList", indexList);

        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);

        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(sw.toString());
        File file = new File("test.sql");
        Boolean append = false;
        try {
            FileChannel fileChannel = new FileOutputStream(file, append).getChannel();
            try {
                fileChannel.write(byteBuffer);
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
