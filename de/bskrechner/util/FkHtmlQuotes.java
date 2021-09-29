package de.bskrechner.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class FkHtmlQuotes
{
  private static final char   ACUTE_ACCENT_CHAR                             = 0x00b4;

  private static final String ACUTE_ACCENT_REPLACE                          = "&acute;";

  private static final String ACUTE_ACCENT_BEZ                              = "acute accent";

  private static final char   RIGHT_FLOOR_CHAR                              = 0x230b;

  private static final String RIGHT_FLOOR_REPLACE                           = "&rfloor;";

  private static final String RIGHT_FLOOR_BEZ                               = "RIGHT FLOOR";

  private static final char   GREEK_THETA_SYMBOL_CHAR                       = 0x03d1;

  private static final String GREEK_THETA_SYMBOL_REPLACE                    = "&thetasym;";

  private static final String GREEK_THETA_SYMBOL_BEZ                        = "GREEK THETA SYMBOL";

  private static final char   UPPERCASE_OCIRCUMFLEX_ACCENT_CHAR             = 0x00d4;

  private static final String UPPERCASE_OCIRCUMFLEX_ACCENT_REPLACE          = "&Ocirc;";

  private static final String UPPERCASE_OCIRCUMFLEX_ACCENT_BEZ              = "uppercase O, circumflex accent";

  private static final char   UPPERCASE_OSLASH_CHAR                         = 0x00d8;

  private static final String UPPERCASE_OSLASH_REPLACE                      = "&Oslash;";

  private static final String UPPERCASE_OSLASH_BEZ                          = "uppercase O, slash";

  private static final char   LOWERCASE_ARING_CHAR                          = 0x00e5;

  private static final String LOWERCASE_ARING_REPLACE                       = "&aring;";

  private static final String LOWERCASE_ARING_BEZ                           = "lowercase a, ring";

  private static final char   LEFT_RIGHT_DOUBLE_ARROW_CHAR                  = 0x21d4;

  private static final String LEFT_RIGHT_DOUBLE_ARROW_REPLACE               = "&hArr;";

  private static final String LEFT_RIGHT_DOUBLE_ARROW_BEZ                   = "LEFT RIGHT DOUBLE ARROW";

  private static final char   GREEK_SMALL_LETTER_ZETA_CHAR                  = 0x03b6;

  private static final String GREEK_SMALL_LETTER_ZETA_REPLACE               = "&zeta;";

  private static final String GREEK_SMALL_LETTER_ZETA_BEZ                   = "GREEK SMALL LETTER ZETA";

  private static final char   GREEK_SMALL_LETTER_EPSILON_CHAR               = 0x03b5;

  private static final String GREEK_SMALL_LETTER_EPSILON_REPLACE            = "&epsilon;";

  private static final String GREEK_SMALL_LETTER_EPSILON_BEZ                = "GREEK SMALL LETTER EPSILON";

  private static final char   UPPERCASE_ICIRCUMFLEX_ACCENT_CHAR             = 0x00ce;

  private static final String UPPERCASE_ICIRCUMFLEX_ACCENT_REPLACE          = "&Icirc;";

  private static final String UPPERCASE_ICIRCUMFLEX_ACCENT_BEZ              = "uppercase I, circumflex accent";

  private static final char   GREEK_CAPITAL_LETTER_PI_CHAR                  = 0x03a0;

  private static final String GREEK_CAPITAL_LETTER_PI_REPLACE               = "&Pi;";

  private static final String GREEK_CAPITAL_LETTER_PI_BEZ                   = "GREEK CAPITAL LETTER PI";

  private static final char   ZERO_WIDTH_JOINER_CHAR                        = 0x200d;

  private static final String ZERO_WIDTH_JOINER_REPLACE                     = "&zwj;";

  private static final String ZERO_WIDTH_JOINER_BEZ                         = "ZERO WIDTH JOINER";

  private static final char   SUBSET_OF_OR_EQUAL_TO_CHAR                    = 0x2286;

  private static final String SUBSET_OF_OR_EQUAL_TO_REPLACE                 = "&sube;";

  private static final String SUBSET_OF_OR_EQUAL_TO_BEZ                     = "SUBSET OF OR EQUAL TO";

  private static final char   LOWERCASE_ATILDE_CHAR                         = 0x00e3;

  private static final String LOWERCASE_ATILDE_REPLACE                      = "&atilde;";

  private static final String LOWERCASE_ATILDE_BEZ                          = "lowercase a, tilde";

  private static final char   INTEGRAL_CHAR                                 = 0x222b;

  private static final String INTEGRAL_REPLACE                              = "&int;";

  private static final String INTEGRAL_BEZ                                  = "INTEGRAL";

  private static final char   GREEK_SMALL_LETTER_OMEGA_CHAR                 = 0x03c9;

  private static final String GREEK_SMALL_LETTER_OMEGA_REPLACE              = "&omega;";

  private static final String GREEK_SMALL_LETTER_OMEGA_BEZ                  = "GREEK SMALL LETTER OMEGA";

  private static final char   COPYRIGHT_CHAR                                = 0x00a9;

  private static final String COPYRIGHT_REPLACE                             = "&copy;";

  private static final String COPYRIGHT_BEZ                                 = "copyright";

  private static final char   THIN_SPACE_CHAR                               = 0x2009;

  private static final String THIN_SPACE_REPLACE                            = "&thinsp;";

  private static final String THIN_SPACE_BEZ                                = "THIN SPACE";

  private static final char   MACRON_CHAR                                   = 0x00af;

  private static final String MACRON_REPLACE                                = "&macr;";

  private static final String MACRON_BEZ                                    = "MACRON";

  private static final char   UPWARD_ARROW_CHAR                             = 0x2191;

  private static final String UPWARD_ARROW_REPLACE                          = "&uarr;";

  private static final String UPWARD_ARROW_BEZ                              = "upward arrow";

  private static final char   GREEK_CAPITAL_LETTER_CHI_CHAR                 = 0x03a7;

  private static final String GREEK_CAPITAL_LETTER_CHI_REPLACE              = "&Chi;";

  private static final String GREEK_CAPITAL_LETTER_CHI_BEZ                  = "GREEK CAPITAL LETTER CHI";

  private static final char   UPPERCASE_OTILDE_CHAR                         = 0x00d5;

  private static final String UPPERCASE_OTILDE_REPLACE                      = "&Otilde;";

  private static final String UPPERCASE_OTILDE_BEZ                          = "uppercase O, tilde";

  private static final char   GREEK_PI_SYMBOL_CHAR                          = 0x03d6;

  private static final String GREEK_PI_SYMBOL_REPLACE                       = "&piv;";

  private static final String GREEK_PI_SYMBOL_BEZ                           = "GREEK PI SYMBOL";

  private static final char   LOWERCASE_EACUTE_ACCENT_CHAR                  = 0x00e9;

  private static final String LOWERCASE_EACUTE_ACCENT_REPLACE               = "&eacute;";

  private static final String LOWERCASE_EACUTE_ACCENT_BEZ                   = "lowercase e, acute accent";

  private static final char   UNION_CHAR                                    = 0x222a;

  private static final String UNION_REPLACE                                 = "&cup;";

  private static final String UNION_BEZ                                     = "UNION";

  private static final char   MODIFIER_LETTER_CIRCUMFLEX_ACCENT_CHAR        = 0x02c6;

  private static final String MODIFIER_LETTER_CIRCUMFLEX_ACCENT_REPLACE     = "&circ;";

  private static final String MODIFIER_LETTER_CIRCUMFLEX_ACCENT_BEZ         = "MODIFIER LETTER CIRCUMFLEX ACCENT";

  private static final char   LOWERCASE_ECIRCUMFLEX_ACCENT_CHAR             = 0x00ea;

  private static final String LOWERCASE_ECIRCUMFLEX_ACCENT_REPLACE          = "&ecirc;";

  private static final String LOWERCASE_ECIRCUMFLEX_ACCENT_BEZ              = "lowercase e, circumflex accent";

  private static final char   GREEK_CAPITAL_LETTER_SIGMA_CHAR               = 0x03a3;

  private static final String GREEK_CAPITAL_LETTER_SIGMA_REPLACE            = "&Sigma;";

  private static final String GREEK_CAPITAL_LETTER_SIGMA_BEZ                = "GREEK CAPITAL LETTER SIGMA";

  private static final char   GREEK_CAPITAL_LETTER_RHO_CHAR                 = 0x03a1;

  private static final String GREEK_CAPITAL_LETTER_RHO_REPLACE              = "&Rho;";

  private static final String GREEK_CAPITAL_LETTER_RHO_BEZ                  = "GREEK CAPITAL LETTER RHO";

  private static final char   UPPERCASE_IACUTE_ACCENT_CHAR                  = 0x00cd;

  private static final String UPPERCASE_IACUTE_ACCENT_REPLACE               = "&Iacute;";

  private static final String UPPERCASE_IACUTE_ACCENT_BEZ                   = "uppercase I, acute accent";

  private static final char   GREEK_CAPITAL_LETTER_IOTA_CHAR                = 0x0399;

  private static final String GREEK_CAPITAL_LETTER_IOTA_REPLACE             = "&Iota;";

  private static final String GREEK_CAPITAL_LETTER_IOTA_BEZ                 = "GREEK CAPITAL LETTER IOTA";

  private static final char   LOWERCASE_OSLASH_CHAR                         = 0x00f8;

  private static final String LOWERCASE_OSLASH_REPLACE                      = "&oslash;";

  private static final String LOWERCASE_OSLASH_BEZ                          = "lowercase o, slash";

  private static final char   SLASH_CHAR                                    = 0x2044;

  private static final String SLASH_REPLACE                                 = "&frasl;";

  private static final String SLASH_BEZ                                     = "slash";

  private static final char   DOUBLE_LOW_9_QUOTE_CHAR                       = 0x201e;

  private static final String DOUBLE_LOW_9_QUOTE_REPLACE                    = "&bdquo;";

  private static final String DOUBLE_LOW_9_QUOTE_BEZ                        = "double low-9 quote";

  private static final char   INFINITY_CHAR                                 = 0x221e;

  private static final String INFINITY_REPLACE                              = "&infin;";

  private static final String INFINITY_BEZ                                  = "INFINITY";

  private static final char   UPPERCASE_UUMLAUT_CHAR                        = 0x00dc;

  private static final String UPPERCASE_UUMLAUT_REPLACE                     = "&Uuml;";

  private static final String UPPERCASE_UUMLAUT_BEZ                         = "uppercase U, umlaut";

  private static final char   BROKEN_BAR_CHAR                               = 0x00a6;

  private static final String BROKEN_BAR_REPLACE                            = "&brvbar;";

  private static final String BROKEN_BAR_BEZ                                = "BROKEN BAR";

  private static final char   BLACK_SPADE_SUIT_CHAR                         = 0x2660;

  private static final String BLACK_SPADE_SUIT_REPLACE                      = "&spades;";

  private static final String BLACK_SPADE_SUIT_BEZ                          = "black spade suit";

  private static final char   ALEF_SYMBOL_CHAR                              = 0x2135;

  private static final String ALEF_SYMBOL_REPLACE                           = "&alefsym;";

  private static final String ALEF_SYMBOL_BEZ                               = "ALEF SYMBOL";

  private static final char   TILDE_OPERATOR_CHAR                           = 0x223c;

  private static final String TILDE_OPERATOR_REPLACE                        = "&sim;";

  private static final String TILDE_OPERATOR_BEZ                            = "TILDE OPERATOR";

  private static final char   DOUBLE_DAGGER_CHAR                            = 0x2021;

  private static final String DOUBLE_DAGGER_REPLACE                         = "&Dagger;";

  private static final String DOUBLE_DAGGER_BEZ                             = "double dagger";

  private static final char   MIDDLE_DOT_CHAR                               = 0x00b7;

  private static final String MIDDLE_DOT_REPLACE                            = "&middot;";

  private static final String MIDDLE_DOT_BEZ                                = "middle dot";

  private static final char   ANGLE_CHAR                                    = 0x2220;

  private static final String ANGLE_REPLACE                                 = "&ang;";

  private static final String ANGLE_BEZ                                     = "ANGLE";

  private static final char   LOWERCASE_EGRAVE_ACCENT_CHAR                  = 0x00e8;

  private static final String LOWERCASE_EGRAVE_ACCENT_REPLACE               = "&egrave;";

  private static final String LOWERCASE_EGRAVE_ACCENT_BEZ                   = "lowercase e, grave accent";

  private static final char   GREATER_THAN_SIGN_CHAR                        = 0x003e;

  private static final String GREATER_THAN_SIGN_REPLACE                     = "&gt;";

  private static final String GREATER_THAN_SIGN_BEZ                         = "greater-than sign";

  private static final char   LOWERCASE_THORN_ICELANDIC_CHAR                = 0x00fe;

  private static final String LOWERCASE_THORN_ICELANDIC_REPLACE             = "&thorn;";

  private static final String LOWERCASE_THORN_ICELANDIC_BEZ                 = "lowercase thorn, Icelandic";

  private static final char   OVERLINE_SPACING_OVERSCORE_CHAR               = 0x203e;

  private static final String OVERLINE_SPACING_OVERSCORE_REPLACE            = "&oline;";

  private static final String OVERLINE_SPACING_OVERSCORE_BEZ                = "overline, = spacing overscore";

  private static final char   UPPERCASE_IGRAVE_ACCENT_CHAR                  = 0x00cc;

  private static final String UPPERCASE_IGRAVE_ACCENT_REPLACE               = "&Igrave;";

  private static final String UPPERCASE_IGRAVE_ACCENT_BEZ                   = "uppercase I, grave accent";

  private static final char   DOT_OPERATOR_CHAR                             = 0x22c5;

  private static final String DOT_OPERATOR_REPLACE                          = "&sdot;";

  private static final String DOT_OPERATOR_BEZ                              = "DOT OPERATOR";

  private static final char   GREEK_CAPITAL_LETTER_ALPHA_CHAR               = 0x0391;

  private static final String GREEK_CAPITAL_LETTER_ALPHA_REPLACE            = "&Alpha;";

  private static final String GREEK_CAPITAL_LETTER_ALPHA_BEZ                = "GREEK CAPITAL LETTER ALPHA";

  private static final char   GREEK_SMALL_LETTER_THETA_CHAR                 = 0x03b8;

  private static final String GREEK_SMALL_LETTER_THETA_REPLACE              = "&theta;";

  private static final String GREEK_SMALL_LETTER_THETA_BEZ                  = "GREEK SMALL LETTER THETA";

  private static final char   GENERAL_CURRENCY_SIGN_CHAR                    = 0x00a4;

  private static final String GENERAL_CURRENCY_SIGN_REPLACE                 = "&curren;";

  private static final String GENERAL_CURRENCY_SIGN_BEZ                     = "general currency sign";

  private static final char   DOWNWARD_ARROW_CHAR                           = 0x2193;

  private static final String DOWNWARD_ARROW_REPLACE                        = "&darr;";

  private static final String DOWNWARD_ARROW_BEZ                            = "downward arrow";

  private static final char   UPPERCASE_AACUTE_ACCENT_CHAR                  = 0x00c1;

  private static final String UPPERCASE_AACUTE_ACCENT_REPLACE               = "&Aacute;";

  private static final String UPPERCASE_AACUTE_ACCENT_BEZ                   = "uppercase A, acute accent";

  private static final char   PLUS_OR_MINUS_CHAR                            = 0x00b1;

  private static final String PLUS_OR_MINUS_REPLACE                         = "&plusmn;";

  private static final String PLUS_OR_MINUS_BEZ                             = "plus or minus";

  private static final char   LOZENGE_CHAR                                  = 0x25ca;

  private static final String LOZENGE_REPLACE                               = "&loz;";

  private static final String LOZENGE_BEZ                                   = "LOZENGE";

  private static final char   LOWERCASE_OTILDE_CHAR                         = 0x00f5;

  private static final String LOWERCASE_OTILDE_REPLACE                      = "&otilde;";

  private static final String LOWERCASE_OTILDE_BEZ                          = "lowercase o, tilde";

  private static final char   NARY_PRODUCT_CHAR                             = 0x220f;

  private static final String NARY_PRODUCT_REPLACE                          = "&prod;";

  private static final String NARY_PRODUCT_BEZ                              = "N-ARY PRODUCT";

  private static final char   UPPERCASE_ACIRCUMFLEX_ACCENT_CHAR             = 0x00c2;

  private static final String UPPERCASE_ACIRCUMFLEX_ACCENT_REPLACE          = "&Acirc;";

  private static final String UPPERCASE_ACIRCUMFLEX_ACCENT_BEZ              = "uppercase A, circumflex accent";

  private static final char   INVERTED_EXCLAMATION_CHAR                     = 0x00a1;

  private static final String INVERTED_EXCLAMATION_REPLACE                  = "&iexcl;";

  private static final String INVERTED_EXCLAMATION_BEZ                      = "inverted exclamation";

  private static final char   LOWERCASE_IACUTE_ACCENT_CHAR                  = 0x00ed;

  private static final String LOWERCASE_IACUTE_ACCENT_REPLACE               = "&iacute;";

  private static final String LOWERCASE_IACUTE_ACCENT_BEZ                   = "lowercase i, acute accent";

  private static final char   LATIN_SMALL_LIGATURE_OE_CHAR                  = 0x0153;

  private static final String LATIN_SMALL_LIGATURE_OE_REPLACE               = "&oelig;";

  private static final String LATIN_SMALL_LIGATURE_OE_BEZ                   = "LATIN SMALL LIGATURE OE";

  private static final char   GREEK_SMALL_LETTER_XI_CHAR                    = 0x03be;

  private static final String GREEK_SMALL_LETTER_XI_REPLACE                 = "&xi;";

  private static final String GREEK_SMALL_LETTER_XI_BEZ                     = "GREEK SMALL LETTER XI";

  private static final char   APPROXIMATELY_EQUAL_TO_CHAR                   = 0x2245;

  private static final String APPROXIMATELY_EQUAL_TO_REPLACE                = "&cong;";

  private static final String APPROXIMATELY_EQUAL_TO_BEZ                    = "APPROXIMATELY EQUAL TO";

  private static final char   RIGHT_TO_LEFT_MARK_CHAR                       = 0x200f;

  private static final String RIGHT_TO_LEFT_MARK_REPLACE                    = "&rlm;";

  private static final String RIGHT_TO_LEFT_MARK_BEZ                        = "RIGHT-TO-LEFT MARK";

  private static final char   DAGGER_CHAR                                   = 0x2020;

  private static final String DAGGER_REPLACE                                = "&dagger;";

  private static final String DAGGER_BEZ                                    = "dagger";

  private static final char   LOWERCASE_IUMLAUT_CHAR                        = 0x00ef;

  private static final String LOWERCASE_IUMLAUT_REPLACE                     = "&iuml;";

  private static final String LOWERCASE_IUMLAUT_BEZ                         = "lowercase i, umlaut";

  private static final char   SQUARE_ROOT_CHAR                              = 0x221a;

  private static final String SQUARE_ROOT_REPLACE                           = "&radic;";

  private static final String SQUARE_ROOT_BEZ                               = "SQUARE ROOT";

  private static final char   UPPERCASE_THORN_ICELANDIC_CHAR                = 0x00de;

  private static final String UPPERCASE_THORN_ICELANDIC_REPLACE             = "&THORN;";

  private static final String UPPERCASE_THORN_ICELANDIC_BEZ                 = "uppercase THORN, Icelandic";

  private static final char   UPPERCASE_AGRAVE_ACCENT_CHAR                  = 0x00c0;

  private static final String UPPERCASE_AGRAVE_ACCENT_REPLACE               = "&Agrave;";

  private static final String UPPERCASE_AGRAVE_ACCENT_BEZ                   = "uppercase A, grave accent";

  private static final char   PER_MILL_SIGN_CHAR                            = 0x2030;

  private static final String PER_MILL_SIGN_REPLACE                         = "&permil;";

  private static final String PER_MILL_SIGN_BEZ                             = "per mill sign";

  private static final char   UP_TACK_CHAR                                  = 0x22a5;

  private static final String UP_TACK_REPLACE                               = "&perp;";

  private static final String UP_TACK_BEZ                                   = "UP TACK";

  private static final char   MULTIPLICATION_SIGN_CHAR                      = 0x00d7;

  private static final String MULTIPLICATION_SIGN_REPLACE                   = "&times;";

  private static final String MULTIPLICATION_SIGN_BEZ                       = "multiplication sign";

  private static final char   BULLET_CHAR                                   = 0x2022;

  private static final String BULLET_REPLACE                                = "&bull;";

  private static final String BULLET_BEZ                                    = "BULLET";

  private static final char   LOWERCASE_IGRAVE_ACCENT_CHAR                  = 0x00ec;

  private static final String LOWERCASE_IGRAVE_ACCENT_REPLACE               = "&igrave;";

  private static final String LOWERCASE_IGRAVE_ACCENT_BEZ                   = "lowercase i, grave accent";

  private static final char   NABLA_CHAR                                    = 0x2207;

  private static final String NABLA_REPLACE                                 = "&nabla;";

  private static final String NABLA_BEZ                                     = "NABLA";

  private static final char   GREEK_SMALL_LETTER_NU_CHAR                    = 0x03bd;

  private static final String GREEK_SMALL_LETTER_NU_REPLACE                 = "&nu;";

  private static final String GREEK_SMALL_LETTER_NU_BEZ                     = "GREEK SMALL LETTER NU";

  private static final char   SECTION_SIGN_CHAR                             = 0x00a7;

  private static final String SECTION_SIGN_REPLACE                          = "&sect;";

  private static final String SECTION_SIGN_BEZ                              = "section sign";

  private static final char   LATIN_SMALL_LETTER_FWITH_HOOK_CHAR            = 0x0192;

  private static final String LATIN_SMALL_LETTER_FWITH_HOOK_REPLACE         = "&fnof;";

  private static final String LATIN_SMALL_LETTER_FWITH_HOOK_BEZ             = "LATIN SMALL LETTER F WITH HOOK";

  private static final char   DOUBLE_QUOTATION_MARK_CHAR                    = 0x0022;

  private static final String DOUBLE_QUOTATION_MARK_REPLACE                 = "&quot;";

  private static final String DOUBLE_QUOTATION_MARK_BEZ                     = "double quotation mark";

  private static final char   LOWERCASE_AACUTE_ACCENT_CHAR                  = 0x00e1;

  private static final String LOWERCASE_AACUTE_ACCENT_REPLACE               = "&aacute;";

  private static final String LOWERCASE_AACUTE_ACCENT_BEZ                   = "lowercase a, acute accent";

  private static final char   REGISTERED_TRADEMARK_CHAR                     = 0x00ae;

  private static final String REGISTERED_TRADEMARK_REPLACE                  = "&reg;";

  private static final String REGISTERED_TRADEMARK_BEZ                      = "registered trademark";

  private static final char   MINUS_SIGN_CHAR                               = 0x2212;

  private static final String MINUS_SIGN_REPLACE                            = "&minus;";

  private static final String MINUS_SIGN_BEZ                                = "MINUS SIGN";

  private static final char   APOSTROPHE_CHAR                               = 0x0027;

  private static final String APOSTROPHE_REPLACE                            = "&apos;";

  private static final String APOSTROPHE_BEZ                                = "APOSTROPHE";

  private static final char   CIRCLED_PLUS_CHAR                             = 0x2295;

  private static final String CIRCLED_PLUS_REPLACE                          = "&oplus;";

  private static final String CIRCLED_PLUS_BEZ                              = "CIRCLED PLUS";

  private static final char   LESS_THAN_SIGN_CHAR                           = 0x003c;

  private static final String LESS_THAN_SIGN_REPLACE                        = "&lt;";

  private static final String LESS_THAN_SIGN_BEZ                            = "less-than sign";

  private static final char   GREEK_CAPITAL_LETTER_DELTA_CHAR               = 0x0394;

  private static final String GREEK_CAPITAL_LETTER_DELTA_REPLACE            = "&Delta;";

  private static final String GREEK_CAPITAL_LETTER_DELTA_BEZ                = "GREEK CAPITAL LETTER DELTA";

  private static final char   LEFTWARDS_DOUBLE_ARROW_CHAR                   = 0x21d0;

  private static final String LEFTWARDS_DOUBLE_ARROW_REPLACE                = "&lArr;";

  private static final String LEFTWARDS_DOUBLE_ARROW_BEZ                    = "LEFTWARDS DOUBLE ARROW";

  private static final char   RIGHT_ANGLE_QUOTE_CHAR                        = 0x00bb;

  private static final String RIGHT_ANGLE_QUOTE_REPLACE                     = "&raquo;";

  private static final String RIGHT_ANGLE_QUOTE_BEZ                         = "right angle quote";

  private static final char   GREEK_CAPITAL_LETTER_ETA_CHAR                 = 0x0397;

  private static final String GREEK_CAPITAL_LETTER_ETA_REPLACE              = "&Eta;";

  private static final String GREEK_CAPITAL_LETTER_ETA_BEZ                  = "GREEK CAPITAL LETTER ETA";

  private static final char   DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_CHAR    = 0x21b5;

  private static final String DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_REPLACE = "&crarr;";

  private static final String DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_BEZ     = "DOWNWARDS ARROW WITH CORNER LEFTWARDS";

  private static final char   LEFT_ANGLE_QUOTE_CHAR                         = 0x00ab;

  private static final String LEFT_ANGLE_QUOTE_REPLACE                      = "&laquo;";

  private static final String LEFT_ANGLE_QUOTE_BEZ                          = "left angle quote";

  private static final char   GREEK_CAPITAL_LETTER_PSI_CHAR                 = 0x03a8;

  private static final String GREEK_CAPITAL_LETTER_PSI_REPLACE              = "&Psi;";

  private static final String GREEK_CAPITAL_LETTER_PSI_BEZ                  = "GREEK CAPITAL LETTER PSI";

  private static final char   UPPERCASE_AUMLAUT_CHAR                        = 0x00c4;

  private static final String UPPERCASE_AUMLAUT_REPLACE                     = "&Auml;";

  private static final String UPPERCASE_AUMLAUT_BEZ                         = "uppercase A, umlaut";

  private static final char   LEFT_TO_RIGHT_MARK_CHAR                       = 0x200e;

  private static final String LEFT_TO_RIGHT_MARK_REPLACE                    = "&lrm;";

  private static final String LEFT_TO_RIGHT_MARK_BEZ                        = "LEFT-TO-RIGHT MARK";

  private static final char   UPPERCASE_OACUTE_ACCENT_CHAR                  = 0x00d3;

  private static final String UPPERCASE_OACUTE_ACCENT_REPLACE               = "&Oacute;";

  private static final String UPPERCASE_OACUTE_ACCENT_BEZ                   = "uppercase O, acute accent";

  private static final char   LOWERCASE_AGRAVE_ACCENT_CHAR                  = 0x00e0;

  private static final String LOWERCASE_AGRAVE_ACCENT_REPLACE               = "&agrave;";

  private static final String LOWERCASE_AGRAVE_ACCENT_BEZ                   = "lowercase a, grave accent";

  private static final char   THERE_EXISTS_CHAR                             = 0x2203;

  private static final String THERE_EXISTS_REPLACE                          = "&exist;";

  private static final String THERE_EXISTS_BEZ                              = "THERE EXISTS";

  private static final char   UPPERCASE_EGRAVE_ACCENT_CHAR                  = 0x00c8;

  private static final String UPPERCASE_EGRAVE_ACCENT_REPLACE               = "&Egrave;";

  private static final String UPPERCASE_EGRAVE_ACCENT_BEZ                   = "uppercase E, grave accent";

  private static final char   SUBSET_OF_CHAR                                = 0x2282;

  private static final String SUBSET_OF_REPLACE                             = "&sub;";

  private static final String SUBSET_OF_BEZ                                 = "SUBSET OF";

  private static final char   GREEK_CAPITAL_LETTER_XI_CHAR                  = 0x039e;

  private static final String GREEK_CAPITAL_LETTER_XI_REPLACE               = "&Xi;";

  private static final String GREEK_CAPITAL_LETTER_XI_BEZ                   = "GREEK CAPITAL LETTER XI";

  private static final char   LOWERCASE_OUMLAUT_CHAR                        = 0x00f6;

  private static final String LOWERCASE_OUMLAUT_REPLACE                     = "&ouml;";

  private static final String LOWERCASE_OUMLAUT_BEZ                         = "lowercase o, umlaut";

  private static final char   EURO_SIGN_CHAR                                = 0x20ac;

  private static final String EURO_SIGN_REPLACE                             = "&euro;";

  private static final String EURO_SIGN_BEZ                                 = "EURO SIGN";

  private static final char   GREEK_CAPITAL_LETTER_UPSILON_CHAR             = 0x03a5;

  private static final String GREEK_CAPITAL_LETTER_UPSILON_REPLACE          = "&Upsilon;";

  private static final String GREEK_CAPITAL_LETTER_UPSILON_BEZ              = "GREEK CAPITAL LETTER UPSILON";

  private static final char   ELLIPSES_CHAR                                 = 0x2026;

  private static final String ELLIPSES_REPLACE                              = "&hellip;";

  private static final String ELLIPSES_BEZ                                  = "ellipses";

  private static final char   PRIME_CHAR                                    = 0x2032;

  private static final String PRIME_REPLACE                                 = "&prime;";

  private static final String PRIME_BEZ                                     = "PRIME";

  private static final char   UPPERCASE_CCEDILLA_CHAR                       = 0x00c7;

  private static final String UPPERCASE_CCEDILLA_REPLACE                    = "&Ccedil;";

  private static final String UPPERCASE_CCEDILLA_BEZ                        = "uppercase C, cedilla";

  private static final char   LATIN_CAPITAL_LIGATURE_OE_CHAR                = 0x0152;

  private static final String LATIN_CAPITAL_LIGATURE_OE_REPLACE             = "&OElig;";

  private static final String LATIN_CAPITAL_LIGATURE_OE_BEZ                 = "LATIN CAPITAL LIGATURE OE";

  private static final char   LATIN_CAPITAL_LETTER_SWITH_CARON_CHAR         = 0x0160;

  private static final String LATIN_CAPITAL_LETTER_SWITH_CARON_REPLACE      = "&Scaron;";

  private static final String LATIN_CAPITAL_LETTER_SWITH_CARON_BEZ          = "LATIN CAPITAL LETTER S WITH CARON";

  private static final char   SUPERSCRIPT_TWO_CHAR                          = 0x00b2;

  private static final String SUPERSCRIPT_TWO_REPLACE                       = "&sup2;";

  private static final String SUPERSCRIPT_TWO_BEZ                           = "superscript two";

  private static final char   NOT_EQUAL_TO_CHAR                             = 0x2260;

  private static final String NOT_EQUAL_TO_REPLACE                          = "&ne;";

  private static final String NOT_EQUAL_TO_BEZ                              = "NOT EQUAL TO";

  private static final char   UPPERCASE_OGRAVE_ACCENT_CHAR                  = 0x00d2;

  private static final String UPPERCASE_OGRAVE_ACCENT_REPLACE               = "&Ograve;";

  private static final String UPPERCASE_OGRAVE_ACCENT_BEZ                   = "uppercase O, grave accent";

  private static final char   SUPERSCRIPT_ONE_CHAR                          = 0x00b9;

  private static final String SUPERSCRIPT_ONE_REPLACE                       = "&sup1;";

  private static final String SUPERSCRIPT_ONE_BEZ                           = "superscript one";

  private static final char   SINGLE_LEFT_POINTING_ANGLE_QUOTE_CHAR         = 0x2039;

  private static final String SINGLE_LEFT_POINTING_ANGLE_QUOTE_REPLACE      = "&lsaquo;";

  private static final String SINGLE_LEFT_POINTING_ANGLE_QUOTE_BEZ          = "single left-pointing angle quote";

  private static final char   BLACK_DIAMOND_SUIT_CHAR                       = 0x2666;

  private static final String BLACK_DIAMOND_SUIT_REPLACE                    = "&diams;";

  private static final String BLACK_DIAMOND_SUIT_BEZ                        = "black diamond suit";

  private static final char   SCRIPT_CAPITAL_P_CHAR                         = 0x2118;

  private static final String SCRIPT_CAPITAL_P_REPLACE                      = "&weierp;";

  private static final String SCRIPT_CAPITAL_P_BEZ                          = "SCRIPT CAPITAL P";

  private static final char   GREEK_CAPITAL_LETTER_NU_CHAR                  = 0x039d;

  private static final String GREEK_CAPITAL_LETTER_NU_REPLACE               = "&Nu;";

  private static final String GREEK_CAPITAL_LETTER_NU_BEZ                   = "GREEK CAPITAL LETTER NU";

  private static final char   TRADEMARK_SIGN_CHAR                           = 0x2122;

  private static final String TRADEMARK_SIGN_REPLACE                        = "&trade;";

  private static final String TRADEMARK_SIGN_BEZ                            = "trademark sign";

  private static final char   LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_CHAR     = 0x0178;

  private static final String LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_REPLACE  = "&Yuml;";

  private static final String LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_BEZ      = "LATIN CAPITAL LETTER Y WITH DIAERESIS";

  private static final char   NARY_SUMMATION_CHAR                           = 0x2211;

  private static final String NARY_SUMMATION_REPLACE                        = "&sum;";

  private static final String NARY_SUMMATION_BEZ                            = "N-ARY SUMMATION";

  private static final char   GREEK_CAPITAL_LETTER_GAMMA_CHAR               = 0x0393;

  private static final String GREEK_CAPITAL_LETTER_GAMMA_REPLACE            = "&Gamma;";

  private static final String GREEK_CAPITAL_LETTER_GAMMA_BEZ                = "GREEK CAPITAL LETTER GAMMA";

  private static final char   NOT_SIGN_CHAR                                 = 0x00ac;

  private static final String NOT_SIGN_REPLACE                              = "&not;";

  private static final String NOT_SIGN_BEZ                                  = "not sign";

  private static final char   MASCULINE_ORDINAL_CHAR                        = 0x00ba;

  private static final String MASCULINE_ORDINAL_REPLACE                     = "&ordm;";

  private static final String MASCULINE_ORDINAL_BEZ                         = "masculine ordinal";

  private static final char   GREEK_CAPITAL_LETTER_KAPPA_CHAR               = 0x039a;

  private static final String GREEK_CAPITAL_LETTER_KAPPA_REPLACE            = "&Kappa;";

  private static final String GREEK_CAPITAL_LETTER_KAPPA_BEZ                = "GREEK CAPITAL LETTER KAPPA";

  private static final char   LOWERCASE_OACUTE_ACCENT_CHAR                  = 0x00f3;

  private static final String LOWERCASE_OACUTE_ACCENT_REPLACE               = "&oacute;";

  private static final String LOWERCASE_OACUTE_ACCENT_BEZ                   = "lowercase o, acute accent";

  private static final char   BLACK_LETTER_CAPITAL_R_CHAR                   = 0x211c;

  private static final String BLACK_LETTER_CAPITAL_R_REPLACE                = "&real;";

  private static final String BLACK_LETTER_CAPITAL_R_BEZ                    = "BLACK-LETTER CAPITAL R";

  private static final char   NOT_AN_ELEMENT_OF_CHAR                        = 0x2209;

  private static final String NOT_AN_ELEMENT_OF_REPLACE                     = "&notin;";

  private static final String NOT_AN_ELEMENT_OF_BEZ                         = "NOT AN ELEMENT OF";

  private static final char   RIGHTWARDS_DOUBLE_ARROW_CHAR                  = 0x21d2;

  private static final String RIGHTWARDS_DOUBLE_ARROW_REPLACE               = "&rArr;";

  private static final String RIGHTWARDS_DOUBLE_ARROW_BEZ                   = "RIGHTWARDS DOUBLE ARROW";

  private static final char   EM_DASH_CHAR                                  = 0x2014;

  private static final String EM_DASH_REPLACE                               = "&mdash;";

  private static final String EM_DASH_BEZ                                   = "em dash";

  private static final char   DEGREE_SIGN_CHAR                              = 0x00b0;

  private static final String DEGREE_SIGN_REPLACE                           = "&deg;";

  private static final String DEGREE_SIGN_BEZ                               = "degree sign";

  private static final char   DIVISION_SIGN_CHAR                            = 0x00f7;

  private static final String DIVISION_SIGN_REPLACE                         = "&divide;";

  private static final String DIVISION_SIGN_BEZ                             = "division sign";

  private static final char   GREEK_SMALL_LETTER_IOTA_CHAR                  = 0x03b9;

  private static final String GREEK_SMALL_LETTER_IOTA_REPLACE               = "&iota;";

  private static final String GREEK_SMALL_LETTER_IOTA_BEZ                   = "GREEK SMALL LETTER IOTA";

  private static final char   LOWERCASE_CCEDILLA_CHAR                       = 0x00e7;

  private static final String LOWERCASE_CCEDILLA_REPLACE                    = "&ccedil;";

  private static final String LOWERCASE_CCEDILLA_BEZ                        = "lowercase c, cedilla";

  private static final char   LEFT_RIGHT_ARROW_CHAR                         = 0x2194;

  private static final String LEFT_RIGHT_ARROW_REPLACE                      = "&harr;";

  private static final String LEFT_RIGHT_ARROW_BEZ                          = "LEFT RIGHT ARROW";

  private static final char   LATIN_SMALL_LETTER_SWITH_CARON_CHAR           = 0x0161;

  private static final String LATIN_SMALL_LETTER_SWITH_CARON_REPLACE        = "&scaron;";

  private static final String LATIN_SMALL_LETTER_SWITH_CARON_BEZ            = "LATIN SMALL LETTER S WITH CARON";

  private static final char   NONBREAKING_SPACE_CHAR                        = 0x00a0;

  private static final String NONBREAKING_SPACE_REPLACE                     = "&nbsp;";

  private static final String NONBREAKING_SPACE_BEZ                         = "nonbreaking space";

  private static final char   LOWERCASE_OGRAVE_ACCENT_CHAR                  = 0x00f2;

  private static final String LOWERCASE_OGRAVE_ACCENT_REPLACE               = "&ograve;";

  private static final String LOWERCASE_OGRAVE_ACCENT_BEZ                   = "lowercase o, grave accent";

  private static final char   BLACK_HEART_SUIT_CHAR                         = 0x2665;

  private static final String BLACK_HEART_SUIT_REPLACE                      = "&hearts;";

  private static final String BLACK_HEART_SUIT_BEZ                          = "black heart suit";

  private static final char   LOWERCASE_UUMLAUT_CHAR                        = 0x00fc;

  private static final String LOWERCASE_UUMLAUT_REPLACE                     = "&uuml;";

  private static final String LOWERCASE_UUMLAUT_BEZ                         = "lowercase u, umlaut";

  private static final char   CEDILLA_CHAR                                  = 0x00b8;

  private static final String CEDILLA_REPLACE                               = "&cedil;";

  private static final String CEDILLA_BEZ                                   = "cedilla";

  private static final char   LOWERCASE_UCIRCUMFLEX_ACCENT_CHAR             = 0x00fb;

  private static final String LOWERCASE_UCIRCUMFLEX_ACCENT_REPLACE          = "&ucirc;";

  private static final String LOWERCASE_UCIRCUMFLEX_ACCENT_BEZ              = "lowercase u, circumflex accent";

  private static final char   LOGICAL_OR_CHAR                               = 0x2228;

  private static final String LOGICAL_OR_REPLACE                            = "&or;";

  private static final String LOGICAL_OR_BEZ                                = "LOGICAL OR";

  private static final char   LEFT_FLOOR_CHAR                               = 0x230a;

  private static final String LEFT_FLOOR_REPLACE                            = "&lfloor;";

  private static final String LEFT_FLOOR_BEZ                                = "LEFT FLOOR";

  private static final char   INVERTED_QUESTION_MARK_CHAR                   = 0x00bf;

  private static final String INVERTED_QUESTION_MARK_REPLACE                = "&iquest;";

  private static final String INVERTED_QUESTION_MARK_BEZ                    = "inverted question mark";

  private static final char   LOWERCASE_OCIRCUMFLEX_ACCENT_CHAR             = 0x00f4;

  private static final String LOWERCASE_OCIRCUMFLEX_ACCENT_REPLACE          = "&ocirc;";

  private static final String LOWERCASE_OCIRCUMFLEX_ACCENT_BEZ              = "lowercase o, circumflex accent";

  private static final char   INTERSECTION_CHAR                             = 0x2229;

  private static final String INTERSECTION_REPLACE                          = "&cap;";

  private static final String INTERSECTION_BEZ                              = "INTERSECTION";

  private static final char   LOWERCASE_SHARPS_GERMAN_CHAR                  = 0x00df;

  private static final String LOWERCASE_SHARPS_GERMAN_REPLACE               = "&szlig;";

  private static final String LOWERCASE_SHARPS_GERMAN_BEZ                   = "lowercase sharps, German";

  private static final char   RIGHT_DOUBLE_QUOTE_CHAR                       = 0x201d;

  private static final String RIGHT_DOUBLE_QUOTE_REPLACE                    = "&rdquo;";

  private static final String RIGHT_DOUBLE_QUOTE_BEZ                        = "right double quote";

  private static final char   LOWERCASE_ICIRCUMFLEX_ACCENT_CHAR             = 0x00ee;

  private static final String LOWERCASE_ICIRCUMFLEX_ACCENT_REPLACE          = "&icirc;";

  private static final String LOWERCASE_ICIRCUMFLEX_ACCENT_BEZ              = "lowercase i, circumflex accent";

  private static final char   CENT_SIGN_CHAR                                = 0x00a2;

  private static final String CENT_SIGN_REPLACE                             = "&cent;";

  private static final String CENT_SIGN_BEZ                                 = "cent sign";

  private static final char   GREEK_SMALL_LETTER_CHI_CHAR                   = 0x03c7;

  private static final String GREEK_SMALL_LETTER_CHI_REPLACE                = "&chi;";

  private static final String GREEK_SMALL_LETTER_CHI_BEZ                    = "GREEK SMALL LETTER CHI";

  private static final char   LEFT_DOUBLE_QUOTE_CHAR                        = 0x201c;

  private static final String LEFT_DOUBLE_QUOTE_REPLACE                     = "&ldquo;";

  private static final String LEFT_DOUBLE_QUOTE_BEZ                         = "left double quote";

  private static final char   PROPORTIONAL_TO_CHAR                          = 0x221d;

  private static final String PROPORTIONAL_TO_REPLACE                       = "&prop;";

  private static final String PROPORTIONAL_TO_BEZ                           = "PROPORTIONAL TO";

  private static final char   GREEK_CAPITAL_LETTER_PHI_CHAR                 = 0x03a6;

  private static final String GREEK_CAPITAL_LETTER_PHI_REPLACE              = "&Phi;";

  private static final String GREEK_CAPITAL_LETTER_PHI_BEZ                  = "GREEK CAPITAL LETTER PHI";

  private static final char   UPPERCASE_ARING_CHAR                          = 0x00c5;

  private static final String UPPERCASE_ARING_REPLACE                       = "&Aring;";

  private static final String UPPERCASE_ARING_BEZ                           = "uppercase A, ring";

  private static final char   GREEK_CAPITAL_LETTER_TAU_CHAR                 = 0x03a4;

  private static final String GREEK_CAPITAL_LETTER_TAU_REPLACE              = "&Tau;";

  private static final String GREEK_CAPITAL_LETTER_TAU_BEZ                  = "GREEK CAPITAL LETTER TAU";

  private static final char   GREEK_SMALL_LETTER_RHO_CHAR                   = 0x03c1;

  private static final String GREEK_SMALL_LETTER_RHO_REPLACE                = "&rho;";

  private static final String GREEK_SMALL_LETTER_RHO_BEZ                    = "GREEK SMALL LETTER RHO";

  private static final char   UPPERCASE_UACUTE_ACCENT_CHAR                  = 0x00da;

  private static final String UPPERCASE_UACUTE_ACCENT_REPLACE               = "&Uacute;";

  private static final String UPPERCASE_UACUTE_ACCENT_BEZ                   = "uppercase U, acute accent";

  private static final char   GREATER_THAN_OR_EQUAL_TO_CHAR                 = 0x2265;

  private static final String GREATER_THAN_OR_EQUAL_TO_REPLACE              = "&ge;";

  private static final String GREATER_THAN_OR_EQUAL_TO_BEZ                  = "GREATER-THAN OR EQUAL TO";

  private static final char   EM_SPACE_CHAR                                 = 0x2003;

  private static final String EM_SPACE_REPLACE                              = "&emsp;";

  private static final String EM_SPACE_BEZ                                  = "EM SPACE";

  private static final char   ALMOST_EQUAL_TO_CHAR                          = 0x2248;

  private static final String ALMOST_EQUAL_TO_REPLACE                       = "&asymp;";

  private static final String ALMOST_EQUAL_TO_BEZ                           = "ALMOST EQUAL TO";

  private static final char   DIAERESIS_CHAR                                = 0x00a8;

  private static final String DIAERESIS_REPLACE                             = "&uml;";

  private static final String DIAERESIS_BEZ                                 = "DIAERESIS";

  private static final char   LOWERCASE_ETH_ICELANDIC_CHAR                  = 0x00f0;

  private static final String LOWERCASE_ETH_ICELANDIC_REPLACE               = "&eth;";

  private static final String LOWERCASE_ETH_ICELANDIC_BEZ                   = "lowercase eth, Icelandic";

  private static final char   GREEK_CAPITAL_LETTER_OMEGA_CHAR               = 0x03a9;

  private static final String GREEK_CAPITAL_LETTER_OMEGA_REPLACE            = "&Omega;";

  private static final String GREEK_CAPITAL_LETTER_OMEGA_BEZ                = "GREEK CAPITAL LETTER OMEGA";

  private static final char   UPPERCASE_IUMLAUT_CHAR                        = 0x00cf;

  private static final String UPPERCASE_IUMLAUT_REPLACE                     = "&Iuml;";

  private static final String UPPERCASE_IUMLAUT_BEZ                         = "uppercase I, umlaut";

  private static final char   GREEK_CAPITAL_LETTER_BETA_CHAR                = 0x0392;

  private static final String GREEK_CAPITAL_LETTER_BETA_REPLACE             = "&Beta;";

  private static final String GREEK_CAPITAL_LETTER_BETA_BEZ                 = "GREEK CAPITAL LETTER BETA";

  private static final char   UPPERCASE_EUMLAUT_CHAR                        = 0x00cb;

  private static final String UPPERCASE_EUMLAUT_REPLACE                     = "&Euml;";

  private static final String UPPERCASE_EUMLAUT_BEZ                         = "uppercase E, umlaut";

  private static final char   UPPERCASE_UGRAVE_ACCENT_CHAR                  = 0x00d9;

  private static final String UPPERCASE_UGRAVE_ACCENT_REPLACE               = "&Ugrave;";

  private static final String UPPERCASE_UGRAVE_ACCENT_BEZ                   = "uppercase U, grave accent";

  private static final char   BLACK_CLUB_SUIT_CHAR                          = 0x2663;

  private static final String BLACK_CLUB_SUIT_REPLACE                       = "&clubs;";

  private static final String BLACK_CLUB_SUIT_BEZ                           = "black club suit";

  private static final char   FEMININE_ORDINAL_CHAR                         = 0x00aa;

  private static final String FEMININE_ORDINAL_REPLACE                      = "&ordf;";

  private static final String FEMININE_ORDINAL_BEZ                          = "feminine ordinal";

  private static final char   UPPERCASE_ECIRCUMFLEX_ACCENT_CHAR             = 0x00ca;

  private static final String UPPERCASE_ECIRCUMFLEX_ACCENT_REPLACE          = "&Ecirc;";

  private static final String UPPERCASE_ECIRCUMFLEX_ACCENT_BEZ              = "uppercase E, circumflex accent";

  private static final char   THEREFORE_CHAR                                = 0x2234;

  private static final String THEREFORE_REPLACE                             = "&there4;";

  private static final String THEREFORE_BEZ                                 = "THEREFORE";

  private static final char   UPWARDS_DOUBLE_ARROW_CHAR                     = 0x21d1;

  private static final String UPWARDS_DOUBLE_ARROW_REPLACE                  = "&uArr;";

  private static final String UPWARDS_DOUBLE_ARROW_BEZ                      = "UPWARDS DOUBLE ARROW";

  private static final char   LOWERCASE_UACUTE_ACCENT_CHAR                  = 0x00fa;

  private static final String LOWERCASE_UACUTE_ACCENT_REPLACE               = "&uacute;";

  private static final String LOWERCASE_UACUTE_ACCENT_BEZ                   = "lowercase u, acute accent";

  private static final char   GREEK_SMALL_LETTER_ALPHA_CHAR                 = 0x03b1;

  private static final String GREEK_SMALL_LETTER_ALPHA_REPLACE              = "&alpha;";

  private static final String GREEK_SMALL_LETTER_ALPHA_BEZ                  = "GREEK SMALL LETTER ALPHA";

  private static final char   GREEK_CAPITAL_LETTER_LAMDA_CHAR               = 0x039b;

  private static final String GREEK_CAPITAL_LETTER_LAMDA_REPLACE            = "&Lambda;";

  private static final String GREEK_CAPITAL_LETTER_LAMDA_BEZ                = "GREEK CAPITAL LETTER LAMDA";

  private static final char   SINGLE_LOW_9_QUOTE_CHAR                       = 0x201a;

  private static final String SINGLE_LOW_9_QUOTE_REPLACE                    = "&sbquo;";

  private static final String SINGLE_LOW_9_QUOTE_BEZ                        = "single low-9 quote";

  private static final char   UPPERCASE_YACUTE_ACCENT_CHAR                  = 0x00dd;

  private static final String UPPERCASE_YACUTE_ACCENT_REPLACE               = "&Yacute;";

  private static final String UPPERCASE_YACUTE_ACCENT_BEZ                   = "uppercase Y, acute accent";

  private static final char   SUPERSCRIPT_THREE_CHAR                        = 0x00b3;

  private static final String SUPERSCRIPT_THREE_REPLACE                     = "&sup3;";

  private static final String SUPERSCRIPT_THREE_BEZ                         = "superscript three";

  private static final char   GREEK_SMALL_LETTER_FINAL_SIGMA_CHAR           = 0x03c2;

  private static final String GREEK_SMALL_LETTER_FINAL_SIGMA_REPLACE        = "&sigmaf;";

  private static final String GREEK_SMALL_LETTER_FINAL_SIGMA_BEZ            = "GREEK SMALL LETTER FINAL SIGMA";

  private static final char   CIRCLED_TIMES_CHAR                            = 0x2297;

  private static final String CIRCLED_TIMES_REPLACE                         = "&otimes;";

  private static final String CIRCLED_TIMES_BEZ                             = "CIRCLED TIMES";

  private static final char   SUPERSET_OF_CHAR                              = 0x2283;

  private static final String SUPERSET_OF_REPLACE                           = "&sup;";

  private static final String SUPERSET_OF_BEZ                               = "SUPERSET OF";

  private static final char   LOWERCASE_AUMLAUT_CHAR                        = 0x00e4;

  private static final String LOWERCASE_AUMLAUT_REPLACE                     = "&auml;";

  private static final String LOWERCASE_AUMLAUT_BEZ                         = "lowercase a, umlaut";

  private static final char   GREEK_CAPITAL_LETTER_ZETA_CHAR                = 0x0396;

  private static final String GREEK_CAPITAL_LETTER_ZETA_REPLACE             = "&Zeta;";

  private static final String GREEK_CAPITAL_LETTER_ZETA_BEZ                 = "GREEK CAPITAL LETTER ZETA";

  private static final char   LOWERCASE_ACIRCUMFLEX_ACCENT_CHAR             = 0x00e2;

  private static final String LOWERCASE_ACIRCUMFLEX_ACCENT_REPLACE          = "&acirc;";

  private static final String LOWERCASE_ACIRCUMFLEX_ACCENT_BEZ              = "lowercase a, circumflex accent";

  private static final char   LOWERCASE_AE_CHAR                             = 0x00e6;

  private static final String LOWERCASE_AE_REPLACE                          = "&aelig;";

  private static final String LOWERCASE_AE_BEZ                              = "lowercase ae";

  private static final char   GREEK_CAPITAL_LETTER_THETA_CHAR               = 0x0398;

  private static final String GREEK_CAPITAL_LETTER_THETA_REPLACE            = "&Theta;";

  private static final String GREEK_CAPITAL_LETTER_THETA_BEZ                = "GREEK CAPITAL LETTER THETA";

  private static final char   ONE_HALF_CHAR                                 = 0x00bd;

  private static final String ONE_HALF_REPLACE                              = "&frac12;";

  private static final String ONE_HALF_BEZ                                  = "one-half";

  private static final char   LESS_THAN_OR_EQUAL_TO_CHAR                    = 0x2264;

  private static final String LESS_THAN_OR_EQUAL_TO_REPLACE                 = "&le;";

  private static final String LESS_THAN_OR_EQUAL_TO_BEZ                     = "LESS-THAN OR EQUAL TO";

  private static final char   LOWERCASE_UGRAVE_ACCENT_CHAR                  = 0x00f9;

  private static final String LOWERCASE_UGRAVE_ACCENT_REPLACE               = "&ugrave;";

  private static final String LOWERCASE_UGRAVE_ACCENT_BEZ                   = "lowercase u, grave accent";

  private static final char   GREEK_SMALL_LETTER_UPSILON_CHAR               = 0x03c5;

  private static final String GREEK_SMALL_LETTER_UPSILON_REPLACE            = "&upsilon;";

  private static final String GREEK_SMALL_LETTER_UPSILON_BEZ                = "GREEK SMALL LETTER UPSILON";

  private static final char   FOR_ALL_CHAR                                  = 0x2200;

  private static final String FOR_ALL_REPLACE                               = "&forall;";

  private static final String FOR_ALL_BEZ                                   = "FOR ALL";

  private static final char   GREEK_CAPITAL_LETTER_OMICRON_CHAR             = 0x039f;

  private static final String GREEK_CAPITAL_LETTER_OMICRON_REPLACE          = "&Omicron;";

  private static final String GREEK_CAPITAL_LETTER_OMICRON_BEZ              = "GREEK CAPITAL LETTER OMICRON";

  private static final char   UPPERCASE_UCIRCUMFLEX_ACCENT_CHAR             = 0x00db;

  private static final String UPPERCASE_UCIRCUMFLEX_ACCENT_REPLACE          = "&Ucirc;";

  private static final String UPPERCASE_UCIRCUMFLEX_ACCENT_BEZ              = "uppercase U, circumflex accent";

  private static final char   EN_DASH_CHAR                                  = 0x2013;

  private static final String EN_DASH_REPLACE                               = "&ndash;";

  private static final String EN_DASH_BEZ                                   = "en dash";

  private static final char   AMPERSAND_CHAR                                = 0x0026;

  private static final String AMPERSAND_REPLACE                             = "&amp;";

  private static final String AMPERSAND_BEZ                                 = "ampersand";

  private static final char   LEFT_POINTING_ANGLE_BRACKET_CHAR              = 0x2329;

  private static final String LEFT_POINTING_ANGLE_BRACKET_REPLACE           = "&lang;";

  private static final String LEFT_POINTING_ANGLE_BRACKET_BEZ               = "LEFT-POINTING ANGLE BRACKET";

  private static final char   POUND_STERLING_CHAR                           = 0x00a3;

  private static final String POUND_STERLING_REPLACE                        = "&pound;";

  private static final String POUND_STERLING_BEZ                            = "pound sterling";

  private static final char   DOWNWARDS_DOUBLE_ARROW_CHAR                   = 0x21d3;

  private static final String DOWNWARDS_DOUBLE_ARROW_REPLACE                = "&dArr;";

  private static final String DOWNWARDS_DOUBLE_ARROW_BEZ                    = "DOWNWARDS DOUBLE ARROW";

  private static final char   LEFTWARD_ARROW_CHAR                           = 0x2190;

  private static final String LEFTWARD_ARROW_REPLACE                        = "&larr;";

  private static final String LEFTWARD_ARROW_BEZ                            = "leftward arrow";

  private static final char   GREEK_SMALL_LETTER_LAMDA_CHAR                 = 0x03bb;

  private static final String GREEK_SMALL_LETTER_LAMDA_REPLACE              = "&lambda;";

  private static final String GREEK_SMALL_LETTER_LAMDA_BEZ                  = "GREEK SMALL LETTER LAMDA";

  private static final char   LOWERCASE_YACUTE_ACCENT_CHAR                  = 0x00fd;

  private static final String LOWERCASE_YACUTE_ACCENT_REPLACE               = "&yacute;";

  private static final String LOWERCASE_YACUTE_ACCENT_BEZ                   = "lowercase y, acute accent";

  private static final char   GREEK_SMALL_LETTER_ETA_CHAR                   = 0x03b7;

  private static final String GREEK_SMALL_LETTER_ETA_REPLACE                = "&eta;";

  private static final String GREEK_SMALL_LETTER_ETA_BEZ                    = "GREEK SMALL LETTER ETA";

  private static final char   GREEK_SMALL_LETTER_OMICRON_CHAR               = 0x03bf;

  private static final String GREEK_SMALL_LETTER_OMICRON_REPLACE            = "&omicron;";

  private static final String GREEK_SMALL_LETTER_OMICRON_BEZ                = "GREEK SMALL LETTER OMICRON";

  private static final char   LOWERCASE_YUMLAUT_CHAR                        = 0x00ff;

  private static final String LOWERCASE_YUMLAUT_REPLACE                     = "&yuml;";

  private static final String LOWERCASE_YUMLAUT_BEZ                         = "lowercase y, umlaut";

  private static final char   GREEK_SMALL_LETTER_PSI_CHAR                   = 0x03c8;

  private static final String GREEK_SMALL_LETTER_PSI_REPLACE                = "&psi;";

  private static final String GREEK_SMALL_LETTER_PSI_BEZ                    = "GREEK SMALL LETTER PSI";

  private static final char   SMALL_TILDE_CHAR                              = 0x02dc;

  private static final String SMALL_TILDE_REPLACE                           = "&tilde;";

  private static final String SMALL_TILDE_BEZ                               = "SMALL TILDE";

  private static final String UMLAUT_BEZ                                    = "umlaut";

  private static final char   GREEK_SMALL_LETTER_MU_CHAR                    = 0x03bc;

  private static final String GREEK_SMALL_LETTER_MU_REPLACE                 = "&mu;";

  private static final String GREEK_SMALL_LETTER_MU_BEZ                     = "GREEK SMALL LETTER MU";

  private static final char   UPPERCASE_ETH_ICELANDIC_CHAR                  = 0x00d0;

  private static final String UPPERCASE_ETH_ICELANDIC_REPLACE               = "&ETH;";

  private static final String UPPERCASE_ETH_ICELANDIC_BEZ                   = "uppercase Eth, Icelandic";

  private static final char   GREEK_SMALL_LETTER_DELTA_CHAR                 = 0x03b4;

  private static final String GREEK_SMALL_LETTER_DELTA_REPLACE              = "&delta;";

  private static final String GREEK_SMALL_LETTER_DELTA_BEZ                  = "GREEK SMALL LETTER DELTA";

  private static final char   NOT_ASUBSET_OF_CHAR                           = 0x2284;

  private static final String NOT_ASUBSET_OF_REPLACE                        = "&nsub;";

  private static final String NOT_ASUBSET_OF_BEZ                            = "NOT A SUBSET OF";

  private static final char   ZERO_WIDTH_NON_JOINER_CHAR                    = 0x200c;

  private static final String ZERO_WIDTH_NON_JOINER_REPLACE                 = "&zwnj;";

  private static final String ZERO_WIDTH_NON_JOINER_BEZ                     = "ZERO WIDTH NON-JOINER";

  private static final char   ELEMENT_OF_CHAR                               = 0x2208;

  private static final String ELEMENT_OF_REPLACE                            = "&isin;";

  private static final String ELEMENT_OF_BEZ                                = "ELEMENT OF";

  private static final char   EMPTY_SET_CHAR                                = 0x2205;

  private static final String EMPTY_SET_REPLACE                             = "&empty;";

  private static final String EMPTY_SET_BEZ                                 = "EMPTY SET";

  private static final char   BLACK_LETTER_CAPITAL_I_CHAR                   = 0x2111;

  private static final String BLACK_LETTER_CAPITAL_I_REPLACE                = "&image;";

  private static final String BLACK_LETTER_CAPITAL_I_BEZ                    = "BLACK-LETTER CAPITAL I";

  private static final char   RIGHT_CEILING_CHAR                            = 0x2309;

  private static final String RIGHT_CEILING_REPLACE                         = "&rceil;";

  private static final String RIGHT_CEILING_BEZ                             = "RIGHT CEILING";

  private static final char   UPPERCASE_AE_CHAR                             = 0x00c6;

  private static final String UPPERCASE_AE_REPLACE                          = "&AElig;";

  private static final String UPPERCASE_AE_BEZ                              = "uppercase AE";

  private static final char   LEFT_CEILING_CHAR                             = 0x2308;

  private static final String LEFT_CEILING_REPLACE                          = "&lceil;";

  private static final String LEFT_CEILING_BEZ                              = "LEFT CEILING";

  private static final char   PARAGRAPH_SIGN_CHAR                           = 0x00b6;

  private static final String PARAGRAPH_SIGN_REPLACE                        = "&para;";

  private static final String PARAGRAPH_SIGN_BEZ                            = "paragraph sign";

  private static final char   RIGHT_POINTING_ANGLE_BRACKET_CHAR             = 0x232a;

  private static final String RIGHT_POINTING_ANGLE_BRACKET_REPLACE          = "&rang;";

  private static final String RIGHT_POINTING_ANGLE_BRACKET_BEZ              = "RIGHT-POINTING ANGLE BRACKET";

  private static final char   GREEK_UPSILON_WITH_HOOK_SYMBOL_CHAR           = 0x03d2;

  private static final String GREEK_UPSILON_WITH_HOOK_SYMBOL_REPLACE        = "&upsih;";

  private static final String GREEK_UPSILON_WITH_HOOK_SYMBOL_BEZ            = "GREEK UPSILON WITH HOOK SYMBOL";

  private static final char   MICRO_SIGN_CHAR                               = 0x00b5;

  private static final String MICRO_SIGN_REPLACE                            = "&micro;";

  private static final String MICRO_SIGN_BEZ                                = "micro sign";

  private static final char   RIGHTWARD_ARROW_CHAR                          = 0x2192;

  private static final String RIGHTWARD_ARROW_REPLACE                       = "&rarr;";

  private static final String RIGHTWARD_ARROW_BEZ                           = "rightward arrow";

  private static final char   LOGICAL_AND_CHAR                              = 0x2227;

  private static final String LOGICAL_AND_REPLACE                           = "&and;";

  private static final String LOGICAL_AND_BEZ                               = "LOGICAL AND";

  private static final char   GREEK_SMALL_LETTER_SIGMA_CHAR                 = 0x03c3;

  private static final String GREEK_SMALL_LETTER_SIGMA_REPLACE              = "&sigma;";

  private static final String GREEK_SMALL_LETTER_SIGMA_BEZ                  = "GREEK SMALL LETTER SIGMA";

  private static final char   DOUBLE_PRIME_CHAR                             = 0x2033;

  private static final String DOUBLE_PRIME_REPLACE                          = "&Prime;";

  private static final String DOUBLE_PRIME_BEZ                              = "DOUBLE PRIME";

  private static final char   UPPERCASE_NTILDE_CHAR                         = 0x00d1;

  private static final String UPPERCASE_NTILDE_REPLACE                      = "&Ntilde;";

  private static final String UPPERCASE_NTILDE_BEZ                          = "uppercase N, tilde";

  private static final char   GREEK_SMALL_LETTER_GAMMA_CHAR                 = 0x03b3;

  private static final String GREEK_SMALL_LETTER_GAMMA_REPLACE              = "&gamma;";

  private static final String GREEK_SMALL_LETTER_GAMMA_BEZ                  = "GREEK SMALL LETTER GAMMA";

  private static final char   ASTERISK_OPERATOR_CHAR                        = 0x2217;

  private static final String ASTERISK_OPERATOR_REPLACE                     = "&lowast;";

  private static final String ASTERISK_OPERATOR_BEZ                         = "ASTERISK OPERATOR";

  private static final char   GREEK_SMALL_LETTER_PI_CHAR                    = 0x03c0;

  private static final String GREEK_SMALL_LETTER_PI_REPLACE                 = "&pi;";

  private static final String GREEK_SMALL_LETTER_PI_BEZ                     = "GREEK SMALL LETTER PI";

  private static final char   GREEK_SMALL_LETTER_KAPPA_CHAR                 = 0x03ba;

  private static final String GREEK_SMALL_LETTER_KAPPA_REPLACE              = "&kappa;";

  private static final String GREEK_SMALL_LETTER_KAPPA_BEZ                  = "GREEK SMALL LETTER KAPPA";

  private static final char   IDENTICAL_TO_CHAR                             = 0x2261;

  private static final String IDENTICAL_TO_REPLACE                          = "&equiv;";

  private static final String IDENTICAL_TO_BEZ                              = "IDENTICAL TO";

  private static final char   SINGLE_RIGHT_POINTING_ANGLE_QUOTE_CHAR        = 0x203a;

  private static final String SINGLE_RIGHT_POINTING_ANGLE_QUOTE_REPLACE     = "&rsaquo;";

  private static final String SINGLE_RIGHT_POINTING_ANGLE_QUOTE_BEZ         = "single right-pointing angle quote";

  private static final char   GREEK_CAPITAL_LETTER_MU_CHAR                  = 0x039c;

  private static final String GREEK_CAPITAL_LETTER_MU_REPLACE               = "&Mu;";

  private static final String GREEK_CAPITAL_LETTER_MU_BEZ                   = "GREEK CAPITAL LETTER MU";

  private static final char   CONTAINS_AS_MEMBER_CHAR                       = 0x220b;

  private static final String CONTAINS_AS_MEMBER_REPLACE                    = "&ni;";

  private static final String CONTAINS_AS_MEMBER_BEZ                        = "CONTAINS AS MEMBER";

  private static final char   EN_SPACE_CHAR                                 = 0x2002;

  private static final String EN_SPACE_REPLACE                              = "&ensp;";

  private static final String EN_SPACE_BEZ                                  = "EN SPACE";

  private static final char   GREEK_SMALL_LETTER_BETA_CHAR                  = 0x03b2;

  private static final String GREEK_SMALL_LETTER_BETA_REPLACE               = "&beta;";

  private static final String GREEK_SMALL_LETTER_BETA_BEZ                   = "GREEK SMALL LETTER BETA";

  private static final char   THREE_FOURTHS_CHAR                            = 0x00be;

  private static final String THREE_FOURTHS_REPLACE                         = "&frac34;";

  private static final String THREE_FOURTHS_BEZ                             = "three-fourths";

  private static final char   LOWERCASE_EUMLAUT_CHAR                        = 0x00eb;

  private static final String LOWERCASE_EUMLAUT_REPLACE                     = "&euml;";

  private static final String LOWERCASE_EUMLAUT_BEZ                         = "lowercase e, umlaut";

  private static final char   UPPERCASE_ATILDE_CHAR                         = 0x00c3;

  private static final String UPPERCASE_ATILDE_REPLACE                      = "&Atilde;";

  private static final String UPPERCASE_ATILDE_BEZ                          = "uppercase A, tilde";

  private static final char   ONE_FOURTH_CHAR                               = 0x00bc;

  private static final String ONE_FOURTH_REPLACE                            = "&frac14;";

  private static final String ONE_FOURTH_BEZ                                = "one-fourth";

  private static final char   RIGHT_SINGLE_QUOTE_CHAR                       = 0x2019;

  private static final String RIGHT_SINGLE_QUOTE_REPLACE                    = "&rsquo;";

  private static final String RIGHT_SINGLE_QUOTE_BEZ                        = "right single quote";

  private static final char   GREEK_SMALL_LETTER_PHI_CHAR                   = 0x03c6;

  private static final String GREEK_SMALL_LETTER_PHI_REPLACE                = "&phi;";

  private static final String GREEK_SMALL_LETTER_PHI_BEZ                    = "GREEK SMALL LETTER PHI";

  private static final char   GREEK_CAPITAL_LETTER_EPSILON_CHAR             = 0x0395;

  private static final String GREEK_CAPITAL_LETTER_EPSILON_REPLACE          = "&Epsilon;";

  private static final String GREEK_CAPITAL_LETTER_EPSILON_BEZ              = "GREEK CAPITAL LETTER EPSILON";

  private static final char   LOWERCASE_NTILDE_CHAR                         = 0x00f1;

  private static final String LOWERCASE_NTILDE_REPLACE                      = "&ntilde;";

  private static final String LOWERCASE_NTILDE_BEZ                          = "lowercase n, tilde";

  private static final char   LEFT_SINGLE_QUOTE_CHAR                        = 0x2018;

  private static final String LEFT_SINGLE_QUOTE_REPLACE                     = "&lsquo;";

  private static final String LEFT_SINGLE_QUOTE_BEZ                         = "left single quote";

  private static final char   GREEK_SMALL_LETTER_TAU_CHAR                   = 0x03c4;

  private static final String GREEK_SMALL_LETTER_TAU_REPLACE                = "&tau;";

  private static final String GREEK_SMALL_LETTER_TAU_BEZ                    = "GREEK SMALL LETTER TAU";

  private static final char   SOFT_HYPHEN_CHAR                              = 0x00ad;

  private static final String SOFT_HYPHEN_REPLACE                           = "&shy;";

  private static final String SOFT_HYPHEN_BEZ                               = "soft hyphen";

  private static final char   SUPERSET_OF_OR_EQUAL_TO_CHAR                  = 0x2287;

  private static final String SUPERSET_OF_OR_EQUAL_TO_REPLACE               = "&supe;";

  private static final String SUPERSET_OF_OR_EQUAL_TO_BEZ                   = "SUPERSET OF OR EQUAL TO";

  private static final char   UPPERCASE_EACUTE_ACCENT_CHAR                  = 0x00c9;

  private static final String UPPERCASE_EACUTE_ACCENT_REPLACE               = "&Eacute;";

  private static final String UPPERCASE_EACUTE_ACCENT_BEZ                   = "uppercase E, acute accent";

  private static final char   PARTIAL_DIFFERENTIAL_CHAR                     = 0x2202;

  private static final String PARTIAL_DIFFERENTIAL_REPLACE                  = "&part;";

  private static final String PARTIAL_DIFFERENTIAL_BEZ                      = "PARTIAL DIFFERENTIAL";

  private static final char   UPPERCASE_OUMLAUT_CHAR                        = 0x00d6;

  private static final String UPPERCASE_OUMLAUT_REPLACE                     = "&Ouml;";

  private static final String UPPERCASE_OUMLAUT_BEZ                         = "uppercase O, umlaut";

  private static final char   YEN_SIGN_CHAR                                 = 0x00a5;

  private static final String YEN_SIGN_REPLACE                              = "&yen;";

  private static final String YEN_SIGN_BEZ                                  = "yen sign";

  /**
   * Fuegt fuer bestimmte Sonderzeichen den HTML_Quote-String ein.
   * 
   * @param pEingabe der Eingabesting
   * @return der Eingabestring mit den quotierten Sonderzeichen, null wenn die Eingabe null ist. 
   */
  public static String quoteUmlaute( String pEingabe )
  {
    if ( pEingabe == null )
    {
      return null;
    }

    StringBuffer str_buf_ergebnis = new StringBuffer();

    int akt_index = 0;

    while ( akt_index < pEingabe.length() )
    {
      switch ( pEingabe.charAt( akt_index ) )
      {
        case UPPERCASE_UUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_UUMLAUT_REPLACE );
          break;
        case LOWERCASE_IUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_IUMLAUT_REPLACE );
          break;
        case UPPERCASE_AUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_AUMLAUT_REPLACE );
          break;
        case LOWERCASE_OUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_OUMLAUT_REPLACE );
          break;
        case LOWERCASE_UUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_UUMLAUT_REPLACE );
          break;
        case UPPERCASE_IUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_IUMLAUT_REPLACE );
          break;
        case UPPERCASE_EUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_EUMLAUT_REPLACE );
          break;
        case LOWERCASE_AUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_AUMLAUT_REPLACE );
          break;
        case LOWERCASE_YUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_YUMLAUT_REPLACE );
          break;
        case LOWERCASE_EUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_EUMLAUT_REPLACE );
          break;
        case UPPERCASE_ATILDE_CHAR :
          str_buf_ergebnis.append( UPPERCASE_ATILDE_REPLACE );
          break;
        case ONE_FOURTH_CHAR :
          str_buf_ergebnis.append( ONE_FOURTH_REPLACE );
          break;
        case RIGHT_SINGLE_QUOTE_CHAR :
          str_buf_ergebnis.append( RIGHT_SINGLE_QUOTE_REPLACE );
          break;
        case GREEK_SMALL_LETTER_PHI_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_PHI_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_EPSILON_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_EPSILON_REPLACE );
          break;
        case LOWERCASE_NTILDE_CHAR :
          str_buf_ergebnis.append( LOWERCASE_NTILDE_REPLACE );
          break;
        case LEFT_SINGLE_QUOTE_CHAR :
          str_buf_ergebnis.append( LEFT_SINGLE_QUOTE_REPLACE );
          break;
        case GREEK_SMALL_LETTER_TAU_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_TAU_REPLACE );
          break;
        case SOFT_HYPHEN_CHAR :
          str_buf_ergebnis.append( SOFT_HYPHEN_REPLACE );
          break;
        case SUPERSET_OF_OR_EQUAL_TO_CHAR :
          str_buf_ergebnis.append( SUPERSET_OF_OR_EQUAL_TO_REPLACE );
          break;
        case UPPERCASE_EACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_EACUTE_ACCENT_REPLACE );
          break;
        case PARTIAL_DIFFERENTIAL_CHAR :
          str_buf_ergebnis.append( PARTIAL_DIFFERENTIAL_REPLACE );
          break;
        case UPPERCASE_OUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_OUMLAUT_REPLACE );
          break;
        case YEN_SIGN_CHAR :
          str_buf_ergebnis.append( YEN_SIGN_REPLACE );
          break;

        default :
          str_buf_ergebnis.append( pEingabe.charAt( akt_index ) );
      }

      akt_index++;
    }
    return str_buf_ergebnis.toString();
  }

  public static String quoteSpecialCharacters( String pEingabe )
  {
    return quoteSpecialCharacters( pEingabe, false, false );
  }

  public static String quoteSpecialCharacters( String pEingabe, boolean pConvertSpace, boolean pConvertBR )
  {
    if ( pEingabe == null )
    {
      return null;
    }

    StringBuffer str_buf_ergebnis = new StringBuffer();

    int akt_index = 0;

    while ( akt_index < pEingabe.length() )
    {
      switch ( pEingabe.charAt( akt_index ) )
      {
        case '\n' :

          if ( pConvertBR )
          {
            str_buf_ergebnis.append( "<br/>\n" );
          }
          else
          {
            str_buf_ergebnis.append( pEingabe.charAt( akt_index ) );
          } ;

          break;

        case ACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( ACUTE_ACCENT_REPLACE );
          break;
        case RIGHT_FLOOR_CHAR :
          str_buf_ergebnis.append( RIGHT_FLOOR_REPLACE );
          break;
        case GREEK_THETA_SYMBOL_CHAR :
          str_buf_ergebnis.append( GREEK_THETA_SYMBOL_REPLACE );
          break;
        case UPPERCASE_OCIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_OCIRCUMFLEX_ACCENT_REPLACE );
          break;
        case UPPERCASE_OSLASH_CHAR :
          str_buf_ergebnis.append( UPPERCASE_OSLASH_REPLACE );
          break;
        case LOWERCASE_ARING_CHAR :
          str_buf_ergebnis.append( LOWERCASE_ARING_REPLACE );
          break;
        case LEFT_RIGHT_DOUBLE_ARROW_CHAR :
          str_buf_ergebnis.append( LEFT_RIGHT_DOUBLE_ARROW_REPLACE );
          break;
        case GREEK_SMALL_LETTER_ZETA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_ZETA_REPLACE );
          break;
        case GREEK_SMALL_LETTER_EPSILON_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_EPSILON_REPLACE );
          break;
        case UPPERCASE_ICIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_ICIRCUMFLEX_ACCENT_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_PI_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_PI_REPLACE );
          break;
        case ZERO_WIDTH_JOINER_CHAR :
          str_buf_ergebnis.append( ZERO_WIDTH_JOINER_REPLACE );
          break;
        case SUBSET_OF_OR_EQUAL_TO_CHAR :
          str_buf_ergebnis.append( SUBSET_OF_OR_EQUAL_TO_REPLACE );
          break;
        case LOWERCASE_ATILDE_CHAR :
          str_buf_ergebnis.append( LOWERCASE_ATILDE_REPLACE );
          break;
        case INTEGRAL_CHAR :
          str_buf_ergebnis.append( INTEGRAL_REPLACE );
          break;
        case GREEK_SMALL_LETTER_OMEGA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_OMEGA_REPLACE );
          break;
        case COPYRIGHT_CHAR :
          str_buf_ergebnis.append( COPYRIGHT_REPLACE );
          break;
        case THIN_SPACE_CHAR :
          str_buf_ergebnis.append( THIN_SPACE_REPLACE );
          break;
        case MACRON_CHAR :
          str_buf_ergebnis.append( MACRON_REPLACE );
          break;
        case UPWARD_ARROW_CHAR :
          str_buf_ergebnis.append( UPWARD_ARROW_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_CHI_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_CHI_REPLACE );
          break;
        case UPPERCASE_OTILDE_CHAR :
          str_buf_ergebnis.append( UPPERCASE_OTILDE_REPLACE );
          break;
        case GREEK_PI_SYMBOL_CHAR :
          str_buf_ergebnis.append( GREEK_PI_SYMBOL_REPLACE );
          break;
        case LOWERCASE_EACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_EACUTE_ACCENT_REPLACE );
          break;
        case UNION_CHAR :
          str_buf_ergebnis.append( UNION_REPLACE );
          break;
        case MODIFIER_LETTER_CIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( MODIFIER_LETTER_CIRCUMFLEX_ACCENT_REPLACE );
          break;
        case LOWERCASE_ECIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_ECIRCUMFLEX_ACCENT_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_SIGMA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_SIGMA_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_RHO_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_RHO_REPLACE );
          break;
        case UPPERCASE_IACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_IACUTE_ACCENT_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_IOTA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_IOTA_REPLACE );
          break;
        case LOWERCASE_OSLASH_CHAR :
          str_buf_ergebnis.append( LOWERCASE_OSLASH_REPLACE );
          break;
        case SLASH_CHAR :
          str_buf_ergebnis.append( SLASH_REPLACE );
          break;
        case DOUBLE_LOW_9_QUOTE_CHAR :
          str_buf_ergebnis.append( DOUBLE_LOW_9_QUOTE_REPLACE );
          break;
        case INFINITY_CHAR :
          str_buf_ergebnis.append( INFINITY_REPLACE );
          break;
        case UPPERCASE_UUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_UUMLAUT_REPLACE );
          break;
        case BROKEN_BAR_CHAR :
          str_buf_ergebnis.append( BROKEN_BAR_REPLACE );
          break;
        case BLACK_SPADE_SUIT_CHAR :
          str_buf_ergebnis.append( BLACK_SPADE_SUIT_REPLACE );
          break;
        case ALEF_SYMBOL_CHAR :
          str_buf_ergebnis.append( ALEF_SYMBOL_REPLACE );
          break;
        case TILDE_OPERATOR_CHAR :
          str_buf_ergebnis.append( TILDE_OPERATOR_REPLACE );
          break;
        case DOUBLE_DAGGER_CHAR :
          str_buf_ergebnis.append( DOUBLE_DAGGER_REPLACE );
          break;
        case MIDDLE_DOT_CHAR :
          str_buf_ergebnis.append( MIDDLE_DOT_REPLACE );
          break;
        case ANGLE_CHAR :
          str_buf_ergebnis.append( ANGLE_REPLACE );
          break;
        case LOWERCASE_EGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_EGRAVE_ACCENT_REPLACE );
          break;
        case GREATER_THAN_SIGN_CHAR :
          str_buf_ergebnis.append( GREATER_THAN_SIGN_REPLACE );
          break;
        case LOWERCASE_THORN_ICELANDIC_CHAR :
          str_buf_ergebnis.append( LOWERCASE_THORN_ICELANDIC_REPLACE );
          break;
        case OVERLINE_SPACING_OVERSCORE_CHAR :
          str_buf_ergebnis.append( OVERLINE_SPACING_OVERSCORE_REPLACE );
          break;
        case UPPERCASE_IGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_IGRAVE_ACCENT_REPLACE );
          break;
        case DOT_OPERATOR_CHAR :
          str_buf_ergebnis.append( DOT_OPERATOR_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_ALPHA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_ALPHA_REPLACE );
          break;
        case GREEK_SMALL_LETTER_THETA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_THETA_REPLACE );
          break;
        case GENERAL_CURRENCY_SIGN_CHAR :
          str_buf_ergebnis.append( GENERAL_CURRENCY_SIGN_REPLACE );
          break;
        case DOWNWARD_ARROW_CHAR :
          str_buf_ergebnis.append( DOWNWARD_ARROW_REPLACE );
          break;
        case UPPERCASE_AACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_AACUTE_ACCENT_REPLACE );
          break;
        case PLUS_OR_MINUS_CHAR :
          str_buf_ergebnis.append( PLUS_OR_MINUS_REPLACE );
          break;
        case LOZENGE_CHAR :
          str_buf_ergebnis.append( LOZENGE_REPLACE );
          break;
        case LOWERCASE_OTILDE_CHAR :
          str_buf_ergebnis.append( LOWERCASE_OTILDE_REPLACE );
          break;
        case NARY_PRODUCT_CHAR :
          str_buf_ergebnis.append( NARY_PRODUCT_REPLACE );
          break;
        case UPPERCASE_ACIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_ACIRCUMFLEX_ACCENT_REPLACE );
          break;
        case INVERTED_EXCLAMATION_CHAR :
          str_buf_ergebnis.append( INVERTED_EXCLAMATION_REPLACE );
          break;
        case LOWERCASE_IACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_IACUTE_ACCENT_REPLACE );
          break;
        case LATIN_SMALL_LIGATURE_OE_CHAR :
          str_buf_ergebnis.append( LATIN_SMALL_LIGATURE_OE_REPLACE );
          break;
        case GREEK_SMALL_LETTER_XI_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_XI_REPLACE );
          break;
        case APPROXIMATELY_EQUAL_TO_CHAR :
          str_buf_ergebnis.append( APPROXIMATELY_EQUAL_TO_REPLACE );
          break;
        case RIGHT_TO_LEFT_MARK_CHAR :
          str_buf_ergebnis.append( RIGHT_TO_LEFT_MARK_REPLACE );
          break;
        case DAGGER_CHAR :
          str_buf_ergebnis.append( DAGGER_REPLACE );
          break;
        case LOWERCASE_IUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_IUMLAUT_REPLACE );
          break;
        case SQUARE_ROOT_CHAR :
          str_buf_ergebnis.append( SQUARE_ROOT_REPLACE );
          break;
        case UPPERCASE_THORN_ICELANDIC_CHAR :
          str_buf_ergebnis.append( UPPERCASE_THORN_ICELANDIC_REPLACE );
          break;
        case UPPERCASE_AGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_AGRAVE_ACCENT_REPLACE );
          break;
        case PER_MILL_SIGN_CHAR :
          str_buf_ergebnis.append( PER_MILL_SIGN_REPLACE );
          break;
        case UP_TACK_CHAR :
          str_buf_ergebnis.append( UP_TACK_REPLACE );
          break;
        case MULTIPLICATION_SIGN_CHAR :
          str_buf_ergebnis.append( MULTIPLICATION_SIGN_REPLACE );
          break;
        case BULLET_CHAR :
          str_buf_ergebnis.append( BULLET_REPLACE );
          break;
        case LOWERCASE_IGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_IGRAVE_ACCENT_REPLACE );
          break;
        case NABLA_CHAR :
          str_buf_ergebnis.append( NABLA_REPLACE );
          break;
        case GREEK_SMALL_LETTER_NU_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_NU_REPLACE );
          break;
        case SECTION_SIGN_CHAR :
          str_buf_ergebnis.append( SECTION_SIGN_REPLACE );
          break;
        case LATIN_SMALL_LETTER_FWITH_HOOK_CHAR :
          str_buf_ergebnis.append( LATIN_SMALL_LETTER_FWITH_HOOK_REPLACE );
          break;
        case DOUBLE_QUOTATION_MARK_CHAR :
          str_buf_ergebnis.append( DOUBLE_QUOTATION_MARK_REPLACE );
          break;
        case LOWERCASE_AACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_AACUTE_ACCENT_REPLACE );
          break;
        case REGISTERED_TRADEMARK_CHAR :
          str_buf_ergebnis.append( REGISTERED_TRADEMARK_REPLACE );
          break;
        case MINUS_SIGN_CHAR :
          str_buf_ergebnis.append( MINUS_SIGN_REPLACE );
          break;
        case APOSTROPHE_CHAR :
          str_buf_ergebnis.append( APOSTROPHE_REPLACE );
          break;
        case CIRCLED_PLUS_CHAR :
          str_buf_ergebnis.append( CIRCLED_PLUS_REPLACE );
          break;
