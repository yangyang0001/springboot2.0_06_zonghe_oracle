package com.inspur.datasource;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 测试自动填充的效果
 * @TableField(.. fill = FieldFill.INSERT)
 * 这里仅仅测试对table_dept表 中name 字段的 的填充
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		Object object = getFieldValByName("deptName", metaObject);
		if(object == null){
			this.setFieldValByName("deptName", "国防部Insert", metaObject);//版本号3.0.6以及之前的版本
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Object object = getFieldValByName("deptName", metaObject);
		if(object == null){
			this.setFieldValByName("deptName", "国防部Update", metaObject);//版本号3.0.6以及之前的版本
		}
	}
}