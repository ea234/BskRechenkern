package de.bskrechner.calc;

import java.math.BigDecimal;

import de.bskrechner.util.FkDatumLong;
import de.bskrechner.util.FkZahl;

public class CalcBsvErgebnis
{
  private long       m_millisekunden_gesamt                           = 0;

  private long       m_millisekunden_vorabberechnungen                = 0;

  private long       m_millisekunden_sparphase_kto_verlauf            = 0;

  private long       m_millisekunden_sparphase_berechnung             = 0;

  private long       m_millisekunden_darlehensphase                   = 0;

  private long       m_datum_berechnung_beginn_long                   = 0;

  private long       m_datum_berechnung_ende_long                     = 0;

  private long       m_stichtag_datum_long                            = 0;

  private long       m_zuteilung_datum_long                           = 0;

  private long       m_anzahl_bsv_sparraten_bis_spar_stopp            = 0;

  private long       m_anzahl_stichtage_bis_zuteilung                 = 0;

  private long       m_tarif_mindestsparzeit_anzahl_monate            = 0;

  private long       m_tarif_mindestsparzeit_datum                    = 0;

  private BigDecimal m_tarif_mindestguthaben_betrag                   = null;

  private long       m_tarif_mindestguthaben_datum                    = 0;

  private boolean    m_tarif_mindestguthaben_knz_erreicht             = false;

  private BigDecimal m_tarif_bewertungszahl_betrag                    = null;

  private long       m_tarif_bewertungszahl_datum                     = 0;

  private boolean    m_tarif_bewertungszahl_knz_erreicht              = false;

  private long       m_tarif_zuteilungsvoraussetzungen_datum          = 0;

  private boolean    m_tarif_zuteilungsvoraussetzungen_knz_erreicht   = false;

  private BigDecimal m_tarif_mindest_bss                              = null;

  private BigDecimal m_tarif_mindest_bwz                              = null;

  private BigDecimal m_tarif_promille_abschluss_gebuehr               = null;

  private BigDecimal m_tarif_proz_guthabenzins                        = null;

  private BigDecimal m_tarif_proz_mindest_sparguthaben                = null;

  private BigDecimal m_tarif_wartezeit_in_monaten                     = null;

  private BigDecimal m_betrag_bsv_sparbeitrag                         = null;

  private long       m_datum_sparstopp_sparrate_long                  = 0;

  private BigDecimal m_betrag_bsv_bausparsumme                        = null;

  private boolean    m_spar_kto_gebuehr_knz_beruecksichtigt           = true;

  private boolean    m_bsd_kto_gebuehr_knz_beruecksichtigt            = true;

  private long       m_spar_kto_gebuehr_anteilig_anz_monate           = 0;

  private BigDecimal m_spar_kto_gebuehr_anteilig_betrag               = null;

  private long       m_spar_kto_gebuehr_anteilig_datum_belastung_long = 0;

  private BigDecimal m_spar_kto_gebuehr_laufend_betrag                = null;

  private long       m_spar_kto_gebuehr_laufend_datum_belastung_long  = 0;

  private BigDecimal m_stichtag_betrag_bewertungszahl                 = null;

  private BigDecimal m_stichtag_betrag_gebeuhren_kfg                  = null;

  private BigDecimal m_stichtag_betrag_kontostand                     = null;

  private BigDecimal m_stichtag_betrag_saldensumme                    = null;

  private BigDecimal m_stichtag_betrag_soll_guthaben                  = null;

  private BigDecimal m_stichtag_betrag_sonderzahlung                  = null;

  private BigDecimal m_stichtag_betrag_sparbeitrag                    = null;

  private BigDecimal m_stichtag_betrag_vl_antragsteller               = null;

  private BigDecimal m_stichtag_betrag_vl_ehepartner                  = null;

  private BigDecimal m_stichtag_betrag_zinsen                         = null;

  private BigDecimal m_zuteilung_betrag_bewertungszahl                = null;

  private BigDecimal m_zuteilung_betrag_bonus_zinsen                  = null;

  private BigDecimal m_zuteilung_betrag_sparbeitrag                   = null;

  private BigDecimal m_zuteilung_betrag_guthaben_zinsen               = null;

  private BigDecimal m_zuteilung_betrag_gebeuhren_kfg                 = null;

  private BigDecimal m_zuteilung_betrag_sonderzahlung                 = null;

  private BigDecimal m_zuteilung_betrag_vl_antragsteller              = null;

  private BigDecimal m_zuteilung_betrag_vl_ehepartner                 = null;

  private BigDecimal m_zuteilung_betrag_zinsen                        = null;

  private BigDecimal m_zuteilung_betrag_bonus_gutgeschrieben          = null;

  private BigDecimal m_bsd_betrag_anfangsdarlehen                     = null;

  private BigDecimal m_bsd_betrag_darlehen_mit_mehrzuteilung          = null;

  private BigDecimal m_bsd_betrag_mehr_darlehen                       = null;

  private BigDecimal m_bsd_darlehens_gebuehr_betrag                   = null;

  private long       m_bsd_darlehens_gebuehr_datum_belastung          = 0;

  private long       m_bsd_datum_darlehen_beginn                      = 0;

  private long       m_bsd_datum_darlehen_ende                        = 0;

  private BigDecimal m_tarif_regelsparbeitrag_betrag                  = null;

  private BigDecimal m_tarif_regelsparbeitrag_promille_bss            = null;

  private BigDecimal m_bsd_tariflicher_darlehensanspruch              = null;

  private BigDecimal m_bsd_tariflicher_ztb                            = null;

  private BigDecimal m_bsd_disagio_betrag                             = null;

  private long       m_bsd_disagio_datum_belastung                    = 0;

  private BigDecimal m_bsd_gesamt_eigene_einzahlung                   = null;

  private BigDecimal m_bsd_gesamt_kfg                                 = null;

  private BigDecimal m_bsd_gesamt_sondertiglung                       = null;

  private BigDecimal m_bsd_gesamt_zinsen                              = null;

  private BigDecimal m_bsd_letzte_rate_betrag                         = null;

  private long       m_bsd_letzte_rate_datum                          = 0;

  private BigDecimal m_bsd_letzte_rate_zinsbelastung                  = null;

  private BigDecimal m_bsd_ztb_promill_bss                            = null;

  private BigDecimal m_bsd_ztb_betrag                                 = null;

  private long       m_rechenkern_fehler_nummer                       = 0;

  private String     m_rechenkern_fehler_text                         = "";

  private long       m_laufzeit_sparphase_gesamt_monate               = 0;

  private long       m_laufzeit_sparphase_jahre                       = 0;

  private long       m_laufzeit_sparphase_monate                      = 0;

  private long       m_laufzeit_bsd_gesamt_monate                     = 0;

  private long       m_laufzeit_bsd_jahre                             = 0;

  private long       m_laufzeit_bsd_monate                            = 0;

  private long       m_laufzeit_vertrag_gesamt_monate                 = 0;

  private long       m_laufzeit_vertrag_jahre                         = 0;

  private long       m_laufzeit_vertrag_monate                        = 0;

  private BigDecimal m_spar_ansp_betrag_ausstehend                    = null;

  private BigDecimal m_spar_ansp_betrag_gesamt                        = null;

  private BigDecimal m_spar_ansp_betrag_gutgeschrieben                = null;

  private boolean    m_spar_ansp_knz_berechnet                        = false;

  private boolean    m_spar_wop_knz_berechnet                         = false;

  private BigDecimal m_spar_wop_betrag_ausstehend                     = null;

  private BigDecimal m_spar_wop_betrag_gebuehr_gesamt                 = null;

  private BigDecimal m_spar_wop_betrag_gesamt                         = null;

  private BigDecimal m_spar_wop_betrag_gutgeschrieben                 = null;

  private BigDecimal m_bsd_betrag_darlehensanspruch                   = null;

  private boolean    m_knz_ist_neuvertrag                             = true;

  private boolean    m_knz_ist_bestandsvertrag                        = false;

  private String     m_calc_vertragsart                               = "Neuvertrag";

  private String     m_vorgabe_sparstopp                              = null;

  private String     m_vorgabe_berechnungsziel                        = null;

  private String     m_log_string                                     = null;

  private BigDecimal m_bsd_kto_gebuehr_anteilig_betrag                = null;        // new BigDecimal( "0.00" ); 0.0

  private long       m_bsd_kto_gebuehr_anteilig_anz_monate            = 0;

  private BigDecimal m_bsd_kto_gebuehr_laufend_betrag                 = null;        // new BigDecimal( "0.00" ); 0.0

  public BigDecimal getBsdKtoGebuehrAnteiligBetrag()
  {
    return m_bsd_kto_gebuehr_anteilig_betrag;
  }

  public void setBsdKtoGebuehrAnteiligBetrag( BigDecimal pBsdKtoGebuehrAnteiligBetrag )
  {
    m_bsd_kto_gebuehr_anteilig_betrag = pBsdKtoGebuehrAnteiligBetrag;
  }

  public long getBsdKtoGebuehrAnteiligAnzMonate()
  {
    return m_bsd_kto_gebuehr_anteilig_anz_monate;
  }

  public void setBsdKtoGebuehrAnteiligAnzMonate( long pBsdKtoGebuehrAnteiligAnzMonate )
  {
    m_bsd_kto_gebuehr_anteilig_anz_monate = pBsdKtoGebuehrAnteiligAnzMonate;
  }

  public BigDecimal getBsdKtoGebuehrLaufendBetrag()
  {
    return m_bsd_kto_gebuehr_laufend_betrag;
  }

  public void setBsdKtoGebuehrLaufendBetrag( BigDecimal pBsdKtoGebuehrLaufendBetrag )
  {
    m_bsd_kto_gebuehr_laufend_betrag = pBsdKtoGebuehrLaufendBetrag;
  }

  public String getVorgabeSparstopp()
  {
    return m_vorgabe_sparstopp;
  }

  public void setVorgabeSparstopp( String pVorgabeSparstopp )
  {
    m_vorgabe_sparstopp = pVorgabeSparstopp;
  }

  public String getVorgabeBerechnungsziel()
  {
    return m_vorgabe_berechnungsziel;
  }

