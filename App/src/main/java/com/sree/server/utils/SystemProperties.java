package com.sree.server.utils;

import java.io.File;

public class SystemProperties
{
  private static String tempDir = null;

  public static String getOSArch()
  {
    return System.getProperty("os.arch");
  }

  private static void createTempPath()
  {
    String tempPath = System.getProperty("java.io.tmpdir");
    Long timeInMillis = Long.valueOf(System.currentTimeMillis());
    tempPath = tempPath.replaceAll("\\\\", "/");

    tempPath = tempPath + "/" + timeInMillis.toString();

    FileOperations.createDirectory(tempPath);

    tempDir = tempPath;
  }

  public static String getTempPath()
  {
    if (tempDir != null)
    {
      return tempDir;
    }

    createTempPath();
    return tempDir;
  }

  public static String javaHome(){
	  String S = System.getenv("JAVA_HOME");
	  if(S==null){
		  S = SystemProperties.getCurrentPath()+File.separator+"jdk1.6.0_27";
	  }
	  return S;
  }
  
  public static String javaJre()
  {
    String S = System.getProperty("java.home");
    System.out.println(S);
    if (SystemType.isWINDOWS())
    {
      PrintLogging.print(5, "We are on Windows");
      int i = S.lastIndexOf("\\jre");
      if (i != -1)
      {
        S = S.substring(0, i);
      }
      else
      {
        PrintLogging.print(1, "WARNING: JAVA_HOME may not point to a valid JDK\n");
      }
    }
    else if (!SystemType.isMACOSX())
    {
      int i = S.lastIndexOf("/jre");
      if (i != -1)
      {
        S = S.substring(0, i);
      }
      else
      {
        PrintLogging.print(1, "WARNING: JAVA_HOME may not point to a valid JDK\n");
      }
    }
    PrintLogging.print(4, "JAVA HOME is being set to " + S);
    return S;
  }

  public static String osName()
  {
    return System.getProperty("os.name");
  }

  public static String xDisplay()
  {
    String S = System.getProperty("display");
    if (S == null)
    {
      PrintLogging.print(1, "WARNING: Guessed the XDisplay to be :0.0 please pass in this parameter using -Ddisplay");

      return ":0.0";
    }

    return S;
  }

  public static String SystemRoot()
  {
    String S = System.getProperty("SystemRoot");
    if (S == null)
    {
      PrintLogging.print(1, "WARNING: Guessed the SystemRoot to be C:\\Windows please pass in this parameter using -DSystemRoot");

      return "C:\\Windows";
    }

    return S;
  }

  public static String userHome()
  {
    String S = System.getProperty("home");
    if (S == null)
    {
      S = "/home/" + System.getProperty("user.name");
      PrintLogging.print(1, "WARNING: Guessed the home directory to be " + S + " please pass in this parameter using -Dhome");
      return S;
    }

    return S;
  }

  public static String getCurrentPath()
  {
    String localPath = System.getProperty("user.dir");
    localPath = localPath.replaceAll("\\\\", "/");
    return localPath;
  }

  public static String getWindowsPath()
  {
    return System.getProperty("java.library.path");
  }
  
  public static String getSystemEnv(String env){
	  return System.getenv(env);
  }
  
  public static String getSystemProperty(String property){
	  return System.getProperty(property);
  }
  
  
  
}