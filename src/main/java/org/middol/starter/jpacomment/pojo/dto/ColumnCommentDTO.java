package org.middol.starter.jpacomment.pojo.dto;


import java.io.Serializable;

/**
 * 表字段信息
 * @author guzt
 */
public class ColumnCommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ColumnCommentDTO{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
