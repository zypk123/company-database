package ah.its.wrokflow.repository.dao;

public class SysUser{
    private Object id;

    private Short rev;

    private Object first;

    private Object last;

    private Object email;

    private Object pwd;

    private Object pictureId;
    
    private String groupId;
    private String groupName;

    public String getGroupId() {
    	
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Short getRev() {
        return rev;
    }

    public void setRev(Short rev) {
        this.rev = rev;
    }

    public Object getFirst() {
        return first;
    }

    public void setFirst(Object first) {
        this.first = first;
    }

    public Object getLast() {
        return last;
    }

    public void setLast(Object last) {
        this.last = last;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPwd() {
        return pwd;
    }

    public void setPwd(Object pwd) {
        this.pwd = pwd;
    }

    public Object getPictureId() {
        return pictureId;
    }

    public void setPictureId(Object pictureId) {
        this.pictureId = pictureId;
    }

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}