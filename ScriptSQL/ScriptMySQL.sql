-- Script base de datos: 

CREATE DATABASE IF NOT EXISTS transporte_ues;  

USE transporte_ues;   

CREATE TABLE IF NOT EXISTS empleados (  

id INT AUTO_INCREMENT PRIMARY KEY, 

 nombre VARCHAR(100) NOT NULL,  

departamento VARCHAR(50) NOT NULL,  

salario_base DOUBLE NOT NULL,  

bono DOUBLE NOT NULL,  

salario_total DOUBLE NOT NULL 

 );