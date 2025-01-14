package com.oasisandino.backend.domain.service;

import com.oasisandino.backend.domain.User;
import com.oasisandino.backend.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllUsers() {
        User user1 = new User();
        user1.setUserid(1);
        user1.setName("John");
        user1.setEmail("john@gmail.com");
        user1.setPassword("password");
        user1.setRegistrationDate(LocalDateTime.parse("2024-01-11T14:30:00"));

        User user2 = new User();
        user2.setUserid(1);
        user2.setName("jhimy");
        user2.setEmail("jhimy@gmail.com");
        user2.setPassword("password");
        user2.setRegistrationDate(LocalDateTime.parse("2023-01-11T14:30:00"));

        List<User> mockUserList = Arrays.asList(user1, user2);

        when(userRepository.findAllUsers()).thenReturn(mockUserList);

        //Act: LLamar al metodo que estamos probando
        List<User> result = userService.findAllUsers();

        // Assert: Verificar el tamaño de la lista
        assertEquals(mockUserList.size(), result.size(),"El tamaño de la lista no coincide");

        for (int i = 0; i < mockUserList.size(); i++) {
            User expectedUser = mockUserList.get(i);
            User actualUser = result.get(i);

            assertEquals(expectedUser.getUserid(),actualUser.getUserid(),"UserId no coincide");
            assertEquals(expectedUser.getName(),actualUser.getName(),"Name no coincide");
            assertEquals(expectedUser.getEmail(),actualUser.getEmail(),"Email no coincide");
            assertEquals(expectedUser.getPassword(),actualUser.getPassword(),"Password no coincide");
            assertEquals(expectedUser.getRegistrationDate(),actualUser.getRegistrationDate(),"RegistrationDate no coincide");
        }

    }

    @Test
    void getUserById() {

        User user = new User();
        user.setUserid(1);
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("password");
        user.setRegistrationDate(LocalDateTime.parse("2024-01-11T14:30:00"));

        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));

        
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setUserid(1);
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("password");
        user.setRegistrationDate(LocalDateTime.parse("2024-01-11T14:30:00"));

        when(userRepository.saveUser(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("john@gmail.com", result.getEmail());
    }

    @Test
    void deleteUserById() {

        User user = new User();
        user.setUserid(1);

        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteUserById(1);

        boolean result = userService.deleteUserById(1);

        assertTrue(result);
        verify(userRepository, times(1)).deleteUserById(1);


    }
}