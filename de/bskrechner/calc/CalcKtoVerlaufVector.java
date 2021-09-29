package de.bskrechner.calc;

import de.bskrechner.util.FkString;

/**
 * <pre>
 * Speichert Instanzen der Java-Klasse "CalcKtoVerlaufElement" in einem Vektor.
 * </pre>
 */
public class CalcKtoVerlaufVector
{
  /**
   * Vektorklasse fuer die Speicherung Instanzen der Java-Klasse "CalcKtoVerlaufElement"
   */
  private CalcBsvVektor m_calc_kto_verlauf_element_vector = null;

  /** Die Bezeichnung fuer den Vektor */
  private String        m_vektor_bezeichnung              = null;

  /**
   * Liefert die Bezeichnung fuer den Vektor zurueck.
   *
   * @return  den Wert der Variablen "m_vektor_bezeichnung"
   */
  public String getVektorBezeichnung()
  {
    return m_vektor_bezeichnung;
  }

  /**
   * Setzt die Bezeichnung fuer den Vektor
   *
   * @param  pVektorBezeichnung die zu setzende Bezeichnung
   */
  public void setVektorBezeichnung( String pVektorBezeichnung )
  {
    m_vektor_bezeichnung = pVektorBezeichnung;
  }

  /**
   * <pre>
   * Erstellt eine neue Instanz der Klasse "CalcKtoVerlaufElement", fuegt 
   * diese dem Speichervektor hinzu und gibt die erstellte Instanz zurueck.
   * </pre>
   *
   * @return eine Instanz vom Typ "CalcKtoVerlaufElement"
   */
  public CalcKtoVerlaufElement addCalcKtoVerlaufElement()
  {
    return addCalcKtoVerlaufElement( new CalcKtoVerlaufElement() );
  }

  /**
   * <pre>
   * Ist der Parameter ungleich null, wird die uebergebene Instanz dem Vektor m_calc_kto_verlauf_element_vector hinzugefuegt.
   * Der Parameter pCalcKtoVerlaufElement wird dem Aufrufer wieder zurueckgegeben.
   * </pre>
   *
   * @param  pCalcKtoVerlaufElement eine Klasse vom Typ "CalcKtoVerlaufElement"
   *
   * @return eine Instanz vom Typ CalcKtoVerlaufElement (gleich dem Eingabeparameter)
   */
  public CalcKtoVerlaufElement addCalcKtoVerlaufElement( CalcKtoVerlaufElement pCalcKtoVerlaufElement )
  {
    /*
     * Pruefung: Parameterinstanz ungleich null?
     */
    if ( pCalcKtoVerlaufElement != null )
    {
      getCalcKtoVerlaufElementVector().add( pCalcKtoVerlaufElement );
    }

    /*
     * Rueckgabe der Parameterinstanz
     */
    return pCalcKtoVerlaufElement;
  }

  public boolean addElement( CalcKtoVerlaufElement pCalcKtoVerlaufElement )
  {
    try
    {
      /*
       * Pruefung: Parameterinstanz ungleich null?
       */
      if ( pCalcKtoVerlaufElement != null )
      {
        getCalcKtoVerlaufElementVector().add( pCalcKtoVerlaufElement );
      }

      return true;
    }
    catch ( Exception exp )
    {
      //
    }

    return false;
  }

  public String toJSONCalcKtoVerlaufElement()
  {
    return toJSONCalcKtoVerlaufElement( null );
  }

