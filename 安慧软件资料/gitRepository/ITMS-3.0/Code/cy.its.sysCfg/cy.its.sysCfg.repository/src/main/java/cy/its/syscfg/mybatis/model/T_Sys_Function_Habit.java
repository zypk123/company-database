package cy.its.syscfg.mybatis.model;

public class T_Sys_Function_Habit {
    private String useHabitsId;

    private String userId;

    private String individualItems;

    private String individualItemValue;

    public String getUseHabitsId() {
        return useHabitsId;
    }

    public void setUseHabitsId(String useHabitsId) {
        this.useHabitsId = useHabitsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIndividualItems() {
        return individualItems;
    }

    public void setIndividualItems(String individualItems) {
        this.individualItems = individualItems;
    }

    public String getIndividualItemValue() {
        return individualItemValue;
    }

    public void setIndividualItemValue(String individualItemValue) {
        this.individualItemValue = individualItemValue;
    }
}