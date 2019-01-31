package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.models.Role;
import hska.iwi.eShopMaster.models.User;

public interface UserManager {
    
    public void registerUser(String username, String name, String lastname, String password, Long role);
    
    public User getUserByUsername(String username);
    
    public boolean deleteUserById(long id);
    
    public Role getRoleByLevel(int level);
    
    public boolean doesUserAlreadyExist(String username);
}
