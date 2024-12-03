package com.ozo.scrap.model.service;

import com.ozo.scrap.model.service.in.TaxationScrapInput;
import com.ozo.scrap.model.service.out.TaxationScrapResult;
import com.ozo.utils.RestApiUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class ScrapService {

    public TaxationScrapResult getTaxationScrapResult(TaxationScrapInput taxationScrapInput, String apiKey, String url) {
        ParameterizedTypeReference<TaxationScrapResult> typeReference = new ParameterizedTypeReference<>() {
        };
        return RestApiUtil.postData(url, apiKey, taxationScrapInput, typeReference);
    }
}
