CREATE SEQUENCE IF NOT EXISTS TEST_DEFINITION_ID_SEQ;
CREATE TABLE TEST_DEFINITION (
   ID serial,
   NAME varchar(40) NOT NULL,
   STATUS varchar(20)
);

-- INSERT INTO TEST_DEFINITION VALUES (nextval('test_definition_id_seq'), 'Manchester City', 'passed');
-- commit;

-- https://arjunsk.medium.com/liquibase-create-schema-tables-items-in-your-database-753a6dd38893