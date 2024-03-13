insert into files(name, path)
values
('American Beauty', 'files/beauty.jpg'),
('Dune part II', 'files/dune2.jpg'),
('The Godfather', 'files/godfather.jpg'),
('Green Book', 'files/greenbook.jpg'),
('Interstellar', 'files/interstellar.jpg'),
('Parasite', 'files/parasite.jpg'),
('Pulp Fiction', 'files/pulpfiction.jpg'),
('Se7en', 'files/se7en.jpg');

insert into genres(name) values('Sci Fi'), ('Thriller'), ('Crime'), ('Romance'), ('Drama');

insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id)
values
('American Beauty', 'A sexually frustrated suburban father has a mid-life crisis after becoming infatuated with his daughter''s best friend.',
1999, 5, 17, 122, 1),
('Dune: Part Two', 'Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.',
2024, 1, 13, 166, 2),
('The Godfather', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',
1972, 3, 17, 175, 3),
('Green Book', 'A working-class Italian-American bouncer becomes the driver for an African-American classical pianist on a tour of venues through the 1960s American South.',
2018, 5, 13, 130, 4),
('Interstellar', 'When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.',
2014, 1, 13, 169, 5),
('Parasite', 'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.',
2019, 2, 17, 131, 6),
('Pulp Fiction', 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.',
1994, 3, 17, 154, 7),
('Se7en', 'Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.',
1995, 5, 17, 127, 8);

insert into halls(name, row_count, place_count, description)
values ('Blue', 6, 8, 'Luxury'), ('Red', 14, 20, 'Comfortable');

insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values
(1, 1, '20-03-24 10:00:00', '20-03-24 12:02:00', 10),
(1, 1, '20-03-24 12:30:00', '20-03-24 14:32:00', 15),
(2, 1, '20-03-24 15:00:00', '20-03-24 17:46:00', 20),
(2, 1, '20-03-24 18:00:00', '20-03-24 20:46:00', 25),
(3, 2, '20-03-24 10:00:00', '20-03-24 12:55:00', 6),
(3, 2, '20-03-24 13:00:00', '20-03-24 15:55:00', 8),
(4, 2, '20-03-24 16:00:00', '20-03-24 18:10:00', 12),
(4, 2, '20-03-24 18:30:00', '20-03-24 20:40:00', 16),
(5, 1, '21-03-24 10:00:00', '21-03-24 12:49:00', 10),
(5, 1, '21-03-24 13:00:00', '21-03-24 15:49:00', 15),
(6, 1, '21-03-24 16:00:00', '21-03-24 18:11:00', 20),
(6, 1, '21-03-24 18:30:00', '21-03-24 20:41:00', 25),
(7, 2, '21-03-24 10:00:00', '21-03-24 12:34:00', 6),
(7, 2, '21-03-24 12:30:00', '21-03-24 15:04:00', 8),
(8, 2, '21-03-24 15:30:00', '21-03-24 17:37:00', 12),
(8, 2, '21-03-24 18:00:00', '21-03-24 20:07:00', 16);