package com.ozo.scrap.api;

import com.ozo.common.response.Response;
import com.ozo.scrap.application.ScrapFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ozo")
@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapFacade scrapFacade;

    @PostMapping("/scrap")
    public Response<Void> scrapAggregateIncome() {
        scrapFacade.scrapAggregateIncome();
        return Response.success("데이터 수집을 완료했습니다.");
    }
}
