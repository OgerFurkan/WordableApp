import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dosyaAdi = "Words.txt";
        int maxSize=5000;
        String[] ingilizceKelimeDizisi = new String[maxSize];
        String[] turkceKelimeDizisi = new String[maxSize];
        int kelimeSayisi = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Words.txt"))) {
            String satir;
            while ((satir = br.readLine()) != null && kelimeSayisi < maxSize) {
                String[] kelimeler = satir.split(" = ");
                if (kelimeler.length == 2) {
                    ingilizceKelimeDizisi[kelimeSayisi] = kelimeler[0];
                    turkceKelimeDizisi[kelimeSayisi] = kelimeler[1];
                    kelimeSayisi++;
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        boolean programDevam = true;

        while (programDevam) {
            System.out.println("Seçim yapın:");
            System.out.println("1. Kelimeleri Listele.");
            System.out.println("2. Yeni Kelime Ekle.");
            System.out.println("3. Hafıza Oyununu Başlatın.");
            System.out.println("4. Kelime Güncelleyin.");
            System.out.println("5. Kelime Silin.");
            System.out.println("6. Programı Sonlandır.");
            System.out.println("--------------------------------------------------------------------");
            String secim = scanner.nextLine();
            System.out.println("--------------------------------------------------------------------");

            switch (secim) {
                case "1":
                    System.out.println("Mevcut Kelimeler:");
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("********************************************************************");
                    System.out.printf("İngilizce  -  Türkçe   ||  Toplam Kelime Sayısı: %d \n",kelimeSayisi);
                    for (int i = 0; i < kelimeSayisi; i++) {
                        System.out.println(ingilizceKelimeDizisi[i] + " = " + turkceKelimeDizisi[i]);
                    }
                    System.out.println("********************************************************************");
                    System.out.println("--------------------------------------------------------------------");
                    break;
                case "2":
                    boolean flag=true;
                    System.out.print("İngilizce kelimeyi girin: ");
                    String yeniIngilizceKelime = scanner.nextLine();
                    for (String temp:ingilizceKelimeDizisi) {
                        if(temp==null){
                        }
                        else if(temp.equalsIgnoreCase(yeniIngilizceKelime)){
                            System.out.println("!!!!! Bu kelime mevcut !!!!! ");
                            System.out.println("--------------------------------------------------------------------");
                            flag=false;
                        }
                    }
                    if (flag){
                        System.out.print("Türkçe karşılığını girin: ");
                        String yeniTurkceKelime = scanner.nextLine();
                        if (kelimeSayisi < maxSize) {
                            ingilizceKelimeDizisi[kelimeSayisi] = yeniIngilizceKelime;
                            turkceKelimeDizisi[kelimeSayisi] = yeniTurkceKelime;
                            kelimeSayisi++;
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter(dosyaAdi, true))) {
                                bw.write(yeniIngilizceKelime + " = " + yeniTurkceKelime);
                                bw.newLine();
                            } catch (IOException e) {
                                System.out.println("Dosyaya yazma hatası: " + e.getMessage());
                            }
                            System.out.println("Kelime başarıyla eklendi.");
                            System.out.println("--------------------------------------------------------------------");
                        }
                    }
                    else if(kelimeSayisi>=5000) {
                        System.out.println("Dizi dolmuş, yeni kelime eklenemiyor.");
                        System.out.println("--------------------------------------------------------------------");
                    }
                    break;

                case"3":
                    Game game = new Game(ingilizceKelimeDizisi,turkceKelimeDizisi,kelimeSayisi);
                    game.gameStart();
                    break;
                case"4":
                    System.out.println("Güncellemek istediğiniz ingilizce kelimenin numarasını seçin ");
                    boolean foundUpdate=false;
                    for (int i = 0; i < kelimeSayisi; i++) {
                        System.out.println(i+1+")"+ingilizceKelimeDizisi[i] + " = " + turkceKelimeDizisi[i]);
                    }
                    int number=scanner.nextInt();
                    for (int i=1;i<=kelimeSayisi;i++){
                        if(number==i){
                            System.out.print("Yeni anlamını yazınız: ");
                            String updatedWord=scanner.next();
                            System.out.println();
                            turkceKelimeDizisi[i-1]=updatedWord;
                            System.out.println("Kelime başarıyla güncellendi...");
                            System.out.println("--------------------------------------------------------------------");
                            foundUpdate=true;
                            break;
                        }
                    }
                    if(!foundUpdate){
                        System.out.println("Girdiğiniz numara geçersiz...");
                    }
                    updateFile(kelimeSayisi,ingilizceKelimeDizisi,turkceKelimeDizisi);
                    break;
                case"5":
                    System.out.print("Silmek istediğiniz İngilizce kelimeyi girin: ");
                    String delete = scanner.nextLine();
                    boolean foundDelete = false;
                    for (int i = 0; i < kelimeSayisi; i++) {
                        if (ingilizceKelimeDizisi[i].equalsIgnoreCase(delete)) {
                            foundDelete = true;
                            for (int j = i; j < kelimeSayisi - 1; j++) {
                                ingilizceKelimeDizisi[j] = ingilizceKelimeDizisi[j + 1];
                                turkceKelimeDizisi[j] = turkceKelimeDizisi[j + 1];
                            }
                            ingilizceKelimeDizisi[kelimeSayisi - 1] = null;
                            turkceKelimeDizisi[kelimeSayisi - 1] = null;
                            kelimeSayisi--;
                            System.out.println("Kelime başarıyla silindi.");
                            break;
                        }
                    }
                    if (!foundDelete) {
                        System.out.println("Kelime bulunamadı.");
                    }
                    updateFile(kelimeSayisi,ingilizceKelimeDizisi,turkceKelimeDizisi);
                    break;
                case "6":
                    System.out.println("Program sonlandırılıyor...");
                    programDevam = false;
                    System.out.println("--------------------------------------------------------------------");
                    break;
                default:
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Geçersiz seçenek. Lütfen 1, 2, 3, 4, 5 veya 6 seçin.");
                    break;
            }
        }
        scanner.close();
    }
    public static void updateFile(int kelimeSayisi,String[] ingilizceKelimeDizisi,String[] turkceKelimeDizisi){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Words.txt"))) {
            for (int i = 0; i < kelimeSayisi; i++) {
                bw.write(ingilizceKelimeDizisi[i] + " = " + turkceKelimeDizisi[i]);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Dosyaya yazma hatası: " + e.getMessage());
        }
    }
}