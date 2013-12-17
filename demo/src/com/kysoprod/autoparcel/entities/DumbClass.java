package com.kysoprod.autoparcel.entities;

import com.kysoprod.autoparcel.lib.AutoParcel;


// This class is a wrapper of primitive value (container).
// It simply extends AutoParcel to become parcelable class.
// Don't need to write parcel reader/writer and CREATOR value.
// It can't be easier ;)
public class DumbClass extends AutoParcel{
	
	// Final variables will be skipped during the parcel process
	private final String varFinal = "THIS IS A FINAL VARIABLE";
	
	// You can manually skip a variable by adding 'transient' keyword
	private transient String varTransient = "this value will be skiped";
	
	
	private boolean varBool = false;
	private int varInteger = -0;
	private long varLong = 0;
	private String varString = "";
	private double varDouble = 0;
	
	public boolean isVarBool() {
		return varBool;
	}
	public void setVarBool(boolean varBool) {
		this.varBool = varBool;
	}
	public int getVarInteger() {
		return varInteger;
	}
	public void setVarInteger(int varInteger) {
		this.varInteger = varInteger;
	}
	public long getVarLong() {
		return varLong;
	}
	public void setVarLong(long varLong) {
		this.varLong = varLong;
	}
	public String getVarString() {
		return varString;
	}
	public void setVarString(String varString) {
		this.varString = varString;
	}
	public double getVarDouble() {
		return varDouble;
	}
	public void setVarDouble(double varDouble) {
		this.varDouble = varDouble;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("varBool=" + (varBool ? "true" : "false"));
		sb.append("\n");
		sb.append("varInteger=" + varInteger);
		sb.append("\n");
		sb.append("varLong=" + varLong);
		sb.append("\n");
		sb.append("varDouble=" + varDouble);
		sb.append("\n");
		sb.append("varString=" + varString);
		return sb.toString();
	}
}
