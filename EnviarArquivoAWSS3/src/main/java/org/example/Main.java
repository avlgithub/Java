package org.example;

public class Main {
    public static  void main(String[] args) {
            AwsS3  awsS3 = new AwsS3();

            //awsS3.enviarArquivoS3();
            awsS3.enviarArquivoS3ComMetaData();
            //  awsS3.enviarArquivoS3SemCredenciais();
    }
}