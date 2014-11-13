package ubu.lsi.dms.agenda.test;

import java.util.ArrayList;
import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;

/**
 * Vamos a almacenar en una clase los tipos que necesitemos para realizar las
 * pruebas que necesitamos en ambas clase, tanto en la fachada de BBDD como en
 * la fachada binaria.
 * 
 * @author killer7129
 *
 */
public class CommonData {
	private List<Contact> contactList;
	private List<Call> callList;
	private List<ContactType> contactTypeList;

	private static CommonData instance = null;

	private CommonData() {
		// Inicializamos los arraylist
		contactList = new ArrayList<Contact>();
		callList = new ArrayList<Call>();
		contactTypeList = new ArrayList<ContactType>();

		for (int i = 1; i <= 10; i++) {
			switch (i) {
			case 1 - 9:
				System.out.println("Entro con " + i);
			}
			// Creamos un string para formaterar el número de 0
			String letra = null;
			if (i >= 1 && i <= 9) {
				letra = "00" + i;
			} else if (i >= 10 && i <= 99) {
				letra = "0" + i;
			} else if (i >= 100 && i <= 999) {
				letra = "" + i;
			}
			// Creamos un contacto, una llamada y un tipo de contacto
			ContactType contactType = new ContactType(i, "TipoDeContacto"
					+ letra);
			Contact contact = new Contact(i, "Nombre" + letra, "Apellidos"
					+ letra, "Estimado" + letra, "Direccion" + letra, "Ciudad"
					+ letra, "Prov" + letra, "CodProv" + letra, "Region"
					+ letra, "Pais" + letra, "NombreCompania" + letra, "Cargo"
					+ letra, "TelefonoTrabajo" + letra, "ExtensionTrabajo"
					+ letra, "TelefonoMovil" + letra, "NumFax" + letra,
					"NomCorreoElectronico" + letra, "Notas" + letra,
					contactType);
			Call call = new Call(i, contact, "2014-10-18 01:00:00.000000",
					"Asunto" + letra, "Notas" + letra);

			if (i < 10) {
				contactList.add(contact);
				contactTypeList.add(contactType);
			}
			callList.add(call);
		}
	}

	public static CommonData getInstance() {
		if (instance == null) {
			instance = new CommonData();
		}
		return instance;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public List<Call> getCallList() {
		return callList;
	}

	public List<ContactType> getContactTypeList() {
		return contactTypeList;
	}
}