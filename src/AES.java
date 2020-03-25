import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

public class AES {

    public static final HashMap<String, String> sbox = new HashMap<String, String>() {
        {
            put("00", "63"); put("01", "7c"); put("02", "77"); put("03", "7b"); put("04", "f2"); put("05", "6b"); put("06", "6f"); put("07", "c5"); put("08", "30"); put("09", "01"); put("0a", "67"); put("0b", "2b"); put("0c", "fe"); put("0d", "d7"); put("0e", "ab"); put("0f", "76");
            put("10", "ca"); put("11", "82"); put("12", "c9"); put("13", "7d"); put("14", "fa"); put("15", "59"); put("16", "47"); put("17", "f0"); put("18", "ad"); put("19", "d4"); put("1a", "a2"); put("1b", "af"); put("1c", "9c"); put("1d", "a4"); put("1e", "72"); put("1f", "c0");
            put("20", "b7"); put("21", "fd"); put("22", "93"); put("23", "26"); put("24", "36"); put("25", "3f"); put("26", "f7"); put("27", "cc"); put("28", "34"); put("29", "a5"); put("2a", "e5"); put("2b", "f1"); put("2c", "71"); put("2d", "d8"); put("2e", "31"); put("2f", "15");
            put("30", "04"); put("31", "c7"); put("32", "23"); put("33", "c3"); put("34", "18"); put("35", "96"); put("36", "05"); put("37", "9a"); put("38", "07"); put("39", "12"); put("3a", "80"); put("3b", "e2"); put("3c", "eb"); put("3d", "27"); put("3e", "b2"); put("3f", "75");
            put("40", "09"); put("41", "83"); put("42", "2c"); put("43", "1a"); put("44", "1b"); put("45", "6e"); put("46", "5a"); put("47", "a0"); put("48", "52"); put("49", "3b"); put("4a", "d6"); put("4b", "b3"); put("4c", "29"); put("4d", "e3"); put("4e", "2f"); put("4f", "84");
            put("50", "53"); put("51", "d1"); put("52", "00"); put("53", "ed"); put("54", "20"); put("55", "fc"); put("56", "b1"); put("57", "5b"); put("58", "6a"); put("59", "cb"); put("5a", "be"); put("5b", "39"); put("5c", "4a"); put("5d", "4c"); put("5e", "58"); put("5f", "cf");
            put("60", "d0"); put("61", "ef"); put("62", "aa"); put("63", "fb"); put("64", "43"); put("65", "4d"); put("66", "33"); put("67", "85"); put("68", "45"); put("69", "f9"); put("6a", "02"); put("6b", "7f"); put("6c", "50"); put("6d", "3c"); put("6e", "9f"); put("6f", "a8");
            put("70", "51"); put("71", "a3"); put("72", "40"); put("73", "8f"); put("74", "92"); put("75", "9d"); put("76", "38"); put("77", "f5"); put("78", "bc"); put("79", "b6"); put("7a", "da"); put("7b", "21"); put("7c", "10"); put("7d", "ff"); put("7e", "f3"); put("7f", "d2");
            put("80", "cd"); put("81", "0c"); put("82", "13"); put("83", "ec"); put("84", "5f"); put("85", "97"); put("86", "44"); put("87", "17"); put("88", "c4"); put("89", "a7"); put("8a", "7e"); put("8b", "3d"); put("8c", "64"); put("8d", "5d"); put("8e", "19"); put("8f", "73");
            put("90", "60"); put("91", "81"); put("92", "4f"); put("93", "dc"); put("94", "22"); put("95", "2a"); put("96", "90"); put("97", "88"); put("98", "46"); put("99", "ee"); put("9a", "b8"); put("9b", "14"); put("9c", "de"); put("9d", "5e"); put("9e", "0b"); put("9f", "db");
            put("a0", "e0"); put("a1", "32"); put("a2", "3a"); put("a3", "0a"); put("a4", "49"); put("a5", "06"); put("a6", "24"); put("a7", "5c"); put("a8", "c2"); put("a9", "d3"); put("aa", "ac"); put("ab", "62"); put("ac", "91"); put("ad", "95"); put("ae", "e4"); put("af", "79");
            put("b0", "e7"); put("b1", "c8"); put("b2", "37"); put("b3", "6d"); put("b4", "8d"); put("b5", "d5"); put("b6", "4e"); put("b7", "a9"); put("b8", "6c"); put("b9", "56"); put("ba", "f4"); put("bb", "ea"); put("bc", "65"); put("bd", "7a"); put("be", "ae"); put("bf", "08");
            put("c0", "ba"); put("c1", "78"); put("c2", "25"); put("c3", "2e"); put("c4", "1c"); put("c5", "a6"); put("c6", "b4"); put("c7", "c6"); put("c8", "e8"); put("c9", "dd"); put("ca", "74"); put("cb", "1f"); put("cc", "4b"); put("cd", "bd"); put("ce", "8b"); put("cf", "8a");
            put("d0", "70"); put("d1", "3e"); put("d2", "b5"); put("d3", "66"); put("d4", "48"); put("d5", "03"); put("d6", "f6"); put("d7", "0e"); put("d8", "61"); put("d9", "35"); put("da", "57"); put("db", "b9"); put("dc", "86"); put("dd", "c1"); put("de", "1d"); put("df", "9e");
            put("e0", "e1"); put("e1", "f8"); put("e2", "98"); put("e3", "11"); put("e4", "69"); put("e5", "d9"); put("e6", "8e"); put("e7", "94"); put("e8", "9b"); put("e9", "1e"); put("ea", "87"); put("eb", "e9"); put("ec", "ce"); put("ed", "55"); put("ee", "28"); put("ef", "df");
            put("f0", "8c"); put("f1", "a1"); put("f2", "89"); put("f3", "0d"); put("f4", "bf"); put("f5", "e6"); put("f6", "42"); put("f7", "68"); put("f8", "41"); put("f9", "99"); put("fa", "2d"); put("fb", "0f"); put("fc", "b0"); put("fd", "54"); put("fe", "bb"); put("ff", "16");
        }};

