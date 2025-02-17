document.addEventListener("DOMContentLoaded", function()
{
  let sections =document.querySelectorAll("section");           //let delle section
  let navLinks = document.querySelectorAll("nav-a");                //let dei link del nav
  sections.forEach((section,index)=>                      //per tutte le section
  {
    if(index===0)                                                                             //se index = 0 aggiungi la section attiva
    {
      sections.classList.add("active");
    } else
    {
      sections.classList.remove("active");                                              //sennò toglila
    }
  });
  navLinks.forEach(link => {                                                //per tutti i link
    link.addEventListener("click", function (e) {                    //al click fai partire l'evento
      e.preventDefault();                                                                    // Previene il comportamento di default dei link

                                                                                            // Ottiene l'ID della sezione da mostrare
      let targetId = this.getAttribute("href").substring(1);
      let targetSection = document.getElementById(targetId);

      // Nasconde tutte le sezioni
      sections.forEach(section => section.classList.remove("active"));

      // Mostra solo la sezione selezionata
      if (targetSection) {
        targetSection.classList.add("active");
        targetSection.scrollIntoView({ behavior: "smooth" });
      }
    });
  });
});

let listaHobby=[
  {"nome":"Palestra","livello":"Esperto"},
  {"nome":"Anime/Manga","livello":"Fanatico"},
  {"nome":"Collezionismo","livello":"Amatoriale"},
  {"nome":"Videogiochi","livello":"Amatoriale"},
  {"nome":"Film/Serie TV","livello":"Amatoriale"}];

//funzione per riaggiornare la tabella
function refreshTable() {
  let table = document.getElementById("table");

// Funzione per modificare una cella: al click la cella diventa un input
  function editCell(cell, index, field) {
    let oldValue = cell.innerText;
    let input = document.createElement("input");
    input.type = "text";
    input.value = oldValue;
    cell.innerText = "";
    cell.appendChild(input);
    input.focus();

    // Salva la modifica quando l'utente preme "Invio"
    input.addEventListener("keypress", (e) => {
      if (e.key === "Enter") {
        if (input.value.trim() === "") {
          alert("Non può essere vuoto");
          return;
        }
        saveEdit(cell, input, index, field);
      }
    });
  }

// Funzione per salvare la modifica nella cella
  function saveEdit(cell, input, index, field) {
    listaHobby[index][field] = input.value;
    cell.innerText = input.value;
  }

// Funzione per aggiungere una nuova riga
  function addNewRow() {
    let newNome = prompt("Inserisci il nuovo hobby:");
    if (!newNome) {
      alert("Nessun hobby inserito.");
      return;
    }
    let newLivello = prompt("Inserisci il livello di esperienza:");
    if (!newLivello) {
      alert("Nessun livello inserito.");
      return;
    }
    listaHobby.push({ "nome": newNome, "livello": newLivello });
    refreshTable();
  }


  table.innerHTML = `
    <tr style="display:none;">
      <td id="userId">1</td>
    </tr>
    <tr>
      <td colspan="3" style="text-align:center; font-weight:bold;">I MIEI HOBBY</td>
    </tr>
    <tr>
      <th>Hobby</th>
      <th>Livello Esperienza</th>
      <th>Gestione</th>
    </tr>
  `;

  // Aggiungi le righe dalla lista JSON
  listaHobby.forEach((data, index) => {     //per tutti i dati nell'array
    let row = table.insertRow();                          // inserisci righe
    let nomeCell = row.insertCell(0);               // inserisci nella riga il primo elemento
    let livelloCell = row.insertCell(1);            // inserisci nella riga il secondo elemento
    let deleteCell = row.insertCell(2);             // inserisci nelle righe il terzo elemento elimina

    nomeCell.innerText = data.nome;                                             //il testo qui lo prendi dal nome
    livelloCell.innerText = data.livello;                                       //qua lo prendi dal livello

    // Aggiungi la funzionalità di modifica cliccando sulle celle
    nomeCell.addEventListener("click", () => editCell(nomeCell, index, "nome"));              //se clicco su cella sotto nome la modifico
    livelloCell.addEventListener("click", () => editCell(livelloCell, index, "livello"));     //se clicco su cella sotto livello la modifico

    // Crea il pulsante "Elimina"
    let deleteButton = document.createElement("button");
    deleteButton.innerText = "Elimina";
    deleteButton.onclick = function () {                                                                      //cliccandoci sopra
      listaHobby.splice(index, 1);                                                                      // Rimuove l'elemento dall'array
      refreshTable();                                                                                             // Ricarica la tabella
    };
    deleteCell.appendChild(deleteButton);                                                                         //togli anche button elimina
  });

  // Aggiungi una riga finale con il pulsante "Aggiungi nuova riga"
  let addRow = table.insertRow();                                                           //nuova riga
  let addCell = addRow.insertCell(0);                                                 //nuova colonna
  addCell.colSpan = 3;
  addCell.style.textAlign = "center";
  let addButton = document.createElement("button");                                   //Creo nuovo elemento button
  addButton.innerText = "Aggiungi nuova riga";                                                                    //testo nuova riga
  addButton.onclick = addNewRow;                                                                                  //al click aggiungi nuova riga
  addCell.appendChild(addButton);                                                                                 //ci metti sotto di nuovo l'add button
}


