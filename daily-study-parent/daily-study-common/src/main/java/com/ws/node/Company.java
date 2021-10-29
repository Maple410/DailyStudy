package com.ws.node;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sq
 * @Date: 2021/10/28 17:51
 */
@Data
public class Company extends Model<Company> implements INode {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long parentId;

    private String deptName;

    private List<INode> children = new ArrayList<>();

    public Company(Long id, Long parentId, String deptName) {
        this.id = id;
        this.parentId = parentId;
        this.deptName = deptName;
    }
}
