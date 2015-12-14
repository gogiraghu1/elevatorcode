package com.rgogi.ecs.impl;

import com.rgogi.ecs.impl.enums.ElevatorState;
import com.rgogi.ecs.impl.enums.ElevatorStatus;
import com.rgogi.ecs.interfaces.ElevatorFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Elevator model functional implementation for various elevator control requests
 * 
 * It encapsulates current floor, destination floors and processes destination floor assigned by Control System
 * 
 * @author rgogi
 *
 */
public class Elevator implements ElevatorFactory {
  private Integer currentFloor;
  private Queue<Integer> destinationFloors;

  public Elevator(Integer currentFloor) {
    this.currentFloor = currentFloor;
    this.destinationFloors = new LinkedList<Integer>();
  }

  public int nextDestionation(){
    return this.destinationFloors.peek();
  }

  public int currentFloor(){
    return this.currentFloor;
  }

  /** 
   * Pop the destination as soon as one of the elevator processes request for destination
   * 
   */
  public void popDestination(){
    this.destinationFloors.remove();
  }
  @Override
  public void addNewDestinatoin(Integer destination) {
    this.destinationFloors.add(destination);
  }

  @Override
  public void moveUp() {
    currentFloor++;
  }

  @Override
  public void moveDown() {
    currentFloor--;
  }

  @Override
  public ElevatorState state() {
    if (destinationFloors.size() > 0){
      if (currentFloor < destinationFloors.peek()){
        return ElevatorState.ELEVATOR_UP;
      } else if (currentFloor > destinationFloors.peek()) {
        return ElevatorState.ELEVATOR_DOWN;
      }
    }
    return ElevatorState.ELEVATOR_HOLD;
  }

  @Override
  public ElevatorStatus status() {
    return (destinationFloors.size() > 0)?ElevatorStatus.ELEVATOR_OCCUPIED:ElevatorStatus.ELEVATOR_EMPTY;
  }
}
