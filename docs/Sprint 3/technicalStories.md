### Technical Stories Sprint 3

## TS-01: Endpoint: Login

# Scenario 1: Autenticación exitosa
Dado que el developer incluye credenciales válidas en el request,
Cuando lo envía al endpoint de autenticación,
Entonces recibe un token JWT y un status 200 como respuesta.

# Scenario 2: Credenciales inválidas
Dado que el developer incluye credenciales incorrectas en el request,
Cuando se procesa la solicitud,
Entonces se retorna status 401 con un mensaje de error.

# Scenario 3: Error interno del servidor
Dado que el developer realiza un request y ocurre un problema en el backend,
Cuando se procesa la autenticación,
Entonces se retorna status 500 con un mensaje genérico de error.

---

## TS-02: Endpoint: Recuperar contraseña

# Scenario 1: Solicitud válida
Dado que el developer envía un request con un correo que existe en la base de datos,
Cuando el request llega al endpoint de recuperación,
Entonces el sistema genera un token y envía el correo de recuperación.

# Scenario 2: Correo inexistente
Dado que el developer envía un request con un correo no registrado,
Cuando se procesa la solicitud,
Entonces se retorna status 404 y no se envía ningún correo.

# Scenario 3: Error en el envío del correo
Dado que el developer ejecuta la acción y ocurre un fallo en el servicio de correo,
Cuando se intenta enviar el mensaje,
Entonces se retorna status 500 y se registra el error en los logs del servidor.

---

## TS-03: Endpoint: Logout

# Scenario 1: Logout exitoso
Dado que el developer envía un token de sesión válido,
Cuando llama al endpoint de logout,
Entonces la sesión se invalida y se retorna status 200.

# Scenario 2: Token inválido o expirado
Dado que el developer incluye un token no válido o expirado,
Cuando se llama al endpoint de logout,
Entonces se retorna status 401 y no se realiza ninguna acción.

# Scenario 3: Falla del servidor
Dado que el developer realiza un request y ocurre un error interno en el servidor,
Cuando se procesa el logout,
Entonces se retorna status 500 con un mensaje genérico.

---

## TS-04: Endpoint: Crear pedido

# Scenario 1: Petición con datos completos
Dado que el developer envía una petición con todos los campos requeridos,
Cuando se procesa el POST,
Entonces se retorna status 201 con el ID del nuevo pedido.

# Scenario 2: Petición incompleta
Dado que el developer envía una petición con campos obligatorios faltantes,
Cuando se procesa la solicitud,
Entonces se retorna status 400 con un mensaje de validación.

---

## TS-05: Endpoint: Consultar pedidos por usuario

# Scenario 1: Usuario con pedidos registrados
Dado que el usuario tiene pedidos en el sistema,
Cuando se llama al endpoint,
Entonces retorna un array con sus pedidos y status 200.

# Scenario 2: Usuario sin pedidos
Dado que el usuario no ha realizado pedidos,
Cuando se ejecuta la solicitud,
Entonces retorna un array vacío con status 200.

---

## TS-06: Endpoint: Registro de usuario

# Scenario 1: Registro exitoso
Dado que el developer envía un request con todos los datos requeridos (nombre, correo, contraseña, rol),
Cuando se procesa el POST al endpoint de registro,
Entonces se crea el usuario en la base de datos y se retorna status 201 con la información del usuario creado.

# Scenario 2: Correo ya registrado
Dado que el developer envía un request con un correo que ya existe en el sistema,
Cuando se procesa la solicitud,
Entonces se retorna status 409 indicando que el correo ya está en uso.

# Scenario 3: Datos inválidos o incompletos
Dado que el developer envía un request con campos obligatorios vacíos o con formato inválido,
Cuando se valida la solicitud,
Entonces se retorna status 400 con el detalle de los campos no válidos.

---

## TS-07: Endpoint: Consultar usuarios

# Scenario 1: Listado de todos los usuarios
Dado que el developer realiza un GET al endpoint de usuarios,
Cuando existen usuarios registrados en el sistema,
Entonces se retorna un array con la información de los usuarios y status 200.

# Scenario 2: Consulta de usuario por ID existente
Dado que el developer realiza un GET con un ID de usuario válido,
Cuando el usuario existe en la base de datos,
Entonces se retorna el detalle del usuario con status 200.

# Scenario 3: Consulta de usuario por ID inexistente
Dado que el developer realiza un GET con un ID que no corresponde a ningún usuario,
Cuando se procesa la solicitud,
Entonces se retorna status 404 con un mensaje indicando que el usuario no existe.

---

## TS-08: Endpoint: CRUD de empresas compradoras

