INSERT INTO public.roles (id, created_at, updated_at, description, name) VALUES (1, '2024-06-23 08:08:19.072000', '2024-06-23 08:08:19.072000', 'Default user role', 'USER');
INSERT INTO public.roles (id, created_at, updated_at, description, name) VALUES (2, '2024-06-23 08:08:19.120000', '2024-06-23 08:08:19.120000', 'Administrator role', 'ADMIN');
INSERT INTO public.users (id, role_id, birth_date, created_at, updated_at, email, first_name, gender, last_name, password) VALUES (1, 2, null, '2024-06-23 08:08:19.308000', '2024-06-23 08:08:19.308000', 'admin@gmail.com', 'admin', null, 'admin', '$2a$10$k25ibqctum7KXp7h.iEeuOb7Ja2giHrSsnp5eTQyADyOaESFLE72O');
INSERT INTO public.users (id, role_id, birth_date, created_at, updated_at, email, first_name, gender, last_name, password) VALUES (2, 1, null, '2024-06-23 08:08:19.489000', '2024-06-23 08:08:19.489000', 'user@gmail.com', 'user', null, 'user', '$2a$10$0Ghm92MlVqfM.kMcfmeiUuQe7ah.1trtSWygJZUP2Jzfq.L6Nn0oy');

