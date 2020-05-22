package com.multi.ds.demo.biz.module.controller;

import com.multi.ds.demo.biz.module.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public String test() {
        testService.test();
        return "ok";
    }
}
