package database.DAO;

import domain.User;

/**
 * Created by sergey on 31.01.2016.
 */
public interface UserDAO {
     User getUserByID(String userID);
     User createUser(User user);
     void deleteUser(String userID);
     void deleteUser(User user);
     void updateUser(User user);
}
