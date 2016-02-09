package database.mongodb;

import database.DAO.UserDAO;
import domain.User;
import domain.UserType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Radchuk on 08.02.2016.
 */
public class UserDAOImplTest {
    private UserDAO userDAO = new UserDAOImpl();

    @Before
    public void cleanDB(){
        DBCleaner.cleanDB();
    }

    @Test
    public void testGetUserByID() throws Exception {
        User user = new User("test user", "test password", "test_salt", "USER");
        user =  userDAO.createUser(user);
        User userFromDB = userDAO.getUserByID(user.getId());


        assertEquals(userFromDB.getUserType(), user.getUserType());
        assertEquals(userFromDB.getSalt(), user.getSalt());
        assertEquals(userFromDB.getUserName(), user.getUserName());
        assertEquals(userFromDB.getEncryptedPassword(), user.getEncryptedPassword());
        assertEquals(userFromDB.getId(), user.getId());
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User("test user", "test password", "test_salt", "USER");
        User createdUser =  userDAO.createUser(user);
        assertEquals(createdUser.getUserType(), user.getUserType());
        assertEquals(createdUser.getSalt(), user.getSalt());
        assertEquals(createdUser.getUserName(), user.getUserName());
        assertEquals(createdUser.getEncryptedPassword(), user.getEncryptedPassword());
        assertNotEquals(createdUser.getId(), user.getId());

    }

    @Test
    public void testDeleteUser()  {
        User user = new User("test user", "test password", "test_salt", "USER");
        User createdUser =  userDAO.createUser(user);
        assertNotEquals(createdUser.getId(),"");
        userDAO.deleteUser(createdUser);
        user = userDAO.getUserByID(createdUser.getId());
        assertNull(user);

    }

    @Test
    public void testDeleteUserById()  {
        User user = new User("test user", "test password", "test_salt", "USER");
        User createdUser =  userDAO.createUser(user);
        assertNotEquals(createdUser.getId(),"");
        userDAO.deleteUser(createdUser.getId());
        user = userDAO.getUserByID(createdUser.getId());
        assertNull(user);
    }

    @Test
    public void testUpdateUser()  {
        User user = new User("test user", "test password", "test_salt", "USER");
        user =  userDAO.createUser(user);
        user.setUserType(UserType.ADMIN);
        user.setEncryptedPassword("updated tests password");
        user.setSalt("updated tests salt");
        userDAO.updateUser(user);
        User userFromDb = userDAO.getUserByID(user.getId());
        assertEquals(userFromDb.getUserType(), user.getUserType());
        assertEquals(userFromDb.getSalt(), user.getSalt());
        assertEquals(userFromDb.getUserName(), user.getUserName());
        assertEquals(userFromDb.getEncryptedPassword(), user.getEncryptedPassword());
        assertEquals(userFromDb.getId(), user.getId());
    }


}