package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByRoleName(String role);

    Optional<User> findByEmail(String email);

    Optional<User> findFirstByOrderByIdAsc();

    @Query("SELECT dfs FROM User dfs WHERE dfs.email =:email AND dfs.id !=:id")
    Optional<User> findByEmailWhereIdIsNot(@Param("id") Long id, @Param("email") String email);
}
