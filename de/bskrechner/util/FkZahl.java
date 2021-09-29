package de.bskrechner.util;

import java.math.BigDecimal;

/**
 * Funktionen fuer Zahlenwerte
 * 
 * <pre>
 * 
 * char     =  16-Bit-Unicode-Zeichen (0x0000 ... 0xFFFF)
 * 
 * byte     =  -2^7  bis 2^7  - 1   = -128 ... 127
 * short    =  -2^15 bis 2^15 - 1   = -32.768 ... 32.767
 * int      =  -2^31 bis 2^31 - 1   = -2.147.483.648 ... 2.147.483.647
 * long     =  -2^63 bis 2^63 - 1   = -9.223.372.036.854.775.808 ... 9.223.372.036.854.775.807
 * 
 * float    =  1,40239846E-45f           bis  3,40282347E+38f
 * double   =  4,94065645841246544E-324  bis  1,79769131486231570E+308
 * 
 * </pre>
 */
public class FkZahl
{
  /**
   * <pre>
   * Errechnet den Mittelwert der beiden Angaben. 
   * Die Reihenfolge der Zahlenangabe ist egal, wird intern korrigiert.
   * 
   * FkZahl.getMittelwert(  10.0,   20.0 ) =  15.0
   * FkZahl.getMittelwert(  20.0,   10.0 ) =  15.0
   * FkZahl.getMittelwert(  15.0,   15.0 ) =  15.0
   * FkZahl.getMittelwert( -20.0,   20.0 ) =   0.0
   * FkZahl.getMittelwert( -20.0,   -7.0 ) = -13.5
   * FkZahl.getMittelwert( 123.45, 678.9 ) = 401.17499999999995
   * </pre> 
   * 
   * @param pZahl1 die erste Zahl
   * @param pZahl2 die zweite Zahl
   * @return den Mittelwert 
   */
  public static double getMittelwert( double pZahl1, double pZahl2 )
  {
    if ( pZahl1 < pZahl2 )
    {
      return pZahl1 + ( ( pZahl2 - pZahl1 ) * .5 );
    }
    else if ( pZahl1 > pZahl2 )
    {
      return pZahl2 + ( ( pZahl1 - pZahl2 ) * .5 );
    }
    else
    {
      return pZahl1; // untergrenze = obergrenze      
    }
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den Integerwert.
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert Vorgabewert im Fehlerfall
   * @return der Wert als int oder der Vorgabewert
   */
  public static int getInteger( String pString, int pVorgabeWert )
  {
    try
    {
      if ( pString != null )
      {
        return Integer.parseInt( pString.trim() );
      }
    }
    catch ( NumberFormatException e )
    {
    }

    return pVorgabeWert;
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den double-Wert.
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert Vorgabewert im Fehlerfall
   * @return der Wert als double oder der Vorgabewert
   */
  public static double getDouble( String pString, double pVorgabe )
  {
    try
    {
      if ( pString != null )
      {
        return Double.parseDouble( pString.trim() );
      }
    }
    catch ( Exception e )
    {
      // keine Fehlerbehandlung, da im Fehlerfall Rueckgabe von 0.0
    }

    return pVorgabe;
  }

  public static long getLong( String pString )
  {
    long ergebnis = 0;

    try
    {
      if ( pString != null )
      {
        ergebnis = Long.parseLong( pString );
      }
    }
    catch ( Exception e )
    {
      // keine Fehlerbehandlung, da im Fehlerfall Rueckgabe von 0
    }

    return ergebnis;
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den Longwert.
   * Kommt es bei der Umwandlung zu einer NumberFormatException,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert Vorgabewert im Fehlerfall
   * @return der Wert als long oder der Vorgabewert
   */
  public static long getLong( String pString, int pVorgabeWert )
  {
    try
    {
      if ( pString != null )
      {
        return Long.parseLong( pString.trim() );
      }
    }
    catch ( NumberFormatException e )
    {
    }

    return pVorgabeWert;
  }

  public static String toPlainString( BigDecimal pWert )
  {
    if ( pWert != null )
    {
      return pWert.toPlainString();
    }

    return "";
  }

  public static int getKennzeichenFeld( String pString )
  {
    int int_wert = 0;

    try
    {
      if ( pString != null )
      {
        int_wert = Integer.parseInt( pString );
      }
    }
    catch ( Exception e )
    {
      // keine Fehlerbehandlung
    }

    if ( int_wert < 0 ) return -1;

    if ( int_wert > 0 ) return 1;

    return 0;
  }
}