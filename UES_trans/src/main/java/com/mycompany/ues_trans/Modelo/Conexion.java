
package com.mycompany.ues_trans.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//En esta clase establecemos la conexion con la base de datos
//Ademas de crear los metodos para abrir y cerrar conexion
public class Conexion {
    private static final String URL      = "jdbc:mysql://127.0.0.1:3306/transporte_ues";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER   = "com.mysql.cj.jdbc.Driver";

    protected Connection conexion;

    public void abrirConexion() {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL no encontrado: " + e.getMessage());
            System.out.println("Conectado exitosamente a la base de datos transporte ues...");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
