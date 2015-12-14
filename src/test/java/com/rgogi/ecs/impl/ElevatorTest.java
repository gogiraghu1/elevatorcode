package com.rgogi.ecs.impl;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.rgogi.ecs.impl.enums.*;
import com.rgogi.ecs.impl.enums.ElevatorStatus;

/**
 * 
 * Simulator for testing elevator control system
 * 
 * @author rgogi
 *
 */
public class ElevatorTest {
  public static final int TENTH_FLOOR = 10;
  public static final int SECOND_FLOOR = 2;
  public static final int BASEMENT_TWO = -2;

  private Elevator elevator;

  @Before
  public void initializeElevator(){
    elevator = new Elevator(0);
  }

  @Test
  public void testAddingDestination(){
    elevator.addNewDestinatoin(TENTH_FLOOR);
    assertEquals(TENTH_FLOOR, elevator.nextDestionation());
  }

  @Test
  public void checkCurrentFloor(){
    // Move to floor 2
    elevator.moveUp();
    elevator.moveUp();
    assertEquals(SECOND_FLOOR, elevator.currentFloor());
  }

  @Test
  public void checkMoveDown(){
    elevator.moveDown();
    elevator.moveDown();
    assertEquals(BASEMENT_TWO, elevator.currentFloor());
  }

  @Test
  public void checkDirectionUp(){
    elevator.addNewDestinatoin(SECOND_FLOOR);
    assertEquals(ElevatorState.ELEVATOR_UP, elevator.state());
  }

  @Test
  public void checkDirectionDown(){
    elevator.addNewDestinatoin(BASEMENT_TWO);
    assertEquals(ElevatorState.ELEVATOR_DOWN, elevator.state());
  }

  @Test
  public void checkDirectionHold(){
    assertEquals(ElevatorState.ELEVATOR_HOLD, elevator.state());
  }

  @Test
  public void checkStatusEmpty(){
    assertEquals(ElevatorStatus.ELEVATOR_EMPTY, elevator.status());
  }

  @Test
  public void checkStatusOccupied(){
    elevator.addNewDestinatoin(TENTH_FLOOR);
    assertEquals(ElevatorStatus.ELEVATOR_OCCUPIED, elevator.status());
  }
}
