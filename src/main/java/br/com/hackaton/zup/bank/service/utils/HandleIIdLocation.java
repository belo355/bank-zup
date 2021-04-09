package br.com.hackaton.zup.bank.service.utils;

public class HandleIIdLocation {

    private HandleIIdLocation(){}

    public static Long handle(String headerLocation){
        String header = headerLocation.replace("http://localhost:8080/proposal/", "");
        return Long.parseLong(header);
    }
}
