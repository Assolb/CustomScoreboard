package com.customscoreboard.main;

import com.customscoreboard.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CustomScoreboard.MODID, name = CustomScoreboard.NAME, version = CustomScoreboard.VERSION)
public class CustomScoreboard {

	public static final String MODID = "customscoreboard";
	public static final String NAME = "Custom Scoreboard Mod";
	public static final String VERSION = "1.0.0";
	
	@SidedProxy(clientSide = "com.customscoreboard.proxy.ClientProxy", serverSide = "com.customscoreboard.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		proxy.preInit(event);
    }
}
