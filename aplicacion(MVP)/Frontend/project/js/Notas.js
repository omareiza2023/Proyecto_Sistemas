const apiGetUrl = 'http://localhost:8081/api/notas/get';
const apiPostUrl = 'http://localhost:8081/api/notas/post';
const apiPutUrl = 'http://localhost:8081/api/notas/put';
const apiDeleteUrl = 'http://localhost:8081/api/notas/delete';

// Función para crear o actualizar una nota
function createOrUpdate() {
    const id = $('#id').val();
    const corte1 = $('#corte_1').val();
    const corte2 = $('#corte_2').val();
    const corte3 = $('#corte_3').val();
    const materia = $('#materia_id').val();
    const usuario = $('#usuario_id').val();

    const data = {
        corte1,
        corte2,
        corte3,
        materia,
        usuario
    };

    if (id) {
        // Si existe un ID, actualizamos la nota
        $.ajax({
            url: `${apiPutUrl}/${id}`,
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                alert('Nota actualizada correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al actualizar nota');
            }
        });
    } else {
        // Si no existe un ID, creamos una nueva nota
        $.ajax({
            url: apiPostUrl,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                alert('Nota creada correctamente');
                loadData(); // Recargar los datos
                resetForm();
            },
            error: function(error) {
                alert('Error al crear nota');
            }
        });
    }
}

// Función para cargar los datos de notas en la tabla
function loadData() {
    $.ajax({
        url: apiGetUrl,
        type: 'GET',
        success: function(data) {
            let tableContent = '';
            data.forEach(nota => {
                tableContent += `
                    <tr>
                        <td>${nota.id}</td>
                        <td>${nota.corte1}</td>
                        <td>${nota.corte2}</td>
                        <td>${nota.corte3}</td>
                        <td>${nota.materia}</td>
                        <td>${nota.usuario}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editNota(${nota.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteNota(${nota.id})">Eliminar</button>
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

// Función para editar una nota
function editNota(id) {
    $.ajax({
        url: `${apiGetUrl}/${id}`,
        type: 'GET',
        success: function(nota) {
            $('#id').val(nota.id);
            $('#corte_1').val(nota.corte1);
            $('#corte_2').val(nota.corte2);
            $('#corte_3').val(nota.corte3);
            $('#materia_id').val(nota.materia);
            $('#usuario_id').val(nota.usuario);
        },
        error: function(error) {
            alert('Error al obtener los datos de la nota');
        }
    });
}

// Función para eliminar una nota
function deleteNota(id) {
    if (confirm('¿Está seguro de que desea eliminar esta nota?')) {
        $.ajax({
            url: `${apiDeleteUrl}/${id}`,
            type: 'DELETE',
            success: function(response) {
                alert('Nota eliminada correctamente');
                loadData(); // Recargar los datos
            },
            error: function(error) {
                alert('Error al eliminar nota');
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
