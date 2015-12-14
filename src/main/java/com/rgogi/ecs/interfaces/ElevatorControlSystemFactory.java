package com.rgogi.ecs.interfaces;

/**
 * 
 * Elevator controller for processing the requests from end user
 * 
 * @author rgogi
 *
 */
public interface ElevatorControlSystemFactory {
  public void pickUp(Integer pickUpFloor);
  public void destination(Integer elevatorId, Integer destinationFloor);
  public void step();

}
