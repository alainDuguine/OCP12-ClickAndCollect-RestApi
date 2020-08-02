--
-- PostgreSQL database dump
--

-- Dumped from database version 11.6
-- Dumped by pg_dump version 11.6

-- Started on 2020-07-17 07:23:51

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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 240597)
-- Name: business_hour; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.business_hour (
    id bigint NOT NULL,
    end_day integer,
    end_time time without time zone,
    start_day integer,
    start_time time without time zone,
    restaurant_id bigint
);


ALTER TABLE public.business_hour OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 240595)
-- Name: business_hour_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.business_hour_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.business_hour_id_seq OWNER TO postgres;

--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 196
-- Name: business_hour_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.business_hour_id_seq OWNED BY public.business_hour.id;


--
-- TOC entry 199 (class 1259 OID 240605)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 240603)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO postgres;

--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 198
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- TOC entry 201 (class 1259 OID 240613)
-- Name: menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menu (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(100) NOT NULL,
    price double precision NOT NULL,
    restaurant_id bigint,
    CONSTRAINT menu_price_check CHECK ((price >= (0)::double precision))
);


ALTER TABLE public.menu OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 240622)
-- Name: menu_course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menu_course (
    id bigint NOT NULL,
    category_id bigint,
    menu_id bigint
);


ALTER TABLE public.menu_course OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 240620)
-- Name: menu_course_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.menu_course_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.menu_course_id_seq OWNER TO postgres;

--
-- TOC entry 2890 (class 0 OID 0)
-- Dependencies: 202
-- Name: menu_course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menu_course_id_seq OWNED BY public.menu_course.id;


--
-- TOC entry 200 (class 1259 OID 240611)
-- Name: menu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.menu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.menu_id_seq OWNER TO postgres;

--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 200
-- Name: menu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menu_id_seq OWNED BY public.menu.id;


--
-- TOC entry 205 (class 1259 OID 240630)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    description character varying(255),
    image_url character varying(255),
    name character varying(100) NOT NULL,
    price double precision NOT NULL,
    category_id bigint NOT NULL,
    restaurant_id bigint NOT NULL,
    CONSTRAINT product_price_check CHECK ((price >= (0)::double precision))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 240628)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 204
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 207 (class 1259 OID 240642)
-- Name: product_in_course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_in_course (
    id bigint NOT NULL,
    extra_cost double precision,
    menu_course_id bigint,
    product_id bigint,
    CONSTRAINT product_in_course_extra_cost_check CHECK ((extra_cost >= (0)::double precision))
);


ALTER TABLE public.product_in_course OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 240640)
-- Name: product_in_course_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_in_course_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_in_course_id_seq OWNER TO postgres;

--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 206
-- Name: product_in_course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_in_course_id_seq OWNED BY public.product_in_course.id;


--
-- TOC entry 209 (class 1259 OID 240651)
-- Name: restaurant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.restaurant (
    id bigint NOT NULL,
    description character varying(255),
    email character varying(255) NOT NULL,
    enabled boolean NOT NULL,
    expired boolean NOT NULL,
    formatted_address character varying(255),
    latitude character varying(255),
    location bytea,
    locked boolean NOT NULL,
    longitude character varying(255),
    name character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    photo character varying(255),
    roles character varying(255),
    type_cuisine character varying(100)
);


ALTER TABLE public.restaurant OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 240649)
-- Name: restaurant_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.restaurant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.restaurant_id_seq OWNER TO postgres;

--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 208
-- Name: restaurant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.restaurant_id_seq OWNED BY public.restaurant.id;


--
-- TOC entry 2723 (class 2604 OID 240600)
-- Name: business_hour id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_hour ALTER COLUMN id SET DEFAULT nextval('public.business_hour_id_seq'::regclass);


--
-- TOC entry 2724 (class 2604 OID 240608)
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- TOC entry 2725 (class 2604 OID 240616)
-- Name: menu id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu ALTER COLUMN id SET DEFAULT nextval('public.menu_id_seq'::regclass);


--
-- TOC entry 2727 (class 2604 OID 240625)
-- Name: menu_course id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_course ALTER COLUMN id SET DEFAULT nextval('public.menu_course_id_seq'::regclass);


