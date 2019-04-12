package com.ttn.linkSharing.config;

import com.ttn.linkSharing.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;
@Component
public class LoggedInUserAuditorAware implements AuditorAware<String> {

    @Autowired
    HttpSession httpSession;

    @Override
    public Optional<String> getCurrentAuditor() {
        User user = (User) httpSession.getAttribute("user");
        return Optional.of(user != null ? user.getEmail() : null);
    }
}