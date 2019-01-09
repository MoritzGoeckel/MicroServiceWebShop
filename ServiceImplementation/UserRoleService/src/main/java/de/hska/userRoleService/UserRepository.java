package de.hska.userRoleService;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

	public Iterable<User> findAllByUsername(String username);	
	public Iterable<User> findAllByRoleID(long roleID);	
	
	@Query("SELECT u FROM User u WHERE u.username = :text OR u.firstname = :text OR u.lastname = :text")
	public Iterable<User> findAllByText(String text);
	
	@Query("SELECT u FROM User u WHERE u.username = :username AND (u.firstname = :text OR u.lastname = :text)")
	public Iterable<User> findAllByUsernameAndText(String username, String text);
	
	public Iterable<User> findAllByUsernameAndRoleID(String username, long roleID);
	
	@Query("SELECT u FROM User u WHERE u.roleID = :roleID And (u.username = :text OR u.firstname = :text OR u.lastname = :text)")
	public Iterable<User> findAllByTextAndRoleID(String text, long roleID);
	
	@Query("SELECT u FROM User u WHERE u.username = :username AND u.roleID = :roleID AND (u.firstname = :text OR u.lastname = :text)")
	public Iterable<User> findAllByUsernameAndTextAndRoleID(String username, String text, long roleID);
	
	
	
}
