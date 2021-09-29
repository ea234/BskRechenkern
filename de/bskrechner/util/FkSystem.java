package de.bskrechner.util;

import java.lang.management.ManagementFactory;
import java.util.Calendar;

public class FkSystem
{
  private static final String STD_ROOT_VERZEICHNIS_VORGABE_SYSTEM_NICHT_ERMITTELBAR = null;

  private static final String STD_ROOT_VERZEICHNIS_LINUX                            = "/media/HD4TBB/Daten_Linux/00_Daten/";

  private static final String STD_ROOT_VERZEICHNIS_WINDOWS                          = "c:/Daten/";

  private static final String STR_SYSTEM_OS_NAME_WIN                                = "win";

  private static final String STR_SYSTEM_OS_NAME_MAC                                = "mac";

  private static final String STR_SYSTEM_OS_NAME_NIX                                = "nix";

  private static final String STR_SYSTEM_OS_NAME_NUX                                = "nux";

  private static final String STR_SYSTEM_OS_NAME_AIX                                = "aix";

  private static final String STR_SYSTEM_OS_NAME_SUNOS                              = "sunos";

  private static final String STR_SYSTEM_OS_NAME_OSX                                = "osx";

  private static final String STR_SYSTEM_OS_NAME_UNI                                = "uni";

  private static final String STR_SYSTEM_OS_NAME_SOL                                = "sol";

  private static final String STR_SYSTEM_OS_NAME_ERR                                = "err";

  private static final String USER_HOME_KEY                                         = "user.home";

  private static final String USER_DIR_KEY                                          = "user.dir";

  private static final String SYSTEM_FILE_SEPARATOR                                 = System.getProperty( "file.separator" );

  private static final String SYSTEM_PROPERTY_OS                                    = System.getProperty( "os.name" ).toLowerCase();

  private static final String SYSTEM_JAVA_IO_TMPDIR                                 = System.getProperty( "java.io.tmpdir" );

  private static final String SYSTEM_LINE_SEPARATOR                                 = System.getProperty( "line.separator" );

  private static final String SYSTEM_PATH_SEPARATOR                                 = System.getProperty( "path.separator" );

  public static void main( String[] args )
  {
    debugAusgabe();
  }

  public static void debugAusgabe()
  {
    String temp_str = "";

    temp_str += "\ngetSystemPropertyOsName() =>" + getSystemPropertyOsName() + "<";
    temp_str += "\nisWindows()               =>" + isWindows() + "<";
    temp_str += "\nisMac()                   =>" + isMac() + "<";
    temp_str += "\nisUnix()                  =>" + isUnix() + "<";
    temp_str += "\nisSolaris()               =>" + isSolaris() + "<";
    temp_str += "\ngetOS()                   =>" + getOS() + "<";
    temp_str += "\ngetStdRootVerzeichnis()   =>" + getStdRootVerzeichnis() + "<";
    temp_str += "\ngetUserHomeDirectory()    =>" + getUserHomeDirectory() + "<";
    temp_str += "\ngetUserHomeKey()          =>" + getUserHomeKey() + "<";
    temp_str += "\ngetSystemFileSeparator()  =>" + getSystemFileSeparator() + "<";
    temp_str += "\ngetSystemJavaIoTmpdir()   =>" + getSystemJavaIoTmpdir() + "<";
    temp_str += "\ngetSystemPathSeparator()  =>" + getSystemPathSeparator() + "<";

    temp_str += "\ngetSystemLineSeparator()  =>" + getSystemLineSeparator() + "<";

    System.out.println( temp_str );
  }

  public static String getSystemPropertyOsName()
  {
    return SYSTEM_PROPERTY_OS;
  }

  public static boolean isWindows()
  {
    return ( SYSTEM_PROPERTY_OS.indexOf( STR_SYSTEM_OS_NAME_WIN ) >= 0 );
  }

  public static boolean isMac()
  {
    return ( SYSTEM_PROPERTY_OS.indexOf( STR_SYSTEM_OS_NAME_MAC ) >= 0 );
  }

  public static boolean isUnix()
  {
    return ( SYSTEM_PROPERTY_OS.indexOf( STR_SYSTEM_OS_NAME_NIX ) >= 0 || SYSTEM_PROPERTY_OS.indexOf( STR_SYSTEM_OS_NAME_NUX ) >= 0 || SYSTEM_PROPERTY_OS.indexOf( STR_SYSTEM_OS_NAME_AIX ) > 0 );
  }

  public static boolean isSolaris()
  {
    return ( SYSTEM_PROPERTY_OS.indexOf( STR_SYSTEM_OS_NAME_SUNOS ) >= 0 );
  }

  public static String getOS()
  {
    if ( isWindows() )
    {
      return STR_SYSTEM_OS_NAME_WIN;
    }
    else if ( isMac() )
    {
      return STR_SYSTEM_OS_NAME_OSX;
    }
    else if ( isUnix() )
    {
      return STR_SYSTEM_OS_NAME_UNI;
    }
    else if ( isSolaris() )
    {
      return STR_SYSTEM_OS_NAME_SOL;
    }
    else
    {
      return STR_SYSTEM_OS_NAME_ERR;
    }
  }

