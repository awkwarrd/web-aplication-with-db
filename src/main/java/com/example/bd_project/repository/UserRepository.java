package com.example.bd_project.repository;
import com.example.bd_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);

    @Query(value = "SELECT u.id AS userId, u.name AS userName, g.name AS goalName, " +
            "COALESCE(SUM(CASE WHEN t.type = 'Income' THEN t.amount ELSE 0 END), 0) - " +
            "COALESCE(SUM(CASE WHEN t.type = 'Expenses' THEN t.amount ELSE 0 END), 0) AS netAmount, " +
            "g.target_amount AS targetAmount, " +
            "g.progress AS progress " +
            "FROM users u " +
            "LEFT JOIN goals g ON g.user_id = u.id " +
            "LEFT JOIN transactions t ON t.user_id = u.id " +
            "WHERE u.name LIKE %:userName% " +
            "GROUP BY u.id, u.name, g.name, g.target_amount, g.progress ", nativeQuery = true)
    List<Object[]> getUsersWithGoalsAndTransactionSums(@Param("userName") String userName);


}
