package com.hust.tacojpa.data;

import com.hust.tacojpa.Taco;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface TacoRepository extends CrudRepository<Taco,Long> {
}
