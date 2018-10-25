package by.itechart.consignmentnote.repository;

import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsignmentNoteRepository extends JpaRepository<ConsignmentNote, Long> {

    Page<ConsignmentNote> findAllByConsignmentNoteType(ConsignmentNoteType consignmentNoteType,
                                                                    Pageable pageable);
}
