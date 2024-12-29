package com.oasisandino.backend.domain.repository;

import com.oasisandino.backend.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface RoomRepository {
    List<Room> findAllRoom();
    Optional<Room> getRoomById(int roomId);
    Room saveRoom(Room room);
    void deleteRoomById(int roomId);
}
