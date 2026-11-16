# APE4_Fase1_Grafos-y-vertices
Red social representada con un **grafo simple, no dirigido y no ponderado**: **6 usuarios** (vértices) y **5 amistades** (aristas), con un ciclo, un usuario aislado y un vértice de grado 3.

| ID | Usuario | Grado |
| --- | --- | --- |
| U01 | Mateo | 3 |
| U02 | Sofía | 2 |
| U03 | Luis | 2 |
| U04 | Valeria | 2 |
| U05 | Andrés | 1 |
| U06 | Camila | 0 |

Amistades: Mateo–Sofía, Sofía–Luis, Luis–Mateo, Mateo–Valeria y Valeria–Andrés.

## Respuestas a las 6 preguntas

### 1. Conjuntos V y E

G = (V, E), donde:

- **V** = {U01, U02, U03, U04, U05, U06}: los usuarios Mateo, Sofía, Luis, Valeria, Andrés y Camila. |V| = 6.
- **E** = {{U01,U02}, {U02,U03}, {U03,U01}, {U01,U04}, {U04,U05}}: las amistades. |E| = 5.

### 2. Tipo de grafo

- **Simple:** no hay lazos ni aristas repetidas (Mateo–Sofía se registra una sola vez).
- **No dirigido:** la amistad es mutua; Mateo–Sofía equivale a Sofía–Mateo.
- **No ponderado:** las aristas solo indican que la amistad existe, sin valores numéricos.

### 3. Grados

- Usuario aislado: **Camila (U06)**, grado 0.
- Usuario con más amistades: **Mateo (U01)**, grado 3 (Sofía, Luis y Valeria).
- Comprobación: 3 + 2 + 2 + 2 + 1 + 0 = 10 = 2·5 = 2|E| ✔

### 4. Ciclo y conectividad

- Ciclo: **U01 – U02 – U03 – U01** (Mateo – Sofía – Luis – Mateo).
- Mateo, Sofía, Luis, Valeria y Andrés forman un componente conectado; Camila queda aparte.
- El grafo **no es conexo**.

### 5. Consultas

| Consulta | Resultado | Motivo |
| --- | --- | --- |
| `existeRelacion(U01, U02)` | verdadero | Mateo y Sofía son amigos |
| `existeRelacion(U03, U04)` | falso | Luis y Valeria no tienen amistad directa |
| `existeRelacion(U02, U05)` | falso | Sofía y Andrés no tienen amistad directa |
| `vecinos(U01)` | {U02, U03, U04} | Sofía, Luis y Valeria |
| `grado(U01)` | 3 | Número de vecinos / aristas incidentes |
| `vecinos(U06)` | { } | Camila no tiene amistades |
| `grado(U06)` | 0 | Vértice aislado |

### 6. Relaciones que deben rechazarse

| Relación | Motivo |
| --- | --- |
| U03–U03 | Lazo: un usuario no puede ser amigo de sí mismo |
| U02–U01 | Duplicada: ya existe como U01–U02 |
| U01–U09 | U09 no pertenece al conjunto V |

## Código: `RedSocial.java`

Programa de consola que reproduce las respuestas anteriores. Requiere JDK 8 o superior.

```bash
javac RedSocial.java
java RedSocial
```

Métodos principales:

- `existeRelacion(a, b)`, `vecinos(id)`, `grado(id)`: consultas; lanzan `IllegalArgumentException` si el ID no existe.
- `validarAmistad(a, b)`: devuelve `OK` o el motivo del rechazo (usuario inexistente, lazo o duplicada).
