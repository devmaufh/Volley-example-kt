CREATE DATABASE services_example;
create table tareas( id_tarea SERIAL PRIMARY KEY,
                     titulo VARCHAR(40),
                     descripcion VARCHAR(250)
                     );
                     