  public void setVorgabeBerechnungsziel( String pVorgabeBerechnungsziel )
  {
    m_vorgabe_berechnungsziel = pVorgabeBerechnungsziel;
  }

  public String getLogString()
  {
    return m_log_string;
  }

  public void setLogString( String pLogString )
  {
    m_log_string = pLogString;
  }

  public long getAnzahlBsvSparratenBisSparStopp()
  {
    return m_anzahl_bsv_sparraten_bis_spar_stopp;
  }

  public long getAnzahlStichtageBisZuteilung()
  {
    return m_anzahl_stichtage_bis_zuteilung;
  }

  public BigDecimal getBetragBsvBausparsumme()
  {
    return m_betrag_bsv_bausparsumme;
  }

  public BigDecimal getBetragBsvSparbeitrag()
  {
    return m_betrag_bsv_sparbeitrag;
  }

  public BigDecimal getBsdBetragAnfangsdarlehen()
  {
    return m_bsd_betrag_anfangsdarlehen;
  }

  public BigDecimal getBsdBetragDarlehenMitMehrzuteilung()
  {
    return m_bsd_betrag_darlehen_mit_mehrzuteilung;
  }

  public BigDecimal getBsdBetragDarlehensanspruch()
  {
    return m_bsd_betrag_darlehensanspruch;
  }

  public BigDecimal getBsdBetragMehrDarlehen()
  {
    return m_bsd_betrag_mehr_darlehen;
  }

  public BigDecimal getBsdDarlehensGebuehrBetrag()
  {
    return m_bsd_darlehens_gebuehr_betrag;
  }

  public long getBsdDarlehensGebuehrDatumBelastung()
  {
    return m_bsd_darlehens_gebuehr_datum_belastung;
  }

  public long getBsdDatumDarlehenBeginn()
  {
    return m_bsd_datum_darlehen_beginn;
  }

  public long getBsdDatumDarlehenEnde()
  {
    return m_bsd_datum_darlehen_ende;
  }

  public BigDecimal getBsdDisagioBetrag()
  {
    return m_bsd_disagio_betrag;
  }

  public long getBsdDisagioDatumBelastung()
  {
    return m_bsd_disagio_datum_belastung;
  }

  public BigDecimal getBsdGesamtEigeneEinzahlung()
  {
    return m_bsd_gesamt_eigene_einzahlung;
  }

  public BigDecimal getBsdGesamtKfg()
  {
    return m_bsd_gesamt_kfg;
  }

  public BigDecimal getBsdGesamtSondertiglung()
  {
    return m_bsd_gesamt_sondertiglung;
  }

  public BigDecimal getBsdGesamtZinsen()
  {
    return m_bsd_gesamt_zinsen;
  }

  public BigDecimal getBsdLetzteRateBetrag()
  {
    return m_bsd_letzte_rate_betrag;
  }

  public long getBsdLetzteRateDatum()
  {
    return m_bsd_letzte_rate_datum;
  }

  public BigDecimal getBsdLetzteRateZinsbelastung()
  {
    return m_bsd_letzte_rate_zinsbelastung;
  }

  public BigDecimal getBsdTariflicherDarlehensanspruch()
  {
    return m_bsd_tariflicher_darlehensanspruch;
  }

  public BigDecimal getBsdTariflicherZtb()
  {
    return m_bsd_tariflicher_ztb;
  }

  public BigDecimal getBsdZtbBetrag()
  {
    return m_bsd_ztb_betrag;
  }

  public BigDecimal getBsdZtbPromillBss()
  {
    return m_bsd_ztb_promill_bss;
  }

  public long getDatumBerechnungBeginn()
  {
    return m_datum_berechnung_beginn_long;
  }

  public long getDatumBerechnungEnde()
  {
    return m_datum_berechnung_ende_long;
  }

  public long getLaufzeitBsdGesamtMonate()
  {
    return m_laufzeit_bsd_gesamt_monate;
  }

  public long getLaufzeitBsdJahre()
  {
    return m_laufzeit_bsd_jahre;
  }

  public long getLaufzeitBsdMonate()
  {
    return m_laufzeit_bsd_monate;
  }

  public long getLaufzeitSparphaseGesamtMonate()
  {
    return m_laufzeit_sparphase_gesamt_monate;
  }

  public long getLaufzeitSparphaseJahre()
  {
    return m_laufzeit_sparphase_jahre;
  }

  public long getLaufzeitSparphaseMonate()
  {
    return m_laufzeit_sparphase_monate;
  }

  public long getLaufzeitVertragGesamtMonate()
  {
    return m_laufzeit_vertrag_gesamt_monate;
  }

  public long getLaufzeitVertragJahre()
  {
    return m_laufzeit_vertrag_jahre;
  }

  public long getLaufzeitVertragMonate()
  {
    return m_laufzeit_vertrag_monate;
  }

  public long getMillisekundenDarlehensphase()
  {
    return m_millisekunden_darlehensphase;
  }

  public long getMillisekundenGesamt()
  {
    return m_millisekunden_gesamt;
  }

  public long getMillisekundenSparphaseBerechnung()
  {
    return m_millisekunden_sparphase_berechnung;
  }

  public long getMillisekundenSparphaseKtoVerlauf()
  {
    return m_millisekunden_sparphase_kto_verlauf;
  }

  public long getMillisekundenVorabberechnungen()
  {
    return m_millisekunden_vorabberechnungen;
  }

  public long getRechenkernFehlerNummer()
  {
    return m_rechenkern_fehler_nummer;
  }

  public String getRechenkernFehlerText()
  {
    return m_rechenkern_fehler_text;
  }

  public BigDecimal getSparAnspBetragAusstehend()
  {
    return m_spar_ansp_betrag_ausstehend;
  }

  public BigDecimal getSparAnspBetragGesamt()
  {
    return m_spar_ansp_betrag_gesamt;
  }

  public BigDecimal getSparAnspBetragGutgeschrieben()
  {
    return m_spar_ansp_betrag_gutgeschrieben;
  }

  public boolean getSparAnspKnzBerechnet()
  {
    return m_spar_ansp_knz_berechnet;
  }

  public long getDatumSparstoppSparrate()
  {
    return m_datum_sparstopp_sparrate_long;
  }

  public long getSparKtoGebuehrAnteiligAnzMonate()
  {
    return m_spar_kto_gebuehr_anteilig_anz_monate;
  }

  public BigDecimal getSparKtoGebuehrAnteiligBetrag()
  {
    return m_spar_kto_gebuehr_anteilig_betrag;
  }

  public long getSparKtoGebuehrAnteiligDatumBelastung()
  {
    return m_spar_kto_gebuehr_anteilig_datum_belastung_long;
  }

  public BigDecimal getSparKtoGebuehrLaufendBetrag()
  {
    return m_spar_kto_gebuehr_laufend_betrag;
  }

  public long getSparKtoGebuehrLaufendDatumBelastung()
  {
    return m_spar_kto_gebuehr_laufend_datum_belastung_long;
  }

  public BigDecimal getSparWopBetragAusstehend()
  {
    return m_spar_wop_betrag_ausstehend;
  }

  public BigDecimal getSparWopBetragGebuehrGesamt()
  {
    return m_spar_wop_betrag_gebuehr_gesamt;
  }

  public BigDecimal getSparWopBetragGesamt()
  {
    return m_spar_wop_betrag_gesamt;
  }

  public BigDecimal getSparWopBetragGutgeschrieben()
  {
    return m_spar_wop_betrag_gutgeschrieben;
  }

  public boolean getSparWopKnzBerechnet()
  {
    return m_spar_wop_knz_berechnet;
  }

  public BigDecimal getStichtagBetragBewertungszahl()
  {
    return m_stichtag_betrag_bewertungszahl;
  }

  public BigDecimal getStichtagBetragGebeuhrenKfg()
  {
    return m_stichtag_betrag_gebeuhren_kfg;
  }

  public BigDecimal getStichtagBetragKontostand()
  {
    return m_stichtag_betrag_kontostand;
  }

  public BigDecimal getStichtagBetragSaldensumme()
  {
    return m_stichtag_betrag_saldensumme;
  }

  public BigDecimal getStichtagBetragSollGuthaben()
  {
    return m_stichtag_betrag_soll_guthaben;
  }

  public BigDecimal getStichtagBetragSonderzahlung()
  {
    return m_stichtag_betrag_sonderzahlung;
  }

  public BigDecimal getStichtagBetragSparbeitrag()
  {
    return m_stichtag_betrag_sparbeitrag;
  }

  public BigDecimal getStichtagBetragVlAntragsteller()
  {
    return m_stichtag_betrag_vl_antragsteller;
  }

  public BigDecimal getStichtagBetragVlEhepartner()
  {
    return m_stichtag_betrag_vl_ehepartner;
  }

  public BigDecimal getStichtagBetragZinsen()
  {
    return m_stichtag_betrag_zinsen;
  }

  public long getStichtagDatum()
  {
    return m_stichtag_datum_long;
  }

  public BigDecimal getTarifBewertungszahlBetrag()
  {
    return m_tarif_bewertungszahl_betrag;
  }

  public long getTarifBewertungszahlDatum()
  {
    return m_tarif_bewertungszahl_datum;
  }

  public boolean getTarifBewertungszahlKnzErreicht()
  {
    return m_tarif_bewertungszahl_knz_erreicht;
  }

  public BigDecimal getTarifMindestBss()
  {
    return m_tarif_mindest_bss;
  }

  public BigDecimal getTarifMindestBwz()
  {
    return m_tarif_mindest_bwz;
  }

  public BigDecimal getTarifMindestguthabenBetrag()
  {
    return m_tarif_mindestguthaben_betrag;
  }

  public long getTarifMindestguthabenDatum()
  {
    return m_tarif_mindestguthaben_datum;
  }

  public boolean getTarifMindestguthabenKnzErreicht()
  {
    return m_tarif_mindestguthaben_knz_erreicht;
  }

  public long getTarifMindestsparzeitAnzahlMonate()
  {
    return m_tarif_mindestsparzeit_anzahl_monate;
  }

  public long getTarifMindestsparzeitDatum()
  {
    return m_tarif_mindestsparzeit_datum;
  }

