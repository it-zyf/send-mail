package com.xyy.mail.mail.controller;

import com.xyy.mail.mail.entity.Infomation;
import com.xyy.mail.mail.service.InfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yayu
 * @title: InfomationController
 * @description: TODO
 * @date 2021/1/6 17:14
 */
@RestController
@RequestMapping("/user")
public class InfomationController {
    @Autowired
    private InfomationService infomationService;

    @RequestMapping("/infomation")
    public List<Infomation> findAllInfomation() {
        return infomationService.findAllInfomation();
    }
}
