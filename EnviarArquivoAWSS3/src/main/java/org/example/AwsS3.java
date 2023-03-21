package org.example;

import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AwsS3 {
    // Preencha os valores abaixo
    final String awsS3AccessKey  = "AccessKey";
    final  String awsS3SecretKey  = "SecretKey";
    final String awsS3BucketName = "BucketName";

    public void enviarArquivoS3ComMetaData() {
        System.out.println("Iniciando ambiente para UPload para S3");
        System.out.println("-----------------------------------------");

        System.out.println(" Configurando credenciais.");

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                awsS3AccessKey,
                awsS3SecretKey
        );

        // Crie o provedor de credenciais
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        System.out.println(" Configurando provedor.");

        // Crie o cliente S3 usando o provedor de credenciais
        S3Client client = S3Client.builder()
                .region(Region.US_EAST_1) // Defina a região do S3
                .credentialsProvider(credentialsProvider) // Defina o provedor de credenciais
                .build();

        // Definie o path do arquivo e o path para onde o arquivo vai na S3
        String targetPathFileS3 = "TESTE_VIA_JAVA_METADATA/";
        String targetPathFile   = "D:/teste/";
        String targetFile       = "teste.pdf";

        System.out.println(" Configurando objeto para a requisição.");

        // Preparando lista de metadata
        Map<String, String> metadataMap = new HashMap<>();
        metadataMap.put("campo-1", "valor-campo-1");
        metadataMap.put("campo-2", "valor-campo-2");
        metadataMap.put("campo-3", "valor-campo-3");
        metadataMap.put("campo-4", "valor-campo-4");

        // Crie o objeto PutObjectRequest com o nome do bucket e o nome do arquivo que você deseja fazer o upload
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(awsS3BucketName)
                .key( targetPathFileS3 + targetFile )
                .metadata( metadataMap )
                .build();

        // Especifique o arquivo que você deseja fazer upload
        File file = new File( targetPathFile + targetFile );

        System.out.println(" Enviando arquivo.");

        // Execute o upload usando o método putObject() e o objeto RequestBody.fromFile()
        client.putObject(request, RequestBody.fromFile(file));

        System.out.println(" Fechando conexão com o servidor S3.");
        // Feche o cliente S3
        client.close();

        System.out.println("-----------------------------------------");
        System.out.println("Fim  do envio");
    }

    public void enviarArquivoS3() {
        System.out.println("Iniciando ambiente para UPload para S3");
        System.out.println("-----------------------------------------");

        System.out.println(" Configurando credenciais.");

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                awsS3AccessKey,
                awsS3SecretKey
        );

        // Crie o provedor de credenciais
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        System.out.println(" Configurando provedor.");

        // Crie o cliente S3 usando o provedor de credenciais
        S3Client client = S3Client.builder()
                .region(Region.US_EAST_1) // Defina a região do S3
                .credentialsProvider(credentialsProvider) // Defina o provedor de credenciais
                .build();

        // Definie o path do arquivo e o path para onde o arquivo vai na S3
        String targetPathFileS3 = "TESTE_VIA_JAVA/";
        String targetPathFile   = "D:/teste/";
        String targetFile       = "teste.pdf";

        System.out.println(" Configurando objeto para a requisição.");

        // Crie o objeto PutObjectRequest com o nome do bucket e o nome do arquivo que você deseja fazer o upload
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(awsS3BucketName)
                .key( targetPathFileS3 + targetFile )
                .build();

        // Especifique o arquivo que você deseja fazer upload
        File file = new File( targetPathFile + targetFile );

        System.out.println(" Enviando arquivo.");

        // Execute o upload usando o método putObject() e o objeto RequestBody.fromFile()
        client.putObject(request, RequestBody.fromFile(file));

        System.out.println(" Fechando conexão com o servidor S3.");
        // Feche o cliente S3
        client.close();

        System.out.println("-----------------------------------------");
        System.out.println("Fim  do envio");
    }

    public void enviarArquivoS3SemCredenciais() {
        System.out.println("Iniciando ambiente para UPload para S3");
        System.out.println("-----------------------------------------");

        // Crie um cliente S3
        S3Client client = S3Client.builder()
                .region(Region.US_EAST_1) // Defina a região do S3
                .build();

        String targetPathFileS3 = "TESTE_VIA_JAVA/";
        String targetPathFile   = "D:/teste/";
        String targetFile       = "teste.pdf";

        // Crie o objeto PutObjectRequest com o nome do bucket e o nome do arquivo que você deseja fazer o upload
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(awsS3BucketName)
                .key(targetPathFile + targetFile )
                .build();

        // Especifique o arquivo que você deseja fazer upload
        File file = new File( targetPathFile + targetFile );

        // Execute o upload usando o método putObject() e o objeto RequestBody.fromFile()
        client.putObject(request, RequestBody.fromFile(file));

        System.out.println(" Fechando conexão com o servidor S3.");
        // Feche o cliente S3
        client.close();

        System.out.println("-----------------------------------------");
        System.out.println("Fim  do envio");
    }
}


