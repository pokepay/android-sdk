# Internal
ポケットチェンジ内部用


<a name="get-user-attribute-schema"></a>
## GetUserAttributeSchema: ユーザアンケート項目
マネーに設定されたユーザアンケートの聞き取り項目をJSON形式で取得します。


```java
Request request = new GetUserAttributeSchema(
    "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
);

```



### Parameters
**`private_money_id`** 
  


```json
{
  "type": "string",
  "format": "uuid"
}
```






---



