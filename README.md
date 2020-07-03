# Pokepay Android SDK

## Document

* https://docs.pokepay.jp/sdk/java.html

## Installation　（jcenter上のライブラリを使用）

ライブラリを使用するModule(appなど）の build.gradle に追記
```
dependencies{
  .....
  implementation 'jp.pocket-change.pokepay.android-sdk:pokepaylib:{Version}' // (※)
}
```

(※) `{Version}` はその時点での最新バージョンを指定する

## Installation　（コンパイルして使用）

1. プロジェクトを作成
2. File > New > ImportModuleからpokepaylibをインポート
3. setting.gradle内に追記 
```
include ':pokepaylib'
```
4. ライブラリを使用するModuleの build.gradle の dependencies内に追記
```
implementation project(':pokepaylib')
```

## Usage

```java
// SetEnv //
Pokepay.setEnv(Env.DEVELOPMENT); // or Env.SANDBOX, Env.PRODUCTION

// Input AccessToken //
Pokepay.Client client = new Pokepay.Client("ZhwMsfoAyWZMGrCAKrrofmwYHV82GkUcf3kYSZYYf1oDKVvFAPIKuefyQoc1KDVr");

// getTerminalInfo //
new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            Terminal terminal = client.getTerminalInfo();
            // => Terminal(id: "45046d7f-aa33-4d26-8cb0-8971aae5a487", name: "", hardwareId: "4e5c5d18-b27f-4b32-a0e0-e8900686fe23", pushToken: nil, user: Pokepay.User(id: "4abed0cc-6431-446f-aaf5-bebc208d84c1", name: "", isMerchant: true), account: Pokepay.Account(id: "1b4533c0-651c-4e79-8444-346419b18c77", name: "", balance: -15357.0, isSuspended: false, privateMoney: Pokepay.PrivateMoney(id: "090bf006-7450-4ed9-8da1-977ea3ff332c", name: "PocketBank", organization: Pokepay.Organization(code: "pocketchange", name: "ポケットチェンジ"), maxBalance: 30000.0, expirationType: "static")))
        } catch (ProcessingError err) {
            // err is Runtime error.
            // The caller may be wrong.
            // Please check your code.
        } catch (BankRequestError err) {
            // err is mainly API Errors
            // It occurres when the status code is other than 2xx (in BankAPI, MessagingAPI)
            // err.error => pokepaylib.Responses.BankError{}
            // err.statusCode => int
        } catch (OAuthRequestError err) {
            // err is mainly API Errors
            // It occurres when the status code is other than 2xx (in OAuthAPI)
            // err.error => pokepaylib.Responses.OAuthError{}
            // err.statusCode => int
        }
    }
}).start();

// createToken //
new Thread(new Runnable() {
    @Override
    public void run() {
        Cashtray cashtray = (Cashtray)client.createToken(1, "AndroidTest cashtray");
        //like 'https://www.pokepay.jp/cashtrays/dc204118-9e3b-493c-b396-b9259ce28663'
    }
}).start();

// scanToken //
new Thread(new Runnable() {
    @Override
    public void run() {
        UserTransaction userTransaction = client.scanToken("https://www.pokepay.jp/cashtrays/dc204118-9e3b-493c-b396-b9259ce28663");
    }
}).start();
```

2. If you use Payregi please add these lines main/AndroidManifest.xml
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android" ...

    <uses-permission android:name="android.permission.BLUETOOTH"/>
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-feature android:name="android.hardware.bluetooth" />
    <uses-feature android:name="android.hardware.bluetooth_le" android:required="true"/>

    <application ...
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
Pokepay.OAuthClient oclient = new Pokepay.OAuthClient(clientId, clientSecret);
new Thread(new Runnable() {
    @Override
    public void run() {
        AccessToken accessToken = oclient.getAccessToken(code);
    }
    // => AccessToken(accessToken: "dXX1Guh7Ze0F_s6L8mAk-t4DXxvO2wd_IwWXbQBGdNo0nkj01tYA9EKY992H_mMP", refreshToken: "XKOfCZmLuRjLggDZzDfz", tokenType: "Bearer", expiresIn: 2591999)
}).start();
```

