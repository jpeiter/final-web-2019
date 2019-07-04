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
INSERT INTO country (name, code) VALUES ('Indonesia', 'IDN');
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

INSERT INTO brand (name, country_id) VALUES ('Swarovski AG', 18);

INSERT INTO product (name, description, price, category_id, brand_id) VALUES ('Esmeralda Preta 50k', 'Joia rarissma encontrada no sertao nordestino brasileiro', 50000.00, 4, 1);

INSERT INTO "user" (name, username, password) VALUES ('Administrador', 'admin', '$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');
INSERT INTO "user" (name, username, password) VALUES ('Teste', 'teste', '$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');

INSERT INTO "role" (name) VALUES ('ROLE_ADMIN');
INSERT INTO "role" (name) VALUES ('ROLE_USER');

INSERT INTO user_roles (user_id, roles_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, roles_id) VALUES (1, 2);
INSERT INTO user_roles (user_id, roles_id) VALUES (2, 2);

INSERT INTO purchase (date, supplier_id, user_id) VALUES ('2019-06-11', 1, 1);
INSERT INTO product_purchase (quantity, total_price, product_id, purchase_id) VALUES (2, 100000, 1, 1);
