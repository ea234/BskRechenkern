package de.bskrechner.util;

/**
 * <pre>
 * Hilfsfunktionen fuer die Stringverarbeitung.
 * </pre>
 */
public class FkString
{
  private static final char   UMLAUT_U_KLEIN          = 0x00fc;

  private static final char   UMLAUT_U_GROSS          = 0x00dc;

  private static final char   UMLAUT_SZ               = 0x00df;

  private static final char   UMLAUT_O_KLEIN          = 0x00f6;

  private static final char   UMLAUT_O_GROSS          = 0x00d6;

  private static final char   UMLAUT_A_KLEIN          = 0x00e4;

  private static final char   UMLAUT_A_GROSS          = 0x00c4;

  /** Konstante fuer ein Leerzeichen */
  private static final String LEERZEICHEN             = " ";

  /** Konstante fuer einen Leerstring */
  static final String         LEERSTRING              = "";

  /** Alle Zeichenfolgen, welchen einen wahren boolschen Wert ergeben koennen. Durch Komma getrennt. ",true,yes,ja,y,j,1,on,+,ein,wahr,ok," */
  private static final String STR_WERTE_BOOLEAN_TRUE  = ",true,yes,ja,y,j,1,on,+,ein,wahr,ok,";

  private static final String STR_WERT_BOOLEAN_TRUE   = ",true,";

  /** Alle Zeichenfolgen, welchen einen nicht wahren boolschen Wert ergeben koennen. Durch Komma getrennt. ",false,no,nein,n,0,off,-,aus,falsch," */
  private static final String STR_WERTE_BOOLEAN_FALSE = ",false,no,nein,n,0,off,-,aus,falsch,";

  private static final String STR_WERT_BOOLEAN_FALSE  = ",false,";

  /** Ein String mit den Zahlen als Inhalt */
  private static final String STR_ZAHLEN              = "0123456789";

  /** Ein String mit den Buchstaben des ABC in Gross */
  private static final String STR_ABC_GROSS           = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + FkString.getStringSonderzeichenDE( "", 1 );

  /** Ein String mit den Buchstaben des ABC in klein */
  private static final String STR_ABC_KLEIN           = "abcdefghijklmnopqrstuvwxyzss" + FkString.getStringSonderzeichenDE( "", -1 );

  /** Ein String mit einigen Sonderzeichen */
  private static final String STR_SONDERZEICHEN       = " _?!\"§$%&/()<>[]{}=*'/*-+:;,.#\\/\n";

  /**
   * <pre>
   * Liefert einen String mit den deutschen Umlauten zurueck.
   * Notwendig um Zeichensatzprobleme zu vermeiden. 
   *
   * FkString.getStringSonderzeichenDE( null,  0 ) = ÄÖÜäöüß
   * FkString.getStringSonderzeichenDE( ", ",  0 ) = Ä, Ö, Ü, ä, ö, ü, ß
   * 
   * FkString.getStringSonderzeichenDE( null,  1 ) = ÄÖÜ
   * FkString.getStringSonderzeichenDE( ", ",  1 ) = Ä, Ö, Ü
   * 
   * FkString.getStringSonderzeichenDE( null, -1 ) = äöüß
   * FkString.getStringSonderzeichenDE( ", ", -1 ) = ä, ö, ü, ß
   * </pre>
   * 
   * @param pTrennzeichen das zu verwendende Trennzeichen
   * @param pKnz 0 gross und klein, 1 nur gross, -1 nur klein
   * @return den erstellten String 
   */
  private static String getStringSonderzeichenDE( String pTrennzeichen, int pKnz )
  {
    // " + FkString.getStringSonderzeichenDE( "" , 0 ) + "

    String str_trennzeichen = ( pTrennzeichen == null ? "" : pTrennzeichen );

    return ( pKnz >= 0 ? FkString.UMLAUT_A_GROSS + str_trennzeichen + FkString.UMLAUT_O_GROSS + str_trennzeichen + FkString.UMLAUT_U_GROSS : "" ) + ( pKnz == 0 ? str_trennzeichen : "" ) + ( pKnz <= 0 ? FkString.UMLAUT_A_KLEIN + str_trennzeichen + FkString.UMLAUT_O_KLEIN + str_trennzeichen + FkString.UMLAUT_U_KLEIN + str_trennzeichen + FkString.UMLAUT_SZ : "" );
  }

