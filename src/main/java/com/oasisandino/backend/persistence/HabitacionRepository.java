package com.oasisandino.backend.persistence;

import com.oasisandino.backend.domain.Room;
import com.oasisandino.backend.domain.repository.RoomRepository;
import com.oasisandino.backend.persistence.crud.HabitacionCrudRepository;
import com.oasisandino.backend.persistence.entity.Habitacion;
import com.oasisandino.backend.persistence.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HabitacionRepository implements RoomRepository {

    private final HabitacionCrudRepository habitacionCrudRepository;
    private final RoomMapper mapper;

    @Autowired // opcional en constructores
    public HabitacionRepository(HabitacionCrudRepository habitacionCrudRepository, @Qualifier("roomMapperImpl") RoomMapper mapper) {
        this.habitacionCrudRepository = habitacionCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Room> findAllRoom() {

        List<Habitacion> habitaciones= (List<Habitacion>) habitacionCrudRepository.findAll();
        return mapper.toRooms(habitaciones);
    }

    @Override
    public Optional<Room> getRoomById(int roomId) {
        return habitacionCrudRepository.findById(roomId).map(habitacion -> mapper.toRoom(habitacion));
    }

    @Override
    public Room saveRoom(Room room) {
        Habitacion habitacion = mapper.toHabitacion(room);
        return mapper.toRoom(habitacionCrudRepository.save(habitacion));
    }

    @Override
    public void deleteRoomById(int roomId) {
        habitacionCrudRepository.deleteById(roomId);
    }
}
