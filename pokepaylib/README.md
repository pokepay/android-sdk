# Low Level APIs
## Usage
```java
LowLevelAPIClass api = new LowLevelAPIClass(...);
responce = api.procSend(AccessToken);
```

## BankAPI
### [Class] CreateAccount -> Account
アカウントの作成

- ```name``` (String): アカウント名
- ```privateMoneyId``` (String): プライベートマネーのID

### [Class] GetAccount -> Account
指定したIDのアカウントの取得

- ```id``` (string): 取得したいアカウントのID

### [Class] GetAccountTransactions -> PaginatedTransactions
指定したアカウントIDのTransactionsを取得

- ```id``` (String): アカウントのID
- ```before``` (String): 指定したものよりも前のIDを取得するオプション
- ```after``` (String): 指定したものよりも後のIDを取得するオプション
- ```perPage``` (int): 指定した数のトランザクションを取得するオプション

### [Class] GetBalances -> PaginatedAccountBlances
指定したアカウントIDのBalancesを取得

- ```id``` (String): アカウントのID
- ```before``` (String): 指定したものよりも前のIDを取得するオプション
- ```after``` (String): 指定したものよりも後のIDを取得するオプション
- ```perPage``` (int): 指定した数のバランスを取得するオプション

### [Class] CreateBill -> Bill
Billによる取引の作成

- ```amount``` (double): 作成したい取引の金額
- ```description``` (String): 作成したい取引の詳細
- ```accountId``` (String): 取引を行うアカウントのId

### [Class] DeleteBill -> String
指定したBillの削除

- ```id``` (String): 削除したいBillのID

### [Class] GetBill -> Bill
指定したBillの情報を取得

- ```id``` (String): 詳細を取得したいBillのID

### [Class] UpdateBill -> Bill
指定したBillの更新

- ```id``` (String):更新したいBillのId
- ```amount``` (double): 更新したい取引の金額
- ```description``` (String): 更新したい取引の詳細

### [Class] CreateCashtray -> Cashtray
Cashtrayによる取引の作成

- ```amount``` (double): 作成したい取引の金額
- ```description``` (String): 作成したい取引の詳細
- ```expiresIn``` (int): 

### [Class] DeleteCashtray -> String
指定したCashtrayの削除

- ```id``` (String): 削除したいCashtrayのID

### [Class] GetCashtray -> Cashtray
指定したCashtrayの詳細を取得

- ```id``` (String): 詳細を取得したいCashtrayのID

### [Class] UpdateCashtray -> Cashtray
指定したCashtrayの更新

- ```id``` (String): 更新したいCashtrayのID
- ```amount``` (double): 更新したい取引の金額
- ```description``` (String): 更新したい取引の詳細
- ```expiresIn``` (int): 

### [Class] CreateCheck -> Check
Checkによる取引の作成

- ```amount``` (double): 作成したい取引の金額
- ```description``` (String): 作成したい取引の詳細
- ```accountId``` (String): 取引を行うアカウントのId

### [Class] DeleteCheck -> String
指定したCheckの削除

- ```id``` (String): 削除したいCheckのID

### [Class] GetCheck -> Check
指定したCheckの詳細を取得

- ```id``` (String): 詳細を取得したいBillのID

### [Class] UpdateCheck -> Check
指定したCheckの更新

- ```id``` (String):更新したいCheckのId
- ```amount``` (double): 更新したい取引の金額
- ```description``` (String): 更新したい取引の詳細

### [Class] SearchPrivateMoney -> PaginatedPrivateMoney
自身に登録されているプライベートマネーの一覧を取得

### [Class] AddTerminalPublicKey -> ServerKey
ターミナルにPublicKeyを追加

- ```key``` (String): 追加したいkey

### [Class] GetTerminal -> Terminal
Terminalの情報の取得

### [Class] UpdateTerminal -> Terminal
指定したTerminalの更新

- ```accountId``` (String): 更新したいアカウントのID
- ```name``` (String): 更新したいアカウントの名前
- ```pushToken``` (String): 追加したいトークン

### [Class] CanselTransaction -> String
指定した決済のキャンセル

- ```id``` (String): キャンセルしたいトランザクションのID

### [Class] CreateTransactionWithBill -> UserTransaction
Billによる取引の決済

- ```billId``` (String): 取引をしたいBillのID
- ```accountId``` (String): 取引をするアカウントのID
- ```amount``` (String): 取引の金額

