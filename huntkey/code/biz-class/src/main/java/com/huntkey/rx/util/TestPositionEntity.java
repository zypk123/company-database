package com.huntkey.rx.util;

import com.huntkey.rx.base.PropertyAnnotation;

/**
 * Created by gaozhiy on 2017/11/16 0016.
 */
public class TestPositionEntity {
    @PropertyAnnotation(fomula="123",limitFomula="345")
    private String id;
    @PropertyAnnotation(fomula="123",limitFomula="345")
    private String name;
    @PropertyAnnotation(fomula="123",limitFomula="345")
    private String pos;
    @PropertyAnnotation(fomula="123",limitFomula="345")
    private int level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
