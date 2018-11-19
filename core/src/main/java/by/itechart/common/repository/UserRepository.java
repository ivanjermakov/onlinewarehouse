package by.itechart.common.repository;

import by.itechart.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {

    Page<User> findAllByCompany_IdAndDeletedIsNull(Long companyId, Pageable pageable);

    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.deleted=current_date where u.id = ?1")
    void setDeleted(Long userId);

    @Query("select u from User u where u.company.id = :companyId and u.birth = :birthDay")
    Set<User> getUsersWithBirthday(@Param("companyId") Long companyId, @Param("birthDay") LocalDate birthDay);

    Set<User> findAllById(List<Long> id);
}
