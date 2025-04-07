-- Roles básicos del sistema
INSERT INTO roles (name, is_deleted)
SELECT 'ADMIN', false WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');
INSERT INTO roles (name, is_deleted)
SELECT 'SELLER', false WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'SELLER');

-- Permisos del sistema
INSERT INTO permissions (name, is_deleted)
SELECT 'KEYWORD_CREATE', false WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE name = 'KEYWORD_CREATE');
INSERT INTO permissions (name, is_deleted)
SELECT 'KEYWORD_READ', false WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE name = 'KEYWORD_READ');
INSERT INTO permissions (name, is_deleted)
SELECT 'KEYWORD_UPDATE', false WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE name = 'KEYWORD_UPDATE');
INSERT INTO permissions (name, is_deleted)
SELECT 'KEYWORD_DELETE', false WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE name = 'KEYWORD_DELETE');

-- Asignación de permisos a roles
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r, permissions p
WHERE r.name = 'ADMIN' AND p.name IN ('KEYWORD_CREATE', 'KEYWORD_READ', 'KEYWORD_UPDATE', 'KEYWORD_DELETE')
AND NOT EXISTS (
    SELECT 1 FROM role_permissions rp
    WHERE rp.role_id = r.id AND rp.permission_id = p.id
);

-- Usuarios base
INSERT INTO users (username, password_hash, email, full_name, is_deleted)
SELECT 'vendedor1', '$2a$10$rPiEAgQNIT1TCoKi1RF1oOYxht/wWR.c8vbAxz1VmYGXI.j8vrZTS', 'vendedor1@bookhub.com', 'Juan Pérez', false
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'vendedor1');

INSERT INTO users (username, password_hash, email, full_name, is_deleted)
SELECT 'vendedor2', '$2a$10$rPiEAgQNIT1TCoKi1RF1oOYxht/wWR.c8vbAxz1VmYGXI.j8vrZTS', 'vendedor2@bookhub.com', 'María García', false
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'vendedor2');

-- Asignación de roles a usuarios
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.username = 'vendedor1' AND r.name = 'SELLER'
AND NOT EXISTS (SELECT 1 FROM user_roles ur WHERE ur.user_id = u.id AND ur.role_id = r.id);

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.username = 'vendedor2' AND r.name = 'SELLER'
AND NOT EXISTS (SELECT 1 FROM user_roles ur WHERE ur.user_id = u.id AND ur.role_id = r.id);

-- Catálogos básicos
-- Autores
INSERT INTO authors (name, is_deleted)
SELECT 'Gabriel García Márquez', false WHERE NOT EXISTS (SELECT 1 FROM authors WHERE name = 'Gabriel García Márquez');
INSERT INTO authors (name, is_deleted)
SELECT 'Jorge Luis Borges', false WHERE NOT EXISTS (SELECT 1 FROM authors WHERE name = 'Jorge Luis Borges');
INSERT INTO authors (name, is_deleted)
SELECT 'Isabel Allende', false WHERE NOT EXISTS (SELECT 1 FROM authors WHERE name = 'Isabel Allende');
INSERT INTO authors (name, is_deleted)
SELECT 'Mario Vargas Llosa', false WHERE NOT EXISTS (SELECT 1 FROM authors WHERE name = 'Mario Vargas Llosa');
INSERT INTO authors (name, is_deleted)
SELECT 'Paulo Coelho', false WHERE NOT EXISTS (SELECT 1 FROM authors WHERE name = 'Paulo Coelho');

-- Formatos
INSERT INTO formats (name, is_deleted)
SELECT 'Tapa dura', false WHERE NOT EXISTS (SELECT 1 FROM formats WHERE name = 'Tapa dura');
INSERT INTO formats (name, is_deleted)
SELECT 'Bolsillo', false WHERE NOT EXISTS (SELECT 1 FROM formats WHERE name = 'Bolsillo');
INSERT INTO formats (name, is_deleted)
SELECT 'Digital', false WHERE NOT EXISTS (SELECT 1 FROM formats WHERE name = 'Digital');

