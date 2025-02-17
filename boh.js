
const fs = require("fs");
const express = require("express");
const app = express();
app.use(express.static("view"));
const port = 3000;
let ws = new WebSocket('ws://192.168.7.254/ws');


ws.onopen = function () {console.log("connessione effettuata con successo")}
ws.onclose = function () {console.log("Disconnesso")}
ws.onmessage = function (event)
{
    let util = JSON.parse(event.data);                                          //parsing dati ricevuti in JSON
    util.date = new Date().toISOString().split('T')[0];                //Aggiungi la data all'oggetto formattandola in modo che compaia solo YYYY-MM-DD
    let data = JSON.stringify(util, null,2);               // converto in stringa JSON formattandolo
    console.log(JSON.parse(event.data));
    realTimeData(data);
    storicData(data);
}

function realTimeData(lastData)
{
    fs.writeFile("realTime.txt", lastData ,"utf8", ()=>{} );                    //per il file ci do un nome, ci passo un dato, lo formatto, e lo chiamo come funzione
}

function storicData(lastData)
{
    let daCapo= `${lastData}\n`;                                                             //serve per aggiungere uno spazio
    fs.appendFile("StoricFile.txt", daCapo, "utf8", ()=>{} );
}

function lectureRealTime()
{
    let textRealTime = fs.readFileSync("RealTime.txt", "utf8");
    return textRealTime;
}
function lectureStoric()
{
    let textStoric = fs.readFileSync("StoricFile.txt", "utf8");
    return textStoric;
}

app.get('/', function(req, res) {
    res.render('index.html');
});
app.get('/realTime', async function(req, res)
{
   try{
       let realTimeData = JSON.parse(await lectureRealTime());

    const realTime =
        {
            lectureRealTime : realTimeData

        }
        res.send(realTime);}catch(err){ console.log("stai sbagliando");res.send(err);}

})
app.get('/storicData', async function(req, res)
{
    try{
        let storicData = JSON.parse(await lectureStoric());

        const storic =
            {
                lectureStoric : storicData

            }
        res.send(storic);}catch(err){ console.log("stai sbagliando");res.send(err);}

})
app.listen(3000,()=>{console.log("server http://localhost:3000");});

//rendi tutte le info html delle API che mandi alla pagina html