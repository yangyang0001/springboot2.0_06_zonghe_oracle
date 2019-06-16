package com.inspur.generator_code.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yang
 * @since 2019-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@KeySequence(value = "SEQ_TEACHER", clazz = Long.class)
@TableName("TABLE_TEACHER")
public class Teacher extends Model<Teacher> implements Serializable{

    private static final long serialVersionUID = 2169828065846096234L;
    @TableId("ID")
    private Long id;

    @TableField("TEACHER_NAME")
    private String teacherName;

    @TableField("TEACHER_AGE")
    private Integer teacherAge;

    @TableField("DELETE_FLAG")
    private String deleteFlag;

    @TableField("VERSION")
    private Long version;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
