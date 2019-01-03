package de.hska.userRoleService;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>{

	public Iterable<Role> findAllByTyp(String typ);
	public Iterable<Role> findAllByLevel(int level);
	public Iterable<Role> findAllByTypAndLevel(String typ, int level);
	
}
