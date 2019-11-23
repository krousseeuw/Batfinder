package com.kru.batfinder2.data;

import com.kru.batfinder2.models.Bat;
import com.kru.batfinder2.models.Sponsor;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager dataManagerInstance = null;
    private List<Bat> mBats = new ArrayList<>();
    private List<Sponsor> mSponsors = new ArrayList<>();

    public static DataManager getInstance() {
        if(dataManagerInstance == null) {
            dataManagerInstance = new DataManager();
            dataManagerInstance.initializeMockSponsors();

        }

        return dataManagerInstance;
    }

    public List<Bat> getAllBats() {
        return mBats;
    }

    public void updateBatList(List<Bat> bats){
        mBats.addAll(bats);
    }

    public Bat returnBatById(int id){
        for (Bat bat : mBats){
            if(id == bat.getId()){
                return bat;
            }
        }

        return null;
    }

    /*private void loadBatsFromApi(){
        String url = "https://localhost:44382/api/Bats";
        final Gson gson = new Gson();

        StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type batListType = new TypeToken<ArrayList<Bat>>(){}.getType();
                mBats = gson.fromJson(response, batListType);
            }
        })
    }

    private Object createMyReqSuccessListener() {
    }*/

    private void initializeMockSponsors(){
        mSponsors.add(new Sponsor("https://www.bats.org.uk/", "Bat Conservation Trust", "UK's Largest Bat Organisation", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/apple-touch-icon.png"));
        mSponsors.add(new Sponsor("http://www.batcon.org/", "Bat Conservation International", "International Bat organisation", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/Bat_Conservation_International_logo.png"));
        mSponsors.add(new Sponsor("https://www.merlintuttle.org/", "Merlin Tuttle's Bat Conservation", "US-Based organisation", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/Merlin+Tuttle_s+Bat+Conservation.jpg"));
    }

    private void initializeMockBats(){
        mBats.add(new Bat(1,"Brown long-eared bat","Gewone grootoorvleermuis","Plecotus auritus",
                45,48,"EARAS", "oren",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBats.add((new Bat(2,"Eastern Red Bat","Rode vleermuis",
                "Lasiurus borealis",93,117,"Cute ass red bat",
                "Schattige reet vleernuis",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy")));
        mBats.add(new Bat(3,"Hoary bat","Grijze vleermuis",
                "Aeorestes cinereus",130,150, "Hairy bat", "harig",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBats.add(new Bat(4,"Common Vampire Bat","Gewone vampier","Desmodus rotundus",
                70,90,"Blood", "Bloed",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBats.add(new Bat(5,"Common Pipistrelle","Dwergvleermuis","Pipistrellus pipistrellus",
                55,54, "Tiny bat", "Kleine vleer",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBats.add(new Bat(6,"Nathusius's pipistrelle","Ruige dwergvleermuis","Pipistrellus nathusii",46,58, "roughboy", "rege",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBats.add(new Bat(7,"Serotine bat","Laatvlieger",
                "Eptesicus serotinus",58,80,
                "Sertotine", "doing",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBats.add(new Bat(8,"Geoffroy's bat","Ingekorven Vleermuis",
                "Myotis emarginatus",41,53,
                "Korvu", "bescrh",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBats.add(new Bat(9,"Natterer's bat","Franjestaart",
                "Myotis nattereri",44,51, "WET", "NAT",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBats.add(new Bat(10,"Daubenton's bat","Watervleermuis","Myotis daubentonii",
                45,55, "Water", "nat gedoe",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));

    }

    public List<Bat> getAllMockBats() {
        dataManagerInstance.initializeMockBats();
        return mBats;
    }

    public List<Sponsor> getSponsors() {
        return mSponsors;
    }
}
