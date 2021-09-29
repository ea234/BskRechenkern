package de.bskrechner.calc;

import de.bskrechner.util.FkZahl;

/**
 * <pre>
 * Java-Klasse "CalcBwzStaffel". 
 * </pre>
 */
class CalcBwzStaffel
{
  /**
   * <pre>
   * Fuehrt einen Class-Cast auf die Klasse "CalcBwzStaffel" auf das uebergebene Objekt aus. 
   *
   * Im Falle einer ClassCastException wird "null" zurueckgegeben.
   * Ist das Objekt selber "null" wird "null" zurueckgegeben.
   *
   * CalcBwzStaffel var_instanz = CalcBwzStaffel.getClassCast( java_object );
   *
   * if ( var_instanz == null )
   * {
   *   str_fehler = "Die Instanz \"java_object\" ist keine Instanz der Klasse \"CalcBwzStaffel\" ";
   *
   *   return;
   * }
   *
   * </pre>
   *
   * @param  pObjekt das zu castende Objekt
   * @return  das Objekt als Instanz von "CalcBwzStaffel", oder "null" im Fehlerfall
   */
  public static CalcBwzStaffel getClassCast( Object pObjekt )
  {
    /*
     * Pruefung: pObjekt ungleich null?
     */
    if ( pObjekt != null )
    {
      try
      {
        return (CalcBwzStaffel) pObjekt;
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

    ergebnis_string_buffer.append( "Bwz Von" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Bwz Bis" );
    ergebnis_string_buffer.append( "|" );
    ergebnis_string_buffer.append( "Ztb" );
    ergebnis_string_buffer.append( "|" );

    return ergebnis_string_buffer.toString();
  }

  private double m_bwz_von = 0.0;

  private double m_bwz_bis = 0.0;

  private double m_ztb     = 0.0;

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bwz_von.
   * </pre>
   * 
   * @param  pBwzVon     der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBwzVon( String pBwzVon )
  {
    m_bwz_von = FkZahl.getDouble( pBwzVon, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bwz_von.
   * </pre>
   * 
   * @param  pBwzVon der zu setzende Wert
   */
  public void setBwzVon( double pBwzVon )
  {
    m_bwz_von = pBwzVon;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bwz_bis.
   * </pre>
   * 
   * @param  pBwzBis der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setBwzBis( String pBwzBis )
  {
    m_bwz_bis = FkZahl.getDouble( pBwzBis, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_bwz_bis.
   * </pre>
   * 
   * @param  pBwzBis der zu setzende Wert
   */
  public void setBwzBis( double pBwzBis )
  {
    m_bwz_bis = pBwzBis;
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_ztb.
   * </pre>
   * 
   * @param  pZtb der zu setzende Wert als String (wird zu double gewandelt)
   */
  public void setZtb( String pZtb )
  {
    m_ztb = FkZahl.getDouble( pZtb, 0.0 );
  }

  /**
   * <pre>
   * Setzt den Wert der Variablen m_ztb.
   * </pre>
   * 
   * @param  pZtb der zu setzende Wert
   */
  public void setZtb( double pZtb )
  {
    m_ztb = pZtb;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bwz_von.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bwz_von"
   */
  public double getBwzVon()
  {
    return m_bwz_von;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_bwz_bis.
   * </pre>
   *  
   * @return den Wert der Variablen "m_bwz_bis"
   */
  public double getBwzBis()
  {
    return m_bwz_bis;
  }

  /**
   * <pre>
   * Liefert den Wert der Variablen m_ztb.
   * </pre>
   *  
   * @return den Wert der Variablen "m_ztb"
   */
  public double getZtb()
  {
    return m_ztb;
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
   *     0 = Bwz Von
   *     1 = Bwz Bis
   *     2 = Ztb
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
        return "" + getBwzVon();

      case 1 :
        return "" + getBwzBis();

      case 2 :
        return "" + getZtb();
    }

    return null;
  }

  /**
   * Erstellt die Stringrepraesentation der Klasse
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcBwzStaffel"
   */
  public String toString()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( "\nKlasse CalcBwzStaffel" );

    ergebnis_string_buffer.append( "\n + m_bwz_von >" + m_bwz_von + "<" );
    ergebnis_string_buffer.append( "\n + m_bwz_bis >" + m_bwz_bis + "<" );
    ergebnis_string_buffer.append( "\n + m_ztb     >" + m_ztb + "<" );

    return ergebnis_string_buffer.toString();
  }

  /**
   * Erstellt eine Stringrepraesentation in einer Zeile, wobei die Werte durch ein Trennzeichen getrennt werden
   *  
   * @return einen String mit den Variablenwerten der Klasse "CalcBwzStaffel" in einer Zeile
   */
  public String toStringZeile()
  {
    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( m_bwz_von + "|" );
    ergebnis_string_buffer.append( m_bwz_bis + "|" );
    ergebnis_string_buffer.append( m_ztb + "|" );

    return ergebnis_string_buffer.toString();
  }

  /**
   * Setzt alle Membervariablen auf "null"
   */
  public void clear()
  {
    m_bwz_von = 0.0;
    m_bwz_bis = 0.0;
    m_ztb = 0.0;
  }
}
