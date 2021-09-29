package de.bskrechner.test;

import java.util.Properties;

import de.bskrechner.calc.CalcBsvErgebnis;
import de.bskrechner.calc.CalcBsvRechenkern;
import de.bskrechner.calc.CalcKonstanten;
import de.bskrechner.calc.CalcVorgabenBsv;
import de.bskrechner.calc.CalcVorgabenTarif;
import de.bskrechner.calc.DebugCalcBsvErgebnis;
import de.bskrechner.calc.PropertieParserCalcVorgabenBsv;
import de.bskrechner.calc.PropertieParserCalcVorgabenTarif;
import de.bskrechner.util.FkDatei;
import de.bskrechner.util.FkDatumLong;
import de.bskrechner.util.FkLog;
import de.bskrechner.util.FkString;
import de.bskrechner.util.FkSystem;
import de.bskrechner.util.NewLineStringBuffer;

public class TestBestandsvertrag
{
  public static void main( String[] args )
  {
    long zeit_differenz_millisekunden = 0;

    long time_stamp_start = System.currentTimeMillis();

    FkLog.wl( "" );
    FkLog.wl( "START GenXmlCalcVorgabenBsv" );
    FkLog.wl( "" );

    String std_ausgabe_verzeichnis = FkSystem.getStdRootVerzeichnis() + "JavaRechenkern/";

    FkDatei.createDirectory( std_ausgabe_verzeichnis );

    try
    {
      Properties properties_bsv_calc_vorgaben = getTestPropertiesCalcVorgabenBsvBestandsvertrag();

      CalcVorgabenBsv inst_calc_bsv_eingabe = PropertieParserCalcVorgabenBsv.parseCalcVorgabenBsv( properties_bsv_calc_vorgaben );

      FkLog.wl( inst_calc_bsv_eingabe.toString() );

      CalcVorgabenTarif calc_bsv_tarif = PropertieParserCalcVorgabenTarif.parseCalcVorgabenTarif();

      FkLog.wl( calc_bsv_tarif.toString() );

      CalcBsvRechenkern calc_bsv_rechenkern = new CalcBsvRechenkern();

      inst_calc_bsv_eingabe.setSparStoppMindestGuthaben();

      inst_calc_bsv_eingabe.setZielBausparsumme();

      CalcBsvErgebnis calc_ergebnis_bss = calc_bsv_rechenkern.calcBausparvertrag( inst_calc_bsv_eingabe, calc_bsv_tarif );

      FkDatei.schreibeDatei( std_ausgabe_verzeichnis + "test_berechnung_ziel_bss.txt", calc_ergebnis_bss.getLogString() );

      inst_calc_bsv_eingabe.setZielLaufzeit();

      CalcBsvErgebnis calc_ergebnis_lz = calc_bsv_rechenkern.calcBausparvertrag( inst_calc_bsv_eingabe, calc_bsv_tarif );

      FkDatei.schreibeDatei( std_ausgabe_verzeichnis + "test_berechnung_ziel_lz.txt", calc_ergebnis_lz.getLogString() );

      inst_calc_bsv_eingabe.setZielSparrate();

      CalcBsvErgebnis calc_ergebnis_rate = calc_bsv_rechenkern.calcBausparvertrag( inst_calc_bsv_eingabe, calc_bsv_tarif );

      FkDatei.schreibeDatei( std_ausgabe_verzeichnis + "test_berechnung_ziel_sparrate.txt", calc_ergebnis_rate.getLogString() );

      CalcBsvErgebnis[] array_ziel_calc_bsv_ergebnis = { calc_ergebnis_bss, calc_ergebnis_lz, calc_ergebnis_rate };

      /*
       * Test der unterschiedlichen Ausgabeformate
       */
      String datei_name_ausgabe_text = std_ausgabe_verzeichnis + "datei_ausgabe_calc_bsv_ergebnis_text.txt";

      String datei_name_ausgabe_html = std_ausgabe_verzeichnis + "datei_ausgabe_calc_bsv_ergebnis_html.html";

      String str_grid_ausgabe_html_array_berechnungsziel = DebugCalcBsvErgebnis.getGridString( array_ziel_calc_bsv_ergebnis, false, DebugCalcBsvErgebnis.AUSGABE_ART_HTML );

      String html_seite = getHtmlGeruest( str_grid_ausgabe_html_array_berechnungsziel + "<br>" + "<br>", "Test Griderstellung" );

      FkDatei.systemSchreibeDatei( datei_name_ausgabe_html, html_seite );

      FkLog.wl( "" );
      FkLog.wl( "Html Datei " + FkDatei.getVerzeichnisPathSeparator( datei_name_ausgabe_html, "\\", false ) );
      FkLog.wl( "Text Datei " + FkDatei.getVerzeichnisPathSeparator( datei_name_ausgabe_text, "\\", false ) );
      FkLog.wl( "" );
    }
    catch ( Exception err_inst )
    {
      FkLog.wl( "Fehler: GenXmlCalcVorgabenBsv ", err_inst );
    }

    long time_stamp_ende = System.currentTimeMillis();

    zeit_differenz_millisekunden = time_stamp_ende - time_stamp_start;

    FkLog.wl( "" );
    FkLog.wl( "Millisekunden " + FkString.right( "          " + zeit_differenz_millisekunden, 10 ) );
    FkLog.wl( "" );
    FkLog.wl( "ENDE GenXmlCalcVorgabenBsv" );
    FkLog.wl( "" );

    System.exit( 0 );
  }

