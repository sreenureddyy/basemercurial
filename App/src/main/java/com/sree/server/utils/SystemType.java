package com.sree.server.utils;

public class SystemType
{
  private static int SystemType = 0;

  private static int MACOSX = 3;
  private static int WINDOWS = 2;
  private static int UNIX = 1;
  @SuppressWarnings("unused")
private static int UNKNOWN = 0;

  public SystemType()
  {
    String osName = SystemProperties.osName();
    if (osName.startsWith("Windows"))
    {
      PrintLogging.print(5, "We are on Windows");
      SystemType = WINDOWS;
    }
    else if (osName.compareTo("Mac OS X") == 0)
    {
      PrintLogging.print(5, "We are on MAC");
      SystemType = MACOSX;
    }
    else
    {
      PrintLogging.print(5, "We are on Unix");
      SystemType = UNIX;
    }
  }

  public static boolean isMACOSX()
  {
    return SystemType == MACOSX;
  }

  public static boolean isWINDOWS()
  {
    return SystemType == WINDOWS;
  }

  public static boolean isUNIX()
  {
    return SystemType == UNIX;
  }

  public static int getOSType()
  {
    return SystemType;
  }

  public static boolean isLINUX()
  {
    return SystemProperties.osName().compareTo("Linux") == 0;
  }

  public static boolean isSOLARIS()
  {
    return SystemProperties.osName().compareTo("SunOS") == 0;
  }

  public static boolean isAIX()
  {
    return SystemProperties.osName().compareTo("AIX") == 0;
  }

  public static boolean isHPUX()
  {
    return SystemProperties.osName().compareTo("HP-UX") == 0;
  }

  public static boolean is32bit()
  {
    return (SystemProperties.getOSArch().compareTo("i386") == 0) || (SystemProperties.getOSArch().compareTo("sparc") == 0) || (SystemProperties.getOSArch().compareTo("x86") == 0) || (SystemProperties.getOSArch().compareTo("ppc") == 0);
  }

  public static boolean is64bit()
  {
    return (SystemProperties.getOSArch().compareTo("amd64") == 0) || (SystemProperties.getOSArch().compareTo("sparcv9") == 0) || (SystemProperties.getOSArch().compareTo("ppc64") == 0) || (SystemProperties.getOSArch().startsWith("IA64"));
  }
}