package com.sree.server.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Properties;

public class JumpStartPropertyFile
{
  private Properties Prop_list = new Properties();
  private String FileToRead = "";
  private boolean isWriteOnly = false;

  public JumpStartPropertyFile(String FileToRead, boolean isWriteOnly)
  {
    this.isWriteOnly = isWriteOnly;
    this.FileToRead = FileToRead;
    readInProperties();
  }

  public JumpStartPropertyFile(String FileToRead)
  {
    this.FileToRead = FileToRead;
    readInProperties();
  }

  private void readInProperties()
  {
    try
    {
      FileInputStream f = new FileInputStream(this.FileToRead);
      try
      {
        this.Prop_list.load(f);
      }
      catch (IOException e)
      {
        PrintLogging.print(1, "ERROR: Unable to read file " + this.FileToRead + " the JSK will be unable to continue loadings");
        System.exit(0);
      }
    }
    catch (FileNotFoundException e)
    {
      PrintLogging.print(1, "ERROR: Unable to read file " + this.FileToRead + " the JSK will be unable to continue loadings");
      System.exit(0);
    }
  }

  public boolean isWritable()
  {
    return this.isWriteOnly;
  }

  public void setWritable(boolean isWriteOnly)
  {
    this.isWriteOnly = isWriteOnly;
  }

  public String getProperty(String Prop)
  {
    return this.Prop_list.getProperty(Prop);
  }

  public void list(PrintStream out)
  {
    this.Prop_list.list(out);
  }

  public void setProperty(String Prop, String setting)
  {
    this.Prop_list.setProperty(Prop, setting);
  }

  public void saveProperties()
  {
    if (this.isWriteOnly) {
      PrintLogging.print(4, this.FileToRead + ": updated properties");
      try
      {
        this.Prop_list.store(new FileOutputStream(this.FileToRead), "updated properties");
      }
      catch (IOException e)
      {
        PrintLogging.print(4, e.toString());
      }
    }
    else
    {
      PrintLogging.print(1, this.FileToRead + ": illegal save attempt property file is listed as read only");
    }
  }

  @SuppressWarnings("rawtypes")
public Enumeration propertyNames()
  {
    return this.Prop_list.propertyNames();
  }

  @SuppressWarnings("rawtypes")
public void addProperties(JumpStartPropertyFile PropsAddition)
  {
    Enumeration pros = PropsAddition.propertyNames();

    while (pros.hasMoreElements())
    {
      String key = (String)pros.nextElement();
      String value = PropsAddition.getProperty(key);

      if (this.Prop_list.containsKey(key)) {
        PrintLogging.print(5, "Found existing entry for " + key + " in existing property file");

        if (this.Prop_list.getProperty(key).compareTo(value) != 0) {
          PrintLogging.print(4, key + " values do not match " + this.Prop_list.getProperty(key) + "<>" + value + " old value will be overwritten");
        }
        else
        {
          PrintLogging.print(5, key + "  match " + this.Prop_list.getProperty(key) + "==" + value + " old value will be overwritten");
        }
      }

      PrintLogging.print(5, "Inserting key:value pair " + key + ":" + value + " into existing property list");
      this.Prop_list.setProperty(key, value);
    }
  }
}