/**
 * A class to represent the Review Manager
 * @author Antonio Lopez Muñoz
 * @author Miguel Angel Contreras Cordoba
 * @author Juan de Dios Medina Bello
 */

 package business;
 import java.io.*;
 import java.nio.file.*;
 import java.util.*;
 import data.*;

 public class ReviewManager {

   /* Attributes and Singleton */
   private static ReviewManager Instance = null;

   private ReviewManager(){}

   /**
    * Function that singleton needs to asure that only one instance is created
    */
   public static ReviewManager getInstance() {
      
      if(Instance == null)
         Instance = new ReviewManager();
      
         return Instance;
   }

   /**
    * General function that select the correct path of the file we need to use
    * @param fileName This is the name of the file the function will search for
    */
   public static Properties readPropertiesFile(String fileName) {
   
      FileInputStream fis = null;
      Properties prop = null;
      try {
         fis = new FileInputStream(fileName);
         prop = new Properties();
         prop.load(fis);
      } catch(FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      } catch(IOException ioe) {
         ioe.printStackTrace();
      }
      
      try {
         fis.close();
      } catch(IOException ioe2) {
         ioe2.printStackTrace();
      }
      

      return prop;
   }

   /**
    * Function that add a new Spectator to the users database
    * @param name Person`s name
    * @param surname Person`s surname
    * @param nickname  Person`s nickname
    * @param email Person`s email
    */

   public void signUp(String name,String surname,String nickname,String email){
      Spectator spectator = new Spectator(name,surname,nickname,email);
      
      FileWriter fichero = null;
      PrintWriter pw = null;
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("usersFilePath"));
      try
      {
         if(f.exists()){
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
               String line = scan.nextLine();
               String[] tokens = line.split(",");

               if(tokens.length != 4){
                  throw new IllegalArgumentException();
               }else{
                  if(tokens[2].equals(nickname)){
                     System.out.println("ERROR That nickname is already used, please try another");
                     return;
                  }
               }
            }
            scan.close();
         }

         fichero = new FileWriter(f,true);
         pw = new PrintWriter(fichero);
         
         pw.println(spectator.getName() + "," + spectator.getSurname() + "," + spectator.getNickname() + "," + spectator.getEmail());
         pw.close();
         System.out.println("Usuario Creado con Exito!");
         
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
         try {
         if (null != fichero)
            fichero.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }      
   }

   /**
    * Function to remove an user from the users database
    * @param nickname User`s nickname
    */
   public void singOut(String nickname){
      PrintWriter pw = null;
      FileWriter fichero = null;
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("usersFilePath"));
      Boolean exist = false;
      
      try
      {
         if(f.exists()){
            Scanner scan = new Scanner(f);
            Path path = Paths.get(prop.getProperty("usersFilePath"));
            Long lines = Files.lines(path).count();
            Integer intLines = Math.toIntExact(lines);
            Vector<String> aux = new Vector<String>(intLines);

            while(scan.hasNextLine()){
               String line = scan.nextLine();
               String[] tokens = line.split(","); 

               if(tokens[2].equals(nickname)){
                  // Si coincide no los copiamos
                  exist = true;
               }else{
                  aux.add(line);                  
               }
            }
               fichero = new FileWriter(f,false);
               pw = new PrintWriter(fichero);
               
               for(int i=0; i<aux.size(); i++){
                  pw.println(aux.get(i));
               }
               if(exist == false){
                  System.out.println("ERROR the user with that nickname is not registered!");
               }else {

                  System.out.println("Usuario Borrado con Exito!");
               }
         
            pw.close();
            scan.close();
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try {
            if (null != fichero)
               fichero.close();
            } catch (Exception e2) {
               e2.printStackTrace();
            }
      }
   }

   /**
    * Function that allow us to check all the users database
    */
   public void checkUsersInfo(){
      
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("usersFilePath"));

      try
      {
         if(f.exists()){
            Scanner scan = new Scanner(f);

            System.out.println("<----------------> Lista Usuarios <--------------->");
            while(scan.hasNextLine()){
               String line = scan.nextLine();
               System.out.println(line);
            }
            System.out.println("<------------------------------------------------->\n");
            scan.close();
         }
      }catch(Exception e){
         e.printStackTrace();
      }

   }

   /**
    * Function that allow us to modificate an users information by his nickname
    * @param nickname User`s nickname
    */
   public void modUser(String nickname){
      PrintWriter pw = null;
      FileWriter fichero = null;
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("usersFilePath"));
      
      try
      {
         if(f.exists()){
            Scanner scan = new Scanner(f);
            Path path = Paths.get(prop.getProperty("usersFilePath"));
            Long lines = Files.lines(path).count();
            Integer intLines = Math.toIntExact(lines);
            Vector<String> aux = new Vector<String>(intLines);
            Boolean exist = false;

            while(scan.hasNextLine()){
               String line = scan.nextLine();
               String[] tokens = line.split(","); 

               if(tokens[2].equals(nickname)){
                  // Si coincide no los copiamos (Lo borramos)
                  exist = true;
               }else{
                  aux.add(line);                  
               }
            }
               fichero = new FileWriter(f,false);
               pw = new PrintWriter(fichero);
               
               for(int i=0; i<aux.size(); i++){
                  pw.println(aux.get(i));
               }
               
               if(exist == false){
                  System.out.println("El Usuario con ese nickname no esta registrado!");
               }else{
                  String name,surname,newNickname,email;
                  InputStreamReader isr = new InputStreamReader(System.in);
                  BufferedReader br = new BufferedReader(isr);
                  
                  System.out.println("Introduzca Nuevos Datos:");
                  System.out.print("Nombre -- ");
                  name = br.readLine();

                  System.out.print("Apellidos -- ");
                  surname = br.readLine();

                  System.out.print("Nuevo Nickname -- ");
                  newNickname = br.readLine();

                  System.out.print("Email -- ");
                  email = br.readLine();

                  Spectator spectator = new Spectator(name,surname,nickname,email);
                  pw.println(spectator.getName() + "," + spectator.getSurname() + "," + newNickname + "," + spectator.getEmail());
                  pw.close();
                  System.out.println("Usuario Modificado con Exito!");
               
               }
               scan.close();
               
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try {
            if (null != fichero)
               fichero.close();
            } catch (Exception e2) {
               e2.printStackTrace();
            }
      }
   }

   /**
    * Function to add a new review to the reviews database
    * @param title Film`s title
    * @param personal_score Personal punctuation from the user making the review
    * @param review User`s review
    * @param nickname User`s nickname
    */
   public void newReview(String title, double personal_score, String review,String nickname){
      Review newReview = new Review(title,personal_score, review,nickname);
      Boolean exists = false;
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("usersFilePath"));
      
      try
      {
         if(f.exists()){
            Scanner scan = new Scanner(f);

            while(scan.hasNextLine()){
               String line = scan.nextLine();
               String[] tokens = line.split(","); 

               if(tokens[2].equals(nickname)){
                  // Si coincide hacemos la critica
                  exists = true;
                  break;
               }
            }
            if(exists == true){
               
               FileWriter fichero = null;
               PrintWriter pw = null;
               File f2 = new File(prop.getProperty("reviewsFilePath"));
               try
               {
                  if(f2.exists()){
                     Scanner scan2 = new Scanner(f2);
                     while(scan2.hasNextLine()){
                        String line2 = scan2.nextLine();
                        String[] tokens2 = line2.split(",");
                        if(tokens2.length != 7){
                           throw new IllegalArgumentException();
                        }else{
                           if(tokens2[0].equals(title) && tokens2[4].equals(nickname)){
                              System.out.println("ERROR That title is already used, please try another");
                              return;
                           }
                        }
                     }
                     scan2.close();
                  }
               
                  fichero = new FileWriter(f2,true);
                  pw = new PrintWriter(fichero);

                  pw.println(newReview.getTittle() + "," + newReview.getPScore() + "," + newReview.getReview() + "," + newReview.getOScore() + "," + nickname + ",0.0,0.0,");
                  pw.close();
               
               } catch (Exception e) {
                     e.printStackTrace();
               } finally {
                  try {
                  if (null != fichero)
                     fichero.close();
                  } catch (Exception e2) {
                     e2.printStackTrace();
                  }
               }

            }else{
               System.out.println("ERROR the user was not found in our system!");
            }
            scan.close();
         }
      }catch(Exception e){
         e.printStackTrace();
      }      
   }

   /**
    * Function to remove a review from the reviews database
    * @param title Title of the movie/serie we want to remove
    * @param nickname Nickname of the user that made that review
    */
   public void eraseReview(String title,String nickname){
      PrintWriter pw = null;
      FileWriter fichero = null;
      Boolean exists = false;
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("usersFilePath"));

      try{

            if(f.exists()){
               Scanner scan = new Scanner(f);

               while(scan.hasNextLine()){
                  String line = scan.nextLine();
                  String[] tokens = line.split(",");

                  if(tokens[2].equals(nickname)){
                     exists = true;
                     break;
                  }
               }

               File f2 = new File(prop.getProperty("reviewsFilePath"));
               Scanner scan2 = new Scanner(f2);
               Path path = Paths.get(prop.getProperty("reviewsFilePath"));
               Long lines = Files.lines(path).count();
               Integer intLines = Math.toIntExact(lines);
               Vector<String> aux = new Vector<String>(intLines);
               
               while(scan2.hasNextLine()){
                  String line2 = scan2.nextLine();
                  String[] tokens2 = line2.split(",");

                  if(tokens2[0].equals(title) && exists == true){
                     // BORRAR LA RESEÑA NO AÑADIENDOLA AL VECTOR
                     exists = true;
                  }else{
                     aux.add(line2);
                  }
               }

               fichero = new FileWriter(f2,false);
               pw = new PrintWriter(fichero);
               
               for(int i=0; i<aux.size(); i++){
                  pw.println(aux.get(i));
               }
               if(exists == false){
                  System.out.println("ERROR the user with that nickname does not match any film with that title!");
               }else {

                  System.out.println("Reseña Borrada con Exito!");
               }
               
            pw.close();
            scan.close();
            scan2.close();

            }

      }catch(Exception e){
         e.printStackTrace();
      }

   }

   /**
    * Function that allow us to check all the reviews database
    */
   public void checkReviewInfo(){
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("reviewsFilePath"));

      try
      {
         if(f.exists()){
            Scanner scan = new Scanner(f);

            System.out.println("<----------------> Lista Reviews <--------------->");
            while(scan.hasNextLine()){
               String line = scan.nextLine();
               System.out.println(line);
            }
            System.out.println("<------------------------------------------------->\n");
            scan.close();
         }
      }catch(Exception e){
         e.printStackTrace();
      }
   }

   /**
    * Function that allow us to search one movie/serie from the reviews database
    * @param title Name of the movie/serie we want to search for
    */
   public void searchReviews(String title){
      FileWriter fichero = null;
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("reviewsFilePath"));
      
      try
      {
         if(f.exists()){
            Scanner scan = new Scanner(f);

            while(scan.hasNextLine()){
               String line = scan.nextLine();
               String[] tokens = line.split(","); 

               if(tokens[0].equals(title)){
                  //Mostramos la linea
                  System.out.println("Titulo -- " + title);
                  System.out.println("Puntuacion -- " + tokens[1]);
                  System.out.println("Reseña -- " + tokens[2]);
                  System.out.println("Puntuacion Reseña -- " + tokens[3]);
                  return;
               }else{
                  System.out.println("ERROR the title was not found in our system!");
               }
            }
            scan.close();
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try {
            if (null != fichero)
               fichero.close();
            } catch (Exception e2) {
               e2.printStackTrace();
            }
      }
   }

   /**
    * Function that allow us to search for one user from users database by his nickname
    * @param nickname User`s nickname 
    */
   public void searchUser(String nickname){ 
      FileWriter fichero = null;
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("usersFilePath"));
      
      try
      {
         if(f.exists()){
            Scanner scan = new Scanner(f);

            while(scan.hasNextLine()){
               String line = scan.nextLine();
               String[] tokens = line.split(","); 

               if(tokens[2].equals(nickname)){
                  //Mostramos la linea
                  System.out.println("Se ha encontrado a" + nickname + ": ");
                  System.out.println("Nombre -- " + tokens[0]);
                  System.out.println("Apellidos -- " + tokens[1]);
                  System.out.println("Nickname -- " + nickname);
                  System.out.println("Email -- " + tokens[3]);
                  return;
               }else{
                  System.out.println("ERROR the user was not found in our system!");
               }
            }
            scan.close();
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try {
            if (null != fichero)
               fichero.close();
            } catch (Exception e2) {
               e2.printStackTrace();
            }
      }
   }

   /**
    * Function that allow us to make a vote to others people review
    * @param title Title of the movie/serie
    * @param nickname Nickname of the person that wants to vote the others reviews
    */
   public void voteReview(String title, String nickname){
      
      PrintWriter pw = null;
      FileWriter fichero = null;
      Properties prop = readPropertiesFile("resources/file.properties");
      File f = new File(prop.getProperty("reviewsFilePath"));

      //Cuenta el numero de lineas del fichero y crea un vector con ese tamaño
      Path path = Paths.get(prop.getProperty("reviewsFilePath"));
      try {
         Long lines = Files.lines(path).count();
         Integer intLines = Math.toIntExact(lines);
         Vector<String> auxV = new Vector<String>(intLines);
         Boolean exists = false;

   
         //inicializa el buffer de lectura por teclado
         BufferedReader bf = new BufferedReader (new InputStreamReader(System.in));

         //Muestra todas las reviews del titulo introducido
         System.out.println("");
         searchReviews(title);
         System.out.println("");

         System.out.println("Introduce el nickname del usuario para votar su critica:");
         String read = bf.readLine();
            
         if(f.exists()){
            Scanner scan = new Scanner(f);
            
            while(scan.hasNextLine()){
               String line = scan.nextLine();
               String[] tokens = line.split(",");
               if(tokens.length != 7){
                  throw new IllegalArgumentException();
               }else
                  if(tokens[0].equals(title) && tokens[4].equals(read) && exists == false){
                     exists = true;
                     System.out.println("Introduce la puntuacion entre 0-10:");
                     read = bf.readLine();
                     double newScore = Double.parseDouble(read);
                     double nScores = Double.parseDouble(tokens[5]);
                     double totalScore = Double.parseDouble(tokens[6]);
                     double scoreAvg = Double.parseDouble(tokens[3]);
                     
                     totalScore += newScore;
                     nScores++;
                     scoreAvg = totalScore/nScores;

                     String sts = String.valueOf(totalScore);
                     String sns = String.valueOf(nScores);
                     String ssa = String.valueOf(scoreAvg);

                     String auxLine = new String(tokens[0] + "," + tokens[1] + "," + tokens[2] + "," + ssa + "," + tokens[4] + "," + sns + "," + sts);
                     auxV.add(auxLine);
                  }
                  else {
                     auxV.add(line);
                  }
               }
               scan.close();
            }
            
            if(exists == false){
               System.out.println("That nickname does not have any reviews registered with that title");
               return;
            }
            else{
               fichero = new FileWriter(f,false);
               pw = new PrintWriter(fichero);
               for(int i = 0; i < auxV.size(); i++){
                  pw.println(auxV.get(i));
               }
            
               System.out.println("Se ha añadido su nota");
            }
         
      } catch (IOException e) {
      e.printStackTrace();
      }
   }


   
}