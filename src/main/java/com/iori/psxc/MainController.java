package com.iori.psxc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {

    @Autowired
    private CustomService customService;

    @Autowired
    private MailUtil mailUtil;

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String post(Custom custom){
        int count = customService.insert(custom);
        if(count == 1){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mailUtil.sendMail(custom);
                }
            }).start();
        }
        return count + "";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String get(){
        return "index";
    }
}
