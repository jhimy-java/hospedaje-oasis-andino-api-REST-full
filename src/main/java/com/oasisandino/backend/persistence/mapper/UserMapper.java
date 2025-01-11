package com.oasisandino.backend.persistence.mapper;


import com.oasisandino.backend.domain.Room;
import com.oasisandino.backend.domain.User;
import com.oasisandino.backend.persistence.entity.Habitacion;
import com.oasisandino.backend.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "idUsuario", target = "userid"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "fechaRegistro", target = "registrationDate"),
    })
    User toUser(Usuario usuario);
    List<User> toUsers(List<Usuario> usuarios);

    @InheritInverseConfiguration
    Habitacion toHabitacion(Room room);
}
