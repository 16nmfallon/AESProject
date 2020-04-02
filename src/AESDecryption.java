import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

public class AESDecryption {

    public static final HashMap<String, String> sbox = new HashMap<String, String>() {
        {
            put("00", "52"); put("01", "09"); put("02", "6a"); put("03", "d5"); put("04", "30"); put("05", "36"); put("06", "a5"); put("07", "38"); put("08", "bf"); put("09", "40"); put("0a", "a3"); put("0b", "9e"); put("0c", "81"); put("0d", "f3"); put("0e", "d7"); put("0f", "fb");
            put("10", "7c"); put("11", "e3"); put("12", "39"); put("13", "82"); put("14", "9b"); put("15", "2f"); put("16", "ff"); put("17", "87"); put("18", "34"); put("19", "8e"); put("1a", "43"); put("1b", "44"); put("1c", "c4"); put("1d", "de"); put("1e", "e9"); put("1f", "cb");
            put("20", "54"); put("21", "7b"); put("22", "94"); put("23", "32"); put("24", "a6"); put("25", "c2"); put("26", "23"); put("27", "3d"); put("28", "ee"); put("29", "4c"); put("2a", "95"); put("2b", "0b"); put("2c", "42"); put("2d", "fa"); put("2e", "c3"); put("2f", "4e");
            put("30", "08"); put("31", "2e"); put("32", "a1"); put("33", "66"); put("34", "28"); put("35", "d9"); put("36", "24"); put("37", "b2"); put("38", "76"); put("39", "5b"); put("3a", "a2"); put("3b", "49"); put("3c", "6d"); put("3d", "8b"); put("3e", "d1"); put("3f", "25");
            put("40", "72"); put("41", "f8"); put("42", "f6"); put("43", "64"); put("44", "86"); put("45", "68"); put("46", "98"); put("47", "16"); put("48", "d4"); put("49", "a4"); put("4a", "5c"); put("4b", "cc"); put("4c", "5d"); put("4d", "65"); put("4e", "b6"); put("4f", "92");
            put("50", "6c"); put("51", "70"); put("52", "48"); put("53", "50"); put("54", "fd"); put("55", "ed"); put("56", "b9"); put("57", "da"); put("58", "5e"); put("59", "15"); put("5a", "46"); put("5b", "57"); put("5c", "a7"); put("5d", "8d"); put("5e", "9d"); put("5f", "84");
            put("60", "90"); put("61", "d8"); put("62", "ab"); put("63", "00"); put("64", "8c"); put("65", "bc"); put("66", "d3"); put("67", "0a"); put("68", "f7"); put("69", "e4"); put("6a", "58"); put("6b", "05"); put("6c", "b8"); put("6d", "b3"); put("6e", "45"); put("6f", "06");
            put("70", "d0"); put("71", "2c"); put("72", "1e"); put("73", "8f"); put("74", "ca"); put("75", "3f"); put("76", "0f"); put("77", "02"); put("78", "c1"); put("79", "af"); put("7a", "bd"); put("7b", "03"); put("7c", "01"); put("7d", "13"); put("7e", "8a"); put("7f", "6b");
            put("80", "3a"); put("81", "91"); put("82", "11"); put("83", "41"); put("84", "4f"); put("85", "67"); put("86", "dc"); put("87", "ea"); put("88", "97"); put("89", "f2"); put("8a", "cf"); put("8b", "ce"); put("8c", "f0"); put("8d", "b4"); put("8e", "e6"); put("8f", "73");
            put("90", "96"); put("91", "ac"); put("92", "74"); put("93", "22"); put("94", "e7"); put("95", "ad"); put("96", "35"); put("97", "85"); put("98", "e2"); put("99", "f9"); put("9a", "37"); put("9b", "e8"); put("9c", "1c"); put("9d", "75"); put("9e", "df"); put("9f", "6e");
            put("a0", "47"); put("a1", "f1"); put("a2", "1a"); put("a3", "71"); put("a4", "1d"); put("a5", "29"); put("a6", "c5"); put("a7", "89"); put("a8", "6f"); put("a9", "b7"); put("aa", "62"); put("ab", "0e"); put("ac", "aa"); put("ad", "18"); put("ae", "be"); put("af", "1b");
            put("b0", "fc"); put("b1", "56"); put("b2", "3e"); put("b3", "4b"); put("b4", "c6"); put("b5", "d2"); put("b6", "79"); put("b7", "20"); put("b8", "9a"); put("b9", "db"); put("ba", "c0"); put("bb", "fe"); put("bc", "78"); put("bd", "cd"); put("be", "5a"); put("bf", "f4");
            put("c0", "1f"); put("c1", "dd"); put("c2", "a8"); put("c3", "33"); put("c4", "88"); put("c5", "07"); put("c6", "c7"); put("c7", "31"); put("c8", "b1"); put("c9", "12"); put("ca", "10"); put("cb", "59"); put("cc", "27"); put("cd", "80"); put("ce", "ec"); put("cf", "5f");
            put("d0", "60"); put("d1", "51"); put("d2", "7f"); put("d3", "a9"); put("d4", "19"); put("d5", "b5"); put("d6", "4a"); put("d7", "0d"); put("d8", "2d"); put("d9", "e5"); put("da", "7a"); put("db", "9f"); put("dc", "93"); put("dd", "c9"); put("de", "9c"); put("df", "ef");
            put("e0", "a0"); put("e1", "e0"); put("e2", "3b"); put("e3", "4d"); put("e4", "ae"); put("e5", "2a"); put("e6", "f5"); put("e7", "b0"); put("e8", "c8"); put("e9", "eb"); put("ea", "bb"); put("eb", "3c"); put("ec", "83"); put("ed", "53"); put("ee", "99"); put("ef", "61");
            put("f0", "17"); put("f1", "2b"); put("f2", "04"); put("f3", "7e"); put("f4", "ba"); put("f5", "77"); put("f6", "d6"); put("f7", "26"); put("f8", "e1"); put("f9", "69"); put("fa", "14"); put("fb", "63"); put("fc", "55"); put("fd", "21"); put("fe", "0c"); put("ff", "7d");
        }
    };

