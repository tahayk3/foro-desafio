# 📕 API DE FORO 📖

Este proyecto con java y POO es una api que guarda datos de un foro y cuenta con autenticacion en JWT.


---

## 📁 Estructura de Archivos

- `Paquete DOMAIN`: manejo de errores y poo de cada clase
- `Paquete SECURITY`: para la generacion de token, validacion del mismo y proteccion de rutas
- `Paquete de CONTROLLER`: Controles para operar operaciones crud en base a un endpoint
- `Paquete migration`: control de migraciones 

---

## 🧰 Tecnologías utilizadas

- Java 17+
- `java.net.http.HttpClient` para peticiones HTTP.
- `jackson` para parsear JSON.
- `mysql` para el uso de una base de datos para la persistencia
- `java-jwt` para generar y validar token
- `lombok` para reducir codigo
- `spring-security` para proteger rutas

---

## 📄 Licencia

Este proyecto se desarrolló con fines educativos y es de uso libre.
