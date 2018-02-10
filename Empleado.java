package org.gradle;

import org.apache.commons.collections.list.GrowthList;

public class Empleado {
    public String id;
    public String nombre;
    public String restringido = "";
    public String emparejado;
    public String libre;
    
    public Empleado(String linea) {
    	// constructor de empleados, a partir de linea del fichero
        String[] split = linea.split("#");
    	id = split[0].trim();
    	nombre = split[1].trim();
    	if(null != split[2] & split[2].trim().length() > 0){
    		restringido = split[2].trim();
    	}
    	emparejado = "";
    	libre = "S";
    }

    public String getId() {
    	return id;
    }
    public void setId(String id) {
    	this.id = id;
    }
    
    public String getNombre() {
     	return nombre;
     }
     public void setNombre(String nombre) {
     	this.nombre = nombre;
     }
     
    public String getRestringido() {
    	return restringido;
    }
    public void setRestringido(String restringido) {
    	this.restringido = restringido;
    }
    
   public String getEmparejado() {
	   return emparejado;
   }
   public void setEmparejado(String emparejado) {
	   this.emparejado = emparejado;
   }
   
   public String getLibre() {
	   return libre;
   }
   public void setLibre(String libre) {
	   this.libre = libre;
   }
   
}