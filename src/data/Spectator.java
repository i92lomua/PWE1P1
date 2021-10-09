/**
 * A Class to represent an Spectator
 * @author Antonio Lopez Muñoz
 * @author Miguel Angel Contreras Cordoba
 * @author Juan de Dios Medina Bello
 */
package data;
public class Spectator {
    
    /* Attributes */
    private String name; 
    private String surname;
    private String nickname;
    private String email;

    /* Constructors */

    /**
     * Default constructor
     */
    public Spectator() {
        this.name = "NONE";
        this.surname = "NONE";
        this.nickname = "NONE";
        this.email = "NONE";
    }

    /**
     * Parametrized constructor
     * @param name the name of the person
     * @param surname the surname of the person
     * @param nickname the nickname of the person
     * @param email the email of the person
     */
    public Spectator(String name,String surname,String nickname,String email) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.email = email;
    }

    /* Setters */
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    /* Getters */
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
