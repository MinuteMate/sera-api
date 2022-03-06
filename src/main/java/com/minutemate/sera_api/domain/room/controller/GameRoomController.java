package com.minutemate.sera_api.domain.room.controller;

import com.minutemate.sera_api.domain.account.repository.UserRepository;
import com.minutemate.sera_api.domain.room.data.dto.GameRoomDto;
import com.minutemate.sera_api.domain.room.service.GameRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/game/room")
public class GameRoomController {
    private final GameRoomService gameRoomService;
    private final UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<GameRoomDto>> getAllRooms() {
        return ResponseEntity.ok(gameRoomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameRoomDto> getRoomById(@PathVariable final String id) {
        return ResponseEntity.ok(gameRoomService.getRoomById(id));
    }

    @PostMapping
    public ResponseEntity<GameRoomDto> createRoom(@RequestParam final String title, final Authentication authentication) {
        return ResponseEntity.ok(gameRoomService.createRoom(userRepository.getByEmail(authentication.getName()).getId(), title));
    }
}
