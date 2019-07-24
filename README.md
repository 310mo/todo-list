# todo-list
## 使用した技術要素
Spring Initializrで雛形を作成した。
データベースはMySQLを使用し、テンプレートエンジンはthymeleafを使用した。
また、データベースとの連携にはJPAを用いている。

## 全体の設計、構成
トップページではtodoリストの確認及びtodoの追加ができる。editボタンを押すと各todoの編集を行うことができる。また、findボタンから検索画面に遷移する。検索画面ではtodoリストに登録した内容（もしくは内容の一部分）またはカテゴリと合致するデータの検索が行える。

## セットアップ手順
```
./gradlew
```
でビルドを行う。
```
./gradlew bootrun
```
で実行する。また、src/main/resorces/application.propertiesのパスワードを変更する。