-- ============================================================
--  Chittagong District Upazila Quiz  -  Database Schema & Seed Data
--  Covers: Chittagong district upazilas including Hathazari,
--          Fatikchhari, Raozan, Sitakunda, Patiya, Rangunia,
--          Boalkhali, Banshkhali, Anwara, Mirsharai, Satkania,
--          Lohagara, Chandanaish, Sandwip
--  Topics: Crops, Geology, Educational Institutions
-- ============================================================

CREATE DATABASE IF NOT EXISTS chittagong_quiz_db;
USE chittagong_quiz_db;

-- ------------------------------------------------------------
-- Table: questions
-- ------------------------------------------------------------
DROP TABLE IF EXISTS questions;
CREATE TABLE questions (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(500) NOT NULL,
    option_a      VARCHAR(200) NOT NULL,
    option_b      VARCHAR(200) NOT NULL,
    option_c      VARCHAR(200) NOT NULL,
    option_d      VARCHAR(200) NOT NULL,
    correct_option CHAR(1) NOT NULL,   -- 'A', 'B', 'C', or 'D'
    category      VARCHAR(50) NOT NULL -- 'Crops', 'Geology', 'Education'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------------
-- Table: results  (name + score saved after each quiz attempt)
-- ------------------------------------------------------------
DROP TABLE IF EXISTS results;
CREATE TABLE results (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    player_name   VARCHAR(100) NOT NULL,
    score         INT NOT NULL,
    total_questions INT NOT NULL DEFAULT 10,
    attempt_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------------
-- Seed: 10 MCQs on Chittagong District upazilas
-- (facts sourced from Banglapedia / Wikipedia)
-- ------------------------------------------------------------
INSERT INTO questions
(question_text, option_a, option_b, option_c, option_d, correct_option, category) VALUES

('How many upazilas does Chittagong district consist of?',
 '10', '12', '14', '16', 'C', 'Geology'),

('Chittagong district is part of which administrative division of Bangladesh?',
 'Dhaka', 'Sylhet', 'Chittagong', 'Khulna', 'C', 'Geology'),

('Which river is the biggest natural carp fish breeding ground in South Asia, flowing through Fatikchhari, Hathazari and Raozan upazilas?',
 'Karnaphuli', 'Halda', 'Sangu', 'Feni', 'B', 'Geology'),

('The Halda River finally joins which larger river near Kalurghat?',
 'Sangu', 'Karnaphuli', 'Matamuhuri', 'Naaf', 'B', 'Geology'),

('In which upazila of Chittagong district is the University of Chittagong situated, about 22 km north of Chittagong city?',
 'Raozan', 'Hathazari', 'Patiya', 'Sitakunda', 'B', 'Geology'),

('Along with paddy, ginger and turmeric, which crop is prominently grown in Sitakunda upazila?',
 'Tea', 'Betel leaf', 'Cotton', 'Coffee', 'B', 'Crops'),

('Which of these is NOT among the main fruits grown in Sitakunda upazila?',
 'Mango', 'Jackfruit', 'Apple', 'Guava', 'C', 'Crops'),

('Cultivation of which crop is banned along the riverbanks under the Halda River Fisheries Heritage protection order?',
 'Rice', 'Tobacco', 'Jute', 'Wheat', 'B', 'Crops'),

('The University of Chittagong was officially opened on which date?',
 '18 November 1966', '3 December 1965', '25 September 1966', '9 October 1968', 'A', 'Education'),

('Al-Jamiatul Ahlia Darul Ulum Muinul Islam, one of the most reputed Qawmi madrasas in Bangladesh, moved to its present location in which upazila in 1901?',
 'Patiya', 'Hathazari', 'Boalkhali', 'Rangunia', 'B', 'Education');
