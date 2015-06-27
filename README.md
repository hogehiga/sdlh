# sdlh

[![Build Status](https://travis-ci.org/hogehiga/sdlh.svg?branch=master)](https://travis-ci.org/hogehiga/sdlh)

## これは何？
あそこに行ったのいつだっけ？を解決するツールです。

## 利用例
```
$ java -jar sdlh-*-jar-with-dependencies.jar 40.0000000 126.0000000 30.0000000 130.0000000 location-history.json
2014/01/01
2014/01/03
```

## 入出力詳細
### 入力
左上緯度、左上経度、右下緯度、右下経度、ロケーション履歴のデータ。

- ロケーション履歴のデータはGoogle Takeoutで取得したJSONファイルを使います。

### 出力
Googleロケーション履歴の座標が指定した長方形の範囲内にある時、その座標が記録された日付。
