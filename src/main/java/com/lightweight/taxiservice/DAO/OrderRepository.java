package com.lightweight.taxiservice.DAO;

import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findFirstByOrderByIdAsc();

    Optional<Order> findLastByOrderByIdAsc();

    @Query("SELECT o FROM Order o WHERE o.status = 'active'")
    Page<Order> findActiveOrders(Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.status = 'archived'")
    Page<Order> findArchivedOrders(Pageable pageable);

    @Query(value = "SELECT o.*, c.model, c.brand,d.first_name,d.last_name, u.first_name,u.last_name " +
            "FROM orders o " +
            "INNER JOIN users u ON u.id = o.user_id " +
            "INNER JOIN drivers d ON d.id = o.driver_id " +
            "INNER JOIN cars c ON d.id = c.driver_id " +
            "WHERE " +
            "CASE " +
            "WHEN :fieldName = 'comment' THEN LOWER(o.comment) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
            "WHEN :fieldName = 'user' THEN (LOWER(u.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(u.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%'))) " +
            "OR " +
            "(LOWER(u.last_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(u.first_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%'))) " +
            "WHEN :fieldName = 'driver' THEN (LOWER(d.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(d.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%'))) " +
            "OR " +
            "(LOWER(d.last_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(d.first_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%'))) " +
            "WHEN :fieldName = 'car' THEN (LOWER(c.model) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :searchValue, '%'))) " +
            "OR " +
            "(LOWER(c.model) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(c.brand) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%'))) " +
            "END "+
            "AND o.status = :status",
            countQuery = "SELECT COUNT(*) FROM orders o " +
                    "INNER JOIN users u ON u.id = o.user_id " +
                    "INNER JOIN drivers d ON d.id = o.driver_id " +
                    "INNER JOIN cars c ON d.id = c.driver_id " +
                    "WHERE " +
                    "CASE " +
                    "WHEN :fieldName = 'comment' THEN LOWER(o.comment) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
                    "WHEN :fieldName = 'user' THEN (LOWER(u.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(u.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%'))) " +
                    "OR " +
                    "(LOWER(u.last_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(u.first_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%'))) " +
                    "WHEN :fieldName = 'driver' THEN (LOWER(d.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(d.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%'))) " +
                    "OR " +
                    "(LOWER(d.last_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(d.first_name) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%'))) " +
                    "WHEN :fieldName = 'car' THEN (LOWER(c.model) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :searchValue, '%'))) " +
                    "OR " +
                    "(LOWER(c.model) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', -1), '%')) AND LOWER(c.brand) LIKE LOWER(CONCAT('%', SPLIT_PART(:searchValue, ' ', 1), '%'))) " +
                    "END "+
                    "AND o.status = :status",
            nativeQuery = true)
    Page<Order> findByFieldContainingIgnoreCase(@Param("fieldName") String fieldName, @Param("searchValue") String searchValue,@Param("status") String status, Pageable pageable);
}
