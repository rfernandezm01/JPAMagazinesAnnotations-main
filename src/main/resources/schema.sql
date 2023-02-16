CREATE TABLE Region
(
  RegionID serial integer PRIMARY KEY,
  nom character varying(30) NOT NULL,
  Habitantes integer,
  Elemento varying(20),
  Nombrearconte varying(30),
  Mundo varying(20),
  CONSTRAINT connectPersonaje PRIMARY KEY (PersonajeID)
);



CREATE TABLE Armas
(
  ArmaID serial integer PRIMARY KEY,
  TipodeArma varying(20),
  Nombre varying(30),
  Numeroestrellas integer,
  PuntosAtaque integer,
  CONSTRAINT connectPersonaje PRIMARY KEY (PersonajeID),
);

CREATE TABLE PersonajeArma(

);


CREATE TABLE Personaje
(
  RegionID serial integer,
  ArmaID serial integer,
  PersonajeID serial integer PRIMARY KEY,
  Nombre varying(30),
  Numeroestrellas integer,
  TipodeArma varying(20),
  Elemento varying(20),
  Sexo varying(20),
  CONSTRAINT connectRegion PRIMARY KEY (RegionID),
  CONSTRAINT connectArmas FOREIGN KEY (ArmaID)
      REFERENCES revistes (id_revista) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_articles_autors FOREIGN KEY (id_autor)
      REFERENCES autors (id_autor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_articles UNIQUE (titol)  
);

