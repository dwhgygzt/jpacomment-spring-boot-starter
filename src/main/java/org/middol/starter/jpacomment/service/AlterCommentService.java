package org.middol.starter.jpacomment.service;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 修改表注释和字段注释
 *
 * @author <a href="mailto:guzhongtao@middol.com">guzhongtao</a>
 */
public interface AlterCommentService {

    /**
     * 获取当前数据库 schema
     *
     * @return 数据库 schema
     */
    String getSchema();

    /**
     * 设置当前的schema
     */
    void setSchema(String schema);

    /**
     * 修改表注释
     *
     * @param tableName    表名称
     * @param tableComment 表注释
     */
    void alterTableComment(String tableName, String tableComment);


    /**
     * 修改表字段注释
     *
     * @param tableName     表名称
     * @param columnName    字段名称
     * @param columnComment 字段注释
     */
    void alterColumnComment(String tableName, String columnName, String columnComment);


    /**
     * 获取  jdbcTemplate
     *
     * @param jdbcTemplate jdbcTemplate
     */
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}
