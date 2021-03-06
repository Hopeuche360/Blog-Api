package com.example.week9task.config;

import com.example.week9task.model.User;
import com.example.week9task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduledTasks {

    private UserRepository userRepository;

    @Autowired
    public ScheduledTasks(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedDelay = 180000)
    public void remove() {
        List<User> users = userRepository.findUserByFlag(true);
        userRepository.deleteAll(users);
    }
}
