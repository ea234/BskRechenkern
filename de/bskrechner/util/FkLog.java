package de.bskrechner.util;

public class FkLog
{
  /**
   * Ausgabe auf System.out
   * 
   * @param pString der auszugebende String
   */
  public static void wl( String pString )
  {
    System.out.println( pString );
  }

  /**
   * Ausgabe auf System.out
   * 
   * @param pString der auszugebende String 
   * @param pThrowable die auszugebende Fehlermeldung
   */
  public static void wl( String pString, Throwable pThrowable )
  {
    System.out.println( pString );

    System.out.println( pThrowable.toString() );

    pThrowable.printStackTrace( System.out );
  }
}