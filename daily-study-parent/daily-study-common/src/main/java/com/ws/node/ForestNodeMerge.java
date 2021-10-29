package com.ws.node;

import java.util.List;

/**
 * @Author: sq
 * @Date: 2021/10/28 16:49
 */
public class ForestNodeMerge {

    public static <T extends INode> List<T> merge(List<T> items) {
        ForestNodeManager<T> forestNodeManager = new ForestNodeManager(items);
        items.forEach((forestNode) -> {
            if (forestNode.getParentId() != 0L) {
                INode node = forestNodeManager.getTreeNodeAT(forestNode.getParentId());
                if (node != null) {
                    node.getChildren().add(forestNode);
                } else {
                    forestNodeManager.addParentId(forestNode.getId());
                }
            }

        });
        return forestNodeManager.getRoot();
    }
}
