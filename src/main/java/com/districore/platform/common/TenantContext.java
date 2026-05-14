package com.districore.platform.common;

public final class TenantContext {
    private static final ThreadLocal<String> TENANT = new ThreadLocal<>();
    private static final String DEFAULT_TENANT = "default";

    private TenantContext() {}

    public static void setTenantId(String tenantId) {
        TENANT.set(tenantId);
    }

    public static String getTenantId() {
        String tenantId = TENANT.get();
        return tenantId != null && !tenantId.isBlank() ? tenantId : DEFAULT_TENANT;
    }

    public static void clear() {
        TENANT.remove();
    }
}
