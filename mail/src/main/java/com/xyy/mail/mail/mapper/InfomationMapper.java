package com.xyy.mail.mail.mapper;

import com.xyy.mail.mail.entity.Infomation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yayu
 * @title: InfomationMapper
 * @description: TODO
 * @date 2021/1/6 17:18
 */
@Repository
public interface InfomationMapper {
    List<Infomation> findAllInfomation();

}
