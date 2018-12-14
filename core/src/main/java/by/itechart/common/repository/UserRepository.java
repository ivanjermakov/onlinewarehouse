package by.itechart.common.repository;

import by.itechart.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {

    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.deleted=current_date where u.id = ?1 and u.company.id = ?2")
    void setDeleted(Long userId, Long companyId);


    @Query("select u from User u where u.company.id = :companyId and u.birth = :birthDay")
    Set<User> getUsersWithBirthday(@Param("companyId") Long companyId, @Param("birthDay") LocalDate birthDay);

    User findUserByCompany_IdAndIdAndDeletedIsNull(Long companyId, Long id);

    Set<User> findAllById(List<Long> id);

    User findUserByUsername(String username);

    Optional<User> findUserByActivationCode(String code);

}
