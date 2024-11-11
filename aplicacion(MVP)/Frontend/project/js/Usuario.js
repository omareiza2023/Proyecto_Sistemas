const apiGetUrl = 'http://localhost:8081/api/usuario/get';
const apiPostUrl = 'http://localhost:8081/api/usuario/post';
const apiPutUrl = 'http://localhost:8081/api/usuario/put';
const apiDeleteUrl = 'http://localhost:8081/api/usuario/delete';
// Función para crear o actualizar un usuario
function createOrUpdate() {
    const id = $('#id').val();
    const username = $('#username').val();
    const password = $('#password').val();
    const rol = $('#rol_id').val();
    const personaId = $('#persona_id').val();

    const data = {
        username,
        password,
        rol: {
            id: parseInt(rol) 
        },
        personaId: {
            id: parseInt(personaId) 
        }
    };

    if (id) {
        // Si existe un ID, actualizamos el usuario
        $.ajax({
            url: `${apiPutUrl}/${id}`,
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                alert('Usuario actualizado correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al actualizar usuario');
            }
        });
    } else {
        // Si no existe un ID, creamos un nuevo usuario
        $.ajax({
            url: apiPostUrl,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                alert('Usuario creado correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al crear usuario');
            }
        });
    }
}

// Función para cargar los datos de usuarios en la tabla
function loadData() {
    $.ajax({
        url: apiGetUrl,
        type: 'GET',
        success: function(data) {
            let tableContent = '';
            data.forEach(usuario => {
                tableContent += `
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.username}</td>
                        <td>${usuario.rol.id}</td>
                        <td>${usuario.personaId.id}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editUsuario(${usuario.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteUsuario(${usuario.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
            });
            $('#dataBody').html(tableContent);
        },
        error: function(error) {
            alert('Error al cargar los datos');
        }
    });
}

// Función para editar un usuario
function editUsuario(id) {
    $.ajax({
        url: `${apiGetUrl}/${id}`,
        type: 'GET',
        success: function(usuario) {
            $('#id').val(usuario.id);
            $('#username').val(usuario.username);
            $('#password').val(usuario.password);
            $('#rol_id').val(usuario.rol);
            $('#persona_id').val(usuario.personaId);
        },
        error: function(error) {
            alert('Error al obtener los datos del usuario');
        }
    });
}

// Función para eliminar un usuario
function deleteUsuario(id) {
    if (confirm('¿Está seguro de que desea eliminar este usuario?')) {
        $.ajax({
            url: `${apiDeleteUrl}/${id}`,
            type: 'DELETE',
            success: function(response) {
                alert('Usuario eliminado correctamente');
                loadData(); // Recargar los datos
            },
            error: function(error) {
                alert('Error al eliminar usuario');
            }
        });
    }
}

// Función para resetear el formulario
function resetForm() {
    $('#crudForm')[0].reset();
    $('#id').val('');
}

// Cargar los datos cuando la página se carga
$(document).ready(function() {
    loadData();
});
