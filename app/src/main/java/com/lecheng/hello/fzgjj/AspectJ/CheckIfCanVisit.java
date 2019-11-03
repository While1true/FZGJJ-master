package com.lecheng.hello.fzgjj.AspectJ;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 不听话的好孩子 on 2018/4/23.
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface CheckIfCanVisit {
}
