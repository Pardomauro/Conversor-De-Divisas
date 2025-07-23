# 💱 Conversor de Divisas

Un conversor de divisas en tiempo real desarrollado en Java que utiliza una API externa para obtener tipos de cambio actualizados automáticamente.

## 🌟 Características

- ✅ **Tipos de cambio en tiempo real** - Conecta con Exchange Rate API para obtener valores actualizados
- 💰 **Múltiples monedas soportadas** - USD, EUR, GBP, CLP, BRL
- 🔄 **Sistema de respaldo** - Valores predeterminados si la API no está disponible
- 🛡️ **Manejo robusto de errores** - Validación de entradas y manejo de excepciones
- 📊 **Interfaz clara** - Menú intuitivo con formato profesional
- 🔌 **Sin dependencias externas** - Utiliza solo librerías estándar de Java

## 🚀 Funcionalidades

### Conversiones Disponibles
Desde **Pesos Argentinos (ARS)** hacia:
- 🇺🇸 Dólares Estadounidenses (USD)
- 🇪🇺 Euros (EUR) 
- 🇬🇧 Libras Esterlinas (GBP)
- 🇨🇱 Pesos Chilenos (CLP)
- 🇧🇷 Reales Brasileños (BRL)

### Características Técnicas
- **API en tiempo real**: Exchange Rate API
- **Parser JSON nativo**: Sin dependencias externas
- **Timeout configurado**: 5 segundos para conexiones
- **Fallback automático**: Valores predeterminados si falla la API
- **Validación de entrada**: Previene errores de usuario

## 📋 Requisitos del Sistema

- **Java**: JDK 8 o superior
- **Conexión a Internet**: Para obtener tipos de cambio actualizados
- **Sistema Operativo**: Windows, macOS, Linux

## 🛠️ Instalación y Uso

### 1. Clonar el Repositorio
```bash
git clone https://github.com/Pardomauro/Conversor-De-Divisas.git
cd Conversor-De-Divisas
```

### 2. Compilar el Proyecto
```bash
cd src
javac *.java logica/*.java
```

### 3. Ejecutar la Aplicación
```bash
java main
```

## 📖 Guía de Uso

1. **Inicio**: La aplicación se conecta automáticamente a la API y muestra los tipos de cambio actuales
2. **Selección**: Elige la moneda de destino (1-5) o 0 para salir
3. **Cantidad**: Ingresa la cantidad en pesos argentinos a convertir
4. **Resultado**: Visualiza el resultado con el tipo de cambio utilizado

### Ejemplo de Uso
```
=== CONVERSOR DE DIVISAS ===
Conectando con la API para obtener tipos de cambio actualizados...

=== TIPOS DE CAMBIO ACTUALES ===
1 ARS = 0.0010 USD
1 ARS = 0.0009 EUR
1 ARS = 0.0008 GBP
1 ARS = 0.9700 CLP
1 ARS = 0.0058 BRL
================================

Seleccione la moneda a la que desea convertir desde pesos argentinos:
1 - Dólares (USD)
2 - Euros (EUR)
3 - Libra Esterlina (GBP)
4 - Pesos Chilenos (CLP)
5 - Reales (BRL)
0 - Salir
Opción: 1

Ingrese la cantidad en pesos argentinos (ARS): $1000

✅ RESULTADO DE LA CONVERSIÓN:
$1000.00 ARS = 1.0000 USD (Dólares Estadounidenses)
Tipo de cambio utilizado: 1 ARS = 0.001000 USD
```

## 🏗️ Estructura del Proyecto

```
App_conversión_divisas/
├── src/
│   ├── main.java                    # Clase principal con interfaz de usuario
│   └── logica/
│       └── conversorDivisas.java    # Lógica de conversión y API
└── README.md                        # Este archivo
```

## 🔧 Detalles Técnicos

### API Utilizada
- **Exchange Rate API**: `https://api.exchangerate-api.com/v4/latest/ARS`
- **Método**: GET request
- **Formato**: JSON response
- **Rate limit**: Generalmente gratuito para uso personal

### Implementación Destacada

#### Parser JSON Nativo
```java
private double extraerValor(String json, String moneda) {
    Pattern pattern = Pattern.compile("\"" + moneda + "\"\\s*:\\s*([0-9]*\\.?[0-9]+)");
    Matcher matcher = pattern.matcher(json);
    return matcher.find() ? Double.parseDouble(matcher.group(1)) : 0.0;
}
```

#### Manejo de Conexiones
```java
private String obtenerDatosAPI() throws Exception {
    URI uri = new URI(API_URL);
    HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
    connection.setRequestMethod("GET");
    connection.setConnectTimeout(5000);
    connection.setReadTimeout(5000);
    // ... resto de la implementación
}
```

## 🚨 Manejo de Errores

La aplicación incluye varios niveles de manejo de errores:

- **Conexión a Internet**: Fallback a valores predeterminados
- **Entrada de Usuario**: Validación de tipos y rangos
- **API Responses**: Verificación de códigos de respuesta HTTP
- **Parsing JSON**: Manejo de formatos inesperados

## 🔄 Posibles Mejoras Futuras

- [ ] Más monedas soportadas
- [ ] Historial de conversiones
- [ ] Interfaz gráfica (GUI)
- [ ] Configuración de API key personalizada
- [ ] Conversión bidireccional
- [ ] Exportar resultados a archivo
- [ ] Gráficos de tendencias

## 🐛 Resolución de Problemas

### La aplicación no se conecta a la API
- Verifica tu conexión a Internet
- La aplicación usará valores predeterminados automáticamente

### Error de compilación
- Asegúrate de tener Java JDK 8 o superior
- Verifica que estés en el directorio correcto (`src/`)

### Caracteres especiales no se muestran
- Usa un terminal que soporte UTF-8
- En Windows, prueba con Windows Terminal o Git Bash

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👨‍💻 Autor

**Mauro Pardo**
- GitHub: [@Pardomauro](https://github.com/Pardomauro)
- Proyecto: [Conversor-De-Divisas](https://github.com/Pardomauro/Conversor-De-Divisas)

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para cambios importantes:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

⭐ **¡Si te gustó este proyecto, dale una estrella!** ⭐
