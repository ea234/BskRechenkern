package de.bskrechner.calc;

import java.util.Properties;

import de.bskrechner.util.FkProperties;
import de.bskrechner.util.FkString;

public class PropertieParserCalcVorgabenBsv
{
  public static CalcVorgabenBsv parseCalcVorgabenBsv( Properties pRequest )
  {
    String rk_input_vorgabe_vertragsart = FkProperties.getString( pRequest, "rk_input_vorgabe_vertragsart", null, 30 );

    String rk_input_datum_vertragsbeginn = FkProperties.getString( pRequest, "rk_input_datum_vertragsbeginn", null, 20 );
    String rk_input_knz_sparstopp = FkProperties.getString( pRequest, "rk_input_knz_sparstopp", null, 30 );
    String rk_input_ag_art_begleichung = FkProperties.getString( pRequest, "rk_input_ag_art_begleichung", null, 20 );

    String rk_input_bestehendes_guthaben = FkProperties.getString( pRequest, "rk_input_bestehendes_guthaben", null, 20 );
    String rk_input_bestehende_bwz = FkProperties.getString( pRequest, "rk_input_bestehende_bwz", null, 20 );
    String rk_input_bestehende_bss = FkProperties.getString( pRequest, "rk_input_bestehende_bss", null, 20 );
    String rk_input_wop_speicher = FkProperties.getString( pRequest, "rk_input_wop_speicher", null, 20 );
    String rk_input_wop_datum_ablauf_bindefrist = FkProperties.getString( pRequest, "rk_input_wop_datum_ablauf_bindefrist", null, 20 );
    String rk_input_datum_berechnungsbeginn = FkProperties.getString( pRequest, "rk_input_datum_berechnungsbeginn", null, 20 );
    String rk_input_laufzeit_in_monaten = FkProperties.getString( pRequest, "rk_input_laufzeit_in_monaten", null, 20 );
    String rk_input_knz_ansp_berechnen = FkProperties.getString( pRequest, "rk_input_knz_ansp_berechnen", null, 20 );
    String rk_input_knz_darlehensverzicht = FkProperties.getString( pRequest, "rk_input_knz_darlehensverzicht", null, 20 );
    String rk_input_knz_kfg_beruecksichtigen = FkProperties.getString( pRequest, "rk_input_knz_kfg_beruecksichtigen", null, 20 );
    String rk_input_ag_knz_beruecksichtigen = FkProperties.getString( pRequest, "rk_input_ag_knz_beruecksichtigen", null, 20 );
    String rk_input_ag_betrag_vorgabe = FkProperties.getString( pRequest, "rk_input_ag_betrag_vorgabe", null, 20 );
    String rk_input_knz_berechnungsziel = FkProperties.getString( pRequest, "rk_input_knz_berechnungsziel", null, 20 );
    String rk_input_betrag_bausparsumme = FkProperties.getString( pRequest, "rk_input_betrag_bausparsumme", null, 20 );
    String rk_input_proz_mehrzuteilung = FkProperties.getString( pRequest, "rk_input_proz_mehrzuteilung", null, 20 );
    String rk_input_knz_wop_berechnen = FkProperties.getString( pRequest, "rk_input_knz_wop_berechnen", null, 20 );
    String rk_input_knz_wop_junge_leute_r = FkProperties.getString( pRequest, "rk_input_knz_wop_junge_leute_r", null, 20 );
    String rk_input_knz_wop_verheiratet = FkProperties.getString( pRequest, "rk_input_knz_wop_verheiratet", null, 20 );
    String rk_input_vorgabe_ztb_betrag = FkProperties.getString( pRequest, "rk_input_vorgabe_ztb_betrag", null, 20 );
    String rk_input_vorgabe_ztb_art = FkProperties.getString( pRequest, "rk_input_vorgabe_ztb_art", null, 20 );
    String rk_input_vorgabe_ztb_optimierung = FkProperties.getString( pRequest, "rk_input_vorgabe_ztb_optimierung", null, 20 );

    String rk_input_zahlung_sparbeitrag_id = FkProperties.getString( pRequest, "rk_input_zahlung_sparbeitrag_id", null, 20 );
    String rk_input_zahlung_sparbeitrag_knz_aufnahme = FkProperties.getString( pRequest, "rk_input_zahlung_sparbeitrag_knz_aufnahme", null, 20 );
    String rk_input_zahlung_sparbeitrag_bezeichnung = FkProperties.getString( pRequest, "rk_input_zahlung_sparbeitrag_bezeichnung", null, 20 );
    String rk_input_zahlung_sparbeitrag_betrag = FkProperties.getString( pRequest, "rk_input_zahlung_sparbeitrag_betrag", null, 20 );
    String rk_input_zahlung_sparbeitrag_rhythmus = FkProperties.getString( pRequest, "rk_input_zahlung_sparbeitrag_rhythmus", null, 20 );
    String rk_input_zahlung_sparbeitrag_datum_von = FkProperties.getString( pRequest, "rk_input_zahlung_sparbeitrag_datum_von", null, 20 );
    String rk_input_zahlung_sparbeitrag_datum_bis = FkProperties.getString( pRequest, "rk_input_zahlung_sparbeitrag_datum_bis", null, 20 );
    String rk_input_zahlung_sparbeitrag_knz_tag = FkProperties.getString( pRequest, "rk_input_zahlung_sparbeitrag_knz_tag", null, 20 );

    String rk_input_zahlung_vl_as_id = FkProperties.getString( pRequest, "rk_input_zahlung_vl_as_id", null, 20 );
    String rk_input_zahlung_vl_as_knz_aufnahme = FkProperties.getString( pRequest, "rk_input_zahlung_vl_as_knz_aufnahme", null, 20 );
    String rk_input_zahlung_vl_as_bezeichnung = FkProperties.getString( pRequest, "rk_input_zahlung_vl_as_bezeichnung", null, 20 );
    String rk_input_zahlung_vl_as_betrag = FkProperties.getString( pRequest, "rk_input_zahlung_vl_as_betrag", null, 20 );
    String rk_input_zahlung_vl_as_rhythmus = FkProperties.getString( pRequest, "rk_input_zahlung_vl_as_rhythmus", null, 20 );
    String rk_input_zahlung_vl_as_datum_von = FkProperties.getString( pRequest, "rk_input_zahlung_vl_as_datum_von", null, 20 );
    String rk_input_zahlung_vl_as_datum_bis = FkProperties.getString( pRequest, "rk_input_zahlung_vl_as_datum_bis", null, 20 );
    String rk_input_zahlung_vl_as_knz_tag = FkProperties.getString( pRequest, "rk_input_zahlung_vl_as_knz_tag", null, 20 );

    String rk_input_zahlung_vl_ep_id = FkProperties.getString( pRequest, "rk_input_zahlung_vl_ep_id", null, 20 );
    String rk_input_zahlung_vl_ep_knz_aufnahme = FkProperties.getString( pRequest, "rk_input_zahlung_vl_ep_knz_aufnahme", null, 20 );
    String rk_input_zahlung_vl_ep_bezeichnung = FkProperties.getString( pRequest, "rk_input_zahlung_vl_ep_bezeichnung", null, 20 );
    String rk_input_zahlung_vl_ep_betrag = FkProperties.getString( pRequest, "rk_input_zahlung_vl_ep_betrag", null, 20 );
    String rk_input_zahlung_vl_ep_rhythmus = FkProperties.getString( pRequest, "rk_input_zahlung_vl_ep_rhythmus", null, 20 );
    String rk_input_zahlung_vl_ep_datum_von = FkProperties.getString( pRequest, "rk_input_zahlung_vl_ep_datum_von", null, 20 );
    String rk_input_zahlung_vl_ep_datum_bis = FkProperties.getString( pRequest, "rk_input_zahlung_vl_ep_datum_bis", null, 20 );
    String rk_input_zahlung_vl_ep_knz_tag = FkProperties.getString( pRequest, "rk_input_zahlung_vl_ep_knz_tag", null, 20 );

    String rk_input_zahlung_sz_1_id = FkProperties.getString( pRequest, "rk_input_zahlung_sz_1_id", null, 20 );
    String rk_input_zahlung_sz_1_knz_aufnahme = FkProperties.getString( pRequest, "rk_input_zahlung_sz_1_knz_aufnahme", null, 20 );
    String rk_input_zahlung_sz_1_bezeichnung = FkProperties.getString( pRequest, "rk_input_zahlung_sz_1_bezeichnung", null, 20 );
    String rk_input_zahlung_sz_1_betrag = FkProperties.getString( pRequest, "rk_input_zahlung_sz_1_betrag", null, 20 );
    String rk_input_zahlung_sz_1_rhythmus = FkProperties.getString( pRequest, "rk_input_zahlung_sz_1_rhythmus", null, 20 );
    String rk_input_zahlung_sz_1_datum_von = FkProperties.getString( pRequest, "rk_input_zahlung_sz_1_datum_von", null, 20 );
    String rk_input_zahlung_sz_1_datum_bis = FkProperties.getString( pRequest, "rk_input_zahlung_sz_1_datum_bis", null, 20 );
    String rk_input_zahlung_sz_1_knz_tag = FkProperties.getString( pRequest, "rk_input_zahlung_sz_1_knz_tag", null, 20 );

    String rk_input_zahlung_sz_2_id = FkProperties.getString( pRequest, "rk_input_zahlung_sz_2_id", null, 20 );
    String rk_input_zahlung_sz_2_knz_aufnahme = FkProperties.getString( pRequest, "rk_input_zahlung_sz_2_knz_aufnahme", null, 20 );
    String rk_input_zahlung_sz_2_bezeichnung = FkProperties.getString( pRequest, "rk_input_zahlung_sz_2_bezeichnung", null, 20 );
    String rk_input_zahlung_sz_2_betrag = FkProperties.getString( pRequest, "rk_input_zahlung_sz_2_betrag", null, 20 );
    String rk_input_zahlung_sz_2_rhythmus = FkProperties.getString( pRequest, "rk_input_zahlung_sz_2_rhythmus", null, 20 );
    String rk_input_zahlung_sz_2_datum_von = FkProperties.getString( pRequest, "rk_input_zahlung_sz_2_datum_von", null, 20 );
    String rk_input_zahlung_sz_2_datum_bis = FkProperties.getString( pRequest, "rk_input_zahlung_sz_2_datum_bis", null, 20 );
    String rk_input_zahlung_sz_2_knz_tag = FkProperties.getString( pRequest, "rk_input_zahlung_sz_2_knz_tag", null, 20 );

    String rk_input_zahlung_st_1_datum_von = FkProperties.getString( pRequest, "rk_input_zahlung_st_1_datum_von", null, 20 );
    String rk_input_zahlung_st_1_datum_bis = FkProperties.getString( pRequest, "rk_input_zahlung_st_1_datum_bis", null, 20 );
    String rk_input_zahlung_st_1_betrag = FkProperties.getString( pRequest, "rk_input_zahlung_st_1_betrag", null, 20 );
    String rk_input_zahlung_st_1_rhythmus = FkProperties.getString( pRequest, "rk_input_zahlung_st_1_rhythmus", null, 20 );
    String rk_input_zahlung_st_1_knz_tag = FkProperties.getString( pRequest, "rk_input_zahlung_st_1_knz_tag", null, 20 );
    String rk_input_zahlung_st_1_knz_aufnahme = FkProperties.getString( pRequest, "rk_input_zahlung_st_1_knz_aufnahme", null, 20 );

    String rk_input_zahlung_st_2_datum_von = FkProperties.getString( pRequest, "rk_input_zahlung_st_2_datum_von", null, 20 );
    String rk_input_zahlung_st_2_datum_bis = FkProperties.getString( pRequest, "rk_input_zahlung_st_2_datum_bis", null, 20 );
    String rk_input_zahlung_st_2_betrag = FkProperties.getString( pRequest, "rk_input_zahlung_st_2_betrag", null, 20 );
    String rk_input_zahlung_st_2_rhythmus = FkProperties.getString( pRequest, "rk_input_zahlung_st_2_rhythmus", null, 20 );
    String rk_input_zahlung_st_2_knz_tag = FkProperties.getString( pRequest, "rk_input_zahlung_st_2_knz_tag", null, 20 );
    String rk_input_zahlung_st_2_knz_aufnahme = FkProperties.getString( pRequest, "rk_input_zahlung_st_2_knz_aufnahme", null, 20 );

    String debug_knz_akt_element = FkProperties.getString( pRequest, "debug_knz_akt_element", "false", 10 );
    String debug_knz_ansp_berechnung = FkProperties.getString( pRequest, "debug_knz_ansp_berechnung", "false", 10 );
    String debug_knz_stichtage = FkProperties.getString( pRequest, "debug_knz_stichtage", "false", 10 );
    String debug_knz_wop_berechnung = FkProperties.getString( pRequest, "debug_knz_wop_berechnung", "false", 10 );
    String debug_knz_zins_berechnung_bsd = FkProperties.getString( pRequest, "debug_knz_zins_berechnung_bsd", "false", 10 );
    String debug_knz_zins_berechnung_spar = FkProperties.getString( pRequest, "debug_knz_zins_berechnung_spar", "false", 10 );

    String calc_vorgaben_knz_kfg_sparphase_beruecksichtigen = FkProperties.getString( pRequest, "knz_kfg_sparphase_beruecksichtigen", "true", 10 );
    String calc_vorgaben_knz_kfg_darlehensphase_beruecksichtigen = FkProperties.getString( pRequest, "knz_kfg_darlehensphase_beruecksichtigen", "true", 10 );

    /*
     *
     * Trennung von Datenherkunft zu Datenverwendung
     * 
     * 
     */

    CalcVorgabenBsv inst_calc_bsv_eingabe = new CalcVorgabenBsv();

    if ( rk_input_vorgabe_vertragsart.equalsIgnoreCase( "BESTANDSVERTRAG" ) )
    {
      inst_calc_bsv_eingabe.setRkInputVorgabeVertragsartBestandsvertrag();
    }
    else
    {
      inst_calc_bsv_eingabe.setRkInputVorgabeVertragsartNeuvertrag();
    }

    inst_calc_bsv_eingabe.setRkInputDatumBerechnungsbeginn( rk_input_datum_berechnungsbeginn );

    inst_calc_bsv_eingabe.setRkInputDatumVertragsbeginn( rk_input_datum_vertragsbeginn );

    inst_calc_bsv_eingabe.setRkInputKnzBerechnungsziel( rk_input_knz_berechnungsziel );

    inst_calc_bsv_eingabe.setRkInputBetragBausparsumme( rk_input_betrag_bausparsumme );

    inst_calc_bsv_eingabe.setRkInputLaufzeitInMonaten( rk_input_laufzeit_in_monaten );

    inst_calc_bsv_eingabe.setRkInputKnzSparstopp( rk_input_knz_sparstopp );

    inst_calc_bsv_eingabe.setRkInputAgArtBegleichung( rk_input_ag_art_begleichung );

    inst_calc_bsv_eingabe.setRkInputBestehendesGuthaben( rk_input_bestehendes_guthaben );
    inst_calc_bsv_eingabe.setRkInputBestehendeBwz( rk_input_bestehende_bwz );
    // -- inst_calc_bsv_eingabe.setRkInputBestehendeBss(  rk_input_bestehende_bss );
    inst_calc_bsv_eingabe.setRkInputWopSpeicher( rk_input_wop_speicher );
    inst_calc_bsv_eingabe.setRkInputWopDatumAblaufBindefrist( rk_input_wop_datum_ablauf_bindefrist );

    inst_calc_bsv_eingabe.setRkInputKnzAnspBerechnen( rk_input_knz_ansp_berechnen );
    inst_calc_bsv_eingabe.setRkInputKnzDarlehensverzicht( rk_input_knz_darlehensverzicht );
    //inst_calc_bsv_eingabe.setRkInputKnzKfgBeruecksichtigen( rk_input_knz_kfg_beruecksichtigen );

    inst_calc_bsv_eingabe.setRkInputDatumVertragsbeginn( rk_input_datum_vertragsbeginn );
    inst_calc_bsv_eingabe.setRkInputBestehendesGuthaben( rk_input_bestehendes_guthaben );
    inst_calc_bsv_eingabe.setRkInputBestehendeBwz( rk_input_bestehende_bwz );
    inst_calc_bsv_eingabe.setRkInputBestehendeBss( rk_input_bestehende_bss );
    inst_calc_bsv_eingabe.setRkInputWopSpeicher( rk_input_wop_speicher );
    inst_calc_bsv_eingabe.setRkInputWopDatumAblaufBindefrist( rk_input_wop_datum_ablauf_bindefrist );
    inst_calc_bsv_eingabe.setRkInputDatumBerechnungsbeginn( rk_input_datum_berechnungsbeginn );
    inst_calc_bsv_eingabe.setRkInputLaufzeitInMonaten( rk_input_laufzeit_in_monaten );
    inst_calc_bsv_eingabe.setRkInputKnzAnspBerechnen( rk_input_knz_ansp_berechnen );
    inst_calc_bsv_eingabe.setRkInputKnzDarlehensverzicht( rk_input_knz_darlehensverzicht );
    //inst_calc_bsv_eingabe.setRkInputKnzKfgBeruecksichtigen( rk_input_knz_kfg_beruecksichtigen );
    inst_calc_bsv_eingabe.setRkInputAgKnzBeruecksichtigen( rk_input_ag_knz_beruecksichtigen );
    inst_calc_bsv_eingabe.setRkInputAgBetragVorgabe( rk_input_ag_betrag_vorgabe );

    inst_calc_bsv_eingabe.setRkInputAgArtBegleichung( rk_input_ag_art_begleichung );
    inst_calc_bsv_eingabe.setRkInputKnzBerechnungsziel( rk_input_knz_berechnungsziel );
    inst_calc_bsv_eingabe.setRkInputKnzSparstopp( rk_input_knz_sparstopp );
    inst_calc_bsv_eingabe.setRkInputBetragBausparsumme( rk_input_betrag_bausparsumme );
    inst_calc_bsv_eingabe.setRkInputProzMehrzuteilung( rk_input_proz_mehrzuteilung );
    inst_calc_bsv_eingabe.setRkInputKnzWopBerechnen( rk_input_knz_wop_berechnen );
    inst_calc_bsv_eingabe.setRkInputKnzWopJungeLeuteR( rk_input_knz_wop_junge_leute_r );
    inst_calc_bsv_eingabe.setRkInputKnzWopVerheiratet( rk_input_knz_wop_verheiratet );
    inst_calc_bsv_eingabe.setRkInputVorgabeZtbBetrag( rk_input_vorgabe_ztb_betrag );
    inst_calc_bsv_eingabe.setRkInputVorgabeZtbArt( rk_input_vorgabe_ztb_art );
    inst_calc_bsv_eingabe.setRkInputVorgabeZtbOptimierung( rk_input_vorgabe_ztb_optimierung );

    CalcZahlung zahlung_eigener_sparbeitrag = new CalcZahlung();

    zahlung_eigener_sparbeitrag.setId( rk_input_zahlung_sparbeitrag_id );
    zahlung_eigener_sparbeitrag.setBezeichnung( rk_input_zahlung_sparbeitrag_bezeichnung );
    zahlung_eigener_sparbeitrag.setDatumAb( rk_input_zahlung_sparbeitrag_datum_von );
    zahlung_eigener_sparbeitrag.setDatumBis( rk_input_zahlung_sparbeitrag_datum_bis );
    zahlung_eigener_sparbeitrag.setBetrag( rk_input_zahlung_sparbeitrag_betrag );
    zahlung_eigener_sparbeitrag.setRhythmus( rk_input_zahlung_sparbeitrag_rhythmus );
    zahlung_eigener_sparbeitrag.setKnzTag( rk_input_zahlung_sparbeitrag_knz_tag );
    zahlung_eigener_sparbeitrag.setKnzAktiv( rk_input_zahlung_sparbeitrag_knz_aufnahme );

    CalcZahlung zahlung_vl_as = new CalcZahlung();

    zahlung_vl_as.setId( rk_input_zahlung_vl_as_id );
    zahlung_vl_as.setBezeichnung( rk_input_zahlung_vl_as_bezeichnung );
    zahlung_vl_as.setDatumAb( rk_input_zahlung_vl_as_datum_von );
    zahlung_vl_as.setDatumBis( rk_input_zahlung_vl_as_datum_bis );
    zahlung_vl_as.setBetrag( rk_input_zahlung_vl_as_betrag );
    zahlung_vl_as.setRhythmus( rk_input_zahlung_vl_as_rhythmus );
    zahlung_vl_as.setKnzTag( rk_input_zahlung_vl_as_knz_tag );
    zahlung_vl_as.setKnzAktiv( rk_input_zahlung_vl_as_knz_aufnahme );

    CalcZahlung zahlung_vl_ep = new CalcZahlung();

    zahlung_vl_ep.setId( rk_input_zahlung_vl_ep_id );
    zahlung_vl_ep.setBezeichnung( rk_input_zahlung_vl_ep_bezeichnung );
    zahlung_vl_ep.setDatumAb( rk_input_zahlung_vl_ep_datum_von );
    zahlung_vl_ep.setDatumBis( rk_input_zahlung_vl_ep_datum_bis );
    zahlung_vl_ep.setBetrag( rk_input_zahlung_vl_ep_betrag );
    zahlung_vl_ep.setRhythmus( rk_input_zahlung_vl_ep_rhythmus );
    zahlung_vl_ep.setKnzTag( rk_input_zahlung_vl_ep_knz_tag );
    zahlung_vl_ep.setKnzAktiv( rk_input_zahlung_vl_ep_knz_aufnahme );

    CalcZahlung sonder_zahlung_1 = new CalcZahlung();

    sonder_zahlung_1.setId( rk_input_zahlung_sz_1_id );
    sonder_zahlung_1.setBezeichnung( rk_input_zahlung_sz_1_bezeichnung );
    sonder_zahlung_1.setDatumAb( rk_input_zahlung_sz_1_datum_von );
    sonder_zahlung_1.setDatumBis( rk_input_zahlung_sz_1_datum_bis );
    sonder_zahlung_1.setBetrag( rk_input_zahlung_sz_1_betrag );
    sonder_zahlung_1.setRhythmus( rk_input_zahlung_sz_1_rhythmus );
    sonder_zahlung_1.setKnzTag( rk_input_zahlung_sz_1_knz_tag );
    sonder_zahlung_1.setKnzAktiv( rk_input_zahlung_sz_1_knz_aufnahme );

    CalcZahlung sonder_zahlung_2 = new CalcZahlung();

    sonder_zahlung_2.setId( rk_input_zahlung_sz_2_id );
    sonder_zahlung_2.setBezeichnung( rk_input_zahlung_sz_2_bezeichnung );
    sonder_zahlung_2.setDatumAb( rk_input_zahlung_sz_2_datum_von );
    sonder_zahlung_2.setDatumBis( rk_input_zahlung_sz_2_datum_bis );
    sonder_zahlung_2.setBetrag( rk_input_zahlung_sz_2_betrag );
    sonder_zahlung_2.setRhythmus( rk_input_zahlung_sz_2_rhythmus );
    sonder_zahlung_2.setKnzTag( rk_input_zahlung_sz_2_knz_tag );
    sonder_zahlung_2.setKnzAktiv( rk_input_zahlung_sz_2_knz_aufnahme );

    inst_calc_bsv_eingabe.setDebugKnzAktElement( FkString.getBoolean( debug_knz_akt_element, false ) );
    inst_calc_bsv_eingabe.setDebugKnzAnspBerechnung( FkString.getBoolean( debug_knz_ansp_berechnung, false ) );
    inst_calc_bsv_eingabe.setDebugKnzStichtage( FkString.getBoolean( debug_knz_stichtage, false ) );
    inst_calc_bsv_eingabe.setDebugKnzWopBerechnung( FkString.getBoolean( debug_knz_wop_berechnung, false ) );
    inst_calc_bsv_eingabe.setDebugKnzZinsBerechnungBsd( FkString.getBoolean( debug_knz_zins_berechnung_bsd, false ) );
    inst_calc_bsv_eingabe.setDebugKnzZinsBerechnungSpar( FkString.getBoolean( debug_knz_zins_berechnung_spar, false ) );

    inst_calc_bsv_eingabe.setKnzKfgSparphaseBeruecksichtigen( FkString.getBoolean( calc_vorgaben_knz_kfg_sparphase_beruecksichtigen, true ) );
    inst_calc_bsv_eingabe.setKnzKfgDarlehensphaseBeruecksichtigen( FkString.getBoolean( calc_vorgaben_knz_kfg_darlehensphase_beruecksichtigen, true ) );

    inst_calc_bsv_eingabe.setRkInputZahlungSparbeitrag( zahlung_eigener_sparbeitrag );

    inst_calc_bsv_eingabe.setRkInputZahlungVlAs( zahlung_vl_as );

    inst_calc_bsv_eingabe.setRkInputZahlungVlEp( zahlung_vl_ep );

    inst_calc_bsv_eingabe.addSonderzahlung( sonder_zahlung_1 );

    inst_calc_bsv_eingabe.addSonderzahlung( sonder_zahlung_2 );

    return inst_calc_bsv_eingabe;
  }
}
