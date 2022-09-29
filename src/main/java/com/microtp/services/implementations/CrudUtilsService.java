package com.microtp.services.implementations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.stereotype.Service;

import com.microtp.annotations.IgnoreToLoad;
import com.microtp.annotations.RequiredField;
import com.microtp.exceptions.RequiredFieldException;

import lombok.extern.java.Log;

@Log
@Service
public class CrudUtilsService {
	
	/**
	 * Carga los valores del dto al objeto destino. 
	 * @param dto
	 * @param dest
	 * @return
	 * @throws RequiredFieldException  Se lanza en caso de que se encuentre valores nulos en atributos requeridos (@RequiredField).
	 */
	Object loadObjectWithDTOValues(Object dto, Object dest) throws RequiredFieldException{
		Class<? extends Object> destClass = dest.getClass();
		Class<? extends Object> DTOClass = dto.getClass();

		// Obtener campos definidos
		for (Field field : DTOClass.getDeclaredFields()) {
			try {
				
				String fieldName = field.getName();

				// Obtener metodo getter asociado al campo
				String getterMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

				Method getterMethod = DTOClass.getMethod(getterMethodName);

				if (getterMethod == null)
					continue;

				// Obtener valor del metodo getter del DTO
				Object value = getterMethod.invoke(dto);

				// Verificar si el campo es requerido. Lanzar exception en caso de que lo sea y
				// no tenga valor definido
				RequiredField[] requiredFieldAnnotation = field.getAnnotationsByType(RequiredField.class);
				if (value == null && requiredFieldAnnotation.length > 0) {
					throw new RequiredFieldException(field.getName());
				}
				
				//Verificar si se debe cargar el valor del campo al objeto destino
				IgnoreToLoad[] ignoreToLoadAnnotation = field.getAnnotationsByType(IgnoreToLoad.class);
				if (ignoreToLoadAnnotation.length > 0) continue;

				// Obtener metodo setter del objeto destino
				String setterMethodNameToFind = "set" + fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				Method[] setterMethods = destClass.getDeclaredMethods();
				Method setterMethod = null;
				for(Method method : setterMethods) {
					if(method.getName().equals(setterMethodNameToFind))
						setterMethod = method;
				}

				// Cargar al objeto destino con el valor obtenido del DTO
				if (setterMethod != null) {
					log.info("Method Invoked: "+setterMethodNameToFind+" | Value: "+value);
					setterMethod.invoke(dest, value);
				}
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		
		return dest;
	}
}
