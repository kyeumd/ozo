package com.szsleedongkyeum.refund.api;

import com.szsleedongkyeum.common.response.Response;
import com.szsleedongkyeum.refund.api.response.RefundResponse;
import com.szsleedongkyeum.refund.application.RefundFacade;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/szs")
@RestController
@RequiredArgsConstructor
public class RefundController {

    private final RefundFacade refundFacade;

    @PostMapping("/refund")
    public Response<RefundResponse> calculateRefund() {
        BigDecimal finalTaxAmount = refundFacade.calculateRefund();
        return Response.success(RefundResponse.from(finalTaxAmount));
    }
}
