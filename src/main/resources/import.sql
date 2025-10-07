INSERT INTO utilisateur(nom,mail,password,role,active,cle_utilisateur) VALUES ("Administrateur","admin@admin.fr","$2y$10$2SCa8UX96FtmRNUkzgN6euxXyVh1UmmQX5dOjtIXawnfjQ.ev30AO","ADMIN",1,"4c3cc7f8-1a54-4980-8ead-6716df265e06");


-- Basketball poules (6 matchs)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','France-USA','2024-07-27','18:00','Bercy Arena',5000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Turquie-Slovénie','2024-07-27','21:00','Bercy Arena',5000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Allemagne-Serbie','2024-07-28','18:00','Bercy Arena',5000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','France-Turquie','2024-07-28','21:00','Bercy Arena',5000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Slovénie-Serbie','2024-07-29','18:00','Bercy Arena',5000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','USA-Allemagne','2024-07-29','21:00','Bercy Arena',5000,0,50);

-- Basketball phases finales (quarts, demis, finales)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Quart de finale 1','2024-08-06','18:00','Bercy Arena',5000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Quart de finale 2','2024-08-06','21:00','Bercy Arena',5000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Quart de finale 3','2024-08-07','18:00','Bercy Arena',5000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Quart de finale 4','2024-08-07','21:00','Bercy Arena',5000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Demi-finale 1','2024-08-09','18:00','Bercy Arena',5000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Demi-finale 2','2024-08-09','21:00','Bercy Arena',5000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Match pour la 3e place','2024-08-11','14:00','Bercy Arena',5000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Basketball','Finale','2024-08-11','21:00','Bercy Arena',5000,0,90);

-- Football poules (6 matchs)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','France-Allemagne','2024-07-25','18:00','Stade de France',81000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Brésil-Argentine','2024-07-25','21:00','Stade Vélodrome',67000,0,65);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Espagne-Nigeria','2024-07-26','18:00','Stade Pierre-Mauroy',50000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Japon-Corée du Sud','2024-07-26','21:00','Stade de Lyon',60000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','France-Brésil','2024-07-27','18:00','Stade de France',81000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Allemagne-Argentine','2024-07-27','21:00','Stade Vélodrome',67000,0,65);

-- Football phases finales
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','1/8 de finale 1','2024-08-05','18:00','Stade Pierre-Mauroy',50000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','1/8 de finale 2','2024-08-05','21:00','Stade de Lyon',60000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','1/8 de finale 3','2024-08-06','18:00','Stade Louis II',30000,0,65);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','1/8 de finale 4','2024-08-06','21:00','Stade Océane',25000,0,65);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Quart de finale 1','2024-08-08','18:00','Stade de France',81000,0,80);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Quart de finale 2','2024-08-08','21:00','Stade Vélodrome',67000,0,80);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Quart de finale 3','2024-08-09','18:00','Stade Pierre-Mauroy',50000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Quart de finale 4','2024-08-09','21:00','Stade de Lyon',60000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Demi-finale 1','2024-08-11','18:00','Stade de France',81000,0,90);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Demi-finale 2','2024-08-11','21:00','Stade Vélodrome',67000,0,90);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Match pour la 3e place','2024-08-12','15:00','Stade de France',81000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Football','Finale','2024-08-12','21:00','Stade de France',81000,0,100);

-- Volley-ball poules (6 matchs)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Brésil-Italie','2024-07-26','18:00','Accor Arena',18000,0,55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','France-Pologne','2024-07-26','21:00','Accor Arena',18000,0,55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Russie-Japon','2024-07-27','18:00','Accor Arena',18000,0,55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','États-Unis-Argentine','2024-07-27','21:00','Accor Arena',18000,0,55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','France-Italie','2024-07-28','18:00','Accor Arena',18000,0,55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Brésil-Pologne','2024-07-28','21:00','Accor Arena',18000,0,55);

-- Volley-ball phases finales
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Quart de finale 1','2024-08-05','18:00','Accor Arena',18000,0,65);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Quart de finale 2','2024-08-05','21:00','Accor Arena',18000,0,65);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Quart de finale 3','2024-08-06','18:00','Accor Arena',18000,0,65);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Quart de finale 4','2024-08-06','21:00','Accor Arena',18000,0,65);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Demi-finale 1','2024-08-08','18:00','Accor Arena',18000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Demi-finale 2','2024-08-08','21:00','Accor Arena',18000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Match pour la 3e place','2024-08-10','14:00','Accor Arena',18000,0,80);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Volley-ball','Finale','2024-08-10','20:30','Accor Arena',18000,0,90);

