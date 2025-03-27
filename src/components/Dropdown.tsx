import { useState } from "react";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import { Link } from "react-router-dom";

const MenuDropdown = () => {
    const [isOpen, setIsOpen] = useState(false);

    return (
        <DropdownButton
            align="end"
            title="Menu"
            id="dropdown-menu"
            show={isOpen}
            onToggle={(isOpen) => setIsOpen(isOpen)} // Usa onToggle per gestire apertura/chiusura
        >
            <Dropdown.Item as="div">
                <Link to="/home" style={{ textDecoration: "none", color: "black" }}>
                    Home
                </Link>
            </Dropdown.Item>
            <Dropdown.Item as="div">
                <Link to="/informazioni" style={{ textDecoration: "none", color: "black" }}>
                    Informazioni Personali
                </Link>
            </Dropdown.Item>
            <Dropdown.Item as="div">
                <Link to="/studyTitle" style={{ textDecoration: "none", color: "black" }}>
                    Titoli di Studio
                </Link>
            </Dropdown.Item>
            <Dropdown.Item as="div">
                <Link to="/hobby" style={{ textDecoration: "none", color: "black" }}>
                    Hobby
                </Link>
            </Dropdown.Item>
            <Dropdown.Item as="div">
                <Link to="/viaggi" style={{ textDecoration: "none", color: "black" }}>
                    Viaggi Fatti
                </Link>
            </Dropdown.Item>
        </DropdownButton>
    );
};

export default MenuDropdown;
