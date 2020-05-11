package com.example.productStore.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.productStore.Entity.UserEntity;
import com.example.productStore.Repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		//List<String> list = Arrays.asList("Jashan", "Pulkit");

		List<UserEntity> entity = repo.findByName(userName);

		for (UserEntity userDetails : entity) {
			if (userDetails.getName().equalsIgnoreCase(userName)) {
				Set<GrantedAuthority> authorities = new HashSet<>();
				GrantedAuthority auth=new SimpleGrantedAuthority(userDetails.getType());
				authorities.add(auth);
				return new User(userName, userDetails.getPassword(), authorities);
			}
		}
		/*
		 * if (list.contains(userName)) { return new User("Jashan",
		 * "$2a$10$1OdIRa4HuN74UqNY9GX0Yue9ROUy7AsrstrW3zptITYYDbSjaMwpa", new
		 * ArrayList<>()); } else { throw new
		 * UsernameNotFoundException("User not found with username: " + username); }
		 */

		throw new UsernameNotFoundException("User not found with username: " + userName);
	}
}