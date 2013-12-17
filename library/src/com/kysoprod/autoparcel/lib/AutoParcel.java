package com.kysoprod.autoparcel.lib;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * The Class AutoParcel.
 * 
 * This "Magic" class turn a subclass into a parcelable one.
 * 
 * WARNING : It doesn't work with an inner class 
 * @author Benoit Deschanel
 */
public abstract class AutoParcel implements Parcelable {
	
	/** The Constant TAG. */
	private static final String TAG = "AUTO_PARCEL";
	public static boolean debug = false;
	
	/**
	 * Instantiates a new auto parcel.
	 */
	public AutoParcel() {
		super();
	}

	/**
	 * Instantiates a new auto parcel.
	 *
	 * @param this constructor allows to read parcel
	 */
	public AutoParcel(Parcel in) {
		readFromParcel(in);
	}

	/** The Constant CREATOR. */
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		@Override
		public Object createFromParcel(Parcel in) {
			String oneClassName = null;
			Class oneClass = null;
			Object obj = null;

			if(debug){
				Log.i(TAG, "dataSize: " + in.dataSize());
				Log.i(TAG, "dataAvail: " + in.dataAvail());
				Log.i(TAG, "dataCapacity: " + in.dataCapacity());
				Log.i(TAG, "dataPosition: " + in.dataPosition());
			}
			try {
				oneClassName = in.readString();
				oneClass = Class.forName(oneClassName);

				obj = oneClass.newInstance();
				((AutoParcel) obj).readFromParcel(in);
			} catch (InstantiationException e) {
				if(debug)Log.e(TAG, "Unable to READ class from autoparcel", e);
			} catch (IllegalAccessException e) {
				if(debug)Log.e(TAG, "Unable to READ class from autoparcel", e);
			} catch (ClassNotFoundException e) {
				if(debug)Log.e(TAG, "Unable to READ class from autoparcel", e);
			}

			return obj;
		}

