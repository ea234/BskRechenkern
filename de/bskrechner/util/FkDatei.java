package de.bskrechner.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <pre>
 * Sammlung von Funktionen im Zusammenhang mit der Dateiverarbeitung.
 * </pre>
 */
public class FkDatei
{
  /**
   * <pre>
   * Erstellt die Datei und schreibt den "pInhalt" rein.
   * Ist kein "pInhalt" null wird eine leere Datei erstellt.
   * </pre>
   * 
   * @param pDateiName der Dateiname 
   * @param pInhalt der zu schreibende Inhalt
   * @return TRUE wenn die Datei geschrieben werden konnte, sonst False
   */
  public static boolean schreibeDatei( String pDateiName, String pInhalt )
  {
    try
    {
      FileWriter output_stream = new FileWriter( pDateiName, false );

      if ( pInhalt != null )
      {
        output_stream.write( pInhalt );
      }

      output_stream.flush();

      output_stream.close();

      output_stream = null;

      return true;
    }
    catch ( Exception abgf_fehler )
    {
      FkLog.wl( "Fehler: errSchreibeDatei", abgf_fehler );
    }

    return false;
  }

  public static boolean systemSchreibeDatei( String pDateiName, String pInhalt )
  {
    boolean knz_ergebnis = false;

    OutputStream output_stream = null;

    try
    {
      output_stream = new FileOutputStream( pDateiName );

      if ( pInhalt != null )
      {
        output_stream.write( pInhalt.getBytes() );
      }

      output_stream.flush();

      knz_ergebnis = true;
    }
    catch ( Exception e )
    {
      FkLog.wl( "Fehler: errSchreibeDatei 1 ", e );
    }

    try
    {
      output_stream.close();
    }
    catch ( IOException e )
    {
      knz_ergebnis = false;
      FkLog.wl( "Fehler: errSchreibeDatei 2 ", e );
    }

    output_stream = null;

    return knz_ergebnis;
  }

