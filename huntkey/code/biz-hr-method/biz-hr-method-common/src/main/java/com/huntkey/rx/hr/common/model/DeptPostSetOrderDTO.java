
package com.huntkey.rx.hr.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

/**
 * Created by xuyf on 2017/11/15 0015.
 */
public class DeptPostSetOrderDTO extends OrderDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = NodeConstant.ID)
    private String id;
    
    @JSONField(name = "odps_type")
    private String odpsType;//单据类型

    @JSONField(name = "odps_beg")
    private String odpsBeg;//生效日期
    
    @JSONField(name = "odps_remark")
    private String odpsRemark;//备注

    @JSONField(name = "odps_dpost_set")
    private List<OdpsDpostSetDTO> odpsDpostSet;//岗位信息

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOdpsType() {
		return odpsType;
	}

	public void setOdpsType(String odpsType) {
		this.odpsType = odpsType;
	}

	public String getOdpsBeg() {
		return odpsBeg;
	}

	public void setOdpsBeg(String odpsBeg) {
		this.odpsBeg = odpsBeg;
	}

	public String getOdpsRemark() {
		return odpsRemark;
	}

	public void setOdpsRemark(String odpsRemark) {
		this.odpsRemark = odpsRemark;
	}

	public List<OdpsDpostSetDTO> getOdpsDpostSet() {
		return odpsDpostSet;
	}

	public void setOdpsDpostSet(List<OdpsDpostSetDTO> odpsDpostSet) {
		this.odpsDpostSet = odpsDpostSet;
	}
    
    @JSONField(name = "odps_dpost_set")
    private List<DeptPostSetDTO> deptPosts = new ArrayList<DeptPostSetDTO>();


    public void setOdpsTtype(String odpsType) {
        this.odpsType = odpsType;
    }

    public String getOdpsTbeg() {
        return odpsBeg;
    }

    public void setOdpsTbeg(String odpsBeg) {
        this.odpsBeg = odpsBeg;
    }

    public List<DeptPostSetDTO> getDeptPosts() {
        return deptPosts;
    }

    public void setDeptPosts(List<DeptPostSetDTO> deptPosts) {
        this.deptPosts = deptPosts;
    }
}
