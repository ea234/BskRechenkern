package de.bskrechner.calc;

import de.bskrechner.util.FkDatumLong;
import de.bskrechner.util.FkZahl;

public class CalcZahlung
{
  /**
   * <pre>
   * Fuehrt einen Class-Cast auf die Klasse "CalcZahlung" auf das uebergebene Objekt aus. 
   *
   * Im Falle einer ClassCastException wird "null" zurueckgegeben.
   * Ist das Objekt selber "null" wird "null" zurueckgegeben.
   *
   * CalcZahlung var_instanz = CalcZahlung.getClassCast( java_object );
   *
   * if ( var_instanz == null )
   * {
   *   str_fehler = "Die Instanz \"java_object\" ist keine Instanz der Klasse \"CalcZahlung\" ";
   *
   *   return;
   * } 
   *
   * </pre>
   *
   * @param  pObjekt das zu castende Objekt
   * @return  das Objekt als Instanz von "CalcZahlung", oder "null" im Fehlerfall
   */
  public static CalcZahlung getClassCast( Object pObjekt )
  {
    /*
     * Pruefung: pObjekt ungleich null?
     */
    if ( pObjekt != null )
    {
      try
      {
        return (CalcZahlung) pObjekt;
      }
      catch ( ClassCastException err_inst )
      {
        // keine Aktion im Fehlerfall, da Rueckgabe von null
      }
    }

    return null;
  }

  private String m_id                   = "";

  private String m_bezeichnung          = "";

  private String m_rhythmus             = "";

  private long   m_knz_aktiv            = 1;

  private long   m_knz_tag              = 0;

  private long   m_datum_bis            = 0;

  private long   m_datum_ab             = 0;

  private double m_betrag               = 0.0;

  private int    m_rk_input_vertragsart = 0;

  public int getRkInputVertragsart()
  {
    return m_rk_input_vertragsart;
  }

