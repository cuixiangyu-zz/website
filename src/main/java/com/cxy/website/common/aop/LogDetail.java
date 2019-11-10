package com.cxy.website.common.aop;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA
 *操作日志记录相关注解类
 *
 * @author zhanglibin
 * @date 2019/10/12
 */
//@LogDetail(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogDetail {

	/**
	 * 操作详情,可使用占位符获取参数:{{tel}}
	 */
	String detail() default "";

	/**
	 * 操作类型：主要是新增、修改、删除
	 */
	String logType() default "";

	/**
	  *被操作的模块 :可以是任何对象，如表名(user)
	 */
	String logModule() default "";
	
	int type() default 0;
}