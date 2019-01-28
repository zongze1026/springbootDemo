package com.zongze.controller;
import com.alibaba.fastjson.JSON;
import com.zongze.entity.Thymeleaf;
import com.zongze.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create By xzz on 2019/1/28
 */
@Controller
public class ThymeleafController {


    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("test","my first thymeleaf test");

        Thymeleaf thymeleaf = new Thymeleaf();
        thymeleaf.setName("zhangsan");
        thymeleaf.setSize(18);
        thymeleaf.setDate(new Date());
        thymeleaf.setResult(false);

        List<Thymeleaf>list = new ArrayList<>();

        Thymeleaf thymeleaf1 = new Thymeleaf();
        thymeleaf1.setName("李四");
        list.add(thymeleaf1);

        list.add(thymeleaf);
        model.addAttribute("user",thymeleaf);
        model.addAttribute("list",list);

        return "index";
    }


    @RequestMapping("/ajax")
    @ResponseBody
    public String ajax(@RequestBody User user){
        System.out.println(JSON.toJSONString(user));
        return "hellow";
    }





}
