package de.bskrechner.test;

import java.util.Properties;

public class TestTarif2
{
  public static Properties getProperties()
  {
    Properties properties_tarif_2 = new Properties();

    double min_bwz = 4.8;

    properties_tarif_2.setProperty( "mindest_bewertungszahl", "" + min_bwz );

    properties_tarif_2.setProperty( "bewertungszahl_faktor_1", "1.0" );
    properties_tarif_2.setProperty( "bonus_knz_bei_darlehensannahme", "true" );
    properties_tarif_2.setProperty( "bonus_knz_bei_darlverzicht", "true" );
    properties_tarif_2.setProperty( "bonus_max_laufzeit_jahre", "0" );
    properties_tarif_2.setProperty( "bonus_min_bwz", "4.8" );
    properties_tarif_2.setProperty( "bonus_min_laufzeit_monate", "0" );
    properties_tarif_2.setProperty( "bsd_proz_darlehensgebuehr", "2.0" );
    properties_tarif_2.setProperty( "bsd_proz_disagio", "0.0" );
    properties_tarif_2.setProperty( "bsd_zinssatz", "2.0" );

    properties_tarif_2.setProperty( "bwz_staffel_1_bwz_von", "" + min_bwz );
    properties_tarif_2.setProperty( "bwz_staffel_1_bwz_bis", "" + ( min_bwz + 1.99 ) );
    properties_tarif_2.setProperty( "bwz_staffel_1_ztb", "6.4" );

    properties_tarif_2.setProperty( "bwz_staffel_2_bwz_von", "" + min_bwz + 2 );
    properties_tarif_2.setProperty( "bwz_staffel_2_bwz_bis", "" + ( min_bwz + 2.99 ) );

    properties_tarif_2.setProperty( "bwz_staffel_3_bwz_von", "" + min_bwz + 3 );
    properties_tarif_2.setProperty( "bwz_staffel_3_bwz_bis", "" + ( min_bwz + 3.99 ) );

    properties_tarif_2.setProperty( "bwz_staffel_4_bwz_von", "" + min_bwz + 4 );
    properties_tarif_2.setProperty( "bwz_staffel_4_bwz_bis", "" + ( min_bwz + 4.99 ) );

    properties_tarif_2.setProperty( "bwz_staffel_5_bwz_von", "" + min_bwz + 5 );
    properties_tarif_2.setProperty( "bwz_staffel_5_bwz_bis", "" + ( min_bwz + 5.99 ) );

    properties_tarif_2.setProperty( "bwz_staffel_6_bwz_von", "" + min_bwz + 6 );
    properties_tarif_2.setProperty( "bwz_staffel_6_bwz_bis", "" + ( min_bwz + 6.99 ) );

    properties_tarif_2.setProperty( "bwz_staffel_2_ztb", "6.0" );
    properties_tarif_2.setProperty( "bwz_staffel_3_ztb", "5.5" );
    properties_tarif_2.setProperty( "bwz_staffel_4_ztb", "5.0" );
    properties_tarif_2.setProperty( "bwz_staffel_5_ztb", "4.5" );
    properties_tarif_2.setProperty( "bwz_staffel_6_ztb", "4.0" );
    properties_tarif_2.setProperty( "bwz_staffel_6_bwz_bis", "1000.0" );
    properties_tarif_2.setProperty( "bwz_staffel_vektor_anzahl", "6" );
    properties_tarif_2.setProperty( "darlehensanspruch_art", "FEST" );
    properties_tarif_2.setProperty( "darlehensanspruch_prozent", "60.0" );
    properties_tarif_2.setProperty( "kto_gebuehr_sparen_knz_ein", "true" );
    properties_tarif_2.setProperty( "kto_gebuehr_sparen_pro_jahr", "18.0" );
    properties_tarif_2.setProperty( "kto_gebuehr_tilgen_knz_ein", "true" );
    properties_tarif_2.setProperty( "kto_gebuehr_tilgen_pro_jahr", "19.0" );
    properties_tarif_2.setProperty( "mindest_bausparsumme", "5000.0" );

    properties_tarif_2.setProperty( "mindest_sparzeit_in_monaten", "24" );
    properties_tarif_2.setProperty( "promille_abschlussgebuehr", "1.8" );
    properties_tarif_2.setProperty( "prozent_bonuszins", "0.0" );
    properties_tarif_2.setProperty( "prozent_guthabenzins", "1.0" );
    properties_tarif_2.setProperty( "prozent_mindest_sparguthaben", "40.0" );
    properties_tarif_2.setProperty( "regel_sparbeitrag", "4.0" );
    properties_tarif_2.setProperty( "wartezeit_in_monaten", "4" );

    return properties_tarif_2;
  }
}
