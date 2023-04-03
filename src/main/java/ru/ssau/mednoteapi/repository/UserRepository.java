package ru.ssau.mednoteapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ssau.mednoteapi.entity.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String userName);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    int updatePassword(@Param(value = "id") Long id, @Param(value = "password") String password);
}