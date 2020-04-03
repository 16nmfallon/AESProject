import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class AESEncryption {

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
        }
    };

    public static final int[][] multiplicationMatrix = {{0x02, 0x03, 0x01, 0x01}, {0x01, 0x02, 0x03, 0x01}, {0x01, 0x01, 0x02, 0x03}, {0x03, 0x01, 0x01, 0x02}};

    public static void main(String[] args) {

        System.out.println("ENCRYPTION\n");
        System.out.println("Enter Plaintext (16 bytes): ");
        Scanner in = new Scanner(System.in);
        String inputtedPlaintext = in.nextLine();
        System.out.println("Enter Key (16 bytes): ");
        String inputtedKey = in.nextLine();

        String plaintext[] = new String[16];   // 00112233445566778899aabbccddeeff
        String key[] = new String[16];          // 000102030405060708090a0b0c0d0e0f
        int count = 0;
        for (int j = 0; j < 16; j++) {
            plaintext[j] = inputtedPlaintext.substring(count, count+2);
            key[j] = inputtedKey.substring(count, count+2);
            count = count + 2;
        }

        String plaintextString = arrayToString(plaintext);
        String keyString = arrayToString(key);
        System.out.println("round[0].input: " + plaintextString);
        System.out.println("round[0].k_sch: " + keyString);
        String[] currentText = plaintext;
        String[] currentkey = key;
        for (int i = 1; i <= 10; i++) {
            currentText = xorString(arrayToString(currentText),arrayToString(currentkey));
            System.out.println("round[" + i + "].start: " + arrayToString(currentText));
            currentText = sBoxSubstitution(currentText);
            System.out.println("round[" + i + "].s_box: " + arrayToString(currentText));
            currentText = rowSubstitution(currentText);
            System.out.println("round[" + i + "].s_row: " + arrayToString(currentText));
            if (i < 10) {
                currentText = columnMultiplication(currentText);
                System.out.println("round[" + i + "].m_col: " + arrayToString(currentText));
            }
            currentkey = keySchedule(currentkey, i);
            System.out.println("round[" + i + "].k_sch: " + arrayToString(currentkey));
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
        int count = 0;
        String[] finalResult = new String[4];
        for(int j = 0; j < 4; j++) {
            if (j == 0) {
                String[] shiftedColumn = new String[]{key[13], key[14], key[15], key[12]};
                String[] afterSubstitution = sBoxSubstitution(shiftedColumn);
                String column1 = arrayToString(afterSubstitution);
                String[] column2 = new String[4];
                for (int i = 0; i < 4; i++) {
                    column2[i] = key[i];
                }
                String[] result = xorStringLength4hex(column1, arrayToString(column2));
                String keyXor = new String();
                if (round == 9) {
                    keyXor = "1b000000";
                } else if (round == 10) {
                    keyXor = "36000000";
                }
                else {
                    keyXor = Integer.toHexString((int) Math.pow(2, round - 1)) + "000000";
                }
                StringBuilder sb = new StringBuilder(keyXor);
                while (sb.length() < 8) {
                    sb.insert(0, "0");
                }
                keyXor = sb.toString();
                finalResult = xorStringLength4hex(arrayToString(result), keyXor);
            } else {
                String[] columnOne = finalResult;
                String[] columnTwo = new String[]{key[(4*j)], key[1+(4*j)], key[2+(4*j)], key[3+(4*j)]};
                finalResult = xorStringLength4hex(arrayToString(columnOne), arrayToString(columnTwo));
            }
            for (int k = 0; k < 4; k++) {
                finalKey[count] = finalResult[k];
                count++;
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
