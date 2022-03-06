package com.minutemate.sera_api.domain.room.data.dto;

import org.springframework.web.socket.WebSocketSession;

import java.util.Set;

public record GameRoomDto(
        String id,
        Long adminId,
        String name,
        Set<WebSocketSession> sessions
) { }
