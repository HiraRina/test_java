package jp.co.sss.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.test.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
