import java.lang.Math;
import java.util.Scanner;

public class Game {
    String[] ingilizce;
    String[] turkce;
    String[] tempI;
    String[] tempT;
    int maxSize;
    int kelimesayisi;
    public Game(String[] ingilizce, String[] turkce,int kelimesayisi, int maxSize) {
        this.ingilizce = ingilizce;
        this.turkce = turkce;
        this.tempT=turkce.clone();
        this.tempI=ingilizce.clone();
        this.kelimesayisi=kelimesayisi;
        this.maxSize=maxSize;
    }

    public void gameStart() {
        Scanner sc = new Scanner(System.in);
        boolean program = true;
        while (program) {
            System.out.println("Seçim yapın:");
            System.out.println("1. Türkçe karşılığını bul ->");
            System.out.println("2. İngilizce karşılığını bul ->");
            System.out.println("3. Oyundan Çık");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    String[] falseWordsT =new String[maxSize*2];
                    int indexNT=0;
                    int correctAnswerT = 0;
                    int falseAnswerT = 0;
                    int tempKelimeSayisiT =kelimesayisi;
                    System.out.println("--------------------------------------------------------------------");
                    while (tempKelimeSayisiT > 0) {
                        int indexOfWord = (int) (Math.random() * maxSize);
                        if (tempI[indexOfWord] != null) {
                            System.out.println("İşte Yeni kelime: ");
                            System.out.print(tempI[indexOfWord] + " = ");
                            String answerFromUser = sc.nextLine().toLowerCase();
                            if (turkce[indexOfWord].toLowerCase().equals(answerFromUser)) {
                                ++correctAnswerT;
                                --tempKelimeSayisiT;
                                System.out.printf("Doğru Bildiniz, Tebrikler! Doğru Sayınız=%d - Yanlış Sayısınız=%d \n", correctAnswerT, falseAnswerT);
                            }
                            else {
                                ++falseAnswerT;
                                --tempKelimeSayisiT;
                                System.out.printf("********* !!!! Yanlış !!!! ********* --> Doğrusu : '%s'|| Doğru Sayınız=%d - Yanlış Sayısınız=%d  \n ", turkce[indexOfWord], correctAnswerT, falseAnswerT);
                                falseWordsT[indexNT] = tempI[indexOfWord];
                                falseWordsT[indexNT+1] = tempT[indexOfWord];
                                indexNT=indexNT+2;
                            }
                            tempT[indexOfWord] = null;
                            tempI[indexOfWord] = null;
                            System.out.println("--------------------------------------------------------------------");
                        }
                    }
                    tempI=ingilizce.clone();
                    tempT=turkce.clone();
                    result(falseWordsT, correctAnswerT, falseAnswerT);

                case "2":
                    String[] falseWordsI =new String[maxSize*2];
                    int indexNI =0;
                    int correctAnswerI = 0;
                    int falseAnswerI = 0;
                    int tempKelimeSayisiI =kelimesayisi;
                    System.out.println("--------------------------------------------------------------------");
                    while (tempKelimeSayisiI > 0) {
                        int indexOfWord = (int) (Math.random() * maxSize);
                        if (tempT[indexOfWord] != null) {
                            System.out.println("İşte Yeni kelime: ");
                            System.out.print(tempT[indexOfWord] + " = ");
                            String answerFromUser = sc.nextLine().toLowerCase();
                            if (ingilizce[indexOfWord].toLowerCase().equals(answerFromUser)) {
                                ++correctAnswerI;
                                --tempKelimeSayisiI;
                                System.out.printf("Doğru Bildiniz, Tebrikler! Doğru Sayınız=%d - Yanlış Sayısınız=%d \n", correctAnswerI, falseAnswerI);
                            }
                            else {
                                ++falseAnswerI;
                                --tempKelimeSayisiI;
                                System.out.printf("********* !!!! Yanlış !!!! ********* --> Doğrusu : '%s'|| Doğru Sayınız=%d - Yanlış Sayısınız=%d  \n ", ingilizce[indexOfWord], correctAnswerI, falseAnswerI);
                                falseWordsI[indexNI] = tempI[indexOfWord];
                                falseWordsI[indexNI +1] = tempT[indexOfWord];
                                indexNI = indexNI +2;
                            }
                            tempT[indexOfWord] = null;
                            tempI[indexOfWord] = null;
                            System.out.println("--------------------------------------------------------------------");
                        }
                    }
                    tempI=ingilizce.clone();
                    tempT=turkce.clone();
                    result(falseWordsI, correctAnswerI, falseAnswerI);
                    break;

                case "3":
                    System.out.println("Oyundan Çıkılıyor ...");
                    System.out.println("--------------------------------------------------------------------");
                    program = false;
                    break;
            }
        }
    }

    private void result(String[] falseWords, int correctAnswer, int falseAnswer) {
        if (correctAnswer ==kelimesayisi) {
            System.out.printf("TEBRİKLER TÜM KELİMELERİ BİLDİNİZ !!!!!! Doğru Sayınız=%d - Yanlış Sayısınız= KOCAMAN BİR 0 :) \n", correctAnswer);
            System.out.println("--------------------------------------------------------------------");
        }
        else {
            System.out.printf("İşte Sonuçların :) Doğru Sayınız=%d - Yanlış Sayısınız=%d \n", correctAnswer, falseAnswer);
            System.out.println("Yanlış Bildiğin Kelimeler :( :");
            System.out.println("--------------------------------------------------------------------");
            for (int i = 0; i < falseAnswer *2; i+=2) {
                System.out.println(falseWords[i] + " = " + falseWords[i+1]);
            }
            System.out.println("--------------------------------------------------------------------");
        }
    }
}