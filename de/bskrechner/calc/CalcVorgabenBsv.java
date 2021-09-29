package de.bskrechner.calc;

import java.util.ArrayList;
import java.util.List;

import de.bskrechner.util.FkDatumLong;
import de.bskrechner.util.FkString;
import de.bskrechner.util.FkZahl;

/**
 * <pre>
 * Java-Klasse "CalcVorgabenBsv".
 * </pre>
 */
public class CalcVorgabenBsv
{
  /** 
   * @return Einen String fuer eine Uerberschriftenzeile
   */
  public static String toStringZeileUeberschrift()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( "Rk Input Datum Vertragsbeginn" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Bestehendes Guthaben" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Bestehende Bwz" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Bestehende Bss" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Wop Speicher" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Wop Datum Ablauf Bindefrist" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Datum Berechnungsbeginn" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Laufzeit In Monaten" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Knz Ansp Berechnen" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Knz Darlehensverzicht" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Knz Kfg Beruecksichtigen" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Ag Knz Beruecksichtigen" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Ag Betrag Vorgabe" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Ag Art Begleichung" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Knz Berechnungsziel" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Knz Sparstopp" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Betrag Bausparsumme" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Proz Mehrzuteilung" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Knz Wop Berechnen" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Knz Wop Junge Leute R" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Knz Wop Verheiratet" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Vorgabe Ztb Betrag" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Vorgabe Ztb Art" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Rk Input Vorgabe Ztb Optimierung" );
    ergebnis_string_buffer.append( "|" );

    return ergebnis_string_buffer.toString();
  }

  private boolean m_debug_knz_akt_element          = false; // true;

  private boolean m_debug_knz_ansp_berechnung      = false; // true;

  private boolean m_debug_knz_stichtage            = false; // true;

  private boolean m_debug_knz_wop_berechnung       = false; // true;

  private boolean m_debug_knz_zins_berechnung_bsd  = false; // true;

  private boolean m_debug_knz_zins_berechnung_spar = false; // true;

  public boolean getDebugKnzAktElement()
  {
    return m_debug_knz_akt_element;
  }

  public void setDebugKnzAktElement( boolean pDebugKnzAktElement )
  {
    m_debug_knz_akt_element = pDebugKnzAktElement;
  }

  public boolean getDebugKnzAnspBerechnung()
  {
    return m_debug_knz_ansp_berechnung;
  }

  public void setDebugKnzAnspBerechnung( boolean pDebugKnzAnspBerechnung )
  {
    m_debug_knz_ansp_berechnung = pDebugKnzAnspBerechnung;
  }

  public boolean getDebugKnzStichtage()
  {
    return m_debug_knz_stichtage;
  }

  public void setDebugKnzStichtage( boolean pDebugKnzStichtage )
  {
    m_debug_knz_stichtage = pDebugKnzStichtage;
  }

  public boolean getDebugKnzWopBerechnung()
  {
    return m_debug_knz_wop_berechnung;
  }

  public void setDebugKnzWopBerechnung( boolean pDebugKnzWopBerechnung )
  {
    m_debug_knz_wop_berechnung = pDebugKnzWopBerechnung;
  }

  public boolean getDebugKnzZinsBerechnungBsd()
  {
    return m_debug_knz_zins_berechnung_bsd;
  }

  public void setDebugKnzZinsBerechnungBsd( boolean pDebugKnzZinsBerechnungBsd )
  {
    m_debug_knz_zins_berechnung_bsd = pDebugKnzZinsBerechnungBsd;
  }

  public boolean getDebugKnzZinsBerechnungSpar()
  {
    return m_debug_knz_zins_berechnung_spar;
  }

  public void setDebugKnzZinsBerechnungSpar( boolean pDebugKnzZinsBerechnungSpar )
  {
    m_debug_knz_zins_berechnung_spar = pDebugKnzZinsBerechnungSpar;
  }

  private long    m_rk_input_datum_vertragsbeginn           = 19000101;

  private double  m_rk_input_bestehendes_guthaben           = 0.0;

  private double  m_rk_input_bestehende_bwz                 = 0.0;

  private double  m_rk_input_bestehende_bss                 = 0.0;

  private double  m_rk_input_wop_speicher                   = 0.0;

  private long    m_rk_input_wop_datum_ablauf_bindefrist    = 19000101;

  private long    m_rk_input_datum_berechnungsbeginn        = 19000101;

  private long    m_rk_input_laufzeit_in_monaten            = 0;

  private boolean m_rk_input_knz_ansp_berechnen             = true;    // false;

  private boolean m_rk_input_knz_darlehensverzicht          = true;    // false;

  private boolean m_rk_input_ag_knz_beruecksichtigen        = true;    // false;

  private String  m_rk_input_ag_betrag_vorgabe              = null;

  private String  m_rk_input_ag_art_begleichung             = null;

  private String  m_rk_input_knz_berechnungsziel            = null;

  private String  m_rk_input_knz_sparstopp                  = null;

  private double  m_rk_input_betrag_bausparsumme            = 0.0;

  private double  m_rk_input_proz_mehrzuteilung             = 0.0;

  private boolean m_rk_input_knz_wop_berechnen              = true;    // false;

  private boolean m_rk_input_knz_wop_junge_leute_r          = true;    // false;

  private boolean m_rk_input_knz_wop_verheiratet            = true;    // false;

  private double  m_rk_input_vorgabe_ztb_betrag             = 0.0;

  private String  m_rk_input_vorgabe_ztb_art                = null;

  private boolean m_rk_input_vorgabe_ztb_optimierung        = true;    // false;

  private int     m_rk_input_vorgabe_vertragsart            = 1;

  private boolean m_knz_kfg_sparphase_beruecksichtigen      = true;

  private boolean m_knz_kfg_darlehensphase_beruecksichtigen = true;

  public boolean getKnzKfgSparphaseBeruecksichtigen()
  {
    return m_knz_kfg_sparphase_beruecksichtigen;
  }

  public void setKnzKfgSparphaseBeruecksichtigen( boolean pKnzKfgSparphaseBeruecksichtigen )
  {
    m_knz_kfg_sparphase_beruecksichtigen = pKnzKfgSparphaseBeruecksichtigen;
  }

  public boolean getKnzKfgDarlehensphaseBeruecksichtigen()
  {
    return m_knz_kfg_darlehensphase_beruecksichtigen;
  }

  public void setKnzKfgDarlehensphaseBeruecksichtigen( boolean pKnzKfgDarlehensphaseBeruecksichtigen )
  {
    m_knz_kfg_darlehensphase_beruecksichtigen = pKnzKfgDarlehensphaseBeruecksichtigen;
  }

  public int getRkInputVorgabeVertragsart()
  {
    return m_rk_input_vorgabe_vertragsart;
  }

  public void setRkInputVorgabeVertragsartNeuvertrag()
  {
    m_rk_input_vorgabe_vertragsart = 1;
  }

  public void setRkInputVorgabeVertragsartBestandsvertrag()
  {
    m_rk_input_vorgabe_vertragsart = 2;
  }

  public boolean istVorgabeartNeuvertrag()
  {
    return m_rk_input_vorgabe_vertragsart == 1;
  }

  public boolean istVorgabeartBetandsvertrag()
  {
    return m_rk_input_vorgabe_vertragsart != 1;
  }

