package by.itechart.consignmentnote.repository;

import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ConsignmentNoteRepository extends JpaRepository<ConsignmentNote, Long>, QuerydslPredicateExecutor<ConsignmentNote> {

    Optional<ConsignmentNote> findByCompanyIdAndId(long companyId, long consignmentNoteId);

    @Modifying
    @Query("update ConsignmentNote cn set cn.consignmentNoteStatus = ?3 where cn.company.id = ?1 and cn.id =?2")
    void setConsignmentNoteStatus(long companyId, long consignmentNoteId, ConsignmentNoteStatus consignmentNoteStatus);

}
