-- =========================================================
--  data.sql
-- =========================================================

-- 1.  statuses
INSERT INTO statuses (label) VALUES ( 'Gemeld');
INSERT INTO statuses (label) VALUES ( 'In behandeling');
INSERT INTO statuses (label) VALUES ( 'Opgelost');
INSERT INTO statuses (label) VALUES ( 'Gesloten – geen actie');

-- 2.  companies
INSERT INTO companies (name, industry) VALUES ('FineerMeubel CNC', 'Meubelpanelen & kantenlijmen');
INSERT INTO companies (name, industry) VALUES ('HoutCrafters BV', 'Massief-houten meubels & 5-assig frezen');
INSERT INTO companies (name, industry) VALUES ('ToolService Nederland', 'Onderhoud CNC-gereedschappen');

-- 3.  users
INSERT INTO users (email, password, role, company_id) VALUES ('peter@fineermeubel.nl',   'cnc123', 'ADMIN',      1);
INSERT INTO users (email, password, role, company_id) VALUES ('marie@fineermeubel.nl',   'cnc123', 'USER',       1);
INSERT INTO users (email, password, role, company_id) VALUES ('wim@houtcrafters.nl',     'cnc123', 'USER',       2);
INSERT INTO users (email, password, role, company_id) VALUES ('sanne@toolservice.nl',    'cnc123', 'CONSULTANT', 3);

-- 4.  tickets
INSERT INTO tickets (title, description, created_at, updated_at, user_id, status_id)
VALUES ('Spindel loopt heet – Biesse Rover 324',
        'Temperatuur > 68 °C na 15 min freeswerk op 18 mm MDF.',
        '2025-11-02 14:00:00', '2025-11-02 14:00:00', 2, 1);

INSERT INTO tickets (title, description, created_at, updated_at, user_id, status_id)
VALUES ('Vacuümtafel verliest druk',
        'Aluminium pods sluiten niet goed af; plaat verschuift tijdens graveerpad.',
        '2025-11-02 14:15:00', '2025-11-02 14:15:00', 3, 2);

INSERT INTO tickets (title, description, created_at, updated_at, user_id, status_id)
VALUES ('Jaarlijks onderhoud HSK-63F gereedschaphouder',
        'Magazijn 18 posities smeren, balcontrole, trekkracht meting.',
        '2025-11-02 14:30:00', '2025-11-02 14:30:00', 4, 3);

-- 5.  timers
INSERT INTO timers (start_time, end_time, duration_in_seconds, active)
VALUES ('2025-11-02 14:00:00', '2025-11-02 15:00:00', 3600, false);

INSERT INTO timers (start_time, end_time, duration_in_seconds, active)
VALUES ('2025-11-02 14:15:00', null, 0, true);

-- 6.  messages
INSERT INTO messages (content, timestamp, ticket_id)
VALUES ('Ticket aangemaakt', '2025-11-02 14:00:00', 1);

INSERT INTO messages (content, timestamp, ticket_id)
VALUES ('Koelventilator blaast 12 V i.p.v. 24 V – voeding vervangen?',
        '2025-11-02 14:30:00', 1);

INSERT INTO messages (content, timestamp, ticket_id)
VALUES ('Vacuümpomp leest 0,5 bar i.p.v. 0,8 – filter verstopt?',
        '2025-11-02 14:20:00', 2);

-- 7.  notifications
INSERT INTO notifications (message, read, created_at, user_id)
VALUES ('Nieuw ticket aangemaamd: spindeloververhitting Biesse', false, '2025-11-02 14:00:00', 1);

INSERT INTO notifications (message, read, created_at, user_id)
VALUES ('Timer gestart voor vacuümtafel controle', false, '2025-11-02 14:15:00', 3);

INSERT INTO notifications (message, read, created_at, user_id)
VALUES ('Onderhoud HSK-63F succesvol afgerond – rapport beschikbaar', true, '2025-11-02 14:30:00', 4);