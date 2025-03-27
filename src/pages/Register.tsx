import { useState } from "react";
import { useAuth } from "../components/AuthProvider";
import {Link, useNavigate} from "react-router-dom";

const RegisterPage: React.FC = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const { register } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (register(email, password)) {
            navigate("/login");
        } else {
            setError("Email già registrata. Usa un'altra email.");
        }
    };

    return (
        <div className="register-container">
            <form onSubmit={handleSubmit} className="register-form">
                <h1>Registrazione</h1>
                {error && <p className="text-red-500 text-center">{error}</p>}
                <input type="email" placeholder="Inserisci L'Email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
                <input type="password" placeholder="Inserisci la Password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
                <button type="submit">Registrati</button>
                <p>Hai già un account? <Link to ="/login">Accedi</Link></p>
            </form>
        </div>
    );
};

export default RegisterPage;
