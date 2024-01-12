package hotel;

import java.util.Date;

public interface IReservationFactory {
	IReservation createReservation(IChambre chambre, Date dateDeb, Date dateFin);
}
