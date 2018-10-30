package by.itechart.web.controller;

import by.itechart.writeoffact.dto.CreateWriteOffActDto;
import by.itechart.writeoffact.dto.WriteOffActDto;
import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.enums.WriteOffActType;
import by.itechart.writeoffact.service.WriteOffActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/write-off-acts")
public class WriteOffActController {

    private final WriteOffActService writeOffActService;

    @Autowired
    public WriteOffActController(WriteOffActService writeOffActService) {
        this.writeOffActService = writeOffActService;
    }

    @GetMapping
    public List<WriteOffAct> getWriteOffActs(@PathVariable long companyId,
                                             @RequestParam WriteOffActType writeOffActType,
                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                                             @RequestParam(required = false) Pageable pageable) {
        return writeOffActService.getWriteOffActs(companyId, pageable, writeOffActType, from, to).getContent();
    }

    @GetMapping("/{writeOffActId}")
    public WriteOffActDto getWriteOffAct(@PathVariable long companyId, @PathVariable long writeOffActId) {
        return writeOffActService.getWriteOffAct(writeOffActId);
    }

    @PostMapping
    public Long saveWriteOffAct(@PathVariable long companyId, @RequestBody CreateWriteOffActDto writeOffAct) {
        return writeOffActService.saveWriteOffAct(writeOffAct);
    }
}
