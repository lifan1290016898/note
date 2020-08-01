package com.demo.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {

    @RequestMapping(value = "/{view}")
    public String forward(@PathVariable("view") String view){
        if(StringUtils.isNotBlank(view)){
            return "/" +view;
        }
        return "";
    }


}
