SELECT * FROM preciosServidor ps WHERE idPreSer = 1539

SELECT producto.dtosExtras, preciosServidor.precio FROM producto INNER JOIN preciosServidor ON producto.idPreSer = preciosServidor.idPreSer WHERE codBarProducto = 3801 AND idMarca = 1

