package de.bskrechner.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

/**
 * <pre>
 * Funktionen in Bezug mit JSON
 *  
 * Die Funktionen sollen dabei moeglichst nicht miteinander verwoben werden, damit
 * jede einzelne Funktion fuer sich allein funktionsfaehig und kopierbar bleibt.
 * 
 * Die Funktionen sollen Java 1.3 kompatibel sein, daher keine Funktionsaufrufe 
 * von neueren Java-Funktionen. Warnungen "Unessecary Cast" wurden nicht entfernt, 
 * da in einer anderen Umgebung der Cast evtl. nicht mehr "Unessecary" ist.
 * 
 *  
 * http://de.wikipedia.org/wiki/JavaScript_Object_Notation
 * 
 * Die JavaScript Object Notation, kurz JSON, ist ein kompaktes Datenformat in fuer 
 * Mensch und Maschine einfach lesbarer Textform zum Zweck des Datenaustauschs zwischen 
 * Anwendungen. Jedes gueltige JSON-Dokument soll ein gueltiges JavaScript sein und 
 * per eval() interpretiert werden koennen.
 * 
 * JSON kennt folgende Datentypen:
 * 
 * Nullwert
 *     wird durch das Schluesselwort null dargestellt.
 *     
 * boolescher Wert
 *     wird durch die Schluesselwoerter true und false dargestellt. Dies sind keine 
 *     Zeichenketten und werden daher nicht in Anfuehrungszeichen gesetzt.
 *     
 * Zahl
 *    ist eine Folge der Ziffern 0-9. Negative Zahlen werden durch ein "-" eingeleitet.
 *    Der Dezimalpunkt ist ".". Die Zahl kann durch die Angabe eines Exponenten e oder E 
 *    ergaenzt werden, dem ein Vorzeichen + oder - und eine Folge der Ziffern 0-9 folgt.
 *    
 * Zeichenkette
 *     beginnt und endet mit doppelten geraden Anfuehrungszeichen ("). 
 *     
 * Array
 *     beginnt mit [ und endet mit ]. Es enthaelt eine durch Kommata geteilte, geordnete 
 *     Liste von Werten, gleichen oder verschiedenen Typs. Leere Arrays sind zulaessig.
 *     
 * Objekt
 *     beginnt mit { und endet mit }. Es enthaelt eine durch Kommata geteilte, ungeordnete 
 *     Liste von Eigenschaften. Objekte ohne Eigenschaften ("leere Objekte") sind zulaessig.
 *
 *     Eigenschaft
 *     besteht aus einem Schluessel und einem Wert, getrennt durch einen Doppelpunkt (Schluessel:Wert). 
 *     Die Schluessel aller Eigenschaften in einem Objekt muessen eindeutig, also paarweise verschieden sein.
 *          der Schluessel ist eine Zeichenkette.
 *          der Wert ist ein Objekt, ein Array, eine Zeichenkette, eine Zahl oder einer der Ausdruecke true, false oder null.
 *          
 * Beispiel:
 *    {
 *        "Herausgeber": "Abc",
 *        "Nummer": "1234-5678-90",
 *        "Deckung": 2000000,
 *        "Waehrung": "EURO",
 *        "Inhaber": {
 *            "VorName": "Max",
 *            "NachName": "Mustermann",
 *            "eMail": "heute@gestern.de",
 *            "Kommunikationswege": [
 *                {
 *                    "typ": "telefon",
 *                    "number": "040 123-456",
 *                    "aktiv": true
 *                },
 *                {
 *                    "typ": "telefax",
 *                    "number": "040 123-789",
 *                    "aktiv": false
 *                }
 *            ],
 *            "Hobbys": [
 *                "Reiten",
 *                "Golfen",
 *                "Lesen"
 *            ],
 *            "Alter": 42,
 *            "Kinder": [],
 *            "Partner": null
 *        }
 *    }
 * 
 * </pre>
 */
public class FkJson
{
  private static final Date DATE_NULL = null;

