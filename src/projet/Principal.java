package projet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Principal {

	public static void main(String[] args) {
		
		trouverNombreClient();

	}
	
	private static int trouverNombreClient(){
		
		try {
			
			FileInputStream fichier = new FileInputStream("Fichier.txt");
			InputStreamReader lecture = new InputStreamReader(fichier);
			BufferedReader buff = new BufferedReader(lecture);
			String[] lignes;
			String ligne;
			int nbLignes = 0;
			
			while ((ligne = buff.readLine()) != null) {
				System.out.println(ligne);
				nbLignes++;
			}
			
			buff.close();
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
			
		}
		
		
		return 0;
	}

}
