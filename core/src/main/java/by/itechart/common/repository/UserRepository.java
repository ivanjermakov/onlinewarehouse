package by.itechart.common.repository;

import by.itechart.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {

    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.deleted=current_date where u.id = ?1")
    void setDeleted(Long userId);
}
