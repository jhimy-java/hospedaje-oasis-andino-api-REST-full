package com.oasisandino.backend.persistence.mapper;

import com.oasisandino.backend.domain.Room;
import com.oasisandino.backend.persistence.entity.Habitacion;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomMapperTest {

    private final RoomMapper roomMapper = Mappers.getMapper(RoomMapper.class);
    @Test
    public void testToRoom() {
        // Arrange: Crear una entidad Habitacion simulada
        Habitacion habitacion = new Habitacion();
        habitacion.setIdHabitacion(1);
        habitacion.setNumero("101");
        habitacion.setPiso(1);
        habitacion.setDescripcion("Habitación estándar");
        habitacion.setCapacidad(2);
        habitacion.setPrecioPorNoche(100);
        habitacion.setPrecioPorMes(2000);

        // Act: Mapear Habitacion a Room
        Room room = roomMapper.toRoom(habitacion);

        // Assert: Verificar los valores mapeados
        assertEquals(1, room.getRoomId());
        assertEquals("101", room.getNumber());
        assertEquals(1, room.getFloor());
        assertEquals("Habitación estándar", room.getDescription());
        assertEquals(2, room.getAbility());
        assertEquals(100, room.getPricePerNight());
        assertEquals(2000, room.getPricePerMonth());
    }

    @Test
    public void testToRooms() {
        // Arrange: Crear una lista de entidades Habitacion simuladas
        Habitacion habitacion1 = new Habitacion();
        habitacion1.setIdHabitacion(1);
        habitacion1.setNumero("101");
        habitacion1.setPiso(1);
        habitacion1.setDescripcion("Habitación estándar");
        habitacion1.setCapacidad(2);
        habitacion1.setPrecioPorNoche(100);
        habitacion1.setPrecioPorMes(2000);

        Habitacion habitacion2 = new Habitacion();
        habitacion2.setIdHabitacion(2);
        habitacion2.setNumero("102");
        habitacion2.setPiso(2);
        habitacion2.setDescripcion("Habitación premium");
        habitacion2.setCapacidad(3);
        habitacion2.setPrecioPorNoche(150);
        habitacion2.setPrecioPorMes(2500);

        List<Habitacion> habitaciones = Arrays.asList(habitacion1, habitacion2);

        // Act: Mapear lista de Habitacion a lista de Room
        List<Room> rooms = roomMapper.toRooms(habitaciones);

        // Assert: Verificar los valores mapeados
        assertEquals(2, rooms.size());

        Room room1 = rooms.getFirst();
        assertEquals(1, room1.getRoomId());
        assertEquals("101", room1.getNumber());

        Room room2 = rooms.get(1);
        assertEquals(2, room2.getRoomId());
        assertEquals("102", room2.getNumber());
    }

    @Test
    public void testToHabitacion() {
        // Arrange: Crear un DTO Room simulado
        Room room = new Room();
        room.setRoomId(1);
        room.setNumber("101");
        room.setFloor(1);
        room.setDescription("Habitación estándar");
        room.setAbility(2);
        room.setPricePerNight(100);
        room.setPricePerMonth(2000);

        // Act: Mapear Room a Habitacion
        Habitacion habitacion = roomMapper.toHabitacion(room);

        // Assert: Verificar los valores mapeados
        assertEquals(1, habitacion.getIdHabitacion());
        assertEquals("101", habitacion.getNumero());
        assertEquals(1, habitacion.getPiso());
        assertEquals("Habitación estándar", habitacion.getDescripcion());
        assertEquals(2, habitacion.getCapacidad());
        assertEquals(100, habitacion.getPrecioPorNoche());
        assertEquals(2000, habitacion.getPrecioPorMes());
    }
}