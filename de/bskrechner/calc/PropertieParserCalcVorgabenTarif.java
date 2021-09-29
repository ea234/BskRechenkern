package de.bskrechner.calc;

import java.util.Properties;

import de.bskrechner.test.TestTarif2;
import de.bskrechner.util.FkProperties;
import de.bskrechner.util.FkZahl;

public class PropertieParserCalcVorgabenTarif
{
  public static CalcVorgabenTarif parseCalcVorgabenTarif()
  {
    Properties temp_properties = TestTarif2.getProperties();

    return getCalcVorgabenTarif( temp_properties );
  }

  /**
   * <pre>
   * Aus den Properties wird eine Instanz der Java-Klasse "CalcVorgabenTarif" erstellt.
   * </pre>
   *
   * @param  pProperties Propertie-Instanz mit den Werten
   * @return  Eine Instanz von "CalcVorgabenTarif" mit den Werten aus den Properties.
   */
  private static CalcVorgabenTarif getCalcVorgabenTarif( Properties pProperties )
  {
    /*
     * Die Werte werden aus der Propertie-Instanz in lokale Variablen uebertragen
     */
    String bewertungszahl_faktor_1 = FkProperties.getString( pProperties, "bewertungszahl_faktor_1", null, -1 );
    String bonus_knz_bei_darlehensannahme = FkProperties.getString( pProperties, "bonus_knz_bei_darlehensannahme", null, -1 );
    String bonus_knz_bei_darlverzicht = FkProperties.getString( pProperties, "bonus_knz_bei_darlverzicht", null, -1 );
    String bonus_max_laufzeit_jahre = FkProperties.getString( pProperties, "bonus_max_laufzeit_jahre", null, -1 );
    String bonus_min_bwz = FkProperties.getString( pProperties, "bonus_min_bwz", null, -1 );
    String bonus_min_laufzeit_monate = FkProperties.getString( pProperties, "bonus_min_laufzeit_monate", null, -1 );
    String darlehensanspruch_art = FkProperties.getString( pProperties, "darlehensanspruch_art", null, -1 );
    String darlehensanspruch_prozent = FkProperties.getString( pProperties, "darlehensanspruch_prozent", null, -1 );
    String kto_gebuehr_sparen_pro_jahr = FkProperties.getString( pProperties, "kto_gebuehr_sparen_pro_jahr", null, -1 );
    String kto_gebuehr_tilgen_pro_jahr = FkProperties.getString( pProperties, "kto_gebuehr_tilgen_pro_jahr", null, -1 );
    String kto_gebuehr_sparen_knz_ein = FkProperties.getString( pProperties, "kto_gebuehr_sparen_knz_ein", null, -1 );
    String kto_gebuehr_tilgen_knz_ein = FkProperties.getString( pProperties, "kto_gebuehr_tilgen_knz_ein", null, -1 );
    String mindest_bausparsumme = FkProperties.getString( pProperties, "mindest_bausparsumme", null, -1 );
    String mindest_bewertungszahl = FkProperties.getString( pProperties, "mindest_bewertungszahl", null, -1 );
    String mindest_sparzeit_in_monaten = FkProperties.getString( pProperties, "mindest_sparzeit_in_monaten", null, -1 );
    String promille_abschlussgebuehr = FkProperties.getString( pProperties, "promille_abschlussgebuehr", null, -1 );
    String prozent_guthabenzins = FkProperties.getString( pProperties, "prozent_guthabenzins", null, -1 );
    String prozent_bonuszins = FkProperties.getString( pProperties, "prozent_bonuszins", null, -1 );
    String prozent_mindest_sparguthaben = FkProperties.getString( pProperties, "prozent_mindest_sparguthaben", null, -1 );
    String wartezeit_in_monaten = FkProperties.getString( pProperties, "wartezeit_in_monaten", null, -1 );
    String regel_sparbeitrag = FkProperties.getString( pProperties, "regel_sparbeitrag", null, -1 );
    String bsd_zinssatz = FkProperties.getString( pProperties, "bsd_zinssatz", null, -1 );
    String bsd_proz_darlehensgebuehr = FkProperties.getString( pProperties, "bsd_proz_darlehensgebuehr", null, -1 );
    String bsd_proz_disagio = FkProperties.getString( pProperties, "bsd_proz_disagio", null, -1 );

    /*
     * Erstellung einer neuen Instanz der Klasse "CalcVorgabenTarif" 
     */
    CalcVorgabenTarif calc_vorgaben_tarif = new CalcVorgabenTarif();

    /*
     * Die gelesenen Werte werden dort reingeschrieben
     */
    calc_vorgaben_tarif.setBewertungszahlFaktor1( bewertungszahl_faktor_1 );
    calc_vorgaben_tarif.setBonusKnzBeiDarlehensannahme( bonus_knz_bei_darlehensannahme );
    calc_vorgaben_tarif.setBonusKnzBeiDarlverzicht( bonus_knz_bei_darlverzicht );
    calc_vorgaben_tarif.setBonusMaxLaufzeitJahre( bonus_max_laufzeit_jahre );
    calc_vorgaben_tarif.setBonusMinBwz( bonus_min_bwz );
    calc_vorgaben_tarif.setBonusMinLaufzeitMonate( bonus_min_laufzeit_monate );
    calc_vorgaben_tarif.setDarlehensanspruchArt( darlehensanspruch_art );
    calc_vorgaben_tarif.setDarlehensanspruchProzent( darlehensanspruch_prozent );
    calc_vorgaben_tarif.setKtoGebuehrSparenProJahr( kto_gebuehr_sparen_pro_jahr );
    calc_vorgaben_tarif.setKtoGebuehrTilgenProJahr( kto_gebuehr_tilgen_pro_jahr );
    calc_vorgaben_tarif.setKtoGebuehrSparenKnzEin( kto_gebuehr_sparen_knz_ein );
    calc_vorgaben_tarif.setKtoGebuehrTilgenKnzEin( kto_gebuehr_tilgen_knz_ein );
    calc_vorgaben_tarif.setMindestBausparsumme( mindest_bausparsumme );
    calc_vorgaben_tarif.setMindestBewertungszahl( mindest_bewertungszahl );
    calc_vorgaben_tarif.setMindestSparzeitInMonaten( mindest_sparzeit_in_monaten );
    calc_vorgaben_tarif.setPromilleAbschlussgebuehr( promille_abschlussgebuehr );
    calc_vorgaben_tarif.setProzentGuthabenzins( prozent_guthabenzins );
    calc_vorgaben_tarif.setProzentBonuszins( prozent_bonuszins );
    calc_vorgaben_tarif.setProzentMindestSparguthaben( prozent_mindest_sparguthaben );
    calc_vorgaben_tarif.setWartezeitInMonaten( wartezeit_in_monaten );
    calc_vorgaben_tarif.setRegelSparbeitrag( regel_sparbeitrag );
    calc_vorgaben_tarif.setBsdZinssatz( bsd_zinssatz );
    calc_vorgaben_tarif.setBsdProzDarlehensgebuehr( bsd_proz_darlehensgebuehr );
    calc_vorgaben_tarif.setBsdProzDisagio( bsd_proz_disagio );

    parseVektorBwzStaffel( pProperties, calc_vorgaben_tarif );

    /*
     * Der Aufrufer bekommt die erstellte Instanz zurueckgeliefert
     */
    return calc_vorgaben_tarif;
  }

