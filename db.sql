CREATE DATABASE "penaDatabase"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

-- Table: public.ProfileRoles

-- DROP TABLE public."ProfileRoles";

CREATE TABLE public."ProfileRoles"
(
    id bigserial NOT NULL,
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ProfileRoles_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public."ProfileRoles"
    OWNER to postgres;

-- Table: public.PersonelTypes

-- DROP TABLE public."PersonelTypes";

CREATE TABLE public."PersonelTypes"
(
    "Id" bigserial NOT NULL,
    "Name" character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "PersonelTypes_pkey" PRIMARY KEY ("Id")
)

TABLESPACE pg_default;

ALTER TABLE public."PersonelTypes"
    OWNER to postgres;

-- Table: public.ClientMembershipTypes

-- DROP TABLE public."ClientMembershipTypes";

CREATE TABLE public."ClientMembershipTypes"
(
    "Id" bigserial NOT NULL,
    "Name" character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ClientMembershipTypes_pkey" PRIMARY KEY ("Id")
)

TABLESPACE pg_default;

ALTER TABLE public."ClientMembershipTypes"
    OWNER to postgres;

-- Table: public.PaymentMethods

-- DROP TABLE public."PaymentMethods";

CREATE TABLE public."PaymentMethods"
(
    "Id" bigserial NOT NULL,
    "Name" character varying(20) COLLATE pg_catalog."default" NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE public."PaymentMethods"
    OWNER to postgres;

EATE TABLE public."Materials"
(
    "Id" bigserial NOT NULL,
    "Name" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "Cost" double precision NOT NULL,
    "Description" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Materials_pkey" PRIMARY KEY ("Id")
)

TABLESPACE pg_default;

ALTER TABLE public."Materials"
    OWNER to postgres;

-- Table: public.Products

-- DROP TABLE public."Products";

CREATE TABLE public."Products"
(
    "Id" bigserial NOT NULL,
    "Name" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "Cost" double precision NOT NULL,
    "Description" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Products_pkey" PRIMARY KEY ("Id")
)

TABLESPACE pg_default;

ALTER TABLE public."Products"
    OWNER to postgres;

-- Table: public.Providers

-- DROP TABLE public."Providers";

CREATE TABLE public."Providers"
(
    "Id" bigserial NOT NULL,
    "Name" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "City" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "StreetAddress" character varying(100) COLLATE pg_catalog."default" NOT NULL,
    "PhoneNumber" bigint,
    "Email" character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "Providers_pkey" PRIMARY KEY ("Id")
)

TABLESPACE pg_default;

ALTER TABLE public."Providers"
    OWNER to postgres;


CREATE TABLE public."Clients"
(
    "Id" bigserial NOT NULL,
    "Name" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "Birthday" timestamp without time zone NOT NULL,
    "Email" character varying(50) COLLATE pg_catalog."default",
    "PhoneNumber" bigint,
    "MemberShipTypeId" bigint NOT NULL,
    "StreetAddress" character varying(100) COLLATE pg_catalog."default" NOT NULL,
    "City" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Clients_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "MembershipType" FOREIGN KEY ("MemberShipTypeId")
        REFERENCES public."ClientMembershipTypes" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE public."Clients"
    OWNER to postgres;

CREATE TABLE public."Personel"
(
    "Id" bigserial NOT NULL,
    "Name" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "Birthday" timestamp without time zone NOT NULL,
    "Email" character varying(50) COLLATE pg_catalog."default",
    "PhoneNumber" bigint,
    "Salary" double precision NOT NULL,
    "AdmissionDate" timestamp without time zone NOT NULL,
    "PersonelTypeId" bigint NOT NULL,
	"ProfileId" bigint,
	"ProfileImagePath" character varying(150) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Personel_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "PersonelType" FOREIGN KEY ("PersonelTypeId")
        REFERENCES public."PersonelTypes" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE public."Clients"
    OWNER to postgres;

CREATE TABLE public."Profiles"
(
    "Id" bigserial NOT NULL,
    "Username" character varying(50) NOT NULL,
    "PasswordHash" bytea NOT NULL,
    "PersonelId" bigint NOT NULL,
    "RoleId" bigint NOT NULL,
    CONSTRAINT "Profiles_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "Personel" FOREIGN KEY ("PersonelId")
        REFERENCES public."Personel" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "Role" FOREIGN KEY ("RoleId")
        REFERENCES public."ProfileRoles" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public."Profiles"
    OWNER to postgres;

CREATE TABLE public."Purchases"
(
    "Id" bigserial NOT NULL,
    "Total" double precision NOT NULL,
    "ProviderId" bigint NOT NULL,
    "Purchased" json NOT NULL,
    PRIMARY KEY ("Id"),
    CONSTRAINT "Provider" FOREIGN KEY ("ProviderId")
        REFERENCES public."Providers" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public."Purchases"
    OWNER to postgres;

CREATE TABLE public."Sales"
(
    "Id" bigserial NOT NULL,
    "Total" double precision NOT NULL,
    "PaymentMethodId" bigint NOT NULL,
    "ClientId" bigint NOT NULL,
    "Date" timestamp without time zone NOT NULL,
    "Purchased" json NOT NULL,
    PRIMARY KEY ("Id"),
    CONSTRAINT "PaymentMethod" FOREIGN KEY ("PaymentMethodId")
        REFERENCES public."PaymentMethods" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "Client" FOREIGN KEY ("ClientId")
        REFERENCES public."Clients" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public."Sales"
    OWNER to postgres;

CREATE TABLE public."ProductStock"
(
    "ProductId" bigint NOT NULL,
    "Quantity" bigint NOT NULL,
    PRIMARY KEY ("ProductId"),
    CONSTRAINT "Product" FOREIGN KEY ("ProductId")
        REFERENCES public."Products" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public."ProductStock"
    OWNER to postgres;

CREATE TABLE public."PasswordSalt"
(
    "Salt" bytea NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE public."PasswordSalt"
    OWNER to postgres;
    
INSERT INTO public."PaymentMethods"("Name")
	VALUES ('Efectivo'),
    ('Debito'),
    ('Credito');

INSERT INTO public."PersonelTypes" ("Name")
VALUES ('Ebanista'),
    ('Vendedor'),
    ('JefeControlCalidad'),
    ('Contador'),
    ('Gerente'),
    ('Recepcionista');

INSERT INTO public."ClientMembershipTypes"(
	"Name")
	VALUES ('Nuevo'),
    ('Estandar'),
    ('Platino');

INSERT INTO public."ProfileRoles" (name)
VALUES ('Usuario'),
        ('Administrador');

CREATE TABLE public."MaterialStock"
(
    "MaterialId" bigint NOT NULL,
    "Quantity" bigint NOT NULL,
    CONSTRAINT "MaterialStock_pkey" PRIMARY KEY ("MaterialId"),
    CONSTRAINT "Material" FOREIGN KEY ("MaterialId")
        REFERENCES public."Materials" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."MaterialStock"
    OWNER to postgres;

INSERT INTO public."Providers"(
"Name", "City", "StreetAddress", "PhoneNumber", "Email")
VALUES ('Maderas Barranquilla', 'Barranquilla', 'Cra 53 # 64 - 25', 3012934, 'maderasbaquilla@hotmail.com'),
('Le??a Fern??ndez', 'Barranquilla', 'Cra 89 # 34 - 95', 3967876, 'amfernandez@gmail.com'),
('Ferreteria el Lugar', 'Puerto Colombia', 'Cra 19 # 24 - 56', 3462453, 'ellugarferreteria@gmail.com')