# Scenario 1: Registro exitoso de empresa compradora
Dado que el developer envía un POST con los datos completos de la empresa (RUC, razón social, dirección, contacto),
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201 con el ID de la empresa creada.

# Scenario 2: Listado de empresas compradoras
Dado que el developer realiza un GET al endpoint de empresas compradoras,
Cuando existen registros en la base de datos,
Entonces se retorna un array con las empresas y status 200.

# Scenario 3: Consulta por ID
Dado que el developer realiza un GET con un ID de empresa compradora válido,
Cuando el registro existe,
Entonces se retorna el detalle de la empresa con status 200.

# Scenario 4: Actualización de datos
Dado que el developer envía un PUT/PATCH con datos válidos de una empresa existente,
Cuando se procesa la solicitud,
Entonces los cambios se guardan correctamente y se retorna status 200.

# Scenario 5: RUC duplicado al registrar
Dado que el developer intenta registrar una empresa con un RUC ya existente,
Cuando se procesa el POST,
Entonces se retorna status 409 indicando que el RUC ya está registrado.

---

## TS-09: Endpoint: CRUD de empresas proveedoras

# Scenario 1: Registro exitoso de empresa proveedora
Dado que el developer envía un POST con los datos completos de la empresa proveedora,
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201 con el ID generado.

# Scenario 2: Listado de empresas proveedoras
Dado que el developer realiza un GET al endpoint de empresas proveedoras,
Cuando existen registros,
Entonces se retorna un array con las empresas y status 200.

# Scenario 3: Consulta por ID
Dado que el developer realiza un GET con un ID válido de empresa proveedora,
Cuando el registro existe,
Entonces se retorna el detalle con status 200.

# Scenario 4: Actualización de datos
Dado que el developer envía un PUT/PATCH con datos válidos,
Cuando se procesa la solicitud,
Entonces los cambios se reflejan correctamente y se retorna status 200.

# Scenario 5: RUC duplicado al registrar
Dado que el developer intenta registrar una empresa proveedora con un RUC ya existente en el sistema,
Cuando se procesa el POST,
Entonces se retorna status 409 con un mensaje de conflicto.

---

## TS-10: Endpoint: Actualizar perfil de usuario

# Scenario 1: Actualización exitosa del perfil propio
Dado que el developer envía un PUT/PATCH con un token válido y datos correctos del usuario autenticado,
Cuando se procesa la solicitud,
Entonces el perfil se actualiza y se retorna status 200 con los datos modificados.

# Scenario 2: Intento de actualizar perfil de otro usuario
Dado que el developer envía una solicitud para modificar el perfil de un usuario distinto al autenticado,
Cuando se valida el token,
Entonces se retorna status 403 indicando acceso denegado.

# Scenario 3: Datos inválidos en la actualización
Dado que el developer envía datos con formato incorrecto o campos vacíos obligatorios,
Cuando se procesa la solicitud,
Entonces se retorna status 400 con el detalle de los errores de validación.

---

## TS-11: Endpoint: CRUD de productos de combustible

# Scenario 1: Creación exitosa de producto
Dado que el developer envía un POST con nombre, tipo de combustible, precio por litro y unidad,
Cuando se procesa la solicitud,
Entonces se crea el producto y se retorna status 201 con el ID generado.

# Scenario 2: Listado de productos
Dado que el developer realiza un GET al endpoint de productos,
Cuando existen productos registrados,
Entonces se retorna un array de productos con status 200.

# Scenario 3: Consulta por ID y por proveedor
Dado que el developer realiza un GET filtrando por ID de producto o por ID de proveedor,
Cuando existen coincidencias,
Entonces se retorna el producto o la lista filtrada con status 200.

# Scenario 4: Actualización de producto
Dado que el developer envía un PUT/PATCH con datos válidos sobre un producto existente,
Cuando se procesa la solicitud,
Entonces los cambios se guardan y se retorna status 200.

# Scenario 5: Eliminación de producto
Dado que el developer envía un DELETE sobre un producto existente,
Cuando se confirma la operación,
Entonces el producto se elimina y se retorna status 204.

---

## TS-12: Endpoint: Actualizar stock de producto

# Scenario 1: Actualización exitosa de stock
Dado que el developer envía un PATCH con la nueva cantidad de stock para un producto existente,
Cuando se procesa la solicitud,
Entonces el stock se actualiza y se retorna status 200 con el valor actualizado.

# Scenario 2: Producto inexistente
Dado que el developer intenta actualizar el stock de un producto que no existe,
Cuando se procesa la solicitud,
Entonces se retorna status 404 con un mensaje de error.

