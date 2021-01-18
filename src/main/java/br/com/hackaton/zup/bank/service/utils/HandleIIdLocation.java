package br.com.hackaton.zup.bank.service.utils;

public class HandleIIdLocation {

    public HandleIIdLocation(){}

    public static Long handle(String headerLocation){
        String header = headerLocation.replace("http://localhost:8080/proposal/", ""); //TODO: ALTERAR URI MANUAL
        return Long.parseLong(header);
    }
}
