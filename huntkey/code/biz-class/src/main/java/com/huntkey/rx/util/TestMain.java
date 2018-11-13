package com.huntkey.rx.util;

/**
 * Created by gaozhiy on 2017/11/16 0016.
 */
public class TestMain {
    public static void main(String[] args){
        TestStaffEntity testStaffEntity = new TestStaffEntity();
        testStaffEntity.setPosition("111111111");
        testStaffEntity.getPositions().add("222222222222");
        testStaffEntity.getPositions().add("3333333333");
        TestPositionEntity testPositionEntity = testStaffEntity.getPositionObj();
        System.out.println("id="+testPositionEntity.getId());

//        TestPositionEntity testPositionEntity1 = testStaffEntity.getPositionObj(0);
//        System.out.println("id======="+testPositionEntity1.getId());
    }
}
