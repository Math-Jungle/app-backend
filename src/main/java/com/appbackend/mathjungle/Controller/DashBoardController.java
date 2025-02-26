package com.appbackend.mathjungle.Controller;

import com.appbackend.mathjungle.Service.AcheivementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/dashboard")
public class DashBoardController {

    @Autowired
    AcheivementsService aService;

}
