package com.oasisandino.backend.persistence.crud;

import com.oasisandino.backend.persistence.entity.Habitacion;
import org.springframework.data.repository.CrudRepository;

public interface HabitacionCrudRepository extends CrudRepository<Habitacion,Integer> {
}
