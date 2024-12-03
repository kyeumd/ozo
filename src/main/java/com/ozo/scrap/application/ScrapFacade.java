package com.ozo.scrap.application;

import com.ozo.scrap.model.service.ScrapService;
import com.ozo.scrap.model.service.in.TaxationScrapInput;
import com.ozo.scrap.model.service.out.TaxationScrapResult;
import com.ozo.taxation.model.service.UsersTaxationSaveService;
import com.ozo.user.model.service.UserService;
import com.ozo.user.model.service.out.UserInfoResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapFacade {

    private final UsersTaxationSaveService usersTaxationService;
    private final ScrapService scrapService;
    private final UserService userService;

    public void scrapAggregateIncome() {
        UserInfoResult currentUserInfo = userService.getCurrentUserInfo();
        usersTaxationService.validateDuplicate(currentUserInfo.userId());
        TaxationScrapInput taxationScrapInput = new TaxationScrapInput(currentUserInfo.name(), currentUserInfo.regNo());
        TaxationScrapResult taxationScrapResult = scrapService.getTaxationScrapResult(taxationScrapInput, "zF9f42h3Ju14Hr3+wEcwBg==",
            "https://test");
        usersTaxationService.saveTaxation(currentUserInfo.userId(), currentUserInfo.name(), taxationScrapResult.convertSaveInput());
    }
}
