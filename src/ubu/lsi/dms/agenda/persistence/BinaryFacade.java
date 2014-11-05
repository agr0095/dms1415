package ubu.lsi.dms.agenda.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;

public class BinaryFacade implements PersistenceFacade {

	// Instancia de la propia clase
	private PersistenceFacade instance = null;

	// Los ficheros de persistencia
	private final File calls;
	private final File contacts;
	private final File contactTypes;

	private BinaryFacade() {
		calls = new File(".\\rsc\\calls.dat");
		contacts = new File(".\\rsc\\contacts.dat");
		contactTypes = new File(".\\rsc\\contactTypes.dat");
	} // BinaryFacade

	@Override
	public PersistenceFacade createPersistenceFacade() {
		if (instance == null)
			instance = new BinaryFacade();
		return this.instance;
	} // createPersistenceFacade

	@Override
	public List<Call> getCallsByContact(Contact contact) {
		List<Call> callList = new ArrayList<Call>();
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(calls));
			callList = (ArrayList<Call>) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		}

		List<Call> callsByContact = new ArrayList<Call>();
		for (Call c : callList)
			if (c.getContacto().compareTo(contact) == 0)
				callsByContact.add(c);
		return callsByContact;
	} // getCallsByContact

	@Override
	public Contact getContact(String surname) {
		List<Contact> contactList = new ArrayList<Contact>();
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			contactList = (ArrayList<Contact>) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		}

		for (Contact c : contactList)
			if (c.getApellidos().compareTo(surname) == 0)
				return c;
		return null;
	} // getContact

	@Override
	public List<Contact> getContactsBySurname(String surname) {
		List<Contact> contactList = new ArrayList<Contact>();
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			contactList = (ArrayList<Contact>) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		}

		List<Contact> contactsBySurname = new ArrayList<Contact>();
		for (Contact c : contactList)

			if (c.getApellidos().compareTo(surname) == 0)
				contactsBySurname.add(c);
		return contactsBySurname;
	} // getContactsBySurname

	@Override
	public List<ContactType> getContactTypes() {
		List<ContactType> contactTypes = new ArrayList<ContactType>();
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			contactTypes = (ArrayList<ContactType>) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		}
		return contactTypes;
	} // getContactTypes

	@Override
	public void insertCall(Call call) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(calls));
			out.writeObject(call);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		}
		
	} // insertCall

	@Override
	public void insertContact(Contact contact) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(contacts));
			out.writeObject(contact);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		}

	} // insertContact

	@Override
	public void insertContactType(ContactType ct) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(contactTypes));
			out.writeObject(ct);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		}

	} // insertContactType

	@Override
	public void updateCall(Call call) {
		// TODO Auto-generated method stub

	} // updateCall

	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub

	} // updateContact

	@Override
	public void updateContactType(ContactType ct) {
		// TODO Auto-generated method stub

	} // updateContactType

} // class BinaryFacade
