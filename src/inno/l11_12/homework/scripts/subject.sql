CREATE TABLE "InnoEduSchema".schedule
(
    id_p bigint NOT NULL,
    id_s bigint NOT NULL,
    CONSTRAINT schedule_pkey PRIMARY KEY (id_p, id_s),
    CONSTRAINT fk1 FOREIGN KEY (id_p)
        REFERENCES "InnoEduSchema"."Person" (id_person) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk2 FOREIGN KEY (id_s)
        REFERENCES "InnoEduSchema".subject (id_subject) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE "InnoEduSchema".schedule
    OWNER to postgres;

-- Index: fki_fk2

-- DROP INDEX "InnoEduSchema".fki_fk2;

CREATE INDEX fki_fk2
    ON "InnoEduSchema".schedule USING btree
    (id_s)
    TABLESPACE pg_default;