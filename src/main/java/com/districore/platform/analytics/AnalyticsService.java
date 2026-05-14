package com.districore.platform.analytics;

import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    public SalesSummary getSalesSummary() {
        // Mock implementation
        return new SalesSummary(1250000.00, 450, 2777.78, "Last 30 days");
    }

    public String getDistributorPerformance() {
        // Mock implementation
        return "Top distributor: Core Distributors - 85% target achievement. Average performance: 72%.";
    }

    public String getRetailerPerformance() {
        // Mock implementation
        return "Top retailer: Elite Retail - ₹2.5L sales. Total active retailers: 150.";
    }

    public String getProductPerformance() {
        // Mock implementation
        return "Top product: Daily Fresh Juice - 1200 units sold. FMCG category: 65% of total sales.";
    }

    public String getSchemePerformance() {
        // Mock implementation
        return "Most effective scheme: FMCG Percentage Off - 15% uplift. Total scheme benefit: ₹85K.";
    }

    public String getStockMovement() {
        // Mock implementation
        return "Stock turnover: 8.5 times. Slow moving items: 12 products. Out of stock incidents: 3.";
    }

    public String getSfaProductivity() {
        // Mock implementation
        return "Average visits per rep: 25/day. Order conversion rate: 68%. Route efficiency: 82%.";
    }

    public String getClaimsSummary() {
        // Mock implementation
        return "Total claims: 45. Approved: 38. Pending: 7. Average processing time: 2.5 days.";
    }
}