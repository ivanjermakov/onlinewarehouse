package by.itechart.common.repository;

import by.itechart.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Page<User> findAllByCompany_IdAndDeletedIsNull(Long companyId, Pageable pageable);

    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.deleted=current_date where u.id = ?1")
    void setDeleted(Long userId);
}
