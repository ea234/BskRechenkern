package de.bskrechner.calc;

import java.util.ArrayList;
import java.util.List;

import de.bskrechner.util.FkJson;
import de.bskrechner.util.FkString;
import de.bskrechner.util.FkZahl;

/**
 * <pre>
 * Java-Klasse "CalcVorgabenTarif".
 * </pre>
 */
public class CalcVorgabenTarif
{
  /** 
   * @return  Einen String fuer eine Uerberschriftenzeile
   */
  public static String toStringZeileUeberschrift()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( "Bewertungszahl Faktor 1" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bonus Knz Bei Darlehensannahme" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bonus Knz Bei Darlverzicht" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bonus Max Laufzeit Jahre" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bonus Min Bwz" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bonus Min Laufzeit Monate" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Darlehensanspruch Art" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Darlehensanspruch Prozent" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Kto Gebuehr Pro Jahr" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Kto Gebuehr Sparen Pro Jahr" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Kto Gebuehr Tilgen Pro Jahr" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Kto Gebuehr Sparen Knz Ein" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Kto Gebuehr Tilgen Knz Ein" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Mindest Bausparsumme" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Mindest Bewertungszahl" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Mindest Sparzeit In Monaten" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Promille Abschlussgebuehr" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Prozent Guthabenzins" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Prozent Bonuszins" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Prozent Mindest Sparguthaben" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Wartezeit In Monaten" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Regel Sparbeitrag" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bsd Zinssatz" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bsd Proz Darlehensgebuehr" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bsd Proz Disagio" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Knz Nztb Tarif" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Tarif Kto Gebuehr Sparen Pro Jahr" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Tarif Kto Gebuehr Tilgen Pro Jahr" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Tarif Kto Gebuehr Sparen Knz Ein" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Tarif Kto Gebuehr Tilten Knz Ein" );
    ergebnis_string_buffer.append( "|" );

    return ergebnis_string_buffer.toString();
  }

  private double  m_bewertungszahl_faktor_1           = 0.0;

  private boolean m_bonus_knz_bei_darlehensannahme    = true;

  private boolean m_bonus_knz_bei_darlverzicht        = true;

  private int     m_bonus_max_laufzeit_jahre          = 0;

  private double  m_bonus_min_bwz                     = 0.0;

  private int     m_bonus_min_laufzeit_monate         = 0;

  private String  m_darlehensanspruch_art             = null;

  private double  m_darlehensanspruch_prozent         = 0.0;

  private double  m_kto_gebuehr_pro_jahr              = 0.0;

  private double  m_kto_gebuehr_sparen_pro_jahr       = 0.0;

  private double  m_kto_gebuehr_tilgen_pro_jahr       = 0.0;

  private boolean m_kto_gebuehr_sparen_knz_ein        = true;

  private boolean m_kto_gebuehr_tilgen_knz_ein        = true;

  private double  m_mindest_bausparsumme              = 0.0;

  private double  m_mindest_bewertungszahl            = 0.0;

  private int     m_mindest_sparzeit_in_monaten       = 0;

  private double  m_promille_abschlussgebuehr         = 0.0;

  private double  m_prozent_guthabenzins              = 0.0;

  private double  m_prozent_bonuszins                 = 0.0;

  private double  m_prozent_mindest_sparguthaben      = 0.0;

  private int     m_wartezeit_in_monaten              = 0;

  private double  m_regel_sparbeitrag                 = 0.0;

  private double  m_bsd_zinssatz                      = 0.0;

  private double  m_bsd_proz_darlehensgebuehr         = 0.0;

  private double  m_bsd_proz_disagio                  = 0.0;

  private boolean m_knz_nztb_tarif                    = false;

  private double  m_tarif_kto_gebuehr_sparen_pro_jahr = 0.0;

  private double  m_tarif_kto_gebuehr_tilgen_pro_jahr = 0.0;

  private boolean m_tarif_kto_gebuehr_sparen_knz_ein  = true;

