package com.dahua.oz.t_annotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 传入包名和微信的模板代码，绕过微信限制不需要在主程序中完成这些代码
 *
 * @author O.z Young
 * @version 2018/4/16
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    /**
     * 获取传入的包名
     *
     * @return 包名
     */
    String packageName();

    /**
     * 获取传入的模板
     *
     * @return 模板
     */
    Class<?> entryTemplete();
}