  public BigDecimal getTarifPromilleAbschlussGebuehr()
  {
    return m_tarif_promille_abschluss_gebuehr;
  }

  public BigDecimal getTarifProzGuthabenzins()
  {
    return m_tarif_proz_guthabenzins;
  }

  public BigDecimal getTarifProzMindestSparguthaben()
  {
    return m_tarif_proz_mindest_sparguthaben;
  }

  public BigDecimal getTarifRegelsparbeitragBetrag()
  {
    return m_tarif_regelsparbeitrag_betrag;
  }

  public BigDecimal getTarifRegelsparbeitragPromilleBss()
  {
    return m_tarif_regelsparbeitrag_promille_bss;
  }

  public BigDecimal getTarifWartezeitInMonaten()
  {
    return m_tarif_wartezeit_in_monaten;
  }

  public boolean getTarifZuteilungsvoraussetzungenKnzErreicht()
  {
    return m_tarif_zuteilungsvoraussetzungen_knz_erreicht;
  }

  public long getTarifZuteilungsvoraussetzungenDatum()
  {
    return m_tarif_zuteilungsvoraussetzungen_datum;
  }

  public BigDecimal getZuteilungBetragBewertungszahl()
  {
    return m_zuteilung_betrag_bewertungszahl;
  }

  public BigDecimal getZuteilungBetragBonusGutgeschrieben()
  {
    return m_zuteilung_betrag_bonus_gutgeschrieben;
  }

  public BigDecimal getZuteilungBetragBonusZinsen()
  {
    return m_zuteilung_betrag_bonus_zinsen;
  }

  public BigDecimal getZuteilungBetragGebuehrenKfg()
  {
    return m_zuteilung_betrag_gebeuhren_kfg;
  }

  public BigDecimal getZuteilungBetragGuthabenZinsen()
  {
    return m_zuteilung_betrag_guthaben_zinsen;
  }

  public BigDecimal getZuteilungBetragSonderzahlung()
  {
    return m_zuteilung_betrag_sonderzahlung;
  }

  public BigDecimal getZuteilungBetragSparbeitrag()
  {
    return m_zuteilung_betrag_sparbeitrag;
  }

  public BigDecimal getZuteilungBetragVlAntragsteller()
  {
    return m_zuteilung_betrag_vl_antragsteller;
  }

  public BigDecimal getZuteilungBetragVlEhepartner()
  {
    return m_zuteilung_betrag_vl_ehepartner;
  }

  public BigDecimal getZuteilungBetragZinsen()
  {
    return m_zuteilung_betrag_zinsen;
  }

  public long getZuteilungDatum()
  {
    return m_zuteilung_datum_long;
  }

  public void setAnzahlBsvSparratenBisSparStopp( long pAnzahlBsvSparratenBisSparStopp )
  {
    m_anzahl_bsv_sparraten_bis_spar_stopp = pAnzahlBsvSparratenBisSparStopp;
  }

  public void setAnzahlStichtageBisZuteilung( long pAnzahlStichtageBisZuteilung )
  {
    m_anzahl_stichtage_bis_zuteilung = pAnzahlStichtageBisZuteilung;
  }

  public void setBetragBsvBausparsumme( BigDecimal pBetragBsvBausparsumme )
  {
    m_betrag_bsv_bausparsumme = pBetragBsvBausparsumme;
  }

  public void setBetragBsvSparbeitrag( BigDecimal pBetragBsvSparbeitrag )
  {
    m_betrag_bsv_sparbeitrag = pBetragBsvSparbeitrag;
  }

  public void setBsdBetragAnfangsdarlehen( BigDecimal pBsdBetragAnfangsdarlehen )
  {
    m_bsd_betrag_anfangsdarlehen = pBsdBetragAnfangsdarlehen;
  }

  public void setBsdBetragDarlehenMitMehrzuteilung( BigDecimal pBsdBetragDarlehenMitMehrzuteilung )
  {
    m_bsd_betrag_darlehen_mit_mehrzuteilung = pBsdBetragDarlehenMitMehrzuteilung;
  }

  public void setBsdBetragDarlehensanspruch( BigDecimal pBsdBetragDarlehensanspruch )
  {
    m_bsd_betrag_darlehensanspruch = pBsdBetragDarlehensanspruch;
  }

  public void setBsdBetragMehrDarlehen( BigDecimal pBsdBetragMehrDarlehen )
  {
    m_bsd_betrag_mehr_darlehen = pBsdBetragMehrDarlehen;
  }

  public void setBsdDarlehensGebuehrBetrag( BigDecimal pBsdDarlehensGebuehrBetrag )
  {
    m_bsd_darlehens_gebuehr_betrag = pBsdDarlehensGebuehrBetrag;
  }

  public void setBsdDarlehensGebuehrDatumBelastung( long pBsdDarlehensGebuehrDatumBelastung )
  {
    m_bsd_darlehens_gebuehr_datum_belastung = pBsdDarlehensGebuehrDatumBelastung;
  }

  public void setBsdDatumDarlehenBeginn( long pBsdDatumDarlehenBeginn )
  {
    m_bsd_datum_darlehen_beginn = pBsdDatumDarlehenBeginn;
  }

  public void setBsdDatumDarlehenEnde( long pBsdDatumDarlehenEnde )
  {
    m_bsd_datum_darlehen_ende = pBsdDatumDarlehenEnde;
  }

  public void setBsdDisagioBetrag( BigDecimal pBsdDisagioBetrag )
  {
    m_bsd_disagio_betrag = pBsdDisagioBetrag;
  }

  public void setBsdDisagioDatumBelastung( long pBsdDisagioDatumBelastung )
  {
    m_bsd_disagio_datum_belastung = pBsdDisagioDatumBelastung;
  }

  public void setBsdGesamtEigeneEinzahlung( BigDecimal pBsdGesamtEigeneEinzahlung )
  {
    m_bsd_gesamt_eigene_einzahlung = pBsdGesamtEigeneEinzahlung;
  }

  public void setBsdGesamtKfg( BigDecimal pBsdGesamtKfg )
  {
    m_bsd_gesamt_kfg = pBsdGesamtKfg;
  }

  public void setBsdGesamtSondertiglung( BigDecimal pBsdGesamtSondertiglung )
  {
    m_bsd_gesamt_sondertiglung = pBsdGesamtSondertiglung;
  }

  public void setBsdGesamtZinsen( BigDecimal pBsdGesamtZinsen )
  {
    m_bsd_gesamt_zinsen = pBsdGesamtZinsen;
  }

  public void setBsdLetzteRateBetrag( BigDecimal pBsdLetzteRateBetrag )
  {
    m_bsd_letzte_rate_betrag = pBsdLetzteRateBetrag;
  }

  public void setBsdLetzteRateDatum( long pBsdLetzteRateDatum )
  {
    m_bsd_letzte_rate_datum = pBsdLetzteRateDatum;
  }

  public void setBsdLetzteRateZinsbelastung( BigDecimal pBsdLetzteRateZinsbelastung )
  {
    m_bsd_letzte_rate_zinsbelastung = pBsdLetzteRateZinsbelastung;
  }

  public void setBsdTariflicherDarlehensanspruch( BigDecimal pBsdTariflicherDarlehensanspruch )
  {
    m_bsd_tariflicher_darlehensanspruch = pBsdTariflicherDarlehensanspruch;
  }

  public void setBsdTariflicherZtb( BigDecimal pBsdTariflicherZtb )
  {
    m_bsd_tariflicher_ztb = pBsdTariflicherZtb;
  }

  public void setBsdZtbBetrag( BigDecimal pBsdZtbBetrag )
  {
    m_bsd_ztb_betrag = pBsdZtbBetrag;
  }

  public void setBsdZtbPromillBss( BigDecimal pBsdZtbPromillBss )
  {
    m_bsd_ztb_promill_bss = pBsdZtbPromillBss;
  }

  public void setDatumBerechnungBeginn( long pDatumBerechnungBeginn )
  {
    m_datum_berechnung_beginn_long = pDatumBerechnungBeginn;
  }

  public void setDatumBerechnungEnde( long pDatumBerechnungEnde )
  {
    m_datum_berechnung_ende_long = pDatumBerechnungEnde;
  }

  public void setLaufzeitBsdGesamtMonate( long pLaufzeitBsdGesamtMonate )
  {
    m_laufzeit_bsd_gesamt_monate = pLaufzeitBsdGesamtMonate;
  }

  public void setLaufzeitBsdJahre( long pLaufzeitBsdJahre )
  {
    m_laufzeit_bsd_jahre = pLaufzeitBsdJahre;
  }

  public void setLaufzeitBsdMonate( long pLaufzeitBsdMonate )
  {
    m_laufzeit_bsd_monate = pLaufzeitBsdMonate;
  }

  public void setLaufzeitSparphaseGesamtMonate( long pLaufzeitSparphaseGesamtMonate )
  {
    m_laufzeit_sparphase_gesamt_monate = pLaufzeitSparphaseGesamtMonate;
  }

  public void setLaufzeitSparphaseJahre( long pLaufzeitSparphaseJahre )
  {
    m_laufzeit_sparphase_jahre = pLaufzeitSparphaseJahre;
  }

  public void setLaufzeitSparphaseMonate( long pLaufzeitSparphaseMonate )
  {
    m_laufzeit_sparphase_monate = pLaufzeitSparphaseMonate;
  }

  public void setLaufzeitVertragGesamtMonate( long pLaufzeitVertragGesamtMonate )
  {
    m_laufzeit_vertrag_gesamt_monate = pLaufzeitVertragGesamtMonate;
  }

  public void setLaufzeitVertragJahre( long pLaufzeitVertragJahre )
  {
    m_laufzeit_vertrag_jahre = pLaufzeitVertragJahre;
  }

  public void setLaufzeitVertragMonate( long pLaufzeitVertragMonate )
  {
    m_laufzeit_vertrag_monate = pLaufzeitVertragMonate;
  }

  public void setMillisekundenDarlehensphase( long pMillisekundenDarlehensphase )
  {
    m_millisekunden_darlehensphase = pMillisekundenDarlehensphase;
  }

  public void setMillisekundenGesamt( long pMillisekundenGesamt )
  {
    m_millisekunden_gesamt = pMillisekundenGesamt;
  }

