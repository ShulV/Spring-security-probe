# Basic аутентификация

## Как делать запрос в самой базовой реализации из коробки (без конфигурации)?
```bash
curl -u user:fc780762-8d7d-48ac-954b-5a05d50890d0 http://localhost:8080/test
```

P.S. -u флаг создает заголовок Authorization со значением base64(login:password)
```bash
echo -n user:fc780762-8d7d-48ac-954b-5a05d50890d0 | base64
```
аналогично:
```bash
curl -H "Authorization: Basic dXNlcjpmYzc4MDc2Mi04ZDdkLTQ4YWMtOTU0Yi01YTA1ZDUwODkwZDA=" http://localhost:8080/test
```
-H, --header - заголовок запроса

Пароль выводится в консоль при запуске приложения:
```bash
Using generated security password: fc780762-8d7d-48ac-954b-5a05d50890d0
```

---

## Как добавить https (самоподписанный сертификат)
сгенерируем самоподписанный сертификат:
```bash
openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365
```
Далее в терминале нужно будет ввести данные CA и пароль (у меня 1234), данные для теста - любые
Создадутся 2 файла:
- key.pem - закрытый ключ
- cert.pem - открытый сертификат

```bash
openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "test-certificate"
```
нужно будет ввести сертификат от key.pem (1234)
ввести пароль для экспорта (тоже 1234)
Создастся 1 файл:
- certificate.p12 - самоподписанный сертификат
нужно скопировать сертификат в ресурсы проекта (как вариант для простого подключения)

```bash
curl -k -u user:86c7f9cb-70c3-429f-99d0-60005c3cbbe5 https://localhost:8080/test
```
-k, --insecure - пропуск проверки подлинности сертификата

P.S. на проде сертификат имеет в цепочке CA доверенный CA, который известен, например, браузеру

---

