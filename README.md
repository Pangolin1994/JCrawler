#### Description
Simple crawler to extract web-page "Title" tag content
#### Libraries
- Http4s - API requesting
- Jsoup - parsing HTML documents
- Circe - json encoding-decoding
#### Usage
Query
curl -X POST -H "Content-Type: application/json" -d '{"Urls": [{"Uri": "https://translate.google.ru"}, {"Uri": "https://http4s.org/v0.23/service"}, {"Uri": "https://habr.com/ru/top/daily"}, {"Uri": "https://blog.rockthejvm.com/tagless-final"}]}' http://localhost:8080/titles <br />
should return <br />
       {"https://translate.google.ru":"Google Переводчик","https://http4s.org/v0.23/service":"http4s | Service", "https://habr.com/ru/top/daily/":"Лучшие публикации за сутки / Хабр", "https://blog.rockthejvm.com/tagless-final":"Tagless Final in Scala - Rock the JVM Blog"}
