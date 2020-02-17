package projet;

import java.io.*;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		BufferedReader lecteurAvecBuffer = null;
        String ligne;

        try
          {
        lecteurAvecBuffer = new BufferedReader(new FileReader("test.txt"));
          }
        catch(FileNotFoundException exc)
          {
        System.out.println("Erreur d'ouverture");
          }
        while ((ligne = lecteurAvecBuffer.readLine()) != null)
          System.out.println(ligne);
        lecteurAvecBuffer.close();
      }

}
