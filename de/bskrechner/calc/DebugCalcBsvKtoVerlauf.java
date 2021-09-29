package de.bskrechner.calc;

import java.util.Properties;

import de.bskrechner.util.FkHtmlQuotes;
import de.bskrechner.util.FkString;

class DebugCalcBsvKtoVerlauf
{
  public static final int  AUSGABE_ART_TEXT = 1;

  private static final int AUSGABE_ART_HTML = 2;

  /**
   * <pre>
   * Liefert einen String entsprechend der Umsetzungstabelle zurueck.
   * 
   *    -1 = Leerstring
   * 
   *      0 = Anzahl Zinstage
   *      1 = Art
   *      2 = Betrag Auszahlung
   *      3 = Betrag Betrag Salden Summe
   *      4 = Betrag Bwz
   *      5 = Betrag Einzahlung Neu
   *      6 = Betrag Einzahlung Reset
   *      7 = Betrag Gebuehr
   *      8 = Betrag Foerderung
   *      9 = Betrag Kontostand
   *     10 = Betrag Tilg Anteil
   *     11 = Betrag Uj Bonus Zins
   *     12 = Betrag Uj Guthabenzins
   *     13 = Betrag Zins Anteil
   *     14 = Betrag Zinsgutschrift
   *     15 = Bezeichnung
   *     16 = Bonus Zinsen
   *     17 = Bsd Info Tilg Anteil
   *     18 = Bsd Info Zins Anteil
   *     19 = Datum
   *     20 = Knz Aktive Zahlung
   *     21 = Knz Loeschung
   *     22 = Sort Id
   *     23 = Str Art
   *     24 = Element Id
   * 
   * </pre>
   * 
   * @param  pAbfrageIndex  identifiziert den abzufragenden Wert
   * 
   * @return die Ueberschrift des abgefragten Strings, oder null, wenn der AbfrageIndex nicht existiert
   */
  private static String getUeberschrift( int pAbfrageIndex )
  {
    switch ( pAbfrageIndex )
    {
      case -1 :
        return "";

      case 0 :
        return "Sort Id";

      case 1 :
        return "Datum";

      case 2 :
        return "Betrag Einzahlung Neu";

      case 3 :
        return "Betrag Gebuehr";

      case 4 :
        return "Betrag Foerderung";

      case 5 :
        return "Betrag Kontostand";

      case 6 :
        return "Betrag Bwz";

      case 7 :
        return "Anzahl Zinstage";

      case 8 :
        return "Art";

      case 9 :
        return "Betrag Auszahlung";

      case 10 :
        return "Betrag Betrag Salden Summe";

      case 11 :
        return "Betrag Einzahlung Reset";

      case 12 :
        return "Betrag Tilg Anteil";

      case 13 :
        return "Betrag Uj Bonus Zins";

      case 14 :
        return "Betrag Uj Guthabenzins";

      case 15 :
        return "Betrag Zins Anteil";

      case 16 :
        return "Betrag Zinsgutschrift";

      case 17 :
        return "Bezeichnung";

      case 18 :
        return "Bonus Zinsen";

      case 19 :
        return "Bsd Info Tilg Anteil";

      case 20 :
        return "Bsd Info Zins Anteil";

      case 21 :
        return "Knz Aktive Zahlung";

      case 22 :
        return "Knz Loeschung";

      case 23 :
        return "Str Art";

      case 24 :
        return "Element Id";
    }

    //return "---";

    return null;

  }

  /**
   * @return die Anzahl der Class-Properties
   */
  private static int getAnzahlBeanProperties()
  {
    return 25;
  }

