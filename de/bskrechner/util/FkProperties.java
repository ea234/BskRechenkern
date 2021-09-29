package de.bskrechner.util;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

public class FkProperties
{
  /**
   * <pre> 
   * Sucht in der Propertie-Instanz nach dem Parameternamen und gibt dessen Wert im Erfolgsfall zurueck.
   * 
   * Wird im Parameter pAnzahlStellen eine positive Zahl uebergeben, wird die Rueckgabe 
   * auf maximal diese Laenge gekuerzt. (Begrenzung der Parameterwerte auf Serverseite)
   *  
   * Ist der Parametername nicht im Request enthalten wird der Vorgabewert zurueckgegeben.
   *  
   * </pre>
   * 
   * @param pRequest der Request
   * @param pName der Parameter
   * @param pVorgabeWert der Vorgabewert fuer den Fehlerfall 
   * @param pAnzahlStellen die Anzahl der vom Parameterwert zurueckzugebenden Zeichen
   * @return den Parameter mit maximal den vorgegebenen Anzahl von Zeichen
   */
  public static String getString( Properties pRequest, String pName, String pVorgabeWert, int pAnzahlStellen )
  {
    /*
     * Variable fuer den Parameterwert auf den Vorgabewert setzen. 
     */
    String parameter_wert = pVorgabeWert;

    try
    {
      parameter_wert = pRequest.getProperty( pName ).trim();
    }
    catch ( Exception e )
    {
      // Im Fehlerfall den Parameterwert auf den Vorgabewert setzen
    }

    /*
     * Ist der Parameter pAnzahlStellen groesser als 0 und der Parameterwert 
     * ungleich null, wird gegen die Zeichenanzahl geprueft. 
     */
    if ( ( pAnzahlStellen > 0 ) && ( parameter_wert != null ) )
    {
      /*
       * Hat der Parameterwert mehr Zeichen als der Grenzwert, wird 
       * die Rueckgabe auf die uebergebene Laenge gekuerzt.
       */
      if ( parameter_wert.length() > pAnzahlStellen )
      {
        return parameter_wert.substring( 0, pAnzahlStellen );
      }
    }

    /*
     * Der unbehandelte Parameterwert wird zurueckgegeben, wenn:
     * ... der Parameterwert selber null ist (dann ist die Rueckgabe auch null)
     * ... die Anzahl der Stellen kleiner als 0 sind
     * ... die Laenge kleiner als die uebergebene Laengenbegrenzung ist
     */
    return parameter_wert;
  }

