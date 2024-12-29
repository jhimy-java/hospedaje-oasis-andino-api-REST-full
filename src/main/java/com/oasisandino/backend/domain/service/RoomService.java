package com.oasisandino.backend.domain.service;

import com.oasisandino.backend.domain.Room;
import com.oasisandino.backend.domain.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRoom()
    {
        return roomRepository.findAllRoom();
    }

    public Optional<Room> getRoomById(int roomId)
    {
        return roomRepository.getRoomById(roomId);
    }

    public Room saveRoom(Room room)
    {
        return roomRepository.saveRoom(room);
    }

    public boolean deleteRoomById(int roomId)
    {
        return getRoomById(roomId).map(room ->{
            roomRepository.deleteRoomById(roomId);
            return true;
        }).orElse(false);
    }

}
