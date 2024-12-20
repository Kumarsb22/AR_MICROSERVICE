package com.iesarapi.rest;

import com.iesarapi.bindingclass.AppForm;
import com.iesarapi.service.IesAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IesAppRestController {
    Logger logger= LoggerFactory.getLogger(IesAppRestController.class);

    private IesAppService  iesAppService;

    @Autowired
    public IesAppRestController(IesAppService iesAppService){
        this.iesAppService=iesAppService;
    }

    @PostMapping("/app")
    public ResponseEntity<String> createApp(@RequestBody AppForm appForm){
        String app = this.iesAppService.createApp(appForm);
        logger.info("Application created successfully");
        return new ResponseEntity<>(app, HttpStatus.CREATED);
    }

    @GetMapping("/apps/{userId}")
    public ResponseEntity<List<AppForm>> fetchApps(@PathVariable("userId") Integer userId){
        logger.info("Applications fetched successfully");
        return new ResponseEntity<>(this.iesAppService.fetchApps(userId),HttpStatus.OK );
    }


}
