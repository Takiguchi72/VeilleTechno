-- Création de la bdd
--CREATE DATABASE veilletechno
--  WITH OWNER = "f.thierry"
--       ENCODING = 'UTF8'
--       TABLESPACE = pg_default
--       LC_COLLATE = 'fr_FR.UTF-8'
--       LC_CTYPE = 'fr_FR.UTF-8'
--       CONNECTION LIMIT = -1;

-- Création du shéma
--CREATE SCHEMA veilletechnologique
--  AUTHORIZATION "f.thierry";

-- Sélection du shéma
SET SCHEMA 'veilletechnologique';

-- Création de la table t_users
create table t_users(
	identifiant varchar(30) not null,
	email varchar(105),
	passwd varchar(50),
	constraint pk_t_users primary key (identifiant)
);

-- Création de la table t_url
create table t_url(
	id integer not null,
	intitule varchar(100),
	adresse varchar(500) not null,
	createur varchar(30),
	constraint pk_t_url primary key (id),
	constraint fk_t_url_t_users foreign key (createur) references t_users (identifiant)
);

-- Création de la table t_tag
create table t_tag(
	id integer not null,
	libelle varchar(150) not null,
	constraint pk_t_tag primary key (id)
);

-- Création de la table t_ligne_url_tag
create table t_ligne_url_tag(
	id_url integer not null,
	id_tag integer not null,
	constraint pk_t_ligne_url_tag primary key (id_url,id_tag),
	constraint fk_t_ligne_url_tag_t_url foreign key (id_url) references t_url (id),
	constraint fk_t_ligne_url_tag_t_tag foreign key (id_tag) references t_tag (id)
);
