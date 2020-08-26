# jpacomment-spring-boot-starter
JPA创建或修改数据库的表注释和字段注释


## 说明：
JPA 比较方便，让开发免于手动创建表操作，但有一个问题表中字段无注释，虽然JPA有提供方法，但无法适应所有主流数据库。
JPA 自身提供方法如下：
```java
public class MyEntity {

 @Column(nullable = false,columnDefinition = "int(2) comment '我是年龄注释...'")
 private Integer age;

}
```
其中 **columnDefinition** 其实就是写 Native Sql，这样违背了JPA的初衷“屏蔽底层数据库差异”。

jpacomment-spring-boot-starter 目前适配了三种数据库 Mysql Sqlserver oracle，后期可以添加其他数据库。

jpacomment-spring-boot-starter 的方法很简单将 java属性上的注解注释内容 修改到表字段里面。

用法如下：

在yaml文件中添加
```yaml
middol:
  jpa:
    comment:
      enable: true
```
Entity 实体类里面添加注解 **@TableComment** 和  **@ColumnComment**
```java

package org.example.entity;

import org.middol.starter.jpacomment.annotation.ColumnComment;
import org.middol.starter.jpacomment.annotation.TableComment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "T_SYS_ORG")
@TableComment("组织信息表")
public class SysOrgEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ColumnComment("编号")
    @Column(unique = true)
    private String orgCode;

    @ColumnComment("组织id")
    private String orgId;

    @ColumnComment("组织名称")
    private String orgName;


}
```

调用 service 方法 更新全库或 单表字段注释，这里没有采用启动自动更新字段注释，而采用手动方式调用，
主要考虑表注释一般不会频繁更新。

```java
@RestController
@RequestMapping("api/sys")
public class SetCcommentController {

    @Resource
    JpacommentService jpacommentService;

   /**
    * 更新全库字段注释
    */
    @GetMapping("alterAllTableAndColumn")
    public String alterAllTableAndColumn() {
        jpacommentService.alterAllTableAndColumn();
        return "success";
    }

}
```

开启日志打印 application.ymal 中添加
```yaml
logging:
  level:
    root: INFO
    org.middol: DEBUG  # jpacomment-spring-boot-starter日志打印
```
控制台可以打印如下信息：


```
。。。
2020-08-26 15:30:53.227 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_ORG 字段 orgId 的注释为 '性别 1 男 2 女' 
2020-08-26 15:30:53.230 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_ORG 字段 orgName 的注释为 '性别 1 男 2 女'
2020-08-26 15:30:53.232 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_ORG 字段 RVERSION 的注释为 '乐观锁版本号'
2020-08-26 15:30:53.235 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_ORG 字段 CREATE_DATE 的注释为 '创建时间'
2020-08-26 15:30:53.237 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_ORG 字段 CREATE_USER 的注释为 '创建人员信息'
2020-08-26 15:30:53.240 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_ORG 字段 UPADATE_DATE 的注释为 '更新时间'
2020-08-26 15:30:53.242 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_ORG 字段 UPDATE_USER 的注释为 '更新人员信息'
2020-08-26 15:30:53.245 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_ORG 字段 REMARK 的注释为 '备注'
2020-08-26 15:30:53.248 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_USER 的注释为 '用户信息表'
2020-08-26 15:30:53.250 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_USER 字段 SEX 的注释为 '性别 1 男 2 女'
2020-08-26 15:30:53.253 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_USER 字段 ADDRESS 的注释为 '地址'
2020-08-26 15:30:53.256 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_USER 字段 RVERSION 的注释为 '乐观锁版本号'
2020-08-26 15:30:53.258 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_USER 字段 CREATE_DATE 的注释为 '创建时间'
2020-08-26 15:30:53.261 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_USER 字段 CREATE_USER 的注释为 '创建人员信息'
2020-08-26 15:30:53.264 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_USER 字段 UPADATE_DATE 的注释为 '更新时间'
2020-08-26 15:30:53.266 DEBUG 10476 --- [nio-8848-exec-3] c.m.s.j.service.JpacommentService        : 修改表 T_SYS_USER 字段 UPDATE_USER 的注释为 '更新人员信息'
。。。
```