//        case LESS_THAN_SIGN_CHAR :
//          buffer.append( LESS_THAN_SIGN_REPLACE );
//          break;
        case GREEK_CAPITAL_LETTER_DELTA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_DELTA_REPLACE );
          break;
        case LEFTWARDS_DOUBLE_ARROW_CHAR :
          str_buf_ergebnis.append( LEFTWARDS_DOUBLE_ARROW_REPLACE );
          break;
        case RIGHT_ANGLE_QUOTE_CHAR :
          str_buf_ergebnis.append( RIGHT_ANGLE_QUOTE_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_ETA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_ETA_REPLACE );
          break;
        case DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_CHAR :
          str_buf_ergebnis.append( DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_REPLACE );
          break;
        case LEFT_ANGLE_QUOTE_CHAR :
          str_buf_ergebnis.append( LEFT_ANGLE_QUOTE_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_PSI_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_PSI_REPLACE );
          break;
        case UPPERCASE_AUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_AUMLAUT_REPLACE );
          break;
        case LEFT_TO_RIGHT_MARK_CHAR :
          str_buf_ergebnis.append( LEFT_TO_RIGHT_MARK_REPLACE );
          break;
        case UPPERCASE_OACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_OACUTE_ACCENT_REPLACE );
          break;
        case LOWERCASE_AGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_AGRAVE_ACCENT_REPLACE );
          break;
        case THERE_EXISTS_CHAR :
          str_buf_ergebnis.append( THERE_EXISTS_REPLACE );
          break;
        case UPPERCASE_EGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_EGRAVE_ACCENT_REPLACE );
          break;
        case SUBSET_OF_CHAR :
          str_buf_ergebnis.append( SUBSET_OF_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_XI_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_XI_REPLACE );
          break;
        case LOWERCASE_OUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_OUMLAUT_REPLACE );
          break;
        case EURO_SIGN_CHAR :
          str_buf_ergebnis.append( EURO_SIGN_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_UPSILON_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_UPSILON_REPLACE );
          break;
        case ELLIPSES_CHAR :
          str_buf_ergebnis.append( ELLIPSES_REPLACE );
          break;
        case PRIME_CHAR :
          str_buf_ergebnis.append( PRIME_REPLACE );
          break;
        case UPPERCASE_CCEDILLA_CHAR :
          str_buf_ergebnis.append( UPPERCASE_CCEDILLA_REPLACE );
          break;
        case LATIN_CAPITAL_LIGATURE_OE_CHAR :
          str_buf_ergebnis.append( LATIN_CAPITAL_LIGATURE_OE_REPLACE );
          break;
        case LATIN_CAPITAL_LETTER_SWITH_CARON_CHAR :
          str_buf_ergebnis.append( LATIN_CAPITAL_LETTER_SWITH_CARON_REPLACE );
          break;
        case SUPERSCRIPT_TWO_CHAR :
          str_buf_ergebnis.append( SUPERSCRIPT_TWO_REPLACE );
          break;
        case NOT_EQUAL_TO_CHAR :
          str_buf_ergebnis.append( NOT_EQUAL_TO_REPLACE );
          break;
        case UPPERCASE_OGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_OGRAVE_ACCENT_REPLACE );
          break;
        case SUPERSCRIPT_ONE_CHAR :
          str_buf_ergebnis.append( SUPERSCRIPT_ONE_REPLACE );
          break;
        case SINGLE_LEFT_POINTING_ANGLE_QUOTE_CHAR :
          str_buf_ergebnis.append( SINGLE_LEFT_POINTING_ANGLE_QUOTE_REPLACE );
          break;
        case BLACK_DIAMOND_SUIT_CHAR :
          str_buf_ergebnis.append( BLACK_DIAMOND_SUIT_REPLACE );
          break;
        case SCRIPT_CAPITAL_P_CHAR :
          str_buf_ergebnis.append( SCRIPT_CAPITAL_P_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_NU_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_NU_REPLACE );
          break;
        case TRADEMARK_SIGN_CHAR :
          str_buf_ergebnis.append( TRADEMARK_SIGN_REPLACE );
          break;
        case LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_CHAR :
          str_buf_ergebnis.append( LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_REPLACE );
          break;
        case NARY_SUMMATION_CHAR :
          str_buf_ergebnis.append( NARY_SUMMATION_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_GAMMA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_GAMMA_REPLACE );
          break;
        case NOT_SIGN_CHAR :
          str_buf_ergebnis.append( NOT_SIGN_REPLACE );
          break;
        case MASCULINE_ORDINAL_CHAR :
          str_buf_ergebnis.append( MASCULINE_ORDINAL_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_KAPPA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_KAPPA_REPLACE );
          break;
        case LOWERCASE_OACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_OACUTE_ACCENT_REPLACE );
          break;
        case BLACK_LETTER_CAPITAL_R_CHAR :
          str_buf_ergebnis.append( BLACK_LETTER_CAPITAL_R_REPLACE );
          break;
        case NOT_AN_ELEMENT_OF_CHAR :
          str_buf_ergebnis.append( NOT_AN_ELEMENT_OF_REPLACE );
          break;
        case RIGHTWARDS_DOUBLE_ARROW_CHAR :
          str_buf_ergebnis.append( RIGHTWARDS_DOUBLE_ARROW_REPLACE );
          break;
        case EM_DASH_CHAR :
          str_buf_ergebnis.append( EM_DASH_REPLACE );
          break;
        case DEGREE_SIGN_CHAR :
          str_buf_ergebnis.append( DEGREE_SIGN_REPLACE );
          break;
        case DIVISION_SIGN_CHAR :
          str_buf_ergebnis.append( DIVISION_SIGN_REPLACE );
          break;
        case GREEK_SMALL_LETTER_IOTA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_IOTA_REPLACE );
          break;
        case LOWERCASE_CCEDILLA_CHAR :
          str_buf_ergebnis.append( LOWERCASE_CCEDILLA_REPLACE );
          break;
        case LEFT_RIGHT_ARROW_CHAR :
          str_buf_ergebnis.append( LEFT_RIGHT_ARROW_REPLACE );
          break;
        case LATIN_SMALL_LETTER_SWITH_CARON_CHAR :
          str_buf_ergebnis.append( LATIN_SMALL_LETTER_SWITH_CARON_REPLACE );
          break;
        case NONBREAKING_SPACE_CHAR :
          str_buf_ergebnis.append( NONBREAKING_SPACE_REPLACE );
          break;
        case LOWERCASE_OGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_OGRAVE_ACCENT_REPLACE );
          break;
        case BLACK_HEART_SUIT_CHAR :
          str_buf_ergebnis.append( BLACK_HEART_SUIT_REPLACE );
          break;
        case LOWERCASE_UUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_UUMLAUT_REPLACE );
          break;
        case CEDILLA_CHAR :
          str_buf_ergebnis.append( CEDILLA_REPLACE );
          break;
        case LOWERCASE_UCIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_UCIRCUMFLEX_ACCENT_REPLACE );
          break;
        case LOGICAL_OR_CHAR :
          str_buf_ergebnis.append( LOGICAL_OR_REPLACE );
          break;
        case LEFT_FLOOR_CHAR :
          str_buf_ergebnis.append( LEFT_FLOOR_REPLACE );
          break;
        case INVERTED_QUESTION_MARK_CHAR :
          str_buf_ergebnis.append( INVERTED_QUESTION_MARK_REPLACE );
          break;
        case LOWERCASE_OCIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_OCIRCUMFLEX_ACCENT_REPLACE );
          break;
        case INTERSECTION_CHAR :
          str_buf_ergebnis.append( INTERSECTION_REPLACE );
          break;
        case LOWERCASE_SHARPS_GERMAN_CHAR :
          str_buf_ergebnis.append( LOWERCASE_SHARPS_GERMAN_REPLACE );
          break;
        case RIGHT_DOUBLE_QUOTE_CHAR :
          str_buf_ergebnis.append( RIGHT_DOUBLE_QUOTE_REPLACE );
          break;
        case LOWERCASE_ICIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_ICIRCUMFLEX_ACCENT_REPLACE );
          break;
        case CENT_SIGN_CHAR :
          str_buf_ergebnis.append( CENT_SIGN_REPLACE );
          break;
        case GREEK_SMALL_LETTER_CHI_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_CHI_REPLACE );
          break;
        case LEFT_DOUBLE_QUOTE_CHAR :
          str_buf_ergebnis.append( LEFT_DOUBLE_QUOTE_REPLACE );
          break;
        case PROPORTIONAL_TO_CHAR :
          str_buf_ergebnis.append( PROPORTIONAL_TO_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_PHI_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_PHI_REPLACE );
          break;
        case UPPERCASE_ARING_CHAR :
          str_buf_ergebnis.append( UPPERCASE_ARING_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_TAU_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_TAU_REPLACE );
          break;
        case GREEK_SMALL_LETTER_RHO_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_RHO_REPLACE );
          break;
        case UPPERCASE_UACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_UACUTE_ACCENT_REPLACE );
          break;