  /**
   * <pre>
   * Erstellt eine Ausgabe aus den gemachten Vorgaben.
   * 
   * Properties formular_feld_inhalte = new Properties();
   * 
   * formular_feld_inhalte.setProperty( "FeldName1", "FeldWert1" );
   * formular_feld_inhalte.setProperty( "FeldName2", "FeldWert2" );
   * formular_feld_inhalte.setProperty( "FeldName3", "FeldWert3" );
   * formular_feld_inhalte.setProperty( "FeldName4", "FeldWert4" );
   * 
   * String datei_name_map_string            = "c:/Daten/MapString.txt"; 
   * 
   * String str_vorlauf                      = "map_string += \", ";
   * String str_mitte                        = " = ";
   * String str_nachlauf                     = " \"; ";
   * 
   * int breite_key                          = FkProperties.getMaxLenKey(   formular_feld_inhalte );
   * int breite_value                        = FkProperties.getMaxLenValue( formular_feld_inhalte );
   * 
   * boolean knz_nach_key_feldern_sortieren  = true;
   * boolean knz_key_in_anfuehrungszeichen   = false;
   * boolean knz_value_in_anfuehrungszeichen = false;
   * boolean knz_key_value_umdrehen          = true;
   * 
   * String generator_map_string = FkProperties.getStrProperties( formular_feld_inhalte, knz_nach_key_feldern_sortieren, str_vorlauf, str_mitte, str_nachlauf, breite_key, breite_value, knz_key_in_anfuehrungszeichen, knz_value_in_anfuehrungszeichen, knz_key_value_umdrehen );
   * 
   * FkLogger.wl( generator_map_string );
   * 
   * FkDatei.schreibeDatei( datei_name_map_string, generator_map_string );
   * 
   * FkLogger.wl( "Map-String-Datei = " + datei_name_map_string );
   * 
   * 
   * map_string += ", FeldWert4  = FeldName4  ";
   * map_string += ", FeldWert3  = FeldName3  ";
   * map_string += ", FeldWert2  = FeldName2  ";
   * map_string += ", FeldWert1  = FeldName1  ";
   *   
   * -------------------------------------------------------------------------------------     
   *  
   * Properties ausgabe_properties = gv_z.getPropErg();
   * 
   * ausgabe_properties = new PropertieWriterX().getPropertiesX( instanz_ausgabe );
   * 
   * String str_vorlauf  = "prop_instanz.setProperty( ";
   * String str_mitte    = " , ";
   * String str_nachlauf = " ); ";
   *  
   * int breite_key      = FkProperties.getMaxLenKey( ausgabe_properties  );
   * int breite_value    = FkProperties.getMaxLenValue( ausgabe_properties  );
   * 
   * boolean knz_nach_key_feldern_sortieren  = true;
   * boolean knz_key_in_anfuehrungszeichen   = true;
   * boolean knz_value_in_anfuehrungszeichen = true;
   * boolean knz_key_value_umdrehen          = false;
   * 
   * String ausgabe_string = FkProperties.getStrProperties( ausgabe_properties, knz_nach_key_feldern_sortieren, str_vorlauf, str_mitte, str_nachlauf, breite_key, breite_value, knz_key_in_anfuehrungszeichen, knz_value_in_anfuehrungszeichen, knz_key_value_umdrehen );
   * 
   * FkDatei.schreibeDatei( FkSystem.getStdRootVerzeichnis() + "ausgabe_properties.txt",  "    Properties properties_neu = new Properties();\n " + ausgabe_string );
   *   
   * -------------------------------------------------------------------------------------     
   *  
   * Properties formular_feld_inhalte = new Properties();
   * 
   * formular_feld_inhalte.setProperty( "FeldName1", "FeldWert1" );
   * formular_feld_inhalte.setProperty( "FeldName2", "FeldWert2" );
   * formular_feld_inhalte.setProperty( "FeldName3", "FeldWert3" );
   * formular_feld_inhalte.setProperty( "FeldName4", "FeldWert4" );
   * 
   * String str_vorlauf                      = "<tr><td>";
   * String str_mitte                        = "</td><td>";
   * String str_nachlauf                     = "</td></tr>";
   * 
   * int breite_key                          = FkProperties.getMaxLenKey(   formular_feld_inhalte );
   * int breite_value                        = FkProperties.getMaxLenValue( formular_feld_inhalte );
   * 
   * boolean knz_nach_key_feldern_sortieren  = true;
   * boolean knz_key_in_anfuehrungszeichen   = false;
   * boolean knz_value_in_anfuehrungszeichen = false;
   * boolean knz_key_value_umdrehen          = false;
   * 
   * String html_prop_tabelle = "";
   * 
   * html_prop_tabelle += "<table>\n";
   * 
   * html_prop_tabelle +=  FkProperties.getStrProperties( formular_feld_inhalte, knz_nach_key_feldern_sortieren, str_vorlauf, str_mitte, str_nachlauf, breite_key, breite_value, knz_key_in_anfuehrungszeichen, knz_value_in_anfuehrungszeichen, knz_key_value_umdrehen );
   * 
   * html_prop_tabelle += "</table>\n";
   * 
   * FkLogger.wl( html_prop_tabelle );
   *   
   * -------------------------------------------------------------------------------------     
   * </pre>
   * 
   * @param pProperties die Instanz mit den Werten
   * @param pKnzSortieren Kennzeichen, ob die Properties sortiert werden sollen
   * @param pVorlauf der String, welcher vor dem Propertie-Key vorangestellt wird (null = Leerstring)
   * @param pMitte der String, welcher nach dem Propertie-Key und vor dem Propertie-Value gesetzt wird (null = Leerstring)
   * @param pNachlauf der String, welcher nach dem Propertie-Wert angehaengt wird (null = Leerstring)
   * @param pBreitePropKey die minimale Feldbreite fuer den Propertie Key
   * @param pBreiteValue die minimale Feldbreite fuer den Wert
   * @param pKnzKeyInAnfuehrungszeichen Kennzeichen, ob der Key in Anfuehrungszeichen gesetzt werden soll
   * @param pKnzValueInAnfuehrungszeichen Kennzeichen, ob der Wert in Anfuerhungszeichen gesetzt werden soll
   * @param pKnzKeyValueUmdrehen Kennzeichen, ob Key und Wert umgedreht werden sollen.
   * @return den sich ergebenden String
   */
  public static String getStrProperties( Properties pProperties, boolean pKnzSortieren, String pVorlauf, String pMitte, String pNachlauf, int pBreitePropKey, int pBreiteValue, boolean pKnzKeyInAnfuehrungszeichen, boolean pKnzValueInAnfuehrungszeichen, boolean pKnzKeyValueUmdrehen )
  {
    /*
     * Pruefung: Parameter pProperties gesetzt?
     * Ist der Parameter null, bekommt der Aufrufer einen Leerstring zurueck
     */
    if ( pProperties == null )
    {
      return "";
    }

    /*
     * Stringbuffer fuer die Rueckgabe erstellen
     */
    StringBuffer str_buffer = new StringBuffer();

    /*
     * Eine Variable fuer die auszugebenden Schluessel der Propertieinstanz erstellen. 
     * 
     * Sollen die Schluesselnamen sortiert ausgegeben werden, wird die Funktion "getSortetKeys" aufgerufen.
     * 
     * Ist die Reihenfolge egal, wird die Enumeration aus den Properties geholt.
     */
    Enumeration enumeration_keys = null;

    if ( pKnzSortieren )
    {
      enumeration_keys = getSortedKeys( pProperties ).elements();
    }
    else
    {
      enumeration_keys = pProperties.keys();
    }

    /*
     * Breitenangaben fuer Key und Value setzen. 
     * Dabei gilt die Untergrenze 0 und die Obergrenze 200 Zeichen.
     */
    int breite_feld_key = ( pBreitePropKey < 0 ? 0 : ( pBreitePropKey > 100 ? 100 : pBreitePropKey ) );

    int breite_feld_value = ( pBreiteValue < 0 ? 0 : ( pBreiteValue > 200 ? 200 : pBreiteValue ) );

    int breite_key_plus = ( pKnzKeyInAnfuehrungszeichen ? 2 : 0 );

    int breite_value_plus = ( pKnzValueInAnfuehrungszeichen ? 2 : 0 );

    /*
     * Anfuehrungzeichen
     * Die Key und Values koennen in Anfuehrungszeichen gesetzt werden. 
     * Hier werden die beiden boolschen Parameter ausgewertet und die 
     * Stringvariablen entsprechend gesetzt.
     */
    String anf_key = ( pKnzKeyInAnfuehrungszeichen ? "\"" : "" );

    String anf_value = ( pKnzValueInAnfuehrungszeichen ? "\"" : "" );

    String temp_string = null;
    String property_key = null;
    String property_value = null;

    /*
     * While-Schleife ueber die Propertie-Keys. 
     * Die While-Schleife wird solange ausgefuehrt, wie in der Enumeration 
     * der Keys noch Werte vorhanden sind.
     */
    while ( enumeration_keys.hasMoreElements() )
    {
      /*
       * Propertie Key aus der Enumeration holen.        
       */
      property_key = (String) enumeration_keys.nextElement();

      /*
       * Den Propertie Value aus den Eingabeproperties holen.
       * Ist der Key nicht vorhanden, wird ein Leerzeichen als Vorgabe geommen.
       */
      property_value = pProperties.getProperty( property_key, "" );

      /*
       * Pruefung: Key Value umdrehen?
       * 
       * Sollen Key und Value vertauscht werden, wird ein solches hier gemacht
       */
      if ( pKnzKeyValueUmdrehen )
      {
        temp_string = property_key;

        property_key = property_value;

        property_value = temp_string;
      }

      /*
       * Ergebnisaubau
       * 1. String aus "pVorlauf" einfuegen, sofern vorhanden
       * 2. Propertie Key mit der Mindestbreite setzten und eventuell in Klammern setzen
       * 3. String aus "pMitte" einfuegen, wenn nicht gesetzt wird "=" genommen
       * 4. Propertie Value mit der Mindestbreite setzen und eventuell in Klammen setzen
       * 5. String aus "pNachlauf" setzen, sofern vorhanden
       * 6. Zeilenumbruch
       */
      str_buffer.append( pVorlauf == null ? "" : pVorlauf );

      str_buffer.append( FkString.getFeldLinksMin( anf_key + property_key + anf_key, breite_feld_key + breite_key_plus ) );

      str_buffer.append( pMitte == null ? "=" : pMitte );

      str_buffer.append( FkString.getFeldLinksMin( anf_value + property_value + anf_value, breite_feld_value + breite_value_plus ) );

      str_buffer.append( pNachlauf == null ? "" : pNachlauf );

      str_buffer.append( "\n" );
    }

    /*
     * Am Funktionsende, den aufgebauten String zurueckgeben.
     */
    return str_buffer.toString();
  }

