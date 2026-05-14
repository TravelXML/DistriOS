package com.districore.platform.retailer;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import com.districore.platform.order.OrderRepository;
import com.districore.platform.order.OrderResponse;
import com.districore.platform.scheme.SchemeRepository;
import com.districore.platform.scheme.SchemeResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class RetailerService {
    private final RetailerRepository repository;
    private final RetailerKycRepository kycRepository;
    private final RetailerDrugLicenseRepository licenseRepository;
    private final OrderRepository orderRepository;
    private final SchemeRepository schemeRepository;

    public RetailerService(RetailerRepository repository, RetailerKycRepository kycRepository, RetailerDrugLicenseRepository licenseRepository,
                           OrderRepository orderRepository, SchemeRepository schemeRepository) {
        this.repository = repository;
        this.kycRepository = kycRepository;
        this.licenseRepository = licenseRepository;
        this.orderRepository = orderRepository;
        this.schemeRepository = schemeRepository;
    }

    public RetailerResponse createRetailer(CreateRetailerRequest request) {
        Retailer retailer = new Retailer();
        retailer.setName(request.getName());
        retailer.setCategory(request.getCategory());
        retailer.setChannel(request.getChannel());
        retailer.setPhone(request.getPhone());
        retailer.setEmail(request.getEmail());
        retailer.setLocation(request.getLocation());
        retailer.setDistributorId(request.getDistributorId());
        retailer.setBeat(request.getBeat());
        retailer.setDrugLicenseRequired(request.isDrugLicenseRequired());
        retailer.setStatus(RetailerStatus.ACTIVE);
        retailer.setTenantId(TenantContext.getTenantId());
        repository.save(retailer);
        return toResponse(retailer);
    }

    public List<RetailerResponse> listRetailers() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public RetailerResponse getRetailer(UUID id) {
        return toResponse(findById(id));
    }

    public RetailerResponse updateRetailer(UUID id, UpdateRetailerRequest request) {
        Retailer retailer = findById(id);
        retailer.setName(request.getName());
        retailer.setCategory(request.getCategory());
        retailer.setChannel(request.getChannel());
        retailer.setPhone(request.getPhone());
        retailer.setEmail(request.getEmail());
        retailer.setLocation(request.getLocation());
        retailer.setDistributorId(request.getDistributorId());
        retailer.setBeat(request.getBeat());
        repository.save(retailer);
        return toResponse(retailer);
    }

    public RetailerResponse updateStatus(UUID id, StatusUpdateRequest request) {
        Retailer retailer = findById(id);
        retailer.setStatus(request.getStatus());
        repository.save(retailer);
        return toResponse(retailer);
    }

    public RetailerKycResponse addKyc(UUID id, RetailerKycRequest request) {
        Retailer retailer = findById(id);
        RetailerKyc kyc = new RetailerKyc();
        kyc.setDocumentType(request.getDocumentType());
        kyc.setDocumentNumber(request.getDocumentNumber());
        kyc.setIssuedDate(request.getIssuedDate());
        kyc.setExpiryDate(request.getExpiryDate());
        kyc.setRetailer(retailer);
        kyc.setTenantId(TenantContext.getTenantId());
        kycRepository.save(kyc);
        return new RetailerKycResponse(kyc.getId().toString(), kyc.getDocumentType(), kyc.getDocumentNumber(), kyc.getIssuedDate(), kyc.getExpiryDate());
    }

    public RetailerDrugLicenseResponse addDrugLicense(UUID id, RetailerDrugLicenseRequest request) {
        Retailer retailer = findById(id);
        RetailerDrugLicense license = new RetailerDrugLicense();
        license.setLicenseNumber(request.getLicenseNumber());
        license.setValidFrom(request.getValidFrom());
        license.setValidUntil(request.getValidUntil());
        license.setDocumentUrl(request.getDocumentUrl());
        license.setRetailer(retailer);
        license.setTenantId(TenantContext.getTenantId());
        licenseRepository.save(license);
        return new RetailerDrugLicenseResponse(license.getId().toString(), license.getLicenseNumber(), license.getValidFrom(), license.getValidUntil(), license.getDocumentUrl());
    }

    public RetailerLedgerResponse getLedger(UUID id) {
        Retailer retailer = findById(id);
        return new RetailerLedgerResponse(retailer.getId().toString(), retailer.getName(), 0, 0);
    }

    public java.util.List<OrderResponse> listOrders(UUID retailerId) {
        return orderRepository.findByTenantId(TenantContext.getTenantId()).stream()
                .filter(order -> retailerId.toString().equals(order.getRetailerId()))
                .map(order -> new OrderResponse(order.getId().toString(), order.getRetailerId(), order.getDistributorId(), order.getStatus(), order.getSource(), order.getLineItems().stream().map(item -> new com.districore.platform.order.OrderLineResponse(item.getProduct().getId().toString(), item.getQuantity(), item.getUnitPrice(), item.getTotalPrice())).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public java.util.List<SchemeResponse> listEligibleSchemes(UUID retailerId) {
        findById(retailerId);
        return schemeRepository.findByTenantId(TenantContext.getTenantId()).stream()
                .map(scheme -> new SchemeResponse(scheme.getId().toString(), scheme.getName(), scheme.getType(), scheme.isActive()))
                .collect(Collectors.toList());
    }

    private Retailer findById(UUID id) {
        return repository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Retailer not found"));
    }

    private RetailerResponse toResponse(Retailer retailer) {
        return new RetailerResponse(retailer.getId().toString(), retailer.getName(), retailer.getCategory(), retailer.getChannel(), retailer.getPhone(), retailer.getEmail(), retailer.getLocation(), retailer.getDistributorId(), retailer.getBeat(), retailer.getStatus());
    }
}
