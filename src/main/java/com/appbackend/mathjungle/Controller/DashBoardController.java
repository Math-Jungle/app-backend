package com.appbackend.mathjungle.Controller;

import com.appbackend.mathjungle.DTO.DashBoardDTO;
import com.appbackend.mathjungle.Mapper.DashBoardMapper;
import com.appbackend.mathjungle.Model.DashBoard;
import com.appbackend.mathjungle.Service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    @Autowired
    DashBoardService dashBoardService;


    @PostMapping("/save-userdata")
    public ResponseEntity<?> saveData(@RequestBody DashBoard dashBoardData , Principal principal) {

        if (principal == null){
            return new ResponseEntity<>("Invalid JWT token",HttpStatus.UNAUTHORIZED);
        }
        System.out.println("Token is valid");
        dashBoardService.saveData(dashBoardData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get-userdata")
    public ResponseEntity<?> getData(Principal principal) {

        if (principal == null){
            return new ResponseEntity<>("Invalid JWT token",HttpStatus.UNAUTHORIZED);

        }
        DashBoard data=dashBoardService.getDashBoardData(principal.getName());
        if (data == null){
            return  ResponseEntity.ok(null);
        }
        DashBoardDTO dashboardData = DashBoardMapper.toDTO(data);
        return new ResponseEntity<>(dashboardData,HttpStatus.OK);


    }


}
