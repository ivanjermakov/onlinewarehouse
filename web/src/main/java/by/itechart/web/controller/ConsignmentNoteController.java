package by.itechart.web.controller;

import by.itechart.consignmentnote.dto.*;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.service.ConsignmentNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/consignment-notes")
public class ConsignmentNoteController {

    private ConsignmentNoteService consignmentNoteService;
    private WebSocketController webSocketController;

    @Autowired
    public ConsignmentNoteController(ConsignmentNoteService consignmentNoteService, WebSocketController webSocketController) {
        this.consignmentNoteService = consignmentNoteService;
        this.webSocketController = webSocketController;
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
    public Long saveConsignmentNote(@PathVariable long companyId,
                                    @RequestBody CreateConsignmentNoteDto consignmentNote) {
        Long id = consignmentNoteService.saveConsignmentNote(consignmentNote, companyId);
        webSocketController.processManagerConsignmentNote(companyId);
        webSocketController.processInspectorConsignmentNote(companyId);
        return id;
    }

    @PutMapping("/{consignmentNoteId}")
    public Long setConsignmentNoteStatus(@PathVariable long companyId,
                                         @PathVariable long consignmentNoteId,
                                         ConsignmentNoteStatus status) {
        return consignmentNoteService.setConsignmentNoteStatus(consignmentNoteId, status, companyId);
    }

    @PutMapping
    public Long updateConsignmentNote(@PathVariable long companyId,
                                      @RequestBody UpdateConsignmentNoteDto consignmentNote) {
        return consignmentNoteService.updateConsignmentNote(consignmentNote, companyId);
    }
}
