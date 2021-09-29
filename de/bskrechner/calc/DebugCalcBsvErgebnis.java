package de.bskrechner.calc;

import java.util.Properties;

import de.bskrechner.util.FkDatei;
import de.bskrechner.util.FkHtml;
import de.bskrechner.util.FkHtmlQuotes;
import de.bskrechner.util.FkLog;
import de.bskrechner.util.FkString;
import de.bskrechner.util.FkSystem;

public class DebugCalcBsvErgebnis
{
  private static final boolean KONVERT_LEERSTRING_TO_NBSP                                     = true;

  public static final int      AUSGABE_ART_TEXT                                               = 1;

  public static final int      AUSGABE_ART_HTML                                               = 2;

  private static final int     BEAN_PROP_INDEX_LEEERSRING                                     = -1;

  private static final int     BEAN_PROP_INDEX_VORGABE_SPARSTOPP                              = 0;

  private static final int     BEAN_PROP_INDEX_VORGABE_BERECHNUNGSZIEL                        = 1;

  private static final int     BEAN_PROP_INDEX_RECHENKERN_FEHLER_NUMMER                       = 2;

  private static final int     BEAN_PROP_INDEX_RECHENKERN_FEHLER_TEXT                         = 3;

  private static final int     BEAN_PROP_INDEX_DATUM_BERECHNUNG_BEGINN_LONG                   = 4;

  private static final int     BEAN_PROP_INDEX_DATUM_BERECHNUNG_ENDE_LONG                     = 5;

  private static final int     BEAN_PROP_INDEX_BETRAG_BSV_SPARBEITRAG                         = 6;

  private static final int     BEAN_PROP_INDEX_DATUM_SPARSTOPP_SPARRATE_LONG                  = 7;

  private static final int     BEAN_PROP_INDEX_BETRAG_BSV_BAUSPARSUMME                        = 8;

  private static final int     BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_ANTEILIG_ANZ_MONATE           = 9;

  private static final int     BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_ANTEILIG_BETRAG               = 10;

  private static final int     BEAN_PROP_INDEX_BELASTUNG_LONG                                 = 11;

  private static final int     BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_ANTEILIG_DATUM_BELASTUNG_LONG = 12;

  private static final int     BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_LAUFEND_BETRAG                = 13;

  private static final int     BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_LAUFEND_DATUM_BELASTUNG_LONG  = 14;

  private static final int     BEAN_PROP_INDEX_SPAR_ANSP_KNZ_BERECHNET                        = 15;

  private static final int     BEAN_PROP_INDEX_SPAR_ANSP_BETRAG_AUSSTEHEND                    = 16;

  private static final int     BEAN_PROP_INDEX_SPAR_ANSP_BETRAG_GESAMT                        = 17;

  private static final int     BEAN_PROP_INDEX_SPAR_ANSP_BETRAG_GUTGESCHRIEBEN                = 18;

  private static final int     BEAN_PROP_INDEX_SPAR_WOP_KNZ_BERECHNET                         = 19;

  private static final int     BEAN_PROP_INDEX_SPAR_WOP_BETRAG_AUSSTEHEND                     = 20;

  private static final int     BEAN_PROP_INDEX_SPAR_WOP_BETRAG_GEBUEHR_GESAMT                 = 21;

  private static final int     BEAN_PROP_INDEX_SPAR_WOP_BETRAG_GESAMT                         = 22;

  private static final int     BEAN_PROP_INDEX_SPAR_WOP_BETRAG_GUTGESCHRIEBEN                 = 23;

  private static final int     BEAN_PROP_INDEX_STICHTAG_DATUM_LONG                            = 24;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_BEWERTUNGSZAHL                 = 25;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_SPARBEITRAG                    = 26;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_VL_ANTRAGSTELLER               = 27;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_VL_EHEPARTNER                  = 28;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_SONDERZAHLUNG                  = 29;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_ZINSEN                         = 30;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_GEBEUHREN_KFG                  = 31;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_KONTOSTAND                     = 32;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_SALDENSUMME                    = 33;

  private static final int     BEAN_PROP_INDEX_STICHTAG_BETRAG_SOLL_GUTHABEN                  = 34;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_DATUM_LONG                           = 35;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_BEWERTUNGSZAHL                = 36;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_SPARBEITRAG                   = 37;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_VL_ANTRAGSTELLER              = 38;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_VL_EHEPARTNER                 = 39;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_SONDERZAHLUNG                 = 40;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_ZINSEN                        = 41;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_GEBEUHREN_KFG                 = 42;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_BONUS_ZINSEN                  = 43;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_BONUS_GUTGESCHRIEBEN          = 44;

  private static final int     BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_GUTHABEN_ZINSEN               = 45;

  private static final int     BEAN_PROP_INDEX_TARIF_MINDESTSPARZEIT_DATUM                    = 46;

  private static final int     BEAN_PROP_INDEX_TARIF_MINDESTSPARZEIT_ANZAHL_MONATE            = 47;

  private static final int     BEAN_PROP_INDEX_TARIF_MINDESTGUTHABEN_DATUM                    = 48;

  private static final int     BEAN_PROP_INDEX_TARIF_MINDESTGUTHABEN_BETRAG                   = 49;

  private static final int     BEAN_PROP_INDEX_TARIF_MINDESTGUTHABEN_KNZ_ERREICHT             = 50;

  private static final int     BEAN_PROP_INDEX_TARIF_BEWERTUNGSZAHL_DATUM                     = 51;

  private static final int     BEAN_PROP_INDEX_TARIF_BEWERTUNGSZAHL_BETRAG                    = 52;

  private static final int     BEAN_PROP_INDEX_TARIF_BEWERTUNGSZAHL_KNZ_ERREICHT              = 53;

