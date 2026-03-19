package substitution;

import java.util.*;
import java.util.stream.Collectors;

public class substitution_decryption_bigrams {
    public static void main(String[] args) {
        String encrypted_text;
        encrypted_text = new Scanner(System.in).nextLine();

        Map<String, Integer> bigramFreq = new HashMap<>();
        char prev = 0;

        for (int i = 0; i < encrypted_text.length() - 1; i++) {
            String bigram = "" + encrypted_text.charAt(i) + encrypted_text.charAt(i + 1);
            bigramFreq.put(bigram, bigramFreq.getOrDefault(bigram, 0) + 1);
        }

        bigramFreq.forEach((k, v) -> System.out.println(k + " : " + v));

        List<String> sortedBigramFreq = bigramFreq.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(50)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(sortedBigramFreq);

        List<String> commonBigrams = Arrays.asList(
                "TH", "HE", "IN", "ER", "AN", "RE", "ON", "AT", "EN", "ND",
                "TI", "ES", "OR", "TE", "OF", "ED", "IS", "IT", "AL", "AR",
                "ST", "TO", "NT", "NG", "SE", "HA", "AS", "OU", "IO", "LE",
                "VE", "CO", "ME", "DE", "RI", "RO", "IC", "NE", "EA", "RA",
                "CE", "LI", "CH", "LL", "BE", "MA", "SI", "OM", "UR", "FA"
        );

        StringBuilder decryptedText = new StringBuilder();
        int i = 0;
        while (i < encrypted_text.length() - 1) {
            String bigram = "" + encrypted_text.charAt(i) + encrypted_text.charAt(i + 1);
            if (sortedBigramFreq.contains(bigram)) {
                int idx = sortedBigramFreq.indexOf(bigram);
                decryptedText.append(commonBigrams.get(idx));
                i += 2;
            } else {
                decryptedText.append(encrypted_text.charAt(i));
                i += 1;
            }
        }

        System.out.println(decryptedText);
    }
}
