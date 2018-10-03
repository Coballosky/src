package Comun;

import java.io.*;


public class Metodos {
	public boolean validarRut(String vrut, String vverificador) 
    { 
        boolean flag = false; 
        String rut = vrut.trim(); 

        String posibleVerificador = vverificador.trim(); 
        int cantidad = rut.length(); 
        int factor = 2; 
        int suma = 0; 
        String verificador = ""; 

        for(int i = cantidad; i > 0; i--) 
        { 
            if(factor > 7) 
            { 
                factor = 2; 
            } 
            suma += (Integer.parseInt(rut.substring((i-1), i)))*factor; 
            factor++; 
        } 
        verificador = String.valueOf(11 - suma%11); 
        if(verificador.equals(posibleVerificador)) 
        { 
            flag = true; 
        } 
        else 
        { 
            if((verificador.equals("10")) && (posibleVerificador.toLowerCase().equals("k"))) 
            { 
                flag = true; 
            } 
            else 
            { 
                if((verificador.equals("11") && posibleVerificador.equals("0"))) 
                { 
                    flag = true; 
                } 
                else 
                { 
                    flag = false; 
                } 
            } 
        } 
        return flag;         
    }
	
	public boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	public boolean IsNumVer(String s) {
		if ( s.equals("k")||s.equals("K") ) {
			return true;
		}else {
			return s != null && s.matches("[-+]?\\d*\\.?\\d+"); 
		}
	}
	
	
/**    ---------------------------------------------------------------------------------          **/
	
	
	public void GuardarSqlConData(String...data)    throws IOException {
		
		File file = new File("c://POO//SqlData.txt");
		
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            	for (int i = 0;i<data.length;i++) {
            		fr.write(data[i]+"\r\n");
            		
            	}
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
            	System.out.println(e);
            }
        }
	}

	
	
	/**           Exceptions  **/


	
	
}
