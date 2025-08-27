# Proyecto Microservicios Proceso de Compras

Este proyecto implementa un ecosistema de microservicios con enfoque **hexagonal** y 
principios de aislamiento total entre componentes. Cada API es independiente y no comparte lógica ni infraestructura con las demás, 
garantizando **desacoplamiento** y **facilidad de mantenimiento**.

---

## 1. Instrucciones de instalación y ejecución

### Requisitos previos
- [Docker](https://www.docker.com/get-started) instalado y configurado.
- [Docker Compose](https://docs.docker.com/compose/) instalado.
- **SQLite** instalado en el sistema.
- Directorio `C:\sqlite` creado en la máquina host (Windows).
- Archivos de la base de datos disponibles en el repositorio dentro del directorio `/database`.

### Instalación
1. Clonar este repositorio:
   git clone https://github.com/ernestopos/linktic.git
   cd linktic
   
2. Copiar los archivos de base de datos:
Desde /database del repo hacia C:\sqlite en tu equipo.

3. Si desea contenirizar las API
Construir las imágenes de Docker: 
docker-compose build

4. Compilar las APIS
Ejecutar las tareas maven: CommonsBuild, ProductBuild, InventoryBuild, FacturacionBuild

5. Ejecutar las API:
Ejecutar las tareas maven: RunAPI-Producto, RunAPI-Iventario, RunAPI-Facturacion

### Descripción de la arquitectura
Estilo arquitectónico: Hexagonal (Ports & Adapters).
Base de datos: SQLite, con archivos persistidos en C:\sqlite.
Microservicios principales:
Producto API: Administración de productos.
Inventario API: Gestión de inventarios.
Facturación API: Orquestador del flujo de compra.
Cada microservicio es totalmente independiente (enfoque nada compartido) → no comparten base de datos, 
repositorios ni lógica interna. Esto permite escalar y mantener cada uno de manera aislada.

### Decisiones técnicas y justificaciones
* Arquitectura hexagonal:
  Garantiza separación entre la lógica de negocio y los detalles de infraestructura, facilitando pruebas unitarias y cambios tecnológicos futuros.
* SQLite como base de datos:
  Seleccionada por su ligereza y facilidad de instalación. Al ubicar los archivos en C:\sqlite se simplifica la configuración y persistencia local.
* Enfoque nada compartido:
  Cada API gestiona sus propios datos y recursos, reduciendo riesgos de dependencias cruzadas y mejorando la autonomía.
* Endpoint de compra implementado en Facturación API:
  La decisión se tomó porque la compra es un proceso orquestador que involucra validación de productos, creación de pedidos y emisión de factura. Centralizarlo en Facturación asegura consistencia y control del flujo completo