package org.nnn.hr.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserPermissionsRepository extends CrudRepository<UserPermission, String> {

	Iterable<UserPermission> findByUserId(String userId);
	
	
}
