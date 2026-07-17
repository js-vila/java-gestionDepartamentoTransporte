
package com.mycompany.ues_trans.Entidad;


public class Empleado {
    
    //Atributos de la clase con su modificador de acceso private
    //Un atributo por cada campo de la tabla en la base de datos
    private int id;
    private String nombre;
    private String departamento;
    private double salario;
    private double bono;
    private double salarioTotal;
    
    
    //Constructores
    public Empleado() {}
    
    public Empleado(int id, String nombre, String departamento, double salario, double bono, double salarioTotal) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.bono = bono;
        this.salarioTotal = salarioTotal;
    }

    public Empleado(String nombre, String departamento, double salario) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }
    
    
    
    //Metodos Getters and Setters de cada atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getBono() {
        return bono;
    }

    public void setBono(double bono) {
        this.bono = bono;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }
    
    //Metodos Sobrecargados para calcular el bono y el salario total
    //Dependiendo de su numero de parametros
    public void CalcularBono(double salario)
    {
        
        this.bono = salario * 0.15;
        this.salarioTotal = this.salario + this.bono;
       
    };
    
    public void CalcularBono(double salario, double viaticos)
    { 
        
        this.bono = (salario * 0.10) + viaticos;
        this.salarioTotal = this.salario  + this.bono;
        
    }
    
    //Metodo toString
    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", salario=" + salario + ", bono=" + bono + ", salarioTotal=" + salarioTotal + '}';
    }
    
    
    
    
    
}
