package com.oasisandino.backend.persistence;

import com.oasisandino.backend.domain.Room;
import com.oasisandino.backend.persistence.crud.HabitacionCrudRepository;
import com.oasisandino.backend.persistence.entity.Habitacion;
import com.oasisandino.backend.persistence.mapper.RoomMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
public class HabitacionRepositoryIntegrationTest {

    @Autowired
    private HabitacionCrudRepository habitacionCrudRepository;

    @Autowired
    private RoomMapper roomMapper;

    private HabitacionRepository habitacionRepository;

    @BeforeEach
    void setUp() {
        habitacionRepository = new HabitacionRepository(habitacionCrudRepository, roomMapper);
    }

    @AfterEach
    void tearDown() {
        habitacionCrudRepository.deleteAll();
    }


    @Test
    void testFindAllRoom() {
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero("101");
        habitacion.setPiso(1);
        habitacion.setDescripcion("Habitacion sencilla");
        habitacion.setCapacidad(2);
        habitacion.setPrecioPorNoche(50);
        habitacion.setPrecioPorMes(1200);
        habitacionCrudRepository.save(habitacion);

        assertFalse(habitacionRepository.findAllRoom().isEmpty());
    }

    @Test
    void testGetRoomById() {
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero("102");
        habitacion.setPiso(2);
        habitacion.setDescripcion("Habitacion doble");
        habitacion.setCapacidad(4);
        habitacion.setPrecioPorNoche(100);
        habitacion.setPrecioPorMes(2000);
        Habitacion saved = habitacionCrudRepository.save(habitacion);

        Optional<Room> result = habitacionRepository.getRoomById(saved.getIdHabitacion());

        assertTrue(result.isPresent());
        assertEquals("102", result.get().getNumber());
    }

    @Test
    void testSaveRoom() {
        Room room = new Room();
        room.setNumber("103");
        room.setFloor(3);
        room.setDescription("Habitacion deluxe");
        room.setAbility(2);
        room.setPricePerNight(200);
        room.setPricePerMonth(5000);

        Room savedRoom = habitacionRepository.saveRoom(room);

        assertNotNull(savedRoom);
        assertEquals("103", savedRoom.getNumber());
    }

    @Test
    void testDeleteRoomById() {
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero("104");
        habitacion.setPiso(4);
        habitacion.setDescripcion("Suite");
        habitacion.setCapacidad(2);
        habitacion.setPrecioPorNoche(300);
        habitacion.setPrecioPorMes(8000);
        Habitacion saved = habitacionCrudRepository.save(habitacion);

        habitacionRepository.deleteRoomById(saved.getIdHabitacion());

        Optional<Habitacion> result = habitacionCrudRepository.findById(saved.getIdHabitacion());

        assertFalse(result.isPresent());
    }
}