  /**
   * <pre>
   * A simple implementation to pretty-print JSON file.
   * 
   * https://stackoverflow.com/questions/4105795/pretty-print-json-in-java/7310424
   * </pre>
   *
   * @param pJsonString der zu formatierende JSON-String
   * @return einen formatierten JSON-String
   */
  public static String prettyPrintJSON( String pJsonString )
  {
    /*
     * Pruefung: Ist "pJsonString" gleich "null" ?
     * 
     * Ist der Parameter nicht geseztzt, wird "null" zurueckgegeben.
     */
    if ( pJsonString == null )
    {
      return null;
    }

    /*
     * Stringbuilder fuer die Aufnahme des Ergebnisses erstellen.
     */
    StringBuilder string_buffer_json_formatiert = new StringBuilder();

    /*
     * Die Variable "einzug_anzahl" speichert die Anzahl der Tabulatoren
     * fuer den Einzug der Elemente
     */
    int einzug_anzahl = 0;

    /*
     * Kennzeichen, ob sich der Leseprozess in einem String befindet.
     */
    boolean knz_in_string = false;

    for ( char akt_zeichen : pJsonString.toCharArray() )
    {
      switch ( akt_zeichen )
      {
        case '"' :

          /* 
           * Anfuehrungszeichen
           * 
           * Ein Anfuehrungszeichen schaltet das Kennzeichen "knz_in_string" um.
           * 
           * Sollen nur Teil-JSON-Strings formatiert werden, kann dieses zu 
           * Fehlern in der Ausgabe fuehren. Diese Funktion ist so ausgelegt, 
           * dass sie sich selber wieder synchronisiert, indem Klammern das
           * Kennzeichen "knz_in_string" wieder auf FALSE stellen.
           */

          knz_in_string = !knz_in_string;

          /*
           * Das Anfuerungszeichen wird in den Ergebnisstring aufgenommen
           */

          string_buffer_json_formatiert.append( akt_zeichen );

          break;

        case '\n' :

          /* 
           * Zeilenumbruch ueberlesen
           * 
           * Ein Zeilenumbruchszeichen aus dem Eingabe-Json-String wird ueberlesen.
           * Die Zeilenumbrueche werden von dieser Funktion selbst erstellt. 
           */

          break;

        case '\r' :

          /* 
           * Linefeed ueberlesen
           */

          break;

        case ':' :

          /* 
           * Doppelpunkt
           * 
           * Befindet sich der Doppelpunkt in einem String, wird
           * nur der Doppelpunkt uebernommen.
           * 
           * Befindet sich der Doppelpunkt ausserhalb eines Strings,
           * wird dieser mit 2 Leerzeichen umschlossen. Diese beiden
           * Leerzeichen wuerden sonst von dieser Funktion eleminiert
           * werden. Sie dienen der besseren Uebersichtlichkeit.
           */

          if ( knz_in_string )
          {
            string_buffer_json_formatiert.append( akt_zeichen );
          }
          else
          {
            string_buffer_json_formatiert.append( ' ' );
            string_buffer_json_formatiert.append( akt_zeichen );
            string_buffer_json_formatiert.append( ' ' );
          }

          break;

        case ' ' :

          /* 
           * Leerzeichen
           * 
           * Leerzeichen in Strings werden uebernommen.
           * 
           * Leerzeichen ausserhalb von Strings werden ueberlesen.
           */

          if ( knz_in_string )
          {
            string_buffer_json_formatiert.append( akt_zeichen );
          }

          break;

        case '{' :
        case '[' :

          /* 
           * Oeffnende Klammern
           * 
           * - beendet einen String
           * - erhoeht die Tab-Einzugsanzahl
           * - die Einzugsanzahl darf nicht mehr als 200 betragen
           * 
           * - Aktuelle Zeichen + CR + TAB-Einzug
           */

          einzug_anzahl++;

          if ( einzug_anzahl > 200 )
          {
            einzug_anzahl = 200;
          }

          knz_in_string = false;

          string_buffer_json_formatiert.append( akt_zeichen );
          string_buffer_json_formatiert.append( "\n" );

          appendTabX( einzug_anzahl, string_buffer_json_formatiert );

          break;

        case '}' :
        case ']' :

          /* 
           * Schliessende Klammern
           * 
           * - beendet einen String
           * - vermindert die Tab-Einzugsanzahl
           * - die Einzugsanzahl darf nicht negativ werden
           * 
           * - CR + TAB-Einzug + Aktuelle Zeichen
           */

          einzug_anzahl--;

          if ( einzug_anzahl < 0 )
          {
            einzug_anzahl = 0;
          }

          knz_in_string = false;

          string_buffer_json_formatiert.append( "\n" );

          appendTabX( einzug_anzahl, string_buffer_json_formatiert );

          string_buffer_json_formatiert.append( akt_zeichen );

          break;

        case ',' :

          /* 
           * Komma
           * 
           * - das Zeichen wird in die aktuelle Zeile uebernommen
           * - ausserhalb eines Stringes beedet das Komma ein Element.
           *   Es wird ein CR und ein Tab-Einzug hinzugefuegt.
           */

          string_buffer_json_formatiert.append( akt_zeichen );

          if ( knz_in_string == false )
          {
            string_buffer_json_formatiert.append( "\n" );

            appendTabX( einzug_anzahl, string_buffer_json_formatiert );
          }

          break;

        default :

          /* 
           * Zeichen ohne JSON-Sonderfunktion werden in den Ergebnisstring uebernommen
           */

          string_buffer_json_formatiert.append( akt_zeichen );
      }
    }

    /* 
     * Der Aufrufer bekommt am Funktionsende den Ergebnisstring zurueck.
     */
    return string_buffer_json_formatiert.toString();
  }

