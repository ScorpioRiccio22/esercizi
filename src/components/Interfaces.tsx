export interface AuthContextType {
    isAuthenticated: boolean;
    user: string | null;
    login: (email: string, password: string) => boolean;
    logout: () => void;
    register: (email: string, password: string) => boolean;
}

export interface  EducationProps {
    title: string;
    school: string;
    year: number;                                           //interfaccia per la tabella titoli di studio
    result: string;
}

export interface HobbyProps
{
    nome: string;
    livello: "Principiante" | "Amatoriale" | "Esperto";             //interfaccia per la tabella hobby
}