  /**
   * <pre>
   * Wandelt alle Slashes im Verzeichnisnamen in den pPathSeparator um. 
   * Ueber den boolschen Parameter kann die Funktion angewiesen werden 
   * auch den letzten Path-Separator zu setzen (bzw. zu pruefen).
   * 
   * FkDatei.getVerzeichnisPathSeparator( "a/b\c/d\e", "\", true  ) = "a\b\c\d\e\"
   * FkDatei.getVerzeichnisPathSeparator( "a/b\c/d\e", "\", false ) = "a\b\c\d\e"
   * 
   * FkDatei.getVerzeichnisPathSeparator( "a/b\c/d\e", "/", true  ) = "a/b/c/d/e/"
   * FkDatei.getVerzeichnisPathSeparator( "a/b\c/d\e", "/", false ) = "a/b/c/d/e"
   * 
   * 
   * FkDatei.getVerzeichnisPathSeparator( "a/b\c/d\e", null, false ) = "a\b\c\d\e"  = Vorgabe fuer Path-Separator
   * FkDatei.getVerzeichnisPathSeparator( "a/b\c/d\e\", "\", true  ) = "a\b\c\d\e\" = letzter Slash schon vorhanden
   * 
   * FkDatei.getVerzeichnisPathSeparator( null,        "/", false ) = null   = Verzeichnis ist null
   * FkDatei.getVerzeichnisPathSeparator( "    ",      "/", false ) = "    " = Verzeichnis ist Leerstring
   * 
   * Slashes innerhalb einer Pfadangabe aendern:
   * 
   * FkDatei.getVerzeichnisPathSeparator( "c:/a/b/c/d/e/datei_name.txt", "\", false ) = "c:\a\b\c\d\e\datei_name.txt"
   * 
   * UNC-Verzeichnisangaben:
   * FkDatei.getVerzeichnisPathSeparator( "//server.de/freigabe/verzeichnis/", null, true ) = "\\server.de\freigabe\verzeichnis\"
   * FkDatei.getVerzeichnisPathSeparator( "\\server.de\freigabe/verzeichnis",  null, true ) = "\\server.de\freigabe\verzeichnis\"
   * 
   * </pre>
   * 
   * @param pVerzeichnisName der Pfadname
   * @param pPathSeparator der zu setzende Separator-Slash 
   * @param pKnzEndzeichenSetzen Kennzeichen, um am Ende ein Slash eventuell hinzuzufuegen 
   * @return den gewandelten Verzeichnisnamen
   */
  public static String getVerzeichnisPathSeparator( String pVerzeichnisName, String pPathSeparator, boolean pKnzEndzeichenSetzen )
  {
    /*
     * Pruefung: Verzeichnisparameter
     * ... bei null wird null zurueckgegeben 
     * ... bei einem leerstring wird der Parameter zurueckgegeben.
     */
    if ( pVerzeichnisName == null ) return pVerzeichnisName;

    if ( pVerzeichnisName.trim().length() == 0 ) return pVerzeichnisName;

    /*
     * Pruefung: Separator
     * Ist der Separator null, wird dieser auf den Vorgabeslash gestellt.
     */
    if ( pPathSeparator == null )
    {
      pPathSeparator = "\\";
    }

    StringBuffer ergebnis = new StringBuffer();

    int length = pVerzeichnisName.length();

    int index_eingabe = 0;

    boolean knz_endzeichen_notwendig = false;

    boolean knz_letztes_zeichen_war_seperator = false;

    /*
     * Pruefung: UNC-Namen
     * UNC-Namen starten auf \\ oder auf //. 
     * Es wird der Anfang auf diese beiden Startarten geprueft. 
     * Sollte der Verzeichnisname so anfangen, wird der Index fuer die While-Schleife auf 2 gesetzt.
     * Im ergebnis werden 2 mal der uebergebene Seperator gesetzt.
     */
    if ( ( pVerzeichnisName.startsWith( "\\\\" ) ) || ( pVerzeichnisName.startsWith( "//" ) ) )
    {
      index_eingabe = 2;

      ergebnis.append( pPathSeparator );

      ergebnis.append( pPathSeparator );
    }

    while ( index_eingabe < length )
    {
      char akt_zeichen = pVerzeichnisName.charAt( index_eingabe );

      switch ( akt_zeichen )
      {
        case '\\' :

          if ( knz_letztes_zeichen_war_seperator == false )
          {
            ergebnis.append( pPathSeparator );

            knz_endzeichen_notwendig = false;

            knz_letztes_zeichen_war_seperator = true;
          }
          break;

        case '/' :

          if ( knz_letztes_zeichen_war_seperator == false )
          {
            ergebnis.append( pPathSeparator );

            knz_endzeichen_notwendig = false;

            knz_letztes_zeichen_war_seperator = true;
          }
          break;

        default :

          ergebnis.append( akt_zeichen );

          knz_endzeichen_notwendig = true;

          knz_letztes_zeichen_war_seperator = false;
      }

      index_eingabe++;
    }

    if ( pKnzEndzeichenSetzen && knz_endzeichen_notwendig )
    {
      ergebnis.append( pPathSeparator );
    }

    return ergebnis.toString();
  }

  /**
   * <pre>
   * Erstellt das uebergebene Verzeichnis inkl. aller Unterverzeichnisse. 
   * Sollte das Verzeichnis schon existieren wird TRUE zurueckgegeben, 
   * ansonsten der Rueckgabewert der Funktion "mkdirs()".
   * </pre>
   * 
   * @param pVerzeichnisName das zu erstellende Verzeichnis (null = Rueckgabe FALSE)
   * @return TRUE wenn das Verzeichnis angelegt werden konnte, sonst false 
   */
  public static boolean createDirectory( String pVerzeichnisName )
  {
    if ( ( pVerzeichnisName == null ) || ( pVerzeichnisName.length() == 0 ) )
    {
      return false;
    }

    File file_instanz = new File( pVerzeichnisName );

    if ( file_instanz.exists() ) return true;

    return file_instanz.mkdirs();
  }
}
