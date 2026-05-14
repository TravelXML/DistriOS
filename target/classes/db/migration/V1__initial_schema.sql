CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE companies (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    name varchar(255) NOT NULL UNIQUE,
    industry_vertical varchar(50) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE industry_configs (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    industry_vertical varchar(50) NOT NULL,
    batch_required boolean NOT NULL,
    expiry_required boolean NOT NULL,
    hsn_mandatory boolean NOT NULL,
    gst_mandatory boolean NOT NULL,
    license_required boolean NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE permissions (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    code varchar(255) NOT NULL,
    description varchar(1024)
);

CREATE TABLE roles (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE role_permissions (
    role_id uuid NOT NULL,
    permission_id uuid NOT NULL,
    PRIMARY KEY(role_id, permission_id),
    FOREIGN KEY(role_id) REFERENCES roles(id),
    FOREIGN KEY(permission_id) REFERENCES permissions(id)
);

CREATE TABLE users (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    username varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    full_name varchar(255) NOT NULL,
    enabled boolean NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE user_roles (
    user_id uuid NOT NULL,
    role_id uuid NOT NULL,
    PRIMARY KEY(user_id, role_id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(role_id) REFERENCES roles(id)
);

CREATE TABLE distributors (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    email varchar(255),
    phone varchar(100),
    territory varchar(255),
    status varchar(50),
    credit_limit numeric(19,4),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE distributor_branches (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    branch_name varchar(255) NOT NULL,
    location varchar(255),
    manager_name varchar(255),
    phone varchar(100),
    distributor_id uuid,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255),
    FOREIGN KEY(distributor_id) REFERENCES distributors(id)
);

CREATE TABLE distributor_drug_licenses (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    license_number varchar(255) NOT NULL,
    valid_from date,
    valid_until date,
    document_url varchar(1024),
    distributor_id uuid,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255),
    FOREIGN KEY(distributor_id) REFERENCES distributors(id)
);

CREATE TABLE retailers (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    category varchar(255),
    channel varchar(255),
    phone varchar(100),
    email varchar(255),
    location varchar(255),
    distributor_id varchar(255),
    beat varchar(255),
    drug_license_required boolean,
    status varchar(50),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE retailer_kyc (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    document_type varchar(255) NOT NULL,
    document_number varchar(255),
    issued_date date,
    expiry_date date,
    retailer_id uuid,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255),
    FOREIGN KEY(retailer_id) REFERENCES retailers(id)
);

CREATE TABLE retailer_drug_licenses (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    license_number varchar(255) NOT NULL,
    valid_from date,
    valid_until date,
    document_url varchar(1024),
    retailer_id uuid,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255),
    FOREIGN KEY(retailer_id) REFERENCES retailers(id)
);

CREATE TABLE brands (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE product_categories (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    parent_category varchar(255),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE uoms (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    code varchar(255) NOT NULL,
    description varchar(1024),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE hsn_codes (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    code varchar(255) NOT NULL,
    description varchar(1024),
    gst_rate numeric(5,2),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE products (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    sku_code varchar(255) NOT NULL UNIQUE,
    product_name varchar(255) NOT NULL,
    brand_id uuid,
    category_id uuid,
    uom_id uuid,
    pack_size integer,
    pack_size_uom varchar(255),
    case_size integer,
    barcode varchar(255),
    hsn_code_id uuid,
    gst_rate numeric(5,2),
    mrp numeric(19,4),
    base_price numeric(19,4),
    batch_required boolean,
    expiry_required boolean,
    serial_number_required boolean,
    warranty_required boolean,
    composition varchar(1024),
    manufacturer_name varchar(255),
    shelf_life_days integer,
    warranty_months integer,
    regulatory_category varchar(255),
    status varchar(50),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255),
    FOREIGN KEY(brand_id) REFERENCES brands(id),
    FOREIGN KEY(category_id) REFERENCES product_categories(id),
    FOREIGN KEY(uom_id) REFERENCES uoms(id),
    FOREIGN KEY(hsn_code_id) REFERENCES hsn_codes(id)
);

CREATE TABLE price_lists (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    distributor_id varchar(255),
    retailer_id varchar(255),
    region varchar(255),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE price_list_items (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    price_list_id uuid,
    product_id uuid,
    price numeric(19,4) NOT NULL,
    currency varchar(10),
    price_type varchar(255),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255),
    FOREIGN KEY(price_list_id) REFERENCES price_lists(id),
    FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE warehouses (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    location varchar(255),
    manager_name varchar(255),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE inventory_transactions (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    warehouse_id uuid,
    product_id uuid,
    transaction_type varchar(50),
    stock_type varchar(50),
    quantity numeric(19,4),
    reference_id varchar(255),
    transaction_date timestamp without time zone,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255),
    FOREIGN KEY(warehouse_id) REFERENCES warehouses(id),
    FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE orders (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    retailer_id varchar(255) NOT NULL,
    distributor_id varchar(255),
    status varchar(50),
    source varchar(50),
    idempotency_key varchar(255),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE order_line_items (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    order_id uuid,
    product_id uuid,
    quantity integer NOT NULL,
    unit_price numeric(19,4) NOT NULL,
    total_price numeric(19,4),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255),
    FOREIGN KEY(order_id) REFERENCES orders(id),
    FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE invoices (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    order_id varchar(255),
    retailer_id varchar(255),
    total_amount numeric(19,4),
    generated_at timestamp without time zone,
    status varchar(50),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE schemes (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    type varchar(50),
    discount_value numeric(19,4),
    buy_quantity integer,
    get_quantity integer,
    target_category varchar(255),
    active boolean,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE claims (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    type varchar(50),
    status varchar(50),
    order_id varchar(255),
    reason varchar(1024),
    created_at timestamp without time zone,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE warranties (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    serial_number varchar(255) NOT NULL,
    product_id varchar(255),
    valid_until date,
    customer_name varchar(255),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);

CREATE TABLE product_recalls (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id varchar(255) NOT NULL,
    product_id varchar(255) NOT NULL,
    reason varchar(1024),
    recall_date timestamp without time zone,
    closed boolean,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by varchar(255),
    updated_by varchar(255)
);
