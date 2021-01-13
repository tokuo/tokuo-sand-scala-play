## 注意書き
- Backlog APIを活用
- application.conf要参照

## 起動方法
```
$ sbt run
```
その後「localhost:9000」にアクセスし、データベースが存在しない場合はDBを作成するため、画面に表示されたボタンをクリックする  

Docker利用時のアプリ起動方法は以下コマンド
```
$ sudo docker build -t shgtkmt-app:0.1 ./  
$ sudo docker run --name shgtkmt-app --rm -it -p 9000:9000 shgtkmt-app:0.1
```

## TODO
- テスト実装
- デザイン
- 認証認可機能（OAuth）
- 機能追加
    - BacklogとDB側の差分があるときの処理
