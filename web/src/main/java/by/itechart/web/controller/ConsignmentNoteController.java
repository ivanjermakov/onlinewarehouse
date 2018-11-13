package by.itechart.web.controller;

import by.itechart.common.dto.GoodsDto;
import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import by.itechart.consignmentnote.dto.ConsignmentNoteDto;
import by.itechart.consignmentnote.dto.ConsignmentNoteFilter;
import by.itechart.consignmentnote.dto.ConsignmentNoteListDto;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.consignmentnote.service.ConsignmentNoteService;
import by.itechart.counterparty.dto.CounterpartyDto;
import by.itechart.warehouse.dto.CreatePlacementDto;
import by.itechart.warehouse.dto.CreateWarehouseDto;
import by.itechart.warehouse.dto.PlacementGoodsDto;
import by.itechart.warehouse.dto.WarehouseDto;
import by.itechart.warehouse.entity.Warehouse;
import by.itechart.warehouse.service.PlacementService;
import by.itechart.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/consignment-notes")
public class ConsignmentNoteController {

    private ConsignmentNoteService consignmentNoteService;

    @Autowired
    public ConsignmentNoteController(ConsignmentNoteService consignmentNoteService) {
        this.consignmentNoteService = consignmentNoteService;
    }

    @GetMapping
    public Page<ConsignmentNoteListDto> getConsignmentNotes(@PathVariable long companyId,
                                                            ConsignmentNoteFilter consignmentNoteFilter,
                                                            Pageable pageable) {
        System.out.println(consignmentNoteFilter);
        return consignmentNoteService.getConsignmentNotes(companyId, consignmentNoteFilter, pageable);
    }

    @GetMapping("/{consignmentNoteId}")
    public ConsignmentNoteDto getConsignmentNote(@PathVariable long companyId, @PathVariable long consignmentNoteId) {
        return consignmentNoteService.getConsignmentNote(companyId, consignmentNoteId);
    }

    @PostMapping
    public Long saveConsignmentNote(@PathVariable long companyId,
                                                  @RequestBody CreateConsignmentNoteDto consignmentNote) {
        return consignmentNoteService.saveConsignmentNote(consignmentNote, companyId);
    }
}
