package com.districore.platform.product;

import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HsnCodeService {
    private final HsnCodeRepository repository;

    public HsnCodeService(HsnCodeRepository repository) {
        this.repository = repository;
    }

    public HsnCodeResponse createHsnCode(HsnCodeRequest request) {
        HsnCode code = new HsnCode();
        code.setCode(request.getCode());
        code.setDescription(request.getDescription());
        code.setGstRate(request.getGstRate());
        code.setTenantId(TenantContext.getTenantId());
        repository.save(code);
        return new HsnCodeResponse(code.getId().toString(), code.getCode(), code.getDescription(), code.getGstRate());
    }

    public List<HsnCodeResponse> listHsnCodes() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream()
                .map(code -> new HsnCodeResponse(code.getId().toString(), code.getCode(), code.getDescription(), code.getGstRate()))
                .collect(Collectors.toList());
    }
}