  private static void appendTabX( int pEinzugAnzahl, StringBuilder pStringBuilder )
  {
    int einzug_zaehler = 0;

    while ( einzug_zaehler < pEinzugAnzahl )
    {
      pStringBuilder.append( "  " );

      einzug_zaehler++;
    }
  }

  /**
   * @return Map to JSON-String  { "key1": "value1", "key2": "value2" }
   */
  public static String toJsonString( HashMap pHashMap )
  {
    StringBuffer ergebnis = new StringBuffer();

    try
    {
      ergebnis.append( "{" );

      String akt_schluessel = null;

      int zaehler = 0;
      Iterator iterator_schluessel = pHashMap.keySet().iterator();

      while ( iterator_schluessel.hasNext() )
      {
        akt_schluessel = (String) iterator_schluessel.next();

        if ( zaehler > 0 )
        {
          ergebnis.append( "," );
        }

        ergebnis.append( "\"" + akt_schluessel + "\":\"" + pHashMap.get( akt_schluessel ) + "\"" );

        zaehler++;
      }

      ergebnis.append( "}" );

      iterator_schluessel = null;

    }
    catch ( Exception e )
    {
      // 
    }

    return ergebnis.toString();
  }

  public static String getStringJson( Properties pProp )
  {
    if ( pProp == null ) return "";
    if ( pProp.isEmpty() ) return "";

    Enumeration enumeration_keys = pProp.keys();

    String akt_property_key = null;
    String akt_property_wert = null;

    String ergebnis = "";

    while ( enumeration_keys.hasMoreElements() )
    {
      akt_property_key = (String) enumeration_keys.nextElement();

      try
      {
        akt_property_wert = pProp.getProperty( akt_property_key, "" );
      }
      catch ( Throwable e )
      {
        akt_property_wert = "";
      }

      ergebnis += "\"" + akt_property_key + "\":\"" + getStringJson( akt_property_wert ) + "\",";
    }

    akt_property_key = null;
    akt_property_wert = null;

    enumeration_keys = null;

    return ergebnis;
  }

  public static String getStringJson( String pString )
  {
    // http://wiki.selfhtml.org/wiki/Artikel:Kontextwechsel/erkennen_und_behandeln#JavaScript

    if ( pString == null ) return "null";

    StringBuffer ergebnis = new StringBuffer();

    int length = pString.length();

    for ( int akt_index = 0; akt_index < length; akt_index++ )
    {
      char akt_zeichen = pString.charAt( akt_index );

      switch ( akt_zeichen )
      {
        case '\n' :
          ergebnis.append( "\\n" );
          break;

        case '"' :
          ergebnis.append( "\\\"" );
          break;

        case '\\' :
          ergebnis.append( "\\\\" );
          break;
// FALSCH
//        case '\'' :
//          ergebnis.append( "\\\'" );
//          break;

        default :
          ergebnis.append( akt_zeichen );
      }
    }

    return ergebnis.toString();
  }

  public static String getStringJson( Date pWert )
  {
    return getStringJson( pWert, DATE_NULL );
  }

  public static String getStringJson( Date pWert, Date pVorgabewert )
  {
    Calendar datum = Calendar.getInstance();

    if ( pWert != null )
    {
      datum.setTime( pWert );
    }
    else if ( pVorgabewert != null )
    {
      datum.setTime( pVorgabewert );
    }
    else
    {
      return "";
    }

    int tag = datum.get( Calendar.DATE );
    int monat = datum.get( Calendar.MONTH ) + 1;

    return ( tag < 10 ? "0" : "" ) + tag + "." + ( monat < 10 ? "0" : "" ) + monat + "." + datum.get( Calendar.YEAR );
  }

