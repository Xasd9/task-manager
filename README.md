# Task Manager API

REST API для управления задачами с JWT-авторизацией. Каждый пользователь имеет доступ только к своим задачам.

## 🚀 Технологии

- **Java 23**
- **Spring Boot 3.3.5**
- **Spring Security + JWT** — безопасность и авторизация
- **Spring Data JPA + Hibernate** — работа с базой данных
- **PostgreSQL 15** — основная база данных
- **H2** — база данных для тестов и локальной разработки
- **Liquibase** — миграции базы данных (управление схемой)
- **Maven профили (dev / prod)** — разные конфигурации для разработки и продакшена
- **Docker + docker-compose** — контейнеризация
- **Swagger / OpenAPI** — документация API
- **Bean Validation** — валидация входных данных
- **Maven** — сборка проекта
- **JUnit 5** — тестирование

## 🏃 Как запустить

### Вариант 1: Быстрый запуск (Профиль `dev` — H2 в памяти)
По умолчанию приложение запускается с профилем `dev`, используя базу данных H2 в памяти. Это не требует Docker.
1. Открой проект в IntelliJ IDEA.
2. Запусти `TaskManagerApplication`.
3. Открой Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### Вариант 2: Полный запуск (Профиль `prod` — PostgreSQL в Docker)
1. Убедись, что Docker Desktop запущен.
2. Запусти базу данных в терминале:
   ```bash
   docker compose up -d
В IntelliJ IDEA в настройках запуска (Edit Configurations) укажи Active profiles: prod (или добавь аргумент --spring.profiles.active=prod).

Запусти TaskManagerApplication.

Открой Swagger UI: http://localhost:8080/swagger-ui/index.html

📚 API Эндпоинты
Аутентификация (открыто для всех)
Метод	URL	Описание	Тело запроса	Ответ
POST	/api/auth/register	Регистрация	{"username": "...", "password": "..."}	{"token": "..."}
POST	/api/auth/login	Логин	{"username": "...", "password": "..."}	{"token": "..."}
Задачи (требуют токен в заголовке Authorization: Bearer <token>)
Метод	URL	Описание
POST	/api/tasks	Создать новую задачу
GET	/api/tasks	Получить только свои задачи
GET	/api/tasks/{id}	Получить свою задачу по id
PUT	/api/tasks/{id}	Обновить свою задачу
DELETE	/api/tasks/{id}	Удалить свою задачу
🧪 Тесты
Запуск всех тестов через IntelliJ:

▶️ рядом с тестовым классом (например, TaskRepositoryTest).

Или через Maven (если установлен):

bash
mvn test
🏗️ Архитектура проекта
Controller — принимает HTTP-запросы, отдаёт JSON.

Service — бизнес-логика, связь контроллера и репозитория.

Repository — работа с базой данных через Spring Data JPA.

Entity — модели таблиц БД (Task, User).

DTO — объекты для передачи данных (запросы/ответы).

Security — JWT-фильтр, настройки безопасности, обработка токенов.

Exception Handler — глобальная обработка ошибок.

Liquibase — управление схемой БД через db.changelog-master.xml.

👤 Автор
Xasd9 — GitHub