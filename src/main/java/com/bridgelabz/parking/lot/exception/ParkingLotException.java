package com.bridgelabz.parking.lot.exception;

public class ParkingLotException extends RuntimeException {
    public enum ExceptionType{
        NO_SUCH_DRIVER, NO_SLOT_AVAILABLE, NO_SUCH_VEHICLE, NO_PARKING
    }

    public ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
