package by.itechart.writeoffact.repository;

import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.enums.WriteOffActType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface WriteOffActRepository extends CrudRepository<WriteOffAct, Long> {

    Page<WriteOffAct> findAllByCompany_IdAndWriteOffActTypeAndCreationBetween(Long companyId, WriteOffActType writeOffActType, LocalDate from, LocalDate to, Pageable pageable);

    Page<WriteOffAct> findAllByCompany_IdAndWriteOffActTypeAndCreationAfter(Long companyId, WriteOffActType writeOffActType, LocalDate from, Pageable pageable);

    Page<WriteOffAct> findAllByCompany_IdAndWriteOffActTypeAndCreationBefore(Long companyId, WriteOffActType writeOffActType, LocalDate to, Pageable pageable);

    Page<WriteOffAct> findAllByCompany_IdAndWriteOffActType(Long companyId, WriteOffActType writeOffActType, Pageable pageable);
}
