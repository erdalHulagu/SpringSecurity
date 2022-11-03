package com.tpro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpro.domain.Role;
import com.tpro.domain.enums.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Optional <Role> findByName(UserRole name);

}