# Scenario 3: Valor de stock inválido
Dado que el developer envía un valor negativo o no numérico para el stock,
Cuando se valida la solicitud,
Entonces se retorna status 400 indicando el error de formato.

---

## TS-13: Endpoint: Consultar pedidos

# Scenario 1: Listado de todos los pedidos
Dado que el developer realiza un GET al endpoint general de pedidos,
Cuando existen pedidos registrados,
Entonces se retorna un array de pedidos y status 200.

# Scenario 2: Consulta por ID
Dado que el developer realiza un GET con un ID de pedido válido,
Cuando el pedido existe,
Entonces se retorna el detalle del pedido con status 200.

# Scenario 3: Consulta por empresa compradora o proveedor
Dado que el developer realiza un GET filtrando por ID de empresa compradora o proveedora,
Cuando existen pedidos asociados,
Entonces se retorna la lista filtrada con status 200.

# Scenario 4: Pedido o filtro sin resultados
Dado que el developer consulta un ID o filtro sin coincidencias,
Cuando se procesa la solicitud,
Entonces se retorna un array vacío o status 404 según el tipo de consulta.

---

## TS-14: Endpoint: Confirmar / cancelar pedido

# Scenario 1: Confirmación exitosa del pedido
Dado que el developer envía un PATCH para confirmar un pedido en estado válido,
Cuando se procesa la solicitud,
Entonces el estado cambia a "Confirmado" y se retorna status 200.

# Scenario 2: Cancelación exitosa del pedido
Dado que el developer envía un PATCH para cancelar un pedido que aún no ha sido despachado,
Cuando se procesa la solicitud,
Entonces el estado cambia a "Cancelado" y se retorna status 200.

# Scenario 3: Intento de cancelar un pedido ya despachado o cerrado
Dado que el developer intenta cancelar un pedido en un estado que no lo permite,
Cuando se procesa la solicitud,
Entonces se retorna status 409 indicando que la operación no es válida para el estado actual.

---

## TS-15: Endpoint: Solicitudes de combustible

# Scenario 1: Creación exitosa de solicitud
Dado que el developer envía un POST con los datos completos de la solicitud de combustible,
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201 con el ID generado.

# Scenario 2: Listado de solicitudes
Dado que el developer realiza un GET al endpoint de solicitudes,
Cuando existen registros,
Entonces se retorna un array de solicitudes con status 200.

# Scenario 3: Aceptación de solicitud
Dado que el developer envía un PATCH para aceptar una solicitud pendiente,
Cuando se procesa la solicitud,
Entonces el estado cambia a "Aceptada" y se retorna status 200.

# Scenario 4: Rechazo de solicitud
Dado que el developer envía un PATCH para rechazar una solicitud pendiente,
Cuando se procesa la solicitud,
Entonces el estado cambia a "Rechazada" y se retorna status 200.

---

## TS-16: Endpoint: Consultar solicitud por ID

# Scenario 1: Consulta exitosa
Dado que el developer realiza un GET con un ID de solicitud válido,
Cuando la solicitud existe,
Entonces se retorna el detalle completo con status 200.

# Scenario 2: Solicitud inexistente
Dado que el developer realiza un GET con un ID que no corresponde a ninguna solicitud,
Cuando se procesa la consulta,
Entonces se retorna status 404 con un mensaje de error.

---

## TS-17: Endpoint: Gestión de entregas

# Scenario 1: Creación de entrega
Dado que el developer envía un POST con los datos de la entrega (pedido, vehículo, conductor),
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201.

# Scenario 2: Despacho de entrega
Dado que el developer envía un PATCH para marcar una entrega como despachada,
Cuando la entrega está en estado válido,
Entonces el estado cambia a "Despachada" y se retorna status 200.

# Scenario 3: Completar entrega
Dado que el developer envía un PATCH para marcar una entrega como completada,
Cuando se procesa la solicitud,
Entonces el estado cambia a "Completada" y se retorna status 200.

# Scenario 4: Marcar entrega como fallida
Dado que el developer envía un PATCH indicando que la entrega no pudo concretarse,
Cuando se procesa la solicitud,
Entonces el estado cambia a "Fallida" y se registra el motivo correspondiente.

# Scenario 5: Consulta de entregas (todas, por ID, por proveedor, por pedido)
Dado que el developer realiza un GET con o sin filtros específicos,
Cuando existen registros coincidentes,
Entonces se retorna la información solicitada con status 200.

---

## TS-18: Endpoint: CRUD de conductores

# Scenario 1: Registro exitoso de conductor
Dado que el developer envía un POST con nombre, DNI y licencia del conductor,
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201.

