package hotel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HotelSerialisation {
	
	public HotelSerialisation() {}
	
	public void sauvegarder(Hotel hotel) {
		ObjectOutputStream sortie;
		try {
			sortie = new ObjectOutputStream(new FileOutputStream("hotel.ser"));
			sortie.writeObject(hotel);
			sortie.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Hotel charger() {
		ObjectInputStream entree;
		Hotel hotel = null;
		try {
			entree = new ObjectInputStream(new FileInputStream("hotel.ser"));
			hotel = (Hotel) entree.readObject();
			entree.close();
		} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return hotel;
	}
}
