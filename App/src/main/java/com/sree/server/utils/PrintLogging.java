package com.sree.server.utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.SimpleLayout;

public class PrintLogging
{
  @SuppressWarnings("unused")
private static int LogLevel = 1;
  public static final int TRACE = 5;
  public static final int DEBUG = 4;
  public static final int WARN = 3;
  public static final int ERROR = 2;
  public static final int INFO = 1;
  public static final int NONE = 0;
  public static final int TESTING = -1;
  private static Logger jskLogger = null;

  public static void setLogLevel(String message, String logFile)
  {
    if (jskLogger != null)
    {
      BasicConfigurator.resetConfiguration();
    }

    BasicConfigurator.configure();
    jskLogger = Logger.getRootLogger();

    jskLogger.removeAllAppenders();

    PatternLayout layout = new PatternLayout("[%t] %-5p %x - %m%n");
    FileAppender appenderFile = null;

    if (logFile.compareTo("UNSET") != 0)
    {
      try
      {
        appenderFile = new FileAppender(layout, SystemProperties.getCurrentPath() + "/" + logFile, false, false, 0);

        jskLogger.addAppender(appenderFile);
      }
      catch (Exception e)
      {
        System.out.println("ERROR: Unable to create file output for log4j, outputting to commandline");
      }
    }
    else
    {
      jskLogger.addAppender(new ConsoleAppender(new SimpleLayout()));
    }

    if (message.compareTo("TRACE") == 0)
    {
      jskLogger.setLevel(Level.TRACE);
    }
    else if (message.compareTo("DEBUG") == 0)
    {
      jskLogger.setLevel(Level.DEBUG);
    }
    else if (message.compareTo("ERROR") == 0)
    {
      jskLogger.setLevel(Level.ERROR);
    }
    else if (message.compareTo("WARN") == 0)
    {
      jskLogger.setLevel(Level.WARN);
    }
    else if (message.compareTo("INFO") == 0)
    {
      jskLogger.setLevel(Level.INFO);
    }
    else if (message.compareTo("NONE") == 0)
    {
      jskLogger.setLevel(Level.OFF);
    }
  }

  public static String getLogLevel()
  {
    return jskLogger.getLevel().toString();
  }

  public static void print(String message)
  {
    String threadName = Thread.currentThread().getName();
    jskLogger.info("thread " + threadName + ": " + message);
  }

  public static void print(Boolean message)
  {
    String threadName = Thread.currentThread().getName();
    jskLogger.info("thread " + threadName + ": " + message);
  }

  public static void print(int Level, String message)
  {
    if (jskLogger == null)
    {
      System.out.println("Waring: message to log, " + message);
    }

    switch (Level)
    {
    case 4:
      jskLogger.debug(message);
      break;
    case 5:
      jskLogger.trace(message);
      break;
    case 1:
      jskLogger.info(message);
      break;
    case 3:
      jskLogger.warn(message);
      break;
    case 2:
      jskLogger.error(message);
      break;
    }
  }

  public static void print(int Level, Boolean message)
  {
    String threadName = Thread.currentThread().getName();
    switch (Level)
    {
    case 4:
      jskLogger.debug("thread " + threadName + ": " + message.toString());
      break;
    case 5:
      jskLogger.trace("thread " + threadName + ": " + message.toString());
      break;
    case 1:
      jskLogger.info("thread " + threadName + ": " + message.toString());
      break;
    case 3:
      jskLogger.warn("thread " + threadName + ": " + message.toString());
      break;
    case 2:
      jskLogger.error("thread " + threadName + ": " + message.toString());
      break;
    }
  }
}