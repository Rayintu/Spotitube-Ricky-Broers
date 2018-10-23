DELETE FROM tracks;


/*
TRACKS
 */

INSERT INTO tracks (title, performer, duration, album, playcount, publicationDate, description, offline_available)
VALUES
  ('Singularity', 'V From BTS', 225, 'Love yourself answer', 0, '2018-05-12', 'Solo song sung by V from BTS', false);

INSERT INTO tracks (title, performer, duration, album, playcount, publicationDate, description, offline_available)
VALUES ('Beach house', 'Boy pablo', 150, 'Beach house', 0, '2018-06-05', 'Nice boy pablo song', false);

INSERT INTO tracks (title, performer, duration, album, playcount, publicationDate, description, offline_available)
VALUES ('Everytime', 'Boy pablo', 153, 'Roy pablo', 0, '2017-11-27', 'Boy pablos biggest hit', false);

INSERT INTO tracks (title, performer, duration, album, playcount, publicationDate, description, offline_available)
VALUES ('Ur phone', 'Boy pablo', 200, 'Beach house', 0, '2018-06-05', 'Nice boy pablo song', false);

INSERT INTO tracks (title, performer, duration, album, playcount, publicationDate, description, offline_available)
VALUES ('Dance, baby!', 'Boy pablo', 195, 'Beach house', 0, '2018-06-05', 'Swingy boy pablo song', false);

/*
PRE FILLED PLAYLIST
 */

SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO playlist_track_connector (playlist_id, track_id) VALUES (1, 5);
INSERT INTO playlist_track_connector (playlist_id, track_id) VALUES (1, 6);

SET FOREIGN_KEY_CHECKS = 1;