  /**
   * <pre>
   * Propertie-Instanz-Initialisierung.
   * 
   * 
   * public static void main( String[] args )
   * {
   *   try
   *   {
   *     Properties temp_properties = new Properties();
   * 
   *     new BwzStaffelVectorPropertieParser.readVectorFromPropertiesBwzStaffel( temp_properties , inst_bwz_staffel_vector );
   * 
   *     DrLogger.wl( FkProperties.getStrPropertiesKeyValue( temp_properties, true, 70 ) );
   * 
   *     temp_properties.clear();
   * 
   *     temp_properties = null;
   *   }
   *   catch ( Exception err_inst )
   *   {
   *     DrLogger.wl( \"Fehler:\", err_inst );
   *   }
   * 
   *   System.exit( 0 );
   * }
   *  
   * </pre>
   * 
   * @param  pProperties die Propertie-Instanz, mit den zu lesenden Daten
   * @param  pBwzStaffelVector Vektor in welchem die gelesenen Daten gespeichert werden
   */
  private static boolean parseVektorBwzStaffel( Properties pProperties, CalcVorgabenTarif pCalcVorgabenTarif )
  {
    CalcBwzStaffel inst_bwz_staffel = null;

    boolean knz_ergebnis_ok = false;

    /*
     * Kennzeichen, ob die gelesenen Daten in den Vektor aufgenommen werden sollen.
     */
    boolean knz_aufnahme_ok = true;

    /*
     * Praefix fuer die Propertieschluessel.
     * Der Praefix ist immer gleich.
     */
    String praefix_bwz_staffel = "bwz_staffel_";

    /*
     * Suffixe fuer die Propertieschluessel.
     */
    String suffix_bwz_von = "_bwz_von";
    String suffix_bwz_bis = "_bwz_bis";
    String suffix_ztb = "_ztb";

    String suffix_vektor_anzahl = "vektor_anzahl";

    /*
     * Stringvariablen fuer die zu lesenden Properties.
     */
    String prop_key_bwz_von = "";
    String prop_key_bwz_bis = "";
    String prop_key_ztb = "";

    /*
     * Stringvariablen fuer die Propertiewerte.
     */
    String prop_val_bwz_von = "";
    String prop_val_bwz_bis = "";
    String prop_val_ztb = "";

    /*
     * Speichert die Index-Nummer der Propertie-Schluessel.
     */
    int index_vektor_aktuell = 0;

    /*
     * Die Anzahl der gespeicherten Vektorelemente lesen.
     */
    int vector_anzahl = FkZahl.getInteger( pProperties.getProperty( praefix_bwz_staffel + suffix_vektor_anzahl ), 0 );

    try
    {
      /*
       * Speichervariable fuer die maximal zu lesenden Vektorelemente. 
       */
      int max_index = 32100;

      int akt_index = 0;

      /*
       * Die While-Schleife laueft solange, 
       * ... der Propertie-Vektor-Indexzaehler kleiner als die Anzahl der gespeicherten Elemente ist.
       * ... der Index-Zaehler noch kleiner als die Maximalanzahl der zu exportierenden Werte ist.
       */
      while ( ( akt_index < vector_anzahl ) && ( akt_index < max_index ) )
      {
        /*
         * Index-Zahl fuer die Propertieschluessel berechnen
         */
        index_vektor_aktuell = index_vektor_aktuell + 1;

        /*
         * Die aktuellen Propertieschluessel fuer den aktuellen Vektor-Index erstellen
         * 
         * Nr 1 =  praefix_bwz_staffel + <index> + suffix_bwz_von    = bwz_staffel_1-n_bwz_von
         * Nr 2 =  praefix_bwz_staffel + <index> + suffix_bwz_bis    = bwz_staffel_1-n_bwz_bis
         * Nr 3 =  praefix_bwz_staffel + <index> + suffix_ztb        = bwz_staffel_1-n_ztb
         */
        prop_key_bwz_von = praefix_bwz_staffel + index_vektor_aktuell + suffix_bwz_von;
        prop_key_bwz_bis = praefix_bwz_staffel + index_vektor_aktuell + suffix_bwz_bis;
        prop_key_ztb = praefix_bwz_staffel + index_vektor_aktuell + suffix_ztb;

        /*
         * Daten aus der Propertie-Instanz in die lokalen Variablen einlesen
         */
        prop_val_bwz_von = pProperties.getProperty( prop_key_bwz_von, "" );
        prop_val_bwz_bis = pProperties.getProperty( prop_key_bwz_bis, "" );
        prop_val_ztb = pProperties.getProperty( prop_key_ztb, "" );

        /*
         * Pruefung, ob die Daten dem Vektor hinzugefuegt werden sollen.
         */
        if ( knz_aufnahme_ok )
        {
          /*
           * Speicherinstanz der Klasse "BwzStaffel" im Vektor anlegen
           */
          inst_bwz_staffel = new CalcBwzStaffel();

          inst_bwz_staffel.setBwzVon( prop_val_bwz_von );
          inst_bwz_staffel.setBwzBis( prop_val_bwz_bis );
          inst_bwz_staffel.setZtb( prop_val_ztb );

          pCalcVorgabenTarif.addBwzStaffel( inst_bwz_staffel );
        }

        /*
         * Indexzaehler erhoehen
         */
        akt_index = akt_index + 1;
      }

      /*
       * Nach der While-Schleife das Funktionergebnis auf TRUE stellen.
       */
      knz_ergebnis_ok = true;
    }
    catch ( Exception err_inst )
    {
      // Keine Aktion
    }

    inst_bwz_staffel = null;

    return knz_ergebnis_ok;
  }
}
