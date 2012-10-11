package com.sree.server.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class FileOperations
{
  public static void copy(String inStr, String outStr)
    throws Exception
  {
    PrintLogging.print(4, "Copying file FileIn:" + inStr + ", FileOut:" + outStr);

    File in = new File(inStr);
    File out = new File(outStr);

    if (out.exists())
    {
      delete(outStr);
    }

    FileChannel sourceChannel = new FileInputStream(in).getChannel();
    FileChannel destinationChannel = new FileOutputStream(out).getChannel();
    sourceChannel.transferTo(0L, sourceChannel.size(), destinationChannel);
    sourceChannel.close();
    destinationChannel.close();
  }

  public static void copyDirectory(File srcPath, File dstPath)
    throws IOException
  {
    PrintLogging.print(5, "Copying Directory " + srcPath + " to " + dstPath);

    if (srcPath.isDirectory())
    {
      if (!dstPath.exists())
      {
        dstPath.mkdir();
      }

      String[] files = srcPath.list();
      for (int i = 0; i < files.length; i++)
      {
        copyDirectory(new File(srcPath, files[i]), new File(dstPath, files[i]));
      }

    }
    else if (!srcPath.exists())
    {
      PrintLogging.print(4, "Directory " + srcPath + " does not exist.");
    }
    else
    {
      InputStream in = new FileInputStream(srcPath);
      OutputStream out = new FileOutputStream(dstPath);

      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0)
      {
        out.write(buf, 0, len);
      }
      in.close();
      out.close();
    }

    PrintLogging.print(5, "Directory " + srcPath + " copied.");
  }

  public static void delete(String inStr)
  {
    PrintLogging.print(5, "Deleting File:" + inStr);

    File in = new File(inStr);

    if (in.exists())
    {
      in.delete();
    }
  }

  public static boolean deleteDirectory(File dir)
  {
    PrintLogging.print(5, "Deleting Directory:" + dir.toString());

    if (!dir.exists()) return false;
    if (!dir.isDirectory()) return false;

    File candir;
    try
    {
      candir = dir.getCanonicalFile();
    }
    catch (IOException e)
    {
      return false;
    }

    if (!candir.equals(dir.getAbsoluteFile()))
    {
      return false;
    }

    File[] files = candir.listFiles();
    if (files != null)
    {
      for (int i = 0; i < files.length; i++)
      {
        File file = files[i];

        PrintLogging.print(5, "Deleting File:" + file.toString());

        boolean deleted = file.delete();
        if (deleted)
        {
          continue;
        }
        if (!file.isDirectory())
          continue;
        deleteDirectory(file);
      }

    }

    return dir.delete();
  }

  public static void createDirectory(String dir)
  {
    try
    {
      boolean success = new File(dir).mkdir();
      if (success)
      {
        PrintLogging.print(5, "Directory: " + dir + " created");
      }
      else
      {
        PrintLogging.print(1, "Warning: Failed to create Directory: " + dir);
      }
    }
    catch (Exception e)
    {
      PrintLogging.print(1, "Error: creating directory: " + e.getMessage());
    }
  }

  public static void createNestedDirectories(String dir)
  {
    try
    {
      boolean success = new File(dir).mkdirs();
      if (success)
      {
        PrintLogging.print(5, "Directories: " + dir + " created");
      }
      else
      {
        PrintLogging.print(1, "Warning: Failed to create Directories: " + dir);
      }
    }
    catch (Exception e)
    {
      PrintLogging.print(1, "Error: creating nested directory structure: " + e.getMessage());
    }
  }

  public static boolean rename(String oldname, String newname)
  {
    PrintLogging.print(4, "rename: " + oldname + " to " + newname);

    File file = new File(oldname);

    File file2 = new File(newname);

    boolean success = file.renameTo(file2);

    return !success;
  }

  public static void createFile(String filPath, String fileName, String contents)
  {
    try
    {
      PrintLogging.print(5, "Writing out to new file " + fileName + " : \n" + contents);
      BufferedWriter out = new BufferedWriter(new FileWriter(filPath + "/" + fileName));
      out.write(contents);
      out.close();
    }
    catch (IOException e)
    {
      PrintLogging.print(1, "ERROR: Unable to create a write data to " + filPath + "/" + fileName);
    }
  }
}