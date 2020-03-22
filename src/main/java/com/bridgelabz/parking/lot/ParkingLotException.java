package com.bridgelabz.parking.lot;

public class ParkingLotException extends RuntimeException {
    public enum ExceptionType{
        NO_PARKING
    }

    public ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
