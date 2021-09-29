package de.bskrechner.calc;

import java.util.ArrayList;
import java.util.List;

public class CalcBsvVektor
{
  /**
   * Instanz fuer die Speicherung der Objekte
   */
  private List m_vector = null;

  /**
   * <pre>
   * Gibt das Objekt fuer die interne Speicherung der Objekte zurueck.
   * Existiert das Objekt noch nicht, wird dieses erzeugt.
   * </pre>
   * 
   * @return das Speicherobjekt 
   */
  private List getVector()
  {
    if ( m_vector == null )
    {
      m_vector = new ArrayList();
    }

    return m_vector;
  }

  /**
   * <pre>
   * Liefert das Objekt am uebergebenen Index. 
   * Existiert der Index nicht, wird null zurueckgegeben.
   * </pre>
   * 
   * @param pIndex der Speicherindex im Vektor
   * @return das gespeicherte Objekt sofern es existiert, ansonsten null
   */
  private Object getIndex( int pIndex )
  {
    try
    {
      return getVector().get( pIndex );
    }
    catch ( IndexOutOfBoundsException err_inst )
    {
      // Keine Fehlerbehandlung da im Fehlerfall rueckgabe von null 
    }

    return null;
  }

  /**
   * Fuegt das uebergebene Objekt dem Vektor hinzu.
   * 
   * @param pObject das hinzuzufuegende Objekt.
   */
  void add( Object pObject )
  {
    getVector().add( pObject );
  }

  /**
   * Liefert die Information, ob der Vektor ueber gespeicherte Elemente verfuegt.
   * 
   * @return TRUE wenn die interne Variable gesetzt und Werte hat, ansonsten FALSE
   */
  boolean hasWerte()
  {
    return ( m_vector != null ) && ( m_vector.size() > 0 );
  }

  /**
   * Liefert die Anzahl der gespeicherten Elemente zurueck.
   * 
   * @return Anzahl der gespeicherten Elemente
   */
  int size()
  {
    return getVector().size();
  }

  /**
   * Bereitstellung der Vektor-Funktion get( index ), inklusive der Fehlermeldung indexOutOfBound
   *  
   * @return das Element am uebergebenen Index
   */
  Object get( int pIndex )
  {
    return getVector().get( pIndex );
  }

  boolean removeAt( int pIndex )
  {
    try
    {
      getVector().remove( pIndex );

      return true;
    }
    catch ( Exception err_inst )
    {
      System.out.println( "Fehler List.removeAt( pIndex ) -> " + err_inst.getMessage() );
    }

    return false;
  }

  /**
   * <pre>
   * Vertauscht die Objekte an den Indexangaben, sofern die Indexpositionen existieren. 
   * </pre>
   * 
   * @param pIndexA der erste Index
   * @param pIndexB der zweite Index
   * @return TRUE sofern die Vertauschung durchgefuehrt werden konnte, sonst FALSE 
   */
  boolean swap( int pIndexA, int pIndexB )
  {
    if ( m_vector != null )
    {
      if ( ( m_vector.size() >= pIndexA ) && ( m_vector.size() >= pIndexB ) )
      {
        Object temp_objekt = m_vector.get( pIndexA );

        m_vector.set( pIndexA, m_vector.get( pIndexB ) );

        m_vector.set( pIndexB, temp_objekt );

        return true;
      }
    }

    return false;
  }

  /**
   * Ruft die Funktion "clear()" beim Vektor auf und setzt 
   * anschliessend die Vektor-Variable auf null.
   */
  public void clear()
  {
    if ( m_vector != null )
    {
      try
      {
        m_vector.clear();
      }
      catch ( Exception err_inst )
      {
        /*
         * Java-Doc fuer die Clear-Funktion
         * 
         * "Removes all of the elements from this List. The List will be empty after this call returns (unless it throws an exception)."
         * 
         * Da eine Exception vorkommen kann, wird ein Try/Catch-Block notwendig
         */
      }
    }

    m_vector = null;
  }

  /**
   * Ruft bei jedem gespeicherten Objekt die toString-Funktion auf 
   * 
   * @return eine Stringrepraesentation des Vektors
   */
  public String toString()
  {
    if ( m_vector == null )
    {
      return "keine Objekte gespeichert";
    }

    String ergebnis = "\nAnzahl Elemente : " + m_vector.size();

    int akt_index = 0;

    Object vektor_objekt = this.getIndex( akt_index );

    while ( vektor_objekt != null )
    {
      ergebnis += "\n" + vektor_objekt.toString();

      akt_index++;
      vektor_objekt = this.getIndex( akt_index );
    }

    vektor_objekt = null;

    return ergebnis;
  }
}
