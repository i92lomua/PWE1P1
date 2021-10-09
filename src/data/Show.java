package data;
import java.util.*;

public class Show {
	
	/* Attributes */
	private String title, description, category;
	private Integer capacity;
	private Date sesion; 			//Se inicializa asi; date = new Date(int year, int month, int date, int hrs, int min);
	private Vector<Review> reviews;


	/**
	 * Parametrized constructor
	 * @param title Title of the show
	 * @param description Description of the show
	 * @param category Type of show
	 * @param capacity Max num of locations
	 * @param sesion Date of the sesion
	 */


	public Show(){
		// PARA QUE NO LLAME AL SUPER CONSTRUCTOR
	}

	public Show(String title, String description, String category, Integer capacity, Date sesion) {

		this.title = title;
		this.description = description;
		this.category = category;
		this.capacity = capacity;
		this.sesion = sesion;
		this.reviews = new Vector<Review> ();		//podemos hacerlo con vector o como en el el ejercicio sacarlo del fichero directamente
	}

	/* Setters */
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(String category) {
		this.category = category;
	} 

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public void setSesion(Date sesion) {
		this.sesion = sesion;
	}

	public void newReview(Review newReview) {
		reviews.add(newReview);
	}


	/* Getters */
	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public String getCategory() {
		return this.category;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public Date getSesion() {
		return this.sesion;
	}

	public Vector<Review> getReviews() {
		return this.reviews;
	}
}
