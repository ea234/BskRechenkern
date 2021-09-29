package de.bskrechner.util;

import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * Ein Long-Datum ist ein Datum dessen Format JJJJMMTT ist.
 * 
 * Ein Long-Datum hat den Namen, weil dieses Format hauptsaechlich mit dem Typ "long" gespeichert wird.
 *  
 * Das Format kann natuerlich auch in einem String gespeichert werden.
 * 
 * 
 * Datumsbestandteile aus dem Long-Datumsformat extrahieren:
 * 
 * long datum_long = 20100830;
 * 
 * int datum_jahr  = (int) ( datum_long * 0.0001 );                                     // " + datum_jahr + "
 * int datum_monat = (int) ( ( datum_long - ( datum_jahr * 10000 ) ) * 0.01 );          // " + datum_monat + "
 * int datum_tag   = (int) ( datum_long - ( datum_jahr * 10000 + datum_monat * 100 ) ); // " + datum_tag + "
 *
 * return (long) ( ( datum_jahr * 10000 ) + ( datum_monat  * 100 ) + datum_tag );
 * 
 * </pre>
 */
public class FkDatumLong
{
  /**
   * <pre>
   * Prueft den Eingabestring auf ein enthaltenes Datum ab.
   * 
   * Ist der Parameter "pString" ein Datum, wird das Datum im Format JJJJMMTT zurueckgegeben. 
   * 
   * Wird kein gueltiges Datum erkannt, wird 0 zurueckgegeben.
   * 
   * Bei den Angaben mit Trennzeichen, muss das Jahr immer 
   * 4stellig angegeben werden. Die Monats- und Tageswerte 
   * koennen 1 bis 2 Stellen haben. Bei mehr als 2 Stellen 
   * im Tag oder Monat, wird 0 zurueckgegeben.
   * 
   * Es werden folgende Formate in der Reihenfolge geprueft:
   * 
   * Format 1: TT.MM.JJJJ, T.MM.JJJJ, TT.M.JJJJ, T.M.JJJJ
   * Format 2: JJJJMMTT
   * Format 3: JJJJ-MM-TT, JJJJ-MM-T, JJJJ-M-TT, JJJJ-M-J
   * Format 4: TT-MM-JJJJ, T-MM-JJJJ, TT-M-JJJJ, T-M-JJJJ
   * 
   * FkDatumLong.parseToLong( "06.01.2010"    ) = 20100106
   * FkDatumLong.parseToLong( "06.1.2010"     ) = 20100106
   * FkDatumLong.parseToLong( "6.01.2010"     ) = 20100106
   * 
   * FkDatumLong.parseToLong( "20100106"      ) = 20100106
   * 
   * FkDatumLong.parseToLong( "2010-01-06"    ) = 20100106
   * FkDatumLong.parseToLong( "2010-01-6"     ) = 20100106
   * FkDatumLong.parseToLong( "2010-1-06"     ) = 20100106
   * FkDatumLong.parseToLong( "2010-1-6"      ) = 20100106
   * 
   * FkDatumLong.parseToLong( "06-01-2010"    ) = 20100106
   * FkDatumLong.parseToLong( "06-1-2010"     ) = 20100106
   * FkDatumLong.parseToLong( "6-01-2010"     ) = 20100106
   * FkDatumLong.parseToLong( "6-1-2010"      ) = 20100106
   * 
   * FkDatumLong.parseToLong( "2010061"       ) = 0
   * FkDatumLong.parseToLong( "2010.12.01"    ) = 0
   * FkDatumLong.parseToLong( null            ) = 0
   * FkDatumLong.parseToLong( ""              ) = 0
   * FkDatumLong.parseToLong( "2010 01 06"    ) = 0
   * FkDatumLong.parseToLong( "28-02-2010"    ) = 20100228
   * FkDatumLong.parseToLong( "29-02-2010"    ) = 0
   * FkDatumLong.parseToLong( "02-28-2010"    ) = 0
   * FkDatumLong.parseToLong( "00-01-2010"    ) = 0
   * FkDatumLong.parseToLong( "06-00-2010"    ) = 0
   * FkDatumLong.parseToLong( "06-01-0000"    ) = 0
   * FkDatumLong.parseToLong( "EF-GH-ABCD"    ) = 0
   *
   * FkDatumLong.parseToLong( "1111111"       ) = 0 
   * FkDatumLong.parseToLong( "30.2.2010"     ) = 0
   *  
   * Ungueltige Werte
   * FkDatumLong.parseToLong( "99.05.2010"    ) = 0 
   * FkDatumLong.parseToLong( "01.99.2010"    ) = 0
   * FkDatumLong.parseToLong( "01.05.1234"    ) = 0
   *
   * FkDatumLong.parseToLong( "9999999999"    ) = 0 
   * FkDatumLong.parseToLong( "0000000001"    ) = 0 
   * FkDatumLong.parseToLong( "00.00.0000"    ) = 0
   *  
   * Ungueltige Zeichen:
   * FkDatumLong.parseToLong( "1A.05.2010"    ) = 0 
   * FkDatumLong.parseToLong( "10.0B.2010"    ) = 0 
   * FkDatumLong.parseToLong( "10.05.C010"    ) = 0 
   * 
   * FkDatumLong.parseToLong( "ABCDEFGH"      ) = 0 
   * FkDatumLong.parseToLong( null            ) = 0 
   *
   * Zu viele Trennzeichen:
   * FkDatumLong.parseToLong( "1..05.2010"    ) = 0 
   * FkDatumLong.parseToLong( "1.05..2010"    ) = 0 
   * FkDatumLong.parseToLong( "1.05.20.10"    ) = 0
   * 
   * FkDatumLong.parseToLong( "1-22010-"      ) = 0
   * FkDatumLong.parseToLong( "1-2-2010-"     ) = 0
   * FkDatumLong.parseToLong( "-1-2-2010"     ) = 0
   * FkDatumLong.parseToLong( "1--2-2010"     ) = 0 
   * FkDatumLong.parseToLong( "12---2010"     ) = 0 
   * 
   * Trennzeichenkombinationen
   * FkDatumLong.parseToLong( "2010-01-01"    ) = 20100101 
   * FkDatumLong.parseToLong( "2010-01.01"    ) = 0 
   * FkDatumLong.parseToLong( "2010.01-01"    ) = 0 
   * 
   * FkDatumLong.parseToLong( "1-2-2010"      ) = 20100201 
   * FkDatumLong.parseToLong( "1.02-2010"     ) = 0 
   * FkDatumLong.parseToLong( "01-2.2010"     ) = 0 
   *  
   * Zu viele vorlaufende 0en:
   * FkDatumLong.parseToLong( "06.01.2010"    ) = 20100106 
   * FkDatumLong.parseToLong( "006.1.2010"    ) = 0 
   * FkDatumLong.parseToLong( "6.001.2010"    ) = 0 
   * FkDatumLong.parseToLong( "6.1.002010"    ) = 0
   * 
   * FkDatumLong.parseToLong( "0808.2010"     ) = 0 = erster Punkt nicht an erwarteter Stelle
   *                                                  erster Punkt zu spaet in der Eingabe
   *                                                  (bzw. korrekter: Monat wird als 808 gelesen, was zu gross ist)
   *                                                  
   * FkDatumLong.parseToLong( "0008.2010"     ) = 0 = es wird kein Tag gelesen, weswegen das Datum abgewiesen wird
   * 
   * FkDatumLong.parseToLong( "0.08.2010"     ) = 0 = der Tag ist 0, weswegen das Datum abgewiesen wird
   * 
   * FkDatumLong.parseToLong( "2010.8.008"    ) = 0 
   * FkDatumLong.parseToLong( "2010.08008"    ) = 0 
   * FkDatumLong.parseToLong( "2010.00008"    ) = 0
   * 
   * FkDatumLong.parseToLong( "2020-01-02"    ) = 20200102 
   * FkDatumLong.parseToLong( "2020-1-02"     ) = 20200102 
   * FkDatumLong.parseToLong( "2020-01-2"     ) = 20200102 
   * FkDatumLong.parseToLong( "2020-1-2"      ) = 20200102 
   * FkDatumLong.parseToLong( "2020-1-002"    ) = 0 
   * FkDatumLong.parseToLong( "2020-001-2"    ) = 0 
   * FkDatumLong.parseToLong( "02020-1-02"    ) = 0 
   * FkDatumLong.parseToLong( "002020-1-2"    ) = 0 
   * FkDatumLong.parseToLong( "2020001002"    ) = 0 
   * FkDatumLong.parseToLong( "2010-1-001"    ) = 0 
   * FkDatumLong.parseToLong( "2010-010-1"    ) = 0 
   * FkDatumLong.parseToLong( "2010-01001"    ) = 0 
   * FkDatumLong.parseToLong( "2010-00001"    ) = 0
   * 
   * </pre>
   * 
   * @param pString die auf ein Datum zu parsende Eingabe
   * @return Ein Datum im Format JJJJMMTT als Long, wenn sich aus der Eingabe ein Datum parsen laesst. 
   *         Im Fehlerfall wird 0 zurueckgegeben.
   */
  public static long parseToLong( String pString )
  {
    /*
     * Pruefung: Eingabe gesetzt ?
     * 
     * Ist die Eingabe "null", bekommt der Aufrufer 0 zurueck.
     */
    if ( pString == null )
    {
      return 0; // Fehler: EINGABE_NICHT_GESETZT
    }

    /*
     * Test 1: Datum im Format TT.MM.JJJJ, T.MM.JJJJ, TT.M.JJJJ, T.M.JJJJ
     * 
     * Das zuerst gepruefte Muster ist ein Datum im Format Tag Monat Jahr. 
     * Dabei koennen beim Tag und Monat die fuehrenden 0en weggelassen werden.
     * Wird kein gueltiges Datum in diesem Muster erkannt, wird von der 
     * Funktion 0 zurueckgegeben.
     */
    long long_datum = FkDatumLong.getLongAusTTPMMPJJJJ( pString );

    /*
     * Pruefung: Ergebnis der ersten Parserfunktion 0 ?
     * 
     * Ist das Ergebnis der ersten Parserfunktion gleich 0, konnte kein Long-Datum 
     * aus der Eingabe ermittelt werden. Es wird die zweite Parserfunktion gestartet,
     * welche ein anderes Datumsmuster prueft. Dieses Vorgehen wird fuer die zwei 
     * weiteren Parserfunktion wiederholt, wobei andere Datumsmuster geprueft werden. 
     */
    if ( long_datum == 0 )
    {
      /*
       * Test 2: Datum im Format JJJJMMTT
       * 
       * Das zweite Muster ist ein Datum im Format Jahr Monat Tag. 
       * Dabei muss das Jahr 4stellig, Monat und Jahr 2 Stellig uebergeben worden sein. 
       * Die Eingabe muss eine Laenge von 8 Zeichen haben.
       */
      long_datum = FkDatumLong.getLongAusJJJJMMTT( pString );
    }

    if ( long_datum == 0 )
    {
      /*
       * Test 3: Datum im Format JJJJ-MM-TT, JJJJ-MM-T, JJJJ-M-TT, JJJJ-M-J
       */
      long_datum = FkDatumLong.getLongAusJJJJxMMxTT( pString, '-' );
    }

    if ( long_datum == 0 )
    {
      /*
       * Test 4: Datum im Format TT-MM-JJJJ, T-MM-JJJJ, TT-M-JJJJ, T-M-JJJJ
       */
      long_datum = FkDatumLong.getLongAusTTxMMxJJJJ( pString, '-' );
    }

    /*
     * Am Funktionsende wird der Wert der Variablen "long_datum" zurueckgegeben.
     */
    return long_datum;
  }

