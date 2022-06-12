package com.example.news;

public class news {

    private int id;
    private String url;
    private String heading;
    private String descryption;
    private String reference;

    public news(int id,String url,String heading,String desc,String ref){

        this.descryption=desc;
        this.id=id;
        this.heading=heading;
        this.reference=ref;
        this.url=url;

    }

    public String getUrl(){

        return this.url;
    }
    public String getDescryption(){

        return this.descryption;
    }
    public String getHeading(){

        return this.heading;
    }
    public String getReference(){

        return this.reference;
    }
    public int getId(){

        return this.id;
    }


    public String toString() {
        return  String.valueOf(id) + "\n " + String.valueOf(url)+ "\n" + String.valueOf(heading)+ "\n" + String.valueOf(descryption);

    }
}
