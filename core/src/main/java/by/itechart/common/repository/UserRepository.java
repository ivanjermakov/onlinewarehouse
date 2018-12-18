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

    @Query(value = "select a.name as name, count(ua.user_id) as y from users u " +
            "inner join user_authority ua on u.id = ua.user_id " +
            "inner join authority a on ua.authority_id = a.id " +
            "where u.company_id = :companyId " +
            "group by a.name", nativeQuery = true)
    List<PieChartData> getUserRolesStatistics(@Param("companyId") Long companyId);

}
