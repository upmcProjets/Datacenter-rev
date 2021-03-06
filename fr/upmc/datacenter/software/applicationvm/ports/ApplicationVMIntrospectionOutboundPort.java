package fr.upmc.datacenter.software.applicationvm.ports;

import java.util.Map;

import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;
import fr.upmc.datacenter.software.applicationvm.ApplicationVM.ApplicationVMPortTypes;
import fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMDynamicStateI;
import fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMIntrospectionI;
import fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMStaticStateI;

/**
 * The class <code>ApplicationVMIntrospectionOutboundPort</code>
 *
 * <p><strong>Description</strong></p>
 * 
 * <p><strong>Invariant</strong></p>
 * 
 * <pre>
 * invariant	true
 * </pre>
 * 
 * <p>Created on : 5 oct. 2015</p>
 * 
 * @author	<a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
 * @version	$Name$ -- $Revision$ -- $Date$
 */
public class			ApplicationVMIntrospectionOutboundPort
extends		AbstractOutboundPort
implements	ApplicationVMIntrospectionI
{

	public				ApplicationVMIntrospectionOutboundPort(
		ComponentI owner
		) throws Exception
	{
		super(ApplicationVMIntrospectionI.class, owner);
	}

	public				ApplicationVMIntrospectionOutboundPort(
		String uri,
		ComponentI owner
		) throws Exception
	{
		super(uri, ApplicationVMIntrospectionI.class, owner);
	}

	/**
	 * @see fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMIntrospectionI#getAVMPortsURI()
	 */
	@Override
	public Map<ApplicationVMPortTypes, String>	getAVMPortsURI()
	throws Exception
	{
		return ((ApplicationVMIntrospectionI)this.connector).getAVMPortsURI() ;
	}

	/**
	 * @see fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMIntrospectionI#getStaticState()
	 */
	@Override
	public ApplicationVMStaticStateI	getStaticState()
	throws Exception
	{
		return ((ApplicationVMIntrospectionI)this.connector).getStaticState() ;
	}

	/**
	 * @see fr.upmc.datacenter.software.applicationvm.interfaces.ApplicationVMIntrospectionI#getDynamicState()
	 */
	@Override
	public ApplicationVMDynamicStateI	getDynamicState()
	throws Exception
	{
		return ((ApplicationVMIntrospectionI)this.connector).getDynamicState() ;
	}
}
