package hotel;

import java.util.Date;

public interface IReservation {
	
	IChambre getChambre();
	
	void setReservation(Date dateDebut, Date dateFin, IChambre chambre);
	
	Date getStartDate();
	
	Date getEndDate();
}
