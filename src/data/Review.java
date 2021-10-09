/**
 * A Class to represent a Review
 * @author Antonio Lopez Muñoz
 * @author Miguel Angel Contreras Cordoba
 * @author Juan de Dios Medina Bello
 */
package data;
public class Review {
    
    /* Attributes */
    private String tittle;
    private double personal_score;
    private String review;
    private double others_score;
    private int nVotes;
    private double sumVotes;

    /**
     * Parametrized Constructor
     * @param tittle The tittle of the review
     * @param score The score for the review
     * @param review The review 
     */
    public Review(String tittle,double score,String review,String nickname){
        this.tittle = tittle;
        this.personal_score = score;
        this.review = review;
        this.others_score = 0;
        this.nVotes = 0;
        this.sumVotes = 0;
    }

    /* Setters */
    public void setTittle(String tittle){
        this.tittle = tittle; 
    }

    public void setPScore(double score){
        this.personal_score = score;
    }

    public void setReview(String review){
        this.review = review;
    }

    public void setOScore(double score){
        this.others_score = score;
    }

    public void addnVotes(){
        this.nVotes++;
    }

    public void addsumVotes(double sumVotes){
        this.sumVotes+=sumVotes;
    }

    /* Getters */
    public String getTittle(){
        return this.tittle;
    }

    public double getPScore(){
        return this.personal_score;
    }

    public String getReview(){
        return this.review;
    }

    public double getOScore(){
        return this.others_score;
    }

    public int getnVotes(){
        return this.nVotes;
    }

    public double getsumVotes(){
        return this.sumVotes;
    }
}