    public static void main(String[] args) {
        String[] plaintext = {"00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "aa", "bb", "cc", "dd", "ee", "ff"};
        String[] key = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0a", "0b", "0c", "0d", "0e", "0f"};

        String plaintextString = arrayToString(plaintext);
        String keyString = arrayToString(key);
        System.out.println("round[0].input: " + plaintextString);
        System.out.println("round[0].k_sch: " + keyString);
        String[] currentText = xorString(plaintextString, keyString);
        for (int i = 1; i <= 10; i++) {
            System.out.println("round[" + i + "].start: " + arrayToString(currentText));
            currentText = sBoxSubstitution(currentText);
            System.out.println("round[" + i + "].s_box: " + arrayToString(currentText));
            currentText = rowSubstitution(currentText);
            System.out.println("round[" + i + "].s_row: " + arrayToString(currentText));
            currentText = columnMultiplication(currentText);

        }



    }

    private static String arrayToString(String[] arr) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private static String[] xorString(String plaintext, String key) {
        BigInteger plaintextInt = new BigInteger(plaintext, 16);
        BigInteger keyInt = new BigInteger(key, 16);
        BigInteger xorResult = plaintextInt.xor(keyInt);
        String s3 = xorResult.toString(16);
        if (plaintext.substring(0,2).equals("00") && key.substring(0,2).equals("00")) {
            s3 = "00" + s3;
        }
        String[] currentText = new String[16];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            currentText[i] = s3.substring(k, k+2);
            k = k + 2;
        }
        return currentText;
    }

    private static String[] sBoxSubstitution(String[] currentText) {
        for(int i = 0; i < 16; i++) {
            currentText[i] = sbox.get(currentText[i]);
        }
        return currentText;
    }

    private static String[] rowSubstitution(String[] currentText) {
        String[] temp = Arrays.copyOf(currentText, 16);
        currentText[1] = temp[5];
        currentText[2] = temp[10];
        currentText[3] = temp[15];
        currentText[5] = temp[9];
        currentText[6] = temp[14];
        currentText[7] = temp[3];
        currentText[9] = temp[13];
        currentText[10] = temp[2];
        currentText[11] = temp[7];
        currentText[13] = temp[1];
        currentText[14] = temp[6];
        currentText[15] = temp[11];
        return currentText;
    }

    private static String[] columnMultiplication(String[] currentText) {
        String[] temp = Arrays.copyOf(currentText, 16);
        int hex1 = 0x02 * Integer.decode(String.format("0x%s", temp[0]));
        String binary1 = Integer.toBinaryString(hex1);
        int hex2 = (0x02 * Integer.decode(String.format("0x%s", temp[1]))) ^ Integer.decode(String.format("0x%s", temp[1]));
        String binary2 = Integer.toBinaryString(hex2);
        int hex3 = Integer.decode(String.format("0x%s", temp[2]));
        String binary3 = Integer.toBinaryString(hex3);
        int hex4 = Integer.decode(String.format("0x%s", temp[3]));
        String binary4 = Integer.toBinaryString(hex4);
        StringBuilder binaryResult = new StringBuilder();
        if ((binary1.charAt(0) + binary2.charAt(0) + binary3.charAt(0) + binary4.charAt(0)) % 2 == 0) {
            binaryResult.append('0');
        } else {
            binaryResult.append('1');
        }
        if ((binary1.charAt(1) + binary2.charAt(1) + binary3.charAt(1) + binary4.charAt(1)) % 2 == 0) {
            binaryResult.append('0');
        } else {
            binaryResult.append('1');
        }
        if ((binary1.charAt(2) + binary2.charAt(2) + binary3.charAt(2) + binary4.charAt(2)) % 2 == 0) {
            binaryResult.append('0');
        } else {
            binaryResult.append('1');
        }
        if ((binary1.charAt(3) + binary2.charAt(3) + binary3.charAt(3) + binary4.charAt(3)) % 2 == 0) {
            binaryResult.append('0');
        } else {
            binaryResult.append('1');
        }
        if ((binary1.charAt(4) + binary2.charAt(4) + binary3.charAt(4) + binary4.charAt(4)) % 2 == 0) {
            binaryResult.append('0');
        } else {
            binaryResult.append('1');
        }
        if ((binary1.charAt(5) + binary2.charAt(5) + binary3.charAt(5) + binary4.charAt(5)) % 2 == 0) {
            binaryResult.append('0');
        } else {
            binaryResult.append('1');
        }
        if ((binary1.charAt(6) + binary2.charAt(6) + binary3.charAt(6) + binary4.charAt(6)) % 2 == 0) {
            binaryResult.append('0');
        } else {
            binaryResult.append('1');
        }
        if ((binary1.charAt(7) + binary2.charAt(7) + binary3.charAt(7) + binary4.charAt(7)) % 2 == 0) {
            binaryResult.append('0');
        } else {
            binaryResult.append('1');
        }
        String finalBinaryResult = binaryResult.toString();
        int decimal = Integer.parseInt(finalBinaryResult,2);
        String hexString = Integer.toString(decimal,16);
        currentText[0] = hexString;
        return temp;
    }
}