  /**
   * <pre>
   * Prueft den Eingabestring auf ein enthaltenes Datum ab.
   * 
   * Ist der Parameter "pString" ein Datum, wird das Datum im Format TT.MM.JJJJ zurueckgegeben. 
   * 
   * Wird kein gueltiges Datum erkannt, wird "null" zurueckgegeben.
   * 
   * Bei den Angaben mit Trennzeichen, muss das Jahr immer 
   * 4stellig angegeben werden. Die Monats- und Tageswerte 
   * koennen 1 bis 2 Stellen haben. Bei mehr als 2 Stellen 
   * im Tag oder Monat, wird "null" zurueckgegeben.
   * 
   * Es werden folgende Formate in der Reihenfolge geprueft:
   * 
   * Format 1: TT.MM.JJJJ, T.MM.JJJJ, TT.M.JJJJ, T.M.JJJJ
   * Format 2: JJJJMMTT
   * Format 3: JJJJ-MM-TT, JJJJ-MM-T, JJJJ-M-TT, JJJJ-M-J
   * Format 4: TT-MM-JJJJ, T-MM-JJJJ, TT-M-JJJJ, T-M-JJJJ
   * 
   * FkDatumLong.parseToString( "06.01.2010" ) = 06.01.2010
   * FkDatumLong.parseToString( "06.1.2010"  ) = 06.01.2010
   * FkDatumLong.parseToString( "6.01.2010"  ) = 06.01.2010
   * 
   * FkDatumLong.parseToString( "20100106"   ) = 01.06.2010
   * 
   * FkDatumLong.parseToString( "2010-01-06" ) = 01.06.2010
   * FkDatumLong.parseToString( "2010-01-6"  ) = 01.06.2010
   * FkDatumLong.parseToString( "2010-1-06"  ) = 01.06.2010
   * FkDatumLong.parseToString( "2010-1-6"   ) = 01.06.2010
   * 
   * FkDatumLong.parseToString( "06-01-2010" ) = 06.01.2010
   * FkDatumLong.parseToString( "06-1-2010"  ) = 06.01.2010
   * FkDatumLong.parseToString( "6-01-2010"  ) = 06.01.2010
   * FkDatumLong.parseToString( "6-1-2010"   ) = 06.01.2010
   * 
   * FkDatumLong.parseToString( "2010061"    ) = null
   * FkDatumLong.parseToString( "2010.12.01" ) = null
   * FkDatumLong.parseToString( null         ) = null
   * FkDatumLong.parseToString( ""           ) = null
   * FkDatumLong.parseToString( "2010 01 06" ) = null
   * FkDatumLong.parseToString( "02-29-2010" ) = null
   * FkDatumLong.parseToString( "00-01-2010" ) = null
   * FkDatumLong.parseToString( "06-00-2010" ) = null
   * FkDatumLong.parseToString( "06-01-0000" ) = null
   * FkDatumLong.parseToString( "EF-GH-ABCD" ) = null
   * FkDatumLong.parseToString( "28-02-2010" ) = 28.02.2010
   * FkDatumLong.parseToString( "29-02-2010" ) = null
   * FkDatumLong.parseToString( "02-28-2010" ) = null
   * </pre>
   * 
   * @param pString die auf ein Datum zu parsende Eingabe
   * @return Ein Datum im Format TT.MM.JJJJ als String, wenn sich aus der Eingabe ein Datum parsen laesst. 
   *         Ist in der Eingabe kein gueltiges Datum enthalten, wird null zurueckgegeben.
   */
  public static String parseToString( String pString )
  {
    /*
     * Aus der Eingabe wird versucht eine Datum im Long-Format zu parsen. 
     * Dieses wird ueber die Funktion "parseToLong" erledigt.
     * Die Funktion gibt 0 zurueck, wenn kein Datum gelesen werden konnte.
     */
    long datum_long = parseToLong( pString );

    /*
     * Pruefung: Konnte ein Datum gelesen werden ?
     */
    if ( datum_long > 0 )
    {
      /*
       * Wurde im Parameter "pString" ein gueltiges Datum uebergeben, ist in 
       * der Variablen "datum_long" das Datum im Format JJJJMMTT gespeichert. 
       * Aus dieser Variablen werden die einzelnen Datums-Bestandteile wieder 
       * herausgerechnet. Mit den seperaten Werten wird dann der Ergebnisstring 
       * im Format TT.MM.JJJJ erstellt und zurueckgegeben.
       */
      int datum_jahr = (int) ( datum_long * 0.0001 );

      int datum_monat = (int) ( ( datum_long - ( datum_jahr * 10000 ) ) * 0.01 );

      int datum_tag = (int) ( datum_long - ( datum_jahr * 10000 + datum_monat * 100 ) );

      return ( datum_tag < 10 ? "0" : "" ) + datum_tag + ( datum_monat < 10 ? ".0" : "." ) + datum_monat + "." + datum_jahr;
    }

    /*
     * Ist in der Eingabe kein Datum vorhanden, wird "null" zurueckgegeben.
     */
    return null; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Prueft den Eingabestring auf ein enthaltenes Datum ab.
   * 
   * Ist der Parameter "pString" ein Datum, wird das Datum im Format TT.MM.JJJJ zurueckgegeben.
   * 
   * Wird kein gueltiges Datum erkannt, wird "null" zurueckgegeben.
   * 
   * Die Datumsbestandteile werden durch das uebergebene Trennzeichen getrennt.
   * Vom Parameter "pTrennzeichen" werden maximal 5 Stellen genommen. 
   * Ist der Parameter "pTrennzeichen" gleich "null" wird kein Trennzeichen gesetzt. 
   * 
   * Bei den Angaben mit Trennzeichen, muss das Jahr immer 
   * 4stellig angegeben werden. Die Monats- und Tageswerte 
   * koennen 1 bis 2 Stellen haben. Bei mehr als 2 Stellen 
   * im Tag oder Monat, wird "null" zurueckgegeben.
   * 
   * Es werden folgende Formate in der Reihenfolge geprueft:
   * 
   * Format 1: TT.MM.JJJJ, T.MM.JJJJ, TT.M.JJJJ, T.M.JJJJ
   * Format 2: JJJJMMTT
   * Format 3: JJJJ-MM-TT, JJJJ-MM-T, JJJJ-M-TT, JJJJ-M-J
   * Format 4: TT-MM-JJJJ, T-MM-JJJJ, TT-M-JJJJ, T-M-JJJJ
   * 
   * FkDatumLong.parseToStringTTPMMPJJJJ( "06.01.2010", "." ) = 06.01.2010
   * FkDatumLong.parseToStringTTPMMPJJJJ(  "6.01.2010", "." ) = 06.01.2010
   * FkDatumLong.parseToStringTTPMMPJJJJ(  "06.1.2010", "." ) = 06.01.2010
   * FkDatumLong.parseToStringTTPMMPJJJJ(   "6.1.2010", "." ) = 06.01.2010
   * 
   * FkDatumLong.parseToStringTTPMMPJJJJ(   "20100106", "-" ) = 06-01-2010
   * 
   * FkDatumLong.parseToStringTTPMMPJJJJ( "2010-01-06", "-" ) = 06-01-2010
   * 
   * FkDatumLong.parseToStringTTPMMPJJJJ( "06.01.2010", null ) = 06012010
   * 
   * FkDatumLong.parseToStringTTPMMPJJJJ( "06.01.2010",        "-"  ) = 06-01-2010
   * FkDatumLong.parseToStringTTPMMPJJJJ( "06.01.2010",       " - " ) = 06 - 01 - 2010
   * FkDatumLong.parseToStringTTPMMPJJJJ( "06.01.2010", "-ABC-DEF-" ) = 06-ABC-01-ABC-2010 (Maximal 5 Stellen des Trennzeichen-Strings)
   * 
   * FkDatumLong.parseToStringTTPMMPJJJJ(         null, "-" ) = null
   * FkDatumLong.parseToStringTTPMMPJJJJ(   "ABCDEFGH", "-" ) = null
   * 
   * FkDatumLong.parseToStringTTPMMPJJJJ( "88.01.2010", "." ) = null
   * FkDatumLong.parseToStringTTPMMPJJJJ( "06.88.2010", "." ) = null
   * FkDatumLong.parseToStringTTPMMPJJJJ( "06.01.0000", "." ) = null
   * </pre>
   * 
   * @param pString die auf ein Datum zu parsende Eingabe
   * @param pTrennzeichen der zu verwendende Trennzeichen-String fuer die Ausgabe. Bei Uebergabe von "null" wird kein Trennzeichen gesetzt. Es werden maximal 5 Stellen genommen.
   * @return Ein Datum im Format TT.MM.JJJJ als String, wenn sich aus der Eingabe ein Datum parsen laesst. 
   *         Im Fehlerfall wird null zurueckgegeben.
   */
  public static String parseToStringTTPMMPJJJJ( String pString, String pTrennzeichen )
  {
    /*
     * Aus der Eingabe wird versucht eine Datum im Long-Format zu parsen. 
     * Dieses wird ueber die Funktion "parseToLong" erledigt.
     * Die Funktion gibt 0 zurueck, wenn kein Datum gelesen werden konnte.
     */
    long datum_long = parseToLong( pString );

    /*
     * Pruefung: Konnte ein Datum gelesen werden ?
     */
    if ( datum_long > 0 )
    {
      /*
       * Wurde im Parameter "pString" ein gueltiges Datum uebergeben, ist in 
       * der Variablen "datum_long" das Datum im Format JJJJMMTT gespeichert. 
       * Aus dieser Variablen werden die einzelnen Datums-Bestandteile wieder 
       * herausgerechnet. Mit den seperaten Werten wird dann der Ergebnisstring 
       * im Format TT.MM.JJJJ erstellt und zurueckgegeben.
       */
      int datum_jahr = (int) ( datum_long * 0.0001 );

      int datum_monat = (int) ( ( datum_long - ( datum_jahr * 10000 ) ) * 0.01 );

      int datum_tag = (int) ( datum_long - ( datum_jahr * 10000 + datum_monat * 100 ) );

      /*
       * Trennzeichenstring
       * Ist der Parameter "pTrennzeichen" gleich "null", wird ein Leerstring als 
       * Trennzeichen genommen. Ist der Parameter gesetzt, werden maximal die ersten 
       * 5 Zeichen des Parameters genommen.
       */
      String str_tz = pTrennzeichen == null ? "" : pTrennzeichen.substring( 0, ( pTrennzeichen.length() > 5 ? 5 : pTrennzeichen.length() ) );

      return ( datum_tag < 10 ? "0" : "" ) + datum_tag + str_tz + ( datum_monat < 10 ? "0" : "" ) + datum_monat + str_tz + datum_jahr;
    }

    /*
     * Ist in der Eingabe kein Datum vorhanden, wird "null" zurueckgegeben.
     */
    return null; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Prueft den Eingabestring auf ein enthaltenes Datum ab.
   * 
   * Ist der Parameter "pString" ein Datum, wird das Datum im Format JJJJ.TT.MM zurueckgegeben.
   * 
   * Wird kein gueltiges Datum erkannt, wird "null" zurueckgegeben.
   * 
   * Die Datumsbestandteile werden durch das uebergebene Trennzeichen getrennt.
   * Vom Parameter "pTrennzeichen" werden maximal 5 Stellen genommen. 
   * Ist der Parameter "pTrennzeichen" gleich "null" wird kein Trennzeichen gesetzt. 
   * 
   * Bei den Angaben mit Trennzeichen, muss das Jahr immer 
   * 4stellig angegeben werden. Die Monats- und Tageswerte 
   * koennen 1 bis 2 Stellen haben. Bei mehr als 2 Stellen 
   * im Tag oder Monat, wird "null" zurueckgegeben.
   * 
   * Es werden folgende Formate in der Reihenfolge geprueft:
   * 
   * Format 1: TT.MM.JJJJ, T.MM.JJJJ, TT.M.JJJJ, T.M.JJJJ
   * Format 2: JJJJMMTT
   * Format 3: JJJJ-MM-TT, JJJJ-MM-T, JJJJ-M-TT, JJJJ-M-J
   * Format 4: TT-MM-JJJJ, T-MM-JJJJ, TT-M-JJJJ, T-M-JJJJ
   * 
   * FkDatumLong.parseToStringJJJJPMMPTT( "06.01.2010", "." ) = 2010.01.06
   * FkDatumLong.parseToStringJJJJPMMPTT( "6.01.2010",  "." ) = 2010.01.06
   * FkDatumLong.parseToStringJJJJPMMPTT( "06.1.2010",  "." ) = 2010.01.06
   * FkDatumLong.parseToStringJJJJPMMPTT( "6.1.2010",   "." ) = 2010.01.06
   * 
   * FkDatumLong.parseToStringJJJJPMMPTT( "20100106",   "-" ) = 2010-01-06
   * 
   * FkDatumLong.parseToStringJJJJPMMPTT( "2010-01-06", "-" ) = 2010-01-06
   * 
   * FkDatumLong.parseToStringJJJJPMMPTT( "06.01.2010",        null ) = 20100106
   * FkDatumLong.parseToStringJJJJPMMPTT( "06.01.2010",         "-" ) = 2010-01-06
   * FkDatumLong.parseToStringJJJJPMMPTT( "06.01.2010",       " - " ) = 2010 - 01 - 06
   * FkDatumLong.parseToStringJJJJPMMPTT( "06.01.2010", "-ABC-DEF-" ) = 2010-ABC-01-ABC-06
   *     
   * FkDatumLong.parseToStringJJJJPMMPTT(         null, "-" ) = null
   * FkDatumLong.parseToStringJJJJPMMPTT(   "ABCDEFGH", "-" ) = null
   * 
   * FkDatumLong.parseToStringJJJJPMMPTT( "88.01.2010", "." ) = null
   * FkDatumLong.parseToStringJJJJPMMPTT( "06.88.2010", "." ) = null
   * FkDatumLong.parseToStringJJJJPMMPTT( "06.01.0000", "." ) = null
   * </pre>
   * 
   * @param pString die auf ein Datum zu parsende Eingabe
   * @param pTrennzeichen das zu verwendende Trennzeichen fuer die Ausgabe. Bei Uebergabe von "null" wird kein Trennzeichen gesetzt. Es werden maximal 5 Stellen eines gesetzten Trennzeichens genommen.
   * @return Ein Datum im Format JJJJ<TZ>TT<TZ>MM als String, wenn sich aus der Eingabe ein Datum parsen laesst. 
   *         Im Fehlerfall wird null zurueckgegeben. Das Trennzeichen im Ergebnis ist das Zeichen aus "pTrennzeichen"
   */
  public static String parseToStringJJJJPMMPTT( String pString, String pTrennzeichen )
  {
    /*
     * Aus der Eingabe wird versucht eine Datum im Long-Format zu parsen. 
     * Dieses wird ueber die Funktion "parseToLong" erledigt.
     * Die Funktion gibt 0 zurueck, wenn kein Datum gelesen werden konnte.
     */
    long datum_long = parseToLong( pString );

    /*
     * Pruefung: Konnte ein Datum gelesen werden ?
     */
    if ( datum_long > 0 )
    {
      /*
       * Wurde im Parameter "pString" ein gueltiges Datum uebergeben, ist in 
       * der Variablen "datum_long" das Datum im Format JJJJMMTT gespeichert. 
       * Aus dieser Variablen werden die einzelnen Datums-Bestandteile wieder 
       * herausgerechnet. Mit den seperaten Werten wird dann der Ergebnisstring 
       * im Format JJJJ.MM.TT erstellt und zurueckgegeben.
       */
      int datum_jahr = (int) ( datum_long * 0.0001 );

      int datum_monat = (int) ( ( datum_long - ( datum_jahr * 10000 ) ) * 0.01 );

      int datum_tag = (int) ( datum_long - ( datum_jahr * 10000 + datum_monat * 100 ) );

      /*
       * Trennzeichenstring
       * Ist der Parameter "pTrennzeichen" gleich "null", wird ein Leerstring 
       * als Trennzeichen genommen. Ist der Parameter gesetzt, werden maximal  
       * die ersten 5 Zeichen des Parameters genommen.
       */
      String str_trennzeichen = pTrennzeichen == null ? "" : pTrennzeichen.substring( 0, ( pTrennzeichen.length() > 5 ? 5 : pTrennzeichen.length() ) );

      return datum_jahr + str_trennzeichen + ( datum_monat < 10 ? "0" : "" ) + datum_monat + str_trennzeichen + ( datum_tag < 10 ? "0" : "" ) + datum_tag;
    }

    /*
     * Ist in der Eingabe kein Datum vorhanden, wird "null" zurueckgegeben.
     */
    return null; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Prueft den Eingabestring auf ein enthaltenes Datum ab.
   * 
   * Ist der Parameter "pString" ein Datum, wird das Datum als Instanz von java.util.Date zurueckgegeben. 
   * 
   * Wird kein gueltiges Datum erkannt, wird "null" zurueckgegeben.
   * 
   * Bei den Angaben mit Trennzeichen, muss das Jahr immer 
   * 4stellig angegeben werden. Die Monats- und Tageswerte 
   * koennen 1 bis 2 Stellen haben. Bei mehr als 2 Stellen 
   * im Tag oder Monat, wird "null" zurueckgegeben.
   * 
   * Es werden folgende Formate in der Reihenfolge geprueft:
   * 
   * Format 1: TT.MM.JJJJ, T.MM.JJJJ, TT.M.JJJJ, T.M.JJJJ
   * Format 2: JJJJMMTT
   * Format 3: JJJJ-MM-TT, JJJJ-MM-T, JJJJ-M-TT, JJJJ-M-J
   * Format 4: TT-MM-JJJJ, T-MM-JJJJ, TT-M-JJJJ, T-M-JJJJ
   * 
   * FkDatumLong.parseToDate( "06.01.2010"    ) = Wed Jan 06 00:00:00 CET 2010
   * FkDatumLong.parseToDate( "06.1.2010"     ) = Wed Jan 06 00:00:00 CET 2010
   * FkDatumLong.parseToDate( "6.01.2010"     ) = Wed Jan 06 00:00:00 CET 2010
   * 
   * FkDatumLong.parseToDate( "20100106"      ) = Wed Jan 06 00:00:00 CEST 2010
   * 
   * FkDatumLong.parseToDate( "2010-01-06"    ) = Wed Jan 06 00:00:00 CEST 2010
   * FkDatumLong.parseToDate( "2010-01-6"     ) = Wed Jan 06 00:00:00 CEST 2010
   * FkDatumLong.parseToDate( "2010-1-06"     ) = Wed Jan 06 00:00:00 CEST 2010
   * FkDatumLong.parseToDate( "2010-1-6"      ) = Wed Jan 06 00:00:00 CEST 2010
   * 
   * FkDatumLong.parseToDate( "06-01-2010"    ) = Wed Jan 06 00:00:00 CET 2010
   * FkDatumLong.parseToDate( "06-1-2010"     ) = Wed Jan 06 00:00:00 CET 2010
   * FkDatumLong.parseToDate( "6-01-2010"     ) = Wed Jan 06 00:00:00 CET 2010
   * FkDatumLong.parseToDate( "6-1-2010"      ) = Wed Jan 06 00:00:00 CET 2010
   * FkDatumLong.parseToDate( "2010061"       ) = null
   * FkDatumLong.parseToDate( "2010.12.01"    ) = null
   * FkDatumLong.parseToDate( null,           ) = null
   * FkDatumLong.parseToDate( ""              ) = null
   * FkDatumLong.parseToDate( "2010 01 06"    ) = null
   * FkDatumLong.parseToDate( "02-29-2010"    ) = null
   * FkDatumLong.parseToDate( "00-01-2010"    ) = null
   * FkDatumLong.parseToDate( "06-00-2010"    ) = null
   * FkDatumLong.parseToDate( "06-01-0000"    ) = null
   * FkDatumLong.parseToDate( "EF-GH-ABCD"    ) = null
   * FkDatumLong.parseToDate( "28-02-2010"    ) = Sun Feb 28 00:00:00 CET 2010
   * FkDatumLong.parseToDate( "29-02-2010"    ) = null
   * FkDatumLong.parseToDate( "02-28-2010"    ) = null
   * </pre>
   * 
   * @param pString die auf ein Datum zu parsende Eingabe
   * @return Ein Datum im Format TT.MM.JJJJ als String, wenn sich aus der Eingabe ein Datum parsen laesst. 
   *         Im Fehlerfall wird null zurueckgegeben.
   */
  public static Date parseToDate( String pString )
  {
    /*
     * Aus der Eingabe wird versucht eine Datum im Long-Format zu parsen. 
     * Dieses wird ueber die Funktion "parseToLong" erledigt.
     * Die Funktion gibt 0 zurueck, wenn kein Datum gelesen werden konnte.
     */
    long datum_long = parseToLong( pString );

    /*
     * Pruefung: Konnte ein Datum gelesen werden ?
     */
    if ( datum_long > 0 )
    {
      /*
       * Wurde im Parameter "pString" ein gueltiges Datum uebergeben, ist in 
       * der Variablen "datum_long" das Datum im Format JJJJMMTT gespeichert. 
       * Aus dieser Variablen werden die einzelnen Datums-Bestandteile 
       * wieder herausgerechnet.
       */
      int datum_jahr = (int) ( datum_long * 0.0001 );

      int datum_monat = (int) ( ( datum_long - ( datum_jahr * 10000 ) ) * 0.01 );

      int datum_tag = (int) ( datum_long - ( datum_jahr * 10000 + datum_monat * 100 ) );

      /*
       * Ergebnisaufbau
       * Das Ergebnis wird in einem Java-Calendar-Objekt aufgebaut.
       * Die Zeit-Werte werden auf 0 gestellt. 
       * Der Aufrufer bekommt das Datum als java.util.Date zurueck.
       */
      Calendar java_calendar = Calendar.getInstance();

      /*
       * Fehlervermeidung
       * Um einen moeglichen Fehler beim setzen der Datumsbestandteile 
       * zu vermeiden, wird der Tag initial auf den Monatsersten gestellt. 
       */
      java_calendar.set( Calendar.DATE, 1 );

      java_calendar.set( Calendar.YEAR, datum_jahr );
      java_calendar.set( Calendar.MONTH, datum_monat - 1 );
      java_calendar.set( Calendar.DATE, datum_tag );

      /*
       * Die Zeitbestandteile werden auf 0 gesetzt.
       */
      java_calendar.set( Calendar.HOUR_OF_DAY, 0 );
      java_calendar.set( Calendar.MINUTE, 0 );
      java_calendar.set( Calendar.SECOND, 0 );
      java_calendar.set( Calendar.MILLISECOND, 0 );

      return java_calendar.getTime();
    }

    /*
     * Ist in der Eingabe kein Datum vorhanden, wird "null" zurueckgegeben.
     */
    return null; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Liefert fuer eine gueltige Datumseingabe im Format JJJJMMTT einen Long-Wert im Format JJJJMMTT.
   * Wandlung eines Stringdatums in einen Long-Wert mit Datumspruefung.
   * 
   * Ist die Eingabe kein gueltiges Datum, wird 0 zurueckgegeben.
   * 
   * Die Eingabe muss eine Laenge von 8 Stellen haben.
   * Das Jahr muss 4stellig sein. Tag und Monat muessen 2stellig sein.
   * 
   * Das Jahr muss mindestens 1583 sein.
   * 
   * Es wird kein Trim auf die Eingabe gemacht.
   * 
   * FkDatumLong.getLongAusJJJJMMTT( "20100115"  ) = 20100115
   * FkDatumLong.getLongAusJJJJMMTT( "20120229"  ) = 20120229
   * FkDatumLong.getLongAusJJJJMMTT( "20100229"  ) = 0 = Datum existiert nicht
   * FkDatumLong.getLongAusJJJJMMTT( "20100999"  ) = 0 = Tageswert zu hoch (99)
   * FkDatumLong.getLongAusJJJJMMTT( "15810101"  ) = 0 = Jahr zu klein 
   * FkDatumLong.getLongAusJJJJMMTT( "00000000"  ) = 0 = Jahr zu klein
   * FkDatumLong.getLongAusJJJJMMTT( "99999999"  ) = 0 = Fehler im Monat und Tag
   * FkDatumLong.getLongAusJJJJMMTT( "20109901"  ) = 0 = Monat zu hoch (99)
   * FkDatumLong.getLongAusJJJJMMTT( "JJJJMMTT"  ) = 0 = Eingabe keine Zahl 
   * FkDatumLong.getLongAusJJJJMMTT( "12010"     ) = 0 = Eingabe zu kurz
   * FkDatumLong.getLongAusJJJJMMTT( ""          ) = 0 = Eingabe zu kurz
   * FkDatumLong.getLongAusJJJJMMTT( null        ) = 0 = Eingabe ist "null" bzw. nicht vorhanden
   * FkDatumLong.getLongAusJJJJMMTT( "20100115A" ) = 0 = Eingabe zu lang
   * 
   * long long_datum = FkDatumLong.getLongAusJJJJMMTT( pStringDatum );
   * 
   * if ( long_datum > 0 ) 
   * {
   *   ...
   * }
   * 
   * </pre>
   * 
   * @param pEingabe die Eingabe im Format JJJJMMTT (Zahlen und 8 Stellen)
   * @return 0 oder ein Datum im Format JJJJMMTT
   */
  public static long getLongAusJJJJMMTT( String pEingabe )
  {
    try
    {
      /*
       * Pruefung: Ist die Eingabe null?
       * Ist die Eingabe null, bekommt der Aufrufer 0 zurueck.
       */
      if ( pEingabe == null )
      {
        return 0; // Fehler: EINGABE_NICHT_GESETZT
      }

      /*
       * Pruefung: Zeichenanzahl korrekt?
       * Die Eingabe muss genau 8 Stellen lang sein. 
       * Ist die Eingabe ungleich 8 Stellen, bekommt der Aufrufer 0 zurueck.
       */
      if ( pEingabe.length() != 8 )
      {
        return 0; // Fehler: EINGABE_LAENGE_UNGLEICH_8
      }

      /*
       * Deklaration der Datumsvariablen und Initialisierung mit -1.
       */
      int datum_tag = -1;
      int datum_monat = -1;
      int datum_jahr = -1;

      int akt_zahl = 0;
      int akt_index = 0;

      /*
       * While-Schleife ueber die Eingabe zum lesen von Tag, Monat und Jahr.
       * 
       * Es werden 8 Zeichen gelesen.
       * 
       * Ist der Leseprozess an der Position 3 und 5 angelangt, wird jeweils 
       * eine Zuordnung an die Datumsbestandteile gemacht. 
       */
      while ( akt_index < 8 )
      {
        /*
         * Aktuelles Zeichen aus der Eingabe lesen
         */
        char akt_char = pEingabe.charAt( akt_index );

        /*
         * Zeichenpruefung
         * Das Datum im Format JJJJMMTT besteht nur aus Zahlen.
         * Ist das aktuelle Zeichen keine Zahl, wird die Funktion mit 0 verlassen.
         */
        if ( ( akt_char < '0' ) || ( akt_char > '9' ) )
        {
          return 0; // Fehler: EINGABE_ZEICHEN_UNGUELTIG
        }

        /*
         * Berechnung Akt-Zahl
         * Der Wert in der Variablen "akt_zahl" wird mit 10 multipliziert und 
         * der Wert des aktuellen Zeichens hinzugezaehlt. 
         * 
         * Der Wert des aktuellen Zeichens ist der Wert des ASCII-Code abzueglich 48.
         */
        akt_zahl = ( akt_zahl * 10 ) + ( ( (int) akt_char ) - 48 );

        /*
         * Zuweisung von Jahr und Monat nach Index-Positionen
         */
        if ( akt_index == 3 )
        {
          /*
           * Jahr - Index 3 (4 Stellen gelesen)
           * Die ersten 4 Stellen sind Zahlen und geben das Jahr an.
           * Beim Erreichen des 4ten Zeichens wird das Jahr mit dem 
           * Wert der Variablen "akt_zahl" gesetzt.
           * 
           * Die Variable "akt_zahl" wird anschliessend auf 0 gesetzt.
           */
          datum_jahr = akt_zahl;

          akt_zahl = 0;
        }
        else if ( akt_index == 5 )
        {
          /*
           * Monat - Index 5
           * Die Zeichen 5 und 6 sind Zahlen und geben den Monat an.  
           * Beim Erreichen des 6ten Zeichens wird der Monat mit 
           * dem Wert der Variablen "akt_zahl" gesetzt.
           * 
           * Die Variable "akt_zahl" wird anschliessend auf 0 gesetzt.
           */
          datum_monat = akt_zahl;

          akt_zahl = 0;
        }

        /*
         * Leseprozess ein Zeichen weiter stellen 
         */
        akt_index++;
      }

      /*
       * Tag 
       * Die Zeichen 7 und 8 sind Zahlen und geben den Tag an.
       * Nach der While-Schleife ist in der Variablen "akt_zahl" der Tag gesetzt.
       * Der Tag wird mit dem Wert aus "akt_zahl" gesetzt.
       */
      datum_tag = akt_zahl;

      /*
       * Pruefung: Datumswerte 
       * Der Tag muss groesser als 1 sein, der Maximalwerttest kommt spaeter.
       * Der Monat muss zwischen 1 und 12 liegen
       * Das Jahr muss zwischen 1583 und 9999 liegen 
       */
      if ( ( datum_tag > 0 ) && ( ( datum_monat >= 1 ) && ( datum_monat <= 12 ) ) && ( ( datum_jahr >= 1583 ) && ( datum_jahr <= 9999 ) ) )
      {
        /*
         * Ermittlung Maximalwert Tag
         * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
         * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
         */
        int anzahl_tage = 31;

        if ( datum_monat == 2 )
        {
          if ( ( ( datum_jahr % 400 ) == 0 ) || ( ( datum_jahr % 100 ) > 0 ) && ( ( datum_jahr % 4 ) == 0 ) )
          {
            anzahl_tage = 29;
          }
          else
          {
            anzahl_tage = 28;
          }
        }
        else if ( datum_monat == 4 || datum_monat == 6 || datum_monat == 9 || datum_monat == 11 )
        {
          anzahl_tage = 30;
        }

        /*
         * Pruefung: Maximalgrenze Tag
         * Ist der uebergebene Tag kleiner oder gleich der Maximalgrenze,
         * wird das Ergebnisdatum erstellt und zurueckgegeben 
         *
         * Liegt der uebergebene Tag hinter der Maximalgrenze, ist das 
         * Datum ungueltig und der Aufrufer bekommt 0 zurueck. 
         */
        if ( datum_tag <= anzahl_tage )
        {
          /*
           * Ergebnisaufbau
           * Es wird das Datum im Format JJJJMMTT zurueckgegeben.
           */
          return datum_jahr * 10000 + datum_monat * 100 + datum_tag;
        }
      }
    }
    catch ( Exception err_inst )
    {
      // Ein Fehler in der While-Schleife ergibt eine Rueckgabe von 0
    }

    return 0; // Fehler: EINGABE_IST_KEIN_DATUM 
  }

  /**
   * <pre>
   * Liefert fuer ein gueltiges String-Datum in den Formaten TT.MM.JJJJ, T.MM.JJJJ oder TT.M.JJJJ das Long-Datum im Format JJJJMMTT.
   * 
   * Ist das String-Datum ungueltig, wird 0 zurueckgegeben.
   * 
   * Auf die Eingabe wird kein Trim gemacht.
   * 
   * Die Eingabe muss eine Laenge zwischen 8 und 10 Stellen haben.
   * Das Trennzeichen fuer die Datumsbestandteile ist der Punkt.
   * 
   * Das Jahr muss 4stellig angegeben werden.
   *  
   * Die Tages- und Monatseingabe sind 2 Stellen lang. 
   * Fuehrende 0en koennen bei diesen Angaben weggelassen werden.
   * Mehr als 2 Stellen in diesen Angaben fuehren zum Fehler.   
   * 
   * Das Jahr ist gueltig, wenn dieses im Bereich von 1583 und 9999 liegt.
   * Der Monat ist gueltig, wenn dieser im Bereich von 1 bis 12 liegt. 
   * Der Tag ist gueltig, wenn dieser entsprechend dem Gregorianischen-Kalender folgt. 
   * 
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( "10.05.2010"  ) = 20100510
   * FkDatumLong.getLongAusTTPMMPJJJJ( "10.5.2010"   ) = 20100510
   * FkDatumLong.getLongAusTTPMMPJJJJ( "1.5.2010"    ) = 20100501
   * FkDatumLong.getLongAusTTPMMPJJJJ( "1.05.2010"   ) = 20100501   
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( " 1.5.2010 "  ) = 0 = Leerzeichen fuehren zum Fehler
   * 
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( "1.5.10"      ) = 0 = zu kurz
   * FkDatumLong.getLongAusTTPMMPJJJJ( "10.05.20100" ) = 0 = zu lang
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( "1..05.2010"  ) = 0 = mehr Punkte als erwartet
   * FkDatumLong.getLongAusTTPMMPJJJJ( "1.05..2010"  ) = 0
   * FkDatumLong.getLongAusTTPMMPJJJJ( "1.05.20.10"  ) = 0
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( ".01.012010"  ) = 0 = Start mit Punkt
   * FkDatumLong.getLongAusTTPMMPJJJJ( "01.012010."  ) = 0 = Ende mit Punkt
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( "10.052010"   ) = 0 = weniger Punkte als erwartet
   * FkDatumLong.getLongAusTTPMMPJJJJ( "10052010"    ) = 0 = keine Trennzeichen in der Eingabe
   * FkDatumLong.getLongAusTTPMMPJJJJ( "1090592010"  ) = 0 = maximale Laenge und keine Punkte
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( "1A.05.2010"  ) = 0 = ungueltige Zeichen 
   * FkDatumLong.getLongAusTTPMMPJJJJ( "10.B.2010"   ) = 0
   * FkDatumLong.getLongAusTTPMMPJJJJ( "10.05.C010"  ) = 0
  
   * FkDatumLong.getLongAusTTPMMPJJJJ( "00.01.2010"  ) = 0 = Tagesanteil hat den Wert 0
   * FkDatumLong.getLongAusTTPMMPJJJJ( "01.00.2010"  ) = 0 = Monatsanteil hat den Wert 0
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( "01.13.2010"  ) = 0 = Monat existiert nicht
   * FkDatumLong.getLongAusTTPMMPJJJJ( "30.2.2010"   ) = 0 = Datum ungueltig
   * FkDatumLong.getLongAusTTPMMPJJJJ( "ABCDEFGH"    ) = 0 = nur Text
   * FkDatumLong.getLongAusTTPMMPJJJJ( "AB.CD.EFGH"  ) = 0
   * FkDatumLong.getLongAusTTPMMPJJJJ( "          "  ) = 0
   * FkDatumLong.getLongAusTTPMMPJJJJ( null          ) = 0 = keine Eingabe
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( "006.1.2010"  ) = 0 = zu viele fuehrende 0en Tag
   * FkDatumLong.getLongAusTTPMMPJJJJ( "6.001.2010"  ) = 0 = zu viele fuehrende 0en Monat
   * FkDatumLong.getLongAusTTPMMPJJJJ( "6.1.002010"  ) = 0 = zu viele fuehrende 0en Jahr
   * FkDatumLong.getLongAusTTPMMPJJJJ( "6.1.02010"   ) = 0 = zu viele Stellen Jahr
   * FkDatumLong.getLongAusTTPMMPJJJJ( "6.1.20100"   ) = 0 = zu viele Stellen Jahr
   * FkDatumLong.getLongAusTTPMMPJJJJ( "06.01.02010" ) = 0 = Jahr ungueltig
   * FkDatumLong.getLongAusTTPMMPJJJJ( "6.1.10"      ) = 0 = zu wenig Stellen Jahr
   * 
   * 
   * FkDatumLong.getLongAusTTPMMPJJJJ( "0808.2010"   ) = 0 = erster Punkt nicht an erwarteter Stelle
   *                                                         erster Punkt zu spaet in der Eingabe
   *                                                         (bzw. korrekter: Monat wird als 808 gelesen, was zu gross ist)
   *                                                  
   * FkDatumLong.getLongAusTTPMMPJJJJ( "0008.2010"   ) = 0 = es wird kein Tag gelesen, weswegen das Datum abgewiesen wird
   * </pre>
   * 
   * @param pDatum das Eingabedatum im Format "TT.MM.JJJJ" (Tag und Monat koennen einstellig sein, Jahr muss 4 stellig sein)
   * @return das Datum im Long-Format JJJJMMTT oder 0 wenn die Eingabe kein korrektes Datum ist.
   */
  public static long getLongAusTTPMMPJJJJ( String pEingabe )
  {
    /*
     * Grundlegende Funktionsweise
     * Es werden Zahlen gelesen und bei Punkten (=Trennzeichen) erfolgt eine  
     * Zuweisung an die Datumsbestanteile, welche initial -1 sind.
     * 
     * Zahlen werden durch ein Trennzeichen (=Punkt) getrennt. 
     * Die Zahlen werden gelesen und bei einem Trennzeichen wird die aktuell 
     * gelesene Zahl einem Datumsbestandteil zugewiesen.
     */

    try
    {
      /*
       * Pruefung: Ist die Eingabe "null" ?
       * 
       * Ist die Eingabe "null", bekommt der Aufrufer 0 zurueck.
       */
      if ( pEingabe == null )
      {
        return 0; // Fehler: EINGABE_NICHT_GESETZT
      }

      /*
       * Ermittlung der Zeichenanzahl der Eingabe
       */
      int anzahl_eingabe_zeichen = pEingabe.length();

      /*
       * Pruefung: Zeichenanzahl korrekt?
       * 
       * Die Eingabe darf nicht kuerzer als 8 Zeichen sein. 
       * Das sind zwei Punkte und 6 Zahlen.
       * 
       * Die Eingabe darf nicht laenger als 10 Zeichen sein. 
       * Das sind zwei Punkte und 8 Zahlen.
       * 
       * Liegt eine ungueltige Eingabe vor, bekommt der Aufrufer 0 zurueck.
       */
      if ( ( anzahl_eingabe_zeichen < 8 ) || ( anzahl_eingabe_zeichen > 10 ) )
      {
        return 0; // Fehler: EINGABE_LAENGE_NICHT_KORREKT
      }

      /*
       * Deklaration der Datumsvariablen und Initialisierung mit -1.
       */
      int datum_tag = -1;
      int datum_monat = -1;
      int datum_jahr = -1;

      int akt_zahl = 0;
      int akt_index = 0;

      /*
       * Position Punkt Erwartet
       * 
       * Die Position des ersten erwarteten Punktes wird auf 10 gestellt.
       * 
       * 0123456789  0123456789  0123456789  
       * 06.01.2010  006.1.2010  006001.201  
       */
      int position_punkt_erwartet = 10;

      /*
       * While-Schleife ueber die Eingabe zum lesen von Tag, Monat und Jahr.
       */
      while ( akt_index < anzahl_eingabe_zeichen )
      {
        /*
         * Aktuelles Zeichen aus der Eingabe lesen
         */
        char akt_char = pEingabe.charAt( akt_index );

        /*
         * Pruefung: Trennzeichen gefunden und aktuelle Zahl groesser 0 ?
         * 
         * Das Trennzeichen muss 2mal vorhanden sein.
         * Bei einem Trennzeichen muss die aktuelle Zahl groesser als 0 sein.
         * Ein Datumsbestandteil kann nicht 0 sein.
         * 
         * Ist die aktuelle Zahl nicht groesser als 0, wird das aktuelle 
         * Zeichen (=Trennzeichen) als ungueltig erkannt und fuehrt in der 
         * naechsten IF-Bedingung zu einem Fehler (Rueckgabe von 0).
         * (Kein Start mit einem Trennzeichen)
         * 
         * Ist die aktuelle Zahl groesser als 0, wird beim ersten Trennzeichen 
         * der Tag und beim zweiten Trennzeichen der Monat gesetzt. 
         * Das Jahr wird nach der While-Schleife gesetzt.
         * 
         * Kommt in der Eingabe ein drittes Trennzeichen vor, sind Tag und Monat 
         * schon gesetzt und es kommt zur Rueckgabe von 0.
         * 
         * Ist in der Eingabe kein Trennzeichen vorhanden, kommt es eventuell 
         * zu einer Ueberlaufexception, oder nach der While-Schleife ist 
         * der Tag oder der Monat noch auf dem Initialwert von -1, welches 
         * wiederum zum Fehler fuehrt. 
         * 
         * Nach der Zuweisung von Tag oder Monat, wird die aktuelle Zahl  
         * auf 0 gestellt.
         */
        if ( ( akt_char == '.' ) && ( akt_zahl > 0 ) )
        {
          if ( ( datum_tag == -1 ) && ( akt_index <= 2 ) )
          {
            /*
             * Tag
             * Der Tag hat minimal eine Stelle und maximal zwei Stellen.
             * 
             * Das Trennzeichen kommt somit an Position 1 oder 2 vor.
             * 
             * Ist die aktuelle Leseposition kleiner gleich 2, ist der Tag 
             * korrekt eingelesen worden und das Trennzeichen steht an einer 
             * korrekten Stelle. 
             * 
             * Die Position des naechsten Punktes liegt maximal 3 Stellen
             * nach der aktuellen Position.
             */
            datum_tag = akt_zahl;

            position_punkt_erwartet = akt_index + 3;
          }
          else if ( ( datum_monat == -1 ) && ( akt_index <= position_punkt_erwartet ) )
          {
            datum_monat = akt_zahl;

            /*
             * Um ein korrektes Jahr lesen zu koennen, muessen in der Eingabe 
             * noch 4 Stellen vorhanden sein. 
             * 
             * Ist die Differenz ungleich 4, wird 0 zurueckgegeben.
             * 
             * In der unten stehenden Pruefung wurde die Punkt-Position 
             * beruecksichtigt. Es wird gegen 5 getestet, da die 
             * Leseposition noch nicht weitergestellt wurde.
             */
            if ( ( anzahl_eingabe_zeichen - akt_index ) != 5 )
            {
              return 0; // Fehler: TRENNZEICHEN_AN_FALSCHER_POSITION
            }
          }
          else
          {
            return 0; // Fehler: TRENNZEICHEN_FEHLER
          }

          akt_zahl = 0;
        }
        /*
         * Zeichenpruefung
         * Ist das aktuelle Zeichen keine Zahl, wird die Funktion mit 0 verlassen.
         */
        else if ( ( akt_char < '0' ) || ( akt_char > '9' ) )
        {
          return 0; // Fehler: EINGABE_ZEICHEN_UNGUELTIG
        }
        else
        {
          /*
           * Berechnung Akt-Zahl
           * Der Wert in der Variablen "akt_zahl" wird mit 10 multipliziert und 
           * der Wert des aktuellen Zeichens hinzugezaehlt. 
           * 
           * Der Wert des aktuellen Zeichens ist der Wert des ASCII-Code abzueglich 48. 
           */
          akt_zahl = ( akt_zahl * 10 ) + ( ( (int) akt_char ) - 48 );
        }

        /*
         * Leseprozess ein Zeichen weiter stellen 
         */
        akt_index++;
      }

      /*
       * Nach dem zweiten Trennzeichen sind die restlichen Zahlen das Jahr. 
       * Nach der While-Schleife ist in der Variablen "akt_zahl" das Jahr gesetzt.
       * Das Jahr wird hier mit dem Wert aus "akt_zahl" gesetzt.
       * 
       * Die Variable "akt_zahl" muss sein, es kann keine Variable eingespart werden. 
       * (Wird die Variable eingespart, ist die Funktion langsamer (weil Java-Optimierungen))
       */
      datum_jahr = akt_zahl;

      /*
       * Pruefung: Datumswerte 
       * Der Tag muss groesser als 1 sein, der Maximalwerttest kommt spaeter.
       * Der Monat muss zwischen 1 und 12 liegen
       * Das Jahr muss zwischen 1583 und 9999 liegen 
       */
      if ( ( datum_tag > 0 ) && ( ( datum_monat >= 1 ) && ( datum_monat <= 12 ) ) && ( ( datum_jahr >= 1583 ) && ( datum_jahr <= 9999 ) ) )
      {
        /*
         * Ermittlung Maximalwert Tag
         * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
         * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
         */
        int anzahl_tage = 31;

        if ( datum_monat == 2 )
        {
          if ( ( ( datum_jahr % 400 ) == 0 ) || ( ( datum_jahr % 100 ) > 0 ) && ( ( datum_jahr % 4 ) == 0 ) )
          {
            anzahl_tage = 29;
          }
          else
          {
            anzahl_tage = 28;
          }
        }
        else if ( datum_monat == 4 || datum_monat == 6 || datum_monat == 9 || datum_monat == 11 )
        {
          anzahl_tage = 30;
        }

        /*
         * Pruefung: Maximalgrenze Tag
         * Es gibt nur dann ein Ergebnis, wenn der Tag kleiner 
         * oder gleich der Maximalgrenze ist.
         */
        if ( datum_tag <= anzahl_tage )
        {
          /*
           * Ergebnisaufbau
           * Rueckgabe im Format JJJJMMTT.
           */
          return datum_jahr * 10000 + datum_monat * 100 + datum_tag;
        }
      }
    }
    catch ( Exception err_inst )
    {
      /*
       * Ein Fehler beim Lesen des Datums ergibt eine Rueckgabe von 0.
       */
    }

    /*
     * Fehler: Rueckgabe von 0
     */
    return 0; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Gibt das Datum im Format JJJJMMTT als long zurueck.
   * 
   * Sind die Datumsangaben ungueltig, wird 0 zurueckgegeben.
   * 
   * Das Jahr ist gueltig, wenn dieses im Bereich von 1583 und 9999 liegt.
   * Der Monat ist gueltig, wenn dieser im Bereich von 1 bis 12 liegt. 
   * Der Tag ist gueltig, wenn dieser entsprechend dem Gregorianischen-Kalender folgt. 
   * 
   * FkDatumLong.getLong(  2010,  6,  1 ) = 20100106
   * 
   * FkDatumLong.getLong(  1583,  1,  1 ) = 15830101
   * FkDatumLong.getLong(  1582, 12,  1 ) = 0
   * 
   * FkDatumLong.getLong( -2010,  6,  1 ) = 0 = Jahr negativ
   * FkDatumLong.getLong(  2010, -6,  1 ) = 0 = Monat negativ 
   * FkDatumLong.getLong(  2010,  6, -1 ) = 0 = Tag negativ 
   * FkDatumLong.getLong(  2010,  2, 29 ) = 0 = Tag gibt es nicht
   * FkDatumLong.getLong(  1234, 56, 78 ) = 0 
   * 
   * </pre>
   * 
   * @param pJahr   das Jahr
   * @param pMonat  der Monat
   * @param pTag    der Tag
   * @return das formatierte Datum als Datentyp long
   */
  public static long getLong( int pJahr, int pMonat, int pTag )
  {
    /*
     * Pruefung: Datumswerte 
     * Der Tag muss groesser als 1 sein, der Maximalwerttest kommt spaeter.
     * Der Monat muss zwischen 1 und 12 liegen
     * Das Jahr muss zwischen 1583 und 9999 liegen 
     */
    if ( ( pTag >= 1 ) && ( ( pMonat >= 1 ) && ( pMonat <= 12 ) ) && ( ( pJahr >= 1583 ) && ( pJahr <= 9999 ) ) )
    {
      /*
       * Ermittlung Maximalwert Tag
       * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
       * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
       */
      int anzahl_tage = 31;

      if ( pMonat == 2 )
      {
        if ( ( ( pJahr % 400 ) == 0 ) || ( ( pJahr % 100 ) > 0 ) && ( ( pJahr % 4 ) == 0 ) )
        {
          anzahl_tage = 29;
        }
        else
        {
          anzahl_tage = 28;
        }
      }
      else if ( pMonat == 4 || pMonat == 6 || pMonat == 9 || pMonat == 11 )
      {
        anzahl_tage = 30;
      }

      /*
       * Pruefung: Maximalgrenze Tag
       * Es gibt nur dann ein Ergebnis, sofern der uebergebene Tag kleiner 
       * oder gleich der Maximalgrenze ist.
       */
      if ( pTag <= anzahl_tage )
      {
        /*
         * Ergebnisaufbau
         * Rueckgabe im Format JJJJMMTT.
         */
        return pJahr * 10000 + pMonat * 100 + pTag;
      }
    }

    return 0; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Gibt das Datum im Format JJJJMMTT als long zurueck.
   * 
   * Sind die Datumsangaben ungueltig, wird 0 zurueckgegeben.
   * 
   * Das Jahr ist gueltig, wenn dieses im Bereich von 1583 und 9999 liegt.
   * Der Monat ist gueltig, wenn dieser im Bereich von 1 bis 12 liegt. 
   * Der Tag ist gueltig, wenn dieser entsprechend dem Gregorianischen-Kalender folgt. 
   * 
   * FkDatumLong.getLong( 2010, 6, 1 ) = 20100106
   * 
   * FkDatumLong.getLong( 1583,  1, 1 ) = 15830101
   * FkDatumLong.getLong( 1582, 12, 1 ) = 0
   * 
   * FkDatumLong.getLong( -2010,  6,  1 ) = 0 = Jahr negativ
   * FkDatumLong.getLong(  2010, -6,  1 ) = 0 = Monat negativ 
   * FkDatumLong.getLong(  2010,  6, -1 ) = 0 = Tag negativ 
   * FkDatumLong.getLong(  2010,  2, 29 ) = 0 = Tag gibt es nicht
   * FkDatumLong.getLong(  1234, 56, 78 ) = 0 
   * </pre>
   * 
   * @param pJahr   das Jahr
   * @param pMonat  der Monat
   * @param pTag    der Tag
   * @return das formatierte Datum als Datentyp long
   */
  public static long getLong( long pJahr, long pMonat, long pTag )
  {
    /*
     * Pruefung: Datumswerte 
     * Der Tag muss groesser als 1 sein, der Maximalwerttest kommt spaeter.
     * Der Monat muss zwischen 1 und 12 liegen
     * Das Jahr muss zwischen 1583 und 9999 liegen 
     */
    if ( ( pTag >= 1 ) && ( ( pMonat >= 1 ) && ( pMonat <= 12 ) ) && ( ( pJahr >= 1583 ) && ( pJahr <= 9999 ) ) )
    {
      /*
       * Ermittlung Maximalwert Tag
       * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
       * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
       */
      int anzahl_tage = 31;

      if ( pMonat == 2 )
      {
        if ( ( ( pJahr % 400 ) == 0 ) || ( ( pJahr % 100 ) > 0 ) && ( ( pJahr % 4 ) == 0 ) )
        {
          anzahl_tage = 29;
        }
        else
        {
          anzahl_tage = 28;
        }
      }
      else if ( pMonat == 4 || pMonat == 6 || pMonat == 9 || pMonat == 11 )
      {
        anzahl_tage = 30;
      }

      /*
       * Pruefung: Maximalgrenze Tag
       * Es gibt nur dann ein Ergebnis, sofern der uebergebene Tag kleiner 
       * oder gleich der Maximalgrenze ist.
       */
      if ( pTag <= anzahl_tage )
      {
        /*
         * Ergebnisaufbau
         * Rueckgabe im Format JJJJMMTT.
         */
        return pJahr * 10000 + pMonat * 100 + pTag;
      }
    }

    return 0; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Prueft den Wert des Parameters "pLongWert" darauf ab, ob dieses ein Datum im Format JJJJMMTT ist.
   * 
   * Ist es ein Long-Datum, wird der Eingabeparameter zurueckgegeben.
   *  
   * Ist es kein Long-Datum, wird 0 zurueckgegeben.
   * 
   * Das Jahr muss groesser gleich 1583 und kleiner als 9999 sein.
   * 
   * Diese Funktion dient dazu, ein Long-Datum zu pruefen.
   * 
   * Das Jahr ist gueltig, wenn dieses im Bereich von 1583 und 9999 liegt.
   * Der Monat ist gueltig, wenn dieser im Bereich von 1 bis 12 liegt. 
   * Der Tag ist gueltig, wenn dieser entsprechend dem Gregorianischen-Kalender folgt. 
   * 
   * FkDatumLong.getLong(  20100101 ) = 20100101
   * FkDatumLong.getLong(  20120229 ) = 20120229
   *   
   * FkDatumLong.getLong(  20100100 ) = 0 = Tag ist 0
   * FkDatumLong.getLong(  20100199 ) = 0 = Tag zu gross
   * 
   * FkDatumLong.getLong(  20100001 ) = 0 = Monat ist 0
   * FkDatumLong.getLong(  20109901 ) = 0 = Monat zu gross
   * 
   * FkDatumLong.getLong(  20100229 ) = 0 = Tag gibt es nicht
   * 
   * FkDatumLong.getLong(  15830101 ) = 15830101 = Eingabe gleich Untergrenze
   * FkDatumLong.getLong(  15820101 ) = 0        = Eingabe zu klein
   * 
   * FkDatumLong.getLong(  99991231 ) = 99991231 = Eingabe ist Obergrenze
   * FkDatumLong.getLong(  99991232 ) = 0        = Eingabe zu gross
   *  
   * FkDatumLong.getLong(         0 ) = 0 = Eingabe zu klein
   * FkDatumLong.getLong( -20100101 ) = 0 = Eingabe negativ
   * FkDatumLong.getLong( 201001011 ) = 0 = Eingabe zu gross
   * FkDatumLong.getLong( 220100101 ) = 0 = Eingabe zu gross
   * 
   * </pre>
   * 
   * @param pLongWert der auf ein Datum zu pruefende Wert
   * @return 0 wenn der Wert im Parameter "pLongWert" kein Datum ergibt, sonst das Datum im Format JJJJMMTT
   */
  public static long getLong( long pLongWert )
  {
    /*
     * Parameterpruefung auf Einhaltung der Grenzen von 15830101 bis 99991231.
     */
    if ( ( pLongWert >= 15830101 ) && ( pLongWert <= 99991231 ) )
    {
      /*
       * Rausrechnen von Jahr, Monat und Tag aus der Eingabe
       */
      int datum_jahr = (int) ( pLongWert * 0.0001 );

      int datum_monat = (int) ( ( pLongWert - ( datum_jahr * 10000 ) ) * 0.01 );

      int datum_tag = (int) ( pLongWert - ( datum_jahr * 10000 + datum_monat * 100 ) );

      /*
       * Pruefung: Datumswerte 
       * Der Tag muss groesser als 0 sein, der Maximalwerttest kommt spaeter.
       * Der Tag kann an dieser Stelle eventuell 0 sein.
       * Der Monat muss zwischen 1 und 12 liegen
       * Das Jahr wurde implizit geprueft und muss kein zweites mal geprueft werden. 
       */
      if ( ( datum_tag > 0 ) && ( ( datum_monat >= 1 ) && ( datum_monat <= 12 ) ) )
      {
        /*
         * Ermittlung Maximalwert Tag
         * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
         * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
         */
        int anzahl_tage = 31;

        if ( datum_monat == 2 )
        {
          if ( ( ( datum_jahr % 400 ) == 0 ) || ( ( datum_jahr % 100 ) > 0 ) && ( ( datum_jahr % 4 ) == 0 ) )
          {
            anzahl_tage = 29;
          }
          else
          {
            anzahl_tage = 28;
          }
        }
        else if ( datum_monat == 4 || datum_monat == 6 || datum_monat == 9 || datum_monat == 11 )
        {
          anzahl_tage = 30;
        }

        /*
         * Pruefung: Maximalgrenze Tag
         * Es gibt nur dann ein Ergebnis, sofern der uebergebene Tag kleiner 
         * oder gleich der Maximalgrenze ist.
         */
        if ( datum_tag <= anzahl_tage )
        {
          /*
           * Ergebnisaufbau
           * Bei der Rueckgabe wird das Datum aus dem Parameter zurueckgegeben,
           * da die Pruefung ergeben hat, dass das ein gueltiges Datum ist und 
           * schon im korrektem Format vorliegt. Es muss nicht gerechnet werden.
           */
          return pLongWert;
        }
      }
    }

    return 0; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  public static String getSystemDateTimeDataObjectEA( String pDatum )
  {
    try
    {
      /*
       * Pruefung: Ist die Eingabe null?
       * Ist die Eingabe null, bekommt der Aufrufer null zurueck.
       */
      if ( pDatum == null )
      {
        return null; // Fehler: EINGABE_NICHT_GESETZT
      }

      /*
       * Pruefung: Zeichenanzahl korrekt?
       * Die Eingabe muss genau 8 Stellen lang sein. 
       * Ist die Eingabe ungleich 8 Stellen, bekommt der Aufrufer null zurueck.
       */
      if ( pDatum.length() != 14 )
      {
        return null; // Fehler: EINGABE_LAENGE_NICHT_KORREKT
      }

      /*
       * Deklaration der Variablen und Initialisierung mit -1.
       */
      int datum_tag = -1;
      int datum_monat = -1;
      int datum_jahr = -1;

      int akt_stunde = -1;
      int akt_minute = -1;
      int akt_sekunde = -1;

      int akt_zahl = 0;

      int akt_index = 0;

      /*
       * While-Schleife ueber die Eingabe zum lesen von Tag, Monat und Jahr.
       */
      while ( akt_index < 14 )
      {
        /*
         * Aktuelles Zeichen aus der Eingabe lesen
         */
        char akt_char = pDatum.charAt( akt_index );

        /*
         * Zeichenpruefung
         * Das Datum im Format JJJJMMTT besteht nur aus Zahlen.
         * Ist das aktuelle Zeichen keine Zahl, wird die Funktion mit null verlassen.
         */
        if ( ( akt_char < '0' ) || ( akt_char > '9' ) )
        {
          return null; // Fehler: EINGABE_ZEICHEN_UNGUELTIG
        }

        /*
         * Berechnung Akt-Zahl
         * Der Wert in der Variablen "akt_zahl" wird mit 10 multipliziert und 
         * der Wert des aktuellen Zeichens hinzugezaehlt. 
         * 
         * Der Wert des aktuellen Zeichens ist der Wert des ASCII-Code abzueglich 48. 
         */
        akt_zahl = ( akt_zahl * 10 ) + ( ( (int) akt_char ) - 48 );

        /*
         * Zuweisung von Jahr und Monat nach Index-Positionen
         */
        if ( akt_index == 3 )
        {
          /*
           * Index 3 (4 Stellen gelesen)
           * Die ersten 4 Stellen sind Zahlen und geben das Jahr an.
           * Beim Erreichen des 4ten Zeichens wird das Jahr mit dem 
           * Wert der Variablen "akt_zahl" gesetzt.
           * 
           * Die Variable "akt_zahl" wird anschliessend auf 0 gesetzt.
           */
          datum_jahr = akt_zahl;

          akt_zahl = 0;
        }
        else if ( akt_index == 5 )
        {
          /*
           * Index 5
           * Die Zeichen 5 und 6 sind Zahlen und geben den Monat an.  
           * Beim Erreichen des 6ten Zeichens wird der Monat mit 
           * dem Wert der Variablen "akt_zahl" gesetzt.
           * 
           * Die Variable "akt_zahl" wird anschliessend auf 0 gesetzt.
           */
          datum_monat = akt_zahl;

          akt_zahl = 0;
        }
        else if ( akt_index == 7 )
        {
          datum_tag = akt_zahl;

          akt_zahl = 0;
        }
        else if ( akt_index == 9 )
        {
          akt_stunde = akt_zahl;

          akt_zahl = 0;
        }
        else if ( akt_index == 11 )
        {
          akt_minute = akt_zahl;

          akt_zahl = 0;
        }

        /*
         * Leseprozess ein Zeichen weiter stellen 
         */
        akt_index++;
      }

      /*
       * Die Zeichen 7 und 8 sind Zahlen und geben den Tag an.
       * Nach der While-Schleife ist in der Variablen "akt_zahl" der Tag gesetzt.
       * Der Tag wird mit dem Wert aus "akt_zahl" gesetzt.
       */
      akt_sekunde = akt_zahl;

      /*
       * Pruefung: Datumswerte 
       * Der Tag muss groesser als 0 sein, der Maximalwert test kommt spaeter.
       * Der Monat muss zwischen 1 und 12 liegen
       * Das Jahr muss zwischen 1583 und 9999 liegen 
       */
      if ( ( datum_tag > 0 ) && ( ( datum_monat >= 1 ) && ( datum_monat <= 12 ) ) && ( ( datum_jahr >= 1583 ) && ( datum_jahr <= 9999 ) ) )
      {
        /*
         * Ermittlung Maximalwert Tag
         * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
         * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
         */
        int anzahl_tage = 31;

        if ( datum_monat == 2 )
        {
          if ( ( ( datum_jahr % 400 ) == 0 ) || ( ( datum_jahr % 100 ) > 0 ) && ( ( datum_jahr % 4 ) == 0 ) )
          {
            anzahl_tage = 29;
          }
          else
          {
            anzahl_tage = 28;
          }
        }
        else if ( datum_monat == 4 || datum_monat == 6 || datum_monat == 9 || datum_monat == 11 )
        {
          anzahl_tage = 30;
        }

        /*
         * Pruefung: Maximalgrenze Tag
         * Ist der uebergebene Tag kleiner oder gleich der Maximalgrenze,
         * wird der Ergebnisstring erstellt. 
         *
         * Liegt der uebergebene Tag hinter der Maximalgrenze, ist das 
         * Datum ungueltig und es gibt kein Ergebnis. 
         */
        if ( datum_tag <= anzahl_tage )
        {
          return datum_jahr + ( datum_monat < 10 ? "-0" : "-" ) + datum_monat + ( datum_tag < 10 ? "-0" : "-" ) + datum_tag + ( akt_stunde < 10 ? "T0" : "T" ) + akt_stunde + ( akt_minute < 10 ? ":0" : ":" ) + akt_minute + ( akt_sekunde < 10 ? ":0" : ":" ) + akt_sekunde;
        }
      }
    }
    catch ( Exception err_inst )
    {
      /*
       * Ein Fehler beim Lesen des Datums ergibt eine Rueckgabe von null.
       */
    }

    /*
     * Funktionsende
     * Der Aufrufer bekommt das erstellte Ergebnis zurueck. 
     */
    return null; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Erstellt aus der Eingabe JJJJxMMxTT oder JJJJxMxTT oder JJJJxMMxT die Ausgabe JJJJMMTT.
   * 
   * Das Eingabedatum muss korrekt sein.
   * 
   * Die Eingabe muss eine Laenge zwischen 8 und 10 Stellen haben.
   * 
   * Das Trennzeichen muss in der Eingabe vorkommen.
   * 
   * Das Trennzeichen darf keine Zahl sein.
   * 
   * Ist die Eingabe kein gueltiges Datum, wird 0 zurueckgegeben.
   * 
   * Das Jahr muss 4stellig angegeben werden.
   *  
   * Die Tages- und Monatseingabe sind 2 Stellen lang. 
   * Fuehrende 0en koennen weggelassen werden.
   * Mehr als 2 Stellen in diesen Angaben fuehren zum Fehler.   
   * 
   * Das Jahr ist gueltig, wenn dieses im Bereich von 1583 und 9999 liegt.
   * Der Monat ist gueltig, wenn dieser im Bereich von 1 bis 12 liegt. 
   * Der Tag ist gueltig, wenn dieser entsprechend dem Gregorianischen-Kalender folgt. 
   * 
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-01-06",    '-' ) = 20100106
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-01-6",     '-' ) = 20100106
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-1-06",     '-' ) = 20100106
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-1-6",      '-' ) = 20100106
   * FkDatumLong.getLongAusJJJJxMMxTT( " 2010-01-06 ",  '-' ) = 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010 01 06",    '-' ) = 0 = Trennzeichen nicht in Eingabe vorhanden
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-02-31",    '-' ) = 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-13-01",    '-' ) = 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010.12.01",    '-' ) = 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "20101201",      '-' ) = 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-02-28",    '-' ) = 20100228
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-02-29",    '-' ) = 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "1234-67-90",    '-' ) = 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "ABCD-EF-GH",    '-' ) = 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-01-06",    'X' ) = 0        = Trennzeichen nicht in Eingabe vorhanden
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010X06X01",    'X' ) = 20100106 
   * FkDatumLong.getLongAusJJJJxMMxTT( null,            '-' ) = 0        = Eingabe nicht gesetzt
   * FkDatumLong.getLongAusJJJJxMMxTT( "",              '-' ) = 0        = Eingabe ist ein Leerstring
   * 
   * FkDatumLong.getLongAusJJJJxMMxTT( "0000-06-01",    '-' ) = 0        = Jahr ist 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-00-01",    '-' ) = 0        = Monat ist 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "2010-06-00",    '-' ) = 0        = Tag ist 0
   * 
   * FkDatumLong.getLongAusJJJJxMMxTT( "2020-01-02",    '-' ) = 20200102
   * FkDatumLong.getLongAusJJJJxMMxTT( "2020-1-02",     '-' ) = 20200102
   * FkDatumLong.getLongAusJJJJxMMxTT( "2020-01-2",     '-' ) = 20200102
   * FkDatumLong.getLongAusJJJJxMMxTT( "2020-1-2",      '-' ) = 20200102
   * 
   * FkDatumLong.getLongAusJJJJxMMxTT( "2020-1-002",    '-' ) = 0        = zu viele fuehrende 0en Tag
   * FkDatumLong.getLongAusJJJJxMMxTT( "2020-001-2",    '-' ) = 0        = zu viele fuehrende 0en Monat
   * FkDatumLong.getLongAusJJJJxMMxTT( "02020-1-02",    '-' ) = 0        = zu viele fuehrende 0en Jahr
   * FkDatumLong.getLongAusJJJJxMMxTT( "002020-1-2",    '-' ) = 0        = zu viele fuehrende 0en Jahr
   * FkDatumLong.getLongAusJJJJxMMxTT( "20-01-02",      '-' ) = 0        = zu wenig Stellen Jahr
   * FkDatumLong.getLongAusJJJJxMMxTT( "2020001002",    '-' ) = 0        = Kein Trennzeichen
   * 
   * FkDatumLong.getLongAusJJJJxMMxTT( "2020001002",    '0' ) = 0
   * FkDatumLong.getLongAusJJJJxMMxTT( "2020901902",    '9' ) = 0
   * 
   * </pre>
   * 
   * @param pDatum die Eingabe
   * @param pTrennzeichen das Trennzeichen der Datumsbestandteile
   * @return 0 oder ein Datum im Format JJJJMMTT als long-Wert
   */
  public static long getLongAusJJJJxMMxTT( String pDatum, char pTrennzeichen )
  {
    try
    {
      /*
       * Pruefung: Ist die Eingabe null?
       * Ist die Eingabe null, bekommt der Aufrufer 0 zurueck.
       */
      if ( pDatum == null )
      {
        return 0; // Fehler: EINGABE_NICHT_GESETZT
      }

      /*
       * Pruefung: Trennzeichen gleich Zahl ?
       * 
       * Das Trennzeichen selber darf keine Zahl sein.
       * Wurde als Trennzeichen eine Zahl uebergeben, wird 
       * 0 zurueckgegeben.
       */
      if ( ( pTrennzeichen >= '0' ) && ( pTrennzeichen <= '9' ) )
      {
        return 0; // Fehler: TRENNZEICHEN_IST_ZAHL
      }

      /*
       * Ermittlung der Zeichenanzahl der Eingabe
       */
      int anzahl_eingabe_zeichen = pDatum.length();

      /*
       * Pruefung: Zeichenanzahl korrekt?
       * 
       * Die Eingabe darf nicht kuerzer als 8 Zeichen sein. 
       * Das sind zwei Punkte und 6 Zahlen.
       * 
       * Die Eingabe darf nicht laenger als 10 Zeichen sein. 
       * Das sind zwei Punkte und 8 Zahlen.
       * 
       * Liegt eine ungueltige Eingabe vor, bekommt der Aufrufer 0 zurueck.
       */
      if ( ( anzahl_eingabe_zeichen < 8 ) || ( anzahl_eingabe_zeichen > 10 ) )
      {
        return 0; // Fehler: EINGABE_LAENGE_NICHT_KORREKT
      }

      /*
       * Deklaration der Datumsvariablen und Initialisierung mit -1.
       */
      int datum_tag = -1;
      int datum_monat = -1;
      int datum_jahr = -1;

      int akt_zahl = 0;
      int akt_index = 0;

      /*
       * While-Schleife ueber die Eingabe zum lesen von Tag, Monat und Jahr.
       */
      while ( akt_index < anzahl_eingabe_zeichen )
      {
        /*
         * Aktuelles Zeichen aus der Eingabe lesen
         */
        char akt_char = pDatum.charAt( akt_index );

        /*
         * Pruefung: Trennzeichen gefunden und aktuelle Zahl groesser 0 ?
         * 
         * Das Trennzeichen muss 2mal vorhanden sein.
         * Bei einem Trennzeichen muss die aktuelle Zahl groesser als 0 sein.
         * Ein Datumsbestandteil kann nicht 0 sein.
         * 
         * Ist die aktuelle Zahl nicht groesser als 0, wird das aktuelle 
         * Zeichen (=Trennzeichen) als ungueltig erkannt und fuehrt in der 
         * naechsten IF-Bedingung zu einem Fehler (Rueckgabe von 0).
         * (Kein Start mit einem Trennzeichen)
         * 
         * Ist die aktuelle Zahl groesser als 0, wird beim ersten Trennzeichen 
         * das Jahr und beim zweiten Trennzeichen der Monat gesetzt. 
         * Der Tag wird nach der While-Schleife gesetzt.
         * 
         * Wird ein drittes Trennzeichen erkannt, sind Jahr und Monat 
         * schon gesetzt und es kommt zur Rueckgabe von 0.
         * 
         * Ist in der Eingabe kein Trennzeichen vorhanden, kommt es eventuell 
         * zu einer Ueberlaufexception, oder nach der While-Schleife ist 
         * der Monat oder das Jahr noch auf dem Initialwert von -1.
         * 
         * Nach der Zuweisung von Jahr oder Monat, wird die aktuelle Zahl  
         * auf 0 gestellt.
         */
        if ( ( akt_char == pTrennzeichen ) && ( akt_zahl > 0 ) )
        {
          if ( ( datum_jahr == -1 ) && ( akt_index == 4 ) )
          {
            /*
             * Das Jahr ist korrekt eingelesen, wenn das erste Trennzeichen 
             * an der 4ten Indexposition steht (= 5te Stelle im String).
             */

            datum_jahr = akt_zahl;
          }
          else if ( ( datum_monat == -1 ) && ( akt_index <= 7 ) )
          {
            /*
             * Der Monat ist korrekt eingelesen, wenn das zweite Trennzeichen 
             * an einer Indexposition kleiner gleich 7 steht. 
             * 
             * Das Jahr wird immer 4stellig gelesen, daher kann an dieser 
             * Stelle die 7 als konstanter Wert stehen.
             */
            datum_monat = akt_zahl;

            /*
             * Um eine korrekte Tagesangebe lesen zu koennen, duerfen in 
             * der Eingabe nicht mehr als 2 Stellen uebrig bleiben. 
             * 
             * Da hier die Lesepositon noch nicht erhoeht worden ist, 
             * muss die Pruefung auf groesser 3 lauten.
             * 
             * Bleiben mehr Stellen uebrig, wird 0 zurueckgegeben.
             */
            if ( ( anzahl_eingabe_zeichen - akt_index ) > 3 )
            {
              return 0; // Fehler: TRENNZEICHEN_AN_FALSCHER_POSITION
            }
          }
          else
          {
            return 0; // Fehler: TRENNZEICHEN_FEHLER
          }

          akt_zahl = 0;
        }
        /*
         * Zeichenpruefung
         * Ist das aktuelle Zeichen keine Zahl, wird die Funktion mit 0 verlassen.
         */
        else if ( ( akt_char < '0' ) || ( akt_char > '9' ) )
        {
          return 0; // Fehler: EINGABE_ZEICHEN_UNGUELTIG
        }
        else
        {
          /*
           * Berechnung Akt-Zahl
           * Der Wert in der Variablen "akt_zahl" wird mit 10 multipliziert und 
           * der Wert des aktuellen Zeichens hinzugezaehlt. 
           * 
           * Der Wert des aktuellen Zeichens ist der Wert des ASCII-Code abzueglich 48. 
           */
          akt_zahl = ( akt_zahl * 10 ) + ( ( (int) akt_char ) - 48 );
        }

        /*
         * Leseprozess ein Zeichen weiter stellen 
         */
        akt_index++;
      }

      /*
       * Nach der While-Schleife ist in der Variablen "akt_zahl" der Tag gesetzt.
       * Der Tag wird hier mit dem Wert aus "akt_zahl" gesetzt.
       */
      datum_tag = akt_zahl;

      /*
       * Pruefung: Datumswerte 
       * Der Tag muss groesser als 0 sein, der Maximalwert test kommt spaeter.
       * Der Monat muss zwischen 1 und 12 liegen
       * Das Jahr muss zwischen 1583 und 9999 liegen 
       */
      if ( ( datum_tag > 0 ) && ( ( datum_monat >= 1 ) && ( datum_monat <= 12 ) ) && ( ( datum_jahr >= 1583 ) && ( datum_jahr <= 9999 ) ) )
      {
        /*
         * Ermittlung Maximalwert Tag
         * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
         * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
         */
        int anzahl_tage = 31;

        if ( datum_monat == 2 )
        {
          if ( ( ( datum_jahr % 400 ) == 0 ) || ( ( datum_jahr % 100 ) > 0 ) && ( ( datum_jahr % 4 ) == 0 ) )
          {
            anzahl_tage = 29;
          }
          else
          {
            anzahl_tage = 28;
          }
        }
        else if ( datum_monat == 4 || datum_monat == 6 || datum_monat == 9 || datum_monat == 11 )
        {
          anzahl_tage = 30;
        }

        /*
         * Pruefung: Maximalgrenze Tag
         * Es gibt nur dann ein Ergebnis, sofern der uebergebene Tag kleiner 
         * oder gleich der Maximalgrenze ist.
         */
        if ( datum_tag <= anzahl_tage )
        {
          /*
           * Der Aufrufer bekommt das Datum im Format JJJJMMTT zurueck.
           */
          return datum_jahr * 10000 + datum_monat * 100 + datum_tag;
        }
      }
    }
    catch ( Exception err_inst )
    {
      /*
       * Ein Fehler beim Lesen des Datums ergibt eine Rueckgabe von 0.
       */
    }

    /*
     * Bei einem Fehler, bekommt der Aufrufer 0 zurueck. 
     */
    return 0; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Erstellt aus der Eingabe TTxMMxJJJJ die Ausgabe JJJJMMTT.
   * 
   * Das Eingabedatum muss korrekt sein.
   * 
   * Die Eingabe muss eine Laenge zwischen 8 und 10 Stellen haben.
   * 
   * Das Trennzeichen muss in der Eingabe vorkommen.
   * Das Trennzeichen darf keine Zahl sein.
   * 
   * Ist die Eingabe kein gueltiges Datum, wird 0 zurueckgegeben.
   * 
   * Das Jahr muss 4stellig angegeben werden.
   *  
   * Die Tages- und Monatseingabe sind 2 Stellen lang. 
   * Fuehrende 0en koennen bei diesen Angaben weggelassen werden.
   * Mehr als 2 Stellen in diesen Angaben fuehren zum Fehler.   
   * 
   * FkDatumLong.getLongAusTTxMMxJJJJ( "06-01-2010",    '-' ) = 20100106
   * FkDatumLong.getLongAusTTxMMxJJJJ( "06-1-2010",     '-' ) = 20100106
   * FkDatumLong.getLongAusTTxMMxJJJJ( "6-01-2010",     '-' ) = 20100106
   * FkDatumLong.getLongAusTTxMMxJJJJ( "6-1-2010",      '-' ) = 20100106
   * FkDatumLong.getLongAusTTxMMxJJJJ( "06-01-2010 ",   '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "06 01-2010",    '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "02-31-2010",    '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "13-01-2010",    '-' ) = 20100113
   * FkDatumLong.getLongAusTTxMMxJJJJ( "01-12-2010",    '-' ) = 20101201
   * FkDatumLong.getLongAusTTxMMxJJJJ( "02-28-2010",    '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "02-29-2010",    '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "67-90-1234",    '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "EF-GH-ABCD",    '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "06-01-2010",    'X' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "06X01X2010",    'X' ) = 20100106
   * FkDatumLong.getLongAusTTxMMxJJJJ( null,            '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "",              '-' ) = 0
   * 
   * FkDatumLong.getLongAusTTxMMxJJJJ( "00-01-2010",    '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "06-00-2010",    '-' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "06-01-0000",    '-' ) = 0
   * 
   * FkDatumLong.getLongAusTTxMMxJJJJ( "06.01.2010",    '.' ) = 20100106
   * FkDatumLong.getLongAusTTxMMxJJJJ( "006.1.2010",    '.' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "6.001.2010",    '.' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "006.1.2010",    '.' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "6.1.002010",    '.' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "006001-2010",   '-' ) = 0
   * 
   * FkDatumLong.getLongAusTTxMMxJJJJ( "0100202020",    '0' ) = 0
   * FkDatumLong.getLongAusTTxMMxJJJJ( "0190292020",    '9' ) = 0
   * </pre>
   * 
   * @param pDatum die Eingabe
   * @param pTrennzeichen das Trennzeichen der Datumsbestandteile. Das Trennzeichen darf keine Zahl sein.
   * @return 0 oder ein Datum im Format JJJJMMTT als long-Wert
   */
  public static long getLongAusTTxMMxJJJJ( String pDatum, char pTrennzeichen )
  {
    try
    {
      /*
       * Pruefung: Ist die Eingabe null?
       * Ist die Eingabe null, bekommt der Aufrufer 0 zurueck.
       */
      if ( pDatum == null )
      {
        return 0; // Fehler: EINGABE_NICHT_GESETZT
      }

      /*
       * Pruefung: Trennzeichen gleich Zahl ?
       * 
       * Das Trennzeichen selber darf keine Zahl sein.
       * Wurde als Trennzeichen eine Zahl uebergeben, wird 
       * 0 zurueckgegeben.
       */
      if ( ( pTrennzeichen >= '0' ) && ( pTrennzeichen <= '9' ) )
      {
        return 0; // Fehler: TRENNZEICHEN_IST_ZAHL
      }

      /*
       * Ermittlung der Zeichenanzahl der Eingabe
       */
      int anzahl_eingabe_zeichen = pDatum.length();

      /*
       * Pruefung: Zeichenanzahl korrekt?
       * 
       * Die Eingabe darf nicht kuerzer als 8 Zeichen sein. 
       * Das sind zwei Punkte und 6 Zahlen.
       * 
       * Die Eingabe darf nicht laenger als 10 Zeichen sein. 
       * Das sind zwei Punkte und 8 Zahlen.
       * 
       * Liegt eine ungueltige Eingabe vor, bekommt der Aufrufer 0 zurueck.
       */
      if ( ( anzahl_eingabe_zeichen < 8 ) || ( anzahl_eingabe_zeichen > 10 ) )
      {
        return 0; // Fehler: EINGABE_LAENGE_NICHT_KORREKT
      }

      /*
       * Deklaration der Datumsvariablen und Initialisierung mit -1.
       */
      int datum_tag = -1;
      int datum_monat = -1;
      int datum_jahr = -1;

      int akt_zahl = 0;
      int akt_index = 0;

      /*
       * 0123456789
       * 06.01.2010
       */
      int position_punkt_erwartet = 5;

      /*
       * While-Schleife ueber die Eingabe zum lesen von Tag, Monat und Jahr.
       */
      while ( akt_index < anzahl_eingabe_zeichen )
      {
        /*
         * Aktuelles Zeichen aus der Eingabe lesen
         */
        char akt_char = pDatum.charAt( akt_index );

        /*
         * Pruefung: Trennzeichen gefunden und aktuelle Zahl groesser 0 ?
         * 
         * Das Trennzeichen muss 2mal vorhanden sein.
         * Bei einem Trennzeichen muss die aktuelle Zahl groesser als 0 sein.
         * Ein Datumsbestandteil kann nicht 0 sein.
         * 
         * Ist die aktuelle Zahl nicht groesser als 0, wird das aktuelle 
         * Zeichen (=Trennzeichen) als ungueltig erkannt und fuehrt in der 
         * naechsten IF-Bedingung zu einem Fehler (Rueckgabe von 0).
         * (Kein Start mit einem Trennzeichen)
         * 
         * Ist die aktuelle Zahl groesser als 0, wird beim ersten Trennzeichen 
         * der Tag und beim zweiten Trennzeichen der Monat gesetzt. 
         * Das Jahr wird nach der While-Schleife gesetzt.
         * 
         * Wird ein drittes Trennzeichen erkannt, sind Tag und Monat 
         * schon gesetzt und es kommt zur Rueckgabe von 0.
         * 
         * Ist in der Eingabe kein Trennzeichen vorhanden, kommt es eventuell 
         * zu einer Ueberlaufexception, oder nach der While-Schleife ist 
         * der Monat oder das Jahr noch auf dem Initialwert von -1.
         * 
         * Nach der Zuweisung von Tag oder Monat, wird die aktuelle Zahl  
         * auf 0 gestellt.
         */
        if ( ( akt_char == pTrennzeichen ) && ( akt_zahl > 0 ) )
        {
          if ( ( datum_tag == -1 ) && ( akt_index <= 2 ) )
          {
            /*
             * Tag
             * Der Tag hat minimal eine Stelle und maximal zwei Stellen.
             * 
             * Das Trennzeichen kommt somit an Position 1 oder 2 vor.
             * 
             * Ist die aktuelle Leseposition kleiner gleich 2, ist der Tag 
             * korrekt eingelesen worden. 
             * 
             * Die Position des naechsten Punktes liegt maximal 3 Stellen
             * nach der aktuellen Position.
             */

            datum_tag = akt_zahl;

            position_punkt_erwartet = akt_index + 3;
          }
          else if ( ( datum_monat == -1 ) && ( akt_index <= position_punkt_erwartet ) )
          {
            datum_monat = akt_zahl;

            /*
             * Um ein korrektes Jahr zu lesen, muessen in der Eingabe 
             * noch 4 Stellen vorhanden sein. 
             * 
             * Ist die Differenz ungleich 4, wird 0 zurueckgegeben.
             * 
             * In der unten stehenden Pruefung wurde die Punkt-Position 
             * beruecksichtigt. Es wird gegen 5 getestet, da die 
             * Leseposition noch nicht weitergestellt wurde.
             */
            if ( ( anzahl_eingabe_zeichen - akt_index ) != 5 )
            {
              return 0; // Fehler: TRENNZEICHEN_AN_FALSCHER_POSITION
            }
          }
          else
          {
            return 0; // Fehler: TRENNZEICHEN_FEHLER
          }

          akt_zahl = 0;
        }
        /*
         * Zeichenpruefung
         * Ist das aktuelle Zeichen keine Zahl, wird die Funktion mit null verlassen.
         */
        else if ( ( akt_char < '0' ) || ( akt_char > '9' ) )
        {
          return 0; // Fehler: EINGABE_ZEICHEN_UNGUELTIG
        }
        else
        {
          /*
           * Berechnung Akt-Zahl
           * Der Wert in der Variablen "akt_zahl" wird mit 10 multipliziert und 
           * der Wert des aktuellen Zeichens hinzugezaehlt. 
           * 
           * Der Wert des aktuellen Zeichens ist der Wert des ASCII-Code abzueglich 48. 
           */
          akt_zahl = ( akt_zahl * 10 ) + ( ( (int) akt_char ) - 48 );
        }

        /*
         * Leseprozess ein Zeichen weiter stellen 
         */
        akt_index++;
      }

      /*
       * Nach der While-Schleife ist in der Variablen "akt_zahl" das Jahr gesetzt.
       * Das Jahr wird hier mit dem Wert aus "akt_zahl" gesetzt.
       */
      datum_jahr = akt_zahl;

      /*
       * Pruefung: Datumswerte 
       * Der Tag muss groesser als 0 sein, der Maximalwert test kommt spaeter.
       * Der Monat muss zwischen 1 und 12 liegen
       * Das Jahr muss zwischen 1583 und 9999 liegen 
       */
      if ( ( datum_tag > 0 ) && ( ( datum_monat >= 1 ) && ( datum_monat <= 12 ) ) && ( ( datum_jahr >= 1583 ) && ( datum_jahr <= 9999 ) ) )
      {
        /*
         * Ermittlung Maximalwert Tag
         * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
         * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
         */
        int anzahl_tage = 31;

        if ( datum_monat == 2 )
        {
          if ( ( ( datum_jahr % 400 ) == 0 ) || ( ( datum_jahr % 100 ) > 0 ) && ( ( datum_jahr % 4 ) == 0 ) )
          {
            anzahl_tage = 29;
          }
          else
          {
            anzahl_tage = 28;
          }
        }
        else if ( datum_monat == 4 || datum_monat == 6 || datum_monat == 9 || datum_monat == 11 )
        {
          anzahl_tage = 30;
        }

        /*
         * Pruefung: Maximalgrenze Tag
         * Es gibt nur dann ein Ergebnis, sofern der uebergebene Tag kleiner 
         * oder gleich der Maximalgrenze ist.
         */
        if ( datum_tag <= anzahl_tage )
        {
          /*
           * Der Aufrufer bekommt das Datum im Format JJJJMMTT zurueck.
           */
          return datum_jahr * 10000 + datum_monat * 100 + datum_tag;
        }
      }
    }
    catch ( Exception err_inst )
    {
      /*
       * Ein Fehler beim Lesen des Datums ergibt eine Rueckgabe von 0.
       */
    }

    /*
     * Bei einem Fehler, bekommt der Aufrufer 0 zurueck. 
     */
    return 0; // Fehler: EINGABE_IST_KEIN_DATUM
  }

  /**
   * <pre>
   * Gibt vom uebergebenen Datum den Jahres und Monatsbestandteil als long zurueck.
   * 
   * Ist "pDatum" gleich "null" wird 0 zurueckgegeben.
   * </pre>
   * 
   * @param pDatum das Datum als Instanz von java.util.Date
   * @return einen long-Wert im Format JJJJMM
   */
  public static long getJJJJMM( Date pDatum )
  {
    /*
     * Pruefung: "pDatum" gleich "null" ?
     * 
     * Ist der Parameter gleich "null" wird 0 zurueckgegeben.
     */
    if ( pDatum == null )
    {
      return 0; // Fehler: EINGABE_IST_NICHT_GESETZT
    }

    Calendar datum_ergebnis = Calendar.getInstance();

    datum_ergebnis.setTime( pDatum );

    return ( datum_ergebnis.get( Calendar.YEAR ) * 100 ) + ( datum_ergebnis.get( Calendar.MONTH ) + 1 );
  }

  /**
   * <pre>
   * Gibt vom aktuellen Datum den Jahres und Monatsbestandteil als long zurueck.
   * </pre>
   * 
   * @return einen long-Wert im Format JJJJMM
   */
  public static long getJJJJMM()
  {
    return ( ( Calendar.getInstance().get( Calendar.YEAR ) * 100 ) + ( Calendar.getInstance().get( Calendar.MONTH ) + 1 ) );
  }

  /**
   * <pre>
   * Gibt vom Parameterdatum den Jahres und Monatsbestandteil als long zurueck.
   * 
   * Das Datum wird auf Plausibilitat geprueft.
   * Ergibt die Eingabe kein Datum, wird 0 zurueckgegeben.
   * 
   * </pre>
   * 
   * @param pDatum ein Datum im Format JJJJMMTT
   * @return Ergibt die Eingabe ein gueltiges Datum, wird long-Wert im Format JJJJMM zurueckgegeben. 
   *         Ist die Eingabe kein Datum, wird 0 zurueckgegeben. 
   */
  public static long getJJJJMM( long pDatum )
  {
    /*
     * Aus dem Parameterwert werden die Angaben fuer das Jahr und den Monat rausgerechnet.
     */
    int datum_jahr = (int) ( pDatum * 0.0001 );

    int datum_monat = (int) ( ( pDatum - ( datum_jahr * 10000 ) ) * 0.01 );

    int datum_tag = (int) ( pDatum - ( datum_jahr * 10000 + datum_monat * 100 ) ); // " + datum_tag + "

    /*
     * Pruefung: Datumswerte 
     * Der Tag muss groesser als 0 sein, der Maximalwert test kommt spaeter.
     * Der Monat muss zwischen 1 und 12 liegen
     * Das Jahr muss zwischen 1583 und 9999 liegen 
     */
    if ( ( datum_tag > 0 ) && ( ( datum_monat >= 1 ) && ( datum_monat <= 12 ) ) && ( ( datum_jahr >= 1583 ) && ( datum_jahr <= 9999 ) ) )
    {
      /*
       * Ermittlung Maximalwert Tag
       * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
       * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
       */
      int anzahl_tage = 31;

      if ( datum_monat == 2 )
      {
        if ( ( ( datum_jahr % 400 ) == 0 ) || ( ( datum_jahr % 100 ) > 0 ) && ( ( datum_jahr % 4 ) == 0 ) )
        {
          anzahl_tage = 29;
        }
        else
        {
          anzahl_tage = 28;
        }
      }
      else if ( datum_monat == 4 || datum_monat == 6 || datum_monat == 9 || datum_monat == 11 )
      {
        anzahl_tage = 30;
      }

      /*
       * Pruefung: Maximalgrenze Tag
       * Es gibt nur dann ein Ergebnis, sofern der uebergebene Tag kleiner 
       * oder gleich der Maximalgrenze ist.
       */
      if ( datum_tag <= anzahl_tage )
      {
        /*
         * Der Aufrufer bekommt das Datum im Format JJJJMM zurueck.
         */
        return datum_jahr * 100 + datum_monat;
      }
    }

    return 0; // Fehler: EINGABE_MONAT_ODER_JAHR_FALSCH
  }

  /**
   * <pre>
   * Liefert fuer eine gueltige Datumseingabe, das Datum im Long-Format zurueck. 
   * 
   * Ergeben die Eingaben ein ungueltiges Datum, wird 0 zurueckgegeben. 
   * 
   * Das Jahr muss 4stellig uebergeben werden. 
   * Der Tag und der Monat sind 1 bis 2 stellig zu uebergeben. 
   * 
   * FkDatumDate.getLongAusTagMonatJahr( "01", "02", "9999" ) = 99990201
   * FkDatumDate.getLongAusTagMonatJahr( "01", "01", "2010" ) = 20100101
   * 
   * FkDatumDate.getLongAusTagMonatJahr(  "1", "02", "2010" ) = 20100201
   * FkDatumDate.getLongAusTagMonatJahr( "01",  "2", "2010" ) = 20100201
   * FkDatumDate.getLongAusTagMonatJahr(  "1",  "2", "2010" ) = 20100201
   * 
   * FkDatumDate.getLongAusTagMonatJahr( "29", "02", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "29", "02", "2011" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "29", "02", "2012" ) = 20120229
   *
   * Ungueltiger Wertebereich:
   * FkDatumDate.getLongAusTagMonatJahr( "00", "01", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "32", "01", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "01", "00", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "01", "13", "2010" ) = 0
   * 
   * Zu viele fuehrende 0en:
   * FkDatumDate.getLongAusTagMonatJahr( "001", "02", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "01", "002", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "01", "02", "02010" ) = 0
   * 
   * Ungueltige Zeichen in der Eingabe
   * FkDatumDate.getLongAusTagMonatJahr( "x1", "02", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "01", "x2", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "01", "02", "20x0" ) = 0
   * 
   * FkDatumDate.getLongAusTagMonatJahr( "1.", "02", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "01", ".2", "2010" ) = 0
   * 
   * Uebergabe von "null":
   * FkDatumDate.getLongAusTagMonatJahr( null, "02", "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "01", null, "2010" ) = 0
   * FkDatumDate.getLongAusTagMonatJahr( "01", "02",   null ) = 0
   * 
   * </pre>
   * 
   * @param pTag der Tag
   * @param pMonat der Monat (1 bis 12)
   * @param pJahr das Jahr (1583 bis 9999)
   * @return Fuer ein gueltiges Datum, die Eingabe im Format JJJJMMTT. 
   *         Fuer ein ungueltiges Datum, wird 0 zurueckgegeben.
   */
  public static long getLongAusTagMonatJahr( String pTag, String pMonat, String pJahr )
  {
    try
    {
      /*
       * JAHR
       * Das Jahr muss eine 4stellige Zahl sein. 
       * Es wird die Funktion "parseZahl4" aufgerufen, welche eben solche Zahlen parst. 
       * Ist die Eingabe keine 4stellige Zahl, wird 0 zurueckgegeben.
       */
      int datum_jahr = parseZahl4( pJahr );

      /*
       * Das gelesene Jahr wird gegen die gueltigen Grenzwerte geprueft. 
       * Das Jahr muss groesser/gleich 1583 und kleiner 9999 sein. 
       * Liegt das geparste Jahr ausserhalb dieser Werte, wird keine weitere 
       * Verarbeitung gemacht. Der Aufrufer bekommt 0 zurueck.
       */
      if ( ( datum_jahr >= 1583 ) && ( datum_jahr <= 9999 ) )
      {
        /*
         * MONAT 
         * Der Monat ist eine 2stellige Zahl sein. 
         * Der Monat muss groesser/gleich 1 und kleiner/gleich 12 sein.
         */
        int datum_monat = parseZahl2( pMonat );

        /*
         * Nachdem der Monat geparst worden ist, wird der Wert gegen die 
         * gueltigen Werte geprueft. Liegt der Wert aussserhalb dieser Grenzen,
         * wird 0 zurueckgegeben.
         * 
         * Der Monat muss zwischen 1 und 12 liegen
         */
        if ( ( datum_monat >= 1 ) && ( datum_monat <= 12 ) )
        {
          /*
           * TAG
           * Der Tag ist eine 2stellige Zahl. 
           * Der Tag wird aus der Eingabe geparst.
           */
          int datum_tag = parseZahl2( pTag );

          /*
           * Es wird nach dem parsen des Tages geprueft, ob
           * der Tag groesser als 0 ist. Ist der Tag 0, wird
           * 0 zurueckgegeben. 
           */
          if ( datum_tag > 0 )
          {
            /*
             * Ermittlung Maximalwert Tag
             * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
             * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
             */
            int anzahl_tage = 31;

            if ( datum_monat == 2 )
            {
              if ( ( ( datum_jahr % 400 ) == 0 ) || ( ( datum_jahr % 100 ) > 0 ) && ( ( datum_jahr % 4 ) == 0 ) )
              {
                anzahl_tage = 29;
              }
              else
              {
                anzahl_tage = 28;
              }
            }
            else if ( datum_monat == 4 || datum_monat == 6 || datum_monat == 9 || datum_monat == 11 )
            {
              anzahl_tage = 30;
            }

            /*
             * Pruefung: Maximalgrenze Tag
             * Es gibt nur dann ein Ergebnis, sofern der uebergebene Tag kleiner 
             * oder gleich der Maximalgrenze ist.
             */
            if ( datum_tag <= anzahl_tage )
            {
              /*
               * Ergebnisaufbau
               * Rueckgabe im Format JJJJMMTT.
               */
              return datum_jahr * 10000 + datum_monat * 100 + datum_tag;
            }
          }
        }
      }
    }
    catch ( Exception err_inst )
    {
      /*
       * Ein Fehler beim Lesen des Datums ergibt eine Rueckgabe von 0.
       */
    }

    /*
     * Fehler: Rueckgabe von 0
     */
    return 0;
  }

  /**
   * @param pEingabe die zu parsende Zahl mit 4 Stellen
   * @return einen Intwert der Eingabe, oder 0 bei einem Fehler
   */
  private static int parseZahl4( String pEingabe )
  {
    /*
     * Pruefung: Ist die Eingabe null?
     * Ist die Eingabe null, bekommt der Aufrufer 0 zurueck.
     */
    if ( pEingabe == null )
    {
      return 0;
    }

    /*
     * Pruefung: Zeichenanzahl korrekt?
     * Die Eingabe muss genau 4 Zeichen entsprechen. 
     * Ist die Eingabe ungleich 4, bekommt der Aufrufer 0 zurueck.
     */
    if ( pEingabe.length() != 4 )
    {
      return 0;
    }

    int akt_zahl = 0;

    int akt_index = 0;

    /*
     * While-Schleife ueber die Eingabe zum lesen der Zahl aus der Eingabe
     */
    while ( akt_index < 4 )
    {
      /*
       * Aktuelles Zeichen aus der Eingabe lesen
       */
      char akt_char = pEingabe.charAt( akt_index );

      /*
       * Zeichenpruefung
       * Ist das aktuelle Zeichen keine Zahl, wird die Funktion mit 0 verlassen.
       */
      if ( ( akt_char < '0' ) || ( akt_char > '9' ) )
      {
        return 0;
      }

      /*
       * Berechnung Akt-Zahl
       * Der Wert in der Variablen "akt_zahl" wird mit 10 multipliziert und 
       * der Wert des aktuellen Zeichens hinzugezaehlt. 
       * 
       * Der Wert des aktuellen Zeichens ist der Wert des ASCII-Code abzueglich 48. 
       */
      akt_zahl = ( akt_zahl * 10 ) + ( ( (int) akt_char ) - 48 );

      /*
       * Leseprozess ein Zeichen weiter stellen 
       */
      akt_index++;
    }

    /*
     * Nach der While-Schleife wird die gelesene Jahreszahl zurueckgegeben.
     * Es werden keine Pruefungen auf den Wertebereich gemacht.
     */
    return akt_zahl;
  }

  /**
   * @param pEingabe die zu parsende Zahl mit 2 Stellen
   * @return einen Intwert der Eingabe, oder 0 bei einem Fehler
   */
  private static int parseZahl2( String pEingabe )
  {
    /*
     * Pruefung: Ist die Eingabe null?
     * Ist die Eingabe null, bekommt der Aufrufer 0 zurueck.
     */
    if ( pEingabe == null )
    {
      return 0;
    }

    int anzahl_zeichen = pEingabe.length();

    /*
     * Pruefung: Zeichenanzahl korrekt?
     * Die Eingabe muss 1 oder 2 Zeichen haben. 
     * Ist die Eingabe gleich 0 oder laenger als 2, bekommt der Aufrufer 0 zurueck.
     */
    if ( ( anzahl_zeichen == 0 ) || ( anzahl_zeichen > 2 ) )
    {
      return 0;
    }

    int akt_zahl = 0;

    int akt_index = 0;

    /*
     * While-Schleife ueber die Eingabe zum lesen der Zahl aus der Eingabe
     */
    while ( akt_index < anzahl_zeichen )
    {
      /*
       * Aktuelles Zeichen aus der Eingabe lesen
       */
      char akt_char = pEingabe.charAt( akt_index );

      /*
       * Zeichenpruefung
       * Ist das aktuelle Zeichen keine Zahl, wird die Funktion mit 0 verlassen.
       */
      if ( ( akt_char < '0' ) || ( akt_char > '9' ) )
      {
        return 0;
      }

      /*
       * Berechnung Akt-Zahl
       * Der Wert in der Variablen "akt_zahl" wird mit 10 multipliziert und 
       * der Wert des aktuellen Zeichens hinzugezaehlt. 
       * 
       * Der Wert des aktuellen Zeichens ist der Wert des ASCII-Code abzueglich 48. 
       */
      akt_zahl = ( akt_zahl * 10 ) + ( ( (int) akt_char ) - 48 );

      /*
       * Leseprozess ein Zeichen weiter stellen 
       */
      akt_index++;
    }

    return akt_zahl;
  }

  /**
   * <pre>
   * Addiert die Anzahl der Tage zum uebergebenen Datum. 
   * Es wird immer die uebergebene Anzahl von Tagen hinzugezaehlt, bzw. 
   * anders gesagt "pAnzahlTage" kann auch kleiner 0 sein. 
   * 
   *                               Ergebnis        Ergebnis  
   *  pDatum        pAnzahlTage    java.Calendar   dieser Fkt.
   *  15.06.2010 +           10 =   25.06.2010   = 25.06.2010  OK
   *  15.06.2010 +           20 =   05.07.2010   = 05.07.2010  OK
   *  15.06.2010 +           30 =   15.07.2010   = 15.07.2010  OK
   *  15.06.2010 +          123 =   16.10.2010   = 16.10.2010  OK
   *  15.06.2010 +         1234 =   31.10.2013   = 31.10.2013  OK
   *  15.06.2010 +        12345 =   02.04.2044   = 02.04.2044  OK
   *  15.06.2010 +       123456 =   19.06.2348   = 19.06.2348  OK
   *  15.06.2010 +        36600 =   30.08.2110   = 30.08.2110  OK = 100 Jahre
   * 
   *  15.06.2010 +      2918121 =   31.12.9999   = 31.12.9999  OK
   *  
   * </pre>
   * 
   * @param pDatum das Ausgangsdatum im Format JJJJMMTT
   * @param pAnzahlTage die anzahl der abzuziehenden Tage
   * @return das sich ergebende Datum im Format JJJJMMTT
   */
  public static long longDateAdd( long pDatum, long pJahre, long pMonate, long pTage, long pKnzTag )
  {
    long anzahl_ges_jahre = 0;
    long anzahl_ges_monate = 0;
    long tages_kontingent = 0;
    long tages_anzahl_akt_monat = 0;

    long datum_ergebnis_jahr = (int) ( pDatum * 0.0001 );

    long datum_ergebnis_monat = (int) ( ( pDatum - ( datum_ergebnis_jahr * 10000 ) ) * 0.01 );

    long datum_ergebnis_tag = (int) ( pDatum - ( datum_ergebnis_jahr * 10000 + datum_ergebnis_monat * 100 ) );

    if ( pTage < 0 )
    {
      tages_kontingent = pTage * -1;

      while ( tages_kontingent > 0 )
      {
        if ( datum_ergebnis_monat == 2 )
        {
          if ( ( ( datum_ergebnis_jahr % 400 ) == 0 ) || ( ( datum_ergebnis_jahr % 100 ) > 0 ) && ( ( datum_ergebnis_jahr % 4 ) == 0 ) )
          {
            tages_anzahl_akt_monat = 29;
          }
          else
          {
            tages_anzahl_akt_monat = 28;
          }
        }
        else if ( ( datum_ergebnis_monat == 4 ) || ( datum_ergebnis_monat == 6 ) || ( datum_ergebnis_monat == 9 ) || ( datum_ergebnis_monat == 11 ) )
        {
          tages_anzahl_akt_monat = 30;
        }
        else
        // if ( ( akt_monat == 1 ) || ( akt_monat == 3 ) || ( akt_monat == 5 ) || ( akt_monat == 7 ) || ( akt_monat == 8 ) || ( akt_monat == 10 ) || ( akt_monat == 12 ) )
        {
          tages_anzahl_akt_monat = 31;
        }

        if ( tages_kontingent > tages_anzahl_akt_monat )
        {
          datum_ergebnis_tag = datum_ergebnis_tag - tages_anzahl_akt_monat;

          tages_kontingent = tages_kontingent - tages_anzahl_akt_monat;

          datum_ergebnis_monat = datum_ergebnis_monat - 1;

          if ( datum_ergebnis_monat == 0 )
          {
            datum_ergebnis_monat = 1;

            datum_ergebnis_jahr = datum_ergebnis_jahr + 1;
          }
        }
        else
        {
          datum_ergebnis_tag = datum_ergebnis_tag - tages_kontingent;

          tages_kontingent = 0;
        }
      }
    }

    if ( pTage > 0 )
    {
      /* 
       * Die hinzuzufuegende Tagesanzahl stellt ein Gesamtkontingent dar.
       * Dieses Kontingent wird in der Variablen "tages_kontingent" gespeichert.
       * Dieser Zwischenspeicher verringert sich um die durchlaufenen Tage.
       * Ist das Tageskontingent verbraucht (=0), ist die While-Schleife
       * beendet.
       * 
       * Das Problem sind die unterschiedlichen Tagesobergrenzen der Monate,
       * welches eine schlichte Aufaddierung verhindert. Es muessen soviele
       * Monate durchlaufen werden, wie im Kontingent Tage vorhanden sind.
       * 
       * Fuer jeden durchlaufenen Monat wird die Tagesanzahl ermittelt.
       * 
       * Ist die Tagesanzahl des Monats kleiner als das Kontingent, kann der
       * Monat insgesamt durchlaufen werden. Das Kontingent verringert sich
       * um den gesamten Monats-Tagesbetrag. Auf den aktuellen Tag werden
       * die Monats-Tage hinzugezaehlt. Erst im nachfolgenden Schritt wird
       * ein ueberlauf korrigiert.
       * 
       * Ist die Tagesanzahl des Monats groesser als das Kontingent, endet die
       * Tagesberechnung mit diesem Monat. Die verbleibenden Tage aus dem
       * Kontingent werden auf den aktuellen Tag hinzuaddiert und das Kontingent
       * anschliessend auf 0 gestellt. Dieses beendet die While-Schleife.
       */
      tages_kontingent = pTage;

      while ( tages_kontingent > 0 )
      {
        if ( datum_ergebnis_monat == 2 )
        {
          if ( ( ( datum_ergebnis_jahr % 400 ) == 0 ) || ( ( datum_ergebnis_jahr % 100 ) > 0 ) && ( ( datum_ergebnis_jahr % 4 ) == 0 ) )
          {
            tages_anzahl_akt_monat = 29;
          }
          else
          {
            tages_anzahl_akt_monat = 28;
          }
        }
        else if ( ( datum_ergebnis_monat == 4 ) || ( datum_ergebnis_monat == 6 ) || ( datum_ergebnis_monat == 9 ) || ( datum_ergebnis_monat == 11 ) )
        {
          tages_anzahl_akt_monat = 30;
        }
        else
        // if ( ( akt_monat == 1 ) || ( akt_monat == 3 ) || ( akt_monat == 5 ) || ( akt_monat == 7 ) || ( akt_monat == 8 ) || ( akt_monat == 10 ) || ( akt_monat == 12 ) )
        {
          tages_anzahl_akt_monat = 31;
        }

        if ( tages_kontingent > tages_anzahl_akt_monat )
        {
          datum_ergebnis_tag = datum_ergebnis_tag + tages_anzahl_akt_monat;

          tages_kontingent = tages_kontingent - tages_anzahl_akt_monat;
        }
        else
        {
          datum_ergebnis_tag = datum_ergebnis_tag + tages_kontingent;

          tages_kontingent = 0;
        }

        /* 
         * Pruefung auf ueberlauf des aktuellen Monats
         * 
         * Das ist der Fall, wenn der oben berechnete Tag groesser als die
         * Maximal zulaessige Tage im aktuellen Monat ist.
         * 
         * Das zuviel gerechnete muss auf den Folgemonat angepasst werden.
         * Dazu wird vom berechneten Endtag in "akt_tag" die Maximal-Monatstage
         * abgezogen. Der dann verbleibende Rest ist der Tag im Folgemonat.
         * Zweitens werden hierdurch auch Schaltjahre beruecksichtigt, da
         * sich die Maxtage immer auf das aktuelle Jahr beziehen.
         * 
         * Der Monat wird um 1 erhoeht. Bei einem Monatsueberlauf kann der Folgemonat
         * nur Januar ( 1 ) sein, das Jahr wird in diesem Fall um 1 erhoeht.
         */
        if ( datum_ergebnis_tag > tages_anzahl_akt_monat )
        {
          datum_ergebnis_tag = datum_ergebnis_tag - tages_anzahl_akt_monat;

          datum_ergebnis_monat = datum_ergebnis_monat + 1;

          if ( datum_ergebnis_monat > 12 )
          {
            datum_ergebnis_monat = 1;

            datum_ergebnis_jahr = datum_ergebnis_jahr + 1;
          }
        }
      }
    }

    /* 
     * Monatswert
     * Ist in der Variablen "pMonate" ein Wert von groesser 0 vorhanden, wurde
     * die Variable "knz_inc_monatlich" auf true gestellt.
     * 
     * Der aktuelle Monat wird um den Betrag in "inc_monat" erhoeht. Wird dabei
     * die Monatsanzahl 12, wird der Monat umgerechnet und ein Jahr hinzugezaehlt.
     * Der neue Monat ist der Wert aus der Addition abzueglich 12.
     * 
     * Die Routine ist so abgestimmt, dass es hoechstens zu einem Jahresueberlauf
     * kommt. Groessere Monatswerte wurden entsprechend umgerechnet und in der
     * Variablen "inc_jahr" beruecksichtigt.
     */
    if ( pMonate > 0 )
    {
      anzahl_ges_jahre = (int) ( pMonate / 12 );

      anzahl_ges_monate = pMonate - ( anzahl_ges_jahre * 12 );

      datum_ergebnis_jahr = datum_ergebnis_jahr + anzahl_ges_jahre;

      datum_ergebnis_monat = datum_ergebnis_monat + anzahl_ges_monate;

      if ( datum_ergebnis_monat > 12 )
      {
        datum_ergebnis_jahr = datum_ergebnis_jahr + 1;

        datum_ergebnis_monat = ( datum_ergebnis_monat - 12 );
      }
    }
    else if ( pMonate < 0 )
    {
      anzahl_ges_jahre = (int) ( ( pMonate * -1 ) / 12 );

      anzahl_ges_monate = ( pMonate * -1 ) - ( anzahl_ges_jahre * 12 );

      datum_ergebnis_jahr = datum_ergebnis_jahr - anzahl_ges_jahre;

      datum_ergebnis_monat = datum_ergebnis_monat - anzahl_ges_monate;

      if ( datum_ergebnis_monat <= 0 )
      {
        datum_ergebnis_jahr = datum_ergebnis_jahr - 1;

        datum_ergebnis_monat = ( 12 + ( datum_ergebnis_monat ) );
      }
    }

    datum_ergebnis_jahr = datum_ergebnis_jahr + pJahre;

    if ( datum_ergebnis_monat == 2 )
    {
      if ( ( ( datum_ergebnis_jahr % 400 ) == 0 ) || ( ( datum_ergebnis_jahr % 100 ) > 0 ) && ( ( datum_ergebnis_jahr % 4 ) == 0 ) )
      {
        tages_anzahl_akt_monat = 29;
      }
      else
      {
        tages_anzahl_akt_monat = 28;
      }
    }
    else if ( ( datum_ergebnis_monat == 4 ) || ( datum_ergebnis_monat == 6 ) || ( datum_ergebnis_monat == 9 ) || ( datum_ergebnis_monat == 11 ) )
    {
      tages_anzahl_akt_monat = 30;
    }
    else
    // if ( ( akt_monat == 1 ) || ( akt_monat == 3 ) || ( akt_monat == 5 ) || ( akt_monat == 7 ) || ( akt_monat == 8 ) || ( akt_monat == 10 ) || ( akt_monat == 12 ) )
    {
      tages_anzahl_akt_monat = 31;
    }

    if ( pKnzTag < 0 )
    {
      datum_ergebnis_tag = 1;
    }
    else if ( pKnzTag > 0 )
    {
      datum_ergebnis_tag = tages_anzahl_akt_monat;
    }
    else
    {
      if ( ( datum_ergebnis_monat == 2 ) && ( datum_ergebnis_tag > 28 ) )
      {
        datum_ergebnis_tag = tages_anzahl_akt_monat;
      }
    }

    return datum_ergebnis_jahr * 10000 + datum_ergebnis_monat * 100 + datum_ergebnis_tag;
  }

  /**
   * @param pLongDatum
   * @param pKnzTag
   * @return
   */
  public static long getLongNaechstenMonat( long pLongDatum, int pKnzTag )
  {
    long vorgabe_rueckgabe = 0;

    if ( pLongDatum < 0 ) return vorgabe_rueckgabe;

    if ( pLongDatum > 99991231 ) return vorgabe_rueckgabe;

    int akt_jahr = (int) ( pLongDatum * 0.0001 );

    if ( akt_jahr == 0 ) return vorgabe_rueckgabe;

    int akt_monat = (int) ( ( pLongDatum - ( akt_jahr * 10000 ) ) * 0.01 );

    if ( akt_monat == 0 ) return vorgabe_rueckgabe;

    if ( akt_monat > 12 ) return vorgabe_rueckgabe;

    akt_monat++;

    if ( akt_monat == 13 )
    {
      akt_monat = 1;

      akt_jahr++;
    }

    int akt_tag = (int) ( pLongDatum - ( akt_jahr * 10000 + akt_monat * 100 ) );

    if ( akt_tag == 0 ) return vorgabe_rueckgabe;

    int anzahl_tage = 31;

    if ( akt_monat == 2 )
    {
      if ( ( ( akt_jahr % 400 ) == 0 ) || ( ( akt_jahr % 100 ) > 0 ) && ( ( akt_jahr % 4 ) == 0 ) )
      {
        anzahl_tage = 29;
      }
      else
      {
        anzahl_tage = 28;
      }
    }
    else if ( akt_monat == 4 || akt_monat == 6 || akt_monat == 9 || akt_monat == 11 )
    {
      anzahl_tage = 30;
    }

    if ( pKnzTag < 0 )
    {
      /*
       * pKnzTag < 0
       * Ist die Flagvariable "pKnzTag" kleiner 0, wird der Tag auf den Monatsersten gestellt. 
       */
      akt_tag = 1;
    }
    else if ( pKnzTag > 0 )
    {
      /*
       * pKnzTag > 0
       * Ist die Flagvariable "pKnzTag" groesser 0, wird der Tag auf den Monatsletzten gestellt. 
       */
      akt_tag = anzahl_tage;
    }
    else
    {
      /*
       * pKnzTag == 0
       * Ist die Flagvariable "pKnzTag" gleich 0, bleibt der Tag der Eingabe erhalten.
       * Ist die Tagesanzahl in dem sich ergebenden Monat kleiner als der Tag aus der 
       * Eingabe, wird die Tagesanzahl des Ergebnisses auf den korrekten Wert gestellt. 
       */
      if ( akt_tag > anzahl_tage )
      {
        akt_tag = anzahl_tage;
      }
    }

    return (long) ( ( akt_jahr * 10000 ) + ( akt_monat * 100 ) + akt_tag );
  }

  /**
   * <pre>
   * Fuegt dem Datum die Anzahl Monate hinzu.
   * Bis zum 1.1.1583 korrekte Ergebnisse bei Monatssubtraktion
   * (1583 = erstes volles Jahr im Gregorianischer Kalender)
   * 
   * FkDatum.longAddMonat( 20100615,    10,  0 ) = "20110415"  OK 
   * FkDatum.longAddMonat( 20100615,   -10,  0 ) = "20090815"  OK 
   * FkDatum.longAddMonat( 20100615,    37,  0 ) = "20130715"  OK 
   * FkDatum.longAddMonat( 20100615,   -37,  0 ) = "20070515"  OK 
   * FkDatum.longAddMonat( 20100615,  1234,  0 ) = "21130415"  OK 
   * FkDatum.longAddMonat( 20100615, -1234,  0 ) = "19070815"  OK 
   * FkDatum.longAddMonat( 20100615,  5620,  0 ) = "24781015"  OK 
   * FkDatum.longAddMonat( 20100615, -5120,  0 ) = "15831015"  OK 
  
   * Test: 31ter August + 1 Monat = 30ter September
   * FkDatum.longAddMonat( 20100831,     1,  0 ) = "20100930"  OK 
   * FkDatum.longAddMonat( 20100831,    -1,  0 ) = "20100731"  OK
   *  
   * FkDatum.longAddMonat( 20100331,    -1,  0 ) = "20100228"  OK 
   * FkDatum.longAddMonat( 20120331,    -1,  0 ) = "20120229"  OK = 2012 Schaltjahr
   *  
   * Test: Parameter pKnzTag
   * FkDatum.longAddMonat( 20100315,    -1, -1 ) = "20100201" = Monatserster
   * FkDatum.longAddMonat( 20100315,    -1,  0 ) = "20100215" = Tag bleibt erhalten 
   * FkDatum.longAddMonat( 20100315,    -1,  1 ) = "20100228" = Monatsletzter 
   * 
   * FEHLER: Das Eingabedatum wird nicht geprueft
   * FkDatum.longAddMonat( 20109999, 1, 0 ) = 20110099
   * </pre>
   * 
   * @param pDatum das Datum, zu welchem die Monate hinzu- oder abgezogen werden sollen
   * @param pAnzahlMonate die Anzahl der Monate
   * @param pKnzTag Kennzeichen fuer den Tag im Ergebnisdatum (-1 = Monatserster, 1 = Monatsletzter, 0 = keine Veraenderung)
   * @return ein Datum im Format JJJJMMTT als Long
   */
  public static long longAddMonat( long pDatum, long pAnzahlMonate, int pKnzTag )
  {
    /* 
     * Datumsbestandteile ermitteln
     * Die Werte fuer Jahr, Monat und Tag werden aus dem Parameter  "pDatum" in die
     * Variablen dieser Funktion ueberfuehrt.
     */
    int datum_ergebnis_jahr = (int) ( pDatum * 0.0001 );

    int datum_ergebnis_monat = (int) ( ( pDatum - ( datum_ergebnis_jahr * 10000 ) ) * 0.01 );

    int datum_ergebnis_tag = (int) ( pDatum - ( datum_ergebnis_jahr * 10000 + datum_ergebnis_monat * 100 ) );

    /* 
     * Berechnung Jahre und Monate
     * Es werden die vollen Jahre aus der Anzahl der Monate rausgerechnet.
     * Das ist immer ein positiver Betrag.
     */
    int anzahl_ges_jahre = (int) ( Math.abs( pAnzahlMonate ) / 12 );

    int anzahl_ges_monate = (int) ( Math.abs( pAnzahlMonate ) - ( anzahl_ges_jahre * 12 ) );

    /* 
     * Pruefung: Monate groesser 0?
     */
    if ( pAnzahlMonate > 0 )
    {
      /* 
       * Anzahl Monate groesser 0
       * Die vollen Jahre werden auf das Jahr aus dem Parameter hinzuaddiert
       * Die restlichen Monate werden auf den Monat aus dem Parameter hinzuaddiert.
       */
      datum_ergebnis_jahr = datum_ergebnis_jahr + anzahl_ges_jahre;

      datum_ergebnis_monat = datum_ergebnis_monat + anzahl_ges_monate;

      /* 
       * Pruefung: Ueberlauf Jahr ?
       * Bedingung: Ergebnismonat groesser 12
       * 
       * Ist dem so, wird das Jahr um 1 erhoeht und von den
       * Ergebnismonaten 12 abgezogen.
       */
      if ( datum_ergebnis_monat > 12 )
      {
        datum_ergebnis_jahr = datum_ergebnis_jahr + 1;

        datum_ergebnis_monat = datum_ergebnis_monat - 12;
      }
    }
    else if ( pAnzahlMonate < 0 )
    {
      /* 
       * Anzahl Monate kleiner 0
       * Die vollen Jahre werden vom Ergebnisjahr abgezogen.
       * Die restlichen Monate werden vom Ergebnismonat abgezogen.
       */
      datum_ergebnis_jahr = datum_ergebnis_jahr - anzahl_ges_jahre;

      datum_ergebnis_monat = datum_ergebnis_monat - anzahl_ges_monate;

      /* 
       * Pruefung: Jahreswechsel
       * Bedingung: Ergebnismonat kleiner gleich 0
       * 
       * Ist dem so, wird das Ergebnisjahr um 1 verringert.
       * 
       * Der Ergebnismonat berechnet sich, indem der Ergebnismonatswert
       * der Zahl 12 hinzuaddiert wird. Da aber der Ergebnismonatswert
       * hier negativ ist, kommt es zu einer Subtraktion.
       * 
       * Der negative Wert in "datum_ergebnis_monat" sind die
       * noch im Vorjahr zu durchlaufenden Monate.
       */
      if ( datum_ergebnis_monat <= 0 )
      {
        datum_ergebnis_jahr = datum_ergebnis_jahr - 1;

        datum_ergebnis_monat = 12 + datum_ergebnis_monat;
      }
    }

    /* 
     * Auswertung: pKnzTag
     */
    if ( pKnzTag < 0 )
    {
      /* 
       * pKnzTag < 0: Tag auf den ersten stellen
       */
      datum_ergebnis_tag = 1;
    }
    else
    {
      /* 
       * pKnzTag >= 0: Maximal-Tage berechnen 
       * 
       * Es wird die Anzahl der Tage im Ergebnis Monat/Jahr ermittelt.
       */
      /*
       * Ermittlung Maximalwert Tag
       * Fuer die Maximalwertpruefung des Tages wird die Anzahl der Tage von 
       * Monat und Jahr bestimmt. Dabei wird das Schaltjahr beruecksichtigt.
       */
      int anzahl_tage = 31;

      if ( datum_ergebnis_monat == 2 )
      {
        if ( ( ( datum_ergebnis_jahr % 400 ) == 0 ) || ( ( datum_ergebnis_jahr % 100 ) > 0 ) && ( ( datum_ergebnis_jahr % 4 ) == 0 ) )
        {
          anzahl_tage = 29;
        }
        else
        {
          anzahl_tage = 28;
        }
      }
      else if ( datum_ergebnis_monat == 4 || datum_ergebnis_monat == 6 || datum_ergebnis_monat == 9 || datum_ergebnis_monat == 11 )
      {
        anzahl_tage = 30;
      }

      if ( pKnzTag < 0 )
      {
        /* 
         * pKnzTag < 0: Tag auf den Monatsersten stellen
         */
        datum_ergebnis_tag = 1;
      }
      else if ( pKnzTag > 0 )
      {
        /* 
         * pKnzTag > 0: Tag auf den Monatsletzten stellen
         */
        datum_ergebnis_tag = anzahl_tage;
      }
      else
      {
        /* 
         * pKnzTag = 0: Tag bleibt!
         * 
         * Der Tag bleibt, wenn der aktuelle Tag kleiner gleich der Maximalanzahl ist.
         * (Der Tag ist gueltig)
         * 
         * Ist der aktuelle Tag groesser als die Maximaltage im Ergebnisdatum, wird der 
         * Tag auf die Maximalanzahl gestellt. 
         */
        if ( datum_ergebnis_tag > anzahl_tage )
        {
          datum_ergebnis_tag = anzahl_tage;
        }
      }
    }

    /* 
     * Funktionsabschluss
     * 
     * Aus den funktionsinternen Variablen wird das Ergebnisdatum im Longformat berechnet.
     */
    return datum_ergebnis_jahr * 10000 + datum_ergebnis_monat * 100 + datum_ergebnis_tag;
  }

  /**
   * @param pDatum das Datum, aus welchem der String generiert werden soll
   * @return die String-Repraesentation des Datums im Format TT.MM.JJJJ
   */
  public static String getDatumStringAusLong( long pDatum )
  {
    int akt_jahr = (int) ( pDatum * 0.0001 );

    int akt_monat = (int) ( ( pDatum - ( akt_jahr * 10000 ) ) * 0.01 );

    int akt_tag = (int) ( pDatum - ( akt_jahr * 10000 + akt_monat * 100 ) );

    return ( akt_tag < 10 ? "0" : "" ) + akt_tag + "." + ( akt_monat < 10 ? "0" : "" ) + akt_monat + "." + ( akt_jahr < 10 ? "000" : ( akt_jahr < 100 ? "00" : ( akt_jahr < 1000 ? "0" : "" ) ) ) + akt_jahr;
  }

  public static final long RK_MIN_TERMIN = 15830101;

  public static final long RK_MAX_TERMIN = 99991231;

  /**
   * @param pDatum das Datum
   * @return das Jahr aus dem Datum
   */
  public static long getJahrAusLong( long pDatum )
  {
    return (long) ( pDatum * 0.0001 );
  }

  /* ################################################################################
   */
  public static long getDatumLetzterStichtag( long pDatum )
  {
    long datum_monat_tag = 0;

    long datum_jahr = (int) ( pDatum * 0.0001 );

    int datum_monat = (int) ( ( pDatum - ( datum_jahr * 10000 ) ) * 0.01 );

    long datum_tag = (int) ( pDatum - ( datum_jahr * 10000 + datum_monat * 100 ) );

    switch ( datum_monat )
    {
      case 1 :
      case 2 :
      {
        datum_jahr = datum_jahr - 1;

        datum_monat_tag = 1231;

        break;
      }

      case 3 :
      {
        if ( datum_tag == 31 )
        {
          datum_monat_tag = 331;
        }
        else
        {
          datum_jahr = datum_jahr - 1;

          datum_monat_tag = 1231;
        }

        break;
      }

      case 4 :
      case 5 :
      {
        datum_monat_tag = 331;

        break;
      }

      case 6 :
      {
        if ( datum_tag == 30 )
        {
          datum_monat_tag = 630;
        }
        else
        {
          datum_monat_tag = 331;
        }

        break;
      }

      case 7 :
      case 8 :
      {
        datum_monat_tag = 630;

        break;
      }

      case 9 :
      {
        if ( datum_tag == 30 )
        {
          datum_monat_tag = 930;
        }
        else
        {
          datum_monat_tag = 630;
        }

        break;
      }

      case 10 :
      case 11 :
      {
        datum_monat_tag = 930;

        break;
      }
      case 12 :
      {
        if ( datum_tag == 31 )
        {
          datum_monat_tag = 1231;
        }
        else
        {
          datum_monat_tag = 930;
        }

        break;
      }
    }

    return datum_jahr * 10000 + datum_monat_tag;
  }

  /* ################################################################################
   */
  public static long getDatumNaechsterStichtag( long pDatum )
  {
    long datum_monat_tag = 0;

    long datum_jahr = (int) ( pDatum * 0.0001 );

    int datum_monat = (int) ( ( pDatum - ( datum_jahr * 10000 ) ) * 0.01 );

    long datum_tag = (int) ( pDatum - ( datum_jahr * 10000 + datum_monat * 100 ) );

    switch ( datum_monat )
    {
      case 1 :
      case 2 :
      {
        datum_monat_tag = 331;

        break;
      }

      case 3 :
      {
        if ( datum_tag == 31 )
        {
          datum_monat_tag = 630;
        }
        else
        {
          datum_monat_tag = 331;
        }

        break;
      }

      case 4 :
      case 5 :
      {
        datum_monat_tag = 630;

        break;
      }

      case 6 :
      {
        if ( datum_tag == 30 )
        {
          datum_monat_tag = 930;
        }
        else
        {
          datum_monat_tag = 630;
        }

        break;
      }

      case 7 :
      case 8 :
      {
        datum_monat_tag = 930;

        break;
      }

      case 9 :
      {
        if ( datum_tag == 30 )
        {
          datum_monat_tag = 1231;
        }
        else
        {
          datum_monat_tag = 930;
        }

        break;
      }

      case 10 :
      case 11 :
      {
        datum_monat_tag = 1231;

        break;
      }

      case 12 :
      {
        if ( datum_tag == 31 )
        {
          datum_monat_tag = 331;

          datum_jahr = datum_jahr + 1;
        }
        else
        {
          datum_monat_tag = 1231;
        }

        break;
      }
    }

    return datum_jahr * 10000 + datum_monat_tag;
  }

  /**
   * <pre>
   * Berechnet die Tagesdifferenz aus den beiden Datumsangaben.
   * Das Ergebnis ist immer positiv. 
   * 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100101, 20100102 ) = 1 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100601, 20100604 ) = 3
   *  
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100101, 20110103 ) = 367 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100101, 20120104 ) = 733 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100401, 20100804 ) = 125 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100201, 20101004 ) = 245
   *  
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20101231, 20110101 ) = 1 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100912, 20110212 ) = 153 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100101, 20121221 ) = 1085 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100101, 20141221 ) = 1815 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100101, 20181221 ) = 3276 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100101, 20201221 ) = 4007 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100101, 20401221 ) = 11312 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20100101, 20801221 ) = 25922
   * 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong(      123,      456 ) = 0 = Datumsangaben kleiner Minimum
   *  
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20101231, 20110101 ) = 1 
   * CalcFkDatum.getAnzahlTageDifferenzAusLong( 20101231, 20091231 ) = 365
   * 
   * </pre>
   * 
   * @param pDatumA erste Datumsangabe im Format JJJJMMTT (15830101 bis 99991231)
   * @param pDatumB zweite Datumsangabe im Format JJJJMMTT (15830101 bis 99991231)
   * @return die Tagesdifferenz 
   */
  public static long getAnzahlTageDifferenzAusLong( long pDatumA, long pDatumB )
  {
    int datum_a_jahr = 0;
    int datum_a_monat = 0;
    int datum_a_tag = 0;
    int datum_b_jahr = 0;
    int datum_b_monat = 0;
    int datum_b_tag = 0;
    long tages_differenz = 0;

    /* 
     * Pruefung: Unterscheiden sich die Datumsangaben ?
     * 
     * Unterscheiden sich die Datumsangaben nicht, ist die Differenz gleich 0
     * 
     * Unterscheiden sich die Datumsangaben, wird die Berechnungsroutine gestartet.
     */
    if ( ( pDatumA != pDatumB ) && ( pDatumA >= 15830101 && pDatumA <= 99991231 ) && ( pDatumB >= 15830101 && pDatumB <= 99991231 ) )
    {
      /* 
       * Datumsreihenfolge
       * 
       * Sicherstellen, dass "Datum A" vor "Datum B" liegt.
       * Herauslesen der einzelnen Datumsbestandteile.
       */
      if ( pDatumA < pDatumB )
      {
        datum_a_jahr = (int) ( pDatumA * 0.0001 );
        datum_a_monat = (int) ( ( pDatumA - ( datum_a_jahr * 10000 ) ) * 0.01 );
        datum_a_tag = (int) ( pDatumA - ( datum_a_jahr * 10000 + datum_a_monat * 100 ) );

        datum_b_jahr = (int) ( pDatumB * 0.0001 );
        datum_b_monat = (int) ( ( pDatumB - ( datum_b_jahr * 10000 ) ) * 0.01 );
        datum_b_tag = (int) ( pDatumB - ( datum_b_jahr * 10000 + datum_b_monat * 100 ) );
      }
      else
      {
        datum_a_jahr = (int) ( pDatumB * 0.0001 );
        datum_a_monat = (int) ( ( pDatumB - ( datum_a_jahr * 10000 ) ) * 0.01 );
        datum_a_tag = (int) ( pDatumB - ( datum_a_jahr * 10000 + datum_a_monat * 100 ) );

        datum_b_jahr = (int) ( pDatumA * 0.0001 );
        datum_b_monat = (int) ( ( pDatumA - ( datum_b_jahr * 10000 ) ) * 0.01 );
        datum_b_tag = (int) ( pDatumA - ( datum_b_jahr * 10000 + datum_b_monat * 100 ) );
      }

      /* 
       * Pruefung: Jahresgleichheit?
       */
      if ( datum_a_jahr == datum_b_jahr )
      {
        /* 
         * Beide Datumsangaben liegen im selben Jahr
         * Es werden die Tage von Datum A bis Datum B gezaehlt.
         * 
         * 1. Aufsummierung der Monatswerte. 
         *    Beginnend mit dem Monat aus Datum 1, bis zum Monat aus Datum 2
         * 
         * 2. Abzug der Tage aus Datum 1, da diese Tage verstrichen sind.
         * 
         * 3. Addieren der Tage aus Datum 2, da diese Tage im Datum B verstrichen sind.
         */
        while ( datum_a_monat < datum_b_monat )
        {
          switch ( datum_a_monat )
          {
            case 2 :

              if ( ( ( datum_a_jahr % 400 ) == 0 ) || ( ( datum_a_jahr % 100 ) > 0 ) && ( ( datum_a_jahr % 4 ) == 0 ) )
              {
                tages_differenz += 29;
              }
              else
              {
                tages_differenz += 28;
              }

              break;

            case 4 :
            case 6 :
            case 9 :
            case 11 :
              tages_differenz += 30;
              break;

            default :
              tages_differenz += 31;
          }

          datum_a_monat++;
        }

        tages_differenz -= datum_a_tag;

        tages_differenz += datum_b_tag;
      }
      else
      {
        /* 
         * Datumsangaben liegen in unterschiedlichen Jahren
         * 
         * 1. Berechnung der noch verbleibenden Tage im Jahr 1.
         * 
         * 2. Berechnung der Tage bis zum Datum 2 im Jahr 2
         *    (Korrekter: Rueckrechnung bis zum Jahresanfang)
         * 
         * 3. Berechnung der vollen Jahresdifferenzbetraege.
         * 
         * Berechnung: Tage bis Jahresende Datum A
         */
        while ( datum_a_monat <= 12 )
        {
          switch ( datum_a_monat )
          {
            case 2 :

              if ( ( ( datum_a_jahr % 400 ) == 0 ) || ( ( datum_a_jahr % 100 ) > 0 ) && ( ( datum_a_jahr % 4 ) == 0 ) )
              {
                tages_differenz += 29;
              }
              else
              {
                tages_differenz += 28;
              }

              break;

            case 4 :
            case 6 :
            case 9 :
            case 11 :
              tages_differenz += 30;
              break;

            default :
              tages_differenz += 31;
          }

          datum_a_monat++;
        }

        tages_differenz -= datum_a_tag;

        datum_b_monat--;

        while ( datum_b_monat > 0 )
        {
          switch ( datum_b_monat )
          {
            case 2 :

              if ( ( ( datum_b_jahr % 400 ) == 0 ) || ( ( datum_b_jahr % 100 ) > 0 ) && ( ( datum_b_jahr % 4 ) == 0 ) )
              {
                tages_differenz += 29;
              }
              else
              {
                tages_differenz += 28;
              }

              break;

            case 4 :
            case 6 :
            case 9 :
            case 11 :
              tages_differenz += 30;
              break;

            default :
              tages_differenz += 31;
          }

          datum_b_monat--;
        }

        tages_differenz += datum_b_tag;

        datum_a_jahr++;

        while ( datum_a_jahr < datum_b_jahr )
        {
          if ( ( ( datum_a_jahr % 400 ) == 0 ) || ( ( datum_a_jahr % 100 ) > 0 ) && ( ( datum_a_jahr % 4 ) == 0 ) )
          {
            tages_differenz += 366;
          }
          else
          {
            tages_differenz += 365;
          }

          datum_a_jahr++;
        }
      }
    }

    return tages_differenz;
  }

  public static long getAnzahlJahreAusMonaten( long pLaufzeitInMonaten )
  {
    return (int) ( pLaufzeitInMonaten / 12 );
  }

  public static long getAnzahlMonateAusMonaten( long pLaufzeitInMonaten )
  {
    int anzahl_jahre = (int) ( pLaufzeitInMonaten / 12 );

    return ( pLaufzeitInMonaten - ( 12 * anzahl_jahre ) );

  }

  /** 
   * @param pDatum das Datum
   * @return das Jahr aus dem Datum
   */
  public static long getLongJahresEnde( long pDatum )
  {
    return ( ( (long) ( pDatum * 0.0001 ) ) * 10000 ) + 1231;
  }

  /**
   * @param pDatum das Datum
   * @return das Jahr aus dem Datum
   */
  public static long getMonatAusLong( long pDatum )
  {
    int datum_ergebnis_jahr = (int) ( pDatum * 0.0001 );

    return (int) ( ( pDatum - ( datum_ergebnis_jahr * 10000 ) ) * 0.01 );
  }

  /**
   * <pre>
   * Berechnet die Monatsdifferenz zwischen den beiden Datumsangaben. 
   * 
   *                                                                       Eingaben      Kontrolle
   *                                                                       umgedreht     Date-Diff
   * CalcFkDatum.getMonatsDifferenz( "01.08.2010", "01.08.2010" ) =    0 =          0 =          0
   * CalcFkDatum.getMonatsDifferenz( "01.01.2010", "01.02.2010" ) =    1 =          1 =          1
   * CalcFkDatum.getMonatsDifferenz( "01.01.2010", "01.03.2010" ) =    2 =          2 =          2
   * CalcFkDatum.getMonatsDifferenz( "01.01.2010", "01.04.2010" ) =    3 =          3 =          3
   * CalcFkDatum.getMonatsDifferenz( "01.01.2010", "01.05.2010" ) =    4 =          4 =          4
   * CalcFkDatum.getMonatsDifferenz( "01.01.2010", "01.06.2010" ) =    5 =          5 =          5
   * CalcFkDatum.getMonatsDifferenz( "01.01.2009", "01.02.2011" ) =   25 =         25 =         25
   * CalcFkDatum.getMonatsDifferenz( "01.01.2008", "01.03.2012" ) =   50 =         50 =         50
   * CalcFkDatum.getMonatsDifferenz( "01.01.2007", "01.04.2013" ) =   75 =         75 =         75
   * CalcFkDatum.getMonatsDifferenz( "01.04.2006", "01.07.2014" ) =   99 =         99 =         99
   * CalcFkDatum.getMonatsDifferenz( "01.04.2005", "01.06.2015" ) =  122 =        122 =        122
   * CalcFkDatum.getMonatsDifferenz( "01.08.1583", "01.09.1968" ) = 4621 =       4621 =       4621
   * CalcFkDatum.getMonatsDifferenz( "01.04.2005", "01.06.1976" ) =  346 =        346 =        346
   * 
   * CalcFkDatum.getMonatsDifferenz( "01.12.2010", "01.12.2011" ) =   12 =         12 =         12
   * CalcFkDatum.getMonatsDifferenz( "01.11.2010", "01.12.2011" ) =   13 =         13 =         13
   * 
   * CalcFkDatum.getMonatsDifferenz( "01.12.2010", "01.01.2011" ) =    1 =          1 =          1
   * CalcFkDatum.getMonatsDifferenz( "01.12.2010", "01.01.2012" ) =   13 =         13 =         13
   * 
   * CalcFkDatum.getMonatsDifferenz( 0, 0 ) = 0
   * </pre>
   * 
   * @param pDatumA das erste Datum im Format JJJJMMTT
   * @param pDatumB das zweite Datum im Format JJJJMMTT
   * @return die Monatsdifferenz als positiver Wert
   */
  public static long getMonatsDifferenz( long pDatumA, long pDatumB )
  {
    long datum_a_jahr = (long) ( pDatumA * 0.0001 );
    long datum_a_monat = (long) ( ( pDatumA - ( datum_a_jahr * 10000 ) ) * 0.01 );

    long datum_b_jahr = (long) ( pDatumB * 0.0001 );
    long datum_b_monat = (long) ( ( pDatumB - ( datum_b_jahr * 10000 ) ) * 0.01 );

    long monats_differenz = 0;

    /* 
     * Pruefung: Startjahr = Endjahr ?
     */
    if ( datum_a_jahr == datum_b_jahr )
    {
      /* 
       * Berechnung Monatsdifferenz bei gleicher Jahreszahl.
       * Der groessere Monatswert bestimmt den Endmonat.
       * Der kleinere Monatswert bestimmt den Startmonat.
       */
      if ( datum_a_monat > datum_b_monat )
      {
        monats_differenz = datum_a_monat - datum_b_monat;
      }
      else
      {
        monats_differenz = datum_b_monat - datum_a_monat;
      }
    }
    else
    {
      if ( datum_a_jahr > datum_b_jahr )
      {
        /* 
         * Berechnung Jahresdifferenz
         * Das kleinere Jahr wird immer um 1 erhoeht.
         * Unterscheidet sich das Startjahr gegenueber dem Endjahr um
         * ein Jahr, liegt kein volles Jahr zwischen Start- und Endjahr. 
         * Es sind nur 2 Jahre beteiligt, welche durch die 
         * Monatsberechnung beruecksichtigt werden.
         */
        monats_differenz = ( datum_a_jahr - ( datum_b_jahr + 1 ) ) * 12;

        /* 
         * Berechnung Monatsdifferenz bei ungleicher Jahreszahl
         * Die Monate aus dem kleineren Jahr, sind die noch verbleibenden Monate bis zum Jahreswechsel. 
         * Die Monate aus dem groesseren Jahr, sind die im Zieljahr zu durchlaufenden Monate
         */
        monats_differenz += ( 12 - datum_b_monat ) + datum_a_monat;
      }
      else
      // if ( datum_a_jahr < datum_b_jahr )
      {
        monats_differenz = ( datum_b_jahr - ( datum_a_jahr + 1 ) ) * 12;

        monats_differenz += ( 12 - datum_a_monat ) + datum_b_monat;
      }
    }

    return monats_differenz;
  }

  /**
   * 
   * @param pDatum das Datum im Format JJJJMMTT
   * @return vom aktuellen Datum den Monat und den Tag im Format MMTT
   */
  public static long getMonatTagAusLong( long pDatum )
  {
    if ( pDatum < 0 ) return 0;

    if ( pDatum > 99991231 ) return 0;

    int akt_jahr = (int) ( pDatum * 0.0001 );

    if ( akt_jahr == 0 ) return 0;

    int akt_monat = (int) ( ( pDatum - ( akt_jahr * 10000 ) ) * 0.01 );

    if ( akt_monat == 0 ) return 0;
    if ( akt_monat > 12 ) return 0;

    int akt_tag = (int) ( pDatum - ( akt_jahr * 10000 + akt_monat * 100 ) );

    if ( akt_monat == 0 ) return 0;

    int anzahl_tage = 31;

    switch ( akt_monat )
    {
      case 2 :
      {
        if ( ( ( akt_jahr % 400 ) == 0 ) || ( ( akt_jahr % 100 ) > 0 ) && ( ( akt_jahr % 4 ) == 0 ) )
        {
          anzahl_tage = 29;
        }
        else
        {
          anzahl_tage = 28;
        }
        break;
      }

      case 4 :
      case 6 :
      case 9 :
      case 11 :
        anzahl_tage = 30;
        break;
    }

    return akt_monat * 100 + akt_tag;

  }

  /**
   * @param pDatum das Datum
   * @return den Tag aus dem Datum
   */
  public static long getTagAusLong( long pDatum )
  {
    int datum_ergebnis_jahr = (int) ( pDatum * 0.0001 );

    return (long) ( ( pDatum - ( datum_ergebnis_jahr * 10000 ) ) * 0.01 );
  }

  /**
   * Liefert die Anzahl der Tage im angegebenen Monat zurueck.
   * 
   * @param pMonat der Monat (1-12)
   * @param pJahr das Jahr
   * @return 30, 31, 28, 29, oder 0 bei ungueltigem Monat
   */
  public static int getTagesAnzahl( long pMonat, long pJahr )
  {
    int anzahl_tage = 0;

    switch ( (int) pMonat )
    {
      case 2 :
      {
        if ( ( ( pJahr % 400 ) == 0 ) || ( ( pJahr % 100 ) > 0 ) && ( ( pJahr % 4 ) == 0 ) )
        {
          anzahl_tage = 29;
        }
        else
        {
          anzahl_tage = 28;
        }

        break;
      }
      case 1 :
      case 3 :
      case 5 :
      case 7 :
      case 8 :
      case 10 :
      case 12 :
        anzahl_tage = 31;
        break;

      case 4 :
      case 6 :
      case 9 :
      case 11 :
        anzahl_tage = 30;
        break;
    }

    return anzahl_tage;
  }

  /**
   * @param pDatum das Datum
   * @return das Jahr aus dem Datum
   */
  public static boolean getLongKnzIstSchaltjahr( long pDatum )
  {
    long akt_jahr = (long) ( pDatum * 0.0001 );

    return ( ( ( akt_jahr % 400 ) == 0 ) || ( ( akt_jahr % 100 ) > 0 ) && ( ( akt_jahr % 4 ) == 0 ) );
  }

  /**
   * <pre>
   * Konvertiert aus einem Datum im Long-Format in ein Datum im String-Format.
   * 
   * CalcFkDatum.getDate(  20100615 ) = "15.06.2010"
   * CalcFkDatum.getDate(  20100228 ) = "28.02.2010"
   * CalcFkDatum.getDate(   9000228 ) = "28.02.0900"
   * CalcFkDatum.getDate(    900228 ) = "28.02.0090"
   * CalcFkDatum.getDate(     90228 ) = "28.02.0009"
   * CalcFkDatum.getDate(  20100230 ) = "30.02.2010"
   * CalcFkDatum.getDate(  20109931 ) = "00.00.0000"
   * CalcFkDatum.getDate( -20100615 ) = "00.00.0000"
   * 
   * </pre>
   * 
   * @param pDatum das Datum, aus welchem der String generiert werden soll im Format JJJJMMTT
   * @return die String-Repraesentation des Datums im Format TT.MM.JJJJ
   */
  public static String getStringAuslong( long pDatum )
  {
    if ( pDatum < 0 ) return "00.00.0000";

    if ( pDatum > 99991231 ) return "00.00.0000";

    int akt_jahr = (int) ( pDatum * 0.0001 );

    if ( akt_jahr == 0 ) return "00.00.0000";

    int akt_monat = (int) ( ( pDatum - ( akt_jahr * 10000 ) ) * 0.01 );

    if ( akt_monat == 0 ) return "00.00.0000";
    if ( akt_monat > 12 ) return "00.00.0000";

    int akt_tag = (int) ( pDatum - ( akt_jahr * 10000 + akt_monat * 100 ) );

    if ( akt_monat == 0 ) return "00.00.0000";

    int anzahl_tage = 31;

    switch ( akt_monat )
    {
      case 2 :
      {
        if ( ( ( akt_jahr % 400 ) == 0 ) || ( ( akt_jahr % 100 ) > 0 ) && ( ( akt_jahr % 4 ) == 0 ) )
        {
          anzahl_tage = 29;
        }
        else
        {
          anzahl_tage = 28;
        }
        break;
      }

      case 4 :
      case 6 :
      case 9 :
      case 11 :
        anzahl_tage = 30;
        break;
    }

    if ( akt_tag > anzahl_tage ) return "00.00.0000";

    return ( akt_tag < 10 ? "0" : "" ) + akt_tag + "." + ( akt_monat < 10 ? "0" : "" ) + akt_monat + "." + ( akt_jahr < 10 ? "000" : ( akt_jahr < 100 ? "00" : ( akt_jahr < 1000 ? "0" : "" ) ) ) + akt_jahr;
  }

}