--
-- TOC entry 2728 (class 2604 OID 240633)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 2730 (class 2604 OID 240645)
-- Name: product_in_course id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_in_course ALTER COLUMN id SET DEFAULT nextval('public.product_in_course_id_seq'::regclass);


--
-- TOC entry 2732 (class 2604 OID 240654)
-- Name: restaurant id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restaurant ALTER COLUMN id SET DEFAULT nextval('public.restaurant_id_seq'::regclass);


--
-- TOC entry 2734 (class 2606 OID 240602)
-- Name: business_hour business_hour_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_hour
    ADD CONSTRAINT business_hour_pkey PRIMARY KEY (id);


--
-- TOC entry 2736 (class 2606 OID 240610)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 2742 (class 2606 OID 240627)
-- Name: menu_course menu_course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_course
    ADD CONSTRAINT menu_course_pkey PRIMARY KEY (id);


--
-- TOC entry 2738 (class 2606 OID 240619)
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);


--
-- TOC entry 2748 (class 2606 OID 240648)
-- Name: product_in_course product_in_course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_in_course
    ADD CONSTRAINT product_in_course_pkey PRIMARY KEY (id);


--
-- TOC entry 2744 (class 2606 OID 240639)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2750 (class 2606 OID 240659)
-- Name: restaurant restaurant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restaurant
    ADD CONSTRAINT restaurant_pkey PRIMARY KEY (id);


--
-- TOC entry 2740 (class 2606 OID 240661)
-- Name: menu uk9s8xm0dmlhjsl1vdvinwcm3pt; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT uk9s8xm0dmlhjsl1vdvinwcm3pt UNIQUE (restaurant_id, name);


--
-- TOC entry 2752 (class 2606 OID 240665)
-- Name: restaurant uk_979xvypjc2lwr1ia4kq77cko0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restaurant
    ADD CONSTRAINT uk_979xvypjc2lwr1ia4kq77cko0 UNIQUE (email);


--
-- TOC entry 2746 (class 2606 OID 240663)
-- Name: product ukm46qbd3yi2rk99ddf2blubus8; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT ukm46qbd3yi2rk99ddf2blubus8 UNIQUE (restaurant_id, name);


--
-- TOC entry 2757 (class 2606 OID 240686)
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 2760 (class 2606 OID 240701)
-- Name: product_in_course fk4le0w0jg3dnena1ifnnu0fsdy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_in_course
    ADD CONSTRAINT fk4le0w0jg3dnena1ifnnu0fsdy FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2754 (class 2606 OID 240671)
-- Name: menu fkblwdtxevpl4mrds8a12q0ohu6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT fkblwdtxevpl4mrds8a12q0ohu6 FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


--
-- TOC entry 2756 (class 2606 OID 240681)
-- Name: menu_course fkfkf3e2n1l65toht97hx313400; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_course
    ADD CONSTRAINT fkfkf3e2n1l65toht97hx313400 FOREIGN KEY (menu_id) REFERENCES public.menu(id);


--
-- TOC entry 2753 (class 2606 OID 240666)
-- Name: business_hour fkin1qvmlk3n7dta0ussafe9i4m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_hour
    ADD CONSTRAINT fkin1qvmlk3n7dta0ussafe9i4m FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


--
-- TOC entry 2755 (class 2606 OID 240676)
-- Name: menu_course fkkunas7ry3e7lrvafewfqtfo7i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_course
    ADD CONSTRAINT fkkunas7ry3e7lrvafewfqtfo7i FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 2759 (class 2606 OID 240696)
-- Name: product_in_course fkkvqruproykv19i1ljwyxte3n3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_in_course
    ADD CONSTRAINT fkkvqruproykv19i1ljwyxte3n3 FOREIGN KEY (menu_course_id) REFERENCES public.menu_course(id);


--
-- TOC entry 2758 (class 2606 OID 240691)
-- Name: product fkp4b7e36gck7975p51raud8juk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkp4b7e36gck7975p51raud8juk FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


-- Completed on 2020-07-17 07:23:51

--
-- PostgreSQL database dump complete
--