  public void setRkInputVertragsart( int pRkInputVertragsart )
  {
    m_rk_input_vertragsart = pRkInputVertragsart;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_id.
   * </pre>
   * 
   * @param  pId         der zu setzende Wert
   */
  public void setId( String pId )
  {
    m_id = pId;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bezeichnung.
   * </pre>
   * 
   * @param  pBezeichnung der zu setzende Wert
   */
  public void setBezeichnung( String pBezeichnung )
  {
    m_bezeichnung = pBezeichnung;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_rhythmus.
   * </pre>
   * 
   * @param  pRhythmus   der zu setzende Wert
   */
  public void setRhythmus( String pRhythmus )
  {
    m_rhythmus = pRhythmus;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_knz_aktiv.
   * </pre>
   * 
   * @param  pKnzAktiv   der zu setzende Wert
   */
  public void setKnzAktiv( long pKnzAktiv )
  {
    m_knz_aktiv = pKnzAktiv;
  }

  public void setKnzAktiv( String pKnzAktiv )
  {
    m_knz_aktiv = FkZahl.getLong( pKnzAktiv );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_knz_tag.
   * </pre>
   * 
   * @param  pKnzTag    der zu setzende Wert
   */
  public void setKnzTag( long pKnzTag )
  {
    m_knz_tag = pKnzTag;
  }

  public void setKnzTag( String pKnzTag )
  {
    m_knz_tag = FkZahl.getKennzeichenFeld( pKnzTag );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_datum_bis.
   * </pre>
   * 
   * @param  pDatumBis  der zu setzende Wert
   */
  public void setDatumBis( long pDatumBis )
  {
    m_datum_bis = pDatumBis;
  }

  public void setDatumBis( String pDatumBis )
  {
    m_datum_bis = FkDatumLong.parseToLong( pDatumBis );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_datum_ab.
   * </pre>
   * 
   * @param  pDatumAb   der zu setzende Wert
   */
  public void setDatumAb( long pDatumAb )
  {
    m_datum_ab = pDatumAb;
  }

  public void setDatumAb( String pDatumAb )
  {
    m_datum_ab = FkDatumLong.parseToLong( pDatumAb );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_betrag.
   * </pre>
   * 
   * @param  pBetrag    der zu setzende Wert
   */
  public void setBetrag( double pBetrag )
  {
    m_betrag = pBetrag;
  }

  public void setBetrag( String pBetrag )
  {
    m_betrag = FkZahl.getDouble( pBetrag, 0.0 );
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_id.
   * </pre>
   *  
   * @return den Wert der Variablen "m_id"
   */
  public String getId()
  {
    return m_id;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bezeichnung.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bezeichnung"
   */
  public String getBezeichnung()
  {
    return m_bezeichnung;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_rhythmus.
   * </pre>
   *  
   * @return den Wert der Variablen "m_rhythmus"
   */
  public String getRhythmus()
  {
    return m_rhythmus;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_knz_aktiv.
   * </pre>
   *  
   * @return den Wert der Variablen "m_knz_aktiv"
   */
  public long getKnzAktiv()
  {
    return m_knz_aktiv;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_knz_tag.
   * </pre>
   *  
   * @return den Wert der Variablen "m_knz_tag"
   */
  public long getKnzTag()
  {
    return m_knz_tag;
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_datum_bis" das Datum im Format JJJJMMTT im Datentyp "long" zurueck.
   *  
   * Liefert den Wert der Variablen m_datum_bis.
   * </pre>
   *  
   * @return den Wert der Variablen "m_datum_bis" im Format JJJJMMTT 
   *  
   */
  public long getDatumBis()
  {
    return m_datum_bis;
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_datum_bis" das Datum im Format TT.MM.JJJJ zurueck.
   * </pre>
   *  
   * @return das Datum der Variablen "m_datum_bis" im Format TT.MM.JJJJ
   */
  public String getStringDatumBis()
  {
    return FkDatumLong.getDatumStringAusLong( m_datum_bis );
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_datum_ab" das Datum im Format JJJJMMTT im Datentyp "long" zurueck.
   *  
   * Liefert den Wert der Variablen m_datum_ab.
   * </pre>
   *  
   * @return den Wert der Variablen "m_datum_ab" im Format JJJJMMTT 
   *  
   */
  public long getDatumAb()
  {
    return m_datum_ab;
  }

  /**
   * <pre>
   * Liefert fuer die Variable "m_datum_ab" das Datum im Format TT.MM.JJJJ zurueck.
   * </pre>
   *  
   * @return das Datum der Variablen "m_datum_ab" im Format TT.MM.JJJJ
   */
  public String getStringDatumAb()
  {
    return FkDatumLong.getDatumStringAusLong( m_datum_ab );
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_betrag.
   * </pre>
   *  
   * @return den Wert der Variablen "m_betrag"
   */
  public double getBetrag()
  {
    return m_betrag;
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

  public boolean istAktiv()
  {
    return m_knz_aktiv != 0;
  }

  public boolean istInaktiv()
  {
    return m_knz_aktiv == 0;
  }

  public void setKnzZahlungAktiv()
  {
    m_knz_aktiv = 1;
  }

  public void setKnzZahlungInaktiv()
  {
    m_knz_aktiv = 0;
  }

  public boolean istRhythmusEinmalig()
  {
    return ( m_rhythmus != null ) && ( m_rhythmus.equalsIgnoreCase( "EINMALIG" ) );
  }

  public boolean istRhythmusHalbJaehrlich()
  {
    return ( m_rhythmus != null ) && ( m_rhythmus.equalsIgnoreCase( "HALBJAEHRLICH" ) );
  }

  public boolean istRhythmusJaehrlich()
  {
    return ( m_rhythmus != null ) && ( m_rhythmus.equalsIgnoreCase( "JAEHRLICH" ) );
  }

  public boolean istRhythmusMonatlich()
  {
    return ( m_rhythmus != null ) && ( m_rhythmus.equalsIgnoreCase( "MONATLICH" ) );
  }

  public boolean istRhythmusViertelJaehrlich()
  {
    return ( m_rhythmus != null ) && ( m_rhythmus.equalsIgnoreCase( "VIERTELJAEHRLICH" ) );
  }

  /**
   * Erstellt die Stringrepraesentation der Klasse
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcZahlung"
   */
  public String toString()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( "\nKlasse CalcZahlung" );

    ergebnis_string_buffer.append( "\n + m_id          >" + m_id + "<" );
    ergebnis_string_buffer.append( "\n + m_bezeichnung >" + m_bezeichnung + "<" );
    ergebnis_string_buffer.append( "\n + m_rhythmus    >" + m_rhythmus + "<" );
    ergebnis_string_buffer.append( "\n + m_knz_aktiv   >" + m_knz_aktiv + "<" );
    ergebnis_string_buffer.append( "\n + m_knz_tag     >" + m_knz_tag + "<" );
    ergebnis_string_buffer.append( "\n + m_datum_ab    >" + m_datum_ab + "<  = >" + getStringDatumAb() + "<" );
    ergebnis_string_buffer.append( "\n + m_datum_bis   >" + m_datum_bis + "<  = >" + getStringDatumBis() + "<" );
    ergebnis_string_buffer.append( "\n + m_betrag      >" + m_betrag + "<" );

    return ergebnis_string_buffer.toString();
  }

  /**
   * Erstellt eine Stringrepraesentation in einer Zeile, wobei die Werte durch ein Trennzeichen getrennt werden
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcZahlung" in einer Zeile
   */
  public String toStringZeile()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( m_id + "|" );
    ergebnis_string_buffer.append( m_bezeichnung + "|" );
    ergebnis_string_buffer.append( m_rhythmus + "|" );
    ergebnis_string_buffer.append( m_knz_aktiv + "|" );
    ergebnis_string_buffer.append( m_knz_tag + "|" );
    ergebnis_string_buffer.append( m_datum_ab + "|" );
    ergebnis_string_buffer.append( m_datum_bis + "|" );
    ergebnis_string_buffer.append( m_betrag + "|" );

    return ergebnis_string_buffer.toString();
  }
}
