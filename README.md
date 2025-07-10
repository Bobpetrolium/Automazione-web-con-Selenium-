# 🧪 Selenium User Manager - Automazione Web in Java

Questo progetto automatizza la gestione degli utenti su un portale web utilizzando **Java** e **Selenium WebDriver**.  
Il programma effettua l’accesso, verifica se un utente esiste, lo elimina (se presente) e lo ricrea automaticamente compilando tutti i campi necessari.

---

## 💡 Come funziona

Il codice avvia un browser Chrome, si collega a una pagina web interna (es. `http://192.168.1.210/#/login`), e simula il comportamento di un amministratore.  
Le azioni svolte includono:

- **Login**: selezione dell’utente e inserimento password  
- **Navigazione**: apertura dei menu `TOOLS > USER MANAGER > Utenti`  
- **Gestione utenti**:
  - Cerca un utente specifico (es. "Luca Bello")
  - Se esiste, lo elimina
  - In ogni caso, crea un nuovo utente compilando i campi richiesti

---

## 🔍 Dettagli del codice

- Viene usata `WebDriverWait` per assicurarsi che ogni elemento sia visibile e cliccabile prima di interagirci.
- La ricerca dell’utente avviene scorrendo ogni riga della tabella (`div.MuiDataGrid-row`) e confrontando nome e cognome.
- In caso di match, il programma scrolla la riga in vista e clicca sul bottone con l’icona `DeleteIcon`.
- Dopo l’eventuale eliminazione, viene aperta la finestra di creazione utente dove:
  - Vengono inseriti `nome`, `cognome`, `username`, `password`, `email`, `telefono`
  - Si selezionano `gruppo`, `ruolo` e `azienda` da menu a tendina
- Il tutto è intervallato da `Thread.sleep()` per gestire i tempi di caricamento (oltre alle attese esplicite).

---

## ⚙️ Requisiti

- ✅ Java 17+
- ✅ [Selenium WebDriver](https://www.selenium.dev/downloads/)
- ✅ [ChromeDriver](https://chromedriver.chromium.org/downloads) compatibile con la versione di Chrome
- ✅ IntelliJ IDEA (o altro IDE Java)

---

## ▶️ Come eseguire

1. Imposta il percorso corretto del ChromeDriver:
   java
   System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");


2. Avvia il file `ProvaSelenium.java` da IntelliJ o da terminale.

---

## File principali

* **ProvaSelenium.java**
  Contiene tutto il codice necessario per:

  * Login automatico
  * Navigazione nel sito
  * Verifica presenza utente
  * Eliminazione e creazione utente

---

## Note aggiuntive

* Il codice gestisce automaticamente anche la presenza di eventuali iframe.
* Tutti i passaggi sono accompagnati da `System.out.println()` per mostrare lo stato delle operazioni nella console.
* In caso di errori o elementi non trovati, il programma mostra messaggi chiari per il debug.

---

## Autore

**Roberto Pedrollo**
Classe 4GI – Informatica
Progetto scolastico realizzato durante l'esperienza PCTO con Java + Selenium
