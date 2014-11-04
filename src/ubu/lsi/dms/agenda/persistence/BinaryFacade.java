package ubu.lsi.dms.agenda.persistence;

import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;

public class BinaryFacade implements PersistenceFacade {

	private PersistenceFacade instance = null;

	private BinaryFacade() {
	} // BinaryFacade

	@Override
	public PersistenceFacade createPersistenceFacade() {
		if (instance == null)
			instance = new BinaryFacade();
		return this.instance;
	} // createPersistenceFacade

	@Override
	public Contact getContact(String surname) {
		// TODO Auto-generated method stub
		return null;
	} // getContact

	@Override
	public void insertContact(Contact contact) {
		// TODO Auto-generated method stub

	} // insertContact

	@Override
	public void insertCall(Call call) {
		// TODO Auto-generated method stub

	} // insertCall

	@Override
	public void insertContactType(ContactType ct) {
		// TODO Auto-generated method stub

	} // insertContactType

	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub

	} // updateContact

	@Override
	public void updateCall(Call call) {
		// TODO Auto-generated method stub

	} // updateCall

	@Override
	public void updateContactType(ContactType ct) {
		// TODO Auto-generated method stub

	} // updateContactType

	@Override
	public List<Contact> getContactsBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	} // getContactsBySurname

	@Override
	public List<Call> getCallsByContact(Contact contacto) {
		// TODO Auto-generated method stub
		return null;
	} // getCallsByContact

	@Override
	public List<ContactType> getContactTypes() {
		// TODO Auto-generated method stub
		return null;
	} // getContactTypes

} // class BinaryFacade
