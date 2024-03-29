INSERT INTO category (name) VALUES ('Vestimentas');
INSERT INTO category (name) VALUES ('Acessorios');
INSERT INTO category (name) VALUES ('Armas');
INSERT INTO category (name) VALUES ('Joias');
INSERT INTO category (name) VALUES ('Embarcacoes');
INSERT INTO category (name) VALUES ('Diversos');

INSERT INTO country (name, code) VALUES ('Africa do Sul', 'ZAF');
INSERT INTO country (name, code) VALUES ('Brasil', 'BRA');
INSERT INTO country (name, code) VALUES ('Camboja', 'KHM');
INSERT INTO country (name, code) VALUES ('Dinamarca', 'DNK');
INSERT INTO country (name, code) VALUES ('Egito', 'EGY');
INSERT INTO country (name, code) VALUES ('Fiji', 'FJI');
INSERT INTO country (name, code) VALUES ('Gabao', 'GAB');
INSERT INTO country (name, code) VALUES ('Honduras', 'HND');
INSERT INTO country (name, code) VALUES ('India', 'IND');
INSERT INTO country (name, code) VALUES ('Jamaica', 'JAM');
INSERT INTO country (name, code) VALUES ('Kuwait', 'KWT');
INSERT INTO country (name, code) VALUES ('Liechtenstein', 'LIE');
INSERT INTO country (name, code) VALUES ('Madagascar', 'MDG');
INSERT INTO country (name, code) VALUES ('Nepal', 'NPL');
INSERT INTO country (name, code) VALUES ('Oma', 'OMN');
INSERT INTO country (name, code) VALUES ('Papua-Nova Guine', 'PNG');
INSERT INTO country (name, code) VALUES ('Paquistao', 'PAK');
INSERT INTO country (name, code) VALUES ('Quenia', 'KEN');
INSERT INTO country (name, code) VALUES ('Russia', 'RUS');
INSERT INTO country (name, code) VALUES ('Saara Ocidental', 'ESH');
INSERT INTO country (name, code) VALUES ('Tanzania', 'TZA');
INSERT INTO country (name, code) VALUES ('Uganda', 'UGA');
INSERT INTO country (name, code) VALUES ('Vietnam', 'VNM');
INSERT INTO country (name, code) VALUES ('Wallis e Futuna', 'WLF');
INSERT INTO country (name, code) VALUES ('Zimbabue', 'ZWE');


INSERT INTO supplier (name, code, country_id, phone, email) VALUES ('Einsteins Front Flip', 'ENG1', 5, '+49-157-555-5770', 'germ@ny.deu');
INSERT INTO supplier (name, code, country_id, phone, email) VALUES ('Westminster Abbey INC.', 'GER1', 23, null, 'will.i.am@shakespeare.com');

INSERT INTO brand (name, country_id) VALUES ('Swarovski AG', 4);
INSERT INTO brand (name, country_id) VALUES ('Bambino`s', 11);
INSERT INTO brand (name, country_id) VALUES ('Jack Sparrow LTDA', 8);

INSERT INTO product (name, description, price, category_id, brand_id) VALUES ('Pérola Negra', 'Joia rarissma encontrada no fundo das Fossas Marianas', 50000.00, 4, 1);
INSERT INTO product (name, description, price, category_id, brand_id) VALUES ('Leme de Carvalho', 'Artefato de madeira nobre, cobiçado por arqueólogos e historiadores', 1750.00, 5, 2);
INSERT INTO product (name, description, price, category_id, brand_id) VALUES ('Bússola de Navegação', 'Instrumento utilizado por marinheiros, dos mais novos aos mais experientes', 400.00, 2, 3);

INSERT INTO "user" (name, username, password) VALUES ('Administrador', 'admin', '$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem'); --123
INSERT INTO "user" (name, username, password) VALUES ('Teste', 'teste', '$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem'); --123
INSERT INTO "user" (name, username, password) VALUES ('jean', 'jean', '$2a$10$Ltf5J3WRHyAtPgcXP1juCe1ObX5SMYZjmwF9WzWBrA/2D9X7oPAAu'); --jean

INSERT INTO "role" (name) VALUES ('ROLE_ADMIN');
INSERT INTO "role" (name) VALUES ('ROLE_USER');

INSERT INTO user_roles (user_id, roles_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, roles_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, roles_id) VALUES (3, 2);

INSERT INTO purchase (date, supplier_id, user_id) VALUES ('2019-06-11', 1, 1);
INSERT INTO product_purchase (quantity, total_price, product_id, purchase_id) VALUES (2, 100000, 1, 1);

INSERT INTO client_purchase(date, shipping_price, country_id, user_id) VALUES ('2019-07-16', 0.0, 2, 3);
INSERT INTO products_client_purchase(quantity, total_price, client_purchase_id, product_id) VALUES (5, 2000.0, 1, 3);