# Scenario 2: Listado por proveedor
Dado que el developer realiza un GET filtrando por ID de proveedor,
Cuando existen conductores asociados,
Entonces se retorna la lista correspondiente con status 200.

# Scenario 3: Consulta por ID
Dado que el developer realiza un GET con un ID de conductor válido,
Cuando el conductor existe,
Entonces se retorna su detalle con status 200.

# Scenario 4: Actualización de datos
Dado que el developer envía un PUT/PATCH con datos válidos,
Cuando se procesa la solicitud,
Entonces los cambios se reflejan correctamente y se retorna status 200.

# Scenario 5: Eliminación de conductor
Dado que el developer envía un DELETE sobre un conductor existente,
Cuando se confirma la operación,
Entonces el registro se elimina y se retorna status 204.

# Scenario 6: DNI duplicado al registrar
Dado que el developer intenta registrar un conductor con un DNI ya existente,
Cuando se procesa el POST,
Entonces se retorna status 409 con un mensaje de conflicto.

---

## TS-19: Endpoint: CRUD de vehículos

# Scenario 1: Registro exitoso de vehículo
Dado que el developer envía un POST con los datos del vehículo (placa, modelo, capacidad),
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201.

# Scenario 2: Listado por proveedor
Dado que el developer realiza un GET filtrando por ID de proveedor,
Cuando existen vehículos asociados,
Entonces se retorna la lista correspondiente con status 200.

# Scenario 3: Consulta por ID
Dado que el developer realiza un GET con un ID de vehículo válido,
Cuando el vehículo existe,
Entonces se retorna su detalle con status 200.

# Scenario 4: Actualización de datos
Dado que el developer envía un PUT/PATCH con datos válidos sobre un vehículo existente,
Cuando se procesa la solicitud,
Entonces los cambios se guardan y se retorna status 200.

# Scenario 5: Eliminación de vehículo
Dado que el developer envía un DELETE sobre un vehículo existente,
Cuando se confirma la operación,
Entonces el registro se elimina y se retorna status 204.

# Scenario 6: Placa duplicada al registrar
Dado que el developer intenta registrar un vehículo con una placa ya existente,
Cuando se procesa el POST,
Entonces se retorna status 409 indicando que la placa ya está registrada.

---

## TS-20: Endpoint: Registrar y procesar pagos

# Scenario 1: Registro exitoso de pago
Dado que el developer envía un POST con los datos del pago (pedido, monto, número de operación),
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201.

# Scenario 2: Marcar pago como completado
Dado que el developer envía un PATCH para marcar un pago pendiente como completado,
Cuando se procesa la solicitud,
Entonces el estado cambia a "Completado" y se retorna status 200.

# Scenario 3: Procesar reembolso
Dado que el developer envía un PATCH para procesar el reembolso de un pago ya completado,
Cuando se procesa la solicitud,
Entonces el estado cambia a "Reembolsado" y se retorna status 200.

# Scenario 4: Número de operación duplicado
Dado que el developer intenta registrar un pago con un número de operación ya existente,
Cuando se procesa el POST,
Entonces se retorna status 409 con un mensaje de conflicto.

---

## TS-21: Endpoint: Consultar pagos

# Scenario 1: Listado de todos los pagos
Dado que el developer realiza un GET al endpoint general de pagos,
Cuando existen pagos registrados,
Entonces se retorna un array con status 200.

# Scenario 2: Consulta por ID
Dado que el developer realiza un GET con un ID de pago válido,
Cuando el pago existe,
Entonces se retorna su detalle con status 200.

# Scenario 3: Consulta por pedido o por empresa
Dado que el developer realiza un GET filtrando por ID de pedido o de empresa,
Cuando existen pagos asociados,
Entonces se retorna la lista filtrada con status 200.

---

## TS-22: Endpoint: Calificaciones de proveedores

# Scenario 1: Creación exitosa de calificación
Dado que el developer envía un POST con el puntaje y comentario de la calificación,
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201.

# Scenario 2: Listado de calificaciones
Dado que el developer realiza un GET al endpoint de calificaciones,
Cuando existen registros para un proveedor,
Entonces se retorna un array con status 200.

# Scenario 3: Actualización de calificación
Dado que el developer envía un PUT/PATCH sobre una calificación existente,
Cuando se procesa la solicitud,
Entonces los cambios se reflejan correctamente y se retorna status 200.

# Scenario 4: Puntaje fuera de rango válido
Dado que el developer envía un puntaje fuera del rango permitido (por ejemplo, mayor a 5),
Cuando se valida la solicitud,
Entonces se retorna status 400 con un mensaje de validación.

