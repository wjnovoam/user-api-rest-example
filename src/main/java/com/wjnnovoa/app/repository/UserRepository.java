package com.wjnnovoa.app.repository;

import com.wjnnovoa.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    /*Se puede utilizar JpaRepository y CrudRepository(este para un crud basic)*/
}
