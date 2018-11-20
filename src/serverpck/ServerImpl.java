package serverpck;

import java.rmi.NotBoundException;
import java.rmi.Remote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl  implements Server {
	
	//All commentaries are in the interface file*********************************

	protected ServerImpl()  {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static List<Flight> flights = new ArrayList<>();

	static List<Hotel> hotels = new ArrayList<>();

	static List<Package> packages = new ArrayList<>();

	@Override
	public List<String> getFlights()  {
		System.out.println("Answering flight data request");
		List<String> newL = new ArrayList<>();

		for (Flight x : flights) {
			newL.add(x.id + " -To: " + x.getTo() + " -From: " + x.getFrom() + " -FlightTime: " + x.getFftimestamp() + " -Price: " + x.getPrice()
					+ " -SeatsRemaining: " + x.getSeats());
		}
		return newL;
	}

	@Override
	public List<String> getPackages()  {
		System.out.println("Answering packages data request");
		List<String> newL = new ArrayList<>();

		for (Package x : packages) {
			newL.add(x.packageId + " -To: " + x.flight.to + " -From: " + x.flight.from + " -FlightTime: " + x.flight.fftimestamp + " -Where: "
					+ x.hotel.where + " -Name: " + x.hotel.name + " -RoomCapacity: " + x.hotel.roomcapacity + " -AvailableRooms "
					+ x.hotel.getAvailablerooms() + " -Price " + x.price + " -Seats " + x.seats);
		}
		return newL;
	}

	@Override
	public List<String> getFlights(String to, String from, long fftimestamp, int seats)  {
		System.out.println("Answering flight data request");

		List<String> newL = new ArrayList<>();

		for (Flight x : flights) {
			if (x.twoway == true)
				if (x.getTo().equals(to) && x.getFrom().equals(from) && x.getFftimestamp() == fftimestamp
						&& x.seats > seats)
					newL.add(x.id + " -To: " + x.getTo() + " -From: " + x.getFrom() + " -FlightTime: " + x.getFftimestamp() + " -Price: "
							+ x.getPrice() + " -Seats: " + x.getSeats());
		}
		return newL;
	}

	@Override
	public List<String> getFlights(String to, String from, long fftimestamp, long sftimestamp, int seats)
			 {
		System.out.println("Answering flight data request");

		List<String> newL = new ArrayList<>();

		for (Flight x : flights) {
			if (x.twoway == true)
				if (x.getTo().equals(to) && x.getFrom().equals(from) && x.getFftimestamp() == fftimestamp
						&& x.getSftimestamp() == sftimestamp && x.seats > seats)
					newL.add(x.id + " - " + x.getTo() + " - " + x.getFrom() + " - " + x.getFftimestamp() + " - "
							+ x.getPrice() + " - " + x.getSeats());
		}
		return newL;
	}

	@Override
	public List<String> getHotels()  {
		System.out.println("Answering hotels data request");

		List<String> newL = new ArrayList<>();

		for (Hotel x : hotels) {
			newL.add(x.id + " - " + x.getName() + " - " + x.getWhere() + " - " + x.getAvailablerooms() + " - "
					+ x.getRoomcapacity());

		}
		return newL;

	}

	@Override
	public void addHotel(int id, String name, String where, int rooms, int roomcap, int roomprice)
			 {
		System.out.println("Adding new hotel...");

		Hotel newHotel = new Hotel(id, name, where, rooms, roomcap, roomprice);

		hotels.add(newHotel);

	

	}

	@Override
	public void addFlight(int id, int seats, String to, String from, long fftimestamp, int price)
			 {
		System.out.println("Adding new flight...");

	
		Flight newFlight = new Flight(id, seats, to, from, fftimestamp, price);
		flights.add(newFlight);
	}

	@Override
	public void addPackage(int idp, int id, int seats, String to, String from, int price, long fftimestamp, int idh,
			String name, String where, int roomcap)  {
		System.out.println("Added package!");
		Package pack = new Package(idp, id, seats, to, from, price, fftimestamp, idh, name, where, roomcap);
		packages.add(pack);
	}

	@Override
	public void addPackage(int idp, int idf, int idh, int seats, int price)  {
		System.out.println("Added package!");
		Flight y = null;
		for (Flight x : flights) {
			if (x.id == idf)
				y = x;
		}

		Hotel z = null;
		for (Hotel h : hotels) {
			if (h.id == idh)
				z = h;
		}
	
		Package packagen = new Package(idp, y, z, seats, price);
		packages.add(packagen);
	}


	


	public void removeFlight(int id)  {
		System.out.println("Removing flight" + id + "...");
		for( Flight x : flights) {
			if (x.id == id) {
				flights.remove(x);
				System.out.println("Removed flight " + x.id);
			}
		}

	}
	public void removeHotel(int id)  {
		System.out.println("Removing hotel" + id + "...");
		for( Hotel x : hotels) {
			if (x.id == id) {
				hotels.remove(x);
				System.out.println("Removed hotel " + x.id);
			}
		}

	}
	public void removePackage(int id)  {
		System.out.println("Removing package" + id + "...");
		for( Package x : packages) {
			if (x.packageId == id) {
				packages.remove(x);
				System.out.println("Removed package " + x.packageId );
			}
		}

	}

	@Override
	public synchronized boolean sellFlight(int id, int seats)  {
		System.out.println("Selling " + seats + " seats on flight " + id + "...");

		for (Flight x : flights) {
			if (x.id == id) {
				if (x.seats < seats) {
					return false;
				}
				x.seats = x.seats - seats;
				return true;
			}
		}
		return false;

	}

	@Override
	public synchronized boolean sellRooms(int id, int seats, long startdate, long enddate)  {
		System.out.println(
				"Selling " + seats + " rooms on hotel " + id + " start and end dates: " + startdate + " - " + enddate);

		for (Hotel x : hotels) {
			if (x.getAvailablerooms() > seats && x.id == id) {
				int rseats = seats;
				for (Room y : x.getRooms()) {
					if (y.is_occupied == false) {
						rseats = rseats - y.size;
					}
					if (rseats <= 0)
						break;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean sellPackage(int flightid, int hotelid, int seats, long startdate, long enddate) {
		System.out.println("Selling " + seats + " seats on flight " + flightid + "...");

		for (Flight x : flights) {
			if (x.id == flightid) {
				if (x.seats < seats) {
					return false;
				}
				x.seats = x.seats - seats;
				return true;
			}
		}
		
		System.out.println(
				"Selling " + seats + " rooms on hotel " + hotelid + " start and end dates: " + startdate + " - " + enddate);

		for (Hotel x : hotels) {
			if (x.getAvailablerooms() > seats && x.id == hotelid) {
				int rseats = seats;
				for (Room y : x.getRooms()) {
					if (y.is_occupied == false) {
						rseats = rseats - y.size;
					}
					if (rseats <= 0)
						break;
				}
				return true;
			}
		}
		return false;
		
	}




}
