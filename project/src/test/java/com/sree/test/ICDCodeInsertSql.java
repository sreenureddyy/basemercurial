/**
 * 
 */
package com.sree.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sree.icd.domain.ICDCode;

/**
 * @author srinivasr
 * 
 */
public class ICDCodeInsertSql extends BaseTest {
	private Logger log = Logger.getLogger(ICDCodeInsertSql.class);

	@Test
	public void testCase() {
		long start = System.currentTimeMillis();
		try {

			/*
			 * Sets up a file reader to read the file passed on the command line
			 * one character at a time
			 */
			FileReader input = new FileReader("icd10cm_desc_2011.txt");
			FileWriter fstream = new FileWriter("out.txt");

			//BufferedWriter out = new BufferedWriter(fstream);

			/*
			 * Filter FileReader through a Buffered read to read a line at a
			 * time
			 */
			BufferedReader bufRead = new BufferedReader(input);

			String line; // String that holds current file line
			int count = 1; // Line number of count

			// Read first line
			line = bufRead.readLine();
			count++;

			// Read through file one line at time. Print line # and line
			ICDCode code = new ICDCode();
			while (line != null) {
				log.info("------------------------------------------------\n");
				//out.write("------------------------------------------------\n"); 
				//out.write(line + "\n");
				//code.setId(new Long(count));
				code.setCreatedBy("admin");
				code.setCreatedDatetime(new Date());
				code.setUpdatedBy("admin");
				code.setUpdatedDatetime(new Date());
				line = line.replaceFirst("\\s", "qqqqqq");
				String[] result = line.split("qqqqqq");

				for (int i = 0; i < result.length; i++) {
					//out.write(result[i].trim() + "\n");
					if(i==0){
						code.setCode(result[i].trim());
					}
					if(i==1){
						code.setDescription(result[i].trim());
					}
				}
				
				log.info(line + "\n");
				log.info(code.getCode()+ "\n");
				log.info(code.getDescription()+ "\n");
				line = bufRead.readLine();
				baseService.save(code);
				code = new ICDCode();
				count++;
			}

			bufRead.close();
			fstream.close();
			System.out.println("total records ::: "+count);
			System.out.println(" total time taken ::: " + (System.currentTimeMillis() - start) / 1000F);
		} catch (ArrayIndexOutOfBoundsException e) {
			/*
			 * If no file was passed on the command line, this expception is
			 * generated. A message indicating how to the class should be called
			 * is displayed
			 */
			System.out.println("Usage: java ReadFile filename\n");

		} catch (IOException e) {
			// If another exception is generated, print a stack trace
			e.printStackTrace();
		}
	}
}
