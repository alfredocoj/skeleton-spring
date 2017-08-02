package br.uema.skeleton.utils;

public class SetSession {
    private static String erro = "Texto de erro";

    public static void setErrorSession(String erro){
        System.out.println("setDebug");
        SetSession.erro = erro;

    }

    public static String getErrorSession(){
        System.out.println("getDebug");
        return SetSession.erro;
    }
}
