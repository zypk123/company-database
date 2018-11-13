package com.huntkey.rx.sceo.formula.engine.circle.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * just impl the circle detect logic
 *
 * @author chenfei on 2017/6/23.
 */
public class CircleChecker {

    private Map<FormulaNode, Integer> counterMap = new HashMap<FormulaNode, Integer>();

    public CircleMapResult checkCircle(FormulaNode node) {

        CircleMapResult cr = new CircleMapResult();
        cr.setHasCircle(false);

        // record the counter.
        if (counterMap.containsKey(node)) {
            counterMap.put(node, counterMap.get(node) + 1);
        } else {
            counterMap.put(node, 1);
        }

        // detect circle.
        // find the circle.
        for (FormulaNode fn : counterMap.keySet()) {
            if (counterMap.get(fn) > 1) {

                cr.setDuplicatedRefNodeInfo(fn.getNodeName() + " has been referenced duplicatedly.");
            }
        }

        return cr;
    }

    /**
     * print the simple circle map.
     * <p>
     * if has not a circle, it will throw an runtime exception.
     *
     * @param starter
     * @param buff
     * @return
     */
    private String calcCircleMap(FormulaNode starter, FormulaNode nextNode, StringBuffer buff) {

        buff.append(nextNode.getNodeName() + " --> ");
        System.out.println(buff.toString());
        List<FormulaNode> refList = nextNode.getRefList();

        // TODO some bugs here.
        for (FormulaNode fn : refList) {

            if (starter.equals(fn)) {
                buff.append(fn.getNodeName() + " --> ");
                return buff.toString();
            } else if (null != fn.getRefList()) {
                StringBuffer buffSub = new StringBuffer();
                buffSub.append(buff);
                calcCircleMap(starter, fn, buffSub);
            } else {
                continue;
            }
        }

        throw new RuntimeException("Something wrong is here.");
    }

//    public static void main(String[] args) {
//
//        CircleChecker cc = new CircleChecker();
//
//        FormulaNode a = new FormulaNode("A");
//        System.out.println(cc.checkCircle(a));
//
//        FormulaNode b = new FormulaNode("B");
//        a.addSubRef(b);
//        System.out.println(cc.checkCircle(b));
//
//        FormulaNode c = new FormulaNode("C");
//        a.addSubRef(c);
//        System.out.println(cc.checkCircle(c));
//
//        FormulaNode d = new FormulaNode("D");
//        c.addSubRef(d);
//        System.out.println(cc.checkCircle(d));
//
//        FormulaNode e = new FormulaNode("E");
//        c.addSubRef(e);
//        System.out.println(cc.checkCircle(e));
//
//        e.addSubRef(c);
//        System.out.println(cc.checkCircle(c));
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CircleChecker that = (CircleChecker) o;

        return counterMap != null ? counterMap.equals(that.counterMap) : that.counterMap == null;
    }

    @Override
    public int hashCode() {
        return counterMap != null ? counterMap.hashCode() : 0;
    }
}