  /**
   * <pre>
   * Erstellt die JSON-Darstellung des Vektors, wenn Elemente vorhanden sind.
   * Die JSON-Rueckgabe wird nicht in Klammern eingefasst. Dieses muss von der
   * aufrufenden Funktion gemacht werden.
   *
   * Es wird folgender String zurueckgegeben:
   *
   *   "calc_kto_verlauf_element": [ 
   *     {
   *       "anzahl_zinstage"            : 0,
   *       "art"                        : 0,
   *       "betrag_auszahlung"          : 0.0d,
   *       "betrag_betrag_salden_summe" : 0.0d,
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
   *   ]
   *
   *
   * json_string = inst_calc_kto_verlauf_element.toJSONCalcKtoVerlaufElement();
   *
   * </pre>
   * 
   * @param  pBezeichnung   die Bezeichnung fuer den JSON-Vektor (null ="calc_kto_verlauf_element") 
   * 
   * @return  die JSON-Darstellung (ohne Klammerung), oder einen Leerstring wenn keine Werte vorhanden sind
   */
  public String toJSONCalcKtoVerlaufElement( String pBezeichnung )
  {
    if ( m_calc_kto_verlauf_element_vector != null && m_calc_kto_verlauf_element_vector.hasWerte() )
    {
      /*
       * Stringbuffer fuer das Ergebnis erstellen
       */
      StringBuffer ergebnis_string_buffer = new StringBuffer();

      ergebnis_string_buffer.append( "\"" + ( pBezeichnung == null ? "calc_kto_verlauf_element" : pBezeichnung ) + "\":[" );

      int akt_index = 0;

      CalcKtoVerlaufElement inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );

      /*
       * Ueber eine While-Schleife die Vektorlemente hinzufuegen.
       */
      while ( inst_calc_kto_verlauf_element != null )
      {
        /*
         * Komma
         * Bevor ein neues JSON-Objekt dem Vektor hinzugefuegt wird,
         * muss dass alte JSON-Objekt mit einem Komma abgetrennt werden.
         * 
         * Das Komma wird ab dem zweitem Element eingefuegt.
         * 
         * Dieses Vorgehen sorgt dafuer, dass das letzte Element kein
         * abschliessendes Komma bekommt.
         */
        if ( akt_index > 0 )
        {
          ergebnis_string_buffer.append( "," );

          //ergebnis_string_buffer.append( "\n" );
        }

        /*
         * Vektorelement
         * Die JSON-Darstellung des aktuellen Vektorelementes wird dem
         * Ergebnis-Stringbuffer hinzugefuegt.
         */
        ergebnis_string_buffer.append( inst_calc_kto_verlauf_element.toJSON() );

        /*
         * Naechstes Element
         * Der Vektorzaehler wird um eins hochgezaehlt und es 
         * wird das naechste Element aus dem Vektor geholt.
         * 
         * Sollte die Rueckgabe "null" sein, gibt es kein weiteres
         * Element und die Schleife ist beendet.
         */
        akt_index++;
        inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );
      }

      /*
       * JSON-Vektor nach der While-Schleife mit ']' beenden
       */
      ergebnis_string_buffer.append( "]" );