  private static String getHtmlGeruest( String pInhalt, String pBodyStyle )
  {
    /*
     * FkDatei.schreibeDatei( "c:/Daten/test_seite.html", FkHtml.getHtmlGeruest( "Inhalt" ) );
     *
     * ---------------------------------------------------------------------------------------------
     *
     * String html_table = RenderHtmlAusgabe.getHtmlTabelle();
     *
     * String html_seite = FkHtml.getHtmlGeruest( html_table );
     * 
     * FkDatei.schreibeDatei( "c:/Daten/test_seite.html", html_seite );
     */

    NewLineStringBuffer ergebnis_html = new NewLineStringBuffer();

    ergebnis_html.append( "<html>" );
    ergebnis_html.append( "<meta http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\">" );
    ergebnis_html.append( "<meta name=\"referrer\" content=\"no-referrer\">" );
    ergebnis_html.append( "<head>" );
    ergebnis_html.append( "<title>Berechnungen</title>" );
    ergebnis_html.append( "</head>" );
    ergebnis_html.append( "<link href=\"pCssDatei.css\" rel=\"stylesheet\" type=\"text/css\" />" );

    if ( pBodyStyle == null )
    {
      ergebnis_html.append( "<body>" );
    }
    else
    {
      ergebnis_html.append( "<body " + pBodyStyle + " >" );
    }

    /*
     * Seiteninhalt hinzufuegen
     */
    ergebnis_html.append( pInhalt == null ? "inhalt" : pInhalt );

    ergebnis_html.append( "</body>" );
    ergebnis_html.append( "</html>" );

    /*
     * Die erstellte HTML-Seite zurueckgeben.
     */
    return ergebnis_html.toString();
  }

  public static CalcVorgabenBsv getTestVorgabenBestandsvertrag()
  {
    Properties prop = getTestPropertiesCalcVorgabenBsvBestandsvertrag();

    CalcVorgabenBsv c_voo = PropertieParserCalcVorgabenBsv.parseCalcVorgabenBsv( prop );

    return c_voo;
  }

