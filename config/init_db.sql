DROP TABLE IF EXISTS resume, contact, section;
CREATE TABLE resume
(
    uuid      CHAR(5) PRIMARY KEY NOT NULL,
    full_name TEXT                 NOT NULL
);

CREATE TABLE contact
(
    id          SERIAL,
    resume_uuid CHAR(5) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL
);
CREATE UNIQUE INDEX contact_uuid_type_index
    ON contact (resume_uuid, type);

CREATE TABLE section
(
    id          SERIAL,
    resume_uuid CHAR(5) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    description TEXT     NOT NULL
);
CREATE UNIQUE INDEX section_uuid_type_index
    ON section (resume_uuid, type);