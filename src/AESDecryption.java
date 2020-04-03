import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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

    public static final HashMap<String, String> ETable = new HashMap<String, String>() {
        {
            put("00", "01"); put("01", "03"); put("02", "05"); put("03", "0f"); put("04", "11"); put("05", "33"); put("06", "55"); put("07", "ff"); put("08", "1a"); put("09", "2e"); put("0a", "72"); put("0b", "96"); put("0c", "a1"); put("0d", "f8"); put("0e", "13"); put("0f", "35");
            put("10", "5f"); put("11", "e1"); put("12", "38"); put("13", "48"); put("14", "d8"); put("15", "73"); put("16", "95"); put("17", "a4"); put("18", "f7"); put("19", "02"); put("1a", "06"); put("1b", "0a"); put("1c", "1e"); put("1d", "22"); put("1e", "66"); put("1f", "aa");
            put("20", "e5"); put("21", "34"); put("22", "5c"); put("23", "e4"); put("24", "37"); put("25", "59"); put("26", "eb"); put("27", "26"); put("28", "6a"); put("29", "be"); put("2a", "d9"); put("2b", "70"); put("2c", "90"); put("2d", "ab"); put("2e", "e6"); put("2f", "31");
            put("30", "53"); put("31", "f5"); put("32", "04"); put("33", "0c"); put("34", "14"); put("35", "3c"); put("36", "44"); put("37", "cc"); put("38", "4f"); put("39", "d1"); put("3a", "68"); put("3b", "b8"); put("3c", "d3"); put("3d", "6e"); put("3e", "b2"); put("3f", "cd");
            put("40", "4c"); put("41", "d4"); put("42", "67"); put("43", "a9"); put("44", "e0"); put("45", "3b"); put("46", "4d"); put("47", "d7"); put("48", "62"); put("49", "a6"); put("4a", "f1"); put("4b", "08"); put("4c", "18"); put("4d", "28"); put("4e", "78"); put("4f", "88");
            put("50", "83"); put("51", "9e"); put("52", "b9"); put("53", "d0"); put("54", "6b"); put("55", "bd"); put("56", "dc"); put("57", "7f"); put("58", "81"); put("59", "98"); put("5a", "b3"); put("5b", "ce"); put("5c", "49"); put("5d", "db"); put("5e", "76"); put("5f", "9a");
            put("60", "b5"); put("61", "c4"); put("62", "57"); put("63", "f9"); put("64", "10"); put("65", "30"); put("66", "50"); put("67", "f0"); put("68", "0b"); put("69", "1d"); put("6a", "27"); put("6b", "69"); put("6c", "bb"); put("6d", "d6"); put("6e", "61"); put("6f", "a3");
            put("70", "fe"); put("71", "19"); put("72", "2b"); put("73", "7d"); put("74", "87"); put("75", "92"); put("76", "ad"); put("77", "ec"); put("78", "2f"); put("79", "71"); put("7a", "93"); put("7b", "ae"); put("7c", "e9"); put("7d", "20"); put("7e", "60"); put("7f", "a0");
            put("80", "fb"); put("81", "16"); put("82", "3a"); put("83", "4e"); put("84", "d2"); put("85", "6d"); put("86", "b7"); put("87", "c2"); put("88", "5d"); put("89", "e7"); put("8a", "32"); put("8b", "56"); put("8c", "fa"); put("8d", "15"); put("8e", "3f"); put("8f", "41");
            put("90", "c3"); put("91", "5e"); put("92", "e2"); put("93", "3d"); put("94", "47"); put("95", "c9"); put("96", "40"); put("97", "c0"); put("98", "5b"); put("99", "ed"); put("9a", "2c"); put("9b", "74"); put("9c", "9c"); put("9d", "bf"); put("9e", "da"); put("9f", "75");
            put("a0", "9f"); put("a1", "ba"); put("a2", "d5"); put("a3", "64"); put("a4", "ac"); put("a5", "ef"); put("a6", "2a"); put("a7", "7e"); put("a8", "82"); put("a9", "9d"); put("aa", "bc"); put("ab", "df"); put("ac", "7a"); put("ad", "8e"); put("ae", "89"); put("af", "80");
            put("b0", "9b"); put("b1", "b6"); put("b2", "c1"); put("b3", "58"); put("b4", "e8"); put("b5", "23"); put("b6", "65"); put("b7", "af"); put("b8", "ea"); put("b9", "25"); put("ba", "6f"); put("bb", "b1"); put("bc", "c8"); put("bd", "43"); put("be", "c5"); put("bf", "54");
            put("c0", "fc"); put("c1", "1f"); put("c2", "21"); put("c3", "63"); put("c4", "a5"); put("c5", "f4"); put("c6", "07"); put("c7", "09"); put("c8", "1b"); put("c9", "2d"); put("ca", "77"); put("cb", "99"); put("cc", "b0"); put("cd", "cb"); put("ce", "46"); put("cf", "ca");
            put("d0", "45"); put("d1", "cf"); put("d2", "4a"); put("d3", "de"); put("d4", "79"); put("d5", "8b"); put("d6", "86"); put("d7", "91"); put("d8", "a8"); put("d9", "e3"); put("da", "3e"); put("db", "42"); put("dc", "c6"); put("dd", "51"); put("de", "f3"); put("df", "0e");
            put("e0", "12"); put("e1", "36"); put("e2", "5a"); put("e3", "ee"); put("e4", "29"); put("e5", "7b"); put("e6", "8d"); put("e7", "8c"); put("e8", "8f"); put("e9", "8a"); put("ea", "85"); put("eb", "94"); put("ec", "a7"); put("ed", "f2"); put("ee", "0d"); put("ef", "17");
            put("f0", "39"); put("f1", "4b"); put("f2", "dd"); put("f3", "7c"); put("f4", "84"); put("f5", "97"); put("f6", "a2"); put("f7", "fd"); put("f8", "1c"); put("f9", "24"); put("fa", "6c"); put("fb", "b4"); put("fc", "c7"); put("fd", "52"); put("fe", "f6"); put("ff", "01");
        }
    };

    public static final HashMap<String, String> LTable = new HashMap<String, String>() {
        {
            put("00", "00"); put("01", "00"); put("02", "19"); put("03", "01"); put("04", "32"); put("05", "02"); put("06", "1a"); put("07", "c6"); put("08", "4b"); put("09", "c7"); put("0a", "1b"); put("0b", "68"); put("0c", "33"); put("0d", "ee"); put("0e", "df"); put("0f", "03");
            put("10", "64"); put("11", "04"); put("12", "e0"); put("13", "0e"); put("14", "34"); put("15", "8d"); put("16", "81"); put("17", "ef"); put("18", "4c"); put("19", "71"); put("1a", "08"); put("1b", "c8"); put("1c", "f8"); put("1d", "69"); put("1e", "1c"); put("1f", "c1");
            put("20", "7d"); put("21", "c2"); put("22", "1d"); put("23", "b5"); put("24", "f9"); put("25", "b9"); put("26", "27"); put("27", "6a"); put("28", "4d"); put("29", "e4"); put("2a", "a6"); put("2b", "72"); put("2c", "9a"); put("2d", "c9"); put("2e", "09"); put("2f", "78");
            put("30", "65"); put("31", "2f"); put("32", "8a"); put("33", "05"); put("34", "21"); put("35", "0f"); put("36", "e1"); put("37", "24"); put("38", "12"); put("39", "f0"); put("3a", "82"); put("3b", "45"); put("3c", "35"); put("3d", "93"); put("3e", "da"); put("3f", "8e");
            put("40", "96"); put("41", "8f"); put("42", "db"); put("43", "bd"); put("44", "36"); put("45", "d0"); put("46", "ce"); put("47", "94"); put("48", "13"); put("49", "5c"); put("4a", "d2"); put("4b", "f1"); put("4c", "40"); put("4d", "46"); put("4e", "83"); put("4f", "38");
            put("50", "66"); put("51", "dd"); put("52", "fd"); put("53", "30"); put("54", "bf"); put("55", "06"); put("56", "8b"); put("57", "62"); put("58", "b3"); put("59", "25"); put("5a", "e2"); put("5b", "98"); put("5c", "22"); put("5d", "88"); put("5e", "91"); put("5f", "10");
            put("60", "7e"); put("61", "6e"); put("62", "48"); put("63", "c3"); put("64", "a3"); put("65", "b6"); put("66", "1e"); put("67", "42"); put("68", "3a"); put("69", "6b"); put("6a", "28"); put("6b", "54"); put("6c", "fa"); put("6d", "85"); put("6e", "3d"); put("6f", "ba");
            put("70", "2b"); put("71", "79"); put("72", "0a"); put("73", "15"); put("74", "9b"); put("75", "9f"); put("76", "5e"); put("77", "ca"); put("78", "4e"); put("79", "d4"); put("7a", "ac"); put("7b", "e5"); put("7c", "f3"); put("7d", "73"); put("7e", "a7"); put("7f", "57");
            put("80", "af"); put("81", "58"); put("82", "a8"); put("83", "50"); put("84", "f4"); put("85", "ea"); put("86", "d6"); put("87", "74"); put("88", "4f"); put("89", "ae"); put("8a", "e9"); put("8b", "d5"); put("8c", "e7"); put("8d", "e6"); put("8e", "ad"); put("8f", "e8");
            put("90", "2c"); put("91", "d7"); put("92", "75"); put("93", "7a"); put("94", "eb"); put("95", "16"); put("96", "0b"); put("97", "f5"); put("98", "59"); put("99", "cb"); put("9a", "5f"); put("9b", "b0"); put("9c", "9c"); put("9d", "a9"); put("9e", "51"); put("9f", "a0");
            put("a0", "7f"); put("a1", "0c"); put("a2", "f6"); put("a3", "6f"); put("a4", "17"); put("a5", "c4"); put("a6", "49"); put("a7", "ec"); put("a8", "d8"); put("a9", "43"); put("aa", "1f"); put("ab", "2d"); put("ac", "a4"); put("ad", "76"); put("ae", "7b"); put("af", "b7");
            put("b0", "cc"); put("b1", "bb"); put("b2", "3e"); put("b3", "5a"); put("b4", "fb"); put("b5", "60"); put("b6", "b1"); put("b7", "86"); put("b8", "3b"); put("b9", "52"); put("ba", "a1"); put("bb", "6c"); put("bc", "aa"); put("bd", "55"); put("be", "29"); put("bf", "9d");
            put("c0", "97"); put("c1", "b2"); put("c2", "87"); put("c3", "90"); put("c4", "61"); put("c5", "be"); put("c6", "dc"); put("c7", "fc"); put("c8", "bc"); put("c9", "95"); put("ca", "cf"); put("cb", "cd"); put("cc", "37"); put("cd", "3f"); put("ce", "5b"); put("cf", "d1");
            put("d0", "53"); put("d1", "39"); put("d2", "84"); put("d3", "3c"); put("d4", "41"); put("d5", "a2"); put("d6", "6d"); put("d7", "47"); put("d8", "14"); put("d9", "2a"); put("da", "9e"); put("db", "5d"); put("dc", "56"); put("dd", "f2"); put("de", "d3"); put("df", "ab");
            put("e0", "44"); put("e1", "11"); put("e2", "92"); put("e3", "d9"); put("e4", "23"); put("e5", "20"); put("e6", "2e"); put("e7", "89"); put("e8", "b4"); put("e9", "7c"); put("ea", "b8"); put("eb", "26"); put("ec", "77"); put("ed", "99"); put("ee", "e3"); put("ef", "a5");
            put("f0", "67"); put("f1", "4a"); put("f2", "ed"); put("f3", "de"); put("f4", "c5"); put("f5", "31"); put("f6", "fe"); put("f7", "18"); put("f8", "0d"); put("f9", "63"); put("fa", "8c"); put("fb", "80"); put("fc", "c0"); put("fd", "f7"); put("fe", "70"); put("ff", "07");
        }
    };

    public static final String[][] multiplicationMatrix = {{"0e", "0b", "0d", "09"}, {"09", "0e", "0b", "0d"}, {"0d", "09", "0e", "0b"}, {"0b", "0d", "09", "0e"}};

    public static void main(String[] args) {

        System.out.println("DECRYPTION\n");
        System.out.println("Enter Ciphertext (16 bytes): ");
        Scanner in = new Scanner(System.in);
        String inputtedPlaintext = in.nextLine();
        System.out.println("Enter Key (16 bytes): ");
        String inputtedKey = in.nextLine();

        String plaintext[] = new String[16];   //  69c4e0d86a7b0430d8cdb78070b4c55a       f4351503aa781c520267d690c42d1f43
        String key[] = new String[16];          // 13111d7fe3944a17f307a78b4d2b30c5       303132333435363738393a3b3c3d3e3f

        int count = 0;
        for (int j = 0; j < 16; j++) {
            plaintext[j] = inputtedPlaintext.substring(count, count+2);
            key[j] = inputtedKey.substring(count, count+2);
            count = count + 2;
        }

        String plaintextString = arrayToString(plaintext);
        String keyString = arrayToString(key);
        System.out.println("round[0].iinput: " + plaintextString);
        System.out.println("round[0].ik_sch: " + keyString);
        String[] currentText = plaintext;
        String[] currentkey = key;
        for (int i = 1; i <= 10; i++) {
            if (i == 1) {
                currentText = xorString(arrayToString(currentText), arrayToString(currentkey));
            }
            System.out.println("round[" + i + "].istart: " + arrayToString(currentText));
            currentText = rowSubstitution(currentText);
            System.out.println("round[" + i + "].is_row: " + arrayToString(currentText));
            currentText = sBoxSubstitution(currentText);
            System.out.println("round[" + i + "].is_box: " + arrayToString(currentText));
            currentkey = keySchedule(currentkey, i);
            System.out.println("round[" + i + "].ik_sch: " + arrayToString(currentkey));
            currentText = xorString(arrayToString(currentText),arrayToString(currentkey));
            if (i < 10) {
                System.out.println("round[" + i + "].ik_add: " + arrayToString(currentText));
                currentText = columnMultiplication(currentText);
            }
            if (i == 10) {
                currentText = xorString(arrayToString(currentText), arrayToString(currentkey));
            }
        }

        String decrpytedText = arrayToString(xorString(arrayToString(currentText), arrayToString(currentkey)));

        System.out.println("round[10].output: " + decrpytedText);

        System.out.println(hexToAscii(decrpytedText));
    }

    private static String hexToAscii(String hexStr) {
        StringBuffer output = new StringBuffer("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
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
            String multiplier1 = "";
            String multiplier2 = "";
            String multiplier3 = "";
            String multiplier4 = "";
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

            int LTableResult1;
            if (Integer.decode(String.format("0x%s", matrixHex1)) == 0) {
                LTableResult1 = 0;
            } else if (Integer.decode(String.format("0x%s", matrixHex1)) == 1) {
                LTableResult1 = Integer.decode(String.format("0x%s", matrixHex1));
            } else {
                LTableResult1 = Integer.decode(String.format("0x%s", LTable.get(multiplier1))) + Integer.decode(String.format("0x%s", LTable.get(matrixHex1)));
                if (LTableResult1 > 0xff) {
                    LTableResult1 = LTableResult1 - 0xff;
                }
            }

            int LTableResult2;
            if (Integer.decode(String.format("0x%s", matrixHex2)) == 0) {
                LTableResult2 = 0;
            } else if (Integer.decode(String.format("0x%s", matrixHex2)) == 1) {
                LTableResult2 = Integer.decode(String.format("0x%s", matrixHex2));
            } else {
                LTableResult2 = Integer.decode(String.format("0x%s", LTable.get(multiplier2))) + Integer.decode(String.format("0x%s", LTable.get(matrixHex2)));
                if (LTableResult2 > 0xff) {
                    LTableResult2 = LTableResult2 - 0xff;
                }
            }

            int LTableResult3;
            if (Integer.decode(String.format("0x%s", matrixHex3)) == 0) {
                LTableResult3 = 0;
            } else if (Integer.decode(String.format("0x%s", matrixHex3)) == 1) {
                LTableResult3 = Integer.decode(String.format("0x%s", matrixHex3));
            } else {
                LTableResult3 = Integer.decode(String.format("0x%s", LTable.get(multiplier3))) + Integer.decode(String.format("0x%s", LTable.get(matrixHex3)));
                if (LTableResult3 > 0xff) {
                    LTableResult3 = LTableResult3 - 0xff;
                }
            }

            int LTableResult4;
            if (Integer.decode(String.format("0x%s", matrixHex4)) == 0) {
                LTableResult4 = 0;
            } else if (Integer.decode(String.format("0x%s", matrixHex4)) == 1) {
                LTableResult4 = Integer.decode(String.format("0x%s", matrixHex4));
            } else {
                LTableResult4 = Integer.decode(String.format("0x%s", LTable.get(multiplier4))) + Integer.decode(String.format("0x%s", LTable.get(matrixHex4)));
                if (LTableResult4 > 0xff) {
                    LTableResult4 = LTableResult4 - 0xff;
                }
            }


            String LTableResultString1 = "";
            String ETableSub1 = "00";
            String LTableResultString2 = "";
            String ETableSub2 = "00";
            String LTableResultString3 = "";
            String ETableSub3 = "00";
            String LTableResultString4 = "";
            String ETableSub4 = "00";


            if (LTableResult1 == 0) {
                ETableSub1 = "00";
            }
            else if (matrixHex1.equals("01")) {
                ETableSub1 = multiplier1;
            }
            else if (LTableResult1 <= 0x09) {
                LTableResultString1 = String.format("%02d", LTableResult1);
                ETableSub1 = ETable.get(LTableResultString1);
            }
            else if (LTableResult1 >= 0x0a && LTableResult1 <= 0x0f) {
                LTableResultString1 = sideCase(LTableResult1);
                ETableSub1 = ETable.get(LTableResultString1);
            }
            else {
                ETableSub1 = ETable.get(Integer.toHexString(LTableResult1));
            }

            if (LTableResult2 == 0) {
                ETableSub2 = "00";
            }
            else if (matrixHex2.equals("01")) {
                ETableSub2 = multiplier2;
            }
            else if (LTableResult2 <= 0x09) {
                LTableResultString2 = String.format("%02d", LTableResult2);
                ETableSub2 = ETable.get(LTableResultString2);
            }
            else if (LTableResult2 >= 0x0a && LTableResult2 <= 0x0f) {
                LTableResultString2 = sideCase(LTableResult2);
                ETableSub2 = ETable.get(LTableResultString2);
            }
            else {
                ETableSub2 = ETable.get(Integer.toHexString(LTableResult2));
            }

            if (LTableResult3 == 0) {
                ETableSub3 = "00";
            }
            else if (matrixHex3.equals("01")) {
                ETableSub3 = multiplier3;
            }
            else if (LTableResult3 <= 0x09) {
                LTableResultString3 = String.format("%02d", LTableResult3);
                ETableSub3 = ETable.get(LTableResultString3);
            }
            else if (LTableResult3 >= 0x0a && LTableResult3 <= 0x0f) {
                LTableResultString3 = sideCase(LTableResult3);
                ETableSub3 = ETable.get(LTableResultString3);
            }
            else {
                ETableSub3 = ETable.get(Integer.toHexString(LTableResult3));
            }

            if (LTableResult4 == 0) {
                ETableSub4 = "00";
            }
            else if (matrixHex4.equals("01")) {
                ETableSub4 = multiplier4;
            }
            else if (LTableResult4 <= 0x09) {
                LTableResultString4 = String.format("%02d", LTableResult4);
                ETableSub4 = ETable.get(LTableResultString4);
            }
            else if (LTableResult4 >= 0x0a && LTableResult4 <= 0x0f) {
                LTableResultString4 = sideCase(LTableResult4);
                ETableSub4 = ETable.get(LTableResultString4);
            }
            else {
                ETableSub4 = ETable.get(Integer.toHexString(LTableResult4));
            }


            binary1 = Integer.toBinaryString(Integer.decode(String.format("0x%s", ETableSub1)));
            binary2 = Integer.toBinaryString(Integer.decode(String.format("0x%s", ETableSub2)));
            binary3 = Integer.toBinaryString(Integer.decode(String.format("0x%s", ETableSub3)));
            binary4 = Integer.toBinaryString(Integer.decode(String.format("0x%s", ETableSub4)));

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

    public static String sideCase(Integer LTableResult) {
        String LTableResultString = "";
        if (LTableResult == 10) {
            LTableResultString = "0a";
        }
        if (LTableResult == 11) {
            LTableResultString = "0b";
        }
        if (LTableResult == 12) {
            LTableResultString = "0c";
        }
        if (LTableResult == 13) {
            LTableResultString = "0d";
        }
        if (LTableResult == 14) {
            LTableResultString = "0e";
        }
        if (LTableResult == 15) {
            LTableResultString = "0f";
        }
        return LTableResultString;
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
