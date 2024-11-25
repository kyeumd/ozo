package com.szsleedongkyeum.scrap.api;

import com.szsleedongkyeum.common.response.Response;
import com.szsleedongkyeum.scrap.application.ScrapFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/szs")
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