---

## TS-23: Endpoint: Gestión de equipos

# Scenario 1: Registro exitoso de equipo
Dado que el developer envía un POST con los datos del equipo y la empresa asociada,
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201.

# Scenario 2: Actualización de equipo
Dado que el developer envía un PUT/PATCH con datos válidos sobre un equipo existente,
Cuando se procesa la solicitud,
Entonces los cambios se guardan y se retorna status 200.

# Scenario 3: Listado de equipos
Dado que el developer realiza un GET al endpoint de equipos,
Cuando existen registros,
Entonces se retorna un array con status 200.

# Scenario 4: Consulta por ID y por empresa
Dado que el developer realiza un GET filtrando por ID de equipo o por ID de empresa,
Cuando existen coincidencias,
Entonces se retorna el resultado correspondiente con status 200.

---

## TS-24: Endpoint: Asignar proveedor favorito

# Scenario 1: Asignación exitosa
Dado que el developer envía un PATCH indicando el ID del proveedor favorito para un equipo existente,
Cuando se procesa la solicitud,
Entonces la asignación se guarda y se retorna status 200.

# Scenario 2: Proveedor inexistente
Dado que el developer intenta asignar un proveedor que no existe en el sistema,
Cuando se procesa la solicitud,
Entonces se retorna status 404 con un mensaje de error.

# Scenario 3: Equipo inexistente
Dado que el developer intenta asignar un proveedor favorito a un equipo que no existe,
Cuando se procesa la solicitud,
Entonces se retorna status 404 indicando que el equipo no fue encontrado.

---

## TS-25: Endpoint: Eliminar equipo

# Scenario 1: Eliminación exitosa
Dado que el developer envía un DELETE sobre un equipo existente,
Cuando se confirma la operación,
Entonces el registro se elimina y se retorna status 204.

# Scenario 2: Equipo inexistente
Dado que el developer intenta eliminar un equipo que no existe en el sistema,
Cuando se procesa la solicitud,
Entonces se retorna status 404 con un mensaje de error.

# Scenario 3: Equipo con dependencias activas
Dado que el developer intenta eliminar un equipo que tiene pedidos o asignaciones activas,
Cuando se procesa la solicitud,
Entonces se retorna status 409 indicando que no puede eliminarse por dependencias existentes.

---

## TS-26: Endpoint: Sistema de notificaciones

# Scenario 1: Creación de notificación
Dado que el developer envía un POST con los datos de la notificación (usuario destinatario, mensaje, tipo),
Cuando se procesa la solicitud,
Entonces se crea el registro y se retorna status 201.

# Scenario 2: Marcar notificación como leída
Dado que el developer envía un PATCH para marcar una notificación existente como leída,
Cuando se procesa la solicitud,
Entonces el estado cambia a "Leída" y se retorna status 200.

# Scenario 3: Consultar notificaciones por usuario
Dado que el developer realiza un GET filtrando por ID de usuario,
Cuando existen notificaciones asociadas,
Entonces se retorna la lista correspondiente con status 200.

# Scenario 4: Consultar notificaciones por empresa compradora o proveedor
Dado que el developer realiza un GET filtrando por ID de empresa compradora o proveedora,
Cuando existen notificaciones asociadas,
Entonces se retorna la lista filtrada con status 200.

# Scenario 5: Consultar notificaciones no leídas de un usuario
Dado que el developer realiza un GET filtrando por usuario y estado "no leída",
Cuando existen coincidencias,
Entonces se retorna el array correspondiente con status 200.

---

## TS-27: Endpoint: Reportes y analítica

# Scenario 1: Resumen general de la plataforma
Dado que el developer realiza un GET al endpoint de resumen general,
Cuando existen datos operativos registrados,
Entonces se retorna un objeto con las métricas consolidadas y status 200.

# Scenario 2: Analítica por proveedor específico
Dado que el developer realiza un GET filtrando por ID de proveedor,
Cuando el proveedor tiene operaciones registradas,
Entonces se retorna el detalle analítico correspondiente con status 200.

# Scenario 3: Analítica por empresa compradora específica
Dado que el developer realiza un GET filtrando por ID de empresa compradora,
Cuando la empresa tiene pedidos registrados,
Entonces se retorna el detalle analítico correspondiente con status 200.

# Scenario 4: Sin datos suficientes para el reporte
Dado que el developer solicita analítica de una entidad sin operaciones registradas,
Cuando se procesa la solicitud,
Entonces se retorna un objeto vacío o un mensaje indicando que no hay datos suficientes, con status 200.