-- Water-polo poules (6 matchs)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Hongrie-Italie','2024-07-26','15:00','Centre Aquatique',8000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','France-Espagne','2024-07-26','18:00','Centre Aquatique',8000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Grèce-Croatie','2024-07-27','15:00','Centre Aquatique',8000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Serbie-Hollande','2024-07-27','18:00','Centre Aquatique',8000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','France-Italie','2024-07-28','15:00','Centre Aquatique',8000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Hongrie-Croatie','2024-07-28','18:00','Centre Aquatique',8000,0,50);

-- Water-polo phases finales
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Quart de finale 1','2024-08-05','14:00','Centre Aquatique',8000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Quart de finale 2','2024-08-05','18:00','Centre Aquatique',8000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Quart de finale 3','2024-08-06','14:00','Centre Aquatique',8000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Quart de finale 4','2024-08-06','18:00','Centre Aquatique',8000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Demi-finale 1','2024-08-08','14:00','Centre Aquatique',8000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Demi-finale 2','2024-08-08','18:00','Centre Aquatique',8000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Match pour la 3e place','2024-08-10','11:00','Centre Aquatique',8000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Water-polo','Finale','2024-08-10','19:00','Centre Aquatique',8000,0,85);

-- Rugby à 7 poules (6 matchs)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','France-Fidji','2024-07-24','13:00','Stade de France',69000,0,80);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','France-États-Unis','2024-07-24','15:00','Stade de France',69000,0,80);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Nouvelle-Zélande-Argentine','2024-07-24','17:00','Stade de France',69000,0,80);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Australie-Angleterre','2024-07-24','19:00','Stade de France',69000,0,80);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Canada-Afrique du Sud','2024-07-25','13:00','Stade de France',69000,0,80);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Japon-Russie','2024-07-25','15:00','Stade de France',69000,0,80);

-- Rugby à 7 phases finales
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Quart de finale 1','2024-07-26','20:00','Stade de France',69000,0,90);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Quart de finale 2','2024-07-26','21:30','Stade de France',69000,0,90);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Demi-finale 1','2024-07-27','15:30','Stade de France',69000,0,95);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Demi-finale 2','2024-07-27','17:00','Stade de France',69000,0,95);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Match pour la 3e place','2024-07-28','16:00','Stade de France',69000,0,100);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Rugby-à-7','Finale','2024-07-28','18:00','Stade de France',69000,0,100);

-- Judo épreuves principales (6 catégories exemples)
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Judo', 'Poids légers hommes (60kg)', '2024-07-27', '14:00', 'Grand Palais Éphémère', 8000, 0, 55);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Judo', 'Poids moyens hommes (81kg)', '2024-07-27', '17:00', 'Grand Palais Éphémère', 8000, 0, 55);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Judo', 'Poids lourds hommes (+100kg)', '2024-07-28', '14:00', 'Grand Palais Éphémère', 8000, 0, 55);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Judo', 'Poids légers femmes (48kg)', '2024-07-29', '14:00', 'Grand Palais Éphémère', 8000, 0, 55);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Judo', 'Poids moyens femmes (63kg)', '2024-07-30', '14:00', 'Grand Palais Éphémère', 8000, 0, 55);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Judo', 'Poids lourds femmes (+78kg)', '2024-07-31', '14:00', 'Grand Palais Éphémère', 8000, 0, 55);

-- Judo finales hommes et femmes
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Judo', 'Finales individuelles hommes', '2024-08-01', '16:00', 'Grand Palais Éphémère', 8000, 0, 70);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Judo', 'Finales individuelles femmes', '2024-08-02', '16:00', 'Grand Palais Éphémère', 8000, 0, 70);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Judo', 'Finale mixte par équipes', '2024-08-05', '18:00', 'Grand Palais Éphémère', 8000, 0, 75);

-- Natation, quelques épreuves clés
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Natation', '100m nage libre hommes - Séries', '2024-08-02', '09:00', 'Centre Aquatique', 17000, 0, 60);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Natation', '100m nage libre hommes - Finale', '2024-08-02', '18:30', 'Centre Aquatique', 17000, 0, 70);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Natation', '4x100m nage libre femmes - Finale', '2024-08-05', '20:00', 'Centre Aquatique', 17000, 0, 70);

-- Cyclisme sur piste, quelques épreuves
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Cyclisme-sur-piste', 'Sprint hommes - Qualifications', '2024-08-06', '15:00', 'Vélodrome National', 7000, 0, 50);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Cyclisme-sur-piste', 'Sprint hommes - Finale', '2024-08-07', '18:00', 'Vélodrome National', 7000, 0, 60);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Cyclisme-sur-piste', 'Course aux points femmes', '2024-08-08', '10:00', 'Vélodrome National', 7000, 0, 55);