  private static final int     BEAN_PROP_INDEX_TARIF_ZUTEILUNGSVORAUSSETZUNGEN_DATUM          = 54;

  private static final int     BEAN_PROP_INDEX_TARIF_ZUTEILUNGSVORAUSSETZUNGEN_KNZ_ERREICHT   = 55;

  private static final int     BEAN_PROP_INDEX_TARIF_REGELSPARBEITRAG_BETRAG                  = 56;

  private static final int     BEAN_PROP_INDEX_TARIF_REGELSPARBEITRAG_PROMILLE_BSS            = 57;

  private static final int     BEAN_PROP_INDEX_TARIF_MINDEST_BSS                              = 58;

  private static final int     BEAN_PROP_INDEX_TARIF_MINDEST_BWZ                              = 59;

  private static final int     BEAN_PROP_INDEX_TARIF_PROMILLE_ABSCHLUSS_GEBUEHR               = 60;

  private static final int     BEAN_PROP_INDEX_TARIF_PROZ_GUTHABENZINS                        = 61;

  private static final int     BEAN_PROP_INDEX_TARIF_PROZ_MINDEST_SPARGUTHABEN                = 62;

  private static final int     BEAN_PROP_INDEX_TARIF_WARTEZEIT_IN_MONATEN                     = 63;

  private static final int     BEAN_PROP_INDEX_BSD_DATUM_DARLEHEN_BEGINN                      = 64;

  private static final int     BEAN_PROP_INDEX_BSD_DATUM_DARLEHEN_ENDE                        = 65;

  private static final int     BEAN_PROP_INDEX_BSD_TARIFLICHER_DARLEHENSANSPRUCH              = 66;

  private static final int     BEAN_PROP_INDEX_BSD_TARIFLICHER_ZTB                            = 67;

  private static final int     BEAN_PROP_INDEX_BSD_ZTB_BETRAG                                 = 68;

  private static final int     BEAN_PROP_INDEX_BSD_ZTB_PROMILL_BSS                            = 69;

  private static final int     BEAN_PROP_INDEX_BSD_BETRAG_ANFANGSDARLEHEN                     = 70;

  private static final int     BEAN_PROP_INDEX_BSD_BETRAG_DARLEHEN_MIT_MEHRZUTEILUNG          = 71;

  private static final int     BEAN_PROP_INDEX_BSD_BETRAG_MEHR_DARLEHEN                       = 72;

  private static final int     BEAN_PROP_INDEX_BSD_DARLEHENS_GEBUEHR_BETRAG                   = 73;

  private static final int     BEAN_PROP_INDEX_BSD_DARLEHENS_GEBUEHR_DATUM_BELASTUNG          = 74;

  private static final int     BEAN_PROP_INDEX_BSD_DISAGIO_BETRAG                             = 75;

  private static final int     BEAN_PROP_INDEX_BSD_DISAGIO_DATUM_BELASTUNG                    = 76;

  private static final int     BEAN_PROP_INDEX_BSD_GESAMT_EIGENE_EINZAHLUNG                   = 77;

  private static final int     BEAN_PROP_INDEX_BSD_GESAMT_KFG                                 = 78;

  private static final int     BEAN_PROP_INDEX_BSD_GESAMT_SONDERTIGLUNG                       = 79;

  private static final int     BEAN_PROP_INDEX_BSD_GESAMT_ZINSEN                              = 80;

  private static final int     BEAN_PROP_INDEX_BSD_LETZTE_RATE_BETRAG                         = 81;

  private static final int     BEAN_PROP_INDEX_BSD_LETZTE_RATE_DATUM                          = 82;

  private static final int     BEAN_PROP_INDEX_BSD_LETZTE_RATE_ZINSBELASTUNG                  = 83;

  private static final int     BEAN_PROP_INDEX_LAUFZEIT_SPARPHASE_GESAMT_MONATE               = 84;

  private static final int     BEAN_PROP_INDEX_LAUFZEIT_SPARPHASE_JAHRE                       = 85;

  private static final int     BEAN_PROP_INDEX_LAUFZEIT_SPARPHASE_MONATE                      = 86;

  private static final int     BEAN_PROP_INDEX_LAUFZEIT_BSD_GESAMT_MONATE                     = 87;

  private static final int     BEAN_PROP_INDEX_LAUFZEIT_BSD_JAHRE                             = 88;

  private static final int     BEAN_PROP_INDEX_LAUFZEIT_BSD_MONATE                            = 89;

  private static final int     BEAN_PROP_INDEX_LAUFZEIT_VERTRAG_GESAMT_MONATE                 = 90;

  private static final int     BEAN_PROP_INDEX_LAUFZEIT_VERTRAG_JAHRE                         = 91;

  private static final int     BEAN_PROP_INDEX_LAUFZEIT_VERTRAG_MONATE                        = 92;

  private static final int     BEAN_PROP_INDEX_MILLISEKUNDEN_GESAMT                           = 93;

  private static final int     BEAN_PROP_INDEX_MILLISEKUNDEN_VORABBERECHNUNGEN                = 94;

  private static final int     BEAN_PROP_INDEX_MILLISEKUNDEN_SPARPHASE_KTO_VERLAUF            = 95;

  private static final int     BEAN_PROP_INDEX_MILLISEKUNDEN_SPARPHASE_BERECHNUNG             = 96;

  private static final int     BEAN_PROP_INDEX_MILLISEKUNDEN_DARLEHENSPHASE                   = 97;

