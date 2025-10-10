# 📱 Inmobiliaria Android App

Aplicación móvil Android para **propietarios de inmuebles** que desean gestionar sus propiedades, contratos e inquilinos de forma fácil y segura.
Desarrollada en **Android (Java)** y conectada con [Laboratorio_3_API](https://github.com/FedericoDG/Laboratorio_3_API)

---

## Requisitos previos

Antes de comenzar, asegurate de tener instalado:

- Android Studio Arctic Fox o superior
- Android SDK 21+ (Android 5.0+)
- Dispositivo Android o Emulador

## Instalación y ejecución del proyecto

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/FedericoDG/Laboratorio_3_Android.git
cd Laboratorio_3_Android
```

### 2️⃣ Configurar el archivo `.env`

**PASO OBLIGATORIO:** Localizá el archivo `.env` en la raíz del proyecto y editá la variable:

```env
API_BASE_URL=https://mi-servidor.com/api/
```

⚠️ **Importante:**

- La URL **DEBE terminar con `/`** (barra final)
- Reemplazá la URL con la de tu servidor donde corre la API

### 3️⃣ Abrir en Android Studio

### 4️⃣ Sincronizar dependencias (Gradle sync)

### 5️⃣ Ejecutar la aplicación (Run 'app')

---

### Datos de Login para Pruebas

Una vez configurada la URL, podés usar estos usuarios de prueba para hacer login:

| Email                      | Password | Propietario          |
| -------------------------- | -------- | -------------------- |
| `juan.gonzalez@email.com`  | `1234`   | Juan Carlos González |
| `maria.martinez@email.com` | `1234`   | María Elena Martínez |
| `carlos.lopez@email.com`   | `1234`   | Carlos Alberto López |

---

## 🏠 Funcionalidades de la App

### Autenticación

- Login seguro con email y contraseña
- Tokens JWT para sesiones seguras
- Logout automático al expirar sesión

### Perfil del Propietario

- Ver datos personales
- Editar información personal

### Mis Inmuebles

- Listar todas mis propiedades
- Ver detalles de cada inmueble
- Agregar nuevas propiedades
- Actualizar disponibilidad

### Contratos Activos

- Ver inmuebles con contratos vigentes
- Detalles de inquilinos
- Historial de pagos recibidos
- Estado de cada contrato

---
