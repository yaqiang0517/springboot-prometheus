package com.prometheus.prometheus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Test
 * @Description TODO
 * @Author zhangyq
 * @Date 2018/10/30 14:33
 **/
@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("/test")
    public String test(){
        return "success";
    }
}