  public static void main( String[] args )
  {
    long zeit_differenz_millisekunden = 0;

    long time_stamp_start = System.currentTimeMillis();

    FkLog.wl( "" );
    FkLog.wl( "START DebugCalcBsvErgebnis" );
    FkLog.wl( "" );

    String datei_name_ausgabe = FkSystem.getStdRootVerzeichnis() + "datei_ausgabe_calc_bsv_ergebnis.txt";

    try
    {
      /*
       * Zwei Testinstanzen erstellen
       */
      CalcBsvErgebnis inst_calc_bsv_ergebnis_1 = null;// FkTestwerteCalcBsvErgebnis.getTestInstanzCalcBsvErgebnis();

      CalcBsvErgebnis inst_calc_bsv_ergebnis_2 = null; //FkTestwerteCalcBsvErgebnis.getTestInstanzCalcBsvErgebnis();

      /*
       * Ein Array mit den beiden Instanzen erstellen 
       */
      CalcBsvErgebnis[] array_calc_bsv_ergebnis = { inst_calc_bsv_ergebnis_1, inst_calc_bsv_ergebnis_2 };

      /*
       * Aufruf der Funktion "getGridString" um den Ergebnisstring zu erhalten
       */
      String str_grid_ausgabe_array_calc_bsv_ergebnis = DebugCalcBsvErgebnis.getGridString( array_calc_bsv_ergebnis, true, AUSGABE_ART_TEXT ) + "\n\n\n" + getGridString( array_calc_bsv_ergebnis, false, AUSGABE_ART_TEXT );

      /*
       * Test: Array-Parameter ist "null" 
       */
      str_grid_ausgabe_array_calc_bsv_ergebnis += "\n\n\n" + getGridString( null, false, AUSGABE_ART_TEXT );

      /*
       * Ausgabedatei erstellen
       */
      FkDatei.systemSchreibeDatei( datei_name_ausgabe, str_grid_ausgabe_array_calc_bsv_ergebnis );

      /*
       * Test der unterschiedlichen Ausgabeformate
       */
      String datei_name_ausgabe_text = FkSystem.getStdRootVerzeichnis() + "datei_ausgabe_calc_bsv_ergebnis_text.txt";

      String datei_name_ausgabe_html = FkSystem.getStdRootVerzeichnis() + "datei_ausgabe_calc_bsv_ergebnis_html.html";

      /*
       * Test Textgrid erstellen
       */
      String str_grid_ausgabe_text_array = DebugCalcBsvErgebnis.getGridString( array_calc_bsv_ergebnis, false, AUSGABE_ART_TEXT );

      FkDatei.systemSchreibeDatei( datei_name_ausgabe_text, str_grid_ausgabe_text_array );

      str_grid_ausgabe_text_array = null;

      /*
       * Test HTML-Grid und HTML-Ergebnisseite erstellen
       */
      String str_grid_ausgabe_html_array = DebugCalcBsvErgebnis.getGridString( array_calc_bsv_ergebnis, false, AUSGABE_ART_HTML );

      String html_seite = FkHtml.getHtmlGeruest( str_grid_ausgabe_html_array, "Test Griderstellung" );

      FkDatei.systemSchreibeDatei( datei_name_ausgabe_html, html_seite );

      str_grid_ausgabe_html_array = null;

      FkLog.wl( "" );
      FkLog.wl( "Html Datei " + FkDatei.getVerzeichnisPathSeparator( datei_name_ausgabe_html, "\\", false ) );
      FkLog.wl( "Text Datei " + FkDatei.getVerzeichnisPathSeparator( datei_name_ausgabe_text, "\\", false ) );
      FkLog.wl( "" );

      /*
       * Testinstanzden clearen
       */
      inst_calc_bsv_ergebnis_1.clear();
      inst_calc_bsv_ergebnis_2.clear();

      /*
       * Objekte auf null setzen
       */
      inst_calc_bsv_ergebnis_1 = null;
      inst_calc_bsv_ergebnis_2 = null;

      array_calc_bsv_ergebnis = null;

      str_grid_ausgabe_array_calc_bsv_ergebnis = null;
    }
    catch ( Exception err_inst )
    {
      FkLog.wl( "Fehler: DebugCalcBsvErgebnis ", err_inst );
    }

    long time_stamp_ende = System.currentTimeMillis();

    zeit_differenz_millisekunden = time_stamp_ende - time_stamp_start;

    FkLog.wl( "" );
    FkLog.wl( "Millisekunden " + FkString.right( "          " + zeit_differenz_millisekunden, 10 ) );
    FkLog.wl( "" );
    FkLog.wl( "ENDE DebugCalcBsvErgebnis" );
    FkLog.wl( "" );
    FkLog.wl( "Datei " + FkDatei.getVerzeichnisPathSeparator( datei_name_ausgabe, "\\", false ) );
    FkLog.wl( "" );

    System.exit( 0 );
  }

