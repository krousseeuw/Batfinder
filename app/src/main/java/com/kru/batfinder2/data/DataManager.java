package com.kru.batfinder2.data;

import com.kru.batfinder2.models.BatDTO;
import com.kru.batfinder2.models.SponsorDTO;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager dataManagerInstance = null;
    private List<BatDTO> mBatDTOS = new ArrayList<>();
    private List<SponsorDTO> mSponsorDTOS = new ArrayList<>();

    public static DataManager getInstance() {
        if(dataManagerInstance == null) {
            dataManagerInstance = new DataManager();
            dataManagerInstance.initializeMockSponsors();

        }

        return dataManagerInstance;
    }

    public List<BatDTO> getAllBats() {
        return mBatDTOS;
    }

    public void updateBatList(List<BatDTO> batDTOS){
        mBatDTOS.addAll(batDTOS);
    }

    public BatDTO returnBatById(int id){
        for (BatDTO batDTO : mBatDTOS){
            if(id == batDTO.getId()){
                return batDTO;
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
                Type batListType = new TypeToken<ArrayList<BatDTO>>(){}.getType();
                mBatDTOS = gson.fromJson(response, batListType);
            }
        })
    }

    private Object createMyReqSuccessListener() {
    }*/

    private void initializeMockSponsors(){
        mSponsorDTOS.add(new SponsorDTO("https://www.bats.org.uk/", "BatDTO Conservation Trust", "UK's Largest BatDTO Organisation", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/apple-touch-icon.png"));
        mSponsorDTOS.add(new SponsorDTO("http://www.batcon.org/", "BatDTO Conservation International", "International BatDTO organisation", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/Bat_Conservation_International_logo.png"));
        mSponsorDTOS.add(new SponsorDTO("https://www.merlintuttle.org/", "Merlin Tuttle's BatDTO Conservation", "US-Based organisation", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/Merlin+Tuttle_s+Bat+Conservation.jpg"));
    }

    private void initializeMockBats(){
        mBatDTOS.add(new BatDTO(1,"Brown long-eared bat","Gewone grootoorvleermuis","Plecotus auritus",
                45,48,"EARAS", "oren",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBatDTOS.add((new BatDTO(2,"Eastern Red BatDTO","Rode vleermuis",
                "Lasiurus borealis",93,117,"Cute ass red bat",
                "Schattige reet vleernuis",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy")));
        mBatDTOS.add(new BatDTO(3,"Hoary bat","Grijze vleermuis",
                "Aeorestes cinereus",130,150, "Hairy bat", "harig",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBatDTOS.add(new BatDTO(4,"Common Vampire BatDTO","Gewone vampier","Desmodus rotundus",
                70,90,"Blood", "Bloed",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBatDTOS.add(new BatDTO(5,"Common Pipistrelle","Dwergvleermuis","Pipistrellus pipistrellus",
                55,54, "Tiny bat", "Kleine vleer",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBatDTOS.add(new BatDTO(6,"Nathusius's pipistrelle","Ruige dwergvleermuis","Pipistrellus nathusii",46,58, "roughboy", "rege",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBatDTOS.add(new BatDTO(7,"Serotine bat","Laatvlieger",
                "Eptesicus serotinus",58,80,
                "Sertotine", "doing",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBatDTOS.add(new BatDTO(8,"Geoffroy's bat","Ingekorven Vleermuis",
                "Myotis emarginatus",41,53,
                "Korvu", "bescrh",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBatDTOS.add(new BatDTO(9,"Natterer's bat","Franjestaart",
                "Myotis nattereri",44,51, "WET", "NAT",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));
        mBatDTOS.add(new BatDTO(10,"Daubenton's bat","Watervleermuis","Myotis daubentonii",
                45,55, "Water", "nat gedoe",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-DtTFWtUCdJlLoZseqV9L5xA4yzOSmyWItrI_nYVwIovsveve", "Photoguy"));

    }

    public List<BatDTO> getAllMockBats() {
        dataManagerInstance.initializeMockBats();
        return mBatDTOS;
    }

    public List<SponsorDTO> getSponsorDTOS() {
        return mSponsorDTOS;
    }
}
