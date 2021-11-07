package com.customscoreboard.proxy;

import com.customscoreboard.client.events.RenderScoreboard;

import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) 
	{
		MinecraftForge.EVENT_BUS.register(new RenderScoreboard());
		super.preInit(e);
	}
}
