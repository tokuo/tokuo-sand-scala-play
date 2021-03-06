# 選定理由
## JDK version
Scala、PlayFlameworkに対応しており、LTSの最新版が11であるため採用

## sbt
Scalaはバージョンによる互換性がないため、クロスビルド機能が存在するsbtが標準的である。  
一方で本アプリはライブラリとしてリリースするわけではないため、クロスビルドを必要としない可能もありmavenでも問題ないとも思われる。
また自身が以前javaを主に使用していたため馴染みのあるmavenやgradleを採用することも考えたが、
同時にそこまで複雑な設定を現時点で記述することは考えられないため学習コストも低く、明確にmavenでなければならない理由も存在しないためsbtを採用。  
バージョンも特定のものでなければならない理由が見受けられなかったため最新版を利用。

## Scala and Play version
JDK11におけるScalaの推奨バージョンが「2.13.4, 2.12.12, 2.11.12」  
Playのstable版におけるscalaのサポートされたバージョンは以下の通り(2021/01/08)

- Play 2.8.x

| 2.13.x | 2.12.x | 2.11.x |
|---|---|---|
| 2.13.4 | 2.12.11 | 2.11.12 |

- Play 2.7.x

| 2.13.x | 2.12.x | 2.11.x |
|---|---|---|
| 2.13.0 | 2.12.9 | 2.11.12 |

後述するテストフレームワークもPlayに付属していることから上記条件にて全て推奨、サポートがされている最新バージョンを利用
- Scala: 2.13.4
- Play: 2.8.7

## Test
コミュニティの活発さ、Playとの相性からScalaTestとspecks2を絞る。  
どちらでも良かったが、giter8でテンプレート作成を行ったらScalaTestをPlay用に統合したscalatestplus-playが付属していたため、ScalaTestを利用。 
ScalaTestはmockitを利用しており、個人的に馴染みやすいと感じた点も１つの選定委理由。

## Http client
現状では並列化する必要はないため「The Play WS API」を利用  
仮に並列化するのであれば、githubのスターの数からAkkaを利用する

## Dstabase
作成時間からH2を採用する。  
もしMySQLなどのオンメモリではないデータベースを利用するようになった場合も移行しやすく、テスト用と本番用で切り替えも行いやすい。
