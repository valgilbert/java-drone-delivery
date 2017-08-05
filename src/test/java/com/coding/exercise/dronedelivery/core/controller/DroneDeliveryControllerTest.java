package com.coding.exercise.dronedelivery.core.controller;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.coding.exercise.dronedelivery.core.model.AssignRequest;
import com.coding.exercise.dronedelivery.core.model.AssignResponse;
import com.coding.exercise.dronedelivery.core.model.Assignment;
import com.coding.exercise.dronedelivery.core.model.Drone;
import com.coding.exercise.dronedelivery.core.model.Package;
import com.coding.exercise.dronedelivery.core.service.DroneService;
import com.coding.exercise.dronedelivery.core.service.PackageService;

public class DroneDeliveryControllerTest {

    @Mock
    private DroneService droneService;

    @Mock
    private PackageService packageService;

    @InjectMocks
    private DroneDeliveryController controller;

    @Before
    public void before() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDrones() throws Exception {
	Mockito.when(droneService.getDrones()).thenReturn(
		new ArrayList<Drone>());
	controller.getDrones();
	Mockito.verify(droneService, Mockito.times(1)).getDrones();
    }

    @Test
    public void testGetPackages() throws Exception {
	Mockito.when(packageService.getPackages()).thenReturn(
		new ArrayList<Package>());
	controller.getPackages();
	Mockito.verify(packageService, Mockito.times(1)).getPackages();
    }

    @Test
    public void testAssign() throws Exception {
	AssignResponse response = new AssignResponse(
		new ArrayList<Assignment>(), new ArrayList<Integer>());
	AssignRequest request = new AssignRequest();

	Mockito.when(
		droneService.assignPackagesToDrones(new ArrayList<Drone>(),
			new ArrayList<Package>())).thenReturn(response);
	controller.assign(request);
	Mockito.verify(droneService, Mockito.times(1));
    }
}