      /*
       * Den JSON-String dem Aufrufer zurueckgeben.
       */
      return ergebnis_string_buffer.toString();
    }

    /*
     * Defaultrueckgabe
     * Sind im Vektor keine Daten gespeichert, bekommt der Aufrufer
     * einen Leerstring zurueck.
     */
    return "";
  }

  /**
   * <pre>
   * Liefert die Information zurueck, ob der Vektor "m_calc_kto_verlauf_element_vector" instanziiert ist 
   * und Werte vom Typ "CalcKtoVerlaufElement" hat.
   * </pre>
   *
   * @return TRUE wenn Werte im Vektor vorhanden sind, sonst FALSE
   */
  public boolean hasWerteCalcKtoVerlaufElement()
  {
    return ( m_calc_kto_verlauf_element_vector != null ) && ( m_calc_kto_verlauf_element_vector.hasWerte() );
  }

  /**
   * <pre>
   * Gibt die Vektorinstanz "m_calc_kto_verlauf_element_vector" zurueck.
   * Ist die Instanz nicht vorhanden, wird diese erstellt.
   * </pre>
   *
   * @return eine Instanz vom Typ "CalcBsvVektor"
   */
  public CalcBsvVektor getCalcKtoVerlaufElementVector()
  {
    /*
     * Pruefung: Vektorvariable vorhanden?
     * Ist die Vektorvariable noch null, wird eine Vektorinstanz erstellt
     */
    if ( m_calc_kto_verlauf_element_vector == null )
    {
      m_calc_kto_verlauf_element_vector = new CalcBsvVektor();
    }

    /*
     * Gebe das Vektorobjekt zurueck
     */
    return m_calc_kto_verlauf_element_vector;
  }

  /**
   * <pre>
   * Liefert das Objekt an dem uebergebenen Index zurueck. 
   * Ist der Parameterindex nicht vorhanden, wird ""null"" zurueckgegeben.
   * </pre>
   * 
   * @param  pIndex         der Index im Speichervektor
   * 
   * @return eine Instanz vom Typ "CalcKtoVerlaufElement", oder "null", wenn der Index nicht existiert
   */
  public CalcKtoVerlaufElement getIndexCalcKtoVerlaufElement( int pIndex )
  {
    try
    {
      /*
       * Hole die Instanz am uebergebenen Vektor-Index.
       *
       * Caste das zurueckgegebene Objekt auf eine Instanz der Klasse "CalcKtoVerlaufElement" 
       * und gebe das gecastete Objekt zurueck.
       *
       * Ein Fehler wird abgefangen (Nullpointerexception, ClassCastException)
       */
      return (CalcKtoVerlaufElement) getCalcKtoVerlaufElementVector().get( pIndex );
    }
    catch ( Exception err_inst )
    {
      // Keine Aktion notwendig, da im Fehlerfall "null" zurueckgegeben wird
    }

    /*
     * Defaultrueckgabe
     * Kam es zu einem Fehler, wird null zurueckgegeben.
     */
    return null;
  }

  public CalcKtoVerlaufElement getIndex( long pIndex )
  {
    try
    {
      /*
       * Hole die Instanz am uebergebenen Vektor-Index.
       *
       * Caste das zurueckgegebene Objekt auf eine Instanz der Klasse "CalcKtoVerlaufElement" 
       * und gebe das gecastete Objekt zurueck.
       *
       * Ein Fehler wird abgefangen (Nullpointerexception, ClassCastException)
       */
      return (CalcKtoVerlaufElement) getCalcKtoVerlaufElementVector().get( (int) pIndex );
    }
    catch ( Exception err_inst )
    {
      // Keine Aktion notwendig, da im Fehlerfall "null" zurueckgegeben wird
    }

    /*
     * Defaultrueckgabe
     * Kam es zu einem Fehler, wird null zurueckgegeben.
     */
    return null;
  }

  /**
   * <pre>
   * Ruft bei jedem Element des Vektors die To-String-Funktion auf
   * und gibt den Ergebnisstring zurueck.
   * </pre>
   *
   * @return Auflistung der Vektorelemente
   */
  public String toStringVektorCalcKtoVerlaufElement()
  {
    // FkDatei.systemSchreibeDatei( FkSystem.getStdRootVerzeichnis() + "vektor_x.txt", inst_calc_kto_verlauf_element_vector.toStringVektorCalcKtoVerlaufElement() );

    StringBuffer ergebnis_string_buffer = new StringBuffer();

    ergebnis_string_buffer.append( "\nJava-Klasse CalcKtoVerlaufElement" + ( m_vektor_bezeichnung != null ? " - " + m_vektor_bezeichnung : "" ) );

    ergebnis_string_buffer.append( "\nAnzahl Elemente " + getAnzahl() + "\n" );

    if ( hasWerteCalcKtoVerlaufElement() )
    {
      int anzahl_werte = getAnzahl();

      int akt_index = 0;

      CalcKtoVerlaufElement inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );

      while ( akt_index < anzahl_werte )
      {
        if ( inst_calc_kto_verlauf_element != null )
        {
          ergebnis_string_buffer.append( "\n " );
          ergebnis_string_buffer.append( FkString.getFeldRechts( akt_index, 4 ) + "  " );
          ergebnis_string_buffer.append( inst_calc_kto_verlauf_element.toStringZeile() );
        }
        else
        {
          ergebnis_string_buffer.append( "\n " );
          ergebnis_string_buffer.append( FkString.getFeldRechts( akt_index, 4 ) + " -----------------------------------------------------" );
        }

        akt_index++;
        inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );
      }
    }

    return ergebnis_string_buffer.toString();
  }

  /**
   * @return Ergebnis der Funktion "toStringVektorCalcKtoVerlaufElement"
   */
  public String toString()
  {
    return toStringVektorCalcKtoVerlaufElement();
  }

  /**
   * <pre>
   * Liefert die Anzahl der gespeicherten Elemente im Vektor zurueck.
   * Ist der Vektor noch nicht gesetzt, wird 0 zurueckgegeben.
   * </pre>
   * 
   * @return die Anzahl der gespeicherten Elemente der Javaklasse "CalcKtoVerlaufElement"
   */
  public int getAnzahl()
  {
    if ( m_calc_kto_verlauf_element_vector == null )
    {
      return 0;
    }

    return m_calc_kto_verlauf_element_vector.size();
  }

  /**
   * <pre>
   * Liefert die Instanz mit der vorgegebenen ID zurueck.
   * Es wird "null" zurueckgegeben, wenn es keine Instanz mit der ID gibt.
   * </pre>
   * 
   * @return die Instanz fuer die ID oder null
   */
  public CalcKtoVerlaufElement getIndexCalcKtoVerlaufElementID( String pID )
  {
    CalcKtoVerlaufElement inst_calc_kto_verlauf_element = null;

    int akt_index = 0;

    inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );

    while ( inst_calc_kto_verlauf_element != null )
    {
      if ( inst_calc_kto_verlauf_element.isID( pID ) )
      {
        return inst_calc_kto_verlauf_element;
      }

      akt_index++;
      inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );
    }

    /*
     * Wurde keine Uebereinstimmung gefunden, wird null zurueckgegeben.
     */
    return null;
  }

  public CalcKtoVerlaufElement getElementByArt( long pArt )
  {
    CalcKtoVerlaufElement inst_calc_kto_verlauf_element = null;

    int akt_index = 0;

    inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );

    while ( inst_calc_kto_verlauf_element != null )
    {
      if ( inst_calc_kto_verlauf_element.getArt() == pArt )
      {
        return inst_calc_kto_verlauf_element;
      }

      akt_index++;
      inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );
    }

    /*
     * Wurde keine Uebereinstimmung gefunden, wird null zurueckgegeben.
     */
    return null;
  }

  public int getIndexByArt( long pArt )
  {
    CalcKtoVerlaufElement inst_calc_kto_verlauf_element = null;

    int akt_index = 0;

    inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );

    while ( inst_calc_kto_verlauf_element != null )
    {
      if ( inst_calc_kto_verlauf_element.getArt() == pArt )
      {
        return akt_index;
      }

      akt_index++;
      inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );
    }

    /*
     * Wurde keine Uebereinstimmung gefunden, wird null zurueckgegeben.
     */
    return -1;
  }

  /**
   * <pre>
   * Liefert eine Instanz mit der uebergebenen ID, oder null, wenn keine Uebereinstimmung gefunden wurde.
   * 
   * Der Parameter "pIdNrVergleich" wird so umgesetzt:
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
   * </pre>
   *
   * @param  pSuchString die zu suchende Zeichenfolge
   * @param  pIdNrVergleich identifiziert nach welchen Daten verglichen werden soll
   *
   * @return  die erste Instanz mit der ID, ansonsten null
   */
  public CalcKtoVerlaufElement getIndexByStringID( String pSuchString, int pIdNrVergleich )
  {
    // CalcKtoVerlaufElement = calc_kto_verlauf_element inst_calc_kto_verlauf_element.getIndexByStringID( "ID", 0 );

    if ( ( pSuchString != null ) && ( pSuchString.trim().length() > 0 ) )
    {
      CalcKtoVerlaufElement inst_calc_kto_verlauf_element = null;

      String str_verlgeich = null;

      int akt_index = 0;

      inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );

      /*
       * While-Schleife ueber alle Instanzen des Vektors
       */
      while ( inst_calc_kto_verlauf_element != null )
      {
        /*
         * Ermittlung Vergleichsstring
         */
        str_verlgeich = inst_calc_kto_verlauf_element.getString( pIdNrVergleich );

        //
        // str_verlgeich = inst_calc_kto_verlauf_element.getAnzahlZinstage();         
        // str_verlgeich = inst_calc_kto_verlauf_element.getArt();                    
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragAuszahlung();       
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragSaldenSumme();
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragBwz();              
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragEinzahlungNeu();    
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragEinzahlungReset();  
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragGebuehr();          
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragFoerderung();       
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragKontostand();       
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragTilgAnteil();       
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragUjBonusZins();      
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragUjGuthabenzins();   
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragZinsAnteil();       
        // str_verlgeich = inst_calc_kto_verlauf_element.getBetragZinsgutschrift();   
        // str_verlgeich = inst_calc_kto_verlauf_element.getBezeichnung();            
        // str_verlgeich = inst_calc_kto_verlauf_element.getBonusZinsen();            
        // str_verlgeich = inst_calc_kto_verlauf_element.getBsdInfoTilgAnteil();      
        // str_verlgeich = inst_calc_kto_verlauf_element.getBsdInfoZinsAnteil();      
        // str_verlgeich = inst_calc_kto_verlauf_element.getDatum();                  
        // str_verlgeich = inst_calc_kto_verlauf_element.getKnzAktiveZahlung();       
        // str_verlgeich = inst_calc_kto_verlauf_element.getKnzLoeschung();           
        // str_verlgeich = inst_calc_kto_verlauf_element.getSortId();                 
        // str_verlgeich = inst_calc_kto_verlauf_element.getStrArt();                 
        // str_verlgeich = inst_calc_kto_verlauf_element.getElementId();              
        //

        /*
         * Vergleich mit der uebergebenen ID
         */
        if ( str_verlgeich != null )
        {
          if ( str_verlgeich.compareTo( pSuchString ) == 0 )
          {
            return inst_calc_kto_verlauf_element;
          }
        }

        akt_index++;
        inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );
      }
    }

    /*
     * Wurde keine Uebereinstimmung gefunden, wird null zurueckgegeben.
     */
    return null;
  }

  //getCalcKtoVerlaufElementElementId()
  /**
   * <pre>
   * Liefert einen String fuer die Sortierung.
   * 
   * Der Parameter "pSortierkriterium" wird so umgesetzt:
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
   * </pre>
   * 
   * @param  pIndex         Index im Vektor
   * @param  pSortierkriterium identifiziert nach welchen Daten sortiert werden soll
   * 
   * @return die Stringrepraesentation des abgefragten Sortierstrings des Indexes
   */
  private String getSortID( int pIndex, int pSortierkriterium )
  {
    return getIndexCalcKtoVerlaufElement( pIndex ).getSortId();
  }

  /**
   * Quick-Sort fuer den Vektor
   */
  private void quickSort( int pIndexStart, int pIndexEnde, int pSortierkriterium, boolean pKnzAufsteigend )
  {
    int index_start = pIndexStart;
    int index_ende = pIndexEnde;

    String vergleichs_string_mitte = getSortID( ( index_start + index_ende ) / 2, pSortierkriterium );

    while ( index_start <= index_ende )
    {
      if ( pKnzAufsteigend )
      {
        while ( getSortID( index_start, pSortierkriterium ).compareTo( vergleichs_string_mitte ) < 0 )
        {
          index_start++;
        }

        while ( getSortID( index_ende, pSortierkriterium ).compareTo( vergleichs_string_mitte ) > 0 )
        {
          index_ende--;
        }
      }
      else
      {
        while ( getSortID( index_start, pSortierkriterium ).compareTo( vergleichs_string_mitte ) > 0 )
        {
          index_start++;
        }

        while ( getSortID( index_ende, pSortierkriterium ).compareTo( vergleichs_string_mitte ) < 0 )
        {
          index_ende--;
        }
      }

      if ( index_start <= index_ende )
      {
        /*
         * Die Vertauschung der Elemente wird mit Java-Object-Klassen in der Vektorklasse gemacht.
         */
        m_calc_kto_verlauf_element_vector.swap( index_start, index_ende );

        index_start++;
        index_ende--;
      }
    }

    if ( pIndexStart < index_ende )
    {
      quickSort( pIndexStart, index_ende, pSortierkriterium, pKnzAufsteigend );
    }

    if ( index_start < pIndexEnde )
    {
      quickSort( index_start, pIndexEnde, pSortierkriterium, pKnzAufsteigend );
    }
  }

  /**
   * Startfunktion fuer die Vektorsortierung
   * 
   * @param  pSortierkriterium identifiziert nach welchen Daten sortiert werden soll
   * @param  pKnzAufsteigend Kennzeichen, ob Aufsteigend sortiert werden soll
   * 
   * @return 1 bei erfolgreicher Sortierung, 0 bei einem Fehler waehrend der Sortierung
   */
  public int startSort()
  {
    if ( m_calc_kto_verlauf_element_vector != null && m_calc_kto_verlauf_element_vector.hasWerte() )
    {
      quickSort( 0, getCalcKtoVerlaufElementVector().size() - 1, 22, true );

      return 1;
    }

    return 0;
  }

  /**
   * Startfunktion fuer die Vektorsortierung und der Sortierung bei allen Elementen
   *
   * @param    pSortierkriterium identifiziert nach welchen Daten sortiert werden soll
   * @param    pKnzAufsteigend Kennzeichen, ob Aufsteigend sortiert werden soll
   *
   * @return   1 bei erfolgreicher Sortierung, 0 bei einem Fehler waehrend der Sortierung
   */
  public int startVektorSort( int pSortierkriterium, boolean pKnzAufsteigend )
  {
    /*
     * Sortierung in dieser Klasse durchfuehren
     */
    int fkt_knz_ergebnis = startSort();

    /*
     * Sortierung bei den Elementen wird nur dann gemacht,
     * wenn die Sortierung in dieser Klasse erfolgreich war.
     */
    if ( fkt_knz_ergebnis == 1 )
    {

      /*
       * Bei allen Elementen des Vektors die Funktion "VektorSortAufrufen"
       */
      int akt_index = 0;

      CalcKtoVerlaufElement inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );

      /*
       * Die While-Schleife wird solange ausgefuehrt, wie
       * ... es noch Elmenete im Vektor gibt
       * ... das Funktionsergebnis noch auf 1 (=OK) steht
       */
      while ( ( inst_calc_kto_verlauf_element != null ) && ( fkt_knz_ergebnis == 1 ) )
      {
        try
        {
          /*
           * Aufruf der Sort-Funktion am aktuellen Element.
           * Das Ergebnis der Sort-Funktion wird hier in der
           * Variablen fuer das Funktionsergebnis zugewiesen.
           */
          // fkt_knz_ergebnis = inst_calc_kto_verlauf_element.startVektorSort( pSortierkriterium, pKnzAufsteigend );

          /*
           * Nur wenn es zu keinem Fehler gekommen ist, wird
           * der Vektorindex hochgezaehlt.
           *
           * Kommt es zu einem Fehler, wird das Funktionsergebnis
           * auf 0 gestellt und die While-Schleife ist beendet.
           */
          akt_index++;

          inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );
        }
        catch ( Exception err_inst )
        {
          fkt_knz_ergebnis = 0;
        }
      }
    }

    /*
     * Am Funktionsende wird das Ergebnis zurueckgegeben.
     */
    return fkt_knz_ergebnis;
  }

  /**
   * <pre>
   * Ist eine Vektorinstanz vorhanden, wird bei allen Elementen 
   * die Funktion "clear()" aufgerufen.
   * 
   * Anschliessend wird die Vektorinstanz auf null gesetzt.
   * </pre>
   */
  public void clear()
  {
    //
    //if ( m_calc_kto_verlauf_element_vector != null )
    //{
    //  m_calc_kto_verlauf_element_vector.clear();
    //}
    //
    //m_calc_kto_verlauf_element_vector = null;
    //

    if ( m_calc_kto_verlauf_element_vector != null )
    {
      int akt_index = 0;

      CalcKtoVerlaufElement inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );

      while ( inst_calc_kto_verlauf_element != null )
      {
        inst_calc_kto_verlauf_element.clear();

        akt_index++;
        inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );
      }

      m_calc_kto_verlauf_element_vector.clear();
    }

    m_calc_kto_verlauf_element_vector = null;
  }

  public void removeMarkierteElemente()
  {
    if ( m_calc_kto_verlauf_element_vector != null )
    {
      int akt_index = 0;

      CalcKtoVerlaufElement inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );

      while ( inst_calc_kto_verlauf_element != null )
      {
        if ( inst_calc_kto_verlauf_element.getKnzLoeschung() == 1 )
        {
          //inst_calc_kto_verlauf_element.clear();

          if ( m_calc_kto_verlauf_element_vector.removeAt( akt_index ) == false )
          {
            akt_index++;

            System.out.println( "m_calc_kto_verlauf_element_vector.remove - fehler" );
          }
        }
        else
        {
          /*
           * Sollte das aktuelle Element nicht entfernt werden, 
           * wird der Index erhoeht. 
           */
          akt_index++;
        }

        inst_calc_kto_verlauf_element = getIndexCalcKtoVerlaufElement( akt_index );
      }

    }
  }
}
