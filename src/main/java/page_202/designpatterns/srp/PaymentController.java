package page_202.designpatterns.srp;

interface PaymentService {
    void pay();
}

interface OverseasPaymentService {
    void pay();
}

class ShinhanPaymentService implements OverseasPaymentService {

    @Override
    public void pay() {
        System.out.println("해외결제 가능합니다");
    }
}

class WooriPaymentService implements PaymentService {
    @Override
    public void pay() {
        System.out.println("해외결제 불가능합니다");
    }
}

public class PaymentController {

    private PaymentService paymentService;
    private OverseasPaymentService overseasPaymentService;

    public void pay() {
        paymentService.pay();
    }

    public void overseasPay() {
        overseasPaymentService.pay();
    }

}