  private boolean m_tarif_kto_gebuehr_tilten_knz_ein  = true;

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bewertungszahl_faktor_1.
   * </pre>
   * 
   * @param  pBewertungszahlFaktor1 der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBewertungszahlFaktor1( String pBewertungszahlFaktor1 )
  {
    m_bewertungszahl_faktor_1 = FkZahl.getDouble( pBewertungszahlFaktor1, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bewertungszahl_faktor_1.
   * </pre>
   * 
   * @param  pBewertungszahlFaktor1 der zu setzende Wert
   */
  public void setBewertungszahlFaktor1( double pBewertungszahlFaktor1 )
  {
    m_bewertungszahl_faktor_1 = pBewertungszahlFaktor1;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_knz_bei_darlehensannahme.
   * </pre>
   * 
   * @param  pBonusKnzBeiDarlehensannahme der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setBonusKnzBeiDarlehensannahme( String pBonusKnzBeiDarlehensannahme )
  {
    m_bonus_knz_bei_darlehensannahme = FkString.getBoolean( pBonusKnzBeiDarlehensannahme, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_knz_bei_darlehensannahme.
   * </pre>
   * 
   * @param  pBonusKnzBeiDarlehensannahme der zu setzende Wert
   */
  public void setBonusKnzBeiDarlehensannahme( boolean pBonusKnzBeiDarlehensannahme )
  {
    m_bonus_knz_bei_darlehensannahme = pBonusKnzBeiDarlehensannahme;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_knz_bei_darlverzicht.
   * </pre>
   * 
   * @param  pBonusKnzBeiDarlverzicht der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setBonusKnzBeiDarlverzicht( String pBonusKnzBeiDarlverzicht )
  {
    m_bonus_knz_bei_darlverzicht = FkString.getBoolean( pBonusKnzBeiDarlverzicht, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_knz_bei_darlverzicht.
   * </pre>
   * 
   * @param  pBonusKnzBeiDarlverzicht der zu setzende Wert
   */
  public void setBonusKnzBeiDarlverzicht( boolean pBonusKnzBeiDarlverzicht )
  {
    m_bonus_knz_bei_darlverzicht = pBonusKnzBeiDarlverzicht;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_max_laufzeit_jahre.
   * </pre>
   * 
   * @param  pBonusMaxLaufzeitJahre der zu setzende Wert als String (wird zu int gewandelt)
   */
  public void setBonusMaxLaufzeitJahre( String pBonusMaxLaufzeitJahre )
  {
    m_bonus_max_laufzeit_jahre = FkZahl.getInteger( pBonusMaxLaufzeitJahre, 0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_max_laufzeit_jahre.
   * </pre>
   * 
   * @param  pBonusMaxLaufzeitJahre der zu setzende Wert
   */
  public void setBonusMaxLaufzeitJahre( int pBonusMaxLaufzeitJahre )
  {
    m_bonus_max_laufzeit_jahre = pBonusMaxLaufzeitJahre;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_min_bwz.
   * </pre>
   * 
   * @param  pBonusMinBwz   der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBonusMinBwz( String pBonusMinBwz )
  {
    m_bonus_min_bwz = FkZahl.getDouble( pBonusMinBwz, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_min_bwz.
   * </pre>
   * 
   * @param  pBonusMinBwz   der zu setzende Wert
   */
  public void setBonusMinBwz( double pBonusMinBwz )
  {
    m_bonus_min_bwz = pBonusMinBwz;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_min_laufzeit_monate.
   * </pre>
   * 
   * @param  pBonusMinLaufzeitMonate der zu setzende Wert als String (wird zu int gewandelt)
   */
  public void setBonusMinLaufzeitMonate( String pBonusMinLaufzeitMonate )
  {
    m_bonus_min_laufzeit_monate = FkZahl.getInteger( pBonusMinLaufzeitMonate, 0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_min_laufzeit_monate.
   * </pre>
   * 
   * @param  pBonusMinLaufzeitMonate der zu setzende Wert
   */
  public void setBonusMinLaufzeitMonate( int pBonusMinLaufzeitMonate )
  {
    m_bonus_min_laufzeit_monate = pBonusMinLaufzeitMonate;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_darlehensanspruch_art.
   * </pre>
   * 
   * @param  pDarlehensanspruchArt der zu setzende Wert
   */
  public void setDarlehensanspruchArt( String pDarlehensanspruchArt )
  {
    m_darlehensanspruch_art = pDarlehensanspruchArt;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_darlehensanspruch_prozent.
   * </pre>
   * 
   * @param  pDarlehensanspruchProzent der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setDarlehensanspruchProzent( String pDarlehensanspruchProzent )
  {
    m_darlehensanspruch_prozent = FkZahl.getDouble( pDarlehensanspruchProzent, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_darlehensanspruch_prozent.
   * </pre>
   * 
   * @param  pDarlehensanspruchProzent der zu setzende Wert
   */
  public void setDarlehensanspruchProzent( double pDarlehensanspruchProzent )
  {
    m_darlehensanspruch_prozent = pDarlehensanspruchProzent;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_pro_jahr.
   * </pre>
   * 
   * @param  pKtoGebuehrProJahr der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setKtoGebuehrProJahr( String pKtoGebuehrProJahr )
  {
    m_kto_gebuehr_pro_jahr = FkZahl.getDouble( pKtoGebuehrProJahr, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_pro_jahr.
   * </pre>
   * 
   * @param  pKtoGebuehrProJahr der zu setzende Wert
   */
  public void setKtoGebuehrProJahr( double pKtoGebuehrProJahr )
  {
    m_kto_gebuehr_pro_jahr = pKtoGebuehrProJahr;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_sparen_pro_jahr.
   * </pre>
   * 
   * @param  pKtoGebuehrSparenProJahr der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setKtoGebuehrSparenProJahr( String pKtoGebuehrSparenProJahr )
  {
    m_kto_gebuehr_sparen_pro_jahr = FkZahl.getDouble( pKtoGebuehrSparenProJahr, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_sparen_pro_jahr.
   * </pre>
   * 
   * @param  pKtoGebuehrSparenProJahr der zu setzende Wert
   */
  public void setKtoGebuehrSparenProJahr( double pKtoGebuehrSparenProJahr )
  {
    m_kto_gebuehr_sparen_pro_jahr = pKtoGebuehrSparenProJahr;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_tilgen_pro_jahr.
   * </pre>
   * 
   * @param  pKtoGebuehrTilgenProJahr der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setKtoGebuehrTilgenProJahr( String pKtoGebuehrTilgenProJahr )
  {
    m_kto_gebuehr_tilgen_pro_jahr = FkZahl.getDouble( pKtoGebuehrTilgenProJahr, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_tilgen_pro_jahr.
   * </pre>
   * 
   * @param  pKtoGebuehrTilgenProJahr der zu setzende Wert
   */
  public void setKtoGebuehrTilgenProJahr( double pKtoGebuehrTilgenProJahr )
  {
    m_kto_gebuehr_tilgen_pro_jahr = pKtoGebuehrTilgenProJahr;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_sparen_knz_ein.
   * </pre>
   * 
   * @param  pKtoGebuehrSparenKnzEin der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setKtoGebuehrSparenKnzEin( String pKtoGebuehrSparenKnzEin )
  {
    m_kto_gebuehr_sparen_knz_ein = FkString.getBoolean( pKtoGebuehrSparenKnzEin, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_sparen_knz_ein.
   * </pre>
   * 
   * @param  pKtoGebuehrSparenKnzEin der zu setzende Wert
   */
  public void setKtoGebuehrSparenKnzEin( boolean pKtoGebuehrSparenKnzEin )
  {
    m_kto_gebuehr_sparen_knz_ein = pKtoGebuehrSparenKnzEin;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_tilgen_knz_ein.
   * </pre>
   * 
   * @param  pKtoGebuehrTilgenKnzEin der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setKtoGebuehrTilgenKnzEin( String pKtoGebuehrTilgenKnzEin )
  {
    m_kto_gebuehr_tilgen_knz_ein = FkString.getBoolean( pKtoGebuehrTilgenKnzEin, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_kto_gebuehr_tilgen_knz_ein.
   * </pre>
   * 
   * @param  pKtoGebuehrTilgenKnzEin der zu setzende Wert
   */
  public void setKtoGebuehrTilgenKnzEin( boolean pKtoGebuehrTilgenKnzEin )
  {
    m_kto_gebuehr_tilgen_knz_ein = pKtoGebuehrTilgenKnzEin;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_mindest_bausparsumme.
   * </pre>
   * 
   * @param  pMindestBausparsumme der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setMindestBausparsumme( String pMindestBausparsumme )
  {
    m_mindest_bausparsumme = FkZahl.getDouble( pMindestBausparsumme, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_mindest_bausparsumme.
   * </pre>
   * 
   * @param  pMindestBausparsumme der zu setzende Wert
   */
  public void setMindestBausparsumme( double pMindestBausparsumme )
  {
    m_mindest_bausparsumme = pMindestBausparsumme;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_mindest_bewertungszahl.
   * </pre>
   * 
   * @param  pMindestBewertungszahl der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setMindestBewertungszahl( String pMindestBewertungszahl )
  {
    m_mindest_bewertungszahl = FkZahl.getDouble( pMindestBewertungszahl, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_mindest_bewertungszahl.
   * </pre>
   * 
   * @param  pMindestBewertungszahl der zu setzende Wert
   */
  public void setMindestBewertungszahl( double pMindestBewertungszahl )
  {
    m_mindest_bewertungszahl = pMindestBewertungszahl;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_mindest_sparzeit_in_monaten.
   * </pre>
   * 
   * @param  pMindestSparzeitInMonaten der zu setzende Wert als String (wird zu int gewandelt)
   */
  public void setMindestSparzeitInMonaten( String pMindestSparzeitInMonaten )
  {
    m_mindest_sparzeit_in_monaten = FkZahl.getInteger( pMindestSparzeitInMonaten, 0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_mindest_sparzeit_in_monaten.
   * </pre>
   * 
   * @param  pMindestSparzeitInMonaten der zu setzende Wert
   */
  public void setMindestSparzeitInMonaten( int pMindestSparzeitInMonaten )
  {
    m_mindest_sparzeit_in_monaten = pMindestSparzeitInMonaten;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_promille_abschlussgebuehr.
   * </pre>
   * 
   * @param  pPromilleAbschlussgebuehr der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setPromilleAbschlussgebuehr( String pPromilleAbschlussgebuehr )
  {
    m_promille_abschlussgebuehr = FkZahl.getDouble( pPromilleAbschlussgebuehr, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_promille_abschlussgebuehr.
   * </pre>
   * 
   * @param  pPromilleAbschlussgebuehr der zu setzende Wert
   */
  public void setPromilleAbschlussgebuehr( double pPromilleAbschlussgebuehr )
  {
    m_promille_abschlussgebuehr = pPromilleAbschlussgebuehr;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_prozent_guthabenzins.
   * </pre>
   * 
   * @param  pProzentGuthabenzins der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setProzentGuthabenzins( String pProzentGuthabenzins )
  {
    m_prozent_guthabenzins = FkZahl.getDouble( pProzentGuthabenzins, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_prozent_guthabenzins.
   * </pre>
   * 
   * @param  pProzentGuthabenzins der zu setzende Wert
   */
  public void setProzentGuthabenzins( double pProzentGuthabenzins )
  {
    m_prozent_guthabenzins = pProzentGuthabenzins;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_prozent_bonuszins.
   * </pre>
   * 
   * @param  pProzentBonuszins der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setProzentBonuszins( String pProzentBonuszins )
  {
    m_prozent_bonuszins = FkZahl.getDouble( pProzentBonuszins, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_prozent_bonuszins.
   * </pre>
   * 
   * @param  pProzentBonuszins der zu setzende Wert
   */
  public void setProzentBonuszins( double pProzentBonuszins )
  {
    m_prozent_bonuszins = pProzentBonuszins;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_prozent_mindest_sparguthaben.
   * </pre>
   * 
   * @param  pProzentMindestSparguthaben der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setProzentMindestSparguthaben( String pProzentMindestSparguthaben )
  {
    m_prozent_mindest_sparguthaben = FkZahl.getDouble( pProzentMindestSparguthaben, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_prozent_mindest_sparguthaben.
   * </pre>
   * 
   * @param  pProzentMindestSparguthaben der zu setzende Wert
   */
  public void setProzentMindestSparguthaben( double pProzentMindestSparguthaben )
  {
    m_prozent_mindest_sparguthaben = pProzentMindestSparguthaben;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_wartezeit_in_monaten.
   * </pre>
   * 
   * @param  pWartezeitInMonaten der zu setzende Wert als String (wird zu int gewandelt)
   */
  public void setWartezeitInMonaten( String pWartezeitInMonaten )
  {
    m_wartezeit_in_monaten = FkZahl.getInteger( pWartezeitInMonaten, 0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_wartezeit_in_monaten.
   * </pre>
   * 
   * @param  pWartezeitInMonaten der zu setzende Wert
   */
  public void setWartezeitInMonaten( int pWartezeitInMonaten )
  {
    m_wartezeit_in_monaten = pWartezeitInMonaten;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_regel_sparbeitrag.
   * </pre>
   * 
   * @param  pRegelSparbeitrag der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setRegelSparbeitrag( String pRegelSparbeitrag )
  {
    m_regel_sparbeitrag = FkZahl.getDouble( pRegelSparbeitrag, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_regel_sparbeitrag.
   * </pre>
   * 
   * @param  pRegelSparbeitrag der zu setzende Wert
   */
  public void setRegelSparbeitrag( double pRegelSparbeitrag )
  {
    m_regel_sparbeitrag = pRegelSparbeitrag;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_zinssatz.
   * </pre>
   * 
   * @param  pBsdZinssatz   der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBsdZinssatz( String pBsdZinssatz )
  {
    m_bsd_zinssatz = FkZahl.getDouble( pBsdZinssatz, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_zinssatz.
   * </pre>
   * 
   * @param  pBsdZinssatz   der zu setzende Wert
   */
  public void setBsdZinssatz( double pBsdZinssatz )
  {
    m_bsd_zinssatz = pBsdZinssatz;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_proz_darlehensgebuehr.
   * </pre>
   * 
   * @param  pBsdProzDarlehensgebuehr der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBsdProzDarlehensgebuehr( String pBsdProzDarlehensgebuehr )
  {
    m_bsd_proz_darlehensgebuehr = FkZahl.getDouble( pBsdProzDarlehensgebuehr, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_proz_darlehensgebuehr.
   * </pre>
   * 
   * @param  pBsdProzDarlehensgebuehr der zu setzende Wert
   */
  public void setBsdProzDarlehensgebuehr( double pBsdProzDarlehensgebuehr )
  {
    m_bsd_proz_darlehensgebuehr = pBsdProzDarlehensgebuehr;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_proz_disagio.
   * </pre>
   * 
   * @param  pBsdProzDisagio der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBsdProzDisagio( String pBsdProzDisagio )
  {
    m_bsd_proz_disagio = FkZahl.getDouble( pBsdProzDisagio, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_proz_disagio.
   * </pre>
   * 
   * @param  pBsdProzDisagio der zu setzende Wert
   */
  public void setBsdProzDisagio( double pBsdProzDisagio )
  {
    m_bsd_proz_disagio = pBsdProzDisagio;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_knz_nztb_tarif.
   * </pre>
   * 
   * @param  pKnzNztbTarif  der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setKnzNztbTarif( String pKnzNztbTarif )
  {
    m_knz_nztb_tarif = FkString.getBoolean( pKnzNztbTarif, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_knz_nztb_tarif.
   * </pre>
   * 
   * @param  pKnzNztbTarif  der zu setzende Wert
   */
  public void setKnzNztbTarif( boolean pKnzNztbTarif )
  {
    m_knz_nztb_tarif = pKnzNztbTarif;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_tarif_kto_gebuehr_sparen_pro_jahr.
   * </pre>
   * 
   * @param  pTarifKtoGebuehrSparenProJahr der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setTarifKtoGebuehrSparenProJahr( String pTarifKtoGebuehrSparenProJahr )
  {
    m_tarif_kto_gebuehr_sparen_pro_jahr = FkZahl.getDouble( pTarifKtoGebuehrSparenProJahr, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_tarif_kto_gebuehr_sparen_pro_jahr.
   * </pre>
   * 
   * @param  pTarifKtoGebuehrSparenProJahr der zu setzende Wert
   */
  public void setTarifKtoGebuehrSparenProJahr( double pTarifKtoGebuehrSparenProJahr )
  {
    m_tarif_kto_gebuehr_sparen_pro_jahr = pTarifKtoGebuehrSparenProJahr;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_tarif_kto_gebuehr_tilgen_pro_jahr.
   * </pre>
   * 
   * @param  pTarifKtoGebuehrTilgenProJahr der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setTarifKtoGebuehrTilgenProJahr( String pTarifKtoGebuehrTilgenProJahr )
  {
    m_tarif_kto_gebuehr_tilgen_pro_jahr = FkZahl.getDouble( pTarifKtoGebuehrTilgenProJahr, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_tarif_kto_gebuehr_tilgen_pro_jahr.
   * </pre>
   * 
   * @param  pTarifKtoGebuehrTilgenProJahr der zu setzende Wert
   */
  public void setTarifKtoGebuehrTilgenProJahr( double pTarifKtoGebuehrTilgenProJahr )
  {
    m_tarif_kto_gebuehr_tilgen_pro_jahr = pTarifKtoGebuehrTilgenProJahr;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_tarif_kto_gebuehr_sparen_knz_ein.
   * </pre>
   * 
   * @param  pTarifKtoGebuehrSparenKnzEin der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setTarifKtoGebuehrSparenKnzEin( String pTarifKtoGebuehrSparenKnzEin )
  {
    m_tarif_kto_gebuehr_sparen_knz_ein = FkString.getBoolean( pTarifKtoGebuehrSparenKnzEin, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_tarif_kto_gebuehr_sparen_knz_ein.
   * </pre>
   * 
   * @param  pTarifKtoGebuehrSparenKnzEin der zu setzende Wert
   */
  public void setTarifKtoGebuehrSparenKnzEin( boolean pTarifKtoGebuehrSparenKnzEin )
  {
    m_tarif_kto_gebuehr_sparen_knz_ein = pTarifKtoGebuehrSparenKnzEin;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_tarif_kto_gebuehr_tilten_knz_ein.
   * </pre>
   * 
   * @param  pTarifKtoGebuehrTiltenKnzEin der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setTarifKtoGebuehrTiltenKnzEin( String pTarifKtoGebuehrTiltenKnzEin )
  {
    m_tarif_kto_gebuehr_tilten_knz_ein = FkString.getBoolean( pTarifKtoGebuehrTiltenKnzEin, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_tarif_kto_gebuehr_tilten_knz_ein.
   * </pre>
   * 
   * @param  pTarifKtoGebuehrTiltenKnzEin der zu setzende Wert
   */
  public void setTarifKtoGebuehrTiltenKnzEin( boolean pTarifKtoGebuehrTiltenKnzEin )
  {
    m_tarif_kto_gebuehr_tilten_knz_ein = pTarifKtoGebuehrTiltenKnzEin;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bewertungszahl_faktor_1.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bewertungszahl_faktor_1"
   */
  public double getBewertungszahlFaktor1()
  {
    return m_bewertungszahl_faktor_1;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bonus_knz_bei_darlehensannahme.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bonus_knz_bei_darlehensannahme"
   */
  public boolean getBonusKnzBeiDarlehensannahme()
  {
    return m_bonus_knz_bei_darlehensannahme;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bonus_knz_bei_darlverzicht.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bonus_knz_bei_darlverzicht"
   */
  public boolean getBonusKnzBeiDarlverzicht()
  {
    return m_bonus_knz_bei_darlverzicht;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bonus_max_laufzeit_jahre.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bonus_max_laufzeit_jahre"
   */
  public int getBonusMaxLaufzeitJahre()
  {
    return m_bonus_max_laufzeit_jahre;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bonus_min_bwz.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bonus_min_bwz"
   */
  public double getBonusMinBwz()
  {
    return m_bonus_min_bwz;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bonus_min_laufzeit_monate.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bonus_min_laufzeit_monate"
   */
  public int getBonusMinLaufzeitMonate()
  {
    return m_bonus_min_laufzeit_monate;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_darlehensanspruch_art.
   * </pre>
   *  
   * @return den Wert der Variablen "m_darlehensanspruch_art"
   */
  public String getDarlehensanspruchArt()
  {
    return m_darlehensanspruch_art;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_darlehensanspruch_prozent.
   * </pre>
   *  
   * @return den Wert der Variablen "m_darlehensanspruch_prozent"
   */
  public double getDarlehensanspruchProzent()
  {
    return m_darlehensanspruch_prozent;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_kto_gebuehr_pro_jahr.
   * </pre>
   *  
   * @return den Wert der Variablen "m_kto_gebuehr_pro_jahr"
   */
  public double getKtoGebuehrProJahr()
  {
    return m_kto_gebuehr_pro_jahr;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_kto_gebuehr_sparen_pro_jahr.
   * </pre>
   *  
   * @return den Wert der Variablen "m_kto_gebuehr_sparen_pro_jahr"
   */
  public double getKtoGebuehrSparenProJahr()
  {
    return m_kto_gebuehr_sparen_pro_jahr;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_kto_gebuehr_tilgen_pro_jahr.
   * </pre>
   *  
   * @return den Wert der Variablen "m_kto_gebuehr_tilgen_pro_jahr"
   */
  public double getKtoGebuehrTilgenProJahr()
  {
    return m_kto_gebuehr_tilgen_pro_jahr;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_kto_gebuehr_sparen_knz_ein.
   * </pre>
   *  
   * @return den Wert der Variablen "m_kto_gebuehr_sparen_knz_ein"
   */
  public boolean getKtoGebuehrSparenKnzEin()
  {
    return m_kto_gebuehr_sparen_knz_ein;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_kto_gebuehr_tilgen_knz_ein.
   * </pre>
   *  
   * @return den Wert der Variablen "m_kto_gebuehr_tilgen_knz_ein"
   */
  public boolean getKtoGebuehrTilgenKnzEin()
  {
    return m_kto_gebuehr_tilgen_knz_ein;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_mindest_bausparsumme.
   * </pre>
   *  
   * @return den Wert der Variablen "m_mindest_bausparsumme"
   */
  public double getMindestBausparsumme()
  {
    return m_mindest_bausparsumme;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_mindest_bewertungszahl.
   * </pre>
   *  
   * @return den Wert der Variablen "m_mindest_bewertungszahl"
   */
  public double getMindestBewertungszahl()
  {
    return m_mindest_bewertungszahl;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_mindest_sparzeit_in_monaten.
   * </pre>
   *  
   * @return den Wert der Variablen "m_mindest_sparzeit_in_monaten"
   */
  public int getMindestSparzeitInMonaten()
  {
    return m_mindest_sparzeit_in_monaten;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_promille_abschlussgebuehr.
   * </pre>
   *  
   * @return den Wert der Variablen "m_promille_abschlussgebuehr"
   */
  public double getPromilleAbschlussgebuehr()
  {
    return m_promille_abschlussgebuehr;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_prozent_guthabenzins.
   * </pre>
   *  
   * @return den Wert der Variablen "m_prozent_guthabenzins"
   */
  public double getProzentGuthabenzins()
  {
    return m_prozent_guthabenzins;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_prozent_bonuszins.
   * </pre>
   *  
   * @return den Wert der Variablen "m_prozent_bonuszins"
   */
  public double getProzentBonuszins()
  {
    return m_prozent_bonuszins;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_prozent_mindest_sparguthaben.
   * </pre>
   *  
   * @return den Wert der Variablen "m_prozent_mindest_sparguthaben"
   */
  public double getProzentMindestSparguthaben()
  {
    return m_prozent_mindest_sparguthaben;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_wartezeit_in_monaten.
   * </pre>
   *  
   * @return den Wert der Variablen "m_wartezeit_in_monaten"
   */
  public int getWartezeitInMonaten()
  {
    return m_wartezeit_in_monaten;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_regel_sparbeitrag.
   * </pre>
   *  
   * @return den Wert der Variablen "m_regel_sparbeitrag"
   */
  public double getRegelSparbeitrag()
  {
    return m_regel_sparbeitrag;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bsd_zinssatz.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bsd_zinssatz"
   */
  public double getBsdZinssatz()
  {
    return m_bsd_zinssatz;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bsd_proz_darlehensgebuehr.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bsd_proz_darlehensgebuehr"
   */
  public double getBsdProzDarlehensgebuehr()
  {
    return m_bsd_proz_darlehensgebuehr;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bsd_proz_disagio.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bsd_proz_disagio"
   */
  public double getBsdProzDisagio()
  {
    return m_bsd_proz_disagio;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_knz_nztb_tarif.
   * </pre>
   *  
   * @return den Wert der Variablen "m_knz_nztb_tarif"
   */
  public boolean getKnzNztbTarif()
  {
    return m_knz_nztb_tarif;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_tarif_kto_gebuehr_sparen_pro_jahr.
   * </pre>
   *  
   * @return den Wert der Variablen "m_tarif_kto_gebuehr_sparen_pro_jahr"
   */
  public double getTarifKtoGebuehrSparenProJahr()
  {
    return m_tarif_kto_gebuehr_sparen_pro_jahr;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_tarif_kto_gebuehr_tilgen_pro_jahr.
   * </pre>
   *  
   * @return den Wert der Variablen "m_tarif_kto_gebuehr_tilgen_pro_jahr"
   */
  public double getTarifKtoGebuehrTilgenProJahr()
  {
    return m_tarif_kto_gebuehr_tilgen_pro_jahr;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_tarif_kto_gebuehr_sparen_knz_ein.
   * </pre>
   *  
   * @return den Wert der Variablen "m_tarif_kto_gebuehr_sparen_knz_ein"
   */
  public boolean getTarifKtoGebuehrSparenKnzEin()
  {
    return m_tarif_kto_gebuehr_sparen_knz_ein;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_tarif_kto_gebuehr_tilten_knz_ein.
   * </pre>
   *  
   * @return den Wert der Variablen "m_tarif_kto_gebuehr_tilten_knz_ein"
   */
  public boolean getTarifKtoGebuehrTiltenKnzEin()
  {
    return m_tarif_kto_gebuehr_tilten_knz_ein;
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
   *      0 = Bewertungszahl Faktor 1
   *      1 = Bonus Knz Bei Darlehensannahme
   *      2 = Bonus Knz Bei Darlverzicht
   *      3 = Bonus Max Laufzeit Jahre
   *      4 = Bonus Min Bwz
   *      5 = Bonus Min Laufzeit Monate
   *      6 = Darlehensanspruch Art
   *      7 = Darlehensanspruch Prozent
   *      8 = Kto Gebuehr Pro Jahr
   *      9 = Kto Gebuehr Sparen Pro Jahr
   *     10 = Kto Gebuehr Tilgen Pro Jahr
   *     11 = Kto Gebuehr Sparen Knz Ein
   *     12 = Kto Gebuehr Tilgen Knz Ein
   *     13 = Mindest Bausparsumme
   *     14 = Mindest Bewertungszahl
   *     15 = Mindest Sparzeit In Monaten
   *     16 = Promille Abschlussgebuehr
   *     17 = Prozent Guthabenzins
   *     18 = Prozent Bonuszins
   *     19 = Prozent Mindest Sparguthaben
   *     20 = Wartezeit In Monaten
   *     21 = Regel Sparbeitrag
   *     22 = Bsd Zinssatz
   *     23 = Bsd Proz Darlehensgebuehr
   *     24 = Bsd Proz Disagio
   *     25 = Knz Nztb Tarif
   *     26 = Tarif Kto Gebuehr Sparen Pro Jahr
   *     27 = Tarif Kto Gebuehr Tilgen Pro Jahr
   *     28 = Tarif Kto Gebuehr Sparen Knz Ein
   *     29 = Tarif Kto Gebuehr Tilten Knz Ein
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
        return "" + getBewertungszahlFaktor1();

      case 1 :
        return "" + getBonusKnzBeiDarlehensannahme();

      case 2 :
        return "" + getBonusKnzBeiDarlverzicht();

      case 3 :
        return "" + getBonusMaxLaufzeitJahre();

      case 4 :
        return "" + getBonusMinBwz();

      case 5 :
        return "" + getBonusMinLaufzeitMonate();

      case 6 :
        return getDarlehensanspruchArt();

      case 7 :
        return "" + getDarlehensanspruchProzent();

      case 8 :
        return "" + getKtoGebuehrProJahr();

      case 9 :
        return "" + getKtoGebuehrSparenProJahr();

      case 10 :
        return "" + getKtoGebuehrTilgenProJahr();

      case 11 :
        return "" + getKtoGebuehrSparenKnzEin();

      case 12 :
        return "" + getKtoGebuehrTilgenKnzEin();

      case 13 :
        return "" + getMindestBausparsumme();

      case 14 :
        return "" + getMindestBewertungszahl();

      case 15 :
        return "" + getMindestSparzeitInMonaten();

      case 16 :
        return "" + getPromilleAbschlussgebuehr();

      case 17 :
        return "" + getProzentGuthabenzins();

      case 18 :
        return "" + getProzentBonuszins();

      case 19 :
        return "" + getProzentMindestSparguthaben();

      case 20 :
        return "" + getWartezeitInMonaten();

      case 21 :
        return "" + getRegelSparbeitrag();

      case 22 :
        return "" + getBsdZinssatz();

      case 23 :
        return "" + getBsdProzDarlehensgebuehr();

      case 24 :
        return "" + getBsdProzDisagio();

      case 25 :
        return "" + getKnzNztbTarif();

      case 26 :
        return "" + getTarifKtoGebuehrSparenProJahr();

      case 27 :
        return "" + getTarifKtoGebuehrTilgenProJahr();

      case 28 :
        return "" + getTarifKtoGebuehrSparenKnzEin();

      case 29 :
        return "" + getTarifKtoGebuehrTiltenKnzEin();
    }

    return null;
  }

  /**
   * Erstellt die Stringrepraesentation der Klasse
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcVorgabenTarif"
   */
  public String toString()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( "\nKlasse CalcVorgabenTarif" );

    ergebnis_string_buffer.append( "\n + m_bewertungszahl_faktor_1           >" + m_bewertungszahl_faktor_1 + "<" );
    ergebnis_string_buffer.append( "\n + m_bonus_knz_bei_darlehensannahme    >" + m_bonus_knz_bei_darlehensannahme + "<" );
    ergebnis_string_buffer.append( "\n + m_bonus_knz_bei_darlverzicht        >" + m_bonus_knz_bei_darlverzicht + "<" );
    ergebnis_string_buffer.append( "\n + m_bonus_max_laufzeit_jahre          >" + m_bonus_max_laufzeit_jahre + "<" );
    ergebnis_string_buffer.append( "\n + m_bonus_min_bwz                     >" + m_bonus_min_bwz + "<" );
    ergebnis_string_buffer.append( "\n + m_bonus_min_laufzeit_monate         >" + m_bonus_min_laufzeit_monate + "<" );
    ergebnis_string_buffer.append( "\n + m_darlehensanspruch_art             >" + m_darlehensanspruch_art + "<" );
    ergebnis_string_buffer.append( "\n + m_darlehensanspruch_prozent         >" + m_darlehensanspruch_prozent + "<" );
    ergebnis_string_buffer.append( "\n + m_kto_gebuehr_pro_jahr              >" + m_kto_gebuehr_pro_jahr + "<" );
    ergebnis_string_buffer.append( "\n + m_kto_gebuehr_sparen_pro_jahr       >" + m_kto_gebuehr_sparen_pro_jahr + "<" );
    ergebnis_string_buffer.append( "\n + m_kto_gebuehr_tilgen_pro_jahr       >" + m_kto_gebuehr_tilgen_pro_jahr + "<" );
    ergebnis_string_buffer.append( "\n + m_kto_gebuehr_sparen_knz_ein        >" + m_kto_gebuehr_sparen_knz_ein + "<" );
    ergebnis_string_buffer.append( "\n + m_kto_gebuehr_tilgen_knz_ein        >" + m_kto_gebuehr_tilgen_knz_ein + "<" );
    ergebnis_string_buffer.append( "\n + m_mindest_bausparsumme              >" + m_mindest_bausparsumme + "<" );
    ergebnis_string_buffer.append( "\n + m_mindest_bewertungszahl            >" + m_mindest_bewertungszahl + "<" );
    ergebnis_string_buffer.append( "\n + m_mindest_sparzeit_in_monaten       >" + m_mindest_sparzeit_in_monaten + "<" );
    ergebnis_string_buffer.append( "\n + m_promille_abschlussgebuehr         >" + m_promille_abschlussgebuehr + "<" );
    ergebnis_string_buffer.append( "\n + m_prozent_guthabenzins              >" + m_prozent_guthabenzins + "<" );
    ergebnis_string_buffer.append( "\n + m_prozent_bonuszins                 >" + m_prozent_bonuszins + "<" );
    ergebnis_string_buffer.append( "\n + m_prozent_mindest_sparguthaben      >" + m_prozent_mindest_sparguthaben + "<" );
    ergebnis_string_buffer.append( "\n + m_wartezeit_in_monaten              >" + m_wartezeit_in_monaten + "<" );
    ergebnis_string_buffer.append( "\n + m_regel_sparbeitrag                 >" + m_regel_sparbeitrag + "<" );
    ergebnis_string_buffer.append( "\n + m_bsd_zinssatz                      >" + m_bsd_zinssatz + "<" );
    ergebnis_string_buffer.append( "\n + m_bsd_proz_darlehensgebuehr         >" + m_bsd_proz_darlehensgebuehr + "<" );
    ergebnis_string_buffer.append( "\n + m_bsd_proz_disagio                  >" + m_bsd_proz_disagio + "<" );
    ergebnis_string_buffer.append( "\n + m_knz_nztb_tarif                    >" + m_knz_nztb_tarif + "<" );

    /*
     * aktuelle Instanz von CalcZahlung aus den Listen Sonderzahlung und Sondertilgung
     */
    CalcBwzStaffel inst_bwz_staffel = null;

    /*
     * Pruefung: Instanz "m_list_bwz_staffel" gesetzt?
     */
    if ( m_list_bwz_staffel != null )
    {
      /*
       * Listenanzahl ermitteln
       */
      int calc_bwz_staffel_list_anzahl = this.getAnzahlBwzStaffel();

      /*
       * Aktuellen Listenindex deklarieren und auf 0 stellen
       */
      int calc_bwz_staffel_list_index = 0;

      /*
       * While Schleife ueber alle Elemente der Liste "m_list_bwz_staffel"
       */
      while ( calc_bwz_staffel_list_index < calc_bwz_staffel_list_anzahl )
      {
        /*
         * Aktuelle Instanz mit der Index-Instanz setzen
         */
        inst_bwz_staffel = this.getBwzStaffel( calc_bwz_staffel_list_index );

        /*
         * Pruefung: Aktuelle Instanz gesetzt?
         */
        if ( inst_bwz_staffel != null )
        {
          /*
           * Ist die Instanz gesetzt, wird der Ergebnisstring erweitert
           */
          ergebnis_string_buffer.append( "\n + m_list_bwz_staffel[" + calc_bwz_staffel_list_index + "] " + inst_bwz_staffel.toStringZeile() );
        }

        /*
         * Indexzaehler weiterstellen
         */
        calc_bwz_staffel_list_index++;
      }
    }

    return ergebnis_string_buffer.toString();
  }

  /**
   * Erstellt eine Stringrepraesentation in einer Zeile, wobei die Werte durch ein Trennzeichen getrennt werden
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcVorgabenTarif" in einer Zeile
   */
  public String toStringZeile()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( m_bewertungszahl_faktor_1 + "|" );
    ergebnis_string_buffer.append( m_bonus_knz_bei_darlehensannahme + "|" );
    ergebnis_string_buffer.append( m_bonus_knz_bei_darlverzicht + "|" );
    ergebnis_string_buffer.append( m_bonus_max_laufzeit_jahre + "|" );
    ergebnis_string_buffer.append( m_bonus_min_bwz + "|" );
    ergebnis_string_buffer.append( m_bonus_min_laufzeit_monate + "|" );
    ergebnis_string_buffer.append( m_darlehensanspruch_art + "|" );
    ergebnis_string_buffer.append( m_darlehensanspruch_prozent + "|" );
    ergebnis_string_buffer.append( m_kto_gebuehr_pro_jahr + "|" );
    ergebnis_string_buffer.append( m_kto_gebuehr_sparen_pro_jahr + "|" );
    ergebnis_string_buffer.append( m_kto_gebuehr_tilgen_pro_jahr + "|" );
    ergebnis_string_buffer.append( m_kto_gebuehr_sparen_knz_ein + "|" );
    ergebnis_string_buffer.append( m_kto_gebuehr_tilgen_knz_ein + "|" );
    ergebnis_string_buffer.append( m_mindest_bausparsumme + "|" );
    ergebnis_string_buffer.append( m_mindest_bewertungszahl + "|" );
    ergebnis_string_buffer.append( m_mindest_sparzeit_in_monaten + "|" );
    ergebnis_string_buffer.append( m_promille_abschlussgebuehr + "|" );
    ergebnis_string_buffer.append( m_prozent_guthabenzins + "|" );
    ergebnis_string_buffer.append( m_prozent_bonuszins + "|" );
    ergebnis_string_buffer.append( m_prozent_mindest_sparguthaben + "|" );
    ergebnis_string_buffer.append( m_wartezeit_in_monaten + "|" );
    ergebnis_string_buffer.append( m_regel_sparbeitrag + "|" );
    ergebnis_string_buffer.append( m_bsd_zinssatz + "|" );
    ergebnis_string_buffer.append( m_bsd_proz_darlehensgebuehr + "|" );
    ergebnis_string_buffer.append( m_bsd_proz_disagio + "|" );
    ergebnis_string_buffer.append( m_knz_nztb_tarif + "|" );
    ergebnis_string_buffer.append( m_tarif_kto_gebuehr_sparen_pro_jahr + "|" );
    ergebnis_string_buffer.append( m_tarif_kto_gebuehr_tilgen_pro_jahr + "|" );
    ergebnis_string_buffer.append( m_tarif_kto_gebuehr_sparen_knz_ein + "|" );
    ergebnis_string_buffer.append( m_tarif_kto_gebuehr_tilten_knz_ein + "|" );

    return ergebnis_string_buffer.toString();
  }

  public String getDebugString()
  {
    return "#funktionsname( \"" + getBewertungszahlFaktor1() + "\", \"" + getBonusKnzBeiDarlehensannahme() + "\", \"" + getBonusKnzBeiDarlverzicht() + "\", \"" + getBonusMaxLaufzeitJahre() + "\", \"" + getBonusMinBwz() + "\", \"" + getBonusMinLaufzeitMonate() + "\", \"" + getDarlehensanspruchArt() + "\", \"" + getDarlehensanspruchProzent() + "\", \"" + getKtoGebuehrProJahr() + "\", \"" + getKtoGebuehrSparenProJahr() + "\", \"" + getKtoGebuehrTilgenProJahr() + "\", \"" + getKtoGebuehrSparenKnzEin() + "\", \"" + getKtoGebuehrTilgenKnzEin() + "\", \"" + getMindestBausparsumme() + "\", \"" + getMindestBewertungszahl() + "\", \"" + getMindestSparzeitInMonaten() + "\", \"" + getPromilleAbschlussgebuehr() + "\", \"" + getProzentGuthabenzins() + "\", \"" + getProzentBonuszins() + "\", \"" + getProzentMindestSparguthaben() + "\", \"" + getWartezeitInMonaten() + "\", \"" + getRegelSparbeitrag() + "\", \"" + getBsdZinssatz() + "\", \"" + getBsdProzDarlehensgebuehr() + "\", \"" + getBsdProzDisagio() + "\", \"" + getKnzNztbTarif() + "\", \"" + getTarifKtoGebuehrSparenProJahr() + "\", \"" + getTarifKtoGebuehrTilgenProJahr() + "\", \"" + getTarifKtoGebuehrSparenKnzEin() + "\", \"" + getTarifKtoGebuehrTiltenKnzEin() + "\", \"\" );";
  }

  /**
   * Liefert eine Kopie dieser Klasse wieder
   *
   * @return  eine Instanz von "CalcVorgabenTarif" mit den Daten dieser Instanz
   */
  public CalcVorgabenTarif getCopy()
  {
    CalcVorgabenTarif quelle_inst_calc_vorgaben_tarif_copy = this;

    CalcVorgabenTarif ziel_inst_calc_vorgaben_tarif_copy = new CalcVorgabenTarif();

    ziel_inst_calc_vorgaben_tarif_copy.setBewertungszahlFaktor1( quelle_inst_calc_vorgaben_tarif_copy.getBewertungszahlFaktor1() );
    ziel_inst_calc_vorgaben_tarif_copy.setBonusKnzBeiDarlehensannahme( quelle_inst_calc_vorgaben_tarif_copy.getBonusKnzBeiDarlehensannahme() );
    ziel_inst_calc_vorgaben_tarif_copy.setBonusKnzBeiDarlverzicht( quelle_inst_calc_vorgaben_tarif_copy.getBonusKnzBeiDarlverzicht() );
    ziel_inst_calc_vorgaben_tarif_copy.setBonusMaxLaufzeitJahre( quelle_inst_calc_vorgaben_tarif_copy.getBonusMaxLaufzeitJahre() );
    ziel_inst_calc_vorgaben_tarif_copy.setBonusMinBwz( quelle_inst_calc_vorgaben_tarif_copy.getBonusMinBwz() );
    ziel_inst_calc_vorgaben_tarif_copy.setBonusMinLaufzeitMonate( quelle_inst_calc_vorgaben_tarif_copy.getBonusMinLaufzeitMonate() );
    ziel_inst_calc_vorgaben_tarif_copy.setDarlehensanspruchArt( quelle_inst_calc_vorgaben_tarif_copy.getDarlehensanspruchArt() );
    ziel_inst_calc_vorgaben_tarif_copy.setDarlehensanspruchProzent( quelle_inst_calc_vorgaben_tarif_copy.getDarlehensanspruchProzent() );
    ziel_inst_calc_vorgaben_tarif_copy.setKtoGebuehrProJahr( quelle_inst_calc_vorgaben_tarif_copy.getKtoGebuehrProJahr() );
    ziel_inst_calc_vorgaben_tarif_copy.setKtoGebuehrSparenProJahr( quelle_inst_calc_vorgaben_tarif_copy.getKtoGebuehrSparenProJahr() );
    ziel_inst_calc_vorgaben_tarif_copy.setKtoGebuehrTilgenProJahr( quelle_inst_calc_vorgaben_tarif_copy.getKtoGebuehrTilgenProJahr() );
    ziel_inst_calc_vorgaben_tarif_copy.setKtoGebuehrSparenKnzEin( quelle_inst_calc_vorgaben_tarif_copy.getKtoGebuehrSparenKnzEin() );
    ziel_inst_calc_vorgaben_tarif_copy.setKtoGebuehrTilgenKnzEin( quelle_inst_calc_vorgaben_tarif_copy.getKtoGebuehrTilgenKnzEin() );
    ziel_inst_calc_vorgaben_tarif_copy.setMindestBausparsumme( quelle_inst_calc_vorgaben_tarif_copy.getMindestBausparsumme() );
    ziel_inst_calc_vorgaben_tarif_copy.setMindestBewertungszahl( quelle_inst_calc_vorgaben_tarif_copy.getMindestBewertungszahl() );
    ziel_inst_calc_vorgaben_tarif_copy.setMindestSparzeitInMonaten( quelle_inst_calc_vorgaben_tarif_copy.getMindestSparzeitInMonaten() );
    ziel_inst_calc_vorgaben_tarif_copy.setPromilleAbschlussgebuehr( quelle_inst_calc_vorgaben_tarif_copy.getPromilleAbschlussgebuehr() );
    ziel_inst_calc_vorgaben_tarif_copy.setProzentGuthabenzins( quelle_inst_calc_vorgaben_tarif_copy.getProzentGuthabenzins() );
    ziel_inst_calc_vorgaben_tarif_copy.setProzentBonuszins( quelle_inst_calc_vorgaben_tarif_copy.getProzentBonuszins() );
    ziel_inst_calc_vorgaben_tarif_copy.setProzentMindestSparguthaben( quelle_inst_calc_vorgaben_tarif_copy.getProzentMindestSparguthaben() );
    ziel_inst_calc_vorgaben_tarif_copy.setWartezeitInMonaten( quelle_inst_calc_vorgaben_tarif_copy.getWartezeitInMonaten() );
    ziel_inst_calc_vorgaben_tarif_copy.setRegelSparbeitrag( quelle_inst_calc_vorgaben_tarif_copy.getRegelSparbeitrag() );
    ziel_inst_calc_vorgaben_tarif_copy.setBsdZinssatz( quelle_inst_calc_vorgaben_tarif_copy.getBsdZinssatz() );
    ziel_inst_calc_vorgaben_tarif_copy.setBsdProzDarlehensgebuehr( quelle_inst_calc_vorgaben_tarif_copy.getBsdProzDarlehensgebuehr() );
    ziel_inst_calc_vorgaben_tarif_copy.setBsdProzDisagio( quelle_inst_calc_vorgaben_tarif_copy.getBsdProzDisagio() );
    ziel_inst_calc_vorgaben_tarif_copy.setKnzNztbTarif( quelle_inst_calc_vorgaben_tarif_copy.getKnzNztbTarif() );
    ziel_inst_calc_vorgaben_tarif_copy.setTarifKtoGebuehrSparenProJahr( quelle_inst_calc_vorgaben_tarif_copy.getTarifKtoGebuehrSparenProJahr() );
    ziel_inst_calc_vorgaben_tarif_copy.setTarifKtoGebuehrTilgenProJahr( quelle_inst_calc_vorgaben_tarif_copy.getTarifKtoGebuehrTilgenProJahr() );
    ziel_inst_calc_vorgaben_tarif_copy.setTarifKtoGebuehrSparenKnzEin( quelle_inst_calc_vorgaben_tarif_copy.getTarifKtoGebuehrSparenKnzEin() );
    ziel_inst_calc_vorgaben_tarif_copy.setTarifKtoGebuehrTiltenKnzEin( quelle_inst_calc_vorgaben_tarif_copy.getTarifKtoGebuehrTiltenKnzEin() );

    return ziel_inst_calc_vorgaben_tarif_copy;
  }

  /**
   * <pre>
   * Erstellt den JSON-String
   *
   *   "calc_vorgaben_tarif": 
   *     {
   *       "bewertungszahl_faktor_1"           : 0.0d,
   *       "bonus_knz_bei_darlehensannahme"    : true,
   *       "bonus_knz_bei_darlverzicht"        : true,
   *       "bonus_max_laufzeit_jahre"          : 0,
   *       "bonus_min_bwz"                     : 0.0d,
   *       "bonus_min_laufzeit_monate"         : 0,
   *       "darlehensanspruch_art"             : "ABC.DEF.GHI",
   *       "darlehensanspruch_prozent"         : 0.0d,
   *       "kto_gebuehr_pro_jahr"              : 0.0d,
   *       "kto_gebuehr_sparen_pro_jahr"       : 0.0d,
   *       "kto_gebuehr_tilgen_pro_jahr"       : 0.0d,
   *       "kto_gebuehr_sparen_knz_ein"        : true,
   *       "kto_gebuehr_tilgen_knz_ein"        : true,
   *       "mindest_bausparsumme"              : 0.0d,
   *       "mindest_bewertungszahl"            : 0.0d,
   *       "mindest_sparzeit_in_monaten"       : 0,
   *       "promille_abschlussgebuehr"         : 0.0d,
   *       "prozent_guthabenzins"              : 0.0d,
   *       "prozent_bonuszins"                 : 0.0d,
   *       "prozent_mindest_sparguthaben"      : 0.0d,
   *       "wartezeit_in_monaten"              : 0,
   *       "regel_sparbeitrag"                 : 0.0d,
   *       "bsd_zinssatz"                      : 0.0d,
   *       "bsd_proz_darlehensgebuehr"         : 0.0d,
   *       "bsd_proz_disagio"                  : 0.0d,
   *       "knz_nztb_tarif"                    : true,
   *       "tarif_kto_gebuehr_sparen_pro_jahr" : 0.0d,
   *       "tarif_kto_gebuehr_tilgen_pro_jahr" : 0.0d,
   *       "tarif_kto_gebuehr_sparen_knz_ein"  : true,
   *       "tarif_kto_gebuehr_tilten_knz_ein"  : true
   *     }
   *
   * </pre>
   *
   * @return  den erstellten JSON-String
   */
  public String toJSON()
  {
    String json_string = "";

    json_string = "{";
    json_string += "\"bewertungszahl_faktor_1\":" + FkJson.getStringJson( m_bewertungszahl_faktor_1 ) + " ,";
    json_string += "\"bonus_knz_bei_darlehensannahme\":\"" + FkJson.getStringJson( m_bonus_knz_bei_darlehensannahme ) + "\",";
    json_string += "\"bonus_knz_bei_darlverzicht\":\"" + FkJson.getStringJson( m_bonus_knz_bei_darlverzicht ) + "\",";
    json_string += "\"bonus_max_laufzeit_jahre\":" + FkJson.getStringJson( m_bonus_max_laufzeit_jahre ) + " ,";
    json_string += "\"bonus_min_bwz\":" + FkJson.getStringJson( m_bonus_min_bwz ) + " ,";
    json_string += "\"bonus_min_laufzeit_monate\":" + FkJson.getStringJson( m_bonus_min_laufzeit_monate ) + " ,";
    json_string += "\"darlehensanspruch_art\":\"" + FkJson.getStringJson( m_darlehensanspruch_art ) + "\",";
    json_string += "\"darlehensanspruch_prozent\":" + FkJson.getStringJson( m_darlehensanspruch_prozent ) + " ,";
    json_string += "\"kto_gebuehr_pro_jahr\":" + FkJson.getStringJson( m_kto_gebuehr_pro_jahr ) + " ,";
    json_string += "\"kto_gebuehr_sparen_pro_jahr\":" + FkJson.getStringJson( m_kto_gebuehr_sparen_pro_jahr ) + " ,";
    json_string += "\"kto_gebuehr_tilgen_pro_jahr\":" + FkJson.getStringJson( m_kto_gebuehr_tilgen_pro_jahr ) + " ,";
    json_string += "\"kto_gebuehr_sparen_knz_ein\":\"" + FkJson.getStringJson( m_kto_gebuehr_sparen_knz_ein ) + "\",";
    json_string += "\"kto_gebuehr_tilgen_knz_ein\":\"" + FkJson.getStringJson( m_kto_gebuehr_tilgen_knz_ein ) + "\",";
    json_string += "\"mindest_bausparsumme\":" + FkJson.getStringJson( m_mindest_bausparsumme ) + " ,";
    json_string += "\"mindest_bewertungszahl\":" + FkJson.getStringJson( m_mindest_bewertungszahl ) + " ,";
    json_string += "\"mindest_sparzeit_in_monaten\":" + FkJson.getStringJson( m_mindest_sparzeit_in_monaten ) + " ,";
    json_string += "\"promille_abschlussgebuehr\":" + FkJson.getStringJson( m_promille_abschlussgebuehr ) + " ,";
    json_string += "\"prozent_guthabenzins\":" + FkJson.getStringJson( m_prozent_guthabenzins ) + " ,";
    json_string += "\"prozent_bonuszins\":" + FkJson.getStringJson( m_prozent_bonuszins ) + " ,";
    json_string += "\"prozent_mindest_sparguthaben\":" + FkJson.getStringJson( m_prozent_mindest_sparguthaben ) + " ,";
    json_string += "\"wartezeit_in_monaten\":" + FkJson.getStringJson( m_wartezeit_in_monaten ) + " ,";
    json_string += "\"regel_sparbeitrag\":" + FkJson.getStringJson( m_regel_sparbeitrag ) + " ,";
    json_string += "\"bsd_zinssatz\":" + FkJson.getStringJson( m_bsd_zinssatz ) + " ,";
    json_string += "\"bsd_proz_darlehensgebuehr\":" + FkJson.getStringJson( m_bsd_proz_darlehensgebuehr ) + " ,";
    json_string += "\"bsd_proz_disagio\":" + FkJson.getStringJson( m_bsd_proz_disagio ) + " ,";
    json_string += "\"knz_nztb_tarif\":\"" + FkJson.getStringJson( m_knz_nztb_tarif ) + "\",";
    json_string += "\"tarif_kto_gebuehr_sparen_pro_jahr\":" + FkJson.getStringJson( m_tarif_kto_gebuehr_sparen_pro_jahr ) + " ,";
    json_string += "\"tarif_kto_gebuehr_tilgen_pro_jahr\":" + FkJson.getStringJson( m_tarif_kto_gebuehr_tilgen_pro_jahr ) + " ,";
    json_string += "\"tarif_kto_gebuehr_sparen_knz_ein\":\"" + FkJson.getStringJson( m_tarif_kto_gebuehr_sparen_knz_ein ) + "\",";
    json_string += "\"tarif_kto_gebuehr_tilten_knz_ein\":\"" + FkJson.getStringJson( m_tarif_kto_gebuehr_tilten_knz_ein ) + "\"";
    json_string += "}";

    return json_string;
  }

  /**
   * Setzt alle Membervariablen auf "null"
   */
  public void clear()
  {
    m_bewertungszahl_faktor_1 = 0.0;
    m_bonus_knz_bei_darlehensannahme = false;
    m_bonus_knz_bei_darlverzicht = false;
    m_bonus_max_laufzeit_jahre = 0;
    m_bonus_min_bwz = 0.0;
    m_bonus_min_laufzeit_monate = 0;
    m_darlehensanspruch_art = null;
    m_darlehensanspruch_prozent = 0.0;
    m_kto_gebuehr_pro_jahr = 0.0;
    m_kto_gebuehr_sparen_pro_jahr = 0.0;
    m_kto_gebuehr_tilgen_pro_jahr = 0.0;
    m_kto_gebuehr_sparen_knz_ein = false;
    m_kto_gebuehr_tilgen_knz_ein = false;
    m_mindest_bausparsumme = 0.0;
    m_mindest_bewertungszahl = 0.0;
    m_mindest_sparzeit_in_monaten = 0;
    m_promille_abschlussgebuehr = 0.0;
    m_prozent_guthabenzins = 0.0;
    m_prozent_bonuszins = 0.0;
    m_prozent_mindest_sparguthaben = 0.0;
    m_wartezeit_in_monaten = 0;
    m_regel_sparbeitrag = 0.0;
    m_bsd_zinssatz = 0.0;
    m_bsd_proz_darlehensgebuehr = 0.0;
    m_bsd_proz_disagio = 0.0;
    m_knz_nztb_tarif = false;
    m_tarif_kto_gebuehr_sparen_pro_jahr = 0.0;
    m_tarif_kto_gebuehr_tilgen_pro_jahr = 0.0;
    m_tarif_kto_gebuehr_sparen_knz_ein = false;
    m_tarif_kto_gebuehr_tilten_knz_ein = false;
  }

  /**
   * Eine Liste mit Instanzen der Klasse "CalcBwzStaffel".
   */
  private List m_list_bwz_staffel = null;

  /**
   * @param pCalcBwzStaffel die hinzuzufuegende Instanz
   */
  public void addBwzStaffel( CalcBwzStaffel pCalcBwzStaffel )
  {
    getListBwzStaffel().add( pCalcBwzStaffel );
  }

  public void addBwzStaffel( double pVon, double pBis, double pBetrag )
  {
    CalcBwzStaffel inst_bwz_staffel = null;

    inst_bwz_staffel = new CalcBwzStaffel();

    inst_bwz_staffel.setBwzVon( pVon );
    inst_bwz_staffel.setBwzBis( pBis );
    inst_bwz_staffel.setZtb( pBetrag );

    getListBwzStaffel().add( inst_bwz_staffel );
  }

  /**
   * @return eine Instanz von java.util.List 
   */
  public List getListBwzStaffel()
  {
    /*
     * Pruefung: Instanz "m_list_bwz_staffel" noch null?
     * Ist die Instanz noch nicht gesetzt, wird dieses gemacht.
     */
    if ( m_list_bwz_staffel == null )
    {
      m_list_bwz_staffel = new ArrayList();
    }

    return m_list_bwz_staffel;
  }

  /**
   * @param pIndex der Index in der Liste
   * @return eine Klasse vom Typ "CalcBwzStaffel" oder null, wenn der Index nicht vorhanden ist
   */
  public CalcBwzStaffel getBwzStaffel( int pIndex )
  {
    /*
     * Pruefung: Instanz "m_list_bwz_staffel" gesetzt?
     */
    if ( m_list_bwz_staffel != null )
    {
      try
      {
        /*
         * Ist eine Instanz der Liste vorhanden, wird versucht den 
         * uebergebenen Index zurueckzugeben. 
         * 
         * Existiert der Index, wird dieser auf die Klasse "CalcBwzStaffel" gecastet und zurueckgegeben
         * 
         * Existiert der Index nicht, kommt es zu einer Exception und 
         * der Aufrufer bekommt null zurueck
         */
        return CalcBwzStaffel.getClassCast( m_list_bwz_staffel.get( pIndex ) );
      }
      catch ( Exception exp )
      {
        //
      }
    }

    return null;
  }

  /**
   * @return die Anzahl der in der Liste vorhandenen Instanzen, oder 0 wenn die Liste nicht instanziiert ist.
   */
  public int getAnzahlBwzStaffel()
  {
    if ( m_list_bwz_staffel == null )
    {
      return 0;
    }

    return m_list_bwz_staffel.size();
  }

  public boolean istDarlehensanspruchArtFest()
  {
    return ( ( m_darlehensanspruch_art != null ) && ( m_darlehensanspruch_art.equalsIgnoreCase( "FEST" ) ) );

  }

  public boolean istDarlehensanspruchArtVariabel()
  {
    return ( ( m_darlehensanspruch_art != null ) && ( m_darlehensanspruch_art.equalsIgnoreCase( "VARIABEL" ) ) );

  }
}