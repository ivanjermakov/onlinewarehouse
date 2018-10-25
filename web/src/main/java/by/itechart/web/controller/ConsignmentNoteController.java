package by.itechart.web.controller;

import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.consignmentnote.service.ConsignmentNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/consignment-notes")
public class ConsignmentNoteController {
    // TODO companyID
    private ConsignmentNoteService consignmentNoteService;

    @Autowired
    public ConsignmentNoteController(ConsignmentNoteService consignmentNoteService) {
        this.consignmentNoteService = consignmentNoteService;
    }

    @GetMapping
    public List<ConsignmentNote> getConsignmentNotes(@PathVariable long companyId,
                                                     @RequestParam ConsignmentNoteType consignmentNoteType,
                                                     @RequestParam(value = "from", required = false) LocalDate from,
                                                     @RequestParam(value = "to", required = false) LocalDate to,
                                                     Pageable pageable) {
        return consignmentNoteService.getConsignmentNotes(consignmentNoteType, pageable).getContent(); //TODO Date
    }

    @GetMapping("/{consignmentNoteId}")
    public ConsignmentNote getConsignmentNote(@PathVariable long companyId, @PathVariable long consignmentNoteId) {
        return consignmentNoteService.getConsignmentNote(consignmentNoteId);
    }

    @PostMapping
    public Long saveCompany(@PathVariable long companyId, @RequestBody ConsignmentNote consignmentNote) {
        return consignmentNoteService.saveOrUpdateConsignmentNote(consignmentNote);
    }
}
