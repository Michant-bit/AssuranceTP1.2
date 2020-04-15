package projet;

import java.io.*;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		BufferedReader lecteurAvecBuffer = null;
		String[] fichier = new String[13];
		String[] nom = null;
		String[] plat = null;
		String[] commande = null;
		float[] prix = null;
		int nombre = 0;
		String ligne;
		String ligneFinale = "";

        try {
        	
        	lecteurAvecBuffer = new BufferedReader(new FileReader("test.txt"));
        
        }
        
        catch(FileNotFoundException exc) {
        	
        	System.out.println("Erreur d'ouverture");
        
        }
        
        for (int i = 0; (ligne = lecteurAvecBuffer.readLine()) != null; i++) {
            
        	fichier[i] = ligne;
        			
        }
        
        lecteurAvecBuffer.close();
        
        // -------------------------------------------------------------------
        
        nombre = nbNoms(fichier);
        
        nom = new String[nombre];
        
        for (int i = 0; i < nom.length; i++) {
        	
        	nom[i] = fichier[i + 1];
        	
        }
        
        
        
        // -------------------------------------------------------------------
        
        nombre = nbPlats(fichier, nom);
        
        plat = new String[nombre];
        
        for (int i = 0; i < plat.length; i++) {
        	
        	plat[i] = fichier[i + 5];
        	
        }
        
        // -------------------------------------------------------------------
        
        nombre = nbCommande(fichier, nom, plat);
        
        commande = new String[nombre];
        
        for (int i = 0; i < commande.length; i++) {
        	
        	commande[i] = fichier[i + ((nom.length + 2) + (plat.length + 1))];
        	
        }
        
        // -------------------------------------------------------------------
        
        ligneFinale += "Bienvenue  chez  Barette !\n";
        
        Client client = new Client();
        
        float dernier = 0.00f;
        
        for (int i = 0; i < nom.length; i++) {
        	
        	dernier = 0.00f;
        	
        	for (int y = 0; y < commande.length; y++) {
            	
        		if(commande[y].contains( nom[i] )) {
            		
            		for (int x = 0; x < plat.length; x++) {
                    	
                    	if((plat[x].split( " " ))[0].contains( (commande[y].split( " " ))[1] )) {	
                    		
                    /*		client = new Client(nom[i], (Float.parseFloat(plat[x].split( " " )[1])) * 
                    				(Float.parseFloat(commande[y].split( " " )[2])));
                    		
                    		ligneFinale += client.afficher();
                    		dernier = client.prix;
                    	*/	
        							for (int k = 0; k < nom.length; k++) {
        							
        							if (nom[i].equals(nom[k])) {
									
        							nom[k] = nom[i];
        							
    								float prixTotal = ((Float.parseFloat(plat[x].split( " " )[1])) * 
    	                    				(Float.parseFloat(commande[y].split( " " )[2]))) + ((Float.parseFloat(plat[x].split( " " )[1])) * 
    	                    				(Float.parseFloat(commande[y].split( " " )[2])));
    								
    								client = new Client(nom[k],prixTotal);
    								ligneFinale += client.afficher();
    	                    		dernier = client.prix;
    	                    		
        							}
        						}
        				
                    
                 	
                    	}
            		}
                    	
            		
            
            		if(y == commande.length - 1 && dernier == 0.00f) {
            			
            			client = new Client(nom[i], 0.00f);
                		ligneFinale += client.afficher();
            		}
            		

            	}
            	
            }
        	
        }
        
        String[] fichierFinal = ligneFinale.split( "\n" );
        
        for (int i = 0; i < fichierFinal.length - 1; i++) {
        	
        	if(fichierFinal[i].split( " " )[0] == fichierFinal[i + 1].split( " " )[0]) {
        		
        		fichierFinal[i] = Float.toString(((Float.parseFloat(fichierFinal[i].split( " " )[1]) + 
        				(Float.parseFloat(fichierFinal[i + 1].split( " " )[1])))));
        		fichierFinal[i + 1] = "";
        		
        	}
        }
        
        ligneFinale = "";
        	
        for (int i = 0; i < fichierFinal.length; i++) {
        	
        	ligneFinale += fichierFinal[i] + "\n";
        	
        }
        
        try (FileWriter writer = new FileWriter("Sortie.txt");
       		 BufferedWriter bw = new BufferedWriter(writer)) {

       		bw.write(ligneFinale);

       	} catch (IOException e) {
       		System.err.format("IOException: %s%n", e);
       	}
        	
      }
	
	private static boolean validation(String[] nom, String[] commande, String[] plat) {
		
		boolean valide = true;
		
		for (int i = 0; i < commande.length; i++) {
			
			for (int y = 0; i < nom.length; y++) {
				
				if(!commande[i].split( " " )[0].contains( nom[y] )) {
				
					valide = false;
					
				}
			
			}
			
		}
		
		
		return valide;
		
	}
	
	private static int nbNoms(String[] fichier) {
    	
    	int nombre = 0;
    	
    	for (int i = 1; i < fichier.length; i++) {
    		
    		if(!fichier[i].contains( ":" )) {
    			
    			nombre++;
    			
    		} else {
    			
    			i = fichier.length - 1;
    			
    		}
    		
    		
    	}
    	
    	return nombre;
    	
    }
	
	private static int nbPlats(String[] fichier, String[] nom) {
		
		int nombre = 0;
		
		for (int i = nom.length + 2; i < fichier.length; i++) {
    		
    		if(!fichier[i].contains( ":" )) {
    			
    			nombre++;
    			
    		} else {
    			
    			i = fichier.length - 1;
    			
    		}
    		
    		
    	}
    	
    	return nombre;
		
	}
	
	private static int nbCommande(String[] fichier, String[] nom, String[] plat) {
		
		int nombre = 0;
		
		for (int i = (nom.length + 2) + (plat.length + 2); i < fichier.length; i++) {
    		
    		if(!fichier[i].contains( ":" ) || !fichier[i].equals( "Fin" )) {
    			
    			nombre++;
    			
    		} else {
    			
    			i = fichier.length - 1;
    			
    		}
    		
    		
    	}
    	
    	return nombre;
		
	}
}
