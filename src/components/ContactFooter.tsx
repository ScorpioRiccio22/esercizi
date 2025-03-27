import { FaFacebook, FaInstagram, FaLinkedin } from "react-icons/fa";

const ContactFooter = () => {
    return (
        <footer className="footer">
                <p>📧 Email:{" "}<a href="mailto:stefanoriccio97@hotmail.it" className="text-blue-400 hover:underline">stefanoriccio97@hotmail.it</a> </p>
                <p>📞 Telefono:{" "}<a href="tel:+393664607064" className="text-blue-400 hover:underline">+39 366 460 7064</a></p>
                    <a href="https://www.facebook.com/Scorpio22Riccio" target="_blank" rel="noopener noreferrer"><FaFacebook /></a>
                    <a href="https://www.instagram.com/scorpioriccio22/" target="_blank" rel="noopener noreferrer"><FaInstagram /></a>
                    <a href="https://www.linkedin.com/in/stefano-riccio-82189a1a2/" target="_blank" rel="noopener noreferrer"><FaLinkedin /></a>
                <p className="text-sm text-gray-400 mt-4">© {new Date().getFullYear()} Tutti i diritti riservati</p>
        </footer>
    );
};

export default ContactFooter;
