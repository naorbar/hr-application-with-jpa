package org.nnn.hr.manager;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.nnn.hr.repository.Permission;
import org.nnn.hr.repository.PermissionsRepository;
import org.nnn.hr.repository.User;
import org.nnn.hr.repository.UserPermission;
import org.nnn.hr.repository.UserPermissionsRepository;
import org.nnn.hr.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HrManager {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	PermissionsRepository permissionsRepository;

	@Autowired
	UserPermissionsRepository userPermissionsRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public HrManager() {

	}

	public Iterable<User> getAllUsers() {
		return usersRepository.findAll();
	}

	public Iterable<User> getAllUsersOrdredByTitle() {
		return usersRepository.findAllOrderedByTitle();
	}

	public Optional<User> getUserById(String id) {
		return usersRepository.findById(id);
	}

	public Optional<User> getUserByTitle(String title) {
		return usersRepository.findByTitle(title);
	}

	public User insertUser(User user) {
		return usersRepository.save(user);
	}

	public Iterable<Permission> getAllPermissions() {
		return permissionsRepository.findAll();
	}

	public UserPermission addPermissionToUser(String userId, String permissionId) {
		Optional<User> u = usersRepository.findById(userId);
		Optional<Permission> p = permissionsRepository.findById(permissionId);
		if (p.isPresent() && u.isPresent()) {
			return userPermissionsRepository.save(new UserPermission(u.get().getId(), p.get().getId()));
		}
		return null;
	}

	public Iterable<UserPermission> getAllUsersPermissions() {
		return userPermissionsRepository.findAll();
	}

	public Set<Permission> getUserPermissions(String userId) {
		Set<Permission> permissions = new HashSet<>();
		Optional<User> u = usersRepository.findById(userId);
		if (!u.isPresent())
			return null;
		Iterable<UserPermission> permissionsIds = userPermissionsRepository.findByUserId(userId);
		for (UserPermission userPermission : permissionsIds) {
			Optional<Permission> p = permissionsRepository.findById(userPermission.getPermissionId());
			if (p.isPresent())
				permissions.add(p.get());
		}
		return permissions;
	}

	public Iterable<User> getUserNativeQuery(String userId) {
		return usersRepository.findUserNativeQuery(userId);
	}

	public int updateUserTitleByNameNativeQuery(String name, String title) {
		return usersRepository.updateUserTitleByNameNativeQuery(name, title);
	}

	/**
	 * An example of using entityManager for complex queries or cross-tables queries:
	 * @author barna10
	 */
	public Object getCounterWithEntityManager() {
		Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM USERS");
		return query.getSingleResult();
	}
}
