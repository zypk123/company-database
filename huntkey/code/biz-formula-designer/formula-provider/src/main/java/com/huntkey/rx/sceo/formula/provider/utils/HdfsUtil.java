package com.huntkey.rx.sceo.formula.provider.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chenfei on 2017/5/15.
 */
@Service
@Lazy(false)
public class HdfsUtil {
    private static Logger logger = LoggerFactory.getLogger(HdfsUtil.class);

    private static FileSystem hdfs = null;
    private static AtomicBoolean sync = new AtomicBoolean(false);

    @Value("${nameNode}")
    private String nameNode;

    private static String tempNameNode;

    @PostConstruct
    public void init() {
        tempNameNode = nameNode;
    }

    public static void deleteTest(String srcPath, String destPath) {
        buildHdfsFileSystem();
        try {
            hdfs.copyFromLocalFile(true, new Path(srcPath), new Path(destPath));
        } catch (IOException e) {
            logger.error("Copy file from hdfs to local failed", e);
        } finally {
            try {
                hdfs.close();
                sync.set(false);
            } catch (IOException e) {
                logger.error("Fail to close hdfs connection", e);
            }
        }
    }

    /**
     * 从hdfs删除文件
     */
    public static void deleteDir(String dir) {
        buildHdfsFileSystem();
        try {
            Path dirPath = new Path(dir);
            if (hdfs.exists(dirPath)) {
                hdfs.delete(dirPath, true);
            }
        } catch (IOException e) {
            logger.error("Copy file from hdfs to local failed", e);
        } finally {
            try {
                hdfs.close();
                sync.set(false);
            } catch (IOException e) {
                logger.error("Fail to close hdfs connection", e);
            }
        }
    }

    /**
     * 从hdfs获取文件
     *
     * @param sourceFileDir
     * @param targetDir
     */
    public static void copyFileToLocal(String sourceFileDir, String targetDir) {
        buildHdfsFileSystem();
        try {
            Path srcPath = new Path(sourceFileDir);
            if (hdfs.exists(srcPath)) {
                hdfs.copyToLocalFile(false, srcPath, new Path(targetDir));
            }
        } catch (IOException e) {
            logger.error("Copy file from hdfs to local failed", e);
        } finally {
            try {
                hdfs.close();
                sync.set(false);
            } catch (IOException e) {
                logger.error("Fail to close hdfs connection", e);
            }
        }
    }

    /**
     * 从hdfs获取文件
     *
     * @param filePaths
     * @param targetDir
     */
    public static void copyFileToLocal(List<String> filePaths, String targetDir) {
        buildHdfsFileSystem();
        try {
            Path srcPath = null;
            for(String sourceFile : filePaths){
                srcPath = new Path(sourceFile);
                if (hdfs.exists(srcPath)) {
                    hdfs.copyToLocalFile(false, srcPath, new Path(targetDir));
                }
            }
        } catch (IOException e) {
            logger.error("Copy file from hdfs to local failed", e);
        } finally {
            try {
                hdfs.close();
                sync.set(false);
            } catch (IOException e) {
                logger.error("Fail to close hdfs connection", e);
            }
        }
    }

    /**
     * 上传文件到hdfs
     *
     * @param filePaths
     * @param targetDir
     */
    public static void putFileToHdfs(String[] filePaths, String targetDir) {
        //TODO
    }

    /**
     * 上传文件到hdfs
     *
     * @param files
     * @param targetDir
     */
    public static void putFileToHdfs(File[] files, String targetDir) {
        buildHdfsFileSystem();
        try {
            for (File file : files) {
                hdfs.copyFromLocalFile(true, new Path(file.getAbsolutePath()), new Path(targetDir));
            }
        } catch (IOException e) {
            logger.error("Put file to hdfs from local failed", e);
        } finally {
            try {
                hdfs.close();
                sync.set(false);
            } catch (IOException e) {
                logger.error("Fail to close hdfs connection", e);
            }
        }
    }

    /**
     * 上传文件到hdfs
     *
     * @param dirPath
     * @param targetHdfsDir
     */
    public static void putFileToHdfs(String dirPath, String targetHdfsDir) {
        buildHdfsFileSystem();
        try {
            File dir = new File(dirPath);
            if (dir.exists()) {
                // 如果hdfsDir不存在，则创建
                Path hdfsDir = new Path(targetHdfsDir);
                if (!hdfs.exists(hdfsDir)) {
                    hdfs.mkdirs(hdfsDir);
                }
                // 将文件上传到hdfs
                for (File file : dir.listFiles()) {
                    // hdfs上不存在的时候才复制
                    if(!hdfs.exists(new Path(hdfsDir + file.getName()))){
                        hdfs.copyFromLocalFile(false, new Path(file.toURI()), hdfsDir);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Put file to hdfs from local failed", e);
        } finally {
            try {
                hdfs.close();
                sync.set(false);
            } catch (IOException e) {
                logger.error("Fail to close hdfs connection", e);
            }
        }
    }

    /**
     * 构建hdfs fs，以操作文件
     */
    private static void buildHdfsFileSystem(String nameNode) {
        if (sync.get() == false) {
            sync.set(true);
            Configuration conf = new Configuration();
            conf.set("fs.hdfs.impl", DistributedFileSystem.class.getName());
            conf.set("default.FS", nameNode);
            try {
                hdfs = FileSystem.get(URI.create(nameNode), conf);
            } catch (IOException e) {
                logger.error("Build hdfs file system failed", e);
            } finally {
                try {
                    hdfs.close();
                    sync.set(false);
                } catch (IOException e) {
                    logger.error("Fail to close hdfs connection", e);
                }
            }
        }
    }

    /**
     * 构建hdfs fs，以操作文件
     */
    private static void buildHdfsFileSystem() {
        if (sync.get() == false) {
            sync.set(true);
            Configuration conf = new Configuration();
            conf.set("fs.hdfs.impl", DistributedFileSystem.class.getName());
            conf.set("default.FS", tempNameNode);
            try {
                hdfs = FileSystem.get(URI.create(tempNameNode), conf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setTempNameNode(String nameNode) {
        tempNameNode = nameNode;
    }
}
