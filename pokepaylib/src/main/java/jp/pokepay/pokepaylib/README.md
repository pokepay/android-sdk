# PokePay Androidライブラリ

## 使用方法
```
Pokepay pokepay = new Pokepay();
pokepay.NewClient(accessToken, isMerchant);
pokepay.NewOAuthClient(clientId, clientSecret);

```


## 実装メソッド
### PokePay.client.getTerminalInfo()
- 端末情報を取得するメソッド
- レスポンスとしてTerminalを返す

### PokePay.client.scanToken(String token, double amount)
- 取引トークンを使って取引を行うメソッド
- amountを入力とすると任意の金額を受け取れる
- レスポンスとしてUserTransactionを返す

### PokePay.client.createToken(double amount, String description, int expiresIn)
- 新しい取引トークンの作成メソッド
- ここで作成したトークンをQRコードなどで表示すると取引可能

### PokePay.oAuthClient.getAuthorizationUrl()
- PokePayの認証URLを取得するメソッド
- このURLをブラウザで開くことで認証ページに飛べる

### PokePay.oAuthClient.getAccessToken(String code)
- 認証コードを使ってアクセストークンを取得するメソッド
- レスポンスとしてStringでトークンを返す