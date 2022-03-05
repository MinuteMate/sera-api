package com.minutemate.sera_api.domain.room.exception;

import java.util.function.Supplier;

public class UnknownGameRoomException extends RuntimeException implements Supplier<X> {
    public UnknownGameRoomException(String id) {
        super("게임방을 찾을 수 없습니다! - " + id);
    }
}
