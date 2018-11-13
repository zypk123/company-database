package com.huntkey.rx.sceo.formula.engine.circle.check;

/**
 * @author chenfei on 2017/7/3.
 */
public class CircleMapResult {

    /**
     * 是否含有回路
     */
    private boolean hasCircle;

    /**
     * 回路简图
     */
    private String circleSimpleMap;

    /**
     * 重复引用节点信息
     */
    private String duplicatedRefNodeInfo;

    public String getDuplicatedRefNodeInfo() {
        return duplicatedRefNodeInfo;
    }

    public void setDuplicatedRefNodeInfo(String duplicatedRefNodeInfo) {
        this.duplicatedRefNodeInfo = duplicatedRefNodeInfo;
    }

    public boolean isHasCircle() {
        return hasCircle;
    }

    public void setHasCircle(boolean hasCircle) {
        this.hasCircle = hasCircle;
    }

    public String getCircleSimpleMap() {
        return circleSimpleMap;
    }

    public void setCircleSimpleMap(String circleSimpleMap) {
        this.circleSimpleMap = circleSimpleMap;
    }

    @Override
    public String toString() {
        return "CircleMapResult{" +
                "hasCircle=" + hasCircle +
                ", circleSimpleMap='" + circleSimpleMap + '\'' +
                ", duplicatedRefNodeInfo='" + duplicatedRefNodeInfo + '\'' +
                '}';
    }
}