  public void setMillisekundenSparphaseBerechnung( long pMillisekundenSparphase )
  {
    m_millisekunden_sparphase_berechnung = pMillisekundenSparphase;
  }

  public void setMillisekundenSparphaseKtoVerlauf( long pMillisekundenSparphaseKtoVerlauf )
  {
    m_millisekunden_sparphase_kto_verlauf = pMillisekundenSparphaseKtoVerlauf;
  }

  public void setMillisekundenVorabberechnungen( long pMillisekundenVorabberechnungen )
  {
    m_millisekunden_vorabberechnungen = pMillisekundenVorabberechnungen;
  }

  public void setRechenkernFehlerNummer( long pRechenkernFehlerNummer )
  {
    m_rechenkern_fehler_nummer = pRechenkernFehlerNummer;
  }

  public void setRechenkernFehlerText( String pRechenkernFehlerText )
  {
    m_rechenkern_fehler_text = pRechenkernFehlerText;
  }

  public void setSparAnspBetragAusstehend( BigDecimal pSparAnspBetragAusstehend )
  {
    m_spar_ansp_betrag_ausstehend = pSparAnspBetragAusstehend;
  }

  public void setSparAnspBetragGesamt( BigDecimal pSparAnspBetragGesamt )
  {
    m_spar_ansp_betrag_gesamt = pSparAnspBetragGesamt;
  }

  public void setSparAnspBetragGutgeschrieben( BigDecimal pSparAnspBetragGutgeschrieben )
  {
    m_spar_ansp_betrag_gutgeschrieben = pSparAnspBetragGutgeschrieben;
  }

  public void setSparAnspKnzBerechnet( boolean pSparAnspKnzBerechnet )
  {
    m_spar_ansp_knz_berechnet = pSparAnspKnzBerechnet;
  }

  public void setDatumSparstoppSparrate( long pDatumSparstoppSparrate )
  {
    m_datum_sparstopp_sparrate_long = pDatumSparstoppSparrate;
  }

  public void setSparKtoGebuehrAnteiligAnzMonate( long pSparKtoGebuehrAnteiligAnzMonate )
  {
    m_spar_kto_gebuehr_anteilig_anz_monate = pSparKtoGebuehrAnteiligAnzMonate;
  }

  public void setSparKtoGebuehrAnteiligBetrag( BigDecimal pSparKtoGebuehrAnteiligBetrag )
  {
    m_spar_kto_gebuehr_anteilig_betrag = pSparKtoGebuehrAnteiligBetrag;
  }

  public void setSparKtoGebuehrAnteiligDatumBelastung( long pSparKtoGebuehrAnteiligDatumBelastung )
  {
    m_spar_kto_gebuehr_anteilig_datum_belastung_long = pSparKtoGebuehrAnteiligDatumBelastung;
  }

  public void setSparKtoGebuehrLaufendBetrag( BigDecimal pSparKtoGebuehrLaufendBetrag )
  {
    m_spar_kto_gebuehr_laufend_betrag = pSparKtoGebuehrLaufendBetrag;
  }

  public void setSparKtoGebuehrLaufendDatumBelastung( long pSparKtoGebuehrLaufendDatumBelastung )
  {
    m_spar_kto_gebuehr_laufend_datum_belastung_long = pSparKtoGebuehrLaufendDatumBelastung;
  }

  public void setSparWopBetragAusstehend( BigDecimal pSparWopBetragAusstehend )
  {
    m_spar_wop_betrag_ausstehend = pSparWopBetragAusstehend;
  }

  public void setSparWopBetragGebuehrGesamt( BigDecimal pSparWopBetragGebuehrGesamt )
  {
    m_spar_wop_betrag_gebuehr_gesamt = pSparWopBetragGebuehrGesamt;
  }

  public void setSparWopBetragGesamt( BigDecimal pSparWopBetragGesamt )
  {
    m_spar_wop_betrag_gesamt = pSparWopBetragGesamt;
  }

  public void setSparWopBetragGutgeschrieben( BigDecimal pSparWopBetragGutgeschrieben )
  {
    m_spar_wop_betrag_gutgeschrieben = pSparWopBetragGutgeschrieben;
  }

  public void setSparWopKnzBerechnet( boolean pSparWopKnzBerechnet )
  {
    m_spar_wop_knz_berechnet = pSparWopKnzBerechnet;
  }

  public void setStichtagBetragBewertungszahl( BigDecimal pStichtagBetragBewertungszahl )
  {
    m_stichtag_betrag_bewertungszahl = pStichtagBetragBewertungszahl;
  }

  public void setStichtagBetragGebeuhrenKfg( BigDecimal pStichtagBetragGebeuhrenKfg )
  {
    m_stichtag_betrag_gebeuhren_kfg = pStichtagBetragGebeuhrenKfg;
  }

  public void setStichtagBetragKontostand( BigDecimal pStichtagBetragKontostand )
  {
    m_stichtag_betrag_kontostand = pStichtagBetragKontostand;
  }

  public void setStichtagBetragSaldensumme( BigDecimal pStichtagBetragSaldensumme )
  {
    m_stichtag_betrag_saldensumme = pStichtagBetragSaldensumme;
  }

  public void setStichtagBetragSollGuthaben( BigDecimal pStichtagBetragSollGuthaben )
  {
    m_stichtag_betrag_soll_guthaben = pStichtagBetragSollGuthaben;
  }

  public void setStichtagBetragSonderzahlung( BigDecimal pStichtagBetragSonderzahlung )
  {
    m_stichtag_betrag_sonderzahlung = pStichtagBetragSonderzahlung;
  }

  public void setStichtagBetragSparbeitrag( BigDecimal pStichtagBetragSparbeitrag )
  {
    m_stichtag_betrag_sparbeitrag = pStichtagBetragSparbeitrag;
  }

  public void setStichtagBetragVlAntragsteller( BigDecimal pStichtagBetragVlAntragsteller )
  {
    m_stichtag_betrag_vl_antragsteller = pStichtagBetragVlAntragsteller;
  }

  public void setStichtagBetragVlEhepartner( BigDecimal pStichtagBetragVlEhepartner )
  {
    m_stichtag_betrag_vl_ehepartner = pStichtagBetragVlEhepartner;
  }

  public void setStichtagBetragZinsen( BigDecimal pStichtagBetragZinsen )
  {
    m_stichtag_betrag_zinsen = pStichtagBetragZinsen;
  }

  public void setStichtagDatum( long pDatumStichtag )
  {
    m_stichtag_datum_long = pDatumStichtag;
  }

  public void setTarifBewertungszahlBetrag( BigDecimal pTarifBewertungszahlBetrag )
  {
    m_tarif_bewertungszahl_betrag = pTarifBewertungszahlBetrag;
  }

  public void setTarifBewertungszahlDatum( long pTarifBewertungszahlDatum )
  {
    m_tarif_bewertungszahl_datum = pTarifBewertungszahlDatum;
  }

  public void setTarifBewertungszahlKnzErreicht( boolean pTarifBewertungszahlKnzErreicht )
  {
    m_tarif_bewertungszahl_knz_erreicht = pTarifBewertungszahlKnzErreicht;
  }

  public void setTarifMindestBss( BigDecimal pTarifMindestBss )
  {
    m_tarif_mindest_bss = pTarifMindestBss;
  }

  public void setTarifMindestBwz( BigDecimal pTarifMindestBwz )
  {
    m_tarif_mindest_bwz = pTarifMindestBwz;
  }

  public void setTarifMindestguthabenBetrag( BigDecimal pTarifMindestguthabenBetrag )
  {
    m_tarif_mindestguthaben_betrag = pTarifMindestguthabenBetrag;
  }

  public void setTarifMindestguthabenDatum( long pTarifMindestguthabenDatum )
  {
    m_tarif_mindestguthaben_datum = pTarifMindestguthabenDatum;
  }

  public void setTarifMindestguthabenKnzErreicht( boolean pTarifMindestguthabenKnzErreicht )
  {
    m_tarif_mindestguthaben_knz_erreicht = pTarifMindestguthabenKnzErreicht;
  }

  public void setTarifMindestsparzeitAnzahlMonate( long pTarifMindestsparzeitAnzahlMonate )
  {
    m_tarif_mindestsparzeit_anzahl_monate = pTarifMindestsparzeitAnzahlMonate;
  }

  public void setTarifMindestsparzeitDatum( long pTarifMindestsparzeitDatum )
  {
    m_tarif_mindestsparzeit_datum = pTarifMindestsparzeitDatum;
  }

  public void setTarifPromilleAbschlussGebuehr( BigDecimal pTarifPromilleAbschlussGebuehr )
  {
    m_tarif_promille_abschluss_gebuehr = pTarifPromilleAbschlussGebuehr;
  }

  public void setTarifProzGuthabenzins( BigDecimal pTarifProzGuthabenzins )
  {
    m_tarif_proz_guthabenzins = pTarifProzGuthabenzins;
  }

  public void setTarifProzMindestSparguthaben( BigDecimal pTarifProzMindestSparguthaben )
  {
    m_tarif_proz_mindest_sparguthaben = pTarifProzMindestSparguthaben;
  }

  public void setTarifRegelsparbeitragBetrag( BigDecimal pTarifRegelsparbeitragBetrag )
  {
    m_tarif_regelsparbeitrag_betrag = pTarifRegelsparbeitragBetrag;
  }

  public void setTarifRegelsparbeitragPromilleBss( BigDecimal pTarifRegelsparbeitragPromilleBss )
  {
    m_tarif_regelsparbeitrag_promille_bss = pTarifRegelsparbeitragPromilleBss;
  }

  public void setTarifWartezeitInMonaten( BigDecimal pTarifWartezeitInMonaten )
  {
    m_tarif_wartezeit_in_monaten = pTarifWartezeitInMonaten;
  }

  public void setTarifZuteilungsvoraussetzungenDatum( long pTarifZuteilungsvoraussetzungenDatum )
  {
    m_tarif_zuteilungsvoraussetzungen_datum = pTarifZuteilungsvoraussetzungenDatum;
  }

