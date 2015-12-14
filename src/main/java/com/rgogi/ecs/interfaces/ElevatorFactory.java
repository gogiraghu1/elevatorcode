package com.rgogi.ecs.interfaces;

import com.rgogi.ecs.impl.enums.*;

/**
 * Elevator Factory interface for managing various states of the elevator
 * 
 * @author rgogi
 *
 */
public interface ElevatorFactory {
  public void moveUp();
  public void moveDown();
  public void addNewDestinatoin(Integer destination);
  public ElevatorState state();
  public ElevatorStatus status();

}
