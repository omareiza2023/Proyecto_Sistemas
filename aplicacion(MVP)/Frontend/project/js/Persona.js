
const apiGetUrl = 'http://localhost:8081/api/persona/get';
const apiPostUrl = 'http://localhost:8081/api/persona/post';
const apiPutUrl = 'http://localhost:8081/api/persona/put';
const apiDeleteUrl = 'http://localhost:8081/api/persona/delete';

// Función para crear o actualizar una persona
function createOrUpdate() {
    const id = $('#id').val();
    const namePersona = $('#name_persona').val();
    const mailPersona = $('#mail_persona').val();
    const numPersona = $('#num_persona').val();
    const direccion = $('#direccion').val();

    const data = {
        namePersona,
        mailPersona,
        numPersona,
        direccion
    };
    console.log(data)
    if (id) {
        // Si existe un ID, actualizamos la persona
        $.ajax({
            url: `${apiPutUrl}/${id}`,
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: 'application/json',
            
            success: function(response) {
                alert('Persona actualizada correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al actualizar persona');
            }
        });
    } else {
        // Si no existe un ID, creamos una nueva persona
        $.ajax({
            url: apiPostUrl,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                alert('Persona creada correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al crear persona');
            }
        });
    }
}

// Función para cargar los datos de personas en la tabla
function loadData() {
    console.log("HELLO WORD")
    $.ajax({
        url: apiGetUrl,
        type: 'GET',
        success: function(data) {
            let tableContent = '';
            data.forEach(persona => {
                tableContent += `
                    <tr>
                        <td>${persona.id}</td>
                        <td>${persona.namePersona}</td>
                        <td>${persona.mailPersona}</td>
                        <td>${persona.numPersona}</td>
                        <td>${persona.direccion}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editPerson(${persona.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="deletePerson(${persona.id})">Eliminar</button>
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

// Función para editar una persona
function editPerson(id) {
    $.ajax({
        url: `${apiGetUrl}/${id}`,
        type: 'GET',
        success: function(persona) {
            $('#id').val(persona.id);
            $('#name_persona').val(persona.namePersona);
            $('#mail_persona').val(persona.mailPersona);
            $('#num_persona').val(persona.numPersona);
            $('#direccion').val(persona.direccion);
        },
        error: function(error) {
            alert('Error al obtener los datos de la persona');
        }
    });
}

// Función para eliminar una persona
function deletePerson(id) {
    if (confirm('¿Está seguro de que desea eliminar esta persona?')) {
        $.ajax({
            url: `${apiDeleteUrl}/${id}`,
            type: 'DELETE',
            headers: {'Access-Control-Allow-Origin': '*'},
            success: function(response) {
                alert('Persona eliminada correctamente');
                loadData(); // Recargar los datos
            },
            error: function(error) {
                alert('Error al eliminar persona');
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
