import random
from faker import Faker
from datetime import datetime, timedelta

fake = Faker('es_CO')

# --- CONFIGURACIÓN ---
NUM_DONANTES = 250
NUM_PACIENTES = 250
NUM_HOSPITALES = 50
NUM_BANCOS = 20
NUM_DONACIONES = 1000
NUM_TRANSFUSIONES = 1000
NUM_CITAS = 500
NUM_ENCARGOS = 200

tipos_sangre = ['O+', 'O-', 'A+', 'A-', 'B+', 'B-', 'AB+', 'AB-']

sql_statements = []

# 1. Donantes
sql_statements.append("-- INSERTANDO DONANTES --")
for i in range(NUM_DONANTES):
    nombre = fake.name()
    direccion = fake.address().replace("\n", ", ")
    telefono = fake.phone_number()
    tipo_sangre = random.choice(tipos_sangre)
    sql_statements.append(
        f"INSERT INTO Donante (nombre, direccion, telefono, tipo_sangre) "
        f"VALUES ('{nombre}', '{direccion}', '{telefono}', '{tipo_sangre}');"
    )

# 2. Pacientes
sql_statements.append("\n-- INSERTANDO PACIENTES --")
for i in range(NUM_PACIENTES):
    nombre = fake.name()
    direccion = fake.address().replace("\n", ", ")
    telefono = fake.phone_number()
    tipo_sangre = random.choice(tipos_sangre)
    sql_statements.append(
        f"INSERT INTO Paciente (nombre, direccion, tipo_sangre, telefono) "
        f"VALUES ('{nombre}', '{direccion}', '{tipo_sangre}', '{telefono}');"
    )

# 3. Hospitales
sql_statements.append("\n-- INSERTANDO HOSPITALES --")
for i in range(NUM_HOSPITALES):
    nombre = fake.company()
    direccion = fake.address().replace("\n", ", ")
    telefono = fake.phone_number()
    sql_statements.append(
        f"INSERT INTO Hospital (nombre, direccion, telefono) "
        f"VALUES ('{nombre}', '{direccion}', '{telefono}');"
    )

# 4. Bancos de Sangre
sql_statements.append("\n-- INSERTANDO BANCOS DE SANGRE --")
for i in range(NUM_BANCOS):
    nombre = f"Banco {fake.company()}"
    ubicacion = fake.city()
    sangre_disponible = random.randint(1, NUM_DONACIONES)
    sql_statements.append(
        f"INSERT INTO Banco_Sangre (nombre, ubicacion, sangre_disponible) "
        f"VALUES ('{nombre}', '{ubicacion}', {sangre_disponible});"
    )

# 5. Donaciones
sql_statements.append("\n-- INSERTANDO DONACIONES --")
for i in range(NUM_DONACIONES):
    tipo_sangre = random.choice(tipos_sangre)
    fecha = fake.date_between(start_date='-1y', end_date='today')
    id_donante = random.randint(1, NUM_DONANTES)
    sql_statements.append(
        f"INSERT INTO Donacion (tipo_sangre, fecha, id_donante) "
        f"VALUES ('{tipo_sangre}', '{fecha}', {id_donante});"
    )

# 6. Transfusiones
sql_statements.append("\n-- INSERTANDO TRANSFUSIONES --")
for i in range(NUM_TRANSFUSIONES):
    id_paciente = random.randint(1, NUM_PACIENTES)
    id_donante = random.randint(1, NUM_DONANTES)
    id_donacion = random.randint(1, NUM_DONACIONES)
    sql_statements.append(
        f"INSERT INTO Transfusion (id_paciente, id_donante, id_donacion) "
        f"VALUES ({id_paciente}, {id_donante}, {id_donacion});"
    )

# 7. Citas
sql_statements.append("\n-- INSERTANDO CITAS --")
for i in range(NUM_CITAS):
    fecha = fake.date_between(start_date='today', end_date='+30d')
    lugar = fake.city()
    id_transfusion = random.randint(1, NUM_TRANSFUSIONES)
    sql_statements.append(
        f"INSERT INTO Cita (fecha, lugar, id_transfusion) "
        f"VALUES ('{fecha}', '{lugar}', {id_transfusion});"
    )

# 8. Encargos
sql_statements.append("\n-- INSERTANDO ENCARGOS --")
for i in range(NUM_ENCARGOS):
    cantidad = random.randint(1,10)
    tipo_sangre = random.choice(tipos_sangre)
    destino = fake.city()
    origen = fake.city()
    estado = random.choice(['Pendiente', 'En proceso', 'Completado'])
    sql_statements.append(
        f"INSERT INTO Encargos (cantidad, tipo_sangre, destino, origen, estado) "
        f"VALUES ({cantidad}, '{tipo_sangre}', '{destino}', '{origen}', '{estado}');"
    )

# Guardar en archivo
with open('poblado_donantes_1000.sql', 'w', encoding='utf-8') as f:
    for linea in sql_statements:
        f.write(linea + '\n')

print(f"¡Éxito! Archivo 'poblado_donantes_1000.sql' generado con {len(sql_statements)} instrucciones SQL.")