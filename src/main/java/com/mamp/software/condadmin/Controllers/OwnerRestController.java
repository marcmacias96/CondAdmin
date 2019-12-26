package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.Owner;
import com.mamp.software.condadmin.services.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "ownerRest")
public class OwnerRestController {

    @Autowired
    private IOwnerService srvOwner;

    @RequestMapping(value = "/listByCondm/{id}", method= RequestMethod.GET)
    public List<Owner> getAllEmployees(@PathVariable("id") Integer id){
        return srvOwner.findByCondom(id);
    }

    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public List<Owner> getAllEmployees(){
        return srvOwner.findAll();
    }
}
