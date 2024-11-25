package com.szsleedongkyeum.scrap.model.service;

import com.szsleedongkyeum.scrap.model.service.in.TaxationScrapInput;
import com.szsleedongkyeum.scrap.model.service.out.TaxationScrapResult;
import com.szsleedongkyeum.utils.RestApiUtil;
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
