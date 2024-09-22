# Microservicios de Aplicaciones


### Ejecutar Docker
Comando para ejecutar el comando de Docker

```shell
docker build -t usuarios . -f .\msv-usuarios\Dockerfile
```
```shell
docker build -t cursos . -f .\msv-cursos\Dockerfile
```

### Arrancamos un contenedor docker
```shell
docker run -p 8001:8001 --rm -d --name msv-usuarios usuarios
```
```shell
docker run -p 8002:8002 --rm -d --name msv-cursos cursos
```

### Eliminar un Contenedor
```shell
$ docker ps
CONTAINER ID   IMAGE          COMMAND           CREATED          STATUS                      PORTS     NAMES
ba8e8e9d4303   46c99cdcdcd5   "java -version"   56 minutes ago   Exited (0) 55 minutes ago             trusting_panini

$ docker rm ba8e8e9d4303
Deleted Containers:
ba8e8e9d430339d37869baf226d1b8f238181befcbab7b2b2b9649a585b0266a
```

### Eliminar masiva Contenedores

```shell
$ docker ps
CONTAINER ID   IMAGE          COMMAND           CREATED          STATUS                      PORTS     NAMES
ba8e8e9d4303   46c99cdcdcd5   "java -version"   56 minutes ago   Exited (0) 55 minutes ago             trusting_panini

$ docker container prune
WARNING! This will remove all stopped containers.
Are you sure you want to continue? [y/N] y
Deleted Containers:
ba8e8e9d430339d37869baf226d1b8f238181befcbab7b2b2b9649a585b0266a
```

### Eliminacion masiva de imagenes
```shell
$ docker image prune
WARNING! This will remove all dangling images.
Are you sure you want to continue? [y/N] y
Deleted Images:
deleted: sha256:e730f300587453b6a9d746a2c5ec55250f88b7abcf3777c02a8b6547b90da0f7
deleted: sha256:461cd785252a5af5b9c6d3f643d90d596aa732443f80272fbb8b48ca89f8f0ec
deleted: sha256:00ab7c63387feb5cdfcaa32a48eed6a32b7472a57e6339f052d90e16c0d76e7e
deleted: sha256:46c99cdcdcd558f86d6d97e2496bd8b2becfdd33e0e83fef797c7b1af97c946d
deleted: sha256:bf570a12fec04cfdb0c4410a55d37749cc7304bfc66f8888e980a92df29898df
```

```shell
$ docker rmi e730f3005874 461cd7852525
```

### Creando una red
```shell
docker network create spring
```

### Asociamos entre los contenedores la misma red
```shell
docker run -p 8001:8001 -d -rm --name msv-usuarios --network spring usuarios
```
```shell
docker run -p 8002:8002 -d -rm --name msv-cursos --network spring cursos
```

### Descargando y corriendo las imagenes
#### Mysql
```shell
docker pull mysql:latest
```
```shell
docker run -d -p 3306:3306 --name mysql --network spring -e MYSQL_ROOT_PASSWORD=sasa -e MYSQL_DATABASE=msv_usuarios mysql:latest
```
Conectandome desde el terminal al contenedor:
```shell
$ docker exec -it mysql bash
```
Luego ingresar a la base de datos
```shell
#password = sasa
$ mysql -u root -p
Enter password:
```
Listamos los schemas creados:
```shell
SHOW DATABASES;
```
