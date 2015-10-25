
-- this short SQL script adds data into the DB, in order to have the admins and some example data

INSERT INTO USER (ID, FIRST_NAME, LAST_NAME, MAIL, PASSWORD, TELEPHONE, ADMIN) VALUES (1, 'Alexandre', 'LEBRUN', 'alexandre.lebrun@etudiant.mines-nantes.fr', 'b9e50e0e8b504aa57a1bb6711ee832ee4ce9c641a1618b91833582382c709023', '0672566352', '1');
INSERT INTO USER (ID, FIRST_NAME, LAST_NAME, MAIL, PASSWORD, TELEPHONE, ADMIN) VALUES (2, 'Arthur', 'FAUGERAS', 'arthur.faugeras@gmail.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', '0645517830', '1');
INSERT INTO USER (ID, FIRST_NAME, LAST_NAME, MAIL, PASSWORD, TELEPHONE, ADMIN) VALUES (3, 'Faouzi', 'CHIHEB', 'faouzi.chiheb@etudiant.mines-nantes.fr', 'b9e50e0e8b504aa57a1bb6711ee832ee4ce9c641a1618b91833582382c709023', '0649019968', '0');
INSERT INTO USER (ID, FIRST_NAME, LAST_NAME, MAIL, PASSWORD, TELEPHONE, ADMIN) VALUES (4, 'Alex', 'NURBEL', 'alex.3385@hotmail.fr', 'b9e50e0e8b504aa57a1bb6711ee832ee4ce9c641a1618b91833582382c709023', '0672566352', '0');

INSERT INTO RESOURCE_TYPE (ID, NAME) VALUES (1, 'Matériel de sport');
INSERT INTO RESOURCE_TYPE (ID, NAME) VALUES (2, 'Matériel informatique');
INSERT INTO RESOURCE_TYPE (ID, NAME) VALUES (3, 'Déguisements');

INSERT INTO RESOURCE (ID, NAME, DESCRIPTION, PLACE, OWNER_ID, TYPE_ID) VALUES (1, 'PC Gamer ROXOR', 'Je vous présente mon magnifique PC gamer surpuissant de la mort qui fait tout tourner au max', '45 rue des Oiseaux 44000 NANTES', 1, 2);
INSERT INTO RESOURCE (ID, NAME, DESCRIPTION, PLACE, OWNER_ID, TYPE_ID) VALUES (2, 'Costume de scientifique', 'Tout est dans le titre', '85 rue des Hirondelles 44000 NANTES', 1, 3);