  /**
   * Sortiert die Schluessel aus der Parameterinstanz nach dem Alphabeth.
   * 
   * @param pProperties die Propertie-Instanz mit zu sortierenden Schluesseln
   * @return einen Vektor mit den alphabetisch sortierten Key's.
   */
  public static Vector getSortedKeys( Properties pProperties )
  {
    /*
     * Ein Arry der Klasse "Map.Entry" fuer die Aufnahme der Propertie-Keys erstellen.
     */
    Map.Entry[] hash_table_entries = new Map.Entry[ pProperties.size() ];

    try
    {
      /*
       * Die Propertie-Keys in die Entry-Map schreiben
       */
      pProperties.entrySet().toArray( hash_table_entries );

      /*
       * Aufruf der Quick-Sort-Funktion fuer den Map.Entry-Array
       */
      quickSort( hash_table_entries, 0, hash_table_entries.length - 1, true );
    }
    catch ( Exception exp )
    {
      //
    }

    /*
     * Ergebnisaufbau
     * Ueber eine While-Schleife werden die sortierten Keys in einen Vektor geschrieben.
     * Die Klasse Vektor ist eine einfachere Klasse als ein Map.Entry-Array.
     * Der Aufrufer soll ueber die konkrete Implementation keine Information bekommen. 
     * Der Aufrufer soll einen Java-Vektor bekommen und sich nicht mit weiterer
     * Technik rumschlagen muessen. 
     */
    Vector ergebnis_vektor = new Vector();

    int akt_index = 0;

    while ( akt_index < hash_table_entries.length )
    {
      ergebnis_vektor.add( hash_table_entries[ akt_index ].getKey().toString() );

      akt_index++;
    }

    /*
     * Am Funktionsende wird der Vektor zurueckgegebe. 
     */
    return ergebnis_vektor;
  }

