import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    String[] ingilizce;
    String[] turkce;
    String[] tempI;
    String[] tempT;

    int kelimesayisi;
    public Game(String[] ingilizce, String[] turkce,int kelimesayisi) {
        this.ingilizce = ingilizce;
        this.turkce = turkce;
        this.tempT=turkce.clone();
        this.tempI=ingilizce.clone();
        this.kelimesayisi=kelimesayisi;
    }

    public void gameStart() {
        Scanner sc = new Scanner(System.in);
        boolean program = true;
        while (program) {
            System.out.println("Seçim yapın:");
            System.out.println("1. Oyunu başlat");
            System.out.println("2. Oyundan Çık");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    ArrayList<String> falseWords = new ArrayList<String>();
                    int correctAnswer = 0;
                    int falseAnswer = 0;
                    int tempKelimeSayisi=kelimesayisi;
                    System.out.println("--------------------------------------------------------------------");
                    while (tempKelimeSayisi > 0) {
                        int indexOfWord = (int) (Math.random() * 5000);
                        if (tempI[indexOfWord] != null) {
                            System.out.println("İşte Yeni kelime: ");
                            System.out.print(tempI[indexOfWord] + " = ");
                            String answerFromUser = sc.nextLine().toLowerCase();
                            if (turkce[indexOfWord].toLowerCase().equals(answerFromUser)) {
                                ++correctAnswer;
                                --tempKelimeSayisi;
                                System.out.printf("Doğru Bildiniz, Tebrikler! Doğru Sayınız=%d - Yanlış Sayısınız=%d \n", correctAnswer, falseAnswer);
                                tempT[indexOfWord] = null;
                                tempI[indexOfWord] = null;
                                System.out.println("--------------------------------------------------------------------");
                            } else {
                                System.out.printf("Maalesef Yanlış :( Doğrusu : '%s' \n ", turkce[indexOfWord]);
                                ++falseAnswer;
                                --tempKelimeSayisi;
                                falseWords.add(tempI[indexOfWord]);
                                tempT[indexOfWord] = null;
                                tempI[indexOfWord] = null;
                                System.out.println("--------------------------------------------------------------------");
                            }
                        }
                    }
                    tempI=ingilizce.clone();
                    tempT=turkce.clone();
                    if (correctAnswer==kelimesayisi) {
                        System.out.printf("TEBRİKLER TÜM KELİMELERİ BİLDİNİZ !!!!!! Doğru Sayınız=%d - Yanlış Sayısınız= KOCAMAN BİR 0 :) \n", correctAnswer);
                        System.out.println("--------------------------------------------------------------------");
                        break;
                    } else {
                        if (!falseWords.isEmpty()) {
                            System.out.printf("İşte Sonuçların :) Doğru Sayınız=%d - Yanlış Sayısınız=%d \n", correctAnswer, falseAnswer);
                            System.out.println("Yanlış Bildiğin Kelimeler :( :");
                            System.out.println(falseWords);
                            System.out.println("--------------------------------------------------------------------");
                            break;
                        }
                    }
                    correctAnswer=0;
                    falseAnswer=0;
                    break;
                case "2":
                    System.out.println("Oyundan Çıkılıyor ...");
                    System.out.println("--------------------------------------------------------------------");
                    program = false;
                    break;
            }
        }
    }
}