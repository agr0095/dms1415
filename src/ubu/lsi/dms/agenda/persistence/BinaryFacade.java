package ubu.lsi.dms.agenda.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;

@SuppressWarnings("unchecked")
/**
 * Class that creates a binary persistence facade.
 * Facade pattern is applied to this class.
 * Singleton pattern is applied to this class.
 * @author <a href="mailto:agr0095@alu.ubu.es">Alejandro González Rogel</a>
 * @author <a href="mailto:ppp0015@alu.ubu.es">Plamen Petyov Petkov</a>
 */
public class BinaryFacade implements PersistenceFacade {

	/**
	 * Returns the reference to a BinaryFacade instance.
	 * 
	 * @return BinaryFacade instance.
	 */
	public static BinaryFacade getInstance() {
		if(instance == null)
			instance = new BinaryFacade();
		return instance;
	} // getInstance

	/**
	 * Self-instance.
	 */
	private static BinaryFacade instance = null;
	// Persistence file paths
	/**
	 * Calls persistence file path.
	 */
	private final File calls;
	/**
	 * Contacts persistence file path.
	 */
	private final File contacts;

	/**
	 * ContactTypes persistence file path.
	 */
	private final File contactTypes;

	/**
	 * Log messages about the BinaryFacade insertions
	 */
	Logger logger = Logger.getLogger("ubu.lsi.dms.agenda.persistence");

	/**
	 * Private constructor. Initialize our file paths.
	 */
	private BinaryFacade() {
		calls = new File(".\\rsc\\calls.dat");
		contacts = new File(".\\rsc\\contacts.dat");
		contactTypes = new File(".\\rsc\\contactTypes.dat");
	} // BinaryFacade

