/**
 * A Class to represent the main App.java 
 * @author Antonio Lopez Muñoz
 * @author Miguel Angel Contreras Cordoba
 * @author Juan de Dios Medina Bello
 */
package display;
import java.io.*;

import business.*;

public class App {
    public static void main(String[] args) throws Exception {
        ReviewManager rm = ReviewManager.getInstance();
        Boolean exit = false;
        String name,surname,nickname,email,title,review;
        double punt;

       do{

            System.out.println("<----------------> Menu <----------------->");
            System.out.println("1.- Nuevo Espectador");
            System.out.println("2.- Eliminar Espectador");
            System.out.println("3.- Modificar Espectador");
            System.out.println("4.- Nueva Critica");
            System.out.println("5.- Eliminar Critica");
            System.out.println("6.- Mostrar lista Usuarios");
            System.out.println("7.- Mostrar lista Criticas");
            System.out.println("8.- Buscar Espectador");
            System.out.println("9.- Buscar Critica");
            System.out.println("10.- Votar critica");
            System.out.println("ESC.- Salir");

            BufferedReader bf = new BufferedReader (new InputStreamReader(System.in));
            String option = bf.readLine();

            System.out.print("\033[H\033[2J"); // Para limpiar la consola
            switch(option){
            
             case "1":
                     System.out.println("Introduzca:");
                     System.out.print("Nombre -- ");
                     name = bf.readLine();
                     System.out.print("Apellidos -- ");
                     surname = bf.readLine();
                     System.out.print("Nickname -- ");
                     nickname = bf.readLine();
                     System.out.print("Email -- ");
                     email = bf.readLine();
 
                     rm.signUp(name, surname, nickname, email);
                     break;
             case "2":
                     System.out.println("Introduzca:");
                     System.out.print("Nickname -- ");
                     nickname = bf.readLine();
                     
                     rm.singOut(nickname);

                     break;
             case "3":
                     System.out.print("Nickname -- ");
                     nickname = bf.readLine();
                     
                     rm.modUser(nickname);
                     break;
             case "4":
                     System.out.print("Nickname --");
                     nickname = bf.readLine();
                     System.out.print("Titulo --");
                     title = bf.readLine();
                     System.out.print("Puntuacion --");
                     String aux= bf.readLine();
                     punt = Double.parseDouble(aux);
                     System.out.print("Reseña --");
                     review = bf.readLine();
                     
                     rm.newReview(title, punt, review, nickname);
                     break;
             case "5":
                     System.out.println("Introduzca:");
                     System.out.print("Nickname -- ");
                     nickname = bf.readLine();
                     System.out.print("Titulo -- ");
                     title = bf.readLine();
 
                     rm.eraseReview(title, nickname);
                     break;
             case "6":
                     rm.checkUsersInfo();
                     break;
             case "7":
                     rm.checkReviewInfo();
                     break;
             case "8":
                     System.out.print("Nickname -- ");
                     nickname = bf.readLine();
                     rm.searchUser(nickname);
                     break;
            
             case "9":
                     System.out.print("Titulo -- ");
                     title = bf.readLine();
                     rm.searchReviews(title);
                     break;
             case "10":
                        System.out.println("Funcion no disponible, disculpe las molestias");
                        /*System.out.println("Introduzca:");
                        System.out.print("Su nickname -- ");
                        nickname = bf.readLine();
                        System.out.print("Titulo -- ");
                        title = bf.readLine();

                        rm.voteReview(title, nickname);*/
                     break;
             case "\033":
                     exit = true;
                     break;
            }
       }while(exit == false);
    }
} 
