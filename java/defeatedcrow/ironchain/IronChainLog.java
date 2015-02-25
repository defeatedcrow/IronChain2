package defeatedcrow.ironchain;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLLog;

public class IronChainLog {
	
	public static final Logger logger = LogManager.getLogger("DCIronChain");
	
	public static void loadModInfo(String modid) {
		logger.trace("Succeeded to check other mod :" + modid);
	}
	
	public static void debugTrace(String msg) {
		if (DCsIronChain.debug) {
			logger.info(msg);
		}
	}
	
	public static void info(String msg) {
		logger.info(msg);
	}

}
