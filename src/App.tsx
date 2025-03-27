import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/Login";
import RegisterPage from "./pages/Register";
import ForgotPasswordPage from "./pages/ForgottPassword";
import HomePage from "./pages/Home";
import StudyTitlePage from "./pages/StudyTitle";
import HobbyPage from "./pages/Hobby";
import InteractivePage from "./pages/Viaggi.tsx";
import { useAuth } from "./components/AuthProvider";
import InformazioniPage from "./pages/Informazioni"
import "./index.css";

const App: React.FC = () => {
    const { isAuthenticated } = useAuth(); // Controlla se l'utente è loggato

    return (
        <Router>
            <Routes>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/forgotPassword" element={<ForgotPasswordPage />} />
                <Route path="/home" element={isAuthenticated ? <HomePage /> : <Navigate to="/login" />} />
                <Route path="/studyTitle" element={isAuthenticated ? <StudyTitlePage /> : <Navigate to="/login" />} />
                <Route path="/hobby" element={isAuthenticated ? <HobbyPage /> : <Navigate to="/login" />} />
                <Route path="/viaggi" element={isAuthenticated ? <InteractivePage /> : <Navigate to="/login" />} />
                <Route path="/informazioni" element={isAuthenticated ? <InformazioniPage /> : <Navigate to="/login" />} />
                <Route path="*" element={<Navigate to="/login" />} />
            </Routes>
        </Router>
    );
};

export default App;