  /**
   * <pre>
   * Liefert einen String entsprechend der Umsetzungstabelle zurueck.
   * 
   *    -1 = Leerstring 
   * 
   *      0 = Vorgabe Sparstopp
   *      1 = Vorgabe Berechnungsziel
   *      2 = Rechenkern Fehler Nummer
   *      3 = Rechenkern Fehler Text
   *      4 = Datum Berechnung Beginn Long
   *      5 = Datum Berechnung Ende Long
   *      6 = Betrag Bsv Sparbeitrag
   *      7 = Datum Sparstopp Sparrate Long
   *      8 = Betrag Bsv Bausparsumme
   *      9 = Spar Kto Gebuehr Anteilig Anz Monate
   *     10 = Spar Kto Gebuehr Anteilig Betrag
   *     11 = Belastung Long
   *     12 = Spar Kto Gebuehr Anteilig Datum Belastung Long
   *     13 = Spar Kto Gebuehr Laufend Betrag
   *     14 = Spar Kto Gebuehr Laufend Datum Belastung Long
   *     15 = Spar Ansp Knz Berechnet
   *     16 = Spar Ansp Betrag Ausstehend
   *     17 = Spar Ansp Betrag Gesamt
   *     18 = Spar Ansp Betrag Gutgeschrieben
   *     19 = Spar Wop Knz Berechnet
   *     20 = Spar Wop Betrag Ausstehend
   *     21 = Spar Wop Betrag Gebuehr Gesamt
   *     22 = Spar Wop Betrag Gesamt
   *     23 = Spar Wop Betrag Gutgeschrieben
   *     24 = Stichtag Datum Long
   *     25 = Stichtag Betrag Bewertungszahl
   *     26 = Stichtag Betrag Sparbeitrag
   *     27 = Stichtag Betrag Vl Antragsteller
   *     28 = Stichtag Betrag Vl Ehepartner
   *     29 = Stichtag Betrag Sonderzahlung
   *     30 = Stichtag Betrag Zinsen
   *     31 = Stichtag Betrag Gebeuhren Kfg
   *     32 = Stichtag Betrag Kontostand
   *     33 = Stichtag Betrag Saldensumme
   *     34 = Stichtag Betrag Soll Guthaben
   *     35 = Zuteilung Datum Long
   *     36 = Zuteilung Betrag Bewertungszahl
   *     37 = Zuteilung Betrag Sparbeitrag
   *     38 = Zuteilung Betrag Vl Antragsteller
   *     39 = Zuteilung Betrag Vl Ehepartner
   *     40 = Zuteilung Betrag Sonderzahlung
   *     41 = Zuteilung Betrag Zinsen
   *     42 = Zuteilung Betrag Gebeuhren Kfg
   *     43 = Zuteilung Betrag Bonus Zinsen
   *     44 = Zuteilung Betrag Bonus Gutgeschrieben
   *     45 = Zuteilung Betrag Guthaben Zinsen
   *     46 = Tarif Mindestsparzeit Datum
   *     47 = Tarif Mindestsparzeit Anzahl Monate
   *     48 = Tarif Mindestguthaben Datum
   *     49 = Tarif Mindestguthaben Betrag
   *     50 = Tarif Mindestguthaben Knz Erreicht
   *     51 = Tarif Bewertungszahl Datum
   *     52 = Tarif Bewertungszahl Betrag
   *     53 = Tarif Bewertungszahl Knz Erreicht
   *     54 = Tarif Zuteilungsvoraussetzungen Datum
   *     55 = Tarif Zuteilungsvoraussetzungen Knz Erreicht
   *     56 = Tarif Regelsparbeitrag Betrag
   *     57 = Tarif Regelsparbeitrag Promille Bss
   *     58 = Tarif Mindest Bss
   *     59 = Tarif Mindest Bwz
   *     60 = Tarif Promille Abschluss Gebuehr
   *     61 = Tarif Proz Guthabenzins
   *     62 = Tarif Proz Mindest Sparguthaben
   *     63 = Tarif Wartezeit In Monaten
   *     64 = Bsd Datum Darlehen Beginn
   *     65 = Bsd Datum Darlehen Ende
   *     66 = Bsd Tariflicher Darlehensanspruch
   *     67 = Bsd Tariflicher Ztb
   *     68 = Bsd Ztb Betrag
   *     69 = Bsd Ztb Promill Bss
   *     70 = Bsd Betrag Anfangsdarlehen
   *     71 = Bsd Betrag Darlehen Mit Mehrzuteilung
   *     72 = Bsd Betrag Mehr Darlehen
   *     73 = Bsd Darlehens Gebuehr Betrag
   *     74 = Bsd Darlehens Gebuehr Datum Belastung
   *     75 = Bsd Disagio Betrag
   *     76 = Bsd Disagio Datum Belastung
   *     77 = Bsd Gesamt Eigene Einzahlung
   *     78 = Bsd Gesamt Kfg
   *     79 = Bsd Gesamt Sondertiglung
   *     80 = Bsd Gesamt Zinsen
   *     81 = Bsd Letzte Rate Betrag
   *     82 = Bsd Letzte Rate Datum
   *     83 = Bsd Letzte Rate Zinsbelastung
   *     84 = Laufzeit Sparphase Gesamt Monate
   *     85 = Laufzeit Sparphase Jahre
   *     86 = Laufzeit Sparphase Monate
   *     87 = Laufzeit Bsd Gesamt Monate
   *     88 = Laufzeit Bsd Jahre
   *     89 = Laufzeit Bsd Monate
   *     90 = Laufzeit Vertrag Gesamt Monate
   *     91 = Laufzeit Vertrag Jahre
   *     92 = Laufzeit Vertrag Monate
   *     93 = Millisekunden Gesamt
   *     94 = Millisekunden Vorabberechnungen
   *     95 = Millisekunden Sparphase Kto Verlauf
   *     96 = Millisekunden Sparphase Berechnung
   *     97 = Millisekunden Darlehensphase
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
        return "Vorgabe Sparstopp";

      case 1 :
        return "Vorgabe Berechnungsziel";

      case 2 :
        return "Rechenkern Fehler Nummer";

      case 3 :
        return "Rechenkern Fehler Text";

      case 4 :
        return "Datum Berechnung Beginn Long";

      case 5 :
        return "Datum Berechnung Ende Long";

      case 6 :
        return "Betrag Bsv Sparbeitrag";

      case 7 :
        return "Datum Sparstopp Sparrate Long";

      case 8 :
        return "Betrag Bsv Bausparsumme";

      case 9 :
        return "Spar Kto Gebuehr Anteilig Anz Monate";

      case 10 :
        return "Spar Kto Gebuehr Anteilig Betrag";

      case 11 :
        return "Belastung Long";

      case 12 :
        return "Spar Kto Gebuehr Anteilig Datum Belastung Long";

      case 13 :
        return "Spar Kto Gebuehr Laufend Betrag";

      case 14 :
        return "Spar Kto Gebuehr Laufend Datum Belastung Long";

      case 15 :
        return "Spar Ansp Knz Berechnet";

      case 16 :
        return "Spar Ansp Betrag Ausstehend";

      case 17 :
        return "Spar Ansp Betrag Gesamt";

      case 18 :
        return "Spar Ansp Betrag Gutgeschrieben";

      case 19 :
        return "Spar Wop Knz Berechnet";

      case 20 :
        return "Spar Wop Betrag Ausstehend";

      case 21 :
        return "Spar Wop Betrag Gebuehr Gesamt";

      case 22 :
        return "Spar Wop Betrag Gesamt";

      case 23 :
        return "Spar Wop Betrag Gutgeschrieben";

      case 24 :
        return "Stichtag Datum Long";

      case 25 :
        return "Stichtag Betrag Bewertungszahl";

      case 26 :
        return "Stichtag Betrag Sparbeitrag";

      case 27 :
        return "Stichtag Betrag Vl Antragsteller";

      case 28 :
        return "Stichtag Betrag Vl Ehepartner";

      case 29 :
        return "Stichtag Betrag Sonderzahlung";

      case 30 :
        return "Stichtag Betrag Zinsen";

      case 31 :
        return "Stichtag Betrag Gebeuhren Kfg";

      case 32 :
        return "Stichtag Betrag Kontostand";

      case 33 :
        return "Stichtag Betrag Saldensumme";

      case 34 :
        return "Stichtag Betrag Soll Guthaben";

      case 35 :
        return "Zuteilung Datum Long";

      case 36 :
        return "Zuteilung Betrag Bewertungszahl";

      case 37 :
        return "Zuteilung Betrag Sparbeitrag";

      case 38 :
        return "Zuteilung Betrag Vl Antragsteller";

      case 39 :
        return "Zuteilung Betrag Vl Ehepartner";

      case 40 :
        return "Zuteilung Betrag Sonderzahlung";

      case 41 :
        return "Zuteilung Betrag Zinsen";

      case 42 :
        return "Zuteilung Betrag Gebeuhren Kfg";

      case 43 :
        return "Zuteilung Betrag Bonus Zinsen";

      case 44 :
        return "Zuteilung Betrag Bonus Gutgeschrieben";

      case 45 :
        return "Zuteilung Betrag Guthaben Zinsen";

      case 46 :
        return "Tarif Mindestsparzeit Datum";

      case 47 :
        return "Tarif Mindestsparzeit Anzahl Monate";

      case 48 :
        return "Tarif Mindestguthaben Datum";

      case 49 :
        return "Tarif Mindestguthaben Betrag";

      case 50 :
        return "Tarif Mindestguthaben Knz Erreicht";

      case 51 :
        return "Tarif Bewertungszahl Datum";

      case 52 :
        return "Tarif Bewertungszahl Betrag";

      case 53 :
        return "Tarif Bewertungszahl Knz Erreicht";

      case 54 :
        return "Tarif Zuteilungsvoraussetzungen Datum";

      case 55 :
        return "Tarif Zuteilungsvoraussetzungen Knz Erreicht";

      case 56 :
        return "Tarif Regelsparbeitrag Betrag";

      case 57 :
        return "Tarif Regelsparbeitrag Promille Bss";

      case 58 :
        return "Tarif Mindest Bss";

      case 59 :
        return "Tarif Mindest Bwz";

      case 60 :
        return "Tarif Promille Abschluss Gebuehr";

      case 61 :
        return "Tarif Proz Guthabenzins";

      case 62 :
        return "Tarif Proz Mindest Sparguthaben";

      case 63 :
        return "Tarif Wartezeit In Monaten";

      case 64 :
        return "Bsd Datum Darlehen Beginn";

      case 65 :
        return "Bsd Datum Darlehen Ende";

      case 66 :
        return "Bsd Tariflicher Darlehensanspruch";

      case 67 :
        return "Bsd Tariflicher Ztb";

      case 68 :
        return "Bsd Ztb Betrag";

      case 69 :
        return "Bsd Ztb Promill Bss";

      case 70 :
        return "Bsd Betrag Anfangsdarlehen";

      case 71 :
        return "Bsd Betrag Darlehen Mit Mehrzuteilung";

      case 72 :
        return "Bsd Betrag Mehr Darlehen";

      case 73 :
        return "Bsd Darlehens Gebuehr Betrag";

      case 74 :
        return "Bsd Darlehens Gebuehr Datum Belastung";

      case 75 :
        return "Bsd Disagio Betrag";

      case 76 :
        return "Bsd Disagio Datum Belastung";

      case 77 :
        return "Bsd Gesamt Eigene Einzahlung";

      case 78 :
        return "Bsd Gesamt Kfg";

      case 79 :
        return "Bsd Gesamt Sondertiglung";

      case 80 :
        return "Bsd Gesamt Zinsen";

      case 81 :
        return "Bsd Letzte Rate Betrag";

      case 82 :
        return "Bsd Letzte Rate Datum";

      case 83 :
        return "Bsd Letzte Rate Zinsbelastung";

      case 84 :
        return "Laufzeit Sparphase Gesamt Monate";

      case 85 :
        return "Laufzeit Sparphase Jahre";

      case 86 :
        return "Laufzeit Sparphase Monate";

      case 87 :
        return "Laufzeit Bsd Gesamt Monate";

      case 88 :
        return "Laufzeit Bsd Jahre";

      case 89 :
        return "Laufzeit Bsd Monate";

      case 90 :
        return "Laufzeit Vertrag Gesamt Monate";

      case 91 :
        return "Laufzeit Vertrag Jahre";

      case 92 :
        return "Laufzeit Vertrag Monate";

      case 93 :
        return "Millisekunden Gesamt";

      case 94 :
        return "Millisekunden Vorabberechnungen";

      case 95 :
        return "Millisekunden Sparphase Kto Verlauf";

      case 96 :
        return "Millisekunden Sparphase Berechnung";

      case 97 :
        return "Millisekunden Darlehensphase";
    }

    //return "---";

    return null;
  }

  /**
   * @return die Anzahl der Class-Properties
   */
  private static int getAnzahlBeanProperties()
  {
    return 98;
  }