  /**
   * @param pEingabe die zu behandelnde Eingabe als Byte-string
   * @param pWeitereZeichen Zeichen, welche auch aus der Eingabe in das Ergebnis uebernommen werden (zusaetzlich zu ABC, Zahlen und Sonderzeichen)
   * @return einen String mit nur den definierten Rueckgabezeichen und den zusaetzlich angegebenen Zeichen
   */
  static String getStringX( byte[] pEingabe, String pWeitereZeichen )
  {
    String ergebnis = "";

    int laenge_eingabe = pEingabe.length;

    int akt_index = 0;

    while ( akt_index < laenge_eingabe )
    {
      char aktuelles_zeichen = (char) pEingabe[ akt_index ];

      if ( pos( STR_ABC_KLEIN, aktuelles_zeichen, 0 ) >= 0 | pos( STR_ZAHLEN, aktuelles_zeichen, 0 ) >= 0 | pos( STR_ABC_GROSS, aktuelles_zeichen, 0 ) >= 0 | pos( STR_SONDERZEICHEN, aktuelles_zeichen, 0 ) >= 0 | pos( pWeitereZeichen, aktuelles_zeichen, 0 ) >= 0 )
      {
        ergebnis = ergebnis + aktuelles_zeichen;
      }

      akt_index++;
    }

    return ergebnis;
  }

  /**
   * <pre>
   * Fuehrt im Endeffekt <code>pString.indexOf( pSuchString, pAbPosition )</code> aus.
   * </pre>
   * 
   * @param pString der Eingabestring 
   * @param pSuchString das zu suchende Zeichen als char
   * @param pAbPosition die Position ab welcher die Suche beginnen soll
   * @return die Position des Auftretens, oder -1 sofern der Suchstring nicht vorhanden oder pString = null ist
   */
  private static int pos( String pString, char pSuchString, int pAbPosition )
  {
    if ( pString == null )
    {
      return -1;
    }

    return pString.indexOf( "" + pSuchString, pAbPosition );
  }

  /**
   * <pre>
   * Schneidet die Anzahl-Stellen von dem uebergebenen String ab und gibt diesen zurueck.
   * 
   * Uebersteigt die Anazhl der abzuschneidenden Stellen die Stringlaenge, wird der 
   * Quellstring insgesamt zurueckgegeben.
   * 
   * Ist die Anzahl der abzuschneidenden Stellen negativ oder 0, wird ein Leerstring zurueckgegeben.
   * 
   * FkString.right( "ABC.DEF.GHI.JKL",  7 ) = "GHI.JKL"
   * FkString.right( "ABC.DEF.GHI.JKL", 20 ) = "ABC.DEF.GHI.JKL" = Anzahl Stellen uebersteigt Stringlaenge
   * FkString.right( "ABC.DEF.GHI.JKL",  0 ) = ""                = 0 Stellen abschneiden = Leerstring
   * FkString.right( "ABC.DEF.GHI.JKL", -7 ) = ""                = negative Anzahl       = Leerstring
   * 
   * </pre>
   * 
   * @param pString der Quellstring
   * @param pAnzahlStellen die Anzahl der von rechts abzuschneidenden Stellen
   * @return der ermittelte Teilstring
   */
  public static String right( String pString, int pAnzahlStellen )
  {
    if ( ( pString != null ) && ( pAnzahlStellen > 0 ) )
    {
      /*
       * Die Ab-Position ist die Laenge des Eingabestrings, abzueglich der von 
       * rechts abzuschneidenden Stellen. Die Ab-Postion darf aber nicht negativ
       * werden. Die minimale Ab-Position ist der Stringanfang (Position 0).
       * 
       * Die Bis-Position ist die laenge des Eingabestrings.
       */
      return pString.substring( Math.max( 0, pString.length() - pAnzahlStellen ), pString.length() );
    }
    /*
     * pString nicht gesetzt oder pAnzahlStellen < 0
     */
    return LEERSTRING;
  }

