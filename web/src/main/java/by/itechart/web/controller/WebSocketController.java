package by.itechart.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    void processDispatcherCommodityLot(long companyId) {
        simpMessagingTemplate.convertAndSend("/company/" + companyId + "/dispatcher/commodity-lot",
                "New commodity lot has been added");
    }

    void processManagerWarehouse(long companyId) {
        simpMessagingTemplate.convertAndSend("/company/" + companyId + "/manager/warehouse",
                "New warehouse has been added");
    }

    void processManagerWriteOffCase(long companyId) {
        simpMessagingTemplate.convertAndSend("/company/" + companyId + "/manager/write-off-case",
                "New write-off case has been added");
    }

    void processManagerCommodityLot(long companyId) {
        simpMessagingTemplate.convertAndSend("/company/" + companyId + "/manager/commodity-lot",
                "New commodity lot to process has been added");
    }

    void processManagerCommodityLotProcessed(long companyId) {
        simpMessagingTemplate.convertAndSend("/company/" + companyId + "/manager/commodity-lot-processed",
                "Commodity lot has been shipped");
    }

    void processManagerConsignmentNote(long companyId) {
        simpMessagingTemplate.convertAndSend("/company/" + companyId + "/manager/consignment-note",
                "New consignment note has been added");
    }

    void processInspectorConsignmentNote(long companyId) {
        simpMessagingTemplate.convertAndSend("/company/" + companyId + "/inspector/consignment-note",
                "New consignment note has been added");
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        simpMessagingTemplate.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }
}
