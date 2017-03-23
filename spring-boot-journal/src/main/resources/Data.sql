--NEWS
INSERT INTO NEWS(title,summary,created) VALUES('Get to know Spring Boot','Today I will learn Spring Boot','2016-01-02 00:00:00.00');
INSERT INTO NEWS(title,summary,created) VALUES('Simple Spring Boot Project','I will do my first Spring Boot project','2016-01-03 00:00:00.00');
INSERT INTO NEWS(title,summary,created) VALUES('Spring Boot Reading','Read more about Spring Boot','2016-02-02 00:00:00.00');
INSERT INTO NEWS(title,summary,created) VALUES('Spring Boot in the Cloud','Learn Spring Boot using Cloud Foundry','2016-02-05 00:00:00.00');

-- ROLE
INSERT INTO ROLE(roles) VALUES('USER');
INSERT INTO ROLE(roles) VALUES('FISRT');
INSERT INTO ROLE(roles) VALUES('PREMIUM');
INSERT INTO ROLE(roles) VALUES('ADMIN');
INSERT INTO ROLE(roles) VALUES('ACTUATOR');

-- UTILISATEUR
INSERT INTO UTILISATEUR(nom,prenom,username,email,password,password_confirmation,type_compte,active,date_naissance) VALUES('Esposito','Francesco','frank','francescoesposito7@gmail.com','$2a$10$a06n27PtOlt25AIzbz5goOzLdMrjU65kjplE87TUO3/wpw3vPS9Da','$2a$10$a06n27PtOlt25AIzbz5goOzLdMrjU65kjplE87TUO3/wpw3vPS9Da',0,TRUE,'1982-11-18 00:00:00.00');
INSERT INTO UTILISATEUR(nom,prenom,username,email,password,password_confirmation,type_compte,active,date_naissance) VALUES('Lefaix','Jimmy','jimmy','jimmy@gmail.com','$2a$10$a06n27PtOlt25AIzbz5goOzLdMrjU65kjplE87TUO3/wpw3vPS9Da','$2a$10$a06n27PtOlt25AIzbz5goOzLdMrjU65kjplE87TUO3/wpw3vPS9Da',0,TRUE,'1991-10-26 00:00:00.00');


--CONTACT
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test1','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test2','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test3','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test4','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test5','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test6','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test7','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test8','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test9','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test10','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test11','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test12','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test13','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test14','test@test.it','test','test');


--CONTACT
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test1','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test2','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test3','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test4','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test5','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test6','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test7','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test8','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test9','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test10','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test11','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test12','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test13','test@test.it','test','test');
INSERT INTO CONTACT(nom,email,objet,sujet) VALUES('test14','test@test.it','test','test');



-- JOIN TABLE
INSERT INTO UTILISATEUR_ROLES(utilisateurs_user_id,roles_role_id) 
VALUES((SELECT user_id FROM UTILISATEUR WHERE username='frank'),
		(SELECT role_id FROM ROLE WHERE roles='ADMIN'));
		
INSERT INTO UTILISATEUR_ROLES(utilisateurs_user_id,roles_role_id) 
VALUES((SELECT user_id FROM UTILISATEUR WHERE username='jimmy'),
		(SELECT role_id FROM ROLE WHERE roles='USER'));


