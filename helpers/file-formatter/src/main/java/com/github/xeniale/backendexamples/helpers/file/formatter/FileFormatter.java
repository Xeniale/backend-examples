package com.github.xeniale.backendexamples.helpers.file.formatter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by kshekhovtsova on 13.05.2015.
 */
public class FileFormatter {

//    public static final String CONTENT =  "             SELECT\n" +
//            "               T.TRANSACTION_ID                                                     AS transactionId,\n" +
//            "               TH.TRANSACTION_HISTORY_ID                                            AS transactionHistoryId,\n" +
//            "               T.CORRELATION_ID                                                     AS correlationId,\n" +
//            "               TH.CLIENT_CORRELATION_ID                                             AS clientCorrelationId,\n" +
//            "               to_char(sys_extract_utc(T.TRANSACTION_TIME), 'mm/dd/yyyy hh24:MM')   AS activityDate,\n" +
//            "               TH1.HISTORY_TRANSACTION_ID                                           AS historyTransactionId,\n" +
//            "               T.TRANSACTION_TIME                                                   AS transactionTime,\n" +
//            "               decode(TH.TRANSACTION_STATE, 'CHARGED', 1, 'REFUNDED', 3)            AS activityType,\n" +
//            "               T.AMOUNT                                                             AS amount,\n" +
//            "               (T.AMOUNT / 1000000)                                                 AS grossAmount,\n"+
//            "               T.CURRENCY                                                           AS currency,\n" +
//            "               TP3.PARAMETER_VALUE                                                  AS productId,\n" +
//            "               TP4.PARAMETER_VALUE                                                  AS productName,\n" +
//            "               TP5.PARAMETER_VALUE                                                  AS authorizationId,\n" +
//            "               CASE WHEN TH.TRANSACTION_STATE like 'REFUND%'\n" +
//            "                 THEN T.CORRELATION_ID\n" +
//            "               ELSE NULL END                                                        AS operatorOriginalTransactionId,\n" +
//            "               (CASE WHEN TP1.PARAMETER_VALUE IS NOT NULL\n" +
//            "                 THEN decode(instr(TP1.PARAMETER_VALUE, ','), 0, TP1.PARAMETER_VALUE,\n" +
//            "                             '\"' || TP1.PARAMETER_VALUE || '\"') END)                AS refundReason,\n" +
//            "               to_char(sys_extract_utc(th.history_time), 'mm/dd/yyyy hh24:MM')      AS acceptanceDate,\n" +
//            "               CASE WHEN TP2.PARAMETER_VALUE IS NULL OR TP2.PARAMETER_VALUE = ''\n" +
//            "                 THEN 'Application'\n" +
//            "               ELSE TP2.PARAMETER_VALUE END                                         AS remittanceRevshareRule,\n" +
//            "               case when TH.TRANSACTION_STATE like '%FAILED' then 'failed' else 'success' end  AS transactionState,\n" +
//            "               ST.TRANSACTION_TYPE                                                  AS transactionType,\n" +
//            "               T.EXTERNAL_ID                                                        AS acr\n" +
//            "             FROM TRANSACTION T\n" +
//            "               JOIN TRANSACTION_HISTORY TH\n" +
//            "                 ON TH.TRANSACTION_ID = T.TRANSACTION_ID AND TH.TRANSACTION_STATE IN (:status)\n" +
//            "               LEFT JOIN TRANSACTION_PARAMETER TP1\n" +
//            "                 ON TP1.TRANSACTION_HISTORY_ID = TH.TRANSACTION_HISTORY_ID AND TP1.PARAMETER_KEY = 'REFUND_REASON'\n" +
//            "               LEFT JOIN TRANSACTION_HISTORY TH1\n" +
//            "                 ON TH1.TRANSACTION_ID = T.TRANSACTION_ID AND TH1.TRANSACTION_STATE = 'CHARGED'\n" +
//            "               JOIN STATUS_TRANSACTION_TYPE ST\n" +
//            "                 ON ST.STATUS = TH.TRANSACTION_STATE\n" +
//            "               LEFT JOIN TRANSACTION_PARAMETER TP2\n" +
//            "                 ON TP2.TRANSACTION_HISTORY_ID = TH1.TRANSACTION_HISTORY_ID AND TP2.PARAMETER_KEY = 'REVSHARE_RULE'\n" +
//            "               LEFT JOIN transaction_parameter tp3\n" +
//            "                 ON TP3.TRANSACTION_HISTORY_ID = TH1.TRANSACTION_HISTORY_ID AND TP3.PARAMETER_KEY = 'PRODUCT_ID'\n" +
//            "               LEFT JOIN transaction_parameter tp4\n" +
//            "                 ON TP4.TRANSACTION_HISTORY_ID = TH1.TRANSACTION_HISTORY_ID AND TP4.PARAMETER_KEY = 'PRODUCT_NAME'\n" +
//            "               LEFT JOIN transaction_parameter tp5\n" +
//            "                 ON TP5.TRANSACTION_HISTORY_ID = TH1.TRANSACTION_HISTORY_ID AND TP5.PARAMETER_KEY = 'BILLING_ID'\n" +
//            "             WHERE T.PARTNER_ID = :partner\n" +
//            "                   AND trunc(to_date(T.TRANSACTION_TIME - 3 / 24)) >= trunc(:dateFrom)\n" +
//            "                   AND trunc(to_date(T.TRANSACTION_TIME - 3 / 24)) <= trunc(:dateTo)\n" +
//            "                   and t.transaction_id > :transactionId " +
//            "                   and rownum <= :limit" +
//            "                   AND T.MCC_MNC IN (:ncc)\n" +
//            "                   AND T.TRANSACTION_STATUS IN (:status)\n" +
//            "             ORDER BY T.TRANSACTION_ID, TH.TRANSACTION_HISTORY_ID ";

