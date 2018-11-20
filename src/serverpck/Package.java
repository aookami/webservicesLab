package serverpck;

public class Package {

	int packageId;
	int seats;
	int price;
	Flight flight;
	Hotel hotel;
	
	Package(int idp, int idf,int seats, String to, String from, int price,
			long fftimestamp, int idh, String name,  String where, int roomcap ){
		
			flight = new Flight(idf, seats, to, from, fftimestamp, price);
			hotel = new Hotel( idh, name,  where,  seats/roomcap, roomcap, price);
			this.price = price;
			this.packageId = idp;
			this.seats = seats;
		
	}
	
	Package(int id, Flight flight, Hotel hotel, int price, int seats){
		
		this.hotel = hotel;
		this.flight = flight;
		this.packageId = id;
		this.price = price;
		this.seats = seats;
	}
}
