package org.middol.starter.jpacomment.pojo.dto;


import java.io.Serializable;
import java.util.List;

/**
 * 表字段信息
 *
 * @author guzt
 */
public class TableCommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String comment;

    private List<ColumnCommentDTO> columnCommentDTOList;

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

    public List<ColumnCommentDTO> getColumnCommentDTOList() {
        return columnCommentDTOList;
    }

    public void setColumnCommentDTOList(List<ColumnCommentDTO> columnCommentDTOList) {
        this.columnCommentDTOList = columnCommentDTOList;
    }

    @Override
    public String toString() {
        return "TableCommentDTO{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", columnCommentDTOList=" + columnCommentDTOList +
                '}';
    }
}
