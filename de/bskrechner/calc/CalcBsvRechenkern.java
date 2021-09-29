package de.bskrechner.calc;

import java.math.BigDecimal;

import de.bskrechner.util.FkDatei;
import de.bskrechner.util.FkDatumLong;
import de.bskrechner.util.FkString;
import de.bskrechner.util.FkSystem;
import de.bskrechner.util.FkZahl;
import de.bskrechner.util.NewLineStringBuffer;

public class CalcBsvRechenkern
{
  /* 
   * 
   * Offen: -Laufzeiten kleiner als 7 Jahre Foerderung darf nicht ausgezahlt werden
   *         wop_datum_ende_bindefrist = Uebergabemoeglichkeit / Pruefung bei Auszahlung
   *         ansp_datum_ende
   * 
   * August   2013 Rudimentaere Anfaenge des Rechenkernes in Visual-Basic 6
   *               Erstellung des Kontoverlaufes, erste grobe Iterationsschleife
   *               Grobe Testfunktionen fuer die Berechnungsziele
   * 
   *          2015 Grundversion der Iterationsschleife steht
   *               
   *               Integration der Auswertungen fuer die Berechnungsziele in die Iterationsschleife
   * 
   * Februar  2016 Berechnung Bauspardarlehen, Sonderzahlungen und Sondertilgungen als Array
   * 
   * Maerz    2016 Foerderung ANSP und Wop, Berechnung Sparrate/Bausparsumme/Laufzeit ueberarbeitet
   * 
   * Dezember 2018 Portierung von Visual-Basic nach Java
   *               Vorabberechnungen fuer das Berechnungsziel Laufzeit ueberarbeitet
   *               Berechnungen fuer das Berechnungsziel Sparrate ueberarbeitet
   * 
   * Februar  2020 Kontofuehrungsgebuehr Sparphase deaktivierbar gemacht, 
   *               Ergebnisklasse: Kontofuehrungsgebuehrberuecksichtigung, Vertragsart
   * 
   * 
   * Desweiteren: Rechtsschreibfehler in den Kommentaren erhoehen die Aufmerksamkeit des Lesers.
   * 
   */

  private static final boolean B_FALSE                                              = false;

  private static final boolean B_TRUE                                               = true;

  private static final int     MONAT_JANUAR                                         = 1;

  private static final int     MONAT_DEZEMBER                                       = 12;

  private static final int     TAG_1                                                = 1;

  private static final double  WOP_MINDEST_EINZAHLUNG                               = 50.0;

  private static final double  WOP_MAX_FORDERBETRAG_LEDIG                           = 512.0;

  private static final double  WOP_MAX_FORDERBETRAG_VERHEIRATET                     = 1024.0;

  private static final double  WOP_PROZENT_FOERDERUNG                               = 8.8;

  private static final double  ANSP_PROZENT_FOERDERUNG                              = 9.0;

  private static final double  ANSP_BETRAG_MINDEST_VL_EINZAHLUNG                    = 0.0;

  private static final double  ANSP_BETRAG_MAXIMAL_VL_EINZAHLUNG                    = 470.0;

  private static final double  MULTIPLIKATOR_DURCH_100                              = 0.01;

  private static final double  MAX_ITERATIONEN                                      = 50.0;

  private static final int     INDEX_0                                              = 0;

  private static final int     INDEX_1                                              = 1;

  private static final int     INDEX_NICHT_VORHANDEN                                = -1;

  private static final int     ITERATION_1                                          = 1;

  private static final int     MAX_ITERATION_CALC_RATE                              = 40;

  private static final int     MAX_ITERATION_CALC_RATE_BWZ                          = 40;

  private static final int     MAX_ITERATION_CALC_BSS                               = 40;

  private static final int     BERECHNUNG_FEHLER_FREI                               = 0;

  private static final int     FEHLER_KEINE_SPAR_RATE_BIS_SPAR_STOPP                = 1;

  private static final int     FEHLER_BSS_UNTERSCHREITET_MINDEST_BSS                = 2;

  private static final int     FEHLER_ZU_VIELE_ITERATIONEN                          = 3;

  private static final int     FEHLER_MINDEST_SPARZEIT_UNTERSCHRITTEN               = 4;

  private static final int     FEHLER_START_ELEMENT_SPAR_NICHT_GEFUNDEN             = 5;

  private static final int     FEHLER_START_ELEMENT_BSD_NICHT_GEFUNDEN              = 5;

  private static final int     FEHLER_ZUTEILUNG_WIRD_NICHT_ERREICHT                 = 6;

  private static final int     FEHLER_BWZ_STAFFEL_KEIN_WERT_GEFUNDEN                = 7;

  private static final int     FEHLER_ERMITTLUNG_ZINS_UND_TILIGUNGSBEITRAG          = 8;

  private static final int     FEHLER_ZTB_VORGABE_PROMILLE_WERT_UNGUELTIG           = 9;

  private static final int     FEHLER_ZTB_VORGABE_BETRAGSANGABE_UNGUELTIG           = 10;

  private static final int     FEHLER_ZTB_VORGABE_BETRAGSANGABE_PROMILLE_W          = 11;

  private static final int     FEHLER_VORGABE_DATEN_UNVOLLSTAENDIG                  = 12;

  private static final double  JAHRES_FAKTOR_365                                    = 0.00273972602739726;// = 1 / 365 =>0.00273972602739726027397260273972

  private static final double  JAHRES_FAKTOR_366                                    = 0.00273224043715847;// = 1 / 366

  private static final double  JAHRES_FAKTOR_12_MONATE                              = 0.08333333333333334;// = 1 / 12

  private static final double  MEHRZUTEILUNG_UNTERGRENZE_PROZENT                    = 10.0;

  private static final double  MEHRZUTEILUNG_OBERGRENZE_PROZENT                     = 350.0;

  private static final double  ZTB_VORGABE_MINDEST_PROMILL_BETRAG                   = 1.0;

  private static final double  ZTB_VORGABE_MAXIMAL_PROMILL_BETRAG                   = 40.0;

  /*
   * Der Rechenkern benutzt Variablen fuer die konstanten Werte. Diese Konstantenwerte werden 
   * in der Funktion "initRkKonstantenClcBss" in die entsprechenden Variablen gesetzt.
   *
   * Die hier aktuell genutzten Werte fuer die Rechenkernvariablen, werden hier als Konstanten definiert.
   *
   * Somit koennen diese Konstanten von anderen Benutzern dieses Rechenkerns diese Werte veraendern.
   */

  private static final int     RK_MAX_ELEMENTE                                      = 32123;

  private static final int     RK_ANZAHL_JAHRE_CALC_LAUFZ_ZUKUNFT                   = 120;

  private static final int     RK_B_ART_NEUVERTRAG                                  = 1;

  private static final int     RK_B_ART_BESTANDSVERTRAG                             = 0;

  private static final int     KNZ_ELEMENT_LOESCHEN                                 = 1;

  private static final int     KNZ_ELEMENT_BEHALTEN                                 = 0;

  private static final int     RHYTHMUS_MONATLICH                                   = 1;

  private static final int     RHYTHMUS_JAEHRLICH                                   = 2;

  private static final int     RHYTHMUS_HALB_JAEHRLICH                              = 3;

  private static final int     RHYTHMUS_QUARTIERLICH                                = 4;

  private static final int     RHYTHMUS_EINMALIG                                    = 5;

  private static final int     KTO_ART_SPAR_PHASE_BEGINN                            = 0;

  private static final int     KTO_ART_AG_BELASTUNG                                 = 100;

  private static final int     KTO_ART_AG_BEGLEICHUNG_SEPARAT                       = 101;

  private static final int     KTO_ART_KTO_GEBUEHR_ANTEILIG                         = 110;

  private static final int     KTO_ART_KTO_GEBUEHR_SPAR_LAUFEND                     = 120;

  private static final int     KTO_ART_GEBUEHR_WOP_LAUFEND                          = 130;

  private static final int     KTO_ART_BSV_SPARBEITRAG                              = 200;

  private static final int     KTO_ART_VL_ANTRAGSTELLER                             = 210;

  private static final int     KTO_ART_VL_EHEPARTNER                                = 220;

  private static final int     KTO_ART_SONDERZAHLUNG                                = 230;

  private static final int     KTO_ART_GUTSCHRIFT_BONUS_ZUTEILUNG                   = 419;

  private static final int     KTO_ART_GUTSCHRIFT_ZINS_JAEHRLICH                    = 420;

  private static final int     KTO_ART_GUTSCHRIFT_ZINS_ZUTEILUNG                    = 430;

  private static final int     KTO_ART_GUTSCHRIFT_ANSP_EINMALIG                     = 480;

  private static final int     KTO_ART_GUTSCHRIFT_ANSP_LAUFEND                      = 485;

  /*
   * Wop-Auszahlung bei Zuteilung
   * Ist das Zuteilungsdatum gleichzeitig das Jahresende und es soll WOP beruecksichtigt 
   * werden, muessen erst die Zinsen fuer das vergangene Jahr berechnet und gutgeschrieben 
   * werden, bevor auf dem sich dann ergebenden Kontostand die letzte Foerderung berechnet wird. 
   * (Es wuerden die Zinsen fehlen, wenn das Zinselement nach dem WOP-Auszahlung Element kommen wuerde).
   *
   * ... gleiches auch fuer die Auszahlung bei der jungen Leute Regelung
   */
  private static final int     KTO_ART_GUTSCHRIFT_WOP_JUNGE_LEUTE_REGELUNG          = 490;

  private static final int     KTO_ART_GUTSCHRIFT_WOP_ZUTEILUNG                     = 491;

  private static final int     KTO_ART_BEWERTUNGSSTICHTAG                           = 498;

  private static final int     KTO_ART_SPAR_PHASE_ZUTEILUNG                         = 499;

  private static final int     KTO_ART_DARLEHEN_PHASE_BEGINN                        = 500;

  private static final int     KTO_ART_DARLEHENS_GEBUEHR                            = 510;

  private static final int     KTO_ART_DARLEHEN_DISAGIO                             = 520;

  private static final int     KTO_ART_DARLEHEN_KTO_GEBUEHR_LAUFEND                 = 530;

  private static final int     KTO_ART_SONDERTILGUNG                                = 540;

  private static final int     KTO_ART_ZTB_EINZAHLUNG                               = 550;

  private static final int     KTO_ART_ZTB_ZINSBELASTUNG                            = 560;

  private static final int     DARL_ANSPRUCH_VARIABEL                               = 1;

  private static final int     DARL_ANSPRUCH_FEST                                   = 2;

  private static final int     AG_ART_SEPARATE_ZAHLUNG                              = 1;

  private static final int     AG_ART_MIT_ZAHLUNGEN_VERRECHNEN                      = 0;

  private static final int     BZ_LAUFZEIT                                          = 1;

  private static final int     BZ_BAUSPARSUMME                                      = 2;

  private static final int     BZ_SPARRATE_SUCHE_MIN_GUTHABEN_RATE                  = 3;

  private static final int     BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_POSITIV          = 9;

  private static final int     BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_NEGATIV          = 10;

  private static final int     BZ_SPARRATE_BERECHNUNGSSCHLEIFE_3                    = 11;

  private static final int     BZ_VERTRAGS_SIMULATION                               = 4;

  private static final int     BZ_FEHLER_BERECHNUNGSZIEL_NICHT_ANGEGEBEN            = 5;

  private static final int     CALC_LAUFZEIT_VORGABE_MINDEST_LAUFZEIT_IN_MONATEN    = 120;

  private static final int     CALC_LAUFZEIT_VORGABE_MAXIMALE_LAUFZEIT_IN_MONATEN   = 480;                // = 40 Jahre

  private static final double  CALC_LAUFZEIT_VORGABE_MONATLICHE_SPARLEISTUNG_FAKTOR = 0.90;

  private static final double  CALC_LAUFZEIT_BSD_FAKTOR_ZTB_ANRECHNUNG              = 0.75;

  private static final int     WERT_NICHT_VORHANDEN                                 = 0;

  private static final int     SPAR_STOPP_ZUTEILUNG                                 = 1;

  private static final int     SPAR_STOPP_MI_GUTHABEN                               = 2;

  private static final int     SPAR_STOPP_MINDEST_ZTV                               = 4;

  private static final int     FEHLER_SPAR_STOPP_NICHT_ANGEGEBEN                    = 5;

  private static final double  BETRAG_WOP_GEBUEHR                                   = 1.53;

  /*
   * Im Rechenkern werden immer wieder verschiedene Zahlen benutzt. 
   * Diese Zahlen werden hier als Konstanten definiert. Das sorgt 
   * dafuer, dass diese Zahlen eine Bedeutung bekommen und somit der 
   * Quelltext lesbarer/beschreibender ist.
   */
  private static final double  PROZENT_0                                            = 0.0;

  private static final long    ANZAHL_JAHRE_0                                       = 0;

  private static final long    ANZAHL_JAHRE_1                                       = 1;

  private static final long    ANZAHL_JAHRE_7                                       = 7;

  private static final long    ANZAHL_TAGE_MINUS_1                                  = -1;

  private static final long    ANZAHL_MONATE_0                                      = 0;

  private static final long    ANZAHL_MONATE_1                                      = 1;

  private static final long    ANZAHL_MONATE_3                                      = 3;

  private static final long    ANZAHL_MONATE_6                                      = 6;

  private static final long    ANZAHL_MONATE_12                                     = 12;

  private static final long    ANZAHL_TAGE_0                                        = 0;

  private static final long    ANZAHL_ELEMENTE_UNBEGRENZT                           = -1;

  private static final long    ANZAHL_ELEMENTE_1                                    = 1;

  private static final long    ANZAHL_0                                             = 0;

  private static final long    ANZAHL_1                                             = 1;

  private static final long    DATUM_0                                              = 0;

  private static final long    DATUM_TAG_MONAT_ERSTER_JANUAR                        = 101;

  private static final long    DAUER_0                                              = 0;

  private static final long    MILLISEKUNDEN_0                                      = 0;

  private static final long    KNZ_TAG_MONATSANFANG                                 = -1;

  private static final long    KNZ_TAG_MONATSENDE                                   = 1;

  private static final long    KNZ_TAG_UNVERAENDERT                                 = 0;

  private static final double  BETRAG_MINUS_1                                       = -1.0;

  private static final double  BETRAG_NICHT_GESETZT                                 = -1.0;

  private static final double  BETRAG_CALC_RATE_DIFF_MAX_GRENZE                     = 0.005;

  private static final double  BETRAG_0                                             = 0.0;

  private static final double  BETRAG_1                                             = 1.0;

  private static final double  BETRAG_09                                            = 0.9;

  private static final double  BETRAG_9                                             = 9;

  private static final double  BETRAG_20                                            = 20.0;

  private static final double  BETRAG_400                                           = 400.0;

  private static final double  BETRAG_470                                           = 470.0;

  private static final double  BETRAG_1000                                          = 1000.0;

  private static final double  BETRAG_100                                           = 100.0;

  private boolean              m_debug_knz_akt_element                              = B_FALSE;

  private boolean              m_debug_knz_bwz                                      = B_FALSE;            // Kennzeichen: Debugausgaben der Bewertungszahlberechnung

  private boolean              m_debug_knz_zins_berechnung_bsd                      = B_FALSE;            // Kennzeichen: Debugausgaben Zinsberechnung Darlehensphase

  private boolean              m_debug_knz_zins_berechnung_spar                     = B_FALSE;            // Kennzeichen: Debugausgaben Zinsberechnung Sparphase

  private boolean              m_debug_knz_ansp_berechnung                          = B_FALSE;

  private boolean              m_debug_knz_berechne_bsd                             = B_FALSE;            // Kennzeichen, ob das Bauspardarelehen berechnet werden soll (Schalter fuer Debug-Sessions)

  private boolean              m_debug_knz_berechnungsziel_schritte                 = B_FALSE;

  private boolean              m_debug_knz_wop_berechnung                           = B_FALSE;

  private boolean              m_debug_knz_kto_verlauf_aufbau                       = B_FALSE;

  private boolean              m_debug_knz_kto_verlauf_rohform                      = B_FALSE;

  private long                 m_rk_datum_minimal                                   = 0;

  private long                 m_rk_datum_maximal                                   = 0;

  private long                 m_rk_max_elemente                                    = 0;

  private long                 m_rk_anzahl_jahre_zahlungs_ende                      = 0;                  // Maximale Anzahl der Jahre, welche ein Vertrag laufen soll

  private long                 m_rk_anzahl_monate_zahlungs_ende                     = 0;

  private long                 m_rk_rhythmus_monatlich                              = 0;

  private long                 m_rk_rhythmus_jaehrlich                              = 0;

  private long                 m_rk_rhythmus_halb_jaehrlich                         = 0;

  private long                 m_rk_rhythmus_quartierlich                           = 0;

  private long                 m_rk_rhythmus_einmalig                               = 0;

  private boolean              m_bsd_knz_darlehensbetrag_negativ_ausweisen          = B_FALSE;

  private NewLineStringBuffer  m_log_string                                         = null;

  /**
   * <pre>
   * Gibt den Stringbuffer fuer den Log-String zurueck. 
   * 
   * Ist der Stringbuffer noch nicht initialisiert worden, wird dieses gemacht.
   * </pre>
   * 
   * @return den Stringbuffer fuer den Log-String der Berechnung
   */
  private NewLineStringBuffer getLogString()
  {
    /*
     * Pruefung: Log-Instanz gleich null ?
     * 
     * Ist die Variable fuer den Stringbuffer des Log-Strings noch null, 
     * wird eine neue Instanz von "NewLineStringbuffer" erstellt.
     */
    if ( m_log_string == null )
    {
      m_log_string = new NewLineStringBuffer();
    }

    return m_log_string;
  }

  /**
   * Setzt den Log-String auf null
   */
  public void resetLogString()
  {
    /*
     * Ist die Instanz des Log-Strings gesetzt, wird dort die 
     * Funktion "clear" aufgerufen. 
     */
    if ( m_log_string != null )
    {
      m_log_string.clear();
    }

    m_log_string = null;
  }

  /**
   * <pre>
   * Initialisierung der Rechenkernvariablen
   * 
   * Um den Rechenkern noch ein wenig mehr variabler halten zu koennen, 
   * werden die Rechenkernkonstanten nochmals in lokalen Variablen kopiert. 
   * </pre>
   */
  private void initRkKonstantenClcBss()
  {
    /* 
     * http://de.wikipedia.org/wiki/Gregorianischer_Kalender
     * Der Gregorianische Kalender (benannt nach Papst Gregor XIII.) entstand
     * Ende des 16. Jahrhunderts durch Reformieren des Julianischen Kalenders
     * und wurde 1582 mit der paepstlichen Bulle Inter gravissimas verordnet.
     * 
     * Daraus ergibt sich ein sinnvolles min-Datum vom 1.1.1583.
     * 1583 ist das erste volle Jahr im Gregorianischen Kalender.
     * Ueber die Sinnhaftigkeit eines Datums in der Vergangenheit laesst sich streiten. 
     * Es musste ein Minimal-Datum geben und das ist es.
     */
    m_rk_datum_minimal = FkDatumLong.RK_MIN_TERMIN;

    /* 
     * Die gesamte Datumsberechnung basiert auf dem Datumsformat JJJJMMTT.
     * 
     * Daraus ergibt sich ein Max-Datum von 31.12.9999
     */
    m_rk_datum_maximal = FkDatumLong.RK_MAX_TERMIN;

    /* 
     * Die maximale Anzahl von Elementen im Kontoverlauf setzen
     */
    m_rk_max_elemente = RK_MAX_ELEMENTE;

    /* 
     * Setzen der Werte fuer die Rhythmus-Konstanten
     */
    m_rk_rhythmus_monatlich = RHYTHMUS_MONATLICH;
    m_rk_rhythmus_jaehrlich = RHYTHMUS_JAEHRLICH;
    m_rk_rhythmus_halb_jaehrlich = RHYTHMUS_HALB_JAEHRLICH;
    m_rk_rhythmus_quartierlich = RHYTHMUS_QUARTIERLICH;
    m_rk_rhythmus_einmalig = RHYTHMUS_EINMALIG;

    m_rk_anzahl_jahre_zahlungs_ende = RK_ANZAHL_JAHRE_CALC_LAUFZ_ZUKUNFT;

    m_rk_anzahl_monate_zahlungs_ende = m_rk_anzahl_jahre_zahlungs_ende * ANZAHL_MONATE_12;
  }


  public CalcBsvErgebnis calcBausparvertrag( CalcVorgabenBsv pCalcBsvEingabe, CalcVorgabenTarif pCalcTarifEingabe )
  {
    long e_millisekunden_start_funktion = System.currentTimeMillis();
    long e_millisekunden_ende_vorabberechnungen = MILLISEKUNDEN_0;
    long e_millisekunden_ende_sparphase_kto_verlauf = MILLISEKUNDEN_0;
    long e_millisekunden_ende_sparphase_calc = MILLISEKUNDEN_0;
    long e_millisekunden_ende_darlehensphase = MILLISEKUNDEN_0;

    long c_millisekunden_ende = MILLISEKUNDEN_0;
    long c_millisekunden_start_1 = MILLISEKUNDEN_0;

    int e_rechenkern_fehler_nummer = BERECHNUNG_FEHLER_FREI;

    m_log_string = null;

    String ergebnis_str = "";
    String my_chr13 = "\n";

    double tarif_bsd_proz_darl_gebuehr = PROZENT_0;

    double tarif_bsd_darl_gebuehr_prozent = PROZENT_0;
    double tarif_bsd_proz_disagio = PROZENT_0;
    double tarif_bsd_zinssatz = PROZENT_0;
    double tarif_bwz_faktor = BETRAG_0;
    int tarif_darlehensanspruch_art = -1;
    double tarif_darlehensanspruch_proz = PROZENT_0;
    double tarif_kto_gebuehren_jahr = BETRAG_0;
    double tarif_mindest_bss = BETRAG_0;
    double tarif_mindest_bwz = 0.00;
    long tarif_mindestsparzeit_monate = ANZAHL_MONATE_0;
    double tarif_promille_abschluss_gebuehr = PROZENT_0;
    double tarif_proz_bonus_zins = PROZENT_0;
    double tarif_proz_guthabenzins = PROZENT_0;
    double tarif_proz_mindest_sparguthaben = PROZENT_0;
    double tarif_regel_sparbeitrag = BETRAG_0;
    long tarif_wartezeit_in_monaten = ANZAHL_MONATE_0;
    double tarif_kto_gebuehr_sparen_pro_jahr = BETRAG_0;
    double tarif_kto_gebuehr_tilgen_pro_jahr = BETRAG_0;
    boolean tarif_kto_gebuehr_sparen_knz_ein = B_FALSE;
    boolean tarif_kto_gebuehr_tilgen_knz_ein = B_FALSE;

    double tarif_mehrzuteilung_untergrenze_prozent = 40.0;

    double tarif_mehrzuteilung_obergrenze_prozent = 250.0;

    long c_anzahl_monate_zwischen_stichtagen = ANZAHL_MONATE_3;

    initRkKonstantenClcBss();

    tarif_kto_gebuehr_sparen_pro_jahr = pCalcTarifEingabe.getKtoGebuehrSparenProJahr();
    tarif_kto_gebuehr_tilgen_pro_jahr = pCalcTarifEingabe.getKtoGebuehrTilgenProJahr();
    tarif_kto_gebuehr_sparen_knz_ein = pCalcTarifEingabe.getKtoGebuehrSparenKnzEin();
    tarif_kto_gebuehr_tilgen_knz_ein = pCalcTarifEingabe.getKtoGebuehrTilgenKnzEin();

    /* 
     * Tarifdatenuebernahme
     * Die Vorgaben aus dem Parameter "pCalcTarifEingabe" werden in die lokalen Variablen 
     * des Rechenkerns uebertragen und anschliessend auf Plausibilitaet geprueft. 
     *
     * Prozentangaben, welche kleiner als 0 sind, werden zu 0.
     */
    boolean tarif_bsd_darl_gebuehr_knz_ein = B_FALSE;

    tarif_mindest_bss = pCalcTarifEingabe.getMindestBausparsumme();
    tarif_wartezeit_in_monaten = pCalcTarifEingabe.getWartezeitInMonaten();
    tarif_proz_guthabenzins = pCalcTarifEingabe.getProzentGuthabenzins();
    tarif_proz_mindest_sparguthaben = pCalcTarifEingabe.getProzentMindestSparguthaben();
    tarif_promille_abschluss_gebuehr = pCalcTarifEingabe.getPromilleAbschlussgebuehr();
    tarif_mindest_bwz = pCalcTarifEingabe.getMindestBewertungszahl();
    tarif_bwz_faktor = pCalcTarifEingabe.getBewertungszahlFaktor1();
    tarif_darlehensanspruch_proz = pCalcTarifEingabe.getDarlehensanspruchProzent();
    tarif_regel_sparbeitrag = pCalcTarifEingabe.getRegelSparbeitrag();
    tarif_bsd_zinssatz = pCalcTarifEingabe.getBsdZinssatz();
    tarif_proz_bonus_zins = pCalcTarifEingabe.getProzentBonuszins();
    tarif_bsd_darl_gebuehr_prozent = pCalcTarifEingabe.getBsdProzDarlehensgebuehr();
    tarif_bsd_proz_disagio = pCalcTarifEingabe.getBsdProzDisagio();

    if ( pCalcTarifEingabe.istDarlehensanspruchArtFest() )
    {
      tarif_darlehensanspruch_art = DARL_ANSPRUCH_FEST;
    }
    else
    {
      tarif_darlehensanspruch_art = DARL_ANSPRUCH_VARIABEL;
    }

    if ( tarif_darlehensanspruch_proz < PROZENT_0 )
    {
      tarif_darlehensanspruch_proz = PROZENT_0;
    }

    if ( tarif_bsd_darl_gebuehr_prozent < PROZENT_0 )
    {
      tarif_bsd_darl_gebuehr_prozent = PROZENT_0;
    }

    if ( tarif_bsd_proz_disagio < PROZENT_0 )
    {
      tarif_bsd_proz_disagio = PROZENT_0;
    }

    if ( tarif_proz_guthabenzins < PROZENT_0 )
    {
      tarif_proz_guthabenzins = PROZENT_0;
    }

    if ( tarif_bwz_faktor < BETRAG_0 )
    {
      tarif_bwz_faktor = BETRAG_0;
    }

    if ( tarif_proz_bonus_zins < PROZENT_0 )
    {
      tarif_proz_bonus_zins = PROZENT_0;
    }

    if ( tarif_mindest_bwz < BETRAG_0 )
    {
      tarif_mindest_bwz = BETRAG_0;
    }

    /*
     * Unter- und Obergrenze Mehrzuteilung in Prozent
     * 
     * Die Tarifangaben fuer die Unter- und Obergrenze in Prozent, werden gegen die Grenzen
     * des Rechenkerns grprueft. 
     * 
     * Wird die jeweilige Grenze ueber oder unterschritten, wird der jeweilige Grenzwert gesetzt.
     * 
     * Liet die Untergrenze nachher ueber der Obergrenze, werden beide Grenzen vertauscht, so dass
     * die Untergrenze kleiner als die Obergrenze ist.
     */
    if ( tarif_mehrzuteilung_untergrenze_prozent < MEHRZUTEILUNG_UNTERGRENZE_PROZENT )
    {
      tarif_mehrzuteilung_untergrenze_prozent = MEHRZUTEILUNG_UNTERGRENZE_PROZENT;
    }
    else if ( tarif_mehrzuteilung_untergrenze_prozent > MEHRZUTEILUNG_OBERGRENZE_PROZENT )
    {
      tarif_mehrzuteilung_untergrenze_prozent = MEHRZUTEILUNG_OBERGRENZE_PROZENT;
    }

    if ( tarif_mehrzuteilung_obergrenze_prozent < MEHRZUTEILUNG_UNTERGRENZE_PROZENT )
    {
      tarif_mehrzuteilung_obergrenze_prozent = MEHRZUTEILUNG_UNTERGRENZE_PROZENT;
    }
    else if ( tarif_mehrzuteilung_obergrenze_prozent > MEHRZUTEILUNG_OBERGRENZE_PROZENT )
    {
      tarif_mehrzuteilung_obergrenze_prozent = MEHRZUTEILUNG_OBERGRENZE_PROZENT;
    }

    if ( tarif_mehrzuteilung_untergrenze_prozent > tarif_mehrzuteilung_obergrenze_prozent )
    {
      double temp_double = tarif_mehrzuteilung_untergrenze_prozent;

      tarif_mehrzuteilung_untergrenze_prozent = tarif_mehrzuteilung_obergrenze_prozent;

      tarif_mehrzuteilung_obergrenze_prozent = temp_double;
    }

    double berechnung_betrag_kto_fuehrungsgebuehr = BETRAG_0;
    double bsv_sparbeitrag_betrag = BETRAG_0;

    String bsv_sparbeitrag_bezeichnung = "";

    long bsv_sparbeitrag_datum_ab = DATUM_0;
    long bsv_sparbeitrag_datum_bis = DATUM_0;

    long c_datum_stichtag_ende = DATUM_0;
    long c_datum_stichtag_start = DATUM_0;

    /*
     * Debugkennzeichen aus der Eingabe uebernehmen.
     */
    boolean c_debug_knz_akt_element = pCalcBsvEingabe.getDebugKnzAktElement();
    boolean c_debug_knz_ansp_berechnung = pCalcBsvEingabe.getDebugKnzAnspBerechnung();
    boolean c_debug_knz_stichtage = pCalcBsvEingabe.getDebugKnzStichtage();
    boolean c_debug_knz_wop_berechnung = pCalcBsvEingabe.getDebugKnzWopBerechnung();
    boolean c_debug_knz_zins_berechnung_bsd = pCalcBsvEingabe.getDebugKnzZinsBerechnungBsd();
    boolean c_debug_knz_zins_berechnung_spar = pCalcBsvEingabe.getDebugKnzZinsBerechnungSpar();

    boolean c_knz_ag_beruecksichtigen = B_FALSE;
    boolean c_knz_ag_mit_sparbeitraegen_verrechnen = B_FALSE;

    boolean c_spar_kto_gebuehr_knz_beruecksichtigen = pCalcBsvEingabe.getKnzKfgSparphaseBeruecksichtigen();

    boolean c_bsd_kto_gebuehr_knz_beruecksichtigen = pCalcBsvEingabe.getKnzKfgDarlehensphaseBeruecksichtigen();

    double c_salden_summe_erreichung_bwz = BETRAG_0;

    long c_knz_berechnungsart = WERT_NICHT_VORHANDEN;

    boolean c_knz_mit_arbeitnehmer_sparzulage = B_FALSE;

    int c_knz_sparstopp_aus_vorgaben = WERT_NICHT_VORHANDEN;

    long c_rk_berechnungsziel = WERT_NICHT_VORHANDEN;
    long c_rk_max_datum_calc_lz_long = DATUM_0;
    long c_spar_ag_art_begleichung = WERT_NICHT_VORHANDEN;
    double c_spar_ag_betrag_vorgabe = BETRAG_0;
    String c_spar_ag_bezeichnung = "";
    long c_spar_ag_datum_belastung = DATUM_0;
    double c_spar_betrag_bausparsumme = BETRAG_0;
    long c_spar_datum_mindestsparzeit_long = DATUM_0;
    long c_spar_datum_vertragsbeginn_long = DATUM_0;
    long c_spar_datum_zuteilung_long = DATUM_0;
    long c_spar_mindest_sparzeit_rest_monate = ANZAHL_MONATE_0;
    double c_spar_wop_gebuehr_laufend = BETRAG_0;
    long c_spar_datum_stichtag_long = DATUM_0;
    double c_tarif_minbwz_betrag = BETRAG_0;
    String e_bsd_kto_gebuehr_laufend_bezeichnung = "";
    double e_bsd_kto_gebuehr_laufend_betrag = BETRAG_0;
    long e_bsd_kto_gebuehr_laufend_datum_belastung = 0;
    double e_spar_ag_betrag = BETRAG_0;
    long e_spar_ansp_einmalig_datum_auszahlung = DATUM_0;
    double e_spar_betrag_tarifliche_abschlussgebuehr = BETRAG_0;
    double e_spar_betrag_tarifliches_mindestsparguthaben = BETRAG_0;
    long e_spar_datum_berechnungsbeginn_jahr = ANZAHL_JAHRE_0;
    long e_spar_datum_berechnungsbeginn_long = DATUM_0;
    long e_spar_datum_berechnungsbeginn_monat = ANZAHL_MONATE_0;
    double e_spar_iteratonsziel_mindestsparguthaben = BETRAG_0;
    boolean e_spar_knz_bonus_moeglich = B_FALSE;
    String e_spar_kto_gebuehr_anteilig_bezeichnung = "";
    double e_spar_kto_gebuehr_anteilig_betrag = BETRAG_0;
    long e_spar_kto_gebuehr_anteilig_anz_monate = ANZAHL_MONATE_0;
    long e_spar_kto_gebuehr_anteilig_datum_belastung = DATUM_0;
    String e_spar_kto_gebuehr_laufend_bezeichnung = "";
    double e_spar_kto_gebuehr_laufend_betrag = BETRAG_0;
    long e_spar_kto_gebuehr_laufend_datum_belastung = DATUM_0;
    long e_spar_laufzeit_gesamt_monate = ANZAHL_MONATE_0;
    long e_spar_wop_datum_junge_leute_regelung = DATUM_0;
    long e_spar_wop_gebuehr_laufend_datum_belastung = DATUM_0;
    long max_sonder_zahlungen = ANZAHL_0;
    double v_bestehend_ansp_ausstehend = BETRAG_0;
    double v_bestehend_ansp_festgeschrieben = BETRAG_0;
    double v_bestehend_ansp_uj_vl_as = BETRAG_0;
    double v_bestehend_ansp_uj_vl_ep = BETRAG_0;
    double v_bestehend_wop_gebuehr_gesamt = BETRAG_0;
    double v_bestehende_bwz = BETRAG_0;
    double v_bestehende_saldensumme = BETRAG_0;
    double v_wop_bestehender_betrag_festgesetzt = BETRAG_0;
    int zaehler_index = WERT_NICHT_VORHANDEN;

    double c_betrag_kontostand_init = BETRAG_0;

/* 
 * ##################################################################################################################
 * ##################################################################################################################
 * ##################################################################################################################
 * ##################################################################################################################
 */

    c_debug_knz_stichtage = m_debug_knz_bwz;
    c_debug_knz_ansp_berechnung = m_debug_knz_ansp_berechnung;
    c_debug_knz_wop_berechnung = m_debug_knz_wop_berechnung;
    c_debug_knz_akt_element = m_debug_knz_akt_element;
    c_debug_knz_zins_berechnung_spar = m_debug_knz_zins_berechnung_spar;
    c_debug_knz_zins_berechnung_bsd = m_debug_knz_zins_berechnung_bsd;

    max_sonder_zahlungen = pCalcBsvEingabe.getAnzahlSonderzahlungen();

    c_spar_wop_gebuehr_laufend = BETRAG_WOP_GEBUEHR;

    /* #################################################################################################
     * #### ABSCHNITT: DATEN AUS VORGABEN UEBERNEHMEN                                               ####
     * #### In diesem Abschnitt werden die Daten aus den Vorgaben in interne Variablen uebertragen. ####
     * #### Gleichzeitig werden mit diesen Daten berechnungsrelevante Daten berechnet.              ####
     * #################################################################################################
     */
    wl( "Eingabe " + pCalcBsvEingabe.toString() );

    /* 
     * Berechnungsart
     * Uebernahme Daten eines bestehenden Vertrages
     * Uebernhame des Berechnungszieles
     * uebernahme Sparstopp
     * Ermittlung des Datums des Berechnungsbeginns
     * Ermittlung des Maximalen Datums ausgehend vom Berechnungsbeginn.
     * Ermittlung Datum des Ablaufes der Mindestsparzeit
     */

    /*
     * Berechnungsart 
     * Es gibt 2 Berechnungsarten. 
     * Eine Berechnungsart fuer Neuvertraege und eine fuer Bestandsvertraege. 
     * 
     * Diese Berechnungsarten legen fest, wie die Werte bei den Vorabberechnungen 
     * beruecksichtigt werden. 
     * 
     * Bei einer Neuvertraegsberechnung werden die Startwerte der Suchschleife auf 0 gestellt. 
     * Bei einer Bestandsvertragsberechnung sind die Startwerte fuer die Suchschleife 
     * teilweise schon gesetzt:
     *  
     * - Zeitabhaengige Werte fangen nicht bei 0 an (Mindestsparzeit).
     * - die Saldensumme wird aus den bestehenden Werten zurueckberechnet.
     */
    c_knz_berechnungsart = pCalcBsvEingabe.istVorgabeartNeuvertrag() ? RK_B_ART_NEUVERTRAG : RK_B_ART_BESTANDSVERTRAG;

    /*
     * Anfangswerte Bausparvertrag
     * Bei jedem Interationsdurchlauf werden die Werte fuer die Suchschleife initialsiert.
     * Diese Werte werden hier gesetzt. 
     * 
     * Neuvertrag
     * Eine Neuvertragsberechnung ist ein Bausparvertrag mit keinen Anfangswerten.
     * Daher werden alle Werte auf den Initialbetrag von 0 gesetzt.
     * Eventuell vorhandene Vorgaben aus der Eingabestruktur werden ignoriert.
     * 
     * Bestandsvertrag
     * Ein Bestandsvertrag ist ein Bausparvertrag, welcher ueber Bestandswerte verfuegt.
     * Diese Werte werden dem Rechenkern ueber die Eingabestruktur mitgegeben.
     * Diese Anfangswerte werden aus der Eingabestruktur in die internen Variablen uebernommen.
     */
    if ( c_knz_berechnungsart == RK_B_ART_NEUVERTRAG )
    {
      v_wop_bestehender_betrag_festgesetzt = BETRAG_0;
      v_bestehende_saldensumme = BETRAG_0;
      v_bestehend_wop_gebuehr_gesamt = BETRAG_0;
      v_bestehend_ansp_festgeschrieben = BETRAG_0;
      v_bestehend_ansp_ausstehend = BETRAG_0;
      v_bestehend_ansp_uj_vl_as = BETRAG_0;
      v_bestehend_ansp_uj_vl_ep = BETRAG_0;
      v_bestehende_saldensumme = BETRAG_0;
      v_bestehende_bwz = BETRAG_0;
    }
    else
    {
      v_wop_bestehender_betrag_festgesetzt = BETRAG_0;
      v_bestehende_saldensumme = BETRAG_0;
      v_bestehend_wop_gebuehr_gesamt = BETRAG_0;
      v_bestehend_ansp_festgeschrieben = BETRAG_0;
      v_bestehend_ansp_ausstehend = BETRAG_0;
      v_bestehend_ansp_uj_vl_as = BETRAG_0;
      v_bestehend_ansp_uj_vl_ep = BETRAG_0;
      v_bestehende_saldensumme = BETRAG_0;
      v_bestehende_bwz = pCalcBsvEingabe.getRkInputBestehendeBwz();
      c_betrag_kontostand_init = pCalcBsvEingabe.getRkInputBestehendesGuthaben();
    }

    /* 
     * Uebernahme Berechnungsziel
     * Aus den textuellen Vorgaben der Eingabe, wird die konkrete Konstante des Rechenkerns ermittelt.
     * Kann kein Berechnungsziel ermittelt werden, wird ein Fehlerkennzeeichen gesetzt.
     */
    if ( pCalcBsvEingabe.istZielBausparsumme() )
    {
      c_rk_berechnungsziel = BZ_BAUSPARSUMME;
    }
    else if ( pCalcBsvEingabe.istZielLaufzeit() )
    {
      c_rk_berechnungsziel = BZ_LAUFZEIT;
    }
    else if ( pCalcBsvEingabe.istZielSparrate() )
    {
      c_rk_berechnungsziel = BZ_SPARRATE_SUCHE_MIN_GUTHABEN_RATE;
    }
    else if ( pCalcBsvEingabe.istZielSimulation() )
    {
      c_rk_berechnungsziel = BZ_VERTRAGS_SIMULATION;
    }
    else
    {
      c_rk_berechnungsziel = BZ_FEHLER_BERECHNUNGSZIEL_NICHT_ANGEGEBEN;

      e_rechenkern_fehler_nummer = FEHLER_VORGABE_DATEN_UNVOLLSTAENDIG;
    }

    boolean calc_ziel_grob_ist_rate = false;

    //calc_ziel_grob_ist_rate = pCalcBsvEingabe.istZielSparrate();

    /* 
     * Sparstopp aus den Vorgaben
     * Der Sparstopp aus den (textuellen) Vorgaben wird ermittelt und in die Rechenkern-Vorgabevariable kopiert.
     */
    if ( pCalcBsvEingabe.istSparStoppMindestGuthaben() )
    {
      c_knz_sparstopp_aus_vorgaben = SPAR_STOPP_MI_GUTHABEN;
    }
    else if ( pCalcBsvEingabe.istSparStoppZuteilung() )
    {
      c_knz_sparstopp_aus_vorgaben = SPAR_STOPP_ZUTEILUNG;
    }
    else if ( pCalcBsvEingabe.istSparStoppZuteilungsvoraussetzungen() )
    {
      c_knz_sparstopp_aus_vorgaben = SPAR_STOPP_MINDEST_ZTV;
    }
    else
    {
      c_rk_berechnungsziel = FEHLER_SPAR_STOPP_NICHT_ANGEGEBEN;

      e_rechenkern_fehler_nummer = FEHLER_VORGABE_DATEN_UNVOLLSTAENDIG;
    }

    /* 
     * Datum Berechnungsbeginn
     * Das Berechnungsbeginn-Datum ist das uebergebene Datum.
     * Der Tagesanteil wird auf den Monatsersten gestellt. (Eventuell Fehlerquelle bei Bestandsvertraegen)
     * 
     * Die Datumsbestandteile Jahr und Monat werden in die lokalen Variablen kopiert.
     */
    e_spar_datum_berechnungsbeginn_jahr = FkDatumLong.getJahrAusLong( pCalcBsvEingabe.getRkInputDatumBerechnungsbeginn() );

    e_spar_datum_berechnungsbeginn_monat = FkDatumLong.getMonatAusLong( pCalcBsvEingabe.getRkInputDatumBerechnungsbeginn() );

    e_spar_datum_berechnungsbeginn_long = FkDatumLong.getLong( e_spar_datum_berechnungsbeginn_jahr, e_spar_datum_berechnungsbeginn_monat, TAG_1 );

    /* 
     * Berechnung des maximalen Datums
     * Es wird das vom Rechenkern maximal zu akzeptierende Datum berechnet.
     * Dieses bestimmt sich aus dem Berechnungsbeginndatum zuzueglich der
     * im Rechenkern hinterlegten maximal zu beruecksichtigenden Jahre.
     * Das sich ergebende Datum wird auf den Monatsletzten gestellt.
     * 
     * Kein Datum im Rechenkern, soll dieses Datum ueberschreiten.
     */
    c_rk_max_datum_calc_lz_long = FkDatumLong.longDateAdd( e_spar_datum_berechnungsbeginn_long, m_rk_anzahl_jahre_zahlungs_ende, ANZAHL_MONATE_0, ANZAHL_TAGE_0, KNZ_TAG_MONATSENDE );

    /* 
     * Pruefung des Maximal-Datums auf Ueberschreitung des maximal
     * moeglichen Datums von 99991231. 
     * 
     * Liegt das berechnete Maximaldatum der Berechnung hinter dieser 
     * Grenze, wird das Maximaldatum auf eben 99991231 gesetzt.
     * 
     * Ueber die Sinnhaftigkeit im Jahre 2021 kann gestritten werden.
     */
    if ( c_rk_max_datum_calc_lz_long >= 99991231 )
    {
      c_rk_max_datum_calc_lz_long = 99991231;
    }

    /*
     * Vertragsbeginn
     * 
     * Neuvertrag
     * Bei einem Neuvertrag wird der Vertragsbeginn auf den Berechnungsbeginn gesetzt. 
     * Ein in den Eingabedaten uebergebenes Datum wird ignoriert.
     */
    if ( c_knz_berechnungsart == RK_B_ART_NEUVERTRAG )
    {
      c_spar_datum_vertragsbeginn_long = e_spar_datum_berechnungsbeginn_long;
    }
    else
    {
      /*
       * Bestandsvertrag
       * Bei einem Bestandsvertrag wird das Vertragsbeginndatum aus den Eingaben gelesen. 
       */
      c_spar_datum_vertragsbeginn_long = pCalcBsvEingabe.getRkInputDatumVertragsbeginn();

      /*
       * Liegt das Beginndatum des Vertrages nach dem Berechnungsbeginn, koennen zwei 
       * Moeglichkeiten genutzt werden:
       * 
       * 1. Es kann ein Fehlerkennzeichen gesetzt und die Berehnung damit abgebrochen werden
       * 2. Der Rechenkern kann das Vertragsbeginndatum auf den Berechnungsbeginn legen.
       * 
       * Aktuell wird der Vertragsbeginn angepasst
       */
      if ( c_spar_datum_vertragsbeginn_long > e_spar_datum_berechnungsbeginn_long )
      {
        c_spar_datum_vertragsbeginn_long = e_spar_datum_berechnungsbeginn_long;
      }
    }

    /* 
     * Tarifliche Mindestsparzeit
     * Die tarifliche Mindestsparzeit wird aus den Tarifvorgaben uebernommen.
     * Ist diese Mindestsparzeit kleiner 0, wird die Mindestsparzeit auf 0 gesetzt.
     */
    tarif_mindestsparzeit_monate = pCalcTarifEingabe.getMindestSparzeitInMonaten();

    if ( tarif_mindestsparzeit_monate < DAUER_0 )
    {
      tarif_mindestsparzeit_monate = DAUER_0;
    }

    /* 
     * Restmonate der Mindestsparzeit
     * 
     * Bei einem Neuvertrag sind die Restmonate gleich der Mindestsparzeit.
     * 
     * Bei einem Bestandsvertrag sind die Restmonate diejenigen, welche nach dem
     * Vertragsbeginn noch uebrig sind. Es wird die Monatsdifferenz zwischen
     * Vertragsabschluss und Berechnungsbeginn berechnet. Ist diese Differenz
     * kleiner 0, sind die Restmonate der Mindestsparzeit auch 0, bzw. die
     * Mindestsparzeit wurde schon erreicht.
     */
    if ( c_knz_berechnungsart == RK_B_ART_NEUVERTRAG )
    {
      c_spar_datum_vertragsbeginn_long = e_spar_datum_berechnungsbeginn_long;

      c_spar_mindest_sparzeit_rest_monate = tarif_mindestsparzeit_monate;
    }
    else
    {
      c_spar_datum_vertragsbeginn_long = pCalcBsvEingabe.getRkInputDatumVertragsbeginn();

      c_spar_mindest_sparzeit_rest_monate = tarif_mindestsparzeit_monate - FkDatumLong.getMonatsDifferenz( c_spar_datum_vertragsbeginn_long, e_spar_datum_berechnungsbeginn_long );

      if ( c_spar_mindest_sparzeit_rest_monate < DAUER_0 )
      {
        c_spar_mindest_sparzeit_rest_monate = DAUER_0;
      }
    }

    /* 
     * Berechnung Datum Mindestsparzeit
     * Es wird das Enddatum der Mindestparzeit berechnet.
     * 
     * Fuer Neuvertraege ist dieses Enddatum gleich der gesamten Mindestsparzeit ab Vertragsbeginn.
     * Fuer Bestandsvertraege ist dieses Enddatum der noch verbleibende Zeitraum ab Vertragsbeginn.
     * 
     * Sind die berechneten Restmonate der Mindestsparzeit groesser 0, werden
     * diese dem Vertragsbeginn hinzugezaehlt.
     * 
     * Sind keine Restmonate der Mindestsparzeit vorhanden, wird das Datum der
     * Mindestsparzeit auf den Berechnungsbeginn gesetzt. Das bedeutet fuer den
     * Rechenkern, dass die Mindestsparzeit beim Berechnungsbeginn schon
     * abgelaufen ist. Gleiches wuerde erfolgen, wenn der Tarif keine
     * Mindestsparzeit hat.
     */
    if ( c_spar_mindest_sparzeit_rest_monate > DAUER_0 )
    {
      c_spar_datum_mindestsparzeit_long = FkDatumLong.longDateAdd( c_spar_datum_vertragsbeginn_long, ANZAHL_JAHRE_0, c_spar_mindest_sparzeit_rest_monate, ANZAHL_TAGE_0, KNZ_TAG_MONATSANFANG );
    }
    else
    {
      c_spar_datum_mindestsparzeit_long = e_spar_datum_berechnungsbeginn_long;
    }

    /*
     * Vervollstaendigung Bausparsumme
     * Ist das Berechnungsziel die Bausparsumme, wird die Angabe einer Bausparsumme 
     * aus den Eingabedaten ignoriert. Die interne Variable wird auf 0 gesetzt. 
     * 
     * Ist das Berechnungsziel nicht die Bausparsumme, wird der Betrag der Bausparsumme 
     * aus den Eingabedaten uebernommen.
     */
    if ( c_rk_berechnungsziel == BZ_BAUSPARSUMME )
    {
      c_spar_betrag_bausparsumme = BETRAG_0;
    }
    else
    {
      c_spar_betrag_bausparsumme = pCalcBsvEingabe.getRkInputBetragBausparsumme();
    }

    /*
     * Vervollstaendigung Laufzeit
     * Ist das Berechnungsziel die Laufzeit, wird die Laufzeit aus den Vorgaben 
     * ignoriert und die Laufzeit auf 0 gesetzt.
     * 
     * Ist das Berechnungsziel nicht die Laufzeit, wird die Laufzeitangabe aus 
     * den Vorgabedate uebernommen.
     */
    if ( c_rk_berechnungsziel == BZ_LAUFZEIT )
    {
      e_spar_laufzeit_gesamt_monate = DAUER_0;
    }
    else
    {
      e_spar_laufzeit_gesamt_monate = pCalcBsvEingabe.getRkInputLaufzeitInMonaten();
    }

    if ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_GUTHABEN_RATE )
    {
      /* 
       * Ist das Berechnungsziel der Sparbeitrag, ist der Sparbeitrag
       * in der ersten Suchschleife der 0.
       */
      bsv_sparbeitrag_betrag = BETRAG_0;
    }
    else
    {
      /* 
       * Ist das Berechnungsziel ungleich "Sparbeitrag", wird der Sparbeitrag
       * aus den Eingaben in die Variable fuer den Sparbeitrag uebertragen.
       */
      bsv_sparbeitrag_betrag = pCalcBsvEingabe.getRkInputZahlungSparbeitrag().getBetrag();
    }

    /* 
     * Datum Sparrate Ab
     * 
     * Das Datum ab wann der Sparbeitrag gezahlt wird, wird aus den Vorgaben genommen.
     * 
     * Wuerde kein Datum uebergeben, wird das Datum auf das Datum des Berechnungsbeginns gesetzt.
     */
    bsv_sparbeitrag_datum_ab = pCalcBsvEingabe.getRkInputZahlungSparbeitrag().getDatumAb();

    if ( bsv_sparbeitrag_datum_ab == DATUM_0 )
    {
      bsv_sparbeitrag_datum_ab = e_spar_datum_berechnungsbeginn_long;
    }

    /* ##############################################################################################
     * 
     * Vorabberechnung von Werten, welche je nach Berechnungsziel berechnet werden koennen.
     * 
     * Je nach Berechnungsziel gibt es berechenbare Werte, welche sich in der Suchschleife
     * nicht aendern.
     */
    if ( c_rk_berechnungsziel != BZ_BAUSPARSUMME )
    {
      /* 
       * Ist das Berechnungsziel NICHT die Bausparsumme, koennen die Werte fuer
       * die tarifliche Abschlussgebuehr und das Mindesparguthaben berechnet
       * werden, da die Bausparsumme als Eingabeparameter in diesem Fall vorliegt.
       * Diese Werte sind unabhaengig von der Berechnungsart (Neuvertrag, Bestandsvertrag)
       * 
       * Ist das Berechnungsziel die Bausparsumme, werden diese Felder
       * spaeter in der Suchschleife berechnet.
       */
      e_spar_betrag_tarifliche_abschlussgebuehr = round2DP( ( c_spar_betrag_bausparsumme * tarif_promille_abschluss_gebuehr ) * MULTIPLIKATOR_DURCH_100 );

      e_spar_betrag_tarifliches_mindestsparguthaben = round2DP( ( c_spar_betrag_bausparsumme * tarif_proz_mindest_sparguthaben ) * MULTIPLIKATOR_DURCH_100 );
    }

    /* 
     * Gesamtlaufzeit in Monaten
     */
    if ( c_rk_berechnungsziel == BZ_LAUFZEIT )
    {
      /* 
       * Laufzeit beim Berechnungsziel LAUFZEIT
       * 
       * Soll die Laufzeit berechnet werden, muss fuer die Erstellung des 
       * Kontoverlaufes ein temporaeres End-Datum berechnet werden. Mit 
       * dem temporaeren Enddatum werden die Verlaufselemente fuer die 
       * Berechnung erstellt. 
       * 
       * Die Suchschleife rechnet mit den Daten aus dem Kontoverlauf.
       * 
       * Beim Berechnungsziel Laufzeit wird ein grobes Zuteilungsdatum fuer
       * die nachfolgenden Berechnungen erstellt. 
       * 
       * Dieses Datum ist so gewaehlt, dass die tatsaechliche Laufzeit immer kuerzer sein wird.
       * 
       * Es sind hier zwei Bedingungen welche erreicht werden muessen.
       * Waehrend der Laufzeit muss das Mindestsparguthaben erreicht werden. 
       * Waehrend der Laufzeit muss die Mindestbewertungszahl erreicht werden. 
       * 
       * Fuer das erste Ziel wird der Betrag des Mindestguthaben genommen. 
       * Dieser Betrag wird um den Betrag der tariflichen Abschlussgebuehr erhoeht. 
       * Es wird davon ausgegangen, dass die Abschlussgebuehr mit den Zahlungen 
       * verrechnet wird. Daher muss dieser Betrag auch von den Raten gedeckt werden. 
       * Fuer die konkrete Berechnung ist es unerheblich ob die Abschlussgebuehr
       * tatsaechlich separat gezahlt werden soll. In diesem Schritt soll nur eine 
       * geschaetzte Obergrenze fuer die Laufzeit festgelegt werden.
       * 
       * Fuer das zweite Ziel wird die notwendige Saldensumme fuer die Mindestbewertungszahl berechnet. 
       * 
       *        Saldensumme = ( Bewertungszahl * Bausparsumme ) / Faktor
       * 
       * Die Bausparsumme wird zusaetzlich um 1000 Euro erhoeht um einen Sicherheitsfaktor 
       * bei der Laufzeitberechnung zu bekommen. 
       * 
       * Ueber eine While-Schleife wird ein grober Kontostand und Saldensumme berechnet. 
       * 
       * Ausgehend vom Berechnungsbeginndatum werden alle Zahlungen jeweils fuer die 
       * aktuell betrachtete Periode grob durchgerechnet. 
       * 
       * Faellt eine Zahlung in den Betrachtungszeitraum, wird dessen Betrag in einen 
       * monatlichen Betrag umgerechnet und mit der Monatsanzahl multipliziert. 
       * 
       * Es werden so alle Zahlungen aufaddiert und nochmals mit einem Anrechnungsfaktor
       * multipliziert. Dieser Anrechnungsfaktor mindert die berechnete Sparleistung, 
       * wodurch die sich ergebende Laufzeit erhoeht. Diese Verlaengerung der 
       * Laufzeit wird hier in Kauf genommen, da dieses nur ein Vorabschaetzwert 
       * fuer die Laufzeitberechnung sein soll. 
       * 
       * Die tatsaechliche Laufzeit wird in der Suchschleife bestimmt. 
       * Ueberfluessig erstellte Verlaufselemente werden aus dem Verlauf wieder
       * entfernt, sobald das korrekte Zuteilungsdatum berechnet wurde. 
       * 
       * Der Fehler ist die Umrechnung auf einen monatlichen Betrag, wobei die 
       * Aufsummierung der Saldensumme falsch ist.
       * 
       * Die temporaere Laufzeit wird gegen die Mindessparzeit geprueft.
       * Wird die Mindestsparzeit unterschritten, wird die Laufzeit auf zwei mal
       * die der Mindestlaufzeit gesetzt. Diese Laufzeit sollte dann ausreichend 
       * sein um die Mindestbewertungszahl und das Mindestsparguthaben zu erreichen.
       * Dieser Fall wird nur bei geringen Bausparsummen auftreten.
       * 
       * Kommt bei der Vorabberechnung eine Laufzeit in Jahren heraus, welche 
       * ueber der im Rechenkern definierten Obergrenze liegt, wird die Laufzeit 
       * mit der maximalen Laufzeit des Rechenkerns ueberschrieben.
       */
      e_spar_laufzeit_gesamt_monate = DAUER_0;

      m_debug_knz_berechnungsziel_schritte = true;

      int c_salden_summe_bwz_laufzeit_monate = 0;

      /*
       * Datumswerte fuer die Suchschleife
       * Ausgehend vom Berechnungsbeginn wird der zuletzt durchlaufene Stichtag ermittelt.
       */
      long akt_datum_von = FkDatumLong.getDatumLetzterStichtag( e_spar_datum_berechnungsbeginn_long );
      long akt_datum_bis = akt_datum_von;

      double c_temp_salden_summe = BETRAG_0;
      double c_temp_besparung_monatlich = BETRAG_0;
      double c_temp_kto_stand = BETRAG_0;
      double c_temp_betrag_akt_bewertungszahl = BETRAG_0;
      long c_temp_datum_mindestguthaben = DATUM_0;

      boolean knz_abbruch_bedingung_erreicht = B_FALSE;
      boolean knz_spar_stopp_erreicht = B_FALSE;
      double c_inv_faktor_bss_berechnet1 = BETRAG_0;

      /* 
       * Berechnung der Saldensumme auf Grundlage von Bewertungszahl und Bausparsumme
       * 
       *          Bewertungszahl = ( Saldensumme * Faktor ) / Bausparsumme
       * 
       *          Bausparsumme   = ( Saldensumme / Bewertungszahl ) * Faktor
       * 
       *          Saldensumme    = ( Bewertungszahl * Bausparsumme ) / Faktor
       */

      v_bestehende_saldensumme = ( v_bestehende_bwz * c_spar_betrag_bausparsumme );

      c_salden_summe_erreichung_bwz = ( tarif_mindest_bwz * ( c_spar_betrag_bausparsumme + BETRAG_1000 ) );

      if ( tarif_bwz_faktor != 0 )
      {
        c_salden_summe_erreichung_bwz = c_salden_summe_erreichung_bwz / tarif_bwz_faktor;
      }

      c_inv_faktor_bss_berechnet1 = 1 / c_spar_betrag_bausparsumme;

      if ( m_debug_knz_berechnungsziel_schritte )
      {
        wl( "calcLaufzeit: " );
        wl( "calcLaufzeit: Vorabschaetzwert Laufzeit" );
        wl( "calcLaufzeit: " );
        wl( "calcLaufzeit: Grobe Berechnung der Laufzeit, damit ein Datum fuer die Verlaufserstellung vorhanden ist." );
        wl( "calcLaufzeit: " );
        wl( "calcLaufzeit: Es wird hier auf die Einhaltung der Mindestbewertungszahl und des Mindestguthabens geprueft." );
        wl( "calcLaufzeit: " );
        wl( "calcLaufzeit: Um einen Puffer zu haben, wurde fuer diese Berechnung die Bausparsumme um 1000 erhoeht." );
        wl( "calcLaufzeit: " );
        wl( "calcLaufzeit: Alle Zahlungen werden auf einen monatlichen Betrag umgerechnet, von welchem dann " );
        wl( "calcLaufzeit: wiederum nur ein Teil annerkannt wird. " );
        wl( "calcLaufzeit: " );
        wl( "calcLaufzeit: tarif_wartezeit_in_monaten                    " + getBetragStr( tarif_wartezeit_in_monaten ) );
        wl( "calcLaufzeit: tarif_mindest_bwz                             " + getBwzStr( tarif_mindest_bwz ) );
        wl( "calcLaufzeit: tarif_proz_mindest_sparguthaben               " + getBetragStr( tarif_proz_mindest_sparguthaben ) );
        wl( "calcLaufzeit: c_spar_betrag_bausparsumme                    " + getBetragStr( c_spar_betrag_bausparsumme ) );
        wl( "calcLaufzeit: c_salden_summe_erreichung_bwz                 " + getBetragStr( c_salden_summe_erreichung_bwz ) + " (Berechnet fuer eine BSS von " + getBetragStr( c_spar_betrag_bausparsumme + BETRAG_1000 ) + ")" );
        wl( "calcLaufzeit: e_spar_betrag_tarifliches_mindestsparguthaben " + getBetragStr( e_spar_betrag_tarifliches_mindestsparguthaben ) );
        wl( "calcLaufzeit: Anrechnungsfaktor Einzahlungen                " + getBetragStr( CALC_LAUFZEIT_VORGABE_MONATLICHE_SPARLEISTUNG_FAKTOR ) );
        wl( "calcLaufzeit: " );
        wl( "calcLaufzeit: Mindestguthaben " + e_spar_betrag_tarifliches_mindestsparguthaben + " + AG " + e_spar_betrag_tarifliche_abschlussgebuehr + " = " + ( e_spar_betrag_tarifliches_mindestsparguthaben + e_spar_betrag_tarifliche_abschlussgebuehr ) );
        wl( "calcLaufzeit: " );
        wl( "calcLaufzeit: Datum      | Monate | BWZ          | Kontostand   | Saldensumme  | Einzahlung " );
      }

      /*
       * While Schleife fuer Laufzeit Vorabberechnung
       * Eine While-Sdchleife fuer die Berechnung des Vorabschaetzwertes fuer die Laufzeit. 
       * Ziel ist es, ein grobes Enddatum zu berechnen. 
       * Dabei werden die 
       */
      while ( ( knz_abbruch_bedingung_erreicht == false ) && ( c_salden_summe_bwz_laufzeit_monate < m_rk_anzahl_monate_zahlungs_ende ) )
      {
        /*
         * Berechnung der aktuell betrachteten Berechnungsperiode
         * Das neue Datum fuer den Beginn der Periode ist das alte "Datum Bis".
         */
        akt_datum_von = akt_datum_bis;

        /*
         * Berechnung Datum-Bis
         * Ausgehend vom Wert im Datum "akt_datum_bis" werden x Monate hinzugezaehlt.
         */
        akt_datum_bis = FkDatumLong.longDateAdd( akt_datum_von, ANZAHL_JAHRE_0, c_anzahl_monate_zwischen_stichtagen, ANZAHL_TAGE_0, KNZ_TAG_MONATSENDE );

        /*
         * Beruecksichtigung Sparstopp
         * Soll die Besparung beim Sparstopp enden, muss auch in der Vorabberechnung die 
         * Besparung eingestellt werden, wenn das Mindestguthaben erreicht ist. 
         * 
         * Wuerde dieses nicht gemacht werden, wuerde durch die Weiterbesparung eine 
         * zu kurze Laufzeit berechnet werden. 
         * Das wuerde in der Folge eventuell dafuer sorgen, dass im Kontoverlauf zu
         * wenig Elemente erstellt wuerden, wodurch dann die Zuteilung nicht erreicht 
         * werden koennte.
         */
        if ( c_temp_kto_stand > e_spar_betrag_tarifliches_mindestsparguthaben )
        {
          if ( c_temp_datum_mindestguthaben == DATUM_0 )
          {
            c_temp_datum_mindestguthaben = akt_datum_von;
          }

          if ( c_knz_sparstopp_aus_vorgaben == SPAR_STOPP_MI_GUTHABEN )
          {
            knz_spar_stopp_erreicht = B_TRUE;
          }
        }

        /* 
         * Berechnung temp Sparleistung
         * 
         * Die Sparleistung wird auf 0 gesetzt. 
         * 
         * Ist der Sparstopp noch nicht erreicht werden die Zahlungen aus den Eingaben 
         * aufaddiert. 
         * 
         * Sparbeitrag + VL Antragsteller + VL Ehepartner + Sonderzahlungen
         */
        c_temp_besparung_monatlich = BETRAG_0;

        if ( knz_spar_stopp_erreicht == false )
        {
          c_temp_besparung_monatlich += calcBetragLaufzeitVorab( pCalcBsvEingabe.getRkInputZahlungSparbeitrag(), akt_datum_von, akt_datum_bis, c_anzahl_monate_zwischen_stichtagen );

          c_temp_besparung_monatlich += calcBetragLaufzeitVorab( pCalcBsvEingabe.getRkInputZahlungVlAs(), akt_datum_von, akt_datum_bis, c_anzahl_monate_zwischen_stichtagen );

          c_temp_besparung_monatlich += calcBetragLaufzeitVorab( pCalcBsvEingabe.getRkInputZahlungVlEp(), akt_datum_von, akt_datum_bis, c_anzahl_monate_zwischen_stichtagen );

          zaehler_index = INDEX_0;

          while ( zaehler_index < max_sonder_zahlungen )
          {
            c_temp_besparung_monatlich += calcBetragLaufzeitVorab( pCalcBsvEingabe.getSonderzahlung( zaehler_index ), akt_datum_von, akt_datum_bis, c_anzahl_monate_zwischen_stichtagen );

            zaehler_index = zaehler_index + 1;
          }
        }

        /*
         * Zinsen
         * Ist der Kontostand groesser als 0, werden auf den Kontostand die Zinsen berechnet und 
         * auf die Monatsanzahl umgerechnet.
         * 
         * Um diese Berechnungsschleife zu vereinfachen, werden die Zinsen auf die temporaere 
         * Besparung draufaddiert. Auf eine Ausweisung der berechneten Zinsen wurde verzichtet.
         */
        if ( c_temp_kto_stand > BETRAG_0 )
        {
          c_temp_besparung_monatlich += ( ( c_temp_kto_stand * tarif_proz_guthabenzins * MULTIPLIKATOR_DURCH_100 ) * JAHRES_FAKTOR_12_MONATE ) * c_anzahl_monate_zwischen_stichtagen;
        }

        /*
         * Erhoehung Kontostand
         * Die berechnete temporaere Sparleistung wird mit dem Faktor fuer diese Schleife multiplziert. 
         * Das Ergebnis wird dem Kontostand draufaddiert.
         */
        c_temp_kto_stand += ( c_temp_besparung_monatlich * CALC_LAUFZEIT_VORGABE_MONATLICHE_SPARLEISTUNG_FAKTOR );

        /*
         * Saldensumme
         * Die Saldensumme, wird um den aktuellen Kontostand erhoeht
         */
        c_temp_salden_summe += c_temp_kto_stand;

        /*
         * Bewertungszahl
         * Die Bewertungszahl wird auf Grundlage der aktuellen Saldensumme berechnet.
         */
        c_temp_betrag_akt_bewertungszahl = get3nk( c_temp_salden_summe * c_inv_faktor_bss_berechnet1 );

        /* 
         * BWZ-Faktor
         * Wenn ein Bewertungszahlfaktor gesetzt ist, wird die Bewertungszahl mit
         * diesem Faktor multipliziert.
         * 
         * Sinn ? Zweck ?
         */
        if ( tarif_bwz_faktor > BETRAG_0 )
        {
          c_temp_betrag_akt_bewertungszahl = get3nk( c_temp_betrag_akt_bewertungszahl * tarif_bwz_faktor );
        }

        if ( m_debug_knz_berechnungsziel_schritte )
        {
          wl( "calcLaufzeit: " + FkDatumLong.getStringAuslong( akt_datum_bis ) + " |   " + getZaehler4( c_salden_summe_bwz_laufzeit_monate ) + " | " + getBwzStr( c_temp_betrag_akt_bewertungszahl ) + " | " + getBetragStr( c_temp_kto_stand ) + " | " + getBetragStr( c_temp_salden_summe ) + " | " + getBetragStr( c_temp_besparung_monatlich ) + " = " + getBetragStr( c_temp_besparung_monatlich * CALC_LAUFZEIT_VORGABE_MONATLICHE_SPARLEISTUNG_FAKTOR ) );
        }

        /*
         * Pruefung: Zuteilungsvorraussetzungen erreicht?
         * Sind die Zuteilungsvorraussetzungen erreicht wird das Kennzeichen fuer das Schleifenende gesetzt. 
         */
        if ( ( c_temp_kto_stand > e_spar_betrag_tarifliches_mindestsparguthaben ) && ( c_temp_salden_summe > c_salden_summe_erreichung_bwz ) )
        {
          knz_abbruch_bedingung_erreicht = true;
        }
        else
        {
          /*
           * Wurden die Zuteilungsvorraussetzungen noch nicht erreicht, werden die Anzahl der Monate 
           * der Vorabschaetzzeit um die Anzahl der Monate zwischen zwei Stichtagen erhoeht.
           * 
           * Wird dabei die Maximalanzahl der Monate ueberschritten, wird die Suchschleife beendet. 
           */
          c_salden_summe_bwz_laufzeit_monate += c_anzahl_monate_zwischen_stichtagen;

          if ( c_salden_summe_bwz_laufzeit_monate > CALC_LAUFZEIT_VORGABE_MAXIMALE_LAUFZEIT_IN_MONATEN )
          {
            knz_abbruch_bedingung_erreicht = true;
          }
        }
      }

      e_spar_laufzeit_gesamt_monate = c_salden_summe_bwz_laufzeit_monate + tarif_wartezeit_in_monaten;

      /*
       * Pruefung gegen die Mindestsparzeit in Monaten des Tarifes. 
       * 
       * Ist die vorab berechnete Laufzeit geringer als die tarifliche Mindestsparzeit, 
       * wird die Laufzeit auf die tarifliche Mindestsparzeit gesetzt.
       * 
       * Es ist hierbei unerheblich, ob bei einem Bestandsvertrag weniger Monate der 
       * Mindestsparzeit noch zu leisten sind. 
       */
      if ( e_spar_laufzeit_gesamt_monate < tarif_mindestsparzeit_monate )
      {
        e_spar_laufzeit_gesamt_monate = tarif_mindestsparzeit_monate;
      }

      /*
       * Pruefung gegen die Mindestlaufzeit in Monaten des Rechenkerns bei dem 
       * Berechnungsziel Laufzeit.
       * 
       * Ist die vorab berechnete Laufzeit geringer, wird die Laufzeit auf die 
       * entsprechende Laufzeit des Rechenkerns angepasst.
       */
      if ( e_spar_laufzeit_gesamt_monate < CALC_LAUFZEIT_VORGABE_MINDEST_LAUFZEIT_IN_MONATEN )
      {
        e_spar_laufzeit_gesamt_monate = CALC_LAUFZEIT_VORGABE_MINDEST_LAUFZEIT_IN_MONATEN;
      }

      if ( m_debug_knz_berechnungsziel_schritte )
      {
        wl( "calcLaufzeit: " );
        wl( "calcLaufzeit: e_spar_laufzeit_gesamt_monate " + e_spar_laufzeit_gesamt_monate );
        wl( "calcLaufzeit: akt_datum_bis                 " + FkDatumLong.getDatumStringAusLong( akt_datum_bis ) );
        wl( "calcLaufzeit: c_temp_datum_mindestguthaben  " + FkDatumLong.getDatumStringAusLong( c_temp_datum_mindestguthaben ) );
        wl( "calcLaufzeit: " );
      }
    }
    else
    {
      /* 
       * Laufzeit bei Berechnungsziel BAUSPARSUMME, SPARRATE, SIMULATION
       * 
       * Pruefung: Laufzeit Jahre negativ?
       * 
       * Ist die vorgegebene Laufzeit in Monaten negativ, wird die Laufzeit
       * in Monaten auf 0 gestellt.
       */
      if ( e_spar_laufzeit_gesamt_monate < DAUER_0 )
      {
        e_spar_laufzeit_gesamt_monate = DAUER_0;
      }
    }

    /* 
     * Pruefung "Gesamt Sparzeit in Monaten"
     * Die gesamte Laufzeit der Monate muss groesser/gleich der noch verbleibenden
     * Mindestsparzeit sein. Wird die noch verbleibende Mindestsparzeit unterschritten
     * wird der Fehler "FEHLER_MINDEST_SPARZEIT_UNTERSCHRITTEN" erstellt.
     */
    if ( e_spar_laufzeit_gesamt_monate < c_spar_mindest_sparzeit_rest_monate )
    {
      e_rechenkern_fehler_nummer = FEHLER_MINDEST_SPARZEIT_UNTERSCHRITTEN;
    }

    /* ##############################################################################################
     * 
     * Berechnung Datum der Zuteilung
     * Das Datum der Zuteilung berechnet sich aus dem Berechnungsbeginn-Datum 
     * zuzueglich der Gesamtlaufzeit der Sparphase in Monaten. 
     */
    c_spar_datum_zuteilung_long = FkDatumLong.longDateAdd( e_spar_datum_berechnungsbeginn_long, ANZAHL_JAHRE_0, e_spar_laufzeit_gesamt_monate, ANZAHL_TAGE_0, KNZ_TAG_MONATSENDE );

    /* ##############################################################################################
     * 
     * Berechnung Stichtag fuer Zuteilung
     * 
     * Vom Zuteilungsdatum muessen die Wartezeitmonate abgezogen werden.
     * Die Wartezeitmonate sind die Monate, in welchen der Bausparer auf
     * die Bereitstellung der Geldmittel warten muss.
     * 
     * Von dem berechneten Zuteilungsdatum wird der letzte durchlaufene Stichtag ermittelt. 
     * Das ist ein Quartalsende.
     */
    c_spar_datum_stichtag_long = FkDatumLong.getDatumLetzterStichtag( FkDatumLong.longDateAdd( c_spar_datum_zuteilung_long, ANZAHL_JAHRE_0, getNegativ( tarif_wartezeit_in_monaten ), ANZAHL_TAGE_0, KNZ_TAG_MONATSENDE ) );

    long c_calc_rate_stichtag_massgebend = c_spar_datum_stichtag_long;

    /* ##############################################################################################
     * 
     * Stichtage fuer Bewertungszahlberechnungen
     * Berechnung der Datumsgrenzen fuer die Ermittlung der zu durchlaufenden
     * Bewertungsstichtage.
     * 
     * Die Untergrenze ist der zuletzt durchlaufene Stichtag vor dem Berechnungsbeginn.
     * (Berechnungsbeginn koennte ja auch gleich der erste Stichtag sein (... Berechnungsbeginn ist immer ein Monatserster))
     * 
     * Die Obergrenze ist das berechnete Zuteilungsdatum.
     */
    c_datum_stichtag_start = FkDatumLong.getDatumLetzterStichtag( e_spar_datum_berechnungsbeginn_long );

    c_datum_stichtag_ende = c_spar_datum_zuteilung_long;

    /* ##############################################################################################
     * 
     * Kontofuehrungsgebuehr
     * 
     * Durch die Konfofuehrungsgebuehr werden Portokosten fuer den Versand
     * von Briefen (Kontoauszug) bezahlt.
     * 
     * Liegt der Vertragsabschluss innerhalb des Jahres, wird eine anteilige
     * Kontofuehrungsgebuehr berechnet. Dafuer wird die jaehrliche Gebuehr auf
     * einen monatlichen Betrag umgerechnet, welcher dann mit den Restmonaten
     * des Jahres multipliziert wird.
     * 
     * Die Kontofuehrungsgebuehr ist unabhaengig von den Berechnungszielen.
     * Daher werden die Daten an dieser Stelle vollstaendig berechnet.
     * Die Kontofuehrungsgebuehr ist vom Tarif abhaengig. Da es aber denkbar 
     * ist, das eine abweichende Kontofuehrungsgebuehr beruecksichtigt werden
     * soll, wird die Berechnung auf Grundlage des Betrages aus der
     * Variablen "berechnung_betrag_kto_fuehrungsgebuehr" gemacht.
     * 
     * Pruefung: Soll die Kontofuehrungsgebuehr beruecksichtigt werden?
     * 
     * Die Kontofuehrungsgebuehr wird nur berechnet, wenn die Variable fuer 
     * die KFG-Beruecksichtigung in den Tarifvariablen auf true gestellt ist  
     * und auch in den Berechnungsvorgaben die Beruecksichtigung auf true steht.
     */
    if ( ( c_spar_kto_gebuehr_knz_beruecksichtigen ) && ( tarif_kto_gebuehr_sparen_knz_ein ) )
    {
      /* 
       * Laufende Kontofuehrungsgebuehr
       * Die laufende Kontofuehrungsgebuehr wird dem Vertrag im Jahr nach dem
       * Berechnungsbeginn belastet und dieses zum Berechnungsende.
       */
      if ( tarif_kto_gebuehr_sparen_knz_ein )
      {
        berechnung_betrag_kto_fuehrungsgebuehr = tarif_kto_gebuehr_sparen_pro_jahr;
      }
      else
      {
        berechnung_betrag_kto_fuehrungsgebuehr = BETRAG_0;
      }

      e_spar_kto_gebuehr_laufend_betrag = berechnung_betrag_kto_fuehrungsgebuehr;

      e_spar_kto_gebuehr_laufend_datum_belastung = FkDatumLong.getLong( e_spar_datum_berechnungsbeginn_jahr + 1, MONAT_JANUAR, TAG_1 ); // 1. Januar in Folgejahren

      e_spar_kto_gebuehr_laufend_bezeichnung = "Kontofuehrungsgebuehr";

      /* 
       * Pruefung: Neuvertrag ?
       * Bei einem Neuvertrag muss die Kontofuehrungsgebuehr berechnet werden. 
       * Bei einem Bestandsvertrag ist die Kontofuehrungsgebuehr schon im Kontosaldo enthalten.
       */
      if ( c_knz_berechnungsart == RK_B_ART_NEUVERTRAG )
      {
        /*
         * Es werden die verbleibende Anzahl der Monate im Jahr berechnet.
         * Dazu wird der Monat des Vertragsbeginns von 13 abgezogen.
         */
        e_spar_kto_gebuehr_anteilig_anz_monate = ( 13 - e_spar_datum_berechnungsbeginn_monat );

        /*
         * Liegt der Monat des Vertragsbeginns nach Januar, wird nur die 
         * anteilige Kontofuehrungsgebuehr berechnet. 
         *
         * Dazu wird die jaehrliche Kontofuehrungsgebuehr auf einen Monatsbetrag
         * umgerechnet und diese dann mit der Anzahl der verbleibenden Monate 
         * im Vertragsbeginnjahr multipliziert. Die Bezeichnung fuer den ersten 
         * Kontofuehrungsgebuehr-Abzug wird entsprechend gesetzt. 
         */
        if ( e_spar_datum_berechnungsbeginn_monat > MONAT_JANUAR )
        {
          e_spar_kto_gebuehr_anteilig_betrag = get2nk( ( ( berechnung_betrag_kto_fuehrungsgebuehr * JAHRES_FAKTOR_12_MONATE ) * e_spar_kto_gebuehr_anteilig_anz_monate ) );

          e_spar_kto_gebuehr_anteilig_bezeichnung = "Anteilige Kontofuehrungsgebuehr fuer " + e_spar_kto_gebuehr_anteilig_anz_monate + " Monate";
        }
        else
        {
          e_spar_kto_gebuehr_anteilig_betrag = berechnung_betrag_kto_fuehrungsgebuehr;

          e_spar_kto_gebuehr_anteilig_bezeichnung = "Kontofuehrungsgebuehr " + e_spar_kto_gebuehr_anteilig_betrag;
        }

        e_spar_kto_gebuehr_anteilig_datum_belastung = e_spar_datum_berechnungsbeginn_long;
      }
      else
      {
        /* 
         * Bei Bestandsvertraegen ist die anteilige Kto-Gebuehr 0.
         * Diese Gebuehr ist im Anfangsguthaben bereits enthalten und muss nicht
         * beruecksichtigt bzw. neu berechnet zu werden.
         * 
         * Die entsprechenden Variablen werden auf 0 gesetzt.
         */
        e_spar_kto_gebuehr_anteilig_anz_monate = ANZAHL_0;
        e_spar_kto_gebuehr_anteilig_betrag = BETRAG_0;
        e_spar_kto_gebuehr_anteilig_datum_belastung = DATUM_0;
        e_spar_kto_gebuehr_anteilig_bezeichnung = "";
      }
    }
    else
    {
      /* 
       * Keine Kto-Gebuehren-Beruecksichtigung
       * Sollen die Kontofuehrungsgebuehren nicht beruecksichtigt werden, werden
       * die entsprechenden Variablen auf 0 gesetzt.
       */
      e_spar_kto_gebuehr_laufend_betrag = BETRAG_0;
      e_spar_kto_gebuehr_laufend_datum_belastung = DATUM_0;
      e_spar_kto_gebuehr_laufend_bezeichnung = "";

      e_spar_kto_gebuehr_anteilig_anz_monate = ANZAHL_0;
      e_spar_kto_gebuehr_anteilig_betrag = BETRAG_0;
      e_spar_kto_gebuehr_anteilig_datum_belastung = DATUM_0;
      e_spar_kto_gebuehr_anteilig_bezeichnung = "";
    }

    /* ##############################################################################################
     * 
     * Abschlussgebuehr
     * Durch die Abschlussgebuehr werden die Kosten fuer den Vertragsabschluss beglichen.
     * Dieses sind Provisionszahlungen.
     * 
     * Betragsvorgabe AG
     * Die Vorgabe eines festen Betrages fuer die Abschlussgebuehr ist fuer spezielle
     * Konditionen (Mitarbeitervertrag) gedacht. Mit der Angabe eines AG-Betrages wird
     * die tarifliche Abschlussgebuehr ueberschrieben/ausgehebelt.
     * 
     * Liegt keine Vorgabe eines AG-Betrages vor, wird der Betrag der AG-Vorgabe auf -1 gestellt
     * Soll die AG nicht beruecksichtigt werden, wird der AG-Vorgabebetrag auf -1 gestellt.
     * 
     * Liegt eine AG-Betragsvorgabe vor, wird daraus eine Zahl erstellt. 
     * Die Vorgabe fuer die Umwandlung in einen Betrag ist -1.
     * 
     * Negative Betragsvorgaben fuer die Abschlusssgebuehr werden nicht uebernommen.
     * Der Wert fuer die Abschlussgebuehrvorgabe wird in diesem Fall auf -1 gestellt.
     */
    c_knz_ag_beruecksichtigen = pCalcBsvEingabe.getRkInputAgKnzBeruecksichtigen();

    if ( ( c_knz_ag_beruecksichtigen == B_FALSE ) || ( pCalcBsvEingabe.getRkInputAgBetragVorgabe() == null ) )
    {
      c_spar_ag_betrag_vorgabe = BETRAG_NICHT_GESETZT;
    }
    else
    {
      c_spar_ag_betrag_vorgabe = get2nk( getDouble( pCalcBsvEingabe.getRkInputAgBetragVorgabe(), BETRAG_NICHT_GESETZT ) );

      if ( c_spar_ag_betrag_vorgabe < BETRAG_0 )
      {
        c_spar_ag_betrag_vorgabe = BETRAG_NICHT_GESETZT;
      }
    }

    /* 
     * Datum Belastung Abschlussgebuehr
     * Soll die AG beruecksichtigt werden, muss ein Verlaufssatz erstellt werden.
     * Damit die Erstellung erfolgt, muss im Datum der AG-Belastung ein Datum
     * hinterlegt werden. Dieses ist der Berechnungsbeginn.
     * 
     * Dieses erfolgt nur bei Neuvertraegen. Da bei einem Bestandsvertrag
     * diese Summe schon im Bausparguthaben enthalten ist.
     * 
     * Ist das Berechnungsziel die Bausparsumme, wird die Abschlussgebuehr in den
     * einzelnen Iteratorlaeufen im Verlaufselement berechnet und eingetragen.
     * 
     * Soll die Abschlussgebuehr nicht beruecksichtigt werden, oder es handelt
     * sich um einen Bestandsvertrag, wird im Datum der AG-Belastung eine "-1"
     * hinterlegt.
     */
    if ( ( c_knz_ag_beruecksichtigen ) && ( c_knz_berechnungsart == RK_B_ART_NEUVERTRAG ) )
    {
      c_spar_ag_datum_belastung = e_spar_datum_berechnungsbeginn_long;

      /* 
       * Berechnung mit separater AG = Sonderzahlung in Hoehe der AG
       * am Berechnungsbeginn und somit muss es auf TRUE stehen.
       */
      c_knz_ag_mit_sparbeitraegen_verrechnen = B_TRUE;
    }
    else
    {
      c_spar_ag_datum_belastung = DATUM_0;

      c_knz_ag_mit_sparbeitraegen_verrechnen = B_FALSE;
    }

    /* 
     * Art der AG-Begleichung
     * Wie soll die Abschlussgebuehr begliche werden. Die Begleichung kann einer
     * separate Zahlung oder als Verrechnung mit den Sparraten gemacht werden.
     * 
     * Dieses Kennzeichen ist unabhaengig davon, ob die AG beruecksichtigt werden soll.
     */
    if ( pCalcBsvEingabe.istAgArtBegleichungSeparat() )
    {
      c_spar_ag_art_begleichung = AG_ART_SEPARATE_ZAHLUNG;
    }
    else
    {
      c_spar_ag_art_begleichung = AG_ART_MIT_ZAHLUNGEN_VERRECHNEN;
    }

    /* 
     * AG-Belastung Hoehe
     * 
     * Pruefung: Ist Berechnungsziel gleich Bausparsumme?
     */
    if ( c_rk_berechnungsziel == BZ_BAUSPARSUMME )
    {
      /* 
       * Soll die Bausparsumme berechnet werden, kann im ersten Schritt noch
       * keine Abschlussgebuehr und kein Mindestguthaben berechnet werden.
       * 
       * Diese Werte werden erst waehrend des Iterationsverfahrens im
       * Kontoverlauf gesetzt.
       */
      e_spar_ag_betrag = BETRAG_0;

      e_spar_betrag_tarifliches_mindestsparguthaben = BETRAG_0;
    }
    else
    {
      /* 
       * Berechnung Abschlussgebuehr
       * Die Abschlussgebuehr wird berechnet, wenn die AG beruecksichtigt
       * werden soll und es sich um einen Neuvertrag handelt.
       * 
       * Bei Bestandsvertraegen ist diese AG schon im Anfangsguthaben enthalten.
       */
      if ( ( c_knz_ag_beruecksichtigen ) && ( c_knz_berechnungsart == RK_B_ART_NEUVERTRAG ) )
      {
        /* 
         * Pruefung: Betragsvorgabe vorhanden?
         * Ist ein AG-Betrag vorgegeben worden ist, wird dieser Betrag gesetzt.
         * Der Betrag muss in diesem Fall groesser/gleich als 0 sein.
         * 
         * Ist kein AG-Betrag vorgegeben worden, wird der Betrag der tariflichen
         * Abschlussgebuehr genommen. Dieser Betrag wurde vorher schon als
         * Ergebniswert berechnet und wird hier nur gesetzt.
         */
        if ( c_spar_ag_betrag_vorgabe >= BETRAG_0 )
        {
          e_spar_ag_betrag = get2nk( c_spar_ag_betrag_vorgabe );

          c_spar_ag_bezeichnung = "Abschlussgebuehr abweichend mit " + e_spar_ag_betrag;
        }
        else
        {
          e_spar_ag_betrag = e_spar_betrag_tarifliche_abschlussgebuehr;

          c_spar_ag_bezeichnung = "Abschlussgebuehr " + tarif_promille_abschluss_gebuehr + " Promille der Bss " + c_spar_betrag_bausparsumme + ", " + e_spar_ag_betrag;
        }
      }
      else
      {
        e_spar_ag_betrag = BETRAG_0;

        c_spar_ag_bezeichnung = "";
      }

      /* 
       * Berechnung Mindestguthaben
       */
      e_spar_betrag_tarifliches_mindestsparguthaben = ( c_spar_betrag_bausparsumme * tarif_proz_mindest_sparguthaben ) * MULTIPLIKATOR_DURCH_100;
    }

    /* 
     * Bonus
     */
    if ( pCalcBsvEingabe.getRkInputKnzDarlehensverzicht() )
    {
      e_spar_knz_bonus_moeglich = pCalcTarifEingabe.getBonusKnzBeiDarlehensannahme();
    }
    else
    {
      e_spar_knz_bonus_moeglich = tarif_proz_bonus_zins > BETRAG_0;
    }

    /* 
     * Foerderung
     * 
     * MERKER: Es muss moeglich sein, dass ein abweichendes Datum fuer die einmalige
     *         Arbeitnehmersparzulage und fuer die Kontofuehrungsgebeuehr eingegeben 
     *         werden kann.
     */
    e_spar_ansp_einmalig_datum_auszahlung = DATUM_0;

    c_knz_mit_arbeitnehmer_sparzulage = pCalcBsvEingabe.getRkInputKnzAnspBerechnen();

    if ( c_knz_mit_arbeitnehmer_sparzulage )
    {
      /* 
       * Die Arbeitnehmersparzulage wird nach 7 Jahren erstmalig ausgezahlt.
       * Die Hoehe umfasst die angesparte Arbeitnehmersparzulage fuer die
       * ersten 7 Jahre.
       * 
       * Danach wird die Arbeitnehmersparzulage immer zum 31.3. gezahlt.
       * 
       * Bei Neuvertraegen ist das Vertragsbeginndatum gleichzeitig der Berechnungsbeginn.
       * Bei Bestandsvertraegen ist das Vertragsbeginndatum unveraendert.
       * Somit wird bei Bestandsvertragsberechnungen das korrekte Datum berechnet.
       * Die laufenden Ansp-Auszahlungen berechnen sich vom diesem Datum.
       * Auch wenn das Datum der einmaligen Auszahlung vor dem Vertragsbeginn liegt,
       * ist das Datum gesetzt, bzw. ungleich dem Datum_0.
       */
      e_spar_ansp_einmalig_datum_auszahlung = FkDatumLong.longDateAdd( c_spar_datum_vertragsbeginn_long, ANZAHL_JAHRE_7, ANZAHL_MONATE_0, ANZAHL_TAGE_0, KNZ_TAG_UNVERAENDERT );
    }

    e_spar_wop_gebuehr_laufend_datum_belastung = DATUM_0;

    e_spar_wop_datum_junge_leute_regelung = DATUM_0;

    v_wop_bestehender_betrag_festgesetzt = BETRAG_0;

    if ( pCalcBsvEingabe.getRkInputKnzWopBerechnen() )
    {
      /* 
       * Die WOP-Gebuehr wird jaehrlich zum 01.03 eines jeden Jahres faellig.
       * Die Gebuehr wird nach dem ersten Jahr des Vertragsbeginns faellig.
       * 
       * Bei Bestandsvertraegen ist das Vertragsdatum unveraendert, daher wird auch
       * das Startdatum korrekt berechnet.
       */
      e_spar_wop_gebuehr_laufend_datum_belastung = ( ( FkDatumLong.getJahrAusLong( c_spar_datum_vertragsbeginn_long ) + ANZAHL_JAHRE_1 ) * 10000 ) + 301;

      if ( pCalcBsvEingabe.getRkInputKnzWopJungeLeuteR() )
      {
        e_spar_wop_datum_junge_leute_regelung = FkDatumLong.longDateAdd( c_spar_datum_vertragsbeginn_long, ANZAHL_JAHRE_7, ANZAHL_MONATE_0, ANZAHL_TAGE_0, KNZ_TAG_MONATSENDE );
      }

      if ( c_knz_berechnungsart == RK_B_ART_BESTANDSVERTRAG )
      {
        v_wop_bestehender_betrag_festgesetzt = pCalcBsvEingabe.getRkInputWopSpeicher();

        if ( v_wop_bestehender_betrag_festgesetzt < BETRAG_0 )
        {
          v_wop_bestehender_betrag_festgesetzt = BETRAG_0;
        }
      }
    }

    /*
     * Bestehende Bewertungszahl und Saldensumme
     * 
     * Wird ein Neuvertrag berechnet sind diese Werte 0. 
     * 
     * Wird ein Bestandsvertrag berechnet, wird die bestehende Bewertungszahl aus den 
     * Eingabedaten genommen. Die Saldensumme wird aus der bestehenden Bausparsumme 
     * zurueckgerechnet.
     */
    if ( c_knz_berechnungsart == RK_B_ART_NEUVERTRAG )
    {
      v_bestehende_bwz = BETRAG_0;

      v_bestehende_saldensumme = BETRAG_0;
    }
    else
    {
      v_bestehende_bwz = pCalcBsvEingabe.getRkInputBestehendeBwz();

      v_bestehende_saldensumme = ( v_bestehende_bwz * pCalcBsvEingabe.getRkInputBetragBausparsumme() );

      if ( tarif_bwz_faktor != INT_0 )
      {
        v_bestehende_saldensumme = v_bestehende_saldensumme / tarif_bwz_faktor;
      }
    }

    /* 
     * Berechnung der Saldensumme aufgrundlage von Bewertungszahl und Bausparsumme
     * 
     *          Bewertungszahl = ( Saldensumme * Faktor ) / Bausparsumme
     * 
     *          Bausparsumme   = ( Saldensumme / Bewertungszahl ) * Faktor
     * 
     *          Saldensumme    = ( Bewertungszahl * Bausparsumme ) / Faktor
     */

    if ( c_rk_berechnungsziel != BZ_BAUSPARSUMME )
    {
      c_salden_summe_erreichung_bwz = ( tarif_mindest_bwz * pCalcBsvEingabe.getRkInputBetragBausparsumme() );

      if ( tarif_bwz_faktor != 0 )
      {
        c_salden_summe_erreichung_bwz = c_salden_summe_erreichung_bwz / tarif_bwz_faktor;
      }
    }

    wl( "berechnung_betrag_kto_fuehrungsgebuehr        =>" + berechnung_betrag_kto_fuehrungsgebuehr + "<" );
    wl( "bsv_sparbeitrag_betrag                        =>" + bsv_sparbeitrag_betrag + "<" );
    wl( "bsv_sparbeitrag_bezeichnung                   =>" + bsv_sparbeitrag_bezeichnung + "<" );
    wl( "bsv_sparbeitrag_datum_ab                      =>" + bsv_sparbeitrag_datum_ab + "<" );
    wl( "bsv_sparbeitrag_datum_bis                     =>" + bsv_sparbeitrag_datum_bis + "<" );
    wl( "c_datum_stichtag_ende                         =>" + c_datum_stichtag_ende + "<" );
    wl( "c_datum_stichtag_start                        =>" + c_datum_stichtag_start + "<" );
    wl( "c_debug_knz_akt_element                       =>" + c_debug_knz_akt_element + "<" );
    wl( "c_debug_knz_ansp_berechnung                   =>" + c_debug_knz_ansp_berechnung + "<" );
    wl( "c_debug_knz_stichtage                         =>" + c_debug_knz_stichtage + "<" );
    wl( "c_debug_knz_wop_berechnung                    =>" + c_debug_knz_wop_berechnung + "<" );
    wl( "c_debug_knz_zins_berechnung_bsd               =>" + c_debug_knz_zins_berechnung_bsd + "<" );
    wl( "c_debug_knz_zins_berechnung_spar              =>" + c_debug_knz_zins_berechnung_spar + "<" );
    wl( "c_knz_ag_beruecksichtigen                     =>" + c_knz_ag_beruecksichtigen + "<" );
    wl( "c_knz_ag_mit_sparbeitraegen_verrechnen        =>" + c_knz_ag_mit_sparbeitraegen_verrechnen + "<" );
    wl( "c_knz_berechnungsart                          =>" + c_knz_berechnungsart + "<" );
    wl( "c_spar_kto_gebuehr_knz_beruecksichtigen       =>" + c_spar_kto_gebuehr_knz_beruecksichtigen + "<" );
    wl( "c_knz_mit_arbeitnehmer_sparzulage             =>" + c_knz_mit_arbeitnehmer_sparzulage + "<" );
    wl( "c_knz_sparstopp                               =>" + c_knz_sparstopp_aus_vorgaben + "<" );
    wl( "c_rk_berechnungsziel                          =>" + c_rk_berechnungsziel + "<" );
    wl( "c_rk_max_datum_calc_lz_long                   =>" + c_rk_max_datum_calc_lz_long + "<" );
    wl( "c_spar_ag_art_begleichung                     =>" + c_spar_ag_art_begleichung + "<" );
    wl( "c_spar_ag_betrag_vorgabe                      =>" + c_spar_ag_betrag_vorgabe + "<" );
    wl( "c_spar_ag_bezeichnung                         =>" + c_spar_ag_bezeichnung + "<" );
    wl( "c_spar_ag_datum_belastung                     =>" + c_spar_ag_datum_belastung + "<" );
    wl( "c_spar_betrag_bausparsumme                    =>" + c_spar_betrag_bausparsumme + "<" );
    wl( "c_spar_datum_mindestsparzeit_long             =>" + c_spar_datum_mindestsparzeit_long + "<" );
    wl( "c_spar_datum_vertragsbeginn_long              =>" + c_spar_datum_vertragsbeginn_long + "<" );
    wl( "c_spar_datum_zuteilung_long                   =>" + c_spar_datum_zuteilung_long + "<" );
    wl( "c_spar_mindest_sparzeit_rest_monate           =>" + c_spar_mindest_sparzeit_rest_monate + "<" );
    wl( "c_spar_datum_stichtag_long                    =>" + c_spar_datum_stichtag_long + "<" );
    wl( "c_spar_wop_gebuehr_laufend                    =>" + c_spar_wop_gebuehr_laufend + "<" );
    wl( "c_tarif_minbwz_betrag                         =>" + c_tarif_minbwz_betrag + "<" );
    wl( "e_bsd_kto_gebuehr_laufend_betrag              =>" + e_bsd_kto_gebuehr_laufend_betrag + "<" );
    wl( "e_bsd_kto_gebuehr_laufend_bezeichnung         =>" + e_bsd_kto_gebuehr_laufend_bezeichnung + "<" );
    wl( "e_bsd_kto_gebuehr_laufend_datum_belastung     =>" + e_bsd_kto_gebuehr_laufend_datum_belastung + "<" );
    wl( "e_rechenkern_fehler_nummer                    =>" + e_rechenkern_fehler_nummer + "<" );
    wl( "e_spar_ag_betrag                              =>" + e_spar_ag_betrag + "<" );
    wl( "e_spar_ansp_einmalig_datum_auszahlung         =>" + e_spar_ansp_einmalig_datum_auszahlung + "<" );
    wl( "e_spar_betrag_tarifliche_abschlussgebuehr     =>" + e_spar_betrag_tarifliche_abschlussgebuehr + "<" );
    wl( "e_spar_betrag_tarifliches_mindestsparguthaben =>" + e_spar_betrag_tarifliches_mindestsparguthaben + "<" );
    wl( "e_spar_datum_berechnungsbeginn_jahr           =>" + e_spar_datum_berechnungsbeginn_jahr + "<" );
    wl( "e_spar_datum_berechnungsbeginn_long           =>" + e_spar_datum_berechnungsbeginn_long + "<" );
    wl( "e_spar_datum_berechnungsbeginn_monat          =>" + e_spar_datum_berechnungsbeginn_monat + "<" );
    wl( "e_spar_iteratonsziel_mindestsparguthaben      =>" + e_spar_iteratonsziel_mindestsparguthaben + "<" );
    wl( "e_spar_knz_bonus_moeglich                     =>" + e_spar_knz_bonus_moeglich + "<" );
    wl( "e_spar_kto_gebuehr_anteilig_anz_monate        =>" + e_spar_kto_gebuehr_anteilig_anz_monate + "<" );
    wl( "e_spar_kto_gebuehr_anteilig_betrag            =>" + e_spar_kto_gebuehr_anteilig_betrag + "<" );
    wl( "e_spar_kto_gebuehr_anteilig_bezeichnung       =>" + e_spar_kto_gebuehr_anteilig_bezeichnung + "<" );
    wl( "e_spar_kto_gebuehr_anteilig_datum_belastung   =>" + e_spar_kto_gebuehr_anteilig_datum_belastung + "<" );
    wl( "e_spar_kto_gebuehr_laufend_betrag             =>" + e_spar_kto_gebuehr_laufend_betrag + "<" );
    wl( "e_spar_kto_gebuehr_laufend_bezeichnung        =>" + e_spar_kto_gebuehr_laufend_bezeichnung + "<" );
    wl( "e_spar_kto_gebuehr_laufend_datum_belastung    =>" + e_spar_kto_gebuehr_laufend_datum_belastung + "<" );
    wl( "e_spar_laufzeit_gesamt_monate                 =>" + e_spar_laufzeit_gesamt_monate + "<" );
    wl( "e_spar_wop_datum_junge_leute_regelung         =>" + e_spar_wop_datum_junge_leute_regelung + "<" );
    wl( "e_spar_wop_gebuehr_laufend_datum_belastung    =>" + e_spar_wop_gebuehr_laufend_datum_belastung + "<" );
    wl( "v_bestehend_ansp_ausstehend                   =>" + v_bestehend_ansp_ausstehend + "<" );
    wl( "v_bestehend_ansp_festgeschrieben              =>" + v_bestehend_ansp_festgeschrieben + "<" );
    wl( "v_bestehend_ansp_uj_vl_as                     =>" + v_bestehend_ansp_uj_vl_as + "<" );
    wl( "v_bestehend_ansp_uj_vl_ep                     =>" + v_bestehend_ansp_uj_vl_ep + "<" );
    wl( "v_bestehend_wop_gebuehr_gesamt                =>" + v_bestehend_wop_gebuehr_gesamt + "<" );
    wl( "v_bestehende_bwz                              =>" + v_bestehende_bwz + "<" );
    wl( "v_bestehende_saldensumme                      =>" + v_bestehende_saldensumme + "<" );
    wl( "v_wop_bestehender_betrag_festgesetzt          =>" + v_wop_bestehender_betrag_festgesetzt + "<" );
    wl( "zaehler_index                                 =>" + zaehler_index + "<" );
    wl( "max_sonder_zahlungen                          =>" + max_sonder_zahlungen + "<" );

    e_millisekunden_ende_vorabberechnungen = System.currentTimeMillis();
/* 
 * ##################################################################################################################
 * ##################################################################################################################
 * ##################################################################################################################
 * ##################################################################################################################
 */
    /* ##############################################################################################
     * #### ABSCHNITT ERSTELLUNG KONTOVERLAUF                                                    ####
     * ##############################################################################################
     * 
     * Erstellung Kontoverlauf
     * Der Kontoverlauf wird in einem zweistufigen Verfahren erstellt.
     * In der ersten Stufe werden die Verlaufselemente je Art dem Verlauf hinzugefuegt.
     * In der zweiten Stufe wird der Verlauf per Quick-Sort-Algorithmus sortiert.
     * 
     * Oder anders, die erste Phase definiert den Inhalt (Bestandteile des Kontoverlaufes),
     *              die zweite Phase definiert die korrekte Reihenfolge
     * 
     * Damit der chronologisch korrekte Verlauf erstellt werden kann, hat jedes
     * Verlaufselement ein Sortierungskennzeichen. Dieses Kennzeichen besteht aus
     * dem Datum im Format JJJJMMTT sowie der Verlaufsart.
     * 
     * Mit der Verlaufsart wird die Reihenfolge der Elemente an einem Datum bestimmt.
     * Die Verlaufsart ist eine Zahl. Gebuehren kommen vor Einzahlungen. Einzahlungen
     * kommen vor Zinsgutschriften/Belastungen.
     * 
     * Sollen Elemente geloescht werden, wird vor dem Sortierkennzeichen noch das
     * Loeschkennzeichen gesetzt. Element loeschen ist 1, Element behalten ist 0.
     * Es kommen erst die zu behaltenden Elemente und dann die zu loeschenden Elemente.
     * 
     * Die Benutzung des Quicksort-Algorithmus stellt die schnellste Art dar, den
     * Verlauf korrekt zu erstellen. Komplexe Einfuegeverfahren fuer die einzelnen
     * Verlaufselemente benoetigen mehr Zeit.
     */

    CalcKtoVerlaufVector kto_verlauf = null;

    //m_debug_knz_kto_verlauf_aufbau  = true;
    //m_debug_knz_kto_verlauf_rohform = true;

    long zd_end_datum = DATUM_0;
    long zd_kto_element_art = DATUM_0;
    double zd_kto_element_betrag_auszahlung = BETRAG_0;
    double zd_kto_element_betrag_einzahlung = BETRAG_0;
    double zd_kto_element_betrag_gebuehr = BETRAG_0;
    String zd_kto_element_bezeichnung = "";
    String zd_kto_element_id = "";
    long zd_kto_element_rhythmus = 0;
    long zd_start_datum = DATUM_0;

    kto_verlauf = new CalcKtoVerlaufVector();

    if ( e_rechenkern_fehler_nummer == BERECHNUNG_FEHLER_FREI )
    {
      c_millisekunden_start_1 = System.currentTimeMillis();

      /* 
       * Element fuer Start Sparphase
       */
      zd_start_datum = e_spar_datum_berechnungsbeginn_long; // Element beginnt am Datum des Berechnungsbeginns
      zd_end_datum = e_spar_datum_berechnungsbeginn_long; // Element endet am Datum des Berechnugnsbeginns
      zd_kto_element_art = KTO_ART_SPAR_PHASE_BEGINN; // Elementart ist Markier fuer den Start der Sparphase
      zd_kto_element_id = "ID_BERECHNUNGSBEGINN"; // die ID ist "ID_BERECHNUNGSBEGINN" 
      zd_kto_element_bezeichnung = "Berechnungsbeginn"; // die Bezeichnung ist "Berechnungsbeginn"
      zd_kto_element_betrag_einzahlung = BETRAG_0; // Es gibt keine Einzahlung
      zd_kto_element_betrag_auszahlung = BETRAG_0; // Es gibt keine Auszahlung
      zd_kto_element_betrag_gebuehr = BETRAG_0; // Es gibt keine Gebuehr

      calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, e_spar_datum_berechnungsbeginn_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

      /* 
       * Element fuer die Zuteilung
       */
      zd_start_datum = c_spar_datum_zuteilung_long;
      zd_end_datum = c_spar_datum_zuteilung_long;
      zd_kto_element_art = KTO_ART_SPAR_PHASE_ZUTEILUNG;
      zd_kto_element_id = "ID_ZUTEILUNG";
      zd_kto_element_bezeichnung = "Zuteilung";
      zd_kto_element_betrag_einzahlung = BETRAG_0;
      zd_kto_element_betrag_gebuehr = BETRAG_0;

      calcElemente( kto_verlauf, c_spar_datum_zuteilung_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

      /* 
       * Bewertungszahltage (Stichtage)
       * Die Bewertungszahltage kommen alle 3 Monate am Monatsende
       */
      zd_start_datum = c_datum_stichtag_start;
      zd_end_datum = c_datum_stichtag_ende;
      zd_kto_element_art = KTO_ART_BEWERTUNGSSTICHTAG;
      zd_kto_element_id = "ID_STICHTAG";
      zd_kto_element_bezeichnung = "Stichtag / BWZ Berechnung";
      zd_kto_element_betrag_einzahlung = BETRAG_0;
      zd_kto_element_betrag_gebuehr = BETRAG_0;

      calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, c_anzahl_monate_zwischen_stichtagen, ANZAHL_JAHRE_0, KNZ_TAG_MONATSENDE, ANZAHL_ELEMENTE_UNBEGRENZT, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

      /* 
       * Jaehrliche Zinsgutschrift
       * Vom Berechnungsbegindatum bis zum Monat vor der Zuteilung werden die
       * Elemente fuer die jaehrliche Zinsgutschrift hinzugefuegt. Eine Zinsgutschrift
       * wird jeweils am Jahresende (31.12. jeden Jahres) gemacht.
       * 
       * Die Betraege fuer die Zinsgutschriften werden innerhalb der Suchschleife
       * berechnet, darum werden die Elemente mit einem Betrag von 0 Euro erstellt.
       * 
       * End-Datum jaehrliche Zinsgutschrift
       * Es gibt eine Zinsgutschrift am Ende der Zuteilung. Ist das Zuteilungsdatum
       * der 31.12. wuerden an diesem Datum 2 Zinsgutschriftn erfolgen. Damit dieses
       * nicht passiert, wird vom Datum der Zuteilung ein 1 Tag abgezogen. Das ist
       * dann das Max-Datum fuer die jaehrlichen Zinsgutschriften.
       */
      zd_start_datum = FkDatumLong.getLongJahresEnde( e_spar_datum_berechnungsbeginn_long );
      zd_end_datum = FkDatumLong.longDateAdd( c_spar_datum_zuteilung_long, ANZAHL_JAHRE_0, ANZAHL_MONATE_0, ANZAHL_TAGE_MINUS_1, KNZ_TAG_UNVERAENDERT );
      zd_kto_element_art = KTO_ART_GUTSCHRIFT_ZINS_JAEHRLICH;
      zd_kto_element_bezeichnung = "Zinsgutschrift jaehrlich";
      zd_kto_element_id = "ID_ZINSGUTSCHRIFT_JAEHRLICH";
      zd_kto_element_betrag_einzahlung = BETRAG_0;
      zd_kto_element_betrag_gebuehr = BETRAG_0;

      calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_1, KNZ_TAG_MONATSENDE, ANZAHL_ELEMENTE_UNBEGRENZT, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

      /* 
       * Zinsgutschrift Zuteilung
       * Es gibt ein Element fuer die Zinsgutschrift am Zuteilungsdatum.
       * Das Datum fuer das Element ist eben das Zuteilungsdatum.
       */
      zd_start_datum = c_spar_datum_zuteilung_long;
      zd_end_datum = c_spar_datum_zuteilung_long;
      zd_kto_element_art = KTO_ART_GUTSCHRIFT_ZINS_ZUTEILUNG;
      zd_kto_element_id = "ID_ZINSGUTSCHRIFT_ZUTEILUNG";
      zd_kto_element_bezeichnung = "Zinsgutschrift Zuteilung";
      zd_kto_element_betrag_einzahlung = BETRAG_0;
      zd_kto_element_betrag_gebuehr = BETRAG_0;

      calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

      if ( e_spar_knz_bonus_moeglich )
      {
        zd_start_datum = c_spar_datum_zuteilung_long;
        zd_end_datum = c_spar_datum_zuteilung_long;
        zd_kto_element_art = KTO_ART_GUTSCHRIFT_BONUS_ZUTEILUNG;
        zd_kto_element_id = "ID_GUTSCHRIFT_BONUS";
        zd_kto_element_bezeichnung = "Bonusauszahlung bei Zuteilung";
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_betrag_gebuehr = BETRAG_0;

        calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );
      }

      if ( c_spar_ag_datum_belastung > DATUM_0 )
      {
        zd_start_datum = c_spar_ag_datum_belastung;
        zd_end_datum = c_spar_ag_datum_belastung;
        zd_kto_element_art = KTO_ART_AG_BELASTUNG;
        zd_kto_element_id = "ID_AG_BELASTUNG";
        zd_kto_element_bezeichnung = c_spar_ag_bezeichnung;
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_betrag_gebuehr = e_spar_ag_betrag;

        calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

        /* 
         * Pruefung: Begleichung AG separat?
         * Soll die Abschlussgebuehr durch eine separate Zahlung erfolgen, wird
         * eine Zahlung in Hoehe der AG in den Verlauf eingebaut. Dieses Verlaufselement
         * wird am Datum der AG-Belasung erstellt.
         * 
         * Ist das Berechnungsziel die Bausparsumme, wird der konkrete AG-Betrag
         * in der Berechnungsschleife gesetzt.
         */
        if ( c_spar_ag_art_begleichung == AG_ART_SEPARATE_ZAHLUNG )
        {
          zd_kto_element_art = KTO_ART_AG_BEGLEICHUNG_SEPARAT;
          zd_kto_element_id = "ID_AG_SEPARAT";
          zd_kto_element_bezeichnung = "Begleichung Abschlussgebuehr separat";
          zd_kto_element_betrag_einzahlung = e_spar_ag_betrag;
          zd_kto_element_betrag_gebuehr = BETRAG_0;

          calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );
        }
      }

      if ( e_spar_kto_gebuehr_anteilig_datum_belastung > DATUM_0 )
      {
        zd_start_datum = e_spar_kto_gebuehr_anteilig_datum_belastung;
        zd_end_datum = e_spar_kto_gebuehr_anteilig_datum_belastung;
        zd_kto_element_art = KTO_ART_KTO_GEBUEHR_ANTEILIG;
        zd_kto_element_id = "ID_KFG_ANTEILIG";
        zd_kto_element_bezeichnung = e_spar_kto_gebuehr_anteilig_bezeichnung;
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_betrag_gebuehr = e_spar_kto_gebuehr_anteilig_betrag;

        calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );
      }

      if ( e_spar_kto_gebuehr_laufend_datum_belastung > DATUM_0 )
      {
        zd_start_datum = e_spar_kto_gebuehr_laufend_datum_belastung;
        zd_end_datum = c_spar_datum_zuteilung_long;
        zd_kto_element_art = KTO_ART_KTO_GEBUEHR_SPAR_LAUFEND;
        zd_kto_element_id = "ID_KFG_LAUFEND";
        zd_kto_element_bezeichnung = e_spar_kto_gebuehr_laufend_bezeichnung;
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_rhythmus = m_rk_rhythmus_jaehrlich;
        zd_kto_element_betrag_gebuehr = e_spar_kto_gebuehr_laufend_betrag;

        calcElementeRhytmus( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, zd_kto_element_rhythmus, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_UNBEGRENZT, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );
      }

      /* 
       * Verlaufselemente: Arbeitnehmersparzulage
       */
      if ( e_spar_ansp_einmalig_datum_auszahlung != DATUM_0 )
      {
        zd_start_datum = e_spar_ansp_einmalig_datum_auszahlung;
        zd_end_datum = e_spar_ansp_einmalig_datum_auszahlung;
        zd_kto_element_art = KTO_ART_GUTSCHRIFT_ANSP_EINMALIG;
        zd_kto_element_id = "ID_ANSP_EINMALIG";
        zd_kto_element_bezeichnung = "Arbeitnehmersparzulage Einmalig";
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_rhythmus = m_rk_rhythmus_einmalig;
        zd_kto_element_betrag_gebuehr = BETRAG_0;

        calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_UNBEGRENZT, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

        /* 
         * Das Datum der einmaligen ANSP-Auszahlung liegt immer vor dem
         * Datum der laufenden Auszahlung. Die laufende ANSP-Auszahlung
         * wird im Folgejahr der ersten Auszahlung gemacht.
         * 
         * Dieses hat zur Folge, dass die beiden Verlaufselemente der
         * Art "KTO_ART_GUTSCHRIFT_ANSP_EINMALIG" und "KTO_ART_GUTSCHRIFT_ANSP_LAUFEND" nicht
         * zusammen an einem Tag anfallen.
         */
        zd_start_datum = ( FkDatumLong.getJahrAusLong( e_spar_ansp_einmalig_datum_auszahlung ) + 1 ) * 10000 + 331;
        zd_end_datum = c_spar_datum_zuteilung_long;
        zd_kto_element_art = KTO_ART_GUTSCHRIFT_ANSP_LAUFEND;
        zd_kto_element_id = "ID_ANSP_LAUFEND";
        zd_kto_element_bezeichnung = "Arbeitnehmersparzulage laufend";
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_betrag_auszahlung = BETRAG_0;
        zd_kto_element_rhythmus = m_rk_rhythmus_jaehrlich;
        zd_kto_element_betrag_gebuehr = BETRAG_0;

        calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_1, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_UNBEGRENZT, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );
      }

      /* 
       * Verlaufselemente: Wohnungsbaupraemie
       */
      if ( e_spar_wop_gebuehr_laufend_datum_belastung > DATUM_0 )
      {
        zd_start_datum = e_spar_wop_gebuehr_laufend_datum_belastung;
        zd_end_datum = c_spar_datum_zuteilung_long;
        zd_kto_element_art = KTO_ART_GEBUEHR_WOP_LAUFEND;
        zd_kto_element_id = "ID_WOP_GEB_LAUFEND";
        zd_kto_element_bezeichnung = "Wop-Geb. laufend";
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_betrag_auszahlung = BETRAG_0;
        zd_kto_element_betrag_gebuehr = c_spar_wop_gebuehr_laufend;

        calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_1, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_UNBEGRENZT, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

        if ( e_spar_wop_datum_junge_leute_regelung > DATUM_0 )
        {
          /* 
           * Element fuer Auszahlung der festgesetzten WOPs mit der Jungen Leute Regelung
           */
          zd_start_datum = e_spar_wop_datum_junge_leute_regelung;
          zd_end_datum = e_spar_wop_datum_junge_leute_regelung;
          zd_kto_element_art = KTO_ART_GUTSCHRIFT_WOP_JUNGE_LEUTE_REGELUNG;
          zd_kto_element_id = "ID_WOP_AUSZAHLUNG_JLR";
          zd_kto_element_bezeichnung = "WOP Auszahlung - Junge Leute Reglung";
          zd_kto_element_betrag_einzahlung = BETRAG_0;
          zd_kto_element_betrag_auszahlung = BETRAG_0;
          zd_kto_element_betrag_gebuehr = BETRAG_0;

          calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );
        }

        /* 
         * Element fuer Auszahlung der festgesetzten WOPs am Zuteilungstag
         */
        zd_start_datum = c_spar_datum_zuteilung_long;
        zd_end_datum = c_spar_datum_zuteilung_long;
        zd_kto_element_art = KTO_ART_GUTSCHRIFT_WOP_ZUTEILUNG;
        zd_kto_element_id = "ID_WOP_AUSZAHLUNG";
        zd_kto_element_bezeichnung = "WOP Auszahlung";
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_betrag_auszahlung = BETRAG_0;
        zd_kto_element_betrag_gebuehr = BETRAG_0;

        calcElemente( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );
      }

      /*
       * Verlaufselemente Sparbeitrag
       */

      /*
       * Aus den Vorgaben wird das Enddatum des Sparbeitrags gelesen. 
       * Ist das Enddatum kleiner als das Datum 0, wurde kein Enddatum fuer 
       * den Sparbeitrag uebergeben worden. In so einem Fall wird als 
       * End-Datum das berechnete Zuteilungsdatum des Vertrages genommen. 
       */
      bsv_sparbeitrag_datum_bis = pCalcBsvEingabe.getRkInputZahlungSparbeitrag().getDatumBis();

      if ( bsv_sparbeitrag_datum_bis < DATUM_0 )
      {
        bsv_sparbeitrag_datum_bis = c_spar_datum_zuteilung_long;
      }

      bsv_sparbeitrag_bezeichnung = "BSV Sparrate " + bsv_sparbeitrag_betrag;

      zd_start_datum = bsv_sparbeitrag_datum_ab;
      zd_end_datum = ( ( bsv_sparbeitrag_datum_bis > DATUM_0 ) && ( bsv_sparbeitrag_datum_bis < c_spar_datum_zuteilung_long ) ? bsv_sparbeitrag_datum_bis : c_spar_datum_zuteilung_long );
      zd_kto_element_art = KTO_ART_BSV_SPARBEITRAG;
      zd_kto_element_id = "ID_SPAR_BEITRAG";
      zd_kto_element_bezeichnung = bsv_sparbeitrag_bezeichnung;
      zd_kto_element_betrag_einzahlung = bsv_sparbeitrag_betrag;
      zd_kto_element_rhythmus = getRhythmusKonstante( pCalcBsvEingabe.getRkInputZahlungSparbeitrag().getRhythmus() );
      zd_kto_element_betrag_gebuehr = BETRAG_0;

      calcElementeRhytmus( kto_verlauf, e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long, zd_start_datum, zd_end_datum, zd_kto_element_rhythmus, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_UNBEGRENZT, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

      /*
       * Elemente VL-Zahlung Antragsteller
       */
      calcElementeZahlung( kto_verlauf, KTO_ART_VL_ANTRAGSTELLER, pCalcBsvEingabe.getRkInputZahlungVlAs(), e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long );

      /*
       * Elemente VL-Zahlung Ehepartner
       */
      calcElementeZahlung( kto_verlauf, KTO_ART_VL_EHEPARTNER, pCalcBsvEingabe.getRkInputZahlungVlEp(), e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long );

      /*
       * Elemente Sonderzahlungen
       * Die Sonderzahlungen werden ueber eine While-Schleife hinzugefuegt.
       */
      zaehler_index = 0;

      while ( zaehler_index < max_sonder_zahlungen )
      {
        calcElementeZahlung( kto_verlauf, KTO_ART_SONDERZAHLUNG, pCalcBsvEingabe.getSonderzahlung( zaehler_index ), e_spar_datum_berechnungsbeginn_long, c_spar_datum_zuteilung_long );

        zaehler_index = zaehler_index + 1;
      }

      if ( m_debug_knz_kto_verlauf_rohform )
      {
        wl( "KtoVerlauf Erstellung: " );
        wl( DebugCalcBsvKtoVerlauf.getGridString( kto_verlauf, B_TRUE, DebugCalcBsvKtoVerlauf.AUSGABE_ART_TEXT ) );
        wl( "KtoVerlauf Erstellung: " );
      }

      /* 
       * Die zeitlich korrekte Reihenfolge der Kontoverlaufselemente, wird am 
       * Abschluss durch Sortierung erstellt.
       */
      kto_verlauf.startSort();
    }

    e_millisekunden_ende_sparphase_kto_verlauf = System.currentTimeMillis();

    /* 
     * ##################################################################################################################
     * ##################################################################################################################
     * ##################################################################################################################
     * ##################################################################################################################
     */
    /* ##############################################################################################
     * #### ABSCHNITT BERECHNUNG SPARPHASE                                                       ####
     * ##############################################################################################
      */

//    m_debug_knz_bwz = B_TRUE;
//
//    m_debug_knz_zins_berechnung_bsd = B_TRUE;
//
//    m_debug_knz_zins_berechnung_spar = B_TRUE;
//
//    m_debug_knz_ansp_berechnung = B_TRUE;
//
    m_debug_knz_berechne_bsd = B_TRUE;

    m_debug_knz_berechnungsziel_schritte = B_TRUE;

//    m_debug_knz_wop_berechnung = B_TRUE;

    CalcKtoVerlaufElement temp_element = null;

    CalcKtoVerlaufElement akt_element = null;

    long c_aktueller_index = INDEX_0;

    double c_alter_berechneter_sparbeitrag = BETRAG_0;

    double c_alter_berechneter_calc_sparbeitrag_x = BETRAG_0;

    double c_ansp_akt_jahr_foerderung = BETRAG_0;

    double c_ansp_aktuelles_jahr = BETRAG_0;

    double c_ansp_anzahl_jahre = BETRAG_0;

    boolean c_ansp_berechnen = B_FALSE;

    double c_ansp_berechnung_basis = BETRAG_0; // Gesamt VL Antragsteller und Ehepartner unter mindestbetragsberuecksichtigung

    double c_ansp_betrag_ausstehend = BETRAG_0;

    double c_ansp_betrag_gutgeschrieben = BETRAG_0;

    double c_ansp_betrag_jahr_vl_as = BETRAG_0; // Summe der VL des Antragstellers im Jahr

    double c_ansp_betrag_jahr_vl_ep = BETRAG_0; // Summe der VL des Ehepartners im Jahr

    double c_ansp_betrag_max_vl = BETRAG_0;

    double c_ansp_betrag_min_vl = BETRAG_0;

    double c_ansp_betrag_prozent_auf_vl = BETRAG_0;

    long c_anzahl_bsv_sparraten_bis_spar_stopp = ANZAHL_0;

    double c_anzahl_faktor_tage_im_jahr = BETRAG_0;

    long c_anzahl_stichtage_bis_zum_massgeblichen_stichtag = ANZAHL_0;

    double c_betrag_akt_bewertungszahl = BETRAG_0;

    double c_betrag_akt_bonus_kto = BETRAG_0;

    double c_betrag_akt_bonus_zinsen = BETRAG_0;

    double c_betrag_akt_grund_zinsen = BETRAG_0;

    double c_betrag_akt_kontostand = BETRAG_0;

    double c_betrag_akt_saldensumme = BETRAG_0;

    double c_betrag_akt_uj_bonus_zinsen = BETRAG_0;

    double c_betrag_akt_uj_grund_zinsen = BETRAG_0;

    double c_betrag_bss_berechnet = BETRAG_0;

    double c_betrag_bss_kto_stand_stichtag = BETRAG_0;

    double c_betrag_bss_obergrenze_berechnet = BETRAG_0;

    double c_betrag_bss_saldensumme_stichtag = BETRAG_0;

    double c_betrag_bss_untergrenze_reduzierung = BETRAG_0;

    double c_betrag_ges_bonus_zinsen = BETRAG_0;

    double c_betrag_ges_bsv_sparbeitrag = BETRAG_0;

    double c_betrag_ges_guthaben_zinsen = BETRAG_0;

    double c_betrag_ges_kto_gebuehren_spar = BETRAG_0;

    double c_betrag_ges_sonderzahlung = BETRAG_0;

    double c_betrag_ges_vl_antragsteller = BETRAG_0;

    double c_betrag_ges_vl_ehepartner = BETRAG_0;

    double c_betrag_ges_zinsen_spar = BETRAG_0;

    double c_calc_rate_differenz_zu_altem_sparbeitrag = BETRAG_0;

    double c_betrag_raten_spar_leistung = BETRAG_0;

    double c_betrag_summe_einzahlungen = BETRAG_0;

    double c_betrag_summe_sparleistung_anderweitig = BETRAG_0;

    double c_bonus_abschluss_1_vorsaldo = BETRAG_0;

    double c_bonus_betrag_akt_kontostand = BETRAG_0;

    double c_bonus_gutgeschrieben = BETRAG_0;

    boolean c_bonus_knz_berechnung = B_FALSE;

    double c_bss_aufgrunlage_sparrate = BETRAG_0;

    double c_bss_vor_reduzierung = BETRAG_0;

    double c_calc_bss_ag_zu_leisten_neu = BETRAG_0;

    double c_calc_bss_fehlbetrag_umgerechnet_in_bss = BETRAG_0;

    double c_calc_bss_fehlbetrag_zu_migu_neu = BETRAG_0;

    boolean c_calc_bss_knz_set_ag_betrag = B_FALSE;

    boolean c_calc_laufzeit_knz_loesche_element = B_FALSE;

    double c_calc_rate_differenz_zu_soll_betrag = BETRAG_0;

    boolean c_calc_rate_kein_sparbeitrag_notwendig = B_FALSE;

    boolean c_calc_rate_keine_weitere_iteration = B_FALSE;

    long c_datum_letzter_stichtag = DATUM_0;

    double c_inv_faktor_bss_berechnet = BETRAG_0;

    long c_rk_anzahl_elemente = ANZAHL_0;

    boolean c_rk_knz_weiterer_iterationslauf_notwendig = B_FALSE;

    boolean c_rk_knz_zinsberechnung_durchfuehren = B_FALSE;

    long c_rk_temp_index = INDEX_0;

    long c_rk_zaehler_iteration = ANZAHL_0;

    double c_spar_mindest_sparrate_wegen_bss = BETRAG_0;

    long c_datum_sparstopp_sparrate_long = DATUM_0;

    boolean c_sparstopp_knz_erreicht = B_FALSE;

    double c_stichtag_betrag_bewertungszahl = BETRAG_0;

    double c_stichtag_betrag_foerderung = BETRAG_0;

    double c_stichtag_betrag_gebeuhren_kfg = BETRAG_0;

    double c_stichtag_betrag_gebeuhren_wop = BETRAG_0;

    double c_stichtag_betrag_kontostand = BETRAG_0;

    double c_stichtag_betrag_saldensumme = BETRAG_0;

    double c_stichtag_betrag_soll_guthaben = BETRAG_0;

    double c_stichtag_betrag_sonderzahlung = BETRAG_0;

    double c_stichtag_betrag_sparbeitrag = BETRAG_0;

    double c_stichtag_betrag_vl_as = BETRAG_0;

    double c_stichtag_betrag_vl_ep = BETRAG_0;

    double c_stichtag_betrag_zinsen = BETRAG_0;

    double c_stichtag_betrag_zinsen_alt = BETRAG_0;

    long c_tages_abschluss_datum = DATUM_0;

    double c_tages_abschluss_saldo = BETRAG_0;

    long c_tarif_migu_datum_long = DATUM_0;

    boolean c_tarif_migu_knz_erreicht = B_FALSE;

    long c_tarif_minbwz_datum_long = DATUM_0;

    boolean c_tarif_minbwz_knz_erreicht = B_FALSE;

    boolean c_tarif_mindest_sparzeit_knz_erreicht = B_FALSE;

    long c_tarif_ztv_datum_long = DATUM_0;

    boolean c_tarif_ztv_knz_erreicht = B_FALSE;

    double c_tariflicher_regel_sparbeitrag = BETRAG_0;

    double c_wop_akt_jahr_foerderung = BETRAG_0;

    double c_wop_aktuelles_jahr = BETRAG_0;

    boolean c_wop_knz_berechnen = B_FALSE;

    double c_wop_berechnung_basis = BETRAG_0;

    double c_wop_betrag_festgesetzt = BETRAG_0;

    double c_wop_betrag_gutgeschrieben = BETRAG_0;

    double c_wop_betrag_mindest_einzahlung = BETRAG_0;

    double c_wop_betrag_wop_gebuehr_gesamt = BETRAG_0;

    double c_wop_foerder_max_betrag_gesamt_ledig = BETRAG_0;

    double c_wop_foerder_max_betrag_gesamt_verheiratet = BETRAG_0;

    double c_wop_foerderbetrag_prozent = BETRAG_0;

    boolean c_wop_knz_aktiv = B_FALSE;

    double c_wop_kto_stand_altes_jahr = BETRAG_0;

    double c_wop_max_einzahlung_je_jahr_beruecksichtigen = BETRAG_0;

    double c_wop_vl_zahlungen_ansp = BETRAG_0;

    long c_zins_abschluss_1_tages_diff = ANZAHL_0;

    double c_zins_abschluss_1_vorsaldo = BETRAG_0;

    long c_zins_abschluss_datum_berechnet = DATUM_0;

    long c_zins_abschluss_end_index = INDEX_0;

    long c_zins_abschluss_index_ab = INDEX_0;

    long c_zins_datum_fuer_tages_diff = DATUM_0;

    double kto_stand_stichtag_korrigiert = BETRAG_0;

    double e_spar_zuteilung_kontostand = BETRAG_0;
    double e_sparbeitrag_mgh = BETRAG_0;
    double e_sparbeitrag_bwz = BETRAG_0;
    double c_calc_bwz_suche_untergrenze = BETRAG_0;
    double c_calc_bwz_suche_obergrenze = BETRAG_0;
    long c_calc_bwz_suche_it_zaehler = ANZAHL_0;

    double c_calc_bwz_suche_alter_diff_betrag = BETRAG_0;
    double betrag_minder_bwz_diff = BETRAG_0;
    double betrag_minder_bwz_salden_summe = BETRAG_0;
    double betrag_minder_bwz_fehlbetrag_je_ss = BETRAG_0;
    long c_calc_bwz_suche_max_iteration = MAX_ITERATION_CALC_RATE_BWZ;

    long c_spar_start_index = kto_verlauf.getIndexByArt( KTO_ART_SPAR_PHASE_BEGINN );

    if ( ( e_rechenkern_fehler_nummer == BERECHNUNG_FEHLER_FREI ) && ( c_spar_start_index == INDEX_NICHT_VORHANDEN ) )
    {
      wl( "Grober Fehler, der nicht passieren darf. Das Startelement fuer die Sparphase wurde nicht gefunden." );

      e_rechenkern_fehler_nummer = FEHLER_START_ELEMENT_SPAR_NICHT_GEFUNDEN;
    }

    if ( e_rechenkern_fehler_nummer == BERECHNUNG_FEHLER_FREI )
    {
      c_calc_rate_kein_sparbeitrag_notwendig = B_FALSE; // Fehler: Variablenname hat ein negatives Wort. Optimierung: Variablename positiv benennen.

      c_calc_rate_keine_weitere_iteration = B_FALSE; // Fehler: Variablenname hat ein negatives Wort. Optimierung: Variablename positiv benennen.

      if ( c_rk_berechnungsziel == BZ_BAUSPARSUMME )
      {
        c_betrag_bss_berechnet = BETRAG_0;

        e_spar_betrag_tarifliche_abschlussgebuehr = BETRAG_0;

        e_spar_betrag_tarifliches_mindestsparguthaben = 99999999.99;

        c_betrag_bss_obergrenze_berechnet = 999999999.99;
      }
      else
      {
        c_betrag_bss_berechnet = c_spar_betrag_bausparsumme;

        c_tariflicher_regel_sparbeitrag = round2DP( getTausender( c_spar_betrag_bausparsumme ) * tarif_regel_sparbeitrag );
      }

      if ( c_rk_berechnungsziel == BZ_LAUFZEIT )
      {
        c_spar_datum_stichtag_long = DATUM_0;
      }

      /* 
       * Werte fuer die Arbeitnehmersparzulage setzen
       */
      c_ansp_betrag_prozent_auf_vl = ANSP_PROZENT_FOERDERUNG;
      c_ansp_betrag_min_vl = ANSP_BETRAG_MINDEST_VL_EINZAHLUNG;
      c_ansp_betrag_max_vl = ANSP_BETRAG_MAXIMAL_VL_EINZAHLUNG;

      /* 
       * Werte fuer die Wop-Foerderung setzen
       */
      c_wop_betrag_mindest_einzahlung = WOP_MINDEST_EINZAHLUNG;
      c_wop_foerder_max_betrag_gesamt_ledig = WOP_MAX_FORDERBETRAG_LEDIG;
      c_wop_foerder_max_betrag_gesamt_verheiratet = WOP_MAX_FORDERBETRAG_VERHEIRATET;
      c_wop_foerderbetrag_prozent = WOP_PROZENT_FOERDERUNG;
      c_wop_knz_aktiv = pCalcBsvEingabe.getRkInputKnzWopBerechnen();

      /* 
       * WOP Maximalgrenzen
       * 
       * Ist in den Eingaben das Kennzeichen fuer die WOP-Grenzen "Verheiratet" gesetzt,
       * werden die Grenzen fuer Verheiratet gesetzt.
       * 
       * Per Vorgabe werden die Grenzen fuer Ledig gesetzt.
       */
      if ( pCalcBsvEingabe.getRkInputKnzWopVerheiratet() )
      {
        c_wop_max_einzahlung_je_jahr_beruecksichtigen = c_wop_foerder_max_betrag_gesamt_verheiratet;

        if ( c_debug_knz_wop_berechnung )
        {
          wl( "WOP: Grenzen \"VERHEIRATET\" : c_wop_max_einzahlung_je_jahr_beruecksichtigen " + c_wop_max_einzahlung_je_jahr_beruecksichtigen );
        }
      }
      else
      {
        c_wop_max_einzahlung_je_jahr_beruecksichtigen = c_wop_foerder_max_betrag_gesamt_ledig;

        if ( c_debug_knz_wop_berechnung )
        {
          wl( "WOP: Grenzen \"LEDIG\" : c_wop_max_einzahlung_je_jahr_beruecksichtigen " + c_wop_max_einzahlung_je_jahr_beruecksichtigen );
        }
      }

      e_spar_iteratonsziel_mindestsparguthaben = e_spar_betrag_tarifliches_mindestsparguthaben;

      c_tarif_minbwz_betrag = tarif_mindest_bwz;

      /* 
       * Untergrenze Bausparsummenreduzierung
       * Beim Berechnungsziel Bausparsumme kann es passieren, dass die Bausparsumme
       * sukzessive reduziert wird. Die Untergrenze ist hier eben die tarifliche
       * Mindestbausparsumme zuzueglich einmal einen Reduzierungsbetrag
       * 
       * Falsch: Es muss eine Reduzierung bis 0 stattfinden, da ansonsten nicht
       * festgesgtellt werden kann, dass keine Bausparsumme berechnet werden kann.
       */
      c_betrag_bss_untergrenze_reduzierung = tarif_mindest_bss - BETRAG_1000;

      c_stichtag_betrag_zinsen_alt = BETRAG_0;

      /* 
       * Im ersten Durchgang wird beim Berechnungsziel BSS noch nicht die AG gesetzt
       */
      c_calc_bss_knz_set_ag_betrag = B_FALSE;

      /* 
       * Kennzeichen Bonusberechnung
       * Eine Bonusberechnung muss nur gemacht werden, wenn vom Tarif her ein
       * Bonuszinssatz vorhanden ist.
       * 
       * Es wird geprueft, ob die entsprechende Tarifvariable einen Wert groesser als 0 hat.
       * 
       * Da es passieren kann, dass die Bonusberechnung in der Berechnungschleife auf
       * B_FALSE gestellt wird (= Bonuszeitraum abgelaufen), muss diese Variable hier
       * wieder neu initialisiert werden.
       */
      c_bonus_knz_berechnung = tarif_proz_bonus_zins > PROZENT_0;

      /*
       * Iterationsschleife
       * 
       * Die Iterationsschleife hat 2 Aufgaben:
       * 
       * 1. Durchrechnen des Kontoverlaufes und Erstellung der Ausgabewerte
       * 
       * 2. Bestimmung der Zielerreichung des Berechnungszieles
       *      - Rate
       *      - Laufzeit
       *      - Bausparsumme
       *      - Vertragssimulation
       *      
       *   Bei der Bestimmung der Zielerreichung geht es immer darum, ob 
       *   der Bausparvertrag zugeteilt werden kann.
       * 
       * Die Iterationsschleife umschliesst die Berechnung der Sparphase, an 
       * dessen Ende die Auswertung der Berechnungsergebnisse durchgefuehrt 
       * werden. Innerhalb der Berechnungsauswertung wird das Kennzeichen 
       * fuer den Abbruch der Iterationsschleife gemacht.
       * 
       * Damit das konkrete Berechnungsziel gefunden werden kann, sind mehrere
       * Iterationen notwendig. Dieses wird mit der Iterationsschleife erledigt.
       * 
       * Die Iterationsschleife muss nur ausgefuehrt werden, wenn die vorhergehenden
       * Berechnungen und Pruefungen bestanden worden sind. Es wird die Variable fuer
       * die Fehlernummer auf den Wert "fehlerfrei" geprueft. Liegt aus den vorhergehenden
       * Pruefungen ein Fehler vor, wird auch die Variable fuer die Steuerung der 
       * Berechnungsschleife auf FALSE stehen. Somit wuerde die Berechnungsschleife 
       * nicht gestartet werden, die Ergebnisse aus der Berechnungsschleife stehen 
       * dann auf ihren Vorgabewerten.
       */
      c_rk_knz_weiterer_iterationslauf_notwendig = e_rechenkern_fehler_nummer == BERECHNUNG_FEHLER_FREI;

      /* 
       * Der Iterationszaehler wird auf 1 gestellt.
       */
      c_rk_zaehler_iteration = ITERATION_1;

      /* 
       * Solange die Flag-Variablen fuer einen weiteren Schleifendurchlauf auf 
       * True steht, wird die Iterationsschleife ausgefuehrt. 
       */
      while ( c_rk_knz_weiterer_iterationslauf_notwendig )
      {
        c_millisekunden_start_1 = System.currentTimeMillis();

        /* 
         * Initialisierung Variablen der Berechnungsschleife
         * Vor jedem Durchlauf der Berechnungsschleife werden die Berechnungsvariablen
         * auf den ihren Startwert gestellt.
         */

        /* 
         * Zuruecksetzen der Summenvariablen
         */
        c_betrag_akt_bonus_zinsen = BETRAG_0;
        c_betrag_akt_grund_zinsen = BETRAG_0;
        c_betrag_akt_kontostand = c_betrag_kontostand_init;
        c_betrag_akt_uj_grund_zinsen = BETRAG_0;
        c_betrag_akt_bonus_kto = BETRAG_0;
        c_betrag_ges_bonus_zinsen = BETRAG_0;
        c_betrag_ges_bsv_sparbeitrag = BETRAG_0;
        c_betrag_ges_guthaben_zinsen = BETRAG_0;
        c_betrag_ges_kto_gebuehren_spar = BETRAG_0;
        c_betrag_ges_sonderzahlung = BETRAG_0;
        c_betrag_ges_vl_antragsteller = BETRAG_0;
        c_betrag_ges_vl_ehepartner = BETRAG_0;
        c_betrag_ges_zinsen_spar = BETRAG_0;
        c_betrag_summe_einzahlungen = BETRAG_0;

        c_bonus_abschluss_1_vorsaldo = BETRAG_0;
        c_bonus_betrag_akt_kontostand = BETRAG_0;
        c_bonus_gutgeschrieben = BETRAG_0;

        c_tages_abschluss_datum = DATUM_0;
        c_tages_abschluss_saldo = BETRAG_0;

        /* 
         * Startwerte fuer die Wohnungsbaufoerderung
         */
        c_wop_aktuelles_jahr = e_spar_datum_berechnungsbeginn_jahr;
        c_wop_betrag_wop_gebuehr_gesamt = v_bestehend_wop_gebuehr_gesamt;
        c_wop_betrag_festgesetzt = v_wop_bestehender_betrag_festgesetzt;
        c_wop_betrag_gutgeschrieben = BETRAG_0;
        c_wop_kto_stand_altes_jahr = BETRAG_0;
        c_wop_vl_zahlungen_ansp = BETRAG_0;

        /* 
         * Startwerte Arbeitnehmersparzulage
         * Die Betraege werden mit den bestehenden Werten initialisiert.
         * Das Startjahr fuer die Arbeitnehmersparzulage ist das Berechnungsbeginnjahr.
         */
        c_ansp_aktuelles_jahr = e_spar_datum_berechnungsbeginn_jahr;
        c_ansp_anzahl_jahre = ANZAHL_0;
        c_ansp_betrag_gutgeschrieben = v_bestehend_ansp_festgeschrieben;
        c_ansp_betrag_ausstehend = v_bestehend_ansp_ausstehend;
        c_ansp_betrag_jahr_vl_as = v_bestehend_ansp_uj_vl_as;
        c_ansp_betrag_jahr_vl_ep = v_bestehend_ansp_uj_vl_ep;

        /* 
         * Startwerte fuer Saldensumme und Bewertungszahl
         * 
         * Die Startwerte sind bei einer Neuvertragsberechnung 0.
         * Bei Bestandsberechnungen sind es die Werte des Bestandsvertrag.
         */
        c_betrag_akt_saldensumme = v_bestehende_saldensumme;
        c_betrag_akt_bewertungszahl = v_bestehende_bwz;

        /* 
         * Kennzeichen "Mindestlaufzeit erreicht" zuruecksetzen
         */
        c_tarif_mindest_sparzeit_knz_erreicht = B_FALSE;

        /* 
         * Kennzeichen und Datum fuer "Mindestguthaben erreicht" zuruecksetzen
         * Das Datum wird auf 0 und die Kennzeichenvariable auf FALSE gesetzt.
         */
        c_tarif_migu_datum_long = DATUM_0;
        c_tarif_migu_knz_erreicht = B_FALSE;

        /* 
         * Kennzeichen und Datum fuer "Mindestbewertungszahl erreicht" zuruecksetzen.
         * Das Datum wird auf 0 und die Kennzeichenvariable auf FALSE gesetzt.
         */
        c_tarif_minbwz_datum_long = DATUM_0;
        c_tarif_minbwz_knz_erreicht = B_FALSE;

        /* 
         * Kennzeichen und Datum fuer "Zuteilungsvorraussetzungen erreicht" zuruecksetzen.
         * Das Datum wird auf 0 und die Kennzeichenvariable auf FALSE gesetzt.
         */
        c_tarif_ztv_datum_long = DATUM_0;
        c_tarif_ztv_knz_erreicht = B_FALSE;

        /* 
         * Sparstopp
         * Kennzeichen und Datum fuer "Sparstopp erreicht" zuruecksetzen.
         * 
         * In der Suchschleife gibt es 2 Moeglichkeiten den Sparstopp zu erreichen:
         * - Mindestsparguthaben
         * - Zuteilungsvorraussetzungen
         * 
         * Ist der Sparstopp das Zuteilungsdatum, muss die Kennzeichenvariable
         * in der Suchschleife nicht auf TRUE gestellt werden, da in der gesamten
         * Sparphase gespart wird und es somit zu keinem Sparstopp kommt.
         */
        c_datum_sparstopp_sparrate_long = DATUM_0;
        c_sparstopp_knz_erreicht = B_FALSE;

        /* 
         * Stichtagswerte zuruecksetzen.
         * Startwert fuer alle Variablen ist 0. Die Werte werden in der Suchschleife gesetzt,
         * wenn die tariflichen Zuteilungsvorraussetzungen erfuellt worden sind und das 
         * Datum des aktuellen Stichtages das relevante Datum fuer die Zuteilung ist.
         */
        c_stichtag_betrag_bewertungszahl = BETRAG_0;
        c_stichtag_betrag_foerderung = BETRAG_0;
        c_stichtag_betrag_gebeuhren_kfg = BETRAG_0;
        c_stichtag_betrag_gebeuhren_wop = BETRAG_0;
        c_stichtag_betrag_kontostand = BETRAG_0;
        c_stichtag_betrag_kontostand = BETRAG_0;
        c_stichtag_betrag_saldensumme = BETRAG_0;
        c_stichtag_betrag_sonderzahlung = BETRAG_0;
        c_stichtag_betrag_vl_as = BETRAG_0;
        c_stichtag_betrag_vl_ep = BETRAG_0;
        c_stichtag_betrag_zinsen = BETRAG_0;

        /* 
         * Zaehlvariablen zuruecksetzen.
         */
        c_anzahl_stichtage_bis_zum_massgeblichen_stichtag = DAUER_0;
        c_anzahl_bsv_sparraten_bis_spar_stopp = ANZAHL_0;

        /* 
         * Zinsberechnungsvariablen zuruecksetzen
         */
        c_rk_knz_zinsberechnung_durchfuehren = B_FALSE;
        c_zins_abschluss_1_tages_diff = ANZAHL_0;
        c_zins_abschluss_1_vorsaldo = BETRAG_NICHT_GESETZT;
        c_zins_abschluss_datum_berechnet = DATUM_0;
        c_zins_abschluss_index_ab = INDEX_1;
        c_zins_datum_fuer_tages_diff = DATUM_0;

        /* 
         * Berechnungsschleife
         * 
         * Die Berechnungsschleife geht ueber die gesamte Anzahl der Vektorelemente
         * des Kontoverlaufes. Bei jedem Durchlauf wird eine "Simulation" der Entwicklung
         * des Bausparverlaufes durchgefuehrt.
         */
        c_aktueller_index = INDEX_0;

        /* 
         * Es werden die Anzahl der Vektorelemente geholt. Die Anzahl kann sich bei dem
         * Berechnungsziel "Laufzeit" reduzieren, da dort ueberfluessige Elemente nach
         * dem ersten Lauf geloescht werden.
         */
        c_rk_anzahl_elemente = kto_verlauf.getAnzahl();

        if ( c_betrag_bss_berechnet > BETRAG_0 )
        {
          c_inv_faktor_bss_berechnet = 1.0 / c_betrag_bss_berechnet;
        }

        if ( m_debug_knz_berechnungsziel_schritte )
        {
          wl( "" );
          wl( "######################################################################################" );
          wl( "" );
          wl( "START_SUCHSCHLEIFE: c_betrag_bss_berechnet                        =>" + c_betrag_bss_berechnet );
          wl( "START_SUCHSCHLEIFE: c_spar_datum_mindestsparzeit_long             =>" + FkDatumLong.getStringAuslong( c_spar_datum_mindestsparzeit_long ) );
          wl( "START_SUCHSCHLEIFE: e_spar_betrag_tarifliches_mindestsparguthaben =>" + e_spar_betrag_tarifliches_mindestsparguthaben );
          wl( "START_SUCHSCHLEIFE: c_tarif_minbwz_betrag                         =>" + c_tarif_minbwz_betrag );
        }

        /*
         * While-Schleife ueber alle Elemente des erstellten Kontoverlaufes.
         */
        while ( c_aktueller_index < c_rk_anzahl_elemente )
        {
          akt_element = kto_verlauf.getIndex( c_aktueller_index );

          /* ###################################################################################
           * 
           * ABSCHNITT 1: Vervollstaendigung des aktuellen Elementes
           * - setzen der uerspruenglichen Hoehe des Einzahlungsbetrages
           * - setzen des aktuell gueltigen Sparbeitrages, wenn das Berechnungsziel die Sparrate ist
           * - setzen der aktuellen AG-Hoehe, wenn das Berechnungsziel die Bausparsumme ist
           * - setzen der aktuellen AG-Hoehe in die evtl. vorhandene separate Zahlung, wenn
           *   das Berechnungsziel die Bausparsumme ist.
           * - ausnuellen des Einzahlungsbetrages, wenn der Sparstopp erreicht worden ist
           * 
           * ###################################################################################
           * 
           * Reset Einzahlungsbetrag
           * Aus vorhergehenden Laeufen kann der Einzahlungsbetrag auf 0 gestellt worden sein.
           * Der Einzahlungsbetrag wird per Aufruf der Funktion "resetBetragEinzahlung" wieder
           * auf den urspruenglichen Wert gestellt.
           */
          akt_element.resetBetragEinzahlung();

          /* 
           * Reset Loeschkennzeichen
           * Bei jedem Element wird das Loeschkennzeichen initial wieder auf "Element behalten" gesetzt. 
           * Es ist die Aufgabe von nachgelagerten Pruefungen, das Loeschkennzeichen wieder "Element loeschen" zu setzen.
           */
          akt_element.setKnzLoeschung( KNZ_ELEMENT_BEHALTEN );

          /* 
           * Pruefung: Sparrate setzen?
           * Bei dem Berechnungsziel "Sparrate" muss fuer ein Verlaufselement
           * der Art "SPARBEITRAG" der aktuelle Wert fuer die Sparrate gesetzt
           * werden. Da die Sparratenhoehe auch in der Bezeichnung aufgefuehrt wird,
           * wird die Bezeichnung des Verlaufselementes auch angepasst.
           * 
           * Das setzen des Sparbeitrages wird an dieser Stelle immer gemacht.
           * Sollte der Sparstopp erreicht worden und deshalb auch kein
           * Sparbeitrag mehr notwendig sein, wird der Sparbeitrag von der
           * naechsten Folgepruefung wieder auf 0 gestellt.
           */
          if ( ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_GUTHABEN_RATE ) || ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_POSITIV ) || ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_NEGATIV ) || ( c_rk_berechnungsziel == BZ_SPARRATE_BERECHNUNGSSCHLEIFE_3 ) )
          {
            if ( akt_element.getArt() == KTO_ART_BSV_SPARBEITRAG )
            {
              akt_element.setBetragEinzahlung( bsv_sparbeitrag_betrag );
            }
          }

          /* 
           * Loeschung von Verlaufselementen
           * Nach erreichen des Sparstopps werden die Verlaufselemente der Art
           * 
           * - KTO_ART_BSV_SPARBEITRAG
           * - KTO_ART_VL_ANTRAGSTELLER
           * - KTO_ART_VL_EHEPARTNER
           * - KTO_ART_SONDERZAHLUNG
           * 
           * mit dem Loeschkennzeichen versehen und der Einzahlungsbetrag
           * wird auf 0 gestellt. Die Verlaufselemente werden am Ende der
           * Sparphasenberechnung entfernt. Beim Berechnungsziel
           * Laufzeit werden die Elemente nach der ersten Iteration geloescht.
           * 
           * Diese Loeschaktion ist vom Berechnungsziel unabhaengig.
           */
          if ( c_sparstopp_knz_erreicht )
          {
            if ( ( akt_element.getArt() == KTO_ART_BSV_SPARBEITRAG ) || ( akt_element.getArt() == KTO_ART_VL_ANTRAGSTELLER ) || ( akt_element.getArt() == KTO_ART_VL_EHEPARTNER ) || ( akt_element.getArt() == KTO_ART_SONDERZAHLUNG ) )
            {
              akt_element.setKnzLoeschung( KNZ_ELEMENT_LOESCHEN );

              akt_element.setBetragEinzahlung( BETRAG_0 );
            }
          }

          /* 
           * Pruefung: AG-Betrag setzen?
           * Ist das Berechnungsziel die Bausparsumme, muss fuer ein Verlaufselement
           * der Art "AG_BELASTUNG", die aktuelle Hoehe der Abschlussgebuehr gesetzt
           * werden. Die Element-Bezeichnung wird ebenfalls angepasst.
           * 
           * Soll die Abschlussgebuehr separat beglichen werden, wird die aktuelle
           * Hoehe der AG in das Verlaufselement AG-Begleichung gesetzt.
           */
          if ( c_calc_bss_knz_set_ag_betrag )
          {
            if ( akt_element.getArt() == KTO_ART_AG_BELASTUNG )
            {
              akt_element.setBetragGebuehr( e_spar_ag_betrag );

              akt_element.setBezeichnung( c_spar_ag_bezeichnung );
            }
            else if ( akt_element.getArt() == KTO_ART_AG_BEGLEICHUNG_SEPARAT )
            {
              akt_element.setBetragEinzahlung( e_spar_ag_betrag );

              akt_element.setBezeichnung( "Begleichung Abschlussgebuehr " + e_spar_ag_betrag );
            }
          }

          /* ###################################################################################
           * 
           * ABSCHNITT 2: Foerderungen Arbeitnehmersparzulage und Wohnungsbaupraemie
           * 
           * ###################################################################################
           * 
           * Vorgehensweise:
           * 
           * Arbeitnehmerspazulage
           *   - Berechnung mit ANSP
           *        - Muss die ANSP berechnet werden?
           *             - Jahreswechsel
           *             - Element "Wop" gutschrift = Zuteilung 31.12
           *             - Element "Zuteilung" = ausstehende ANSP
           * 
           *        - Soll ANSP-Berechnet werden?
           *             - Berechnung BASIS
           *                 - VL-Zahlung AS
           *                 - VL-Zahlung EP
           *                 - Jeweils die Obergrenze beachten
           *             - Basis vorhaden?
           *                 - Foerderbetrag berechnen
           *                 - Foerderbetrag auf die festgesetzten
           *                   Foerderbetraege hinzuaddieren
           * 
           *                 - Betrag ANSP-VL-ZAHLUNGEN setzen
           * 
           * Wohnungsbaupraemie
           * -Berechnung mit Wohnungsbaupraemie?
           *     - Muss eine Berechnung angestossen werden?
           *          - Jahreswechsel
           *          - Gutschriftelement mit Datum 31.12
           *          - Zuteilungselement
           * 
           *     - Soll die Berechnung gemacht werden?
           *          - Berechnungsbasis
           *              - Kontostand aktuell abzueglich letzter Kontostand
           *              - Abziehen der ANSP-VL-Zahlungen
           * 
           *          - Basis groesser als Mindestbetrag
           *              -Basis auf Maximalgrenze pruefen
           * 
           *              - Foerderbetrag berechnen
           * 
           * https://de.wikipedia.org/wiki/Arbeitnehmersparzulage
           * 
           * Die Arbeitnehmersparzulage muss vor der Wop-Berechnung stattfinden,
           * da fuer VL-Zahlungen auf die schon ANSP gefoerdert wurde nicht mehr
           * bei der Wohnungsbaupraemie beruecksichtigt werden.
           * 
           * Die Arbeitnehmersparzulage wird pro Jahr berechnet. Es muessen die VL-Zahlungen fuer
           * jeweils ein Betrachtungsjahr aufsummiert werden.
           * 
           * Die Werte fuer die Berechnung werden nach der Berechnung auf 0 gestellt
           * 
           * Bei dem letzten Element der Sparphase, der Zuteilung, wird die noch
           * ausstehende Arbeitnehmersparzulagen-Foerderung berechnet. Die noch
           * ausstehende Foerderung ist der Foerderbeetrag, welcher sich durch die
           * VL-Zahlungen im Zuteilungsjahr berechnet. (1.1. bis Zuteilungsdatum)
           * 
           * Wenn die Zuteilung vor dem Ablauf der einmaligen Foerderungsauszahlung liegt,
           * ist der ausstehende Foerderungsbetrag eben der angesammelte Betrag.
           * 
           * Pruefung: Arbeitnehmersparzulage berechnen ?
           */
          if ( c_knz_mit_arbeitnehmer_sparzulage )
          {
            c_ansp_berechnen = B_FALSE;

            /* 
             * Pruefung: Arbeitnehmersparzulage: aendert sich das Jahr ?
             */
            if ( FkDatumLong.getJahrAusLong( akt_element.getDatum() ) != c_ansp_aktuelles_jahr )
            {
              /* 
               * Arbeitnehmer-Spz. und Jahreswechsel
               * Die Arbeitnehmersparzulage wird jaehrlich berechnet.
               * 
               * Mit dem aktuellen Element wurde ein neues Jahr begonnen.
               * Es wird die Arbeitnehmersparzulage fuer das vergangene Jahr berechnet.
               */
              c_ansp_berechnen = B_TRUE;
            }
            else if ( ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_WOP_ZUTEILUNG ) || ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_WOP_JUNGE_LEUTE_REGELUNG ) )
            {
              /* 
               * Element Wop-Auszahlung bei Zuteilung
               * 
               * Faellt die Zuteilung auf einen 31.12 muss fuer das Vergangene Jahr
               * die WOP-Foerderung berechnet werden. Dabei muessen die VL-Zahlungen
               * abgezogen werden, auf welche ANSP gewaehrt wurde. Diese muessen berechnet
               * werden.
               * 
               * Es liegt kein Datumswechsel vor, welcher die VL-Zahlungen setzt, auf
               * welche ANSP gewaehrt wurde. Die Berechnung wird zwar auch von dem
               * Element "Zuteilung" berechnet, das waere aber zu spaet, da vor dem
               * Zuteilungselement das Element fuer die WOP-Gutschrift kommt.
               */
              c_ansp_berechnen = FkDatumLong.getMonatTagAusLong( akt_element.getDatum() ) == 1231;

              if ( c_ansp_berechnen && c_debug_knz_wop_berechnung )
              {
                wl( "WOP: Jahr " + c_wop_aktuelles_jahr + " Zuteilung 31.12. ANSP-VL-Zahlungen berechnen (c_wop_vl_zahlungen_ansp = " + c_wop_vl_zahlungen_ansp + ", c_ansp_betrag_jahr_vl_as " + c_ansp_betrag_jahr_vl_as + ", c_ansp_betrag_jahr_vl_ep = " + c_ansp_betrag_jahr_vl_ep + ") " );
              }
            }
            else if ( akt_element.getArt() == KTO_ART_SPAR_PHASE_ZUTEILUNG )
            {
              /* 
               * Zuteilung
               * Bei dem Element "Zuteilung" muessen noch die ausstehenden Foerderbetraege
               * berechnet werden. Da die Zuteilung innerhalb eines Jahres stattfindet ist
               * der Foerderbetrag fuer die bis zur Zuteilung gezahlten VL-Zahlungen zu berechnen.
               */
              c_ansp_berechnen = B_TRUE;
            }

            if ( c_ansp_berechnen )
            {
              /* 
               * Berechnung Basisgrundlage fuer Arbeitnehmersparzulage
               * 
               * Die Basis fuer die Berechnungsgrundlage sind die geleisteten
               * VL-Zahlungen des Antragstellers und des Ehepartners im
               * Betrachtungsjahr. Diese beiden Summen fuer AS und EP wurden
               * bei den VL-Zahlungen jeweils aufsummiert.
               * 
               * Die jweilige Summen der VL-Zahlungen duerfen den gesetzten
               * Maximalbetrag der VL-Summenhoehe dabei nicht ueberschreiten.
               * 
               * Liegt eine VL-Zahlung ueber der Grenze, wird die Basis nur
               * um den Maximalbetrag erhoeht.
               */
              c_ansp_berechnung_basis = BETRAG_0;

              /* 
               * Pruefung: VL Antragsteller > 0
               */
              if ( c_ansp_betrag_jahr_vl_as >= c_ansp_betrag_min_vl )
              {
                if ( c_ansp_betrag_jahr_vl_as <= c_ansp_betrag_max_vl )
                {
                  c_ansp_berechnung_basis = c_ansp_berechnung_basis + c_ansp_betrag_jahr_vl_as;
                }
                else
                {
                  c_ansp_berechnung_basis = c_ansp_berechnung_basis + c_ansp_betrag_max_vl;
                }
              }

              /* 
               * Pruefung: VL Ehepartner > 0
               */
              if ( c_ansp_betrag_jahr_vl_ep >= c_ansp_betrag_min_vl )
              {
                if ( c_ansp_betrag_jahr_vl_ep <= c_ansp_betrag_max_vl )
                {
                  c_ansp_berechnung_basis = c_ansp_berechnung_basis + c_ansp_betrag_jahr_vl_ep;
                }
                else
                {
                  c_ansp_berechnung_basis = c_ansp_berechnung_basis + c_ansp_betrag_max_vl;
                }
              }

              /* 
               * Pruefung: Berechnungsgrundlage vorhanden?
               * Die Berechnung wird nur angestossen, sofern die Berechnungsgrundlage
               * einen Wert von groesser 0 hat.
               */
              if ( c_ansp_berechnung_basis > BETRAG_0 )
              {
                /* 
                 * VL-Zahlungen auf die Ansp gewaehrt wurde
                 * Die Variable "c_wop_vl_zahlungen_ansp" wird am Ende der WOP-Foerderungsberechnung auf
                 * den Betrag 0 gestellt. Nur wenn ANSP gewaehrt wird (also eine Berechnungsbasis
                 * vorhanden ist) werden in dieser Variablen die VL-Summen gespeichert.
                 * 
                 * Problem ist, dass die VL-Summen nach der ANSP-Berechnung auf 0 gestellt werden.
                 * Eine weitere ANSP-Berechnung findet keine Berechnungsbasis mehr vor und berechnet
                 * dann auch nicht nochmal die Arbeitnehmersparzulage. Das kann nur im Jahr passieren,
                 * wenn die Berechnung durch das Zuteilungselement angestossen wird.
                 * 
                 * Das Zuteilungselement wuerde auch die nachgelagerte WOP-Foerderung ausloesen (sofern WOP
                 * beruecksichtigt werden soll). Dann ist auch noch der gespeicherte Wert der schon
                 * ANSP-VL-Zahlungen vorhanden.
                 * 
                 * ... es wird hier gemacht um zufaellige Ausnullungen zu verhindern.
                 */
                c_wop_vl_zahlungen_ansp = c_ansp_betrag_jahr_vl_as + c_ansp_betrag_jahr_vl_ep; // Eventuell Falsch, sofern die VL-Zahlungen ueber dem Limit liegen;

                /* 
                 * Berechnung Foerderbetrag
                 * Auf den errechneten Basisbetrag werden die Prozente der Foerderung berechnet.
                 * Der berechnete Foerderbetrag wird zu den bestehenden ausstehenden Foerderungsbetraegen
                 * hinzuaddiert.
                 */
                c_ansp_akt_jahr_foerderung = round2DP( ( c_ansp_berechnung_basis * c_ansp_betrag_prozent_auf_vl ) * MULTIPLIKATOR_DURCH_100 );

                c_ansp_betrag_ausstehend = c_ansp_betrag_ausstehend + c_ansp_akt_jahr_foerderung;

                /* 
                 * Die Jahre der ANSP-Foerderungen wird um 1 Jahr hochgezaehlt.
                 */
                c_ansp_anzahl_jahre = c_ansp_anzahl_jahre + ANZAHL_1;

                if ( c_debug_knz_ansp_berechnung )
                {
                  wl( "ANSP: Jahr " + c_ansp_aktuelles_jahr + " (" + c_ansp_anzahl_jahre + "),  Basis " + c_ansp_berechnung_basis + ", Foerderbetrag " + c_ansp_akt_jahr_foerderung + ", c_ansp_betrag_ausstehend " + c_ansp_betrag_ausstehend );
                }
              }
              else
              {
                /* 
                 * Soll die ANSP nicht berechnet werden, erfolgt nur eine Log-Ausgabe, warum
                 * die Foerderung nicht berechnet wird.
                 */
                if ( c_debug_knz_ansp_berechnung )
                {
                  if ( akt_element.getArt() == KTO_ART_SPAR_PHASE_ZUTEILUNG )
                  {
                    wl( "ANSP: keine Berechnung fuer Jahr " + c_ansp_aktuelles_jahr + ", da keine Basis vorhanden ist. (bzw. die Foerderung vorher berechnet worden ist und deshalb die Basis 0 ist)" );
                  }
                  else
                  {
                    wl( "ANSP: keine Berechnung fuer Jahr " + c_ansp_aktuelles_jahr + ", da keine Basis vorhanden ist." );
                  }
                }
              }

              /* 
               * Abschluss: laufende Variablen auf Ausgangswerte stellen.
               * 
               * Das aktuelle ANSP-Jahr wird auf das Jahr des aktuellen Elementes gestellt.
               * 
               * Die beiden Summenvariablen fuer VL-Antragsteller und VL-Ehepartner werden
               * auf 0 gestellt.
               */
              c_ansp_aktuelles_jahr = FkDatumLong.getJahrAusLong( akt_element.getDatum() );

              c_ansp_betrag_jahr_vl_as = BETRAG_0;

              c_ansp_betrag_jahr_vl_ep = BETRAG_0;
            }
          }

          /* 
           * https://de.wikipedia.org/wiki/Wohnungsbaupr%C3%A4mie
           */
          if ( c_wop_knz_aktiv )
          {
            c_wop_knz_berechnen = B_FALSE;

            if ( FkDatumLong.getJahrAusLong( akt_element.getDatum() ) != c_wop_aktuelles_jahr )
            {
              /* 
               * WOP und Jahreswechsel
               * Mit dem aktuellen Element wurde ein neues Jahr begonnen.
               * Es wird die Arbeitnehmersparzulage fuer das vergangene Jahr berechnet.
               */
              c_wop_knz_berechnen = B_TRUE;
            }
            else if ( ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_WOP_ZUTEILUNG ) || ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_WOP_JUNGE_LEUTE_REGELUNG ) )
            {
              /* 
               * Element Wop-Auszahlung bei Zuteilung
               * Faellt die Zuteilung auf einen 31.12, muss fuer das vergangene Jahr
               * die Foerderung berechnet werden.
               * 
               * Der fuer das aktuelle Jahr noch ausstehende Foerderbetrag muss
               * berechnet werden.
               * 
               * Weiterhin bedeutet dies, dass die Sparphase ohne noch auststehende
               * Foerderung beendet wird, da ja jeder Foerderbetrag gutgeschrieben
               * werden konnte.
               * 
               * Durch die Sortierreihenfolge des Rechenkerns ist sichergestellt,
               * das die Zinsen schon gutgeschrieben worden sind.
               */
              c_wop_knz_berechnen = FkDatumLong.getMonatTagAusLong( akt_element.getDatum() ) == 1231;

              if ( c_wop_knz_berechnen && c_debug_knz_wop_berechnung )
              {
                wl( "WOP: Jahr " + c_wop_aktuelles_jahr + " Zuteilung faellt mit Datum der WOP-Auszahlung zusammen. Ausstehende Foerderung wird berechnet (c_wop_vl_zahlungen_ansp = " + c_wop_vl_zahlungen_ansp + ")" );
              }
            }
            else if ( akt_element.getArt() == KTO_ART_SPAR_PHASE_ZUTEILUNG )
            {
              /* 
               * Zuteilung
               * Bei dem Zuteilungselement muss die noch ausstehende Foerderung nur dann
               * berechnet werden, wenn das Datum der Zuteilung kein 31.12 ist. Bei einem
               * 31.12 wurde der Foerderbetrag schon berechnet und dann auch gleichzeitig
               * dem Kontostand gutgeschrieben.
               * 
               * Da diese Routine den Kontostand fuer die Berechnung nimmt (weil es so
               * komfortabel einfach ist), ist der Kontostand am Zuteilungselement
               * genau um den Foerderbetrag angewachsen, welches dann in einer
               * Foerderung der Foerderung enden wuerde. Bei dem unten stehenden Beispiel
               * wurde zuerst die korrekte Gutschrift der festgesetzten WOP gemacht
               * und am Zuteilungselement wurde eine ausstehende Foerderung auf den
               * Foerderbetrag gerechnet (Basis 450 => Foerderung von 39.65)
               * 
               * WOP: Jahr 2026,  Basis 1298, Foerderbetrag 45,06, c_wop_vl_zahlungen_ansp 0, c_wop_betrag_festgesetzt 450,6
               * WOP: Gutschrift der ausstehenden Foerderung am 31.12.2026 von c_wop_betrag_festgesetzt 450,6, c_wop_betrag_gutgeschrieben 450,6
               * WOP: Jahr 2026,  Basis 450,6, Foerderbetrag 39,65, c_wop_vl_zahlungen_ansp 0, c_wop_betrag_festgesetzt 39,65 <= FALSCH
               */
              c_wop_knz_berechnen = FkDatumLong.getMonatTagAusLong( akt_element.getDatum() ) != 1231;
            }

            if ( c_wop_knz_berechnen )
            {
              /* 
               * Wop gibt es fuer alle Einzahlungen und Zinsen auf das Bausparkonto
               * Daraus resultiert, dass es ausreichend sein muss, sich den Kontostand
               * zu merken, die Differenz zu bilden und die Ansp-VL-Zahlungen abzuziehen.
               * 
               * Betrag Kontostand
               * Die WOP-Berechnung wird immer zum 1.1. oder zum Zuteilungsdatum gemacht.
               * Es ist ein jaehrliches Vorkommen.
               * 
               * Sofern der Rechenkern feststellt, das eine WOP-Berechnung aufgrund eines
               * Jahreswechsel notwendig ist, war das letzte Verlaufselemente die Zinsberechnung.
               * Daher ist die Guthabenverzinsung und auch Gutschrift erfolgt und die Zinsen
               * sind im aktuellen Kontostand vorhanden.
               * 
               * Wurde die WOP-Berechnung durch das Zuteilungselement angestossen, wurde
               * im vorletzten Element die Zinsgutschrift bei Zuteilung berechnet und gutgeschrieben.
               * Auch in diesem Fall sind die angelaufenen Zinsen im Guthaben enthalten.
               * 
               * Fazit ist, dass der aktuelle Kontostand fuer die WOP-Berechnung genommen kann, da
               * dort sichergestellt ist, dass die Zinsen enthalten sind.
               * 
               * WOP-Basis
               * Vom aktuellem Kontostand wird der zuletzt gespeicherte Kontostand abgezogen.
               * Das Ergebnis ist die Veraenderung im Kontostand, welche durch die Einzahlungen
               * und Gebuehren entstanden sein koennen.
               */
              c_wop_berechnung_basis = c_betrag_akt_kontostand - c_wop_kto_stand_altes_jahr;

              /* 
               * VL-Ansp abziehen
               * Sofern Arbeitnehmersparzulage beruecksichtigt wird, muss der Betrag abgezogen
               * werden, welcher durch die VL-Zahlungen angespart worden ist. Fuer diesen
               * Betrag hat es schon eine Foerderung gegeben. Eine Doppelfoerderung wird
               * nicht gemacht.
               */
              c_wop_berechnung_basis = c_wop_berechnung_basis - c_wop_vl_zahlungen_ansp;

              /* 
               * Mindestsparbeitrag fuer Foerderung
               * Es muessen mindestens 50 Euro eingezahlt worden sein, damit die Foerderung
               * berechnet wird.
               */
              if ( c_wop_berechnung_basis > c_wop_betrag_mindest_einzahlung )
              {
                /* 
                 * Maximalsparbeitrag der Foerderung
                 * Es werden nicht uneingeschraenkt Einzahlungen gefoerdert. Es gibt
                 * hier Obergrenzen fuer die eine Foerderung gezahlt wird. Diese Obergrenze
                 * ist fuer ledige Personen 512 Euro, fuer verheiratete Personen 1024.
                 * 
                 * Die Foerderbasis wird an diese Grenzen angepasst.
                 */
                if ( c_wop_berechnung_basis > c_wop_max_einzahlung_je_jahr_beruecksichtigen )
                {
                  c_wop_berechnung_basis = c_wop_max_einzahlung_je_jahr_beruecksichtigen;
                }

                /* 
                 * Foerderbetrag berechnen
                 * Auf den ermittelten Basisbetrag gibt es X-Prozent Foerderung.
                 */
                c_wop_akt_jahr_foerderung = round2DP( ( c_wop_berechnung_basis * c_wop_foerderbetrag_prozent ) * MULTIPLIKATOR_DURCH_100 );
              }

              /* 
               * WOP Festgesetzt
               * Der aktuelle Foerderbetrag wird dem Betrag der bereits festgesetzten
               * WOP-Foerderung hinzuaddiert.
               */
              c_wop_betrag_festgesetzt = c_wop_betrag_festgesetzt + c_wop_akt_jahr_foerderung;

              if ( c_debug_knz_wop_berechnung )
              {
                wl( "WOP: Jahr " + c_wop_aktuelles_jahr + ", Basis aufgrund vom Kontostand " + ( ( c_betrag_akt_kontostand - c_wop_kto_stand_altes_jahr ) - c_wop_vl_zahlungen_ansp ) + ", Basis " + c_wop_berechnung_basis + ", Foerderbetrag " + c_wop_akt_jahr_foerderung + ", c_wop_vl_zahlungen_ansp " + c_wop_vl_zahlungen_ansp + ", c_wop_betrag_festgesetzt " + c_wop_betrag_festgesetzt );
              }

              /* 
               * Kontostand fuer die naechste WOP-Berechnung merken
               */
              c_wop_kto_stand_altes_jahr = c_betrag_akt_kontostand;

              /* 
               * Der Betrag der VL-ANSP-Zahlungen wird auf 0 gestellt.
               * Dieser Betrag wird einmal bei der ANSP-Berechnung auf die
               * Summe der VL-Zahlungen von AS und EP gestellt.
               * Da bei einer Zuteilung innerhalb eines Jahres auch bei dem
               * Element Zuteilung die Ansp-Berechnung durchgefuehrt wird
               */
              c_wop_vl_zahlungen_ansp = BETRAG_0;

              /* 
               * Das aktuelle Jahr wird gemerkt, da die naechste WOP-Berechnung
               * diesen Wert benoetigt (Um den Jahreswechsel feststellen zu koennen)
               */
              c_wop_aktuelles_jahr = FkDatumLong.getJahrAusLong( akt_element.getDatum() );
            }
          }

          /* ###################################################################################
           * 
           * ABSCHNITT 3: Ermittlung, ob eine Zinsberechnung erforderlich ist
           * 
           * Dass ist der Fall wenn:
           * - das Tagesdatum sich aendert
           * - das aktuelle Element von der Art "ZINSGUTSCHRIFT" ist
           * 
           * ###################################################################################
           * 
           * Pruefung: Datumsaenderung ?
           * 
           * Wenn mit dem neuen Verlaufselement ein "neuer Tag eroeffnet wird",
           * muss fuer den vorhergehenden Tag (=Datum) der Tagesabschluss gemacht
           * werden.
           */
          if ( akt_element.getDatum() != c_tages_abschluss_datum )
          {
            if ( c_debug_knz_zins_berechnung_spar )
            {
              wl( "ZINS_CALC: " );
              wl( "ZINS_CALC: Tagesabschluss altes Datum = " + c_tages_abschluss_datum + ", neues Datum " + akt_element.getDatum() + "." );
              wl( "ZINS_CALC: " );
            }

            /* 
             * Pruefung: gibt es einen aktuellen Tag?
             * Beim ersten Durchlauf ist der aktuelle TAG -1. 
             * Es ist noch kein Tagesabschluss zu machen.
             */
            if ( c_tages_abschluss_datum == DATUM_0 )
            {
              c_tages_abschluss_datum = akt_element.getDatum();

              c_zins_abschluss_datum_berechnet = c_tages_abschluss_datum;

              /* 
               * Datum fuer Zins-Tages-Diff merken.
               */
              c_zins_datum_fuer_tages_diff = akt_element.getDatum();

              if ( c_debug_knz_zins_berechnung_spar )
              {
                wl( "ZINS_CALC: Startdatum fuer Zinsberechnung setzen. Aktueller Tag wird auf " + c_tages_abschluss_datum + " gesetzt!" );
              }
            }
            else
            {
              /* 
               * Nachdem der Tag abgeschlossen ist, wird geprueft, ob sich durch
               * die Kontoverlaufssaetze der aktuelle Kontostand geaendert hat.
               * 
               * Dazu wird der letzte Tages-Abschluss-Saldo mit dem aktuellem
               * Kontostand abgeglichen.
               * 
               * Hat eine Veraenderung stattgefunden, wird das Kennzeichen
               * fuer die Zinsberechnung auf TRUE gesetzt.
               */
              if ( c_tages_abschluss_saldo != c_betrag_akt_kontostand )
              {
                c_rk_knz_zinsberechnung_durchfuehren = B_TRUE;
              }

              if ( c_debug_knz_zins_berechnung_spar )
              {
                if ( c_rk_knz_zinsberechnung_durchfuehren )
                {
                  wl( "ZINS_CALC: Zinsberechnung durchfuehren, da sich der Kontostand geaendert hat. (Von " + c_tages_abschluss_saldo + " auf " + c_betrag_akt_kontostand + ")" );
                }
                else
                {
                  wl( "ZINS_CALC: Zinsberechnung wird nicht durchgefuehrt, da sich der Kontostand nicht geaendert hat." );
                }

                wl( "ZINS_CALC:" );
                wl( "ZINS_CALC: Tagesabschluss alter Kto-Stand " + c_tages_abschluss_saldo + ", neuer Kto-Stand " + c_betrag_akt_kontostand + " = Differenz = " + ( c_betrag_akt_kontostand - c_tages_abschluss_saldo ) );
                wl( "ZINS_CALC:" );
              }

              /* 
               * Tagesabschlussdaten merken
               * Der Datum des aktuellen Verlaufselementes wird gespeichert.
               * Mit diesem Datum wurde eine Pruefung gegegen das letzte Datum gemacht
               */
              c_tages_abschluss_datum = akt_element.getDatum();

              c_tages_abschluss_saldo = c_betrag_akt_kontostand;
            }
          }

          /* 
           * Pruefung: aktuelles Element ist Zins-Gutschrift
           * Handelt es sich bei dem aktuellen Element un eine Zinsgutschrift, muessen
           * auch noch die Zinsen bis zum aktuellen Datum berechnet und den UJ-Zinsen
           * hinzuaddiert werden.
           * 
           * Hintergrund: der aktuelle Tag aendert sich in diesem Fall nicht, da am
           * Datum der Zinsgutschrift auch noch Zahlungen eingehen koennen.
           * 
           * Es wird das Kennzeichen fuer die Zinsberechnung auf TRUE gestellt.
           * 
           * Falsch: Bei der jaehrlichen Zinsgutschrift, werden lediglich die angesammelten
           * unterjaehrigen Zinsen gutgeschrieben. Es wird jedoch keine neue Zinsberechnung
           * angestossen, da dieses Seiteneffekte gibt, da die Zinsberechnung nicht durch einen
           * Datumswechsel angestossen wird.
           */
          if ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ZINS_JAEHRLICH )
          {
            c_rk_knz_zinsberechnung_durchfuehren = B_TRUE;
          }
          else if ( ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ZINS_ZUTEILUNG ) )
          {
            c_rk_knz_zinsberechnung_durchfuehren = B_TRUE;
          }

          /* ###################################################################################
           * 
           * ABSCHNITT 4: Zinsberechnung fuer unterjaehrige Zinsen durchfuehren
           * 
           * ###################################################################################
           * 
           * Pruefung: Zinsberechnung durchfuehren?
           */
          if ( c_rk_knz_zinsberechnung_durchfuehren )
          {
            if ( c_debug_knz_zins_berechnung_spar )
            {
              if ( FkDatumLong.getMonatTagAusLong( akt_element.getDatum() ) == DATUM_TAG_MONAT_ERSTER_JANUAR )
              {
                wl( akt_element.toString() );
              }
            }

            /* 
             * Kennzeichen fuer Zinsberechnung wieder auf FALSE stellen.
             */
            c_rk_knz_zinsberechnung_durchfuehren = B_FALSE;

            /* 
             * Pruefung: gibt es einen Vorsaldo?
             * Steht der Zinsabschluss-Vorsaldo auf -1 (=Startwert), wird
             * dieser auf den Betrag des aktuellen Kontostandes gesetzt.
             */
            if ( c_zins_abschluss_1_vorsaldo == BETRAG_MINUS_1 )
            {
              c_zins_abschluss_1_vorsaldo = c_betrag_akt_kontostand;
            }

            /* 
             * Berechnung der Tagesdifferenz
             * Berechnung der Tage, ueber welcher der aktuelle Kontosaldo gueltig war.
             * Ueber diesen Zeitraum wird der Saldo mit dem Guthabenzinssatz verzinst.
             * 
             * Die Tagesdifferenz berechnet sich ausgehend vom letzten Datum der
             * Zinsberechnung bis zum aktuellem Datum des Kontoverlaufselementes.
             * 
             * Es gibt 2 Arten wie die Zinsberechnung angestossen wird.
             * - Datumsaenderung
             * - Elementart des Verlaufselementes (Zinsberechnung)
             * 
             * Findet eine Datumsaenderung statt, ist die Zinsdifferenz diejenige
             * von der alten Zinsberechnung bis zum aktuellem Verlaufselement.
             * 
             * Findet der Anstoss der Berechnung ueber das Element Zinsberechnung
             * statt, kann es passieren, dass das Datum des aktuellen Elementes
             * mit dem Datum der letzten Zinsberechnung uebereinstimmt. Dieses
             * ist dann der Fall, wenn bei einem 31.12 das letzte Element die
             * jaehrliche Zinsberechnung ist und zuvor noch Einzahlungen an diesem
             * Datum gemacht worden sind. In diesem Fall gibt die Funktion fuer
             * die Tagesdifferenzberechnung eine Dauer von 0 zurueck.
             * 
             * Stimmt das Datum der letzten Zinsberechnung mit dem Datum des
             * aktuellen Verlaufselementes ueberein, wird die Tagesdifferenz 
             * auf die Dauer von 1 gestellt.
             */
            if ( c_zins_datum_fuer_tages_diff == akt_element.getDatum() )
            {
              c_zins_abschluss_1_tages_diff = ANZAHL_1;
            }
            else
            {
              c_zins_abschluss_1_tages_diff = FkDatumLong.getAnzahlTageDifferenzAusLong( c_zins_datum_fuer_tages_diff, akt_element.getDatum() );
            }

            /* 
             * Der Vorsaldo bestand fuer den Zeitraum bis zu diesem aktuellen Tagesabschluss.
             * Der Vorsaldo wird als Grundlage fuer die Zinsberechnung genommen.
             * Zinsen werden nur berechnet, wenn der Vorsaldo groesser 0 war.
             * 
             * Ist der Saldo fuer die Zinsberechnung kleiner gleich 0, sind die aktuellen
             * Zinsen auch 0.
             */
            if ( c_zins_abschluss_1_vorsaldo > BETRAG_0 )
            {
              /* 
               * Berechnung Tagesteiler
               * Es wird die Tagesanzahl des aktuellen Jahres gesetzt.
               * In einem normalen Jahr sind das 365 Tage. 
               * In einem Schaltjahr sind das 366 Tage.
               * Es bleibt zu klaeren in wie weit das auch fuer den Teiler in einem
               * Schaltjahr zutrifft.
               * 
               * Der Jahresteiler wurde mit der entsprechenden mathematischen Inversen ersetzt.
               * (Divisionen vermeiden, Multiplikationen gehen schneller)
               */
              if ( FkDatumLong.getLongKnzIstSchaltjahr( akt_element.getDatum() ) )
              {
                c_anzahl_faktor_tage_im_jahr = JAHRES_FAKTOR_366;
              }
              else
              {
                c_anzahl_faktor_tage_im_jahr = JAHRES_FAKTOR_365;
              }

              c_betrag_akt_grund_zinsen = round2DP( ( ( c_zins_abschluss_1_vorsaldo * tarif_proz_guthabenzins * MULTIPLIKATOR_DURCH_100 ) * c_anzahl_faktor_tage_im_jahr ) * c_zins_abschluss_1_tages_diff );

              c_betrag_akt_uj_grund_zinsen = c_betrag_akt_uj_grund_zinsen + c_betrag_akt_grund_zinsen;

              if ( c_bonus_knz_berechnung )
              {
                if ( c_bonus_abschluss_1_vorsaldo == BETRAG_0 )
                {
                  c_bonus_abschluss_1_vorsaldo = c_bonus_betrag_akt_kontostand;
                }

                c_betrag_akt_bonus_zinsen = round2DP( ( ( c_bonus_abschluss_1_vorsaldo * tarif_proz_bonus_zins * MULTIPLIKATOR_DURCH_100 ) * c_anzahl_faktor_tage_im_jahr ) * c_zins_abschluss_1_tages_diff );

                c_betrag_akt_uj_bonus_zinsen = c_betrag_akt_uj_bonus_zinsen + c_betrag_akt_bonus_zinsen;

                c_bonus_abschluss_1_vorsaldo = c_bonus_betrag_akt_kontostand;
              }
              else
              {
                c_betrag_akt_bonus_zinsen = BETRAG_0;
              }
            }
            else
            {
              c_betrag_akt_grund_zinsen = BETRAG_0;
            }

            if ( c_debug_knz_zins_berechnung_spar )
            {
              if ( c_bonus_knz_berechnung )
              {
                wl( "ZINS_CALC: Von " + c_zins_datum_fuer_tages_diff + " |Bis " + akt_element.getDatum() + " |Zinstage " + c_zins_abschluss_1_tages_diff + " |Zinssaldo " + c_zins_abschluss_1_vorsaldo + " | tarif_proz_guthabenzins " + tarif_proz_guthabenzins + " |akt. Zinsen " + c_betrag_akt_grund_zinsen + " |UJ Zinsen " + c_betrag_akt_uj_grund_zinsen + " |c_betrag_akt_grund_zinsen " + c_betrag_akt_grund_zinsen + " |c_betrag_akt_uj_bonus_zinsen " + c_betrag_akt_uj_bonus_zinsen + " | tarif_proz_bonus_zins " + tarif_proz_bonus_zins );
              }
              else
              {
                wl( "ZINS_CALC: Von " + c_zins_datum_fuer_tages_diff + " |Bis " + akt_element.getDatum() + " |Zinstage " + c_zins_abschluss_1_tages_diff + " |Zinssaldo " + c_zins_abschluss_1_vorsaldo + " | tarif_proz_guthabenzins " + tarif_proz_guthabenzins + " |akt. Zinsen " + c_betrag_akt_grund_zinsen + " |UJ Zinsen " + c_betrag_akt_uj_grund_zinsen );
              }

              wl( "ZINS_CALC:" );
            }

            /* 
             * Zinsfortfuehrungsschleife
             * Damit die Kontoverlaufssaetze ueber die korrekten Daten verfuegen, werden
             * die ermittelten Werte fuer Zinstage und Akt-Zinsen in die entsprechenden
             * Verlaufssaetze eingefuegt.
             * 
             * Start-Index:
             * Die Zinsfortfuehrungsschleife hat als Startwert immer den aktuellen Wert
             * der Variablen "c_zins_abschluss_index_ab"
             * 
             * End-Index:
             * Hat sich nur der Tag geaendert, endet die Zinsschleife vor dem
             * aktuellem Verlaufselement. Das aktuelle Verlaufselement startet
             * einen neuen Tag.
             * 
             * Handelt es sich um ein Verlaufselement der Art "Zinsgutschrift", hat
             * sich das aktuelle Tagesdatum nicht geaendert. Die Sortierreihenfolge
             * legt die Zinsgutschriften an den Ende eines Tages. Die Zinsfortfuehrungschleife
             * muss deshalb bis zum aktuellen Element laufen, damit der Tag abgeschlossen ist.
             *
             * FALSCH: bei einem Element "Zinsgutschrift" muss der Zinsabschlussindex auf dem Element 
             * stehen bleiben, welche den Tag des Zigutschriftelementes startet. Es wuerden 
             * sonst die Zahlungen des Zigutschr-Tages nicht bezinst werden.
             * 
             * Es muss geprueft werden, ob die Fortfuerhungsschleife gestartet werden kann.
             * Datum des Elementes pruefen, bis zu welchem gegangen werden darf
             * 
             * Eventuell zwei Versionen der Zinsfortfuehrungsschleife erstellen.
             * 
             * Bei Zinsgutschrift Zuteilung muss bis zum Ende iteriert werden
             *
             * Sofern eine Zinsgutschrift erfolgt, muessen die an dem Tag der Zinsgutschrift
             * eintreffenden Zahlungen noch beruecksichtigt werden.
             * 
             * Die Zinsberechnung wird durch das Element Zinsberechnung ausgeloest.
             * es koennen eventuell noch Zahlungen folgen.
             * 
             * Die Zinsberechnung erhoeht den Kontostand (Gutschrift der Zinsen erfolgt weiter unten)
             * Die Zinsnachfuehrungsschleife steht am Tagesanfang (= Sortierung der Verlaufselemente). (?? = Bessere Dokumentation machen, Warum Tagesanfang?)
             * 
             * Bei einer Zinsberechnungsausloesung durch einen Datumswechsel, 
             * steht der Zinsindex auf dem Element, welches zuletzt mit Zinsen
             * bezinzt wurde. Die Zinsfortfuehrungsschleife laeuft solange durch,
             * bis das bezinste Datum sich aendert. (Bis der Indexzaehler gleich 
             * dem aktuellem Element ist).
             * 
             * Bei einer Zinsberechnungsausloesung durch eine Zinsgutschrift, muessen 
             * alle an dem Tag der Zinsgutschrift eintreffenden Zahlungen fuer die 
             * Zinsberechnung beruecksichtigt werden. (Reihenfolge => Sortierung = hier nicht wichtig)
             * 
             * Der Zinsindex steht im Zweifel auf dem Element Zinsgutschrift selber, oder
             * aber auf einem Element, welches das gleich Datum wie die Zinsgutschrift hat.
             * Der Zinsindex muss bis zum Zinsgutschriftelement hochgezaehlt werden (= bestehende
             * Schleife).
             * 
             * Mit der Zinsgutschrift wird der Kontostand veraendert.
             * Ab dem Datum der Zinsgutschrift besteht der neue Kontostand.
             * Kommt nun eine weitere Zahlung am Tag der Zinsgutschrift hinzu, hat sich
             * der Tag nicht geaendert, die Zinstage sind 0, somit laeuft die Zinsberechnung
             * ins leere. FALSCH: Da sich das Datum nicht geaendert hat, wird keine
             * notwendigkeit zur Zinsberechnung erkannt.
             * 
             * Sind die Anzahl der Zinstage 0, findet alles am gleichen Tag statt.
             * Es gibt dann auf den eventuell veraenderten Kontostand keine Zinsen.
             * 
             * Erst die Zinsgutschrift und dann eventuell die Arbeitnehmersparzulage.
             * Die Arbeitnehmersparzulage erhoeht den Kontostand.
             * Darueber hinausgehende Elemente kommen nach der Zinsgutschrift 
             * und koennen den Kontostand eventuell veraendern.
             * 
             * Dieser veraenderte Kontostand muss auch in der Zinsberechnung beruecksichtigt werden.
             */
            if ( ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ZINS_JAEHRLICH ) || ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ZINS_ZUTEILUNG ) )
            {
              c_zins_abschluss_end_index = c_aktueller_index;
            }
            else
            {
              c_zins_abschluss_end_index = c_aktueller_index;
            }

            while ( c_zins_abschluss_index_ab < c_zins_abschluss_end_index )
            {
              temp_element = kto_verlauf.getIndex( c_zins_abschluss_index_ab );

              if ( ( temp_element != null ) )
              {
                temp_element.setBetragUjBonusZins( c_betrag_akt_bonus_zinsen );

                temp_element.setBetragUjGuthabenzins( c_betrag_akt_grund_zinsen );

                temp_element.setAnzahlZinstage( c_zins_abschluss_1_tages_diff );
              }

              c_zins_abschluss_index_ab = c_zins_abschluss_index_ab + 1;
            }

            if ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ZINS_JAEHRLICH )
            {
              temp_element = kto_verlauf.getIndex( c_zins_abschluss_index_ab );

              while ( temp_element != null )
              {
                if ( c_tages_abschluss_datum == temp_element.getDatum() )
                {
                  temp_element.setBetragUjBonusZins( c_betrag_akt_bonus_zinsen );

                  temp_element.setBetragUjGuthabenzins( c_betrag_akt_grund_zinsen );

                  temp_element.setAnzahlZinstage( 234 ); //c_zins_abschluss_1_tages_diff );

                  c_zins_abschluss_index_ab = c_zins_abschluss_index_ab + ANZAHL_1;

                  temp_element = kto_verlauf.getIndex( c_zins_abschluss_index_ab );
                }
                else
                {
                  temp_element = null;
                }
              }
            }

            /* 
             * Zinsstartwerte aktualisieren
             * Fuer die naechste Zinsberechnung werden die aktuellen Werte gespeichert.
             */
            c_zins_datum_fuer_tages_diff = akt_element.getDatum();

            c_zins_abschluss_datum_berechnet = akt_element.getDatum();

            c_zins_abschluss_1_vorsaldo = c_betrag_akt_kontostand;
          }

          /* 
           * ###################################################################################
           * 
           * ABSCHNITT 5: Zinsgutschrift durchfuehren
           * 
           * ###################################################################################
           */
          if ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_BONUS_ZUTEILUNG )
          {
            if ( c_debug_knz_zins_berechnung_spar )
            {
              wl( "ZINS_CALC: Zinsgutschrift" + akt_element.getDatum() + ": Kto-Stand " + c_betrag_akt_kontostand + " + UJ-Zinsen " + c_betrag_akt_uj_grund_zinsen + " = " + ( c_betrag_akt_kontostand + c_betrag_akt_uj_grund_zinsen ) );
              wl( "ZINS_CALC: ----------------------------------------------------------------------------------------------------" );
            }

            c_betrag_akt_kontostand = c_betrag_akt_kontostand + c_betrag_akt_uj_bonus_zinsen;

            c_bonus_gutgeschrieben = c_betrag_akt_uj_bonus_zinsen;

            akt_element.setBonusZinsen( c_betrag_akt_uj_bonus_zinsen );

            akt_element.setBetragZinsgutschrift( c_betrag_akt_uj_grund_zinsen );

            /* 
             * Nachdem die aktuellen UJ-Zinsen "verwertet" worden sind, wird der
             * Betrag der UJ-Zinsen auf 0 gestellt.
             */
            c_betrag_akt_uj_bonus_zinsen = BETRAG_0;
          }

          /* Zinsgutschrift
           * Ist das aktuelle Element von der Art "Zinsgutschrift" werden die angelaufenen
           * UJ-Zinsen dem aktuellem Kontostand und dem Summen-Zins-Zaehler hinzuaddiert.
           * Anschliessend werden die UJ-Zinsen wieder auf 0 gestellt, da der Betrag
           * in dem Guthaben enthalten ist.
           */
          if ( ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ZINS_JAEHRLICH ) || ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ZINS_ZUTEILUNG ) )
          {
            if ( c_debug_knz_zins_berechnung_spar )
            {
              wl( "ZINS_CALC: Zinsgutschrift" + akt_element.getDatum() + ": Kto-Stand " + c_betrag_akt_kontostand + " + UJ-Zinsen " + c_betrag_akt_uj_grund_zinsen + " = " + ( c_betrag_akt_kontostand + c_betrag_akt_uj_grund_zinsen ) );
              wl( "ZINS_CALC: ----------------------------------------------------------------------------------------------------" );
            }

            /* 
             * Das Bonuskonto wird seperat gefuehrt
             */
            c_betrag_akt_bonus_kto = c_betrag_akt_bonus_kto + c_betrag_akt_uj_bonus_zinsen;

            c_betrag_akt_kontostand = c_betrag_akt_kontostand + c_betrag_akt_uj_grund_zinsen;

            c_betrag_ges_zinsen_spar = c_betrag_ges_zinsen_spar + c_betrag_akt_uj_grund_zinsen;

            akt_element.setBetragZinsgutschrift( c_betrag_akt_uj_grund_zinsen );
            /* 
             * Nachdem die aktuellen UJ-Zinsen "verwertet" worden sind, wird der
             * Betrag der UJ-Zinsen auf 0 gestellt.
             */
            c_betrag_akt_uj_grund_zinsen = BETRAG_0;

            c_zins_datum_fuer_tages_diff = c_zins_abschluss_datum_berechnet;

            c_zins_abschluss_datum_berechnet = akt_element.getDatum();
          }

          /* ###################################################################################
           * 
           * ABSCHNITT 6: Fortschreibung von Zaehler- und Summenvariablen
           * 
           * ###################################################################################
           * 
           * Anzahl Bsv-Sparbeitrag
           * Die Anzahl der anfallenden Bsv-Sparbeitraege bis zum Stichtag fuer die Zuteilung
           * werden gezaehlt. Diese Anzahl wird beim Berechnungsziel "Sparrate" genutzt um
           * den von der Sparrate erbringenden Betrag auf die Einzahlungen zu verteilen.
           * 
           * Wurde der Sparstopp erreicht wird die Anzahl nicht hochgezaehlt.
           */
          if ( akt_element.getArt() == KTO_ART_BSV_SPARBEITRAG )
          {
            if ( akt_element.getDatum() <= c_spar_datum_stichtag_long )
            {
              if ( c_sparstopp_knz_erreicht )
              {
                /* wl( "Einzahlung nach Sparstopp." );
                 * 
                 * Fehlerquelle: Berechnungsziel Sparrate und Sparziel Mindestsparguthaben?
                 *               Es koennten eventuell zu wenig Einzahlungen gezaehlt werden.
                 *
                 * @TODO: Warum koennten eventuell zu wenig Einzahlungen gemacht werden ? 
                 *        Kommentar erweitern
                 */
              }
              else
              {
                c_anzahl_bsv_sparraten_bis_spar_stopp = c_anzahl_bsv_sparraten_bis_spar_stopp + ANZAHL_1;
              }
            }

            c_betrag_ges_bsv_sparbeitrag = c_betrag_ges_bsv_sparbeitrag + akt_element.getBetragEinzahlung();

            /* 
             * Gesamtsumme der Kontofuehrungsgebuehren
             * Handelt es sich bei dem aktuellen Verlaufselement um die Art Kto-Fuehrungsgebuehr,
             * wird die Summe der Kto-Fuerhungsgebuehren hochgezaehlt.
             */
          }
          else if ( ( akt_element.getArt() == KTO_ART_KTO_GEBUEHR_ANTEILIG ) || ( akt_element.getArt() == KTO_ART_KTO_GEBUEHR_SPAR_LAUFEND ) )
          {
            c_betrag_ges_kto_gebuehren_spar = c_betrag_ges_kto_gebuehren_spar + akt_element.getBetragGebuehr();

            /* 
             * Gesamtsumme VL-Leistung Antragsteller
             * Gesamtsumme der VL-Leistung des Antragstellers aufsummieren.
             * Betrag der VL-Einzahlungen-AS im Jahr aufsummieren
             */
          }
          else if ( akt_element.getArt() == KTO_ART_VL_ANTRAGSTELLER )
          {
            c_betrag_ges_vl_antragsteller = c_betrag_ges_vl_antragsteller + akt_element.getBetragEinzahlung();

            c_ansp_betrag_jahr_vl_as = c_ansp_betrag_jahr_vl_as + akt_element.getBetragEinzahlung();
          }
          else if ( akt_element.getArt() == KTO_ART_VL_EHEPARTNER )
          {
            c_betrag_ges_vl_ehepartner = c_betrag_ges_vl_ehepartner + akt_element.getBetragEinzahlung();

            c_ansp_betrag_jahr_vl_ep = c_ansp_betrag_jahr_vl_ep + akt_element.getBetragEinzahlung();
          }
          else if ( akt_element.getArt() == KTO_ART_SONDERZAHLUNG )
          {
            c_betrag_ges_sonderzahlung = c_betrag_ges_sonderzahlung + akt_element.getBetragEinzahlung();
          }
          else if ( akt_element.getArt() == KTO_ART_GEBUEHR_WOP_LAUFEND )
          {
            c_wop_betrag_wop_gebuehr_gesamt = c_wop_betrag_wop_gebuehr_gesamt + akt_element.getBetragGebuehr();
          }
          else if ( ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_WOP_ZUTEILUNG ) || ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_WOP_JUNGE_LEUTE_REGELUNG ) )
          {
            /* 
             * Auszahlung WOP
             * Die Auszahlung der WOP erfolgt am Datum der Zuteilung oder
             * mit der "Jungen Leute Regelung" 7 Jahre nach Vertragsbeginn.
             * 
             * Es wird der ausstehende Foerderbetrag in die Spalte "Foerderung" eingetragen.
             * Nach der Gutschrift, wird der ausstehende Betrag auf 0 gesetzt.
             * 
             * Bei der "Jungen Leute Regelung" kann die fruehzeitige Guthabenverfuegung
             * einer schnellere Zuteilung oder eine geringeren Sparrate zur Folge haben.
             */
            akt_element.setBetragFoerderung( c_wop_betrag_festgesetzt );

            c_wop_betrag_gutgeschrieben = c_wop_betrag_gutgeschrieben + c_wop_betrag_festgesetzt;

            if ( c_debug_knz_wop_berechnung )
            {
              wl( "WOP: Gutschrift der ausstehenden Foerderung am " + akt_element.getDatumAsString() + " von c_wop_betrag_festgesetzt " + c_wop_betrag_festgesetzt + ", c_wop_betrag_gutgeschrieben " + c_wop_betrag_gutgeschrieben );
            }

            c_wop_betrag_festgesetzt = BETRAG_0;
          }
          else if ( ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ANSP_EINMALIG ) || ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ANSP_LAUFEND ) )
          {
            /* 
             * Arbeitnehmersparzulage
             * Es wird der ausstehende Foerderbetrag in die Spalte "Foerderung" eingetragen.
             * Der ausstehende Foerderbetrag ist immer derjenige Betrag, welche in den
             * vergangenen Jahren (jeweils Kalenderjahr und nicht Vertragsjahr) berechnet
             * worden ist.
             * 
             * Nach der Gutschrift, wird der ausstehende Betrag auf 0 gesetzt.
             */
            akt_element.setBetragFoerderung( c_ansp_betrag_ausstehend );

            c_ansp_betrag_gutgeschrieben = c_ansp_betrag_gutgeschrieben + c_ansp_betrag_ausstehend;

            if ( c_debug_knz_ansp_berechnung )
            {
              wl( "ANSP: Gutschrift der ausstehenden Foerderung am " + akt_element.getDatumAsString() + " von c_ansp_betrag_ausstehend " + c_ansp_betrag_ausstehend + ", c_ansp_betrag_gutgeschrieben " + c_ansp_betrag_gutgeschrieben );
            }

            c_ansp_betrag_ausstehend = BETRAG_0;
          }

          /* ###################################################################################
           * 
           * ABSCHNITT 7: neuen Kontostand berechnen
           * 
           * ###################################################################################
           * 
           * Fortfuehrung Kontostand
           * Das aktuelle Element beeinflusst den Kontostand
           * Gebuehren und Auszahlungen reduzieren den Kontostand
           * Einzalungen erhoehen den Kontostand.
           * Dieses wird bei jedem Verlaufselement gemacht. Die entsprechenden
           * Betraege sind 0 sofern es sich um andere Verlaufselemente handelt.
           * Das sind z.B. Bewertungszahlberechnugen, Stichtage ...
           */
          c_betrag_akt_kontostand = c_betrag_akt_kontostand - akt_element.getBetragGebuehr();

          c_betrag_akt_kontostand = c_betrag_akt_kontostand - akt_element.getBetragAuszahlung();

          c_betrag_akt_kontostand = c_betrag_akt_kontostand + akt_element.getBetragEinzahlung();

          c_betrag_akt_kontostand = c_betrag_akt_kontostand + akt_element.getBetragFoerderung();

          c_betrag_summe_einzahlungen = c_betrag_summe_einzahlungen + akt_element.getBetragEinzahlung();

          if ( c_bonus_knz_berechnung )
          {
            c_bonus_betrag_akt_kontostand = c_bonus_betrag_akt_kontostand - akt_element.getBetragGebuehr();

            c_bonus_betrag_akt_kontostand = c_bonus_betrag_akt_kontostand - akt_element.getBetragAuszahlung();

            c_bonus_betrag_akt_kontostand = c_bonus_betrag_akt_kontostand + akt_element.getBetragEinzahlung();

            c_bonus_betrag_akt_kontostand = c_bonus_betrag_akt_kontostand + akt_element.getBetragFoerderung();
          }

          /* 
           * Nach der Veraenderung wird der Kontostand auf 2 Stellen gerundet.
           */
          c_betrag_akt_kontostand = round2DP( c_betrag_akt_kontostand );

          /* ###################################################################################
           * 
           * ABSCHNITT 8: Bewertungsstichtag (BWZ-Ermittlung)
           * 
           * ###################################################################################
           */
          //if (( akt_element.getArt() == KTO_ART_BEWERTUNGSSTICHTAG ) && ( akt_element.getDatum() <= c_spar_datum_stichtag_long )) // XXXXXX -falsch
          if ( akt_element.getArt() == KTO_ART_BEWERTUNGSSTICHTAG )
          {
            /* 
             * Datum letzter Stichtag
             * 
             * Das Datum des aktuellen Elementes ist das Datum des zuletzt
             * durchlaufenen Stichtages.
             */
            c_datum_letzter_stichtag = akt_element.getDatum();

            /* 
             * Berechnung der Saldensumme
             * Die Saldensumme ist die Summe der aufaddierten Kontostaende an jedem Stichtag.
             * Die Saldensumme ist die Summe aller HABEN-Salden. Negative Kontostaende
             * werden bei der Berechnung der Saldensumme nicht beruecksichtigt.
             * 
             * (Wie schnell sammelt sich Guthaben an)
             */
            if ( c_betrag_akt_kontostand > BETRAG_0 )
            {
              c_betrag_akt_saldensumme = c_betrag_akt_saldensumme + c_betrag_akt_kontostand;
            }

            /* 
             * Die berechnete Saldensumme wird im Verlaufselement gespeichert.
             */
            akt_element.setBetragSaldenSumme( c_betrag_akt_saldensumme );

            /* 
             * Aufsummierung der Stichtage
             * Solange noch nicht der massgebliche Stichtag fuer die Zuteilung erreicht worden ist,
             * werden die durchlaufenen Stichtage gezaehlt.
             */
            if ( akt_element.getDatum() <= c_spar_datum_stichtag_long )
            {
              c_anzahl_stichtage_bis_zum_massgeblichen_stichtag = c_anzahl_stichtage_bis_zum_massgeblichen_stichtag + ANZAHL_1;
            }

            /* 
             * Pruefung: Bausparsumme fuer BWZ-Berechnung vorhanden?
             * Die Bewertungszahl kann nur bei vorhandensein einer Bss berechnet werden.
             * Ist das Berecnungsziel "Bss" ist die Bausparsumme im ersten Durchgang 0.
             */
            if ( c_betrag_bss_berechnet > BETRAG_0 )
            {
              /* 
               * Bewertungszahl
               * An den Bewertungsstichtagen wird die Bewertungszahl jeweils neu berechnet.
               * Die Bewertungszahl stellt die Sparleistung des Bausparers dar.
               * 
               * Die Bewertungszahl ist die Saldensumme geteilt durch die Bausparsumme.
               * 
               * Die Bewertungszahl wird auf 3 Stellen nach dem Komma gefuehrt.
               * 
               * Die Bewertungszahl wird nicht gerundet, sondern abgeschnitten.
               */
              c_betrag_akt_bewertungszahl = get3nk( c_betrag_akt_saldensumme * c_inv_faktor_bss_berechnet );

              /* 
               * BWZ-Faktor
               * Wurde ein Bewertungszahlfaktor gesetzt, wird die Bewertungszahl mit
               * diesem Faktor multipliziert.
               * 
               * Sinn ? Zweck ?
               */
              if ( tarif_bwz_faktor > BETRAG_0 )
              {
                c_betrag_akt_bewertungszahl = get3nk( c_betrag_akt_bewertungszahl * tarif_bwz_faktor );
              }

              /* 
               * Die berechnete Bewertungszahl wird im Verlaufselement gespeichert.
               */
              akt_element.setBetragBwz( c_betrag_akt_bewertungszahl );

              /* 
               * Feststellung Mindestbewertungszahl erreicht
               * 
               * Das Erreichen der Mindestbewertungszahl kann nur an Bewertungsstichtagen erfolgen.
               * 
               * Es muessen folgende Bedingungen erfuellt sein:
               * 
               * - Das Kennzeichen "Mindest-BWZ erreicht" muss noch auf FALSE stehen.
               *   Ist das Kennzeichen TRUE, so wurde die Mindest-BWZ schon bei einem
               *   vorhergehenden Stichtag erreicht.
               * 
               * - Die aktuelle Bewertungszahl muss groesser gleich der zu erreichenden
               *   Bewertungszahl sein (Bei Optimierungen auf einen bestimmten Zins-
               *   und Tilgungsbeitrag kann die zu erreichenden Bewertungszahl von der
               *   tariflichen Mindestbewertungszahl abweichen)
               * 
               * Sind diese beiden Bedingungen erfuellt, wird das Kennzeichen "MinBwz Erreicht"
               * auf TRUE gesetzt und das datum der Erreichung gespeichert.
               */
              if ( ( c_tarif_minbwz_knz_erreicht == B_FALSE ) && ( c_betrag_akt_bewertungszahl >= c_tarif_minbwz_betrag ) )
              {
//                if ( calc_ziel_grob_ist_rate )
//                {
//                  if ( akt_element.getDatum() == c_calc_rate_stichtag_massgebend )
//                  {
//                    c_tarif_minbwz_datum_long = akt_element.getDatum();
//
//                    c_tarif_minbwz_knz_erreicht = B_TRUE;
//                  }
//                }
//                else
//                {
                // if ( c_tarif_minbwz_datum_long == c_calc_rate_stichtag_massgebend )
                c_tarif_minbwz_datum_long = akt_element.getDatum();

                c_tarif_minbwz_knz_erreicht = B_TRUE;
//                }
              }
            }
            else
            {
              /* 
               * Ist kein Betrag fuer die Bausparsumme vorhanden, wird die Bewertungszahl
               * explizit auf 0 gesetzt. Das ist beim Berechnungsziel Bausparsumme im
               * ersten Durchlauf der Suchschleife der Fall.
               * 
               * Beim Berechnungsziel BSS soll in der ersten Berechnung die Zuteilung
               * nicht erreicht werden.
               */
              akt_element.setBetragBwz( BETRAG_0 );
            }

            /* 
             * Feststellung ZTV erreicht
             * Die Zuteilungsvorraussetzungen koennen nur an einem Stichtag erfuellt werden.
             * Die Pruefung wird nur bei einem Verlaufselement "Stichtag" durchgefuehrt.
             * 
             * Es muessen folgende Bedingungen erfuellt sein:
             * 
             * - Das Kennzeichen "ZTV erreicht" muss noch auf FALSE stehen.
             *   Ist das Kennzeichen TRUE, so sind die Zuteilungsvorr. schon bei
             *   einem vorhergehenden Stichtag erfuellt worden.
             * 
             * - Das Kennzeichen "Mindestguthaben erreicht" muss auf TRUE stehen
             * - Das Kennzeichen "Mindestbewertungszahl erreicht" muss auf TRUE stehen
             * - Das Kennzeichen "Mindestsparzeit erreicht" muss auf TRUE stehen.
             * 
             * Sind alle diese Bedingungen erfuellt, werden am am aktuellen Datum die
             * Zuteilungsvorraussetzungen erfuellt. Das Kennzeichen "ZTV Erreicht" wird
             * auf TRUE gestellt und das Datum der ZTV-Erreichtung wird gespeichert.
             */
            if ( ( c_tarif_ztv_knz_erreicht == B_FALSE ) && ( c_tarif_minbwz_knz_erreicht && c_tarif_migu_knz_erreicht && c_tarif_mindest_sparzeit_knz_erreicht ) )
            {
              if ( ( c_rk_berechnungsziel == BZ_BAUSPARSUMME ) || ( c_rk_berechnungsziel == BZ_LAUFZEIT ) )
              {
                c_tarif_ztv_datum_long = akt_element.getDatum();

                c_tarif_ztv_knz_erreicht = B_TRUE;
              }
              else
              {
                if ( ( akt_element.getDatum() == c_calc_rate_stichtag_massgebend ) )
                {
                  c_tarif_ztv_datum_long = akt_element.getDatum();

                  c_tarif_ztv_knz_erreicht = B_TRUE;
                }
              }

              /* 
               * Sparstopp "Mindest Zuteilungsvoraussetzungen"
               * Ist der Sparstop auf "ZTV Erreicht" eingestellt wird dieses geprueft.
               * 
               * Es muessen folgende Bedingungen erfuellt sein:
               * 
               * - Dass Kennzeichen "Sparstopp erreicht" muss auf FALSE stehen.
               *   
               *   Ist das Kennzeichen TRUE, so wurde der Sparstopp schon bei einem
               *   vorhergehenden Stichtag erreicht.
               * 
               * - Dass Kennzeichen "Sparstopp" muss auf Mindest-Zuteilungsvorraussetzungen stehen
               * 
               * Sind diese Bedingungen erfuellt, wird das Kennzeichen "Sparstopp erreicht"
               * auf TRUE gestellt. Das Datum wird ebenfalls gespeichert.
               */
              if ( ( c_sparstopp_knz_erreicht == B_FALSE ) && ( c_knz_sparstopp_aus_vorgaben == SPAR_STOPP_MINDEST_ZTV ) )
              {
                c_datum_sparstopp_sparrate_long = c_tarif_ztv_datum_long;

                c_sparstopp_knz_erreicht = B_TRUE;
              }
            }

            if ( akt_element.getDatum() == c_spar_datum_stichtag_long )
            {
              /* 
               * Am Datum des fuer die Zuteilung relevanten Stichtages werden
               * die erreichten Stichtagsergebniswerte gespeichert:
               * 
               *  - Bewertungszahl
               *  - Kontostand
               *  - Saldensumme
               *  - Vl AS
               *  - Vl EP
               *  - Sonderzahlung
               *  - Zinsen
               *  - Gebeuhren Kontofuehrung
               *  - Gebeuhren Wop
               *  - Foerderung
               *  - Sparbeitrag
               *  
               * -------------------------
               * Wann ist ein korrektes Datum fuer das relevante Stichtagsdatum vorhanden?
               * 
               * Wenn es eine Laufzeitvorgabe gibt, kann der Stichtag schon im Vorwege berechnet werden. 
               * Beim Berechnungsziel Rate und Bausparsumme ist der Fall, da dort die Laufzeit ein Eingabewert ist. 
               * 
               * Beim Berechnungsziel Laufzeit muss vorher noch ein grobes Enddatum fuer die Zuteilung berechnet werden.
               */
              c_stichtag_betrag_bewertungszahl = c_betrag_akt_bewertungszahl;
              c_stichtag_betrag_kontostand = c_betrag_akt_kontostand;
              c_stichtag_betrag_saldensumme = c_betrag_akt_saldensumme;
              c_stichtag_betrag_vl_as = c_betrag_ges_vl_antragsteller;
              c_stichtag_betrag_vl_ep = c_betrag_ges_vl_ehepartner;
              c_stichtag_betrag_sonderzahlung = c_betrag_ges_sonderzahlung;
              c_stichtag_betrag_zinsen = c_betrag_ges_zinsen_spar;
              c_stichtag_betrag_gebeuhren_kfg = c_betrag_ges_kto_gebuehren_spar;
              c_stichtag_betrag_gebeuhren_wop = c_wop_betrag_wop_gebuehr_gesamt;
              c_stichtag_betrag_foerderung = c_ansp_betrag_gutgeschrieben + c_wop_betrag_gutgeschrieben;
              c_stichtag_betrag_sparbeitrag = c_betrag_ges_bsv_sparbeitrag;
            }

            if ( c_debug_knz_stichtage )
            {
              wl( "BWZ_CALC : Datum " + akt_element.getDatumAsString() + " Saldensumme " + c_betrag_akt_saldensumme + " BWZ " + c_betrag_akt_bewertungszahl + " " + c_tarif_minbwz_knz_erreicht );
            }
          }

          /* 
           * Feststellung Mindessparguthaben erreicht
           * Das Mindestsparguthaben kann mit einem beliebigen Element erreicht werden.
           * 
           * Es muessen folgende Bedingungen erfuellt sein:
           * 
           * - Das Kennzeichen "Mindestguthaben erreicht" muss noch auf FALSE stehen.
           *   Ist das Kennzeichen TRUE, so wurde das Mindestsparguthaben schon bei
           *   einem vorhergehenden Element ueberschritten.
           * 
           * - Der aktuelle Kontostand muss groesser/gleich dem Mindestsparguthaben sein
           * 
           * Sind diese Bedingungen erfuellt wird das Kennzeichen "Mindestpartguthaben erreicht"
           * auf TRUE gestellt und das Datum des Erreichens wird gespeichert.
           */
          if ( c_betrag_akt_kontostand >= e_spar_betrag_tarifliches_mindestsparguthaben )
          {
            if ( c_tarif_migu_knz_erreicht == B_FALSE )
            {
              if ( calc_ziel_grob_ist_rate )
              {
                if ( akt_element.getDatum() == c_calc_rate_stichtag_massgebend )
                {
                  c_tarif_migu_datum_long = akt_element.getDatum();

                  c_tarif_migu_knz_erreicht = B_TRUE;
                }
              }
              else
              {
                c_tarif_migu_datum_long = akt_element.getDatum();

                c_tarif_migu_knz_erreicht = B_TRUE;
              }

              /* 
               * Sparstopp "Mindestguthaben"
               * Ist der Sparstop auf "Mindestguthaben" eingestellt wird dieses geprueft.
               * 
               * Es muessen folgende Bedingungen erfuellt sein:
               * 
               * - Dass Kennzeichen "Sparstopp erreicht" muss auf FALSE stehen.
               * - Dass Kennzeichen "Sparstopp" muss auf Mindestguthaben stehen
               * 
               * Sind diese Bedingungen erfuellt, wird das Kennzeichen "Sparstopp erreicht"
               * auf TRUE gestellt. Das Datum wird ebenfalls gespeichert.
               */
              if ( ( c_sparstopp_knz_erreicht == B_FALSE ) && ( c_knz_sparstopp_aus_vorgaben == SPAR_STOPP_MI_GUTHABEN ) )
              {
                //if ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_GUTHABEN_RATE )
                //{
                /*
                 * Ist das Berechnungsziel die Sparrate und die Suchschleife befindet sich 
                 * in der ersten Iteration um die Rate fuer das Mindestguthaben zu berechnen, 
                 * darf die Rate nicht beim Erreichen des Mindestguthabens enden. 
                 * 
                 * Es kann passieren, dass in diesem Fall das Mindestguthaben zu einem 
                 * frueheren Zeitpunkt im Verlauf erreicht wird und so eine zu hohe 
                 * Rate berechnet werden wuerde.
                 * 
                 * In der ersten Suchschleife muss die Besparung zum Zeitpunkt des berechneten 
                 * Stichtages aufhoeren, da dann alle Einzahlungszeitpunkte fuer die Sparrate 
                 * durchlaufen worden sind. Das ergibt dann eine niedrigere Sparrate.
                 * 
                 * Erst in der zweiten Suchschleife fuer die Sparrate muss auf den korrekten Zeitpunkt 
                 * des Sparstopps geacchtet werden. In diesem Fall kann die Rate so hoch sein, dass 
                 * durch das weitersparen ein zu hohes Guthaben angespart werden wuerde. 
                 * 
                 * 
                 * BWZ-Schleife
                 * Um die Bewertungszahl zu erreichen, muss eventuell der Sparbeitrag drastisch ansteigen.
                 * Das ist der Fall, wenn der gegebene Zeitraum kurz und die BWZ hoch ist.
                 * 
                 * Das hat den Effekt, dass das Mindestguthaben sehr frueh erreicht wird. 
                 * Bei einer weiteren Besparung des Vertrags wurde dadurch das Mindestguthaben weit 
                 * ueberschritten werden. 
                 * 
                 * 
                 * Eine hohe BWZ und ein Sparstopp am MiGu bewirken eine fruehe Einstellung der Rate
                 * 
                 * Bei gleichbleibender Laufzeit und Sparstopp Mindestguthaben
                 * hohe BWZ   - die Generierung der BWZ wird den Sparbeitrag nach oben schnellen lassen
                 *              das nach oben schnellen des Sparbeitrages resultiert in einem frueheren Sparstopp
                 *               
                 * kleine BWZ - Sparstopp MiGu die Generierung der BWZ verteilt sich besser ueber die Laufzeit
                 * 
                 */
                //}
                // else
                {
                  c_datum_sparstopp_sparrate_long = akt_element.getDatum();

                  c_sparstopp_knz_erreicht = B_TRUE;
                }
              }
            }
          }

          /* 
           * Pruefung: Mindestsparzeit
           * Es wird geprueft, ob das aktuelle Datum groesser/gleich dem Enddatum der
           * Mindestsparzeit ist. Wird das Enddatum erreicht, wird die entsprechende
           * Kennzeichenvariable auf TRUE gesetzt.
           * 
           * (Hier fuer Debug-Zwecke noch mit If-Abfrage)
           */
          if ( c_tarif_mindest_sparzeit_knz_erreicht == B_FALSE )
          {
            if ( akt_element.getDatum() >= c_spar_datum_mindestsparzeit_long )
            {
              c_tarif_mindest_sparzeit_knz_erreicht = B_TRUE;
            }
          }

          /* 
           * Kontostand setzen
           * Nach den Berechnungen wird am aktuell betrachteten Verlaufselement der
           * berechnete Kontostand gesetzt.
           */
          akt_element.setBetragKontostand( c_betrag_akt_kontostand );

          if ( c_debug_knz_akt_element )
          {
            wl( akt_element.toString() );
          }

          if ( c_rk_berechnungsziel == BZ_LAUFZEIT )
          {
            if ( c_rk_zaehler_iteration == ITERATION_1 )
            {
              if ( c_tarif_ztv_knz_erreicht )
              {
                if ( c_spar_datum_stichtag_long == DATUM_0 )
                {
                  /* 
                   * Berechnungsziel Laufzeit + ZTV-Erreicht + Erste Iteration
                   * 
                   * Schleifenabbruch erzwingen
                   * 
                   * Es muss ein zweiter Durchgang durch die Suchschleife gemacht werden, da
                   * die ueberfluessigen Elemente geloescht werden muessen und sich die Elemente
                   * fuer die Zuteilung weiter nach vorne verschieben.
                   * 
                   * Es muss an zukuenftige Erweiterungen gedacht werden, welche einen einfachen
                   * Durchlauf erschweren koennten. Es ist besser die Berechnungsschleife
                   * einfach zu halten und dafuer zweimal durchzugehen.
                   */
                  c_aktueller_index = c_rk_anzahl_elemente;

                  /* 
                   * Das Ergebnisdatum fuer den Stichtag ist der letzte durchlaufene Stichtag
                   */
                  c_spar_datum_stichtag_long = c_datum_letzter_stichtag;

                  if ( m_debug_knz_berechnungsziel_schritte )
                  {
                    wl( "calcLaufzeit: Zuteilungsvorraussetzungen erreicht. Setzen des letzten Stichtages = " + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long ) + " als den fuer die Zuteilung massgebenden Stichtag" );
                  }
                }
              }
            }
          }

          /* 
           * Ende der Verlaufsberechnung
           * Am Schleifenende wird der Indexzaehler fuer den Berechnungsdurchlauf um
           * 1 erhoeht und es wird mit dem naechsten Verlaufselement weiter gemacht.
           */
          c_aktueller_index = c_aktueller_index + ANZAHL_1;
        }

        if ( ( c_sparstopp_knz_erreicht == B_FALSE ) && ( c_knz_sparstopp_aus_vorgaben == SPAR_STOPP_ZUTEILUNG ) )
        {
          c_datum_sparstopp_sparrate_long = c_spar_datum_zuteilung_long;

          c_sparstopp_knz_erreicht = B_TRUE;
        }

        c_millisekunden_ende = System.currentTimeMillis();

        if ( m_debug_knz_berechnungsziel_schritte )
        {
          wl( "" );
          wl( "ENDE  SUCHSCHLEIFE: c_sparstopp_datum_long                        =>" + FkDatumLong.getStringAuslong( c_datum_sparstopp_sparrate_long ) );
          wl( "ENDE  SUCHSCHLEIFE: c_tarif_minbwz_datum_long                     =>" + FkDatumLong.getStringAuslong( c_tarif_minbwz_datum_long ) );
          wl( "ENDE  SUCHSCHLEIFE: c_tarif_migu_datum_long                       =>" + FkDatumLong.getStringAuslong( c_tarif_migu_datum_long ) );
          wl( "ENDE  SUCHSCHLEIFE: c_tarif_ztv_datum_long                        =>" + FkDatumLong.getStringAuslong( c_tarif_ztv_datum_long ) );
          wl( "ENDE  SUCHSCHLEIFE: c_spar_datum_stichtag_long                    =>" + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long ) );
          wl( "ENDE  SUCHSCHLEIFE: c_spar_datum_zuteilung_long                   =>" + FkDatumLong.getStringAuslong( c_spar_datum_zuteilung_long ) );
          wl( "" );
          wl( "ZEIT Iterationsschleife Sparen Dauer in Millisekunden             =>" + ( c_millisekunden_ende - c_millisekunden_start_1 ) + "<" );
          wl( "" );
        }

        c_millisekunden_start_1 = System.currentTimeMillis();

        /* 
         * Zweiter Schritt der Zielsuchdurchlaeufe:
         * 
         * Ergebnisauswertung in Bezug mit dem Berechnungsziel und der Ermittlung,
         * ob die Suchschleife das Berechnungszielergebnis gefunden hat.
         */
        if ( ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_GUTHABEN_RATE ) || ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_POSITIV ) || ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_NEGATIV ) || ( c_rk_berechnungsziel == BZ_SPARRATE_BERECHNUNGSSCHLEIFE_3 ) )
        {
          if ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_GUTHABEN_RATE )
          {
            /* 
             * ####    ####    ####    ####    ####    ####    ####    ####
             * 
             * BERECHNUNGSZIEL SPARRATE
             * 
             * Ermittlung des durch die Sparrate zu leistenden Betrages.
             * 
             * Hauptstellschraube ist die Sparrate und die dadurch generierten Zinsen.
             * 
             * ####    ####    ####    ####    ####    ####    ####    ####
             * 
             * Berechnung Sparrate
             * Es wird die zu erreichende Sparleistung am Stichtag errechnet.
             * 
             * Die zu erreichende Sparleistung ist das Mindestguthaben zuzueglich
             * der zu zahlenden Gebuehren (Abschlussgebuehr, Kontofuehrungsgebuehr, Wop-Gebuehr)
             * 
             * Von dieser Sparleistung werden die anderweitig gezahlten Sparertraege
             * am Stichtag abgezogen (VL-Zahlungen, Sonderzahlungen und Zinsen)
             * 
             * Es verbleibt der von der Sparrate zu leistende Betrag (=Ratensparleistung)
             * 
             * Diese Ratensparleistung wird durch die Anzahl der Stichtage dividiert.
             * Das ergibt den Fehlbetrag auf das zu erreichende Guthaben.
             * Dieser Fehlbetrag wird in dieser Routine "Ratensparleistung" genannt.
             * Weil dieser Betrag durch die Sparrate zu erreichen ist.
             * 
             * Die Ratensparleistung wird nicht zu 100 Prozent aus der Sparrate beglichen.
             * 
             * Die Ratensparleistung reduziert sich um die Zinsen auf die Sparrate.
             * 
             * Es muss daher diejenige Sparrate gefunden werden, dessen Zinsertraege
             * gleich der berechneten Ratensparleistung ist.
             * 
             * Es findet eine Einpendellung der Sparrate um die "Zinsen der Sparrate" statt.
             * Sofern die Foerderungen beruecksichtigt werden sollen, spielen auch die
             * Sparraten abhaengigen Foerderbetraege eine Rolle. Das kann im Moment nur die
             * Wohnungsbaupraemie sein, da nur dort Foerderbetraege auf die Sparraten berechnet
             * werden.
             * 
             * System Berechnung Sparrate
             * Beschreibung des Wechselseitigen Systems bei Berechnungsziel Sparrate.
             * Die Foerderungsabhaengigkeiten wurden ausser acht gelassen.
             * Die Foerderungsbetraege auf die Sparrate wuerden aber den gleichen
             * Effekt wie die Zinsen besitzen.
             * 
             * Iteration 1
             * Der Sparbeitrag ist 0 und somit sind die Zinsen fuer den Sparbeitrag auch 0.
             * Das fehlen der Zinsen fuer den Sparbeitrag reduziert die anderweitige Sparleistung.
             * Das zu wenig an anderweitiger Sparleistung erhoeht die Ratensparleistung.
             * Es wird eine Sparrate berechnet welche zu hoch ist, da die Zinsen fuer den Sparbeitrag nicht enthalten sind.
             * 
             * Iteration 2
             * Der Sparbeitrag ist zu hoch und somit auch die Zinsen.
             * Das mehr an Zinsen erhoeht die Sparleistung der anderweitigen Zahlungen.
             * Die Sparleistung der anderweitigen Zahlungen reduziert den Ratensparbeitrag mehr als notwendig.
             * Die Sparrate fuer Iteration 3 wird zu niedrig ausfallen, da die Ratensparleistung zu klein berechnet worden ist.
             * 
             * Iteration 3
             * Der Sparbeitrag ist zu niedrig und somit auch die Zinsen.
             * Das weniger an Zinsen reduziert die anderweitige Sparleistung.
             * Das zu wenig an anderweitiger Sparleistung erhoeht die Ratensparleistung.
             * Es errechnet sich ein zu erreichender Ratensparbetrag, welcher zu hoch ist.
             * 
             * Je weiter die Iterationen fortschreiten, desto geringer wird der Fehlbetrag in den Zinsen,
             * desto kleiner wird die Abweichung in den berechneten Sparbeitraegen.
             * 
             * Die Sparrate ist gefunden, sofern die Sparraten zweier Iterationen sich nur noch um
             * einen gewissen Betrag unterscheiden (Im Idealfall 0).
             * 
             * Iteration 1 | 0     | 10621    | 2458,77 | 378,89 | 10621,00 | 2458,77 | 22,15
             * Iteration 2 | 22,15 | 13188,48 | -108,71 | 487,72 | 10729,82 | 2349,94 | 21,17
             * Iteration 3 | 21,17 | 13074,86 | 4,9     | 482,88 | 10724,98 | 2354,78 | 21,21
             * Iteration 4 | 21,21 | 13079,51 | 0,25    | 483,1  | 10725,20 | 2354,56 | 21,21
             * 
             * 
             * Die Iteratonsfalle
             * Es kann sein, dass die berechnete Rate nicht ausreichend ist um die 
             * geforderte Summe zu erreichen. Die Zinszahlungen sind aber so hoch, dass
             * die Ratensparleistung immer wieder dieselbe Sparrate ergibt. Dieses wuerde
             * dann immer wieder die gleichen Zinsen ergeben und die Suchschleife wuerde 
             * nie zu einem Ende kommen.
             * 
             * Ist die letze Sparrate aus dem Iterationslauf gleich der neu berechneten
             * Sparrate, wird geprueft, ob die Differenz zum Stichtagssollguthaben kleiner
             * als 2 Euro ist. Wenn dem so ist, wird auf den neu berechneten Sparbeitrag
             * 1 Cent draufaddiert und eine Variable gesetzt, welche keine weitere
             * Suchschleife mehr startet (bis auf eine mit dem korrigierten Sparbeitrag).
             * 
             * Steckt man in dieser Iterationsfalle, wird der Negativbetrag eine Tendenz
             * zu dem Sparbeitrag haben, bei welcher genau 1 Cent die Differenz zum 
             * positiven Ueberschuss (zum Stichtagsguthaben) haben wird.
             * 
             * Die Zinsen auf den zu niedrig berechneten Sparbeitrag schrumpfen, da diese
             * indirekt wieder die von der Rate zu leistenden Sparbeitrag erhoehen.
             * 
             * Die Zinsen erhoehen die Ratensparleistung nicht in dem Masse, als dass
             * eine Rate berechnet werden kann, welche einen positiven ueberschuss
             * zum Stichtagssollguthaben erreicht.
             * 
             * Im Beispiel ist es in Iteration 6 ein Fehlbetrag von "-2,65" Euro, welcher
             * dann in Iteration zu einem Fehlbetrag von "-0,3499" schrumpft, welches
             * schliesslich zur Korrektur mit dem Centbetrag fuehrt.
             *  
             * (Im Beispiel ist die Bausparsumme 1.965.000)
             * 
             * Iteration 1 |     0,00 |  23.030,00 | -598020,00 |      0,00 | 23.030,00 | 598.020,00 | 111 | 5.387,56 | Rate 0 zu niedrig
             * Iteration 2 | 5.387,56 | 645.834,70 |  24784,700 | 24.785,54 | 47.815,54 | 573.234,45 | 111 | 5.164,27 | Rate 5387,56 zu hoch
             * Iteration 3 | 5.164,27 | 619.949,59 |  -1100,409 | 23.685,62 | 46.715,62 | 574.334,38 | 111 | 5.174,18 | Rate 5164,27 zu niedrig
             * Iteration 4 | 5.174,18 | 621.098,35 |     48,359 | 23.734,37 | 46.764,37 | 574.285,61 | 111 | 5.173,73 | Rate 5174,18 zu hoch
             * Iteration 5 | 5.173,73 | 621.047,34 |     -2,65  | 23.732,20 | 46.762,20 | 574.287,79 | 111 | 5.173,76 | Rate 5173,739 zu niedrig
             * Iteration 6 | 5.173,76 | 621.049,65 |     -0,349 | 23.732,28 | 46.762,29 | 574.287,70 | 111 | 5.173,77 | Rate 5173,76 zu niedrig
             * Iteration 7 | 5.173,77 | 621.050,84 |      0,84  | 23.732,36 | 46.762,37 | 574.287,63 | 111 | 5.173,77 | Rate 5173,77 zu hoch 
             * 
             * 
             * Berechnung der Sparrate und Optimierung
             * 
             * Das Mindestsparguthaben erhoeht sich, da fuer die ZTB mehr gespart werden muss.
             * Die Abschlussgebuehr bleibt gleich, da die Bausparsumme gleich bleibt.
             * Die Kontofuehrungsgebuehren bleiben gleich, da sich die Laufzeit nicht aendert.
             * Die WOP-Gebuehren aendern sich nicht, da die Jahre gleich bleiben.
             * 
             * Der Promillewert muss in die ZTB umgerechnet werden und dieses ergibt die
             * zu erreichende Saldensumme und daraus ergibt sich das zu erreichende Bausparguthaben.
             */
            c_stichtag_betrag_soll_guthaben = e_spar_iteratonsziel_mindestsparguthaben;

            /* 
             * Es kommen die Gebuehren hinzu, da diese vom Bausparer geleistet werden muessen.
             * Dieses ist die Abschlussgebuehr, sofern diese mit den Raten verrechnet werden soll
             * und die Kontofuehrungsgebuehren bis zum Stichtag.
             */
            if ( c_knz_ag_beruecksichtigen && c_knz_ag_mit_sparbeitraegen_verrechnen )
            {
              c_stichtag_betrag_soll_guthaben = c_stichtag_betrag_soll_guthaben + e_spar_ag_betrag;
            }

            if ( c_spar_kto_gebuehr_knz_beruecksichtigen )
            {
              c_stichtag_betrag_soll_guthaben = c_stichtag_betrag_soll_guthaben + c_stichtag_betrag_gebeuhren_kfg;
            }

            c_stichtag_betrag_soll_guthaben = c_stichtag_betrag_soll_guthaben + c_stichtag_betrag_gebeuhren_wop;

            c_alter_berechneter_sparbeitrag = bsv_sparbeitrag_betrag;

            /* 
             * Berechnung der anderweitigen Sparleistungen
             * Es werden die schon vorhandenen VL-Zahlungen und Sonderzahlungen abgezogen.
             * Die VL-Zahlungen vermindern den von der Sparrate zu leistenden Betrag.
             */
            c_betrag_summe_sparleistung_anderweitig = round2DP( c_stichtag_betrag_foerderung + c_stichtag_betrag_zinsen + c_stichtag_betrag_sonderzahlung + c_stichtag_betrag_vl_as + c_stichtag_betrag_vl_ep );

            /* 
             * Berechnung der Raten-Spar-Leistung
             * Die von der Sparrate zu erreichende Sparleistung, ist das am Stichtag notwendige
             * Guthaben abzueglich der anderweitigen Sparleistungen.
             */
            c_betrag_raten_spar_leistung = c_stichtag_betrag_soll_guthaben - c_betrag_summe_sparleistung_anderweitig;

            /* 
             * Differenzbetrag
             * Von den gesamt erbrachten Sparleistungen wird das Soll-Guthaben am Stichtag abgezogen.
             * 
             * Ergebnis positiv => Es wurde ueberspart             => Rate zu hoch    => Es wurde mehr eingezahlt als notwendig ist
             * Ergebnis negativ => Sollbetrag wird nicht erreicht  => Rate zu niedrig => Es wurde zu wenig eingezahlt
             */
            c_calc_rate_differenz_zu_soll_betrag = ( c_stichtag_betrag_sparbeitrag + c_betrag_summe_sparleistung_anderweitig ) - c_stichtag_betrag_soll_guthaben;

            /* 
             * Pruefung: Betrag RatenSparleistung negativ?
             * Wird das Mindestsparguthaben durch die vorhandenen VL und Sonderzahlungen
             * erreicht, muss kein weiterer Sparbeitrag gezahlt werden. In diesem Fall
             * ist die Sparleistung, welche durch die Sparrate zu erreichen ist, negativ.
             */

            c_calc_rate_differenz_zu_altem_sparbeitrag = -100.0;

            //c_calc_rate_differenz_zu_altem_sparbeitrag = -100#;

            if ( c_calc_rate_keine_weitere_iteration )
            {
              c_calc_rate_differenz_zu_altem_sparbeitrag = BETRAG_0;
            }
            else if ( c_betrag_raten_spar_leistung < BETRAG_0 )
            {
              c_betrag_raten_spar_leistung = BETRAG_0;

              bsv_sparbeitrag_betrag = BETRAG_0;

              bsv_sparbeitrag_bezeichnung = "BSV Sparrate nicht notwendig ";

              c_calc_rate_kein_sparbeitrag_notwendig = B_TRUE;
            }
            else
            {
              c_calc_rate_kein_sparbeitrag_notwendig = B_FALSE;

              /* 
               * Spar-Leistung > 0
               * Ist die Raten-Sparleistung groesser als 0, wird dieser Betrag
               * durch die Anzahl der Sparzahlungseingaeng geteilt.
               */
              if ( c_anzahl_bsv_sparraten_bis_spar_stopp > ANZAHL_0 )
              {
                bsv_sparbeitrag_betrag = get2nk( c_betrag_raten_spar_leistung / c_anzahl_bsv_sparraten_bis_spar_stopp );

                bsv_sparbeitrag_bezeichnung = "BSV Sparrate " + bsv_sparbeitrag_betrag;

                c_calc_rate_differenz_zu_altem_sparbeitrag = Math.abs( c_alter_berechneter_sparbeitrag - bsv_sparbeitrag_betrag );

                /* 
                 * Wegen des Datentyps "double" wird hier ein Bereich abgefragt.
                 * Nur ein Vergleich auf einen Wert von 0.0 koennte schiefgehen.
                 */
                if ( ( c_calc_rate_differenz_zu_altem_sparbeitrag >= BETRAG_0 ) && ( c_calc_rate_differenz_zu_altem_sparbeitrag <= BETRAG_1 ) )
                {
                  if ( ( c_calc_rate_differenz_zu_soll_betrag > -2.0 ) && ( c_calc_rate_differenz_zu_soll_betrag < BETRAG_0 ) )
                  {
                    c_calc_rate_keine_weitere_iteration = B_TRUE;

                    bsv_sparbeitrag_betrag = bsv_sparbeitrag_betrag + 0.01;

                    c_calc_rate_differenz_zu_altem_sparbeitrag = Math.abs( c_alter_berechneter_sparbeitrag - bsv_sparbeitrag_betrag );
                  }
                }

                bsv_sparbeitrag_bezeichnung = "BSV Sparrate " + bsv_sparbeitrag_betrag;
              }
              else
              {
                e_rechenkern_fehler_nummer = FEHLER_KEINE_SPAR_RATE_BIS_SPAR_STOPP;
              }
            }

            double diff_salden_summe = get5nk( c_salden_summe_erreichung_bwz - c_stichtag_betrag_saldensumme );

            double salden_summe_fehlbetrag_je_ss = ( diff_salden_summe < BETRAG_0 ? BETRAG_0 : diff_salden_summe / c_anzahl_stichtage_bis_zum_massgeblichen_stichtag );

            String debug_string = "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: BERECHNUNGSZIEL SPARRATE (Iteration " + c_rk_zaehler_iteration + ", Sparrate " + c_alter_berechneter_sparbeitrag + ")";
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_tarif_minbwz_datum_long                  " + FkDatumLong.getStringAuslong( c_tarif_minbwz_datum_long );
            debug_string += "\n" + "calcRate: c_tarif_minbwz_knz_erreicht                " + c_tarif_minbwz_knz_erreicht;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_tarif_migu_datum_long                    " + FkDatumLong.getStringAuslong( c_tarif_migu_datum_long );
            debug_string += "\n" + "calcRate: c_tarif_migu_knz_erreicht                  " + c_tarif_migu_knz_erreicht;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_tarif_ztv_datum_long                     " + FkDatumLong.getStringAuslong( c_tarif_ztv_datum_long );
            debug_string += "\n" + "calcRate: c_tarif_ztv_knz_erreicht                   " + c_tarif_ztv_knz_erreicht;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_spar_datum_stichtag_long                 " + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long );
            debug_string += "\n" + "calcRate: c_stichtag_betrag_bewertungszahl           " + c_stichtag_betrag_bewertungszahl;
            debug_string += "\n" + "calcRate: c_anzahl_stichtage_bis_zuteilung           " + c_anzahl_stichtage_bis_zum_massgeblichen_stichtag;
            debug_string += "\n" + "calcRate: c_stichtag_betrag_saldensumme              " + c_stichtag_betrag_saldensumme;
            debug_string += "\n" + "calcRate: c_stichtag_betrag_kontostand               " + c_stichtag_betrag_kontostand + " (= absolut erreichter Wert)";
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_salden_summe_mindestens                  " + c_salden_summe_erreichung_bwz;
            debug_string += "\n" + "calcRate: diff_salden_summe                          " + diff_salden_summe;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: salden_summe_fehlbetrag_je_ss              " + salden_summe_fehlbetrag_je_ss;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: bsv_sparbeitrag_betrag                     " + bsv_sparbeitrag_betrag;
            debug_string += "\n" + "calcRate: c_alter_berechneter_calc_sparbeitrag_x     " + c_alter_berechneter_calc_sparbeitrag_x;
            debug_string += "\n" + "calcRate: tarif_mindest_bwz                          " + tarif_mindest_bwz;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + DebugCalcBsvKtoVerlauf.getGridString( kto_verlauf, B_TRUE, DebugCalcBsvKtoVerlauf.AUSGABE_ART_TEXT );

            FkDatei.schreibeDatei( FkSystem.getStdRootVerzeichnis() + "verlauf_suche_rate_1_" + c_rk_zaehler_iteration + ".txt", debug_string );

            if ( c_rk_zaehler_iteration == ITERATION_1 )
            {
              c_calc_bwz_suche_obergrenze = salden_summe_fehlbetrag_je_ss;
            }

            betrag_minder_bwz_diff = get5nk( tarif_mindest_bwz - c_stichtag_betrag_bewertungszahl );

            betrag_minder_bwz_salden_summe = get5nk( betrag_minder_bwz_diff * pCalcBsvEingabe.getRkInputBetragBausparsumme() );

            if ( tarif_bwz_faktor != 0 )
            {
              betrag_minder_bwz_salden_summe = get5nk( betrag_minder_bwz_salden_summe / tarif_bwz_faktor );
            }

            betrag_minder_bwz_fehlbetrag_je_ss = get5nk( betrag_minder_bwz_salden_summe / c_anzahl_stichtage_bis_zum_massgeblichen_stichtag );

            if ( m_debug_knz_berechnungsziel_schritte )
            {
              wl( "calcRate: " );
              wl( "calcRate: BERECHNUNGSZIEL SPARRATE (Iteration " + c_rk_zaehler_iteration + ", Sparrate " + c_alter_berechneter_sparbeitrag + ")" );
              wl( "calcRate: " );
              wl( "calcRate: c_tarif_minbwz_datum_long                  " + FkDatumLong.getStringAuslong( c_tarif_minbwz_datum_long ) );
              wl( "calcRate: c_tarif_minbwz_knz_erreicht                " + c_tarif_minbwz_knz_erreicht );
              wl( "calcRate: " );
              wl( "calcRate: c_tarif_migu_datum_long                    " + FkDatumLong.getStringAuslong( c_tarif_migu_datum_long ) );
              wl( "calcRate: c_tarif_migu_knz_erreicht                  " + c_tarif_migu_knz_erreicht );
              wl( "calcRate: " );
              wl( "calcRate: c_tarif_ztv_datum_long                     " + FkDatumLong.getStringAuslong( c_tarif_ztv_datum_long ) );
              wl( "calcRate: c_tarif_ztv_knz_erreicht                   " + c_tarif_ztv_knz_erreicht );
              wl( "calcRate: " );
              wl( "calcRate: c_spar_datum_stichtag_long                 " + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long ) );
              wl( "calcRate: c_stichtag_betrag_bewertungszahl           " + c_stichtag_betrag_bewertungszahl );
              wl( "calcRate: c_anzahl_stichtage_bis_zuteilung           " + c_anzahl_stichtage_bis_zum_massgeblichen_stichtag );
              wl( "calcRate: c_stichtag_betrag_saldensumme              " + c_stichtag_betrag_saldensumme );
              wl( "calcRate: c_stichtag_betrag_kontostand               " + c_stichtag_betrag_kontostand + " (= absolut erreichter Wert)" );
              wl( "calcRate: " );
              wl( "calcRate: Hinweis: Der Stichtagskontostand ist um den Betrag der Gebuehren niedriger, da " );
              wl( "calcRate:          die Gebuehren im Verlauf als Belastung enthalten sind." );
              wl( "calcRate: " );
              wl( "calcRate: c_salden_summe_mindestens                  " + c_salden_summe_erreichung_bwz );
              wl( "calcRate: diff_salden_summe                          " + diff_salden_summe );
              wl( "calcRate: " );
              wl( "calcRate: salden_summe_fehlbetrag_je_ss              " + salden_summe_fehlbetrag_je_ss );
              wl( "calcRate: " );
              wl( "calcRate: " );
              wl( "calcRate: betrag_minder_bwz_diff                   =>" + betrag_minder_bwz_diff + "<" );
              wl( "calcRate: betrag_minder_bwz_salden_summe           =>" + betrag_minder_bwz_salden_summe + "<" );
              wl( "calcRate: betrag_minder_bwz_fehlbetrag_je_ss       =>" + betrag_minder_bwz_fehlbetrag_je_ss + "<" );
              wl( "calcRate: " );
              wl( "calcRate: c_betrag_bss_berechnet                     " + c_betrag_bss_berechnet );
              wl( "calcRate: e_spar_betrag_tarifliches_mindestguthab.   " + e_spar_betrag_tarifliches_mindestsparguthaben + " = " + tarif_proz_mindest_sparguthaben + "% der Bss " );
              wl( "calcRate: e_spar_ag_betrag                         + " + e_spar_ag_betrag );
              wl( "calcRate: c_stichtag_betrag_gebeuhren_kfg          + " + c_stichtag_betrag_gebeuhren_kfg );
              wl( "calcRate: c_stichtag_betrag_gebeuhren_wop          + " + c_stichtag_betrag_gebeuhren_wop );
              wl( "calcRate: " );
              wl( "calcRate: c_stichtag_betrag_soll_guthaben          = " + c_stichtag_betrag_soll_guthaben );
              wl( "calcRate: " );
              wl( "calcRate: " );
              wl( "calcRate: c_stichtag_betrag_vl_as                    " + c_stichtag_betrag_vl_as );
              wl( "calcRate: c_stichtag_betrag_vl_ep                  + " + c_stichtag_betrag_vl_ep );
              wl( "calcRate: c_stichtag_betrag_sonderzahlung          + " + c_stichtag_betrag_sonderzahlung );
              wl( "calcRate: c_stichtag_betrag_zinsen                 + " + c_stichtag_betrag_zinsen + " / Zinsen ALT " + c_stichtag_betrag_zinsen_alt + " Differenz " + ( c_stichtag_betrag_zinsen - c_stichtag_betrag_zinsen_alt ) );
              wl( "calcRate: c_stichtag_betrag_foerderung             + " + c_stichtag_betrag_foerderung );
              wl( "calcRate: " );
              wl( "calcRate: c_betrag_summe_sparleistung_anderweitig  = " + c_betrag_summe_sparleistung_anderweitig );
              wl( "calcRate: " );
              wl( "calcRate: " );
              wl( "calcRate: c_betrag_raten_spar_leistung              " + c_betrag_raten_spar_leistung + " c_stichtag_betrag_sparbeitrag " + c_stichtag_betrag_sparbeitrag );
              wl( "calcRate: c_anzahl_bsv_sparraten_bis_spar_stopp   / " + c_anzahl_bsv_sparraten_bis_spar_stopp );
              wl( "calcRate: " );

              if ( c_calc_rate_kein_sparbeitrag_notwendig )
              {
                wl( "calcRate: Iteration  1: Mit den vorhandenen Einzahlungen wird die Zuteilung erreicht. Es ist kein weiterer Sparbeitrag notwendig." );

                c_calc_rate_differenz_zu_soll_betrag = BETRAG_0;
              }
              else
              {
                wl( "calcRate: bsv_sparbeitrag_betrag                    " + bsv_sparbeitrag_betrag + " (vorheriger Spabeitrag = " + c_alter_berechneter_sparbeitrag + ", Differenz = " + ( c_alter_berechneter_sparbeitrag - bsv_sparbeitrag_betrag ) + ")" );
                wl( "calcRate: " );

                if ( c_calc_rate_differenz_zu_soll_betrag > BETRAG_0 )
                {
                  wl( "calcRate: Die Sparrate von " + c_alter_berechneter_sparbeitrag + " ist zu hoch, da die gesamte Sparleistung zum Stichtag (" + get2nk( ( c_stichtag_betrag_sparbeitrag + c_betrag_summe_sparleistung_anderweitig ) ) + ") die notwendige Sparleistung (" + get2nk( c_stichtag_betrag_soll_guthaben ) + ") uebersteigt." );
                }
                else if ( c_calc_rate_differenz_zu_soll_betrag < BETRAG_0 )
                {
                  wl( "calcRate: Die Sparrate von " + c_alter_berechneter_sparbeitrag + " ist zu klein, da die gesamte Sparleistung zum Stichtag (" + get2nk( ( c_stichtag_betrag_sparbeitrag + c_betrag_summe_sparleistung_anderweitig ) ) + ") nicht die notwendige Sparleistung (" + get2nk( c_stichtag_betrag_soll_guthaben ) + ") erreicht." );
                }
              }

              wl( "calcRate: " );
              wl( "calcRate: c_calc_rate_differenz_zu_soll_betrag       = " + c_calc_rate_differenz_zu_soll_betrag + "  Check " + ( ( c_calc_rate_differenz_zu_soll_betrag >= BETRAG_0 ) && ( c_calc_rate_differenz_zu_soll_betrag <= BETRAG_1 ) ) );
              wl( "calcRate: c_calc_rate_differenz_zu_altem_sparbeitrag = " + c_calc_rate_differenz_zu_altem_sparbeitrag + "  Check " + ( ( c_calc_rate_differenz_zu_altem_sparbeitrag >= BETRAG_0 ) && ( c_calc_rate_differenz_zu_altem_sparbeitrag <= BETRAG_CALC_RATE_DIFF_MAX_GRENZE ) ) );
              wl( "calcRate: " );
              wl( "calcRate: Iteration  " + c_rk_zaehler_iteration + " | " + getBetragStr( c_alter_berechneter_sparbeitrag ) + " | " + getBetragStr( ( c_stichtag_betrag_sparbeitrag + c_betrag_summe_sparleistung_anderweitig ) ) + " | " + get4nk( c_calc_rate_differenz_zu_soll_betrag ) + " | " + getBetragStr( c_stichtag_betrag_zinsen ) + " | " + getBetragStr( c_betrag_summe_sparleistung_anderweitig ) + " | " + getBetragStr( c_betrag_raten_spar_leistung ) + " | " + c_anzahl_bsv_sparraten_bis_spar_stopp + " | " + getBetragStr( bsv_sparbeitrag_betrag ) + " | " + ( ( ( c_stichtag_betrag_sparbeitrag + c_betrag_summe_sparleistung_anderweitig ) > c_stichtag_betrag_soll_guthaben ) ? "Rate " + get3nk( c_alter_berechneter_sparbeitrag ) + " zu hoch" : "Rate " + get3nk( c_alter_berechneter_sparbeitrag ) + " zu niedrig" ) );
              wl( "calcRate: " );
            }

            c_rk_zaehler_iteration = c_rk_zaehler_iteration + ANZAHL_1;

            if ( ( c_rk_zaehler_iteration > MAX_ITERATION_CALC_RATE ) || ( ( c_calc_rate_differenz_zu_soll_betrag >= BETRAG_0 ) && ( c_calc_rate_differenz_zu_soll_betrag <= BETRAG_1 ) ) || ( ( c_calc_rate_differenz_zu_altem_sparbeitrag >= BETRAG_0 ) && ( c_calc_rate_differenz_zu_altem_sparbeitrag <= BETRAG_CALC_RATE_DIFF_MAX_GRENZE ) ) )
            {
              /* 
               * Iteratonsziel 1 erreicht
               * 
               * Die erste Iteration sucht nach der Sparrate, mit welcher das Mindestguthaben 
               * erreicht werden kann. Die erste Suchschleife laesst die Bewertungszahl 
               * mitlaufen aber optimiert nicht auf das erreichen der Bewertungszahl. 
               * 
               * Wird durch die Sparrate der ersten Suchschleife die BWZ nicht erreicht,  
               * wird eine zweite Suchschleife gestartet.
               * 
               * Mit der zweiten Suchschleife wird die Sparrate gesucht, mit welcher die  
               * Bewertungszahl erreicht wird.
               * 
               * 
               * Wurde festgestellt, dass die Sparrate erfolgreich berechnet worden ist,
               * muss immer die Sparrate als Ergebniswert gesetzt werden, mit welcher der 
               * letzte Iterationslauf durchlaufen worden ist.
               * 
               * Sofern die Rate neu berechnet worden ist, wuerde eine falsche Sparrate beauskunftet werden.
               */
              bsv_sparbeitrag_betrag = c_alter_berechneter_sparbeitrag;

              e_sparbeitrag_mgh = bsv_sparbeitrag_betrag;
              e_sparbeitrag_bwz = BETRAG_0;

              /*
               * Berechnungsziel Sparrate und Mindestbewertungszahl
               * 
               * Ist die Differenz am Bewertungsstichtag negativ, ist die von der Suchschleife  
               * berechnete Sparrate zu hoch und es wird eventuell bei einem Sparstopp MiGu zu 
               * frueh mit der Besparung aufgehoert. 
               * 
               * Es entstehen dann Luecken in der Besparung bzw. der vorhandene Zeitraum wird 
               * nicht ausgenutzt und die beauskunftete Rate waere zu hoch. 
               * 
               * In diesem Fall wird die Obergrenze fuer die BWZ Suchschleife auf den berechneten 
               * Sparbeitrag gesetzt, da dieser zu hoch ist. 
               * 
               * Die Untergrenze wird um den Fehlbetrag x verringert.
               */
              if ( c_tarif_minbwz_knz_erreicht )
              {
                if ( betrag_minder_bwz_fehlbetrag_je_ss < 0 )
                {
                  c_rk_berechnungsziel = BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_POSITIV;

                  c_calc_bwz_suche_max_iteration += c_rk_zaehler_iteration;

                  c_calc_bwz_suche_obergrenze = c_alter_berechneter_sparbeitrag;

                  c_calc_bwz_suche_untergrenze = c_calc_bwz_suche_untergrenze + betrag_minder_bwz_fehlbetrag_je_ss;

                  if ( c_calc_bwz_suche_untergrenze > c_calc_bwz_suche_obergrenze )
                  {
                    double temp_wertx = c_calc_bwz_suche_obergrenze;

                    c_calc_bwz_suche_obergrenze = c_calc_bwz_suche_untergrenze;

                    c_calc_bwz_suche_untergrenze = temp_wertx;
                  }
                }
                else
                {
                  c_rk_knz_weiterer_iterationslauf_notwendig = B_FALSE;
                }

                e_sparbeitrag_bwz = bsv_sparbeitrag_betrag;
              }
              else
              {
                /*
                 * Muss es eine zweite Suchschleife fuer das erreichen der Mindestbewertungszahl geben, 
                 * wird das interne Berechnungsziel auf auf "BZ_SPARRATE_SUCHE_MIN_BWZ_RATE" gestellt.
                 */
                c_rk_berechnungsziel = BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_POSITIV;

                /*
                 * Die maximale Iterationsanzahl wird um die Iterantionsazahl der ersten 
                 * Suchschleife erhoeht. 
                 * 
                 * 
                 */
                c_calc_bwz_suche_max_iteration += c_rk_zaehler_iteration;

                /*
                 * Berechnung Ober- und Untergrenze fuer die Zweite Suchschleife.
                 * 
                 * Untergrenze = Sparbeitrag, mit welcher die Mindestbewertungszahl nicht erreicht worden ist.
                 *               Der Sparbeitrag reicht aus, um das Mindestsparguthaben zu erreichen.
                 * 
                 * Obergrenze  = Sparbeitrag, welche zu hoch ist, da bei der Berechnung die Bildung der 
                 *               Saldensumme als linearer Vorgang angenommen worden ist. 
                 *               
                 *               Die Bildung der Saldensumme verlaeuft jedoch "exponentiell" ... halbwegs jedenfalls.
                 *               
                 * Untergrenze fuer die BWZ-Suchschleife
                 * Die Untergrenze fuer die zweite Suchschleife ist der Sparbeitrag der  
                 * ersten Suchschleife. Mit dem Sparbeitrag aus der ersten Suchschleife 
                 * ist das Erreichen des Mindestguthabens sichergestellt. 
                 * 
                 * Der Sparbeitrag, mit welchem die Bewertungszahl erreicht wird, liegt  
                 * ueber diesem Betrag.
                 */
                c_calc_bwz_suche_untergrenze = c_alter_berechneter_sparbeitrag;

                /*
                 * Obergrenze fuer die BWZ-Suchschleife
                 * Die Obergrenze fuer die zweite Suchschleife ist der Sparbeitrag der ersten 
                 * Suchschleife, zuzueglich des Betrages, welcher zum Erreichen der Mindestbewertungszahl 
                 * fehlt. 
                 * 
                 * Dieser Betrag berechnet sich aus der Saldensumme. 
                 * 
                 * Hierzu wurde der Differenz der Saldensumme zum Stichtag mit der Saldensumme
                 * fuer die Mindestbewertungszahl gebildet.
                 * 
                 * Mit der sich ergebenden Diff-Saldensumme wurd der Fehlbetrag berechnet. 
                 * 
                 * Dieser Fehlbetrag wurde dann durch die Anzahl der Stichtage bis zur Zuteilung geteilt. 
                 * 
                 * Das Ergebnis ist ein Betrag, welcher der Saldensumme je Stichtag fehlt. 
                 * 
                 * Dieser Betrag fehlt nicht insgesamt. 
                 * Die Saldensumme bildet sich aus der Summe des Kontostandes je Stichtag.
                 * Es handelt sich lediglich um eine Obergrenze des Fehlbetrages.
                 * 
                 * Diese Obergrenze wird hier auf den Sparbeitrag der ersten Suchschleife draufaddiert.
                 */
                c_calc_bwz_suche_obergrenze = c_calc_bwz_suche_untergrenze + betrag_minder_bwz_fehlbetrag_je_ss;

                if ( c_calc_bwz_suche_untergrenze > c_calc_bwz_suche_obergrenze )
                {
                  double temp_wertx = c_calc_bwz_suche_obergrenze;

                  c_calc_bwz_suche_obergrenze = c_calc_bwz_suche_untergrenze;

                  c_calc_bwz_suche_untergrenze = temp_wertx;
                }

                /*
                 * Um die erste Iteration der zweiten Suchschleife zu Kennzeichnen, wird 
                 * der Differenzbetrag zum alten Diff-Betrag auf 0 gesetzt.
                 */
                c_calc_bwz_suche_alter_diff_betrag = BETRAG_0;

                c_alter_berechneter_calc_sparbeitrag_x = bsv_sparbeitrag_betrag;

                c_calc_bwz_suche_it_zaehler = ANZAHL_0;

                c_tarif_minbwz_knz_erreicht = true; // muss sein, vereinfacht die zweite Suchschleife
              }
            }
            else
            {
              c_rk_knz_weiterer_iterationslauf_notwendig = B_TRUE;
            }
          }

          if ( c_rk_berechnungsziel == BZ_SPARRATE_BERECHNUNGSSCHLEIFE_3 )
          {
            c_rk_knz_weiterer_iterationslauf_notwendig = B_FALSE;
          }

          /*
           * Pruefung: Ist das Berechnungsziel die Sparrate in der zweiten Suchschleife?
           */
          if ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_POSITIV )
          {
            /*
             * 
             * 1. Pruefung, ob mit dem letzten Sparbeitrag die Bewertungszahl erreicht worden ist
             * 
             * 2. Wurde die Zuteilung erreicht muss eventuell eine neue Sparrate errechnet werden.
             * 
             * 3. Wurde die Zuteilung nicht erreicht, ist die zweite Suchschleife beendet.
             *    Es muss eine dritte Schleife durchlaufen werden, bei welcher die Sparrate auf 
             *    die zuletzt gueltige Rate gesetzt wird.
             */
            wl( "calcRate: ----------------------------------------------------------------------------------------------" );
            wl( "calcRate: " );
            wl( "calcRate: BERECHNUNGSZIEL SPARRATE BWZ (Iteration " + c_calc_bwz_suche_it_zaehler + ", Sparrate " + bsv_sparbeitrag_betrag + ")" );
            wl( "calcRate: " );
            wl( "calcRate: c_calc_bwz_suche_untergrenze =>" + c_calc_bwz_suche_untergrenze + "<" );
            wl( "calcRate: c_calc_bwz_suche_obergrenze  =>" + c_calc_bwz_suche_obergrenze + "<" );

            c_calc_bwz_suche_it_zaehler++;

            c_rk_zaehler_iteration++;

            /*
             * Berechnung Differenz-Saldensumme
             * 
             * Es wurde eine Saldensumme berechnet, welche fuer die Erreichung der BWZ
             */

            double diff_salden_summe = get5nk( c_salden_summe_erreichung_bwz - c_stichtag_betrag_saldensumme );

            double salden_summe_fehlbetrag_je_ss = ( diff_salden_summe < BETRAG_0 ? BETRAG_0 : diff_salden_summe / c_anzahl_stichtage_bis_zum_massgeblichen_stichtag );

            if ( c_calc_bwz_suche_it_zaehler == ANZAHL_MONATE_12 )
            {
              wl( "c_alter_berechneter_calc_sparbeitrag_x " + c_alter_berechneter_calc_sparbeitrag_x );
            }
            if ( c_calc_bwz_suche_it_zaehler == 30 )
            {
              c_rk_knz_weiterer_iterationslauf_notwendig = false;
            }

            if ( c_calc_bwz_suche_it_zaehler > ANZAHL_1 )
            {
              c_calc_rate_differenz_zu_altem_sparbeitrag = Math.abs( c_alter_berechneter_sparbeitrag - bsv_sparbeitrag_betrag );
            }
            else
            {
              c_calc_rate_differenz_zu_altem_sparbeitrag = BETRAG_100;
            }

            if ( ( c_calc_rate_differenz_zu_altem_sparbeitrag >= BETRAG_0 ) && ( c_calc_rate_differenz_zu_altem_sparbeitrag <= BETRAG_1 ) )
            {
              if ( c_tarif_minbwz_knz_erreicht == false )
              {
                c_rk_berechnungsziel = BZ_SPARRATE_BERECHNUNGSSCHLEIFE_3;

                bsv_sparbeitrag_betrag = c_alter_berechneter_calc_sparbeitrag_x;
              }
              else
              {
                c_rk_knz_weiterer_iterationslauf_notwendig = false;

                e_sparbeitrag_bwz = bsv_sparbeitrag_betrag;
              }
            }

            if ( ( c_rk_knz_weiterer_iterationslauf_notwendig ) && ( c_rk_berechnungsziel == BZ_SPARRATE_SUCHE_MIN_BWZ_RATE_DIFF_POSITIV ) )
            {
              if ( c_calc_bwz_suche_it_zaehler == 1 )
              {
                //
              }
              else if ( c_tarif_minbwz_knz_erreicht == false )
              {
                wl( "calcRate: Erhoehen der Untergrenze von " + c_calc_bwz_suche_untergrenze + " auf " + bsv_sparbeitrag_betrag );

                /*
                 * Der Saldensummen-Differenzbetrag zum alten Differenzbetrag ist groesser geworden. 
                 * 
                 * Die notwendige Saldensumme zur Erreichung der BWZ wird nicht erreicht.
                 * 
                 * Es fehlt Sparleistung. 
                 * 
                 * Es muss mehr Sparleistung erbracht werden. 
                 * 
                 * Ein Mehr an Sparleistung wird erreicht, indem die Untergrenze angehoben wird.
                 * 
                 * Dadurch erhoeht sich der Mittelwert, welcher als Sparleistung genommen wird
                 */
                c_calc_bwz_suche_untergrenze = bsv_sparbeitrag_betrag;
              }
              else
              //if ( diff_salden_summe < c_calc_bwz_suche_alter_diff_betrag )
              {
                wl( "calcRate: Verminderung der Obergrenze von " + c_calc_bwz_suche_obergrenze + " auf " + bsv_sparbeitrag_betrag );

                /*
                 * Der Differenzbetrag zum alten Differenzbetrag ist kleiner geworden. 
                 * 
                 * Es  Sparleistung, die notwendige Saldensumme wird nicht erreicht. 
                 * 
                 * Es muss weniger Sparleistung erbracht werden. 
                 * 
                 * Ein Weniger an Sparleistung wird erreicht, indem die Obergrenze abgesenkt wird.
                 * 
                 * Dadurch verringert sich der Mittelwert, welcher als Sparleistung genommen wird
                 */
                c_calc_bwz_suche_obergrenze = bsv_sparbeitrag_betrag;
              }

              if ( c_tarif_minbwz_knz_erreicht )
              {
                c_alter_berechneter_calc_sparbeitrag_x = bsv_sparbeitrag_betrag;
              }

              c_alter_berechneter_sparbeitrag = bsv_sparbeitrag_betrag;

              bsv_sparbeitrag_betrag = FkZahl.getMittelwert( c_calc_bwz_suche_untergrenze, c_calc_bwz_suche_obergrenze );
            }

            wl( "calcRate: " );
            wl( "calcRate: BERECHNUNGSZIEL SPARRATE BWZ (Iteration " + c_rk_zaehler_iteration + ", Sparrate " + c_alter_berechneter_sparbeitrag + ")" );
            wl( "calcRate: " );
            wl( "calcRate: c_tarif_minbwz_datum_long                  " + FkDatumLong.getStringAuslong( c_tarif_minbwz_datum_long ) );
            wl( "calcRate: c_tarif_minbwz_knz_erreicht                " + c_tarif_minbwz_knz_erreicht );
            wl( "calcRate: " );
            wl( "calcRate: c_tarif_migu_datum_long                    " + FkDatumLong.getStringAuslong( c_tarif_migu_datum_long ) );
            wl( "calcRate: c_tarif_migu_knz_erreicht                  " + c_tarif_migu_knz_erreicht );
            wl( "calcRate: " );
            wl( "calcRate: c_tarif_ztv_datum_long                     " + FkDatumLong.getStringAuslong( c_tarif_ztv_datum_long ) );
            wl( "calcRate: c_tarif_ztv_knz_erreicht                   " + c_tarif_ztv_knz_erreicht );
            wl( "calcRate: " );
            wl( "calcRate: c_spar_datum_stichtag_long                 " + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long ) );
            wl( "calcRate: c_stichtag_betrag_bewertungszahl           " + c_stichtag_betrag_bewertungszahl );
            wl( "calcRate: c_anzahl_stichtage_bis_zuteilung           " + c_anzahl_stichtage_bis_zum_massgeblichen_stichtag );
            wl( "calcRate: c_stichtag_betrag_saldensumme              " + c_stichtag_betrag_saldensumme );
            wl( "calcRate: c_stichtag_betrag_kontostand               " + c_stichtag_betrag_kontostand + " (= absolut erreichter Wert)" );
            wl( "calcRate: " );
            wl( "calcRate: c_salden_summe_mindestens                  " + c_salden_summe_erreichung_bwz );
            wl( "calcRate: diff_salden_summe                          " + diff_salden_summe );
            wl( "calcRate: " );
            wl( "calcRate: salden_summe_fehlbetrag_je_ss              " + salden_summe_fehlbetrag_je_ss );
            wl( "calcRate: " );
            wl( "calcRate: bsv_sparbeitrag_betrag                     " + bsv_sparbeitrag_betrag );
            wl( "calcRate: c_alter_berechneter_calc_sparbeitrag_x     " + c_alter_berechneter_calc_sparbeitrag_x );
            wl( "calcRate: tarif_mindest_bwz                          " + tarif_mindest_bwz );
            wl( "calcRate: " );

            String debug_string = "\n" + "calcRate: ";

            debug_string += "\n" + "calcRate: BERECHNUNGSZIEL SPARRATE BWZ (Iteration " + c_rk_zaehler_iteration + ", Sparrate " + c_alter_berechneter_sparbeitrag + ")";
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_tarif_minbwz_datum_long                  " + FkDatumLong.getStringAuslong( c_tarif_minbwz_datum_long );
            debug_string += "\n" + "calcRate: c_tarif_minbwz_knz_erreicht                " + c_tarif_minbwz_knz_erreicht;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_tarif_migu_datum_long                    " + FkDatumLong.getStringAuslong( c_tarif_migu_datum_long );
            debug_string += "\n" + "calcRate: c_tarif_migu_knz_erreicht                  " + c_tarif_migu_knz_erreicht;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_tarif_ztv_datum_long                     " + FkDatumLong.getStringAuslong( c_tarif_ztv_datum_long );
            debug_string += "\n" + "calcRate: c_tarif_ztv_knz_erreicht                   " + c_tarif_ztv_knz_erreicht;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_spar_datum_stichtag_long                 " + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long );
            debug_string += "\n" + "calcRate: c_stichtag_betrag_bewertungszahl           " + c_stichtag_betrag_bewertungszahl;
            debug_string += "\n" + "calcRate: c_anzahl_stichtage_bis_zuteilung           " + c_anzahl_stichtage_bis_zum_massgeblichen_stichtag;
            debug_string += "\n" + "calcRate: c_stichtag_betrag_saldensumme              " + c_stichtag_betrag_saldensumme;
            debug_string += "\n" + "calcRate: c_stichtag_betrag_kontostand               " + c_stichtag_betrag_kontostand + " (= absolut erreichter Wert)";
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: c_salden_summe_mindestens                  " + c_salden_summe_erreichung_bwz;
            debug_string += "\n" + "calcRate: diff_salden_summe                          " + diff_salden_summe;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: salden_summe_fehlbetrag_je_ss              " + salden_summe_fehlbetrag_je_ss + "";
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: bsv_sparbeitrag_betrag                     " + bsv_sparbeitrag_betrag;
            debug_string += "\n" + "calcRate: c_alter_berechneter_calc_sparbeitrag_x     " + c_alter_berechneter_calc_sparbeitrag_x;
            debug_string += "\n" + "calcRate: tarif_mindest_bwz                          " + tarif_mindest_bwz;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + "calcRate: e_sparbeitrag_mgh                          " + e_sparbeitrag_mgh;
            debug_string += "\n" + "calcRate: e_sparbeitrag_bwz                          " + e_sparbeitrag_bwz;
            debug_string += "\n" + "calcRate: ";
            debug_string += "\n" + DebugCalcBsvKtoVerlauf.getGridString( kto_verlauf, B_TRUE, DebugCalcBsvKtoVerlauf.AUSGABE_ART_TEXT );

            FkDatei.schreibeDatei( FkSystem.getStdRootVerzeichnis() + "verlauf_bwz_suche_rate_" + c_calc_bwz_suche_it_zaehler + ".txt", debug_string );

            c_calc_bwz_suche_alter_diff_betrag = diff_salden_summe;
          }
        }
        else if ( c_rk_berechnungsziel == BZ_BAUSPARSUMME )
        {
          /* 
           * ####    ####    ####    ####    ####    ####    ####    ####
           * 
           * BERECHNUNGSZIEL BAUSPARSUMME
           * 
           * Ermittlung der zu erreichenden Bausparsumme auf Grundlage der Saldensumme
           * 
           * Hauptstellschraube ist die Bausparsumme und die dadurch
           * - erreichte Bewertungszahl
           * - das zu erreichende Mindestguthaben
           * - die eventuell einzubeziehende Abschlussgebuehr
           * 
           * Bewertungszahlabhaengikeiten
           * Durch die sich aendernden Bausparsummen
           * ####    ####    ####    ####    ####    ####    ####    ####
           */

          if ( m_debug_knz_berechnungsziel_schritte )
          {
            wl( "calcBausparsumme: BERECHNUNGSZIEL BAUSPARSUMME (Iteration " + c_rk_zaehler_iteration + ", Bss " + c_betrag_bss_berechnet + ") " );
            wl( "calcBausparsumme: " );
            wl( "calcBausparsumme: c_tarif_minbwz_betrag                      " + c_tarif_minbwz_betrag );
            wl( "calcBausparsumme: c_tarif_minbwz_datum_long                  " + FkDatumLong.getStringAuslong( c_tarif_minbwz_datum_long ) );
            wl( "calcBausparsumme: c_tarif_minbwz_knz_erreicht                " + c_tarif_minbwz_knz_erreicht );
            wl( "calcBausparsumme: " );
            wl( "calcBausparsumme: e_spar_betrag_tarif_mindguthaben           " + e_spar_betrag_tarifliches_mindestsparguthaben );
            wl( "calcBausparsumme: c_tarif_migu_datum_long                    " + FkDatumLong.getStringAuslong( c_tarif_migu_datum_long ) );
            wl( "calcBausparsumme: c_tarif_migu_knz_erreicht                  " + c_tarif_migu_knz_erreicht );
            wl( "calcBausparsumme: " );
            wl( "calcBausparsumme: c_tarif_ztv_datum_long                     " + FkDatumLong.getStringAuslong( c_tarif_ztv_datum_long ) );
            wl( "calcBausparsumme: c_tarif_ztv_knz_erreicht                   " + c_tarif_ztv_knz_erreicht );
            wl( "calcBausparsumme: " );
            wl( "calcBausparsumme: c_spar_datum_stichtag_long                 " + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long ) );
            wl( "calcBausparsumme: c_stichtag_betrag_zinsen                   " + c_stichtag_betrag_zinsen + " / Zinsen ALT " + c_stichtag_betrag_zinsen_alt + " Differenz " + ( c_stichtag_betrag_zinsen - c_stichtag_betrag_zinsen_alt ) );
            wl( "calcBausparsumme: c_stichtag_betrag_kontostand               " + c_stichtag_betrag_kontostand + " (=Absolut erreichter Wert)" );
            wl( "calcBausparsumme: c_stichtag_betrag_saldensumme              " + c_stichtag_betrag_saldensumme );
            wl( "calcBausparsumme: c_stichtag_betrag_bewertungszahl           " + c_stichtag_betrag_bewertungszahl );
            wl( "calcBausparsumme: " );
            wl( "calcBausparsumme: c_betrag_akt_bewertungszahl                " + c_betrag_akt_bewertungszahl );
            wl( "calcBausparsumme: " );
          }

          c_betrag_bss_obergrenze_berechnet = c_betrag_bss_berechnet;

          /* 
           * Calc-Bausparsumme
           * 
           * Es erfolgt kein Iterations-Abbruch, wenn der Iterationszaehler auf 1 steht.
           * In der ersten Iteration ist keine Bausparsumme gesetzt und somit kann das
           * Berechnungsziel in Iteration 1 noch nicht gefunden worden sein.
           * 
           * 
           * Ende Suchlauf 1
           * 
           * Das Berechnungsziel ist gefunden, wenn mit der ermittelten Bausparsumme die
           * Zuteilungsvorraussetzungen am berechneten Stichtag oder eventuell frueher
           * erfuellt sind und der Iterationszaehler groesser als 1 ist.
           * 
           * Ende Suchlauf 2
           * 
           * Die Zuteilungsvorraussetzungen werden zu einem frueheren Zeitpunkt erreicht:
           * 
           * - Die Sparzahlungen reichen aus, um zu einem frueheren Stichtag die
           *   Zuteilungsvorraussetzungen erfuellen zu koennen.
           * 
           * - Die Sparzahlungen reichen aber nicht aus, um zwischen den beiden
           *   Stichtagen eine Ehoehung der Bausparsumme um 1000 Euro hinzubekommen.
           *   Daher sind weitere Berechnungen zwecklos.
           * 
           * - Der Iterationszaehler ist groesser als 1
           */
          if ( ( c_rk_zaehler_iteration > ITERATION_1 ) && ( ( c_tarif_ztv_knz_erreicht ) && ( c_tarif_ztv_datum_long <= c_spar_datum_stichtag_long ) ) )
          {
            if ( m_debug_knz_berechnungsziel_schritte )
            {
              wl( "calcBausparsumme: Zuteilung wurde am berechneten Stichtag (" + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long ) + ") erreicht. Es muss keine neue Bausparsumme berechnet werden." );
              wl( "calcBausparsumme: " );
              wl( "calcBausparsumme: Iteration " + c_rk_zaehler_iteration + " " + getBetragStr( c_stichtag_betrag_kontostand ) + " " + getBetragStr( c_betrag_bss_saldensumme_stichtag ) + " " + getBetragStr( c_betrag_bss_kto_stand_stichtag ) );
              wl( "calcBausparsumme: " );
            }

            c_rk_knz_weiterer_iterationslauf_notwendig = B_FALSE;
          }
          else
          {
            /* 
             * Berechnung einer neuen Bausparsumme
             * 
             * Der Iterationszaehler ist 1 => Dann ist keine Bausparsumme gesetzt
             * 
             * oder:
             * 
             * Zuteilungsvorraussetzungen wurden nicht oder zu einem spaeteren Zeitpunkt (als dem berechneten Stichtag) erreicht
             */
            if ( m_debug_knz_berechnungsziel_schritte )
            {
              if ( ( c_tarif_ztv_knz_erreicht ) && ( c_tarif_ztv_datum_long > c_spar_datum_stichtag_long ) )
              {
                wl( "calcBausparsumme: Zuteilung wurde nach dem berechneten Stichtag (" + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long ) + ") erreicht. Berechnung einer neuen Bausparsumme" );
              }
              else
              {
                wl( "calcBausparsumme: Zuteilung wurde nicht erreicht. Berechnung einer neuen Bausparsumme" );
              }

              wl( "calcBausparsumme: " );
            }

            c_rk_knz_weiterer_iterationslauf_notwendig = B_TRUE;

            /* 
             * Kontostand zum Stichtag
             * Nach der Berechnung steht der erreichte Kontstand am Stichtag fest.
             * 
             * Im ersten Suchlauf ist die AG 0 ist. Daher faellt keine Belastung der AG an.
             * Das fehlen der AG ergibt ein mehr an Kontostand.
             * Das hoehere an Kontostand bewirkt ein mehr an Zinsen.
             * Das mehr an Zinsen und das fehlen der AG, fuehrt letztendlich zu einem
             * hoeheren Kontostand und Saldensumme zum berechneten Stichtag.
             * 
             * Die in der zweiten Berechnung beruecksichtigte AG fuehrt dazu, dass sich
             * der Kontostand um den Betrag der Abschlussgebuehr verringert.
             * Dieses bedeutet auch eine Verringerung der gutgeschriebenen Zinsen
             * bis zum Stichtag, welches ebenfalls einen geringren Kontostand
             * zur Folge hat.
             * 
             * Bausparsumme Mindestguthaben
             * 
             * Bausparsumme auf der Grundlage des erreichten Kontostandes am Stichtag
             */
            c_betrag_bss_kto_stand_stichtag = round2DP( getVolleTausenderBSS( ( BETRAG_100 * c_stichtag_betrag_kontostand ) / tarif_proz_mindest_sparguthaben ) );

            /* 
             * Bausparsumme Saldensumme am Stichtag
             * Die Bausparsumme aufgrund der Saldensumme wird mittels der zu erreichenden
             * Zielbewertungszahl ermittelt.
             * 
             * Dieses ist im Normalfall die Mindestbewertungszahl des Tarifes.
             * Durch eine Vorgabe, kann diese Berwertungszahl auch hoeheer ausfallen (wenn z.B. eine
             * bestimmter ZTB erreicht werden soll)
             * 
             * Die Bewertungszahl wird bei einer normalen Berechnung wie folgt ermittelt:
             * (inklusive BWZ-Faktor)
             * 
             *          Bewertungszahl = ( Saldensumme * Faktor ) / Bausparsumme
             * 
             *          Bausparsumme   = ( Saldensumme / Bewertungszahl ) * Faktor
             * 
             * Der Bewertungszahlfaktor muss/darf nur beruecksichtigt werden, sofern dieser
             * groesser als 0 ist. Es wuerde ansonsten eine Bausparsumme von 0 berechnet werden.
             */
            if ( tarif_bwz_faktor > BETRAG_0 )
            {
              c_betrag_bss_saldensumme_stichtag = round2DP( getVolleTausenderBSS( ( c_stichtag_betrag_saldensumme / c_tarif_minbwz_betrag ) * tarif_bwz_faktor ) );
            }
            else
            {
              c_betrag_bss_saldensumme_stichtag = round2DP( getVolleTausenderBSS( c_stichtag_betrag_saldensumme / c_tarif_minbwz_betrag ) );
            }

            if ( m_debug_knz_berechnungsziel_schritte )
            {
              wl( "calcBausparsumme: c_betrag_bss_saldensumme_stichtag        =>" + c_betrag_bss_saldensumme_stichtag + "<" );
              wl( "calcBausparsumme: c_betrag_bss_kto_stand_stichtag          =>" + c_betrag_bss_kto_stand_stichtag + "<" );
              wl( "calcBausparsumme: " );
            }

            /* 
             * Ermittlung berechnete Bausparsumme
             * Es muss von beiden berechneten Bausparsumme die kleinere fuer die
             * naechste Berechnung genommen werden.
             */
            if ( c_betrag_bss_saldensumme_stichtag < c_betrag_bss_kto_stand_stichtag )
            {
              c_betrag_bss_berechnet = c_betrag_bss_saldensumme_stichtag;

              if ( m_debug_knz_berechnungsziel_schritte )
              {
                wl( "calcBausparsumme: c_betrag_bss_saldensumme_stichtag < c_betrag_bss_kto_stand_stichtag " );
                wl( "calcBausparsumme: " );
                wl( "calcBausparsumme: Neue Bss = c_betrag_bss_saldensumme_stichtag = " + c_betrag_bss_berechnet );
              }
            }
            else
            {
              c_betrag_bss_berechnet = c_betrag_bss_kto_stand_stichtag;

              if ( m_debug_knz_berechnungsziel_schritte )
              {
                wl( "calcBausparsumme: c_betrag_bss_saldensumme_stichtag >= c_betrag_bss_kto_stand_stichtag " );
                wl( "calcBausparsumme: " );
                wl( "calcBausparsumme: Neue Bss = c_betrag_bss_kto_stand_stichtag = " + c_betrag_bss_berechnet );
              }
            }

            if ( tarif_regel_sparbeitrag > BETRAG_0 )
            {
              c_bss_aufgrunlage_sparrate = getVolleTausenderBSS( ( bsv_sparbeitrag_betrag / tarif_regel_sparbeitrag ) * BETRAG_1000 );

              // c_spar_mindest_sparrate_wegen_bss = round2DP( getTausender( c_betrag_bss_berechnet ) * tarif_regel_sparbeitrag );
            }
            /* 
             */
            if ( ( c_betrag_bss_berechnet >= c_betrag_bss_obergrenze_berechnet ) && ( c_rk_zaehler_iteration > 1 ) )
            {
              if ( m_debug_knz_berechnungsziel_schritte )
              {
                wl( "calcBausparsumme: " );
                wl( "calcBausparsumme: Berechnete BSS " + c_betrag_bss_berechnet + " ist groesser gleich der Obergrenze von " + c_betrag_bss_obergrenze_berechnet );
                wl( "calcBausparsumme: " );
                wl( "calcBausparsumme: Berechnete BSS wird auf " + ( c_betrag_bss_obergrenze_berechnet - BETRAG_1000 ) + " gesetzt. (... und eben gleichzeitig die Untergrenze angepasst)" );
                wl( "calcBausparsumme: " );
              }

              c_betrag_bss_berechnet = c_betrag_bss_obergrenze_berechnet - BETRAG_1000;
            }

            e_spar_betrag_tarifliche_abschlussgebuehr = round2DP( ( c_betrag_bss_berechnet * tarif_promille_abschluss_gebuehr ) * MULTIPLIKATOR_DURCH_100 );

            e_spar_betrag_tarifliches_mindestsparguthaben = round2DP( c_betrag_bss_berechnet * tarif_proz_mindest_sparguthaben ) * MULTIPLIKATOR_DURCH_100;

            /* 
             * Korrigierter Kontostand Stichtag
             * Im alten Kontostand ist eventuell noch die gezahlte Abschlussgebuehr auf die
             * alte berechnete Bausparsumme enthalten.
             * 
             * Genauer: Der Kontostand am Stichtag ist um diesen Betrag niedriger (mal abgesehen
             * von den Kontofuehrungsgebuehren). Da die AG bezahlt worden ist, muss der Betrag
             * 
             * Korrektur um die geleistete Abschlussgebuehr.
             * Die alte Abschlussgebuehr wird auf den Kontostand draufaddiert, da diese
             * vom Bausparer geleistet worden ist. Da die Abschlussgebuehr aber als
             * Belastung (negativer Betrag) im Guthaben enthalten ist, muss die Sparleistung
             * fuer die Abschlussgebuehr wieder hinzugezogen werden.
             * 
             * Diese Korrektur muss gemacht werden, damit die naechsten Pruefungen korrekt
             * durchgefuehrt werden koennen. Es soll vermieden werden, dass aufgrund eines
             * falschen Stichtagsguthabens (... eines zu kleinen Guthabens) ein falsches
             * Ergebnis der Zuteilungsvorhersage getroffen wird.
             * 
             * Die Aussage, ob das Mindestsparguthaben fuer die neue BSS erreicht wird, soll
             * auf dem korrigierten Stichtagsguthaben aufsetzen.
             * 
             * Es koennte sein, dass durch eine "nicht korrektur" eine moegliche Bausparsumme
             * verworfen wird.
             */
            kto_stand_stichtag_korrigiert = c_stichtag_betrag_kontostand + e_spar_ag_betrag;

            if ( m_debug_knz_berechnungsziel_schritte )
            {
              wl( "calcBausparsumme: " );
              wl( "calcBausparsumme: Korrektur Sparleistung  c_stichtag_betrag_kontostand " + c_stichtag_betrag_kontostand + " + e_spar_ag_betrag " + e_spar_ag_betrag + ". " + kto_stand_stichtag_korrigiert );
              wl( "calcBausparsumme: " );
            }

            /* 
             * AG Neu
             * Es wird ermittelt, welche Abschlussgebuehr mit der neuen Bausparsumme anfaellt.
             * 
             * Der Betrag ist 0, wenn die Abschlussgebuehr nicht beruecksichtigt werden soll.
             * Der Betrag ist 0, wenn die Abschlussgebuehr separat gezahlt wird (da dieser Betrag sich dann nicht auswirkt)
             * 
             * Der Betrag ist gleich dem Vorgabebetrag, sofern ein Vorgabetrag vorliegt.
             * 
             * Der Betrag ist die tarifliche Abschlusgebuehr, sofern keine der vorliegenden Optionen zutrifft.
             */
            if ( c_knz_ag_beruecksichtigen )
            {
              if ( c_spar_ag_betrag_vorgabe == BETRAG_NICHT_GESETZT )
              {
                if ( c_knz_ag_mit_sparbeitraegen_verrechnen )
                {
                  c_calc_bss_ag_zu_leisten_neu = e_spar_betrag_tarifliche_abschlussgebuehr;
                }
                else
                {
                  c_calc_bss_ag_zu_leisten_neu = BETRAG_0;
                }
              }
              else
              {
                c_calc_bss_ag_zu_leisten_neu = c_spar_ag_betrag_vorgabe;
              }
            }
            else
            {
              c_calc_bss_ag_zu_leisten_neu = BETRAG_0;
            }

            /* 
             * Pruefung: Bss gegen Mindestguthaben
             * 
             * Der Kontostand am Stichtag muss das Mindestguthaben der berechneten
             * neuen Bausparsumme abdecken koennen. Dieses wird hier geprueft.
             * 
             * Reicht der Kontostand aus, wird nur ein Hinweis in das Log geschrieben.
             * 
             * Reicht der Kontostand nicht, muss die Bss jeweils in 1000er Schritten reduziert werden.
             * Bei der Reduzierung darf die Bausparsumme nicht unter die Mindestbausparsumme des
             * Tarifes gelangen. Diese Reduzierung muss nur gemacht werden, sofern die berechnete
             * Bausparsumme groesser als die Mindestbausparsumme des Tarifes ist.
             */
            if ( round2DP( ( kto_stand_stichtag_korrigiert - c_calc_bss_ag_zu_leisten_neu ) - e_spar_betrag_tarifliches_mindestsparguthaben ) >= BETRAG_0 )
            {
              if ( m_debug_knz_berechnungsziel_schritte )
              {
                wl( "calcBausparsumme: Betrag Mindestguthaben von " + e_spar_betrag_tarifliches_mindestsparguthaben + " wird erreicht. (Stichtagkontostand " + getBetragStr( kto_stand_stichtag_korrigiert ) + " - Minguthaben = " + round2DP( ( kto_stand_stichtag_korrigiert - c_calc_bss_ag_zu_leisten_neu ) - e_spar_betrag_tarifliches_mindestsparguthaben ) + ")" );
              }
            }
            else if ( c_betrag_bss_berechnet >= tarif_mindest_bss )
            {
              if ( m_debug_knz_berechnungsziel_schritte )
              {
                wl( "calcBausparsumme: Betrag Mindestguthaben von " + e_spar_betrag_tarifliches_mindestsparguthaben + " wird nicht erreicht. Die neue Bss von " + c_betrag_bss_berechnet + " kann nicht erreicht werden. " + ( ( kto_stand_stichtag_korrigiert - c_calc_bss_ag_zu_leisten_neu ) - e_spar_betrag_tarifliches_mindestsparguthaben ) );
                wl( "calcBausparsumme: " );
                wl( "calcBausparsumme: Die Baussparumme wird jetzt iterativ immer um einen Betrag von " + BETRAG_1000 + " reduziert, bis das jeweilige Mindestguthaben erreicht wird." );
                wl( "calcBausparsumme: " );
              }

              c_bss_vor_reduzierung = c_betrag_bss_berechnet;

              c_calc_bss_fehlbetrag_zu_migu_neu = ( e_spar_betrag_tarifliches_mindestsparguthaben - ( kto_stand_stichtag_korrigiert - c_calc_bss_ag_zu_leisten_neu ) );

              c_calc_bss_fehlbetrag_umgerechnet_in_bss = round2DP( ( getVolleTausenderBSS( ( BETRAG_100 * c_calc_bss_fehlbetrag_zu_migu_neu ) / tarif_proz_mindest_sparguthaben ) - BETRAG_1000 ) );

              //c_calc_bss_fehlbetrag_umgerechnet_in_bss = c_calc_bss_fehlbetrag_umgerechnet_in_bss - BETRAG_1000 // Test: Reduzierung Fehlbetrag um nochmals 1000

              if ( c_calc_bss_fehlbetrag_umgerechnet_in_bss > BETRAG_0 )
              {
                if ( m_debug_knz_berechnungsziel_schritte )
                {
                  wl( "calcBausparsumme: c_calc_bss_fehlbetrag_zu_migu_neu " + c_calc_bss_fehlbetrag_zu_migu_neu + " = c_calc_bss_fehlbetrag_umgerechnet_in_bss " + c_calc_bss_fehlbetrag_umgerechnet_in_bss + " = c_betrag_bss_berechnet " + c_betrag_bss_berechnet + " auf " + ( c_betrag_bss_berechnet - c_calc_bss_fehlbetrag_umgerechnet_in_bss ) + " " );
                  wl( "calcBausparsumme: " );
                }

                c_betrag_bss_berechnet = c_betrag_bss_berechnet - c_calc_bss_fehlbetrag_umgerechnet_in_bss;
              }

              /* 
               * je 1000 Euro BSS gibts % Mindestsparguthaben
               * 
               * Betrag Mindestguthaben von 39000 wird nicht erreicht. Die neue Bss von 130000 kann nicht erreicht werden. -1970,1
               * Reduzierung um 1000 auf jetzt 129000. -1670,1
               * Reduzierung um 1000 auf jetzt 128000. -1370,1
               * Reduzierung um 1000 auf jetzt 127000. -1070,1
               * Reduzierung um 1000 auf jetzt 126000. -770,099999999999
               * Reduzierung um 1000 auf jetzt 125000. -470,099999999999
               * Reduzierung um 1000 auf jetzt 124000. -170,099999999999
               * Reduzierung um 1000 auf jetzt 123000.  129,900000000001
               * 
               * calcBausparsumme: c_betrag_bss_kto_stand_stichtag = 123000
               * calcBausparsumme: c_betrag_bss_saldensumme_stichtag = 149000
               */
              while ( ( ( ( ( kto_stand_stichtag_korrigiert - c_calc_bss_ag_zu_leisten_neu ) - e_spar_betrag_tarifliches_mindestsparguthaben ) < BETRAG_0 ) && ( c_betrag_bss_berechnet > c_betrag_bss_untergrenze_reduzierung ) ) )
              {
                c_betrag_bss_berechnet = c_betrag_bss_berechnet - BETRAG_1000;

                e_spar_betrag_tarifliche_abschlussgebuehr = round2DP( ( c_betrag_bss_berechnet * tarif_promille_abschluss_gebuehr ) * MULTIPLIKATOR_DURCH_100 );

                e_spar_betrag_tarifliches_mindestsparguthaben = round2DP( c_betrag_bss_berechnet * tarif_proz_mindest_sparguthaben ) * MULTIPLIKATOR_DURCH_100;

                /* 
                 * Neuberechnung AG
                 * In der Reduzierungsschleife muss der neu zu leistende AG-Betrag
                 * nur gesetzt werden, sofern dieser mit den Sparleistungen zu
                 * verrechnet werden soll und es keine anderweitige Vorgabe gibt.
                 * 
                 * Bei allen anderen Konstellationen muss der AG-Betrag nur einmal gesetzt
                 * werden.
                 */
                if ( c_knz_ag_beruecksichtigen )
                {
                  if ( c_spar_ag_betrag_vorgabe == BETRAG_NICHT_GESETZT )
                  {
                    if ( c_knz_ag_mit_sparbeitraegen_verrechnen )
                    {
                      c_calc_bss_ag_zu_leisten_neu = e_spar_betrag_tarifliche_abschlussgebuehr;
                    }
                  }
                }

                if ( m_debug_knz_berechnungsziel_schritte )
                {
                  wl( "calcBausparsumme: Reduzierung BSS " + c_betrag_bss_berechnet + ", AG " + c_calc_bss_ag_zu_leisten_neu + ", Kto-Stand Stichtag " + ( kto_stand_stichtag_korrigiert - c_calc_bss_ag_zu_leisten_neu ) + ", MinGu " + e_spar_betrag_tarifliches_mindestsparguthaben + ", " + ( ( kto_stand_stichtag_korrigiert - c_calc_bss_ag_zu_leisten_neu ) - e_spar_betrag_tarifliches_mindestsparguthaben ) );
                }
              }

              if ( m_debug_knz_berechnungsziel_schritte )
              {
                wl( "calcBausparsumme: " );
                wl( "calcBausparsumme: c_bss_vor_reduzierung " + c_bss_vor_reduzierung + " zu " + c_betrag_bss_berechnet + ", Differenz " + ( c_bss_vor_reduzierung - c_betrag_bss_berechnet ) );
                wl( "calcBausparsumme: " );
              }
            }

            /* 
             * Pruefung: Bss kleiner tariflicher Mindest-Bss
             * Ist die berechnete Bausparsumme kleiner als die tarifliche Mindestbausparsumme,
             * wird das Fehlerkennzeichen auf "FEHLER_BSS_UNTERSCHREITET_MINDEST_BSS" gesetzt.
             */
            if ( c_rk_zaehler_iteration > ITERATION_1 )
            {
              if ( c_betrag_bss_berechnet < tarif_mindest_bss )
              {
                e_rechenkern_fehler_nummer = FEHLER_BSS_UNTERSCHREITET_MINDEST_BSS;
              }
            }

            /* 
             * Berechnung Abschlussgebuehr
             * Es wird geprueft, ob die Abschlussgebuehr beruecksichtigt werden soll. 
             * 
             * Soll die AG beruecksichtigt werden, wird zuerst dass Kennzeichen fuer 
             * das setzen des AG-Betrages in der Berechnungsschleife gesetzt. 
             * 
             * Danach wird geprueft, ob ein fester Abschlussgebuehrbetrag von aussen 
             * vorgegeben wurde. Wenn es so einen Betrag gibt, muss hier der AG-Betrag
             * nicht berechnet werden. Wurde kein AG-Betrag vorgegeben, wird der 
             * AG-Betrag berechnet und die Kontoverlaufsbezeichnung neu erstellt.
             */
            if ( c_knz_ag_beruecksichtigen )
            {
              c_calc_bss_knz_set_ag_betrag = B_TRUE;

              if ( c_spar_ag_betrag_vorgabe == BETRAG_NICHT_GESETZT )
              {
                e_spar_ag_betrag = e_spar_betrag_tarifliche_abschlussgebuehr;

                c_spar_ag_bezeichnung = "Abschlussgebuehr " + tarif_promille_abschluss_gebuehr + " Promille der Bss " + c_betrag_bss_berechnet + ", " + e_spar_ag_betrag;
              }
            }
            else
            {
              e_spar_ag_betrag = BETRAG_0;
            }

            if ( ( c_betrag_bss_berechnet == BETRAG_0 ) || ( c_rk_zaehler_iteration > MAX_ITERATION_CALC_BSS ) )
            {
              c_rk_knz_weiterer_iterationslauf_notwendig = B_FALSE;
            }

            if ( m_debug_knz_berechnungsziel_schritte )
            {
              wl( "calcBausparsumme: " );
              wl( "calcBausparsumme: Iteration " + c_rk_zaehler_iteration + " " + getBetragStr( c_stichtag_betrag_kontostand ) + " " + getBetragStr( c_betrag_bss_saldensumme_stichtag ) + " " + getBetragStr( c_betrag_bss_kto_stand_stichtag ) );
              wl( "calcBausparsumme: " );
            }
          }

          if ( c_rk_knz_weiterer_iterationslauf_notwendig )
          {
            c_rk_zaehler_iteration = c_rk_zaehler_iteration + ANZAHL_1;
          }
        }
        else if ( c_rk_berechnungsziel == BZ_LAUFZEIT )
        {
          if ( m_debug_knz_berechnungsziel_schritte )
          {
            wl( "calcLaufzeit: BERECHNUNGSZIEL LAUFZEIT (Iteration " + c_rk_zaehler_iteration + ", Stichtag " + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long ) + ")" );
            wl( "calcLaufzeit: " );
            wl( "calcLaufzeit: c_tarif_minbwz_betrag                 " + c_tarif_minbwz_betrag );
            wl( "calcLaufzeit: c_tarif_minbwz_datum_long             " + FkDatumLong.getStringAuslong( c_tarif_minbwz_datum_long ) );
            wl( "calcLaufzeit: c_tarif_minbwz_knz_erreicht           " + c_tarif_minbwz_knz_erreicht );
            wl( "calcLaufzeit: " );
            wl( "calcLaufzeit: e_spar_betrag_tarif_mindguthaben      " + e_spar_betrag_tarifliches_mindestsparguthaben );
            wl( "calcLaufzeit: c_tarif_migu_datum_long               " + FkDatumLong.getStringAuslong( c_tarif_migu_datum_long ) );
            wl( "calcLaufzeit: c_tarif_migu_knz_erreicht             " + c_tarif_migu_knz_erreicht );
            wl( "calcLaufzeit: " );
            wl( "calcLaufzeit: c_tarif_ztv_datum_long                " + FkDatumLong.getStringAuslong( c_tarif_ztv_datum_long ) );
            wl( "calcLaufzeit: c_tarif_ztv_knz_erreicht              " + c_tarif_ztv_knz_erreicht );
            wl( "calcLaufzeit: " );
            wl( "calcLaufzeit: c_spar_datum_stichtag_long            " + FkDatumLong.getStringAuslong( c_spar_datum_stichtag_long ) );
            wl( "calcLaufzeit: c_stichtag_betrag_zinsen              " + c_stichtag_betrag_zinsen + " / Zinsen ALT " + c_stichtag_betrag_zinsen_alt + " Differenz " + ( c_stichtag_betrag_zinsen - c_stichtag_betrag_zinsen_alt ) );
            wl( "calcLaufzeit: c_stichtag_betrag_kontostand          " + c_stichtag_betrag_kontostand + " (=Absolut erreichter Wert)" );
            wl( "calcLaufzeit: c_stichtag_betrag_saldensumme         " + c_stichtag_betrag_saldensumme );
            wl( "calcLaufzeit: c_stichtag_betrag_bewertungszahl      " + c_stichtag_betrag_bewertungszahl );
            wl( "calcLaufzeit: " );
            wl( "calcLaufzeit: c_betrag_akt_bewertungszahl           " + c_betrag_akt_bewertungszahl );
            wl( "calcLaufzeit: c_betrag_akt_kontostand               " + c_betrag_akt_kontostand );
            wl( "calcLaufzeit: " );
            wl( "calcLaufzeit: c_spar_datum_zuteilung_long           " + FkDatumLong.getStringAuslong( c_spar_datum_zuteilung_long ) );
            wl( "calcLaufzeit: c_betrag_ges_bsv_sparbeitrag          " + c_betrag_ges_bsv_sparbeitrag );
            wl( "calcLaufzeit: c_betrag_ges_kto_gebuehren_spar       " + c_betrag_ges_kto_gebuehren_spar );
            wl( "calcLaufzeit: c_betrag_ges_sonderzahlung            " + c_betrag_ges_sonderzahlung );
            wl( "calcLaufzeit: c_betrag_ges_vl_antragsteller         " + c_betrag_ges_vl_antragsteller );
            wl( "calcLaufzeit: c_betrag_ges_vl_ehepartner            " + c_betrag_ges_vl_ehepartner );
            wl( "calcLaufzeit: c_betrag_ges_zinsen_spar              " + c_betrag_ges_zinsen_spar );
            wl( "calcLaufzeit: " );
            wl( "calcLaufzeit: c_anzahl_bsv_sparraten_bis_spar_stopp " + c_anzahl_bsv_sparraten_bis_spar_stopp );
            wl( "calcLaufzeit: " );
            wl( "calcLaufzeit: c_rk_anzahl_elemente                  " + c_rk_anzahl_elemente );
            wl( "calcLaufzeit: " );
          }

          if ( c_rk_zaehler_iteration == ITERATION_1 )
          {
            /* 
             * Berechnungsziel Laufzeit nach der Ersten Iteration
             * 
             * Das Ergebnis der Suchschleife ist der fuer die Zuteilung massgebliche Stichtag.
             * Auf diesen Stichtag werden die Wartezeitmonate hinzaddiert, welches dann
             * den Zuteilungstermin ergibt.
             * 
             * Wurde das Stichtagsdatum nicht gesetzt, wird der Fehler:
             * 
             *           FEHLER_ZUTEILUNG_WIRD_NICHT_ERREICHT
             * 
             * gesetzt.
             */
            if ( c_spar_datum_stichtag_long == DATUM_0 )
            {
              e_rechenkern_fehler_nummer = FEHLER_ZUTEILUNG_WIRD_NICHT_ERREICHT;
            }
            else
            {
              c_spar_datum_zuteilung_long = FkDatumLong.longDateAdd( c_spar_datum_stichtag_long, ANZAHL_JAHRE_0, tarif_wartezeit_in_monaten, ANZAHL_TAGE_0, KNZ_TAG_MONATSENDE );
            }

            /*
             * Berechnung Gesamtlaufzeit
             * Aus dem jetzt feststehenden Zuteilungsdatum wird die Gesamtlaufzeit in Monaten berechnet.
             */
            e_spar_laufzeit_gesamt_monate = FkDatumLong.getMonatsDifferenz( c_spar_datum_zuteilung_long, e_spar_datum_berechnungsbeginn_long );

            c_aktueller_index = INDEX_0;

            if ( m_debug_knz_berechnungsziel_schritte )
            {
              wl( "calcLaufzeit: " );
              wl( "calcLaufzeit: e_spar_laufzeit_gesamt_monate " + e_spar_laufzeit_gesamt_monate );
              wl( "calcLaufzeit: c_spar_datum_zuteilung_long   " + c_spar_datum_zuteilung_long + " " + FkDatumLong.getDatumStringAusLong( c_spar_datum_zuteilung_long ) );
              wl( "calcLaufzeit: " );
              wl( "calcLaufzeit: Loeschung von nicht benoetigten Elemente aus dem Verlauf" );
              wl( "calcLaufzeit: " );
            }

            /* 
             * Ueberfluessige Elemente loeschen
             * 
             * Alle im Verlauf vorhandenen Elemente nach dem berechneten Endtermin (=Zuteilung)
             * werden fuer das Loeschen markiert.
             * 
             * Alle Elemente vor dem Zuteilungstermin bleiben erhalten. Bei diesen Elementen
             * muss daher nicht auf die Elementart geprueft werden.
             * 
             * Die Elementart muss nur dann geprueft werden, wenn es sich um ein Element handelt,
             * welches fuer die korrekte Abrechnung des Bausparvertrages notwendig ist. Dieses
             * sind diejenigen Elemente, welche selber am Stichtag anfallen (Gutschriften).
             * 
             * Gutschriften vor dem Zuteilungstermin ????
            */
            while ( c_aktueller_index < c_rk_anzahl_elemente )
            {
              akt_element = kto_verlauf.getIndex( c_aktueller_index );

              if ( akt_element != null )
              {
                c_calc_laufzeit_knz_loesche_element = B_TRUE;

                if ( ( akt_element.getArt() == KTO_ART_SPAR_PHASE_ZUTEILUNG ) || ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_ZINS_ZUTEILUNG ) || ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_WOP_ZUTEILUNG ) || ( akt_element.getArt() == KTO_ART_GUTSCHRIFT_BONUS_ZUTEILUNG ) )
                {
                  c_calc_laufzeit_knz_loesche_element = B_FALSE;

                  /* 
                   * Es wird das Datum bei diesem Element angepasst
                   */
                  akt_element.setDatum( c_spar_datum_zuteilung_long );

                  /*
                   * Da das Datum geaendert worden ist, wird auch eine neue Sort-ID noetig.
                   */
                  akt_element.completeSortID();

                  /* 
                   * Das Loeschkennzeichen wird auf 0 gesetzt (= Element bleibt erhalten)
                   */
                  akt_element.setKnzLoeschung( KNZ_ELEMENT_BEHALTEN );
                }

                if ( c_calc_laufzeit_knz_loesche_element )
                {
                  if ( akt_element.getDatum() > c_spar_datum_zuteilung_long )
                  {
                    akt_element.setKnzLoeschung( KNZ_ELEMENT_LOESCHEN );

                    akt_element.setElementId( "LOESCH_NACH_ZUTEILUNG_" + akt_element.getElementId() );

                    wl( "calcLaufzeit: Loesche Elemnt 2 - " + akt_element.toStringZeile() );

                    c_rk_temp_index = c_rk_temp_index + ANZAHL_1;

                    c_calc_laufzeit_knz_loesche_element = false;
                  }
                }

                if ( ( c_calc_laufzeit_knz_loesche_element ) && ( akt_element.getArt() == KTO_ART_BSV_SPARBEITRAG ) || ( akt_element.getArt() == KTO_ART_VL_ANTRAGSTELLER ) || ( akt_element.getArt() == KTO_ART_VL_EHEPARTNER ) )
                {
                  if ( akt_element.getDatum() > c_datum_sparstopp_sparrate_long )
                  {
                    akt_element.setKnzLoeschung( KNZ_ELEMENT_LOESCHEN );

                    akt_element.setElementId( "LOESCH_NACH_SPARSTOPP_" + akt_element.getElementId() );

                    wl( "calcLaufzeit: Loesche Elemnt 1 - " + akt_element.toStringZeile() );

                    c_rk_temp_index = c_rk_temp_index + ANZAHL_1;
                  }
                }
              }

              c_aktueller_index = c_aktueller_index + ANZAHL_1;
            }

            /* 
             * Loeschung der ueberfluessigen Elemente.
             * 
             * Die Loeschung sortiert den Vektor neu, so dass hier der Vektor nicht nochmal
             * sortiert werden muss.
             */
            kto_verlauf.removeMarkierteElemente();

            kto_verlauf.startSort();

            c_rk_anzahl_elemente = kto_verlauf.getAnzahl();

            if ( m_debug_knz_berechnungsziel_schritte )
            {
              wl( "calcLaufzeit: " );
              wl( "calcLaufzeit: Anzahl Elemente zum Loeschen markiert   " + c_rk_temp_index );
              wl( "calcLaufzeit: Anzahl Elemente nach Loeschen           " + c_rk_anzahl_elemente );
              wl( "calcLaufzeit: " );
            }
          }

          if ( c_rk_zaehler_iteration == 1 )
          {
            c_rk_zaehler_iteration = c_rk_zaehler_iteration + ANZAHL_1;

            c_rk_knz_weiterer_iterationslauf_notwendig = B_TRUE;
          }
          else
          {
            if ( e_spar_laufzeit_gesamt_monate < c_spar_mindest_sparzeit_rest_monate )
            {
              e_rechenkern_fehler_nummer = FEHLER_MINDEST_SPARZEIT_UNTERSCHRITTEN;
            }

            c_rk_knz_weiterer_iterationslauf_notwendig = B_FALSE;
          }
        }
        else if ( c_rk_berechnungsziel == BZ_VERTRAGS_SIMULATION )
        {
          wl( "" );
          wl( "BERECHNUNGSZIEL BZ_VERTRAGS_SIMULATION " );
          wl( "" );
          //
          c_rk_knz_weiterer_iterationslauf_notwendig = B_FALSE;
        }

        /* 
         * Abbruch wegen zu vieler Iterationen
         */
        if ( c_rk_zaehler_iteration > MAX_ITERATIONEN )
        {
          e_rechenkern_fehler_nummer = FEHLER_ZU_VIELE_ITERATIONEN;
        }

        /* 
         * Pruefung: Abbruch wegen Fehler?
         * Steht die Variable fuer die Fehlernummer noch auf "ohne Fehler" kann es weiter gehen.
         * Steht die Variable auf einem anderen Wert, wird der Wert ausgegeben und die Variable
         * fuer den Schleifenabbruch gesetzt.
         */
        if ( e_rechenkern_fehler_nummer != BERECHNUNG_FEHLER_FREI )
        {
          wl( "Abbruch der Berechnung wegen Fehler " + getFehlerString( e_rechenkern_fehler_nummer ) );

          c_rk_knz_weiterer_iterationslauf_notwendig = B_FALSE;
        }

        c_stichtag_betrag_zinsen_alt = c_stichtag_betrag_zinsen;

        c_millisekunden_ende = System.currentTimeMillis();

        if ( m_debug_knz_berechnungsziel_schritte )
        {
          wl( "" );
          wl( "ZEIT Ergebnisauswertung Dauer in Millisekunden             =>" + ( c_millisekunden_ende - c_millisekunden_start_1 ) + "<" );
          wl( "" );
        }
      }

      if ( c_knz_sparstopp_aus_vorgaben == SPAR_STOPP_ZUTEILUNG )
      {
        c_datum_sparstopp_sparrate_long = c_spar_datum_zuteilung_long;

        c_sparstopp_knz_erreicht = B_TRUE;
      }

      if ( c_rk_berechnungsziel != BZ_LAUFZEIT )
      {
        //  kto_verlauf.removeMarkierteElemente();
      }

      e_spar_zuteilung_kontostand = c_betrag_akt_kontostand;

      if ( pCalcBsvEingabe.getRkInputKnzDarlehensverzicht() )
      {
        e_spar_zuteilung_kontostand = c_bonus_betrag_akt_kontostand;
      }

      if ( m_debug_knz_ansp_berechnung )
      {
        wl( "" );
        wl( "ANSP: Ausstehend " + c_ansp_betrag_ausstehend + ", c_ansp_betrag_gutgeschrieben " + c_ansp_betrag_gutgeschrieben );
      }

      wl( "" );
      wl( "'################################################################################################################## " );
      wl( "'###### ERGEBNISSE ENDE-SPARPHASE                                                                         ######### " );
      wl( "'################################################################################################################## " );
      wl( "" );
      wl( "ERGEBNISSE_2: e_sparbeitrag_mgh                       =>" + e_sparbeitrag_mgh + "<" );
      wl( "ERGEBNISSE_2: e_sparbeitrag_bwz                       =>" + e_sparbeitrag_bwz + "<" );
      wl( "" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_bewertungszahl        =>" + c_stichtag_betrag_bewertungszahl + "<" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_gebeuhren_kfg         =>" + c_stichtag_betrag_gebeuhren_kfg + "<" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_kontostand            =>" + c_stichtag_betrag_kontostand + "<" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_saldensumme           =>" + c_stichtag_betrag_saldensumme + "<" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_soll_guthaben         =>" + c_stichtag_betrag_soll_guthaben + "<" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_sonderzahlung         =>" + c_stichtag_betrag_sonderzahlung + "<" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_sparbeitrag           =>" + c_stichtag_betrag_sparbeitrag + "<" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_vl_as                 =>" + c_stichtag_betrag_vl_as + "<" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_vl_ep                 =>" + c_stichtag_betrag_vl_ep + "<" );
      wl( "ERGEBNISSE_2: c_stichtag_betrag_zinsen                =>" + c_stichtag_betrag_zinsen + "<" );
      wl( "ERGEBNISSE_2: c_anzahl_bsv_sparraten_bis_spar_stopp   =>" + c_anzahl_bsv_sparraten_bis_spar_stopp + "<" );
      wl( "ERGEBNISSE_2: c_anzahl_stichtage_bis_zuteilung        =>" + c_anzahl_stichtage_bis_zum_massgeblichen_stichtag + "<" );
      wl( "" );
      wl( "ERGEBNISSE_2: c_betrag_akt_bewertungszahl             =>" + c_betrag_akt_bewertungszahl + "<" );
      wl( "ERGEBNISSE_2: c_betrag_akt_bonus_zinsen               =>" + c_betrag_akt_bonus_zinsen + "<" );
      wl( "ERGEBNISSE_2: c_betrag_akt_grund_zinsen               =>" + c_betrag_akt_grund_zinsen + "<" );
      wl( "ERGEBNISSE_2: c_betrag_akt_kontostand                 =>" + c_betrag_akt_kontostand + "<" );
      wl( "ERGEBNISSE_2: c_betrag_akt_bonus_kto                  =>" + c_betrag_akt_bonus_kto + "<" );
      wl( "ERGEBNISSE_2: c_betrag_akt_saldensumme                =>" + c_betrag_akt_saldensumme + "<" );
      wl( "ERGEBNISSE_2: c_betrag_bss_berechnet                  =>" + c_betrag_bss_berechnet + "<" );
      wl( "ERGEBNISSE_2: c_betrag_ges_bonus_zinsen               =>" + c_betrag_ges_bonus_zinsen + "<" );
      wl( "ERGEBNISSE_2: c_betrag_ges_bsv_sparbeitrag            =>" + c_betrag_ges_bsv_sparbeitrag + "<" );
      wl( "ERGEBNISSE_2: c_betrag_ges_guthaben_zinsen            =>" + c_betrag_ges_guthaben_zinsen + "<" );
      wl( "ERGEBNISSE_2: c_betrag_ges_kto_gebuehren_spar         =>" + c_betrag_ges_kto_gebuehren_spar + "<" );
      wl( "ERGEBNISSE_2: c_betrag_ges_sonderzahlung              =>" + c_betrag_ges_sonderzahlung + "<" );
      wl( "ERGEBNISSE_2: c_betrag_ges_vl_antragsteller           =>" + c_betrag_ges_vl_antragsteller + "<" );
      wl( "ERGEBNISSE_2: c_betrag_ges_vl_ehepartner              =>" + c_betrag_ges_vl_ehepartner + "<" );
      wl( "ERGEBNISSE_2: c_betrag_ges_zinsen_spar                =>" + c_betrag_ges_zinsen_spar + "<" );
      wl( "" );
      wl( "ERGEBNISSE_2: c_rk_zaehler_iteration                  =>" + c_rk_zaehler_iteration + "<" );
      wl( "" );
      wl( "'################################################################################################################## " );
      wl( "'################################################################################################################## " );
      wl( "" );
    }

    e_millisekunden_ende_sparphase_calc = System.currentTimeMillis();

    /* 
     * ##################################################################################################################
     * ##################################################################################################################
     * ##################################################################################################################
     * ##################################################################################################################
     */

    CalcKtoVerlaufElement akt_element_zinsbelastung = null;
    CalcKtoVerlaufElement c_bsd_element_letzter_ztb = null;
    CalcKtoVerlaufElement c_bsd_letzte_rate_element = null;
    double bsd_e_tariflicher_ztb = BETRAG_0;
    double bsd_e_anfangsdarlehen = BETRAG_0;
    double bsd_e_betrag_darlehen_mit_mehrzuteilung = BETRAG_0;
    double bsd_e_betrag_mehr_darlehen = BETRAG_0;
    double bsd_e_darlehens_gebuehr_betrag = BETRAG_0;
    String bsd_e_darlehens_gebuehr_bezeichnung = "";
    long bsd_e_darlehens_gebuehr_datum_belastung = DATUM_0;
    long bsd_e_datum_darlehensbeginn = DATUM_0;
    long bsd_e_datum_darlbeginn_monat = 0;
    double bsd_e_betrag_disagio = BETRAG_0;
    String bsd_e_disagio_bezeichnung = "";
    long bsd_e_disagio_datum_belastung = DATUM_0;
    double bsd_e_gesamt_eigene_einzahlung = BETRAG_0;
    double bsd_e_gesamt_kfg = BETRAG_0;
    double bsd_e_gesamt_sondertiglung = BETRAG_0;
    double bsd_e_gesamt_zinsen = BETRAG_0;
    double bsd_e_letzte_rate_betrag = BETRAG_0;
    long bsd_e_letzte_rate_datum = DATUM_0;
    double bsd_e_letzte_rate_zinsbelastung = BETRAG_0;
    double bsd_e_tariflicher_darlehensanspruch = BETRAG_0;
    double c_bsd_betrag_darlehensstand = BETRAG_0;
    long c_bsd_aktueller_index = INDEX_0;
    double c_bsd_akt_zins_belastung = BETRAG_0;
    double c_bsd_aktuelle_darlehenszinsen_bis_belastung = BETRAG_0;
    double c_bsd_anfangsdarlehen = BETRAG_0;
    long c_bsd_anzahl_monate_gesamt = ANZAHL_MONATE_0;
    long c_bsd_berechnungsbeginn_long = DATUM_0;
    long c_bsd_berechnungsende_long = DATUM_0;
    boolean c_bsd_bsd_knz_darlehen_berechnen = B_FALSE;
    boolean c_bsd_knz_zinsberechnung_durchfuehren = B_FALSE;
    long c_bsd_datum_letztes_kto_element = DATUM_0;
    long c_bsd_index_zinsbelastung = INDEX_0;
    double c_bsd_nztb_anfangsdarlehen = BETRAG_0;
    double c_bsd_nztb_bausparsumme = BETRAG_0;
    double c_bsd_nztb_guthaben_bei_zuteilung = BETRAG_0;
    String c_bsd_nztb_tarif_name = "";
    double c_bsd_nztb_tarif_zins_darlehen = BETRAG_0;
    double c_bsd_nztb_tarif_zins_sparen = BETRAG_0;
    double c_bsd_nztb_zinsen_bei_zuteilung = BETRAG_0;
    double c_bsd_input_proz_mehrzuteilung = BETRAG_0;
    long c_bsd_start_index = INDEX_0;
    long c_bsd_zeitraum_darlehensbestand_ab = DATUM_0;
    long c_bsd_zeitraum_darlehensbestand_anzahl_tage = ANZAHL_0;
    double c_bsd_zeitraum_darlehensbestand_betrag = BETRAG_0;
    double c_bsd_zeitraum_darlehensbestand_zinsen = BETRAG_0;
    double c_bsd_zins_und_tilgungsbeitrag = BETRAG_0;
    double ztb_promill_bss = BETRAG_0;
    double e_kto_gebuehr_tilgen_anteilig_betrag = BETRAG_0;
    long e_kto_gebuehr_tilgen_anteilig_anz_monate = ANZAHL_0;

    c_millisekunden_start_1 = System.currentTimeMillis();

    /* 
     * Fuer Debug-Zwecke kann die Berechnung der Darlehensphase ausgeschaltet werden.
     * (... es steht weniger in der Log-Datei)
     */
    if ( m_debug_knz_berechne_bsd )
    {
      c_bsd_letzte_rate_element = null;

      bsd_e_tariflicher_darlehensanspruch = BETRAG_0;
      bsd_e_letzte_rate_zinsbelastung = BETRAG_0;
      bsd_e_letzte_rate_datum = DATUM_0;
      bsd_e_letzte_rate_betrag = BETRAG_0;
      bsd_e_gesamt_zinsen = BETRAG_0;
      bsd_e_gesamt_sondertiglung = BETRAG_0;
      bsd_e_gesamt_kfg = BETRAG_0;
      bsd_e_gesamt_eigene_einzahlung = BETRAG_0;
      bsd_e_disagio_datum_belastung = DATUM_0;
      bsd_e_disagio_bezeichnung = "Disagio";
      bsd_e_betrag_disagio = BETRAG_0;
      bsd_e_datum_darlehensbeginn = DATUM_0;
      bsd_e_darlehens_gebuehr_datum_belastung = DATUM_0;
      bsd_e_darlehens_gebuehr_bezeichnung = "Darlehensgebuehr";
      bsd_e_darlehens_gebuehr_betrag = BETRAG_0;
      bsd_e_betrag_mehr_darlehen = BETRAG_0;
      bsd_e_betrag_darlehen_mit_mehrzuteilung = BETRAG_0;

      if ( e_rechenkern_fehler_nummer == BERECHNUNG_FEHLER_FREI )
      {
        /* 
         * Darlehensphase
         * 
         * Die Darlehensphase ist nicht vorhanden, wenn
         *  -die Zuteilungsvoraussetzungen nicht erreicht werden (Vertragssimulation)
         *  -auf das Darlehen verzichtet wird.
         * 
         * Zins- und Tilgungsbeitrag
         * 
         * Tariflicher ZTB
         * Entsprechend der ZTB-Staffel wird der (tarifliche) Tilgungsbeitrag berechnet.
         * Der tarifliche ZTB kann auch gleich dem minimal moeglichen ZTB sein.
         * 
         * Minimal moeglicher ZTB
         * Berechnung des ZTBs aufgrundlage des minimal moeglichen ZTBs
         * (Berechnung auf Grundlage der erbrachten Sparleistung zur Kassenleistung)
         * 
         * Vorgabe ZTB
         * Ergebniswert kann auch ein vorgegebener ZTB sein.
         * Ergebnis kann auch sein, dass dieser nicht erreicht wird.
         * 
         * 
         * Unterscheidung zwischen "Sparphase mit Darlehen" und "nur Darlehen" fehlt
         */

        /* 
         * DARLEHENSPHASE
         * 
         * Nach einer erfolgreichen Berechnung der Sparphase werden die von dem Tarif
         * moeglichen technischen Daten der Darlehensphase berechnet.
         * 
         * Das sind die Werte, welche unabhaengig von einem Darlehensverzicht berechnet werden koennen.
         * 
         * c_datum_zuteilung_long = c_spar_datum_zuteilung_long * CalcFkDatum.getLongAusTTPMMPJJJJ( e_spar_datum_zuteilung_string )
         * 
         * Datum Darlehensbeginn
         * Das Bauspardarlehen wird einen Monat nach dem Zuteilungstermin ausgezahlt.
         * Auf das Datum der Zuteilung wird 1 Monat draufgerechnet und der Tag auf den
         * Monatsersten gestellt.
         */
        bsd_e_datum_darlehensbeginn = FkDatumLong.longDateAdd( c_spar_datum_zuteilung_long, ANZAHL_JAHRE_0, ANZAHL_MONATE_1, ANZAHL_TAGE_0, KNZ_TAG_MONATSANFANG );

        bsd_e_datum_darlbeginn_monat = FkDatumLong.getMonatAusLong( bsd_e_datum_darlehensbeginn );

        /* 
         * Pruefung: Zuteilung erreicht?
         * Wird die Zuteilung nicht erreicht (Vertragsimulation), wird keine Darlehensphase berechnet.
         * Es wird nur eine Log-Ausgabe gemacht.
         * 
         * Wird die Zuteilung erreicht, wird die Darlehensphase berechnet.
         */
        if ( c_tarif_ztv_knz_erreicht == B_FALSE )
        {
          wl( "Keine Darlehensphase, da die Zuteilung nicht erreicht wurde." );
        }
        else
        {
          if ( ( c_bsd_kto_gebuehr_knz_beruecksichtigen ) && ( tarif_kto_gebuehr_tilgen_knz_ein ) )
          {
            e_bsd_kto_gebuehr_laufend_betrag = tarif_kto_gebuehr_tilgen_pro_jahr; //berechnung_betrag_kto_fuehrungsgebuehr;

            e_bsd_kto_gebuehr_laufend_bezeichnung = "BSD Kto-Geb. laufend";

            e_kto_gebuehr_tilgen_anteilig_anz_monate = ( 13 - bsd_e_datum_darlbeginn_monat );

            e_kto_gebuehr_tilgen_anteilig_betrag = round2DP( ( e_bsd_kto_gebuehr_laufend_betrag / ANZAHL_MONATE_12 ) * e_spar_kto_gebuehr_anteilig_anz_monate );

            // e_kto_gebuehr_tilgen_anteilig_bezeichnung = "Anteilige Kontofuehrungsgebuehr fuer " + e_spar_kto_gebuehr_anteilig_anz_monate + " Monate"

            /* 
             * Datum Belastung KFG
             * Die Belastung der BSD-KFG erfolgt immer zum 1 Januar.
             * Faellt das Datum des Darlehensbginns auf den Monat Januar, bleibt das Jahr unveraendert.
             * 
             * Faellt das Datum des Darlehensbeginns auf andere Monate, wurde die KFG Spar schon voll
             * gezogen. Das Balastungsdatum erfolgt dann zum ersten Januar im Folgemonat.
             */
            if ( FkDatumLong.getMonatAusLong( bsd_e_datum_darlehensbeginn ) == MONAT_JANUAR )
            {
              e_bsd_kto_gebuehr_laufend_datum_belastung = FkDatumLong.getLong( FkDatumLong.getJahrAusLong( bsd_e_datum_darlehensbeginn ), MONAT_JANUAR, TAG_1 );
            }
            else
            {
              e_bsd_kto_gebuehr_laufend_datum_belastung = FkDatumLong.getLong( FkDatumLong.getJahrAusLong( bsd_e_datum_darlehensbeginn ) + ANZAHL_JAHRE_1, MONAT_JANUAR, TAG_1 );
            }
          }
          else
          {
            e_bsd_kto_gebuehr_laufend_betrag = BETRAG_0;

            e_bsd_kto_gebuehr_laufend_bezeichnung = "Kontofuehrungsgebuehr Darlehen";

            e_bsd_kto_gebuehr_laufend_datum_belastung = DATUM_0;
          }

          wl( "Zuteilung erreicht. Darlehensphase kann berechnet werden." );

          /* 
           * Tariflicher Darlehensanspruch
           */
          if ( tarif_darlehensanspruch_art == DARL_ANSPRUCH_FEST )
          {
            /* 
             * Fester Darlehensanspruch
             * Bei einem festen Darlehensanspruch ist der Darlehensanspruch ein fester Prozentsatz von der Bss.
             * 
             * Der Betrag wird im Rechenkern nur berechnet, sofern der Prozentstz groesser als 0 Prozent ist.
             * Wrid nicht vorkommen, ist hier aber zur Sicherheit eingebaut.
             */
            if ( tarif_darlehensanspruch_proz > PROZENT_0 )
            {
              bsd_e_tariflicher_darlehensanspruch = round2DP( ( c_betrag_bss_berechnet * tarif_darlehensanspruch_proz ) * MULTIPLIKATOR_DURCH_100 );
            }
          }
          else
          {
            /* 
             * Variabler Darlehensanspruch
             * Bei einem variablen Darlehensanspruch ist der Darlehensbetrag die Differenz zwischen
             * Bausparsumme und dem Guthaben bei Zuteilung (Kontostand)
             * 
             * Sollte mehr als die Bausparsumme bespart worden sein, ist der Darlehensbetrag kleiner
             * als 0. Der Darlehensbetrag wird in diesem Fall auf 0 gestellt.
             */
            bsd_e_tariflicher_darlehensanspruch = round2DP( c_betrag_bss_berechnet - e_spar_zuteilung_kontostand );

            if ( bsd_e_tariflicher_darlehensanspruch < BETRAG_0 )
            {
              bsd_e_tariflicher_darlehensanspruch = BETRAG_0;
            }
          }

          /* 
           * Konkrete Darlehenshoehe
           * Liegt ein Darlehensverzicht vor, wird der Darlehenswert fuer die Berechnung auf 0 gesetzt.
           * 
           * Soll die Berechnung mit Darlehen erfolgen, wird der Darlehenswert fuer die Berechnung auf
           * den tariflichen Darlehenanspruch gesetzt.
           */
          if ( pCalcBsvEingabe.getRkInputKnzDarlehensverzicht() )
          {
            c_bsd_anfangsdarlehen = BETRAG_0;
          }
          else
          {
            c_bsd_anfangsdarlehen = bsd_e_tariflicher_darlehensanspruch;
          }

          /* 
           * Tarifliche Darlehensgebuehr
           * Die Darlehensgebuehr wird berechnet wenn der Prozentsatz fuer die Darlehensgebuehr
           * groesser als 0 und auch der tarifliche Darlehensanspruch groesser als 0 ist.
           * 
           * Die Belastung der Darlehensgebuehr erfolgt bei der Darlehensauszahlung.
           */
          if ( ( tarif_bsd_proz_darl_gebuehr > PROZENT_0 ) && ( bsd_e_tariflicher_darlehensanspruch > BETRAG_0 ) )
          {
            bsd_e_darlehens_gebuehr_betrag = round2DP( ( bsd_e_tariflicher_darlehensanspruch * tarif_bsd_proz_darl_gebuehr ) * MULTIPLIKATOR_DURCH_100 );

            bsd_e_darlehens_gebuehr_datum_belastung = bsd_e_datum_darlehensbeginn;

            bsd_e_darlehens_gebuehr_bezeichnung = "Darlehensgebuehr ( " + get3nk( tarif_bsd_proz_darl_gebuehr ) + " % von " + get2nk( bsd_e_tariflicher_darlehensanspruch ) + " )";
          }

          /*
           * Korrektur Disagio-Prozentwert
           * Ist der Disagio-Prozentwert kleiner 0, wird dieser auf 0 gestellt.
           * Bei kleiner 0, waere es ein Aufgeld (=Agio).
           */
          if ( tarif_bsd_proz_disagio < PROZENT_0 )
          {
            tarif_bsd_proz_disagio = PROZENT_0;
          }

          /* 
           * Disagiobetrag
           * Ein Disagiobetrag wird berechnet, wenn der Disagio-Prozentsatz und der Darlehensanspruch
           * groesser als 0 sind.
           * 
           * Durch den Abzug von einem Disagio-Betrag reduziert sich der nominelle Zinssatz.
           */
          if ( ( tarif_bsd_proz_disagio > PROZENT_0 ) && ( bsd_e_tariflicher_darlehensanspruch > BETRAG_0 ) )
          {
            bsd_e_betrag_disagio = round2DP( ( bsd_e_tariflicher_darlehensanspruch * tarif_bsd_proz_disagio ) * MULTIPLIKATOR_DURCH_100 );

            bsd_e_disagio_datum_belastung = bsd_e_datum_darlehensbeginn;

            bsd_e_disagio_bezeichnung = "Disagio ( " + get3nk( tarif_bsd_proz_disagio ) + " % von " + get2nk( bsd_e_tariflicher_darlehensanspruch ) + " )";
          }

          /* 
           * Mehrzuteilung
           * 
           * Uebernahme der Vorgabe aus der Eingabe. 
           * Der Prozentwert wird auf Einhaltung der Ober- und Untergrenze geprueft. 
           * Wird eine Grenze unter- oder ueberschritten, wird der Wert auf den 
           * entsprechenden Grenzwert gestellt.
           */
          c_bsd_input_proz_mehrzuteilung = pCalcBsvEingabe.getRkInputProzMehrzuteilung();

          if ( c_bsd_input_proz_mehrzuteilung <= BETRAG_0 )
          {
            c_bsd_input_proz_mehrzuteilung = BETRAG_100;
          }
          else if ( c_bsd_input_proz_mehrzuteilung < tarif_mehrzuteilung_untergrenze_prozent )
          {
            c_bsd_input_proz_mehrzuteilung = tarif_mehrzuteilung_untergrenze_prozent;
          }
          else if ( c_bsd_input_proz_mehrzuteilung > tarif_mehrzuteilung_obergrenze_prozent )
          {
            c_bsd_input_proz_mehrzuteilung = tarif_mehrzuteilung_obergrenze_prozent;
          }

          /* 
           * Die Mehrzuteilung wird berechnet, wenn der Prozentsatz der Mehrzuteilung von 100 abweicht.
           * Es kann auch eine Minderzuteilung sein, wenn der Prozentsatz kleiner 100 ist.
           */
          if ( ( c_bsd_input_proz_mehrzuteilung != BETRAG_100 ) && ( bsd_e_tariflicher_darlehensanspruch > BETRAG_0 ) )
          {
            /* 
             * Betrag Mehrdarlehen
             * Es wird der zusaetzliche Betrag des mehr an Darlehens berechnet.
             * Im Falle einer Minderzuteilung eben der Betrag der abgezogen wird.
             */
            bsd_e_betrag_mehr_darlehen = round2DP( bsd_e_tariflicher_darlehensanspruch * ( ( c_bsd_input_proz_mehrzuteilung - BETRAG_100 ) * MULTIPLIKATOR_DURCH_100 ) );

            /* 
             * Betrag BSD mit Mehrzuteilungsbetrag
             */
            bsd_e_betrag_darlehen_mit_mehrzuteilung = bsd_e_tariflicher_darlehensanspruch + bsd_e_betrag_mehr_darlehen;

            wl( "Mehzuteilung " + c_bsd_input_proz_mehrzuteilung + " % von " + bsd_e_tariflicher_darlehensanspruch + " = " + bsd_e_betrag_mehr_darlehen );

            /* 
             * Neue BSD-Anfangsdarlehenshoehe setzen.
             * Die zu tilgende Anfangsdarlehenshoehe wird entsprechend mit dem
             * Mehrzuteilungsdarlehensbetrag angepasst.
             */
            c_bsd_anfangsdarlehen = bsd_e_betrag_darlehen_mit_mehrzuteilung;
          }

          /* 
           * Ergebnis Anfangsdarlehen
           * Das berechnete Anfagsdarlehen wird in die Ergebnisvariable uebertragen
           */
          bsd_e_anfangsdarlehen = c_bsd_anfangsdarlehen;

          /* 
           * Kennzeichen "Darlehen berechnen" setzen
           * Die Darlehensphase wird berechnet, wenn die Hoehe des Anfangsdarlehens groesser 0 ist.
           */
          c_bsd_bsd_knz_darlehen_berechnen = ( bsd_e_anfangsdarlehen > BETRAG_0 );

          /* 
           * Zins und Tilgungsbeitrag
           * Der Zins und Tilgungsbeitrag wird nur berechnet, sofern das Anfangsdarlehen
           * groesser als 0 ist.
           * 
           * Zuerst wird der tarifliche Zins und Tilgungsbeitrag berechnet.
           * Anschliessend wird geprueft, ob der tarifliche Wert genommen werden soll,
           * oder ob ein Wert vorgegeben worden ist.
           * 
           * Das Bauspardarlehen ist eine Art Annuitaetendarlehen.
           * Die Annuitaet ist ein jaehrlich zu entrichtender Beitrag.
           * Bei einem Bauspardarlehen wird dieser Betrag auf einen monatlichen Betrag umgerechnet.
           * Dieser monatliche Betrag ist der Zins- und Tilgungsbeitrag.
           * 
           * ZTB nach Bewertungszahlstaffel
           * Bei Verwendung einer Bewertungszahlstaffel berechnet sich die Hoehe des ZTBs
           * auf Grundlage der erreichten Bewertungszahl am Stichtag fuer die Zuteilung.
           * 
           * ZTB nach Sparerleistung zu Kassenleistung
           * Der NZTB errechnet sich auf Grundlage der Sparerleistung zur Kassenleistung.
           * Wieviel hat der Sparer am Guthaben eingezahlt zu dem Zinsbetrag der BSK.
           * 
           * Bei Zuteilung wird eine niedrigere Monatsrate angeboten, wenn das Verhaeltnis
           * der erbrachten Sparerleistung zur Kassenleistung mehr als 0,4 ausmacht.
           */
          if ( bsd_e_anfangsdarlehen > BETRAG_0 )
          {
            if ( pCalcTarifEingabe.getKnzNztbTarif() )
            {
              c_bsd_nztb_tarif_name = "xxx";

              c_bsd_nztb_bausparsumme = c_betrag_bss_berechnet;
              c_bsd_nztb_anfangsdarlehen = bsd_e_anfangsdarlehen;
              c_bsd_nztb_guthaben_bei_zuteilung = c_stichtag_betrag_kontostand;
              c_bsd_nztb_zinsen_bei_zuteilung = c_stichtag_betrag_zinsen;
              c_bsd_nztb_tarif_zins_sparen = tarif_proz_guthabenzins;
              c_bsd_nztb_tarif_zins_darlehen = tarif_bsd_zinssatz;

              //bsd_e_tariflicher_ztb = calcNiedrigstMoeglichenZTB( c_bsd_nztb_tarif_name, c_bsd_nztb_bausparsumme, c_bsd_nztb_anfangsdarlehen, c_bsd_nztb_guthaben_bei_zuteilung, c_bsd_nztb_zinsen_bei_zuteilung, c_bsd_nztb_tarif_zins_sparen, c_bsd_nztb_tarif_zins_darlehen );

              ztb_promill_bss = ( bsd_e_tariflicher_ztb * BETRAG_1000 ) / c_betrag_bss_berechnet;

              if ( bsd_e_tariflicher_ztb == BETRAG_0 )
              {
                e_rechenkern_fehler_nummer = FEHLER_ERMITTLUNG_ZINS_UND_TILIGUNGSBEITRAG;
              }
            }
            else
            {
              /* 
               * ZTB-Beitragsstaffel einbauen
               */
              c_bsd_zins_und_tilgungsbeitrag = BETRAG_0;

              int bsd_index_ztb = INDEX_0;
              int ztb_anzahl_bwz_staffel = pCalcTarifEingabe.getAnzahlBwzStaffel();

              ztb_promill_bss = BETRAG_0;

              while ( ( bsd_index_ztb < ztb_anzahl_bwz_staffel ) && ( ztb_promill_bss == BETRAG_0 ) )
              {
                if ( ( c_stichtag_betrag_bewertungszahl >= pCalcTarifEingabe.getBwzStaffel( bsd_index_ztb ).getBwzVon() ) && ( c_stichtag_betrag_bewertungszahl <= pCalcTarifEingabe.getBwzStaffel( bsd_index_ztb ).getBwzBis() ) )
                {
                  ztb_promill_bss = pCalcTarifEingabe.getBwzStaffel( bsd_index_ztb ).getZtb();
                }
                else
                {
                  wl( "BWZ_ST: " + bsd_index_ztb + " c_stichtag_betrag_bewertungszahl " + c_stichtag_betrag_bewertungszahl + "  = von " + pCalcTarifEingabe.getBwzStaffel( bsd_index_ztb ).getBwzVon() + " bis " + pCalcTarifEingabe.getBwzStaffel( bsd_index_ztb ).getBwzBis() + " ) " );
                }

                bsd_index_ztb = bsd_index_ztb + 1;
              }

              if ( ztb_promill_bss == BETRAG_0 )
              {
                /* 
                 * Fehler: Keine uebereinstimmung in der BWZ-Staffel gefunden
                 */
                e_rechenkern_fehler_nummer = FEHLER_BWZ_STAFFEL_KEIN_WERT_GEFUNDEN;
              }
              else
              {
                bsd_e_tariflicher_ztb = round3DP( ( c_betrag_bss_berechnet * ( ztb_promill_bss * 0.001 ) ) );
              }
            }

            /* 
             * Zins- und Tilgungsbeitrag
             * Ermittlung des ZTBs fuer die Berechnung des Bauspardarlehens.
             * 
             * Vorgabe TARIF
             *   - den tariflichen ZTB nehmen
             * 
             * Vorgaben PROMILLE
             *  - die Wertangabe des Feldes "rk_input_vorgabe_ztb_betrag" als Promillewert der BSS nehmen
             *  - Pruefungen gegen Grenzwerte
             * 
             * Vorgabe EURO
             *  - die Wertangabe des Feldes "yxz_was_auch_immer" als Eurowert nehmen
             *  - Pruefungen gegen Grenzwerte
             */
            if ( pCalcBsvEingabe.istZtbAuswahlTariflich() )
            {
              c_bsd_zins_und_tilgungsbeitrag = bsd_e_tariflicher_ztb;
            }
            else
            {
              if ( ( pCalcBsvEingabe.istZtbAuswahlTariflich() == B_FALSE ) && ( pCalcBsvEingabe.getRkInputVorgabeZtbOptimierung() == B_FALSE ) )
              {
                if ( pCalcBsvEingabe.istZtbAuswahlVorgabePromille() )// ( pCalcBsvEingabe.rk_input_vorgabe_ztb_art == "PROMILLE" )
                {
                  ztb_promill_bss = pCalcBsvEingabe.getRkInputVorgabeZtbBetrag();

                  if ( ztb_promill_bss < ZTB_VORGABE_MINDEST_PROMILL_BETRAG )
                  {
                    e_rechenkern_fehler_nummer = FEHLER_ZTB_VORGABE_PROMILLE_WERT_UNGUELTIG;
                  }
                  else
                  {
                    c_bsd_zins_und_tilgungsbeitrag = round3DP( c_betrag_bss_berechnet * ( ztb_promill_bss * 0.001 ) );
                  }
                }
                else if ( pCalcBsvEingabe.istZtbAuswahlVorgabeBetrag() )
                {
                  c_bsd_zins_und_tilgungsbeitrag = pCalcBsvEingabe.getRkInputVorgabeZtbBetrag();

                  ztb_promill_bss = round3DP( c_bsd_zins_und_tilgungsbeitrag * BETRAG_1000 ) / c_betrag_bss_berechnet;

                  if ( c_bsd_zins_und_tilgungsbeitrag <= BETRAG_0 )
                  {
                    e_rechenkern_fehler_nummer = FEHLER_ZTB_VORGABE_BETRAGSANGABE_UNGUELTIG;
                  }
                  else if ( ztb_promill_bss <= ZTB_VORGABE_MINDEST_PROMILL_BETRAG )
                  {
                    e_rechenkern_fehler_nummer = FEHLER_ZTB_VORGABE_BETRAGSANGABE_PROMILLE_W;
                  }
                  else if ( ztb_promill_bss >= 40.0 )
                  {
                    e_rechenkern_fehler_nummer = FEHLER_ZTB_VORGABE_BETRAGSANGABE_PROMILLE_W;
                  }
                }
              }
            }
          }
          else
          {
            wl( "Anfangsdarlehen ist 0, keine Darlehensphase berechnen" );
          }
        }
      }

      if ( ( c_bsd_bsd_knz_darlehen_berechnen ) && ( ( c_bsd_zins_und_tilgungsbeitrag == BETRAG_0 ) && ( e_rechenkern_fehler_nummer == BERECHNUNG_FEHLER_FREI ) ) )
      {
        wl( "Fehler c_bsd_zins_und_tilgungsbeitrag ist nicht gesetzt. " );

        e_rechenkern_fehler_nummer = FEHLER_ERMITTLUNG_ZINS_UND_TILIGUNGSBEITRAG;
      }

      if ( ( c_bsd_bsd_knz_darlehen_berechnen ) && ( e_rechenkern_fehler_nummer == BERECHNUNG_FEHLER_FREI ) )
      {
        /* 
         * ##############################################################################################
         * #### ABSCHNITT BERECHNUNG BSD-DATUM-BIS (GESCHAETZTE LAUFZEIT FUER VERLAUFSERSTELLUNG)    ####
         * ##############################################################################################
         * 
         * Kalkulatorisches geschaetztes BSD-Ende
         * 
         * Bei der Bauspardarlehensberechnung ist die Laufzeit das Berechnungsziel.
         * 
         * Es wird eine Datumsobergrenze berechnet. Bis zu diesem Datum werden die BSD-Verlaufselemente erstellt.
         * 
         * Diese Datumsgrenze (Dauer) muss ausreichend sein, damit in dieser Zeit das Darlehen getilgt werden kann.
         * 
         * Es wird eine grobe Laufzeit in Monaten berechnet:
         * 
         *  Anzahl Monate BSD Laufzeit = Darlehenshoehe / ( 75% des ZTBs )
         * 
         * Fuer die Berechnung kann nicht der gesamte Betrag des ZTBs genommen werden, da
         * die im ZTB enthaltenen Zinsen eine Verlaengerung der Darlehensdauer darstellen.
         * Diese Verlaengerung wird durch den Prozentabzug vom ZTB mehr oder weniger beruecksichtigt.
         * 
         * https://de.wikipedia.org/wiki/Annuit%C3%A4tendarlehen
         */
        c_bsd_anzahl_monate_gesamt = (long) ( c_bsd_anfangsdarlehen / ( c_bsd_zins_und_tilgungsbeitrag * CALC_LAUFZEIT_BSD_FAKTOR_ZTB_ANRECHNUNG ) );

        /* 
         * Die geschaetzt berechneten Monate werden zum Darlehensbeginndatum hinzugezaehlt.
         * Das so berechnete Darlehensende, sollte in der Dauer ausreichend sein, um das
         * Darlehen zu tilgen.
         * 
         * Mit dem hier berechneten Datum werden die Verlaufselemente erstellt.
         * Reicht die Dauer nicht aus umd das Darlehen zu tilgen, laeuft der
         * Algorithmus auf einen Fehler. 
         */
        c_bsd_berechnungsbeginn_long = bsd_e_datum_darlehensbeginn;

        c_bsd_berechnungsende_long = FkDatumLong.longDateAdd( bsd_e_datum_darlehensbeginn, ANZAHL_JAHRE_0, c_bsd_anzahl_monate_gesamt, ANZAHL_TAGE_0, KNZ_TAG_MONATSENDE );

        /* 
         * ##############################################################################################
         * #### ABSCHNITT ERSTELLUNG KONTOVERLAUF BAUSPARDARLEHEN                                    ####
         * ##############################################################################################
         * 
         * Es werden die Bauspardarlehens-Verlaufselemente dem Kontoverlauf hinzugefuegt.
         * 
         * -KTO_ART_DARLEHEN_PHASE_BEGINN
         * -KTO_ART_DARLEHEN_KTO_GEBUEHR_LAUFEND
         * -KTO_ART_ZTB_EINZAHLUNG
         * -KTO_ART_DARLEHENS_GEBUEHR
         * -KTO_ART_ZTB_ZINSBELASTUNG
         * -KTO_ART_SONDERTILGUNG
         */
        c_millisekunden_start_1 = System.currentTimeMillis();

        zd_start_datum = c_bsd_berechnungsbeginn_long;
        zd_end_datum = c_bsd_berechnungsbeginn_long;
        zd_kto_element_art = KTO_ART_DARLEHEN_PHASE_BEGINN;
        zd_kto_element_id = "ID_BSD_START";
        zd_kto_element_bezeichnung = "BSD-Berechnungsbeginn";
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_betrag_auszahlung = BETRAG_0;
        zd_kto_element_betrag_gebuehr = BETRAG_0;

        calcElemente( kto_verlauf, c_bsd_berechnungsbeginn_long, c_bsd_berechnungsbeginn_long, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

        if ( bsd_e_darlehens_gebuehr_datum_belastung > DATUM_0 )
        {
          zd_start_datum = bsd_e_darlehens_gebuehr_datum_belastung;
          zd_end_datum = bsd_e_darlehens_gebuehr_datum_belastung;
          zd_kto_element_art = KTO_ART_DARLEHENS_GEBUEHR;
          zd_kto_element_id = "ID_BSD_DARLGEB";
          zd_kto_element_bezeichnung = bsd_e_darlehens_gebuehr_bezeichnung;
          zd_kto_element_betrag_einzahlung = BETRAG_0;
          zd_kto_element_betrag_auszahlung = BETRAG_0;
          zd_kto_element_betrag_gebuehr = bsd_e_darlehens_gebuehr_betrag;

          calcElemente( kto_verlauf, bsd_e_darlehens_gebuehr_datum_belastung, bsd_e_darlehens_gebuehr_datum_belastung, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );
        }

        if ( bsd_e_disagio_datum_belastung > DATUM_0 )
        {
          zd_start_datum = bsd_e_disagio_datum_belastung;
          zd_end_datum = bsd_e_disagio_datum_belastung;
          zd_kto_element_art = KTO_ART_DARLEHEN_DISAGIO;
          zd_kto_element_id = "ID_BSD_DISAGIO";
          zd_kto_element_bezeichnung = bsd_e_disagio_bezeichnung;
          zd_kto_element_betrag_einzahlung = BETRAG_0;
          zd_kto_element_betrag_auszahlung = BETRAG_0;
          zd_kto_element_betrag_gebuehr = BETRAG_0;

          calcElemente( kto_verlauf, bsd_e_disagio_datum_belastung, bsd_e_disagio_datum_belastung, zd_start_datum, zd_end_datum, ANZAHL_MONATE_0, ANZAHL_JAHRE_0, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_1, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

          zd_kto_element_betrag_auszahlung = BETRAG_0;
        }

        /* 
         * BSD-Kontofuehrungsgebuehr
         * Soll die Kontofuehrungsgebuehr beruecksichtigt werden, werden die
         * Verlaufselemente erstellt.
         */
        if ( e_bsd_kto_gebuehr_laufend_betrag > BETRAG_0 )
        {
          zd_start_datum = e_bsd_kto_gebuehr_laufend_datum_belastung;
          zd_end_datum = c_bsd_berechnungsende_long;
          zd_kto_element_art = KTO_ART_DARLEHEN_KTO_GEBUEHR_LAUFEND;
          zd_kto_element_id = "ID_BSD_KFG";
          zd_kto_element_bezeichnung = e_bsd_kto_gebuehr_laufend_bezeichnung;
          zd_kto_element_betrag_einzahlung = BETRAG_0;
          zd_kto_element_betrag_auszahlung = BETRAG_0;
          zd_kto_element_rhythmus = m_rk_rhythmus_jaehrlich;
          zd_kto_element_betrag_gebuehr = e_bsd_kto_gebuehr_laufend_betrag;

          calcElementeRhytmus( kto_verlauf, c_bsd_berechnungsbeginn_long, c_bsd_berechnungsende_long, zd_start_datum, zd_end_datum, zd_kto_element_rhythmus, KNZ_TAG_UNVERAENDERT, ANZAHL_ELEMENTE_UNBEGRENZT, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );
        }

        zd_start_datum = c_bsd_berechnungsbeginn_long;
        zd_end_datum = c_bsd_berechnungsende_long;
        zd_kto_element_art = KTO_ART_ZTB_EINZAHLUNG;
        zd_kto_element_id = "ID_ZTB_EINZAHLUNG";
        zd_kto_element_bezeichnung = "Zins- und Tilgungsbeitrag";
        zd_kto_element_betrag_einzahlung = c_bsd_zins_und_tilgungsbeitrag;
        zd_kto_element_betrag_auszahlung = BETRAG_0;
        zd_kto_element_betrag_gebuehr = BETRAG_0;
        zd_kto_element_rhythmus = m_rk_rhythmus_monatlich;

        calcElementeRhytmus( kto_verlauf, c_bsd_berechnungsbeginn_long, c_bsd_berechnungsende_long, zd_start_datum, zd_end_datum, zd_kto_element_rhythmus, KNZ_TAG_MONATSENDE, 0, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

        zd_start_datum = c_bsd_berechnungsbeginn_long;
        zd_end_datum = c_bsd_berechnungsende_long;
        zd_kto_element_art = KTO_ART_ZTB_ZINSBELASTUNG;
        zd_kto_element_id = "ID_ZTB_ZINS_BELASTUNG";
        zd_kto_element_bezeichnung = "Zinsbelastung";
        zd_kto_element_betrag_einzahlung = BETRAG_0;
        zd_kto_element_betrag_auszahlung = BETRAG_0;
        zd_kto_element_betrag_gebuehr = BETRAG_0;
        zd_kto_element_rhythmus = m_rk_rhythmus_monatlich;
        zd_kto_element_betrag_auszahlung = BETRAG_0;

        calcElementeRhytmus( kto_verlauf, c_bsd_berechnungsbeginn_long, c_bsd_berechnungsende_long, zd_start_datum, zd_end_datum, zd_kto_element_rhythmus, KNZ_TAG_MONATSENDE, 0, zd_kto_element_art, zd_kto_element_id, zd_kto_element_bezeichnung, zd_kto_element_betrag_einzahlung, zd_kto_element_betrag_auszahlung, zd_kto_element_betrag_gebuehr );

        int bsd_index_zahlung = INDEX_0;
        int bsd_max_sonder_tilgungen = 0;

        bsd_max_sonder_tilgungen = pCalcBsvEingabe.getAnzahlSondertilgungen();

        while ( ( bsd_index_zahlung < bsd_max_sonder_tilgungen ) )
        {
          calcElementeZahlung( kto_verlauf, KTO_ART_SONDERTILGUNG, pCalcBsvEingabe.getSondertilgung( bsd_index_zahlung ), c_bsd_berechnungsbeginn_long, c_bsd_berechnungsende_long );

          bsd_index_zahlung = bsd_index_zahlung + 1;
        }

        /* 
         * Chronologische Reihenfolge durch Sortierung herstellen.
         */
        kto_verlauf.startSort();

        /* 
         * ##############################################################################################
         * #### ABSCHNITT BERECHNUNG DES DARLEHENSVERLAUFES                                          ####
         * ##############################################################################################
         * 
         * Ermittlung Startindex BSD-Phase
         * Der Kontoverlauf wurde sortiert und es wird der Startindex fuer die Darlehensphase ermittelt.
         * Wird der Startindex nicht gefunden, wird die Berechnung mit dem Fehler 
         * 
         *              FEHLER_START_ELEMENT_BSD_NICHT_GEFUNDEN
         * 
         * beendet.
         */
        c_bsd_start_index = kto_verlauf.getIndexByArt( KTO_ART_DARLEHEN_PHASE_BEGINN );

        if ( c_bsd_start_index == INDEX_NICHT_VORHANDEN )
        {
          wl( "Grober Fehler, der nicht passieren darf. Das Bsd-Startelement muss gefunden werden." );

          e_rechenkern_fehler_nummer = FEHLER_START_ELEMENT_BSD_NICHT_GEFUNDEN;
        }
        else
        {
          c_millisekunden_start_1 = System.currentTimeMillis();

          /* 
           * Startwert aeussere Schleife
           * Der Index der aeusseren Schleife wird in der Variablen "c_bsd_index_zinsbelastung" gespeichert.
           * Dieser Index startet ab dem ermittelten Index fuer das Element "KTO_ART_DARLEHEN_PHASE_BEGINN"
           */
          c_bsd_index_zinsbelastung = c_bsd_start_index;

          /* 
           * Startwert innere Schleife
           * Der Index der inneren Schleife wird in der Variablen "c_bsd_aktueller_index" gespeichert.
           * Der Startwert ist auch der Index fuer das Element "KTO_ART_DARLEHEN_PHASE_BEGINN"
           * In der inneren Schleife soll kein Element der Art "KTO_ART_ZTB_ZINSBELASTUNG" bearbeitet werden.
           * Dieses wird am Ende der aeusseren Schleife sichergestellt, da dort der Zaehler entsprechend gesetzt wird.
           * 
           * Der Startwert kann nicht beim Eintritt in die innere Schleife gesetzt werden,
           * da der innere Index immer zum Index der aeusseren Schleife laeuft. Der Zaehler
           * der inneren Schleife laeuft dem Zaehler der aeusseren Schleife hinterher.
           */
          c_bsd_aktueller_index = c_bsd_start_index;

          /* 
           * Darlehenshoehe
           * Der aktuelle Datelehensbestand wird in der Variablen "c_bsd_betrag_darlehensstand" gespeichert.
           * Die Darlehenshoehe ist die Hoehe aus der Variablen "c_bsd_anfangsdarlehen".
           * Der Darlehensbestand ist in der Berechnungsschleife positiv.
           */
          c_bsd_betrag_darlehensstand = c_bsd_anfangsdarlehen;

          /* 
           * Anzahl der Vektorelemente
           * Es wird die neue Anzahl der Kontoverlaufselemente ermittelt, da die Elemente
           * der Darlehensphase hinzugekommen sind.
           * 
           * Es wurden mehr Verlaufselemente erstellt, als notwendig sind.
           * Die ueberfluessigen Elemente werden am Ende der Berechnung geloescht.
           */
          c_rk_anzahl_elemente = kto_verlauf.getAnzahl();

          /* 
           * Startwerte Zeitraum fuer erste Zinsbelastung
           * Die erste Zinsberechnungsperiode startet bei Darlehensauszahlung.
           * Das die das BSD-Berechnungsbeginn-Datum.
           */
          c_bsd_zeitraum_darlehensbestand_ab = c_bsd_berechnungsbeginn_long;

          /* 
           * Die Zins-Berechnungsgrundlage ist gleich dem Darlehensstand.
           */
          c_bsd_zeitraum_darlehensbestand_betrag = c_bsd_betrag_darlehensstand;

          /* 
           * Der aktuell unterperiodische Zinsbelastungsbetrag ist 0.
           */
          c_bsd_aktuelle_darlehenszinsen_bis_belastung = BETRAG_0;

          /* 
           * Fortfuehrungsindex fuer die Zinsberechnung startet beim Index des ersten BSD-Elementes.
           */
          c_zins_abschluss_index_ab = c_bsd_aktueller_index;

          /* 
           * Berechnungsvorgang
           * Es gibt zwei WHILE-Schleifen, eine aeussere und eine innere.
           * Die aeussere Schleife geht von einer Zinsbelastung zur naechsten.
           * Innere Schleife geht alle Elemente zwischen zwei Zinsbelastungen durch
           * 
           * Die aeussere Schleife sorgt dafuer, dass der Verlauf mit einer Zinsbelastung aufhoert
           * Die innere Schleife berechnet den Betrag der Zinsbelastung, der betrachteten Periode.
           * Das Darlehen kann nur innerhalb der inneren Schleife getilgt werden.
           * 
           * Die aeussere Schleife wird abgebrochen, wenn ein Fehler vorliegt.
           */
          while ( ( c_bsd_index_zinsbelastung < c_rk_anzahl_elemente ) && ( e_rechenkern_fehler_nummer == BERECHNUNG_FEHLER_FREI ) )
          {
            akt_element_zinsbelastung = kto_verlauf.getIndex( c_bsd_index_zinsbelastung );

            /* 
             * Pruefung: Darlehensbetrag getilgt?
             * Wenn der Darlehensbetrag getilgt ist, muessen alle Verlaufselemente nach dem
             * Tilgungsdatum aus dem Verlauf geloescht werden.
             * 
             * Die betreffenden Verlaufselemente werden mit einem Loeschkennzeichen versehen.
             */
            if ( c_bsd_betrag_darlehensstand <= BETRAG_0 )
            {
              akt_element_zinsbelastung.setKnzLoeschung( KNZ_ELEMENT_LOESCHEN );
            }
            else
            {
              /* 
               * Loeschkennzeichen Element behalten setzen.
               */
              akt_element_zinsbelastung.setKnzLoeschung( KNZ_ELEMENT_BEHALTEN );

              /* 
               * Pruefung: Aktuelles Element "KTO_ART_ZTB_ZINSBELASTUNG"
               * Wenn die aeussere Schleife auf ein Element "Zinsbelastung" trifft,
               * wird die innere Schleife gestartet. Innerhalb der inneren Schleife
               * werden die vom Darlehensnehmer zu zahlenden Zinsen berechnet, welche
               * dann widerum nach der inneren Schleife belastet werden.
               */
              if ( akt_element_zinsbelastung.getArt() == KTO_ART_ZTB_ZINSBELASTUNG )
              {
                /* 
                 * Startwert fuer die unterperiodischen Zinsen werden auf 0 gestellt.
                 * Der Startwert wurde auch vor dem Start der aeusseren Schleife schon
                 * auf 0 gestellt. Das sollte auch so belassen werden, da es ja korrekter sein koennte.
                 */
                c_bsd_aktuelle_darlehenszinsen_bis_belastung = BETRAG_0;

                c_bsd_knz_zinsberechnung_durchfuehren = B_FALSE;

                /* 
                 * ZTB-Element ausnullen
                 * Es wird das Element des letzten Zins- und Tilgungsbeitrages auf Nothing gestellt.
                 * Bei diesem Element wird am Ende der Zinsbetrag aus dem Einzahlungsbetrag
                 * herausgerechnet.
                 * 
                 * Wird das Darlehen durch eine Sondertilgung beendet, gibt es keinen letzten
                 * ZTB-Einzahlungbetrag mehr (bzw. kein Verlaufselement der Art ZTB-Einzahlung).
                 */
                c_bsd_element_letzter_ztb = null;

                /* 
                 * Innere While-Schleife
                 * Die innere While-Schleife laeuft vom Index der letzten Zinsbelastung (+1) bis
                 * zum aktuellen Zinsbelastungsindex. Somit kann der Darlehensverlauf nur mit einem
                 * Element "Zinsbelastung" enden.
                 * 
                 * Am Ende der Inneren Schleife ist der Index der inneren Schleife gleich dem
                 * Index der aeusseren Schleife.
                 * 
                 * Die innere Schleife soll das Element der aeusseren Schleife ueberspringen.
                 * Um dieses zu erreichen muessen keine weiteren Vorkehrungen mehr gemacht werden.
                 */
                while ( ( ( c_bsd_aktueller_index < c_bsd_index_zinsbelastung ) && ( e_rechenkern_fehler_nummer == BERECHNUNG_FEHLER_FREI ) ) )
                {
                  akt_element = kto_verlauf.getIndex( c_bsd_aktueller_index );

                  /* 
                   * Pruefung: Darlehen getilgt?
                   * In der inneren Schleife gilt das Darlehen als getilgt, wenn der Betrag
                   * fuer die letzte Rate groesser als 0 ist. Es kann hier nicht auf den
                   * Darlehensbetrag geprueft werden, da dieser in der inneren Schleife immer
                   * groesser 0 ist (da immer noch eine Zinsbelastung vorhanden ist).
                   * 
                   * Wenn das Darlehen getilgt ist, werden die noch vorhandenen Element in
                   * der inneren Schleife fuer die Loeschung markiert.
                   * 
                   * Es muessen auch alle noch nicht bearbeiteten Elemente in der inneren Schleife
                   * durchgangen werden, damit alle ein Loeschkennzeichen bekommen.
                   */
                  if ( bsd_e_letzte_rate_betrag > BETRAG_0 )
                  {
                    akt_element.setKnzLoeschung( KNZ_ELEMENT_LOESCHEN );
                  }
                  else
                  {
                    akt_element.setKnzLoeschung( KNZ_ELEMENT_BEHALTEN );

                    /* ###################################################################################
                     * 
                     * Pruefung auf Darlehensende
                     * 
                     * Es wird geprueft, ob das aktuelle Element eine Einzahlung ist.
                     * Das ist bei der Art "KTO_ART_ZTB_EINZAHLUNG" und "KTO_ART_SONDERTILGUNG" der Fall.
                     * 
                     * Ist das der Fall, wird geprueft ob die Einzahlung ausreicht um das Darlehen zu tilgen.
                     * Diese Pruefung wird in 2 Teilpruefungen gemacht:
                     * 
                     * 1. Teilpruefung
                     * Der Betrag der Einzahlung muss groesser als das Restdarlehen zuzueglich den noch
                     * nicht festgeschriebenen Zinsen sein.
                     * 
                     * Die erste Teilpruefung verhindert dass jedesmal die Zinsen berechnet werden (schneller)
                     * 
                     * 2. Teilpruefung
                     * Nachdem die erste Pruefung ihr OK gegeben hat, wird der noch offene Zinsbetrag der Periode
                     * berechnet. Dieses ist der Zeitraum der letzten Teilzinsberechnung und diesem aktuellem
                     * Element. Sind keine Sondertilgungen innerhalb der Periode angefallen, ist in der
                     * Variabeln "c_bsd_aktuelle_darlehenszinsen_bis_belastung" ein Betrag von 0 enthalten.
                     * 
                     * Bei der zweiten Teilpruefung muss nun die aktuelle Einzahlung groesser als der Betrag aus
                     * der ersten Teilpruefung zuzueglich des hier angefallenen Restzinsbetrages fuer die
                     * noch ausstehende Periode (bzw. vom Datum der letzten Sondertilgung).
                     * 
                     * Gibt auch die zweite Teilpruefung ihr OK, wird der Einzahlungsbetrag auf den noch
                     * ausstehenden Betrag reduziert (Restdarlehensbetrag + Aufgelaufene Periodenzinsen)
                     * 
                     * Durch die Sortierreihenfolge der Elemente ist sichergestellt, das Gebuehrenelemente vor
                     * den Einzahlungselementen kommen. Erst erhoehen Gebuehren den Darlehensbetrag, bevor
                     * Einzahlungen den Darlehensbetrag reduzieren.
                     */
                    if ( ( akt_element.getArt() == KTO_ART_ZTB_EINZAHLUNG ) || ( akt_element.getArt() == KTO_ART_SONDERTILGUNG ) )
                    {
                      if ( akt_element.getBetragEinzahlung() > ( c_bsd_betrag_darlehensstand + c_bsd_aktuelle_darlehenszinsen_bis_belastung ) )
                      {
                        bsd_e_letzte_rate_zinsbelastung = calcBsdZinsen( B_FALSE, c_bsd_zeitraum_darlehensbestand_ab, akt_element.getDatum(), c_bsd_zeitraum_darlehensbestand_betrag, tarif_bsd_zinssatz );

                        if ( akt_element.getBetragEinzahlung() > ( c_bsd_betrag_darlehensstand + c_bsd_aktuelle_darlehenszinsen_bis_belastung + bsd_e_letzte_rate_zinsbelastung ) )
                        {
                          akt_element.setBetragEinzahlung( round2DP( c_bsd_betrag_darlehensstand + c_bsd_aktuelle_darlehenszinsen_bis_belastung + bsd_e_letzte_rate_zinsbelastung ) );

                          c_bsd_letzte_rate_element = akt_element;

                          bsd_e_letzte_rate_datum = c_bsd_letzte_rate_element.getDatum();

                          bsd_e_letzte_rate_betrag = c_bsd_letzte_rate_element.getBetragEinzahlung();
                        }
                        else
                        {
                          bsd_e_letzte_rate_zinsbelastung = BETRAG_0; // Wenn es nicht passt, wird der Betrag der letzten Zinsbelastung auf 0 gestellt.
                        }
                      }
                    }

                    /* ###################################################################################
                     * 
                     * Fortfuehrung Kontostand
                     */
                    if ( akt_element.getArt() == KTO_ART_ZTB_EINZAHLUNG )
                    {
                      /* 
                       * ZTB-Einzahlung
                       * 
                       * Der Zins- und Tilgungsbeitrag reduziert in erster Linie die Darlehenshoehe.
                       * Mit dem Betrag wird aber auch gleich die zu leistende Zinsbelastung beglichen.
                       * Die Zinsfestschreibung ist ein gesonderter Kontoverlaufseintrag. Daher wird
                       * an dieser Stelle der volle Zins- und Tilgungsbeitrag von der Darlehenshoehe
                       * abgezogen.
                       * 
                       * Vom Rechenkern her ist sichergestellt, dass auf eine Einzahlung des ZTBs
                       * auch gleich die Zinsbelastung stattfindet (Sortierreihenfolge).
                       * 
                       * Die Zinsbelastung wird am gleichen Tag gemacht. Somit ist sichergestellt,
                       * das kein zuviel reduzierter Darlehensbetrag in die Zinsberechnugen einfiesst.
                       * Desweiteren wird die Zinsbelastung auf dem vorhergehenden Kontostand gerechnet.
                       * Da bei einem ZTB-Einzahlung gleich die Zinsbelastung folgt, wird der
                       * Kontostand bei der Zinsbelastung wieder auf den korrekten Darlehensstand
                       * gesetzt (Variable c_bsd_zeitraum_darlehensbestand_betrag).
                       */
                      c_bsd_betrag_darlehensstand = c_bsd_betrag_darlehensstand - akt_element.getBetragEinzahlung();

                      /*  
                       * Merken dieses Elementes (Berechnung des Zinsanteils und Tilgungsanteils in der aeusseren Schleife)
                       */
                      c_bsd_element_letzter_ztb = akt_element;

                      /* 
                       * Aufsummierung der Gesamtsumme der eigenen Einzahlungen in der Darlehensphase
                       */
                      bsd_e_gesamt_eigene_einzahlung = bsd_e_gesamt_eigene_einzahlung + akt_element.getBetragEinzahlung();
                    }
                    else if ( akt_element.getArt() == KTO_ART_SONDERTILGUNG )
                    {
                      /* 
                       * Sondertilgung
                       * 
                       * Sondertilgungen reduzieren die Darlehenshoehe.
                       */
                      c_bsd_betrag_darlehensstand = c_bsd_betrag_darlehensstand - akt_element.getBetragEinzahlung();

                      /*  Aufsummierung der Gesamtsumme der Sondertilgungen
                       */
                      bsd_e_gesamt_sondertiglung = bsd_e_gesamt_sondertiglung + akt_element.getBetragEinzahlung();
                    }
                    else if ( akt_element.getArt() == KTO_ART_DARLEHEN_KTO_GEBUEHR_LAUFEND )
                    {
                      /* 
                       * Kontofuehrungsgebuehren
                       * Gebuehren muessen vom Darlehensnehmer beglichen werden und erhoehen
                       * deshalb den Darlehensbetrag.
                       */
                      c_bsd_betrag_darlehensstand = c_bsd_betrag_darlehensstand + akt_element.getBetragGebuehr();

                      /* 
                       * Aufaddierung der Kontofuehrungsgebuehren in der Darlehensphase
                       */
                      bsd_e_gesamt_kfg = bsd_e_gesamt_kfg + akt_element.getBetragGebuehr();
                    }
                    else if ( akt_element.getArt() == KTO_ART_DARLEHEN_DISAGIO )
                    {
                      /* 
                       * Disagio
                       * Der Disagio-Betrag steht in der Spalte "Auszahlung" und wird vom
                       * Darlehensbetrag abgezogen
                       */
                      c_bsd_betrag_darlehensstand = c_bsd_betrag_darlehensstand - akt_element.getBetragAuszahlung();
                    }

                    /* ###################################################################################
                     * 
                     * Pruefung: Kontostand negativ ausweisen?
                     * Soll der Kontostand als negativer Betrag erscheinen, wird dieser mit -1 multipliziert
                     * Soll der Kontostand als positiver Betrag erscheinen, wird dieser einfach gesetzt.
                     * 
                     * Optional koennte auch der Darlehensstand immer mit einem Betrag multipliziert werden (eben 1 oder -1).
                     * Dann koennte diese If-Abfrage entfallen.
                     */
                    if ( m_bsd_knz_darlehensbetrag_negativ_ausweisen )
                    {
                      akt_element.setBetragKontostand( get3nk( getNegativ( c_bsd_betrag_darlehensstand ) ) );
                    }
                    else
                    {
                      akt_element.setBetragKontostand( get3nk( c_bsd_betrag_darlehensstand ) );
                    }

                    /* ###################################################################################
                     * 
                     * Zinsberechnung
                     */
                    if ( akt_element.getDatum() != c_bsd_zeitraum_darlehensbestand_ab )
                    {
                      /* 
                       * Das Datum des aktuellen Elementes weicht vom Startdatum des Zeitraumes ab.
                       * 
                       * Es wird ermittelt, ob sich der Darlehensstand geaendert hat.
                       * Da hier mit dem Datentyp double gehandhabt wird, muss der Differenzbetrag
                       * groesser als BETRAG_001 sein. Eine reine Pruefung mit "<>" reicht nicht aus.
                       */
                      if ( Math.abs( c_bsd_zeitraum_darlehensbestand_betrag - c_bsd_betrag_darlehensstand ) > 0.001 )
                      {
                        c_bsd_knz_zinsberechnung_durchfuehren = B_TRUE;
                      }

                      if ( c_debug_knz_zins_berechnung_bsd )
                      {
                        if ( c_bsd_knz_zinsberechnung_durchfuehren )
                        {
                          wl( "BSD_ZINS_CALC: Zinsberechnung durchfuehren, da sich der Darlehensstand geaendert hat. (Von " + c_bsd_zeitraum_darlehensbestand_betrag + " auf " + c_bsd_betrag_darlehensstand + ")" );
                        }
                        else
                        {
                          wl( "BSD_ZINS_CALC: Zinsberechnung wird nicht durchgefuehrt, da sich der Kontostand nicht geaendert hat." );
                        }

                        wl( "BSD_ZINS_CALC:" );
                        wl( "BSD_ZINS_CALC: Tagesabschluss alter Kto-Stand " + c_bsd_zeitraum_darlehensbestand_betrag + ", neuer Kto-Stand " + c_bsd_betrag_darlehensstand + " = Differenz = " + ( c_bsd_zeitraum_darlehensbestand_betrag - c_bsd_betrag_darlehensstand ) + ", Datum von " + FkDatumLong.getStringAuslong( c_bsd_zeitraum_darlehensbestand_ab ) + " bis " + akt_element.getDatumAsString() + " = " + FkDatumLong.getAnzahlTageDifferenzAusLong( c_bsd_zeitraum_darlehensbestand_ab, akt_element.getDatum() ) );
                        wl( "BSD_ZINS_CALC:" );
                      }
                    }

                    /* 
                     * Eine Zinsberechnung wird gemacht, wenn das Element fuer die
                     * letzte Rate gesetzt ist.
                     * 
                     * Der Berechnungsteil der inneren Schleife wird dann beendet und
                     * nur noch der Loeschteil der inneren Schleife ausgefuehrt.
                     */
                    if ( c_bsd_letzte_rate_element != null )
                    {
                      c_bsd_knz_zinsberechnung_durchfuehren = B_TRUE;
                    }

                    if ( c_bsd_knz_zinsberechnung_durchfuehren )
                    {
                      /* 
                       * BSD Zinsberechnung
                       * 
                       * Berechne die Zinsen fuer den Zeitraum, fuer welchen ein Darlehensstand existierte.
                       * Die Zinsen der aktuellen Zinsperiode (innere Schleife) berechnen sich jeweils fuer
                       * den aktuell VERGANGENEN Zeitraum. Der Darlehensstand ist derjenige vom Anfang der
                       * Periode.
                       * 
                       * Wann immer sich das Darelehen DURCH EINZAHLUNGEN REDUZIERT muss eine Zinsberechnung durchgefuehrt werden
                       * 
                       * -Darlehensgebuehr fuehrt nicht zu einer Zinsberechnung
                       * 
                       * -Agio oder Disagio fuehrt nicht zu einer Zinsberechnung
                       * 
                       * -Sondertilgungen reduzieren die Darlehenshoehe und somit die Zinslast
                       * 
                       * -Der Zins- und Tilgungsbeitrag ist der normale Beitrag welcher im ersten Schritt
                       *  den Darlehensbetrag reduziert. Der in dem Beitrag enthaltene Zinsanteil wird am
                       *  Datum der Zinsbelastung herausgerechnet und erhoeht somit den Darlehensbetrag.
                       * 
                       * -Der Zinsanteil sind diejenigen Zinsen, welche ueber den Betrachtungszeitraum aufgelaufen sind.
                       *  Diese berechnen sich immer nach der aktuellen Darlehenshoehe und der vergangenen Zeit.
                       *  Der Betrachtungszeitraum ist der Zeitraum, zwischen der letzten Zinsberechnung und dem 
                       *  aktuellen Tag, an welchen sich die Darlehenshoehe geaendert hat.
                       * 
                       *  Der tarifliche Darelehenszinsatz ist ein jaehrlicher Zinssatz.
                       *  Daher muss die Zinsbelastung auf einen Tageswert runtergerechnet werden.
                       *  Dieser Tageswert wird dann wieder mit den verstrichenen Tagen multipliziert.
                       * 
                       *  Der Zinsanteil verringert sich mit jeder Periode, da die zu tilgende Darlehenshoehe abnimmt.
                       *  Der Tilgungsanteil erhoeht sich mit jeder Periode, da sich der Zinsanteil verringert.
                       * 
                       * 
                       * Auszug aus den Tarifbedinungen
                       * 11 Verzinsung und Tilgung des Bauspardarlehens
                       * 
                       * ( 1 ) Der gebundene Sollzinssatz fuer das Bauspardarlehen (Darlehensschuld)
                       * betraegt 2,45% jaehrlich, die effektiven Jahreszinsen nach der Preisangabenver-
                       * ordnung (PAngV) sind in Absatz 2 angegeben.
                       * 
                       * Die Bausparkasse berechnet die Zinsen monatlich auf der Grundlage taggenauer
                       * Verrechnung aller Zahlungseingaenge und Belastungen.
                       * 
                       * Die Zinsen sind jeweils am Monatsende faellig.
                       * 
                       * ( 2 ) Zur Verzinsung und Tilgung der Darlehensschuld hat der Bausparer monatlich einen
                       * Tilgungsbeitrag (Monatsrate) zu zahlen. Die Monatsrate bestimmt sich nach der Hoehe
                       * der auf 3 Stellen nach dem Komma auf- bzw. abgerundeten Bewertungszahl.
                       * 
                       * Massgebend ist die Bewertungszahl an dem Bewertungsstichtag, der zu der Zuteilungsperiode 
                       * gehoert, in der die Annahme der Zuteilung oder die Wiederbereitstellung der Bausparsumme
                       * erfolgt ist. Diese Bewertungszahl wird dem Bausparer bei Zuteilung bzw. Wiederbereitstellung
                       * mitgeteilt.
                       * 
                       * Die Monatsrate ist in von Tausend des zugeteilten Bauspardarlehens
                       * angegeben und betraegt bei einer Bewertungszahl von:
                       * 
                       * Die Monatsraten sind so zu entrichten, dass sie jeweils am letzten Geschaeftstag
                       * des Kalendermonats kostenfrei bei der Bausparkasse eingegangen sind.
                       * 
                       * Durch die fortschreitende Tilgung der Darlehensschuld verringern sich die in
                       * den Monatsraten enthaltenen Zinsen zu Gunsten der Tilgung.
                       * 
                       * 6 Abs. 1 ). Die Monatsrate betraegt maximal den in obiger
                       * Tabelle genannten Promillesatz, der zur entsprechenden Bewertungszahl am
                       * Stichtag gehoert. Bei Zuteilung wird eine niedrigere Monatsrate angeboten,
                       * wenn das individuelle Verhaeltnis der erbrachten Sparerleistung zur Kassenleis
                       * tung mehr als 0,4 ausmacht. In diesem Fall ist ein gegebenenfalls von der
                       * Tabelle abweichender effektiver Jahreszins nach PAngV der Darlehenszusage
                       * zu entnehmen.
                       * ( 3 ) Entgelte/Gebuehren und Aufwendungen werden der Darlehensschuld zugeschlagen
                       * und wie diese verzinst und getilgt.
                       * 
                       * ( 4 ) Die erste Monatsrate ist im ersten Monat nach vollstaendiger Auszahlung des
                       * Bauspardarlehens, bei Teilauszahlung spaetestens im dritten Monat nach der
                       * ersten Teilauszahlung, zu zahlen. Die Bausparkasse teilt dem Bausparer die
                       * Faelligkeit der ersten Monatsrate mit.
                       * 
                       * ( 5 ) Der Bausparer ist berechtigt, jederzeit Sondertilgungen zu leisten.
                       * Zahlt der Bausparer den 10. Teil des Anfangsdarlehens oder mehr in einem Betrag, min
                       * destens 500 EUR, als Sondertilgung zurueck, so kann er verlangen, dass die
                       * Monatsrate zum Beginn des folgenden Quartals im Verhaeltnis des neuen zum
                       * bisherigen Restdarlehen herabgesetzt wird
                       */
                      c_bsd_zeitraum_darlehensbestand_zinsen = calcBsdZinsen( c_debug_knz_zins_berechnung_bsd, c_bsd_zeitraum_darlehensbestand_ab, akt_element.getDatum(), c_bsd_zeitraum_darlehensbestand_betrag, tarif_bsd_zinssatz );

                      /* 
                       * Aufsummierung der Zinsen der aktuellen Periode
                       */
                      c_bsd_aktuelle_darlehenszinsen_bis_belastung = c_bsd_aktuelle_darlehenszinsen_bis_belastung + c_bsd_zeitraum_darlehensbestand_zinsen;

                      /* 
                       * Zinsfortfuehrungsschleife
                       * Die Zinsfortfuehrungsschleife hat als Startwert immer den aktuellen Wert
                       * der Variablen "c_zins_abschluss_index_ab". Es werden bei den Elementen
                       * die berechneten Zwischensummen gesetzt (Unterperiodische Zinsen, Anzahl
                       * der Zinstage).
                       * 
                       * Der aktuelle Index hat die Zinsberechnung ausgeloest und ist
                       * nicht Bestandteil der Fortfuehrung der letzten Zins-Periode.
                       */
                      while ( c_zins_abschluss_index_ab < c_bsd_aktueller_index )
                      {
                        temp_element = kto_verlauf.getIndex( c_zins_abschluss_index_ab );

                        if ( temp_element != null )
                        {
                          temp_element.setBsdInfoZinsAnteil( c_bsd_zeitraum_darlehensbestand_zinsen );

                          temp_element.setBetragUjGuthabenzins( c_bsd_aktuelle_darlehenszinsen_bis_belastung );

                          temp_element.setAnzahlZinstage( c_bsd_zeitraum_darlehensbestand_anzahl_tage );
                        }

                        c_zins_abschluss_index_ab = c_zins_abschluss_index_ab + 1;
                      }

                      /* 
                       * Fuer die naechste Darlehensberechnung wird der Tag dieser Zinsberechnung
                       * im Datums-Feld "Zeitraum Darlehen Ab" gespeichert.
                       */
                      c_bsd_zeitraum_darlehensbestand_ab = akt_element.getDatum();

                      /* 
                       * Den neuen Startwert des Zeitraum-Darlehensbetrages setzen, damit
                       * die naechste Zinsberechnung mit diesem Darlehensbetrag rechnet.
                       */
                      c_bsd_zeitraum_darlehensbestand_betrag = c_bsd_betrag_darlehensstand;

                      /* 
                       * Flag fuer die Zinsberechnung auf FALSE stellen
                       */
                      c_bsd_knz_zinsberechnung_durchfuehren = B_FALSE;

                      /* 
                       * Berechnungen fuer die letzte Einzahlung
                       */
                      if ( c_bsd_letzte_rate_element != null )
                      {
                        if ( m_bsd_knz_darlehensbetrag_negativ_ausweisen )
                        {
                          c_bsd_letzte_rate_element.setBetragKontostand( get3nk( getNegativ( bsd_e_letzte_rate_zinsbelastung ) ) );
                        }
                        else
                        {
                          c_bsd_letzte_rate_element.setBetragKontostand( get3nk( bsd_e_letzte_rate_zinsbelastung ) );
                        }
                      }
                    }
                  }

                  c_bsd_aktueller_index = c_bsd_aktueller_index + 1;
                }

                /* 
                 * ###################################################################################
                 * 
                 * Zinsbelastung aus der Periode festschreiben
                 * 
                 * Die in der ineren Schleife berechneten Zinsen der Periode werden
                 * auf 2 Nachkommastellen gerundet und im Element der Zinsbelastung
                 * gespeichert.
                 */
                c_bsd_akt_zins_belastung = round2DP( c_bsd_aktuelle_darlehenszinsen_bis_belastung );

                akt_element_zinsbelastung.setBetragZinsAnteil( c_bsd_akt_zins_belastung );

                /* 
                 * Die Darlehenszinsen werden aufsummiert
                 */
                bsd_e_gesamt_zinsen = bsd_e_gesamt_zinsen + c_bsd_aktuelle_darlehenszinsen_bis_belastung;

                if ( c_debug_knz_zins_berechnung_bsd )
                {
                  wl( "BSD_ZINS_CALC_Z " );
                  wl( "BSD_ZINS_CALC_Z " + akt_element_zinsbelastung.getDatum() + " : Zinsbelastung am " + FkDatumLong.getStringAuslong( akt_element_zinsbelastung.getDatum() ) + " Zinsen " + getBetragStr( c_bsd_akt_zins_belastung ) );
                  wl( "BSD_ZINS_CALC_Z " );
                  wl( "" );
                }

                /* 
                 * Tilgungsanteil des ZTBs berechnen
                 * 
                 * Im Verlaufselement des letzten Zins- und Tilgungsbeitrages kann
                 * nun aus dem Gesamtbeitrag der reine Tilungsanteil berechnet werden.
                 * 
                 * Dazu muss es ein Element der letzten ZTB-Einzahlung geben.
                 * Dieses Element wird in der inneren Schleife gesetzt.
                 * 
                 * Ist das Element nicht gesetzt, wurde das Darlehen mit einer
                 * Sondertilgung beendet.
                 * 
                 * Ist ein solches Element vorhanden, wird vom Einzahlungsbetrag
                 * die Zinsbelastung abgezogen und im Feld "BsdInfoTilgAnteil"
                 * des Elementes gespeichert.
                 */
                if ( c_bsd_element_letzter_ztb != null )
                {
                  c_bsd_element_letzter_ztb.setBsdInfoTilgAnteil( c_bsd_element_letzter_ztb.getBetragEinzahlung() - c_bsd_akt_zins_belastung );

                  c_bsd_element_letzter_ztb.setBsdInfoZinsAnteil( c_bsd_akt_zins_belastung );
                }
                else if ( c_bsd_letzte_rate_element != null )
                {
                  c_bsd_letzte_rate_element.setBsdInfoTilgAnteil( c_bsd_letzte_rate_element.getBetragEinzahlung() - c_bsd_akt_zins_belastung );

                  c_bsd_letzte_rate_element.setBsdInfoZinsAnteil( c_bsd_akt_zins_belastung );
                }

                /* 
                 * Zinsbelastung erhoeht die Darlehenshoehe
                 * Nur wenn der Darlehensbetrag noch groesser als 0 ist.
                 * Ansonsten wuerde der Kontostand wieder groesser als 0 sein
                 * und das Konto wuerde bis zum Schluss offen bleiben.
                 */
                if ( c_bsd_betrag_darlehensstand > BETRAG_0 )
                {
                  /* 
                   * Die Zinsen aus der inneren Schleife erhoehen den Darlehensstand, da
                   * mit der letzten Einzahlung des ZTBs ja nicht die volle Hoehe als Tilgung
                   * angesehen werden kann.
                   */
                  c_bsd_betrag_darlehensstand = c_bsd_betrag_darlehensstand + akt_element_zinsbelastung.getBetragZinsAnteil();

                  /* 
                   * Nach der Veraenderung wird der Kontostand auf 2 Stellen gerundet.
                   */
                  if ( m_bsd_knz_darlehensbetrag_negativ_ausweisen )
                  {
                    akt_element_zinsbelastung.setBetragKontostand( get3nk( getNegativ( c_bsd_betrag_darlehensstand ) ) );
                  }
                  else
                  {
                    akt_element_zinsbelastung.setBetragKontostand( get3nk( c_bsd_betrag_darlehensstand ) );
                  }

                  c_bsd_zeitraum_darlehensbestand_ab = akt_element_zinsbelastung.getDatum();

                  c_bsd_zeitraum_darlehensbestand_betrag = c_bsd_betrag_darlehensstand;
                }
                else
                {
                  c_bsd_berechnungsende_long = akt_element_zinsbelastung.getDatum();
                }
              }
            }

            c_bsd_index_zinsbelastung = c_bsd_index_zinsbelastung + 1;
          }

          /* 
           * Abschlussverarbeitung der Darlehensphase
           * Nachdem die While-Schleife die Darlehensphase berechnet hat, werden die
           * zum loeschen markierten Elemente aus dem Verlauf geloescht.
           */
          kto_verlauf.removeMarkierteElemente();
        }
      }

      wl( "" );
      wl( "'################################################################################################################## " );
      wl( "'###### ERGEBNISSE DARLEHEN                                                                                    #### " );
      wl( "'################################################################################################################## " );
      wl( "" );
      wl( "bsd_e_anfangsdarlehen                                =>" + bsd_e_anfangsdarlehen + "<" );
      wl( "bsd_e_betrag_darlehen_mit_mehrzuteilung              =>" + bsd_e_betrag_darlehen_mit_mehrzuteilung + "<" );
      wl( "bsd_e_betrag_mehr_darlehen                           =>" + bsd_e_betrag_mehr_darlehen + "<" );
      wl( "bsd_e_darlehens_gebuehr_betrag                       =>" + bsd_e_darlehens_gebuehr_betrag + "<" );
      wl( "bsd_e_darlehens_gebuehr_bezeichnung                  =>" + bsd_e_darlehens_gebuehr_bezeichnung + "<" );
      wl( "bsd_e_darlehens_gebuehr_datum_belastung              =>" + bsd_e_darlehens_gebuehr_datum_belastung + "<" );
      wl( "bsd_e_datum_darlehensbeginn                          =>" + bsd_e_datum_darlehensbeginn + "<" );
      wl( "bsd_e_betrag_disagio                                 =>" + bsd_e_betrag_disagio + "<" );
      wl( "bsd_e_disagio_bezeichnung                            =>" + bsd_e_disagio_bezeichnung + "<" );
      wl( "bsd_e_disagio_datum_belastung                        =>" + bsd_e_disagio_datum_belastung + "<" );
      wl( "bsd_e_gesamt_eigene_einzahlung                       =>" + bsd_e_gesamt_eigene_einzahlung + "<" );
      wl( "bsd_e_gesamt_kfg                                     =>" + bsd_e_gesamt_kfg + "<" );
      wl( "bsd_e_gesamt_sondertiglung                           =>" + bsd_e_gesamt_sondertiglung + "<" );
      wl( "bsd_e_gesamt_zinsen                                  =>" + bsd_e_gesamt_zinsen + "<" );
      wl( "bsd_e_letzte_rate_betrag                             =>" + bsd_e_letzte_rate_betrag + "<" );
      wl( "bsd_e_letzte_rate_datum                              =>" + bsd_e_letzte_rate_datum + "<" );
      wl( "bsd_e_letzte_rate_zinsbelastung                      =>" + bsd_e_letzte_rate_zinsbelastung + "<" );
      wl( "bsd_e_tariflicher_darlehensanspruch                  =>" + bsd_e_tariflicher_darlehensanspruch + "<" );
      wl( "c_bsd_betrag_darlehensstand                          =>" + c_bsd_betrag_darlehensstand + "<" );
      wl( "c_bsd_akt_zins_belastung                             =>" + c_bsd_akt_zins_belastung + "<" );
      wl( "c_bsd_aktuelle_darlehenszinsen_bis_belastung         =>" + c_bsd_aktuelle_darlehenszinsen_bis_belastung + "<" );
      wl( "c_bsd_anfangsdarlehen                                =>" + c_bsd_anfangsdarlehen + "<" );
      wl( "c_bsd_anzahl_monate_gesamt                           =>" + c_bsd_anzahl_monate_gesamt + "<" );
      wl( "c_bsd_berechnungsbeginn_long                         =>" + c_bsd_berechnungsbeginn_long + "<" );
      wl( "c_bsd_berechnungsende_long                           =>" + c_bsd_berechnungsende_long + "<" );
      wl( "c_bsd_bsd_knz_darlehen_berechnen                     =>" + c_bsd_bsd_knz_darlehen_berechnen + "<" );
      wl( "c_bsd_datum_letztes_kto_element                      =>" + c_bsd_datum_letztes_kto_element + "<" );
      wl( "c_bsd_start_index                                    =>" + c_bsd_start_index + "<" );
      wl( "c_bsd_zeitraum_darlehensbestand_ab                   =>" + c_bsd_zeitraum_darlehensbestand_ab + "<" );
      wl( "c_bsd_zeitraum_darlehensbestand_anzahl_tage          =>" + c_bsd_zeitraum_darlehensbestand_anzahl_tage + "<" );
      wl( "c_bsd_zeitraum_darlehensbestand_betrag               =>" + c_bsd_zeitraum_darlehensbestand_betrag + "<" );
      wl( "c_bsd_zeitraum_darlehensbestand_zinsen               =>" + c_bsd_zeitraum_darlehensbestand_zinsen + "<" );
      wl( "c_bsd_zins_und_tilgungsbeitrag                       =>" + c_bsd_zins_und_tilgungsbeitrag + "<" );
      wl( "c_bsd_nztb_anfangsdarlehen                           =>" + c_bsd_nztb_anfangsdarlehen + "<" );
      wl( "c_bsd_nztb_bausparsumme                              =>" + c_bsd_nztb_bausparsumme + "<" );
      wl( "c_bsd_nztb_guthaben_bei_zuteilung                    =>" + c_bsd_nztb_guthaben_bei_zuteilung + "<" );
      wl( "c_bsd_nztb_tarif_name                                =>" + c_bsd_nztb_tarif_name + "<" );
      wl( "c_bsd_nztb_tarif_zins_darlehen                       =>" + c_bsd_nztb_tarif_zins_darlehen + "<" );
      wl( "c_bsd_nztb_tarif_zins_sparen                         =>" + c_bsd_nztb_tarif_zins_sparen + "<" );
      wl( "c_bsd_nztb_zinsen_bei_zuteilung                      =>" + c_bsd_nztb_zinsen_bei_zuteilung + "<" );
      wl( "tarif_kto_gebuehren_jahr                             =>" + tarif_kto_gebuehren_jahr + "<" );
      wl( "tarif_mindest_bss                                    =>" + tarif_mindest_bss + "<" );
      wl( "tarif_mindest_bwz                                    =>" + tarif_mindest_bwz + "<" );
      wl( "tarif_promille_abschluss_gebuehr                     =>" + tarif_promille_abschluss_gebuehr + "<" );
      wl( "tarif_proz_guthabenzins                              =>" + tarif_proz_guthabenzins + "<" );
      wl( "tarif_proz_mindest_sparguthaben                      =>" + tarif_proz_mindest_sparguthaben + "<" );
      wl( "tarif_regel_sparbeitrag                              =>" + tarif_regel_sparbeitrag + "<" );
      wl( "tarif_wartezeit_in_monaten                           =>" + tarif_wartezeit_in_monaten + "<" );
    }

    e_millisekunden_ende_darlehensphase = System.currentTimeMillis();

    /* 
     * ##################################################################################################################
     * ##################################################################################################################
     * ##################################################################################################################
     * ##################################################################################################################
     */
    CalcBsvErgebnis calc_bsv_ergebnis = new CalcBsvErgebnis();

    /*
     * Fehlernummer und Fehlertext setzen
     */
    calc_bsv_ergebnis.setRechenkernFehlerNummer( e_rechenkern_fehler_nummer );

    if ( e_rechenkern_fehler_nummer == FEHLER_VORGABE_DATEN_UNVOLLSTAENDIG )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_VORGABE_DATEN_UNVOLLSTAENDIG" );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_KEINE_SPAR_RATE_BIS_SPAR_STOPP )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_KEINE_SPAR_RATE_BIS_SPAR_STOPP" );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_BSS_UNTERSCHREITET_MINDEST_BSS )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_BSS_UNTERSCHREITET_MINDEST_BSS" );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_MINDEST_SPARZEIT_UNTERSCHRITTEN )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_MINDEST_SPARZEIT_UNTERSCHRITTEN" );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_ZU_VIELE_ITERATIONEN )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_ZU_VIELE_ITERATIONEN" );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_START_ELEMENT_SPAR_NICHT_GEFUNDEN )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_START_ELEMENT_SPAR_NICHT_GEFUNDEN" );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_START_ELEMENT_BSD_NICHT_GEFUNDEN )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_START_ELEMENT_BSD_NICHT_GEFUNDEN" );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_BWZ_STAFFEL_KEIN_WERT_GEFUNDEN )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_BWZ_STAFFEL_KEIN_WERT_GEFUNDEN " + c_stichtag_betrag_bewertungszahl );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_ERMITTLUNG_ZINS_UND_TILIGUNGSBEITRAG )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_ERMITTLUNG_ZINS_UND_TILIGUNGSBEITRAG " );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_ZUTEILUNG_WIRD_NICHT_ERREICHT )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_ZUTEILUNG_WIRD_NICHT_ERREICHT " );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_ZTB_VORGABE_BETRAGSANGABE_UNGUELTIG )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_ZTB_VORGABE_BETRAGSANGABE_UNGUELTIG " );
    }
    else if ( e_rechenkern_fehler_nummer == FEHLER_ZTB_VORGABE_BETRAGSANGABE_PROMILLE_W )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER_ZTB_VORGABE_BETRAGSANGABE_PROMILLE_W " + get5nk( ztb_promill_bss ) );
    }
    else if ( e_rechenkern_fehler_nummer != BERECHNUNG_FEHLER_FREI )
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "FEHLER NR  " + e_rechenkern_fehler_nummer );
    }
    else
    {
      calc_bsv_ergebnis.setRechenkernFehlerText( "OK" );

      if ( c_calc_rate_kein_sparbeitrag_notwendig )
      {
        calc_bsv_ergebnis.setRechenkernFehlerText( "OK - Weiterer Sparbeitrag nicht notwendig." );
      }
    }

    /*
     * Kennzeichen Neuvertrag- oder Bestandsvertragberechnung
     */
    calc_bsv_ergebnis.setKnzIstNeuvertrag( c_knz_berechnungsart == RK_B_ART_NEUVERTRAG );

    /*
     * Datumsangaben der Sparphase (Beginn und Ende)
     */
    calc_bsv_ergebnis.setDatumBerechnungBeginn( e_spar_datum_berechnungsbeginn_long );

    calc_bsv_ergebnis.setDatumBerechnungEnde( c_spar_datum_zuteilung_long );

    /*
     * Anzahl der Sparraten bis zum Sparstopp setzen
     */
    calc_bsv_ergebnis.setAnzahlBsvSparratenBisSparStopp( c_anzahl_bsv_sparraten_bis_spar_stopp );

    /*
     * Anzahl der Tage bis zur Zuteilung
     */
    calc_bsv_ergebnis.setAnzahlStichtageBisZuteilung( c_anzahl_stichtage_bis_zum_massgeblichen_stichtag );

    /*
     * Daten des Tarifes setzen
     */
    calc_bsv_ergebnis.setTarifMindestsparzeitAnzahlMonate( tarif_mindestsparzeit_monate );
    calc_bsv_ergebnis.setTarifMindestsparzeitDatum( c_spar_datum_mindestsparzeit_long );

    calc_bsv_ergebnis.setTarifMindestguthabenBetrag( getBigDecimal( e_spar_betrag_tarifliches_mindestsparguthaben ) );
    calc_bsv_ergebnis.setTarifMindestguthabenDatum( c_tarif_migu_datum_long );
    calc_bsv_ergebnis.setTarifMindestguthabenKnzErreicht( c_tarif_migu_knz_erreicht );

    calc_bsv_ergebnis.setTarifBewertungszahlBetrag( getBigDecimal( c_tarif_minbwz_betrag ) );
    calc_bsv_ergebnis.setTarifBewertungszahlDatum( c_tarif_minbwz_datum_long );
    calc_bsv_ergebnis.setTarifBewertungszahlKnzErreicht( c_tarif_minbwz_knz_erreicht );

    calc_bsv_ergebnis.setTarifZuteilungsvoraussetzungenDatum( c_tarif_ztv_datum_long );
    calc_bsv_ergebnis.setTarifZuteilungsvoraussetzungenKnzErreicht( c_tarif_ztv_knz_erreicht );

    calc_bsv_ergebnis.setTarifRegelsparbeitragBetrag( getBigDecimal( c_tariflicher_regel_sparbeitrag ) );
    calc_bsv_ergebnis.setTarifRegelsparbeitragPromilleBss( getBigDecimal( tarif_regel_sparbeitrag ) );

    calc_bsv_ergebnis.setTarifMindestBss( getBigDecimal( tarif_mindest_bss ) );
    calc_bsv_ergebnis.setTarifMindestBwz( getBigDecimal5( tarif_mindest_bwz ) );
    calc_bsv_ergebnis.setTarifPromilleAbschlussGebuehr( getBigDecimal( tarif_promille_abschluss_gebuehr ) );
    calc_bsv_ergebnis.setTarifProzGuthabenzins( getBigDecimal( tarif_proz_guthabenzins ) );
    calc_bsv_ergebnis.setTarifProzMindestSparguthaben( getBigDecimal( tarif_proz_mindest_sparguthaben ) );
    calc_bsv_ergebnis.setTarifWartezeitInMonaten( getBigDecimal( tarif_wartezeit_in_monaten ) );

    calc_bsv_ergebnis.setBetragBsvSparbeitrag( getBigDecimal( bsv_sparbeitrag_betrag ) );
    calc_bsv_ergebnis.setDatumSparstoppSparrate( c_datum_sparstopp_sparrate_long );

    calc_bsv_ergebnis.setBetragBsvBausparsumme( getBigDecimal( c_betrag_bss_berechnet ) );

    /*
     * Daten Kontofuehrungsgebuehr Sparphase
     */
    calc_bsv_ergebnis.setSparKtoGebuehrKnzBeruecksichtigt( c_spar_kto_gebuehr_knz_beruecksichtigen );

    calc_bsv_ergebnis.setSparKtoGebuehrAnteiligAnzMonate( e_spar_kto_gebuehr_anteilig_anz_monate );
    calc_bsv_ergebnis.setSparKtoGebuehrAnteiligBetrag( getBigDecimal( e_spar_kto_gebuehr_anteilig_betrag ) );
    calc_bsv_ergebnis.setSparKtoGebuehrAnteiligDatumBelastung( e_spar_kto_gebuehr_anteilig_datum_belastung );

    calc_bsv_ergebnis.setSparKtoGebuehrLaufendBetrag( getBigDecimal( e_spar_kto_gebuehr_laufend_betrag ) );
    calc_bsv_ergebnis.setSparKtoGebuehrLaufendDatumBelastung( e_spar_kto_gebuehr_laufend_datum_belastung );

    /*
     * Ergebnisse Foerderung Arbeitnehmersparzulage
     */
    calc_bsv_ergebnis.setSparAnspKnzBerechnet( c_knz_mit_arbeitnehmer_sparzulage );
    calc_bsv_ergebnis.setSparAnspBetragAusstehend( getBigDecimal( c_ansp_betrag_ausstehend ) );
    calc_bsv_ergebnis.setSparAnspBetragGesamt( getBigDecimal( c_ansp_betrag_ausstehend + c_ansp_betrag_gutgeschrieben ) );
    calc_bsv_ergebnis.setSparAnspBetragGutgeschrieben( getBigDecimal( c_ansp_betrag_gutgeschrieben ) );

    /*
     * Ergebnisse Foerderung Wohnungsbaupraemie (WOP)
     */
    calc_bsv_ergebnis.setSparWopKnzBerechnet( c_wop_knz_aktiv );
    calc_bsv_ergebnis.setSparWopBetragAusstehend( getBigDecimal( c_wop_betrag_festgesetzt ) );
    calc_bsv_ergebnis.setSparWopBetragGebuehrGesamt( getBigDecimal( c_wop_betrag_wop_gebuehr_gesamt ) );
    calc_bsv_ergebnis.setSparWopBetragGesamt( getBigDecimal( c_wop_betrag_festgesetzt + c_wop_betrag_gutgeschrieben ) );
    calc_bsv_ergebnis.setSparWopBetragGutgeschrieben( getBigDecimal( c_wop_betrag_gutgeschrieben ) );

    /*
     * Ergebnisse der Stichtag-Daten setzen
     */
    calc_bsv_ergebnis.setStichtagDatum( c_spar_datum_stichtag_long );
    calc_bsv_ergebnis.setStichtagBetragBewertungszahl( getBigDecimal5( c_stichtag_betrag_bewertungszahl ) );
    calc_bsv_ergebnis.setStichtagBetragSparbeitrag( getBigDecimal( c_stichtag_betrag_sparbeitrag ) );
    calc_bsv_ergebnis.setStichtagBetragVlAntragsteller( getBigDecimal( c_stichtag_betrag_vl_as ) );
    calc_bsv_ergebnis.setStichtagBetragVlEhepartner( getBigDecimal( c_stichtag_betrag_vl_ep ) );
    calc_bsv_ergebnis.setStichtagBetragSonderzahlung( getBigDecimal( c_stichtag_betrag_sonderzahlung ) );
    calc_bsv_ergebnis.setStichtagBetragZinsen( getBigDecimal( c_stichtag_betrag_zinsen ) );
    calc_bsv_ergebnis.setStichtagBetragGebeuhrenKfg( getBigDecimal( c_stichtag_betrag_gebeuhren_kfg ) );
    calc_bsv_ergebnis.setStichtagBetragKontostand( getBigDecimal( c_stichtag_betrag_kontostand ) );
    calc_bsv_ergebnis.setStichtagBetragSaldensumme( getBigDecimal( c_stichtag_betrag_saldensumme ) );
    calc_bsv_ergebnis.setStichtagBetragSollGuthaben( getBigDecimal( c_stichtag_betrag_soll_guthaben ) );

    /*
     * Gesamtbetraege der Sparphase setzen
     */
    calc_bsv_ergebnis.setZuteilungDatum( c_spar_datum_zuteilung_long );
    calc_bsv_ergebnis.setZuteilungBetragBewertungszahl( getBigDecimal5( c_betrag_akt_bewertungszahl ) );
    calc_bsv_ergebnis.setZuteilungBetragSparbeitrag( getBigDecimal( c_betrag_ges_bsv_sparbeitrag ) );
    calc_bsv_ergebnis.setZuteilungBetragVlAntragsteller( getBigDecimal( c_betrag_ges_vl_antragsteller ) );
    calc_bsv_ergebnis.setZuteilungBetragVlEhepartner( getBigDecimal( c_betrag_ges_vl_ehepartner ) );
    calc_bsv_ergebnis.setZuteilungBetragSonderzahlung( getBigDecimal( c_betrag_ges_sonderzahlung ) );
    calc_bsv_ergebnis.setZuteilungBetragZinsen( getBigDecimal( c_betrag_ges_zinsen_spar ) );
    calc_bsv_ergebnis.setZuteilungBetragGebuehrenKfg( getBigDecimal( c_betrag_ges_kto_gebuehren_spar ) );
    calc_bsv_ergebnis.setZuteilungBetragBonusZinsen( getBigDecimal( c_betrag_ges_bonus_zinsen ) );
    calc_bsv_ergebnis.setZuteilungBetragBonusGutgeschrieben( getBigDecimal( c_bonus_gutgeschrieben ) );
    calc_bsv_ergebnis.setZuteilungBetragGuthabenZinsen( getBigDecimal( c_betrag_ges_guthaben_zinsen ) );

    /*
     * Ergebnisse Darlehensphase
     */
    calc_bsv_ergebnis.setBsdBetragAnfangsdarlehen( getBigDecimal( bsd_e_anfangsdarlehen ) );
    calc_bsv_ergebnis.setBsdBetragDarlehenMitMehrzuteilung( getBigDecimal( bsd_e_betrag_darlehen_mit_mehrzuteilung ) );
    calc_bsv_ergebnis.setBsdBetragMehrDarlehen( getBigDecimal( bsd_e_betrag_mehr_darlehen ) );
    calc_bsv_ergebnis.setBsdDarlehensGebuehrBetrag( getBigDecimal( bsd_e_darlehens_gebuehr_betrag ) );
    calc_bsv_ergebnis.setBsdDarlehensGebuehrDatumBelastung( bsd_e_darlehens_gebuehr_datum_belastung );
    calc_bsv_ergebnis.setBsdDatumDarlehenBeginn( bsd_e_datum_darlehensbeginn );
    calc_bsv_ergebnis.setBsdDatumDarlehenEnde( bsd_e_letzte_rate_datum );
    calc_bsv_ergebnis.setBsdDisagioBetrag( getBigDecimal( bsd_e_betrag_disagio ) );
    calc_bsv_ergebnis.setBsdDisagioDatumBelastung( bsd_e_disagio_datum_belastung );
    calc_bsv_ergebnis.setBsdGesamtEigeneEinzahlung( getBigDecimal( bsd_e_gesamt_eigene_einzahlung ) );
    calc_bsv_ergebnis.setBsdGesamtSondertiglung( getBigDecimal( bsd_e_gesamt_sondertiglung ) );
    calc_bsv_ergebnis.setBsdGesamtZinsen( getBigDecimal( bsd_e_gesamt_zinsen ) );
    calc_bsv_ergebnis.setBsdLetzteRateBetrag( getBigDecimal( bsd_e_letzte_rate_betrag ) );
    calc_bsv_ergebnis.setBsdLetzteRateDatum( bsd_e_letzte_rate_datum );
    calc_bsv_ergebnis.setBsdLetzteRateZinsbelastung( getBigDecimal( bsd_e_letzte_rate_zinsbelastung ) );
    calc_bsv_ergebnis.setBsdTariflicherDarlehensanspruch( getBigDecimal( bsd_e_tariflicher_darlehensanspruch ) );
    calc_bsv_ergebnis.setBsdTariflicherZtb( getBigDecimal( bsd_e_tariflicher_ztb ) );
    calc_bsv_ergebnis.setBsdTariflicherZtb( getBigDecimal( bsd_e_tariflicher_ztb ) );
    calc_bsv_ergebnis.setBsdZtbBetrag( getBigDecimal( c_bsd_zins_und_tilgungsbeitrag ) );
    calc_bsv_ergebnis.setBsdZtbPromillBss( getBigDecimal5( ( bsd_e_tariflicher_ztb * BETRAG_1000 ) / c_betrag_bss_berechnet ) );

    /*
     * Daten Kontofuehrungsgebuehr Darlehensphase
     */
    calc_bsv_ergebnis.setBsdKtoGebuehrKnzBeruecksichtigt( c_bsd_kto_gebuehr_knz_beruecksichtigen );

    calc_bsv_ergebnis.setBsdGesamtKfg( getBigDecimal( bsd_e_gesamt_kfg ) );

    calc_bsv_ergebnis.setBsdKtoGebuehrAnteiligBetrag( getBigDecimal5( e_kto_gebuehr_tilgen_anteilig_betrag ) );

    calc_bsv_ergebnis.setBsdKtoGebuehrAnteiligAnzMonate( e_kto_gebuehr_tilgen_anteilig_anz_monate );

    calc_bsv_ergebnis.setBsdKtoGebuehrLaufendBetrag( getBigDecimal5( e_bsd_kto_gebuehr_laufend_betrag ) );

    /*
     * Ergebnisse Laufzeiten (Sparphase, Darlehensphase, Gesamt)
     */
    calc_bsv_ergebnis.setLaufzeitSparphaseGesamtMonate( e_spar_laufzeit_gesamt_monate );
    calc_bsv_ergebnis.setLaufzeitSparphaseJahre( FkDatumLong.getAnzahlJahreAusMonaten( calc_bsv_ergebnis.getLaufzeitSparphaseGesamtMonate() ) );
    calc_bsv_ergebnis.setLaufzeitSparphaseMonate( FkDatumLong.getAnzahlMonateAusMonaten( calc_bsv_ergebnis.getLaufzeitSparphaseGesamtMonate() ) );

    calc_bsv_ergebnis.setLaufzeitBsdGesamtMonate( FkDatumLong.getMonatsDifferenz( c_bsd_berechnungsbeginn_long, c_bsd_berechnungsende_long ) );
    calc_bsv_ergebnis.setLaufzeitBsdJahre( FkDatumLong.getAnzahlJahreAusMonaten( calc_bsv_ergebnis.getLaufzeitBsdGesamtMonate() ) );
    calc_bsv_ergebnis.setLaufzeitBsdMonate( FkDatumLong.getAnzahlMonateAusMonaten( calc_bsv_ergebnis.getLaufzeitBsdGesamtMonate() ) );

    calc_bsv_ergebnis.setLaufzeitVertragGesamtMonate( calc_bsv_ergebnis.getLaufzeitSparphaseGesamtMonate() + calc_bsv_ergebnis.getLaufzeitBsdGesamtMonate() );
    calc_bsv_ergebnis.setLaufzeitVertragJahre( FkDatumLong.getAnzahlJahreAusMonaten( calc_bsv_ergebnis.getLaufzeitVertragGesamtMonate() ) );
    calc_bsv_ergebnis.setLaufzeitVertragMonate( FkDatumLong.getAnzahlMonateAusMonaten( calc_bsv_ergebnis.getLaufzeitVertragGesamtMonate() ) );

    /*
     * Ergebnisse Berechnungszeiten
     */
    calc_bsv_ergebnis.setMillisekundenVorabberechnungen( e_millisekunden_ende_vorabberechnungen - e_millisekunden_start_funktion );

    calc_bsv_ergebnis.setMillisekundenSparphaseKtoVerlauf( e_millisekunden_ende_sparphase_kto_verlauf - e_millisekunden_ende_vorabberechnungen );

    calc_bsv_ergebnis.setMillisekundenSparphaseBerechnung( e_millisekunden_ende_sparphase_calc - e_millisekunden_ende_sparphase_kto_verlauf );

    calc_bsv_ergebnis.setMillisekundenDarlehensphase( e_millisekunden_ende_darlehensphase - e_millisekunden_ende_sparphase_calc );

    calc_bsv_ergebnis.setMillisekundenGesamt( System.currentTimeMillis() - e_millisekunden_start_funktion );

    wl( "" );
    wl( "'################################################################################################################## " );
    wl( "'###### ERGEBNISS RUECKGABE                                                                                    #### " );
    wl( "'################################################################################################################## " );
    wl( "" );
    wl( "" );

    ergebnis_str = ergebnis_str + my_chr13;
    ergebnis_str = ergebnis_str + my_chr13 + getLogString().toString();
    ergebnis_str = ergebnis_str + my_chr13 + DebugCalcBsvKtoVerlauf.getGridString( kto_verlauf, B_TRUE, DebugCalcBsvKtoVerlauf.AUSGABE_ART_TEXT );
    ergebnis_str = ergebnis_str + my_chr13;

    kto_verlauf.clear();

    ergebnis_str = ergebnis_str + my_chr13 + my_chr13 + calc_bsv_ergebnis.toString();

    calc_bsv_ergebnis.setLogString( ergebnis_str );

    kto_verlauf = null;

    m_log_string.clear();

    m_log_string = null;

    wl( calc_bsv_ergebnis.toString() + "\n" + FkSystem.getStdRootVerzeichnis() );

    return calc_bsv_ergebnis;
  }

  private String getFehlerString( int e_rechenkern_fehler_nummer )
  {
    return null;
  }

  private double calcMonatlicheZahlung( CalcZahlung pZahlung )
  {
    double betrag_monatliche_zahlung = BETRAG_0;

    if ( ( pZahlung != null ) && ( pZahlung.istAktiv() ) )
    {
      if ( pZahlung.getBetrag() != BETRAG_0 )
      {
        if ( pZahlung.istRhythmusMonatlich() )
        {
          betrag_monatliche_zahlung = pZahlung.getBetrag();
        }
        else if ( pZahlung.istRhythmusJaehrlich() )
        {
          betrag_monatliche_zahlung = pZahlung.getBetrag() * JAHRES_FAKTOR_12_MONATE;
        }
        else if ( pZahlung.istRhythmusViertelJaehrlich() )
        {
          betrag_monatliche_zahlung = ( pZahlung.getBetrag() * 4 ) * JAHRES_FAKTOR_12_MONATE;
        }
        else if ( pZahlung.istRhythmusHalbJaehrlich() )
        {
          betrag_monatliche_zahlung = ( pZahlung.getBetrag() * 2 ) * JAHRES_FAKTOR_12_MONATE;
        }
        else if ( pZahlung.istRhythmusEinmalig() )
        {
          betrag_monatliche_zahlung = pZahlung.getBetrag() * JAHRES_FAKTOR_12_MONATE;
        }
      }
    }

    return betrag_monatliche_zahlung;
  }

  private static BigDecimal getBigDecimal( double pZahl )
  {
    return new BigDecimal( "" + pZahl ).setScale( 2, BigDecimal.ROUND_HALF_UP );
  }

  private static BigDecimal getBigDecimal5( double pZahl )
  {
    double temp_zahl = (double) ( (int) ( pZahl * 1000 ) * 0.001 ) + 0.00000001;

    return new BigDecimal( "" + temp_zahl ).setScale( 5, BigDecimal.ROUND_HALF_EVEN );
  }

  private static double round3DP( double pZahl )
  {
    double temp_zahl = (double) ( (int) ( pZahl * 1000 ) * 0.001 ) + 0.00000001;

    return new BigDecimal( "" + temp_zahl ).setScale( 3, BigDecimal.ROUND_HALF_EVEN ).doubleValue();
  }

  private static double round2DP( double pZahl )
  {
    return new BigDecimal( "" + pZahl ).setScale( 2, BigDecimal.ROUND_HALF_UP ).doubleValue();
  }

  private static double get3nk( double pZahl )
  {
    double temp_zahl = (double) ( (int) ( pZahl * 1000 ) * 0.001 ) + 0.00000001;

    return new BigDecimal( "" + temp_zahl ).setScale( 3, BigDecimal.ROUND_HALF_EVEN ).doubleValue();
  }

  private static double get5nk( double pZahl )
  {
    double temp_zahl = (double) ( (int) ( pZahl * 1000 ) * 0.001 ) + 0.00000001;

    return new BigDecimal( "" + temp_zahl ).setScale( 5, BigDecimal.ROUND_HALF_EVEN ).doubleValue();
  }

  private static double get4nk( double pZahl )
  {
    double temp_zahl = (double) ( (int) ( pZahl * 1000 ) * 0.001 ) + 0.00000001;

    return new BigDecimal( "" + temp_zahl ).setScale( 4, BigDecimal.ROUND_HALF_EVEN ).doubleValue();
  }

  private static double get2nk( double pZahl )
  {
    return new BigDecimal( "" + pZahl ).setScale( 2, BigDecimal.ROUND_HALF_UP ).doubleValue();
  }

  /**
   * <pre>
   * Ermittelt aus dem Parameter "pString" den double-Wert.
   * 
   * Kommt es bei der Umwandlung zu einer Exception,
   * wird der Vorgabewert zurueckgegeben. 
   * 
   * Auf pString wird ein TRIM ausgefuehrt.
   * </pre>
   * 
   * @param pString zu parsende Zeichenkette
   * @param pVorgabeWert Vorgabewert im Fehlerfall
   * @return der Wert als double oder der Vorgabewert
   */
  private static double getDouble( String pString, double pVorgabe )
  {
    try
    {
      /*
       * Pruefung: Eingabestring ungleich null?
       */
      if ( pString != null )
      {
        /*
         * Ist der Eingabestring gesetzt, wird auf diesen ein
         * TRIM ausgefuehrt und per "Double.parseDouble()" in 
         * einen Doublewert gewandelt. 
         * 
         * Ist der String keine Zahl, kommt es zu einer 
         * Exception und der Aufrufer bekommt die Vorgabe 
         * zurueck.
         */
        return Double.parseDouble( pString.trim() );
      }
    }
    catch ( Exception err_inst )
    {
      // keine Fehlerbehandlung, da im Fehlerfall Rueckgabe von 0.0
    }

    return pVorgabe;
  }

  private static final int    INT_MINUS_1    = -1;

  private static final int    INT_0          = 0;

  private static final long   LONG_MINUS_1   = -1;

  private static final long   LONG_0         = 0;

  private static final double DOUBLE_MINUS_1 = -1.0d;

  private static final double DOUBLE_0       = 0.0d;

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit negativen Vorzeichen
   */
  private static int getNegativ( int pZahl )
  {
    if ( pZahl >= INT_0 )
    {
      return INT_MINUS_1 * pZahl;
    }

    return pZahl;
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit negativen Vorzeichen
   */
  private static long getNegativ( long pZahl )
  {
    if ( pZahl >= LONG_0 )
    {
      return LONG_MINUS_1 * pZahl;
    }

    return pZahl;
  }

  /**
   * @param pZahl die Zahl
   * @return die Zahl mit negativen Vorzeichen
   */
  private static double getNegativ( double pZahl )
  {
    if ( pZahl >= DOUBLE_0 )
    {
      return DOUBLE_MINUS_1 * pZahl;
    }

    return pZahl;
  }

  /**
   * Ausgabe auf System.out und in den Log-String-Buffer
   * 
   * @param pString der auszugebende String
   */
  private void wl( String pString )
  {
    System.out.println( pString );

    getLogString().append( pString );
  }

  /**
   * Ausgabe auf System.out. Es erfolgt keine Ausgabe in den Log-String-Buffer.
   * 
   * @param pString der auszugebende String 
   * @param pThrowable die auszugebende Fehlermeldung
   */
  private void wl( String pString, Throwable pThrowable )
  {
    System.out.println( pString );

    System.out.println( pThrowable.toString() );

    pThrowable.printStackTrace( System.out );
  }

  /**
   * @param pRhythmus die textuelle Bezeichnung des Rhythmus
   * @return der Rhythmus als Konstantenwert des Rechenkerns
   */
  private long getRhythmusKonstante( String pRhythmus )
  {
    long ergebnis_rhythmus = m_rk_rhythmus_einmalig;

    if ( pRhythmus != null )
    {
      if ( pRhythmus.equalsIgnoreCase( "monatlich" ) )
      {
        ergebnis_rhythmus = m_rk_rhythmus_monatlich;
      }
      else if ( pRhythmus.equalsIgnoreCase( "jaehrlich" ) )
      {
        ergebnis_rhythmus = m_rk_rhythmus_jaehrlich;
      }
      else if ( pRhythmus.equalsIgnoreCase( "einmalig" ) || pRhythmus.equalsIgnoreCase( "einmalbeitrag" ) )
      {
        ergebnis_rhythmus = m_rk_rhythmus_einmalig;
      }
      else if ( pRhythmus.equalsIgnoreCase( "vierteljaehrlich" ) )
      {
        ergebnis_rhythmus = m_rk_rhythmus_quartierlich;
      }
      else if ( pRhythmus.equalsIgnoreCase( "halbjaehrlich" ) )
      {
        ergebnis_rhythmus = m_rk_rhythmus_halb_jaehrlich;
      }
    }

    return ergebnis_rhythmus;
  }

  /**
   * <pre>
   * Erstellt fuer eine aktive Zahlung, die Verlaufselemente in dem uebergebenen Verlaufsvektor.
   * 
   * Ist "pZahlung" gleich null, wird nichts erstellt.
   * </pre>
   * 
   * @param pInstKtoVerlauf Vektor in welchem die Verlaufselemente hinzuzufuegen sind
   * @param pKtoElementArt die Art der zu erstellenden Verlaufselemente
   * @param pZahlung die Zahlung, aus welcher die Daten fuer die Erstellung der Verlaufselemente genommen werden.
   *                 Ist die Zahlung nicht "Aktiv", wird die Zahlung nicht in den Verlauf aufgenommen.
   * @param pAddStartDatum Vorgabedatum, wenn das Startdatum aus der Zahlung nicht gesetzt ist (das Datum 0 ist)
   * @param pAddEndDatum Vorgabedatum, wenn das Enddatum aus der Zahlung nicht gesetzt ist (das Datum 0 ist)
   * @return die Anzahl der erstellten Elemente
   */
  private int calcElementeZahlung( CalcKtoVerlaufVector pInstKtoVerlauf, long pKtoElementArt, CalcZahlung pZahlung, long pAddStartDatum, long pAddEndDatum )
  {
    long zd_end_datum = DATUM_0;
    long zd_start_datum = DATUM_0;

    if ( pZahlung != null )
    {
      if ( pZahlung.istAktiv() )
      {
        zd_start_datum = pZahlung.getDatumAb();

        if ( zd_start_datum <= DATUM_0 )
        {
          zd_start_datum = pAddStartDatum;
        }

        zd_end_datum = pZahlung.getDatumBis();

        if ( zd_end_datum <= DATUM_0 )
        {
          zd_end_datum = pAddEndDatum;
        }

        return calcElementeRhytmus( pInstKtoVerlauf, pAddStartDatum, pAddEndDatum, zd_start_datum, zd_end_datum, getRhythmusKonstante( pZahlung.getRhythmus() ), pZahlung.getKnzTag(), ANZAHL_ELEMENTE_UNBEGRENZT, pKtoElementArt, pZahlung.getId(), pZahlung.getBezeichnung(), pZahlung.getBetrag(), BETRAG_0, BETRAG_0 );
      }
    }

    return INT_0;
  }

  /**
   * @param pInstKtoVerlauf
   * @param pAddStartDatum
   * @param pAddEndDatum
   * @param pStartDatum
   * @param pEndDatum
   * @param pRhytmus
   * @param pKnzTag
   * @param pMaxAnzahlElemente
   * @param pArt
   * @param pElementID
   * @param pBezeichnung
   * @param pBetragEinzahlung
   * @param pBetragAuszahlung
   * @param pBetragGebuehr
   * @return
   */
  private int calcElementeRhytmus( CalcKtoVerlaufVector pInstKtoVerlauf, long pAddStartDatum, long pAddEndDatum, long pStartDatum, long pEndDatum, long pRhytmus, long pKnzTag, long pMaxAnzahlElemente, long pArt, String pElementID, String pBezeichnung, double pBetragEinzahlung, double pBetragAuszahlung, double pBetragGebuehr )
  {
    long inc_monate = ANZAHL_MONATE_0;

    long inc_jahre = ANZAHL_JAHRE_0;

    if ( pRhytmus == m_rk_rhythmus_jaehrlich )
    {
      inc_jahre = ANZAHL_JAHRE_1;
    }
    else if ( pRhytmus == m_rk_rhythmus_monatlich )
    {
      inc_monate = ANZAHL_MONATE_1;
    }
    else if ( pRhytmus == m_rk_rhythmus_halb_jaehrlich )
    {
      inc_monate = ANZAHL_MONATE_6;
    }
    else if ( pRhytmus == m_rk_rhythmus_quartierlich )
    {
      inc_monate = ANZAHL_MONATE_3;
    }
    else if ( pRhytmus == m_rk_rhythmus_einmalig )
    {
      pMaxAnzahlElemente = ANZAHL_ELEMENTE_1;
    }

    return calcElemente( pInstKtoVerlauf, pAddStartDatum, pAddEndDatum, pStartDatum, pEndDatum, inc_monate, inc_jahre, pKnzTag, pMaxAnzahlElemente, pArt, pElementID, pBezeichnung, pBetragEinzahlung, pBetragAuszahlung, pBetragGebuehr );
  }

  private int calcElemente( CalcKtoVerlaufVector pInstKtoVerlauf, long pAddStartDatum, long pAddEndDatum, long pStartDatum, long pEndDatum, long pIncMonate, long pIncJahre, long pKnzTag, long pMaxAnzahlElemente, long pArt, String pElementID, String pBezeichnung, double pBetragEinzahlung, double pBetragAuszahlung, double pBetragGebuehr )
  {
    if ( m_debug_knz_kto_verlauf_aufbau )
    {
      wl( "KtoVerlauf Erstellung: ----------------------------------------------" );
      wl( "KtoVerlauf Erstellung: pArt               =>" + pArt + "<" );
      wl( "KtoVerlauf Erstellung: pElementID         =>" + pElementID + "<" );
      wl( "KtoVerlauf Erstellung: pBezeichnung       =>" + pBezeichnung + "<" );
      wl( "KtoVerlauf Erstellung: pAddStartDatum     =>" + pAddStartDatum + "<" );
      wl( "KtoVerlauf Erstellung: pAddEndDatum       =>" + pAddEndDatum + "<" );
      wl( "KtoVerlauf Erstellung: pStartDatum        =>" + pStartDatum + "<" );
      wl( "KtoVerlauf Erstellung: pEndDatum          =>" + pEndDatum + "<" );
      wl( "KtoVerlauf Erstellung: pIncMonate         =>" + pIncMonate + "<" );
      wl( "KtoVerlauf Erstellung: pIncJahre          =>" + pIncJahre + "<" );
      wl( "KtoVerlauf Erstellung: pKnzTag            =>" + pKnzTag + "<" );
      wl( "KtoVerlauf Erstellung: pMaxAnzahlElemente =>" + pMaxAnzahlElemente + "<" );
      wl( "KtoVerlauf Erstellung: pBetragEinzahlung  =>" + pBetragEinzahlung + "<" );
      wl( "KtoVerlauf Erstellung: pBetragAuszahlung  =>" + pBetragAuszahlung + "<" );
      wl( "KtoVerlauf Erstellung: pBetragGebuehr     =>" + pBetragGebuehr + "<" );
    }

    /*  
     * Voreinstellung fuer die Rueckgabe ist -2
     */
    int calcElemente = -2;

    /* 
     * Pruefung: Datumsuntergrenze eingehalten?
     */
    if ( ( pStartDatum < m_rk_datum_minimal ) || ( pEndDatum < m_rk_datum_minimal ) )
    {
      wl( "FEHLER: Datumsuntergrenze bei pStartdatum oder pEndDatum unterschritten " + pElementID + " / " + pBezeichnung );

      return -2;
    }

    /* 
     * Pruefung: Datumsobergrenze eingehalten?
     */
    if ( ( pStartDatum > m_rk_datum_maximal ) || ( pEndDatum > m_rk_datum_maximal ) )
    {
      wl( "FEHLER: Datumsobergrenze bei pStartdatum oder pEndDatum unterschritten " + pElementID + " / " + pBezeichnung );

      return -2;
    }

    /* 
     * Pruefung: Liegt das Startdatum nach dem Enddatum?
     * 
     * Fehler wenn das End-Datum vor dem Startdatum liegt.
     * Alternative Vorgehensweise waere die Datumsangaben umdrehen!
     */
    if ( pStartDatum > pEndDatum )
    {
      wl( "FEHLER: pStartdatum liegt nach pEndDatum ( " + pStartDatum + " > " + pEndDatum + " ) " + pElementID + " / " + pBezeichnung );

      return -2;
    }

    CalcKtoVerlaufElement akt_element = null;

    long tages_anzahl_akt_monat = ANZAHL_0; // Die Anzahl der Tage im Monat vom aktuell betrachteten Monat/Jahr.
    boolean knz_add_to_kto_verlauf_ok = B_TRUE;

    long datum_verlauf_add_von = DATUM_0;
    long datum_verlauf_add_bis = DATUM_0;

    /* 
     * Der Startwert fuer die Anzahl der Elemente ist 1.
     * Es gibt kein 0tes eingefuegtes Element.
     */
    long anzahl_elemente = 1;

    /* 
     * Begrenzung der Elementanzahl
     * In dem Parameter "pMaxAnzahlElemente" kann die Anzahl der insgesamt
     * zu berechnenden Elemente (=Schleifendurchlaeufe) angegeben werden.
     * 
     * Intern wird dieser Wert in der Variablen "max_anzahl_elemente"
     * gespeichert. Um ungueltige Werte zu vermeiden werden bei Werten von
     * kleiner 1 oder groesser als MAX_VERLAUF die Anzahl auf MAX_VERLAUF
     * gesetzt.
     * 
     * Wenn die Anzahl der Elemente egal sein soll, muss im Parameter fuer
     * die Anzahl eine 0 uebergeben werden.
     */
    long max_anzahl_elemente = pMaxAnzahlElemente;

    if ( ( max_anzahl_elemente < ANZAHL_1 ) || ( max_anzahl_elemente > m_rk_max_elemente ) )
    {
      max_anzahl_elemente = m_rk_max_elemente;
    }

    /* 
     * Berechnung Startdatumswerte
     * Das aktuelle Datum fuer die Zahlung wird in der Variablen "akt_datum"
     * gespeichert. Dazu korrespondierend sind die Variablen fuer die Datums-
     * bestandteile "akt_tag", "akt_monat" und "akt_jahr" gespeichert.
     * 
     * Die Startwerte fuer die Datumsbestandteile werden aus dem "akt_datum" berechnet.
     */

    long akt_datum = pStartDatum;

    long akt_jahr = (long) ( pStartDatum * 0.0001 );
    long akt_monat = (long) ( ( pStartDatum - ( akt_jahr * 10000 ) ) * MULTIPLIKATOR_DURCH_100 );
    long akt_tag = 0;

    /* 
     * Pruefung: Kennzeichen fuer Monatsultimo gesetzt?
     * 
     * Wenn ja, muss der aktuelle Tag auf die maximal gueltigen Tage des Startmonats
     * gesetzt werden. Dieses in der Variablen "akt_tag" und "akt_datum".
     */
    if ( pKnzTag == 1 )
    {
      akt_tag = FkDatumLong.getTagesAnzahl( akt_monat, akt_jahr );

      akt_datum = akt_jahr * 10000 + akt_monat * 100 + akt_tag;
    }
    else
    {
      akt_tag = (long) ( pStartDatum - ( akt_jahr * 10000 + akt_monat * 100 ) );
    }

    /* 
     * Die Parameterwerte fuer increment werden in lokalen Variablen zwischengespeichert.
     */
    long inc_monat = pIncMonate;

    long inc_jahr = pIncJahre;

    long inc_tag = ANZAHL_0; // unbenutzt

    /* 
     * Umrechnung von Monats- zu Jahres-Incrementwerten.
     * Diese Routine soll nur Monats-Werte kleiner 12 hinzufuegen, damit bei einem
     * eventuellen Jahreswechsel nur 1 Jahr hinzugefuegt werden muss. Ist in der
     * Variablen "pIncMonat" ein Wert von groesser als 12 uebergeben worden, werden
     * die vollen Jahre aus diesem Wert rausgerechnet und der Variablen "inc_jahre"
     * hinzugezaehlt. Die Restmonate werden in der Variablen "inc_monat" gespeichert.
     */
    if ( pIncMonate >= MONAT_DEZEMBER )
    {
      inc_monat = ( pIncMonate - ( ANZAHL_MONATE_12 * ( (int) ( pIncMonate / ANZAHL_MONATE_12 ) ) ) );

      inc_jahr = inc_jahr + ( (int) ( pIncMonate / ANZAHL_MONATE_12 ) );
    }

    /* 
     * Es werden nur positive increment-angaben genommen.
     * Die Berechnung geht immer vom kleinsten zum groessten Datum.
     * 
     * Sind alle Angaben kleiner oder gleich null wird nur eine Zahlung erzeugt.
     * Somit wird eine Endlosschleife verhindert.
     */
    boolean knz_inc_einmalig = ( inc_tag <= ANZAHL_0 ) && ( inc_monat <= ANZAHL_0 ) && ( inc_jahr <= ANZAHL_0 );

    if ( pAddStartDatum < pAddEndDatum )
    {
      datum_verlauf_add_von = pAddStartDatum;

      datum_verlauf_add_bis = pAddEndDatum;
    }
    else
    {
      datum_verlauf_add_von = pAddStartDatum;

      datum_verlauf_add_bis = pAddEndDatum;
    }

    /* 
     * Die Schleife fuer die Tagesberechnung wird solange durchgefuehrt, wie
     *    ... das aktuelle Datum fuer das naechste Element noch kleiner gleich dem Enddatum fuer die Zahlungen ist (Datum-Bis der Zahlung)
     *    ... das aktuelle Datum fuer das naechste Element noch kleiner gleich dem Datum fuer das Hinzufuegen von Zahlungen ist (Datum-Bis des Verlaufes)
     *    ... solange der Schrittzaehler groesser 0 ist (Abbruchbedingung fuer Einfuegefehler)
     *    ... und der Zaehler fuer die Elemente kleiner/gleich der geforderten Maximalanzahl von Elementen ist.
     */
    while ( ( ( akt_datum <= pEndDatum ) && ( akt_datum <= datum_verlauf_add_bis ) && ( anzahl_elemente <= max_anzahl_elemente ) ) )
    {
      /* 
       * Neue Zahlung
       * Dem Verlauf wird nur eine neue Zahlung hinzugefuegt, wenn sich das
       * aktuelle Datum innerhalb der Grenzen des Kontoverlaufes befindet.
       * 
       * Ist das aktuelle Datum noch vor dem Datum des Verlaufes wird keine Zahlung
       * dem Verlauf hinzugefuegt.
       * 
       * Mit den aktuellen Werten eine neue Zahlung erstellen
       * Das neue Element (=Zahlung) wird dem Verlauf hinzugefuegt.
       */
      if ( ( akt_datum >= datum_verlauf_add_von ) && ( akt_datum <= datum_verlauf_add_bis ) )
      {
        akt_element = new CalcKtoVerlaufElement();

        akt_element.clear();

        akt_element.setDatum( akt_datum );
        akt_element.setArt( pArt );

        akt_element.setBetragEinzahlungReset( pBetragEinzahlung );
        akt_element.setBetragEinzahlungNeu( pBetragEinzahlung );
        akt_element.setBetragAuszahlung( pBetragAuszahlung );
        akt_element.setBetragGebuehr( pBetragGebuehr );

        akt_element.setElementId( pElementID );
        akt_element.setBezeichnung( pBezeichnung );

        akt_element.completeSortID();

        knz_add_to_kto_verlauf_ok = pInstKtoVerlauf.addElement( akt_element );
      }

      /* 
       * Neuberechnung der Variablen fuer das Datum.
       */
      if ( knz_inc_einmalig )
      {
        /* 
         * Einmalige Eintraege
         * Da nur ein Element dem Verlauf hinzugefuegt werden soll, muss die Bedingung
         * fuer das Schleifenende gesetzt werden. Dafuer wird das aktuelle Datum auf
         * das Enddatum + 1 gesetzt.
         * 
         * Es gibt hier die moegliche Fehlerquelle, wenn in der Variablen pEndDatum
         * schon der Maximal moegliche Wert fuer einen Long-Wert enthalten ist. Da
         * ein solches "Datum" aber eher unwahrscheinlich ist, kann diese Fehlerquelle
         * hingenommen werden.
         */
        akt_datum = pEndDatum + ANZAHL_1; // Kennzeichen fuer Schleifenende;
      }
      else
      {
        /* 
         * Monatswert
         * Ist in der Variablen "inc_monat" ein Wert von groesser 0 vorhanden, wurde
         * die Variable "knz_inc_monatlich" auf B_TRUE gestellt.
         * 
         * Der aktuelle Monat wird um den Betrag in "inc_monat" erhoeht. Wird dabei
         * die Monatsanzahl 12, wird der Monat umgerechnet und ein Jahr hinzugezaehlt.
         * Der neue Monat ist der Wert aus der Addition abzueglich 12.
         * 
         * Die Routine ist so abgestimmt, dass es hoechstens zu einem Jahresueberlauf
         * kommt. Groessere Monatswerte wurden entsprechend umgerechnet und in der
         * Variablen "inc_jahr" beruecksichtigt.
         * 
         * If ( knz_inc_monatlich ) {
         */

        akt_monat = akt_monat + inc_monat;

        if ( akt_monat > MONAT_DEZEMBER )
        {
          akt_monat = akt_monat - ANZAHL_MONATE_12;

          akt_jahr = akt_jahr + ANZAHL_JAHRE_1;
        }

        /* }
         * 
         * Jahreswert
         * Bei den Jahreswerten wird der Wert in der Variablen "akt_jahr" einfach
         * nur um den Wert der Variablen "inc_jahr" erhoeht. Ueberlaeufe muessen nicht
         * beruecksichtigt werden.
         * 
         * If ( knz_inc_jaehrlich ) {
         */

        akt_jahr = akt_jahr + inc_jahr;

        /* }
         * 
         * Ausnullen von unkorrekten Datumsangaben
         * Die Tagesanzahl muss bestimmt werden, da durch die neu berechneten Werte
         * in den Variablen "akt_monat" und "akt_jahr" ein falsches Datum kommen kann.
         * 
         * Sollte der Monat weniger Tage haben, als die Variablen "akt_tag" vorgibt, werden
         * die Tage auf die maximal gueltige Tagesanzahl begrennzt.
         * 
         * Der gespeicherte Wert in "akt_tag" wird fuer nachfolgende Berechnungen nicht
         * veraendert, es sei denn, das Kennzeichen fuer Monatsultimo wurde gesetzt.
         */
        tages_anzahl_akt_monat = FkDatumLong.getTagesAnzahl( akt_monat, akt_jahr );

        if ( pKnzTag > 0 )
        {
          akt_tag = tages_anzahl_akt_monat;
        }

        if ( akt_tag > tages_anzahl_akt_monat )
        {
          akt_datum = akt_jahr * 10000 + akt_monat * 100 + tages_anzahl_akt_monat;
        }
        else
        {
          akt_datum = akt_jahr * 10000 + akt_monat * 100 + akt_tag;
        }
      }
      /* 
       * Den Zaehler fuer die Anzahl der Elemente um 1 erhoehen.
       */
      anzahl_elemente = anzahl_elemente + 1;
    }

    return 1;
  }

  /* ################################################################################
   */
  private double calcBsdZinsen( boolean pKnzLog, long pDatumVon, long pDatumBis, double pDarlehensbetrag, double pBsdZinssatz )
  {
    long zeitraum_anzahl_tage = ANZAHL_0;
    double zeitraum_bsd_zinsen = BETRAG_0;
    double temp_zinsen_im_jahr = BETRAG_0;
    double temp_zinsen_je_tag = BETRAG_0;

    temp_zinsen_im_jahr = ( pDarlehensbetrag * pBsdZinssatz * MULTIPLIKATOR_DURCH_100 );

    if ( FkDatumLong.getLongKnzIstSchaltjahr( pDatumBis ) )
    {
      temp_zinsen_je_tag = temp_zinsen_im_jahr * JAHRES_FAKTOR_366;
    }
    else
    {
      temp_zinsen_je_tag = temp_zinsen_im_jahr * JAHRES_FAKTOR_365;
    }

    zeitraum_anzahl_tage = FkDatumLong.getAnzahlTageDifferenzAusLong( pDatumVon, pDatumBis );

    zeitraum_bsd_zinsen = get5nk( temp_zinsen_je_tag * zeitraum_anzahl_tage );

    if ( pKnzLog )
    {
      wl( "BSD_ZINS_CALC_Z " + pDatumBis + " : von " + FkDatumLong.getStringAuslong( pDatumVon ) + " bis " + FkDatumLong.getStringAuslong( pDatumBis ) + " = " + FkString.right( "     " + zeitraum_anzahl_tage, 4 ) + " Tag( e ) - " + FkString.right( "               " + getBetragStr( pDarlehensbetrag ), 15 ) + " = Zinsen " + getBetragStr( zeitraum_bsd_zinsen ) );
    }

    return zeitraum_bsd_zinsen;
  }

  /* ################################################################################
   */
  private static String getBetragStr( double pBetrag )
  {
    return FkString.right( "               " + get2nk( pBetrag ), 12 );
  }

  private static String getBwzStr( double pBetrag )
  {
    return FkString.right( "               " + get5nk( pBetrag ), 12 );
  }

  private static String getZaehler4( long pZahl )
  {
    return FkString.right( "               " + pZahl, 4 );
  }

  private static double getTausender( double pZahl )
  {
    if ( pZahl < DOUBLE_0 )
    {
      return (double) ( (int) ( pZahl * -0.001 ) );
    }
    else
    {
      return (double) ( (int) ( pZahl * 0.001 ) );
    }
  }

  private static double getVolleTausenderBSS( double pZahl )
  {
    if ( pZahl < DOUBLE_0 )
    {
      return (double) ( (int) ( pZahl * -0.001 ) ) * 1000.0;

    }
    else
    {
      return (double) ( (int) ( pZahl * 0.001 ) ) * 1000.0;
    }
  }

  private double calcBetragLaufzeitVorab( CalcZahlung pZahlung, long pDatumVon, long pDatumBis, long pAnzahlMonate )
  {
    if ( pDatumVon < 0 )
    {
      return BETRAG_0;
    }

    if ( pDatumBis < 0 )
    {
      return BETRAG_0;
    }

    double betrag_monatliche_zahlung = BETRAG_0;

    if ( ( pZahlung != null ) && ( pZahlung.istAktiv() ) )
    {
      if ( ( pZahlung.getDatumBis() > 0 ) && ( pZahlung.getDatumBis() < pDatumVon ) )
      {
        return BETRAG_0; // Zahlung Bis ist gesetzt und die Zahlung endet vor pDatumVon = keine Zahlung 
      }

      if ( ( pZahlung.getDatumAb() > 0 ) && ( pZahlung.getDatumAb() > pDatumBis ) )
      {
        return BETRAG_0; // Zahlung Ab ist gesetzt und startet nach Zeitraum = keine Zahlung
      }

      if ( pZahlung.getBetrag() != BETRAG_0 )
      {
        if ( pZahlung.istRhythmusMonatlich() )
        {
          betrag_monatliche_zahlung = pZahlung.getBetrag();
        }
        else if ( pZahlung.istRhythmusJaehrlich() )
        {
          betrag_monatliche_zahlung = pZahlung.getBetrag() * JAHRES_FAKTOR_12_MONATE;
        }
        else if ( pZahlung.istRhythmusViertelJaehrlich() )
        {
          betrag_monatliche_zahlung = ( pZahlung.getBetrag() * 4 ) * JAHRES_FAKTOR_12_MONATE;
        }
        else if ( pZahlung.istRhythmusHalbJaehrlich() )
        {
          betrag_monatliche_zahlung = ( pZahlung.getBetrag() * 2 ) * JAHRES_FAKTOR_12_MONATE;
        }
        else if ( pZahlung.istRhythmusEinmalig() )
        {
          betrag_monatliche_zahlung = pZahlung.getBetrag() * JAHRES_FAKTOR_12_MONATE;
        }
      }
    }

    return betrag_monatliche_zahlung * pAnzahlMonate;
  }
}
