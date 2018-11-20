package serverpck;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/* Neste arquivo estão contidas as anotações que o jersey irá buscar para
 * montar os endpoints.
 * As definições de métodos estão no arquivo ServerImpl, e os comentários na
 * interface Server
 * 
 * O jersey procura as anotações dentro do pacote serverpck
 * Então a partir do endpoint configurado no web.xml, constrói-se os comandos
 * disponíveis no servidor
 * 
 * Por exemplo, o comando getFlights será exposto no endpoint 
 * "http://localhost:8080/ServerD/rest/agt/getflights
 * 
 */

//
@Path("/agt") 
public class ServerMain {

	static ServerImpl serv = new ServerImpl();
	
	/*
	 * Retorna uma lista de objetos Flight, contendo todas as informações sobre
	 * voos cadastrados no servidor
	 */
	@GET
	@Path("/getflights")
	public Response getFlights() {

		String output = serv.getFlights().toString();
		return Response.status(200).entity(output).build();
	}

	/*
	 * Retorna uma lista de objetos Hotel, contendo todas as informações sobre
	 * hotéis cadastrados no servidor
	 */
	@GET
	@Path("/gethotels")
	public Response getHotels() {

		String output = serv.getHotels().toString();
		return Response.status(200).entity(output).build();
	}

	/*
	 * Retorna uma lista de objetos Package, contendo todas as informações sobre
	 * Packages cadastrados no servidor
	 */
	@GET
	@Path("/getpackages")
	public Response getPackages() {

		String output = serv.getPackages().toString();
		return Response.status(200).entity(output).build();
	}
	
	/**
	 * Registra um pacote novo, que terá seu voo e hotel exclusivo 
	 * (independente dos outros já cadastrados)
	 * @param data String contendo a informação necessária para cadastrar um pacote, um hotel e um voo
	 * @return Resposta HTTP apropriada
	 */

	@POST
	@Path("/registerpackage")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registerPackage(String data) {
		serv.addPackage((int) Integer.valueOf(data.split("-")[0]), (int) Integer.valueOf(data.split("-")[1]),
				(int) Integer.valueOf(data.split("-")[2]), data.split("-")[3], data.split("-")[4],
				(int) Integer.valueOf(data.split("-")[5]), (int) Integer.valueOf(data.split("-")[6]),
				(int) Integer.valueOf(data.split("-")[7]), data.split("-")[8], data.split("-")[9],
				(int) Integer.valueOf(data.split("-")[10]));

		return Response.status(200).entity("Registered package").build();
	}

	@POST
	@Path("/registerpackageid")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registerPackageById(String data) {
		serv.addPackage((int) Integer.valueOf(data.split("-")[0]), (int) Integer.valueOf(data.split("-")[1]),
				(int) Integer.valueOf(data.split("-")[2]), (int) Integer.valueOf(data.split("-")[3]),
				(int) Integer.valueOf(data.split("-")[4]));

		return Response.status(200).entity("Registered package").build();
	}

	/**
	 * Registers a new flight on the server
	 * @param msg String containing the flight data
	 * @return HttpResponse
	 */
	@POST
	@Path("/registerflight")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registerFlight(String msg) {
		serv.addFlight(Integer.valueOf(msg.split("-")[0]), Integer.valueOf(msg.split("-")[1]), msg.split("-")[2],
				msg.split("-")[3], Integer.valueOf(msg.split("-")[4]), Integer.valueOf(msg.split("-")[5]));
		return Response.status(200).entity("Registered " + msg).build();
	}

	// int id, String name, String where, int rooms, int roomcap, int roomprice)
	
	/**
	 * Registers a new hotel on the server
	 * 
	 * @param msg String containing the hotel data
	 * @return HttpResponse
	 */
	@POST
	@Path("/registerhotel")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registerHotel(String msg) {
		serv.addHotel((int) Integer.valueOf(msg.split("-")[0]), msg.split("-")[1], msg.split("-")[2],
				(int) Integer.valueOf(msg.split("-")[3]), (int) Integer.valueOf(msg.split("-")[4]),
				(int) Integer.valueOf(msg.split("-")[5]));
		return Response.status(200).entity("Registered" + msg).build();
	}
	/**
	 * Removes a flight from the server
	 * @param msg Id of the flight to be removed
	 * @return HttpResponse
	 */

	@POST
	@Path("/removeflight")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeFlight(String msg) {
		serv.removeFlight((int) Integer.valueOf(msg));
		return Response.status(200).entity("Removed Flighjt" + msg).build();
	}

	
	/**
	 * Removes a hotel from the server
	 * @param msg Id of the hotel to be removed
	 * @return HttpResponse
	 */
	@POST
	@Path("/removehotel")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeHotel(String msg) {
		serv.removeHotel((int) Integer.valueOf(msg));
		return Response.status(200).entity("Removed hotel" + msg).build();
	}
	/**
	 * Removes a package from the server
	 * @param msg Id of the package to be removed
	 * @return HttpResponse
	 */

	@POST
	@Path("/removePackage")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response removePackage(String msg) {
		serv.removePackage((int) Integer.valueOf(msg));
		return Response.status(200).entity("Removed package" + msg).build();
	}

	
	/**
	 * Sells a number of flight seats
	 * 
	 * @param msg The flight id and the number of seats
	 * @return HttpResponse
	 */
	@POST
	@Path("/sellflight")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response sellFlight(String msg) {
		System.out.println(msg);
		serv.sellFlight((int)Integer.valueOf(msg.split("-")[0]), (int) Integer.valueOf(msg.split("-")[1]));
		return Response.status(200).entity("Sold flight" + msg).build();
	}
	
	/**
	 * Books rooms on a hotel
	 * 
	 * @param msg Data about the rooms to be booked
	 * @return HttpResponse
	 */

	@POST
	@Path("/sellhotel")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response sellHotel(String msg) {
		serv.sellRooms((int) Integer.valueOf(msg.split("-")[0]), (int) Integer.valueOf(msg.split("-")[1]),
				(long) Integer.valueOf(msg.split("-")[2]), (long) Integer.valueOf(msg.split("-")[3]));
		return Response.status(200).entity("Sold rooms" + msg).build();
	}

	
	/**
	 * Sells a package 
	 * 
	 * @param msg Data about the package
	 * @return HttpResponse
	 */
	@POST
	@Path("/sellpackage")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response sellPackage(String msg) {
		serv.sellPackage((int) Integer.valueOf(msg.split("-")[0]), (int) Integer.valueOf(msg.split("-")[1]), (int) Integer.valueOf(msg.split("-")[2]) , (long) Integer.valueOf(msg.split("-")[3]),  (long) Integer.valueOf(msg.split("-")[4]));
		return Response.status(200).entity("Sold package" + msg).build();
	}

}