	@Override
	public List<Call> getCallsByContact(Contact contact) {
		List<Call> listOfCalls = new ArrayList<Call>();
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(calls));
			listOfCalls = loadFile(calls);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<Call> callsByContact = new ArrayList<Call>();
		for (Call c : listOfCalls)
			if (c.getContacto().equals(contact))
				callsByContact.add(c);
		return callsByContact;
	} // getCallsByContact

	@Override
	public Contact getContact(String surname) {
		List<Contact> listOfContacts = new ArrayList<Contact>();
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			listOfContacts = (ArrayList<Contact>) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (Contact c : listOfContacts)
			if (c.getApellidos().equals(surname))
				return c;
		return null;
	} // getContact

	@Override
	public List<Contact> getContactsBySurname(String surname) {
		List<Contact> listOfContacts = new ArrayList<Contact>();
		List<Contact> contactsBySurname = new ArrayList<Contact>();
		ObjectInputStream in = null;

		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			listOfContacts = loadFile(contacts);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (Contact c : listOfContacts)
			if (c.getApellidos().equals(surname))
				contactsBySurname.add(c);

		return contactsBySurname;
	} // getContactsBySurname

	@Override
	public List<ContactType> getContactTypes() {
		List<ContactType> listOfCTs = new ArrayList<ContactType>();
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(contactTypes));
			listOfCTs = loadFile(contactTypes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listOfCTs;
	} // getContactTypes

	@Override
	public void insertCall(Call call) {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		List<Call> listOfCalls = new ArrayList<Call>();
		List<Contact> listOfContacts = new ArrayList<Contact>();
		boolean contactFound = false;

		try {
			if (calls.exists())
				// Take everything from the files
				listOfCalls = loadFile(calls);
			out = new ObjectOutputStream(new FileOutputStream(calls));
			listOfCalls.add(call);
			out.writeObject(listOfCalls);
			// We look for if the contact who called us is already stored in our
			// agenda.
			if (contacts.exists()) {
				listOfContacts = loadFile(contacts);
				for (Contact c : listOfContacts)
					if (c.getIdContacto() == call.getContacto().getIdContacto())
						contactFound = true;
			}
			// If the contact isn't there we add it
			if (!contactFound) {
				insertContact(call.getContacto());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();

				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // insertCall

	@Override
	public void insertContact(Contact contact) {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		List<Contact> listOfContacts = new ArrayList<Contact>();

		try {
			if (contacts.exists())
				// Take everything from the files
				listOfContacts = loadFile(contacts);
			out = new ObjectOutputStream(new FileOutputStream(contacts));
			listOfContacts.add(contact);
			out.writeObject(listOfContacts);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();

				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // insertContact

	@Override
	public void insertContactType(ContactType ct) {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		List<ContactType> listOfCTs = new ArrayList<ContactType>();

		try {
			if (contactTypes.exists())
				// Take everything from the files
				listOfCTs = loadFile(contactTypes);
			out = new ObjectOutputStream(new FileOutputStream(contactTypes));
			listOfCTs.add(ct);
			out.writeObject(listOfCTs);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();

				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // insertContactType

	/**
	 * Loads all the objects the file has stored.
	 * 
	 * @param file
	 *            we want to load
	 * @return List of loaded objects
	 */
	private <T> List<T> loadFile(File file) {
		List<T> list = new ArrayList<T>();
		ObjectInputStream in = null;

		try {
			in = new ObjectInputStream(new FileInputStream(file));
			list = (ArrayList<T>) in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	} // loadFile

	@Override
	public void updateCall(Call call) {

		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean callFound = false;
		List<Call> listOfCalls = new ArrayList<Call>();

		// Read all the contacts
		try {
			if (calls.exists()) {
				in = new ObjectInputStream(new FileInputStream(calls));
				listOfCalls = loadFile(calls);
				// Look for a call with similar ID and
				// ,if we find it, replace the contact with new information
				for (int i = 0; i < listOfCalls.size() && !callFound; i++) {
					if (listOfCalls.get(i).getIdLlamada() == call
							.getIdLlamada()) {
						listOfCalls.remove(i);
						listOfCalls.add(i, call);
						callFound = true;
					}
				}

				if (callFound) {
					// Store the new data again
					out = new ObjectOutputStream(new FileOutputStream(calls));
					out.writeObject(listOfCalls);
				} else {
					logger.info("We couldn't update the given call. It doesn't exist.");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();

				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // updateCall

	@Override
	public void updateContact(Contact contact) {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean contactFound = false;
		List<Contact> listOfContacts;

		// Read all the contacts
		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			listOfContacts = loadFile(contacts);
			// Look for a contact with similar ID and
			// ,if we find it, replace the contact with new information
			for (int i = 0; i < listOfContacts.size() && !contactFound; i++) {
				if (listOfContacts.get(i).getIdContacto() == contact
						.getIdContacto()) {
					listOfContacts.remove(i);
					listOfContacts.add(i, contact);
					contactFound = true;
				}
			}
			if (contactFound) {
				// Store the new data again
				out = new ObjectOutputStream(new FileOutputStream(contacts));
				out.writeObject(listOfContacts);
			} else {
				logger.info("We couldn't update the given contact. It doesn't exist.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();

				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // updateContact

	@Override
	public void updateContactType(ContactType ct) {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean contactFound = false;
		List<ContactType> listOfCTs;

		// Read all the contacts
		try {
			in = new ObjectInputStream(new FileInputStream(contactTypes));
			listOfCTs = loadFile(contactTypes);
			// Look for a contact with similar ID and
			// ,if we find it, replace the contact with new information
			for (int i = 0; i < listOfCTs.size() && !contactFound; i++) {
				if (listOfCTs.get(i).getIdTipoContacto() == ct
						.getIdTipoContacto()) {
					listOfCTs.remove(i);
					listOfCTs.add(i, ct);
					contactFound = true;
				}
			}

			if (contactFound) { // Store the new data again
				out = new ObjectOutputStream(new FileOutputStream(contactTypes));
				out.writeObject(listOfCTs);
			} else {
				logger.info("We couldn't update the given contact type. It doesn't exist.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // We close all the Streams we may have opened.
			try {
				if (in != null)
					in.close();

				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // updateContactType

} // class BinaryFacade
