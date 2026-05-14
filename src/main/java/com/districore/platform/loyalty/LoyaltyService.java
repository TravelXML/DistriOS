package com.districore.platform.loyalty;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import com.districore.platform.retailer.RetailerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoyaltyService {
    private final LoyaltyAccountRepository accountRepository;
    private final LoyaltyTransactionRepository transactionRepository;
    private final RetailerRepository retailerRepository;

    public LoyaltyService(LoyaltyAccountRepository accountRepository, LoyaltyTransactionRepository transactionRepository,
                          RetailerRepository retailerRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.retailerRepository = retailerRepository;
    }

    public LoyaltyResponse getAccount(String retailerId) {
        retailerRepository.findByIdAndTenantId(java.util.UUID.fromString(retailerId), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Retailer not found"));
        LoyaltyAccount account = accountRepository.findByRetailerIdAndTenantId(retailerId, TenantContext.getTenantId())
                .orElseGet(() -> createAccount(retailerId));
        return new LoyaltyResponse(account.getRetailerId(), account.getPointsBalance(), account.getTier());
    }

    public LoyaltyResponse earnPoints(LoyaltyRequest request) {
        LoyaltyAccount account = accountRepository.findByRetailerIdAndTenantId(request.getRetailerId(), TenantContext.getTenantId())
                .orElseGet(() -> createAccount(request.getRetailerId()));
        account.setPointsBalance(account.getPointsBalance() + request.getPoints());
        accountRepository.save(account);
        LoyaltyTransaction transaction = new LoyaltyTransaction();
        transaction.setAccount(account);
        transaction.setType(LoyaltyTransactionType.EARN);
        transaction.setPoints(request.getPoints());
        transaction.setDescription(request.getDescription());
        transaction.setTenantId(TenantContext.getTenantId());
        transactionRepository.save(transaction);
        return new LoyaltyResponse(account.getRetailerId(), account.getPointsBalance(), account.getTier());
    }

    public LoyaltyResponse redeemPoints(LoyaltyRequest request) {
        LoyaltyAccount account = accountRepository.findByRetailerIdAndTenantId(request.getRetailerId(), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Loyalty account not found"));
        if (account.getPointsBalance() < request.getPoints()) {
            throw new IllegalArgumentException("Insufficient loyalty points");
        }
        account.setPointsBalance(account.getPointsBalance() - request.getPoints());
        accountRepository.save(account);
        LoyaltyTransaction transaction = new LoyaltyTransaction();
        transaction.setAccount(account);
        transaction.setType(LoyaltyTransactionType.REDEEM);
        transaction.setPoints(request.getPoints());
        transaction.setDescription(request.getDescription());
        transaction.setTenantId(TenantContext.getTenantId());
        transactionRepository.save(transaction);
        return new LoyaltyResponse(account.getRetailerId(), account.getPointsBalance(), account.getTier());
    }

    public java.util.List<LoyaltyTransactionResponse> listTransactions(String retailerId) {
        LoyaltyAccount account = accountRepository.findByRetailerIdAndTenantId(retailerId, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Loyalty account not found"));
        return transactionRepository.findByAccountIdAndTenantId(account.getId(), TenantContext.getTenantId()).stream()
                .map(tx -> new LoyaltyTransactionResponse(tx.getId().toString(), tx.getType().name(), tx.getPoints(), tx.getDescription()))
                .collect(Collectors.toList());
    }

    private LoyaltyAccount createAccount(String retailerId) {
        LoyaltyAccount account = new LoyaltyAccount();
        account.setRetailerId(retailerId);
        account.setPointsBalance(0);
        account.setTier("BRONZE");
        account.setTenantId(TenantContext.getTenantId());
        return accountRepository.save(account);
    }
}
