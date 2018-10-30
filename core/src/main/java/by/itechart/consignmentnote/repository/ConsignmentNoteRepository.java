package by.itechart.consignmentnote.repository;

import by.itechart.consignmentnote.entity.ConsignmentNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ConsignmentNoteRepository extends JpaRepository<ConsignmentNote, Long>, QuerydslPredicateExecutor<ConsignmentNote> {

    ConsignmentNote findByCompanyIdAndId(long companyId, long consignmentNoteId);
}
