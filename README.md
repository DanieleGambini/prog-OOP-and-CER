# prog-OOP-and-CER

Il presente progetto é relativo al corso di "Programmazione ad oggetti", con una sezione aggiuntiva di implementazione riguardante il corso di "Calcolatori elettronici e reti di calcolatori".

L’applicazione sviluppata si propone di realizzare un’interfaccia web per la gestione di richieste da parte di client.
In particolare, le richieste sono inoltrate sotto forma di REST API e permettono all’utente di ottenere dati, metadati e statistiche riguardanti un dataset di riferimento.

# immagine usecase diagram

Dopo la fase di inizializzazione del web server il flusso di esecuzione prevede l’acquisizione dei dati forniti da un server esterno, tramite richiesta http.
In seguito tali dati vengono analizzati e strutturati in modo da poter essere gestiti.
Terminata questa prima fase l’applicativo si mette in ascolto di eventuali richeste da parte del client.

L’interfaccia permette di soddisfare le seguenti richieste:

- restitiure il dataset o un suo sottoinsieme;
- restituire i metadati rellativi al dataset;
- restituire le statistiche del dataset o di un suo sottoinsieme;
- restituire una pagina html di help;
- restituire pagina informativa relativa alla codifica dei tipi nativi in Java.

La restituzione dei dati avviene in formato json.

# immagine sequence diagram

L’applicazione si suddivide in due parti, la prima é stata sviluppata in Java e si occupa delle delle funzioni di interfaccia, del download e del parsing dei dati; la seconda é stata sviluppata in linguaggio **Pyhton** e si propone di eseguire il calcolo delle statistiche serverless sulla piattaforma cloud **Microsoft Azure**.

Una possibile scenario di utilizzo é la creazione di un web server per la gestione di richieste da molteplici client, scaricando il calcolo computazionalmente oneroso su risorse di calcolo esterne.

#Realizzazione

##Routine di inizializzazione

Per lo sviluppo dell'applicazione Java si è scelto l'IDE Eclipse, mentre come application framework è stato utilizzato Spring.
Questa scelta ha permesso di avviare una Spring application, ospitata in un server locale di tipo Apache Tomcat, indirizzabile attraverso la porta locale della macchina.

> localhost:8080

### immagine terminale subito dopo l'avvio

La routine di inizializzazione dell'applicazione prevede il dowloading di un file, attraverso una http request, eseguita sulla base di un URL di riferimento.
Il file scaricato, in formato json, viene poi analizzato e parsato sfruttando i metodi presenti nella classe Downloader con lo scopo di isolare una particolare stringa url, utilizzata per effettuare una seconda operazione di downloading tramite http request.
Questa operazione permette di ottenere un secondo file (formato CSV) contenente il dataset di riferimento.

I dati contenuti in tale file sono separati da segni di punteggiatura "**,**" e "**;**" e  vengono parsati attraverso i metodi presenti nella classe Parser in modo da ottenere dati utilizzabili per il popolamento della classe **Dataset**.

Tale classe è costituita dall'aggregazione delle classi **Data** e **Header**; questa scelta ha permesso di separare, già in fase di popolamento delle classi, i dati relativi alla intestazione, dalle singole istanze del dataset.
Al termine della fase di parsing è stato inoltre previsto un messaggio di conferma per l'utente "**READY**" che in tale modo può verificare la corretta esecuzione della routine di inizializzazione.


## Interfaccia con il cliente









## Esecuzione delle richieste







## Azure

1. Astrazione del calcolo dalla macchina;
2. Scalabilitá e modularitá;
3.

Come giá anticipato, il motivo di per cui si é scelto questo tipo di designe é quello di avere una macchina in grado di gestire tutte le operazioni necessarie alla richieste di eventuali client ed avere diretto controllo sulla macchina che fornisce tali servizi, ma con lo possibilitá di eseguire graviose operazioni di calcolo in piattaforme piú performati.

A causa della complessitá della gestione e del costo associato ad un calcolatore in grado di avere elevatissima capacitá di calcolo, é stato pensato di utilizzare

Il progetto é stato pensato con l’obbiettivo di avere una macchina in grado di gestire le richieste da eventuali client e di poter eseguire ingenti quantitá di calcoli senza avere influenza sulla macchina stessa con cui si interfacciano i client.

Un vantaggio di questo designe é la scalabiliá e la modularitá.
Infatti non si é piú legati alla macchia a che si ha a disposizione, con la possibilitá di incorrere in rallentamenti nel soddisfare le richieste.

Il calcolo delle statistiche, che é di gran lunga il compito pió oneroso, é scaricato su piattaforme cloud che offrono maggiore capacitá di calcolo senza dover intercorrere nei proibitivi costi di infrastrutture e attrezzature.

Oltretutto la soluzione di utilizzo di una architettura serverless, permette risparmiare nei costi infrastrutturali e di macchinari giá di per sé proibitivi, ma anche di rendere piú flessibile e scalabile il servizio.
Infatti il calcolo delle statistiche é reso indipendente dalla capacitá computazionale della macchina che si ha a disposizione.

Oltre alla modularitá, questa architettura permette di astrarre il processo e renderlo indipendente dalle attrezzature a disposizione.


É stato scelto l’approccio di dividere il calcolo delle statistiche e farle eseguire su cloud é stato deciso per molteplici motivi.


Il calcolo di statistiche, puó richiedere un elevato numero di operazione di calcolo che necessitano tempo per essere calcolate, soprattutto in caso di cospicui dataset molto

Quando viene inoltrata una richiesta di calcolo delle statistiche, il server, fornisce tramite alla *Azure Function* il dataset sul quale é stato richiesto il calcolo.