// Carica i dati quando il DOM è pronto
document.addEventListener("DOMContentLoaded", refreshTable);


async function getMeteo() {
  let apiKey = "e544b7db482e13c854e7115eeac09718";
  let city = document.getElementById("citta").value.trim();

  if (city === "") {          //controllo per vedere che si inserisce come città, sennò caccia un paragrafo errore
    document.getElementById("risultato").innerHTML = "<p style='color:red;'>⚠️ Inserisci una città valida!</p>";
    return;
  }

  let url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric&lang=it`;

  try {
    let response = await fetch(url);        //aspetta la fetch
    if (!response.ok) {                                //se risposta non valida lancia errore statusd
      throw new Error(`Errore: ${response.status}`);
    }

    let data = await response.json();  // Aggiungi questa riga per ottenere la risposta JSON
    let nomeCitta = data.name;
    let temperatura = data.main.temp;
    let condizione = data.weather[0].description;
    let umidita = data.main.humidity;
    let vento = data.wind.speed;

    document.getElementById("risultato").innerHTML = `
            <h2 style="font-size: 24px; font-weight: bold;">🌍 ${nomeCitta}</h2>
            <p style="font-size: 24px;">🌡 Temperatura: <strong>${temperatura}°C</strong></p>
            <p style="font-size: 24px;">☁️ Condizione: <strong>${condizione}</strong></p>
            <p style="font-size: 24px;">💦 Umidità: <strong>${umidita}%</strong></p>
            <p style="font-size: 24px;">💨 Vento: <strong>${vento} m/s</strong></p>
        `;  // Mostra i dati meteo

  } catch (error) {
    document.getElementById("risultato").innerHTML = `<p style='color:red;'>❌ Errore: Città non trovata o API non valida.</p>`;
    console.error("Errore nel recupero del meteo:", error);  //eventuale catch su città o API
  }
}
document.addEventListener("DOMContentLoaded", function(){                                                 //attendi caricamento DOM
document.getElementById("contactForm").addEventListener("submit", function(event)   //prendicontactForm e al submit fai partire la funzione
{
  event.preventDefault();                                                                                     //per il form prima le variabili utili sono nome un booleano true
  let isValid= true;                                                                                 //cognome,email,telefono,messaggio
  let nome =document.getElementById("nome2").value.trim();
  let cognome =document.getElementById("cognome").value.trim();
  let email =document.getElementById("email").value.trim();
  let telefono =document.getElementById("telefono").value.trim();
  let messaggio =document.getElementById("messaggio").value.trim();

  function showError(id,message)                                                                        //mi serve una funzione per mostrare errore
  {
    document.getElementById(id).textContent = message;
    isValid=false;                                                                                           //funziona solo se booleano è false
  }

  document.querySelectorAll(".error").forEach((el=>el.textContent=""));                    //vale per tutti gli errori, e cancellali una volta che hai scritto


  //Validazione Nome (minimo 2 caratteri e solo lettere) uso regex
  if (nome.length < 2 || !/^[a-zA-ZÀ-ÿ\s]+$/.test(nome)) {
    showError("nomeError", "Il nome deve essere valido (solo lettere).");
  }

  // Validazione Cognome (minimo 2 caratteri e solo lettere) uso regex
  if (cognome.length < 2 || !/^[a-zA-ZÀ-ÿ\s]+$/.test(cognome)) {
    showError("cognomeError", "Il cognome deve essere valido (solo lettere).");
  }

  // Validazione Email (formato email valido) uso regex
  let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    showError("emailError", "Inserisci un'email valida.");
  }

  // Validazione Telefono (solo numeri, minimo 8 cifre, massimo 15) uso regex
  let phoneRegex = /^[0-9]{8,15}$/;
  if (!phoneRegex.test(telefono)) {
    showError("telefonoError", "Il numero di telefono deve contenere solo numeri (8-15 cifre).");
  }

  // Validazione Messaggio (minimo 5 caratteri) uso regex
  if (messaggio.length < 5) {
    showError("messaggioError", "Il messaggio deve essere più lungo di 5 caratteri.");
  }

  // Se tutti i campi sono validi, mostra il messaggio di successo con prompt
  if (isValid) {
    // Prompt con il messaggio di successo
    alert("Messaggio inviato con successo!");

    // Resetta il form
    document.getElementById("contactForm").reset();
  }
})});

document.addEventListener("DOMContentLoaded", function()
{
  document.getElementById("avvio").addEventListener("click", loadRealTime);           //al click parte loadRealTime
  document.getElementById("storico").addEventListener("click", loadHistory);          //al click parte loadHistory

  function loadRealTime() {
    fetch("http://localhost:3000/realTime")                                                   //dovrebbe essere l'url che attiva l'API giusta
        .then(response => response.json())                                    //rispondi su formato JSON
        .then(data => {
          if (data.lectureRealTime) {                                                   //se il dato deriva da lectureRealTime(funzione che sta in boh.js)
            let info = data.lectureRealTime;                                           //variabile per salvare il dato in entrata

                                                                                      // Inserisco i dati nella tabella in tempo reale
            document.getElementById("temp").innerText = `${info.temperature.toFixed(1)} °C`;
            document.getElementById("pressione").innerText = `${info.pression.toFixed(1)} hPa`;          //dovrebbe essere il modo in cui prendo i dati e li inserisco
            document.getElementById("umidita").innerText = `${info.humidity.toFixed(1)} %`;
          }
        })
        .catch(error => console.error("Errore nel caricamento dei dati in tempo reale:", error));
  }

  function loadHistory() {
    fetch("http://localhost:3000/storicData")
        .then(response => response.json())
        .then(data => {
          if (data.lectureStoric) {
            let infoStoric = data.lectureStoric.split("\n").filter(row => row.trim() !== "");                    //dovrei ottenere i dati separati in verticale

                                                                                                                          // Prendo le ultime 10 letture, se disponibili
            let utilStoric = infoStoric.slice(-10).map(row => JSON.parse(row));

                                                                                                                          // Svuoto la tabella prima di aggiungere nuovi dati
            let tbody = document.getElementById("historyBody");
            tbody.innerHTML = "";

                                                                                                                          // Inserisco ogni entry nella tabella storica
            utilStoric.forEach(info => {
              let row = `<tr>
                        <td>${info.temperature.toFixed(1)} °C</td>
                        <td>${info.pression.toFixed(1)} hPa</td>                                       
                        <td>${info.humidity.toFixed(1)} %</td>
                    </tr>`;
              tbody.innerHTML += row;
            });
          }
        })
        .catch(error => console.error("Errore nel caricamento dello storico:", error));
  }});







