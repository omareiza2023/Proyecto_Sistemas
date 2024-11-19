const apiGetUrl = 'http://localhost:8081/api/usuario/get';

// Función para realizar la validación de inicio de sesión
function login() {
    const usernameInput = $('#username').val();
    const passwordInput = $('#password').val();

    $.ajax({
        url: apiGetUrl,
        type: 'GET',
        success: function(usuarios) {
            // Buscar el usuario ingresado en la lista de usuarios
            const usuario = usuarios.find(u => u.username === usernameInput);

            if (usuario) {
                // Validar si la contraseña ingresada coincide
                if (usuario.password === passwordInput) {
                    alert('Inicio de sesión exitoso');
                    
                    // Redireccionar según el rol del usuario
                    if (usuario.rol.rolName === "Estudiante") {
                        window.location.href = '../project/view/Estudiante.html';//estudiante
                    } else if (usuario.rol.rolName === "Profesor") {
                        window.location.href = '../project/view/Profesor.html';// profe
                    } else if (usuario.rol.rolName === "Administrador") {
                        window.location.href = '../project/view/administrador.html'; //admin
                    } else {
                        alert('Rol de usuario no reconocido');
                    }
                } else {
                    alert('Contraseña incorrecta');
                }
            } else {
                alert('Usuario no encontrado');
            }
        },
        error: function(error) {
            alert('Error al conectar con el servidor');
        }
    });
}

// Evento de envío del formulario de inicio de sesión
$(document).ready(function() {
    $('#loginForm').on('submit', function(event) {
        event.preventDefault(); // Evitar que el formulario se envíe normalmente
        login(); // Llamar a la función de inicio de sesión
    });
});


