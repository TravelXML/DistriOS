package com.districore.platform.pricing;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PriceListItemRepository extends JpaRepository<PriceListItem, UUID> {
    List<PriceListItem> findByPriceListId(UUID priceListId);
}
