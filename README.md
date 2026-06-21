![S.C.H.A.L.E](static/S.C.H.A.L.E-logo-transparent.png)
# schale

現職の案件のクラス設計・実装の振り返りやPoC検証のためのサンドボックスとしてECサイトを作成  
本リポジトリはそのバックエンド部分で共通的に使用できるパーツをまとめたリポジトリ

---

![Java 17](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot 3.5.9](https://img.shields.io/badge/Spring_Boot-3.5.9-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)

## 使用技術
- Java 17 (Corretto)
- Spring Boot 3.5.9
- AWS DynamoDB (Enhanced Client)
- Gradle 8.10.2
- Lombok
- Docker Compose（ローカルDynamoDB）

## パッケージ構成
サブモジュール構成にしており、リポジトリを超えて共通的に扱うモジュールを配置し、  
こちらのjarは起動しないようにしてください。

| パッケージ名   | 役割                                                   |
|----------|------------------------------------------------------|
| dynamodb | DynamoDBへアクセスするためのクライアントをラッパーした各種テーブルにアクセスするためのパッケージ |