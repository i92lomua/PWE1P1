package business;
import java.util.*;

public class ShowManager {

	public void logIn(String nickname) {

	}
	
	public void newShow(String title, String description, String category, Integer capacity, Date sesion) {

	}

	public void cancelShow(String title, Date sesion) {

	}

	public void modShow(String title, Date sesion) { //siempre vamos a usar titilo y sesion para identificar un espectaculo ya que puede haber dos espectaculos con el mismo nombre y distinta fecha

	}

	public void seatsOnSale(String title, Date sesion) { //nos dice el numero de entrada que hay disponibles para un show

	}
	
	public void searchByTitle(String title) { //muestra todas las sesiones para ese titulo

	}

	public void searchByCategory(String category) { //muestra todos los shows en esa categoria
	
	}

	public void seachNear(Boolean category) { //muestra los shows mas proximos con entradas disponibles (por ejemplo en los proximos 3 dias). el boolean es para que indique si quiere o no indicar la categoria de los espectaculos
	
	}

	public void publishReview(String title, Date sesion) {	//el espectaculo se debe haber celebrado

	}

	public void showReviews(String title) {	//las reviews son idependientes de la sesion ya que el show es el mismo
	
	}

	public void voteReview(String title) {

	}

}
