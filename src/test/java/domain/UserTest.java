package domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Radchuk on 08.02.2016.
 */
public class UserTest{


        @Test
        public void test_CreateUserByFullConstructor(){
            User user = new User("test_id","User","EncryptedPassword","Salt",UserType.UNDEFINED.toString());
            assertNotNull(user);
            assertEquals(user.getEncryptedPassword(),"EncryptedPassword");
            assertEquals(user.getSalt(),"Salt");
            assertEquals(user.getUserName(),"User");
            assertEquals(user.getUserType(), UserType.UNDEFINED);
            assertEquals(user.getId(), "test_id");
        }

        @Test
        public void test_CreateUserByShortConstructor(){
            User user = new User("User","EncryptedPassword","Salt",UserType.UNDEFINED.toString());
            assertNotNull(user);
            assertEquals(user.getEncryptedPassword(),"EncryptedPassword");
            assertEquals(user.getSalt(),"Salt");
            assertEquals(user.getUserName(),"User");
            assertEquals(user.getUserType(), UserType.UNDEFINED);
            assertNull(user.getId());

        }



}