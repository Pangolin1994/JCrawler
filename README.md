#### Usage
Query: curl -X POST -H "Content-Type: application/json" \
       -d '{"Urls": [{"Uri": "https://translate.google.ru"}, {"Uri": "https://http4s.org/v0.23/service"}]}' \
       http://localhost:8080/titles
should return
       {"https://translate.google.ru":"Google Переводчик","https://http4s.org/v0.23/service":"http4s | Service"}
