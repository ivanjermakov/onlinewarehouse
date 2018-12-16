package by.itechart.counterparty.repository;

import by.itechart.common.repository.PieChartData;
import by.itechart.counterparty.entity.Counterparty;
import by.itechart.counterparty.enums.CounterpartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CounterpartyRepository extends JpaRepository<Counterparty, Long>, QuerydslPredicateExecutor<Counterparty> {

    Optional<Counterparty> findByCompanyIdAndId(long companyId, long counterpartyId);

    Page<Counterparty> findAllByCompanyIdAndCounterpartyType(Long companyId, CounterpartyType counterpartyType, Pageable pageable);

    @Modifying
    @Query("update Counterparty c set c.deleted = current_date where c.id = ?1")
    void setDeleted(Long counterpartyId);

    @Query(value = "select c2.name as name, sum(g.cost * cng.amount) as y from consignment_note cn " +
            "inner join counterparty c2 on cn.counterparty_id = c2.id " +
            "inner join consignment_note_goods cng on cn.id = cng.consignment_note_id " +
            "inner join goods g on cng.goods_id = g.id " +
            "where cn.consignment_note_type = :consignmentNoteType and cn.company_id = :companyId " +
            "group by cn.counterparty_id, c2.name", nativeQuery = true)
    List<PieChartData> getCounterpartiesGoodsCostStatistics(@Param("consignmentNoteType") String consignmentNoteType,
                                                            @Param("companyId") long companyId);

}
