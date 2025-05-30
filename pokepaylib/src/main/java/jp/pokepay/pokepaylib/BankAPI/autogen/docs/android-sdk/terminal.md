# Terminal
端末を表すデータです。
端末の単位はクライアントアプリの実装によって決定されます。
認証時のHardwareIDの単位で端末識別をし、サーバサイドに存在しないHardwareIDが送られた際には新規作成されます。
HardwareIDはクライアントアプリ側で採番されることを想定しています。


<a name="get-terminal"></a>
## GetTerminal: 端末の情報を取得
アクセスしている端末の情報を返します。

```java
Request request = new GetTerminal();

```






成功したときは
[Terminal](./responses.md#terminal)
を返します



---


<a name="update-terminal"></a>
## UpdateTerminal: 端末の情報を変更
アクセスしている端末の情報を変更します。

```java
Request request = new UpdateTerminal()
        .name("acDgdY")                           // 端末の名前
        .accountId("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx") // デフォルト口座ID
        .pushService("apns")                      // プッシュ通知サービス
        .pushToken("37RY1ziLELmFBMnn24Y00BddIYIaGsnHTfyj3vGhpYs6lE3PVxThCRcEAVa4JmfjoJZ9ajsO39BqxPDSP5BpfA0dYcuMmHpa4aDHWm32hBFhI0DxRhz83lKq4Wp1hKlNvpHM0s7Dd9Uu6qWqC0qUtLag9adxARTcCt"); // プッシュ通知用トークン

```



### Parameters
**`name`** 
  

識別のために端末に名前をつけます。

```json
{
  "type": "string",
  "maxLength": 256
}
```

**`account_id`** 
  

取引のときにデフォルトで利用する口座を設定します。

```json
{
  "type": "string",
  "format": "uuid"
}
```

**`push_service`** 
  

プッシュ通知に使うサービスを選択します。
- apns: APNsはAppleが提供しているPush通知基盤です。iOS端末のときに選択します。
- fcm: FCM(Firebase Cloud Messaging)はGoogleが提供するメッセージングプラットフォームです。Android端末のときに選択します。

```json
{
  "type": "string",
  "enum": [
    "apns",
    "fcm"
  ]
}
```

**`push_token`** 
  

プッシュ通知に使うデバイストークンを設定します。

```json
{
  "type": "string",
  "maxLength": 256
}
```



成功したときは
[Terminal](./responses.md#terminal)
を返します



---


<a name="add-terminal-public-key"></a>
## AddTerminalPublicKey: 端末に新たな公開鍵を追加
アクセスしている端末に新たに公開鍵を追加します。この鍵はネットワークを介さない端末間の直接取引に利用します。

```java
Request request = new AddTerminalPublicKey(
    "Kjz1M2ku"                                    // key: 公開鍵
);

```



### Parameters
**`key`** 
  

新たな公開鍵の文字列表現です。

```json
{
  "type": "string"
}
```



成功したときは
[TerminalServerKey](./responses.md#terminal-server-key)
を返します



---