  /**
   * <pre>
   * Erstellt eine Tabelle mit den Daten aus dem Vektor.
   * </pre>
   *
   * @param pCalcKtoVerlaufVector der Vektor mit den auszugebenden Daten
   * @param pKnzZeilenBasiert Kennzeichen, ob die Daten in Zeilen ausgegeben werden sollen
   * @param pAusgabeArt die zu erstellende Ausgabeart (Text oder HTML)
   * @return einen String mit den Daten des Vektors als Text-Tabelle
   */
  public static String getGridString( CalcKtoVerlaufVector pCalcKtoVerlaufVector, boolean pKnzZeilenBasiert, int pAusgabeArt )
  {
    /*
     * Breite der Zaehlerspalte
     * Die Anzahl der im Vektor enthaltenen Elemente definiert die Breite der Zaehlerspalte.
     *
     * <      10 = 2 Zeichen
     * <     100 = 3 Zeichen
     * <   1.000 = 4 Zeichen
     * < 100.000 = 6 Zeichen
     * sonst       7 Zeichen
     */
    int breite_zaehler_spalte = 7;

    if ( pCalcKtoVerlaufVector.getAnzahl() < 10 )
    {
      breite_zaehler_spalte = 2;
    }
    else if ( pCalcKtoVerlaufVector.getAnzahl() < 100 )
    {
      breite_zaehler_spalte = 3;
    }
    else if ( pCalcKtoVerlaufVector.getAnzahl() < 1000 )
    {
      breite_zaehler_spalte = 4;
    }
    else if ( pCalcKtoVerlaufVector.getAnzahl() < 100000 )
    {
      breite_zaehler_spalte = 6;
    }

    /*
     * Trennstring fuer die Spalten
     */
    String trenn_string = " | "; // ( pTrennstring == null ? " | " : pTrennstring );

    String ausgabe_string_aktuell = null;

    /*
     * Spaltenvorgabebreite
     */
    String prop_spaltenbreite_vorgabe = "5";

    int prop_spaltenbreite_value = 0;
    int prop_spaltenbreite_maximal = 50;

    /*
     * Zugriffsschluessel fuer die Spaltenbreiten
     */
    String prop_breite_key = "";

    /*
     * Praefix fuer die Spaltenbreiten-Propertie-Schluessel
     */
    String prop_breite_key_praefix = "SB";

    /*
     * Prafixe fuer die Daten-Propertie-Schluessel
     */
    String prop_grid_daten_key_praefix_zeile = "Z";
    String prop_grid_daten_key_praefix_spalte = "S";

    /*
     * Aktueller Zugriffsschluessel fuer die Ausgabedaten
     */
    String prop_grid_daten_key_akt = "";

    int max_ausgabe_zeilen = 32123;

    int zaehler_zeile = 0;
    int zaehler_spalte = 0;

    /*
     * Werte fuer die Startposition der Ausgabe im Grid.
     * Dieses ist normalerweise 0. Mit diesen beiden
     * Variablen, kann die Ausgabe verschoben werden.
     */
    int spalte_start_wert = 0;
    int zeile_start_wert = 0;

    /*
     * Schritt 1: Propertie-Grid erstellen
     * In einer Propertie-Instanz werden die auszugebenden Daten abgelegt.
     *
     * Der jeweilige Schluessel wird dabei aus "Z + INDEX + S + INDEX" gebildet.
     *
     * Zeilenbasiert ist der Schluessel  "Z + ZEILEN_INDEX + S + SPALTEN_INDEX"
     * Spaltenbasiert ist der Schluessel "Z + SPALTEN_INDEX + S + ZEILEN_INDEX"
     */
    Properties grid_ausgabe = new Properties();

    int max_ausgabe_spalten = 32123;

    int max_daten_properties = getAnzahlBeanProperties();

    /*
     * Pruefung: Ausgabeart
     */
    if ( pKnzZeilenBasiert )
    {
      /*
       * Zeilenbasierte Ausgabe
       * Ueberschriften und Daten werden jeweils in einer Zeile ausgeben
       *
       * Die Datenauspraegungen stehen in den Spalten.
       */

      /*
       * Die maximale Zeilenanzahl sind die im Vektor enthaltenen Instanzen.
       */
      max_ausgabe_zeilen = pCalcKtoVerlaufVector.getAnzahl() + 1;

      /*
       * Die maximale Spaltenanzahl sind die Anzahl der Properties
       */
      max_ausgabe_spalten = max_daten_properties;
    }
    else
    {
      /*
       * Spaltenbasierte Ausgabe
       * Die Ueberschriften werden in der ersten Spalte ausgebeben.
       * Die Daten werden in den nachfolgenden Spalten ausgegeben.
       *
       * Die Datenauspraegungen stehen in den Zeilen.
       */

      /*
       * Die maximale Zeilenanzahl sind die Anzahl der Properties
       */
      max_ausgabe_zeilen = max_daten_properties;

      /*
       * Die maximale Spaltenanzahl sind die im Vektor enthaltenen Instanzen.
       */
      max_ausgabe_spalten = pCalcKtoVerlaufVector.getAnzahl() + 1;
    }

    int zaehler_ausgabe_zeile = 0;
    int zaehler_ausgabe_spalte = 0;

    /*
     * Schritt 1A: Ueberschriftenzeile im Propertie-Grid erstellen
     */

    /*
     * Ueber eine While-Schleife werden die Ueberschriften dem Ergebnisgring hinzugefuegt
     *
     * In die erste Spalte/Zeile kommt die aktuelle Datensatz-Nummer.
     * Dafuer wird der Zaehler der Klassenproperties (=zaehler_spalte) auf -1 und
     * der auktuelle Ausgabestring auf "Nr." gestellt.
     *
     * Das sorgt dafuer, dass erst im zweiten Schleifendurchgang die Ueberschriften der
     * Java-Klasse in das Propertie-Grid geschrieben werden.
     *
     * Dieses Vorgehen reduziert die Komplexitaet dieser Funktion.
     */
    zaehler_spalte = -1;

    ausgabe_string_aktuell = "Nr.";

    while ( ( ausgabe_string_aktuell != null ) && ( zaehler_spalte <= max_daten_properties ) )
    {
      if ( pKnzZeilenBasiert )
      {
        prop_grid_daten_key_akt = prop_grid_daten_key_praefix_zeile + ( zeile_start_wert + zaehler_ausgabe_zeile ) + prop_grid_daten_key_praefix_spalte + ( spalte_start_wert + ( zaehler_spalte + 1 ) );
      }
      else
      {
        prop_grid_daten_key_akt = prop_grid_daten_key_praefix_zeile + ( zeile_start_wert + ( zaehler_spalte + 1 ) ) + prop_grid_daten_key_praefix_spalte + ( spalte_start_wert + zaehler_ausgabe_zeile );
      }

      grid_ausgabe.setProperty( prop_grid_daten_key_akt, ( ausgabe_string_aktuell == null ? "null" : ausgabe_string_aktuell ) );

      zaehler_spalte++;
      ausgabe_string_aktuell = getUeberschrift( zaehler_spalte );
    }

    /*
     * Schritt 1B: Vektordaten in das Propertie-Grid schreiben
     */

    /*
     * Korrektur Anfangswert Zeile oder Spalte
     * Der Korrekturwert ist notwendig, damit die Ueberschriften erhalten bleiben.
     *
     * Zeilenweise Ausgabe:  In Zeile 0 stehen die Ueberschriften, die Daten beginnen ab zeile 1.
     *
     * Spaltenweise Ausgabe: In Spalte 0 stehen die Ueberschriften, die Daten beginnen ab Spalte 1.
     *
     */
    if ( pKnzZeilenBasiert )
    {
      zeile_start_wert++;
    }
    else
    {
      spalte_start_wert++;
    }

    int akt_index = 0;

    CalcKtoVerlaufElement inst_calc_kto_verlauf_element = pCalcKtoVerlaufVector.getIndexCalcKtoVerlaufElement( akt_index );

    /*
     * Erste While-Schleife laeuft ueber alle Vektorinstanzen
     */
    while ( ( inst_calc_kto_verlauf_element != null ) && ( zaehler_zeile < max_ausgabe_zeilen ) )
    {
      /*
       * In die erste Zeile/Spalte kommt wieder der aktuelle Datensatzzaehler.
       */
      zaehler_spalte = -1;

      ausgabe_string_aktuell = FkString.right( "000000" + ( zaehler_zeile + 1 ), breite_zaehler_spalte );

      /*
       * Zweite While-Schleife laeuft ueber alle Ausgabefelder (Spalten)
       */
      while ( zaehler_spalte < max_daten_properties )
      {
        if ( pKnzZeilenBasiert )
        {
          prop_grid_daten_key_akt = prop_grid_daten_key_praefix_zeile + ( zeile_start_wert + akt_index ) + prop_grid_daten_key_praefix_spalte + ( spalte_start_wert + ( zaehler_spalte + 1 ) );
        }
        else
        {
          prop_grid_daten_key_akt = prop_grid_daten_key_praefix_zeile + ( zeile_start_wert + ( zaehler_spalte + 1 ) ) + prop_grid_daten_key_praefix_spalte + ( spalte_start_wert + akt_index );
        }

        grid_ausgabe.setProperty( prop_grid_daten_key_akt, ( ausgabe_string_aktuell == null ? "null" : ausgabe_string_aktuell ) );

        zaehler_spalte++;
        ausgabe_string_aktuell = inst_calc_kto_verlauf_element.getString( zaehler_spalte );
      }

      /*
       * Den Zeilenzaehler erhoehen
       */
      zaehler_zeile++;

      /*
       * Den Vektor-Index-zaehler erhoehen und die naechste Instanz aus dem Vektor holen
       */
      akt_index++;
      inst_calc_kto_verlauf_element = pCalcKtoVerlaufVector.getIndexCalcKtoVerlaufElement( akt_index );
    }

    int grid_anzahl_zeilen = spalte_start_wert + zaehler_spalte;
    int grid_anzahl_spalten = zeile_start_wert + zaehler_zeile;

    if ( pKnzZeilenBasiert )
    {
      grid_anzahl_zeilen = zeile_start_wert + zaehler_zeile;
      grid_anzahl_spalten = spalte_start_wert + zaehler_spalte;
    }

    grid_anzahl_zeilen--;
    grid_anzahl_spalten++;

    /*
     * Schritt 2: Ermittlung Spaltenbreite
     */

    /*
     * Auessere Schleife fuer die Zeilen
     */
    while ( zaehler_ausgabe_zeile <= grid_anzahl_zeilen )
    {
      /*
       * Spaltenzaehler auf 0 setzen.
       */
      zaehler_ausgabe_spalte = 0;

      while ( zaehler_ausgabe_spalte < grid_anzahl_spalten )
      {
        prop_grid_daten_key_akt = prop_grid_daten_key_praefix_zeile + zaehler_ausgabe_zeile + prop_grid_daten_key_praefix_spalte + zaehler_ausgabe_spalte;

        ausgabe_string_aktuell = grid_ausgabe.getProperty( prop_grid_daten_key_akt, null );

        if ( ausgabe_string_aktuell != null )
        {
          prop_breite_key = prop_breite_key_praefix + zaehler_ausgabe_spalte;

          prop_spaltenbreite_value = Integer.parseInt( grid_ausgabe.getProperty( prop_breite_key, prop_spaltenbreite_vorgabe ) );

          if ( ausgabe_string_aktuell.length() > prop_spaltenbreite_value )
          {
            if ( ausgabe_string_aktuell.length() <= prop_spaltenbreite_maximal )
            {
              /*
               * Neue Breite im Ergebnis hinterlegen
               */
              grid_ausgabe.setProperty( prop_breite_key, "" + ausgabe_string_aktuell.length() );
            }
          }
        }

        /*
         * Spaltenzaehler um eins erhoehen.
         */
        zaehler_ausgabe_spalte++;
      }

      /*
       * Zeilenzaehler um eins erhoehen.
       */
      zaehler_ausgabe_zeile++;
    }

    /*
     * Schritt 3: Erstellung Funktionsrueckgabe
     */
    grid_ausgabe.setProperty( "grid_anzahl_zeilen", "" + grid_anzahl_zeilen );
    grid_ausgabe.setProperty( "grid_anzahl_spalten", "" + grid_anzahl_spalten );

    /*
     * Erstellung der Ausgabe
     *
     * Ist der Parameter "pAusgabeArt" gleich HTML, wird eine HTML-Tabelle erstellt.
     *
     * Ist der Parameter "pAusgabeArt" irgendwas anderes, wird eine Text-Darstellung erstellt.
     *
     */
    String ausgabe_text_grid = null;

    if ( pAusgabeArt == AUSGABE_ART_HTML )
    {
      ausgabe_text_grid = getHtmlGrid( grid_ausgabe );
    }
    else
    {
      ausgabe_text_grid = getTextGrid( grid_ausgabe );
    }

    /*
     * Abschlussarbeiten
     * Variablen auf "null" setzen
     */
    grid_ausgabe.clear();

    grid_ausgabe = null;

    prop_grid_daten_key_akt = null;
    ausgabe_string_aktuell = null;

    zaehler_ausgabe_zeile = 0;
    zaehler_ausgabe_spalte = 0;

    grid_anzahl_zeilen = 0;
    grid_anzahl_spalten = 0;

    /*
     * Zum Schluss den erstellten String zurueckgeben.
     */
    return ausgabe_text_grid;
  }

