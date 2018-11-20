import requests


#main loop

print("Welcome to serverControl v0.1")
print("Available commands:help, addFlight, addHotel, removeFlight, removeHotel, addPackage, addPackageL")
while(True):
    print("Waiting user input...")
    text = input()
    if text.__eq__("help"):
        print("Available help:addFlight, removeFlight, addHotel, removeHotel")
        text2 = input()
        if text2.__eq__("addFlight"):
            print("Adds a flight to the server")
            print("Syntax: id-numberofseats-destination-departure-firstflighttime-priceperseat")
        if text2.__eq__("removeFlight"):
            print("Removes a flight from the server")
            print("Syntax: id")
        if text2.__eq__("addHotel"):
            print("Adds a hotel to the server")
            print("Syntax is: id-HotelName-HotelLocation-NumberOfRooms-RoomCapacity-RoomPrice")
        if text2.__eq__("removeHotel"):
            print("Removes a hotel from the server")
            print("Syntax: id")
        if text2.__eq__("addPackage"):
            print("Adds a package to the server, using the constituents ids")
        if text2.__eq__("addPackageL"):
            print("Adds a package to the server, creating new constituents")

    if text.__eq__("addFlight"):
        print("id:")
        id = input()
        print("numberofseats:")
        ns = input()
        print("destination:")
        destination = input()
        print("departure:")
        departure = input()
        print("firstflighttime:")
        firstflighttime = input()
        print("seatprice:")
        seatprice = input()
        fullstring = id +"-"+ ns+"-"+destination +"-"+ departure+"-"+firstflighttime +"-"+ seatprice
        response = requests.post("http://localhost:8080/ServerD/rest/agt/registerflight", fullstring)
        print(response.text)

    if text.__eq__("addHotel"):
        print("id:")
        id = input()
        print("HotelName:")
        ns = input()
        print("destination:")
        destination = input()
        print("NumberOfRooms:")
        departure = input()
        print("RoomCapacity:")  
        firstflighttime = input()
        print("RoomPrice:")
        seatprice = input()
        fullstring = id + "-" + ns + "-" + destination + "-" + departure + "-" + firstflighttime + "-" + seatprice
        response = requests.post("http://localhost:8080/ServerD/rest/agt/registerhotel", fullstring)
        print(response.text)

    if text.__eq__("addPackage"):
        print("idpackage:")
        idp = input()
        print("idflight:")
        idf = input()
        print("idhotel:")
        idh = input()
        print("seats:")
        seats = input()
        print("price:")
        price = input()
        longstring = id + "-" + idf + "-" + idh + "-" +seats + "-" +price
        response = requests.post("http://localhost:8080/ServerD/rest/agt/registerpackageid", longstring)
        print(response.text)
##	public void addPackage(int idp, int id, int seats, String to, String from, int price, long fftimestamp, int idh,


    if text.__eq__("addPackageL"):
        print("idpackage:")
        idp = input()
        print("idflight:")
        idf = input()
        print("seats:")
        seats = input()
        print("to:")
        to = input()
        print("from:")
        fro = input()
        print("price:")
        price = input()
        print("fftimestamp:")
        fftimestamp = input()
        print("idhotel:")
        idhotel = input()
        print("nameHotel:")
        hname = input()
        print("hotelLoc:")
        hloc = input()
        print("RoomCap")
        rcap = input()
        longstring = idp  + "-" +idf  + "-" +seats  + "-" +to  + "-" +fro + "-" +price + "-" +fftimestamp + "-" +idhotel
        longstring = longstring + "-" +  hname + "-" + hloc + "-" + rcap
        print(longstring)
        response = requests.post("http://localhost:8080/ServerD/rest/agt/registerpackage", longstring)
        print(response.text)

    if text.__eq__("removeFlight"):
        print("Id:")
        id = input()
        response = requests.post("http://localhost:8080/ServerD/rest/agt/removeflight", id)
        print(response.text)
    if text.__eq__("removeHotel"):
        print("Id:")
        id = input()
        response = requests.post("http://localhost:8080/ServerD/rest/agt/removehotel", id)
        print(response.text)
