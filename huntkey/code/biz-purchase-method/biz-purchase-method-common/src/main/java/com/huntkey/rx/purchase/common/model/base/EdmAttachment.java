package com.huntkey.rx.purchase.common.model.base;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 附件实体类
 *
 * @author zhangyu
 * @create 2018-1-2 15:38
 **/
public class EdmAttachment {

    /**
     * 主键id
     */
    private String id;

    /**
     * 附件类型
     */
    private Byte edmaType;

    /**
     * 附件名称(输入)
     */
    @NotNull(message = "附件名称不能为空")
    private String edmaName;

    /**
     * 附件原名称
     */
    private String edmaSourceName;

    /**
     * 附件路径
     */
    private String edmaPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Byte getEdmaType() {
        return edmaType;
    }

    public void setEdmaType(Byte edmaType) {
        this.edmaType = edmaType;
    }

    public String getEdmaName() {
        return edmaName;
    }

    public void setEdmaName(String edmaName) {
        this.edmaName = edmaName;
    }

    public String getEdmaSourceName() {
        return edmaSourceName;
    }

    public void setEdmaSourceName(String edmaSourceName) {
        this.edmaSourceName = edmaSourceName;
    }

    public String getEdmaPath() {
        return edmaPath;
    }

    public void setEdmaPath(String edmaPath) {
        this.edmaPath = edmaPath;
    }
}