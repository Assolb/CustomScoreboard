package com.customscoreboard.utils;

public class RgbaColor4f {

	private float red, green, blue, alpha;
	
	public RgbaColor4f(float red, float green, float blue, float alpha) 
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public float getRed()
	{
		return red;
	}
	
	public float getGreen()
	{
		return green;
	}
	
	public float getBlue()
	{
		return blue;
	}
	
	public float getAlpha()
	{
		return alpha;
	}
}
