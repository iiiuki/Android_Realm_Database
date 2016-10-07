package com.example.hiep.androidrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Doc https://realm.io/docs/java/latest/

        // Tạo đối tượng  Realm
        /*
            Chú ý rằng gọi Realm.getInstance mà không truyền vào một RealmConfiguration thì sẽ tạo ra một file cơ sở dữ liệu Realm tên là default.realm.
            Realm myRealm = Realm.getInstance(getApplicationContext());
         */
        Realm myRealm = Realm.getInstance(new RealmConfiguration.Builder(getApplicationContext()).name("iiiuki.realm").build());

        /*
        Tạo transaction

        Trong khi đọc dữ liệu từ Realm là rất đơn giản, bạn sẽ thấy trong các bước tiếp theo, ghi dữ liệu vào nó sẽ hơi phức tạp hơn.
        Để bắt đầu một transaction, sử dụng phương thức beginTransaction. Tương tự, để kết thúc một transaction, sử dụng phương thức commitTransaction.
         */

        // Cách 1
        myRealm.beginTransaction();

        Animal animalDog = myRealm.createObject(Animal.class);
        animalDog.setId(0);
        animalDog.setName("Dog");

        myRealm.commitTransaction();

        // Cách 2
        Animal animalCat=new Animal(1,"Cat");
        myRealm.beginTransaction();
        Animal animalCatCopy = myRealm.copyToRealm(animalCat);
        myRealm.commitTransaction();



        /*
         Truy vấn

         Realm cung cấp một API rất trực quan và rõ ràng để tạo các truy vấn. Để tạo một truy vấn, sử dụng "where" của đối tượng Realm và truyền vào đối tượng mà bạn muốn truy vấn.
         Sau khi tạo truy vấn xong, bạn có thể sử dụng phương thức findAll để lấy tất cả các kết qủa, trả về dưới dạng RealmResults object.
         */

        // Lấy tất cả đối tượng
        RealmResults<Animal> results1 =myRealm.where(Animal.class).findAll();

        for(Animal animal:results1) {
            Log.d(MainActivity.class.getSimpleName(),"===> name: "+ animal.getName());
        }

        //Realm cung cấp một số phương thức, chẳng hạn như beginsWith, endsWith, lesserThan và greaterThan, bạn có thể sử dụng chúng để lọc các kết qủa trả về.
        // Lấy đối tượng có id > 0
        RealmResults<Animal> results2=myRealm.where(Animal.class).greaterThan("id",0).findAll();
        if (results2.size()>0){
            for(Animal animal:results2) {
                Log.d(MainActivity.class.getSimpleName(),"===>i d > 0, name: "+ animal.getName());
            }
        }

        //Nếu bạn muốn kết quả của truy vấn trả về được sắp xếp, bạn có thể sử dụng phương thức findAllSorted
        // Sắp xếp theo tên

        RealmResults<Animal> results3=myRealm.where(Animal.class).findAllSorted("name",false);
        if (results3.size()>0){
            for(Animal animal:results3) {
                Log.d(MainActivity.class.getSimpleName(),"===> sorted, name: "+ animal.getName());
            }
        }

        //
        Animal animal = myRealm.where(Animal.class).equalTo("id", 2).findFirst();
        Log.d(MainActivity.class.getSimpleName(),"===> equalTo, name: "+ animal.getName());

        //Update
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Animal brid = realm.where(Animal.class).equalTo("id", 2).findFirst();
                brid.setName("bird *** ");
            }
        });
        Log.d(MainActivity.class.getSimpleName(),"===> equalTo, name: "+ animal.getName());
    }
}
