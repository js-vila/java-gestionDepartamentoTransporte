
package com.mycompany.ues_trans.Modelo;

import com.mycompany.ues_trans.Entidad.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmpleadoModel extends Conexion {
    
    
    //Esta clase se encarga de ejecutar las consultas SQL
    //Siempre tomanado en cuenta las buenas praticas, como parametrizar las consultas
    //Y tambien abriendo y cerrando conexion en cada ocasion.
    
    
    //Metodo para crear un nuevo registro en la tabla empleado
       public boolean insertar(Empleado emp) {
        String sql = "INSERT INTO empleados (nombre, departamento, salario_base, bono, salario_Total) VALUES (?, ?, ?, ?, ?)";
        boolean resultado = false;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getDepartamento());
            ps.setDouble(3, emp.getSalario());
            ps.setDouble(4, emp.getBono());
            ps.setDouble(5, emp.getSalarioTotal());
            resultado = ps.executeUpdate() > 0;
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error al insertar empleado: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return resultado;
    }
    
     //Metodo para actualizar un registro de latabla
    public boolean actualizar(Empleado emp) {
        String sql = "UPDATE empleados SET nombre=?, departamento=?, salario_base=?, bono=?, salario_total=? WHERE id=?";
        boolean resultado = false;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getDepartamento());
            ps.setDouble(3, emp.getSalario());
            ps.setDouble(4, emp.getBono());
            ps.setDouble(5, emp.getSalarioTotal());
            ps.setInt(6, emp.getId());
            resultado = ps.executeUpdate() > 0;
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error al actualizar lista empleados: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return resultado;
    }   
    
    //Metoto para eliminar por ID un registro 
     public boolean eliminar(int id) {
        String sql = "DELETE FROM empleados WHERE id=?";
        boolean resultado = false;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            resultado = ps.executeUpdate() > 0;
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error al eliminar Empleado: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return resultado;
    }
     
    //Este metodo guarda los registros en un lista
     public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados ORDER BY id";
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("departamento"),
                        rs.getDouble("salario_base"),
                        rs.getDouble("bono"),
                        rs.getDouble("salario_total")
                );
                lista.add(em);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error al listar empledos: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return lista;
    }
       
     
     //Buscar por nombre en los registros de la tabla
     public List<Empleado> buscarPorNombre(String nombre) {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE nombre LIKE ? ORDER BY id";
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado(
                       rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("departamento"),
                        rs.getDouble("salario_base"),
                        rs.getDouble("bono"),
                        rs.getDouble("salario_total")
                );
                lista.add(em);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error al buscar empleado por nombre: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return lista;
    }
     
     
     //Este metodo nos agrupara el costo total por departamento
     //Utilizado para generar el reporte
     public Map<String, Double> obtenerCostosPorDepartamento() {
    // Diccionario para guardar los resultados
    Map<String, Double> costos = new HashMap<>();
    
    String sql = "SELECT departamento, SUM(salario_total) AS costo_total FROM empleados GROUP BY departamento";
    
    try {
       
        abrirConexion(); 
               
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            costos.put(rs.getString("departamento"), rs.getDouble("costo_total"));
        }
               
        rs.close();
        ps.close();
        
        } catch (SQLException e) {
        System.err.println("Error al generar costos por departamento: " + e.getMessage());
        } finally {
      
            cerrarConexion(); 
        }
    
    return costos;
}
  
}
