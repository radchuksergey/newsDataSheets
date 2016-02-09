package database.DAO;

import domain.User;

/**
 * Created by sergey on 31.01.2016.
 */
public interface UserDAO {
     User getUserByID(String userID) throws DBException;
     User createUser(User user) throws DBException;
     void deleteUser(String userID) throws DBException;
     void deleteUser(User user) throws DBException;
     void updateUser(User user) throws DBException;
}
