package com.huntkey.rx.util;

import com.huntkey.rx.base.PropertyAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaozhiy on 2017/11/16 0016.
 */
public class TestStaffEntity {
    @PropertyAnnotation(fomula="123",limitFomula="345")
    private String name;
    @PropertyAnnotation(fomula="123",limitFomula="345")
    private String age;
    @PropertyAnnotation(fomula="123",limitFomula="345")
    private String sex;
    @PropertyAnnotation(fomula="123",limitFomula="345",className = "TestPositionEntity")
    private String position;

    @PropertyAnnotation(fomula="123",limitFomula="345",className = "TestPositionEntity")
    private List<String> positions = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public TestPositionEntity getPositionObj(){
        String propertyName = "position";
        Object result = EntityUtils.getPropertyObj(this,propertyName);
        if(result != null){
            return (TestPositionEntity)result;
        }else{
            return null;
        }
    }


    //获取指定下标的对象
//    public TestPositionEntity getPositionObj(int index){
//        String propertyName = "positions";
//        Object result = EntityUtils.getPropertyObj(this,propertyName,index);
//        if(result != null){
//            return (TestPositionEntity)result;
//        }else{
//            return null;
//        }
//    }
}
