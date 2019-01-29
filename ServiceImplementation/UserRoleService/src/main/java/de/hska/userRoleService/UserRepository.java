package de.hska.userRoleService;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.hska.userRoleService.model.RepoUser;

public interface UserRepository extends CrudRepository<RepoUser, Long>{

	public Iterable<RepoUser> findAllByUsername(String username);	
	public Iterable<RepoUser> findAllByRole_Id(long roleID);	
	
	@Query("SELECT u FROM RepoUser u WHERE u.username = :text OR u.firstName = :text OR u.lastName = :text")
	public Iterable<RepoUser> findAllByText(String text);
	
	@Query("SELECT u FROM RepoUser u WHERE u.username = :username AND (u.firstName = :text OR u.lastName = :text)")
	public Iterable<RepoUser> findAllByUsernameAndText(String username, String text);
	
	public Iterable<RepoUser> findAllByUsernameAndRole_Id(String username, long role_ID);
	
	@Query("SELECT u FROM RepoUser u WHERE u.role.id = :roleID And (u.username = :text OR u.firstName = :text OR u.lastName = :text)")
	public Iterable<RepoUser> findAllByTextAndRole_Id(String text, long roleID);
	
	@Query("SELECT u FROM RepoUser u WHERE u.username = :username AND u.role.id = :roleID AND (u.firstName = :text OR u.lastName = :text)")
	public Iterable<RepoUser> findAllByUsernameAndTextAndRole_Id(String username, String text, long roleID);
	
	
	
}