    // Needed in Key Scheduling
    public static final HashMap<String, String> sboxForEncryption = new HashMap<String, String>() {
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
        }
    };

    public static final int[][] multiplicationMatrix = {{0x02, 0x03, 0x01, 0x01}, {0x01, 0x02, 0x03, 0x01}, {0x01, 0x01, 0x02, 0x03}, {0x03, 0x01, 0x01, 0x02}};

    public static void main(String[] args) {

        String[] plaintext = {"69", "c4", "e0", "d8", "6a", "7b", "04", "30", "d8", "cd", "b7", "80", "70", "b4", "c5", "5a"};
        String[] key = {"13", "11", "1d", "7f", "e3", "94", "4a", "17", "f3", "07", "a7", "8b", "4d", "2b", "30", "c5"};


        String plaintextString = arrayToString(plaintext);
        String keyString = arrayToString(key);
        System.out.println("round[0].iinput: " + plaintextString);
        System.out.println("round[0].ik_sch: " + keyString);
        String[] currentText = plaintext;
        String[] currentkey = key;
        for (int i = 1; i <= 10; i++) {
            currentText = xorString(arrayToString(currentText),arrayToString(currentkey));
            System.out.println("round[" + i + "].istart: " + arrayToString(currentText));
            currentText = rowSubstitution(currentText);
            System.out.println("round[" + i + "].is_row: " + arrayToString(currentText));
            currentText = sBoxSubstitution(currentText);
            System.out.println("round[" + i + "].is_box: " + arrayToString(currentText));
            currentkey = keySchedule(currentkey, i);
            System.out.println("round[" + i + "].ik_sch: " + arrayToString(currentkey));
            /*
            if (i < 10) {
                currentText = columnMultiplication(currentText);
                System.out.println("round[" + i + "].m_col: " + arrayToString(currentText));
            }
             */
        }

        System.out.println("round[10].output: " + arrayToString(xorString(arrayToString(currentText), arrayToString(currentkey))));




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
        StringBuilder sb = new StringBuilder(s3);
        while (sb.length() < 32) {
            sb.insert(0, "0");
        }
        s3 = sb.toString();
        String[] currentText = new String[16];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            currentText[i] = s3.substring(k, k+2);
            k = k + 2;
        }
        return currentText;
    }

    private static String[] sBoxSubstitution(String[] currentText) {
        for(int i = 0; i < currentText.length; i++) {
            currentText[i] = sbox.get(currentText[i]);
        }
        return currentText;
    }

    private static String[] sBoxSubstitutionForKeyScheduling(String[] currentText) {
        for(int i = 0; i < currentText.length; i++) {
            currentText[i] = sboxForEncryption.get(currentText[i]);
        }
        return currentText;
    }

    private static String[] rowSubstitution(String[] currentText) {
        String[] temp = Arrays.copyOf(currentText, 16);
        currentText[1] = temp[13];
        currentText[2] = temp[10];
        currentText[3] = temp[7];
        currentText[5] = temp[1];
        currentText[6] = temp[14];
        currentText[7] = temp[11];
        currentText[9] = temp[5];
        currentText[10] = temp[2];
        currentText[11] = temp[15];
        currentText[13] = temp[9];
        currentText[14] = temp[6];
        currentText[15] = temp[3];
        return currentText;
    }

    private static String[] columnMultiplication(String[] currentText) {
        String[] temp = Arrays.copyOf(currentText, 16);

        for (int i = 0; i < 16; i++) {
            String binary1 = "";
            String binary2 = "";
            String binary3 = "";
            String binary4 = "";
            int multiplier1 = 0;
            int multiplier2 = 0;
            int multiplier3 = 0;
            int multiplier4 = 0;
            // Setting the right multiplier for the multiplication matrix.
            if (i % 4 == 0) {
                multiplier1 = multiplicationMatrix[0][0];
                multiplier2 = multiplicationMatrix[0][1];
                multiplier3 = multiplicationMatrix[0][2];
                multiplier4 = multiplicationMatrix[0][3];
            }
            else if (i % 4 == 1) {
                multiplier1 = multiplicationMatrix[1][0];
                multiplier2 = multiplicationMatrix[1][1];
                multiplier3 = multiplicationMatrix[1][2];
                multiplier4 = multiplicationMatrix[1][3];
            }
            else if (i % 4 == 2) {
                multiplier1 = multiplicationMatrix[2][0];
                multiplier2 = multiplicationMatrix[2][1];
                multiplier3 = multiplicationMatrix[2][2];
                multiplier4 = multiplicationMatrix[2][3];
            }
            else {
                multiplier1 = multiplicationMatrix[3][0];
                multiplier2 = multiplicationMatrix[3][1];
                multiplier3 = multiplicationMatrix[3][2];
                multiplier4 = multiplicationMatrix[3][3];
            }
            String matrixHex1 = "";
            String matrixHex2 = "";
            String matrixHex3 = "";
            String matrixHex4 = "";
            // Setting the number in the matrix that needs to be multiplied
            if (i < 4) {
                matrixHex1 = temp[0];
                matrixHex2 = temp[1];
                matrixHex3 = temp[2];
                matrixHex4 = temp[3];
            }
            else if (i < 8) {
                matrixHex1 = temp[4];
                matrixHex2 = temp[5];
                matrixHex3 = temp[6];
                matrixHex4 = temp[7];
            }
            else if (i < 12) {
                matrixHex1 = temp[8];
                matrixHex2 = temp[9];
                matrixHex3 = temp[10];
                matrixHex4 = temp[11];
            }
            else {
                matrixHex1 = temp[12];
                matrixHex2 = temp[13];
                matrixHex3 = temp[14];
                matrixHex4 = temp[15];
            }

            // computes the correct binary number based on the hex and the multiplier that were passed in.
            binary1 = computeBinary(multiplier1, matrixHex1);
            binary2 = computeBinary(multiplier2, matrixHex2);
            binary3 = computeBinary(multiplier3, matrixHex3);
            binary4 = computeBinary(multiplier4, matrixHex4);

            // Adds on extra 0's in the front of the strings if it is less than 8 digits.
            StringBuilder sb1 = new StringBuilder(binary1);
            while (sb1.length() < 8) {
                sb1.insert(0, "0");
            }
            binary1 = sb1.toString();

            StringBuilder sb2 = new StringBuilder(binary2);
            while (sb2.length() < 8) {
                sb2.insert(0, "0");
            }
            binary2 = sb2.toString();

            StringBuilder sb3 = new StringBuilder(binary3);
            while (sb3.length() < 8) {
                sb3.insert(0, "0");
            }
            binary3 = sb3.toString();

            StringBuilder sb4 = new StringBuilder(binary4);
            while (sb4.length() < 8) {
                sb4.insert(0, "0");
            }
            binary4 = sb4.toString();

            // Does the xor operation without the carry over bit.
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
            // Converts the binary back to hex and stores it into the main array.
            String finalBinaryResult = binaryResult.toString();
            int decimal = Integer.parseInt(finalBinaryResult,2);
            String hexString = Integer.toString(decimal,16);
            if (hexString.length() == 1) {
                StringBuilder sb = new StringBuilder(hexString);
                sb.insert(0, "0");
                hexString = sb.toString();
            }
            currentText[i] = hexString;
        }

        return currentText;
    }

    public static String shiftLeft(String binary) {
        StringBuilder binaryResult = new StringBuilder();
        binaryResult.append(binary.charAt(1));
        binaryResult.append(binary.charAt(2));
        binaryResult.append(binary.charAt(3));
        binaryResult.append(binary.charAt(4));
        binaryResult.append(binary.charAt(5));
        binaryResult.append(binary.charAt(6));
        binaryResult.append(binary.charAt(7));
        binaryResult.append('0');
        return binaryResult.toString();
    }

    public static String computeBinary(int multiplier, String matrixHex) {
        // Converts String hex to and Int hex
        int hex = Integer.decode(String.format("0x%s", matrixHex));
        // Converts Int hex to binary String.
        String binary = Integer.toBinaryString(hex);
        StringBuilder sb = new StringBuilder(binary);
        // Adds zeros to the front of the string if it is less than 8 digits.
        while (sb.length() < 8) {
            sb.insert(0, "0");
        }
        binary = sb.toString();
        String originalBinary = binary;
        //Case 2: Not Leading 1, for 0x02
        if (binary.charAt(0) == '0' && multiplier == 0x02) {
            hex = multiplier * Integer.decode(String.format("0x%s", matrixHex));
            binary = Integer.toBinaryString(hex);

        }
        // Case 3 Leading 1, for 0x02
        else if (binary.charAt(0) == '1' && multiplier == 0x02){
            binary = shiftLeft(binary);
            BigInteger shiftedValue = new BigInteger(binary, 2);
            BigInteger pValue = new BigInteger("11011", 2);
            BigInteger xorResult = shiftedValue.xor(pValue);
            binary = xorResult.toString(2);

        }
        // Case 4 No Leading 1, for 0x03
        else if (binary.charAt(0) == '0' && multiplier == 0x03){
            hex = (0x02 * Integer.decode(String.format("0x%s", matrixHex))) ^ Integer.decode(String.format("0x%s", matrixHex));
            binary = Integer.toBinaryString(hex);
        }
        // Case 5 Leading 1, for 0x03
        else if (binary.charAt(0) == '1' && multiplier == 0x03){
            binary = shiftLeft(binary);
            BigInteger shiftedValue = new BigInteger(binary, 2);
            BigInteger pValue = new BigInteger("11011", 2);
            BigInteger xorResult = shiftedValue.xor(pValue);
            BigInteger originalValue = new BigInteger(originalBinary, 2);
            BigInteger finalResult = xorResult.xor(originalValue);
            binary = finalResult.toString(2);
        }
        return binary;
    }

    public static String[] keySchedule (String[] key, int round) {
        //String[] temp = Arrays.copyOf(currentText, 16);
        String[] finalKey = new String[16];
        int count = 15;
        String[] finalResult = new String[4];
        for(int j = 3; j >= 0; j--) {
            if (j == 0) {
                String[] shiftedColumn = new String[]{finalKey[13], finalKey[14], finalKey[15], finalKey[12]};
                String[] afterSubstitution = sBoxSubstitutionForKeyScheduling(shiftedColumn);
                String column1 = arrayToString(afterSubstitution);
                String[] column2 = new String[4];
                for (int i = 0; i < 4; i++) {
                    column2[i] = key[i];
                }
                String[] result = xorStringLength4hex(column1, arrayToString(column2));
                String keyXor = new String();
                if (round == 2) {
                    keyXor = "1b000000";
                } else if (round == 1) {
                    keyXor = "36000000";
                }
                else {
                    keyXor = Integer.toHexString((int) Math.pow(2, 10 - round)) + "000000";
                }
                StringBuilder sb = new StringBuilder(keyXor);
                while (sb.length() < 8) {
                    sb.insert(0, "0");
                }
                keyXor = sb.toString();
                finalResult = xorStringLength4hex(arrayToString(result), keyXor);
            } else {
                String[] columnOne = new String[]{key[(4*(j-1))], key[1+(4*(j-1))], key[2+(4*(j-1))], key[3+(4*(j-1))]};;
                String[] columnTwo = new String[]{key[(4*j)], key[1+(4*j)], key[2+(4*j)], key[3+(4*j)]};
                finalResult = xorStringLength4hex(arrayToString(columnOne), arrayToString(columnTwo));
            }
            for (int k = 3; k >= 0; k--) {
                finalKey[count] = finalResult[k];
                count--;
            }
        }

        return finalKey;
    }

    private static String[] xorStringLength4hex(String plaintext, String key) {
        BigInteger plaintextInt = new BigInteger(plaintext, 16);
        BigInteger keyInt = new BigInteger(key, 16);
        BigInteger xorResult = plaintextInt.xor(keyInt);
        String s3 = xorResult.toString(16);
        StringBuilder sb = new StringBuilder(s3);
        while (sb.length() < 8) {
            sb.insert(0, "0");
        }
        s3 = sb.toString();
        String[] currentText = new String[4];
        int k = 0;
        for (int i = 0; i < 4; i++) {
            currentText[i] = s3.substring(k, k+2);
            k = k + 2;
        }
        return currentText;
    }
}
