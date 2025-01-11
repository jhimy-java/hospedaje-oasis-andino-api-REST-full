package com.oasisandino.backend.persistence.crud;

import com.oasisandino.backend.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer> {


}