    public static void main(String[] args) {
        String inFilePath = null;
        String encoding = StandardCharsets.UTF_8.name();
        String outFilePath = null;
        try {
            switch (args.length) {
                case 0:
                    System.out.print("No arguments");
                    System.exit(0);
                case 1:
                    inFilePath = args[0];
                    break;
                case 2:
                    inFilePath = args[0];
                    encoding = args[1];
                    break;
                case 3:
                    inFilePath = args[0];
                    encoding = args[1];
                    outFilePath = args[2];
                    break;
                default:
                    System.exit(1);
            }
            String content = load(inFilePath, encoding);
            out(unescapeString(content), outFilePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
//        say(CONTENT);
    }

    private static String load(String fileName, String encoding) throws IOException {
        return FileUtils.readFileToString(new File(fileName), encoding).replaceAll("[\\\"]{1}\\s*[\\+]{1}\\s*[\\\"]", "").replaceAll("[\\\\]{1}\\s*[\"]{1}", "\"");
    }

    private static void out(String content, String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("-----------File content------------:");
            System.out.print(content);
        }
        else {
            FileUtils.writeStringToFile(new File(filePath), content);
        }
    }


    public final static String unescapeString(String oldstr) {
    /*
     * In contrast to fixing Java's broken regex charclasses,
     * this one need be no bigger, as unescaping shrinks the string
     * here, where in the other one, it grows it.
     */
        StringBuffer newstr = new StringBuffer(oldstr.length());

        boolean saw_backslash = false;

        for (int i = 0; i < oldstr.length(); i++) {
            int cp = oldstr.codePointAt(i);
            if (oldstr.codePointAt(i) > Character.MAX_VALUE) {
                i++;
            }

            if (!saw_backslash) {
                if (cp == '\\') {
                    saw_backslash = true;
                } else {
                    newstr.append(Character.toChars(cp));
                }
                continue; /* switch */
            }

            if (cp == '\\') {
                saw_backslash = false;
                newstr.append('\\');
                newstr.append('\\');
                continue;
            }

            switch (cp) {

                case 'r':
                    newstr.append('\r');
                    break;

                case 'n':
                    newstr.append('\n');
                    break;

                case 'f':
                    newstr.append('\f');
                    break;

                case 'b':
                    newstr.append("\\b");
                    break;

                case 't':
                    newstr.append('\t');
                    break;

                case 'a':
                    newstr.append('\007');
                    break;

                case 'e':
                    newstr.append('\033');
                    break;

            /*
             * A "control" character is what you get when you xor its
             * codepoint with '@'==64.  This only makes sense for ASCII,
             * and may not yield a "control" character after all.
             *
             * Strange but true: "\c{" is ";", "\c}" is "=", etc.
             */
                case 'c': {
                    if (++i == oldstr.length()) {
                        die("trailing \\c");
                    }
                    cp = oldstr.codePointAt(i);
                /*
                 * don't need to grok surrogates, as next line blows them up
                 */
                    if (cp > 0x7f) {
                        die("expected ASCII after \\c");
                    }
                    newstr.append(Character.toChars(cp ^ 64));
                    break; /* switch */
                }

                case '8':
                case '9':
                    die("illegal octal digit");
                      /* NOTREACHED */

    /*
     * may be 0 to 2 octal digits following this one
     * so back up one for fallthrough to next case;
     * unread this digit and fall through to next case.
     */
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    --i;

            /*
             * Can have 0, 1, or 2 octal digits following a 0
             * this permits larger values than octal 377, up to
             * octal 777.
             */
                case '0': {
                    if (i + 1 == oldstr.length()) {
                    /* found \0 at end of string */
                        newstr.append(Character.toChars(0));
                        break; /* switch */
                    }
                    i++;
                    int digits = 0;
                    int j;
                    for (j = 0; j <= 2; j++) {
                        if (i + j == oldstr.length()) {
                            break; /* for */
                        }
                    /* safe because will unread surrogate */
                        int ch = oldstr.charAt(i + j);
                        if (ch < '0' || ch > '7') {
                            break; /* for */
                        }
                        digits++;
                    }
                    if (digits == 0) {
                        --i;
                        newstr.append('\0');
                        break; /* switch */
                    }
                    int value = 0;
                    try {
                        value = Integer.parseInt(
                                oldstr.substring(i, i + digits), 8);
                    } catch (NumberFormatException nfe) {
                        die("invalid octal value for \\0 escape");
                    }
                    newstr.append(Character.toChars(value));
                    i += digits - 1;
                    break; /* switch */
                } /* end case '0' */

                case 'x': {
                    if (i + 2 > oldstr.length()) {
                        die("string too short for \\x escape");
                    }
                    i++;
                    boolean saw_brace = false;
                    if (oldstr.charAt(i) == '{') {
                        i++;
                        saw_brace = true;
                    }
                    int j;
                    for (j = 0; j < 8; j++) {

                        if (!saw_brace && j == 2) {
                            break;  /* for */
                        }

                    /*
                     * ASCII test also catches surrogates
                     */
                        int ch = oldstr.charAt(i + j);
                        if (ch > 127) {
                            die("illegal non-ASCII hex digit in \\x escape");
                        }

                        if (saw_brace && ch == '}') {
                            break; /* for */
                        }

                        if (!((ch >= '0' && ch <= '9')
                                ||
                                (ch >= 'a' && ch <= 'f')
                                ||
                                (ch >= 'A' && ch <= 'F')
                        )
                                ) {
                            die(String.format(
                                    "illegal hex digit #%d '%c' in \\x", ch, ch));
                        }

                    }
                    if (j == 0) {
                        die("empty braces in \\x{} escape");
                    }
                    int value = 0;
                    try {
                        value = Integer.parseInt(oldstr.substring(i, i + j), 16);
                    } catch (NumberFormatException nfe) {
                        die("invalid hex value for \\x escape");
                    }
                    newstr.append(Character.toChars(value));
                    if (saw_brace) {
                        j++;
                    }
                    i += j - 1;
                    break; /* switch */
                }

                case 'u': {
                    if (i + 4 > oldstr.length()) {
                        die("string too short for \\u escape");
                    }
                    i++;
                    int j;
                    for (j = 0; j < 4; j++) {
                    /* this also handles the surrogate issue */
                        if (oldstr.charAt(i + j) > 127) {
                            die("illegal non-ASCII hex digit in \\u escape");
                        }
                    }
                    int value = 0;
                    try {
                        value = Integer.parseInt(oldstr.substring(i, i + j), 16);
                    } catch (NumberFormatException nfe) {
                        die("invalid hex value for \\u escape");
                    }
                    newstr.append(Character.toChars(value));
                    i += j - 1;
                    break; /* switch */
                }

                case 'U': {
                    if (i + 8 > oldstr.length()) {
                        die("string too short for \\U escape");
                    }
                    i++;
                    int j;
                    for (j = 0; j < 8; j++) {
                    /* this also handles the surrogate issue */
                        if (oldstr.charAt(i + j) > 127) {
                            die("illegal non-ASCII hex digit in \\U escape");
                        }
                    }
                    int value = 0;
                    try {
                        value = Integer.parseInt(oldstr.substring(i, i + j), 16);
                    } catch (NumberFormatException nfe) {
                        die("invalid hex value for \\U escape");
                    }
                    newstr.append(Character.toChars(value));
                    i += j - 1;
                    break; /* switch */
                }

                default:
                    newstr.append('\\');
                    newstr.append(Character.toChars(cp));
           /*
            * say(String.format(
            *       "DEFAULT unrecognized escape %c passed through",
            *       cp));
            */
                    break; /* switch */

            }
            saw_backslash = false;
        }

    /* weird to leave one at the end */
        if (saw_backslash) {
            newstr.append('\\');
        }

        return newstr.toString();
    }

    /*
     * Return a string "U+XX.XXX.XXXX" etc, where each XX set is the
     * xdigits of the logical Unicode code point. No bloody brain-damaged
     * UTF-16 surrogate crap, just true logical characters.
     */
    public final static String uniplus(String s) {
        if (s.length() == 0) {
            return "";
        }
     /* This is just the minimum; sb will grow as needed. */
        StringBuffer sb = new StringBuffer(2 + 3 * s.length());
        sb.append("U+");
        for (int i = 0; i < s.length(); i++) {
            sb.append(String.format("%X", s.codePointAt(i)));
            if (s.codePointAt(i) > Character.MAX_VALUE) {
                i++; /****WE HATES UTF-16! WE HATES IT FOREVERSES!!!****/
            }
            if (i + 1 < s.length()) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private static final void die(String foa) {
        throw new IllegalArgumentException(foa);
    }

    private static final void say(String what) {
        System.out.println(what);
    }
}