### [Class] CreateTransactionWithCashtray -> UserTransaction
Cashtrayによる取引の決済

- ```cashtrayId``` (String): 取引したいCashtrayのID
- ```accountId``` (String): 取引したいアカウントのID

### [Class] CreateTransactionWithCheck -> UserTransaction
Checkによる取引の決済

- ```checkId``` (String): 取引したいCheckのID
- ```accountId``` (String): 取引したいアカウントのID

### [Class] CreateTransactionWithJwt -> String
Jwtによる取引の決済

- ```data``` (String): Jwtのデータ
- ```accountId``` (String): 取引したいアカウントのID

### [Class] GetTransaction -> UserTransaction
指定したTransactionの取得

- ```id``` (String): 確認したい取引のid

### [Class] SendToAccount -> UserTransaction
指定したアカウントへの送金

- ```accountId``` (String): 送金したいアカウントのID
- ```amount``` (double): 送金する値段
- ```receiverTerminalId``` (String): 受け取る側のTerminalのID
- ```senderAccountId``` (String): 送る側のアカウントのID
- ```description``` (String): 取引の詳細

### [Class] SendToUser -> UserTransaction
指定したユーザへの送金

- ```userId``` (String): 送金したいユーザのID
- ```amount``` (double): 送金する値段
- ```receiverTerminalId``` (String): 受け取る側のTerminalのID
- ```senderAccountId``` (String): 送る側のアカウントのID
- ```description``` (String): 取引の詳細

### [Class] DeleteUserEmail -> String
指定したユーザのメールアドレスを削除

- ```id``` (String): ユーザのID
- ```email``` (String): 削除したいメールアドレス

### [Class] GetUserAccounts -> PaginatedAccounts
指定したユーザのアカウント一覧の取得

- ```id``` (String): 取得したいユーザのID
- ```before``` (String): 指定した文字列の前のもの取得するオプション
- ```after``` (String): 指定した文字列の後のもの取得するオプション
- ```perPage``` (int): 指定した件数を取得するオプション

### [Class] GetUserTransactions -> PaginatedTransactions
指定したユーザのTransaction一覧を取得

- ```id``` (String): 取得したいユーザのID
- ```before``` (String): 指定した文字列の前のもの取得するオプション
- ```after``` (String): 指定した文字列の後のもの取得するオプション
- ```perPage``` (int): 指定した件数を取得するオプション

### [Class] RegisterUserEmail -> String
- ```token``` (String): トークンを指定

### [Class] SendConfimationEmall -> String
指定したメールアドレスにConfimationのメールを送る

- ```id``` (String): 送りたいユーザのID
- ```email``` (String): メールアドレス

### [Class] UpdateUser -> User
指定したユーザの更新

- ```id``` (String): 更新したいユーザのID
- ```name``` (String): 更新したいユーザの名前


## MessagingAPI
### [Class] GetMessage -> Message
指定したIDのメッセージを取得

- ```id``` (String): 取得したいメッセージのID

### [Class] GetUnreadCount -> MessageUnreadCount
未読のメールの数を取得

### [Class] ListMessages -> PaginatedMessages
メッセージの一覧を取得

- ```before``` (String): 指定した文字列よりも前のメッセージを取得
- ```after``` (String): 指定した文字列よりも後のメッセージを取得
- ```perPage``` (int): 取得するメッセージの数を取得

### [Class] ReceiveMessageAttachment -> MessageAttachment
- ```message``` (Message): 

### [Class] SendMessage -> Message
メッセージを指定したユーザに送信

- ```toUserId``` (String): 送り先のユーザのID
- ```amount``` (double): 金額の指定
- ```subject``` (String): 件名
- ```body``` (String): メールの本文
- ```fromAccountId``` (String): 送り元のユーザのID
- ```requestId``` (String):

## OAuthAPI
### [Class] ExchangeAuthCode -> AccessToken
指定したコードとclientIdでAccessTokenを取得

- ```code``` (String): コードの指定
- ```clientId``` (String): ClientId
- ```clientSecret``` (String): ClientSecret

## Pokeregi
### [Class] BLEController
Pokeregi取引の際にPokeregi端末への通信とサーバへの通信を行う

- ```uuid``` (String): BLEのUUIDの指定
- ```accessToken``` (String): 通信を行うアクセストークンの指定
- ```context``` (String): 呼び出し元のActivityアプリのcontextを指定

### [Method] BLEController.getResult
通信結果を返す。通信中の場合は待機