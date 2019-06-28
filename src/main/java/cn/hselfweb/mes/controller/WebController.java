package cn.hselfweb.mes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class WebController {
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}