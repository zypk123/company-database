package com.huntkey.rx.sceo.formula.provider.function.definefunc.listener;

import java.util.Map;

/**
 * @author chenfei1 on 2017/8/5 0005.
 */
public final class ListenerUtils {

    public static void parseNodeData2Map(String nodeData, Map<Long, String> cache) {

        if (null != nodeData) {
            String[] nodeArr = nodeData.split(";");
            for (String node : nodeArr) {

                if (node.trim().length() == 0) {
                    continue;
                }

                try {
                    String[] arr = node.split(":");
                    Long timestamp = Long.parseLong(arr[0]);
                    String compiledId = arr[1];
                    cache.put(timestamp, compiledId);
                } catch (NumberFormatException e) {
                    // ignore.
                }
            }
        }
    }
}
