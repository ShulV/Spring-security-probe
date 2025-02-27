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
Пароль выводится в консоль при запуске приложения:
```bash
Using generated security password: fc780762-8d7d-48ac-954b-5a05d50890d0
```

---