  public void setRkInputVorgabeVertragsart( int pRkInputVorgabeVertragsart )
  {
    m_rk_input_vorgabe_vertragsart = pRkInputVorgabeVertragsart;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_datum_vertragsbeginn.
   * </pre>
   * 
   * @param  pRkInputDatumVertragsbeginn der zu setzende Wert als String im Format JJJJMMTT.
   */
  public void setRkInputDatumVertragsbeginn( String pRkInputDatumVertragsbeginn )
  {
    m_rk_input_datum_vertragsbeginn = FkDatumLong.parseToLong( pRkInputDatumVertragsbeginn );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_datum_vertragsbeginn.
   * </pre>
   * 
   * @param  pRkInputDatumVertragsbeginn der zu setzende Wert
   */
  public void setRkInputDatumVertragsbeginn( long pRkInputDatumVertragsbeginn )
  {
    m_rk_input_datum_vertragsbeginn = pRkInputDatumVertragsbeginn;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_bestehendes_guthaben.
   * </pre>
   * 
   * @param  pRkInputBestehendesGuthaben der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setRkInputBestehendesGuthaben( String pRkInputBestehendesGuthaben )
  {
    m_rk_input_bestehendes_guthaben = FkZahl.getDouble( pRkInputBestehendesGuthaben, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_bestehendes_guthaben.
   * </pre>
   * 
   * @param  pRkInputBestehendesGuthaben der zu setzende Wert
   */
  public void setRkInputBestehendesGuthaben( double pRkInputBestehendesGuthaben )
  {
    m_rk_input_bestehendes_guthaben = pRkInputBestehendesGuthaben;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_bestehende_bwz.
   * </pre>
   * 
   * @param  pRkInputBestehendeBwz der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setRkInputBestehendeBwz( String pRkInputBestehendeBwz )
  {
    m_rk_input_bestehende_bwz = FkZahl.getDouble( pRkInputBestehendeBwz, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_bestehende_bwz.
   * </pre>
   * 
   * @param  pRkInputBestehendeBwz der zu setzende Wert
   */
  public void setRkInputBestehendeBwz( double pRkInputBestehendeBwz )
  {
    m_rk_input_bestehende_bwz = pRkInputBestehendeBwz;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_bestehende_bss.
   * </pre>
   * 
   * @param  pRkInputBestehendeBss der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setRkInputBestehendeBss( String pRkInputBestehendeBss )
  {
    m_rk_input_bestehende_bss = FkZahl.getDouble( pRkInputBestehendeBss, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_bestehende_bss.
   * </pre>
   * 
   * @param  pRkInputBestehendeBss der zu setzende Wert
   */
  public void setRkInputBestehendeBss( double pRkInputBestehendeBss )
  {
    m_rk_input_bestehende_bss = pRkInputBestehendeBss;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_wop_speicher.
   * </pre>
   * 
   * @param  pRkInputWopSpeicher der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setRkInputWopSpeicher( String pRkInputWopSpeicher )
  {
    m_rk_input_wop_speicher = FkZahl.getDouble( pRkInputWopSpeicher, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_wop_speicher.
   * </pre>
   * 
   * @param  pRkInputWopSpeicher der zu setzende Wert
   */
  public void setRkInputWopSpeicher( double pRkInputWopSpeicher )
  {
    m_rk_input_wop_speicher = pRkInputWopSpeicher;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_wop_datum_ablauf_bindefrist.
   * </pre>
   * 
   * @param  pRkInputWopDatumAblaufBindefrist der zu setzende Wert als String im Format JJJJMMTT.
   */
  public void setRkInputWopDatumAblaufBindefrist( String pRkInputWopDatumAblaufBindefrist )
  {
    m_rk_input_wop_datum_ablauf_bindefrist = FkDatumLong.parseToLong( pRkInputWopDatumAblaufBindefrist );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_wop_datum_ablauf_bindefrist.
   * </pre>
   * 
   * @param  pRkInputWopDatumAblaufBindefrist der zu setzende Wert
   */
  public void setRkInputWopDatumAblaufBindefrist( long pRkInputWopDatumAblaufBindefrist )
  {
    m_rk_input_wop_datum_ablauf_bindefrist = pRkInputWopDatumAblaufBindefrist;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_datum_berechnungsbeginn.
   * </pre>
   * 
   * @param  pRkInputDatumBerechnungsbeginn der zu setzende Wert als String im Format JJJJMMTT.
   */
  public void setRkInputDatumBerechnungsbeginn( String pRkInputDatumBerechnungsbeginn )
  {
    m_rk_input_datum_berechnungsbeginn = FkDatumLong.parseToLong( pRkInputDatumBerechnungsbeginn );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_datum_berechnungsbeginn.
   * </pre>
   * 
   * @param  pRkInputDatumBerechnungsbeginn der zu setzende Wert
   */
  public void setRkInputDatumBerechnungsbeginn( long pRkInputDatumBerechnungsbeginn )
  {
    m_rk_input_datum_berechnungsbeginn = pRkInputDatumBerechnungsbeginn;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_laufzeit_in_monaten.
   * </pre>
   * 
   * @param  pRkInputLaufzeitInMonaten der zu setzende Wert als String (wird zu int gewandelt)
   */
  public void setRkInputLaufzeitInMonaten( long pRkInputLaufzeitInMonaten )
  {
    m_rk_input_laufzeit_in_monaten = pRkInputLaufzeitInMonaten;
  }

  public void setRkInputLaufzeitInMonaten( String pRkInputLaufzeitInMonaten )
  {
    m_rk_input_laufzeit_in_monaten = FkZahl.getLong( pRkInputLaufzeitInMonaten, 0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_laufzeit_in_monaten.
   * </pre>
   * 
   * @param  pRkInputLaufzeitInMonaten der zu setzende Wert
   */
  public void setRkInputLaufzeitInMonaten( int pRkInputLaufzeitInMonaten )
  {
    m_rk_input_laufzeit_in_monaten = pRkInputLaufzeitInMonaten;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_ansp_berechnen.
   * </pre>
   * 
   * @param  pRkInputKnzAnspBerechnen der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setRkInputKnzAnspBerechnen( String pRkInputKnzAnspBerechnen )
  {
    m_rk_input_knz_ansp_berechnen = FkString.getBoolean( pRkInputKnzAnspBerechnen, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_ansp_berechnen.
   * </pre>
   * 
   * @param  pRkInputKnzAnspBerechnen der zu setzende Wert
   */
  public void setRkInputKnzAnspBerechnen( boolean pRkInputKnzAnspBerechnen )
  {
    m_rk_input_knz_ansp_berechnen = pRkInputKnzAnspBerechnen;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_darlehensverzicht.
   * </pre>
   * 
   * @param  pRkInputKnzDarlehensverzicht der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setRkInputKnzDarlehensverzicht( String pRkInputKnzDarlehensverzicht )
  {
    m_rk_input_knz_darlehensverzicht = FkString.getBoolean( pRkInputKnzDarlehensverzicht, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_darlehensverzicht.
   * </pre>
   * 
   * @param  pRkInputKnzDarlehensverzicht der zu setzende Wert
   */
  public void setRkInputKnzDarlehensverzicht( boolean pRkInputKnzDarlehensverzicht )
  {
    m_rk_input_knz_darlehensverzicht = pRkInputKnzDarlehensverzicht;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_ag_knz_beruecksichtigen.
   * </pre>
   * 
   * @param  pRkInputAgKnzBeruecksichtigen der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setRkInputAgKnzBeruecksichtigen( String pRkInputAgKnzBeruecksichtigen )
  {
    m_rk_input_ag_knz_beruecksichtigen = FkString.getBoolean( pRkInputAgKnzBeruecksichtigen, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_ag_knz_beruecksichtigen.
   * </pre>
   * 
   * @param  pRkInputAgKnzBeruecksichtigen der zu setzende Wert
   */
  public void setRkInputAgKnzBeruecksichtigen( boolean pRkInputAgKnzBeruecksichtigen )
  {
    m_rk_input_ag_knz_beruecksichtigen = pRkInputAgKnzBeruecksichtigen;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_ag_betrag_vorgabe.
   * </pre>
   * 
   * @param  pRkInputAgBetragVorgabe der zu setzende Wert
   */
  public void setRkInputAgBetragVorgabe( String pRkInputAgBetragVorgabe )
  {
    m_rk_input_ag_betrag_vorgabe = pRkInputAgBetragVorgabe;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_ag_art_begleichung.
   * </pre>
   * 
   * @param  pRkInputAgArtBegleichung der zu setzende Wert
   */
  public void setRkInputAgArtBegleichung( String pRkInputAgArtBegleichung )
  {
    m_rk_input_ag_art_begleichung = pRkInputAgArtBegleichung;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_berechnungsziel.
   * </pre>
   * 
   * @param  pRkInputKnzBerechnungsziel der zu setzende Wert
   */
  public void setRkInputKnzBerechnungsziel( String pRkInputKnzBerechnungsziel )
  {
    m_rk_input_knz_berechnungsziel = pRkInputKnzBerechnungsziel;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_sparstopp.
   * </pre>
   * 
   * @param  pRkInputKnzSparstopp der zu setzende Wert
   */
  public void setRkInputKnzSparstopp( String pRkInputKnzSparstopp )
  {
    m_rk_input_knz_sparstopp = FkString.ucase( pRkInputKnzSparstopp );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_betrag_bausparsumme.
   * </pre>
   * 
   * @param  pRkInputBetragBausparsumme der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setRkInputBetragBausparsumme( String pRkInputBetragBausparsumme )
  {
    m_rk_input_betrag_bausparsumme = FkZahl.getDouble( pRkInputBetragBausparsumme, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_betrag_bausparsumme.
   * </pre>
   * 
   * @param  pRkInputBetragBausparsumme der zu setzende Wert
   */
  public void setRkInputBetragBausparsumme( double pRkInputBetragBausparsumme )
  {
    m_rk_input_betrag_bausparsumme = pRkInputBetragBausparsumme;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_proz_mehrzuteilung.
   * </pre>
   * 
   * @param  pRkInputProzMehrzuteilung der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setRkInputProzMehrzuteilung( String pRkInputProzMehrzuteilung )
  {
    m_rk_input_proz_mehrzuteilung = FkZahl.getDouble( pRkInputProzMehrzuteilung, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_proz_mehrzuteilung.
   * </pre>
   * 
   * @param  pRkInputProzMehrzuteilung der zu setzende Wert
   */
  public void setRkInputProzMehrzuteilung( double pRkInputProzMehrzuteilung )
  {
    m_rk_input_proz_mehrzuteilung = pRkInputProzMehrzuteilung;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_wop_berechnen.
   * </pre>
   * 
   * @param  pRkInputKnzWopBerechnen der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setRkInputKnzWopBerechnen( String pRkInputKnzWopBerechnen )
  {
    m_rk_input_knz_wop_berechnen = FkString.getBoolean( pRkInputKnzWopBerechnen, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_wop_berechnen.
   * </pre>
   * 
   * @param  pRkInputKnzWopBerechnen der zu setzende Wert
   */
  public void setRkInputKnzWopBerechnen( boolean pRkInputKnzWopBerechnen )
  {
    m_rk_input_knz_wop_berechnen = pRkInputKnzWopBerechnen;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_wop_junge_leute_r.
   * </pre>
   * 
   * @param  pRkInputKnzWopJungeLeuteR der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setRkInputKnzWopJungeLeuteR( String pRkInputKnzWopJungeLeuteR )
  {
    m_rk_input_knz_wop_junge_leute_r = FkString.getBoolean( pRkInputKnzWopJungeLeuteR, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_wop_junge_leute_r.
   * </pre>
   * 
   * @param  pRkInputKnzWopJungeLeuteR der zu setzende Wert
   */
  public void setRkInputKnzWopJungeLeuteR( boolean pRkInputKnzWopJungeLeuteR )
  {
    m_rk_input_knz_wop_junge_leute_r = pRkInputKnzWopJungeLeuteR;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_wop_verheiratet.
   * </pre>
   * 
   * @param  pRkInputKnzWopVerheiratet der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setRkInputKnzWopVerheiratet( String pRkInputKnzWopVerheiratet )
  {
    m_rk_input_knz_wop_verheiratet = FkString.getBoolean( pRkInputKnzWopVerheiratet, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_knz_wop_verheiratet.
   * </pre>
   * 
   * @param  pRkInputKnzWopVerheiratet der zu setzende Wert
   */
  public void setRkInputKnzWopVerheiratet( boolean pRkInputKnzWopVerheiratet )
  {
    m_rk_input_knz_wop_verheiratet = pRkInputKnzWopVerheiratet;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_vorgabe_ztb_betrag.
   * </pre>
   * 
   * @param  pRkInputVorgabeZtbBetrag der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setRkInputVorgabeZtbBetrag( String pRkInputVorgabeZtbBetrag )
  {
    m_rk_input_vorgabe_ztb_betrag = FkZahl.getDouble( pRkInputVorgabeZtbBetrag, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_vorgabe_ztb_betrag.
   * </pre>
   * 
   * @param  pRkInputVorgabeZtbBetrag der zu setzende Wert
   */
  public void setRkInputVorgabeZtbBetrag( double pRkInputVorgabeZtbBetrag )
  {
    m_rk_input_vorgabe_ztb_betrag = pRkInputVorgabeZtbBetrag;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_vorgabe_ztb_art.
   * </pre>
   * 
   * @param  pRkInputVorgabeZtbArt der zu setzende Wert
   */
  public void setRkInputVorgabeZtbArt( String pRkInputVorgabeZtbArt )
  {
    m_rk_input_vorgabe_ztb_art = pRkInputVorgabeZtbArt;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_vorgabe_ztb_optimierung.
   * </pre>
   * 
   * @param  pRkInputVorgabeZtbOptimierung der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setRkInputVorgabeZtbOptimierung( String pRkInputVorgabeZtbOptimierung )
  {
    m_rk_input_vorgabe_ztb_optimierung = FkString.getBoolean( pRkInputVorgabeZtbOptimierung, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rk_input_vorgabe_ztb_optimierung.
   * </pre>
   * 
   * @param  pRkInputVorgabeZtbOptimierung der zu setzende Wert
   */
  public void setRkInputVorgabeZtbOptimierung( boolean pRkInputVorgabeZtbOptimierung )
  {
    m_rk_input_vorgabe_ztb_optimierung = pRkInputVorgabeZtbOptimierung;
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_rk_input_datum_vertragsbeginn" das Datum im Format JJJJMMTT im Datentyp "long" zurueck.
   *  
   * Liefert den Wert der Variablen m_rk_input_datum_vertragsbeginn.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_datum_vertragsbeginn" im Format JJJJMMTT 
   *  
   */
  public long getRkInputDatumVertragsbeginn()
  {
    return m_rk_input_datum_vertragsbeginn;
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_rk_input_datum_vertragsbeginn" das Datum im Format TT.MM.JJJJ zurueck.
   * </pre>
   *  
   * @return das Datum der Variablen "m_rk_input_datum_vertragsbeginn" im Format TT.MM.JJJJ
   */
  public String getStringRkInputDatumVertragsbeginn()
  {
    return FkDatumLong.getDatumStringAusLong( m_rk_input_datum_vertragsbeginn );
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_bestehendes_guthaben.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_bestehendes_guthaben"
   */
  public double getRkInputBestehendesGuthaben()
  {
    return m_rk_input_bestehendes_guthaben;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_bestehende_bwz.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_bestehende_bwz"
   */
  public double getRkInputBestehendeBwz()
  {
    return m_rk_input_bestehende_bwz;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_wop_speicher.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_wop_speicher"
   */
  public double getRkInputWopSpeicher()
  {
    return m_rk_input_wop_speicher;
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_rk_input_wop_datum_ablauf_bindefrist" das Datum im Format JJJJMMTT im Datentyp "long" zurueck.
   *  
   * Liefert den Wert der Variablen m_rk_input_wop_datum_ablauf_bindefrist.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_wop_datum_ablauf_bindefrist" im Format JJJJMMTT 
   *  
   */
  public long getRkInputWopDatumAblaufBindefrist()
  {
    return m_rk_input_wop_datum_ablauf_bindefrist;
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_rk_input_wop_datum_ablauf_bindefrist" das Datum im Format TT.MM.JJJJ zurueck.
   * </pre>
   *  
   * @return das Datum der Variablen "m_rk_input_wop_datum_ablauf_bindefrist" im Format TT.MM.JJJJ
   */
  public String getStringRkInputWopDatumAblaufBindefrist()
  {
    return FkDatumLong.getDatumStringAusLong( m_rk_input_wop_datum_ablauf_bindefrist );
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_rk_input_datum_berechnungsbeginn" das Datum im Format JJJJMMTT im Datentyp "long" zurueck.
   *  
   * Liefert den Wert der Variablen m_rk_input_datum_berechnungsbeginn.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_datum_berechnungsbeginn" im Format JJJJMMTT 
   *  
   */
  public long getRkInputDatumBerechnungsbeginn()
  {
    return m_rk_input_datum_berechnungsbeginn;
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_rk_input_datum_berechnungsbeginn" das Datum im Format TT.MM.JJJJ zurueck.
   * </pre>
   *  
   * @return das Datum der Variablen "m_rk_input_datum_berechnungsbeginn" im Format TT.MM.JJJJ
   */
  public String getStringRkInputDatumBerechnungsbeginn()
  {
    return FkDatumLong.getDatumStringAusLong( m_rk_input_datum_berechnungsbeginn );
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_laufzeit_in_monaten.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_laufzeit_in_monaten"
   */
  public long getRkInputLaufzeitInMonaten()
  {
    return m_rk_input_laufzeit_in_monaten;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_knz_ansp_berechnen.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_knz_ansp_berechnen"
   */
  public boolean getRkInputKnzAnspBerechnen()
  {
    return m_rk_input_knz_ansp_berechnen;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_knz_darlehensverzicht.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_knz_darlehensverzicht"
   */
  public boolean getRkInputKnzDarlehensverzicht()
  {
    return m_rk_input_knz_darlehensverzicht;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_ag_knz_beruecksichtigen.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_ag_knz_beruecksichtigen"
   */
  public boolean getRkInputAgKnzBeruecksichtigen()
  {
    return m_rk_input_ag_knz_beruecksichtigen;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_ag_betrag_vorgabe.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_ag_betrag_vorgabe"
   */
  public String getRkInputAgBetragVorgabe()
  {
    return m_rk_input_ag_betrag_vorgabe;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_ag_art_begleichung.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_ag_art_begleichung"
   */
  public String getRkInputAgArtBegleichung()
  {
    return m_rk_input_ag_art_begleichung;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_knz_berechnungsziel.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_knz_berechnungsziel"
   */
  public String getRkInputKnzBerechnungsziel()
  {
    return m_rk_input_knz_berechnungsziel;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_knz_sparstopp.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_knz_sparstopp"
   */
  public String getRkInputKnzSparstopp()
  {
    return m_rk_input_knz_sparstopp;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_betrag_bausparsumme.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_betrag_bausparsumme"
   */
  public double getRkInputBetragBausparsumme()
  {
    return m_rk_input_betrag_bausparsumme;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_proz_mehrzuteilung.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_proz_mehrzuteilung"
   */
  public double getRkInputProzMehrzuteilung()
  {
    return m_rk_input_proz_mehrzuteilung;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_knz_wop_berechnen.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_knz_wop_berechnen"
   */
  public boolean getRkInputKnzWopBerechnen()
  {
    return m_rk_input_knz_wop_berechnen;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_knz_wop_junge_leute_r.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_knz_wop_junge_leute_r"
   */
  public boolean getRkInputKnzWopJungeLeuteR()
  {
    return m_rk_input_knz_wop_junge_leute_r;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_knz_wop_verheiratet.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_knz_wop_verheiratet"
   */
  public boolean getRkInputKnzWopVerheiratet()
  {
    return m_rk_input_knz_wop_verheiratet;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_vorgabe_ztb_betrag.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_vorgabe_ztb_betrag"
   */
  public double getRkInputVorgabeZtbBetrag()
  {
    return m_rk_input_vorgabe_ztb_betrag;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_vorgabe_ztb_art.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_vorgabe_ztb_art"
   */
  public String getRkInputVorgabeZtbArt()
  {
    return m_rk_input_vorgabe_ztb_art;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rk_input_vorgabe_ztb_optimierung.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rk_input_vorgabe_ztb_optimierung"
   */
  public boolean getRkInputVorgabeZtbOptimierung()
  {
    return m_rk_input_vorgabe_ztb_optimierung;
  }

  /**
   * @param  pID die zu pruefende ID
   * @return  TRUE wenn die Instanz der ID entspricht, sonst FALSE
   */
  public boolean isID( String pID )
  {
    if ( pID != null )
    {
      return pID.compareTo( "ID_VARIABLE_EINFUEGEN" ) == 0;
    }

    return false;
  }

  /**
   * <pre>
   * Liefert einen String entsprechend der Umsetzungstabelle zurueck.
   * 
   *      0 = Rk Input Datum Vertragsbeginn
   *      1 = Rk Input Bestehendes Guthaben
   *      2 = Rk Input Bestehende Bwz
   *      3 = Rk Input Bestehende Bss
   *      4 = Rk Input Wop Speicher
   *      5 = Rk Input Wop Datum Ablauf Bindefrist
   *      6 = Rk Input Datum Berechnungsbeginn
   *      7 = Rk Input Laufzeit In Monaten
   *      8 = Rk Input Knz Ansp Berechnen
   *      9 = Rk Input Knz Darlehensverzicht
   *     10 = Rk Input Knz Kfg Beruecksichtigen
   *     11 = Rk Input Ag Knz Beruecksichtigen
   *     12 = Rk Input Ag Betrag Vorgabe
   *     13 = Rk Input Ag Art Begleichung
   *     14 = Rk Input Knz Berechnungsziel
   *     15 = Rk Input Knz Sparstopp
   *     16 = Rk Input Betrag Bausparsumme
   *     17 = Rk Input Proz Mehrzuteilung
   *     18 = Rk Input Knz Wop Berechnen
   *     19 = Rk Input Knz Wop Junge Leute R
   *     20 = Rk Input Knz Wop Verheiratet
   *     21 = Rk Input Vorgabe Ztb Betrag
   *     22 = Rk Input Vorgabe Ztb Art
   *     23 = Rk Input Vorgabe Ztb Optimierung
   * 
   * </pre>
   * 
   * @param  pAbfrageIndex  identifiziert den abzufragenden Wert
   * 
   * @return die Stringrepraesentation des abgefragten Strings, oder null, wenn der AbfrageIndex nicht existiert
   */
  public String getString( int pAbfrageIndex )
  {
    switch ( pAbfrageIndex )
    {
      case 0 :
        return "" + getRkInputDatumVertragsbeginn();

      case 1 :
        return "" + getRkInputBestehendesGuthaben();

      case 2 :
        return "" + getRkInputBestehendeBwz();

      case 3 :
        return "--";

      case 4 :
        return "" + getRkInputWopSpeicher();

      case 5 :
        return "" + getRkInputWopDatumAblaufBindefrist();

      case 6 :
        return "" + getRkInputDatumBerechnungsbeginn();

      case 7 :
        return "" + getRkInputLaufzeitInMonaten();

      case 8 :
        return "" + getRkInputKnzAnspBerechnen();

      case 9 :
        return "" + getRkInputKnzDarlehensverzicht();

      case 10 :
        return "" + getKnzKfgSparphaseBeruecksichtigen();

      case 11 :
        return "" + getRkInputAgKnzBeruecksichtigen();

      case 12 :
        return getRkInputAgBetragVorgabe();

      case 13 :
        return getRkInputAgArtBegleichung();

      case 14 :
        return getRkInputKnzBerechnungsziel();

      case 15 :
        return getRkInputKnzSparstopp();

      case 16 :
        return "" + getRkInputBetragBausparsumme();

      case 17 :
        return "" + getRkInputProzMehrzuteilung();

      case 18 :
        return "" + getRkInputKnzWopBerechnen();

      case 19 :
        return "" + getRkInputKnzWopJungeLeuteR();

      case 20 :
        return "" + getRkInputKnzWopVerheiratet();

      case 21 :
        return "" + getRkInputVorgabeZtbBetrag();

      case 22 :
        return getRkInputVorgabeZtbArt();

      case 23 :
        return "" + getRkInputVorgabeZtbOptimierung();
    }

    return null;
  }

  /**
   * Erstellt die Stringrepraesentation der Klasse
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcVorgabenBsv"
   */
  public String toString()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( "\nKlasse CalcVorgabenBsv" );

    ergebnis_string_buffer.append( "\n + m_rk_input_datum_vertragsbeginn        >" + m_rk_input_datum_vertragsbeginn + "<  = >" + getStringRkInputDatumVertragsbeginn() + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_bestehendes_guthaben        >" + m_rk_input_bestehendes_guthaben + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_bestehende_bwz              >" + m_rk_input_bestehende_bwz + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_bestehende_bss              >" + m_rk_input_bestehende_bss + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_wop_speicher                >" + m_rk_input_wop_speicher + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_wop_datum_ablauf_bindefrist >" + m_rk_input_wop_datum_ablauf_bindefrist + "<  = >" + getStringRkInputWopDatumAblaufBindefrist() + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_datum_berechnungsbeginn     >" + m_rk_input_datum_berechnungsbeginn + "<  = >" + getStringRkInputDatumBerechnungsbeginn() + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_laufzeit_in_monaten         >" + m_rk_input_laufzeit_in_monaten + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_knz_ansp_berechnen          >" + m_rk_input_knz_ansp_berechnen + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_knz_darlehensverzicht       >" + m_rk_input_knz_darlehensverzicht + "<" );
    ergebnis_string_buffer.append( "\n + m_knz_kfg_sparphase_beruecksichtigen =>" + m_knz_kfg_sparphase_beruecksichtigen + "<" );
    ergebnis_string_buffer.append( "\n + m_knz_kfg_darlehensphase_beruecksichtigen =>" + m_knz_kfg_darlehensphase_beruecksichtigen + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_ag_knz_beruecksichtigen     >" + m_rk_input_ag_knz_beruecksichtigen + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_ag_betrag_vorgabe           >" + m_rk_input_ag_betrag_vorgabe + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_ag_art_begleichung          >" + m_rk_input_ag_art_begleichung + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_knz_berechnungsziel         >" + m_rk_input_knz_berechnungsziel + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_knz_sparstopp               >" + m_rk_input_knz_sparstopp + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_betrag_bausparsumme         >" + m_rk_input_betrag_bausparsumme + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_proz_mehrzuteilung          >" + m_rk_input_proz_mehrzuteilung + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_knz_wop_berechnen           >" + m_rk_input_knz_wop_berechnen + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_knz_wop_junge_leute_r       >" + m_rk_input_knz_wop_junge_leute_r + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_knz_wop_verheiratet         >" + m_rk_input_knz_wop_verheiratet + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_vorgabe_ztb_betrag          >" + m_rk_input_vorgabe_ztb_betrag + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_vorgabe_ztb_art             >" + m_rk_input_vorgabe_ztb_art + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_vorgabe_ztb_optimierung     >" + m_rk_input_vorgabe_ztb_optimierung + "<" );
    ergebnis_string_buffer.append( "\n + m_rk_input_vorgabe_ztb_optimierung     >" + m_rk_input_vorgabe_ztb_optimierung + "<" );

    ergebnis_string_buffer.append( "\n + m_debug_knz_akt_element                >" + m_debug_knz_akt_element + "<" );
    ergebnis_string_buffer.append( "\n + m_debug_knz_ansp_berechnung            >" + m_debug_knz_ansp_berechnung + "<" );
    ergebnis_string_buffer.append( "\n + m_debug_knz_stichtage                  >" + m_debug_knz_stichtage + "<" );
    ergebnis_string_buffer.append( "\n + m_debug_knz_wop_berechnung             >" + m_debug_knz_wop_berechnung + "<" );
    ergebnis_string_buffer.append( "\n + m_debug_knz_zins_berechnung_bsd        >" + m_debug_knz_zins_berechnung_bsd + "<" );
    ergebnis_string_buffer.append( "\n + m_debug_knz_zins_berechnung_spar       >" + m_debug_knz_zins_berechnung_spar + "<" );

    if ( m_rk_input_zahlung_sparbeitrag != null )
    {
      ergebnis_string_buffer.append( "\n + m_rk_input_zahlung_sparbeitrag     >" + m_rk_input_zahlung_sparbeitrag.toStringZeile() + "<" );
    }
    else
    {
      ergebnis_string_buffer.append( "\n + m_rk_input_zahlung_sparbeitrag     >null<" );
    }

    if ( m_rk_input_zahlung_vl_as != null )
    {
      ergebnis_string_buffer.append( "\n + m_rk_input_zahlung_vl_as           >" + m_rk_input_zahlung_vl_as.toStringZeile() + "<" );
    }
    else
    {
      ergebnis_string_buffer.append( "\n + m_rk_input_zahlung_vl_as           >null<" );
    }

    if ( m_rk_input_zahlung_vl_ep != null )
    {
      ergebnis_string_buffer.append( "\n + m_rk_input_zahlung_vl_ep           >" + m_rk_input_zahlung_vl_ep.toStringZeile() + "<" );
    }
    else
    {
      ergebnis_string_buffer.append( "\n + m_rk_input_zahlung_vl_ep           >null<" );
    }

    /*
     * aktuelle Instanz von CalcZahlung aus den Listen Sonderzahlung und Sondertilgung
     */
    CalcZahlung inst_calc_zahlung = null;

    /*
     * Pruefung: Instanz "m_list_zahlung_sonderzahlungen" gesetzt?
     */
    if ( m_list_zahlung_sonderzahlungen != null )
    {
      /*
       * Listenanzahl ermitteln
       */
      int calc_zahlung_list_anzahl = this.getAnzahlSonderzahlungen();

      /*
       * Aktuellen Listenindex deklarieren und auf 0 stellen
       */
      int calc_zahlung_list_index = 0;

      /*
       * While Schleife ueber alle Elemente der Liste "m_list_zahlung_sonderzahlungen"
       */
      while ( calc_zahlung_list_index < calc_zahlung_list_anzahl )
      {
        /*
         * Aktuelle Instanz mit der Index-Instanz setzen
         */
        inst_calc_zahlung = this.getSonderzahlung( calc_zahlung_list_index );

        /*
         * Pruefung: Aktuelle Instanz gesetzt?
         */
        if ( inst_calc_zahlung != null )
        {
          /*
           * Ist die Instanz gesetzt, wird der Ergebnisstring erweitert
           */
          ergebnis_string_buffer.append( "\n + Sonderzahlung[" + calc_zahlung_list_index + "] " + inst_calc_zahlung.toStringZeile() );
        }

        /*
         * Indexzaehler weiterstellen
         */
        calc_zahlung_list_index++;
      }
    }

    /*
     * Pruefung: Instanz "m_list_zahlung_sondertilgungen" gesetzt?
     */
    if ( m_list_zahlung_sondertilgungen == null )
    {
      ergebnis_string_buffer.append( "\n + keine Sondertilgungen vorhanden" );
    }
    else
    {
      /*
       * Listenanzahl ermitteln
       */
      int calc_zahlung_list_anzahl = this.getAnzahlSondertilgungen();

      /*
       * Aktuellen Listenindex deklarieren und auf 0 stellen
       */
      int calc_zahlung_list_index = 0;

      /*
       * While Schleife ueber alle Elemente der Liste "m_list_zahlung_sondertilgungen"
       */
      while ( calc_zahlung_list_index < calc_zahlung_list_anzahl )
      {
        /*
         * Aktuelle Instanz mit der Index-Instanz setzen
         */
        inst_calc_zahlung = this.getSondertilgung( calc_zahlung_list_index );

        /*
         * Pruefung: Aktuelle Instanz gesetzt?
         */
        if ( inst_calc_zahlung != null )
        {
          /*
           * Ist die Instanz gesetzt, wird der Ergebnisstring erweitert
           */
          ergebnis_string_buffer.append( "\n + Sondertilgung[" + calc_zahlung_list_index + "] " + inst_calc_zahlung.toStringZeile() );
        }

        /*
         * Indexzaehler weiterstellen
         */
        calc_zahlung_list_index++;
      }
    }

    return ergebnis_string_buffer.toString();
  }

  /**
   * Erstellt eine Stringrepraesentation in einer Zeile, wobei die Werte durch ein Trennzeichen getrennt werden
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcVorgabenBsv" in einer Zeile
   */
  public String toStringZeile()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( m_rk_input_datum_vertragsbeginn + "|" );
    ergebnis_string_buffer.append( m_rk_input_bestehendes_guthaben + "|" );
    ergebnis_string_buffer.append( m_rk_input_bestehende_bwz + "|" );
    ergebnis_string_buffer.append( m_rk_input_bestehende_bss + "|" );
    ergebnis_string_buffer.append( m_rk_input_wop_speicher + "|" );
    ergebnis_string_buffer.append( m_rk_input_wop_datum_ablauf_bindefrist + "|" );
    ergebnis_string_buffer.append( m_rk_input_datum_berechnungsbeginn + "|" );
    ergebnis_string_buffer.append( m_rk_input_laufzeit_in_monaten + "|" );
    ergebnis_string_buffer.append( m_rk_input_knz_ansp_berechnen + "|" );
    ergebnis_string_buffer.append( m_rk_input_knz_darlehensverzicht + "|" );
    ergebnis_string_buffer.append( m_rk_input_ag_knz_beruecksichtigen + "|" );
    ergebnis_string_buffer.append( m_rk_input_ag_betrag_vorgabe + "|" );
    ergebnis_string_buffer.append( m_rk_input_ag_art_begleichung + "|" );
    ergebnis_string_buffer.append( m_rk_input_knz_berechnungsziel + "|" );
    ergebnis_string_buffer.append( m_rk_input_knz_sparstopp + "|" );
    ergebnis_string_buffer.append( m_rk_input_betrag_bausparsumme + "|" );
    ergebnis_string_buffer.append( m_rk_input_proz_mehrzuteilung + "|" );
    ergebnis_string_buffer.append( m_rk_input_knz_wop_berechnen + "|" );
    ergebnis_string_buffer.append( m_rk_input_knz_wop_junge_leute_r + "|" );
    ergebnis_string_buffer.append( m_rk_input_knz_wop_verheiratet + "|" );
    ergebnis_string_buffer.append( m_rk_input_vorgabe_ztb_betrag + "|" );
    ergebnis_string_buffer.append( m_rk_input_vorgabe_ztb_art + "|" );
    ergebnis_string_buffer.append( m_rk_input_vorgabe_ztb_optimierung + "|" );

    return ergebnis_string_buffer.toString();
  }


//
//  /**
//   * <pre>
//   * Erstellt den JSON-String
//   *
//   *   "calc_vorgaben_bsv": 
//   *     {
//   *       "rk_input_datum_vertragsbeginn"        : 20100101,
//   *       "rk_input_bestehendes_guthaben"        : 0.0d,
//   *       "rk_input_bestehende_bwz"              : 0.0d,
//   *       "rk_input_bestehende_bss"              : 0.0d,
//   *       "rk_input_wop_speicher"                : 0.0d,
//   *       "rk_input_wop_datum_ablauf_bindefrist" : 20100101,
//   *       "rk_input_datum_berechnungsbeginn"     : 20100101,
//   *       "rk_input_laufzeit_in_monaten"         : 0,
//   *       "rk_input_knz_ansp_berechnen"          : true,
//   *       "rk_input_knz_darlehensverzicht"       : true,
//   *       "rk_input_knz_kfg_beruecksichtigen"    : true,
//   *       "rk_input_ag_knz_beruecksichtigen"     : true,
//   *       "rk_input_ag_betrag_vorgabe"           : "ABC.DEF.GHI",
//   *       "rk_input_ag_art_begleichung"          : "ABC.DEF.GHI",
//   *       "rk_input_knz_berechnungsziel"         : "ABC.DEF.GHI",
//   *       "rk_input_knz_sparstopp"               : "ABC.DEF.GHI",
//   *       "rk_input_betrag_bausparsumme"         : 0.0d,
//   *       "rk_input_proz_mehrzuteilung"          : 0.0d,
//   *       "rk_input_knz_wop_berechnen"           : true,
//   *       "rk_input_knz_wop_junge_leute_r"       : true,
//   *       "rk_input_knz_wop_verheiratet"         : true,
//   *       "rk_input_vorgabe_ztb_betrag"          : 0.0d,
//   *       "rk_input_vorgabe_ztb_art"             : "ABC.DEF.GHI",
//   *       "rk_input_vorgabe_ztb_optimierung"     : true
//   *     }
//   *
//   * </pre>
//   *
//   * @return  den erstellten JSON-String
//   */
//  public String toJSON()
//  {
//    String json_string = "";
//
//    json_string = "{";
//    json_string += "\"rk_input_datum_vertragsbeginn\":" + FkJson.getStringJson( m_rk_input_datum_vertragsbeginn ) + " ,";
//    json_string += "\"rk_input_bestehendes_guthaben\":" + FkJson.getStringJson( m_rk_input_bestehendes_guthaben ) + " ,";
//    json_string += "\"rk_input_bestehende_bwz\":" + FkJson.getStringJson( m_rk_input_bestehende_bwz ) + " ,";
//    json_string += "\"rk_input_bestehende_bss\":" + FkJson.getStringJson( m_rk_input_bestehende_bss ) + " ,";
//    json_string += "\"rk_input_wop_speicher\":" + FkJson.getStringJson( m_rk_input_wop_speicher ) + " ,";
//    json_string += "\"rk_input_wop_datum_ablauf_bindefrist\":" + FkJson.getStringJson( m_rk_input_wop_datum_ablauf_bindefrist ) + " ,";
//    json_string += "\"rk_input_datum_berechnungsbeginn\":" + FkJson.getStringJson( m_rk_input_datum_berechnungsbeginn ) + " ,";
//    json_string += "\"rk_input_laufzeit_in_monaten\":" + FkJson.getStringJson( m_rk_input_laufzeit_in_monaten ) + " ,";
//    json_string += "\"rk_input_knz_ansp_berechnen\":\"" + FkJson.getStringJson( m_rk_input_knz_ansp_berechnen ) + "\",";
//    json_string += "\"rk_input_knz_darlehensverzicht\":\"" + FkJson.getStringJson( m_rk_input_knz_darlehensverzicht ) + "\",";
//    json_string += "\"rk_input_knz_kfg_beruecksichtigen\":\"" + FkJson.getStringJson( m_rk_input_knz_kfg_beruecksichtigen ) + "\",";
//    json_string += "\"rk_input_ag_knz_beruecksichtigen\":\"" + FkJson.getStringJson( m_rk_input_ag_knz_beruecksichtigen ) + "\",";
//    json_string += "\"rk_input_ag_betrag_vorgabe\":\"" + FkJson.getStringJson( m_rk_input_ag_betrag_vorgabe ) + "\",";
//    json_string += "\"rk_input_ag_art_begleichung\":\"" + FkJson.getStringJson( m_rk_input_ag_art_begleichung ) + "\",";
//    json_string += "\"rk_input_knz_berechnungsziel\":\"" + FkJson.getStringJson( m_rk_input_knz_berechnungsziel ) + "\",";
//    json_string += "\"rk_input_knz_sparstopp\":\"" + FkJson.getStringJson( m_rk_input_knz_sparstopp ) + "\",";
//    json_string += "\"rk_input_betrag_bausparsumme\":" + FkJson.getStringJson( m_rk_input_betrag_bausparsumme ) + " ,";
//    json_string += "\"rk_input_proz_mehrzuteilung\":" + FkJson.getStringJson( m_rk_input_proz_mehrzuteilung ) + " ,";
//    json_string += "\"rk_input_knz_wop_berechnen\":\"" + FkJson.getStringJson( m_rk_input_knz_wop_berechnen ) + "\",";
//    json_string += "\"rk_input_knz_wop_junge_leute_r\":\"" + FkJson.getStringJson( m_rk_input_knz_wop_junge_leute_r ) + "\",";
//    json_string += "\"rk_input_knz_wop_verheiratet\":\"" + FkJson.getStringJson( m_rk_input_knz_wop_verheiratet ) + "\",";
//    json_string += "\"rk_input_vorgabe_ztb_betrag\":" + FkJson.getStringJson( m_rk_input_vorgabe_ztb_betrag ) + " ,";
//    json_string += "\"rk_input_vorgabe_ztb_art\":\"" + FkJson.getStringJson( m_rk_input_vorgabe_ztb_art ) + "\",";
//    json_string += "\"rk_input_vorgabe_ztb_optimierung\":\"" + FkJson.getStringJson( m_rk_input_vorgabe_ztb_optimierung ) + "\"";
//    json_string += "}";
//
//    return json_string;
//  }

  /**
   * Setzt alle Membervariablen auf "null"
   */
  public void clear()
  {
    m_rk_input_datum_vertragsbeginn = 0l;
    m_rk_input_bestehendes_guthaben = 0.0;
    m_rk_input_bestehende_bwz = 0.0;
    m_rk_input_bestehende_bss = 0.0;
    m_rk_input_wop_speicher = 0.0;
    m_rk_input_wop_datum_ablauf_bindefrist = 0l;
    m_rk_input_datum_berechnungsbeginn = 0l;
    m_rk_input_laufzeit_in_monaten = 0;
    m_rk_input_knz_ansp_berechnen = false;
    m_rk_input_knz_darlehensverzicht = false;
    m_rk_input_ag_knz_beruecksichtigen = false;
    m_rk_input_ag_betrag_vorgabe = null;
    m_rk_input_ag_art_begleichung = null;
    m_rk_input_knz_berechnungsziel = null;
    m_rk_input_knz_sparstopp = null;
    m_rk_input_betrag_bausparsumme = 0.0;
    m_rk_input_proz_mehrzuteilung = 0.0;
    m_rk_input_knz_wop_berechnen = false;
    m_rk_input_knz_wop_junge_leute_r = false;
    m_rk_input_knz_wop_verheiratet = false;
    m_rk_input_vorgabe_ztb_betrag = 0.0;
    m_rk_input_vorgabe_ztb_art = null;
    m_rk_input_vorgabe_ztb_optimierung = false;
  }

  private List m_list_zahlung_sondertilgungen = null;

  private List m_list_zahlung_sonderzahlungen = null;

  public void addSondertilgung( CalcZahlung pBskZahlung )
  {
    getSondertilgungen().add( pBskZahlung );
  }

  public void addSonderzahlung( CalcZahlung pBskZahlung )
  {
    getSonderzahlungen().add( pBskZahlung );
  }

  public List getSondertilgungen()
  {
    if ( m_list_zahlung_sondertilgungen == null )
    {
      m_list_zahlung_sondertilgungen = new ArrayList();
    }
    return m_list_zahlung_sondertilgungen;
  }

  public boolean hasSondertilgungen()
  {
    if ( m_list_zahlung_sondertilgungen == null )
    {
      return false;
    }

    return m_list_zahlung_sondertilgungen.size() > 0;
  }

  public boolean hasSonderzahlungen()
  {
    if ( m_list_zahlung_sonderzahlungen == null )
    {
      return false;
    }

    return m_list_zahlung_sonderzahlungen.size() > 0;
  }

  public List getSonderzahlungen()
  {
    if ( m_list_zahlung_sonderzahlungen == null )
    {
      m_list_zahlung_sonderzahlungen = new ArrayList();
    }

    return m_list_zahlung_sonderzahlungen;
  }

  public int getAnzahlSonderzahlungen()
  {
    if ( m_list_zahlung_sonderzahlungen == null )
    {
      return 0;
    }

    return m_list_zahlung_sonderzahlungen.size();
  }

  public int getAnzahlSondertilgungen()
  {
    if ( m_list_zahlung_sondertilgungen == null )
    {
      return 0;
    }

    return m_list_zahlung_sondertilgungen.size();
  }

  public CalcZahlung getSonderzahlung( int pIndex )
  {
    if ( m_list_zahlung_sonderzahlungen != null )
    {
      try
      {
        return CalcZahlung.getClassCast( m_list_zahlung_sonderzahlungen.get( pIndex ) );
      }
      catch ( Exception exp )
      {
      }
    }

    return null;
  }

  public CalcZahlung getSondertilgung( int pIndex )
  {
    if ( m_list_zahlung_sondertilgungen != null )
    {
      try
      {
        return CalcZahlung.getClassCast( m_list_zahlung_sondertilgungen.get( pIndex ) );
      }
      catch ( Exception exp )
      {
      }
    }

    return null;
  }

  private CalcZahlung m_rk_input_zahlung_sparbeitrag = null;

  private CalcZahlung m_rk_input_zahlung_vl_as       = null;

  private CalcZahlung m_rk_input_zahlung_vl_ep       = null;

  public CalcZahlung getRkInputZahlungSparbeitrag()
  {
    if ( m_rk_input_zahlung_sparbeitrag == null )
    {
      m_rk_input_zahlung_sparbeitrag = new CalcZahlung();
    }

    return m_rk_input_zahlung_sparbeitrag;
  }

  public void setRkInputZahlungSparbeitrag( CalcZahlung pRkInputZahlungSparbeitrag )
  {
    m_rk_input_zahlung_sparbeitrag = pRkInputZahlungSparbeitrag;
  }

  public CalcZahlung getRkInputZahlungVlAs()
  {
    return m_rk_input_zahlung_vl_as;
  }

  public void setRkInputZahlungVlAs( CalcZahlung pRkInputZahlungVlAs )
  {
    m_rk_input_zahlung_vl_as = pRkInputZahlungVlAs;
  }

  public CalcZahlung getRkInputZahlungVlEp()
  {
    return m_rk_input_zahlung_vl_ep;
  }

  public void setRkInputZahlungVlEp( CalcZahlung pRkInputZahlungVlEp )
  {
    m_rk_input_zahlung_vl_ep = pRkInputZahlungVlEp;
  }

  public boolean istSparStoppMindestGuthaben()
  {
    return ( ( m_rk_input_knz_sparstopp != null ) && ( m_rk_input_knz_sparstopp.equalsIgnoreCase( "MINDEST_SPARGUTHABEN" ) ) );
  }

  public boolean istSparStoppZuteilung()
  {
    return ( ( m_rk_input_knz_sparstopp != null ) && ( m_rk_input_knz_sparstopp.equalsIgnoreCase( "ZUTEILUNG" ) ) );
  }

  public boolean istSparStoppZuteilungsvoraussetzungen()
  {
    return ( ( m_rk_input_knz_sparstopp != null ) && ( m_rk_input_knz_sparstopp.equalsIgnoreCase( "MINDEST_ZTV" ) ) );
  }

  public void setSparStoppMindestGuthaben()
  {
    m_rk_input_knz_sparstopp = "MINDEST_SPARGUTHABEN";
  }

  public void setSparStoppZuteilung()
  {
    m_rk_input_knz_sparstopp = "ZUTEILUNG";
  }

  public void setSparStoppZuteilungsvoraussetzungen()
  {
    m_rk_input_knz_sparstopp = "MINDEST_ZTV";
  }

  public boolean istZielBausparsumme()
  {
    return ( ( m_rk_input_knz_berechnungsziel != null ) && ( m_rk_input_knz_berechnungsziel.equalsIgnoreCase( "BAUSPARSUMME" ) ) );
  }

  public boolean istZielLaufzeit()
  {
    return ( ( m_rk_input_knz_berechnungsziel != null ) && ( m_rk_input_knz_berechnungsziel.equalsIgnoreCase( "LAUFZEIT" ) ) );
  }

  public boolean istZielSparrate()
  {
    return ( ( m_rk_input_knz_berechnungsziel != null ) && ( m_rk_input_knz_berechnungsziel.equalsIgnoreCase( "SPARRATE" ) ) );
  }

  public boolean istZielSimulation()
  {
    return ( ( m_rk_input_knz_berechnungsziel != null ) && ( m_rk_input_knz_berechnungsziel.equalsIgnoreCase( "SIMULATION" ) ) );
  }

  public void setZielBausparsumme()
  {
    m_rk_input_knz_berechnungsziel = "BAUSPARSUMME";
  }

  public void setZielLaufzeit()
  {
    m_rk_input_knz_berechnungsziel = "LAUFZEIT";
  }

  public void setZielSparrate()
  {
    m_rk_input_knz_berechnungsziel = "SPARRATE";
  }

  public void setZielSimulation()
  {
    m_rk_input_knz_berechnungsziel = "SIMULATION";
  }

  public boolean istAgArtBegleichungVerrechnung()
  {
    return ( ( m_rk_input_ag_art_begleichung != null ) && ( m_rk_input_ag_art_begleichung.equalsIgnoreCase( "VERRECHNUNG" ) ) );
  }

  public boolean istAgArtBegleichungSeparat()
  {
    return ( ( m_rk_input_ag_art_begleichung != null ) && ( m_rk_input_ag_art_begleichung.equalsIgnoreCase( "SEPARAT" ) ) );
  }

  public void setZtbAuswahlTariflich()
  {
    m_rk_input_vorgabe_ztb_art = "TARIF";
  }

  public boolean istZtbAuswahlTariflich()
  {
    return ( ( m_rk_input_vorgabe_ztb_art != null ) && ( m_rk_input_vorgabe_ztb_art.equalsIgnoreCase( "TARIF" ) ) );
  }

  public boolean istZtbAuswahlVorgabeBetrag()
  {
    return ( ( m_rk_input_vorgabe_ztb_art != null ) && ( m_rk_input_vorgabe_ztb_art.equalsIgnoreCase( "BETRAG" ) ) );
  }

  public boolean istZtbAuswahlVorgabePromille()
  {
    return ( ( m_rk_input_vorgabe_ztb_art != null ) && ( m_rk_input_vorgabe_ztb_art.equalsIgnoreCase( "PROMILLE" ) ) );
  }

  public boolean istZtbAuswahlNiedrigstMoeglicher()
  {
    return ( ( m_rk_input_vorgabe_ztb_art != null ) && ( m_rk_input_vorgabe_ztb_art.equalsIgnoreCase( "NZTB" ) ) );
  }
}
