package com.sree.common.utils;

import java.io.File;

public class RunCommand {
	public static void main(String[] args) {
		try {
			String[] CMD = { "cmd.exe", "/C",
					"C:/1/JumpstartKit/RunTime/App_Server/jboss-portal-2.6.1.GA/bin/run.bat" };
			String[] ENV = { "JAVA_HOME=D:\\Java\\jdk1.6.0_02", "DISPLAY=:0.1",
					"HOME=/home/egandt", "SystemRoot=D:\\Windows" };
			String DIR = "C:/1/JumpstartKit/RunTime/App_Server/jboss-portal-2.6.1.GA/bin";

			Runtime rt = Runtime.getRuntime();
			System.out.println("Execing " + CMD[0] + " " + CMD[1] + " "
					+ CMD[2]);
			Process proc = rt.exec(CMD, ENV, new File(DIR));

			StreamGobbler errorGobbler = new StreamGobbler(proc
					.getErrorStream(), "ERROR");

			StreamGobbler outputGobbler = new StreamGobbler(proc
					.getInputStream(), "OUTPUT");

			errorGobbler.start();
			outputGobbler.start();

			int exitVal = proc.waitFor();
			System.out.println("ExitValue: " + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}