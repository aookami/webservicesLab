import requests


print("ClientControl v0.1")

print("Available commands:help, getFlights, getHotels, getPackages, buyFlight, buyRooms, buyPackage")

while True:
    print("Waiting user input...")
    text = input()
    if text.__eq__("help"):
        print("Available help: getFlights, getHotels, getPackages, buyFlight, buyRooms, buyPackage")
        text2 = input()
        if text2.__eq__("getFlights"):
            print("Gets info about all the flights in the server")
        if text2.__eq__("getHotels"):
            print("Returns all info about hotels in the server")
        if text2.__eq__("getPackages"):
            print("Returns data about packages")
        if text2.__eq__("buyFlights"):
            print("Purchases seats on a flight")
        if text2.__eq__("buyRooms"):
            print("Buys rooms on a hotel")
        if text2.__eq__("buyPackage"):
            print("Purchases a package")

    if text.__eq__("getFlights"):
        response = requests.get("http://localhost:8080/ServerD/rest/agt/getflights")
        print(response.text)

    if text.__eq__("getHotels"):
        response = requests.get("http://localhost:8080/ServerD/rest/agt/gethotels")
        print(response.text)
    if text.__eq__("getPackages"):
        response = requests.get("http://localhost:8080/ServerD/rest/agt/gethotels")
        print(response.text)

    if text.__eq__("buyFlights")