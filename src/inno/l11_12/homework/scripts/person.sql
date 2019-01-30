CREATE TABLE "InnoEduSchema"."Person"
(
    id_person bigint NOT NULL DEFAULT nextval('"InnoEduSchema".person_id_person_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    birthdate timestamp without time zone,
    CONSTRAINT person_pk PRIMARY KEY (id_person)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE "InnoEduSchema"."Person"
    OWNER to postgres;

GRANT ALL ON TABLE "InnoEduSchema"."Person" TO postgres;

GRANT ALL ON TABLE "InnoEduSchema"."Person" TO PUBLIC;

COMMENT ON TABLE "InnoEduSchema"."Person"
    IS 'List of persons';