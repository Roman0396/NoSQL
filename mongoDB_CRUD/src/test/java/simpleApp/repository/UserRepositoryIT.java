package simpleApp.repository;

import simpleApp.model.User;
import org.junit.Test;


import static org.junit.Assert.*;

public class UserRepositoryIT {

    UserRepository userRepository = new UserRepository();


    @Test
    public void userEntityCRUDFlow() {
        User user = new User();
        user.setId(1);
        user.setName("name");
        user.setEmployee(true);
        user.setRole("role");

        userRepository.createUser(user);

        User createdUser = userRepository.getUserById(user.getId());
        verifyUser(user, createdUser);

        user.setName("updatedName");
        userRepository.updateUser(user);


        User updatedUser = userRepository.getUserById(user.getId());
        verifyUser(user, updatedUser);

        userRepository.deleteUser(user);
        assertNull(userRepository.getUserById(user.getId()));

    }

    private void verifyUser(User expected, User actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.isEmployee(), actual.isEmployee());
    }

}