  public void setTarifZuteilungsvoraussetzungenKnzErreicht( boolean pTarifZuteilungsvoraussetzungenKnzErreicht )
  {
    m_tarif_zuteilungsvoraussetzungen_knz_erreicht = pTarifZuteilungsvoraussetzungenKnzErreicht;
  }

  public void setZuteilungBetragBewertungszahl( BigDecimal pZuteilungBetragBewertungszahl )
  {
    m_zuteilung_betrag_bewertungszahl = pZuteilungBetragBewertungszahl;
  }

  public void setZuteilungBetragBonusGutgeschrieben( BigDecimal pZuteilungBetragBonusGutgeschrieben )
  {
    m_zuteilung_betrag_bonus_gutgeschrieben = pZuteilungBetragBonusGutgeschrieben;
  }

  public void setZuteilungBetragBonusZinsen( BigDecimal pZuteilungBetragBonusZinsen )
  {
    m_zuteilung_betrag_bonus_zinsen = pZuteilungBetragBonusZinsen;
  }

  public void setZuteilungBetragGebuehrenKfg( BigDecimal pZuteilungBetragGebuehrenKfg )
  {
    m_zuteilung_betrag_gebeuhren_kfg = pZuteilungBetragGebuehrenKfg;
  }

  public void setZuteilungBetragGuthabenZinsen( BigDecimal pZuteilungBetragGuthabenZinsen )
  {
    m_zuteilung_betrag_guthaben_zinsen = pZuteilungBetragGuthabenZinsen;
  }

  public void setZuteilungBetragSonderzahlung( BigDecimal pZuteilungBetragSonderzahlung )
  {
    m_zuteilung_betrag_sonderzahlung = pZuteilungBetragSonderzahlung;
  }

  public void setZuteilungBetragSparbeitrag( BigDecimal pZuteilungBetragSparbeitrag )
  {
    m_zuteilung_betrag_sparbeitrag = pZuteilungBetragSparbeitrag;
  }

  public void setZuteilungBetragVlAntragsteller( BigDecimal pZuteilungBetragVlAntragsteller )
  {
    m_zuteilung_betrag_vl_antragsteller = pZuteilungBetragVlAntragsteller;
  }

  public void setZuteilungBetragVlEhepartner( BigDecimal pZuteilungBetragVlEhepartner )
  {
    m_zuteilung_betrag_vl_ehepartner = pZuteilungBetragVlEhepartner;
  }

  public void setZuteilungBetragZinsen( BigDecimal pZuteilungBetragZinsen )
  {
    m_zuteilung_betrag_zinsen = pZuteilungBetragZinsen;
  }

  public void setZuteilungDatum( long pDatumZuteilung )
  {
    m_zuteilung_datum_long = pDatumZuteilung;
  }

  public boolean getSparKtoGebuehrKnzBeruecksichtigt()
  {
    return m_spar_kto_gebuehr_knz_beruecksichtigt;
  }

  public void setSparKtoGebuehrKnzBeruecksichtigt( boolean pSparKtoGebuehrKnzBeruecksichtigt )
  {
    m_spar_kto_gebuehr_knz_beruecksichtigt = pSparKtoGebuehrKnzBeruecksichtigt;
  }

  public boolean getBsdKtoGebuehrKnzBeruecksichtigt()
  {
    return m_bsd_kto_gebuehr_knz_beruecksichtigt;
  }

  public void setBsdKtoGebuehrKnzBeruecksichtigt( boolean pBsdKtoGebuehrKnzBeruecksichtigt )
  {
    m_bsd_kto_gebuehr_knz_beruecksichtigt = pBsdKtoGebuehrKnzBeruecksichtigt;
  }

  public boolean getKnzIstNeuvertrag()
  {
    return m_knz_ist_neuvertrag;
  }

  public void setKnzIstNeuvertrag( boolean pKnzIstNeuvertrag )
  {
    m_knz_ist_neuvertrag = pKnzIstNeuvertrag;

    m_knz_ist_bestandsvertrag = !m_knz_ist_neuvertrag;

    if ( m_knz_ist_neuvertrag )
    {
      m_calc_vertragsart = "Neuvertrag";
    }
    else
    {
      m_calc_vertragsart = "Neuvertrag";
    }
  }

  public boolean getKnzIstBestandsvertrag()
  {
    return m_knz_ist_bestandsvertrag;
  }

  public String getCalcVertragsart()
  {
    return m_calc_vertragsart;
  }

