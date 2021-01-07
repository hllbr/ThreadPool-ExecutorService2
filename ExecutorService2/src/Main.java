
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        //ilk olarak bir thread havuzu oluştuyoruz.Ardından havuza göre bir executorservice oluşturmamız gerek.Bu iş atamalarını,task verme işlemlerini executorservice üzerinden yepmamız lazım.
        
        ExecutorService exeser = Executors.newFixedThreadPool(2);//nThreads kısmı en fazla kaç thread'in aynı anda çalışmasını istyorsak belirtmemiz gerekn alandır.
        //havuzu oluşturduk bizim threadleri oluşturmamız ve bunlara iş ataması yapmamız gerekiyor.
        for(int i = 1;i<=5;i++){
            //bir metod kullanıyoruz ve bunun içersinde thread oluşturuyoruz for içersinde.Metodumuz sayesinde threade görev ataması yapmış oluyoruz.
            exeser.submit(new Worker(String.valueOf(i), i));//bu yapıyla exeser içersinde 5 yask ve 5 thread oluşturduk
        }
        exeser.shutdown();
        System.out.println("Bütün işler teslim edildi.");

     
            //işler teslim edildi bu noktaya kadar burada biz javaya ben başka hiçbir işi kabul etmiyorum...
//hiçbir iş ataması yapmıcam.Threadlerimizin işi bitene kadar exeseri kapatmayacağım.işler bitince kapatıcam şeklinde bir ifade olmuş oldu.
//shutdown metodunu kullanmazsak exeser bizim main metodunu(main threadi hiçbir zaman bitirmicek).

//biz belirli bir süre geçtiğinde threadleri kapatmak isteyebilirim.bunun için
          try {
        exeser.awaitTermination(1, TimeUnit.DAYS);//en fazla bir gün beklerim.
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
