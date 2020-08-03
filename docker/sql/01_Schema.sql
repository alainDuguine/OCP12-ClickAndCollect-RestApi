--
-- PostgreSQL database dump
--

-- Dumped from database version 11.6
-- Dumped by pg_dump version 11.6

-- Started on 2020-08-03 17:12:18

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
-- TOC entry 197 (class 1259 OID 248164)
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
-- TOC entry 196 (class 1259 OID 248162)
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
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 196
-- Name: business_hour_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.business_hour_id_seq OWNED BY public.business_hour.id;


--
-- TOC entry 199 (class 1259 OID 248172)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 248170)
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
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 198
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- TOC entry 201 (class 1259 OID 248180)
-- Name: client_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client_order (
    id bigint NOT NULL,
    email character varying(255),
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    phone_number character varying(10),
    pickup_date_time timestamp without time zone NOT NULL,
    restaurant_id bigint
);


ALTER TABLE public.client_order OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 248178)
-- Name: client_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_order_id_seq OWNER TO postgres;

--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 200
-- Name: client_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_order_id_seq OWNED BY public.client_order.id;


--
-- TOC entry 203 (class 1259 OID 248191)
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
-- TOC entry 205 (class 1259 OID 248200)
-- Name: menu_course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menu_course (
    id bigint NOT NULL,
    category_id bigint,
    menu_id bigint
);


ALTER TABLE public.menu_course OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 248198)
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
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 204
-- Name: menu_course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menu_course_id_seq OWNED BY public.menu_course.id;


--
-- TOC entry 202 (class 1259 OID 248189)
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
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 202
-- Name: menu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menu_id_seq OWNED BY public.menu.id;


--
-- TOC entry 207 (class 1259 OID 248208)
-- Name: menu_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menu_order (
    id bigint NOT NULL,
    quantity integer NOT NULL,
    client_order_id bigint,
    menu_id bigint
);


ALTER TABLE public.menu_order OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 248206)
-- Name: menu_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.menu_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.menu_order_id_seq OWNER TO postgres;

--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 206
-- Name: menu_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menu_order_id_seq OWNED BY public.menu_order.id;


--
-- TOC entry 209 (class 1259 OID 248216)
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
-- TOC entry 208 (class 1259 OID 248214)
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
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 208
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 211 (class 1259 OID 248228)
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
-- TOC entry 210 (class 1259 OID 248226)
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
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 210
-- Name: product_in_course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_in_course_id_seq OWNED BY public.product_in_course.id;


--
-- TOC entry 213 (class 1259 OID 248237)
-- Name: product_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_order (
    id bigint NOT NULL,
    quantity integer NOT NULL,
    client_order_id bigint,
    product_id bigint
);


ALTER TABLE public.product_order OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 248235)
-- Name: product_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_order_id_seq OWNER TO postgres;

--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 212
-- Name: product_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_order_id_seq OWNED BY public.product_order.id;


--
-- TOC entry 215 (class 1259 OID 248245)
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
-- TOC entry 214 (class 1259 OID 248243)
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
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 214
-- Name: restaurant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.restaurant_id_seq OWNED BY public.restaurant.id;


--
-- TOC entry 217 (class 1259 OID 248256)
-- Name: selected_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.selected_product (
    id bigint NOT NULL,
    menu_order_id bigint,
    product_in_course_id bigint
);


ALTER TABLE public.selected_product OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 248254)
-- Name: selected_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.selected_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.selected_product_id_seq OWNER TO postgres;

--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 216
-- Name: selected_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.selected_product_id_seq OWNED BY public.selected_product.id;


--
-- TOC entry 2748 (class 2604 OID 248167)
-- Name: business_hour id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_hour ALTER COLUMN id SET DEFAULT nextval('public.business_hour_id_seq'::regclass);


--
-- TOC entry 2749 (class 2604 OID 248175)
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- TOC entry 2750 (class 2604 OID 248183)
-- Name: client_order id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client_order ALTER COLUMN id SET DEFAULT nextval('public.client_order_id_seq'::regclass);