-- Aviron, exemples
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Aviron', 'Skiff homme', '2024-07-29', '10:00', 'Seine', 8000, 0, 50);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Aviron', 'Skiff femme', '2024-07-29', '11:00', 'Seine', 8000, 0, 50);

-- Tir à l\'arc, exemple
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Tir-à-l\'arc', 'Individuel hommes - Qualifications', '2024-07-30', '09:00', 'Champs de tir', 12000, 0, 45);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Tir-à-l\'arc', 'Individuel hommes - Finale', '2024-08-02', '11:00', 'Champs de tir', 12000, 0, 55);

-- Escrime, exemple
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Escrime', 'Florett hommes - Elite', '2024-07-29', '10:00', 'Grand Palais Éphémère', 8000, 0, 55);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Escrime', 'Sabre femmes - Finale', '2024-07-30', '14:00', 'Grand Palais Éphémère', 8000, 0, 55);

-- Gymnastique artistique, exemples
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Gymnastique-artistique', 'All-around hommes - Qualifications', '2024-07-27', '09:00', 'Accor Arena', 18000, 0, 60);
INSERT INTO evenement(sport, nom_evenement, date_evenement, heure_evenement, lieu, capacite_max, billets_vendus, prix_billet) VALUES ('Gymnastique-artistique', 'Finale individuelle femmes', '2024-08-02', '19:30', 'Accor Arena', 18000, 0, 70);

-- Athlétisme (exemples choisis)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Athlétisme','100m hommes - Séries','2024-08-02','09:00','Stade de France',80000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Athlétisme','100m hommes - Finale','2024-08-02','20:15','Stade de France',80000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Athlétisme','200m femmes - Demi-finales','2024-08-04','18:00','Stade de France',80000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Athlétisme','200m femmes - Finale','2024-08-05','20:00','Stade de France',80000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Athlétisme','Marathon hommes','2024-08-09','07:00','Paris Centre-ville',40000,0,70);

-- Surf (nouveau sport)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Surf','Compétition homme - Round 1','2024-07-27','08:00','Tahiti','3000',0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Surf','Compétition femme - Round 1','2024-07-28','08:00','Tahiti','3000',0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Surf','Finale homme','2024-07-30','14:00','Tahiti','3000',0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Surf','Finale femme','2024-07-30','18:00','Tahiti','3000',0,70);

-- Escalade
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Escalade','Vitesse hommes qualificatifs','2024-08-05','13:00','Parc des Sports',6000,0,55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Escalade','Vitesse hommes finale','2024-08-05','18:00','Parc des Sports',6000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Escalade','Difficulté femmes','2024-08-06','14:00','Parc des Sports',6000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Escalade','Combiné mixte','2024-08-07','16:00','Parc des Sports',6000,0,70);

-- Breaking (nouveau)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Breaking','Compétition B-boys ronde 1','2024-08-09','10:00','Place de la Bastille',15000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Breaking','Compétition B-girls ronde 1','2024-08-09','14:00','Place de la Bastille',15000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Breaking','Finale B-boys','2024-08-10','18:00','Place de la Bastille',15000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Breaking','Finale B-girls','2024-08-10','21:00','Place de la Bastille',15000,0,60);

-- Golf
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Golf','Tour 1 hommes','2024-08-01','08:00','Golf National',12000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Golf','Tour 2 hommes','2024-08-02','08:00','Golf National',12000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Golf','Tour 3 hommes','2024-08-03','08:00','Golf National',12000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Golf','Final homme','2024-08-04','10:00','Golf National',12000,0,90);

-- Triathlon
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Triathlon','Triathlon hommes','2024-07-31','07:00','Paris - Centre-ville',15000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Triathlon','Triathlon femmes','2024-08-01','07:00','Paris - Centre-ville',15000,0,60);

-- Tennis (épreuves individuelles)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Tennis','Simple hommes 1/16e finale','2024-07-27','10:00','Court Central Roland Garros',15000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Tennis','Simple hommes 1/8e finale','2024-07-28','14:00','Court Central Roland Garros',15000,0,65);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Tennis','Simple hommes 1/4 finale','2024-07-29','14:00','Court Central Roland Garros',15000,0,70);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Tennis','Simple hommes demi-finale','2024-07-31','20:00','Court Central Roland Garros',15000,0,75);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Tennis','Simple hommes finale','2024-08-02','16:00','Court Central Roland Garros',15000,0,80);

