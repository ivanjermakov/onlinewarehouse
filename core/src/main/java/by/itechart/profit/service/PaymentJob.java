package by.itechart.profit.service;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentJob implements Job {

    private final PaymentService paymentService;

    @Autowired
    public PaymentJob(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void execute(JobExecutionContext context) {
        paymentService.makePayments();
    }
}