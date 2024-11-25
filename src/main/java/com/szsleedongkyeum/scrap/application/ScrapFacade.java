package com.szsleedongkyeum.scrap.application;

import com.szsleedongkyeum.scrap.model.service.ScrapService;
import com.szsleedongkyeum.scrap.model.service.in.TaxationScrapInput;
import com.szsleedongkyeum.scrap.model.service.out.TaxationScrapResult;
import com.szsleedongkyeum.taxation.model.service.UsersTaxationService;
import com.szsleedongkyeum.user.model.service.UserService;
import com.szsleedongkyeum.user.model.service.out.UserInfoResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapFacade {

    private final UsersTaxationService usersTaxationService;
    private final ScrapService scrapService;
    private final UserService userService;

    public void scrapAggregateIncome() {
        UserInfoResult currentUserInfo = userService.getCurrentUserInfo();
        TaxationScrapInput taxationScrapInput = new TaxationScrapInput(currentUserInfo.name(), currentUserInfo.regNo());
        TaxationScrapResult taxationScrapResult = scrapService.getTaxationScrapResult(taxationScrapInput, "zF9f42h3Ju14Hr3+wEcwBg==",
            "https://codetest-v4.3o3.co.kr/scrap");
        usersTaxationService.saveTaxation(currentUserInfo.userId(), currentUserInfo.name(), taxationScrapResult.convertSaveInput());
    }
}