-- Cyclisme sur route
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Cyclisme-sur-route', 'Course en ligne hommes', '2024-08-07', '09:00', 'Paris Centre', 30000, 0, 50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Cyclisme-sur-route', 'Contre-la-montre femmes', '2024-08-08', '07:30', 'Paris Centre', 20000, 0, 50);

-- Haltérophilie
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Haltérophilie', 'Poids légers hommes', '2024-07-30', '10:00', 'Accor Arena', 15000, 0, 60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Haltérophilie', 'Poids moyens femmes', '2024-07-31', '10:00', 'Accor Arena', 15000, 0, 60);

-- Lutte
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Lutte', 'Lutte gréco-romaine hommes 60kg', '2024-08-04', '14:00', 'Grand Palais Éphémère', 8000, 0, 55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Lutte', 'Lutte libre femmes 62kg', '2024-08-05', '14:00', 'Grand Palais Éphémère', 8000, 0, 55);

-- Taekwondo
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Taekwondo', 'Poids légers hommes', '2024-07-31', '16:00', 'Grand Palais Éphémère', 8000, 0, 60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Taekwondo', 'Poids lourds femmes', '2024-08-01', '16:00', 'Grand Palais Éphémère', 8000, 0, 60);

-- Tennis de table
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Tennis-de-table', 'Simple hommes - 1/16 de finale', '2024-07-28', '11:00', 'Le Bourget', 9000, 0, 55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Tennis-de-table', 'Simple femmes - Finale', '2024-08-02', '18:00', 'Le Bourget', 9000, 0, 60);

-- Tir sportif
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Tir-sportif', 'Pistolet 10m hommes', '2024-07-31', '10:00', 'Château de Versailles', 3000, 0, 50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Tir-sportif', 'Carabine 50m femmes', '2024-08-01', '10:00', 'Château de Versailles', 3000, 0, 50);

-- Triathlon
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Triathlon', 'Triathlon hommes', '2024-07-31', '07:00', 'Paris centre-ville', 15000, 0, 60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Triathlon', 'Triathlon femmes', '2024-08-01', '07:00', 'Paris centre-ville', 15000, 0, 60);

-- Badminton
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Badminton','Simple hommes - 1/16 finale','2024-07-27','13:00','Palais des Sports',7500,0,45);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Badminton','Simple femmes - 1/8 finale','2024-07-28','18:00','Palais des Sports',7500,0,45);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Badminton','Double mixte - Finale','2024-08-01','20:00','Palais des Sports',7500,0,50);

-- Boxe
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Boxe','Poids légers hommes','2024-08-02','16:00','Accor Arena',15000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Boxe','Poids lourds femmes','2024-08-03','16:00','Accor Arena',15000,0,60);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Boxe','Finale toutes catégories','2024-08-10','20:00','Accor Arena',15000,0,70);

-- Canoë-kayak sprint
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Canoë-kayak','K1 hommes 1000m','2024-07-30','10:00','Île de loisirs',12000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Canoë-kayak','K4 femmes 500m','2024-07-31','14:00','Île de loisirs',12000,0,50);

-- Cyclisme sur piste (suite)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Cyclisme-sur-piste','Poursuite individuelle hommes','2024-08-07','14:00','Vélodrome National',7000,0,55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Cyclisme-sur-piste','Madison mixte','2024-08-09','16:00','Vélodrome National',7000,0,60);

-- Plongeon
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Plongeon','Tremplin 3m hommes - Séries','2024-08-04','10:00','Centre Aquatique',9000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Plongeon','Tremplin 3m hommes - Finale','2024-08-05','18:30','Centre Aquatique',9000,0,60);

-- Skateboard
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Skateboard','Street hommes - Qualifications','2024-07-29','09:00','Place de la Concorde',12000,0,55);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Skateboard','Street hommes - Finale','2024-07-30','14:00','Place de la Concorde',12000,0,65);

-- Softball/Baseball (exemples)
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Softball','Match 1','2024-07-25','14:00','Parc des Sports',7000,0,50);
INSERT INTO evenement(sport,nom_evenement,date_evenement,heure_evenement,lieu,capacite_max,billets_vendus,prix_billet) VALUES ('Softball','Match 2','2024-07-26','14:00','Parc des Sports',7000,0,50);





INSERT INTO offre(nom_offre,nb_places,discount) VALUES ("SOLO",1,1);
INSERT INTO offre(nom_offre,nb_places,discount) VALUES ("DUO",2,0.95);
INSERT INTO offre(nom_offre,nb_places,discount) VALUES ("FAMILIALE",4,0.9);