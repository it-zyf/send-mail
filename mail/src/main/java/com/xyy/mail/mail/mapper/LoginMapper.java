package com.xyy.mail.mail.mapper;

import com.xyy.mail.mail.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yayu
 * @title: LoginMapper
 * @description: TODO
 * @date 2020/10/10 15:14
 */
@Repository
public interface LoginMapper {
    Student findStudentByName(@Param("name") String name);
}
