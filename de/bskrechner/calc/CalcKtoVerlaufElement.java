package de.bskrechner.calc;

import de.bskrechner.util.FkDatumLong;
import de.bskrechner.util.FkJson;
import de.bskrechner.util.FkString;
import de.bskrechner.util.FkZahl;

/**
 * <pre>
 * Java-Klasse "CalcKtoVerlaufElement".
 * </pre>
 */
public class CalcKtoVerlaufElement
{
  /**
   * <pre>
   * Fuehrt einen Class-Cast auf die Klasse "CalcKtoVerlaufElement" auf das uebergebene Objekt aus. 
   *
   * Im Falle einer ClassCastException wird "null" zurueckgegeben.
   * Ist das Objekt selber "null" wird "null" zurueckgegeben.
   *
   * CalcKtoVerlaufElement var_instanz = CalcKtoVerlaufElement.getClassCast( java_object );
   *
   * if ( var_instanz == null )
   * {
   *   str_fehler = "Die Instanz \"java_object\" ist keine Instanz der Klasse \"CalcKtoVerlaufElement\" ";
   *
   *   return;
   * }
   *
   * </pre>
   *
   * @param  pObjekt das zu castende Objekt
   * @return  das Objekt als Instanz von "CalcKtoVerlaufElement", oder "null" im Fehlerfall
   */
  public static CalcKtoVerlaufElement getClassCast( Object pObjekt )
  {
    /*
     * Pruefung: pObjekt ungleich null?
     */
    if ( pObjekt != null )
    {
      try
      {
        return (CalcKtoVerlaufElement) pObjekt;
      }
      catch ( ClassCastException err_inst )
      {
        // keine Aktion im Fehlerfall, da Rueckgabe von null
      }
    }

    return null;
  }

  /**
   * @return  Einen String fuer eine Uerberschriftenzeile
   */
  public static String toStringZeileUeberschrift()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( "Anzahl Zinstage" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Art" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Auszahlung" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Betrag Salden Summe" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Bwz" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Einzahlung Neu" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Einzahlung Reset" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Gebuehr" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Foerderung" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Kontostand" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Tilg Anteil" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Uj Bonus Zins" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Uj Guthabenzins" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Zins Anteil" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Betrag Zinsgutschrift" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bezeichnung" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bonus Zinsen" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bsd Info Tilg Anteil" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bsd Info Zins Anteil" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Datum" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Knz Aktive Zahlung" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Knz Loeschung" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Sort Id" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Str Art" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Element Id" );
    ergebnis_string_buffer.append( "|" );

