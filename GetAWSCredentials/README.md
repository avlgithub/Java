# AWS Secrets Manager - Java Example

Este projeto em Java conecta-se ao **AWS Secrets Manager** para recuperar valores secretos armazenados na AWS, usando credenciais de acesso fornecidas via parâmetros de linha de comando. Ele usa o **AWS SDK** para autenticar e acessar o segredo, e o **Gson** para converter o valor secreto (em formato JSON) para um mapa de chave-valor, exibindo-o no console.

## Como Funciona

1. O código recebe os seguintes parâmetros de entrada:
   - `accessKey`: Chave de acesso da AWS.
   - `secretKey`: Chave secreta da AWS.
   - `secretName`: Nome do segredo a ser recuperado.
   - `region` (opcional): Região AWS onde o segredo está armazenado. Se não fornecido, a região padrão `us-east-1` será usada.

2. O segredo é acessado através do **AWS Secrets Manager**. Caso o segredo seja encontrado, seu conteúdo será impresso no console.

## Pré-requisitos

- **AWS SDK para Java**: Para interagir com o AWS Secrets Manager.
- **Credenciais AWS**: O usuário precisa ter permissões para acessar o Secrets Manager.

## Como Rodar

Para executar o código, use os seguintes parâmetros de linha de comando:

```bash
java -jar app.jar <accessKey> <secretKey> <secretName> [region]
