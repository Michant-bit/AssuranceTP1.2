package projet;

import java.io.*;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		BufferedReader lecteurAvecBuffer = null;
		String[] fichier = new String[13];
		String ligne;
        int nb = 0;

        try {
        	
        	lecteurAvecBuffer = new BufferedReader(new FileReader("test.txt"));
        
        }
        
        catch(FileNotFoundException exc) {
        	
        	System.out.println("Erreur d'ouverture");
        
        }
        
        for (int i = 0; (ligne = lecteurAvecBuffer.readLine()) != null; i++) {
            
        	fichier[i] = ligne;
        	System.out.println( fichier[i] );
        			
        }
        
        	lecteurAvecBuffer.close();
        	
      }
}
