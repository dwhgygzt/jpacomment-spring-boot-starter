package org.middol.starter.jpacomment.annotation;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:guzhongtao@middol.com">guzhongtao</a>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TableComment {

    /**
     * 字段注释
     *
     * @return String
     */
    String value() default "";
}
