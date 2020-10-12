package com.zongze.freemarker.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

/**
 * @Date 2020/10/12 11:16
 * @Created by xzz
 */
public class FreemarkerUtil {

    private static Locale locale = new Locale("zh", "CN");
    private static Configuration configuration = new Configuration();


    public static String createHTML(String templatePath,Map<String,String> model) throws IOException {
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(FreemarkerUtil.class, "/templates/");
        return processString(templatePath,model);
    }


    private static  String processString(String templFilePath, Map model)
            throws IOException {

        Template template = null;
        StringWriter writer = new StringWriter();
        try {
            template = configuration.getTemplate(templFilePath);
            template.setLocale(locale);
            template.process(model, writer);
            return writer.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (writer != null) {
                writer.flush();
                writer.close();
            }

        }
        return "";
    }







}
