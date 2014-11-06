package ubu.lsi.dms.agenda.test;

import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;
import ubu.lsi.dms.agenda.persistence.BinaryFacade;

/**
 * Clase que contiene tests para probar la persistencia
 * con ficheros binarios.
 * 
 * @author Plamen
 *
 */
public class BinaryFacadeTest{
	public static void main(String args[]) {
		BinaryFacade BF = (BinaryFacade) BinaryFacade.getInstance();
		ContactType firstCT = new ContactType(1,"First ContactType");
		Contact contact = new Contact(1, "Alejandro","Gonzalez",
				"Sr.","C-Europa", "Burgos", "Burgos",
				"09001", "Burgos", "España",
				"UBU", "Jefe", "000-000-000",
				"Jefe", "111-111-111", "222-222-222",
				"agr0095", "Nota", firstCT);
		BF.insertContact(contact);
		BF.getContactsBySurname("Gonzalez");
		BF.getContactsBySurname("Gonzalez");
	}
}
