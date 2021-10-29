package com.ws.node;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: sq
 * @Date: 2021/10/28 16:48
 */
public interface INode  extends Serializable {

    Long getId();

    Long getParentId();

    List<INode> getChildren();
}