  /**
   * <pre>
   * Erzeugt einen Substring der Eingabe bis zur vorgegebenen Position
   * Ist die Positon negativ oder der String nicht gesetzt, wird ein Leerstring zurueckgeben.
   * Ist die Positon groesser als die Laenge des Stringes wird der gesamte String zurueckgegeben.
   * 
   * Fuehrt im Endeffekt <code>pString.substring( 0, pBisPosition )</code> aus, behandelt 
   * aber null-Referenzen und Bedingung bei pBisPosition.
   * </pre>
   * 
   * @param pString der String
   * @param pBisPosition bis zu welcher Stelle abgeschnitten werden soll
   * @return einen Substring bis zur angegebenen Stelle 
   */
  static String getStringBis( String pString, int pBisPosition )
  {
    if ( ( pString != null ) && ( pString.length() > 0 ) && ( pBisPosition > 0 ) )
    {
      if ( pBisPosition >= pString.length() ) return pString;

      return pString.substring( 0, pBisPosition );
    }

    return LEERSTRING;
  }

  /**
   * <pre>
   * Gibt einen String in der angegebenen Laenge und der angegebenen Zeichenfolge zurueck.
   *  
   * Ist die Laenge negativ oder 0, wird ein Leerstring zurueckgegeben
   * 
   * Ist der Parameeter "pZeichen" gleich null, wird ein Leerstring zurueckgegeben.
   * </pre>
   * 
   * @param pAnzahlStellen die Laenge
   * @param pZeichen das zu wiederholende Zeichen
   * @return einen String der angegebenen Laenge mit dem uebergebenen Zeichen
   */
  private static String nZeichen( int pAnzahlStellen, String pZeichen )
  {

    if ( pZeichen == null ) return "";

    /*
     * Ist die Laenge negativ oder 0, wird ein Leerstring zurueckgegeben
     */
    if ( pAnzahlStellen <= 0 ) return "";

    if ( pAnzahlStellen > 15000 ) pAnzahlStellen = 15000;

    String ergebnis = pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen + pZeichen;

    /*
     * Der String "ergebnis" wird solange verdoppelt bis die Laenge groesser der Anzahl aus dem Parameter ist. 
     * Anschliessend wird ein Substring der Parameter-Laenge zurueckgegeben.
     */
    int zaehler = 1;

    while ( ( zaehler <= 50 ) && ( ergebnis.length() <= pAnzahlStellen ) )
    {
      ergebnis += ergebnis;

      zaehler++;
    }

    return ergebnis.substring( 0, pAnzahlStellen );
  }

  /**
   * Aufruf der gleichnamigen Funktion getFeldLinks mit dem Leerzeichen als Auffuellparameter.
   * 
   * @param pInhalt der Inhalt des Feldes
   * @param pLaenge die Laenge des Feldes
   * @return ein Feld in der angegebenen Laenge, wobei der Feldinhalt links steht und evtl. mit
   * Leerzeichen aufgefuellt wurde
   */
  public static String getFeldLinks( String pInhalt, int pLaenge )
  {
    return getFeldLinks( pInhalt, " ", pLaenge );
  }