  public static String getStringJson( BigDecimal pWert )
  {
    if ( pWert == null )
    {
      return "null";
    }

    return pWert.toPlainString();
  }

  public static String getStringJson( BigDecimal pWert, String pVorgabewert )
  {
    if ( pWert == null )
    {
      return pVorgabewert;
    }

    return pWert.toPlainString();
  }

  public static String getStringJson( Integer pWert )
  {
    if ( pWert == null )
    {
      return "null";
    }

    return pWert.toString();
  }

  public static String getStringJson( Integer pWert, String pVorgabewert )
  {
    if ( pWert == null )
    {
      return pVorgabewert;
    }

    return pWert.toString();
  }

  public static String getStringJson( int pWert )
  {
    return "" + pWert;
  }

  public static String getStringJson( long pWert )
  {
    return "" + pWert;
  }

  public static String getStringJson( double pWert )
  {
    return "" + pWert;
  }

  public static String getStringJson( boolean pWert )
  {
    return "" + pWert;
  }

  public static String getJson( String pJsonName, Date pWert )
  {
    return "\"" + pJsonName + "\":\"" + getStringJson( pWert ) + "\"";
  }

  public static String getJson( String pJsonName, Date pWert, Date pVorgabewert )
  {
    return "\"" + pJsonName + "\":\"" + getStringJson( pWert, DATE_NULL ) + "\"";
  }

  public static String getJson( String pJsonName, BigDecimal pWert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":\"\"";
    }