--
-- TOC entry 2751 (class 2604 OID 248194)
-- Name: menu id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu ALTER COLUMN id SET DEFAULT nextval('public.menu_id_seq'::regclass);


--
-- TOC entry 2753 (class 2604 OID 248203)
-- Name: menu_course id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_course ALTER COLUMN id SET DEFAULT nextval('public.menu_course_id_seq'::regclass);


--
-- TOC entry 2754 (class 2604 OID 248211)
-- Name: menu_order id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_order ALTER COLUMN id SET DEFAULT nextval('public.menu_order_id_seq'::regclass);


--
-- TOC entry 2755 (class 2604 OID 248219)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 2757 (class 2604 OID 248231)
-- Name: product_in_course id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_in_course ALTER COLUMN id SET DEFAULT nextval('public.product_in_course_id_seq'::regclass);


--
-- TOC entry 2759 (class 2604 OID 248240)
-- Name: product_order id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_order ALTER COLUMN id SET DEFAULT nextval('public.product_order_id_seq'::regclass);


--
-- TOC entry 2760 (class 2604 OID 248248)
-- Name: restaurant id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restaurant ALTER COLUMN id SET DEFAULT nextval('public.restaurant_id_seq'::regclass);


--
-- TOC entry 2761 (class 2604 OID 248259)
-- Name: selected_product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.selected_product ALTER COLUMN id SET DEFAULT nextval('public.selected_product_id_seq'::regclass);


--
-- TOC entry 2763 (class 2606 OID 248169)
-- Name: business_hour business_hour_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_hour
    ADD CONSTRAINT business_hour_pkey PRIMARY KEY (id);


--
-- TOC entry 2765 (class 2606 OID 248177)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 2767 (class 2606 OID 248188)
-- Name: client_order client_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client_order
    ADD CONSTRAINT client_order_pkey PRIMARY KEY (id);


--
-- TOC entry 2773 (class 2606 OID 248205)
-- Name: menu_course menu_course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_course
    ADD CONSTRAINT menu_course_pkey PRIMARY KEY (id);


--
-- TOC entry 2775 (class 2606 OID 248213)
-- Name: menu_order menu_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_order
    ADD CONSTRAINT menu_order_pkey PRIMARY KEY (id);


--
-- TOC entry 2769 (class 2606 OID 248197)
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);


--
-- TOC entry 2781 (class 2606 OID 248234)
-- Name: product_in_course product_in_course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_in_course
    ADD CONSTRAINT product_in_course_pkey PRIMARY KEY (id);


--
-- TOC entry 2783 (class 2606 OID 248242)
-- Name: product_order product_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_order
    ADD CONSTRAINT product_order_pkey PRIMARY KEY (id);


--
-- TOC entry 2777 (class 2606 OID 248225)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2785 (class 2606 OID 248253)
-- Name: restaurant restaurant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restaurant
    ADD CONSTRAINT restaurant_pkey PRIMARY KEY (id);


--
-- TOC entry 2789 (class 2606 OID 248261)
-- Name: selected_product selected_product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.selected_product
    ADD CONSTRAINT selected_product_pkey PRIMARY KEY (id);


--
-- TOC entry 2771 (class 2606 OID 248263)
-- Name: menu uk9s8xm0dmlhjsl1vdvinwcm3pt; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT uk9s8xm0dmlhjsl1vdvinwcm3pt UNIQUE (restaurant_id, name);


--
-- TOC entry 2787 (class 2606 OID 248267)
-- Name: restaurant uk_979xvypjc2lwr1ia4kq77cko0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restaurant
    ADD CONSTRAINT uk_979xvypjc2lwr1ia4kq77cko0 UNIQUE (email);


--
-- TOC entry 2779 (class 2606 OID 248265)
-- Name: product ukm46qbd3yi2rk99ddf2blubus8; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT ukm46qbd3yi2rk99ddf2blubus8 UNIQUE (restaurant_id, name);


