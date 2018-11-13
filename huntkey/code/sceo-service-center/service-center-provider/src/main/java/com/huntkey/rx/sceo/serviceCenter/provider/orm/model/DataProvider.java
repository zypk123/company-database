package com.huntkey.rx.sceo.serviceCenter.provider.orm.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * Created by zhanglb on 2017/7/6.
 */

/*
 * 包含CRUD的完整节点的参数 data 的数据格式
 {
    "edmName": "user",
    "dataset": [
        {
            "user_001": "zhangsan",
            "user_002": "test",
            "user_003": "备注"
        }
    ],
    "assemble": [
        {
            "edmName": "user_c1",
            "dataset": [
                {
                    "pid": "",
                    "user_c1_001": "zhangsan",
                    "user_c1_002": "test",
                    "user_c1_003": "备注"
                },
                {
                    "pid": "",
                    "user_c1_001": "west",
                    "user_c1_002": "test",
                    "user_c1_003": "备注"
                }
            ],
            "assemble": [
                {
                    "edmName": "user_c1_c1",
                    "dataset": [
                        {
                            "pid": "",
                            "user_c1_c1_001": "zhangsan",
                            "user_c1_c1_002": "test",
                            "user_c1_c1_003": "备注"
                        },
                        {
                            "pid": "",
                            "user_c1_c1_001": "lisi",
                            "user_c1_c1_002": "test",
                            "user_c1_c1_003": "备注"
                        }
                    ],
                    "assemble": []
                },
                {
                    "edmName": "user_c1_c2",
                    "dataset": [
                        {
                            "pid": "",
                            "user_c1_c2_001": "zhangsan",
                            "user_c1_c2_002": "test",
                            "user_c1_c2_003": "备注"
                        },
                        {
                            "pid": "",
                            "user_c1_c2_001": "lisi",
                            "user_c1_c2_002": "test",
                            "user_c1_c2_003": "备注"
                        }
                    ],
                    "assemble": []
                }
            ],
            "user_c1_c2": {
                "edmName": "user_c1_c2",
                "dataset": [
                    {
                        "pid": "",
                        "user_c1_c2_001": "zhangsan",
                        "user_c1_c2_002": "test",
                        "user_c1_c2_003": "备注"
                    },
                    {
                        "pid": "",
                        "user_c1_c2_001": "lisi",
                        "user_c1_c2_002": "test",
                        "user_c1_c2_003": "备注"
                    }
                ],
                "assemble": []
            }
        },
        {
            "edmName": "user_c2",
            "dataset": [
                {
                    "pid": "",
                    "user_c2_001": "zhangsan",
                    "user_c2_002": "test",
                    "user_c2_003": "备注"
                },
                {
                    "pid": "",
                    "user_c2_001": "lisi",
                    "user_c2_002": "test",
                    "user_c2_003": "备注"
                }
            ],
            "assemble": []
        }
    ]
}
*/

public class DataProvider extends JSONObject {

	private String edmName;

	private JSONArray dataset;

	private JSONArray assemble;

	private String pid;

	public String getEdmName() {
		return edmName;
	}

	public void setEdmName(String edmName) {
		this.edmName = edmName;
	}

	public JSONArray getDataset() {
		return dataset;
	}

	public void setDataset(JSONArray dataset) {
		this.dataset = dataset;
	}

	public JSONArray getAssemble() {
		return assemble;
	}

	public void setAssemble(JSONArray assemble) {
		this.assemble = assemble;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}
