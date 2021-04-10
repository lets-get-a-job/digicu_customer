package com.example.digicu_customer.ui.main.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.ShopDataModel;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {
    MutableLiveData<List<ShopDataModel>> shopDataModelMutableLiveData;

    public MutableLiveData<List<ShopDataModel>> getShopDataModelMutableLiveData() {
        if (shopDataModelMutableLiveData == null) {
            shopDataModelMutableLiveData = new MutableLiveData<>();
            loadShopDataModel();
        }

        return shopDataModelMutableLiveData;
    }

    private void loadShopDataModel() {
        List<ShopDataModel>shopDataModels = new ArrayList<>();
        shopDataModels.add(new ShopDataModel("test1", "상도동1", "010-1234-1234", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2Ff%2Ff1%2FSamsung_logo_blue.png%2F1200px-Samsung_logo_blue.png&imgrefurl=https%3A%2F%2Fko.wikinews.org%2Fwiki%2F%25ED%258C%258C%25EC%259D%25BC%3ASamsung_logo_blue.png&tbnid=KUkZih-o9KPayM&vet=12ahUKEwiwt7W93_LvAhXLzYsBHUtvCWMQMygBegUIARC6AQ..i&docid=bInBWFu3dkGBJM&w=1200&h=393&q=samsung%20logo&ved=2ahUKEwiwt7W93_LvAhXLzYsBHUtvCWMQMygBegUIARC6AQ"));
        shopDataModels.add(new ShopDataModel("test2", "상도동2", "010-1234-1234", "https://www.google.com/imgres?imgurl=https%3A%2F%2Flogodownload.org%2Fwp-content%2Fuploads%2F2017%2F10%2Fstarbucks-logo-0.png&imgrefurl=https%3A%2F%2Fen.logodownload.org%2Fstarbucks-logo%2F&tbnid=XHiILoMy81elMM&vet=12ahUKEwiHwb3H3_LvAhVQzIsBHVlJAr4QMygAegUIARC7AQ..i&docid=yCq5yea7Irm04M&w=4096&h=4096&q=startbucks%20logo&ved=2ahUKEwiHwb3H3_LvAhVQzIsBHVlJAr4QMygAegUIARC7AQ"));
        shopDataModels.add(new ShopDataModel("test3", "상도동3", "010-1234-1234", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fi.pinimg.com%2Foriginals%2Fd5%2Faf%2F73%2Fd5af7344a7415abe0908fd01e28adc54.png&imgrefurl=https%3A%2F%2Fwww.pinterest.co.kr%2Fpin%2F462322717980122585%2F&tbnid=WhbjuOSettXq5M&vet=12ahUKEwjQverQ3_LvAhVL0pQKHUXvAUIQMygBegUIARCsAQ..i&docid=1Qg_R2ZzxMFJ_M&w=549&h=386&q=angelinus%20logo&ved=2ahUKEwjQverQ3_LvAhVL0pQKHUXvAUIQMygBegUIARCsAQ"));
        shopDataModels.add(new ShopDataModel("test4", "상도동4", "010-1234-1234", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fimage.freepik.com%2Ffree-vector%2Fcoffee-cafe-logo-design_57043-146.jpg&imgrefurl=https%3A%2F%2Fwww.freepik.com%2Fpremium-vector%2Fcoffee-cafe-logo-design_2756500.htm&tbnid=70E64fN9IMEHHM&vet=12ahUKEwiJ7MDc3_LvAhWZEKYKHZ84CE4QMygDegUIARDNAQ..i&docid=r_6tNJSQR8rQkM&w=626&h=375&q=cafe%20logo&ved=2ahUKEwiJ7MDc3_LvAhWZEKYKHZ84CE4QMygDegUIARDNAQ"));
        shopDataModels.add(new ShopDataModel("test5", "상도동5", "010-1234-1234", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.pngkey.com%2Fpng%2Fdetail%2F192-1929751_colombian-restaurant-colombian-cafe-coffee-barista-cafe-logo.png&imgrefurl=https%3A%2F%2Fwww.pngkey.com%2Fdetail%2Fu2w7w7a9o0q8q8r5_colombian-restaurant-colombian-cafe-coffee-barista-cafe-logo%2F&tbnid=lnxIYwe8OU7h7M&vet=10CEoQMyiIAWoXChMI6IPK59_y7wIVAAAAAB0AAAAAEAI..i&docid=1upSewuna-3l2M&w=820&h=364&q=cafe%20logo&ved=0CEoQMyiIAWoXChMI6IPK59_y7wIVAAAAAB0AAAAAEAI"));

        shopDataModelMutableLiveData.setValue(shopDataModels);
    }
}
