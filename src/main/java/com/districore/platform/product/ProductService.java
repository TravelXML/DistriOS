package com.districore.platform.product;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import com.districore.platform.product.ProductSerialNumberRepository;
import com.districore.platform.product.ProductSerialNumberRequest;
import com.districore.platform.product.ProductSerialNumberResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final ProductCategoryRepository categoryRepository;
    private final UomRepository uomRepository;
    private final HsnCodeRepository hsnCodeRepository;
    private final ProductSerialNumberRepository serialNumberRepository;

    public ProductService(ProductRepository productRepository,
                          BrandRepository brandRepository,
                          ProductCategoryRepository categoryRepository,
                          UomRepository uomRepository,
                          HsnCodeRepository hsnCodeRepository,
                          ProductSerialNumberRepository serialNumberRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.uomRepository = uomRepository;
        this.hsnCodeRepository = hsnCodeRepository;
        this.serialNumberRepository = serialNumberRepository;
    }

    public ProductResponse createProduct(ProductRequest request) {
        Brand brand = brandRepository.findById(UUID.fromString(request.getBrandId()))
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        ProductCategory category = categoryRepository.findById(UUID.fromString(request.getCategoryId()))
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Uom uom = uomRepository.findById(UUID.fromString(request.getUomId()))
                .orElseThrow(() -> new ResourceNotFoundException("UOM not found"));
        HsnCode hsnCode = hsnCodeRepository.findById(UUID.fromString(request.getHsnCodeId()))
                .orElseThrow(() -> new ResourceNotFoundException("HSN code not found"));
        Product product = new Product();
        product.setSkuCode(request.getSkuCode());
        product.setProductName(request.getProductName());
        product.setBrand(brand);
        product.setCategory(category);
        product.setUom(uom);
        product.setPackSize(request.getPackSize());
        product.setPackSizeUom(request.getPackSizeUom());
        product.setCaseSize(request.getCaseSize());
        product.setBarcode(request.getBarcode());
        product.setHsnCode(hsnCode);
        product.setGstRate(request.getGstRate());
        product.setMrp(request.getMrp());
        product.setBasePrice(request.getBasePrice());
        product.setBatchRequired(request.isBatchRequired());
        product.setExpiryRequired(request.isExpiryRequired());
        product.setSerialNumberRequired(request.isSerialNumberRequired());
        product.setWarrantyRequired(request.isWarrantyRequired());
        product.setComposition(request.getComposition());
        product.setManufacturerName(request.getManufacturerName());
        product.setShelfLifeDays(request.getShelfLifeDays());
        product.setWarrantyMonths(request.getWarrantyMonths());
        product.setRegulatoryCategory(request.getRegulatoryCategory());
        product.setStatus(ProductStatus.ACTIVE);
        product.setTenantId(TenantContext.getTenantId());
        productRepository.save(product);
        return toResponse(product);
    }

    public List<ProductResponse> listProducts() {
        return productRepository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ProductResponse getProduct(UUID id) {
        return toResponse(findById(id));
    }

    public ProductResponse updateProduct(UUID id, ProductRequest request) {
        Product product = findById(id);
        Brand brand = brandRepository.findById(UUID.fromString(request.getBrandId())).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        ProductCategory category = categoryRepository.findById(UUID.fromString(request.getCategoryId())).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Uom uom = uomRepository.findById(UUID.fromString(request.getUomId())).orElseThrow(() -> new ResourceNotFoundException("UOM not found"));
        HsnCode hsnCode = hsnCodeRepository.findById(UUID.fromString(request.getHsnCodeId())).orElseThrow(() -> new ResourceNotFoundException("HSN code not found"));
        product.setSkuCode(request.getSkuCode());
        product.setProductName(request.getProductName());
        product.setBrand(brand);
        product.setCategory(category);
        product.setUom(uom);
        product.setPackSize(request.getPackSize());
        product.setPackSizeUom(request.getPackSizeUom());
        product.setCaseSize(request.getCaseSize());
        product.setBarcode(request.getBarcode());
        product.setHsnCode(hsnCode);
        product.setGstRate(request.getGstRate());
        product.setMrp(request.getMrp());
        product.setBasePrice(request.getBasePrice());
        product.setBatchRequired(request.isBatchRequired());
        product.setExpiryRequired(request.isExpiryRequired());
        product.setSerialNumberRequired(request.isSerialNumberRequired());
        product.setWarrantyRequired(request.isWarrantyRequired());
        product.setComposition(request.getComposition());
        product.setManufacturerName(request.getManufacturerName());
        product.setShelfLifeDays(request.getShelfLifeDays());
        product.setWarrantyMonths(request.getWarrantyMonths());
        product.setRegulatoryCategory(request.getRegulatoryCategory());
        productRepository.save(product);
        return toResponse(product);
    }

    public ProductResponse updateStatus(UUID id, StatusUpdateRequest request) {
        Product product = findById(id);
        product.setStatus(request.getStatus());
        productRepository.save(product);
        return toResponse(product);
    }

    public ProductSerialNumberResponse addSerialNumber(UUID productId, ProductSerialNumberRequest request) {
        Product product = findById(productId);
        ProductSerialNumber serial = new ProductSerialNumber();
        serial.setProduct(product);
        serial.setSerialNumber(request.getSerialNumber());
        serial.setTenantId(TenantContext.getTenantId());
        serialNumberRepository.save(serial);
        return new ProductSerialNumberResponse(serial.getId().toString(), serial.getSerialNumber(), product.getId().toString());
    }

    public java.util.List<ProductSerialNumberResponse> listSerialNumbers(UUID productId) {
        findById(productId);
        return serialNumberRepository.findByProductIdAndTenantId(productId, TenantContext.getTenantId()).stream()
                .map(serial -> new ProductSerialNumberResponse(serial.getId().toString(), serial.getSerialNumber(), serial.getProduct().getId().toString()))
                .collect(Collectors.toList());
    }

    private Product findById(UUID id) {
        return productRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId().toString(), product.getSkuCode(), product.getProductName(), product.getBrand().getName(), product.getCategory().getName(), product.getUom().getCode(), product.getPackSize(), product.getPackSizeUom(), product.getCaseSize(), product.getBarcode(), product.getHsnCode().getCode(), product.getGstRate(), product.getMrp(), product.getBasePrice(), product.getStatus());
    }
}
