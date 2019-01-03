package de.hska.userRoleService;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

	public Iterable<User> findAllByUsername(String username);	
	public Iterable<User> findAllByFirstname(String firstname);
	public Iterable<User> findAllByLastname(String lastname);
	public Iterable<User> findAllByRoleId(long roleId);	
	public Iterable<User> findAllByUsernameAndFirstname(String username, String firstname);
	public Iterable<User> findAllByUsernameAndLastname(String username, String lastname);
	public Iterable<User> findAllByUsernameAndRoleId(String username, long roleId);
	public Iterable<User> findAllByFirstnameAndLastname(String firstname, String lastname);
	public Iterable<User> findAllByFirstnameAndRoleId(String firstname, long roleId);
	public Iterable<User> findAllByLastnameAndRoleId(String lastname, long roleId);	
	public Iterable<User> findAllByUsernameAndFirstnameAndLastname(String username, String firstname, String lastname);
	public Iterable<User> findAllByUsernameAndFirstnameAndRoleId(String username, String firstname, long roleId);
	public Iterable<User> findAllByUsernameAndLastnameAndRoleId(String username, String lastname, long roleId);
	public Iterable<User> findAllByFirstnameAndLastnameAndRoleId(String firstname, String lastname, long roleId);
	public Iterable<User> findAllByUsernameAndFirstnameAndLastnameAndRoleId(String username, String firstname, String lastname, long roleId);

	
	
	
}
