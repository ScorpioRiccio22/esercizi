import React, { createContext, useContext, useEffect, useState } from "react";
import { AuthContextType } from "./Interfaces.tsx";

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<string | null>(() => {
        return localStorage.getItem("loggedUser") || null; // Inizializza con il valore salvato
    });

    const isAuthenticated = !!user;

    useEffect(() => {
        const storedUser = localStorage.getItem("loggedUser");
        if (storedUser) setUser(storedUser);
    }, []);

    const login = (email: string, password: string): boolean => {
        const users = JSON.parse(localStorage.getItem("users") || "[]");

        const userFound = users.find((user: { email: string; password: string }) =>
            user.email === email && user.password === password
        );

        if (userFound) {
            localStorage.setItem("loggedUser", email);
            setUser(email);
            return true;
        }

        return false;
    };

    const register = (email: string, password: string): boolean => {
        const users = JSON.parse(localStorage.getItem("users") || "[]");

        if (users.some((user: { email: string }) => user.email === email)) {
            return false; // Email già registrata
        }

        users.push({ email, password });
        localStorage.setItem("users", JSON.stringify(users));
        return true;
    };

    const logout = () => {
        localStorage.removeItem("loggedUser");
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, isAuthenticated, login, register, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error("useAuth deve essere usato all'interno di un AuthProvider");
    }
    return context;
};
