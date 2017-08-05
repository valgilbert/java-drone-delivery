package com.coding.exercise.dronedelivery.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coding.exercise.dronedelivery.core.model.AssignRequest;
import com.coding.exercise.dronedelivery.core.model.AssignResponse;
import com.coding.exercise.dronedelivery.core.model.Drone;
import com.coding.exercise.dronedelivery.core.model.Package;
import com.coding.exercise.dronedelivery.core.service.DroneService;
import com.coding.exercise.dronedelivery.core.service.PackageService;

@Controller
@RequestMapping(value = "/dronedelivery")
public class DroneDeliveryController {

    @Autowired
    private DroneService droneService;

    @Autowired
    private PackageService packageService;

    public DroneDeliveryController() {
	super();
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    @ResponseBody
    public AssignResponse assign(@RequestBody AssignRequest request)
	    throws Exception {
	return droneService.assignPackagesToDrones(request.getDrones(),
		request.getPackages());
    }

    @RequestMapping(value = "/drones", method = RequestMethod.GET)
    @ResponseBody
    public List<Drone> getDrones() throws Exception {
	return droneService.getDrones();
    }

    @RequestMapping(value = "/packages", method = RequestMethod.GET)
    @ResponseBody
    public List<Package> getPackages() throws Exception {
	return packageService.getPackages();
    }

}
