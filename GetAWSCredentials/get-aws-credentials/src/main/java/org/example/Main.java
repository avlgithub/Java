package org.example;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.AWSSecretsManagerException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;


public class Main {
    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Comando para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para Unix/Linux/macOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Erro ao limpar o console: " + e.getMessage());
        }
    }


    public static AWSCredentials credentials(String accessKey, String secretValue) {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretValue);
        return credentials;
    }

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] parts = arg.split("=", 2);
                params.put(parts[0].replace("--", ""), parts[1]);
            }
        }

        // Validar se os parâmetros obrigatórios estão presentes
        if (!params.containsKey("accessKey") ||
                !params.containsKey("secretKey") ||
                !params.containsKey("secretName")) {
                System.err.println("");
                System.err.println("Informe os parametro(s): --accessKey=<value> --secretKey=<value> --secretName=<value> --region=<value>");
                System.err.println("");
            return;
        }

        // Obter os parâmetros
        String paramClearConsole = params.getOrDefault("clearConsole", "N");
        String paramACSKey = params.get("accessKey");
        String paramSCT = params.get("secretKey");
        String paramSCName = params.get("secretName");
        String paramRg = params.getOrDefault("region", "us-east-1"); // Região padrão como "us-east-1"

        if (paramClearConsole.toUpperCase().equals("S")){
            clearConsole();
        }

        System.out.println();
        System.out.println(":: Aguarde...");

    try{
        AWSSecretsManager client = AWSSecretsManagerClientBuilder
                                        .standard()
                                        .withCredentials(
                                            new AWSStaticCredentialsProvider(
                                                    credentials(paramACSKey, paramSCT)
                                                )
                                        )
                                        .withRegion( paramRg ).build();

        GetSecretValueRequest secretValueRequest = new GetSecretValueRequest().withSecretId(paramSCName);
        GetSecretValueResult secretValueResult = null;

        secretValueResult = client.getSecretValue(secretValueRequest);

        String secretValue=secretValueResult.getSecretString();
        System.out.println(" Secret Value: " + secretValue);
        System.out.println(":: Fim do processo!");
        System.out.println();

        Gson gson=new Gson();
        Map<String,Object> map=gson.fromJson(secretValue, Map.class);
        map.forEach((k,v)->System.err.println("key:"+k+",value:"+v));

    } catch (AWSSecretsManagerException e) {
        System.err.println("Erro ao acessar o Secrets Manager: " + e.getMessage());
    } catch (IllegalArgumentException e) {
        System.err.println("Erro nos argumentos fornecidos: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
    }
    }
}