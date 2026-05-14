INSERT INTO companies (id, tenant_id, name, industry_vertical, created_at, updated_at, created_by, updated_by)
VALUES (uuid_generate_v4(), 'default', 'DistriCore Holdings', 'FMCG', now(), now(), 'system', 'system');

INSERT INTO industry_configs (id, tenant_id, industry_vertical, batch_required, expiry_required, hsn_mandatory, gst_mandatory, license_required, created_at, updated_at, created_by, updated_by)
VALUES
(uuid_generate_v4(), 'default', 'FMCG', false, false, false, false, false, now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', 'OTC_PHARMA', true, true, true, true, true, now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', 'FMCD', false, false, false, false, false, now(), now(), 'system', 'system');

INSERT INTO permissions (id, code, description) VALUES
(uuid_generate_v4(), 'USER_MANAGE', 'Manage users'),
(uuid_generate_v4(), 'DISTRIBUTOR_MANAGE', 'Manage distributors'),
(uuid_generate_v4(), 'RETAILER_MANAGE', 'Manage retailers'),
(uuid_generate_v4(), 'PRODUCT_MANAGE', 'Manage products'),
(uuid_generate_v4(), 'ORDER_MANAGE', 'Manage orders');

INSERT INTO roles (id, name) VALUES
(uuid_generate_v4(), 'SUPER_ADMIN'),
(uuid_generate_v4(), 'COMPANY_ADMIN'),
(uuid_generate_v4(), 'DISTRIBUTOR_ADMIN'),
(uuid_generate_v4(), 'SALES_MANAGER'),
(uuid_generate_v4(), 'SALES_REP'),
(uuid_generate_v4(), 'RETAILER'),
(uuid_generate_v4(), 'WAREHOUSE_USER'),
(uuid_generate_v4(), 'FINANCE_USER'),
(uuid_generate_v4(), 'SUPPORT_USER');

INSERT INTO users (id, tenant_id, username, password, full_name, enabled, created_at, updated_at, created_by, updated_by)
VALUES (uuid_generate_v4(), 'default', 'admin', '$2b$12$dDzyL2iROKDlStAld1hZWe50OI2ixCaHwmoTkb1oInT/XXhWu/XP6', 'Default Admin', true, now(), now(), 'system', 'system');

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'admin' AND r.name = 'SUPER_ADMIN';

INSERT INTO brands (id, tenant_id, name, created_at, updated_at, created_by, updated_by)
VALUES
(uuid_generate_v4(), 'default', 'DistriBrand', now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', 'CoreElectro', now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', 'PharmaSense', now(), now(), 'system', 'system');

INSERT INTO product_categories (id, tenant_id, name, parent_category, created_at, updated_at, created_by, updated_by)
VALUES
(uuid_generate_v4(), 'default', 'Beverages', null, now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', 'Household', null, now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', 'Consumer Electronics', null, now(), now(), 'system', 'system');

INSERT INTO uoms (id, tenant_id, code, description, created_at, updated_at, created_by, updated_by)
VALUES
(uuid_generate_v4(), 'default', 'PCS', 'Pieces', now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', 'BOX', 'Box', now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', 'CASE', 'Case', now(), now(), 'system', 'system');