-- Géneros
INSERT INTO genres (name, is_deleted)
SELECT 'Novela', false WHERE NOT EXISTS (SELECT 1 FROM genres WHERE name = 'Novela');
INSERT INTO genres (name, is_deleted)
SELECT 'Fantasía', false WHERE NOT EXISTS (SELECT 1 FROM genres WHERE name = 'Fantasía');
INSERT INTO genres (name, is_deleted)
SELECT 'Historia', false WHERE NOT EXISTS (SELECT 1 FROM genres WHERE name = 'Historia');
INSERT INTO genres (name, is_deleted)
SELECT 'Ciencia Ficción', false WHERE NOT EXISTS (SELECT 1 FROM genres WHERE name = 'Ciencia Ficción');

-- Idiomas
INSERT INTO languages (name, is_deleted)
SELECT 'Español', false WHERE NOT EXISTS (SELECT 1 FROM languages WHERE name = 'Español');
INSERT INTO languages (name, is_deleted)
SELECT 'Portugués', false WHERE NOT EXISTS (SELECT 1 FROM languages WHERE name = 'Portugués');
INSERT INTO languages (name, is_deleted)
SELECT 'Francés', false WHERE NOT EXISTS (SELECT 1 FROM languages WHERE name = 'Francés');

-- Editoriales
INSERT INTO publishers (name, is_deleted)
SELECT 'Planeta', false WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE name = 'Planeta');
INSERT INTO publishers (name, is_deleted)
SELECT 'Sudamericana', false WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE name = 'Sudamericana');
INSERT INTO publishers (name, is_deleted)
SELECT 'Alfaguara', false WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE name = 'Alfaguara');
INSERT INTO publishers (name, is_deleted)
SELECT 'Anagrama', false WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE name = 'Anagrama');

-- Keywords
INSERT INTO keywords (keyword, is_deleted)
SELECT 'aventura', false WHERE NOT EXISTS (SELECT 1 FROM keywords WHERE keyword = 'aventura');
INSERT INTO keywords (keyword, is_deleted)
SELECT 'misterio', false WHERE NOT EXISTS (SELECT 1 FROM keywords WHERE keyword = 'misterio');
INSERT INTO keywords (keyword, is_deleted)
SELECT 'romance', false WHERE NOT EXISTS (SELECT 1 FROM keywords WHERE keyword = 'romance');
INSERT INTO keywords (keyword, is_deleted)
SELECT 'suspenso', false WHERE NOT EXISTS (SELECT 1 FROM keywords WHERE keyword = 'suspenso');
INSERT INTO keywords (keyword, is_deleted)
SELECT 'filosofia', false WHERE NOT EXISTS (SELECT 1 FROM keywords WHERE keyword = 'filosofia');
INSERT INTO keywords (keyword, is_deleted)
SELECT 'politica', false WHERE NOT EXISTS (SELECT 1 FROM keywords WHERE keyword = 'politica');

-- Libros
INSERT INTO books (title, author_id, publisher_id, language_id, format_id, isbn, selling_price, stock, is_deleted)
SELECT 'Cien años de soledad', a.id, p.id, l.id, f.id, '9788497592208', 29.99, 50, false
FROM authors a, publishers p, languages l, formats f
WHERE a.name = 'Gabriel García Márquez' AND p.name = 'Planeta' AND l.name = 'Español' AND f.name = 'Tapa dura'
AND NOT EXISTS (SELECT 1 FROM books WHERE isbn = '9788497592208');

INSERT INTO books (title, author_id, publisher_id, language_id, format_id, isbn, selling_price, stock, is_deleted)
SELECT 'La casa de los espíritus', a.id, p.id, l.id, f.id, '9788401352898', 24.99, 40, false
FROM authors a, publishers p, languages l, formats f
WHERE a.name = 'Isabel Allende' AND p.name = 'Alfaguara' AND l.name = 'Español' AND f.name = 'Tapa dura'
AND NOT EXISTS (SELECT 1 FROM books WHERE isbn = '9788401352898');

INSERT INTO books (title, author_id, publisher_id, language_id, format_id, isbn, selling_price, stock, is_deleted)
SELECT 'El Aleph', a.id, p.id, l.id, f.id, '9788499089515', 19.99, 30, false
FROM authors a, publishers p, languages l, formats f
WHERE a.name = 'Jorge Luis Borges' AND p.name = 'Sudamericana' AND l.name = 'Español' AND f.name = 'Bolsillo'
AND NOT EXISTS (SELECT 1 FROM books WHERE isbn = '9788499089515');

