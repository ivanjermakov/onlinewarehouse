package by.itechart.mail.repository;

import by.itechart.mail.entity.BirthdayMailSend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

public interface BirthdayMailSendRepository extends JpaRepository<BirthdayMailSend, Long> {

    @Query(value = "select distinct user_id\n" +
            "from birthday_mail_send\n" +
            "where timestamp = ?1\n" +
            "  and user_id not in (select user_id from birthday_mail_send where timestamp = ?1\n" +
            "                                                               and successful = true)", nativeQuery = true)
    Set<BigInteger> getUsersToCongratulate(LocalDate date);

    @Query(value = "select distinct count(successful)\n" +
            "from birthday_mail_send\n" +
            "where user_id = ?1 and timestamp = ?2 and successful = false", nativeQuery = true)
    Integer getCelebrateAttemptsCount(Long userId, LocalDate date);
}
