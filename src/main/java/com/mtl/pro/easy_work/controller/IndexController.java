package com.mtl.pro.easy_work.controller;

import com.mtl.pro.easy_work.common.StaticFileds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;


@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "/index.html";
    }

    @GetMapping("/reminderTimes")
    @ResponseBody
    public Set<String> reminderTimes(){
        return StaticFileds.getTimes();
    }
}