INSERT INTO hsn_codes (id, tenant_id, code, description, gst_rate, created_at, updated_at, created_by, updated_by)
VALUES
(uuid_generate_v4(), 'default', '1001', 'Wheat and meslin', 5.00, now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', '3004', 'Medicaments', 12.00, now(), now(), 'system', 'system'),
(uuid_generate_v4(), 'default', '8523', 'Discs, tapes etc.', 18.00, now(), now(), 'system', 'system');

INSERT INTO products (id, tenant_id, sku_code, product_name, brand_id, category_id, uom_id, pack_size, pack_size_uom, case_size, barcode, hsn_code_id, gst_rate, mrp, base_price, batch_required, expiry_required, serial_number_required, warranty_required, composition, manufacturer_name, shelf_life_days, warranty_months, regulatory_category, status, created_at, updated_at, created_by, updated_by)
SELECT uuid_generate_v4(), 'default', 'FMCG-001', 'Daily Fresh Juice', b.id, c.id, u.id, 12, 'PCS', 144, '1234567890123', h.id, 5.00, 120.00, 90.00, false, false, false, false, 'Fruit juice blend', 'DistriBrand Foods', 365, 0, 'General', 'ACTIVE', now(), now(), 'system', 'system'
FROM brands b, product_categories c, uoms u, hsn_codes h
WHERE b.name = 'DistriBrand' AND c.name = 'Beverages' AND u.code = 'PCS' AND h.code = '1001'
UNION ALL
SELECT uuid_generate_v4(), 'default', 'FMCD-001', 'CoreHome Washing Machine', b.id, c.id, u.id, 1, 'PCS', 1, '2234567890123', h.id, 18.00, 45000.00, 40000.00, false, false, true, true, 'High-efficiency washer', 'CoreElectro Industries', 0, 24, 'Appliance', 'ACTIVE', now(), now(), 'system', 'system'
FROM brands b, product_categories c, uoms u, hsn_codes h
WHERE b.name = 'CoreElectro' AND c.name = 'Consumer Electronics' AND u.code = 'PCS' AND h.code = '8523'
UNION ALL
SELECT uuid_generate_v4(), 'default', 'OTC-001', 'PharmaCare Pain Relief', b.id, c.id, u.id, 10, 'BOX', 100, '3234567890123', h.id, 12.00, 150.00, 120.00, true, true, false, false, 'Paracetamol tablets', 'PharmaSense Labs', 730, 0, 'OTC', 'ACTIVE', now(), now(), 'system', 'system'
FROM brands b, product_categories c, uoms u, hsn_codes h
WHERE b.name = 'PharmaSense' AND c.name = 'Household' AND u.code = 'BOX' AND h.code = '3004';

INSERT INTO warehouses (id, tenant_id, name, location, manager_name, created_at, updated_at, created_by, updated_by)
VALUES (uuid_generate_v4(), 'default', 'Central Warehouse', 'Mumbai', 'Amit Shah', now(), now(), 'system', 'system');

INSERT INTO distributors (id, tenant_id, name, email, phone, territory, status, credit_limit, created_at, updated_at, created_by, updated_by)
VALUES (uuid_generate_v4(), 'default', 'Core Distributors', 'contact@coredist.com', '+911234567890', 'West Region', 'ACTIVE', 1000000.00, now(), now(), 'system', 'system');

INSERT INTO distributor_branches (id, tenant_id, branch_name, location, manager_name, phone, distributor_id, created_at, updated_at, created_by, updated_by)
SELECT uuid_generate_v4(), 'default', 'West Branch', 'Mumbai', 'Rahul Mehta', '+919876543210', d.id, now(), now(), 'system', 'system' FROM distributors d WHERE d.name = 'Core Distributors';

INSERT INTO retailers (id, tenant_id, name, category, channel, phone, email, location, distributor_id, beat, drug_license_required, status, created_at, updated_at, created_by, updated_by)
VALUES (uuid_generate_v4(), 'default', 'Elite Retail', 'Grocery', 'Retail', '+919999888777', 'elite@retail.com', 'Pune', 'Core Distributors', 'Beat 1', false, 'ACTIVE', now(), now(), 'system', 'system');

INSERT INTO price_lists (id, tenant_id, name, distributor_id, retailer_id, region, created_at, updated_at, created_by, updated_by)
SELECT uuid_generate_v4(), 'default', 'Default Price List', d.id, r.id, 'West Region', now(), now(), 'system', 'system'
FROM distributors d, retailers r
WHERE d.name = 'Core Distributors' AND r.name = 'Elite Retail';

INSERT INTO price_list_items (id, tenant_id, price_list_id, product_id, price, currency, price_type, created_at, updated_at, created_by, updated_by)
SELECT uuid_generate_v4(), 'default', pl.id, p.id, 90.00, 'INR', 'RETAIL', now(), now(), 'system', 'system'
FROM price_lists pl, products p
WHERE pl.name = 'Default Price List' AND p.sku_code = 'FMCG-001';

INSERT INTO schemes (id, tenant_id, name, type, discount_value, buy_quantity, get_quantity, target_category, active, created_at, updated_at, created_by, updated_by)
VALUES
(uuid_generate_v4(), 'default', 'FMCG Percentage Off', 'PERCENTAGE_DISCOUNT', 10.00, null, null, 'Beverages', true, now(), now(), 'system', 'system');
