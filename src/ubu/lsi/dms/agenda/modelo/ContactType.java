package ubu.lsi.dms.agenda.modelo;

import java.io.Serializable;

/**
 * Clase de entidad con la información de TipodeContacto
 * @author Carlos López
 *
 */
@SuppressWarnings("serial")
public class ContactType implements Serializable{
	private int idTipoContacto;
	private String TipoContacto;
	
	
	public ContactType(int idTipoContacto, String tipoContacto) {
		super();
		this.idTipoContacto = idTipoContacto;
		TipoContacto = tipoContacto;
	}


	/**
	 * @return the tipoContacto
	 */
	public String getTipoContacto() {
		return TipoContacto;
	}


	/**
	 * @param tipoContacto the tipoContacto to set
	 */
	public void setTipoContacto(String tipoContacto) {
		TipoContacto = tipoContacto;
	}


	/**
	 * @return the idTipoContacto
	 */
	public int getIdTipoContacto() {
		return idTipoContacto;
	}


	@Override
	public String toString() {
		return "TipoContacto [idTipoContacto=" + idTipoContacto
				+ ", TipoContacto=" + TipoContacto + "]";
	}
	
	
	

}
