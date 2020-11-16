package com.zongze.freemarker.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dalaoyang
 * @Description
 * @project springboot_learn
 * @package com.dalaoyang.controller
 * @email 397600342@qq.com
 * @date 2018/3/14
 */
@Controller
public class TestController {


    @GetMapping("test")
    public String testFreemarker(ModelMap modelMap){
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setBirthday(new Date());
        user.add("李四");
        user.add("王五");
        user.add("赵柳");
        modelMap.addAttribute("user", user);
        return "test";
    }



    @GetMapping("test2")
    public String testFreemarker2(HttpServletResponse response) throws IOException {
        Map<String,String>model = new HashMap<>();
        model.put("msg", "这时一个导出pdf测试");
        model.put("image", "https://statics.haibaobaoxian.com/cw/image/1598952893513.jpg");
        String html = FreemarkerUtil.createHTML("freemarker.ftl",model);
        System.out.println(html);
        response.setHeader("Content-disposition", "attachment; filename=" + new String("pdf导出测试".getBytes("utf-8"), "iso8859-1") + ".pdf");
        response.setContentType("application/pdf");
        PDFUtils.writeStringToOutputStreamAsPDF(html, response.getOutputStream());
        return "success";
    }
}