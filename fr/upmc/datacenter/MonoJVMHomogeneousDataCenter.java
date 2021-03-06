package fr.upmc.datacenter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.upmc.components.cvm.AbstractCVM;
import fr.upmc.datacenter.hardware.computers.Computer;

/**
 * The class <code>MonoJVMDataCenter</code> .
 *
 * <p><strong>Description</strong></p>
 * 
 * <p><strong>Invariant</strong></p>
 * 
 * <pre>
 * invariant	true
 * </pre>
 * 
 * <p>Created on : August 26, 2015</p>
 * 
 * @author	<a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
 * @version	$Name$ -- $Revision$ -- $Date$
 */
public class			MonoJVMHomogeneousDataCenter
extends		AbstractCVM
{
	// ------------------------------------------------------------------------
	// Constants and instance variables
	// ------------------------------------------------------------------------

	public static final int		NUMBER_OF_COMPUTERS = 10 ;
	public static final int		NUMBER_OF_PROCESSORS_PER_COMPUTER = 2 ;
	public static final int		NUMBER_OF_CORES_PER_PROCESSOR = 4 ;

	// Predefined URI of the different ports visible at the component assembly
	// level.
	public static final String	ComputerServicesInboundPortURIPrefix = "cs-ibp-" ;
	public static final String	ComputerServicesOutboundPortURIPrefix = "cs-obp" ;
	public static final String	ComputerStaticStateDataInboundPortURIPrefix = "css-dip-" ;
	public static final String	ComputerStaticStateDataOutboundPortURIPrefix = "css-dop-" ;
	public static final String	ComputerDynamicStateDataInboundPortURIPrefix = "cds-dip-" ;
	public static final String	ComputerDynamicStateDataOutboundPortURIPrefix = "cds-dop" ;

	protected int					defautFrequency ;
	protected int					maxFrequencyGap ;
	protected Set<Integer>			admissibleFrequencies ;
	protected Map<Integer,Integer>	processingPower ;
	protected Computer[]			computers ;

	// ------------------------------------------------------------------------
	// Component virtual constructor
	// ------------------------------------------------------------------------

	public				MonoJVMHomogeneousDataCenter(
		Set<Integer> admissibleFrequencies,
		int defautFrequency,
		int maxFrequencyGap,
		Map<Integer, Integer> processingPower
		) throws Exception
	{
		super();
		this.admissibleFrequencies = new HashSet<Integer>() ;
		for (Integer f : admissibleFrequencies) {
			this.admissibleFrequencies.add((Integer) f) ;
		}
		this.defautFrequency = defautFrequency ;
		this.maxFrequencyGap = maxFrequencyGap ;
		this.processingPower = new HashMap<Integer,Integer>() ;
		for (Integer fr : processingPower.keySet()) {
			this.processingPower.put(fr, processingPower.get(fr)) ;
		}

		this.computers = new Computer[NUMBER_OF_COMPUTERS] ;
	}

	// ------------------------------------------------------------------------
	// Component virtual machine methods
	// ------------------------------------------------------------------------

	@Override
	public void			deploy() throws Exception
	{
		for(int c = 0 ; c < NUMBER_OF_COMPUTERS ; c++) {
			// ----------------------------------------------------------------
			// Create and deploy a computer component with its processors.
			// ----------------------------------------------------------------
			this.computers[c] = new Computer(
					"computer-" + c,
					this.admissibleFrequencies,
					this.processingPower,  
					this.defautFrequency,
					this.maxFrequencyGap,		// max frequency gap within a processor
					NUMBER_OF_PROCESSORS_PER_COMPUTER,
					NUMBER_OF_CORES_PER_PROCESSOR,
					ComputerServicesInboundPortURIPrefix + c,
					ComputerStaticStateDataInboundPortURIPrefix + c,
					ComputerDynamicStateDataInboundPortURIPrefix + c) ;
			this.addDeployedComponent(this.computers[c]) ;
		}

		// allow to complete the deployment in a subclass
		this.completeDeployment() ;
		// close the deployment at the component virtual machine level.
		super.deploy();

	}

	public void			completeDeployment() {
		
	}
}
