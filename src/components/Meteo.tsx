import {useState} from 'react';



const MeteoPage: React.FC = () => {
    const apiKey: string = "305b00eeb0898a0f21bd45c7d137b237";
    let [citta, setCitta] = useState("");                                               //variabili utili
    let [meteo, setMeteo] = useState<any>(null);
    let [errore, setError] = useState("");

    let getMeteo = async () => {
        if (citta.trim() === "") {
            setError("⚠️ Inserisci una città valida!");
            setMeteo(null);                                                         //if per errore su input
            return;
        }

        setError("");
        const url = `https://api.openweathermap.org/data/2.5/weather?q=${citta}&appid=${apiKey}&units=metric&lang=it`;              //url con API

        try {
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(`Errore: ${response.status}`);
            }                                                               //try per lanciare eventualmente errore
            const json = await response.json();
            setMeteo({
                name: json.name,
                temp: json.main.temp,
                condition: json.weather[0].description,                         //rendo ciò che ricevo un json e nella variabile meteo ci metto questi dati che mi servono
                humidity: json.main.humidity,
                wind: json.wind.speed,
            });
        } catch (error) {
            setError("❌ Errore: Città non trovata o API non valida.");
            setMeteo(null);
        }
    };

    return (
        <div className="meteo-container">
            <div className="meteo-title">
                <h2>🌍 Meteo del Giorno</h2>
            </div>
            <div className ="meteo-input">
                <input
                    type="text"
                    placeholder="Inserisci una città"
                    value={citta}
                    onChange={(e) => setCitta(e.target.value)}
                />
            </div>
            <button
                className="meteo-button"
                onClick={getMeteo}>Cerca</button>
            {errore && <p className="text-red-500 mt-2">{errore}</p>}
            {meteo && (
                <div className="meteo-result">
                    <h2 className="font-bold">🌍 {meteo.name}</h2>
                    <p>🌡 Temperatura: <strong>{meteo.temp}°C</strong></p>
                    <p>☁️ Condizione: <strong>{meteo.condition}</strong></p>
                    <p>💦 Umidità: <strong>{meteo.humidity}%</strong></p>
                    <p>💨 Vento: <strong>{meteo.wind} m/s</strong></p>
                </div>
            )}
        </div>
    );
};

export default MeteoPage;