  public String toString()
  {
    String temp_str = "";

    temp_str += "\n m_rechenkern_fehler_nummer                       =>" + m_rechenkern_fehler_nummer + "<";
    temp_str += "\n m_rechenkern_fehler_text                         =>" + m_rechenkern_fehler_text + "<";
    temp_str += "\n ";
    temp_str += "\n m_calc_vertragsart                               =>" + m_calc_vertragsart + "<";
    temp_str += "\n m_knz_ist_neuvertrag                             =>" + m_knz_ist_neuvertrag + "<";
    temp_str += "\n m_knz_ist_bestandsvertrag                        =>" + m_knz_ist_bestandsvertrag + "<";
    temp_str += "\n ";
    temp_str += "\n m_datum_berechnung_beginn_long                   =>" + m_datum_berechnung_beginn_long + "< " + FkDatumLong.getDatumStringAusLong( m_datum_berechnung_beginn_long );
    temp_str += "\n m_datum_berechnung_ende_long                     =>" + m_datum_berechnung_ende_long + "< " + FkDatumLong.getDatumStringAusLong( m_datum_berechnung_ende_long );
    temp_str += "\n m_datum_sparstopp_sparrate_long                  =>" + m_datum_sparstopp_sparrate_long + "< " + FkDatumLong.getDatumStringAusLong( m_datum_sparstopp_sparrate_long );
    temp_str += "\n m_betrag_bsv_sparbeitrag                         =>" + m_betrag_bsv_sparbeitrag + "<";
    temp_str += "\n m_betrag_bsv_bausparsumme                        =>" + m_betrag_bsv_bausparsumme + "<";
    temp_str += "\n ";
    temp_str += "\n m_spar_kto_gebuehr_knz_beruecksichtigt           =>" + m_spar_kto_gebuehr_knz_beruecksichtigt + "<";
    temp_str += "\n m_spar_kto_gebuehr_anteilig_datum_belastung_long =>" + m_spar_kto_gebuehr_anteilig_datum_belastung_long + "< " + FkDatumLong.getDatumStringAusLong( m_spar_kto_gebuehr_anteilig_datum_belastung_long );
    temp_str += "\n m_spar_kto_gebuehr_anteilig_anz_monate           =>" + m_spar_kto_gebuehr_anteilig_anz_monate + "<";
    temp_str += "\n m_spar_kto_gebuehr_anteilig_betrag               =>" + m_spar_kto_gebuehr_anteilig_betrag + "<";
    temp_str += "\n m_spar_kto_gebuehr_laufend_datum_belastung_long  =>" + m_spar_kto_gebuehr_laufend_datum_belastung_long + "< " + FkDatumLong.getDatumStringAusLong( m_spar_kto_gebuehr_laufend_datum_belastung_long );
    temp_str += "\n m_spar_kto_gebuehr_laufend_betrag                =>" + m_spar_kto_gebuehr_laufend_betrag + "<";
    temp_str += "\n ";
    temp_str += "\n m_spar_ansp_knz_berechnet                        =>" + m_spar_ansp_knz_berechnet + "<";
    temp_str += "\n m_spar_ansp_betrag_ausstehend                    =>" + m_spar_ansp_betrag_ausstehend + "<";
    temp_str += "\n m_spar_ansp_betrag_gesamt                        =>" + m_spar_ansp_betrag_gesamt + "<";
    temp_str += "\n m_spar_ansp_betrag_gutgeschrieben                =>" + m_spar_ansp_betrag_gutgeschrieben + "<";
    temp_str += "\n ";
    temp_str += "\n m_spar_wop_knz_berechnet                         =>" + m_spar_wop_knz_berechnet + "<";
    temp_str += "\n m_spar_wop_betrag_ausstehend                     =>" + m_spar_wop_betrag_ausstehend + "<";
    temp_str += "\n m_spar_wop_betrag_gebuehr_gesamt                 =>" + m_spar_wop_betrag_gebuehr_gesamt + "<";
    temp_str += "\n m_spar_wop_betrag_gesamt                         =>" + m_spar_wop_betrag_gesamt + "<";
    temp_str += "\n m_spar_wop_betrag_gutgeschrieben                 =>" + m_spar_wop_betrag_gutgeschrieben + "<";
    temp_str += "\n ";
    temp_str += "\n m_stichtag_datum_long                            =>" + m_stichtag_datum_long + "< " + FkDatumLong.getDatumStringAusLong( m_stichtag_datum_long );
    temp_str += "\n m_stichtag_betrag_bewertungszahl                 =>" + m_stichtag_betrag_bewertungszahl + "<";
    temp_str += "\n m_stichtag_betrag_sparbeitrag                    =>" + m_stichtag_betrag_sparbeitrag + "<";
    temp_str += "\n m_stichtag_betrag_vl_antragsteller               =>" + m_stichtag_betrag_vl_antragsteller + "<";
    temp_str += "\n m_stichtag_betrag_vl_ehepartner                  =>" + m_stichtag_betrag_vl_ehepartner + "<";
    temp_str += "\n m_stichtag_betrag_sonderzahlung                  =>" + m_stichtag_betrag_sonderzahlung + "<";
    temp_str += "\n m_stichtag_betrag_zinsen                         =>" + m_stichtag_betrag_zinsen + "<";
    temp_str += "\n m_stichtag_betrag_gebeuhren_kfg                  =>" + m_stichtag_betrag_gebeuhren_kfg + "<";
    temp_str += "\n m_stichtag_betrag_kontostand                     =>" + m_stichtag_betrag_kontostand + "<";
    temp_str += "\n m_stichtag_betrag_saldensumme                    =>" + m_stichtag_betrag_saldensumme + "<";
    temp_str += "\n m_stichtag_betrag_soll_guthaben                  =>" + m_stichtag_betrag_soll_guthaben + "<";
    temp_str += "\n ";
    temp_str += "\n m_zuteilung_datum_long                           =>" + m_zuteilung_datum_long + "< " + FkDatumLong.getDatumStringAusLong( m_zuteilung_datum_long );
    temp_str += "\n m_zuteilung_betrag_bewertungszahl                =>" + m_zuteilung_betrag_bewertungszahl + "<";
    temp_str += "\n m_zuteilung_betrag_sparbeitrag                   =>" + m_zuteilung_betrag_sparbeitrag + "<";
    temp_str += "\n m_zuteilung_betrag_vl_antragsteller              =>" + m_zuteilung_betrag_vl_antragsteller + "<";
    temp_str += "\n m_zuteilung_betrag_vl_ehepartner                 =>" + m_zuteilung_betrag_vl_ehepartner + "<";
    temp_str += "\n m_zuteilung_betrag_sonderzahlung                 =>" + m_zuteilung_betrag_sonderzahlung + "<";
    temp_str += "\n m_zuteilung_betrag_zinsen                        =>" + m_zuteilung_betrag_zinsen + "<";
    temp_str += "\n m_zuteilung_betrag_gebeuhren_kfg                 =>" + m_zuteilung_betrag_gebeuhren_kfg + "<";
    temp_str += "\n m_zuteilung_betrag_bonus_zinsen                  =>" + m_zuteilung_betrag_bonus_zinsen + "<";
    temp_str += "\n m_zuteilung_betrag_bonus_gutgeschrieben          =>" + m_zuteilung_betrag_bonus_gutgeschrieben + "<";
    temp_str += "\n m_zuteilung_betrag_guthaben_zinsen               =>" + m_zuteilung_betrag_guthaben_zinsen + "<";
    temp_str += "\n ";
    temp_str += "\n m_tarif_mindestsparzeit_datum                    =>" + m_tarif_mindestsparzeit_datum + "< " + FkDatumLong.getDatumStringAusLong( m_tarif_mindestsparzeit_datum );
    temp_str += "\n m_tarif_mindestsparzeit_anzahl_monate            =>" + m_tarif_mindestsparzeit_anzahl_monate + "<";
    temp_str += "\n ";
    temp_str += "\n m_tarif_mindestguthaben_datum                    =>" + m_tarif_mindestguthaben_datum + "< " + FkDatumLong.getDatumStringAusLong( m_tarif_mindestguthaben_datum );
    temp_str += "\n m_tarif_mindestguthaben_betrag                   =>" + m_tarif_mindestguthaben_betrag + "<";
    temp_str += "\n m_tarif_mindestguthaben_knz_erreicht             =>" + m_tarif_mindestguthaben_knz_erreicht + "<";
    temp_str += "\n ";
    temp_str += "\n m_tarif_bewertungszahl_datum                     =>" + m_tarif_bewertungszahl_datum + "< " + FkDatumLong.getDatumStringAusLong( m_tarif_bewertungszahl_datum );
    temp_str += "\n m_tarif_bewertungszahl_betrag                    =>" + m_tarif_bewertungszahl_betrag + "<";
    temp_str += "\n m_tarif_bewertungszahl_knz_erreicht              =>" + m_tarif_bewertungszahl_knz_erreicht + "<";
    temp_str += "\n ";
    temp_str += "\n m_tarif_zuteilungsvoraussetzungen_datum          =>" + m_tarif_zuteilungsvoraussetzungen_datum + "< " + FkDatumLong.getDatumStringAusLong( m_tarif_zuteilungsvoraussetzungen_datum );
    temp_str += "\n m_tarif_zuteilungsvoraussetzungen_knz_erreicht   =>" + m_tarif_zuteilungsvoraussetzungen_knz_erreicht + "<";
    temp_str += "\n ";
    temp_str += "\n m_tarif_regelsparbeitrag_betrag                  =>" + m_tarif_regelsparbeitrag_betrag + "<";
    temp_str += "\n m_tarif_regelsparbeitrag_promille_bss            =>" + m_tarif_regelsparbeitrag_promille_bss + "<";
    temp_str += "\n ";
    temp_str += "\n m_tarif_mindest_bss                              =>" + m_tarif_mindest_bss + "<";
    temp_str += "\n m_tarif_mindest_bwz                              =>" + m_tarif_mindest_bwz + "<";
    temp_str += "\n m_tarif_promille_abschluss_gebuehr               =>" + m_tarif_promille_abschluss_gebuehr + "<";
    temp_str += "\n m_tarif_proz_guthabenzins                        =>" + m_tarif_proz_guthabenzins + "<";
    temp_str += "\n m_tarif_proz_mindest_sparguthaben                =>" + m_tarif_proz_mindest_sparguthaben + "<";
    temp_str += "\n m_tarif_wartezeit_in_monaten                     =>" + m_tarif_wartezeit_in_monaten + "<";
    temp_str += "\n ";
    temp_str += "\n m_bsd_datum_darlehen_beginn                      =>" + m_bsd_datum_darlehen_beginn + "< " + FkDatumLong.getDatumStringAusLong( m_bsd_datum_darlehen_beginn );
    temp_str += "\n m_bsd_datum_darlehen_ende                        =>" + m_bsd_datum_darlehen_ende + "< " + FkDatumLong.getDatumStringAusLong( m_bsd_datum_darlehen_ende );
    temp_str += "\n m_bsd_tariflicher_darlehensanspruch              =>" + m_bsd_tariflicher_darlehensanspruch + "<";
    temp_str += "\n m_bsd_tariflicher_ztb                            =>" + m_bsd_tariflicher_ztb + "<";
    temp_str += "\n m_bsd_ztb_betrag                                 =>" + m_bsd_ztb_betrag + "<";
    temp_str += "\n m_bsd_ztb_promill_bss                            =>" + m_bsd_ztb_promill_bss + "<";
    temp_str += "\n m_bsd_betrag_anfangsdarlehen                     =>" + m_bsd_betrag_anfangsdarlehen + "<";
    temp_str += "\n m_bsd_betrag_darlehen_mit_mehrzuteilung          =>" + m_bsd_betrag_darlehen_mit_mehrzuteilung + "<";
    temp_str += "\n m_bsd_betrag_mehr_darlehen                       =>" + m_bsd_betrag_mehr_darlehen + "<";
    temp_str += "\n m_bsd_darlehens_gebuehr_betrag                   =>" + m_bsd_darlehens_gebuehr_betrag + "<";
    temp_str += "\n m_bsd_darlehens_gebuehr_datum_belastung          =>" + m_bsd_darlehens_gebuehr_datum_belastung + "<";
    temp_str += "\n m_bsd_gesamt_eigene_einzahlung                   =>" + m_bsd_gesamt_eigene_einzahlung + "<";
    temp_str += "\n m_bsd_gesamt_sondertiglung                       =>" + m_bsd_gesamt_sondertiglung + "<";
    temp_str += "\n m_bsd_gesamt_zinsen                              =>" + m_bsd_gesamt_zinsen + "<";
    temp_str += "\n ";
    temp_str += "\n m_bsd_disagio_datum_belastung                    =>" + m_bsd_disagio_datum_belastung + "< " + FkDatumLong.getDatumStringAusLong( m_bsd_disagio_datum_belastung );
    temp_str += "\n m_bsd_disagio_betrag                             =>" + m_bsd_disagio_betrag + "<";
    temp_str += "\n ";
    temp_str += "\n m_bsd_kto_gebuehr_knz_beruecksichtigt            =>" + m_bsd_kto_gebuehr_knz_beruecksichtigt + "<";
    temp_str += "\n m_bsd_kto_gebuehr_anteilig_anz_monate            =>" + m_bsd_kto_gebuehr_anteilig_anz_monate + "<";
    temp_str += "\n m_bsd_kto_gebuehr_anteilig_betrag                =>" + m_bsd_kto_gebuehr_anteilig_betrag + "<";
    temp_str += "\n m_bsd_kto_gebuehr_laufend_betrag                 =>" + m_bsd_kto_gebuehr_laufend_betrag + "<";
    temp_str += "\n m_bsd_gesamt_kfg                                 =>" + m_bsd_gesamt_kfg + "<";
    temp_str += "\n ";
    temp_str += "\n m_bsd_letzte_rate_datum                          =>" + m_bsd_letzte_rate_datum + "< " + FkDatumLong.getDatumStringAusLong( m_bsd_letzte_rate_datum );
    temp_str += "\n m_bsd_letzte_rate_betrag                         =>" + m_bsd_letzte_rate_betrag + "<";
    temp_str += "\n m_bsd_letzte_rate_zinsbelastung                  =>" + m_bsd_letzte_rate_zinsbelastung + "<";
    temp_str += "\n ";
    temp_str += "\n m_laufzeit_sparphase_gesamt_monate               =>" + m_laufzeit_sparphase_gesamt_monate + "<";
    temp_str += "\n m_laufzeit_sparphase_jahre                       =>" + m_laufzeit_sparphase_jahre + "<";
    temp_str += "\n m_laufzeit_sparphase_monate                      =>" + m_laufzeit_sparphase_monate + "<";
    temp_str += "\n ";
    temp_str += "\n m_laufzeit_bsd_gesamt_monate                     =>" + m_laufzeit_bsd_gesamt_monate + "<";
    temp_str += "\n m_laufzeit_bsd_jahre                             =>" + m_laufzeit_bsd_jahre + "<";
    temp_str += "\n m_laufzeit_bsd_monate                            =>" + m_laufzeit_bsd_monate + "<";
    temp_str += "\n ";
    temp_str += "\n m_laufzeit_vertrag_gesamt_monate                 =>" + m_laufzeit_vertrag_gesamt_monate + "<";
    temp_str += "\n m_laufzeit_vertrag_jahre                         =>" + m_laufzeit_vertrag_jahre + "<";
    temp_str += "\n m_laufzeit_vertrag_monate                        =>" + m_laufzeit_vertrag_monate + "<";
    temp_str += "\n ";
    temp_str += "\n m_millisekunden_gesamt                           =>" + m_millisekunden_gesamt + "<";
    temp_str += "\n m_millisekunden_vorabberechnungen                =>" + m_millisekunden_vorabberechnungen + "<";
    temp_str += "\n m_millisekunden_sparphase_kto_verlauf            =>" + m_millisekunden_sparphase_kto_verlauf + "<";
    temp_str += "\n m_millisekunden_sparphase_berechnung             =>" + m_millisekunden_sparphase_berechnung + "<";
    temp_str += "\n m_millisekunden_darlehensphase                   =>" + m_millisekunden_darlehensphase + "<";

    return temp_str;
  }

