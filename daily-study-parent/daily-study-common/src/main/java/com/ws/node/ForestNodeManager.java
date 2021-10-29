package com.ws.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: sq
 * @Date: 2021/10/28 16:50
 */
public class ForestNodeManager<T extends INode> {

    private List<T> list;

    private List<Long> parentIds = new ArrayList<>();

    public ForestNodeManager(List<T> items) {
        this.list = items;
    }

    public INode getTreeNodeAT(Long id) {
        for (INode forestNode : this.list) {
            if (forestNode.getId().longValue() == id.longValue()){
                return forestNode;
            }

        }
        return null;
    }

    public void addParentId(Long parentId) {
        this.parentIds.add(parentId);
    }

    public List<T> getRoot() {
        List<T> roots = new ArrayList<>();
        for (INode iNode : this.list) {
            if (iNode.getParentId().longValue() == 0L || this.parentIds.contains(iNode.getId())){
                roots.add((T)iNode);
            }
        }
        return roots;
    }
}