  /**
   * Gibt einen String der uebergebenen Laenge zurueck, wobei der Feldinhalt links steht.
   * 
   * @param pInhalt der Inhalt des Feldes
   * @param pAuffuellZeichen das zur Auffuellung vorgegebene Zeichen
   * @param pLaenge die Laenge des Feldes
   * @return ein Feld in der angegebenen Laenge, wobei der Feldinhalt links steht und evtl. mit dem
   * uebergebenen Auffuellzeichen aufgefuellt wurde
   */
  private static String getFeldLinks( String pInhalt, String pAuffuellZeichen, int pLaenge )
  {
    if ( pInhalt == null ) pInhalt = "";

    if ( pInhalt.length() >= pLaenge )
    {
      return pInhalt.substring( 0, pLaenge );
    }

    return pInhalt + nZeichen( pLaenge - pInhalt.length(), pAuffuellZeichen );
  }

  /**
   * @param pInhalt der Inhalt des Feldes
   * @param pMindestLaenge die vorgegebene Mindestlaenge des Rueckgabestrings
   * @return einen String, welcher mindestens pMindesLaenge breit ist (evtl. aufgefuellt mit Leerzeichen)
   */
  static String getFeldLinksMin( String pInhalt, int pMindestLaenge )
  {
    if ( pInhalt == null ) pInhalt = "";

    if ( pInhalt.length() >= pMindestLaenge )
    {
      return pInhalt;
    }

    return pInhalt + nZeichen( pMindestLaenge - pInhalt.length(), " " );
  }

  /**
   * @param pInhalt der Inhalt des Feldes
   * @param pAuffuellZeichen das zu benutzende Auffuellzeichen
   * @param pLaenge die Laenge
   * @return ein String der vorgegebenen Laenge und dem Inhalt rechts ausgerichtet
   */
  private static String getFeldRechts( String pInhalt, String pAuffuellZeichen, int pLaenge )
  {
    if ( pInhalt == null ) pInhalt = "";

    if ( pInhalt.length() >= pLaenge )
    {
      return pInhalt.substring( 0, pLaenge );
    }

    return nZeichen( pLaenge - pInhalt.length(), pAuffuellZeichen ) + pInhalt;
  }

  /**
   * @param pInhalt der Inhalt als int
   * @param pLaenge die vorgegebene Laenge
   * @return ein String der vorgegebebenen Laenge und dem Inhalt rechts ausgerichtet
   */
  public static String getFeldRechts( int pInhalt, int pLaenge )
  {
    return getFeldRechts( LEERSTRING + pInhalt, LEERZEICHEN, pLaenge );
  }

  /**
   * <pre>
   * Ermittelt einen Boolschen Wert aus dem uebergebenen String unabhaengig von der
   * Gross/Kleinschreibung. Bei null wird der Vorgabewert zurueckgegeben.
   * 
   * TRUE - 1, j, y, +, t, yes, true, ja, ein, an 
   * FALSE - 0, n, -, f, no, false, nein, aus, 
   * </pre>
   * @param pString der Wert, welcher entweder einen Zustand true oder false beschreibt
   * @param pVorgabeWert der Vorgabewert fuer keine Uebereinstimmung
   * @return true oder false
   */
  public static boolean getBoolean( String pString, boolean pVorgabeWert )
  {
    if ( ( pString != null ) && ( pString.length() > 0 ) )
    {
      if ( STR_WERT_BOOLEAN_TRUE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return true;
      }

      if ( STR_WERT_BOOLEAN_FALSE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return false;
      }

      /*
       * Damit keine Teilzeichenfolgen gefunden werden, wird ein Komma 
       * vor die Eingabe gestellt. Die Uebereinstimmung muss also mit einem
       * Komma beginnen. 
       */
      if ( STR_WERTE_BOOLEAN_TRUE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return true;
      }

      if ( STR_WERTE_BOOLEAN_FALSE.indexOf( "," + pString.toLowerCase() ) >= 0 )
      {
        return false;
      }
    }

    return pVorgabeWert;
  }

