# 🧪 Selenium User Manager Automation

Automazione per la gestione degli utenti (verifica, eliminazione e creazione) tramite **Selenium WebDriver** in Java.  
Il progetto simula il comportamento di un amministratore che accede a un portale web, naviga nei menu e gestisce gli utenti in modo automatico.

---

## 🚀 Cos'è Selenium?

[Selenium](https://www.selenium.dev/) è una suite di strumenti open-source per l'automazione dei browser web.  
Permette di simulare azioni utente come clic, inserimento di testo, navigazione tra pagine e verifica di elementi.

In questo progetto, Selenium viene usato con **Java** e **ChromeDriver** per:

- Accedere automaticamente al portale utente
- Verificare la presenza di un utente specifico
- Eliminarlo se già esistente
- Creare un nuovo utente con dati predefiniti

---

## 📁 Funzionalità

✅ Login automatico  
✅ Navigazione tra sezioni (TOOLS > USER MANAGER > Utenti)  
✅ Ricerca utente per nome e cognome  
✅ Eliminazione utente se già esistente  
✅ Creazione nuovo utente con campi compilati  
✅ Selezione da menu a tendina (gruppo, ruolo, azienda)  
✅ Gestione di iframe (se presente)

---

## 🧠 Requisiti

- Java 17 o superiore
- [Selenium Java Bindings](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
- [ChromeDriver](https://sites.google.com/chromium.org/driver/)
- IntelliJ IDEA (consigliato)

---

## ⚙️ Setup e Avvio

1. Clona il repository:

   ```bash
   git clone https://github.com/tuo-username/selenium-user-manager.git
   cd selenium-user-manager
