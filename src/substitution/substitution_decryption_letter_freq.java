package substitution;

import java.util.*;
import java.util.stream.Collectors;

public class substitution_decryption_letter_freq {
    public static void main(String[] args) {
        String encrypted_text;
        encrypted_text = new Scanner(System.in).nextLine();
        encrypted_text = encrypted_text.toUpperCase();

        Map<Character, Integer> freq = new HashMap<>();

        for (char c : encrypted_text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        freq.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));

        List<Character> sortedFreq = freq.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(sortedFreq);


        String most_freq_english = "ETAOINSHRDLCUMWFGYPBVKJXQZ";

        String decrypted_text_letters = "";
        for (char c : encrypted_text.toCharArray()) {
            int i = sortedFreq.indexOf(c);
            decrypted_text_letters += most_freq_english.charAt(i);
        }

        System.out.println(decrypted_text_letters);
    }
}


