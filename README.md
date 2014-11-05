dms1415
=======

Aplicación que gestiona los datos de una agenda: Contactos, Tipo de contactos, Llamadas. 

Aplicando patrones de diseño

=====
Modificación de Alejandro 04/11/2014

Con respecto al patrón singleton en la clase BinaryFacade he modificado:
	-El atributo instance ahora es static. Si no es Static creo que el singleton no cumple su función.
	-Redefinido el método "createPersistenceFacade" haciendo que unicamente retorne instance.
		instance lo instancio al crear el objeto.
	-(No he modificado nada porque afecta a la interfaz pero lo planto aquí).
		En la interfaz tenemos un método "createPersistenceFacade()". Ya que estamos siguiendo
		la nomenclatura de java para los métodos, creo que debería llamarse "getPersistenceFacade"
		y que simplemente se dediace a hacer un return(también aparece de esta manera en los apuntes)
	