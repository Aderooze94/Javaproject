package reservation;

import java.io.Serializable;
import java.util.Date;

import hotel.IChambre;
import hotel.IReservation;

public class Reservation implements IReservation, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dateDeb;
	private Date dateFin;
	private IChambre chambre;
	
	public Reservation(Date dateDeb, Date dateFin, IChambre chambre) {
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.chambre = chambre;
	}
	
	public Date getStartDate() {
		return dateDeb;
	}
	
	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}
	
	public Date getEndDate() {
		return dateFin;
	}
	
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	public IChambre getChambre() {
		return chambre;
	}
	
	public void setChambre(IChambre chambre) {
		this.chambre = chambre;
	}
	
	public void setReservation(Date dateDeb, Date dateFin, IChambre chambre) {
		if(this.chambre != chambre)
			setChambre(chambre);
		if(this.dateDeb != dateDeb)
			setDateDeb(dateDeb);
		if(this.dateFin != dateFin)
			setDateFin(dateFin);
	}

}
