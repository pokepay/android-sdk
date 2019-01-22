# Pokepay Android SDK

## Installation

1. プロジェクトを作成
2. File > New > ImportModuleからpokepaylibをインポート
3. setting.gradle内に追記 
```
include ':pokepaylib'
```
4. 作成したプロジェクトのbuild.gradleのdependencies内に追記
```
implementation project(':pokepaylib')
```

## Usage

```java
Pokepay pokepay = new Pokepay();
// Input AccessToken //
pokepay.NewClient("ZhwMsfoAyWZMGrCAKrrofmwYHV82GkUcf3kYSZYYf1oDKVvFAPIKuefyQoc1KDVr");

// getTerminalInfo //
new Thread(new Runnable() {
    @Override
    public void run() {
        pokepay.NewClient(accessToken1);
        Terminal terminal = pokepay.client.getTerminalInfo();
        // => Terminal(id: "45046d7f-aa33-4d26-8cb0-8971aae5a487", name: "", hardwareId: "4e5c5d18-b27f-4b32-a0e0-e8900686fe23", pushToken: nil, user: Pokepay.User(id: "4abed0cc-6431-446f-aaf5-bebc208d84c1", name: "", isMerchant: true), account: Pokepay.Account(id: "1b4533c0-651c-4e79-8444-346419b18c77", name: "", balance: -15357.0, isSuspended: false, privateMoney: Pokepay.PrivateMoney(id: "090bf006-7450-4ed9-8da1-977ea3ff332c", name: "PocketBank", organization: Pokepay.Organization(code: "pocketchange", name: "ポケットチェンジ"), maxBalance: 30000.0, expirationType: "static")))
    }
}).start();

// createToken //
new Thread(new Runnable() {
    @Override
    public void run() {
        Cashtray cashtray = (Cashtray)pokepay.client.createToken(1, "AndroidTest cashtray");
        //like 'https://www.pokepay.jp/cashtrays/dc204118-9e3b-493c-b396-b9259ce28663'
    }
}).start();

// scanToken //
new Thread(new Runnable() {
    @Override
    public void run() {
        UserTransaction userTransaction = pokepay.client.scanToken("https://www.pokepay.jp/cashtrays/dc204118-9e3b-493c-b396-b9259ce28663");
    }
}).start();
```

## Authorization
1. Open Authorization URL in Web browser (like webview)

```java
Pokepay pokepay = new Pokepay();
pokepay.NewOAuthClient(clientId, clientSecret);
pokepay.oAuthClient.getAuthorizationUrl();
// => https://www.pokepay.jp/oauth/authorize?client_id=xxxxxxxxxxx&response_type=code
```

2. Wait for the user to authorize your app on Pokepay's Web page.
3. Browser redirects to the app with authorization code
4. Exchange the authorization code for an access token

```java
Pokepay pokepay = new Pokepay();
pokepay.NewOAuthClient(clientId, clientSecret);
new Thread(new Runnable() {
    @Override
    public void run() {
        AccessToken accessToken = pokepay.oAuthClient.getAccessToken(code);
    }
    // => AccessToken(accessToken: "dXX1Guh7Ze0F_s6L8mAk-t4DXxvO2wd_IwWXbQBGdNo0nkj01tYA9EKY992H_mMP", refreshToken: "XKOfCZmLuRjLggDZzDfz", tokenType: "Bearer", expiresIn: 2591999)
}).start();
```

## APIs
### [Class] Pokepay.Client
- ```accessToken``` (String): An access token to request Pokepay APIs.
- ```isMerchant``` (Bool): A flag whether access as a merchant. (It should be an error if the access token isn't for merchant one.)

### [Method] Client.getTerminalInfo
Get the accessing ```Terminal``` informations.

### [Method] Client.scanToken(String token, double amount)
Scan a token and make a new transaction via Bank APIs.

### [Method] Client.createToken(double amount, String description, int expiresIn)
Make a new token for sending/receiving money, which can be done with ```scanToken```.

### [Class] Pokepay.OAuthClient
- ```clientId``` (String): OAuth client ID
- ```clientSecret``` (String): OAuth client secret

### [Method] OAuthClient.getAuthorizationUrl()
Get the OAuth authorization URL to open with an external browser. (ex. https://www.pokepay.jp/oauth/authorize?client_id=xxxxxxxx&response_type=code)

### [Method] OAuthClient.getAccessToken(String code)
Exchange authorization code, a string in redirection URL, with an access token.
