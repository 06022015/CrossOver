package com.crossover.trial.journals.rest;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.User;
import com.crossover.trial.journals.repository.PublisherRepository;
import com.crossover.trial.journals.service.JournalService;
import com.crossover.trial.journals.service.ServiceException;
import com.crossover.trial.journals.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/29/16
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    private final static String NEW_JOURNAL_NAME = "User service";

    @Autowired
    private UserService userService;


    @Test(expected = ServiceException.class)
    public void subscribeFail() {
        User user = getUser("user1");
        userService.subscribe(user, 100L);
    }

    @Test
    public void subscribeSuccess() {
        User user = getUser("user1");
        userService.subscribe(user, 2L);
        assertTrue(user.getSubscriptions().size()>1);
    }

    @Test
    public void findByIdSuccess() {
        User user =  userService.findById(1L);
        assertNotNull(user);
    }



    protected User getUser(String name) {
        Optional<User> user = userService.getUserByLoginName(name);
        if (!user.isPresent()) {
            fail("user1 doesn't exist");
        }
        return user.get();
    }



}