  public static String getStdRootVerzeichnis()
  {
    return getStdRootVerzeichnis( STD_ROOT_VERZEICHNIS_VORGABE_SYSTEM_NICHT_ERMITTELBAR );
  }

  public static String getStdRootVerzeichnis( String pVorgabeVerzeichnis )
  {
    if ( FkSystem.isWindows() )
    {
      /*
       * windows 8.1
       */
      return STD_ROOT_VERZEICHNIS_WINDOWS;
    }

    if ( FkSystem.isUnix() || FkSystem.isSolaris() )
    {
      return STD_ROOT_VERZEICHNIS_LINUX;
    }

    return pVorgabeVerzeichnis;
  }

  public static String getSystemFileSeparator()
  {
    return SYSTEM_FILE_SEPARATOR;
  }

  public static String getSystemJavaIoTmpdir()
  {
    return SYSTEM_JAVA_IO_TMPDIR;
  }

  public static String getSystemLineSeparator()
  {
    return SYSTEM_LINE_SEPARATOR;
  }

  public static String getSystemPathSeparator()
  {
    return SYSTEM_PATH_SEPARATOR;
  }

  public static String getUserHomeDirectory()
  {
    return System.getProperty( USER_DIR_KEY );
  }

  public static String getUserHomeKey()
  {
    return System.getProperty( USER_HOME_KEY );
  }

  public static String getDebugRuntimeMemory()
  {
    Calendar datum = Calendar.getInstance();

    int monat = datum.get( Calendar.MONTH ) + 1;
    int tag = datum.get( Calendar.DATE );
    int stunden = datum.get( Calendar.HOUR_OF_DAY );
    int minuten = datum.get( Calendar.MINUTE );
    int sekunden = datum.get( Calendar.SECOND );

    String trennzeichen = ":";

    String datum_und_zeit = ( tag < 10 ? "0" : "" ) + tag + "." + ( monat < 10 ? "0" : "" ) + monat + "." + datum.get( Calendar.YEAR ) + " " + ( stunden < 10 ? "0" : "" ) + stunden + trennzeichen + ( minuten < 10 ? "0" : "" ) + minuten + trennzeichen + ( sekunden < 10 ? "0" : "" ) + sekunden;

    datum = null;

    Runtime runtime = Runtime.getRuntime();

    int mb = 1048576; // =  1024 * 1024;

    int nr_of_threads = -1;
    int thread_active_count = -1;
    int available_pProcessors = -1;

    try
    {
      available_pProcessors = runtime.availableProcessors();
    }
    catch ( Exception e )
    {
      //
    }
    try
    {
      nr_of_threads = ManagementFactory.getThreadMXBean().getThreadCount();
    }
    catch ( Exception e )
    {
      //
    }
    try
    {
      thread_active_count = Thread.activeCount();
    }
    catch ( Exception e )
    {
      //
    }

    return datum_und_zeit + " | Total " + runtime.totalMemory() / mb + " MB | Max " + runtime.maxMemory() / mb + " MB | Free " + runtime.freeMemory() / mb + " MB | Used " + ( runtime.totalMemory() - runtime.freeMemory() ) / mb + " MB | AVPROC " + available_pProcessors + " | THRNR " + nr_of_threads + " | THRAC " + thread_active_count;
  }

  public static void debugThread()
  {
// http://stackoverflow.com/questions/1922290/how-to-get-the-number-of-threads-in-a-java-process

    Thread current_thread = Thread.currentThread();

    current_thread.setName( "debugThread" );

    current_thread.setPriority( 1 );

    System.out.println( "current thread: " + current_thread );

    int active_count = Thread.activeCount();

    System.out.println( "currently active threads: " + active_count );

    Thread array_threads[] = new Thread[ active_count ];

    Thread.enumerate( array_threads );

    int index = 0;

    while ( index < active_count )
    {
      System.out.println( index + ": " + array_threads[ index ] );

      index++;
    }
  }

  public static String getDebugLaufzeit( long pZeitStart, long pZeitEnde )
  {
    long zeit_differenz_betrag = (long) ( pZeitEnde - pZeitStart );

    long m_laufzeit_stunden = 0;
    long m_laufzeit_minuten = 0;
    long m_laufzeit_sekunden = 0;
    long m_laufzeit_milli_s = 0;

    if ( zeit_differenz_betrag > 0 )
    {
      m_laufzeit_milli_s = (long) ( zeit_differenz_betrag % 1000 );

      zeit_differenz_betrag /= 1000;

      m_laufzeit_sekunden = (long) ( zeit_differenz_betrag % 60 );

      zeit_differenz_betrag /= 60;

      m_laufzeit_minuten = (long) ( zeit_differenz_betrag % 60 );

      m_laufzeit_stunden = (long) zeit_differenz_betrag / 60;
    }

    return ( m_laufzeit_stunden < 10 ? "0" : "" ) + m_laufzeit_stunden + ":" + ( m_laufzeit_minuten < 10 ? "0" : "" ) + m_laufzeit_minuten + ":" + ( m_laufzeit_sekunden < 10 ? "0" : "" ) + m_laufzeit_sekunden + ":" + ( m_laufzeit_milli_s < 10 ? "00" : ( m_laufzeit_milli_s < 100 ? "0" : "" ) ) + m_laufzeit_milli_s;
  }
}
