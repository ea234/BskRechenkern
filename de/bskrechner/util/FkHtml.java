package de.bskrechner.util;

public class FkHtml
{
  public static String getHtmlGeruest( String pInhalt, String pBodyStyle )
  {
    NewLineStringBuffer ergebnis_html = new NewLineStringBuffer();

    ergebnis_html.append( "<html>" );
    ergebnis_html.append( "<meta http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\">" );
    ergebnis_html.append( "<meta name=\"referrer\" content=\"no-referrer\">" );
    ergebnis_html.append( "<head>" );
    ergebnis_html.append( "<title>Titel</title>" );
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
}