		@Override
		public AutoParcel[] newArray(int size) {
			return new AutoParcel[size];
		}
	};


	/**
	 * Read from parcel to populate object fields.
	 *
	 * @param read the parcel
	 */
	private final void readFromParcel(Parcel in) {
		boolean error = false;
		boolean isAccessible = false;

		Field[] fields = ClassReflect.getFields(this.getClass(), AutoParcel.class);
		for (Field field : fields) {
			try {
				
				int modifiers = field.getModifiers();
				if (Modifier.isFinal(modifiers)) {
					if(debug)Log.w(TAG, getFieldName(field) + " > is final, it will be ignored");
					continue;
				}
				if (Modifier.isTransient(modifiers)) {
					if(debug)Log.w(TAG, getFieldName(field) + " > is Transient, it will be ignored");
					continue;
				}

				isAccessible = field.isAccessible();
				if (!isAccessible) {
					field.setAccessible(true);
				}

				if (field.getType() == int.class || field.getType() == Integer.class) { // Integer
					
					Integer oneInt = in.readInt();
					field.set(this, oneInt);
					if (oneInt != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + oneInt);
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}

				} else if (field.getType() == String.class) { // String
					
					String string = in.readString();
					field.set(this, string);
					if (string != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + string);
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}

				} else if (field.getType() == double.class || field.getType() == Double.class) { // Double
					
					Double oneDouble = in.readDouble();
					field.set(this, oneDouble);
					if (oneDouble != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + oneDouble);
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}

				} else if (field.getType() == long.class || field.getType() == Long.class) { // Long
					
					Long oneLong = in.readLong();
					field.set(this, oneLong);
					if (oneLong != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + oneLong);
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}

				} else if (field.getType() == boolean.class || field.getType() == Boolean.class) { // Boolean
					
					if (in.readInt() == 1) {
						field.set(this, true);
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = true");
					} else {
						field.set(this, false);
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = false");
					}
					
				} else if (field.getType() == byte.class || field.getType() == Byte.class) { // Byte
					
					Byte oneByte = in.readByte();
					field.set(this,oneByte);
					if (oneByte != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + oneByte);
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}

				} else if (field.getType() == URL.class) { // URL
					
					String string = in.readString();
					if (string != null && string.length() > 0) {
						field.set(this, new URL(string) );
					} else {
						field.set(this, null);
					}
					
					if (string != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + string);
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}
					
				} else if (field.getType() == Uri.class) { // Uri
					
					String string = in.readString();
					if (string != null && string.length() > 0) {
						field.set(this, Uri.parse(string) );
					} else {
						field.set(this, null);
					}
					
					if (string != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + string);
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}

				} else if (field.getType() == URI.class) { // URI
					
					String string = in.readString();
					if (string != null && string.length() > 0) {
						field.set(this, URI.create(string) );
					} else {
						field.set(this, null);
					}
					
					if (string != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + string);
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}
					
				} else if (field.get(this) instanceof HashMap) { // Map
					
					HashMap hashmap = in.readHashMap(HashMap.class.getClassLoader());
					field.set(this, hashmap);
					if (hashmap != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + hashmap.toString());
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}

					
				} else if (field.get(this) instanceof Map) { // Map
					
					Map map = null;
					in.readMap(map, field.getType().getClassLoader());
					
					field.set(this, map);
					if (map != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + map.toString());
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}


				} else if ( isSerializable(field.getType()) ){	
					
					Serializable serializable = in.readSerializable();
					if (serializable != null ) {
						field.set(this, serializable );
					} else {
						field.set(this, null);
					}
					if (serializable != null) {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = " + serializable.toString());
					} else {
						if(debug)Log.v(TAG, "READ < " + getFieldName(field) + " = null");
					}

				} else {
					if(debug)Log.w(TAG, getFieldName(field) + " > is not compatible with AutoParcel, it will be ignored otherwise override this method to handle it");
				}

				if (!isAccessible) {
					field.setAccessible(isAccessible);
				}

			} catch (Exception e) {
				if(debug)Log.e(TAG, "Field treatment has crashed: " + getFieldName(field) + " (" + field.getType() + " / serializable:" + isSerializable(field.getType()) + " ) will be ignored", e);
				error = true;
			}
		}
		if (!error) {
			if(debug)Log.i(TAG, "READ < OK");
		} else {
			if(debug)Log.d(TAG, "READ < KO");
		}
	}

	
	private boolean isSerializable( Class<?> oneClass ){
		Class<?>[] interfaceClasses;
		interfaceClasses = oneClass.getInterfaces();
		for(Class<?> interfaceClass :interfaceClasses){
			if( interfaceClass == Serializable.class ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Write to parcel.
	 *
	 * @param dest the dest
	 * @param flags the flags
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public final void writeToParcel(Parcel dest, int flags) {
		boolean error = false;
		boolean isAccessible = false;
		
		dest.writeString(this.getClass().getName());
		Field[] fields = ClassReflect.getFields(this.getClass(), AutoParcel.class);
		for (Field field : fields) {
			try {
				
				int modifiers = field.getModifiers();
				if (Modifier.isFinal(modifiers)) {
					if(debug)Log.w(TAG, getFieldName(field) + " > is final, it will be ignored");
					continue;
				}
				if (Modifier.isTransient(modifiers)) {
					if(debug)Log.w(TAG, getFieldName(field) + " > is Transient, it will be ignored");
					continue;
				}
				isAccessible = field.isAccessible();
				if (!isAccessible) {
					field.setAccessible(true);
				}

				if (field.getType() == boolean.class || field.getType() == Boolean.class) {
					if (field.getBoolean(this)) {
						if(debug)Log.v(TAG, "WRITE > " + getFieldName(field) + " = true");
					} else {
						if(debug)Log.v(TAG, "WRITE > " + getFieldName(field) + " = false");
					}
				} else {
					if (field.get(this) != null) {
						if(debug)Log.v(TAG, "WRITE > " + getFieldName(field) + " = " + field.get(this).toString());
					} else {
						if(debug)Log.v(TAG, "WRITE > " + getFieldName(field) + " = null");
					}

				}

				if (field.getType() == int.class || field.getType() == Integer.class) { // Integer
					if (field.get(this) != null) {
						dest.writeInt(field.getInt(this));
					} else {
						dest.writeString(null);
					}

				} else if (field.getType() == String.class) { // String
					if (field.get(this) != null) {
						dest.writeString(field.get(this).toString());
					} else {
						dest.writeString(null);
					}

				} else if (field.getType() == double.class || field.getType() == Double.class) { // Double
					if (field.get(this) != null) {
						dest.writeDouble(field.getDouble(this));
					} else {
						dest.writeString(null);
					}

				} else if (field.getType() == long.class || field.getType() == Long.class) { // Long
					if (field.get(this) != null) {
						dest.writeLong(field.getLong(this));
					} else {
						dest.writeString(null);
					}

				} else if (field.getType() == boolean.class || field.getType() == Boolean.class) { // Boolean
					if (field.getBoolean(this)) {
						dest.writeInt(1);
					} else {
						dest.writeInt(0);
					}
				} else if (field.getType() == byte.class || field.getType() == Byte.class) { // Byte
					if (field.get(this) != null) {
						dest.writeByte(field.getByte(this));
					} else {
						dest.writeString(null);
					}

				} else if (field.getType() == URL.class || field.getType() == Uri.class || field.getType() == URI.class) { // URL
					if (field.get(this) != null) {
						dest.writeString(field.get(this).toString());
					} else {
						dest.writeString(null);
					}
					
				} else if (field.get(this) instanceof HashMap) { // HashMap
					if (field.get(this) != null) {
						dest.writeMap((HashMap) field.get(this));
					} else {
						dest.writeMap(null);
					}

				} else if (field.get(this) instanceof Map) { // Map
					if (field.get(this) != null) {
						dest.writeMap((Map) field.get(this));
					} else {
						dest.writeMap(null);
					}

					
				} else if ( isSerializable(field.getType()) ){
					
					if (field.get(this) != null) {
						dest.writeSerializable((Serializable) field.get(this));
					} else {
						dest.writeSerializable(null);
					}
					
				} else {
					if(debug)Log.w(TAG, getFieldName(field) + " > is not compatible with AutoParcel, it will be ignored otherwise override this method to handle it");
				}

				if (!isAccessible) {
					field.setAccessible(isAccessible);
				}

			} catch (Exception e) {
				if(debug)Log.e(TAG, "Field treatment has crashed: " + getFieldName(field) + " (" + field.getType() + " / serializable:" + isSerializable(field.getType()) + " ) will be ignored", e);
				error = true;
			}
		}
		if (!error) {
			if(debug)Log.i(TAG, "WRITE > OK");
		} else {
			if(debug)Log.e(TAG, "WRITE > KO");
		}
	}


	/**
	 * Gets the field name.
	 *
	 * @param field the field
	 * @return the field name
	 */
	private String getFieldName(Field field) {
		return this.getClass().getName() + "::" + field.getName();
	}


	/**
	 * Describe contents.
	 *
	 * @return the int
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		return 0;
	}
}
