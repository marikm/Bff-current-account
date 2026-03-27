-- Insert a test account (Remember: we don't send the ID because Postgres generates it automatically)
INSERT INTO account (customer_name, account_number, current_balance)
VALUES ('Marina Matsumoto', '12345-6', 1500.50);

-- Insert 3 transactions for this account (Assuming the account above got ID 1)
INSERT INTO transactions (account_id, amount, type, description, transaction_date)
VALUES (1, 50.00, 'DEBIT', 'Bakery Purchase', '2026-03-23 08:30:00');

INSERT INTO transactions (account_id, amount, type, description, transaction_date)
VALUES (1, 200.00, 'CREDIT', 'Transfer Received - Adriel', '2026-03-23 10:15:00');

INSERT INTO transactions (account_id, amount, type, description, transaction_date)
VALUES (1, 15.00, 'DEBIT', 'Uber Rideshare', '2026-03-23 12:45:00');