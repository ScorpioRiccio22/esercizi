import { useState } from "react";
import { useAuth } from "../components/AuthProvider";
import {Link, useNavigate} from "react-router-dom";

const LoginPage: React.FC = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (login(email, password)) {
            navigate("/home");
        } else {
            setError("Email o password errati");
        }
    };

    return (
        <div className="login-container">
            <form onSubmit={handleSubmit} className="login-form">
                <h1>Login</h1>
                {error && <p>{error}</p>}
                <input type="email" placeholder="Inserisci L'Email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
                <input type="password" placeholder="Inserisci la Password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
                <span onClick={() => navigate("/forgotPassword")}>Password dimenticata?</span>
                <button type="submit">Accedi</button>
                <p>Non hai un account?{" "}<Link to="/register">Registrati</Link></p>
            </form>
        </div>
    );
};

export default LoginPage;
