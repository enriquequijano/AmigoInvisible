package org.gradle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Funciones {
	int numParticipantes = 0;
	
    public Funciones() {
    }
    
    /*
     * devuelve un array con los empleados leidos del archivo
     */
    public  Empleado[] cargaParticipantes(String archivo) {

    	List<Empleado> p = new ArrayList<Empleado>();
    	
    	try{
	    	FileReader fr=new FileReader(archivo);
	        BufferedReader br=new BufferedReader(fr);
	    	String linea = "";
	   
	    	while ((linea=br.readLine())!=null) {
	    		// recorre fichero creando un objeto participante por cada linea
	    		p.add(new Empleado(linea));	
	    	}
	        br.close();
	        fr.close();
    	}
    	catch (Exception e)
    	{
    		System.out.println ("exception en cargaParticipantes");
    		return null;
    	}
     
    	return p.toArray(new Empleado[p.size()]);
    }
    
    /*
     * Sorteo de los empleados
     */
    void repartir(Empleado[] lParticipante){
    	
    	numParticipantes = lParticipante.length;
    	float patron = 0;
    	int contador_asignados =0;
    	boolean asignar = true;
    	
    	// para que sea aleatorio, divido el timestamp (para que sea diferente en cada ejecucion)
    	// por el numero de participantes
    	// obteniendo un patron para recorrer el array y asignar los empleados aleatoriamente
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	while (patron == 0){
    		// como el calculo puede dar como resultado = 0, se repite hasta que el patron sea > 0
    		patron = timestamp.getTime() % numParticipantes;
    		System.out.println ("patron:" + patron);
    	}
    	
    	int n = 0;
    	// hasta que todos los participantes estén asignados
    	for(int i=0; contador_asignados<numParticipantes; i++){
    		
    		n = (int)patron + n;
    		if (n >= numParticipantes){
    			// si el patron es mayor que el numero de participantes
    			// se inicializa con el valor del primer participante que no esté asignado
    			n = buscarSigLibre(lParticipante, lParticipante[i].getNombre());
    		}
    		
    		if (!"S".equals(lParticipante[n].getLibre()) |
    			lParticipante[i].getNombre() == lParticipante[n].getNombre()){
    			// si ya está asignado (o es él mismo), se busca otro
    			n = buscarSigLibre(lParticipante, lParticipante[i].getNombre());
    		}
    			
    		asignar = true;
    		if (!"".equals(lParticipante[i].getRestringido())){
    			asignar = comprobarRestriccion(lParticipante[i].getRestringido(),lParticipante[n].getId());
    		}
    		if (asignar){
        		lParticipante[i].setEmparejado(lParticipante[n].getNombre());
        		System.out.println (lParticipante[i].getNombre() + " regala a " + lParticipante[i].getEmparejado());
        		lParticipante[n].setLibre("N");
        		contador_asignados ++;    				
    		}else{
    			System.out.println ("a " + lParticipante[i].getNombre() + " no se le puede asignar " + lParticipante[n].getNombre());
    			// repetir mismo empleado para buscarle otro que no tenga restriccion
    			i = i-1;
    		}

    	 }
 
    }
    
    public int buscarSigLibre(Empleado[] lParticipante, String nombre){
    	// busca el primer empleado no asignado que no se llame igual a si mismo
		int z;
		for ( z = 0; z <numParticipantes;z++){
			if("S".equals(lParticipante[(int)z].getLibre()) &
					lParticipante[(int)z].getNombre() != nombre	){
				break;
			}
		}
    	return z;
    }
    
    public boolean comprobarRestriccion(String restriccion, String nombre){
    	
    	boolean permitido = true;
    	if (restriccion == nombre){
    		permitido = false;
    	}
    	
    	return permitido;
    }

}
