# Internetipanga süsteem

## Projekti teema

Internetipanga süsteemi arendamine, kasutades backend'i jaoks Java Spring Boot'i ning frontend'i jaoks HTML-i, CSS-i ja JavaScripti.

Süsteem võimaldab kasutajal sisse logida ja hallata ning vaadata oma pangakontodega seotud andmeid.

## Projekti eesmärk

Projekti eesmärk on arendada internetipanga süsteem, kus kasutaja saab:

- süsteemi sisse logida;
- läbida autentimise;
- vaadata oma pangakontosid;
- vaadata konto IBAN-i ja saldot;
- vaadata reserveeritud summat;
- vaadata saadaolevat saldot;
- teha pangatoiminguid;
- hallata oma kontosid ja teisi pangateenuseid.

Projekt on arendamisel ning seda täiendatakse järk-järgult uute funktsioonidega.

Saadaolev saldo arvutatakse valemiga:

`available = balance - reserved`

## Projekti struktuur

Projekt koosneb backend'ist, frontend'ist ja andmebaasist.

### Backend

Backend on loodud **Java Spring Boot** abil.


Разработка системы интернет-банкинга с использованием Java Spring Boot для backend и HTML, CSS и JavaScript для frontend.

Система позволяет пользователю авторизоваться и работать со своими банковскими счетами.

## Цель проекта

Цель проекта — разработать интернет-банк, в котором пользователь сможет:

- войти в систему;
- пройти аутентификацию;
- просматривать свои банковские счета;
- видеть IBAN и баланс счёта;
- видеть зарезервированную сумму;
- видеть доступный баланс;
- выполнять банковские операции;
- управлять своими счетами и другими банковскими услугами.

Проект находится в процессе разработки и будет постепенно расширяться новыми функциями.

Доступный баланс рассчитывается по формуле:

`available = balance - reserved`
