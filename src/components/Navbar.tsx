import LogoutButtonComponent from "./ButtonLogOut.tsx";
import MenuDropdown from "./Dropdown.tsx";

const NavbarComponent = () => {
    return (
        <nav className="navbar">

            <MenuDropdown />
                <img src="/images/gemini.png" alt="logo"/>
            <LogoutButtonComponent />
        </nav>
    );
};

export default NavbarComponent;