--
-- TOC entry 2797 (class 2606 OID 248303)
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 2800 (class 2606 OID 248318)
-- Name: product_in_course fk4le0w0jg3dnena1ifnnu0fsdy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_in_course
    ADD CONSTRAINT fk4le0w0jg3dnena1ifnnu0fsdy FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2791 (class 2606 OID 248273)
-- Name: client_order fk7ubjvxcr8vheqkd9su8gr4srg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client_order
    ADD CONSTRAINT fk7ubjvxcr8vheqkd9su8gr4srg FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


--
-- TOC entry 2792 (class 2606 OID 248278)
-- Name: menu fkblwdtxevpl4mrds8a12q0ohu6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT fkblwdtxevpl4mrds8a12q0ohu6 FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


--
-- TOC entry 2796 (class 2606 OID 248298)
-- Name: menu_order fkf1mvypxn8rwaf37ftr2dy7ccw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_order
    ADD CONSTRAINT fkf1mvypxn8rwaf37ftr2dy7ccw FOREIGN KEY (menu_id) REFERENCES public.menu(id);


--
-- TOC entry 2794 (class 2606 OID 248288)
-- Name: menu_course fkfkf3e2n1l65toht97hx313400; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_course
    ADD CONSTRAINT fkfkf3e2n1l65toht97hx313400 FOREIGN KEY (menu_id) REFERENCES public.menu(id);


--
-- TOC entry 2802 (class 2606 OID 248328)
-- Name: product_order fkh73acsd9s5wp6l0e55td6jr1m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_order
    ADD CONSTRAINT fkh73acsd9s5wp6l0e55td6jr1m FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2790 (class 2606 OID 248268)
-- Name: business_hour fkin1qvmlk3n7dta0ussafe9i4m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_hour
    ADD CONSTRAINT fkin1qvmlk3n7dta0ussafe9i4m FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


--
-- TOC entry 2793 (class 2606 OID 248283)
-- Name: menu_course fkkunas7ry3e7lrvafewfqtfo7i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_course
    ADD CONSTRAINT fkkunas7ry3e7lrvafewfqtfo7i FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 2799 (class 2606 OID 248313)
-- Name: product_in_course fkkvqruproykv19i1ljwyxte3n3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_in_course
    ADD CONSTRAINT fkkvqruproykv19i1ljwyxte3n3 FOREIGN KEY (menu_course_id) REFERENCES public.menu_course(id);


--
-- TOC entry 2801 (class 2606 OID 248323)
-- Name: product_order fklnq65s4qeajvq2phf2epy7piy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_order
    ADD CONSTRAINT fklnq65s4qeajvq2phf2epy7piy FOREIGN KEY (client_order_id) REFERENCES public.client_order(id);


--
-- TOC entry 2804 (class 2606 OID 248338)
-- Name: selected_product fkmvchawh2cnbjqtt23j6u10wf3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.selected_product
    ADD CONSTRAINT fkmvchawh2cnbjqtt23j6u10wf3 FOREIGN KEY (product_in_course_id) REFERENCES public.product_in_course(id);


--
-- TOC entry 2795 (class 2606 OID 248293)
-- Name: menu_order fkp1nd26wacoylvgjyay224rr50; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu_order
    ADD CONSTRAINT fkp1nd26wacoylvgjyay224rr50 FOREIGN KEY (client_order_id) REFERENCES public.client_order(id);


--
-- TOC entry 2798 (class 2606 OID 248308)
-- Name: product fkp4b7e36gck7975p51raud8juk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkp4b7e36gck7975p51raud8juk FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);


--
-- TOC entry 2803 (class 2606 OID 248333)
-- Name: selected_product fkr3g3x5x3qu55hff0baidbur16; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.selected_product
    ADD CONSTRAINT fkr3g3x5x3qu55hff0baidbur16 FOREIGN KEY (menu_order_id) REFERENCES public.menu_order(id);


-- Completed on 2020-08-03 17:12:18

--
-- PostgreSQL database dump complete
--

