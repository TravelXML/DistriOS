package com.districore.platform.pricing;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import com.districore.platform.product.ProductRepository;
import com.districore.platform.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PricingService {
    private final PriceListRepository priceListRepository;
    private final PriceListItemRepository itemRepository;
    private final ProductRepository productRepository;

    public PricingService(PriceListRepository priceListRepository, PriceListItemRepository itemRepository, ProductRepository productRepository) {
        this.priceListRepository = priceListRepository;
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
    }

    public PriceListResponse createPriceList(PriceListRequest request) {
        PriceList priceList = new PriceList();
        priceList.setName(request.getName());
        priceList.setDistributorId(request.getDistributorId());
        priceList.setRetailerId(request.getRetailerId());
        priceList.setRegion(request.getRegion());
        priceList.setTenantId(TenantContext.getTenantId());
        priceListRepository.save(priceList);
        return toResponse(priceList);
    }

    public List<PriceListResponse> listPriceLists() {
        return priceListRepository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public PriceListResponse getPriceList(UUID id) {
        return toResponse(priceListRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Price list not found")));
    }

    public PriceListItemResponse createPriceListItem(UUID priceListId, PriceListItemRequest request) {
        PriceList priceList = priceListRepository.findByIdAndTenantId(priceListId, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Price list not found"));
        Product product = productRepository.findByIdAndTenantId(UUID.fromString(request.getProductId()), TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        PriceListItem item = new PriceListItem();
        item.setPriceList(priceList);
        item.setProduct(product);
        item.setPrice(request.getPrice());
        item.setPriceType(request.getPriceType());
        item.setTenantId(TenantContext.getTenantId());
        itemRepository.save(item);
        return new PriceListItemResponse(item.getId().toString(), item.getPriceList().getId().toString(), item.getProduct().getId().toString(), item.getPrice(), item.getPriceType());
    }

    public List<PriceListItemResponse> getProductPrices(String productId) {
        return itemRepository.findByProductIdAndTenantId(UUID.fromString(productId), TenantContext.getTenantId()).stream()
                .map(item -> new PriceListItemResponse(item.getId().toString(), item.getPriceList().getId().toString(), item.getProduct().getId().toString(), item.getPrice(), item.getPriceType()))
                .collect(Collectors.toList());
    }

    public List<PriceListItemResponse> getRetailerApplicablePrices(String retailerId) {
        List<PriceList> lists = priceListRepository.findByRetailerIdAndTenantId(retailerId, TenantContext.getTenantId());
        return lists.stream()
                .flatMap(priceList -> itemRepository.findByPriceListId(priceList.getId()).stream())
                .map(item -> new PriceListItemResponse(item.getId().toString(), item.getPriceList().getId().toString(), item.getProduct().getId().toString(), item.getPrice(), item.getPriceType()))
                .collect(Collectors.toList());
    }

    public PriceCalculationResponse calculatePrice(PricingCalculateRequest request) {
        Product product = productRepository.findByIdAndTenantId(UUID.fromString(request.getProductId()), TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        BigDecimal base = product.getBasePrice();
        BigDecimal taxAmount = base.multiply(product.getGstRate().divide(BigDecimal.valueOf(100)));
        BigDecimal total = base.add(taxAmount);
        return new PriceCalculationResponse(product.getId().toString(), base, taxAmount, total);
    }

    private PriceListResponse toResponse(PriceList list) {
        return new PriceListResponse(list.getId().toString(), list.getName(), list.getDistributorId(), list.getRetailerId(), list.getRegion());
    }
}
