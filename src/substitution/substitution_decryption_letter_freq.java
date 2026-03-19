package substitution;

import java.util.*;
import java.util.stream.Collectors;

public class substitution_decryption {
    public static void main(String[] args) {
        /*String encrypted_text;
        encrypted_text = new Scanner(System.in).nextLine();

        Map<Character, Integer> freq = new HashMap<>();
        Map<String, Integer> bigramFreq = new HashMap<>();
        Map<String, Integer> trigramFreq = new HashMap<>();
        char prev = 0;
        char prev_prev = 0;

        for (char c : encrypted_text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            if (prev != 0) {
                String bigram = "" + prev + c;
                bigramFreq.put(bigram, bigramFreq.getOrDefault(bigram, 0) + 1);
                if(prev_prev != 0) {
                    String trigram = prev_prev + "" + prev + c;
                    trigramFreq.put(bigram, trigramFreq.getOrDefault(bigram, 0) + 1);
                }
                prev_prev = prev;
            }
            prev = c;
        }

        freq.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
        bigramFreq.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));


        List<Character> sortedFreq = freq.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<String> sortedBigramFreq = bigramFreq.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(26)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<String> sortedTrigramFreq = trigramFreq.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(26)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(sortedFreq);
        System.out.println(sortedBigramFreq);
        System.out.println(sortedTrigramFreq);


        String most_freq_english = "ETAOINSHRDLCUMWFGYPBVKJXQZ";
        List<String> commonBigrams = Arrays.asList(
                "TH", "HE", "IN", "ER", "AN", "RE", "ON", "AT", "EN", "ND",
                "TI", "ES", "OR", "TE", "OF", "ED", "IS", "IT", "AL", "AR",
                "ST", "TO", "NT", "NG", "SE", "HA"
        );
        List<String> commonTrigrams = Arrays.asList(
                "THE", "AND", "ING", "HER", "ERE", "ENT", "THA", "NTH", "WAS",
                "ETH", "FOR", "DTH", "HAT", "ION", "TIO", "VER", "TER", "HES",
                "HIS", "OFT", "FTH", "STH", "OTH", "RES", "ONT", "STO"
        );

        String decrypted_text_letters = "";
        String decrypted_text_bigrams = "";
        String decrypted_text_trigrams = "";
        prev = 0;
        for (char c : encrypted_text.toCharArray()) {
            int i = sortedFreq.indexOf(c);
            decrypted_text_letters += most_freq_english.charAt(i);
        }

        for (int k = 0; k < encrypted_text.length(); k++) {
            char c = encrypted_text.charAt(k);
            if (k > 0) {
                String bigram = "" + encrypted_text.charAt(k - 1) + c;

                if (sortedBigramFreq.contains(bigram)) {
                    int j = sortedBigramFreq.indexOf(bigram);
                    decrypted_text_bigrams += commonBigrams.get(j);
                    k+=1;
                    continue;
                } else {
                    decrypted_text_bigrams += bigram;
                }
            }
        }

        System.out.println(decrypted_text_letters.toString());
        System.out.println(decrypted_text_bigrams.toString());


        for (int k = 0; k < encrypted_text.length(); k++) {
            char c = encrypted_text.charAt(k);

            int i = sortedFreq.indexOf(c);
            decrypted_text_letters += most_freq_english.charAt(i);

            if (k > 1) {
                String trigram = "" + encrypted_text.charAt(k - 2) + encrypted_text.charAt(k - 1) + c;

                if (sortedTrigramFreq.contains(trigram)) {
                    int j = sortedTrigramFreq.indexOf(trigram);
                    decrypted_text_trigrams += commonTrigrams.get(j);
                    k += 2;
                    continue;
                } else {
                    decrypted_text_trigrams += trigram;
                }
            }
        }
        System.out.println(decrypted_text_trigrams.toString()); */


        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        Map<Character, Integer> letters = new HashMap<>();
        Map<String, Integer> bigrams = new HashMap<>();
        Map<String, Integer> trigrams = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            letters.put(c, letters.getOrDefault(c, 0) + 1);

            if (i < text.length() - 1) {
                String bigram = text.substring(i, i + 2);
                bigrams.put(bigram, bigrams.getOrDefault(bigram, 0) + 1);
            }

            if (i < text.length() - 2) {
                String trigram = text.substring(i, i + 3);
                trigrams.put(trigram, trigrams.getOrDefault(trigram, 0) + 1);
            }
        }

        System.out.println("Letters: " + letters);
        System.out.println("Bigrams: " + bigrams);
        System.out.println("Trigrams: " + trigrams);



        Scanner sc1 = new Scanner(System.in);
        String encrypted = sc1.nextLine();

        // example frequency order you got from analysis
        List<Character> sortedCipher = Arrays.asList(
                'X','U','D','B','N','Y','L','T','W','S','G','H','R','M','P','Q','V','O','F','K','C','A','I','J','Z','E'
        );

        String englishFreq = "ETAOINSHRDLCUMWFGYPBVKJXQZ";

        // build substitution map
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < sortedCipher.size(); i++) {
            map.put(sortedCipher.get(i), englishFreq.charAt(i));
        }

        // decrypt
        String decrypted = "";
        for (char c : encrypted.toCharArray()) {
            if (map.containsKey(c)) {
                decrypted += map.get(c);
            } else {
                decrypted += c;
            }
        }

        System.out.println(decrypted);

    }
}


