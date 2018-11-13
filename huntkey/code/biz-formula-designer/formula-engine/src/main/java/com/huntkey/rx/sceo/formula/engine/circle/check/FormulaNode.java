package com.huntkey.rx.sceo.formula.engine.circle.check;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfei on 2017/7/3.
 */
public class FormulaNode {

    private String nodeName;
    private List<FormulaNode> refList;

    public FormulaNode(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<FormulaNode> getRefList() {
        return refList;
    }

    public void setRefList(List<FormulaNode> refList) {
        this.refList = refList;
    }

    public void addSubRef(FormulaNode subNode) {
        if (null == this.refList) {
            this.refList = new ArrayList<FormulaNode>();
        }

        this.refList.add(subNode);
    }

    @Override
    public String toString() {
        return "FormulaNode{" +
                "nodeName='" + nodeName + '\'' +
                ", refList=" + refList +
                '}';
    }
}
