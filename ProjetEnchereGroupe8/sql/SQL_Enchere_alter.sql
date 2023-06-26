ALTER TABLE dbo.UTILISATEURS ALTER COLUMN email VARCHAR(50);
INSERT INTO dbo.UTILISATEURS (pseudo,nom,prenom,email,rue,code_postal,ville,mot_de_passe,credit,administrateur)
VALUES ('toto','Martin','Tony','tony.martin@gmail.com','rue des camelias','44 000','Nantes','azerty1234',500,0);

INSERT INTO dbo.CATEGORIES (libelle)
Values ('Informatique');

INSERT INTO dbo.ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,no_utilisateur,no_categorie)
VALUES('PC Gamer','PC Gamer pour travailler','2023-06-26','2023-07-30',2,1);