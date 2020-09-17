package com.xyy.mail.mail.mapper;

import com.xyy.mail.mail.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * @author yayu
 * @title: AddUserMapper
 * @description: TODO
 * @date 2020/9/1716:34
 */
@Repository
public interface AddUserMapper {
    void addStudent(Student student);
}
