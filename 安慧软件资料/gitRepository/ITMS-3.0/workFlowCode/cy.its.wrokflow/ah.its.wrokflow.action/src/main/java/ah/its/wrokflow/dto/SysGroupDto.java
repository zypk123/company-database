package ah.its.wrokflow.dto;

public class SysGroupDto extends PageEntity{
    private Object id;

    private Short rev;

    private Object name;

    private Object type;
    
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

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }
}