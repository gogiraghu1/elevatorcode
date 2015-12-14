package com.rgogi.ecs.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.rgogi.ecs.impl.Elevator;
import com.rgogi.ecs.impl.ElevatorControlSystem;
import com.rgogi.ecs.impl.exceptions.InvalidNumber;
/**
 * 
 * Simulator for unit testing elevator control system
 * 
 * @author rgogi
 *
 */
public class ElevatorControlSystemTest {
  public static final int ELEVATOR_ID_ZERO = 0;
  public static final int ELEVATOR_ID_ONE = 1;
  public static final int TENTH_FLOOR = 10;
  public static final int FIRST_FLOOR = 1;
  public static final int SEVENTH_FLOOR = 8;
  public static final int FOURTH_FLOOR = 4;
  private ElevatorControlSystem elevatorControlSystem;
  private ArrayList<Elevator> elevators;
  @Before
  public void initialize(){
    try {
      elevatorControlSystem = new ElevatorControlSystem(4, 20);
    } catch (InvalidNumber invalidNumber) {
      invalidNumber.printStackTrace();
    }
  }

  @Test
  public void testRequestingOneElevator(){
    elevatorControlSystem.pickUp(TENTH_FLOOR);
    for(int idx=0;idx<TENTH_FLOOR;idx++){
      elevatorControlSystem.step();
    }
    elevators = elevatorControlSystem.getElevators();
    assertEquals(TENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
  }
  @Test
  public void testElevatorTwoNotMoving(){
    elevatorControlSystem.pickUp(TENTH_FLOOR);
    for(int idx=0;idx<TENTH_FLOOR;idx++){
      elevatorControlSystem.step();
    }
    elevators = elevatorControlSystem.getElevators();
    assertEquals(FIRST_FLOOR, elevators.get(ELEVATOR_ID_ONE).currentFloor());
  }

  @Test
  public void testCallingTwoElevators(){
    elevatorControlSystem.pickUp(TENTH_FLOOR);
    elevatorControlSystem.pickUp(SEVENTH_FLOOR);
    for(int idx=0;idx<TENTH_FLOOR;idx++){
      elevatorControlSystem.step();
    }
    elevators = elevatorControlSystem.getElevators();
    assertEquals(TENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
    assertEquals(SEVENTH_FLOOR, elevators.get(ELEVATOR_ID_ONE).currentFloor());
  }
  
  @Test
  public void testCallingThreeElevators(){
    elevatorControlSystem.pickUp(TENTH_FLOOR);
    elevatorControlSystem.pickUp(SEVENTH_FLOOR);
    elevatorControlSystem.pickUp(FOURTH_FLOOR);
    for(int idx=0;idx<TENTH_FLOOR;idx++){
      elevatorControlSystem.step();
    }
    elevators = elevatorControlSystem.getElevators();
    assertEquals(TENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
    assertEquals(SEVENTH_FLOOR, elevators.get(ELEVATOR_ID_ONE).currentFloor());
  }

  @Test
  public void testSendingElevatorToDestination(){
    elevatorControlSystem.destination(ELEVATOR_ID_ZERO, TENTH_FLOOR);
    for(int idx=0;idx<TENTH_FLOOR;idx++){
      elevatorControlSystem.step();
    }
    elevators = elevatorControlSystem.getElevators();
    assertEquals(TENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
  }

  @Test
  public void testSendingElevatorToMultipleDestinations(){
    elevatorControlSystem.destination(ELEVATOR_ID_ZERO, TENTH_FLOOR);
    elevatorControlSystem.destination(ELEVATOR_ID_ZERO, SEVENTH_FLOOR);
    for(int idx=0;idx<TENTH_FLOOR;idx++){
      elevatorControlSystem.step();
    }
    elevators = elevatorControlSystem.getElevators();
    assertEquals(TENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
    for(int idx=0;idx<TENTH_FLOOR-SEVENTH_FLOOR;idx++){
      elevatorControlSystem.step();
    }
    elevators = elevatorControlSystem.getElevators();
    assertEquals(SEVENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
  }

}
