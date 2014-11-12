package ubu.lsi.dms.agenda.persistence;

/**
 * 
 * 
 * @author <a href="mailto:agr0095@alu.ubu.es">Alejandro González Rogel</a>
 * @author <a href="mailto:ppp0015@alu.ubu.es">Plamen Petyov Petkov</a>
 */
public class BinaryFactory implements PersistenceFactory {

	private static PersistenceFactory instance = new BinaryFactory();

	private BinaryFactory() {
	}


	@Override
	public PersistenceFacade createPersistenceFacade() {
		return BinaryFacade.getInstance();
	} // createPersistenceFacade

	/**
	 * Returns a BinaryFactory instance.
	 * 
	 * @return
	 */
	public static PersistenceFactory getInstance() {
		return instance;
	}

} // class BinaryFactory
