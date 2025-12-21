-- =========================================
-- Billing Database : testdb
-- =========================================

CREATE TABLE IF NOT EXISTS billing_account
(
    id         VARCHAR(20) PRIMARY KEY,
    patient_id UUID NOT NULL,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    status     VARCHAR(20) NOT NULL
    );

-- =========================================
-- ACTIVE Billing Accounts ONLY
-- =========================================

INSERT INTO billing_account (id, patient_id, name, email, status)
SELECT 'BA-10001', '223e4567-e89b-12d3-a456-426614174005', 'Michael Green', 'michael.green@example.com', 'ACTIVE'
    WHERE NOT EXISTS (SELECT 1 FROM billing_account WHERE id = 'BA-10001');

INSERT INTO billing_account (id, patient_id, name, email, status)
SELECT 'BA-10002', '223e4567-e89b-12d3-a456-426614174006', 'Sarah Taylor', 'sarah.taylor@example.com', 'ACTIVE'
    WHERE NOT EXISTS (SELECT 1 FROM billing_account WHERE id = 'BA-10002');

INSERT INTO billing_account (id, patient_id, name, email, status)
SELECT 'BA-10003', '223e4567-e89b-12d3-a456-426614174007', 'David Wilson', 'david.wilson@example.com', 'ACTIVE'
    WHERE NOT EXISTS (SELECT 1 FROM billing_account WHERE id = 'BA-10003');

INSERT INTO billing_account (id, patient_id, name, email, status)
SELECT 'BA-10004', '223e4567-e89b-12d3-a456-426614174008', 'Laura White', 'laura.white@example.com', 'ACTIVE'
    WHERE NOT EXISTS (SELECT 1 FROM billing_account WHERE id = 'BA-10004');

INSERT INTO billing_account (id, patient_id, name, email, status)
SELECT 'BA-10005', '223e4567-e89b-12d3-a456-426614174010', 'Emma Moore', 'emma.moore@example.com', 'ACTIVE'
    WHERE NOT EXISTS (SELECT 1 FROM billing_account WHERE id = 'BA-10005');

INSERT INTO billing_account (id, patient_id, name, email, status)
SELECT 'BA-10006', '223e4567-e89b-12d3-a456-426614174012', 'Sophia Clark', 'sophia.clark@example.com', 'ACTIVE'
    WHERE NOT EXISTS (SELECT 1 FROM billing_account WHERE id = 'BA-10006');

INSERT INTO billing_account (id, patient_id, name, email, status)
SELECT 'BA-10007', '223e4567-e89b-12d3-a456-426614174014', 'Isabella Walker', 'isabella.walker@example.com', 'ACTIVE'
    WHERE NOT EXISTS (SELECT 1 FROM billing_account WHERE id = 'BA-10007');