  /**
   * @return ein Array mit allen Propertie-Indexwerten fuer die Ausgabe
   */
  private static int[] getArrayAusgabeProperties()
  {
    int[] index_prop_array = new int[ getAnzahlBeanProperties() + 15 ];

    //int[] index_prop_array = { Feld1, Feld2 };

    int akt_index = 0;

    /*
     * Leerzeile in Ausgabe
     * Um eine Leerzeile in der Ausgabe zu erstellen ist der Ausgabeindex -1 vorhanden.
     * Bei diesen Index wird ein Leerstring zurueckgegeben.
     */
    //

    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_VORGABE_SPARSTOPP;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_VORGABE_BERECHNUNGSZIEL;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_RECHENKERN_FEHLER_NUMMER;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_RECHENKERN_FEHLER_TEXT;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_DATUM_BERECHNUNG_BEGINN_LONG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_DATUM_BERECHNUNG_ENDE_LONG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BETRAG_BSV_SPARBEITRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_DATUM_SPARSTOPP_SPARRATE_LONG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BETRAG_BSV_BAUSPARSUMME;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_ANTEILIG_ANZ_MONATE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_ANTEILIG_BETRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BELASTUNG_LONG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_ANTEILIG_DATUM_BELASTUNG_LONG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_LAUFEND_BETRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_KTO_GEBUEHR_LAUFEND_DATUM_BELASTUNG_LONG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_ANSP_KNZ_BERECHNET;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_ANSP_BETRAG_AUSSTEHEND;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_ANSP_BETRAG_GESAMT;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_ANSP_BETRAG_GUTGESCHRIEBEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_WOP_KNZ_BERECHNET;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_WOP_BETRAG_AUSSTEHEND;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_WOP_BETRAG_GEBUEHR_GESAMT;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_WOP_BETRAG_GESAMT;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_SPAR_WOP_BETRAG_GUTGESCHRIEBEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_DATUM_LONG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_BEWERTUNGSZAHL;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_SPARBEITRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_VL_ANTRAGSTELLER;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_VL_EHEPARTNER;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_SONDERZAHLUNG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_ZINSEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_GEBEUHREN_KFG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_KONTOSTAND;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_SALDENSUMME;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_STICHTAG_BETRAG_SOLL_GUTHABEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_DATUM_LONG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_BEWERTUNGSZAHL;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_SPARBEITRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_VL_ANTRAGSTELLER;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_VL_EHEPARTNER;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_SONDERZAHLUNG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_ZINSEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_GEBEUHREN_KFG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_BONUS_ZINSEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_BONUS_GUTGESCHRIEBEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_ZUTEILUNG_BETRAG_GUTHABEN_ZINSEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_MINDESTSPARZEIT_DATUM;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_MINDESTSPARZEIT_ANZAHL_MONATE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_MINDESTGUTHABEN_DATUM;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_MINDESTGUTHABEN_BETRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_MINDESTGUTHABEN_KNZ_ERREICHT;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_BEWERTUNGSZAHL_DATUM;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_BEWERTUNGSZAHL_BETRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_BEWERTUNGSZAHL_KNZ_ERREICHT;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_ZUTEILUNGSVORAUSSETZUNGEN_DATUM;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_ZUTEILUNGSVORAUSSETZUNGEN_KNZ_ERREICHT;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_REGELSPARBEITRAG_BETRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_REGELSPARBEITRAG_PROMILLE_BSS;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_MINDEST_BSS;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_MINDEST_BWZ;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_PROMILLE_ABSCHLUSS_GEBUEHR;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_PROZ_GUTHABENZINS;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_PROZ_MINDEST_SPARGUTHABEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_TARIF_WARTEZEIT_IN_MONATEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_DATUM_DARLEHEN_BEGINN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_DATUM_DARLEHEN_ENDE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_TARIFLICHER_DARLEHENSANSPRUCH;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_TARIFLICHER_ZTB;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_ZTB_BETRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_ZTB_PROMILL_BSS;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_BETRAG_ANFANGSDARLEHEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_BETRAG_DARLEHEN_MIT_MEHRZUTEILUNG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_BETRAG_MEHR_DARLEHEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_DARLEHENS_GEBUEHR_BETRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_DARLEHENS_GEBUEHR_DATUM_BELASTUNG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_DISAGIO_BETRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_DISAGIO_DATUM_BELASTUNG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_GESAMT_EIGENE_EINZAHLUNG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_GESAMT_KFG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_GESAMT_SONDERTIGLUNG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_GESAMT_ZINSEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_LETZTE_RATE_BETRAG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_LETZTE_RATE_DATUM;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_BSD_LETZTE_RATE_ZINSBELASTUNG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LAUFZEIT_SPARPHASE_GESAMT_MONATE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LAUFZEIT_SPARPHASE_JAHRE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LAUFZEIT_SPARPHASE_MONATE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LAUFZEIT_BSD_GESAMT_MONATE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LAUFZEIT_BSD_JAHRE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LAUFZEIT_BSD_MONATE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LAUFZEIT_VERTRAG_GESAMT_MONATE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LAUFZEIT_VERTRAG_JAHRE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LAUFZEIT_VERTRAG_MONATE;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_LEEERSRING;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_MILLISEKUNDEN_GESAMT;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_MILLISEKUNDEN_VORABBERECHNUNGEN;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_MILLISEKUNDEN_SPARPHASE_KTO_VERLAUF;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_MILLISEKUNDEN_SPARPHASE_BERECHNUNG;
    index_prop_array[ akt_index++ ] = BEAN_PROP_INDEX_MILLISEKUNDEN_DARLEHENSPHASE;

    return index_prop_array;
  }

