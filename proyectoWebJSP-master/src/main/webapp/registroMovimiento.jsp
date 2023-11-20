<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Movimiento</title>
</head>
<body>
    <h2>Registrar Nuevo Movimiento</h2>
    <form action="insertarMovimiento" method="post">
        <label for="descripcion">Descripción:</label>
        <input type="text" name="descripcion" required><br>

        <label for="tipoMovimiento">Tipo de Movimiento (1: Ingreso, 2: Egreso):</label>
        <input type="text" name="tipoMovimiento" required><br>

        <label for="valor">Valor:</label>
        <input type="text" name="valor" required><br>

        <input type="submit" value="Registrar">
    </form>
</body>
</html>