  /**
   * <pre>
   * Erstellt eine textuelle Ausgabe der Grid-Daten
   * </pre>
   *
   * @param pGrid das Grid mit den Daten als Instanz von java.util.Properties
   * @return einen String mit den Daten in Text-Form
   */
  private static String getTextGrid( Properties pGrid )
  {
    int zaehler_ausgabe_zeile = 0;
    int zaehler_ausgabe_spalte = 0;

    /*
     * Ermittlung der Grid-Ausdehnungen
     */
    int grid_anzahl_zeilen = Integer.parseInt( pGrid.getProperty( "grid_anzahl_zeilen", "10" ), 10 );
    int grid_anzahl_spalten = Integer.parseInt( pGrid.getProperty( "grid_anzahl_spalten", "10" ), 10 );

    /*
     * Der zu verwendende Trennstring zwischen den Tabellenzellen
     */
    String trenn_string = " | "; // ( pTrennstring == null ? " | " : pTrennstring );

    /*
     * Der aktuelle Ausgabestring
     */
    String ausgabe_string_aktuell = null;

    /*
     * Der aktuelle Zugriffsschluessel fuer die Tabellendaten (Ausgabestring)
     */
    String prop_grid_daten_key_akt = "";

    /*
     * Der aktuelle Zugriffsschluessel fuer die Spaltenbreite
     */
    String prop_breite_key = "";

    String prop_spaltenbreite_vorgabe = "5";
    String prop_breite_key_praefix = "SB";
    String prop_grid_daten_key_praefix_zeile = "Z";
    String prop_grid_daten_key_praefix_spalte = "S";

    /*
     * Schritt 3: Erstellung Funktionsrueckgabe
     */
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    /*
     * Zeilenzaehler auf 0 stellen
     */
    zaehler_ausgabe_zeile = 0;

    /*
     * Auessere Schleife fuer die Zeilen
     */
    while ( zaehler_ausgabe_zeile <= grid_anzahl_zeilen )
    {
      /*
       * Spaltenzaehler auf 0 setzen.
       */
      zaehler_ausgabe_spalte = 0;

      /*
       * Innere Schleife fuer die Spalten starten
       */
      while ( zaehler_ausgabe_spalte < grid_anzahl_spalten )
      {
        /*
         * Schluessel fuer die Spaltenbreite erstellen
         */
        prop_breite_key = prop_breite_key_praefix + zaehler_ausgabe_spalte;

        /*
         * Schluessel fuer das aktuelle Ausgabefeld erstellen
         */
        prop_grid_daten_key_akt = prop_grid_daten_key_praefix_zeile + zaehler_ausgabe_zeile + prop_grid_daten_key_praefix_spalte + zaehler_ausgabe_spalte;

        /*
         * Ausgabewert aus den Ausgabe-Properties holen
         */
        ausgabe_string_aktuell = pGrid.getProperty( prop_grid_daten_key_akt, "" );

        /*
         * Den Ausgabewert in der Spaltenbreite dem Ausgabestringbuffer hinzufuegen
         */
        ergebnis_string_buffer.append( FkString.getFeldLinks( ( ausgabe_string_aktuell == null ? "null" : ausgabe_string_aktuell ), Integer.parseInt( pGrid.getProperty( prop_breite_key, prop_spaltenbreite_vorgabe ) ) ) );

        /*
         * Den Trennstring hinzufuegen
         */
        ergebnis_string_buffer.append( trenn_string );

        /*
         * Spaltenzaehler um eins erhoehen.
         */
        zaehler_ausgabe_spalte++;
      }

      /*
       * Nach jeder Zeile wird ein Zeilenumbruch dem Ergebnisstring hinzugefuegt.
       */
      ergebnis_string_buffer.append( "\n" );

      /*
       * Zeilenzaehler um eins erhoehen.
       */
      zaehler_ausgabe_zeile++;
    }

    /*
     * Erstellten String an den Aufrufer zurueckgeben
     */
    return ergebnis_string_buffer.toString();
  }

