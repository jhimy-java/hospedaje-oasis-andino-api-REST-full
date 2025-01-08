package com.oasisandino.backend.domain.service;

import com.oasisandino.backend.domain.Room;
import com.oasisandino.backend.domain.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    public RoomServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRoom()
    {
        // Arrange: Crear una lista simulada de habitaciones
        Room room1 = new Room();
        room1.setRoomId(1);
        room1.setNumber("101");
        room1.setFloor(1);
        room1.setDescription("Habitación estándar");
        room1.setAbility(2);
        room1.setPricePerNight(100);
        room1.setPricePerMonth(2000);

        Room room2 = new Room();
        room2.setRoomId(2);
        room2.setNumber("102");
        room2.setFloor(1);
        room2.setDescription("Habitación premium");
        room2.setAbility(3);
        room2.setPricePerNight(150);
        room2.setPricePerMonth(2500);

        List<Room> mockRoomList = Arrays.asList(room1, room2);

        when(roomRepository.findAllRoom()).thenReturn(mockRoomList);

        //Act: LLamar al metodo que estamos probando
        List<Room> result = roomService.getAllRoom();

        // Assert: Verificar el tamaño de la lista
        assertEquals(mockRoomList.size(), result.size(), "El tamaño de la lista no coincide");

        // Verificar cada objeto en la lista
        for (int i = 0; i < mockRoomList.size(); i++) {
            Room expectedRoom = mockRoomList.get(i);
            Room actualRoom = result.get(i);

            assertEquals(expectedRoom.getRoomId(), actualRoom.getRoomId(), "RoomId no coincide");
            assertEquals(expectedRoom.getNumber(), actualRoom.getNumber(), "Number no coincide");
            assertEquals(expectedRoom.getFloor(), actualRoom.getFloor(), "Floor no coincide");
            assertEquals(expectedRoom.getDescription(), actualRoom.getDescription(), "Description no coincide");
            assertEquals(expectedRoom.getAbility(), actualRoom.getAbility(), "Ability no coincide");
            assertEquals(expectedRoom.getPricePerNight(), actualRoom.getPricePerNight(), "PricePerNight no coincide");
            assertEquals(expectedRoom.getPricePerMonth(), actualRoom.getPricePerMonth(), "PricePerMonth no coincide");
        }


        // Assert: Verificar el resultado
        //assertEquals(2, result.size());
        //assertEquals("101", result.get(0).getNumber());
        //assertEquals("102", result.get(1).getNumber());
    }

    @Test
    public void testGetRoomById() {
        // Arrange: Crear un objeto Room simulado
        Room room = new Room();
        room.setRoomId(1);
        room.setNumber("101");
        room.setFloor(1);
        room.setDescription("Habitación estándar");
        room.setAbility(2);
        room.setPricePerNight(100);
        room.setPricePerMonth(2000);

        when(roomRepository.getRoomById(1)).thenReturn(Optional.of(room));

        // Act: Llamar al método que estamos probando
        Optional<Room> result = roomService.getRoomById(1);

        // Assert: Verificar el resultado
        assertTrue(result.isPresent());
        assertEquals("101", result.get().getNumber());
    }

    @Test
    public void testSaveRoom() {
        // Arrange: Crear un objeto Room simulado
        Room room = new Room();
        room.setRoomId(1);
        room.setNumber("101");
        room.setFloor(1);
        room.setDescription("Habitación estándar");
        room.setAbility(2);
        room.setPricePerNight(100);
        room.setPricePerMonth(2000);

        when(roomRepository.saveRoom(room)).thenReturn(room);

        // Act: Llamar al método que estamos probando
        Room result = roomService.saveRoom(room);

        // Assert: Verificar el resultado
        assertNotNull(result);
        assertEquals("101", result.getNumber());
    }

    @Test
    public void testDeleteRoomById() {
        // Arrange: Simular que el método getRoomById devuelve un Room
        Room room = new Room();
        room.setRoomId(1);

        when(roomRepository.getRoomById(1)).thenReturn(Optional.of(room));
        doNothing().when(roomRepository).deleteRoomById(1);

        // Act: Llamar al método que estamos probando
        boolean result = roomService.deleteRoomById(1);

        // Assert: Verificar el resultado
        assertTrue(result);
        verify(roomRepository, times(1)).deleteRoomById(1);
    }
}