  /**
   * <pre>
   * Wandelt den uebergebenen String in Grossbuchstaben um.
   *  
   * Bei einer Uebergabe von null wird null zurueckgegeben.
   * 
   * Fuehrt im Endeffekt "pString.toUpperCase()" aus
   * </pre>
   * @param pString die zu wandelnde Zeichenkette
   * @return der String in Grossbuchstaben, oder null
   */
  public static String ucase( String pString )
  {
    if ( pString != null )
    {
      return pString.toUpperCase();
    }

    return null;
  }

  /**
   * <pre>
   * Ersetzt alle Vorkommen des Suchstrings in pQuellstring mit der Zeichenfolge pStringNeu.
   * </pre>
   * 
   * @param pQuellString der zu durchsuchende String
   * @param pSuchString der Suchstring
   * @param pStringNeu der Ersatzstring fuer den Suchstring (Leerstring oder null = Eliminierung Suchstring)
   * @return ein String, in welchem die Suchzeichenfolge durch die Ersatzzeichenfolge ersetzt wurde
   */
  static String replace( String pQuellString, String pSuchString, String pStringNeu )
  {
    return replace( pQuellString, pSuchString, pStringNeu, 1 );
  }

  /**
   * <pre>
   * Ersetzt alle Vorkommen des Suchstrings in pQuellstring mit der Zeichenfolge pStringNeu.
   * Die Gross-Kleinschreibung kann mit der Uebergabe von 0 im Parameter "pKnzGrossKleinschreibung" ignoriert werden.
   * Alle anderen Werte im Parameter "pKnzGrossKleinschreibung" fuehren zu einer Beachtung der GK-Schreibung. 
   * 
   * FkString.replace( "ABC.XYZ.def.xyz.GHI.xYz.jkl", ".XYZ.",  "-", 1 ) = "ABC-def.xyz.GHI.xYz.jkl"
   * FkString.replace( "ABC.XYZ.def.xyz.GHI.xYz.jkl", ".XYZ.",  "-", 0 ) = "ABC-def-GHI-jkl"
   * FkString.replace( "ABC.XYZ.def.xyz.GHI.xYz.jkl", ".jkl",   "-", 0 ) = "ABC.XYZ.def.xyz.GHI.xYz-"
   * FkString.replace( "ABC.XYZ.def.xyz.GHI.xYz.jkl", ".jkl",  null, 0 ) = "ABC.XYZ.def.xyz.GHI.xYz"
   * </pre>
   * 
   * @param pQuellString der zu durchsuchende String
   * @param pSuchString der Suchstring
   * @param pStringNeu der Ersatzstring fuer den Suchstring ( Leerstring oder null = Eliminierung Suchstring )
   * @param pKnzGrossKleinschreibung Kennzeichen, ob die Gross/Kleinschreibung beim Suchen beachtet werden soll (1=beachten (voreingestellt), 0=ignorieren)
   * @return ein String, in welchem die Suchzeichenfolge durch die Ersatzzeichenfolge ersetzt wurde
   */
  private static String replace( String pQuellString, String pSuchString, String pStringNeu, int pKnzGrossKleinschreibung )
  {
    if ( ( pQuellString == null ) || ( pSuchString == null ) ) return pQuellString;

    String such_string_ucase = ""; // Suchtext 
    String quell_string_ucase = ""; // der zu durchsuchende Text
    String str_ergebnis = ""; // Stringbuffer fuer die Rueckgabe
    int position_such_string = 0; // die aktuell gefundene Startposition des Suchstrings
    int position_such_prozess = 0; // die aktuelle AB-Position fuer die Suche im Quellstring
    int position_such_bis = 32123; // die Position, bis zu welcher gesucht wird.
    int zaehler = 0; // Zaehler zur Vermeidung von Endlossschleifen

    /* 
     * Variableninitialisierung
     * Soll die Gross/Kleinschreibung beruecksichtigt werden, werden die beiden 
     * Strings unveraendert in die funktionsinternen Variablen uebernommen.
     *  
     * Soll die Gross/Kleinschreibung unberuecksichtigt bleiben, wrid der 
     * zu durchsuchende und der Suchstring in Grossbuchstaben gewandelt.
     */
    if ( pKnzGrossKleinschreibung == 0 )
    {
      such_string_ucase = pSuchString.toUpperCase();
      quell_string_ucase = pQuellString.toUpperCase();
    }
    else
    {
      such_string_ucase = pSuchString;
      quell_string_ucase = pQuellString;
    }

    /* 
     * Erste Position des Suchstrings ermitteln
     */
    position_such_string = quell_string_ucase.indexOf( such_string_ucase, position_such_prozess );

    /*
     * Pruefung: Suchstring nicht gefunden ?
     * Ist der Suchstring nicht im Quellstring vorhanden, ist das Funktionsergebnis 
     * gleich dem Quellstring, da es nichts zu ersetzen gibt. Durch Rueckgabe des 
     * Quellstringsie wird diese Funktion beendet.
     */
    if ( position_such_string < 0 )
    {
      return pQuellString;
    }

    /* 
     * Die Suchschleife wird solange durchlaufen wie
     * ... die Position des Suchstrings noch groesser als 0 ist
     * ... der Zaehler noch kleiner der position_such_bis ist ( Vermeidung Endlossschleife )
     */
    while ( ( position_such_string >= 0 ) && ( zaehler < position_such_bis ) )
    {
      /* 
       * Pruefung: Suchstring gefunden ?
       * Das ist der Fall, wenn die Positon einen Wert groesser 0 hat.
       */
      if ( position_such_string >= 0 )
      {
        /* 
         * Ergebnisstring aufbauen
         * 1. Teilstring aus dem Quellstring ab der aktuellen Leseposition bis 
         * zur Fundstelle des Suchstrings in den Ergebnisstring kopieren. 
         */
        str_ergebnis += pQuellString.substring( position_such_prozess, position_such_string );

        /*
         * 2. Die Ersatzzeichenfolge dem Ergebnisstring hinzufuegen. Das hinzufuegen
         * wird nur gemacht wenn die Ersatzzeichenfolge kein Null-Pointer ist. 
         *  
         * Ist "pStringNeu" ein Leerstring (oder eben ein Null-Pointer), wird durch 
         * diese Funktion nur der Suchstring aus dem Quellstring entfernt. Es gibt 
         * keinen Ersatzstring.
         */
        if ( pStringNeu != null )
        {
          str_ergebnis += pStringNeu;
        }

        /* 
         * Position Leseprozess setzen
         * Die neue Startposition fuer den naechsten Suchvorgang beginnt ab der
         * eben gefundenen Position des Suchstrings zuzueglich dessen Laenge.
         */
        position_such_prozess = position_such_string + such_string_ucase.length();
      }

      /* 
       * Position Suchstring ermitteln
       * Im Upper-Case-Quellstring wird der Upper-Case-Suchstring gesucht. Somit
       * wird die Gross/Klein-Schreibung eliminiert. Die Position wird in der
       * Variablen "position_such_string" gespeichert.
       */
      position_such_string = quell_string_ucase.indexOf( such_string_ucase, position_such_prozess );

      /* 
       * Zaehler erhoehen
       * Der Zaehler fuer die Vermeidung einer Endlosschleife wird um 1 erhoeht.
       */
      zaehler++;
    }

    /* 
     * Pruefung: wurden alle Zeichen der Eingabe behandelt ?
     * Ist nach der Schleife die Position des Suchprozesses kleiner als die
     * Laenge des Quellstrings, wird der Rest vom Quellstring ab der letzten
     * Leseposition dem Ergebnis hinzugefuegt. 
     */
    if ( position_such_prozess < pQuellString.length() )
    {
      str_ergebnis += pQuellString.substring( position_such_prozess );
    }

    return str_ergebnis;
  }
}
