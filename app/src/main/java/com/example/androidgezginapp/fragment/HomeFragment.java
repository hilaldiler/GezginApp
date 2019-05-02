package com.example.androidgezginapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidgezginapp.model.Place;
import com.example.androidgezginapp.adapter.PlaceAdapter;
import com.example.androidgezginapp.R;

import java.util.ArrayList;
import java.util.List;
public class HomeFragment extends Fragment {

    List<Place> postList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyler_view_posts);

        postList.add(new Place(R.drawable.istanbul, "İstanbul",
                "Avrupa ve Asya'yı birbirine bağlayan, çok sayıda medeniyetin izlerini taşıyan istanbul..."));
        postList.add(new Place(R.drawable.kastamonu, "Kastamonu", "Türkiye ya da resmî adıyla Türkiye Cumhuriyeti, topraklarının büyük bölümü Anadolu'ya, " +
                "küçük bir bölümü ise Balkanlar'ın uzantısı olan Trakya'ya yayılmış bir ülke. Kuzeybatıda Bulgaristan, " +
                "batıda Yunanistan, kuzeydoğuda Gürcistan, doğuda Ermenistan, İran ve Azerbaycan'ın ekslav toprağı Nahçıvan, " +
                "güneydoğuda ise Irak ve Suriye komşusudur. Güneyini Akdeniz, batısını Ege Denizi ve kuzeyini Karadeniz çevreler. " +
                "Marmara Denizi ise İstanbul Boğazı ve Çanakkale Boğazı ile birlikte Anadolu'yu Trakya'dan yani Asya'yı Avrupa'dan ayırır. " +
                "Türkiye, Avrupa ve Asya'nın kavşak noktasında yer alması sayesinde önemli bir jeostratejik güce sahiptir."));
        postList.add(new Place(R.drawable.mugla, "Mugla",
                "Muğla, Türkiye'nin bir ili ve en kalabalık yirmi dördüncü şehri. 2016 itibarıyla 923.773 " +
                        "nüfusa sahiptir.[1] Ege Bölgesi'nde, topraklarının küçük bir kısmı Akdeniz Bölgesi içine giren," +
                        " Ortaca, Dalaman, Köyceğiz, Fethiye, Marmaris, Milas, Datça ve Bodrum gibi tatil yöreleri ile ünlü bir yerleşim yeridir. " +
                "İlde 13 ilçe bulunur."));

        PlaceAdapter adapter = new PlaceAdapter(postList, getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation
                (LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager
                (linearLayoutManager);

        return view;
    }
}
