package serverpck;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface Server extends Remote {
	/**
	 * 
	 * Returns a list of strings containing data about all the registered flights on
	 * the server
	 * 
	 * @return A list of strings @
	 */

	public List<String> getFlights();

	/**
	 * Returns a list of strings containing data about all the registered two way
	 * flights that matches the given parameters
	 * 
	 * @param to
	 * @param from
	 * @param fftimestamp first flight time
	 * @param sftimestamp return flight time
	 * @param seats       number of seats available on the flight
	 * @return List of strings containg flight data @
	 */

	public List<String> getFlights(String to, String from, long fftimestamp, long sftimestamp, int seats);

	/**
	 * Returns a list of strings containing data about all the registered one way
	 * flights that matches the given parameters
	 * 
	 * @param to
	 * @param from
	 * @param fftimestamp first flight time
	 * @param seats       number of seats available on the flight
	 * @return List of strings containg flight data @
	 */
	public List<String> getFlights(String to, String from, long fftimestamp, int seats);

	/**
	 * Returns a list of strings containing data about all the hotels registered on
	 * the server
	 * 
	 * @return A list of strings with hotel data @
	 */
	public List<String> getHotels();

	/**
	 * Returns data about all packages registered by the server
	 * 
	 * @return List of strings containing all data @
	 */
	public List<String> getPackages();

	/**
	 * Adds a hotel to the server
	 * 
	 * @param id      server-side id
	 * @param name    Name of the hotel
	 * @param where   location
	 * @param rooms   number of rooms
	 * @param roomcap number of people that a room can accomodate
	 * @param price   Price for the room @
	 */
	public void addHotel(int id, String name, String where, int rooms, int roomcap, int price);

	/**
	 * Adds a flight to the server
	 * 
	 * @param id          serverside id
	 * @param seats       how many seats are available
	 * @param to          destination
	 * @param from        flight's home airport
	 * @param fftimestamp take-off time
	 * @param price @
	 */
	public void addFlight(int id, int seats, String to, String from, long fftimestamp, int price);

	/**
	 * Sells a number of seats on the flight
	 * 
	 * @param id    serverside id of the flight
	 * @param seats number of seats to be purchased
	 * @return true if the transaction was successfull @
	 */
	public boolean sellFlight(int id, int seats);

	/**
	 * Sells a number of rooms on a hotel
	 * 
	 * @param id        serverside id of the hotel
	 * @param seats     how many people
	 * @param startdate check in date
	 * @param enddate   check in timestamp
	 * @return true if the transaction succeeds, false otherwise @
	 */
	public boolean sellRooms(int id, int seats, long startdate, long enddate);

	/**
	 * Registers a flight interest
	 * 
	 * @param to
	 * @param from
	 * @param maxprice
	 * @param client   Client name-service name
	 * @return @
	 */

	public boolean sellPackage(int flightid, int hotelid, int seats, long startdate, long enddate);

	/**
	 * Registers a room interest
	 * 
	 * @param where    hotel location
	 * @param seats    how many people
	 * @param maxprice
	 * @param client   Client name-service name
	 * @return @
	 */

	void addPackage(int idp, int id, int seats, String to, String from, int price, long fftimestamp, int idh,
			String name, String where, int roomcap);

	/**
	 * Adds a package by combining a flight and a hotel
	 * 
	 * @param idp   package id
	 * @param idf   flight id
	 * @param idh   hotel id
	 * @param seats how many people
	 * @param price package price @
	 */
	void addPackage(int idp, int idf, int idh, int seats, int price);

	/**
	 * Adds a package interest
	 * 
	 * @param to       destination
	 * @param from     home
	 * @param where    hotel location
	 * @param maxprice package max price
	 * @param seats    how many people
	 * @param client   client name-service identifier @
	 */
	

}
