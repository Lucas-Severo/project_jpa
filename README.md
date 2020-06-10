<h1 align=center>Projeto JPA</h1>
<p align=center>Um projeto java utilizando JPA para a persistência dos dados</p>

<h1 align=center>
	<img src="readme/preview.gif" alt="preview"/>
</h1>

# Requisitos

- Java 11
- PostgreSQL

# Clone

```
git clone git@github.com:Lucas-Severo/project_jpa.git
```

# Configuração

1) Crie um banco de dados com postgresql com o nome ```todos```

2) Altera o usuário e senha no arquivo [persistence.xml](https://github.com/Lucas-Severo/project_jpa/blob/master/src/main/resources/META-INF/persistence.xml#L20)

# Instalação

## Linux
```bash
cd project_jpa/
./mvnw compile assembly:single
```

## Windows
```bash
cd project_jpa/
mvnw.cmd assembly:single
```

# Execução

```
java -jar target/todo-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```
