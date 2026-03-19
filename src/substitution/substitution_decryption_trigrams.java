package substitution;

import java.util.*;
import java.util.stream.Collectors;

public class substitution_decryption_trigrams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String encryptedText = sc.nextLine();
        //encryptedText = encryptedText.toLowerCase();

        Map<String, Integer> trigramFreq = new HashMap<>();
        for (int i = 0; i < encryptedText.length() - 2; i++) {
            String trigram = "" + encryptedText.charAt(i) + encryptedText.charAt(i + 1) + encryptedText.charAt(i + 2);
            trigramFreq.put(trigram, trigramFreq.getOrDefault(trigram, 0) + 1);
        }

        trigramFreq.forEach((k, v) -> System.out.println(k + " : " + v));

        List<String> sortedTrigramFreq = trigramFreq.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(26)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Top trigrams: " + sortedTrigramFreq);


        List<String> commonTrigrams = Arrays.asList(
                "THE", "AND", "ING", "HER", "ERE", "ENT", "THA", "NTH", "WAS",
                "ETH", "FOR", "DTH", "HAT", "ION", "TIO", "VER", "TER", "HES",
                "HIS", "OFT", "FTH", "STH", "OTH", "RES", "ONT", "STO"
        );


        StringBuilder decryptedText = new StringBuilder();
        int i = 0;
        while (i < encryptedText.length() - 2) {
            String trigram = "" + encryptedText.charAt(i) + encryptedText.charAt(i + 1) + encryptedText.charAt(i + 2);
            if (sortedTrigramFreq.contains(trigram)) {
                int idx = sortedTrigramFreq.indexOf(trigram);
                decryptedText.append(commonTrigrams.get(idx));
                i += 3;
            } else {
                decryptedText.append(encryptedText.charAt(i));
                i += 1;
            }
        }

        System.out.println(decryptedText);
    }
}

