package by.itechart.web.controller;

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
//        List<WriteOffAct> writeOffActs = new ArrayList<>();
//        // return list of writeOffActs with WriteOffActType and in this period of time
//        for (int i = 0; i < 10; i++) {
//            writeOffActs.add(createWriteOffAct(i));
//        }
//        return writeOffActs;
        return writeOffActService.getWriteOffActs(companyId, pageable, writeOffActType, from, to).getContent();
    }

    @GetMapping("/{writeOffActId}")
    public WriteOffAct getWriteOffAct(@PathVariable long companyId, @PathVariable long writeOffActId) {
//        WriteOffAct writeOffAct = createWriteOffAct(writeOffActId);
//        // return writeOffAct with companyId and writeOffActId
//        return writeOffAct;
        return writeOffActService.getWriteOffAct(writeOffActId);
    }

    @PostMapping
    public Long saveWriteOffAct(@PathVariable long companyId, @RequestBody WriteOffAct writeOffAct) {
//        // save writeOffAct and return generated writeOffAct id
//        Long id = 10L;
//        return id;
        return writeOffActService.saveWriteOffAct(writeOffAct);
    }

    private WriteOffAct createWriteOffAct(long id) {
        WriteOffAct writeOffAct = new WriteOffAct();
        writeOffAct.setId(id);
        writeOffAct.setTotalAmount(5);
        return writeOffAct;
    }
}
