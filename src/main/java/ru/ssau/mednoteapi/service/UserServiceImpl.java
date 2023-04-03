package ru.ssau.mednoteapi.service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.mednoteapi.entity.domain.User;
import ru.ssau.mednoteapi.entity.dto.UserRecord;
import ru.ssau.mednoteapi.mapper.UserMapper;
import ru.ssau.mednoteapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Argon2 encoder = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Transactional
    @Override
    public UserRecord save(UserRecord userRecord) {
        return userMapper.toRecord(userRepository
                .save(userMapper.toEntity(new UserRecord(userRecord.displayName(), userRecord.userName(), encoder.hash(22, 65536, 1, userRecord.password().toCharArray())))));
    }

    @Transactional(readOnly = true)
    @Override
    public UserRecord findByID(long id) {
        return userMapper.toRecord(
                userRepository.findById(id)
                        .orElseThrow(() -> new UsernameNotFoundException("User with id:" + id + " not found")));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserRecord> findAll() {
        return userMapper.toUserRecordList(userRepository.findAll());
    }

    @Transactional
    @Override
    public UserRecord delete(UserRecord userRecord) {
        userRepository.delete(userMapper.toEntity(userRecord));
        return userRecord;
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Transactional(readOnly = true)
    public boolean entered(String username, String password) {
        return userRepository.findUserByUserName(username).filter(user -> encoder.verify(user.getPassword(), password.toCharArray())).isPresent();
    }

    @Transactional
    @Override
    public int updatePassword(Long id, String password) {
        return Optional.of(userRepository.updatePassword(id, encoder.hash(22, 65536, 1, password.toCharArray()))).orElseThrow(() -> new UsernameNotFoundException("User with id:" + id + " not found"));
    }
}
