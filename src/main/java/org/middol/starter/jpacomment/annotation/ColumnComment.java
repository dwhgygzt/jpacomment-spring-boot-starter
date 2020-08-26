package org.middol.starter.jpacomment.annotation;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:guzhongtao@middol.com">guzhongtao</a>
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ColumnComment {

    /**
     * 字段注释
     *
     * @return String
     */
    String value() default "";
}
