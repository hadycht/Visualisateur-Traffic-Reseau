
DESCRIPTION DE NOTRE CODE:

On a implimenté ce projet avec JAVA, pour nous ça été plus évident vu qu'on a remarqué une similarité entre les classes et les protocoles (genre chaqu protocole avec sa classe). 

On a codé 6 classes en tout, avec une class Visualisateur.java qui contenait le main de l'exécution sur le terminal. 

Les 5 autres classes sont: PaquetEthernet.java, TrameEthernet.java, IPv4.java, TCP.java et HTTP.java. 

--La classe PaquetEthernet.java : 

Cette classe sert à récupérer tout le paquet de trames présent dans le fichier. 
Ainsi elle a deux attributs: liste qui une liste de listes des entiers tel que chaque liste d'entiers correspond à une trame, donc c'est une liste de trames. 
elle a aussi l'attribut nbTrames qui correspond aux nombres de trames dans tout le fichier. 
la méthode la plus importante dans cette classe est celle de loadTrames(), qui permet d'initialiser la liste des trame, donc c'est elle qui fait le découpage de tout le fichier et de prendre
en considération seulement les offsets et les octets. 


--La classe TrameEthernet.java : 

Cette classe sert à encapsuler tout une trame Ethernet. Ainsi, elle en a deuw attributs, enteteEth qui représente l'entete de la trame ethernet, et l'attribut dataEth qui représenet la partie data de la trame Ethernet. Grâce à cette classe, il est possible de récuperer les adresses Mac des machines qui communiquent. 

--La classe IPv4.java : 

Cette classe modélise la partie IPv4 de la data de la trame Ethernet. Elle aussi contient deux attributs, enteteIPv4 qui représente l'entete du protocole IPv4, et l'attribut dataIPv4 qui correspond à la partie données de IPv4. Cette classe contient des méthodes qui permettent de récupérer les adresses IP des machines, la valeur du TTL, la valeur du checksum et presque tous les autres parties de l'entete du protocol IPv4. 

--La classe TCP.java: 

Cette classe modélise la partie TCP de la data IPv4. Elle aussi contient deux attributs, enteteTcp qui représente l'entete du protocole TCP, et l'attribut dataTcp qui correspond à la partie données de TCP. Cette classe contient des méthodes qui permettent de récupérer les Ports des machines, les drapeux positionnés dans le protocol, la valeur du checksum et presque tous les autres parties de l'entete du protocol TCP.

--La classe HTTP.java : 

Cette classe modèlise la partie HTTP de la data TCP. Elle contient deux attributs, nature qui dit si c'est une Requête HTTP ou une Réponse HTTP, et aussi l'attribut enteteHTTP qui récupère la méthode, l'url, la version et le host lors d'une Requête HTTP, la version, le code et le message lors d'une réponse HTTP. 

Donc pour implémenter les différentes classes, on suit le processus suivant.

FICHIER.txt -------------> PaquetEthernet.java --------> TrameEthernet.java ---------------> IPv4 --------------> TCP.java ------------------> HTTP.java

Donc pour implémenter PaquetEthernet.java, j'aurais besoin du fichier en entrée, pour implémenter TrameEthernet.java j'aurais besoin en entrée d'une trame de PaquetEthernet.java.
Pour implémenter IPv4.java, j'aurais besoin de la partie dataEth de la classe TrameEthernet.java.

Pour implémenter TCP.java, j'aurais besoin de la partie dataIPv4 de la classe IPv4.java.

Pour implémenter HTTP.java, j'aurais besoin de la partie dataTCP de la classe TCP.java.

