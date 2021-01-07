package com.xyy.mail.mail.service.impl;

import com.xyy.mail.mail.entity.Infomation;
import com.xyy.mail.mail.mapper.InfomationMapper;
import com.xyy.mail.mail.service.InfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yayu
 * @title: InfomationImpl
 * @description: TODO
 * @date 2021/1/6 17:17
 */
@Service
public class InfomationImpl implements InfomationService {
    @Autowired
    private InfomationMapper infomationMapper;
    @Override
    public List<Infomation> findAllInfomation() {
        return infomationMapper.findAllInfomation();
    }
}
