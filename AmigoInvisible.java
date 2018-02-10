package org.gradle;

public class AmigoInvisible {
	static String archivo;
	
	public AmigoInvisible(String a){
		archivo = a;
	}
	
	public static void ejecucion(){
		Funciones funciones = new Funciones(); 
		// cargar lista de empleados/participantes
		Empleado[] lParticipante = funciones.cargaParticipantes(archivo);
		
		if(null != lParticipante & lParticipante.length > 0){
			if (lParticipante.length == 1 || lParticipante.length == 2){
				System.out.println ("no hay empleados suficientes");
			}else {
				// realizar sorteo si hay mas de 2 empleados participando
				funciones.repartir(lParticipante);
			}
		}
	}
}