  /**
   * <pre>
   * Erstellt eine Tabelle mit den Daten der gespeicherten Instanzen im Array
   * </pre>
   *
   * @param pArrayCalcBsvErgebnis Array mit den auszugebenden Daten
   * @param pKnzZeilenBasiert Kennzeichen, ob die Daten in Zeilen ausgegeben werden sollen
   * @param pAusgabeArt die zu erstellende Ausgabeart (Text oder HTML)
   * @return einen String mit den Daten des Arrays als Text-Tabelle
   */
  public static String getGridString( CalcBsvErgebnis[] pArrayCalcBsvErgebnis, boolean pKnzZeilenBasiert, int pAusgabeArt )
  {
    /*
     * Definition der auszugebenden Bean-Properties
     *
     * Es wird ein Array mit den auszugebenden Index-Werten der Beanproperties definiert
     */
    int[] index_prop_array = getArrayAusgabeProperties();

    /*
     * Anzahl der Ausgabe-Arrayelemeente
     */
    int max_daten_properties = index_prop_array.length;

    /*
     * Anzahl der Arrayelemeente
     */
    int anzahl_array_elemente = ( pArrayCalcBsvErgebnis == null ? 0 : pArrayCalcBsvErgebnis.length );

    /*
     * Breite der Zaehlerspalte
     * Die Anzahl der im Array enthaltenen Instanzen definiert die Breite der Zaehlerspalte.
     *
     * <      10 = 2 Zeichen
     * <     100 = 3 Zeichen
     * <   1.000 = 4 Zeichen
     * < 100.000 = 6 Zeichen
     * sonst       7 Zeichen
     */
    int breite_zaehler_spalte = 7;

    if ( anzahl_array_elemente < 10 )
    {
      breite_zaehler_spalte = 2;
    }
    else if ( anzahl_array_elemente < 100 )
    {
      breite_zaehler_spalte = 3;
    }
    else if ( anzahl_array_elemente < 1000 )
    {
      breite_zaehler_spalte = 4;
    }
    else if ( anzahl_array_elemente < 100000 )
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

    int max_ausgabe_spalten = 32123;

    int zaehler_zeile = 0;
    int zaehler_spalte = 0;

    /*
     * Werte fuer die Startposition der Datenausgabe im Grid.
     * Die Startpositionen werden mit 0 vorbelegt.
     * Mit den Variablen "spalte_start_wert" und "zeile_start_wert", kann
     * die Ausgabe dieser Funktion im Ausgabegrid verschoben werden.
     */
    int spalte_start_wert = 0;
    int zeile_start_wert = 0;

    /*
     * Schritt 1: Propertie-Grid erstellen
     * In einer Propertie-Instanz werden die auszugebenden Daten abgelegt.
     *
     * Der jeweilige Schluessel wird dabei aus "Z + INDEX + S + INDEX" gebildet.
     *
     * Zeilenbasiert ist der Schluessel  "Z + ZEILEN_INDEX  + S + SPALTEN_INDEX"
     * Spaltenbasiert ist der Schluessel "Z + SPALTEN_INDEX + S + ZEILEN_INDEX"
     */
    Properties grid_ausgabe = new Properties();

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
       * Die maximale Zeilenanzahl sind die im Array enthaltenen Instanzen.
       */
      max_ausgabe_zeilen = anzahl_array_elemente;

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
       * Die maximale Spaltenanzahl sind die im Array enthaltenen Instanzen.
       */
      max_ausgabe_spalten = anzahl_array_elemente;
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

    int index_prop_zaehler = -1;
    int index_prop_anzahl = index_prop_array.length;

    ausgabe_string_aktuell = "Nr.";

    while ( ausgabe_string_aktuell != null ) // && ( zaehler_spalte <= max_daten_properties ) && ( index_prop_zaehler < index_prop_anzahl ))
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

      index_prop_zaehler++;

      if ( index_prop_zaehler < index_prop_anzahl )
      {
        ausgabe_string_aktuell = getUeberschrift( index_prop_array[ index_prop_zaehler ] );
      }
      else
      {
        ausgabe_string_aktuell = null;
      }
    }

