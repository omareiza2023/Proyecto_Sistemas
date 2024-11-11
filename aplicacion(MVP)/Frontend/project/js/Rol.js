const apiGetUrl = 'http://localhost:8081/api/rol/get';
const apiPostUrl = 'http://localhost:8081/api/rol/post';
const apiPutUrl = 'http://localhost:8081/api/rol/put';
const apiDeleteUrl = 'http://localhost:8081/api/rol/delete';
// Función para crear o actualizar un rol
function createOrUpdate() {
    const id = $('#id').val();
    const rolName = $('#rol_name').val();
    const description = $('#description').val();

    const data = {
        rolName,
        description
        
    };
    console.log(data.rolName)
    if (id) {
        // Si existe un ID, actualizamos el rol
        $.ajax({
            url: `${apiPutUrl}/${id}`,
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                alert('Rol actualizado correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al actualizar rol');
            }
        });
    } else {
        $.ajax({
            url: apiPostUrl,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',

            success: function(response) {
                alert('Rol creado correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al crear rol');
            }
        });
    }
}

// Función para cargar los datos de roles en la tabla
function loadData() {
    console.log("HELLO WORD")

    $.ajax({
        url: apiGetUrl,
        type: 'GET',
        success: function(data) {
            let tableContent = '';
            data.forEach(rol => {
                tableContent += `
                    <tr>
                        <td>${rol.id}</td>
                        <td>${rol.rolName}</td>
                        <td>${rol.description}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editRol(${rol.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteRol(${rol.id})">Eliminar</button>
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

// Función para editar un rol
function editRol(id) {
    $.ajax({
        url: `${apiGetUrl}/${id}`,
        type: 'GET',
        success: function(rol) {
            $('#id').val(rol.id);
            $('#rol_name').val(rol.rolName);
            $('#description').val(rol.description);
        },
        error: function(error) {
            alert('Error al obtener los datos del rol');
        }
    });
}

// Función para eliminar un rol
function deleteRol(id) {
    if (confirm('¿Está seguro de que desea eliminar este rol?')) {
        $.ajax({
            url: `${apiDeleteUrl}/${id}`,
            type: 'DELETE',
            success: function(response) {
                alert('Rol eliminado correctamente');
                loadData(); // Recargar los datos
            },
            error: function(error) {
                alert('Error al eliminar rol');
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
