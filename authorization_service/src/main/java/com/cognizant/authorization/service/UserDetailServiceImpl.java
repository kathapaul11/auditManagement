package com.cognizant.authorization.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authorization.model.MyUser;
import com.cognizant.authorization.repo.MyUserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private MyUserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		MyUser user= userRepo.findByUserName(userName);
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
		
	}
	public void createUser(MyUser myUser) {
		
		userRepo.save(myUser);
		
	}

}
