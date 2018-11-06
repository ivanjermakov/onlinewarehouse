package by.itechart.web.controller;

import by.itechart.consignmentnote.dto.ConsignmentNoteDto;
import by.itechart.consignmentnote.dto.ConsignmentNoteFilter;
import by.itechart.consignmentnote.dto.ConsignmentNoteListDto;
import by.itechart.consignmentnote.dto.CreateConsignmentNoteDto;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.consignmentnote.service.ConsignmentNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        return consignmentNoteService.getConsignmentNotes(companyId, consignmentNoteFilter, pageable);
    }

    @GetMapping("/{consignmentNoteId}")
    public ConsignmentNoteDto getConsignmentNote(@PathVariable long companyId, @PathVariable long consignmentNoteId) {
        return consignmentNoteService.getConsignmentNote(companyId, consignmentNoteId);
    }

    @PostMapping
    public ConsignmentNoteDto saveConsignmentNote(@PathVariable long companyId,
                                                  @RequestBody CreateConsignmentNoteDto consignmentNote) {
        return consignmentNoteService.saveConsignmentNote(consignmentNote, companyId);
    }
}
