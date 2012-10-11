/**
 * 
 */
package com.sree.server.utils;

import org.apache.commons.lang.StringUtils;


/**
 * @author Sree
 *
 */
public class StringOperations {
	public static Boolean goodString(String str){
		if(StringUtils.isNotBlank(str) && StringUtils.isNotEmpty(str))
			return true;
		return false;
	}
}
