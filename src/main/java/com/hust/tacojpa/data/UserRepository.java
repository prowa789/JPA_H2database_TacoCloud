package com.hust.tacojpa.data;


import com.hust.tacojpa.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