  /** HTML-Style 1 fuer Tabellenzeilen */
  private static final String HTML_STYLE_ZEILE_1 = " style=\"background-color:rgb( 220, 220, 220 );\" ";

  /** HTML-Style 2 fuer Tabellenzeilen */
  private static final String HTML_STYLE_ZEILE_2 = "";

  /**
   * <pre>
   * Erstellt mit den Daten aus dem Grid eine HTML-Tabelle
   * </pre>
   *
   * @param pGrid das Grid mit den Daten als Instanz von java.util.Properties
   * @return einen String mit den Daten als HTML-Tabelle
   */
  private static String getHtmlGrid( Properties pGrid )
  {
    int zaehler_ausgabe_zeile = 0;
    int zaehler_ausgabe_spalte = 0;

    /*
     * Ermittlung der Grid-Ausdehnungen
     */
    int grid_anzahl_zeilen = Integer.parseInt( pGrid.getProperty( "grid_anzahl_zeilen", "10" ), 10 );
    int grid_anzahl_spalten = Integer.parseInt( pGrid.getProperty( "grid_anzahl_spalten", "10" ), 10 );

    /*
     * Der aktuelle Ausgabestring aus den Properties
     */
    String ausgabe_string_aktuell = null;

    String prop_grid_daten_key_praefix_zeile = "Z";
    String prop_grid_daten_key_praefix_spalte = "S";

    /*
     * Der aktuelle Zugriffsschluessel fuer die Tabellen-Zelle
     */
    String prop_grid_daten_key_akt = "";

    /*
     * Umschalter fuer den CSS-Style der Tabellenzeilen
     */
    boolean knz_schalter_tabellen_zeile = true;

    /*
     * Erstellung Strinbuffer fuer das Ergebnis
     */
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    /*
     * Starttag fuer die Tabelle hinzufuegen
     */
    ergebnis_string_buffer.append( "<table>\n" );

    /*
     * Zeilenzaehler auf 0 stellen
     */
    zaehler_ausgabe_zeile = 0;

    /*
     * Auessere Schleife fuer die Zeilen
     */
    while ( zaehler_ausgabe_zeile <= grid_anzahl_zeilen )
    {
      /*
       * Tabellenzeilenumschalter
       * Der Schalter fuer die Tabellenfarbe wird negiert.
       *
       * Anschliessend wird dem Ergebnis das Starttag fuer eine
       * Table-Row hinzugefuegt.
       *
       * Die Tabellenzeile bekommt abwechselnd die Row-Styles zugewiesen.
       */
      knz_schalter_tabellen_zeile = !knz_schalter_tabellen_zeile;

      if ( knz_schalter_tabellen_zeile )
      {
        ergebnis_string_buffer.append( "<tr " + HTML_STYLE_ZEILE_1 + ">\n" );
      }
      else
      {
        ergebnis_string_buffer.append( "<tr " + HTML_STYLE_ZEILE_2 + ">\n" );
      }

      /*
       * Spaltenzaehler auf 0 setzen.
       */
      zaehler_ausgabe_spalte = 0;

      /*
       * Innere Schleife fuer die Spalten starten
       */
      while ( zaehler_ausgabe_spalte < grid_anzahl_spalten )
      {
        /*
         * Schluessel fuer das aktuelle Ausgabefeld erstellen
         */
        prop_grid_daten_key_akt = prop_grid_daten_key_praefix_zeile + zaehler_ausgabe_zeile + prop_grid_daten_key_praefix_spalte + zaehler_ausgabe_spalte;

        /*
         * Ausgabewert aus den Ausgabe-Properties holen
         */
        ausgabe_string_aktuell = pGrid.getProperty( prop_grid_daten_key_akt, null );

        /*
         * Table-Data Tag hinzufuegen.
         *
         * Der Sonderzeichen im Ausgabestring werden in HTML-Qoutes konvertiert.
         */
        ergebnis_string_buffer.append( "<td>" );

        ergebnis_string_buffer.append( ausgabe_string_aktuell == null ? "null" : FkHtmlQuotes.quoteSpecialCharacters( ausgabe_string_aktuell ) );

        ergebnis_string_buffer.append( "</td>\n" );

        /*
         * Spaltenzaehler um eins erhoehen.
         */
        zaehler_ausgabe_spalte++;
      }

      /*
       * Tag fuer das Zeilenende einer Table-Row setzen
       */
      ergebnis_string_buffer.append( "</tr>\n" );

      /*
       * Zeilenzaehler um eins erhoehen.
       */
      zaehler_ausgabe_zeile++;
    }

    /*
     * Tabellen-End-Tag setzen
     */
    ergebnis_string_buffer.append( "</table>\n" );

    /*
     * Ergebnis an den Aufrufer zurueckgeben.
     */
    return ergebnis_string_buffer.toString();
  }

}