package reservation;

import java.util.Date;

import hotel.IChambre;
import hotel.IReservationFactory;

public class ReservationFactory implements IReservationFactory {

	@Override
	public Reservation createReservation(IChambre chambre, Date dateDeb, Date dateFin) {
		return new Reservation(dateDeb, dateFin, chambre);
	}

}
