package com.rgogi.ecs.rest;


import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.rgogi.ecs.impl.Elevator;
import com.rgogi.ecs.impl.ElevatorControlSystem;
import com.rgogi.ecs.impl.exceptions.InvalidNumber;


@Path("/elevators")
public class ElevatorService {
	
	private ElevatorControlSystem elevatorControlSystem;
	  private ArrayList<Elevator> elevators;

   public void initialize(){
	    try {
	      elevatorControlSystem = new ElevatorControlSystem(4, 20);
	    } catch (InvalidNumber invalidNumber) {
	      invalidNumber.printStackTrace();
	    }
   }
	
	@GET
	@Path("/{floor}")
	public Response getMsg(@PathParam("floor") int number) {
		 initialize();
		 elevatorControlSystem.pickUp(number);
		    for(int idx=0;idx<number;idx++){
		      elevatorControlSystem.step();
		    }
		    elevators = elevatorControlSystem.getElevators();
		String output ="";    
		for(int i =0; i < 4; i++) 
			output = output +  "Elevator number {" + i + "}" + elevators.get(i).currentFloor() + "\n";
        
			return Response.status(200).entity(output).build();

	}

}