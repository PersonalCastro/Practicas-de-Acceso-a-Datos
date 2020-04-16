Drop table tortuga;
Drop table cuidador;
Drop table grupoEspecialista;
Drop table refugio;

create table refugio( id integer,
                  nombre char(20),
                  ciudad char(20),
                  abierto boolean,
                  sucursales integer,
                  primary key(id)
             );

create table grupoEspecialista( id integer,
                  especialidad char(20),
                  capacidad integer,
                  expedicion boolean,
                  informes integer,
                  idReferencia_refugio integer,

                  primary key(id),
                  FOREIGN KEY (idReferencia_refugio) REFERENCES refugio(id)
            );

create table cuidador( id integer,
                  nombre char(20),
                  edad integer,
                  jefe boolean,
                  dni char(20),
                  idReferencia_grupoEspecialista integer,

                  primary key(id),
                  FOREIGN KEY (idReferencia_grupoEspecialista) REFERENCES grupoEspecialista(id)
            );

create table tortuga( id integer,
                  apodo char(20),
                  peso decimal,
                  hiberna boolean,
                  edad integer,
                  idReferencia_Cuidador integer,

                  primary key(id),
                  FOREIGN KEY (idReferencia_Cuidador) REFERENCES cuidador(id)
            );

CREATE USER 'libro_ad'@'localhost' IDENTIFIED BY '(password)';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,EXECUTE ON proyecto_orm.* TO 'libro_ad'@'localhost';

INSERT INTO refugio (id,nombre,ciudad,abierto,sucursales) VALUES(0,"","",false,0);
INSERT INTO grupoEspecialista (id,especialidad,capacidad,expedicion,informes,idReferencia_refugio) VALUES(0,"",0,false,0,0);
INSERT INTO cuidador (id,nombre,edad,jefe,dni,idReferencia_grupoEspecialista) VALUES(0,"",0,false,"",0);
INSERT INTO tortuga (id,apodo,peso,hiberna,edad,idReferencia_Cuidador) VALUES(0,"",0,false,0,0);
