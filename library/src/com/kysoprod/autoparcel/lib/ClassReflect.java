package com.kysoprod.autoparcel.lib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * This simplify class reflection.
 * 
 * @author Benoit Deschanel
 */
class ClassReflect {

	static Field[] getFields(Class<?> oneClass) {
		return getFields(oneClass, Object.class, null, null);
	}

	static Field[] getFields(Class<?> oneClass, Class<?> toClass) {
		return getFields(oneClass, toClass, null, null);
	}

	static Field[] getFields(Class<?> oneClass, Class<?> toClass, ArrayList<Field> fields, ArrayList<String> fieldNames) {
		if (fields == null) {
			fields = new ArrayList<Field>();
		}
		if (fieldNames == null) {
			fieldNames = new ArrayList<String>();
		}

		for (Field field : oneClass.getDeclaredFields()) {
			if (!fieldNames.contains(field.getName())) {
				fields.add(field);
				fieldNames.add(field.getName());
			}
		}

		if (oneClass.getSuperclass() != null && oneClass.getSuperclass() != toClass) {
			return getFields(oneClass.getSuperclass(), toClass, fields, fieldNames);
		} else {
			Field[] result = new Field[fields.size()];
			for (int x = 0; x < fields.size(); x++) {
				result[x] = fields.get(x);
			}
			return result;
		}
	}

	static Constructor<?>[] getConstructors(Class<?> oneClass) {
		return getConstructors(oneClass, Object.class, null);
	}

	static Constructor<?>[] getConstructors(Class<?> oneClass, Class<?> toClass) {
		return getConstructors(oneClass, toClass, null);
	}

	private static Constructor<?>[] getConstructors(Class<?> oneClass, Class<?> toClass, ArrayList<Constructor> constructors) {
		if (constructors == null) {
			constructors = new ArrayList<Constructor>();
		}

		for (Constructor<?> constructor : oneClass.getConstructors()) {
			if (!constructors.contains(constructor)) {
				constructors.add(constructor);
			}
		}

		if (oneClass.getSuperclass() != null) {
			return getConstructors(oneClass.getSuperclass(), toClass, constructors);
		} else {
			Constructor<?>[] result = new Constructor[constructors.size()];
			for (int x = 0; x < constructors.size(); x++) {
				result[x] = constructors.get(x);
			}
			return result;
		}
	}

	static boolean isStatic(Field field) {
		int modifiers = field.getModifiers();
		return Modifier.isStatic(modifiers);
	}

	static boolean isTransient(Field field) {
		int modifiers = field.getModifiers();
		return Modifier.isTransient(modifiers);
	}

	static boolean isAbstract(Field field) {
		int modifiers = field.getModifiers();
		return Modifier.isAbstract(modifiers);
	}

	static boolean isFinal(Field field) {
		int modifiers = field.getModifiers();
		return Modifier.isFinal(modifiers);
	}

}
