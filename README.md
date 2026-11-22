# Simulación de Centro de Cómputos y Escrutinio Electoral (Clases Abstractas y Polimorfismo en Java)

Este proyecto es una aplicación en Java que modela un sistema de escrutinio electoral, implementando una arquitectura basada en clases abstractas y herencia para administrar distintos tipos de categorías electorales (intendente, concejales y legisladores) con reglas especializadas para validar y contabilizar votos en función de cantidades emitidas, tachas y preferencias. Pone en práctica conceptos fundamentales de la Programación Orientada a Objetos (POO) como la generalización de atributos protegidos (`protected`), el uso de constructores mediante `super`, la definición de métodos abstractos (`abstract`), la sobrescritura polimórfica de comportamiento (`@Override`), el manejo de colecciones dinámicas (`ArrayList`) y el procesamiento centralizado en memoria.

---

## Clases y Estructura del Sistema

El diseño del software se organiza a través de una clase base abstracta, sus subclases especializadas por categoría electoral, un componente procesador de escrutinio y un punto de ejecución:

* **`BoletaElectoral` (Clase Abstracta)**: Centraliza los atributos y comportamientos comunes de cualquier boleta o categoría dentro del proceso electoral.
* **Atributos protegidos (`protected`)**: `nombreCandidato` y `partidoPolitico`, permitiendo que las clases hijas accedan directamente a los datos identificatorios de la candidatura.
* **Constructor**: Inicializa el nombre del candidato y el partido político correspondiente.
* **Métodos operativos**:
* `abstract int contarVoto(int cantidadEmitida)`: Método abstracto que obliga a cada tipo de boleta a definir sus propias reglas de validez y cómputo de votos.
* `getNombreCandidato()` / `getPartidoPolitico()`: Métodos de acceso público para consultar de forma encapsulada los datos de la boleta.




* **`BoletaIntendente`**: Extiende de `BoletaElectoral` para modelar la categoría ejecutiva municipal.
* **Sobrescritura (`@Override`)**:
* `contarVoto(int cantidadEmitida)`: Valida que la cantidad de votos emitidos no sea negativa; si es válida, retorna el total emitido, y en caso contrario devuelve `0`.




* **`BoletaConcejales`**: Extiende de `BoletaElectoral` para modelar la categoría legislativa municipal sujeta a control de tachas.
* **Atributo privado (`private`)**: `cantidadTachas`.
* **Constructor**: Invoca a `super(...)` para inicializar candidato y partido, y asigna la cantidad de tachas registradas.
* **Sobrescritura (`@Override`)**:
* `contarVoto(int cantidadEmitida)`: Verifica que los votos emitidos no sean negativos y que la cantidad de tachas se encuentre dentro del rango permitido (entre `0` y `2` inclusive); si excede el límite o es inválida, anula el cómputo devolviendo `0`.




* **`BoletaLegisladores`**: Extiende de `BoletaElectoral` para modelar cargos legislativos con sistema de preferencias.
* **Atributo privado (`private`)**: `cantidadPreferencias`.
* **Constructor**: Utiliza `super(...)` para los atributos base y configura la cantidad de preferencias marcadas.
* **Sobrescritura (`@Override`)**:
* `contarVoto(int cantidadEmitida)`: Comprueba que la cantidad de votos emitidos no sea negativa y que las preferencias estén dentro del tope válido (entre `0` y `3` inclusive); si supera el máximo o es negativa, retorna `0`.




* **`CentroComputos`**: Componente controlador encargado de centralizar las boletas y ejecutar el escrutinio.
* **Atributo privado (`private`)**: `ArrayList<BoletaElectoral> boletas`.
* **Métodos operativos**:
* `agregarBoleta(BoletaElectoral boleta)`: Incorpora una nueva boleta a la lista de cómputo.
* `procesarVotos(ArrayList<Integer> votosEmitidos)`: Recorre las boletas registradas, invoca polimórficamente `contarVoto(...)` con la cantidad correspondiente de votos emitidos, acumula los votos válidos e imprime el reporte detallado junto con el total general.




* **`Main`**: Punto de entrada del programa. Instancia un `CentroComputos`, crea las boletas de intendente, concejales y legisladores, las registra en el centro, carga una lista con los votos emitidos por categoría y ejecuta `procesarVotos(...)` para mostrar el resultado por consola.

---

## Reglas de Negocio y Control de Validación

1. **Contrato Estricto de Escrutinio:** La clase abstracta `BoletaElectoral` obliga a cada categoría a implementar `contarVoto(int cantidadEmitida)`, unificando el punto de entrada del cálculo mientras permite reglas distintas según el cargo.
2. **Validación Defensiva de Parámetros:** Cada subclase controla cantidades negativas y verifica los límites normativos de tachas (máximo 2) y preferencias (máximo 3) antes de validar los votos.
3. **Procesamiento Polimórfico en Memoria:** El `CentroComputos` opera sobre una colección de `BoletaElectoral` sin depender de implementaciones concretas, resolviendo en tiempo de ejecución qué regla de conteo aplica a cada boleta.

---

## Salida por Consola

Al ejecutar la clase `Main`, el programa genera el siguiente resultado:

```text
===== REPORTE ELECTORAL =====
------------------------------
Candidato: Juan Perez
Partido: Partido A
Votos validos: 1500
------------------------------
Candidato: Maria Gomez
Partido: Partido B
Votos validos: 1200
------------------------------
Candidato: Carlos Lopez
Partido: Partido C
Votos validos: 0
------------------------------
TOTAL DE VOTOS VALIDOS: 2700

```

---

## Conceptos de Programación Aplicados

* **Abstracción y Polimorfismo:** Uso de una clase base abstracta y sobrescritura de métodos (`@Override`) para tratar distintas boletas electorales de manera uniforme.
* **Encapsulamiento:** Protección de los atributos internos mediante modificadores `protected` y `private`, exponiendo datos solo a través de métodos de consulta (`getters`) o lógica de validación.
* **Separación de Responsabilidades:** División clara entre el modelado de cada boleta, la lógica de escrutinio en `CentroComputos` y la orquestación de datos de prueba en `Main`.
