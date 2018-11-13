package com.huntkey.rx.sceo.serviceCenter.common.emun;

/**
 * Created by linziy on 2017/8/22.
 * It's the type for table
 */
public enum TableType {
    LINK_TABLE(0,"链表"),
    MAIN_TABLE(1,"主表"),
    SON_TABLE(2,"子表");

    private int type;
    private String desc;

    private TableType(int type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public static final String LINK_SUFFIX="_linkagedetail";

    /**
     * 获取表的类型
     * @param tableName
     * @return
     */
    public final static TableType getTableType(String tableName){
        //判断表的类型
        TableType tableType = null;
        if (tableName.endsWith(LINK_SUFFIX)){
            tableType = TableType.LINK_TABLE;
        }else if(-1 == tableName.indexOf("_")){
            tableType = TableType.MAIN_TABLE;
        }else{
            tableType = TableType.SON_TABLE;
        }
        return tableType;
    }
}
