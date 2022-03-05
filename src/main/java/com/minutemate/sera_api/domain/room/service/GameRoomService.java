package com.minutemate.sera_api.domain.room.service;

import com.minutemate.sera_api.domain.room.data.dto.GameRoomDto;

import java.util.List;

public interface GameRoomService {
    List<GameRoomDto> getAllRooms();

    GameRoomDto getRoomById(String id);

    GameRoomDto createRoom(Long userId, String title);
}
