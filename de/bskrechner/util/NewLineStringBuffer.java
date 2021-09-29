package de.bskrechner.util;

public class NewLineStringBuffer
{
  private final String  LEERSTRING      = "";

  private StringBuffer  m_string_buffer = null;

  private static String m_str_new_line  = "\n";

  public StringBuffer getStringBuffer()
  {
    if ( m_string_buffer == null )
    {
      m_string_buffer = new StringBuffer();
    }

    return m_string_buffer;
  }

  public void setNewLineZeichen( String pNewLineZeichen )
  {
    if ( pNewLineZeichen != null )
    {
      m_str_new_line = pNewLineZeichen;
    }
    else
    {
      m_str_new_line = "";
    }
  }

  public void appendX( String pString1 )
  {
    getStringBuffer().append( pString1 );
    getStringBuffer().append( m_str_new_line );
  }

  public void appendX( String pString1, String pString2 )
  {
    getStringBuffer().append( pString1 );
    getStringBuffer().append( pString2 );

    getStringBuffer().append( m_str_new_line );
  }

  public void appendX( String pString1, String pString2, String pString3 )
  {
    getStringBuffer().append( pString1 );
    getStringBuffer().append( pString2 );
    getStringBuffer().append( pString3 );

    getStringBuffer().append( m_str_new_line );
  }

  public void append( String pString )
  {
    if ( pString == null ) return;

    getStringBuffer().append( pString );
    getStringBuffer().append( m_str_new_line );
  }

  public void append( int pZahl )
  {
    getStringBuffer().append( pZahl );
    getStringBuffer().append( m_str_new_line );
  }

  public void append( StringBuffer pStringBuffer )
  {
    if ( pStringBuffer != null )
    {
      getStringBuffer().append( pStringBuffer.toString() );
    }
  }

  public void append( NewLineStringBuffer pStringBuffer )
  {
    if ( pStringBuffer != null )
    {
      getStringBuffer().append( pStringBuffer.toString() );
    }
  }

  public void append( String pString1, String pString2 )
  {
    getStringBuffer().append( pString1 );
    getStringBuffer().append( pString2 );

    getStringBuffer().append( m_str_new_line );
  }

  public void append( String pString1, String pString2, String pString3 )
  {
    getStringBuffer().append( pString1 );
    getStringBuffer().append( pString2 );
    getStringBuffer().append( pString3 );

    getStringBuffer().append( m_str_new_line );
  }

  public void append( String pString1, String pString2, String pString3, String pString4 )
  {
    getStringBuffer().append( pString1 );
    getStringBuffer().append( pString2 );
    getStringBuffer().append( pString3 );
    getStringBuffer().append( pString4 );

    getStringBuffer().append( m_str_new_line );
  }

  public void append( String pString1, String pString2, String pString3, String pString4, String pString5 )
  {
    getStringBuffer().append( pString1 );
    getStringBuffer().append( pString2 );
    getStringBuffer().append( pString3 );
    getStringBuffer().append( pString4 );
    getStringBuffer().append( pString5 );

    getStringBuffer().append( m_str_new_line );
  }

  public void appendI( String pString )
  {
    getStringBuffer().append( pString );
  }

  public void println( String pString )
  {
    if ( pString == null ) return;

    getStringBuffer().append( pString );
    getStringBuffer().append( m_str_new_line );
  }

  public void print( String pString )
  {
    if ( pString == null ) return;

    getStringBuffer().append( pString );
  }

  public String toString()
  {
    if ( m_string_buffer == null ) return LEERSTRING;
    return m_string_buffer.toString();
  }

  public void clear()
  {
    if ( m_string_buffer != null )
    {
      m_string_buffer.setLength( 0 );
    }

    m_string_buffer = null;
  }

  public static String getGeruest( String pEingabeString )
  {
    return FkString.replace( "\n\n" + pEingabeString + "\n\n", "\n", "\");\npBuffer.append(\"" );
  }
}