    /*
     * Schritt 1B: Daten aus den Array-Instanzen in das Propertie-Grid schreiben
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

    CalcBsvErgebnis inst_calc_bsv_ergebnis = null;

    int akt_index = 0;

    /*
     * Erste While-Schleife laeuft ueber alle Array-Instanzen
     */
    while ( akt_index < anzahl_array_elemente )
    {
      try
      {
        inst_calc_bsv_ergebnis = pArrayCalcBsvErgebnis[ akt_index ];
      }
      catch ( Exception err_inst )
      {
        inst_calc_bsv_ergebnis = null;
      }

      /*
       * Pruefung: Ausgabeinstanz gesetzt ?
       */
      if ( ( inst_calc_bsv_ergebnis != null ) && ( zaehler_zeile < max_ausgabe_zeilen ) )
      {
        /*
         * In die erste Zeile/Spalte kommt wieder der aktuelle Datensatzzaehler.
         */
        zaehler_spalte = -1;

        index_prop_zaehler = -1;

        ausgabe_string_aktuell = FkString.right( "000000" + ( zaehler_zeile + 1 ), breite_zaehler_spalte );

        /*
         * Zweite While-Schleife laeuft ueber alle Ausgabefelder (Spalten)
         */
        while ( ( zaehler_spalte < max_daten_properties ) && ( index_prop_zaehler < index_prop_anzahl ) )
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

          index_prop_zaehler++;

          if ( index_prop_zaehler < index_prop_anzahl )
          {
            ausgabe_string_aktuell = inst_calc_bsv_ergebnis.getString( index_prop_array[ index_prop_zaehler ] );
          }
        }

        /*
         * Den Zeilenzaehler erhoehen
         */
        zaehler_zeile++;
      }

