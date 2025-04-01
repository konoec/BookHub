# BookHub

## Descripción
BookHub es una aplicación desarrollada en Java que permite a los usuarios gestionar su colección de libros. La aplicación proporciona funcionalidades para añadir, editar, eliminar, además de buscar libros dentro de la colección.

## Características
- Añadir nuevos libros con detalles como título, autor, género y año de publicación.
- Editar la información de los libros existentes.
- Eliminar libros de la colección.
- Buscar libros por título, autor o género.
- Interfaz de usuario intuitiva y fácil de usar.

## Requisitos del Sistema
- Java 8 o superior.
- Spring Boot 2.5.4 o superior.
- IDE recomendado: IntelliJ IDEA o Eclipse.
- Base de datos (por ejemplo, MySQL, PostgreSQL).

## Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/konoec/BookHub.git
   ```
2. Importa el proyecto en tu IDE preferido.
3. Configura la base de datos en el archivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/bookhub
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```
4. Compila y ejecuta la aplicación.

## Uso
1. Ejecuta la aplicación.
2. Usa el menú principal para navegar entre las diferentes funcionalidades.
3. Añade, edita, elimina y busca libros según tus necesidades.

## Contribuciones
Las contribuciones son bienvenidas. Por favor, sigue los siguientes pasos:
1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -m 'Añadir nueva característica'`).
4. Sube tus cambios (`git push origin feature/nueva-caracteristica`).
5. Abre un Pull Request.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

# BookHub

## Description
BookHub is a Java application that allows users to manage their book collection. The application provides functionalities to add, edit, delete, and search for books within the collection.

## Features
- Add new books with details such as title, author, genre, and publication year.
- Edit the information of existing books.
- Delete books from the collection.
- Search for books by title, author, or genre.
- Intuitive and user-friendly interface.

## System Requirements
- Java 8 or higher.
- Spring Boot 2.5.4 or higher.
- Recommended IDE: IntelliJ IDEA or Eclipse.
- Database (e.g., MySQL, PostgreSQL).

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/konoec/BookHub.git
   ```
2. Import the project into your preferred IDE.
3. Configure the database in the `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/bookhub
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```
4. Compile and run the application.

## Usage
1. Run the application.
2. Use the main menu to navigate through the different functionalities.
3. Add, edit, delete, and search for books as needed.

## Contributions
Contributions are welcome. Please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push your changes (`git push origin feature/new-feature`).
5. Open a Pull Request.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
