/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev.pkg2.melike;

import java.util.Scanner;

/**
 *
 * @author melikekecelioglu
 */
/**
 * @file bp-odev2-melike
 * @description istenilen yon, stepnumber kadar matris kenarlari donduren uygulama
 * @assignement 2.odev
 * @date 10/12/2020
 * @author Melike KECELIOGLU / melike.kecelioglu@stu.fsm.edu.tr
 */
public class Odev2Melike {

    public static void main(String[] args) {

        System.out.println("*2D Array Uygulamasına hosgeldiniz*");              //Menu kismi
        System.out.println("");
        Scanner s = new Scanner(System.in);
        System.out.println("Lutfen secenek seciniz: ");
        System.out.println("1. Uygulamayi calistir");
        System.out.println("2. Matris oluştur");
        System.out.println("3. Direction");
        System.out.println("4. Step number");
        System.out.println("5. Exit");
        int array[] = {5, 5, -1, 100, 2};                                       //Array'in set edilmesi
        int matrix[][] = new int[array[0]][array[1]];                           //matrix'in set edilmesi                           
        for (int i = 0; i < array[0]; i++) {                                    //matrix olusturma
            for (int j = 0; j < array[1]; j++) {
                matrix[i][j] = (int) (Math.random() * array[3]);
            }
        }

        boolean control = true;                                                 //boolean kontrol atamasi              
        while (control) {                                                       //boolean ile kontrol true olduğu surece donguye girecek.
            int counter = 0;
            System.out.print("Secenek:");                                       //kullanicidan secenek isteniyor.
            String secenek = s.nextLine();                                      //String tipi degiskene secenek ataniyor.
            secenekKontrol(secenek);                                            //secenek'in 0-5 arasinda olmasini kontrol eden metod cagriliyor.
            parseInt(secenek);                                                  //String tipindeki degiskeni int e cevirmek icin yazdigim metod cagriliyor.
            int k = parseInt(secenek);                                          //metodu int k degerine atiyorum. Böylece kotroleri yapilan secenklere assagidaki ifli yapilarda Secenek'i atayabileyim.
            if (k == 5) {                                                       //secenek 5 oldugunda cikti verip boolean false oldugundan while dongusunden cikiyor.
                System.out.println("Cikis yapiliyor..");
                control = false;
            } else if (k == 1) {
                shiftingmatris(matrix, array[1]);
                matrix = print(matrix, array);

            } else if (k == 2) {
                secenek2(array);
                matrix = new int[array[0]][array[1]];                           //Ekrana istenilen matrisi yazdirmasi icin for donguleri ile matris olusturuluyor.
                for (int i = 0; i < array[0]; i++) {
                    for (int j = 0; j < array[1]; j++) {
                        matrix[i][j] = (int) (1 + Math.random() * array[3]);
                        System.out.print(matrix[i][j] + " ");
                    }
                    System.out.println("");
                }

            } else if (k == 3) {                                                //secenek 3 secildiginde ilgili metod cagriliyor.
               
             secenek3Kontrol(array);

            } else if (k == 4) {                                                //seceenk 4 oldugunda ilgili metod cagriliyor.

                secenek4(array);

            }
        }
    }                                                                           //main kisminda sadece secenekler oldugundan burada main bitiyor ve metodlara geciliyor.