      /*
       * Der Array-Index-Zaehler wird erhoeht.
       */
      akt_index++;
    }

    /*
     * Grid-Ausdehnungen
     */
    int grid_anzahl_zeilen = spalte_start_wert + zaehler_spalte;
    int grid_anzahl_spalten = zeile_start_wert + zaehler_zeile;

    if ( pKnzZeilenBasiert )
    {
      grid_anzahl_zeilen = zeile_start_wert + zaehler_zeile;
      grid_anzahl_spalten = spalte_start_wert + zaehler_spalte;
    }

    /*
     * Korrektur Zeilenanzahl
     * Es wird immer eine Zeile zuviel erstellt.
     *
     * Die While-Schleife laeuft ueber alle Array-Instanzen.
     * Abbruchbedingung ist, dass der Index-Zaehler groesser als die Array-Elemente sein muss.
     * Fuer jedes Array-Element wird aber auch der Zeilenzaehler erhoeht.
     * Somit ist am Ende der Schleife der Zeilenzaehler immer um ein Element zu weit gezaehlt worden.
     */
    grid_anzahl_zeilen--;

    /*
     * Korrektur Spaltenanzahl
     * Problem ist, das eine Nummern-Spalte hinzugefuegt wird.
     * Diese Nummernspalte muss beruecksichtigt werden.
     */
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

      /*
       * Innere Schleife laeuft bis zur Anzahl der Spalten
       */
      while ( zaehler_ausgabe_spalte <= grid_anzahl_spalten )
      {
        /*
         * Zugriffs-Schluessel auf das aktuelle Zeile/Spalte-Feld bilden
         */
        prop_grid_daten_key_akt = prop_grid_daten_key_praefix_zeile + zaehler_ausgabe_zeile + prop_grid_daten_key_praefix_spalte + zaehler_ausgabe_spalte;

        /*
         * Ausgabestring fuer das aktuelle Feld aus dem (Propertie)Grid ermittel 
         */
        ausgabe_string_aktuell = grid_ausgabe.getProperty( prop_grid_daten_key_akt, null );

        /*
         * Pruefung: Ausgabestring vorhanden?
         */
        if ( ausgabe_string_aktuell != null )
        {
          /*
           * Ist ein Ausgabestring vorhanden, muss dessen Stringlaenge gegen die bisher 
           * gespeicherte maximale Stringlaenge verglichen werden.
           * 
           * Es wird zuerst der Zugriffsschluessel auf die Spaltenbreite erstellt.
           */
          prop_breite_key = prop_breite_key_praefix + zaehler_ausgabe_spalte;

          /*
           * Aus dem (Propertie)Grid wird die Stringlaenge ermittelt.
           * Als Vorgabebreite wird der eingestellte Maximalwert genommen. 
           */
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
     * Variablen auf null setzen
     */
    grid_ausgabe.clear();

    grid_ausgabe = null;

    ausgabe_string_aktuell = null;

    prop_breite_key = null;
    prop_breite_key_praefix = null;
    prop_breite_key_praefix = null;
    prop_grid_daten_key_akt = null;
    prop_grid_daten_key_praefix_spalte = null;
    prop_grid_daten_key_praefix_zeile = null;
    prop_spaltenbreite_vorgabe = null;
    prop_spaltenbreite_maximal = 0;
    prop_spaltenbreite_maximal = 0;
    prop_spaltenbreite_value = 0;
    prop_spaltenbreite_value = 0;
    zaehler_ausgabe_zeile = 0;
    zaehler_ausgabe_spalte = 0;
    zaehler_zeile = 0;
    zaehler_spalte = 0;
    grid_anzahl_zeilen = 0;
    grid_anzahl_spalten = 0;
    spalte_start_wert = 0;
    zeile_start_wert = 0;
    max_ausgabe_zeilen = 0;
    max_ausgabe_spalten = 0;
    max_daten_properties = 0;
    breite_zaehler_spalte = 0;
    akt_index = 0;

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

        /*
         * Konvertierung zu nbsp
         * Ist der aktuelle Ausgabestring ein Leerstring und es soll eine Konvertierung zu 
         * einem Non-Breaking-Space gemacht werden, wird ein solches gemacht. 
         * 
         * Ist der Ausgabestring gesetzt, wird dieser dem Ausgabestring hinzugefuegt.
         */
        if ( ( ausgabe_string_aktuell != null ) && ( ausgabe_string_aktuell.trim().length() == 0 ) && ( KONVERT_LEERSTRING_TO_NBSP ) )
        {
          ergebnis_string_buffer.append( "&nbsp" );
        }
        else
        {
          ergebnis_string_buffer.append( ausgabe_string_aktuell == null ? "null" : FkHtmlQuotes.quoteSpecialCharacters( ausgabe_string_aktuell ) );
        }

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