//        case GREATER_THAN_OR_EQUAL_TO_CHAR :
//          buffer.append( GREATER_THAN_OR_EQUAL_TO_REPLACE );
//          break;
        case EM_SPACE_CHAR :
          str_buf_ergebnis.append( EM_SPACE_REPLACE );
          break;
        case ALMOST_EQUAL_TO_CHAR :
          str_buf_ergebnis.append( ALMOST_EQUAL_TO_REPLACE );
          break;
        case DIAERESIS_CHAR :
          str_buf_ergebnis.append( DIAERESIS_REPLACE );
          break;
        case LOWERCASE_ETH_ICELANDIC_CHAR :
          str_buf_ergebnis.append( LOWERCASE_ETH_ICELANDIC_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_OMEGA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_OMEGA_REPLACE );
          break;
        case UPPERCASE_IUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_IUMLAUT_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_BETA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_BETA_REPLACE );
          break;
        case UPPERCASE_EUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_EUMLAUT_REPLACE );
          break;
        case UPPERCASE_UGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_UGRAVE_ACCENT_REPLACE );
          break;
        case BLACK_CLUB_SUIT_CHAR :
          str_buf_ergebnis.append( BLACK_CLUB_SUIT_REPLACE );
          break;
        case FEMININE_ORDINAL_CHAR :
          str_buf_ergebnis.append( FEMININE_ORDINAL_REPLACE );
          break;
        case UPPERCASE_ECIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_ECIRCUMFLEX_ACCENT_REPLACE );
          break;
        case THEREFORE_CHAR :
          str_buf_ergebnis.append( THEREFORE_REPLACE );
          break;
        case UPWARDS_DOUBLE_ARROW_CHAR :
          str_buf_ergebnis.append( UPWARDS_DOUBLE_ARROW_REPLACE );
          break;
        case LOWERCASE_UACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_UACUTE_ACCENT_REPLACE );
          break;
        case GREEK_SMALL_LETTER_ALPHA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_ALPHA_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_LAMDA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_LAMDA_REPLACE );
          break;
        case SINGLE_LOW_9_QUOTE_CHAR :
          str_buf_ergebnis.append( SINGLE_LOW_9_QUOTE_REPLACE );
          break;
        case UPPERCASE_YACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_YACUTE_ACCENT_REPLACE );
          break;
        case SUPERSCRIPT_THREE_CHAR :
          str_buf_ergebnis.append( SUPERSCRIPT_THREE_REPLACE );
          break;
        case GREEK_SMALL_LETTER_FINAL_SIGMA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_FINAL_SIGMA_REPLACE );
          break;
        case CIRCLED_TIMES_CHAR :
          str_buf_ergebnis.append( CIRCLED_TIMES_REPLACE );
          break;
        case SUPERSET_OF_CHAR :
          str_buf_ergebnis.append( SUPERSET_OF_REPLACE );
          break;
        case LOWERCASE_AUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_AUMLAUT_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_ZETA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_ZETA_REPLACE );
          break;
        case LOWERCASE_ACIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_ACIRCUMFLEX_ACCENT_REPLACE );
          break;
        case LOWERCASE_AE_CHAR :
          str_buf_ergebnis.append( LOWERCASE_AE_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_THETA_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_THETA_REPLACE );
          break;
        case ONE_HALF_CHAR :
          str_buf_ergebnis.append( ONE_HALF_REPLACE );
          break;
        case LESS_THAN_OR_EQUAL_TO_CHAR :
          str_buf_ergebnis.append( LESS_THAN_OR_EQUAL_TO_REPLACE );
          break;
        case LOWERCASE_UGRAVE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_UGRAVE_ACCENT_REPLACE );
          break;
        case GREEK_SMALL_LETTER_UPSILON_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_UPSILON_REPLACE );
          break;
        case FOR_ALL_CHAR :
          str_buf_ergebnis.append( FOR_ALL_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_OMICRON_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_OMICRON_REPLACE );
          break;
        case UPPERCASE_UCIRCUMFLEX_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_UCIRCUMFLEX_ACCENT_REPLACE );
          break;
        case EN_DASH_CHAR :
          str_buf_ergebnis.append( EN_DASH_REPLACE );
          break;
        case AMPERSAND_CHAR :
          str_buf_ergebnis.append( AMPERSAND_REPLACE );
          break;
        case LEFT_POINTING_ANGLE_BRACKET_CHAR :
          str_buf_ergebnis.append( LEFT_POINTING_ANGLE_BRACKET_REPLACE );
          break;
        case POUND_STERLING_CHAR :
          str_buf_ergebnis.append( POUND_STERLING_REPLACE );
          break;
        case DOWNWARDS_DOUBLE_ARROW_CHAR :
          str_buf_ergebnis.append( DOWNWARDS_DOUBLE_ARROW_REPLACE );
          break;
        case LEFTWARD_ARROW_CHAR :
          str_buf_ergebnis.append( LEFTWARD_ARROW_REPLACE );
          break;
        case GREEK_SMALL_LETTER_LAMDA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_LAMDA_REPLACE );
          break;
        case LOWERCASE_YACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_YACUTE_ACCENT_REPLACE );
          break;
        case GREEK_SMALL_LETTER_ETA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_ETA_REPLACE );
          break;
        case GREEK_SMALL_LETTER_OMICRON_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_OMICRON_REPLACE );
          break;
        case LOWERCASE_YUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_YUMLAUT_REPLACE );
          break;
        case GREEK_SMALL_LETTER_PSI_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_PSI_REPLACE );
          break;
        case SMALL_TILDE_CHAR :
          str_buf_ergebnis.append( SMALL_TILDE_REPLACE );
          break;
        case GREEK_SMALL_LETTER_MU_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_MU_REPLACE );
          break;
        case UPPERCASE_ETH_ICELANDIC_CHAR :
          str_buf_ergebnis.append( UPPERCASE_ETH_ICELANDIC_REPLACE );
          break;
        case GREEK_SMALL_LETTER_DELTA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_DELTA_REPLACE );
          break;
        case NOT_ASUBSET_OF_CHAR :
          str_buf_ergebnis.append( NOT_ASUBSET_OF_REPLACE );
          break;
        case ZERO_WIDTH_NON_JOINER_CHAR :
          str_buf_ergebnis.append( ZERO_WIDTH_NON_JOINER_REPLACE );
          break;
        case ELEMENT_OF_CHAR :
          str_buf_ergebnis.append( ELEMENT_OF_REPLACE );
          break;
        case EMPTY_SET_CHAR :
          str_buf_ergebnis.append( EMPTY_SET_REPLACE );
          break;
        case BLACK_LETTER_CAPITAL_I_CHAR :
          str_buf_ergebnis.append( BLACK_LETTER_CAPITAL_I_REPLACE );
          break;
        case RIGHT_CEILING_CHAR :
          str_buf_ergebnis.append( RIGHT_CEILING_REPLACE );
          break;
        case UPPERCASE_AE_CHAR :
          str_buf_ergebnis.append( UPPERCASE_AE_REPLACE );
          break;
        case LEFT_CEILING_CHAR :
          str_buf_ergebnis.append( LEFT_CEILING_REPLACE );
          break;
        case PARAGRAPH_SIGN_CHAR :
          str_buf_ergebnis.append( PARAGRAPH_SIGN_REPLACE );
          break;
        case RIGHT_POINTING_ANGLE_BRACKET_CHAR :
          str_buf_ergebnis.append( RIGHT_POINTING_ANGLE_BRACKET_REPLACE );
          break;
        case GREEK_UPSILON_WITH_HOOK_SYMBOL_CHAR :
          str_buf_ergebnis.append( GREEK_UPSILON_WITH_HOOK_SYMBOL_REPLACE );
          break;
        case MICRO_SIGN_CHAR :
          str_buf_ergebnis.append( MICRO_SIGN_REPLACE );
          break;
        case RIGHTWARD_ARROW_CHAR :
          str_buf_ergebnis.append( RIGHTWARD_ARROW_REPLACE );
          break;
        case LOGICAL_AND_CHAR :
          str_buf_ergebnis.append( LOGICAL_AND_REPLACE );
          break;
        case GREEK_SMALL_LETTER_SIGMA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_SIGMA_REPLACE );
          break;
        case DOUBLE_PRIME_CHAR :
          str_buf_ergebnis.append( DOUBLE_PRIME_REPLACE );
          break;
        case UPPERCASE_NTILDE_CHAR :
          str_buf_ergebnis.append( UPPERCASE_NTILDE_REPLACE );
          break;
        case GREEK_SMALL_LETTER_GAMMA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_GAMMA_REPLACE );
          break;
        case ASTERISK_OPERATOR_CHAR :
          str_buf_ergebnis.append( ASTERISK_OPERATOR_REPLACE );
          break;
        case GREEK_SMALL_LETTER_PI_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_PI_REPLACE );
          break;
        case GREEK_SMALL_LETTER_KAPPA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_KAPPA_REPLACE );
          break;
        case IDENTICAL_TO_CHAR :
          str_buf_ergebnis.append( IDENTICAL_TO_REPLACE );
          break;
        case SINGLE_RIGHT_POINTING_ANGLE_QUOTE_CHAR :
          str_buf_ergebnis.append( SINGLE_RIGHT_POINTING_ANGLE_QUOTE_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_MU_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_MU_REPLACE );
          break;
        case CONTAINS_AS_MEMBER_CHAR :
          str_buf_ergebnis.append( CONTAINS_AS_MEMBER_REPLACE );
          break;
        case EN_SPACE_CHAR :
          str_buf_ergebnis.append( EN_SPACE_REPLACE );
          break;
        case GREEK_SMALL_LETTER_BETA_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_BETA_REPLACE );
          break;
        case THREE_FOURTHS_CHAR :
          str_buf_ergebnis.append( THREE_FOURTHS_REPLACE );
          break;
        case LOWERCASE_EUMLAUT_CHAR :
          str_buf_ergebnis.append( LOWERCASE_EUMLAUT_REPLACE );
          break;
        case UPPERCASE_ATILDE_CHAR :
          str_buf_ergebnis.append( UPPERCASE_ATILDE_REPLACE );
          break;
        case ONE_FOURTH_CHAR :
          str_buf_ergebnis.append( ONE_FOURTH_REPLACE );
          break;
        case RIGHT_SINGLE_QUOTE_CHAR :
          str_buf_ergebnis.append( RIGHT_SINGLE_QUOTE_REPLACE );
          break;
        case GREEK_SMALL_LETTER_PHI_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_PHI_REPLACE );
          break;
        case GREEK_CAPITAL_LETTER_EPSILON_CHAR :
          str_buf_ergebnis.append( GREEK_CAPITAL_LETTER_EPSILON_REPLACE );
          break;
        case LOWERCASE_NTILDE_CHAR :
          str_buf_ergebnis.append( LOWERCASE_NTILDE_REPLACE );
          break;
        case LEFT_SINGLE_QUOTE_CHAR :
          str_buf_ergebnis.append( LEFT_SINGLE_QUOTE_REPLACE );
          break;
        case GREEK_SMALL_LETTER_TAU_CHAR :
          str_buf_ergebnis.append( GREEK_SMALL_LETTER_TAU_REPLACE );
          break;
        case SOFT_HYPHEN_CHAR :
          str_buf_ergebnis.append( SOFT_HYPHEN_REPLACE );
          break;
        case SUPERSET_OF_OR_EQUAL_TO_CHAR :
          str_buf_ergebnis.append( SUPERSET_OF_OR_EQUAL_TO_REPLACE );
          break;
        case UPPERCASE_EACUTE_ACCENT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_EACUTE_ACCENT_REPLACE );
          break;
        case PARTIAL_DIFFERENTIAL_CHAR :
          str_buf_ergebnis.append( PARTIAL_DIFFERENTIAL_REPLACE );
          break;
        case UPPERCASE_OUMLAUT_CHAR :
          str_buf_ergebnis.append( UPPERCASE_OUMLAUT_REPLACE );
          break;
        case YEN_SIGN_CHAR :
          str_buf_ergebnis.append( YEN_SIGN_REPLACE );
          break;

        default :
          str_buf_ergebnis.append( pEingabe.charAt( akt_index ) );
      }

      akt_index++;
    }
    return str_buf_ergebnis.toString();
  }

  public static String quoteText( String pEingabe )
  {
    if ( pEingabe == null )
    {
      return null;
    }

    StringBuffer buffer = new StringBuffer();

    int akt_index = 0;

    while ( akt_index < pEingabe.length() )
    {
      switch ( pEingabe.charAt( akt_index ) )
      {
        case ACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case RIGHT_FLOOR_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) RIGHT_FLOOR_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_THETA_SYMBOL_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_THETA_SYMBOL_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_OCIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_OCIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_OSLASH_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_OSLASH_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_ARING_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_ARING_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFT_RIGHT_DOUBLE_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFT_RIGHT_DOUBLE_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_ZETA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_ZETA_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_EPSILON_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_EPSILON_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_ICIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_ICIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_PI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_PI_CHAR );
          buffer.append( ") + \"" );
          break;
        case ZERO_WIDTH_JOINER_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ZERO_WIDTH_JOINER_CHAR );
          buffer.append( ") + \"" );
          break;
        case SUBSET_OF_OR_EQUAL_TO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SUBSET_OF_OR_EQUAL_TO_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_ATILDE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_ATILDE_CHAR );
          buffer.append( ") + \"" );
          break;
        case INTEGRAL_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) INTEGRAL_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_OMEGA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_OMEGA_CHAR );
          buffer.append( ") + \"" );
          break;
        case COPYRIGHT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) COPYRIGHT_CHAR );
          buffer.append( ") + \"" );
          break;
        case THIN_SPACE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) THIN_SPACE_CHAR );
          buffer.append( ") + \"" );
          break;
        case MACRON_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) MACRON_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPWARD_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPWARD_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_CHI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_CHI_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_OTILDE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_OTILDE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_PI_SYMBOL_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_PI_SYMBOL_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_EACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_EACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case UNION_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UNION_CHAR );
          buffer.append( ") + \"" );
          break;
        case MODIFIER_LETTER_CIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) MODIFIER_LETTER_CIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_ECIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_ECIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_SIGMA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_SIGMA_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_RHO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_RHO_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_IACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_IACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_IOTA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_IOTA_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_OSLASH_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_OSLASH_CHAR );
          buffer.append( ") + \"" );
          break;
        case SLASH_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SLASH_CHAR );
          buffer.append( ") + \"" );
          break;
        case DOUBLE_LOW_9_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DOUBLE_LOW_9_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case INFINITY_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) INFINITY_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_UUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_UUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case BROKEN_BAR_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) BROKEN_BAR_CHAR );
          buffer.append( ") + \"" );
          break;
        case BLACK_SPADE_SUIT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) BLACK_SPADE_SUIT_CHAR );
          buffer.append( ") + \"" );
          break;
        case ALEF_SYMBOL_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ALEF_SYMBOL_CHAR );
          buffer.append( ") + \"" );
          break;
        case TILDE_OPERATOR_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) TILDE_OPERATOR_CHAR );
          buffer.append( ") + \"" );
          break;
        case DOUBLE_DAGGER_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DOUBLE_DAGGER_CHAR );
          buffer.append( ") + \"" );
          break;
        case MIDDLE_DOT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) MIDDLE_DOT_CHAR );
          buffer.append( ") + \"" );
          break;
        case ANGLE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ANGLE_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_EGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_EGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREATER_THAN_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREATER_THAN_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_THORN_ICELANDIC_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_THORN_ICELANDIC_CHAR );
          buffer.append( ") + \"" );
          break;
        case OVERLINE_SPACING_OVERSCORE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) OVERLINE_SPACING_OVERSCORE_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_IGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_IGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case DOT_OPERATOR_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DOT_OPERATOR_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_ALPHA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_ALPHA_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_THETA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_THETA_CHAR );
          buffer.append( ") + \"" );
          break;
        case GENERAL_CURRENCY_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GENERAL_CURRENCY_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case DOWNWARD_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DOWNWARD_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_AACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_AACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case PLUS_OR_MINUS_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) PLUS_OR_MINUS_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOZENGE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOZENGE_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_OTILDE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_OTILDE_CHAR );
          buffer.append( ") + \"" );
          break;
        case NARY_PRODUCT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) NARY_PRODUCT_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_ACIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_ACIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case INVERTED_EXCLAMATION_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) INVERTED_EXCLAMATION_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_IACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_IACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case LATIN_SMALL_LIGATURE_OE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LATIN_SMALL_LIGATURE_OE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_XI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_XI_CHAR );
          buffer.append( ") + \"" );
          break;
        case APPROXIMATELY_EQUAL_TO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) APPROXIMATELY_EQUAL_TO_CHAR );
          buffer.append( ") + \"" );
          break;
        case RIGHT_TO_LEFT_MARK_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) RIGHT_TO_LEFT_MARK_CHAR );
          buffer.append( ") + \"" );
          break;
        case DAGGER_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DAGGER_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_IUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_IUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case SQUARE_ROOT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SQUARE_ROOT_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_THORN_ICELANDIC_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_THORN_ICELANDIC_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_AGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_AGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case PER_MILL_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) PER_MILL_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case UP_TACK_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UP_TACK_CHAR );
          buffer.append( ") + \"" );
          break;
        case BULLET_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) BULLET_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_IGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_IGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case NABLA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) NABLA_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_NU_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_NU_CHAR );
          buffer.append( ") + \"" );
          break;
        case SECTION_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SECTION_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case LATIN_SMALL_LETTER_FWITH_HOOK_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LATIN_SMALL_LETTER_FWITH_HOOK_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_AACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_AACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case REGISTERED_TRADEMARK_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) REGISTERED_TRADEMARK_CHAR );
          buffer.append( ") + \"" );
          break;
        case MINUS_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) MINUS_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case APOSTROPHE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) APOSTROPHE_CHAR );
          buffer.append( ") + \"" );
          break;
        case CIRCLED_PLUS_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) CIRCLED_PLUS_CHAR );
          buffer.append( ") + \"" );
          break;
        case LESS_THAN_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LESS_THAN_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_DELTA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_DELTA_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFTWARDS_DOUBLE_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFTWARDS_DOUBLE_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case RIGHT_ANGLE_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) RIGHT_ANGLE_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_ETA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_ETA_CHAR );
          buffer.append( ") + \"" );
          break;
        case DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFT_ANGLE_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFT_ANGLE_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_PSI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_PSI_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_AUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_AUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFT_TO_RIGHT_MARK_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFT_TO_RIGHT_MARK_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_OACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_OACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_AGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_AGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case THERE_EXISTS_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) THERE_EXISTS_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_EGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_EGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case SUBSET_OF_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SUBSET_OF_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_XI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_XI_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_OUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_OUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case EURO_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) EURO_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_UPSILON_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_UPSILON_CHAR );
          buffer.append( ") + \"" );
          break;
        case ELLIPSES_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ELLIPSES_CHAR );
          buffer.append( ") + \"" );
          break;
        case PRIME_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) PRIME_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_CCEDILLA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_CCEDILLA_CHAR );
          buffer.append( ") + \"" );
          break;
        case LATIN_CAPITAL_LIGATURE_OE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LATIN_CAPITAL_LIGATURE_OE_CHAR );
          buffer.append( ") + \"" );
          break;
        case LATIN_CAPITAL_LETTER_SWITH_CARON_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LATIN_CAPITAL_LETTER_SWITH_CARON_CHAR );
          buffer.append( ") + \"" );
          break;
        case SUPERSCRIPT_TWO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SUPERSCRIPT_TWO_CHAR );
          buffer.append( ") + \"" );
          break;
        case NOT_EQUAL_TO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) NOT_EQUAL_TO_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_OGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_OGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case SUPERSCRIPT_ONE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SUPERSCRIPT_ONE_CHAR );
          buffer.append( ") + \"" );
          break;
        case SINGLE_LEFT_POINTING_ANGLE_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SINGLE_LEFT_POINTING_ANGLE_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case BLACK_DIAMOND_SUIT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) BLACK_DIAMOND_SUIT_CHAR );
          buffer.append( ") + \"" );
          break;
        case SCRIPT_CAPITAL_P_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SCRIPT_CAPITAL_P_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_NU_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_NU_CHAR );
          buffer.append( ") + \"" );
          break;
        case TRADEMARK_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) TRADEMARK_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_CHAR );
          buffer.append( ") + \"" );
          break;
        case NARY_SUMMATION_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) NARY_SUMMATION_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_GAMMA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_GAMMA_CHAR );
          buffer.append( ") + \"" );
          break;
        case NOT_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) NOT_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case MASCULINE_ORDINAL_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) MASCULINE_ORDINAL_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_KAPPA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_KAPPA_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_OACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_OACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case BLACK_LETTER_CAPITAL_R_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) BLACK_LETTER_CAPITAL_R_CHAR );
          buffer.append( ") + \"" );
          break;
        case NOT_AN_ELEMENT_OF_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) NOT_AN_ELEMENT_OF_CHAR );
          buffer.append( ") + \"" );
          break;
        case RIGHTWARDS_DOUBLE_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) RIGHTWARDS_DOUBLE_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case EM_DASH_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) EM_DASH_CHAR );
          buffer.append( ") + \"" );
          break;
        case DEGREE_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DEGREE_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case DIVISION_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DIVISION_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_IOTA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_IOTA_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_CCEDILLA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_CCEDILLA_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFT_RIGHT_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFT_RIGHT_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case LATIN_SMALL_LETTER_SWITH_CARON_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LATIN_SMALL_LETTER_SWITH_CARON_CHAR );
          buffer.append( ") + \"" );
          break;
        case NONBREAKING_SPACE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) NONBREAKING_SPACE_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_OGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_OGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case BLACK_HEART_SUIT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) BLACK_HEART_SUIT_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_UUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_UUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case CEDILLA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) CEDILLA_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_UCIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_UCIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOGICAL_OR_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOGICAL_OR_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFT_FLOOR_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFT_FLOOR_CHAR );
          buffer.append( ") + \"" );
          break;
        case INVERTED_QUESTION_MARK_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) INVERTED_QUESTION_MARK_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_OCIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_OCIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case INTERSECTION_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) INTERSECTION_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_SHARPS_GERMAN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_SHARPS_GERMAN_CHAR );
          buffer.append( ") + \"" );
          break;
        case RIGHT_DOUBLE_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) RIGHT_DOUBLE_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_ICIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_ICIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case CENT_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) CENT_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_CHI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_CHI_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFT_DOUBLE_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFT_DOUBLE_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case PROPORTIONAL_TO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) PROPORTIONAL_TO_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_PHI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_PHI_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_ARING_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_ARING_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_TAU_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_TAU_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_RHO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_RHO_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_UACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_UACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREATER_THAN_OR_EQUAL_TO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREATER_THAN_OR_EQUAL_TO_CHAR );
          buffer.append( ") + \"" );
          break;
        case EM_SPACE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) EM_SPACE_CHAR );
          buffer.append( ") + \"" );
          break;
        case ALMOST_EQUAL_TO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ALMOST_EQUAL_TO_CHAR );
          buffer.append( ") + \"" );
          break;
        case DIAERESIS_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DIAERESIS_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_ETH_ICELANDIC_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_ETH_ICELANDIC_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_OMEGA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_OMEGA_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_IUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_IUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_BETA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_BETA_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_EUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_EUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_UGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_UGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case BLACK_CLUB_SUIT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) BLACK_CLUB_SUIT_CHAR );
          buffer.append( ") + \"" );
          break;
        case FEMININE_ORDINAL_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) FEMININE_ORDINAL_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_ECIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_ECIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case THEREFORE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) THEREFORE_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPWARDS_DOUBLE_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPWARDS_DOUBLE_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_UACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_UACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_ALPHA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_ALPHA_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_LAMDA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_LAMDA_CHAR );
          buffer.append( ") + \"" );
          break;
        case SINGLE_LOW_9_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SINGLE_LOW_9_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_YACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_YACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case SUPERSCRIPT_THREE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SUPERSCRIPT_THREE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_FINAL_SIGMA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_FINAL_SIGMA_CHAR );
          buffer.append( ") + \"" );
          break;
        case CIRCLED_TIMES_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) CIRCLED_TIMES_CHAR );
          buffer.append( ") + \"" );
          break;
        case SUPERSET_OF_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SUPERSET_OF_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_AUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_AUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_ZETA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_ZETA_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_ACIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_ACIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_AE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_AE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_THETA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_THETA_CHAR );
          buffer.append( ") + \"" );
          break;
        case ONE_HALF_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ONE_HALF_CHAR );
          buffer.append( ") + \"" );
          break;
        case LESS_THAN_OR_EQUAL_TO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LESS_THAN_OR_EQUAL_TO_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_UGRAVE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_UGRAVE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_UPSILON_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_UPSILON_CHAR );
          buffer.append( ") + \"" );
          break;
        case FOR_ALL_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) FOR_ALL_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_OMICRON_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_OMICRON_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_UCIRCUMFLEX_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_UCIRCUMFLEX_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case EN_DASH_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) EN_DASH_CHAR );
          buffer.append( ") + \"" );
          break;
        case AMPERSAND_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) AMPERSAND_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFT_POINTING_ANGLE_BRACKET_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFT_POINTING_ANGLE_BRACKET_CHAR );
          buffer.append( ") + \"" );
          break;
        case POUND_STERLING_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) POUND_STERLING_CHAR );
          buffer.append( ") + \"" );
          break;
        case DOWNWARDS_DOUBLE_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DOWNWARDS_DOUBLE_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFTWARD_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFTWARD_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_LAMDA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_LAMDA_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_YACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_YACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_ETA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_ETA_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_OMICRON_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_OMICRON_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_YUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_YUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_PSI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_PSI_CHAR );
          buffer.append( ") + \"" );
          break;
        case SMALL_TILDE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SMALL_TILDE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_MU_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_MU_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_ETH_ICELANDIC_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_ETH_ICELANDIC_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_DELTA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_DELTA_CHAR );
          buffer.append( ") + \"" );
          break;
        case NOT_ASUBSET_OF_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) NOT_ASUBSET_OF_CHAR );
          buffer.append( ") + \"" );
          break;
        case ZERO_WIDTH_NON_JOINER_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ZERO_WIDTH_NON_JOINER_CHAR );
          buffer.append( ") + \"" );
          break;
        case ELEMENT_OF_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ELEMENT_OF_CHAR );
          buffer.append( ") + \"" );
          break;
        case EMPTY_SET_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) EMPTY_SET_CHAR );
          buffer.append( ") + \"" );
          break;
        case BLACK_LETTER_CAPITAL_I_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) BLACK_LETTER_CAPITAL_I_CHAR );
          buffer.append( ") + \"" );
          break;
        case RIGHT_CEILING_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) RIGHT_CEILING_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_AE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_AE_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFT_CEILING_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFT_CEILING_CHAR );
          buffer.append( ") + \"" );
          break;
        case PARAGRAPH_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) PARAGRAPH_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case RIGHT_POINTING_ANGLE_BRACKET_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) RIGHT_POINTING_ANGLE_BRACKET_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_UPSILON_WITH_HOOK_SYMBOL_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_UPSILON_WITH_HOOK_SYMBOL_CHAR );
          buffer.append( ") + \"" );
          break;
        case MICRO_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) MICRO_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;
        case RIGHTWARD_ARROW_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) RIGHTWARD_ARROW_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOGICAL_AND_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOGICAL_AND_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_SIGMA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_SIGMA_CHAR );
          buffer.append( ") + \"" );
          break;
        case DOUBLE_PRIME_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) DOUBLE_PRIME_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_NTILDE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_NTILDE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_GAMMA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_GAMMA_CHAR );
          buffer.append( ") + \"" );
          break;
        case ASTERISK_OPERATOR_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ASTERISK_OPERATOR_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_PI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_PI_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_KAPPA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_KAPPA_CHAR );
          buffer.append( ") + \"" );
          break;
        case IDENTICAL_TO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) IDENTICAL_TO_CHAR );
          buffer.append( ") + \"" );
          break;
        case SINGLE_RIGHT_POINTING_ANGLE_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SINGLE_RIGHT_POINTING_ANGLE_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_MU_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_MU_CHAR );
          buffer.append( ") + \"" );
          break;
        case CONTAINS_AS_MEMBER_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) CONTAINS_AS_MEMBER_CHAR );
          buffer.append( ") + \"" );
          break;
        case EN_SPACE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) EN_SPACE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_BETA_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_BETA_CHAR );
          buffer.append( ") + \"" );
          break;
        case THREE_FOURTHS_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) THREE_FOURTHS_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_EUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_EUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_ATILDE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_ATILDE_CHAR );
          buffer.append( ") + \"" );
          break;
        case ONE_FOURTH_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) ONE_FOURTH_CHAR );
          buffer.append( ") + \"" );
          break;
        case RIGHT_SINGLE_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) RIGHT_SINGLE_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_PHI_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_PHI_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_CAPITAL_LETTER_EPSILON_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_CAPITAL_LETTER_EPSILON_CHAR );
          buffer.append( ") + \"" );
          break;
        case LOWERCASE_NTILDE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LOWERCASE_NTILDE_CHAR );
          buffer.append( ") + \"" );
          break;
        case LEFT_SINGLE_QUOTE_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) LEFT_SINGLE_QUOTE_CHAR );
          buffer.append( ") + \"" );
          break;
        case GREEK_SMALL_LETTER_TAU_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) GREEK_SMALL_LETTER_TAU_CHAR );
          buffer.append( ") + \"" );
          break;
        case SOFT_HYPHEN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SOFT_HYPHEN_CHAR );
          buffer.append( ") + \"" );
          break;
        case SUPERSET_OF_OR_EQUAL_TO_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) SUPERSET_OF_OR_EQUAL_TO_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_EACUTE_ACCENT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_EACUTE_ACCENT_CHAR );
          buffer.append( ") + \"" );
          break;
        case PARTIAL_DIFFERENTIAL_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) PARTIAL_DIFFERENTIAL_CHAR );
          buffer.append( ") + \"" );
          break;
        case UPPERCASE_OUMLAUT_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) UPPERCASE_OUMLAUT_CHAR );
          buffer.append( ") + \"" );
          break;
        case YEN_SIGN_CHAR :
          buffer.append( "\" + (char) (" );
          buffer.append( (int) YEN_SIGN_CHAR );
          buffer.append( ") + \"" );
          break;

        default :
          buffer.append( pEingabe.charAt( akt_index ) );
      }

      akt_index++;
    }
    return buffer.toString();
  }

  public static String getHtmlSeite()
  {
    StringBuffer str_buf_ergebnis = new StringBuffer();

    str_buf_ergebnis.append( "<html>\n" );
    str_buf_ergebnis.append( "<meta http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\">\n" );
    str_buf_ergebnis.append( "<head>\n" );
    str_buf_ergebnis.append( "<title>Uebersicht HTML-Quotes</title>\n" );
    str_buf_ergebnis.append( "</head>\n" );
    str_buf_ergebnis.append( "<body>\n" );
    str_buf_ergebnis.append( "<table cellspacing=\"3px\" cellpadding=\"3\" border=\"1\" >\n" );
    str_buf_ergebnis.append( getTabSpalte( ACUTE_ACCENT_CHAR, ACUTE_ACCENT_REPLACE, ACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ALEF_SYMBOL_CHAR, ALEF_SYMBOL_REPLACE, ALEF_SYMBOL_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ALMOST_EQUAL_TO_CHAR, ALMOST_EQUAL_TO_REPLACE, ALMOST_EQUAL_TO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( AMPERSAND_CHAR, AMPERSAND_REPLACE, AMPERSAND_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ANGLE_CHAR, ANGLE_REPLACE, ANGLE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( APOSTROPHE_CHAR, APOSTROPHE_REPLACE, APOSTROPHE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( APPROXIMATELY_EQUAL_TO_CHAR, APPROXIMATELY_EQUAL_TO_REPLACE, APPROXIMATELY_EQUAL_TO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ASTERISK_OPERATOR_CHAR, ASTERISK_OPERATOR_REPLACE, ASTERISK_OPERATOR_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( BLACK_CLUB_SUIT_CHAR, BLACK_CLUB_SUIT_REPLACE, BLACK_CLUB_SUIT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( BLACK_DIAMOND_SUIT_CHAR, BLACK_DIAMOND_SUIT_REPLACE, BLACK_DIAMOND_SUIT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( BLACK_HEART_SUIT_CHAR, BLACK_HEART_SUIT_REPLACE, BLACK_HEART_SUIT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( BLACK_LETTER_CAPITAL_I_CHAR, BLACK_LETTER_CAPITAL_I_REPLACE, BLACK_LETTER_CAPITAL_I_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( BLACK_LETTER_CAPITAL_R_CHAR, BLACK_LETTER_CAPITAL_R_REPLACE, BLACK_LETTER_CAPITAL_R_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( BLACK_SPADE_SUIT_CHAR, BLACK_SPADE_SUIT_REPLACE, BLACK_SPADE_SUIT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( BROKEN_BAR_CHAR, BROKEN_BAR_REPLACE, BROKEN_BAR_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( BULLET_CHAR, BULLET_REPLACE, BULLET_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( CEDILLA_CHAR, CEDILLA_REPLACE, CEDILLA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( CENT_SIGN_CHAR, CENT_SIGN_REPLACE, CENT_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( CIRCLED_PLUS_CHAR, CIRCLED_PLUS_REPLACE, CIRCLED_PLUS_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( CIRCLED_TIMES_CHAR, CIRCLED_TIMES_REPLACE, CIRCLED_TIMES_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( CONTAINS_AS_MEMBER_CHAR, CONTAINS_AS_MEMBER_REPLACE, CONTAINS_AS_MEMBER_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( COPYRIGHT_CHAR, COPYRIGHT_REPLACE, COPYRIGHT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DAGGER_CHAR, DAGGER_REPLACE, DAGGER_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DEGREE_SIGN_CHAR, DEGREE_SIGN_REPLACE, DEGREE_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DIAERESIS_CHAR, DIAERESIS_REPLACE, DIAERESIS_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DIVISION_SIGN_CHAR, DIVISION_SIGN_REPLACE, DIVISION_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DOT_OPERATOR_CHAR, DOT_OPERATOR_REPLACE, DOT_OPERATOR_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DOUBLE_DAGGER_CHAR, DOUBLE_DAGGER_REPLACE, DOUBLE_DAGGER_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DOUBLE_LOW_9_QUOTE_CHAR, DOUBLE_LOW_9_QUOTE_REPLACE, DOUBLE_LOW_9_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DOUBLE_PRIME_CHAR, DOUBLE_PRIME_REPLACE, DOUBLE_PRIME_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DOUBLE_QUOTATION_MARK_CHAR, DOUBLE_QUOTATION_MARK_REPLACE, DOUBLE_QUOTATION_MARK_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_CHAR, DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_REPLACE, DOWNWARDS_ARROW_WITH_CORNER_LEFTWARDS_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DOWNWARDS_DOUBLE_ARROW_CHAR, DOWNWARDS_DOUBLE_ARROW_REPLACE, DOWNWARDS_DOUBLE_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( DOWNWARD_ARROW_CHAR, DOWNWARD_ARROW_REPLACE, DOWNWARD_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ELEMENT_OF_CHAR, ELEMENT_OF_REPLACE, ELEMENT_OF_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ELLIPSES_CHAR, ELLIPSES_REPLACE, ELLIPSES_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( EMPTY_SET_CHAR, EMPTY_SET_REPLACE, EMPTY_SET_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( EM_DASH_CHAR, EM_DASH_REPLACE, EM_DASH_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( EM_SPACE_CHAR, EM_SPACE_REPLACE, EM_SPACE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( EN_DASH_CHAR, EN_DASH_REPLACE, EN_DASH_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( EN_SPACE_CHAR, EN_SPACE_REPLACE, EN_SPACE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( EURO_SIGN_CHAR, EURO_SIGN_REPLACE, EURO_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( FEMININE_ORDINAL_CHAR, FEMININE_ORDINAL_REPLACE, FEMININE_ORDINAL_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( FOR_ALL_CHAR, FOR_ALL_REPLACE, FOR_ALL_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GENERAL_CURRENCY_SIGN_CHAR, GENERAL_CURRENCY_SIGN_REPLACE, GENERAL_CURRENCY_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREATER_THAN_OR_EQUAL_TO_CHAR, GREATER_THAN_OR_EQUAL_TO_REPLACE, GREATER_THAN_OR_EQUAL_TO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREATER_THAN_SIGN_CHAR, GREATER_THAN_SIGN_REPLACE, GREATER_THAN_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_ALPHA_CHAR, GREEK_CAPITAL_LETTER_ALPHA_REPLACE, GREEK_CAPITAL_LETTER_ALPHA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_BETA_CHAR, GREEK_CAPITAL_LETTER_BETA_REPLACE, GREEK_CAPITAL_LETTER_BETA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_CHI_CHAR, GREEK_CAPITAL_LETTER_CHI_REPLACE, GREEK_CAPITAL_LETTER_CHI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_DELTA_CHAR, GREEK_CAPITAL_LETTER_DELTA_REPLACE, GREEK_CAPITAL_LETTER_DELTA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_EPSILON_CHAR, GREEK_CAPITAL_LETTER_EPSILON_REPLACE, GREEK_CAPITAL_LETTER_EPSILON_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_ETA_CHAR, GREEK_CAPITAL_LETTER_ETA_REPLACE, GREEK_CAPITAL_LETTER_ETA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_GAMMA_CHAR, GREEK_CAPITAL_LETTER_GAMMA_REPLACE, GREEK_CAPITAL_LETTER_GAMMA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_IOTA_CHAR, GREEK_CAPITAL_LETTER_IOTA_REPLACE, GREEK_CAPITAL_LETTER_IOTA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_KAPPA_CHAR, GREEK_CAPITAL_LETTER_KAPPA_REPLACE, GREEK_CAPITAL_LETTER_KAPPA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_LAMDA_CHAR, GREEK_CAPITAL_LETTER_LAMDA_REPLACE, GREEK_CAPITAL_LETTER_LAMDA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_MU_CHAR, GREEK_CAPITAL_LETTER_MU_REPLACE, GREEK_CAPITAL_LETTER_MU_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_NU_CHAR, GREEK_CAPITAL_LETTER_NU_REPLACE, GREEK_CAPITAL_LETTER_NU_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_OMEGA_CHAR, GREEK_CAPITAL_LETTER_OMEGA_REPLACE, GREEK_CAPITAL_LETTER_OMEGA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_OMICRON_CHAR, GREEK_CAPITAL_LETTER_OMICRON_REPLACE, GREEK_CAPITAL_LETTER_OMICRON_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_PHI_CHAR, GREEK_CAPITAL_LETTER_PHI_REPLACE, GREEK_CAPITAL_LETTER_PHI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_PI_CHAR, GREEK_CAPITAL_LETTER_PI_REPLACE, GREEK_CAPITAL_LETTER_PI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_PSI_CHAR, GREEK_CAPITAL_LETTER_PSI_REPLACE, GREEK_CAPITAL_LETTER_PSI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_RHO_CHAR, GREEK_CAPITAL_LETTER_RHO_REPLACE, GREEK_CAPITAL_LETTER_RHO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_SIGMA_CHAR, GREEK_CAPITAL_LETTER_SIGMA_REPLACE, GREEK_CAPITAL_LETTER_SIGMA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_TAU_CHAR, GREEK_CAPITAL_LETTER_TAU_REPLACE, GREEK_CAPITAL_LETTER_TAU_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_THETA_CHAR, GREEK_CAPITAL_LETTER_THETA_REPLACE, GREEK_CAPITAL_LETTER_THETA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_UPSILON_CHAR, GREEK_CAPITAL_LETTER_UPSILON_REPLACE, GREEK_CAPITAL_LETTER_UPSILON_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_XI_CHAR, GREEK_CAPITAL_LETTER_XI_REPLACE, GREEK_CAPITAL_LETTER_XI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_CAPITAL_LETTER_ZETA_CHAR, GREEK_CAPITAL_LETTER_ZETA_REPLACE, GREEK_CAPITAL_LETTER_ZETA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_PI_SYMBOL_CHAR, GREEK_PI_SYMBOL_REPLACE, GREEK_PI_SYMBOL_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_ALPHA_CHAR, GREEK_SMALL_LETTER_ALPHA_REPLACE, GREEK_SMALL_LETTER_ALPHA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_BETA_CHAR, GREEK_SMALL_LETTER_BETA_REPLACE, GREEK_SMALL_LETTER_BETA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_CHI_CHAR, GREEK_SMALL_LETTER_CHI_REPLACE, GREEK_SMALL_LETTER_CHI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_DELTA_CHAR, GREEK_SMALL_LETTER_DELTA_REPLACE, GREEK_SMALL_LETTER_DELTA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_EPSILON_CHAR, GREEK_SMALL_LETTER_EPSILON_REPLACE, GREEK_SMALL_LETTER_EPSILON_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_ETA_CHAR, GREEK_SMALL_LETTER_ETA_REPLACE, GREEK_SMALL_LETTER_ETA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_FINAL_SIGMA_CHAR, GREEK_SMALL_LETTER_FINAL_SIGMA_REPLACE, GREEK_SMALL_LETTER_FINAL_SIGMA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_GAMMA_CHAR, GREEK_SMALL_LETTER_GAMMA_REPLACE, GREEK_SMALL_LETTER_GAMMA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_IOTA_CHAR, GREEK_SMALL_LETTER_IOTA_REPLACE, GREEK_SMALL_LETTER_IOTA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_KAPPA_CHAR, GREEK_SMALL_LETTER_KAPPA_REPLACE, GREEK_SMALL_LETTER_KAPPA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_LAMDA_CHAR, GREEK_SMALL_LETTER_LAMDA_REPLACE, GREEK_SMALL_LETTER_LAMDA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_MU_CHAR, GREEK_SMALL_LETTER_MU_REPLACE, GREEK_SMALL_LETTER_MU_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_NU_CHAR, GREEK_SMALL_LETTER_NU_REPLACE, GREEK_SMALL_LETTER_NU_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_OMEGA_CHAR, GREEK_SMALL_LETTER_OMEGA_REPLACE, GREEK_SMALL_LETTER_OMEGA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_OMICRON_CHAR, GREEK_SMALL_LETTER_OMICRON_REPLACE, GREEK_SMALL_LETTER_OMICRON_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_PHI_CHAR, GREEK_SMALL_LETTER_PHI_REPLACE, GREEK_SMALL_LETTER_PHI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_PI_CHAR, GREEK_SMALL_LETTER_PI_REPLACE, GREEK_SMALL_LETTER_PI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_PSI_CHAR, GREEK_SMALL_LETTER_PSI_REPLACE, GREEK_SMALL_LETTER_PSI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_RHO_CHAR, GREEK_SMALL_LETTER_RHO_REPLACE, GREEK_SMALL_LETTER_RHO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_SIGMA_CHAR, GREEK_SMALL_LETTER_SIGMA_REPLACE, GREEK_SMALL_LETTER_SIGMA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_TAU_CHAR, GREEK_SMALL_LETTER_TAU_REPLACE, GREEK_SMALL_LETTER_TAU_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_THETA_CHAR, GREEK_SMALL_LETTER_THETA_REPLACE, GREEK_SMALL_LETTER_THETA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_UPSILON_CHAR, GREEK_SMALL_LETTER_UPSILON_REPLACE, GREEK_SMALL_LETTER_UPSILON_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_XI_CHAR, GREEK_SMALL_LETTER_XI_REPLACE, GREEK_SMALL_LETTER_XI_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_SMALL_LETTER_ZETA_CHAR, GREEK_SMALL_LETTER_ZETA_REPLACE, GREEK_SMALL_LETTER_ZETA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_THETA_SYMBOL_CHAR, GREEK_THETA_SYMBOL_REPLACE, GREEK_THETA_SYMBOL_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( GREEK_UPSILON_WITH_HOOK_SYMBOL_CHAR, GREEK_UPSILON_WITH_HOOK_SYMBOL_REPLACE, GREEK_UPSILON_WITH_HOOK_SYMBOL_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( IDENTICAL_TO_CHAR, IDENTICAL_TO_REPLACE, IDENTICAL_TO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( INFINITY_CHAR, INFINITY_REPLACE, INFINITY_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( INTEGRAL_CHAR, INTEGRAL_REPLACE, INTEGRAL_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( INTERSECTION_CHAR, INTERSECTION_REPLACE, INTERSECTION_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( INVERTED_EXCLAMATION_CHAR, INVERTED_EXCLAMATION_REPLACE, INVERTED_EXCLAMATION_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( INVERTED_QUESTION_MARK_CHAR, INVERTED_QUESTION_MARK_REPLACE, INVERTED_QUESTION_MARK_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LATIN_CAPITAL_LETTER_SWITH_CARON_CHAR, LATIN_CAPITAL_LETTER_SWITH_CARON_REPLACE, LATIN_CAPITAL_LETTER_SWITH_CARON_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_CHAR, LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_REPLACE, LATIN_CAPITAL_LETTER_YWITH_DIAERESIS_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LATIN_CAPITAL_LIGATURE_OE_CHAR, LATIN_CAPITAL_LIGATURE_OE_REPLACE, LATIN_CAPITAL_LIGATURE_OE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LATIN_SMALL_LETTER_FWITH_HOOK_CHAR, LATIN_SMALL_LETTER_FWITH_HOOK_REPLACE, LATIN_SMALL_LETTER_FWITH_HOOK_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LATIN_SMALL_LETTER_SWITH_CARON_CHAR, LATIN_SMALL_LETTER_SWITH_CARON_REPLACE, LATIN_SMALL_LETTER_SWITH_CARON_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LATIN_SMALL_LIGATURE_OE_CHAR, LATIN_SMALL_LIGATURE_OE_REPLACE, LATIN_SMALL_LIGATURE_OE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFTWARDS_DOUBLE_ARROW_CHAR, LEFTWARDS_DOUBLE_ARROW_REPLACE, LEFTWARDS_DOUBLE_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFTWARD_ARROW_CHAR, LEFTWARD_ARROW_REPLACE, LEFTWARD_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFT_ANGLE_QUOTE_CHAR, LEFT_ANGLE_QUOTE_REPLACE, LEFT_ANGLE_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFT_CEILING_CHAR, LEFT_CEILING_REPLACE, LEFT_CEILING_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFT_DOUBLE_QUOTE_CHAR, LEFT_DOUBLE_QUOTE_REPLACE, LEFT_DOUBLE_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFT_FLOOR_CHAR, LEFT_FLOOR_REPLACE, LEFT_FLOOR_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFT_POINTING_ANGLE_BRACKET_CHAR, LEFT_POINTING_ANGLE_BRACKET_REPLACE, LEFT_POINTING_ANGLE_BRACKET_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFT_RIGHT_ARROW_CHAR, LEFT_RIGHT_ARROW_REPLACE, LEFT_RIGHT_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFT_RIGHT_DOUBLE_ARROW_CHAR, LEFT_RIGHT_DOUBLE_ARROW_REPLACE, LEFT_RIGHT_DOUBLE_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFT_SINGLE_QUOTE_CHAR, LEFT_SINGLE_QUOTE_REPLACE, LEFT_SINGLE_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LEFT_TO_RIGHT_MARK_CHAR, LEFT_TO_RIGHT_MARK_REPLACE, LEFT_TO_RIGHT_MARK_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LESS_THAN_OR_EQUAL_TO_CHAR, LESS_THAN_OR_EQUAL_TO_REPLACE, LESS_THAN_OR_EQUAL_TO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LESS_THAN_SIGN_CHAR, LESS_THAN_SIGN_REPLACE, LESS_THAN_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOGICAL_AND_CHAR, LOGICAL_AND_REPLACE, LOGICAL_AND_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOGICAL_OR_CHAR, LOGICAL_OR_REPLACE, LOGICAL_OR_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_AACUTE_ACCENT_CHAR, LOWERCASE_AACUTE_ACCENT_REPLACE, LOWERCASE_AACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_ACIRCUMFLEX_ACCENT_CHAR, LOWERCASE_ACIRCUMFLEX_ACCENT_REPLACE, LOWERCASE_ACIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_AE_CHAR, LOWERCASE_AE_REPLACE, LOWERCASE_AE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_AGRAVE_ACCENT_CHAR, LOWERCASE_AGRAVE_ACCENT_REPLACE, LOWERCASE_AGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_ARING_CHAR, LOWERCASE_ARING_REPLACE, LOWERCASE_ARING_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_ATILDE_CHAR, LOWERCASE_ATILDE_REPLACE, LOWERCASE_ATILDE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_AUMLAUT_CHAR, LOWERCASE_AUMLAUT_REPLACE, LOWERCASE_AUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_CCEDILLA_CHAR, LOWERCASE_CCEDILLA_REPLACE, LOWERCASE_CCEDILLA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_EACUTE_ACCENT_CHAR, LOWERCASE_EACUTE_ACCENT_REPLACE, LOWERCASE_EACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_ECIRCUMFLEX_ACCENT_CHAR, LOWERCASE_ECIRCUMFLEX_ACCENT_REPLACE, LOWERCASE_ECIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_EGRAVE_ACCENT_CHAR, LOWERCASE_EGRAVE_ACCENT_REPLACE, LOWERCASE_EGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_ETH_ICELANDIC_CHAR, LOWERCASE_ETH_ICELANDIC_REPLACE, LOWERCASE_ETH_ICELANDIC_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_EUMLAUT_CHAR, LOWERCASE_EUMLAUT_REPLACE, LOWERCASE_EUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_IACUTE_ACCENT_CHAR, LOWERCASE_IACUTE_ACCENT_REPLACE, LOWERCASE_IACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_ICIRCUMFLEX_ACCENT_CHAR, LOWERCASE_ICIRCUMFLEX_ACCENT_REPLACE, LOWERCASE_ICIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_IGRAVE_ACCENT_CHAR, LOWERCASE_IGRAVE_ACCENT_REPLACE, LOWERCASE_IGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_IUMLAUT_CHAR, LOWERCASE_IUMLAUT_REPLACE, LOWERCASE_IUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_NTILDE_CHAR, LOWERCASE_NTILDE_REPLACE, LOWERCASE_NTILDE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_OACUTE_ACCENT_CHAR, LOWERCASE_OACUTE_ACCENT_REPLACE, LOWERCASE_OACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_OCIRCUMFLEX_ACCENT_CHAR, LOWERCASE_OCIRCUMFLEX_ACCENT_REPLACE, LOWERCASE_OCIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_OGRAVE_ACCENT_CHAR, LOWERCASE_OGRAVE_ACCENT_REPLACE, LOWERCASE_OGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_OSLASH_CHAR, LOWERCASE_OSLASH_REPLACE, LOWERCASE_OSLASH_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_OTILDE_CHAR, LOWERCASE_OTILDE_REPLACE, LOWERCASE_OTILDE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_OUMLAUT_CHAR, LOWERCASE_OUMLAUT_REPLACE, LOWERCASE_OUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_SHARPS_GERMAN_CHAR, LOWERCASE_SHARPS_GERMAN_REPLACE, LOWERCASE_SHARPS_GERMAN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_THORN_ICELANDIC_CHAR, LOWERCASE_THORN_ICELANDIC_REPLACE, LOWERCASE_THORN_ICELANDIC_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_UACUTE_ACCENT_CHAR, LOWERCASE_UACUTE_ACCENT_REPLACE, LOWERCASE_UACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_UCIRCUMFLEX_ACCENT_CHAR, LOWERCASE_UCIRCUMFLEX_ACCENT_REPLACE, LOWERCASE_UCIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_UGRAVE_ACCENT_CHAR, LOWERCASE_UGRAVE_ACCENT_REPLACE, LOWERCASE_UGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_UUMLAUT_CHAR, LOWERCASE_UUMLAUT_REPLACE, LOWERCASE_UUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_YACUTE_ACCENT_CHAR, LOWERCASE_YACUTE_ACCENT_REPLACE, LOWERCASE_YACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOWERCASE_YUMLAUT_CHAR, LOWERCASE_YUMLAUT_REPLACE, LOWERCASE_YUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( LOZENGE_CHAR, LOZENGE_REPLACE, LOZENGE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( MACRON_CHAR, MACRON_REPLACE, MACRON_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( MASCULINE_ORDINAL_CHAR, MASCULINE_ORDINAL_REPLACE, MASCULINE_ORDINAL_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( MICRO_SIGN_CHAR, MICRO_SIGN_REPLACE, MICRO_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( MIDDLE_DOT_CHAR, MIDDLE_DOT_REPLACE, MIDDLE_DOT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( MINUS_SIGN_CHAR, MINUS_SIGN_REPLACE, MINUS_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( MODIFIER_LETTER_CIRCUMFLEX_ACCENT_CHAR, MODIFIER_LETTER_CIRCUMFLEX_ACCENT_REPLACE, MODIFIER_LETTER_CIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( MULTIPLICATION_SIGN_CHAR, MULTIPLICATION_SIGN_REPLACE, MULTIPLICATION_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( NABLA_CHAR, NABLA_REPLACE, NABLA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( NARY_PRODUCT_CHAR, NARY_PRODUCT_REPLACE, NARY_PRODUCT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( NARY_SUMMATION_CHAR, NARY_SUMMATION_REPLACE, NARY_SUMMATION_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( NONBREAKING_SPACE_CHAR, NONBREAKING_SPACE_REPLACE, NONBREAKING_SPACE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( NOT_AN_ELEMENT_OF_CHAR, NOT_AN_ELEMENT_OF_REPLACE, NOT_AN_ELEMENT_OF_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( NOT_ASUBSET_OF_CHAR, NOT_ASUBSET_OF_REPLACE, NOT_ASUBSET_OF_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( NOT_EQUAL_TO_CHAR, NOT_EQUAL_TO_REPLACE, NOT_EQUAL_TO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( NOT_SIGN_CHAR, NOT_SIGN_REPLACE, NOT_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ONE_FOURTH_CHAR, ONE_FOURTH_REPLACE, ONE_FOURTH_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ONE_HALF_CHAR, ONE_HALF_REPLACE, ONE_HALF_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( OVERLINE_SPACING_OVERSCORE_CHAR, OVERLINE_SPACING_OVERSCORE_REPLACE, OVERLINE_SPACING_OVERSCORE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( PARAGRAPH_SIGN_CHAR, PARAGRAPH_SIGN_REPLACE, PARAGRAPH_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( PARTIAL_DIFFERENTIAL_CHAR, PARTIAL_DIFFERENTIAL_REPLACE, PARTIAL_DIFFERENTIAL_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( PER_MILL_SIGN_CHAR, PER_MILL_SIGN_REPLACE, PER_MILL_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( PLUS_OR_MINUS_CHAR, PLUS_OR_MINUS_REPLACE, PLUS_OR_MINUS_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( POUND_STERLING_CHAR, POUND_STERLING_REPLACE, POUND_STERLING_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( PRIME_CHAR, PRIME_REPLACE, PRIME_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( PROPORTIONAL_TO_CHAR, PROPORTIONAL_TO_REPLACE, PROPORTIONAL_TO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( REGISTERED_TRADEMARK_CHAR, REGISTERED_TRADEMARK_REPLACE, REGISTERED_TRADEMARK_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( RIGHTWARDS_DOUBLE_ARROW_CHAR, RIGHTWARDS_DOUBLE_ARROW_REPLACE, RIGHTWARDS_DOUBLE_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( RIGHTWARD_ARROW_CHAR, RIGHTWARD_ARROW_REPLACE, RIGHTWARD_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( RIGHT_ANGLE_QUOTE_CHAR, RIGHT_ANGLE_QUOTE_REPLACE, RIGHT_ANGLE_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( RIGHT_CEILING_CHAR, RIGHT_CEILING_REPLACE, RIGHT_CEILING_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( RIGHT_DOUBLE_QUOTE_CHAR, RIGHT_DOUBLE_QUOTE_REPLACE, RIGHT_DOUBLE_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( RIGHT_FLOOR_CHAR, RIGHT_FLOOR_REPLACE, RIGHT_FLOOR_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( RIGHT_POINTING_ANGLE_BRACKET_CHAR, RIGHT_POINTING_ANGLE_BRACKET_REPLACE, RIGHT_POINTING_ANGLE_BRACKET_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( RIGHT_SINGLE_QUOTE_CHAR, RIGHT_SINGLE_QUOTE_REPLACE, RIGHT_SINGLE_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( RIGHT_TO_LEFT_MARK_CHAR, RIGHT_TO_LEFT_MARK_REPLACE, RIGHT_TO_LEFT_MARK_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SCRIPT_CAPITAL_P_CHAR, SCRIPT_CAPITAL_P_REPLACE, SCRIPT_CAPITAL_P_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SECTION_SIGN_CHAR, SECTION_SIGN_REPLACE, SECTION_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SINGLE_LEFT_POINTING_ANGLE_QUOTE_CHAR, SINGLE_LEFT_POINTING_ANGLE_QUOTE_REPLACE, SINGLE_LEFT_POINTING_ANGLE_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SINGLE_LOW_9_QUOTE_CHAR, SINGLE_LOW_9_QUOTE_REPLACE, SINGLE_LOW_9_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SINGLE_RIGHT_POINTING_ANGLE_QUOTE_CHAR, SINGLE_RIGHT_POINTING_ANGLE_QUOTE_REPLACE, SINGLE_RIGHT_POINTING_ANGLE_QUOTE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SLASH_CHAR, SLASH_REPLACE, SLASH_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SMALL_TILDE_CHAR, SMALL_TILDE_REPLACE, SMALL_TILDE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SOFT_HYPHEN_CHAR, SOFT_HYPHEN_REPLACE, SOFT_HYPHEN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SQUARE_ROOT_CHAR, SQUARE_ROOT_REPLACE, SQUARE_ROOT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SUBSET_OF_CHAR, SUBSET_OF_REPLACE, SUBSET_OF_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SUBSET_OF_OR_EQUAL_TO_CHAR, SUBSET_OF_OR_EQUAL_TO_REPLACE, SUBSET_OF_OR_EQUAL_TO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SUPERSCRIPT_ONE_CHAR, SUPERSCRIPT_ONE_REPLACE, SUPERSCRIPT_ONE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SUPERSCRIPT_THREE_CHAR, SUPERSCRIPT_THREE_REPLACE, SUPERSCRIPT_THREE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SUPERSCRIPT_TWO_CHAR, SUPERSCRIPT_TWO_REPLACE, SUPERSCRIPT_TWO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SUPERSET_OF_CHAR, SUPERSET_OF_REPLACE, SUPERSET_OF_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( SUPERSET_OF_OR_EQUAL_TO_CHAR, SUPERSET_OF_OR_EQUAL_TO_REPLACE, SUPERSET_OF_OR_EQUAL_TO_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( THEREFORE_CHAR, THEREFORE_REPLACE, THEREFORE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( THERE_EXISTS_CHAR, THERE_EXISTS_REPLACE, THERE_EXISTS_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( THIN_SPACE_CHAR, THIN_SPACE_REPLACE, THIN_SPACE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( THREE_FOURTHS_CHAR, THREE_FOURTHS_REPLACE, THREE_FOURTHS_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( TILDE_OPERATOR_CHAR, TILDE_OPERATOR_REPLACE, TILDE_OPERATOR_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( TRADEMARK_SIGN_CHAR, TRADEMARK_SIGN_REPLACE, TRADEMARK_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UNION_CHAR, UNION_REPLACE, UNION_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_AACUTE_ACCENT_CHAR, UPPERCASE_AACUTE_ACCENT_REPLACE, UPPERCASE_AACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_ACIRCUMFLEX_ACCENT_CHAR, UPPERCASE_ACIRCUMFLEX_ACCENT_REPLACE, UPPERCASE_ACIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_AE_CHAR, UPPERCASE_AE_REPLACE, UPPERCASE_AE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_AGRAVE_ACCENT_CHAR, UPPERCASE_AGRAVE_ACCENT_REPLACE, UPPERCASE_AGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_ARING_CHAR, UPPERCASE_ARING_REPLACE, UPPERCASE_ARING_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_ATILDE_CHAR, UPPERCASE_ATILDE_REPLACE, UPPERCASE_ATILDE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_AUMLAUT_CHAR, UPPERCASE_AUMLAUT_REPLACE, UPPERCASE_AUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_CCEDILLA_CHAR, UPPERCASE_CCEDILLA_REPLACE, UPPERCASE_CCEDILLA_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_EACUTE_ACCENT_CHAR, UPPERCASE_EACUTE_ACCENT_REPLACE, UPPERCASE_EACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_ECIRCUMFLEX_ACCENT_CHAR, UPPERCASE_ECIRCUMFLEX_ACCENT_REPLACE, UPPERCASE_ECIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_EGRAVE_ACCENT_CHAR, UPPERCASE_EGRAVE_ACCENT_REPLACE, UPPERCASE_EGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_ETH_ICELANDIC_CHAR, UPPERCASE_ETH_ICELANDIC_REPLACE, UPPERCASE_ETH_ICELANDIC_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_EUMLAUT_CHAR, UPPERCASE_EUMLAUT_REPLACE, UPPERCASE_EUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_IACUTE_ACCENT_CHAR, UPPERCASE_IACUTE_ACCENT_REPLACE, UPPERCASE_IACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_ICIRCUMFLEX_ACCENT_CHAR, UPPERCASE_ICIRCUMFLEX_ACCENT_REPLACE, UPPERCASE_ICIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_IGRAVE_ACCENT_CHAR, UPPERCASE_IGRAVE_ACCENT_REPLACE, UPPERCASE_IGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_IUMLAUT_CHAR, UPPERCASE_IUMLAUT_REPLACE, UPPERCASE_IUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_NTILDE_CHAR, UPPERCASE_NTILDE_REPLACE, UPPERCASE_NTILDE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_OACUTE_ACCENT_CHAR, UPPERCASE_OACUTE_ACCENT_REPLACE, UPPERCASE_OACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_OCIRCUMFLEX_ACCENT_CHAR, UPPERCASE_OCIRCUMFLEX_ACCENT_REPLACE, UPPERCASE_OCIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_OGRAVE_ACCENT_CHAR, UPPERCASE_OGRAVE_ACCENT_REPLACE, UPPERCASE_OGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_OSLASH_CHAR, UPPERCASE_OSLASH_REPLACE, UPPERCASE_OSLASH_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_OTILDE_CHAR, UPPERCASE_OTILDE_REPLACE, UPPERCASE_OTILDE_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_OUMLAUT_CHAR, UPPERCASE_OUMLAUT_REPLACE, UPPERCASE_OUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_THORN_ICELANDIC_CHAR, UPPERCASE_THORN_ICELANDIC_REPLACE, UPPERCASE_THORN_ICELANDIC_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_UACUTE_ACCENT_CHAR, UPPERCASE_UACUTE_ACCENT_REPLACE, UPPERCASE_UACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_UCIRCUMFLEX_ACCENT_CHAR, UPPERCASE_UCIRCUMFLEX_ACCENT_REPLACE, UPPERCASE_UCIRCUMFLEX_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_UGRAVE_ACCENT_CHAR, UPPERCASE_UGRAVE_ACCENT_REPLACE, UPPERCASE_UGRAVE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_UUMLAUT_CHAR, UPPERCASE_UUMLAUT_REPLACE, UPPERCASE_UUMLAUT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPPERCASE_YACUTE_ACCENT_CHAR, UPPERCASE_YACUTE_ACCENT_REPLACE, UPPERCASE_YACUTE_ACCENT_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPWARDS_DOUBLE_ARROW_CHAR, UPWARDS_DOUBLE_ARROW_REPLACE, UPWARDS_DOUBLE_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UPWARD_ARROW_CHAR, UPWARD_ARROW_REPLACE, UPWARD_ARROW_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( UP_TACK_CHAR, UP_TACK_REPLACE, UP_TACK_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( YEN_SIGN_CHAR, YEN_SIGN_REPLACE, YEN_SIGN_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ZERO_WIDTH_JOINER_CHAR, ZERO_WIDTH_JOINER_REPLACE, ZERO_WIDTH_JOINER_BEZ ) );
    str_buf_ergebnis.append( getTabSpalte( ZERO_WIDTH_NON_JOINER_CHAR, ZERO_WIDTH_NON_JOINER_REPLACE, ZERO_WIDTH_NON_JOINER_BEZ ) );
    str_buf_ergebnis.append( "</table>\n" );
    str_buf_ergebnis.append( "</body>\n" );
    str_buf_ergebnis.append( "</html>" );

    return str_buf_ergebnis.toString();
  }

  private static String getTabSpalte( char pChar, String pReplace, String pBezeichnung )
  {
    String str_ergebnis = "";

    str_ergebnis += "<tr>";
    str_ergebnis += "<td>" + pReplace + "</td>";
    str_ergebnis += "<td>" + (int) pChar + "</td>";
    str_ergebnis += "<td>" + Integer.toHexString( (int) pChar ) + "</td>";
    str_ergebnis += "<td>&amp;" + pReplace.substring( 1 ) + "</td>";
    str_ergebnis += "<td>" + pBezeichnung.toUpperCase() + "</td>";
//    str_ergebnis += "<td>datei_inhalt = Replace( datei_inhalt, \"&amp;" + pReplace.substring( 1 ) + "\", \"" + pReplace + "\" )</td>";
    // str_ergebnis += "<td>datei_inhalt = Replace( datei_inhalt, \"" + pReplace + "\", \"&amp;" + pReplace.substring( 1 ) + "\" )</td>";
    //str_ergebnis += "<td>else if ( akt_char_code === " + (int) pChar + " ) { str_ergebnis += '&amp;" + pReplace.substring( 1 ) + "';  }</td>";
//    str_ergebnis += "<td>"
//        + "pBuffer.append( TBVB + TBVB + TBVB + VB_CASE + LZ + \"\\\">\\\":\" + LZ + VAR_NAME_ERGEBNIS_STRING_LOCAL + LZ + VB_ZUWEISUNG + LZ + VAR_NAME_ERGEBNIS_STRING_LOCAL + LZ + \"&\" + LZ + \"\\\""+"\\\"\" );" );
//        + "</td>";

    str_ergebnis += "<td>Case \"" + pChar + "\": fkt_ergebnis = fkt_ergebnis & \"&amp;" + pReplace.substring( 1 ) + "\"</td>";

// "Case \"&\": fkt_ergebnis = fkt_ergebnis & \"&amp;\""

    //str_ergebnis += "<td>ElseIf ( akt_char_code === " + (int) pChar + " ) { str_ergebnis += '&amp;" + pReplace.substring( 1 ) + "';  }</td>";
    str_ergebnis += "</tr>\n";

//    pBuffer.append(  );

    return str_ergebnis;
  }

  private static String getHtmlTabelleSystemProperties()
  {
    Properties pProperties = System.getProperties();
    boolean pKnzSortieren = true;

    if ( pProperties == null ) return "";

    Enumeration enumeration_keys = pProperties.keys();

    if ( pKnzSortieren )
    {
      Vector vector_keys = FkProperties.getSortedKeys( pProperties );

      if ( vector_keys != null )
      {
        enumeration_keys = vector_keys.elements();
      }
    }

    String str_vorlauf = "<tr><td>";
    String str_mitte = "</td><td>";
    String str_nachlauf = "</td></tr>\n";

    StringBuffer str_buffer = new StringBuffer();

    str_buffer.append( "<html>\n" );
    str_buffer.append( "<meta http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\">\n" );
    str_buffer.append( "<head>\n" );
    str_buffer.append( "<title>Uebersicht System-Properties</title>\n" );
    str_buffer.append( "</head>\n" );
    str_buffer.append( "<body>\n" );
    str_buffer.append( "<table cellspacing=\"3px\" cellpadding=\"3\" border=\"1\" >\n" );

    String property_key = null;

    str_buffer.append( "\n" );

    while ( enumeration_keys.hasMoreElements() )
    {
      property_key = (String) enumeration_keys.nextElement();

      str_buffer.append( str_vorlauf );
      str_buffer.append( property_key );
      str_buffer.append( str_mitte );
      str_buffer.append( quoteSpecialCharacters( pProperties.getProperty( property_key ) ) );
      str_buffer.append( str_nachlauf );
    }

    str_buffer.append( "</table>\n" );
    str_buffer.append( "</body>\n" );
    str_buffer.append( "</html>" );

    enumeration_keys = null;
    property_key = null;

    return "\n" + str_buffer.toString();
  }

  public static void main( String[] args )
  {
    try
    {

//String Registrieren Sie sich jetzt für Ihren Persönlichen Bereich in unserem Vertriebsportal. In Ihrem Persönlichen Bereich verfügen Sie  über einen erweiterten Funktionsumfang im Vergleich zum klassischen Portal-Zugang. Weitere Informationen erfragen Sie bitte bei Ihrem Verkaufsdirektor der Deutscher Ring Bausparkasse AG oder unserem PartnerService.

      FkDatei.schreibeDatei( "c:/Daten/system_properties.html", getHtmlTabelleSystemProperties() );

      FkDatei.schreibeDatei( "c:/Daten/html_quotes.html", getHtmlSeite() );
    }
    catch ( Exception e )
    {
      System.out.println( e );
      e.printStackTrace( System.out );
    }

    System.exit( 0 );
  }

}
