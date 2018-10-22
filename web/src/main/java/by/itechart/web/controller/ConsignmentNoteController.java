package by.itechart.web.controller;

import by.itechart.consignmentnote.entity.ConsignmentNote;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company/{companyId}/consignment-note")
public class ConsignmentNoteController {

    @GetMapping
    public List<ConsignmentNote> getConsignmentNotes(@PathVariable long companyId,
                                                     @RequestParam ConsignmentNoteType consignmentNoteType,
                                                     @RequestParam(value = "from", required = false) LocalDate from,
                                                     @RequestParam(value = "to", required = false) LocalDate to) {
        List<ConsignmentNote> consignmentNotes = new ArrayList<>();
        // return list of consignmentNotes with ConsignmentNoteType and in this period of time
        for (int i = 0; i < 10; i++) {
            consignmentNotes.add(createConsignmentNote(i));
        }
        return consignmentNotes;
    }

    @GetMapping("/{consignmentNoteId}")
    public ConsignmentNote getConsignmentNote(@PathVariable long companyId, @PathVariable long consignmentNoteId) {
        ConsignmentNote consignmentNote = createConsignmentNote(consignmentNoteId);
        // return consignmentNote with companyId and consignmentNoteId
        return consignmentNote;
    }

    @PostMapping
    public Long saveCompany(@PathVariable long companyId, @RequestBody ConsignmentNote consignmentNote) {
        // save consignmentNote and return generated consignmentNote id
        Long id = 10L;
        return id;
    }

    private ConsignmentNote createConsignmentNote(long id) {
        ConsignmentNote consignmentNote = new ConsignmentNote();
        consignmentNote.setId(id);
        consignmentNote.setNumber("№" + id);
        consignmentNote.setConsignmentNoteType(ConsignmentNoteType.OUT);
        return consignmentNote;
    }
}
