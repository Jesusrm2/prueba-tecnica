package com.jesus.pruebatec.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByIdUser(Integer username);
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);
    @Modifying
    @Query("update User u set u.sesionStatus=:sesionStatus where u.idUser = :idUser")
    void updateUser(
            @Param("idUser") Integer id,
            @Param("sesionStatus") String sesionStatus);
    @Query(" select u.username, u.sesionStatus, u.userStatus, s.fechaIngreso, s.fechaCierrre from User u INNER JOIN Session s on u.idUser = s.userId")
    void getUserSession();
}