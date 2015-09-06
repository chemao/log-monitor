/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * text file reader and writer
 * @author xuhf
 * @since 2015年9月2日 下午12:36:11
 * @version V1.0 
 */
public abstract class TextFileUtil {
	public static String loadFileContentAsString(String fileName) throws Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			StringBuilder stringBuilder = new StringBuilder();
			
			String line = null;
			while((line = br.readLine()) != null) {
				stringBuilder.append(line);
			}
			
			return stringBuilder.toString();
		} finally {
			if (br != null) {
				try {
					br.close();
				} finally{
					// silence close
				}
			}
		}
	}
	
	public static void saveStringToFile(String fileName, String content) throws Exception {
		// overwrite configFile
		FileWriter fileWriter = null;
		try {
			File file = new File(fileName).getParentFile();
			if (!file.exists() && !file.mkdirs()) {
				throw new Exception("create file directory error,"+file.getAbsolutePath());
			}
			fileWriter = new FileWriter(fileName);
			fileWriter.write(content);
			fileWriter.flush();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} finally{
					// silence close
				}
			}
		}
	}
	
}
