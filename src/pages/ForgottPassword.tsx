import { useState } from "react";
import {Link} from "react-router-dom";

const ForgotPasswordPage: React.FC = () => {
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
;

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        const users = JSON.parse(localStorage.getItem("users") || "[]");

        const userExists = users.some((user: { email: string }) => user.email === email);

        if (userExists) {
            setMessage("Se l'email è registrata, riceverai un link per reimpostare la password.");
            setError("");
        } else {
            setError("Email non trovata. Controlla l'indirizzo o registrati.");
            setMessage("");
        }
    };

    return (
        <div className="forgot-password-container">
            <form onSubmit={handleSubmit} className="forgot-password-form">
                <h1>Recupero Password</h1>
                {message && <p className="text-green-500 text-center">{message}</p>}
                {error && <p className="text-red-500 text-center">{error}</p>}
                <input type="email" placeholder="Inserisci la tua email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
                <button type="submit">Recupera Password</button>
                <p>Torna al <Link to="/login">Login</Link></p>
            </form>
        </div>
    );
};

export default ForgotPasswordPage;
