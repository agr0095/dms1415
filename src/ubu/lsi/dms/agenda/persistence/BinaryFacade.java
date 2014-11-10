package ubu.lsi.dms.agenda.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;

@SuppressWarnings("unchecked")
public class BinaryFacade implements PersistenceFacade {

	// Instancia de la propia clase
	private static final PersistenceFacade instance = new BinaryFacade();

	// Los ficheros de persistencia
	private final File calls;
	private final File contacts;
	private final File contactTypes;

	private BinaryFacade() {
		calls = new File(".\\rsc\\calls.dat");
		contacts = new File(".\\rsc\\contacts.dat");
		contactTypes = new File(".\\rsc\\contactTypes.dat");
	} // BinaryFacade

	/**
	 * Devuelve una referencia a la instancia de la propia clase. La referencia
	 * es a un objeto BinaryFacade.
	 * 
	 * @return instancia de BinaryFacade.
	 */
	public static PersistenceFacade getInstance() {
		return instance;
	} // getInstance

	@Override
	public List<Call> getCallsByContact(Contact contact) {
		List<Call> callList = new ArrayList<Call>();
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(calls));
			callList = loadCalls();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		List<Call> callsByContact = new ArrayList<Call>();
		for (Call c : callList)
			if (c.getContacto().equals(contact))
				callsByContact.add(c);
		return callsByContact;
	} // getCallsByContact

	@Override
	public Contact getContact(String surname) {
		List<Contact> contactList = new ArrayList<Contact>();
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			contactList = (ArrayList<Contact>) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		for (Contact c : contactList)
			if (c.getApellidos().equals(surname))
				return c;
		return null;
	} // getContact

	@Override
	public List<Contact> getContactsBySurname(String surname) {
		List<Contact> contactList = new ArrayList<Contact>();
		List<Contact> contactsBySurname = new ArrayList<Contact>();
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			contactList = loadContacts();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		for (Contact c : contactList)

			if (c.getApellidos().equals(surname))
				contactsBySurname.add(c);
		
		return contactsBySurname;
	} // getContactsBySurname

	@Override
	public List<ContactType> getContactTypes() {
		List<ContactType> contactTypes = new ArrayList<ContactType>();
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			contactTypes = loadContactTypes();
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return contactTypes;
	} // getContactTypes

	@Override
	public void insertCall(Call call) {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		List<Call> allCalls=new ArrayList<Call>();
		try {
			if (calls.exists())
				// Take everything from the files
				allCalls = loadCalls();
			out = new ObjectOutputStream(new FileOutputStream(calls));
			allCalls.add(call);
			out.writeObject(allCalls);
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	} // insertCall

	private List<Call> loadCalls() {
		List<Call> allCalls = new ArrayList<Call>();
		ObjectInputStream in = null;

		try {
			in = new ObjectInputStream(new FileInputStream(calls));		
			allCalls =(ArrayList<Call>) in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allCalls;
	}

	@Override
	public void insertContact(Contact contact) {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		List<Contact> allContacts=new ArrayList<Contact>();
		
		try {
			if (contacts.exists())
				// Take everything from the files
				allContacts = loadContacts();
			out = new ObjectOutputStream(new FileOutputStream(contacts));
			allContacts.add(contact);
			out.writeObject(allContacts);
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} // insertContact

	private List<Contact> loadContacts() {
		List<Contact> allContacts = new ArrayList<Contact>();
		ObjectInputStream in = null;

		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			allContacts =(ArrayList<Contact>) in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allContacts;
	}
	
	
	@Override
	public void insertContactType(ContactType ct) {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		List<ContactType> allCT=new ArrayList<ContactType>();
		
		try {
			if (contactTypes.exists())
				// Take everything from the files
				allCT = loadContactTypes();
			out = new ObjectOutputStream(new FileOutputStream(contactTypes));
			allCT.add(ct);
			out.writeObject(allCT);
		} catch (IOException e) {
			// TODO Se puede utilizar herramienta de logging
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	} // insertContactType
	
	private List<ContactType> loadContactTypes() {
		List<ContactType> allCT = new ArrayList<ContactType>();
		ObjectInputStream in = null;

		try {
			in = new ObjectInputStream(new FileInputStream(contactTypes));

			allCT =(ArrayList<ContactType>) in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allCT;
	}

	@Override
	public void updateCall(Call call) {

		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean contactFound = false;
		List<Call> allCalls = new ArrayList<Call>();

		// Read all the contacts
		try {
			if (calls.exists()) {
				in = new ObjectInputStream(new FileInputStream(calls));
				allCalls = loadCalls();
				// Look for a contact with similar ID and
				// ,if we find it, replace the contact with new information
				for (int i = 0; i < allCalls.size() && !contactFound; i++) {
					if (allCalls.get(i).getIdLlamada() == call.getIdLlamada()) {
						allCalls.remove(i);
						allCalls.add(i, call);
						contactFound = true;
					}
				}

				// Store the new data again
				out = new ObjectOutputStream(new FileOutputStream(calls));
				out.writeObject(allCalls);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	} // updateCall

	@Override
	public void updateContact(Contact contact) {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean contactFound = false;
		List<Contact> allContacts;

		// Read all the contacts
		try {
			in = new ObjectInputStream(new FileInputStream(contacts));
			allContacts = loadContacts();
			// Look for a contact with similar ID and
			// ,if we find it, replace the contact with new information
			for (int i = 0; i < allContacts.size() && !contactFound; i++) {
				if (allContacts.get(i).getIdContacto() == contact
						.getIdContacto()) {
					allContacts.remove(i);
					allContacts.add(i, contact);
					contactFound = true;
				}
			}

			// Store the new data again
			out = new ObjectOutputStream(new FileOutputStream(contacts));
			out.writeObject(allContacts);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} // updateContact

	@Override
	public void updateContactType(ContactType ct) {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		boolean contactFound = false;
		List<ContactType> allCT;

		// Read all the contacts
		try {
			in = new ObjectInputStream(new FileInputStream(contactTypes));
			allCT = loadContactTypes();
			// Look for a contact with similar ID and
			// ,if we find it, replace the contact with new information
			for (int i = 0; i < allCT.size() && !contactFound; i++) {
				if (allCT.get(i).getIdTipoContacto() == ct.getIdTipoContacto()) {
					allCT.remove(i);
					allCT.add(i, ct);
					contactFound = true;
				}
			}

			// Store the new data again
			out = new ObjectOutputStream(new FileOutputStream(contactTypes));
			out.writeObject(allCT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (in != null)
					in.close();
				
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} // updateContactType

} // class BinaryFacade