    public static int[][] print(int[][] matrix, int[] array) {                  //secenek 1 'de donen matrisi ve donmeden onceki halini yazdirmak icin print metodu basliyor.

        for (int i = 0; i < matrix.length; i++) {                               //oncelikle matrisin ilk hali for donguleri ile her eleman gezilerek ekrana bastiriliyor.
            for (int j = 0; j < matrix[i].length; j++) {

                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println(" ");
        }

        for (int i = 0; i < array[4]; i++) {                                    //dondurulmus matrisi bastirmak icin tek for dongusu ile ilgili metod cagriliyor ve step number kadar donduruluyor.
            matrix = shiftingmatris(matrix, array[2]);
        }
        System.out.println(" ");
        for (int i = 0; i < matrix.length; i++) {                               //dondurulmus matris bastiriliyor.
            for (int j = 0; j < matrix.length; j++) {

                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println(" ");
        }
        return matrix;

    }

    public static void print1(int[][] yenimatris, int[] array, int[][] matrix) { //secenek 1 'de donen matrisi ve donmeden onceki halini yazdirmak icin print metodu basliyor.

        for (int i = 0; i < yenimatris.length; i++) {                            //oncelikle matrisin ilk hali for donguleri ile her eleman gezilerek ekraan bastiriliyor.
            for (int j = 0; j < yenimatris[i].length; j++) {

                System.out.print(yenimatris[i][j] + "  ");
            }
            System.out.println(" ");
        }

        for (int i = 0; i < array[4]; i++) {                                    //dondurulmus matrisi bastirmak icin tek for dongusu ile ilgili metod cagriliyor ve step number kadar donduruluyor.
            yenimatris = shiftingmatris(matrix, array[2]);
        }
        System.out.println(" ");
        for (int i = 0; i < yenimatris.length; i++) {                           //dondurulmus matris bastiriliyor.
            for (int j = 0; j < yenimatris.length; j++) {

                System.out.print(yenimatris[i][j] + "  ");
            }
            System.out.println(" ");
        }

    }

    static int[][] shiftingmatris(int[][] y_matris, int yon) {                  //matrisi donduren metod parametre olarak matris ve yon aliyorum.
        int[][] yenimatris = new int[y_matris.length][y_matris[0].length];      //islem yapacagim matrisin satir ve sutun bilgisini yeni bir matrise atiyorum.
        if (yon == -1) {                                                        //yon -1 oldugu surece satirin baslangic scope'undan bitisine kadar olan islemler gerceklestirilecek.
            for (int i = 0; i < y_matris.length; i++) {                         //satir sayisi kadar giden ilk for
                for (int j = 0; j < y_matris[0].length; j++) {                  //sutun saysii kadar giden for
                    if (i == 0 && j != y_matris[0].length - 1) {                //satir 0'sa ve sutun ilk sutun'un uzunlugundan 1 eksikse if icindeki islemler yapiliyor.
                        yenimatris[i][j + 1] = y_matris[i][j];                  //yenimatris'in i'ninci sutun'u yeni matris'in i+1.'ci sutuna ataniyor. cunku yon -1 yani elemanlarin 1 sola kaymasi lazim.
                    } else if (i == y_matris.length - 1 && j != 0) {            //sutun 0'sa ve satir satir uzunlugunun 1 eksinine esitse else if icine giriyor.
                        yenimatris[i][j - 1] = y_matris[i][j];                  //yenimatris'in sutun'u sutun-1. elemanina ataniyor. yon 1 oldugundan dolayi ayni mantik gecerli.
                    }
                }
            }

            for (int i = 0; i < y_matris.length; i++) {                         //ustteki ilk for'da aslinda kenar elemanlarla ilgilendik. simdi ise geri kalan elemanlarla ilgilenecegiz.
                for (int j = 0; j < y_matris[0].length; j++) {
                    if (i != 0 && j == 0) {                                      //satir 0'a esit degilken sutun sabit, 0 iken if icerisindeki islmler gerceklesecek.
                        yenimatris[i - 1][j] = y_matris[i][j];                  //yon -1 oldugundan dolayi i. satir i-1.elemana atacanak. 
                    } else if (j == y_matris[1].length - 1
                            &&                                                  //1.sutun uzunlugunun sutun'a esit olması ve satir'in sutun uzunlugu-1'e esit olmamasi durumunda iceri girecek.
                            i != y_matris.length - 1) {
                        yenimatris[i + 1][j] = y_matris[i][j];                   //i. indisdeki eleman i+1. indisdeki elmana atacak.

                    }
                }
            }

        } else if (yon == 1) {                                                  //yon 1 oldugu surece ilgili scope'lar arasindaki islemler yapilacak.
            for (int i = 0; i < y_matris.length; i++) {
                for (int j = 0; j < y_matris[1].length; j++) {
                    if (i == 0 && j != 0) {                                     //satir'in 0, sutunun 0'a esit olmadigi surece sutunu gezioruz.
                        yenimatris[i][j - 1] = y_matris[i][j];                  //yeni matris'e j. indisdeki eleman j-1'e atanacak. cunku yon 1

                    } else if (i == y_matris.length - 1
                            &&                                                  //109. satirdaki kod ile ayni mantik sadece i, j yer degistiriyo dolayisiyla 1,0 oluyor.
                            j != y_matris[0].length - 1) {
                        yenimatris[i][j + 1] = y_matris[i][j];                  //j.eleman j+1.indisdeki eleman ataniyor.
                    }
                }
            }
            for (int i = 0; i < y_matris.length; i++) {
                for (int j = 0; j < y_matris[0].length; j++) {
                    if (j == 0 && i != y_matris.length - 1) {                   //sutun 0, satir matris uzunluk-1'e esit olmadigi surece alt satir islemleri gerceklesecek.
                        yenimatris[i + 1][j] = y_matris[i][j];
                    } else if (j == y_matris[0].length - 1 && i != 0) {         //satir0, sutun 0.satir uzunlugu-1 'e esit olmadigi surece alt satir islmeleri gerceklesecek.
                        yenimatris[i - 1][j] = y_matris[i][j];

                    }
                }
            }

        }

        for (int i = 1; i < y_matris.length - 1; i++) {                         //y matris elemanlarini yeni matris'e atamak icin for aciyorum.
            for (int j = 1; j < y_matris[1].length - 1; j++) {
                yenimatris[i][j] = y_matris[i][j];
            }
        }
        return yenimatris;                                                      //dondrudugum matris'i return ediyorum.
    }

    public static void secenek2(int arra[]) {                                   //secenek2 metodu
        Scanner s = new Scanner(System.in);

        System.out.print("Dizinin matris satir boyutunu giriniz: ");            //kullanicidan satir isteniyor.
        String satir = s.nextLine();
        sKontrol(satir);                                                        //kontrol icin ilgili metod cagriliyor
        parseInt(satir);                                                        //string degeri int 'e cevirmek icin kullanılan metod cagriliyor.
        arra[0] = parseInt(satir);                                             

        while (parseInt(satir) < 0) {                                           //girilen deger 0'dan kucuk ise kullanici uyari alacak
            System.out.println("Satir 0'dan kucuk olamaz...");
            System.out.print("Lutfen matris satir boyutunu giriniz:");
            satir = s.nextLine();
            if (parseInt(satir) > 0) {                                          //girilen deger 0'dan buyuk ise sikinti yok.
                break;
            }
        }

        System.out.print("Dizinin matris sutun giriniz: ");                     //ustteki kontrollerin aynilari sutun ve aralik icin de gecerli.
        String sutun = s.nextLine();
        sKontrol(sutun);
        parseInt(sutun);

        while (parseInt(sutun) < 0) {
            System.out.println("Sutun 0'dan kucuk olamaz...");
            System.out.print("Lutfen matris sutun boyutunu giriniz:");
            sutun = s.nextLine();
            if (parseInt(sutun) > 0) {
                break;
            }
            break;
        }
        arra[1] = parseInt(sutun);
        System.out.print("Dizinin 0 ile kac araliginda olmasini istediginizi giriniz: ");
        String aralik = s.nextLine();
        sKontrol(aralik);
        while (parseInt(aralik) < 0) {
            System.out.println("Aralik 0'dan kucuk olamaz...");
            System.out.print("Lutfen matris icin aralik degeri giriniz:");
            aralik = s.nextLine();
            if (parseInt(aralik) > 0) {
                break;
            }
        }
        arra[3] = parseInt(aralik);
        System.out.println(" { " + arra[0] + " , " + arra[1] + " , " + arra[2] + " , " + arra[3] + " , " + arra[4] + "}");

        System.out.println(" ");
    }
    
     public static void secenek3Kontrol(int[] array) {                          //secenek3 kontrol edilmesi icin olusturulan metod.                                            
        Scanner s = new Scanner(System.in);
        System.out.print("Yon giriniz: ");                             
        String yon = s.nextLine();  
        
       while(parseInt(yon)!= 1 ||parseInt(yon)!= -1){                          //int'e cevrilen yon degeri 1 veya -1'e esit odegilse uyarilar verilip tekrar isteniyor.
           System.out.print("Lutfen 1 veya -1 giriniz!!!");
           yon=s.nextLine();
           if(parseInt(yon)==1||parseInt(yon)==-1){                            // deger 1 veya -1 'e esitse sikinti yok.
           break;
       }
       }
       array[2]=parseInt(yon);                                                  //gerekli kontroller saglandiktan sonra sikinti yoksa yon degerini array[2]'e atiyourm.
                System.out.println(" { " + array[0] + " , "                     //ve array'in son halini yazdiriyorum.
                        + array[1] + " , "  
                        + array[2] + " , " + array[3] + " , " + array[4] + "}");
            
    }
    

    

    public static int[][] rastgeleMatrisOlustur(int matrix[][],                 //secenek 2 secildiginde belirlenen aralik ile matris olusturma kismi
            int satir, int sutun, int aralik) {
        int[][] matri = new int[satir][sutun];
        System.out.print("\nMatris:\n");
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                matrix[i][j] = (int) (1 + Math.random() * aralik);              //rastgele sayilarla belirlenen araliklarda matris olustuyor.
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        return matrix;                                                          
    }

    public static void secenek4(int[] array) {                                  //secenk 4 secildiginde cagrilan metod.
        Scanner s = new Scanner(System.in);
        System.out.print("Step number giriniz: ");                              //kullancidan String tipinde step number isteniyor.
        String step = s.nextLine();
        sKontrol(step);                                                         //satir,sutun ve aralikta yapilan tum konttrollerin ayni kontroller yapiliyor.
        parseInt(step);

        while (parseInt(step) < 0) {
            System.out.println("Step number 0'dan kucuk olamaz...");
            System.out.print("Lutfen step number giriniz:");
            step = s.nextLine();
            if (parseInt(step) > 0) {
                break;
            }
        }
        array[4] = parseInt(step);
        System.out.println(" { " + array[0] + " , " + array[1] + " , " + array[2] + " , " + array[3] + " , " + array[4] + "}");

    }

    public static boolean secenekKontrol(String secenek) {                     //seceneklere harf, sayi disi kaarket, 0-5 disinda girilmesi muhtemel her deger icin kontrol metodu.

        Scanner s = new Scanner(System.in);

        for (int i = 0; i < secenek.length(); i++) {                            //String tipinde aldigim secenek degiskeninin uzunlugu kadar giden her bir elmeanini tariyan for dongusu.
            if (secenek.charAt(i) > 53 || secenek.charAt(i) < 49) {             //i. eleman ASCII tablosunda 0-5 degerlerinin disinda olan degerler arasinda ise if icine giriyor.
                return false;

            } else {                                                            //ASCII tablosunda 0-5 arasindaki elemanlar gecerlidir. ilgili scope'dan cikilir.
                continue;
            }
        }
        return true;
    }

    public static String sKontrol(String parameter) {                           //0-9 arasi deger girilebilmesi icin olusturaln kontrol metodu

        Scanner s = new Scanner(System.in);
        boolean kontrol = true;
        while (kontrol) {
            for (int i = 0; i < parameter.length(); i++) {
                if (parameter.charAt(i) > 48 && parameter.charAt(i) < 57) {
                    break;

                } else {
                    System.out.println("Lutfen tekrar giriniz..");
                    parameter = s.nextLine();
                    break;
                }

            }
            break;
        }

        return parameter;
    }

    public static int parseInt(String str) {                                    //String degeri int cevirne metod.
        int i = 0, n = 0, tut= 1;
        if (str.charAt(0) == '-') {                                             //ilk eleman ASCII'de 45.eleman ise i 1, tut -1 oluyor.
            i = 1;
            tut = -1;
        }
        for (; i < str.length(); i++) {                                         //string uzunlugu kadar ilerleyen dongu ile her seferinde n sayisina indideki karakter 48 cikartiliyor.
           
            n += str.charAt(i) - 48;                                            //ASCII'de sayilarin 48 den baslamasi dolayisiyla 48 cikartiliyor.
        }
        int m = tut * n;                                                        //degerleri m degiskeninde tutup return ediyourm ki disaridan erisilebilsin.
        return m;
    }


   

}
