package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.Owner;
import com.mamp.software.condadmin.services.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ownersRest")
public class OwnerRestController {

    @Autowired
    private IOwnerService srvOwner;

   @GetMapping(value = "/listByCondom/{id}")
    public List<Owner> getAllEmployees(@PathVariable("id") Integer id){
        return srvOwner.findByCondom(id);
    }

    @GetMapping(value = "/list")
    public List<Owner> getAllEmployees(){
        List<Owner> list =srvOwner.findAll();
       return list;
    }
}
