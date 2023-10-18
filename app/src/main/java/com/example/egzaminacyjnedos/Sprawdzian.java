package com.example.egzaminacyjnedos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Sprawdzian implements Comparable<Sprawdzian> {
    private String przedmiot;
    private String coDoZrobienia;
    private Date dataWykonania;

    public Sprawdzian(String przedmiot, String coDoZrobienia, Date dataWykonania) {
        this.przedmiot = przedmiot;
        this.coDoZrobienia = coDoZrobienia;
        this.dataWykonania = dataWykonania;
    }


    public Date getDataWykonania() {
        return dataWykonania;
    }

    public void setDataWykonania(Date dataWykonania) {
        this.dataWykonania = dataWykonania;
    }

    public String getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(String przedmiot) {
        this.przedmiot = przedmiot;
    }

    public String getCoDoZrobienia() {
        return coDoZrobienia;
    }

    public void setCoDoZrobienia(String coDoZrobienia) {
        this.coDoZrobienia = coDoZrobienia;
    }

    @Override
    public String toString() {
        return przedmiot + ": " + coDoZrobienia + " - " + odczytajDate(dataWykonania); //odczytajDate(dataWykonania);
    }

    public static String odczytajDate(Date kotek){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(kotek);
        calendar.set(Calendar.YEAR, 2023);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(calendar.getTime());
    }

    public int compareTo(Sprawdzian spr) {
        if (getDataWykonania() == null || spr.getDataWykonania() == null)
            return 0;
        return getDataWykonania().compareTo(spr.getDataWykonania());
    }
}
