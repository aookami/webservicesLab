O projeto deve ser executado em um servidor Apache Tomcat 8.5

Os endpoints estão configurados como "http://localhost:8080/ServerD/rest/agt/*"

O arquivo principal (onde estão definidos os endpoints) é ServerMain.java.
A definição da interface servidor é Server.java
A implementação da interface é ServerImpl.java


O programa dos clientes está na pasta ServerClients, onde existem dois programas
em Python3, o Client, que expõe o terminal com os métodos de Cliente, e
o ServerControl, que expõe o terminal de controle do servidor.
Os programas em python são triviais e utilizam a biblioteca Requests para realizar
requests http no servidor. 
