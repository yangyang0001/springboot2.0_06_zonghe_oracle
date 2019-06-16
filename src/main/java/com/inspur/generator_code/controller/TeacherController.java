package com.inspur.generator_code.controller;

import com.inspur.generator_code.entity.Teacher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yang
 * @since 2019-06-16
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@RequestMapping("/insertTeacher")
	public String insertTeacher(){
		Teacher teacher = new Teacher();
		teacher.setTeacherName("zhangsan");
		teacher.setTeacherAge(22);
		teacher.setVersion(1L);
		teacher.setDeleteFlag("0");
		boolean flag = teacher.insert();
		return String.valueOf(flag);
	}

}