    return ergebnis_string_buffer.toString();
  }

  private long    m_anzahl_zinstage         = 0;

  private long    m_art                     = 0;

  private double  m_betrag_auszahlung       = 0.0;

  private double  m_betrag_salden_summe     = 0.0;

  private double  m_betrag_bwz              = 0.0;

  private double  m_betrag_einzahlung_neu   = 0.0;

  private double  m_betrag_einzahlung_reset = 0.0;

  private double  m_betrag_gebuehr          = 0.0;

  private double  m_betrag_foerderung       = 0.0;

  private double  m_betrag_kontostand       = 0.0;

  private double  m_betrag_tilg_anteil      = 0.0;

  private double  m_betrag_uj_bonus_zins    = 0.0;

  private double  m_betrag_uj_guthabenzins  = 0.0;

  private double  m_betrag_zins_anteil      = 0.0;

  private double  m_betrag_zinsgutschrift   = 0.0;

  private double  m_bezeichnung             = 0.0;

  private double  m_bonus_zinsen            = 0.0;

  private double  m_bsd_info_tilg_anteil    = 0.0;

  private double  m_bsd_info_zins_anteil    = 0.0;

  private long    m_datum                   = 0;

  private boolean m_knz_aktive_zahlung      = true;

  private int     m_knz_loeschung           = 0;

  private String  m_sort_id                 = null;

  private String  m_str_art                 = null;

  private String  m_element_id              = null;

  /**
   * <pre>
   * Setzt den Wert der Variablen m_anzahl_zinstage.
   * </pre>
   * 
   * @param  pAnzahlZinstage der zu setzende Wert als String (wird zu int gewandelt)
   */
  public void setAnzahlZinstage( long pAnzahlZinstage )
  {
    m_anzahl_zinstage = pAnzahlZinstage;
  }

  public void setArt( long pArt )
  {
    m_art = pArt;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_auszahlung.
   * </pre>
   * 
   * @param  pBetragAuszahlung der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragAuszahlung( String pBetragAuszahlung )
  {
    m_betrag_auszahlung = FkZahl.getDouble( pBetragAuszahlung, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_auszahlung.
   * </pre>
   * 
   * @param  pBetragAuszahlung der zu setzende Wert
   */
  public void setBetragAuszahlung( double pBetragAuszahlung )
  {
    m_betrag_auszahlung = pBetragAuszahlung;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_salden_summe.
   * </pre>
   * 
   * @param  pBetragSaldenSumme der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragSaldenSumme( String pBetragSaldenSumme )
  {
    m_betrag_salden_summe = FkZahl.getDouble( pBetragSaldenSumme, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_salden_summe.
   * </pre>
   * 
   * @param  pBetragSaldenSumme der zu setzende Wert
   */
  public void setBetragSaldenSumme( double pBetragSaldenSumme )
  {
    m_betrag_salden_summe = pBetragSaldenSumme;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_bwz.
   * </pre>
   * 
   * @param  pBetragBwz  der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragBwz( String pBetragBwz )
  {
    m_betrag_bwz = FkZahl.getDouble( pBetragBwz, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_bwz.
   * </pre>
   * 
   * @param  pBetragBwz  der zu setzende Wert
   */
  public void setBetragBwz( double pBetragBwz )
  {
    m_betrag_bwz = pBetragBwz;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_einzahlung_neu.
   * </pre>
   * 
   * @param  pBetragEinzahlungNeu der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragEinzahlungNeu( String pBetragEinzahlungNeu )
  {
    m_betrag_einzahlung_neu = FkZahl.getDouble( pBetragEinzahlungNeu, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_einzahlung_neu.
   * </pre>
   * 
   * @param  pBetragEinzahlungNeu der zu setzende Wert
   */
  public void setBetragEinzahlungNeu( double pBetragEinzahlungNeu )
  {
    m_betrag_einzahlung_neu = pBetragEinzahlungNeu;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_einzahlung_reset.
   * </pre>
   * 
   * @param  pBetragEinzahlungReset der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragEinzahlungReset( String pBetragEinzahlungReset )
  {
    m_betrag_einzahlung_reset = FkZahl.getDouble( pBetragEinzahlungReset, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_einzahlung_reset.
   * </pre>
   * 
   * @param  pBetragEinzahlungReset der zu setzende Wert
   */
  public void setBetragEinzahlungReset( double pBetragEinzahlungReset )
  {
    m_betrag_einzahlung_reset = pBetragEinzahlungReset;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_gebuehr.
   * </pre>
   * 
   * @param  pBetragGebuehr der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragGebuehr( String pBetragGebuehr )
  {
    m_betrag_gebuehr = FkZahl.getDouble( pBetragGebuehr, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_gebuehr.
   * </pre>
   * 
   * @param  pBetragGebuehr der zu setzende Wert
   */
  public void setBetragGebuehr( double pBetragGebuehr )
  {
    m_betrag_gebuehr = pBetragGebuehr;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_foerderung.
   * </pre>
   * 
   * @param  pBetragFoerderung der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragFoerderung( String pBetragFoerderung )
  {
    m_betrag_foerderung = FkZahl.getDouble( pBetragFoerderung, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_foerderung.
   * </pre>
   * 
   * @param  pBetragFoerderung der zu setzende Wert
   */
  public void setBetragFoerderung( double pBetragFoerderung )
  {
    m_betrag_foerderung = pBetragFoerderung;
  }

  public void setBetragEinzahlung( double pBetrag )
  {
    m_betrag_einzahlung_neu = pBetrag;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_kontostand.
   * </pre>
   * 
   * @param  pBetragKontostand der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragKontostand( String pBetragKontostand )
  {
    m_betrag_kontostand = FkZahl.getDouble( pBetragKontostand, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_kontostand.
   * </pre>
   * 
   * @param  pBetragKontostand der zu setzende Wert
   */
  public void setBetragKontostand( double pBetragKontostand )
  {
    m_betrag_kontostand = pBetragKontostand;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_tilg_anteil.
   * </pre>
   * 
   * @param  pBetragTilgAnteil der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragTilgAnteil( String pBetragTilgAnteil )
  {
    m_betrag_tilg_anteil = FkZahl.getDouble( pBetragTilgAnteil, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_tilg_anteil.
   * </pre>
   * 
   * @param  pBetragTilgAnteil der zu setzende Wert
   */
  public void setBetragTilgAnteil( double pBetragTilgAnteil )
  {
    m_betrag_tilg_anteil = pBetragTilgAnteil;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_uj_bonus_zins.
   * </pre>
   * 
   * @param  pBetragUjBonusZins der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragUjBonusZins( String pBetragUjBonusZins )
  {
    m_betrag_uj_bonus_zins = FkZahl.getDouble( pBetragUjBonusZins, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_uj_bonus_zins.
   * </pre>
   * 
   * @param  pBetragUjBonusZins der zu setzende Wert
   */
  public void setBetragUjBonusZins( double pBetragUjBonusZins )
  {
    m_betrag_uj_bonus_zins = pBetragUjBonusZins;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_uj_guthabenzins.
   * </pre>
   * 
   * @param  pBetragUjGuthabenzins der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragUjGuthabenzins( String pBetragUjGuthabenzins )
  {
    m_betrag_uj_guthabenzins = FkZahl.getDouble( pBetragUjGuthabenzins, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_uj_guthabenzins.
   * </pre>
   * 
   * @param  pBetragUjGuthabenzins der zu setzende Wert
   */
  public void setBetragUjGuthabenzins( double pBetragUjGuthabenzins )
  {
    m_betrag_uj_guthabenzins = pBetragUjGuthabenzins;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_zins_anteil.
   * </pre>
   * 
   * @param  pBetragZinsAnteil der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragZinsAnteil( String pBetragZinsAnteil )
  {
    m_betrag_zins_anteil = FkZahl.getDouble( pBetragZinsAnteil, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_zins_anteil.
   * </pre>
   * 
   * @param  pBetragZinsAnteil der zu setzende Wert
   */
  public void setBetragZinsAnteil( double pBetragZinsAnteil )
  {
    m_betrag_zins_anteil = pBetragZinsAnteil;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_zinsgutschrift.
   * </pre>
   * 
   * @param  pBetragZinsgutschrift der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBetragZinsgutschrift( String pBetragZinsgutschrift )
  {
    m_betrag_zinsgutschrift = FkZahl.getDouble( pBetragZinsgutschrift, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag_zinsgutschrift.
   * </pre>
   * 
   * @param  pBetragZinsgutschrift der zu setzende Wert
   */
  public void setBetragZinsgutschrift( double pBetragZinsgutschrift )
  {
    m_betrag_zinsgutschrift = pBetragZinsgutschrift;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bezeichnung.
   * </pre>
   * 
   * @param  pBezeichnung der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBezeichnung( String pBezeichnung )
  {
    m_bezeichnung = FkZahl.getDouble( pBezeichnung, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bezeichnung.
   * </pre>
   * 
   * @param  pBezeichnung der zu setzende Wert
   */
  public void setBezeichnung( double pBezeichnung )
  {
    m_bezeichnung = pBezeichnung;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_zinsen.
   * </pre>
   * 
   * @param  pBonusZinsen der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBonusZinsen( String pBonusZinsen )
  {
    m_bonus_zinsen = FkZahl.getDouble( pBonusZinsen, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bonus_zinsen.
   * </pre>
   * 
   * @param  pBonusZinsen der zu setzende Wert
   */
  public void setBonusZinsen( double pBonusZinsen )
  {
    m_bonus_zinsen = pBonusZinsen;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_info_tilg_anteil.
   * </pre>
   * 
   * @param  pBsdInfoTilgAnteil der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBsdInfoTilgAnteil( String pBsdInfoTilgAnteil )
  {
    m_bsd_info_tilg_anteil = FkZahl.getDouble( pBsdInfoTilgAnteil, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_info_tilg_anteil.
   * </pre>
   * 
   * @param  pBsdInfoTilgAnteil der zu setzende Wert
   */
  public void setBsdInfoTilgAnteil( double pBsdInfoTilgAnteil )
  {
    m_bsd_info_tilg_anteil = pBsdInfoTilgAnteil;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_info_zins_anteil.
   * </pre>
   * 
   * @param  pBsdInfoZinsAnteil der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBsdInfoZinsAnteil( String pBsdInfoZinsAnteil )
  {
    m_bsd_info_zins_anteil = FkZahl.getDouble( pBsdInfoZinsAnteil, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bsd_info_zins_anteil.
   * </pre>
   * 
   * @param  pBsdInfoZinsAnteil der zu setzende Wert
   */
  public void setBsdInfoZinsAnteil( double pBsdInfoZinsAnteil )
  {
    m_bsd_info_zins_anteil = pBsdInfoZinsAnteil;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_datum.
   * </pre>
   * 
   * @param  pDatum      der zu setzende Wert als String (wird zu int gewandelt)
   */
  public void setDatum( String pDatum )
  {
    m_datum = FkZahl.getInteger( pDatum, 0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_datum.
   * </pre>
   * 
   * @param  pDatum      der zu setzende Wert
   */
  public void setDatum( long pDatum )
  {
    m_datum = pDatum;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_knz_aktive_zahlung.
   * </pre>
   * 
   * @param  pKnzAktiveZahlung der zu setzende Wert als String (wird zu boolean gewandelt)
   */
  public void setKnzAktiveZahlung( String pKnzAktiveZahlung )
  {
    m_knz_aktive_zahlung = FkString.getBoolean( pKnzAktiveZahlung, false );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_knz_aktive_zahlung.
   * </pre>
   * 
   * @param  pKnzAktiveZahlung der zu setzende Wert
   */
  public void setKnzAktiveZahlung( boolean pKnzAktiveZahlung )
  {
    m_knz_aktive_zahlung = pKnzAktiveZahlung;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_knz_loeschung.
   * </pre>
   * 
   * @param  pKnzLoeschung der zu setzende Wert als String (wird zu int gewandelt)
   */
  public void setKnzLoeschung( String pKnzLoeschung )
  {
    m_knz_loeschung = FkZahl.getInteger( pKnzLoeschung, 0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_knz_loeschung.
   * </pre>
   * 
   * @param  pKnzLoeschung der zu setzende Wert
   */
  public void setKnzLoeschung( int pKnzLoeschung )
  {
    m_knz_loeschung = pKnzLoeschung;
  }

  public void resetBetragEinzahlung()
  {
    m_betrag_einzahlung_neu = m_betrag_einzahlung_reset;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_sort_id.
   * </pre>
   * 
   * @param  pSortId     der zu setzende Wert
   */
  public void setSortId( String pSortId )
  {
    m_sort_id = pSortId;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_str_art.
   * </pre>
   * 
   * @param  pStrArt     der zu setzende Wert
   */
  public void setStrArt( String pStrArt )
  {
    m_str_art = pStrArt;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_element_id.
   * </pre>
   * 
   * @param  pElementId  der zu setzende Wert
   */
  public void setElementId( String pElementId )
  {
    m_element_id = pElementId;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_anzahl_zinstage.
   * </pre>
   *  
   * @return den Wert der Variablen "m_anzahl_zinstage"
   */
  public long getAnzahlZinstage()
  {
    return m_anzahl_zinstage;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_art.
   * </pre>
   *  
   * @return den Wert der Variablen "m_art"
   */
  public long getArt()
  {
    return m_art;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_auszahlung.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_auszahlung"
   */
  public double getBetragAuszahlung()
  {
    return m_betrag_auszahlung;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_salden_summe.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_salden_summe"
   */
  public double getBetragSaldenSumme()
  {
    return m_betrag_salden_summe;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_bwz.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_bwz"
   */
  public double getBetragBwz()
  {
    return m_betrag_bwz;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_einzahlung_neu.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_einzahlung_neu"
   */
  public double getBetragEinzahlungNeu()
  {
    return m_betrag_einzahlung_neu;
  }

  public double getBetragEinzahlung()
  {
    return m_betrag_einzahlung_neu;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_einzahlung_reset.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_einzahlung_reset"
   */
  public double getBetragEinzahlungReset()
  {
    return m_betrag_einzahlung_reset;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_gebuehr.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_gebuehr"
   */
  public double getBetragGebuehr()
  {
    return m_betrag_gebuehr;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_foerderung.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_foerderung"
   */
  public double getBetragFoerderung()
  {
    return m_betrag_foerderung;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_kontostand.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_kontostand"
   */
  public double getBetragKontostand()
  {
    return m_betrag_kontostand;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_tilg_anteil.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_tilg_anteil"
   */
  public double getBetragTilgAnteil()
  {
    return m_betrag_tilg_anteil;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_uj_bonus_zins.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_uj_bonus_zins"
   */
  public double getBetragUjBonusZins()
  {
    return m_betrag_uj_bonus_zins;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_uj_guthabenzins.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_uj_guthabenzins"
   */
  public double getBetragUjGuthabenzins()
  {
    return m_betrag_uj_guthabenzins;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_zins_anteil.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_zins_anteil"
   */
  public double getBetragZinsAnteil()
  {
    return m_betrag_zins_anteil;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag_zinsgutschrift.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag_zinsgutschrift"
   */
  public double getBetragZinsgutschrift()
  {
    return m_betrag_zinsgutschrift;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bezeichnung.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bezeichnung"
   */
  public double getBezeichnung()
  {
    return m_bezeichnung;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bonus_zinsen.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bonus_zinsen"
   */
  public double getBonusZinsen()
  {
    return m_bonus_zinsen;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bsd_info_tilg_anteil.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bsd_info_tilg_anteil"
   */
  public double getBsdInfoTilgAnteil()
  {
    return m_bsd_info_tilg_anteil;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bsd_info_zins_anteil.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bsd_info_zins_anteil"
   */
  public double getBsdInfoZinsAnteil()
  {
    return m_bsd_info_zins_anteil;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_datum.
   * </pre>
   *  
   * @return den Wert der Variablen "m_datum"
   */
  public long getDatum()
  {
    return m_datum;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_knz_aktive_zahlung.
   * </pre>
   *  
   * @return den Wert der Variablen "m_knz_aktive_zahlung"
   */
  public boolean getKnzAktiveZahlung()
  {
    return m_knz_aktive_zahlung;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_knz_loeschung.
   * </pre>
   *  
   * @return den Wert der Variablen "m_knz_loeschung"
   */
  public int getKnzLoeschung()
  {
    return m_knz_loeschung;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_sort_id.
   * </pre>
   *  
   * @return den Wert der Variablen "m_sort_id"
   */
  public String getSortId()
  {
    if ( m_sort_id == null )
    {
      completeSortID();
    }

    return m_sort_id;
  }

  public void completeSortID()
  {
    m_sort_id = m_datum + "_" + FkString.right( "0000" + m_art, 4 );
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_str_art.
   * </pre>
   *  
   * @return den Wert der Variablen "m_str_art"
   */
  public String getStrArt()
  {
    return m_str_art;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_element_id.
   * </pre>
   *  
   * @return den Wert der Variablen "m_element_id"
   */
  public String getElementId()
  {
    return m_element_id;
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
   *      0 = Sort Id
   *      1 = Datum
   *      2 = Betrag Einzahlung Neu
   *      3 = Betrag Gebuehr
   *      4 = Betrag Foerderung
   *      5 = Betrag Kontostand
   *      6 = Betrag Bwz
   *      7 = Anzahl Zinstage
   *      8 = Art
   *      9 = Betrag Auszahlung
   *     10 = Betrag Betrag Salden Summe
   *     11 = Betrag Einzahlung Reset
   *     12 = Betrag Tilg Anteil
   *     13 = Betrag Uj Bonus Zins
   *     14 = Betrag Uj Guthabenzins
   *     15 = Betrag Zins Anteil
   *     16 = Betrag Zinsgutschrift
   *     17 = Bezeichnung
   *     18 = Bonus Zinsen
   *     19 = Bsd Info Tilg Anteil
   *     20 = Bsd Info Zins Anteil
   *     21 = Knz Aktive Zahlung
   *     22 = Knz Loeschung
   *     23 = Str Art
   *     24 = Element Id
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
        return getSortId();

      case 1 :
        return "" + FkDatumLong.getStringAuslong( getDatum() );

      case 2 :
        return getElementId();

      case 3 :
        return "" + getBetragEinzahlungNeu();

      case 4 :
        return "" + getBetragGebuehr();

      case 5 :
        return "" + getBetragFoerderung();

      case 6 :
        return "" + getBetragKontostand();

      case 7 :
        return "" + getBetragBwz();

      case 8 :
        return "" + getAnzahlZinstage();

      case 9 :
        return "" + getArt();

      case 10 :
        return "" + getBetragAuszahlung();

      case 11 :
        return "" + getBetragSaldenSumme();

      case 12 :
        return "" + getBetragEinzahlungReset();

      case 13 :
        return "" + getBetragTilgAnteil();

      case 14 :
        return "" + getBetragUjBonusZins();

      case 15 :
        return "" + getBetragUjGuthabenzins();

      case 16 :
        return "" + getBetragZinsAnteil();

      case 17 :
        return "" + getBetragZinsgutschrift();

      case 18 :
        return "" + getBezeichnung();

      case 19 :
        return "" + getBonusZinsen();

      case 20 :
        return "" + getBsdInfoTilgAnteil();

      case 21 :
        return "" + getBsdInfoZinsAnteil();

      case 22 :
        return "" + getKnzAktiveZahlung();

      case 23 :
        return "" + getKnzLoeschung();

      case 24 :
        return getStrArt();
    }

    return null;
  }

  /**
   * Erstellt die Stringrepraesentation der Klasse
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcKtoVerlaufElement"
   */
  public String toString()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( "\nKlasse CalcKtoVerlaufElement" );

    ergebnis_string_buffer.append( "\n + m_sort_id                    >" + m_sort_id + "<" );
    ergebnis_string_buffer.append( "\n + m_datum                      >" + m_datum + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_einzahlung_neu      >" + m_betrag_einzahlung_neu + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_gebuehr             >" + m_betrag_gebuehr + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_foerderung          >" + m_betrag_foerderung + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_kontostand          >" + m_betrag_kontostand + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_bwz                 >" + m_betrag_bwz + "<" );
    ergebnis_string_buffer.append( "\n + m_anzahl_zinstage            >" + m_anzahl_zinstage + "<" );
    ergebnis_string_buffer.append( "\n + m_art                        >" + m_art + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_auszahlung          >" + m_betrag_auszahlung + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_betrag_salden_summe >" + m_betrag_salden_summe + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_einzahlung_reset    >" + m_betrag_einzahlung_reset + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_tilg_anteil         >" + m_betrag_tilg_anteil + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_uj_bonus_zins       >" + m_betrag_uj_bonus_zins + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_uj_guthabenzins     >" + m_betrag_uj_guthabenzins + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_zins_anteil         >" + m_betrag_zins_anteil + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag_zinsgutschrift      >" + m_betrag_zinsgutschrift + "<" );
    ergebnis_string_buffer.append( "\n + m_bezeichnung                >" + m_bezeichnung + "<" );
    ergebnis_string_buffer.append( "\n + m_bonus_zinsen               >" + m_bonus_zinsen + "<" );
    ergebnis_string_buffer.append( "\n + m_bsd_info_tilg_anteil       >" + m_bsd_info_tilg_anteil + "<" );
    ergebnis_string_buffer.append( "\n + m_bsd_info_zins_anteil       >" + m_bsd_info_zins_anteil + "<" );
    ergebnis_string_buffer.append( "\n + m_knz_aktive_zahlung         >" + m_knz_aktive_zahlung + "<" );
    ergebnis_string_buffer.append( "\n + m_knz_loeschung              >" + m_knz_loeschung + "<" );
    ergebnis_string_buffer.append( "\n + m_str_art                    >" + m_str_art + "<" );
    ergebnis_string_buffer.append( "\n + m_element_id                 >" + m_element_id + "<" );

    return ergebnis_string_buffer.toString();
  }

  /**
   * Erstellt eine Stringrepraesentation in einer Zeile, wobei die Werte durch ein Trennzeichen getrennt werden
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcKtoVerlaufElement" in einer Zeile
   */
  public String toStringZeile()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( m_sort_id + "|" );
    ergebnis_string_buffer.append( m_datum + "|" );
    ergebnis_string_buffer.append( m_element_id + "|" );
    ergebnis_string_buffer.append( m_betrag_einzahlung_neu + "|" );
    ergebnis_string_buffer.append( m_betrag_gebuehr + "|" );
    ergebnis_string_buffer.append( m_betrag_foerderung + "|" );
    ergebnis_string_buffer.append( m_betrag_kontostand + "|" );
    ergebnis_string_buffer.append( m_betrag_bwz + "|" );
    ergebnis_string_buffer.append( m_anzahl_zinstage + "|" );
    ergebnis_string_buffer.append( m_art + "|" );
    ergebnis_string_buffer.append( m_betrag_auszahlung + "|" );
    ergebnis_string_buffer.append( m_betrag_salden_summe + "|" );
    ergebnis_string_buffer.append( m_betrag_einzahlung_reset + "|" );
    ergebnis_string_buffer.append( m_betrag_tilg_anteil + "|" );
    ergebnis_string_buffer.append( m_betrag_uj_bonus_zins + "|" );
    ergebnis_string_buffer.append( m_betrag_uj_guthabenzins + "|" );
    ergebnis_string_buffer.append( m_betrag_zins_anteil + "|" );
    ergebnis_string_buffer.append( m_betrag_zinsgutschrift + "|" );
    ergebnis_string_buffer.append( m_bezeichnung + "|" );
    ergebnis_string_buffer.append( m_bonus_zinsen + "|" );
    ergebnis_string_buffer.append( m_bsd_info_tilg_anteil + "|" );
    ergebnis_string_buffer.append( m_bsd_info_zins_anteil + "|" );
    ergebnis_string_buffer.append( m_knz_aktive_zahlung + "|" );
    ergebnis_string_buffer.append( m_knz_loeschung + "|" );
    ergebnis_string_buffer.append( m_str_art + "|" );

    return ergebnis_string_buffer.toString();
  }

  public String getDebugString()
  {
    return "#funktionsname( \"" + getAnzahlZinstage() + "\", \"" + getArt() + "\", \"" + getBetragAuszahlung() + "\", \"" + getBetragSaldenSumme() + "\", \"" + getBetragBwz() + "\", \"" + getBetragEinzahlungNeu() + "\", \"" + getBetragEinzahlungReset() + "\", \"" + getBetragGebuehr() + "\", \"" + getBetragFoerderung() + "\", \"" + getBetragKontostand() + "\", \"" + getBetragTilgAnteil() + "\", \"" + getBetragUjBonusZins() + "\", \"" + getBetragUjGuthabenzins() + "\", \"" + getBetragZinsAnteil() + "\", \"" + getBetragZinsgutschrift() + "\", \"" + getBezeichnung() + "\", \"" + getBonusZinsen() + "\", \"" + getBsdInfoTilgAnteil() + "\", \"" + getBsdInfoZinsAnteil() + "\", \"" + getDatum() + "\", \"" + getKnzAktiveZahlung() + "\", \"" + getKnzLoeschung() + "\", \"" + getSortId() + "\", \"" + getStrArt() + "\", \"" + getElementId() + "\", \"\" );";
  }

  /**
   * Liefert eine Kopie dieser Klasse wieder
   *
   * @return  eine Instanz von "CalcKtoVerlaufElement" mit den Daten dieser Instanz
   */
  public CalcKtoVerlaufElement getCopy()
  {
    CalcKtoVerlaufElement quelle_inst_calc_kto_verlauf_element_copy = this;

    CalcKtoVerlaufElement ziel_inst_calc_kto_verlauf_element_copy = new CalcKtoVerlaufElement();

    ziel_inst_calc_kto_verlauf_element_copy.setAnzahlZinstage( quelle_inst_calc_kto_verlauf_element_copy.getAnzahlZinstage() );
    ziel_inst_calc_kto_verlauf_element_copy.setArt( quelle_inst_calc_kto_verlauf_element_copy.getArt() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragAuszahlung( quelle_inst_calc_kto_verlauf_element_copy.getBetragAuszahlung() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragSaldenSumme( quelle_inst_calc_kto_verlauf_element_copy.getBetragSaldenSumme() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragBwz( quelle_inst_calc_kto_verlauf_element_copy.getBetragBwz() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragEinzahlungNeu( quelle_inst_calc_kto_verlauf_element_copy.getBetragEinzahlungNeu() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragEinzahlungReset( quelle_inst_calc_kto_verlauf_element_copy.getBetragEinzahlungReset() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragGebuehr( quelle_inst_calc_kto_verlauf_element_copy.getBetragGebuehr() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragFoerderung( quelle_inst_calc_kto_verlauf_element_copy.getBetragFoerderung() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragKontostand( quelle_inst_calc_kto_verlauf_element_copy.getBetragKontostand() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragTilgAnteil( quelle_inst_calc_kto_verlauf_element_copy.getBetragTilgAnteil() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragUjBonusZins( quelle_inst_calc_kto_verlauf_element_copy.getBetragUjBonusZins() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragUjGuthabenzins( quelle_inst_calc_kto_verlauf_element_copy.getBetragUjGuthabenzins() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragZinsAnteil( quelle_inst_calc_kto_verlauf_element_copy.getBetragZinsAnteil() );
    ziel_inst_calc_kto_verlauf_element_copy.setBetragZinsgutschrift( quelle_inst_calc_kto_verlauf_element_copy.getBetragZinsgutschrift() );
    ziel_inst_calc_kto_verlauf_element_copy.setBezeichnung( quelle_inst_calc_kto_verlauf_element_copy.getBezeichnung() );
    ziel_inst_calc_kto_verlauf_element_copy.setBonusZinsen( quelle_inst_calc_kto_verlauf_element_copy.getBonusZinsen() );
    ziel_inst_calc_kto_verlauf_element_copy.setBsdInfoTilgAnteil( quelle_inst_calc_kto_verlauf_element_copy.getBsdInfoTilgAnteil() );
    ziel_inst_calc_kto_verlauf_element_copy.setBsdInfoZinsAnteil( quelle_inst_calc_kto_verlauf_element_copy.getBsdInfoZinsAnteil() );
    ziel_inst_calc_kto_verlauf_element_copy.setDatum( quelle_inst_calc_kto_verlauf_element_copy.getDatum() );
    ziel_inst_calc_kto_verlauf_element_copy.setKnzAktiveZahlung( quelle_inst_calc_kto_verlauf_element_copy.getKnzAktiveZahlung() );
    ziel_inst_calc_kto_verlauf_element_copy.setKnzLoeschung( quelle_inst_calc_kto_verlauf_element_copy.getKnzLoeschung() );
    ziel_inst_calc_kto_verlauf_element_copy.setSortId( quelle_inst_calc_kto_verlauf_element_copy.getSortId() );
    ziel_inst_calc_kto_verlauf_element_copy.setStrArt( quelle_inst_calc_kto_verlauf_element_copy.getStrArt() );
    ziel_inst_calc_kto_verlauf_element_copy.setElementId( quelle_inst_calc_kto_verlauf_element_copy.getElementId() );

    return ziel_inst_calc_kto_verlauf_element_copy;
  }

  /**
   * <pre>
   * Erstellt den JSON-String
   *
   *   "calc_kto_verlauf_element": 
   *     {
   *       "anzahl_zinstage"            : 0,
   *       "art"                        : 0,
   *       "betrag_auszahlung"          : 0.0d,
   *       "betrag_salden_summe" : 0.0d,
   *       "betrag_bwz"                 : 0.0d,
   *       "betrag_einzahlung_neu"      : 0.0d,
   *       "betrag_einzahlung_reset"    : 0.0d,
   *       "betrag_gebuehr"             : 0.0d,
   *       "betrag_foerderung"          : 0.0d,
   *       "betrag_kontostand"          : 0.0d,
   *       "betrag_tilg_anteil"         : 0.0d,
   *       "betrag_uj_bonus_zins"       : 0.0d,
   *       "betrag_uj_guthabenzins"     : 0.0d,
   *       "betrag_zins_anteil"         : 0.0d,
   *       "betrag_zinsgutschrift"      : 0.0d,
   *       "bezeichnung"                : 0.0d,
   *       "bonus_zinsen"               : 0.0d,
   *       "bsd_info_tilg_anteil"       : 0.0d,
   *       "bsd_info_zins_anteil"       : 0.0d,
   *       "datum"                      : 0,
   *       "knz_aktive_zahlung"         : true,
   *       "knz_loeschung"              : 0,
   *       "sort_id"                    : "ABC.DEF.GHI",
   *       "str_art"                    : "ABC.DEF.GHI",
   *       "element_id"                 : "ABC.DEF.GHI"
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
    json_string += "\"anzahl_zinstage\":" + FkJson.getStringJson( m_anzahl_zinstage ) + " ,";
    json_string += "\"art\":" + FkJson.getStringJson( m_art ) + " ,";
    json_string += "\"betrag_auszahlung\":" + FkJson.getStringJson( m_betrag_auszahlung ) + " ,";
    json_string += "\"betrag_salden_summe\":" + FkJson.getStringJson( m_betrag_salden_summe ) + " ,";
    json_string += "\"betrag_bwz\":" + FkJson.getStringJson( m_betrag_bwz ) + " ,";
    json_string += "\"betrag_einzahlung_neu\":" + FkJson.getStringJson( m_betrag_einzahlung_neu ) + " ,";
    json_string += "\"betrag_einzahlung_reset\":" + FkJson.getStringJson( m_betrag_einzahlung_reset ) + " ,";
    json_string += "\"betrag_gebuehr\":" + FkJson.getStringJson( m_betrag_gebuehr ) + " ,";
    json_string += "\"betrag_foerderung\":" + FkJson.getStringJson( m_betrag_foerderung ) + " ,";
    json_string += "\"betrag_kontostand\":" + FkJson.getStringJson( m_betrag_kontostand ) + " ,";
    json_string += "\"betrag_tilg_anteil\":" + FkJson.getStringJson( m_betrag_tilg_anteil ) + " ,";
    json_string += "\"betrag_uj_bonus_zins\":" + FkJson.getStringJson( m_betrag_uj_bonus_zins ) + " ,";
    json_string += "\"betrag_uj_guthabenzins\":" + FkJson.getStringJson( m_betrag_uj_guthabenzins ) + " ,";
    json_string += "\"betrag_zins_anteil\":" + FkJson.getStringJson( m_betrag_zins_anteil ) + " ,";
    json_string += "\"betrag_zinsgutschrift\":" + FkJson.getStringJson( m_betrag_zinsgutschrift ) + " ,";
    json_string += "\"bezeichnung\":" + FkJson.getStringJson( m_bezeichnung ) + " ,";
    json_string += "\"bonus_zinsen\":" + FkJson.getStringJson( m_bonus_zinsen ) + " ,";
    json_string += "\"bsd_info_tilg_anteil\":" + FkJson.getStringJson( m_bsd_info_tilg_anteil ) + " ,";
    json_string += "\"bsd_info_zins_anteil\":" + FkJson.getStringJson( m_bsd_info_zins_anteil ) + " ,";
    json_string += "\"datum\":" + FkJson.getStringJson( m_datum ) + " ,";
    json_string += "\"knz_aktive_zahlung\":\"" + FkJson.getStringJson( m_knz_aktive_zahlung ) + "\",";
    json_string += "\"knz_loeschung\":" + FkJson.getStringJson( m_knz_loeschung ) + " ,";
    json_string += "\"sort_id\":\"" + FkJson.getStringJson( m_sort_id ) + "\",";
    json_string += "\"str_art\":\"" + FkJson.getStringJson( m_str_art ) + "\",";
    json_string += "\"element_id\":\"" + FkJson.getStringJson( m_element_id ) + "\"";
    json_string += "}";

    return json_string;
  }

  /**
   * Setzt alle Membervariablen auf "null"
   */
  public void clear()
  {
    m_anzahl_zinstage = 0;
    m_art = 0;
    m_betrag_auszahlung = 0.0;
    m_betrag_salden_summe = 0.0;
    m_betrag_bwz = 0.0;
    m_betrag_einzahlung_neu = 0.0;
    m_betrag_einzahlung_reset = 0.0;
    m_betrag_gebuehr = 0.0;
    m_betrag_foerderung = 0.0;
    m_betrag_kontostand = 0.0;
    m_betrag_tilg_anteil = 0.0;
    m_betrag_uj_bonus_zins = 0.0;
    m_betrag_uj_guthabenzins = 0.0;
    m_betrag_zins_anteil = 0.0;
    m_betrag_zinsgutschrift = 0.0;
    m_bezeichnung = 0.0;
    m_bonus_zinsen = 0.0;
    m_bsd_info_tilg_anteil = 0.0;
    m_bsd_info_zins_anteil = 0.0;
    m_datum = 0;
    m_knz_aktive_zahlung = false;
    m_knz_loeschung = 1;
    m_sort_id = null;
    m_str_art = null;
    m_element_id = null;
  }

  public String getDatumAsString()
  {
    return FkDatumLong.getStringAuslong( m_datum );
  }
}
