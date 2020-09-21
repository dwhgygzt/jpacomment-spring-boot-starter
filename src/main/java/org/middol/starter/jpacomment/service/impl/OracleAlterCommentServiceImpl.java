package org.middol.starter.jpacomment.service.impl;

import org.middol.starter.jpacomment.service.AlterCommentService;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 修改表注释和字段注释
 *
 * @author <a href="mailto:guzhongtao@middol.com">guzhongtao</a>
 */
public class OracleAlterCommentServiceImpl implements AlterCommentService {
    private String schema;

    @Override
    public void setSchema(String schema) {
        this.schema = schema;
    }

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 1schema 2表名称 3注释
     */
    String updateTableComment = "COMMENT ON TABLE %s.%s IS '%s'";

    /**
     * 1schema 2表名称 3字段名称 4注释
     */
    String updateColumnComment = "COMMENT ON COLUMN %s.%s.%s IS '%s'";

    @Override
    public void alterTableComment(String tableName, String tableComment) {
        jdbcTemplate.update(String.format(updateTableComment, schema, tableName.toUpperCase(), tableComment));
    }

    @Override
    public void alterColumnComment(String tableName, String columnName, String columnComment) {
        jdbcTemplate.update(String.format(updateColumnComment, schema, tableName.toUpperCase(), columnName.toUpperCase(), columnComment));
    }

    @Override
    public String getSchema() {
        return schema;
    }
}
