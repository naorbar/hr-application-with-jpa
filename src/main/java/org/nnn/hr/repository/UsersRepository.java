package org.nnn.hr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends CrudRepository<User, String> {

	Optional<User> findByTitle(String title);

	@Query(value = "SELECT * FROM USERS u ORDER BY u.title", nativeQuery = true)
	Iterable<User> findAllOrderedByTitle();

	@Query(value = "SELECT * FROM USERS u WHERE u.id = :userId", nativeQuery = true)
	Iterable<User> findUserNativeQuery(@Param("userId") String userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE USERS u SET u.title = :title WHERE u.name = :name", nativeQuery = true)
	int updateUserTitleByNameNativeQuery(@Param("name") String name, @Param("title") String title);
}
