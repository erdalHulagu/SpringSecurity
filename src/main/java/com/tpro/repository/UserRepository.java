package com.tpro.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tpro.domain.User;
import com.tpro.exception.ResourceNotFoundException;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserName(String userName) throws ResourceNotFoundException;
	
	Boolean existsByUserName(String userName);
}
	