package com.solution.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solution.demo.model.Vehicle;



@RestController
public class VehicleController {
	@Bean()
	@Scope(value = "singleton")
	private List<Vehicle> getVechileList() {
		return new ArrayList<Vehicle>();
	}

	@Autowired()
	List<Vehicle> vehicleList;

	@RequestMapping("/vehicle-api/v1/vehicles/vehicle")
	public ResponseEntity getEmployees(@RequestBody Vehicle vehicle) {
	Logger logger =Logger.getLogger(" VehicleController");
		try {
			logger.info(vehicle.toString());
			if("MANUAL".equals(vehicle.getTransmissionType())|| "Automatic".equals(vehicle.getTransmissionType()) ){
				throw new Exception("Invalid Transmisson Type");
			}
			vehicleList.add(vehicle);
		} catch (Exception e) {
			return ResponseEntity.ok().header("vehicle_id", UUID.randomUUID().toString()).body(e.getMessage());
		}
		logger.info("Completed");
		return ResponseEntity.ok().header("vehicle_id", UUID.randomUUID().toString()).build();
	}

}
