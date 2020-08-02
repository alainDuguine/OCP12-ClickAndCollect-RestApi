--
-- PostgreSQL database dump
--

-- Dumped from database version 11.6
-- Dumped by pg_dump version 11.6

-- Started on 2020-07-17 07:26:31

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2895 (class 0 OID 240651)
-- Dependencies: 209
-- Data for Name: restaurant; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (1, 'La meilleure cantine du quartier Gambetta !', 'em@il.com', true, false, '83 Rue Orfila, 75020, Paris', '48.868028', false, '2.399884', 'Chez Monique & Myrtille', '$2a$10$d660/hnfPNZrT/8r2jnlhe5B.4YxDcRQn2cqLEojowGj7eqkBi3tK', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\1.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');
INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (2, 'La meilleure cantine du quartier Gambetta !', '2em@il.com', true, false, '81 rue Orfila, 75020, Paris', '48.867999', false, '2.399762', 'Les Petits Oignons', '$2a$10$cN3Chu5a.78viX87hupfO.7KHeE9NIDg.GEjpzPZUd63xWuOfadEu', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\2.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');
INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (3, 'La meilleure cantine du quartier Gambetta !', '3em@il.com', true, false, '93 Avenue Gambetta, 75020, Paris', '48.866465', false, '2.399659', 'Chantefable', '$2a$10$cxiXvY6v1dTcdNXc4X11geoX3a7nGbdAVo1Hnnk8nysYRim7q9ukm', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\3.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');
INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (4, 'La meilleure cantine du quartier Gambetta !', '4em@il.com', true, false, '63 Avenue Gambetta, 75020, Paris', '48.865089', false, '2.396215', 'Shingané', '$2a$10$lrtQDMjfLvbqVuqgwcXXhe/a6xU8i1fKh7114hQVf2EQ5.MN3l5GS', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\4.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');
INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (5, 'La meilleure cantine du quartier Gambetta !', '5em@il.com', true, false, '15 Rue Chevreul, 75011, Paris', '48.850293', false, '2.391831', 'Kodawari Ramen', '$2a$10$TiXYco6fBQS.UqGE1QnstuITEhmjIe7KWfKuq7j7JqWvG4iu8jqsa', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\5.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');
INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (6, 'La meilleure cantine du quartier Gambetta !', '6em@il.com', true, false, '81 Rue du Charolais, 75012, Paris', '48.843742', false, '2.381803', 'Ground Control', '$2a$10$3elegX8qehI9Vm17/JY.UewpKAYXPIUDZTEUShBjRyZyMKfwcfIKC', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\6.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');
INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (7, 'La meilleure cantine du quartier Gambetta !', '7em@il.com', true, false, '68 Boulevard de l''Hôpital, 75013, Paris', '48.841782', false, '2.359768', 'Au Pays de Confucius', '$2a$10$dcGvnW8YchrsCATrYyFF0OQNvxHkpClZVCMh0N4JTeQsU3ifj1VGS', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\7.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');
INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (8, 'La meilleure cantine du quartier Gambetta !', '8em@il.com', true, false, '39 Rue Geoffroy-Saint-Hilaire, 75005, Paris', '48.841782', false, '2.355641', 'Restaurant La Mosquée de Paris', '$2a$10$NvXvW7qA52yxL1XHlvRP0.Vy1EaRDxozAaAhdu54E4uuP5zkq/iry', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\8.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');
INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (9, 'La meilleure cantine du quartier Gambetta !', '9em@il.com', true, false, '129 Boulevard du Montparnasse, 75006, Paris', '48.843053', false, '2.326374', 'My Noodles', '$2a$10$jPdXJ2EDvB.cs40X6OqeTuDEql1uos3OHvslzOg0xSys6cr2cK9mC', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\9.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');
INSERT INTO public.restaurant (id, description, email, enabled, expired, formatted_address, latitude, locked, longitude, name, password, photo, roles, type_cuisine) VALUES (10, 'La meilleure cantine du quartier Gambetta !', '10em@il.com', true, false, '73 Rue d''Alésia, 75014 Paris', '48.827709', false, '2.32875', 'Le Verre Siffleur', '$2a$10$DKi1EexOIemSGlOBeWyqeOEDSfyFjq.qP8wqpQlVeZkCU5F9rnwum', 'C:\Users\alain\Pictures\ClickAndCollectPhoto\10.jpg', 'ROLE_USER', 'Cuisine du jour fraîche et du marché');


--
-- TOC entry 2883 (class 0 OID 240597)
-- Dependencies: 197
-- Data for Name: business_hour; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (6, 4, '18:00:00', 0, '09:00:00', 1);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (7, 6, '23:45:00', 0, '08:00:00', 2);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (8, 6, '23:45:00', 0, '07:00:00', 3);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (9, 6, '14:30:00', 1, '12:00:00', 4);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (10, 5, '22:45:00', 1, '19:00:00', 4);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (11, 6, '22:30:00', 6, '19:00:00', 4);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (12, 4, '15:00:00', 0, '12:00:00', 5);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (13, 3, '22:30:00', 0, '18:30:00', 5);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (14, 5, '23:00:00', 4, '18:30:00', 5);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (15, 6, '16:00:00', 5, '12:00:00', 5);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (16, 6, '22:30:00', 6, '18:30:00', 5);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (17, 5, '23:45:00', 2, '17:00:00', 6);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (18, 6, '23:45:00', 5, '12:00:00', 6);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (19, 6, '15:00:00', 1, '12:00:00', 7);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (20, 6, '23:00:00', 1, '18:30:00', 7);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (21, 6, '23:45:00', 0, '09:00:00', 8);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (22, 0, '14:45:00', 0, '11:30:00', 9);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (23, 0, '22:30:00', 0, '18:00:00', 9);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (24, 6, '14:45:00', 2, '11:30:00', 9);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (25, 6, '22:30:00', 2, '18:00:00', 9);
INSERT INTO public.business_hour (id, end_day, end_time, start_day, start_time, restaurant_id) VALUES (26, 6, '23:45:00', 0, '08:00:00', 10);


--
-- TOC entry 2885 (class 0 OID 240605)
-- Dependencies: 199
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category (id, name) VALUES (1, 'Entrée');
INSERT INTO public.category (id, name) VALUES (2, 'Plat');
INSERT INTO public.category (id, name) VALUES (3, 'Dessert');
INSERT INTO public.category (id, name) VALUES (4, 'Boisson');
INSERT INTO public.category (id, name) VALUES (5, 'Formule');


--
-- TOC entry 2887 (class 0 OID 240613)
-- Dependencies: 201
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (1, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 1);
INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (2, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 2);
INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (3, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 3);
INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (4, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 4);
INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (5, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 5);
INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (6, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 6);
INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (7, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 7);
INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (8, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 8);
INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (9, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 9);
INSERT INTO public.menu (id, description, name, price, restaurant_id) VALUES (10, 'Un menu avec une entrée, un plat et un dessert au choix', 'Menu Complet', 16, 10);


--
-- TOC entry 2889 (class 0 OID 240622)
-- Dependencies: 203
-- Data for Name: menu_course; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (1, 1, 1);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (2, 2, 1);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (3, 3, 1);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (4, 1, 2);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (5, 2, 2);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (6, 3, 2);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (7, 1, 3);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (8, 2, 3);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (9, 3, 3);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (10, 1, 4);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (11, 2, 4);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (12, 3, 4);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (13, 1, 5);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (14, 2, 5);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (15, 3, 5);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (16, 1, 6);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (17, 2, 6);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (18, 3, 6);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (19, 1, 7);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (20, 2, 7);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (21, 3, 7);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (22, 1, 8);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (23, 2, 8);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (24, 3, 8);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (25, 1, 9);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (26, 2, 9);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (27, 3, 9);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (28, 1, 10);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (29, 2, 10);
INSERT INTO public.menu_course (id, category_id, menu_id) VALUES (30, 3, 10);


--
-- TOC entry 2891 (class 0 OID 240630)
-- Dependencies: 205
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (1, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 1);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (2, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 1);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (3, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 1);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (4, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 1);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (5, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 1);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (6, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 1);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (7, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 1);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (8, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 1);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (9, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 1);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (10, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 2);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (11, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 2);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (12, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 2);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (13, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 2);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (14, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 2);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (15, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 2);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (16, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 2);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (17, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 2);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (18, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 2);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (19, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 3);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (20, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 3);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (21, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 3);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (22, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 3);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (23, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 3);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (24, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 3);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (25, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 3);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (26, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 3);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (27, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 3);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (28, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 4);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (29, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 4);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (30, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 4);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (31, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 4);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (32, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 4);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (33, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 4);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (34, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 4);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (35, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 4);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (36, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 4);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (37, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 5);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (38, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 5);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (39, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 5);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (40, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 5);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (41, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 5);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (42, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 5);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (43, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 5);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (44, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 5);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (45, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 5);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (46, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 6);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (47, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 6);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (48, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 6);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (49, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 6);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (50, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 6);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (51, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 6);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (52, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 6);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (53, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 6);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (54, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 6);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (55, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 7);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (56, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 7);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (57, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 7);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (58, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 7);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (59, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 7);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (60, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 7);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (61, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 7);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (62, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 7);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (63, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 7);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (64, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 8);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (65, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 8);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (66, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 8);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (67, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 8);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (68, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 8);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (69, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 8);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (70, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 8);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (71, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 8);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (72, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 8);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (73, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 9);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (74, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 9);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (75, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 9);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (76, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 9);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (77, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 9);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (78, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 9);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (79, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 9);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (80, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 9);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (81, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 9);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (82, 'Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !', 'https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg', 'Flan de courgettes', 5.5, 1, 10);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (83, 'La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !', 'https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg', 'Salade César', 7, 1, 10);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (84, 'Un concentré de fraîcheur et d''exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais', 'https://assets.afcdn.com/recipe/20150814/642_w600.jpg', 'Tzatziki', 5, 1, 10);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (85, 'Une délicieuse portion de lasagnes maison, à l''italienne, où le crémeux est amené par la mozzarella, et sans béchamel !', 'https://assets.afcdn.com/recipe/20160630/63224_w600.jpg', 'Lasagnes à la bolognaise', 12.5, 2, 10);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (86, 'Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres', 'https://assets.afcdn.com/recipe/20140112/13508_w600.jpg', 'Poulet rôti et ses pommes de terre', 14, 2, 10);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (87, 'Un délicat pavé de saumon grillé à l''unilatérale, avec une subtile sauce à l''aneth relevée au citron', 'https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg', 'Pavé de saumon, sauce à l''aneth', 16, 2, 10);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (88, 'Un crumble fondant aux pommes, avec une pointe de cannelle', 'https://assets.afcdn.com/recipe/20130123/50045_w600.jpg', 'Crumble aux pommes', 6.5, 3, 10);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (89, 'Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona', 'https://assets.afcdn.com/recipe/20160624/25618_w600.jpg', 'Mousse au chocolat maison', 7, 3, 10);
INSERT INTO public.product (id, description, image_url, name, price, category_id, restaurant_id) VALUES (90, 'Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar', 'https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg', 'Riz au lait', 5.5, 3, 10);


--
-- TOC entry 2893 (class 0 OID 240642)
-- Dependencies: 207
-- Data for Name: product_in_course; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (1, NULL, 1, 1);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (2, 1, 1, 2);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (3, NULL, 2, 4);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (4, 1, 2, 6);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (5, NULL, 3, 7);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (6, 1.5, 3, 8);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (7, NULL, 4, 10);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (8, 1, 4, 11);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (9, NULL, 5, 13);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (10, 1, 5, 15);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (11, NULL, 6, 16);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (12, 1.5, 6, 17);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (13, NULL, 7, 19);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (14, 1, 7, 20);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (15, NULL, 8, 22);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (16, 1, 8, 24);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (17, NULL, 9, 25);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (18, 1.5, 9, 26);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (19, NULL, 10, 28);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (20, 1, 10, 29);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (21, NULL, 11, 31);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (22, 1, 11, 33);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (23, NULL, 12, 34);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (24, 1.5, 12, 35);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (25, NULL, 13, 37);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (26, 1, 13, 38);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (27, NULL, 14, 40);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (28, 1, 14, 42);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (29, NULL, 15, 43);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (30, 1.5, 15, 44);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (31, NULL, 16, 46);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (32, 1, 16, 47);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (33, NULL, 17, 49);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (34, 1, 17, 51);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (35, NULL, 18, 52);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (36, 1.5, 18, 53);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (37, NULL, 19, 55);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (38, 1, 19, 56);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (39, NULL, 20, 58);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (40, 1, 20, 60);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (41, NULL, 21, 61);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (42, 1.5, 21, 62);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (43, NULL, 22, 64);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (44, 1, 22, 65);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (45, NULL, 23, 67);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (46, 1, 23, 69);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (47, NULL, 24, 70);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (48, 1.5, 24, 71);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (49, NULL, 25, 73);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (50, 1, 25, 74);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (51, NULL, 26, 76);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (52, 1, 26, 78);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (53, NULL, 27, 79);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (54, 1.5, 27, 80);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (55, NULL, 28, 82);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (56, 1, 28, 83);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (57, NULL, 29, 85);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (58, 1, 29, 87);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (59, NULL, 30, 88);
INSERT INTO public.product_in_course (id, extra_cost, menu_course_id, product_id) VALUES (60, 1.5, 30, 89);


--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 196
-- Name: business_hour_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.business_hour_id_seq', 26, true);


--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 198
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 5, true);


--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 202
-- Name: menu_course_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menu_course_id_seq', 30, true);


--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 200
-- Name: menu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menu_id_seq', 10, true);


--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 204
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 90, true);


--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 206
-- Name: product_in_course_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_in_course_id_seq', 60, true);


--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 208
-- Name: restaurant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.restaurant_id_seq', 10, true);


-- Completed on 2020-07-17 07:26:31

--
-- PostgreSQL database dump complete
--

