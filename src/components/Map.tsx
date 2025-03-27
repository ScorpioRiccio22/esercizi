import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import L from "leaflet";
import "leaflet/dist/leaflet.css";

const QuartoMap: React.FC = () => {
    const position: [number, number] = [40.8762, 14.1468]; // Stessa posizione di center

    // Creazione dell'icona personalizzata
    const customIcon = new L.Icon({
        iconUrl: "/images/house.png", // Percorso dell'immagine
        iconSize: [40, 40], // Dimensione dell'icona
        iconAnchor: [20, 40], // Punto di ancoraggio
    });

    return (
        <div style={{ height: "200px", width: "30%" }}>
            <MapContainer center={position} zoom={13} style={{ height: "100%", width: "100%" }}>
                <TileLayer
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                />

                {/* Marker con icona personalizzata */}
                <Marker position={position} icon={customIcon}>
                    <Popup>📍 Quarto (NA) - CAP 80010</Popup>
                </Marker>
            </MapContainer>
        </div>
    );
};

export default QuartoMap;
