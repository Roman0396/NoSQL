package springApp.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springApp.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMongoRepositoryIT {

    @Autowired
    UserMongoRepository userMongoRepository;

    @After
    public void resetCollection() {
        userMongoRepository.deleteAll();
    }

    @Test
    public void userCRUDtest() {

        User user = new User();
        user.setName("name");
        user.setEmployee(true);
        user.setRole("role");
        //create user
        User createdUser = userMongoRepository.save(user);
        boolean isExist = userMongoRepository.exists(createdUser.getId());
        assertEquals(isExist, true);
        verifyUser(user, createdUser);


        User userById = userMongoRepository.findOne(createdUser.getId());
        verifyUser(userById, createdUser);

        //delete user
        userMongoRepository.delete(createdUser.getId());
        boolean isExistAfterDelete = userMongoRepository.exists(createdUser.getId());
        assertEquals(isExistAfterDelete, false);

    }

    @Test
    public void findByNameTest() {
        User user = new User();
        user.setName("name");
        user.setEmployee(true);
        user.setRole("role");
        userMongoRepository.save(user);
        List<User> users = userMongoRepository.findByName(user.getName());
        assertEquals(users.size(), 1);
        Optional<User> optionalUser = users.stream().findFirst();
        assertEquals(true, optionalUser.isPresent());
        verifyUser(user, optionalUser.get());
    }

    @Test
    public void findByRoleTest() {
        User user = new User();
        user.setName("name");
        user.setEmployee(true);
        user.setRole("role");
        userMongoRepository.save(user);
        List<User> users = userMongoRepository.findUsersByRegexpRole("e$");
        assertEquals(users.size(), 1);
        Optional<User> optionalUser = users.stream().findFirst();
        assertEquals(true, optionalUser.isPresent());
        verifyUser(user, optionalUser.get());
    }


    private void verifyUser(User expected, User actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.isEmployee(), actual.isEmployee());
    }
}