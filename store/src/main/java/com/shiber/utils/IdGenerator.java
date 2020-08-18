/**
 * 
 */
package com.shiber.utils;

import java.util.UUID;

/**
 * @author String
 *2020年8月16日
 */
public class IdGenerator {
	public static String getGenerator(String fileName){
		String name = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
		return name;
	}
}
