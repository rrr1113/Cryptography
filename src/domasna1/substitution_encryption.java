package domasna1;

import java.util.Scanner;

public class substitution_encryption {
    public static void main(String[] args) {
        String plain_text;
        plain_text = new Scanner(System.in).nextLine();

        String cleaned_text = plain_text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        System.out.println("Број на карактери:" + cleaned_text.length());
        System.out.println(cleaned_text);


        String letters = "abcdefghijklmnopqrstuvwxyz";
        String key = "dpisnlxfcygzejtqvkubhoawmr";

        String encrypted_text = "";
        for (char c : cleaned_text.toCharArray()) {
            int i = letters.indexOf(c);
            encrypted_text += key.charAt(i);
        }
        System.out.println("Број на карактери:" + cleaned_text.length());
        System.out.println(encrypted_text);
    }
}
