package com.cognizant.authorization.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.authorization.model.MyUser;

@Repository
public interface MyUserRepo extends JpaRepository<MyUser,Integer> {

	public MyUser findByUserName(String userName);
}
