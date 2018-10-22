package by.itechart.web.controller;

import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.enums.WriteOffActType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/write-off-acts")
public class WriteOffActController {

    @GetMapping
    public List<WriteOffAct> getWriteOffActs(@PathVariable long companyId,
                                             @RequestParam WriteOffActType writeOffActType,
                                             @RequestParam(value = "from", required = false) LocalDate from,
                                             @RequestParam(value = "to", required = false) LocalDate to) {
        List<WriteOffAct> writeOffActs = new ArrayList<>();
        // return list of writeOffActs with WriteOffActType and in this period of time
        for (int i = 0; i < 10; i++) {
            writeOffActs.add(createWriteOffAct(i));
        }
        return writeOffActs;
    }

    @GetMapping("/{writeOffActId}")
    public WriteOffAct getWriteOffAct(@PathVariable long companyId, @PathVariable long writeOffActId) {
        WriteOffAct writeOffAct = createWriteOffAct(writeOffActId);
        // return writeOffAct with companyId and writeOffActId
        return writeOffAct;
    }

    @PostMapping
    public Long saveWriteOffAct(@PathVariable long companyId, @RequestBody WriteOffAct writeOffAct) {
        // save writeOffAct and return generated writeOffAct id
        Long id = 10L;
        return id;
    }

    private WriteOffAct createWriteOffAct(long id) {
        WriteOffAct writeOffAct = new WriteOffAct();
        writeOffAct.setId(id);
        writeOffAct.setTotalAmount(5);
        return writeOffAct;
    }
}
