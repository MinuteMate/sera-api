package com.minutemate.sera_api.domain.room.service;

import com.minutemate.sera_api.domain.room.data.dto.GameRoomDto;
import com.minutemate.sera_api.domain.room.exception.UnknownGameRoomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameRoomServiceImpl implements GameRoomService {
    private final List<GameRoomDto> gameRoomRepository = new ArrayList<>();

    @Override
    public List<GameRoomDto> getAllRooms() {
        return new ArrayList<>(gameRoomRepository);
    }

    @Override
    public GameRoomDto getRoomById(final String id) {
        return gameRoomRepository.stream()
                .filter(dto -> Objects.equals(dto.id(), id))
                .findFirst()
                .orElseThrow(() -> new UnknownGameRoomException(id));
    }

    @Override
    public GameRoomDto createRoom(final Long userId, final String title) {
        final GameRoomDto dto = new GameRoomDto(generateRoomId(), userId, title, new HashSet<>());
        gameRoomRepository.add(dto);
        return dto;
    }

    private String generateRoomId() {
        while (true) {
            String id = String.format("%06d", new Random().nextInt(1000000));
            if (!gameRoomRepository.stream().map(GameRoomDto::id).collect(Collectors.toList()).contains(id)) return id;
        }
    }
}
