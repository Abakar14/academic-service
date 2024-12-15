-- Table: student

-- DROP TABLE IF EXISTS student;

CREATE TABLE IF NOT EXISTS student
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    active boolean DEFAULT true,
    added_on timestamp(6) without time zone,
    birthday timestamp(6) without time zone,
    communication_type smallint,
    deleted boolean DEFAULT false,
    email character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    gender smallint,
    inserted_by character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    mat_number character varying(255) COLLATE pg_catalog."default",
    modified_on timestamp(6) without time zone,
    phone character varying(255) COLLATE pg_catalog."default",
    updated_by character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT student_pkey PRIMARY KEY (id),
    CONSTRAINT student_communication_type_check CHECK (communication_type >= 0 AND communication_type <= 5),
    CONSTRAINT student_gender_check CHECK (gender >= 0 AND gender <= 2)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS student
    OWNER to postgres;


-- Table: student_document

-- DROP TABLE IF EXISTS student_document;

CREATE TABLE IF NOT EXISTS student_document
(
    student_id bigint NOT NULL,
    document_id bigint,
    CONSTRAINT fkth8q4p04nhdgy8ih0r182qicl FOREIGN KEY (student_id)
    REFERENCES student (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS student_document
    OWNER to postgres;

-- Table: student_course

-- DROP TABLE IF EXISTS student_course;

CREATE TABLE IF NOT EXISTS student_course
(
    student_id bigint NOT NULL,
    course_id bigint,
    CONSTRAINT fkq7yw2wg9wlt2cnj480hcdn6dq FOREIGN KEY (student_id)
    REFERENCES student (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS student_course
    OWNER to postgres;