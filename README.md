# Saludable
# Censo2021
Aplicación móvil para Android desarrollada en el marco del bootcamp Android Kotlin que lleve a cabo en el año 2021. 
Está destinada para que el paciente de un centro de nutrición registre sus comidas diarias permitiendo a dicho centro poder consumir la información registrada desde otra plataforma para llevar un seguimiento del mismo. Dicha aplicación cuenta con las siguientes características:
- El paciente al ingresar a la aplicación se registra una nueva cuenta con la que va a iniciar sesión en la aplicación, en dicho registro coloca sus datos personales, el usuario, contraseña y el tipo de tratamiento (Bulimia, Anorexia u Obesidad).
- Una vez iniciada la sesión en la aplicación el paciente registrará las comidas que haya consumido en el día indicando alguno de los siguientes tipos: 
  - Desayuno
  - Almuerzo
  - Merienda
  - Cena.
- Al registrar las comidas se tomará y guardará automáticamente la fecha y la hora de dicho registro.
- En la pantalla principal el paciente podrá consultar las comidas registradas pulsando en el recuadro de alguna en particular para ver el detalle, también podrá consultar las comidas consumidas en fechas anteriores.
- El paciente podrá mantener la sesion abierta solamente saliendo de la aplicación desde la pantalla principal.
- La información relacionada con el paciente se almacena en una base de datos local en el telefono y en una base de datos externa para poder ser consultada por el centro de nutrición desde su plataforma.
- La información relacionada con las comidas consumidas por el paciente se almacena en una base de datos externa para poder ser consultada por el centro de nutrición desde su plataforma.
- Como premio por haber utilizado la aplicación, la misma tiene que ofrecer un trago al registar la comida, mostrando su imagen y características, dicha información se obtiene al consumir la información desde una API REST de tragos. 

Este sistema se desarrollo utilizando las siguientes tecnologías y herramientas:
- Herramientas de desarrollo: Android Studio
- Lenguaje utilizado: Kotlin
- Arquitectura: MVVM
- Motor de base de datos: SQLite para la base de datos local y Firestore de Firebase para la base de datos externa
- Librerías y APIs: Glide, Retrofit y Gson
- UnitTest: Robolectric
- InstrumentedTest: Espresso
