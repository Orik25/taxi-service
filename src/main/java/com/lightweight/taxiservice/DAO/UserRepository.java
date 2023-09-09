package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByRoleName(String role);

    Optional<User> findByEmail(String email);

    Optional<User> findFirstByOrderByIdAsc();

    @Query(value = "SELECT u.*,r.id as roles_id, r.name FROM users u " +
            "INNER JOIN roles r ON u.role_id = r.id " +
            "WHERE " +
            "CASE " +
            "WHEN :fieldName = 'first_name' THEN LOWER(u.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'last_name' THEN LOWER(u.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'phone' THEN LOWER(u.phone) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'email' THEN LOWER(u.email) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'role' THEN LOWER(r.name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "END",
            countQuery = "SELECT COUNT(*) FROM users u " +
                    "INNER JOIN roles r ON u.role_id = r.id " +
                    "WHERE " +
                    "CASE " +
                    "WHEN :fieldName = 'first_name' THEN LOWER(u.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'last_name' THEN LOWER(u.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'phone' THEN LOWER(u.phone) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'email' THEN LOWER(u.email) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'role' THEN LOWER(r.name) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "END",
            nativeQuery = true)
    Page<User> findByFieldContainingIgnoreCase(@Param("fieldName") String fieldName, @Param("searchValue") String searchValue, Pageable pageable);


}