  /**
   * @return eine Instanz der Klasse "Properties" mit den Testdaten
   */
  public static Properties getTestPropertiesCalcVorgabenBsvBestandsvertrag()
  {
    long datum_vertragsbeginn = 20200101;
    long datum_berechnungsbeginn = FkDatumLong.getLongNaechstenMonat( datum_vertragsbeginn, -1 );
    long datum_eigene_zahlung = FkDatumLong.longAddMonat( datum_berechnungsbeginn, 12 * 6, -1 );

    String calc_vorgaben_bsv_rk_input_knz_berechnungsziel = "LAUFZEIT";// "BAUSPARSUMME"; //"SPARRATE";

    calc_vorgaben_bsv_rk_input_knz_berechnungsziel = "SPARRATE";

    Properties properties_berechnung = new Properties();

    String debug_knz_akt_element = "true";
    String debug_knz_ansp_berechnung = "true";
    String debug_knz_stichtage = "true";
    String debug_knz_wop_berechnung = "true";
    String debug_knz_zins_berechnung_bsd = "true";
    String debug_knz_zins_berechnung_spar = "true";

    String calc_vorgaben_knz_kfg_sparphase_beruecksichtigen = "false";
    String calc_vorgaben_knz_kfg_darlehensphase_beruecksichtigen = "false";

    calc_vorgaben_knz_kfg_sparphase_beruecksichtigen = "true";
    calc_vorgaben_knz_kfg_darlehensphase_beruecksichtigen = "true";

    String calc_vorgaben_bsv_rk_input_datum_vertragsbeginn = "" + datum_vertragsbeginn;
    String calc_vorgaben_bsv_rk_input_betrag_bausparsumme = "120000.0";
    String calc_vorgaben_bsv_rk_input_bestehendes_guthaben = "1234.50";
    String calc_vorgaben_bsv_rk_input_bestehende_bwz = "1.0";

    String calc_vorgaben_bsv_rk_input_wop_speicher = "0.0";
    String calc_vorgaben_bsv_rk_input_wop_datum_ablauf_bindefrist = "20180201";
    String calc_vorgaben_bsv_rk_input_datum_berechnungsbeginn = "" + datum_berechnungsbeginn;
    String calc_vorgaben_bsv_rk_input_laufzeit_in_monaten = "140";
    String calc_vorgaben_bsv_rk_input_knz_ansp_berechnen = "false";
    String calc_vorgaben_bsv_rk_input_knz_darlehensverzicht = "false";
    String calc_vorgaben_bsv_rk_input_knz_kfg_beruecksichtigen = "true";
    String calc_vorgaben_bsv_rk_input_ag_knz_beruecksichtigen = "true";
    String calc_vorgaben_bsv_rk_input_ag_betrag_vorgabe = "";
    String calc_vorgaben_bsv_rk_input_ag_art_begleichung = "";

    String calc_vorgaben_bsv_rk_input_knz_sparstopp = "MINDEST_ZTV"; // MINDEST_SPARGUTHABEN, ZUTEILUNG

//    calc_vorgaben_bsv_rk_input_knz_sparstopp =  "MINDEST_SPARGUTHABEN";//, ZUTEILUNG
//
//    calc_vorgaben_bsv_rk_input_knz_sparstopp = "ZUTEILUNG";

    String calc_vorgaben_bsv_rk_input_proz_mehrzuteilung = "125.0";
    String calc_vorgaben_bsv_rk_input_knz_wop_berechnen = "true";
    String calc_vorgaben_bsv_rk_input_knz_wop_junge_leute_r = "true";
    String calc_vorgaben_bsv_rk_input_knz_wop_verheiratet = "true";
    String calc_vorgaben_bsv_rk_input_vorgabe_ztb_betrag = "0.0d";
    String calc_vorgaben_bsv_rk_input_vorgabe_ztb_art = "TARIF";
    String calc_vorgaben_bsv_rk_input_vorgabe_ztb_optimierung = "false";
    String calc_vorgaben_bsv_rk_input_vorgabe_vertragsart = "BESTANDSVERTRAG";

    calc_vorgaben_bsv_rk_input_vorgabe_vertragsart = "NEUVERTRAG";

    String rk_input_zahlung_sparbeitrag_id = "Z10";
    String rk_input_zahlung_sparbeitrag_knz_aufnahme = "1";
    String rk_input_zahlung_sparbeitrag_bezeichnung = "Zahlung Sparbeitrag";
    String rk_input_zahlung_sparbeitrag_betrag = "1000.00";
    String rk_input_zahlung_sparbeitrag_rhythmus = CalcKonstanten.STR_RHYTHMUS_MONATLICH;
    String rk_input_zahlung_sparbeitrag_datum_von = FkDatumLong.parseToString(  "" + datum_eigene_zahlung );
    String rk_input_zahlung_sparbeitrag_datum_bis = "";
    String rk_input_zahlung_sparbeitrag_knz_tag = "1";

    String rk_input_zahlung_vl_as_id = "Z20";
    String rk_input_zahlung_vl_as_knz_aufnahme = "1";
    String rk_input_zahlung_vl_as_bezeichnung = "Zahlung VL Antragsteller";
    String rk_input_zahlung_vl_as_betrag = "40.00";
    String rk_input_zahlung_vl_as_rhythmus = CalcKonstanten.STR_RHYTHMUS_MONATLICH;
    String rk_input_zahlung_vl_as_datum_von = FkDatumLong.parseToString( "" + datum_eigene_zahlung );
    String rk_input_zahlung_vl_as_datum_bis = "";
    String rk_input_zahlung_vl_as_knz_tag = "1";

    String rk_input_zahlung_vl_ep_id = "Z30";
    String rk_input_zahlung_vl_ep_knz_aufnahme = "1";
    String rk_input_zahlung_vl_ep_bezeichnung = "Zahlung VL Ehepartner";
    String rk_input_zahlung_vl_ep_betrag = "30.00";
    String rk_input_zahlung_vl_ep_rhythmus = CalcKonstanten.STR_RHYTHMUS_MONATLICH;
    String rk_input_zahlung_vl_ep_datum_von = FkDatumLong.parseToString( "" + datum_eigene_zahlung );
    String rk_input_zahlung_vl_ep_datum_bis = "";
    String rk_input_zahlung_vl_ep_knz_tag = "1";

    String rk_input_zahlung_sz_1_id = "Z40";
    String rk_input_zahlung_sz_1_knz_aufnahme = "1";
    String rk_input_zahlung_sz_1_bezeichnung = "Zahlung Sonder 1";
    String rk_input_zahlung_sz_1_betrag = "50.00";
    String rk_input_zahlung_sz_1_rhythmus = CalcKonstanten.STR_RHYTHMUS_MONATLICH;

    long datum_von = FkDatumLong.longAddMonat( datum_berechnungsbeginn, 12, -1 );
    long datum_bis = FkDatumLong.longAddMonat( datum_von, 12, -1 );

    String rk_input_zahlung_sz_1_datum_von = "" + datum_von;
    String rk_input_zahlung_sz_1_datum_bis = "" + datum_bis;
    String rk_input_zahlung_sz_1_knz_tag = "1";

    String rk_input_zahlung_sz_2_id = "Z50";
    String rk_input_zahlung_sz_2_knz_aufnahme = "0";
    String rk_input_zahlung_sz_2_bezeichnung = "Zahlung Sonder 2";
    String rk_input_zahlung_sz_2_betrag = "234.00";
    String rk_input_zahlung_sz_2_rhythmus = CalcKonstanten.STR_RHYTHMUS_HALBJAEHRLICH;

    datum_von = FkDatumLong.longAddMonat( datum_berechnungsbeginn, 12 * 4, -1 );
    datum_bis = FkDatumLong.longAddMonat( datum_von, 18, -1 );

    String rk_input_zahlung_sz_2_datum_von = "" + datum_von;
    String rk_input_zahlung_sz_2_datum_bis = "" + datum_bis;
    String rk_input_zahlung_sz_2_knz_tag = "1";

    properties_berechnung.setProperty( "rk_input_datum_vertragsbeginn", calc_vorgaben_bsv_rk_input_datum_vertragsbeginn );
    properties_berechnung.setProperty( "rk_input_bestehendes_guthaben", calc_vorgaben_bsv_rk_input_bestehendes_guthaben );
    properties_berechnung.setProperty( "rk_input_bestehende_bwz", calc_vorgaben_bsv_rk_input_bestehende_bwz );
    //properties_berechnung.setProperty( "rk_input_bestehende_bss", calc_vorgaben_bsv_rk_input_bestehende_bss );
    properties_berechnung.setProperty( "rk_input_wop_speicher", calc_vorgaben_bsv_rk_input_wop_speicher );
    properties_berechnung.setProperty( "rk_input_wop_datum_ablauf_bindefrist", calc_vorgaben_bsv_rk_input_wop_datum_ablauf_bindefrist );
    properties_berechnung.setProperty( "rk_input_datum_berechnungsbeginn", calc_vorgaben_bsv_rk_input_datum_berechnungsbeginn );
    properties_berechnung.setProperty( "rk_input_laufzeit_in_monaten", calc_vorgaben_bsv_rk_input_laufzeit_in_monaten );
    properties_berechnung.setProperty( "rk_input_knz_ansp_berechnen", calc_vorgaben_bsv_rk_input_knz_ansp_berechnen );
    properties_berechnung.setProperty( "rk_input_knz_darlehensverzicht", calc_vorgaben_bsv_rk_input_knz_darlehensverzicht );
    properties_berechnung.setProperty( "rk_input_knz_kfg_beruecksichtigen", calc_vorgaben_bsv_rk_input_knz_kfg_beruecksichtigen );
    properties_berechnung.setProperty( "rk_input_ag_knz_beruecksichtigen", calc_vorgaben_bsv_rk_input_ag_knz_beruecksichtigen );
    properties_berechnung.setProperty( "rk_input_ag_betrag_vorgabe", calc_vorgaben_bsv_rk_input_ag_betrag_vorgabe );
    properties_berechnung.setProperty( "rk_input_ag_art_begleichung", calc_vorgaben_bsv_rk_input_ag_art_begleichung );
    properties_berechnung.setProperty( "rk_input_knz_berechnungsziel", calc_vorgaben_bsv_rk_input_knz_berechnungsziel );
    properties_berechnung.setProperty( "rk_input_knz_sparstopp", calc_vorgaben_bsv_rk_input_knz_sparstopp );
    properties_berechnung.setProperty( "rk_input_betrag_bausparsumme", calc_vorgaben_bsv_rk_input_betrag_bausparsumme );
    properties_berechnung.setProperty( "rk_input_proz_mehrzuteilung", calc_vorgaben_bsv_rk_input_proz_mehrzuteilung );
    properties_berechnung.setProperty( "rk_input_knz_wop_berechnen", calc_vorgaben_bsv_rk_input_knz_wop_berechnen );
    properties_berechnung.setProperty( "rk_input_knz_wop_junge_leute_r", calc_vorgaben_bsv_rk_input_knz_wop_junge_leute_r );
    properties_berechnung.setProperty( "rk_input_knz_wop_verheiratet", calc_vorgaben_bsv_rk_input_knz_wop_verheiratet );
    properties_berechnung.setProperty( "rk_input_vorgabe_ztb_betrag", calc_vorgaben_bsv_rk_input_vorgabe_ztb_betrag );
    properties_berechnung.setProperty( "rk_input_vorgabe_ztb_art", calc_vorgaben_bsv_rk_input_vorgabe_ztb_art );
    properties_berechnung.setProperty( "rk_input_vorgabe_ztb_optimierung", calc_vorgaben_bsv_rk_input_vorgabe_ztb_optimierung );
    properties_berechnung.setProperty( "rk_input_vorgabe_vertragsart", calc_vorgaben_bsv_rk_input_vorgabe_vertragsart );

    properties_berechnung.setProperty( "rk_input_zahlung_sparbeitrag_id", rk_input_zahlung_sparbeitrag_id );
    properties_berechnung.setProperty( "rk_input_zahlung_sparbeitrag_knz_aufnahme", rk_input_zahlung_sparbeitrag_knz_aufnahme );
    properties_berechnung.setProperty( "rk_input_zahlung_sparbeitrag_bezeichnung", rk_input_zahlung_sparbeitrag_bezeichnung );
    properties_berechnung.setProperty( "rk_input_zahlung_sparbeitrag_betrag", rk_input_zahlung_sparbeitrag_betrag );
    properties_berechnung.setProperty( "rk_input_zahlung_sparbeitrag_rhythmus", rk_input_zahlung_sparbeitrag_rhythmus );
    properties_berechnung.setProperty( "rk_input_zahlung_sparbeitrag_datum_von", rk_input_zahlung_sparbeitrag_datum_von );
    properties_berechnung.setProperty( "rk_input_zahlung_sparbeitrag_datum_bis", rk_input_zahlung_sparbeitrag_datum_bis );
    properties_berechnung.setProperty( "rk_input_zahlung_sparbeitrag_knz_tag", rk_input_zahlung_sparbeitrag_knz_tag );

    properties_berechnung.setProperty( "rk_input_zahlung_vl_as_id", rk_input_zahlung_vl_as_id );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_as_knz_aufnahme", rk_input_zahlung_vl_as_knz_aufnahme );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_as_bezeichnung", rk_input_zahlung_vl_as_bezeichnung );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_as_betrag", rk_input_zahlung_vl_as_betrag );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_as_rhythmus", rk_input_zahlung_vl_as_rhythmus );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_as_datum_von", rk_input_zahlung_vl_as_datum_von );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_as_datum_bis", rk_input_zahlung_vl_as_datum_bis );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_as_knz_tag", rk_input_zahlung_vl_as_knz_tag );

    properties_berechnung.setProperty( "rk_input_zahlung_vl_ep_id", rk_input_zahlung_vl_ep_id );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_ep_knz_aufnahme", rk_input_zahlung_vl_ep_knz_aufnahme );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_ep_bezeichnung", rk_input_zahlung_vl_ep_bezeichnung );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_ep_betrag", rk_input_zahlung_vl_ep_betrag );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_ep_rhythmus", rk_input_zahlung_vl_ep_rhythmus );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_ep_datum_von", rk_input_zahlung_vl_ep_datum_von );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_ep_datum_bis", rk_input_zahlung_vl_ep_datum_bis );
    properties_berechnung.setProperty( "rk_input_zahlung_vl_ep_knz_tag", rk_input_zahlung_vl_ep_knz_tag );

    properties_berechnung.setProperty( "rk_input_zahlung_sz_1_id", rk_input_zahlung_sz_1_id );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_1_knz_aufnahme", rk_input_zahlung_sz_1_knz_aufnahme );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_1_bezeichnung", rk_input_zahlung_sz_1_bezeichnung );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_1_betrag", rk_input_zahlung_sz_1_betrag );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_1_rhythmus", rk_input_zahlung_sz_1_rhythmus );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_1_datum_von", rk_input_zahlung_sz_1_datum_von );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_1_datum_bis", rk_input_zahlung_sz_1_datum_bis );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_1_knz_tag", rk_input_zahlung_sz_1_knz_tag );

    properties_berechnung.setProperty( "rk_input_zahlung_sz_2_id", rk_input_zahlung_sz_2_id );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_2_knz_aufnahme", rk_input_zahlung_sz_2_knz_aufnahme );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_2_bezeichnung", rk_input_zahlung_sz_2_bezeichnung );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_2_betrag", rk_input_zahlung_sz_2_betrag );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_2_rhythmus", rk_input_zahlung_sz_2_rhythmus );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_2_datum_von", rk_input_zahlung_sz_2_datum_von );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_2_datum_bis", rk_input_zahlung_sz_2_datum_bis );
    properties_berechnung.setProperty( "rk_input_zahlung_sz_2_knz_tag", rk_input_zahlung_sz_2_knz_tag );

    properties_berechnung.setProperty( "debug_knz_akt_element", debug_knz_akt_element );
    properties_berechnung.setProperty( "debug_knz_ansp_berechnung", debug_knz_ansp_berechnung );
    properties_berechnung.setProperty( "debug_knz_stichtage", debug_knz_stichtage );
    properties_berechnung.setProperty( "debug_knz_wop_berechnung", debug_knz_wop_berechnung );
    properties_berechnung.setProperty( "debug_knz_zins_berechnung_bsd", debug_knz_zins_berechnung_bsd );
    properties_berechnung.setProperty( "debug_knz_zins_berechnung_spar", debug_knz_zins_berechnung_spar );

    properties_berechnung.setProperty( "knz_kfg_sparphase_beruecksichtigen", calc_vorgaben_knz_kfg_sparphase_beruecksichtigen );
    properties_berechnung.setProperty( "knz_kfg_darlehensphase_beruecksichtigen", calc_vorgaben_knz_kfg_darlehensphase_beruecksichtigen );

    return properties_berechnung;
  }
}
