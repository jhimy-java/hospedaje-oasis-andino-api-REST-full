package com.oasisandino.backend.persistence;

import com.oasisandino.backend.domain.User;
import com.oasisandino.backend.domain.repository.UserRepository;
import com.oasisandino.backend.persistence.crud.UsuarioCrudRepository;
import com.oasisandino.backend.persistence.entity.Usuario;
import com.oasisandino.backend.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {


    private final UsuarioCrudRepository usuarioCrudRepository;
    private final UserMapper mapper;

    @Autowired
    public UsuarioRepository(UsuarioCrudRepository usuarioCrudRepository,@Qualifier("userMapperImpl") UserMapper userMapper) {
        this.usuarioCrudRepository = usuarioCrudRepository;
        this.mapper = userMapper;
    }


    @Override
    public List<User> findAllUsers() {

        List<Usuario> usuarios = (List<Usuario>) usuarioCrudRepository.findAll();
        return mapper.toUsers(usuarios);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return usuarioCrudRepository.findById(id).map(mapper::toUser);
    }

    @Override
    public User saveUser(User user) {
        Usuario usuario = mapper.toUsuario(user);
        return mapper.toUser(usuarioCrudRepository.save(usuario));
    }

    @Override
    public void deleteUserById(int id) {
        usuarioCrudRepository.deleteById(id);
    }
}
