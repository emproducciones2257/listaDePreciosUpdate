DROP TABLE IF EXISTS 'color'
;

DROP TABLE IF EXISTS 'deArt'
;

DROP TABLE IF EXISTS 'marca'
;

DROP TABLE IF EXISTS 'preciosServidor'
;

DROP TABLE IF EXISTS 'producto'
;

CREATE TABLE 'color'
(
	'idColor' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	'idMarca' INTEGER NOT NULL,
	'codColor' INTEGER NULL,
	'nombreColor' TEXT NOT NULL,
	CONSTRAINT 'FK_color_marca' FOREIGN KEY ('idMarca') REFERENCES 'marca' ('idMarca') ON DELETE No Action ON UPDATE No Action
)
;

CREATE TABLE 'deArt'
(
	'idDeArt' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	'codDesc' INTEGER NOT NULL,
	'descripcion' TEXT NOT NULL
)
;

CREATE TABLE 'marca'
(
	'idMarca' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	'nombre' TEXT NOT NULL,
	'codMarca' INTEGER NOT NULL
)
;

CREATE TABLE 'preciosServidor'
(
	'idPreSer' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	'descArt' TEXT NOT NULL,
	'precio' NUMERIC NOT NULL
)
;

CREATE TABLE 'producto'
(
	'idProd' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	'idMarca' INTEGER NOT NULL,
	'idPreSer' INTEGER NOT NULL,
	'cantidad' INTEGER NULL,
	'dtosExtras' TEXT NULL,
	'idColor' INTEGER NULL,
	'unidadVenta' INTEGER NOT NULL DEFAULT 0,
	'medida' INTEGER NULL,
	CONSTRAINT 'FK_producto_color' FOREIGN KEY ('idColor') REFERENCES 'color' ('idColor') ON DELETE No Action ON UPDATE No Action,
	CONSTRAINT 'FK_producto_marca' FOREIGN KEY ('idMarca') REFERENCES 'marca' ('idMarca') ON DELETE No Action ON UPDATE No Action,
	CONSTRAINT 'FK_producto_preciosServidor' FOREIGN KEY ('idPreSer') REFERENCES 'preciosServidor' ('idPreSer') ON DELETE No Action ON UPDATE No Action
)
;

CREATE INDEX 'IXFK_color_marca'
 ON 'color' ('idMarca' ASC)
;

CREATE INDEX 'IXFK_producto_color'
 ON 'producto' ('idColor' ASC)
;

CREATE INDEX 'IXFK_producto_marca'
 ON 'producto' ('idMarca' ASC)
;

CREATE INDEX 'IXFK_producto_preciosServidor'
 ON 'producto' ('idPreSer' ASC)
;
