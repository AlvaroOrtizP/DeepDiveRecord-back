package com.record.DeepDiveRecord.domain.model.exception;

public class InvalidFishingDataException extends RuntimeException {
    public InvalidFishingDataException(String message) {
        super(message);
    }
}