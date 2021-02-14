package com.bibliotheque.universite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotheque.universite.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);
}