  public String toJS()
  {
    String temp_str = "";

    return temp_str;
  }

  public void clear()
  {
    m_millisekunden_gesamt = 0;

    m_millisekunden_vorabberechnungen = 0;

    m_millisekunden_sparphase_kto_verlauf = 0;

    m_millisekunden_sparphase_berechnung = 0;

    m_millisekunden_darlehensphase = 0;

    m_datum_berechnung_beginn_long = 0;

    m_datum_berechnung_ende_long = 0;

    m_stichtag_datum_long = 0;

    m_zuteilung_datum_long = 0;

    m_anzahl_bsv_sparraten_bis_spar_stopp = 0;

    m_anzahl_stichtage_bis_zuteilung = 0;

    m_tarif_mindestsparzeit_anzahl_monate = 0;

    m_tarif_mindestsparzeit_datum = 0;

    m_tarif_mindestguthaben_betrag = null;

    m_tarif_mindestguthaben_datum = 0;

    m_tarif_mindestguthaben_knz_erreicht = false;

    m_tarif_bewertungszahl_betrag = null;

    m_tarif_bewertungszahl_datum = 0;

    m_tarif_bewertungszahl_knz_erreicht = false;

    m_tarif_zuteilungsvoraussetzungen_datum = 0;

    m_tarif_zuteilungsvoraussetzungen_knz_erreicht = false;

    m_tarif_mindest_bss = null;

    m_tarif_mindest_bwz = null;

    m_tarif_promille_abschluss_gebuehr = null;

    m_tarif_proz_guthabenzins = null;

    m_tarif_proz_mindest_sparguthaben = null;

    m_tarif_wartezeit_in_monaten = null;

    m_betrag_bsv_sparbeitrag = null;

    m_datum_sparstopp_sparrate_long = 0;

    m_betrag_bsv_bausparsumme = null;

    m_spar_kto_gebuehr_anteilig_anz_monate = 0;

    m_spar_kto_gebuehr_anteilig_betrag = null;

    m_spar_kto_gebuehr_anteilig_datum_belastung_long = 0;

    m_spar_kto_gebuehr_laufend_betrag = null;

    m_spar_kto_gebuehr_laufend_datum_belastung_long = 0;

    m_stichtag_betrag_bewertungszahl = null;

    m_stichtag_betrag_gebeuhren_kfg = null;

    m_stichtag_betrag_kontostand = null;

    m_stichtag_betrag_saldensumme = null;

    m_stichtag_betrag_soll_guthaben = null;

    m_stichtag_betrag_sonderzahlung = null;

    m_stichtag_betrag_sparbeitrag = null;

    m_stichtag_betrag_vl_antragsteller = null;

    m_stichtag_betrag_vl_ehepartner = null;

    m_stichtag_betrag_zinsen = null;

    m_zuteilung_betrag_bewertungszahl = null;

    m_zuteilung_betrag_bonus_zinsen = null;

    m_zuteilung_betrag_sparbeitrag = null;

    m_zuteilung_betrag_guthaben_zinsen = null;

    m_zuteilung_betrag_gebeuhren_kfg = null;

    m_zuteilung_betrag_sonderzahlung = null;

    m_zuteilung_betrag_vl_antragsteller = null;

    m_zuteilung_betrag_vl_ehepartner = null;

    m_zuteilung_betrag_zinsen = null;

    m_zuteilung_betrag_bonus_gutgeschrieben = null;

    m_bsd_betrag_anfangsdarlehen = null;

    m_bsd_betrag_darlehen_mit_mehrzuteilung = null;

    m_bsd_betrag_mehr_darlehen = null;

    m_bsd_darlehens_gebuehr_betrag = null;

    m_bsd_darlehens_gebuehr_datum_belastung = 0;

    m_bsd_datum_darlehen_beginn = 0;

    m_bsd_datum_darlehen_ende = 0;

    m_tarif_regelsparbeitrag_betrag = null;

    m_tarif_regelsparbeitrag_promille_bss = null;

    m_bsd_tariflicher_darlehensanspruch = null;

    m_bsd_tariflicher_ztb = null;

    m_bsd_disagio_betrag = null;

    m_bsd_disagio_datum_belastung = 0;

    m_bsd_gesamt_eigene_einzahlung = null;

    m_bsd_gesamt_kfg = null;

    m_bsd_gesamt_sondertiglung = null;

    m_bsd_gesamt_zinsen = null;

    m_bsd_letzte_rate_betrag = null;

    m_bsd_letzte_rate_datum = 0;

    m_bsd_letzte_rate_zinsbelastung = null;

    m_bsd_ztb_promill_bss = null;

    m_bsd_ztb_betrag = null;

    m_rechenkern_fehler_nummer = 0;

    m_rechenkern_fehler_text = "";

    m_laufzeit_sparphase_gesamt_monate = 0;

    m_laufzeit_sparphase_jahre = 0;

    m_laufzeit_sparphase_monate = 0;

    m_laufzeit_bsd_gesamt_monate = 0;

    m_laufzeit_bsd_jahre = 0;

    m_laufzeit_bsd_monate = 0;

    m_laufzeit_vertrag_gesamt_monate = 0;

    m_laufzeit_vertrag_jahre = 0;

    m_laufzeit_vertrag_monate = 0;

    m_spar_ansp_betrag_ausstehend = null;

    m_spar_ansp_betrag_gesamt = null;

    m_spar_ansp_betrag_gutgeschrieben = null;

    m_spar_ansp_knz_berechnet = false;

    m_spar_wop_knz_berechnet = false;

    m_spar_wop_betrag_ausstehend = null;

    m_spar_wop_betrag_gebuehr_gesamt = null;

    m_spar_wop_betrag_gesamt = null;

    m_spar_wop_betrag_gutgeschrieben = null;

    m_bsd_betrag_darlehensanspruch = null;

  }