  /**
   * Quicksort fuer den Parameterarray
   * 
   * @param pArray ein Array mit Instanzen von "Map.Entry"
   * @param pIndexStart der Startindex
   * @param pIndexEnde der Endindex
   * @param pKnzAufsteigend Kennzeichen, ob Aufsteingend sortiert werden soll
   */
  public static void quickSort( Map.Entry[] pArray, int pIndexStart, int pIndexEnde, boolean pKnzAufsteigend )
  {
    int index_start = pIndexStart;

    int index_end = pIndexEnde;

    if ( pArray.length > 0 )
    {
      String vergleichs_string_mitte = pArray[ ( pIndexStart + pIndexEnde ) / 2 ].getKey().toString();

      while ( index_start <= index_end )
      {
        if ( pKnzAufsteigend )
        {
          while ( pArray[ index_start ].getKey().toString().compareTo( vergleichs_string_mitte ) < 0 )
          {
            index_start++;
          }

          while ( pArray[ index_end ].getKey().toString().compareTo( vergleichs_string_mitte ) > 0 )
          {
            index_end--;
          }
        }
        else
        {
          while ( pArray[ index_start ].getKey().toString().compareTo( vergleichs_string_mitte ) > 0 )
          {
            index_start++;
          }

          while ( pArray[ index_end ].getKey().toString().compareTo( vergleichs_string_mitte ) < 0 )
          {
            index_end--;
          }
        }

        if ( index_start <= index_end )
        {
          Map.Entry temp_objekt = pArray[ index_start ];

          pArray[ index_start ] = pArray[ index_end ];

          pArray[ index_end ] = temp_objekt;

          index_start++;
          index_end--;
        }
      }

      if ( pIndexStart < index_end )
      {
        quickSort( pArray, pIndexStart, index_end, pKnzAufsteigend );
      }

      if ( index_start < pIndexEnde )
      {
        quickSort( pArray, index_start, pIndexEnde, pKnzAufsteigend );
      }
    }
  }

}
