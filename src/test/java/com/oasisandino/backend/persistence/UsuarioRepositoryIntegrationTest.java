package com.oasisandino.backend.persistence;

import com.oasisandino.backend.domain.User;
import com.oasisandino.backend.persistence.crud.UsuarioCrudRepository;
import com.oasisandino.backend.persistence.entity.Usuario;
import com.oasisandino.backend.persistence.mapper.UserMapper;
import com.oasisandino.backend.persistence.mapper.UserMapperImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    private UsuarioRepository usuarioRepository;


    @BeforeEach
    void setUp() {
        UserMapper userMapper= new UserMapperImpl();
        usuarioRepository = new UsuarioRepository(usuarioCrudRepository,userMapper);
    }

    @AfterEach
    void tearDown() {
        usuarioCrudRepository.deleteAll();

    }

    @Test
    void findAllUsers() {
        //User user = new User();
        //user.setUserid(1);
        //user.setName("John");
        //user.setEmail("john@gmail.com");
        //user.setPassword("password");
        //user.setRegistrationDate(LocalDateTime.parse("2024-01-11T14:30:00"));

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("John");
        usuario.setEmail("john@gmail.com");
        usuario.setPassword("password");
        usuario.setFechaRegistro(LocalDateTime.parse("2024-01-11T14:30:00"));

        usuarioCrudRepository.save(usuario);

        assertFalse(usuarioRepository.findAllUsers().isEmpty());


    }

    @Test
    void getUserById() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("John");
        usuario.setEmail("john@gmail.com");
        usuario.setPassword("password");
        usuario.setFechaRegistro(LocalDateTime.parse("2024-01-11T14:30:00"));

        Usuario userSaved = usuarioCrudRepository.save(usuario);

        Optional<User> result = usuarioRepository.getUserById(userSaved.getIdUsuario());
        assertTrue(result.isPresent());
        assertEquals("John",result.get().getName());

    }

    @Test
    void saveUser() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("John");
        usuario.setEmail("john@gmail.com");
        usuario.setPassword("password");
        usuario.setFechaRegistro(LocalDateTime.parse("2024-01-11T14:30:00"));

        Usuario userSaved = usuarioCrudRepository.save(usuario);

        assertNotNull(userSaved);
        assertEquals("John",userSaved.getNombre());

    }

    @Test
    void deleteUserById() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("John");
        usuario.setEmail("john@gmail.com");
        usuario.setPassword("password");
        usuario.setFechaRegistro(LocalDateTime.parse("2024-01-11T14:30:00"));

        Usuario userSaved = usuarioCrudRepository.save(usuario);

        usuarioRepository.deleteUserById(userSaved.getIdUsuario());

        Optional<User> result = usuarioRepository.getUserById(userSaved.getIdUsuario());

        assertFalse(result.isPresent());

    }
}