    return "\"" + pJsonName + "\":\"" + pWert.toPlainString() + "\"";
  }

  public static String getJson( String pJsonName, BigDecimal pWert, String pVorgabewert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":\"" + pVorgabewert + "\"";
    }

    return "\"" + pJsonName + "\":\"" + pWert.toPlainString() + "\"";
  }

  public static String getJson( String pJsonName, BigDecimal pWert, BigDecimal pVorgabewert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":\"" + pVorgabewert.toPlainString() + "\"";
    }

    return "\"" + pJsonName + "\":\"" + pWert.toPlainString() + "\"";
  }

  public static String getJson( String pJsonName, String pWert, String pVorgabewert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":\"" + pVorgabewert + "\"";
    }

    return "\"" + pJsonName + "\":\"" + getStringJson( pWert ) + "\"";
  }

  public static String getJson( String pJsonName, Double pWert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":\"\"";
    }
    return "\"" + pJsonName + "\":\"" + pWert.doubleValue() + "\"";
  }

  public static String getJson( String pJsonName, Integer pWert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":\"\"";
    }

    return "\"" + pJsonName + "\":\"" + pWert.intValue() + "\"";
  }

  public static String getJson( String pJsonName, Integer pWert, String pVorgabewert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":\"" + pVorgabewert + "\"";
    }

    return "\"" + pJsonName + "\":\"" + getStringJson( pWert ) + "\"";
  }

  public static String getJson( String pJsonName, Long pWert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":\"\"";
    }

    return "\"" + pJsonName + "\":\"" + pWert.longValue() + "\"";
  }

  public static String getJson( String pJsonName, int pWert )
  {
    return "\"" + pJsonName + "\":\"" + pWert + "\"";
  }

  public static String getJson( String pJsonName, long pWert )
  {
    return "\"" + pJsonName + "\":\"" + pWert + "\"";
  }

  public static String getJson( String pJsonName, double pWert )
  {
    return "\"" + pJsonName + "\":\"" + pWert + "\"";
  }

  public static String getJson( String pJsonName, boolean pWert )
  {
    return "\"" + pJsonName + "\":" + pWert;
  }

  public static String getStringJsonZahl( String pJsonName, String pWert )
  {
    return "\"" + pJsonName + "\":" + pWert;
  }

  public static String getStringJsonZahl( String pJsonName, int pWert )
  {
    return "\"" + pJsonName + "\":" + pWert;
  }

  public static String getStringJsonZahl( String pJsonName, long pWert )
  {
    return "\"" + pJsonName + "\":" + pWert;
  }

  public static String getStringJsonZahl( String pJsonName, double pWert )
  {
    return "\"" + pJsonName + "\":" + pWert;
  }

  public static String getStringJsonZahl( String pJsonName, BigDecimal pWert )
  {
    return "\"" + pJsonName + "\":" + ( pWert == null ? "null" : pWert.toPlainString() );
  }

  public static String getStringJsonZahl( String pJsonName, BigDecimal pWert, String pVorgabewert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":" + pVorgabewert;
    }

    return "\"" + pJsonName + "\":" + pWert.toPlainString();
  }

  public static String getStringJsonZahl( String pJsonName, BigDecimal pWert, BigDecimal pVorgabewert )
  {
    if ( pWert == null )
    {
      return "\"" + pJsonName + "\":" + pVorgabewert.toPlainString();
    }

    return "\"" + pJsonName + "\":" + pWert.toPlainString();
  }

  public static String getJson( String pJsonName, boolean pWert, String pWertTrue, String pWertFalse )
  {
    return "\"" + pJsonName + "\":\"" + getStringJson( pWert ? pWertTrue : pWertFalse ) + "\"";
  }

  public static String getJson( String pJsonName, String pWert )
  {
    return "\"" + pJsonName + "\":\"" + getStringJson( pWert ) + "\"";
  }

  public static String getStringJsonBase64( String pJsonName, String pWert )
  {
    if ( pWert == null ) return "\"" + pJsonName + "\":\"\"";

    return "\"" + pJsonName + "\":\"" + getStringJson( pWert ) + "\"";
  }

  public static String getStringJsonPreFormatted( String pJsonName, String pWert )
  {
    return "\"" + pJsonName + "\":" + pWert + "";
  }

  public static String getJson( String pJsonName, String[] pArray )
  {
    StringBuffer ergebnis = new StringBuffer();

    if ( pArray == null )
    {
      return "";
    }

    ergebnis.append( "\"" );
    ergebnis.append( pJsonName );
    ergebnis.append( "\":[" );

    int akt_index = 0;

    while ( akt_index < pArray.length )
    {
      if ( akt_index > 0 )
      {
        ergebnis.append( "," );
      }

      ergebnis.append( "\"" );

      if ( pArray[ akt_index ] != null )
      {
        ergebnis.append( pArray[ akt_index ] );
      }

      ergebnis.append( "\"" );

      akt_index++;
    }

    ergebnis.append( "]" );

    return ergebnis.toString();
  }

  public static String getJson( String pJsonName, Vector pVector )
  {
    StringBuffer ergebnis = new StringBuffer();

    if ( pVector == null )
    {
      return "";
    }

    ergebnis.append( "\"" );
    ergebnis.append( pJsonName );
    ergebnis.append( "\":[" );

    Object java_object = null;
    String json_string = "";

    int akt_index = 0;

    while ( akt_index < pVector.size() )
    {
      java_object = pVector.elementAt( akt_index );

      if ( java_object != null )
      {
        json_string = "{";
        json_string += "\"class\":\"" + java_object.getClass().getName() + "\",";
        json_string += "\"feld1\":\"feld1\",";
        json_string += "\"feld2\":\"feld2\"";
        json_string += "}";

        if ( akt_index > 0 )
        {
          ergebnis.append( "," );
        }

        ergebnis.append( json_string );
      }

      akt_index++;
    }

    ergebnis.append( "]" );

    return ergebnis.toString();
  }

  public static String getJson( String pJsonName, String pString1, String pString2, String pString3, String pString4, String pString5 )
  {
    StringBuffer ergebnis = new StringBuffer();

    String komma = "";

    ergebnis.append( "\"" );
    ergebnis.append( pJsonName );
    ergebnis.append( "\":[" );

    if ( pString1 != null )
    {
      ergebnis.append( "\"" );
      ergebnis.append( pString1 );
      ergebnis.append( "\"" );
      komma = ",";
    }

    if ( pString2 != null )
    {
      ergebnis.append( komma );
      ergebnis.append( "\"" );
      ergebnis.append( pString2 );
      ergebnis.append( "\"" );
      komma = ",";
    }

    if ( pString3 != null )
    {
      ergebnis.append( komma );
      ergebnis.append( "\"" );
      ergebnis.append( pString3 );
      ergebnis.append( "\"" );
      komma = ",";
    }

    if ( pString4 != null )
    {
      ergebnis.append( komma );
      ergebnis.append( "\"" );
      ergebnis.append( pString4 );
      ergebnis.append( "\"" );
      komma = ",";
    }

    if ( pString5 != null )
    {
      ergebnis.append( komma );
      ergebnis.append( "\"" );
      ergebnis.append( pString5 );
      ergebnis.append( "\"" );
    }

    ergebnis.append( "]" );

    return ergebnis.toString();
  }

  public static String getJsonObjekt( String pJsonName, String pJsonStringVonObjekt )
  {
    return "\"" + pJsonName + "\":" + pJsonStringVonObjekt;
  }
}