INSERT INTO books (title, author_id, publisher_id, language_id, format_id, isbn, selling_price, stock, is_deleted)
SELECT 'La ciudad y los perros', a.id, p.id, l.id, f.id, '9788420412337', 22.99, 25, false
FROM authors a, publishers p, languages l, formats f
WHERE a.name = 'Mario Vargas Llosa' AND p.name = 'Anagrama' AND l.name = 'Español' AND f.name = 'Bolsillo'
AND NOT EXISTS (SELECT 1 FROM books WHERE isbn = '9788420412337');

-- Asociaciones libro-género
INSERT INTO book_genres (book_id, genre_id)
SELECT b.id, g.id
FROM books b, genres g
WHERE b.title = 'Cien años de soledad' AND g.name = 'Novela'
AND NOT EXISTS (SELECT 1 FROM book_genres bg WHERE bg.book_id = b.id AND bg.genre_id = g.id);

INSERT INTO book_genres (book_id, genre_id)
SELECT b.id, g.id
FROM books b, genres g
WHERE b.title = 'La casa de los espíritus' AND g.name = 'Novela'
AND NOT EXISTS (SELECT 1 FROM book_genres bg WHERE bg.book_id = b.id AND bg.genre_id = g.id);

INSERT INTO book_genres (book_id, genre_id)
SELECT b.id, g.id
FROM books b, genres g
WHERE b.title = 'El Aleph' AND g.name = 'Novela'
AND NOT EXISTS (SELECT 1 FROM book_genres bg WHERE bg.book_id = b.id AND bg.genre_id = g.id);

INSERT INTO book_genres (book_id, genre_id)
SELECT b.id, g.id
FROM books b, genres g
WHERE b.title = 'La ciudad y los perros' AND g.name = 'Novela'
AND NOT EXISTS (SELECT 1 FROM book_genres bg WHERE bg.book_id = b.id AND bg.genre_id = g.id);

-- Asociaciones libro-keyword
INSERT INTO book_keywords (book_id, keyword_id)
SELECT b.id, k.id
FROM books b, keywords k
WHERE b.title = 'Cien años de soledad' AND k.keyword IN ('romance', 'misterio')
AND NOT EXISTS (
    SELECT 1 FROM book_keywords bk
    WHERE bk.book_id = b.id AND bk.keyword_id = k.id
);

INSERT INTO book_keywords (book_id, keyword_id)
SELECT b.id, k.id
FROM books b, keywords k
WHERE b.title = 'El Aleph' AND k.keyword IN ('filosofia', 'misterio')
AND NOT EXISTS (
    SELECT 1 FROM book_keywords bk
    WHERE bk.book_id = b.id AND bk.keyword_id = k.id
);

-- Clientes
INSERT INTO customers (full_name, document_type, document_number, email, phone, address)
SELECT 'Ana López', 'DNI', '12345678', 'ana@email.com', '987654321', 'Av. Principal 123'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE document_number = '12345678');

INSERT INTO customers (full_name, document_type, document_number, email, phone, address)
SELECT 'Carlos Ruiz', 'DNI', '87654321', 'carlos@email.com', '123456789', 'Jr. Secundaria 456'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE document_number = '87654321');

-- Ventas y detalles
INSERT INTO sales (customer_id, user_id, subtotal, tax_amount, total_amount, payment_type, invoice_number, series, correlative, issue_date)
SELECT
    c.id,
    u.id,
    100.00,
    18.00,
    118.00,
    'INVOICE',
    'F001-00001',
    'F001',
    1,
    CURRENT_TIMESTAMP()
FROM customers c, users u
WHERE c.document_number = '12345678' AND u.username = 'vendedor1'
AND NOT EXISTS (SELECT 1 FROM sales s WHERE s.invoice_number = 'F001-00001');

INSERT INTO sale_details (sale_id, book_id, quantity, unit_price, subtotal)
SELECT s.id, b.id, 2, 29.99, 59.98
FROM sales s, books b
WHERE s.invoice_number = 'F001-00001' AND b.title = 'Cien años de soledad'
AND NOT EXISTS (
    SELECT 1 FROM sale_details sd
    WHERE sd.sale_id = s.id AND sd.book_id = b.id
);

INSERT INTO sale_details (sale_id, book_id, quantity, unit_price, subtotal)
SELECT s.id, b.id, 1, 24.99, 24.99
FROM sales s, books b
WHERE s.invoice_number = 'F001-00001' AND b.title = 'La casa de los espíritus'
AND NOT EXISTS (
    SELECT 1 FROM sale_details sd
    WHERE sd.sale_id = s.id AND sd.book_id = b.id
);