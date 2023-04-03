package ru.ssau.mednoteapi.service;

import org.springframework.stereotype.Service;
import ru.ssau.mednoteapi.entity.domain.User;
import ru.ssau.mednoteapi.entity.dto.UserRecord;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    UserRecord save(UserRecord userRecord);

    UserRecord findByID(long id);

    List<UserRecord> findAll();

    UserRecord delete(UserRecord userRecord);


    Optional<User> findByUsername(String username);

    boolean entered(String username, String password);


    int updatePassword(Long id, String password);

}
