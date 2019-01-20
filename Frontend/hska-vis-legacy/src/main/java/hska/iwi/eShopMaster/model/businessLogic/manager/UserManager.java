package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.microServiceWebShop.models.Role;
import hska.microServiceWebShop.models.User;

public interface UserManager {
    
    public void registerUser(String username, String name, String lastname, String password, Role role);
    
    public User getUserByUsername(String username);
    
    public boolean deleteUserById(int id);
    
    public Role getRoleByLevel(int level);
    
    public boolean doesUserAlreadyExist(String username);
}