  /**
   * <pre>
   * Liefert einen String entsprechend der Umsetzungstabelle zurueck.
   * 
   *      0 = Vorgabe Sparstopp
   *      1 = Vorgabe Berechnungsziel
   *      2 = Rechenkern Fehler Nummer
   *      3 = Rechenkern Fehler Text
   *      4 = Datum Berechnung Beginn Long
   *      5 = Datum Berechnung Ende Long
   *      6 = Betrag Bsv Sparbeitrag
   *      7 = Datum Sparstopp Sparrate Long
   *      8 = Betrag Bsv Bausparsumme
   *      9 = Spar Kto Gebuehr Anteilig Anz Monate
   *     10 = Spar Kto Gebuehr Anteilig Betrag
   *     11 = Belastung Long
   *     12 = Spar Kto Gebuehr Anteilig Datum Belastung Long
   *     13 = Spar Kto Gebuehr Laufend Betrag
   *     14 = Spar Kto Gebuehr Laufend Datum Belastung Long
   *     15 = Spar Ansp Knz Berechnet
   *     16 = Spar Ansp Betrag Ausstehend
   *     17 = Spar Ansp Betrag Gesamt
   *     18 = Spar Ansp Betrag Gutgeschrieben
   *     19 = Spar Wop Knz Berechnet
   *     20 = Spar Wop Betrag Ausstehend
   *     21 = Spar Wop Betrag Gebuehr Gesamt
   *     22 = Spar Wop Betrag Gesamt
   *     23 = Spar Wop Betrag Gutgeschrieben
   *     24 = Stichtag Datum Long
   *     25 = Stichtag Betrag Bewertungszahl
   *     26 = Stichtag Betrag Sparbeitrag
   *     27 = Stichtag Betrag Vl Antragsteller
   *     28 = Stichtag Betrag Vl Ehepartner
   *     29 = Stichtag Betrag Sonderzahlung
   *     30 = Stichtag Betrag Zinsen
   *     31 = Stichtag Betrag Gebeuhren Kfg
   *     32 = Stichtag Betrag Kontostand
   *     33 = Stichtag Betrag Saldensumme
   *     34 = Stichtag Betrag Soll Guthaben
   *     35 = Zuteilung Datum Long
   *     36 = Zuteilung Betrag Bewertungszahl
   *     37 = Zuteilung Betrag Sparbeitrag
   *     38 = Zuteilung Betrag Vl Antragsteller
   *     39 = Zuteilung Betrag Vl Ehepartner
   *     40 = Zuteilung Betrag Sonderzahlung
   *     41 = Zuteilung Betrag Zinsen
   *     42 = Zuteilung Betrag Gebeuhren Kfg
   *     43 = Zuteilung Betrag Bonus Zinsen
   *     44 = Zuteilung Betrag Bonus Gutgeschrieben
   *     45 = Zuteilung Betrag Guthaben Zinsen
   *     46 = Tarif Mindestsparzeit Datum
   *     47 = Tarif Mindestsparzeit Anzahl Monate
   *     48 = Tarif Mindestguthaben Datum
   *     49 = Tarif Mindestguthaben Betrag
   *     50 = Tarif Mindestguthaben Knz Erreicht
   *     51 = Tarif Bewertungszahl Datum
   *     52 = Tarif Bewertungszahl Betrag
   *     53 = Tarif Bewertungszahl Knz Erreicht
   *     54 = Tarif Zuteilungsvoraussetzungen Datum
   *     55 = Tarif Zuteilungsvoraussetzungen Knz Erreicht
   *     56 = Tarif Regelsparbeitrag Betrag
   *     57 = Tarif Regelsparbeitrag Promille Bss
   *     58 = Tarif Mindest Bss
   *     59 = Tarif Mindest Bwz
   *     60 = Tarif Promille Abschluss Gebuehr
   *     61 = Tarif Proz Guthabenzins
   *     62 = Tarif Proz Mindest Sparguthaben
   *     63 = Tarif Wartezeit In Monaten
   *     64 = Bsd Datum Darlehen Beginn
   *     65 = Bsd Datum Darlehen Ende
   *     66 = Bsd Tariflicher Darlehensanspruch
   *     67 = Bsd Tariflicher Ztb
   *     68 = Bsd Ztb Betrag
   *     69 = Bsd Ztb Promill Bss
   *     70 = Bsd Betrag Anfangsdarlehen
   *     71 = Bsd Betrag Darlehen Mit Mehrzuteilung
   *     72 = Bsd Betrag Mehr Darlehen
   *     73 = Bsd Darlehens Gebuehr Betrag
   *     74 = Bsd Darlehens Gebuehr Datum Belastung
   *     75 = Bsd Disagio Betrag
   *     76 = Bsd Disagio Datum Belastung
   *     77 = Bsd Gesamt Eigene Einzahlung
   *     78 = Bsd Gesamt Kfg
   *     79 = Bsd Gesamt Sondertiglung
   *     80 = Bsd Gesamt Zinsen
   *     81 = Bsd Letzte Rate Betrag
   *     82 = Bsd Letzte Rate Datum
   *     83 = Bsd Letzte Rate Zinsbelastung
   *     84 = Laufzeit Sparphase Gesamt Monate
   *     85 = Laufzeit Sparphase Jahre
   *     86 = Laufzeit Sparphase Monate
   *     87 = Laufzeit Bsd Gesamt Monate
   *     88 = Laufzeit Bsd Jahre
   *     89 = Laufzeit Bsd Monate
   *     90 = Laufzeit Vertrag Gesamt Monate
   *     91 = Laufzeit Vertrag Jahre
   *     92 = Laufzeit Vertrag Monate
   *     93 = Millisekunden Gesamt
   *     94 = Millisekunden Vorabberechnungen
   *     95 = Millisekunden Sparphase Kto Verlauf
   *     96 = Millisekunden Sparphase Berechnung
   *     97 = Millisekunden Darlehensphase
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
      case -1 :
        return "";

      case 0 :
        return getVorgabeSparstopp();

      case 1 :
        return getVorgabeBerechnungsziel();

      case 2 :
        return "" + getRechenkernFehlerNummer();

      case 3 :
        return getRechenkernFehlerText();

      case 4 :
        return "" + getDatumBerechnungBeginn();

      case 5 :
        return "" + getDatumBerechnungEnde();

      case 6 :
        return FkZahl.toPlainString( getBetragBsvSparbeitrag() );

      case 7 :
        return "" + getDatumSparstoppSparrate();

      case 8 :
        return FkZahl.toPlainString( getBetragBsvBausparsumme() );

      case 9 :
        return "" + getSparKtoGebuehrAnteiligAnzMonate();

      case 10 :
        return FkZahl.toPlainString( getSparKtoGebuehrAnteiligBetrag() );

      case 11 :
        return "getBelastung()";

      case 12 :
        return "" + getSparKtoGebuehrAnteiligDatumBelastung();

      case 13 :
        return FkZahl.toPlainString( getSparKtoGebuehrLaufendBetrag() );

      case 14 :
        return "" + getSparKtoGebuehrLaufendDatumBelastung();

      case 15 :
        return "" + getSparAnspKnzBerechnet();

      case 16 :
        return FkZahl.toPlainString( getSparAnspBetragAusstehend() );

      case 17 :
        return FkZahl.toPlainString( getSparAnspBetragGesamt() );

      case 18 :
        return FkZahl.toPlainString( getSparAnspBetragGutgeschrieben() );

      case 19 :
        return "" + getSparWopKnzBerechnet();

      case 20 :
        return FkZahl.toPlainString( getSparWopBetragAusstehend() );

      case 21 :
        return FkZahl.toPlainString( getSparWopBetragGebuehrGesamt() );

      case 22 :
        return FkZahl.toPlainString( getSparWopBetragGesamt() );

      case 23 :
        return FkZahl.toPlainString( getSparWopBetragGutgeschrieben() );

      case 24 :
        return "" + getStichtagDatum();

      case 25 :
        return FkZahl.toPlainString( getStichtagBetragBewertungszahl() );

      case 26 :
        return FkZahl.toPlainString( getStichtagBetragSparbeitrag() );

      case 27 :
        return FkZahl.toPlainString( getStichtagBetragVlAntragsteller() );

      case 28 :
        return FkZahl.toPlainString( getStichtagBetragVlEhepartner() );

      case 29 :
        return FkZahl.toPlainString( getStichtagBetragSonderzahlung() );

      case 30 :
        return FkZahl.toPlainString( getStichtagBetragZinsen() );

      case 31 :
        return FkZahl.toPlainString( getStichtagBetragGebeuhrenKfg() );

      case 32 :
        return FkZahl.toPlainString( getStichtagBetragKontostand() );

      case 33 :
        return FkZahl.toPlainString( getStichtagBetragSaldensumme() );

      case 34 :
        return FkZahl.toPlainString( getStichtagBetragSollGuthaben() );

      case 35 :
        return "" + getZuteilungDatum();

      case 36 :
        return FkZahl.toPlainString( getZuteilungBetragBewertungszahl() );

      case 37 :
        return FkZahl.toPlainString( getZuteilungBetragSparbeitrag() );

      case 38 :
        return FkZahl.toPlainString( getZuteilungBetragVlAntragsteller() );

      case 39 :
        return FkZahl.toPlainString( getZuteilungBetragVlEhepartner() );

      case 40 :
        return FkZahl.toPlainString( getZuteilungBetragSonderzahlung() );

      case 41 :
        return FkZahl.toPlainString( getZuteilungBetragZinsen() );

      case 42 :
        return "-----x";//FkBigDecimal.toPlainString( getZuteilungBetragGebeuhrenKfg() );

      case 43 :
        return FkZahl.toPlainString( getZuteilungBetragBonusZinsen() );

      case 44 :
        return FkZahl.toPlainString( getZuteilungBetragBonusGutgeschrieben() );

      case 45 :
        return FkZahl.toPlainString( getZuteilungBetragGuthabenZinsen() );

      case 46 :
        return "" + getTarifMindestsparzeitDatum();

      case 47 :
        return "" + getTarifMindestsparzeitAnzahlMonate();

      case 48 :
        return "" + getTarifMindestguthabenDatum();

      case 49 :
        return FkZahl.toPlainString( getTarifMindestguthabenBetrag() );

      case 50 :
        return "" + getTarifMindestguthabenKnzErreicht();

      case 51 :
        return "" + getTarifBewertungszahlDatum();

      case 52 :
        return FkZahl.toPlainString( getTarifBewertungszahlBetrag() );

      case 53 :
        return "" + getTarifBewertungszahlKnzErreicht();

      case 54 :
        return "" + getTarifZuteilungsvoraussetzungenDatum();

      case 55 :
        return "" + getTarifZuteilungsvoraussetzungenKnzErreicht();

      case 56 :
        return FkZahl.toPlainString( getTarifRegelsparbeitragBetrag() );

      case 57 :
        return FkZahl.toPlainString( getTarifRegelsparbeitragPromilleBss() );

      case 58 :
        return FkZahl.toPlainString( getTarifMindestBss() );

      case 59 :
        return FkZahl.toPlainString( getTarifMindestBwz() );

      case 60 :
        return FkZahl.toPlainString( getTarifPromilleAbschlussGebuehr() );

      case 61 :
        return FkZahl.toPlainString( getTarifProzGuthabenzins() );

      case 62 :
        return FkZahl.toPlainString( getTarifProzMindestSparguthaben() );

      case 63 :
        return FkZahl.toPlainString( getTarifWartezeitInMonaten() );

      case 64 :
        return "" + getBsdDatumDarlehenBeginn();

      case 65 :
        return "" + getBsdDatumDarlehenEnde();

      case 66 :
        return FkZahl.toPlainString( getBsdTariflicherDarlehensanspruch() );

      case 67 :
        return FkZahl.toPlainString( getBsdTariflicherZtb() );

      case 68 :
        return FkZahl.toPlainString( getBsdZtbBetrag() );

      case 69 :
        return FkZahl.toPlainString( getBsdZtbPromillBss() );

      case 70 :
        return FkZahl.toPlainString( getBsdBetragAnfangsdarlehen() );

      case 71 :
        return FkZahl.toPlainString( getBsdBetragDarlehenMitMehrzuteilung() );

      case 72 :
        return FkZahl.toPlainString( getBsdBetragMehrDarlehen() );

      case 73 :
        return FkZahl.toPlainString( getBsdDarlehensGebuehrBetrag() );

      case 74 :
        return "" + getBsdDarlehensGebuehrDatumBelastung();

      case 75 :
        return FkZahl.toPlainString( getBsdDisagioBetrag() );

      case 76 :
        return "" + getBsdDisagioDatumBelastung();

      case 77 :
        return FkZahl.toPlainString( getBsdGesamtEigeneEinzahlung() );

      case 78 :
        return FkZahl.toPlainString( getBsdGesamtKfg() );

      case 79 :
        return FkZahl.toPlainString( getBsdGesamtSondertiglung() );

      case 80 :
        return FkZahl.toPlainString( getBsdGesamtZinsen() );

      case 81 :
        return FkZahl.toPlainString( getBsdLetzteRateBetrag() );

      case 82 :
        return "" + getBsdLetzteRateDatum();

      case 83 :
        return FkZahl.toPlainString( getBsdLetzteRateZinsbelastung() );

      case 84 :
        return "" + getLaufzeitSparphaseGesamtMonate();

      case 85 :
        return "" + getLaufzeitSparphaseJahre();

      case 86 :
        return "" + getLaufzeitSparphaseMonate();

      case 87 :
        return "" + getLaufzeitBsdGesamtMonate();

      case 88 :
        return "" + getLaufzeitBsdJahre();

      case 89 :
        return "" + getLaufzeitBsdMonate();

      case 90 :
        return "" + getLaufzeitVertragGesamtMonate();

      case 91 :
        return "" + getLaufzeitVertragJahre();

      case 92 :
        return "" + getLaufzeitVertragMonate();

      case 93 :
        return "" + getMillisekundenGesamt();

      case 94 :
        return "" + getMillisekundenVorabberechnungen();

      case 95 :
        return "" + getMillisekundenSparphaseKtoVerlauf();

      case 96 :
        return "" + getMillisekundenSparphaseBerechnung();

      case 97 :
        return "" + getMillisekundenDarlehensphase();
    }

    return null;
  }
}