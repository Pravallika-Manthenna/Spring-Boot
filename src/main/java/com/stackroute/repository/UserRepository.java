package com.stackroute.repository;

import com.stackroute.MuzixApplication;
import com.stackroute.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//using repository annotation
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select user from User user where firstName=?1")
    List<MuzixApplication> userByName(String name);  //Abstract method
}
