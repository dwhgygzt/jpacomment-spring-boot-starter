package org.middol.starter.jpacomment.service.impl;

import cn.hutool.core.util.StrUtil;
import org.middol.starter.jpacomment.service.AlterCommentService;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 修改表注释和字段注释
 *
 * @author <a href="mailto:guzhongtao@middol.com">guzhongtao</a>
 */
public class MysqlAlterCommentServiceImpl implements AlterCommentService {
    private String schema;

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 1数据库名称 2表名称 3注释
     */
    String updateTableComment = " ALTER TABLE %s.%s COMMENT = ?";

    /**
     * 1数据库名称 2表名称 3字段名称
     */
    String getUpdateColumnComment = " SELECT CONCAT('ALTER TABLE `',a.TABLE_SCHEMA,'`.`',a.TABLE_NAME,'` MODIFY COLUMN `',a.COLUMN_NAME,'` ',a.COLUMN_TYPE,\n" +
            " (CASE WHEN a.IS_NULLABLE = 'NO' THEN ' NOT NULL ' ELSE\t'' END), \n" +
            " (CASE WHEN a.COLUMN_DEFAULT IS NOT NULL THEN CONCAT(' DEFAULT ''',a.COLUMN_DEFAULT,''' ') ELSE\t'' END) ,' COMMENT ?') ALTER_SQL\n" +
            "FROM information_schema.`COLUMNS` a\n" +
            "WHERE a.TABLE_SCHEMA = ? \n" +
            "AND a.TABLE_NAME = ?\n" +
            "AND a.COLUMN_NAME = ? ";

    @Override
    public void alterTableComment(String tableName, String tableComment) {
        jdbcTemplate.update(String.format(updateTableComment, "`" + schema + "`", "`" + tableName + "`"), tableComment);
    }

    @Override
    public void alterColumnComment(String tableName, String columnName, String columnComment) {
        String updateColumnComment = jdbcTemplate.queryForObject(getUpdateColumnComment, String.class, schema, tableName, columnName);
        if (StrUtil.isNotBlank(updateColumnComment)) {
            jdbcTemplate.update(updateColumnComment, columnComment);
        }
    }

    @Override
    public String getSchema() {
        return schema;
    }
}
